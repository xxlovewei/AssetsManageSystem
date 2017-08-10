package com.dt.module.product.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dt.core.common.annotion.Acl;
import com.dt.core.common.annotion.Res;
import com.dt.core.common.annotion.impl.ResData;
import com.dt.core.common.base.BaseController;
import com.dt.core.common.dao.Rcd;
import com.dt.core.common.dao.RcdSet;
import com.dt.core.common.dao.sql.Insert;
import com.dt.core.common.dao.sql.Update;
import com.dt.core.db.DB;

/*前台商品类目管理*/
@Controller
public class CategoryForegroundController extends BaseController{

	@Autowired
	private DB db = null;
 
	//出发点:聚合后台类目数据，精准化营销,导购,导航等
	//后台类目的子节点为虚拟品类，不挂载具体属性模版，从后台属性模版中继承，
	//前台类目由后台叶子类目+属性组成(并且只能添加是枚举类型的属性)。
	

	public int getNextUserCatId(){
		String sql="select decode(max(id),null,1,max(id)+1) value from (select id from PRODUCT_CAT_USER_ROOT union all select id from PRODUCT_CAT_USER) ";
		return db.uniqueRecord(sql).getInteger("value");
	}
	
	

	@Res
	@Acl
	@RequestMapping("/api/categoryF/rootCatAdd.do")
	public ResData rootCatAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
	 
		Insert ins=new Insert("PRODUCT_CAT_USER_ROOT");
		
		String code=request.getParameter("CODE");
		if(code==null){
			return ResData.FAILURE("请输入编码");
		}
		
		if (db.uniqueRecord(" select count(1) value from PRODUCT_CAT_USER_ROOT where IS_DELETED='N' and code=? ",code).getInteger("value") >0 ){
			return ResData.FAILURE("该编码已使用");
		}
		
		
		ins.set("id", getNextUserCatId());  
		ins.set("is_deleted", "N");
		ins.set("is_used", request.getParameter("IS_USED"));
		ins.setIf("text", request.getParameter("TEXT"));
		ins.setIf("code", request.getParameter("CODE"));
		ins.setIf("mark", request.getParameter("MARK"));
		 
		db.execute(ins);
		return ResData.SUCCESS_OPER();
	}
	
	@Res
	@Acl
	@RequestMapping("/api/categoryF/rootCatDel.do")
	public ResData rootCatDel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//String id = request.getParameter("id");
	 
		return ResData.SUCCESS_OPER();
	}
	
	
	@Res
	@Acl
	@RequestMapping("/api/categoryF/rootCatUpdate.do")
	public ResData rootCatUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("ID");
		
		//更新的不允许更新code 
		
		Update ups=new Update("PRODUCT_CAT_USER_ROOT");
		ups.set("is_used", request.getParameter("IS_USED"));
		ups.setIf("text", request.getParameter("TEXT"));
		ups.setIf("mark", request.getParameter("MARK"));
		ups.where().and("id=?",id);
	 
		db.execute(ups);
		return ResData.SUCCESS_OPER();
	 
	}
	
	@Res
	@Acl(value = "allow")
	@RequestMapping("/api/categoryF/rootCatQuery.do")
	public ResData rootCatQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 
		String sql="select * from PRODUCT_CAT_USER_ROOT where is_deleted='N' order by od";
		return ResData.SUCCESS(db.query(sql).toJsonArrayWithJsonObject());
		
	}
	

	@Res
	@Acl(value = "allow")
	@RequestMapping("/api/categoryF/rootCatQueryById.do")
	public ResData rootCatQueryById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 
		
		String id=request.getParameter("ID");
		String sql="select * from PRODUCT_CAT_USER_ROOT where is_deleted='N' and id=? ";
		return ResData.SUCCESS(db.uniqueRecord(sql,id).toJsonObject());
		
	}
	
	
	
	
	
	@Res
	@Acl(value = "allow")
	@RequestMapping("/api/categoryF/queryTreeList.do")
	public ResData categoryFqueryTreeList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// resource type url
		String root_id=request.getParameter("ROOT_ID");
		
		JSONArray res = new JSONArray();
		
		String rootsql="select * from PRODUCT_CAT_USER_ROOT where ID=?  and is_deleted='N'";
		Rcd root_rs=db.uniqueRecord(rootsql,root_id);
		
		JSONObject root = new JSONObject();
		root.put("id", root_id);
		root.put("parent", "#");
		root.put("text", root_rs.getString("text"));
		root.put("is_cat", "N");
		root.put("type", "root");
		res.add(root);
		RcdSet rs = db.query("select * from PRODUCT_CAT_USER where root_id=? and is_deleted='N'",root_id);
		JSONObject e = new JSONObject();
		for (int i = 0; i < rs.size(); i++) {
			e = new JSONObject();
			e.put("id", rs.getRcd(i).getString("id"));
			e.put("text", rs.getRcd(i).getString("text"));
			e.put("is_cat", rs.getRcd(i).getString("is_cat"));
			if (rs.getRcd(i).getString("is_cat").equals("Y")) {
				e.put("type", "category");
			} else {
				e.put("type", "node");
			}

			e.put("parent", rs.getRcd(i).getString("parent_id"));
			res.add(e);
		}
		
		return ResData.SUCCESS(res);
	}
	
	

	@Res
	@Acl
	@Transactional
	@RequestMapping("/api/categoryF/add.do")
	public ResData categoryFadd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 类目下已经有品类后,则不能在添加层级，原则是:品类是类目的最后一级
		// 节点类型,root,node,category
		String node_type="node";
		String id = request.getParameter("ID");
		String action = request.getParameter("ACTION");// cate,node
		String text = request.getParameter("TEXT");
 
		
		
		String is_cat="N";
		if (action.equals("cate")){
			is_cat="Y";
			node_type="category";
		}else if (action.equals("node")){
			is_cat="N";
			node_type="node";
		}else{
			return ResData.FAILURE("选择正确的操作");
		}
		
		
		
		Insert ins=new Insert("PRODUCT_CAT_USER");
		int next_id=getNextUserCatId();
		ins.set("id",next_id );
		ins.set("is_deleted", "N");
		ins.setIf("text", text);
		ins.set("parent_id", id);
		ins.set("is_cat", is_cat);
		
		
		String curInfosql="";
		curInfosql=curInfosql+" select 'node' type,route,is_cat,root_id from PRODUCT_CAT_USER where id=? union all " ;
        curInfosql=curInfosql+" select 'root' type ,'' route,'' is_cat,0 root_id from PRODUCT_CAT_USER_ROOT where id=? ";
		Rcd cur_rs=db.uniqueRecord(curInfosql,id,id);
		if(cur_rs==null){
			return ResData.FAILURE_OPER();
		}
		
		if (cur_rs.getString("type").equals("root")){
			ins.set("root_id", id);
			ins.set("route",next_id);
			//本节点为根节点
		}else{
			//本节点不是根节点
			ins.set("root_id", cur_rs.getString("root_id"));
			ins.set("route", cur_rs.getString("route")+"-"+next_id);
			//如果当前是层级没有现在，如果是当前是品类，则无法在创建
			String cur_is_cat=cur_rs.getString("is_cat");
			if(cur_is_cat.equals("Y")){
				return ResData.FAILURE("当前层级下不允许在创建子节点");
			}
			
		}
	 
		db.execute(ins);
	 
		JSONObject e = new JSONObject();
		e.put("ID", next_id);
		e.put("TYPE", node_type);
		return ResData.SUCCESS("操作成功",e);
	}
 
	

	@Res
	@Acl 
	@RequestMapping("/api/categoryF/rename.do")
	public ResData categoryBrename(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String text = request.getParameter("text");
		if (id == null || text == null) {
			return ResData.FAILURE("请输入正确的参数");
		}

		String curInfosql="";
		curInfosql=curInfosql+" select 'node' type,route,is_cat,root_id from PRODUCT_CAT_USER where id=? union all " ;
        curInfosql=curInfosql+" select 'root' type ,'' route,'' is_cat,0 root_id from PRODUCT_CAT_USER_ROOT where id=? ";
		Rcd cur_rs=db.uniqueRecord(curInfosql,id,id);
		if(cur_rs==null){
			return ResData.FAILURE_OPER();
		}
		
		if (cur_rs.getString("type").equals("root")){
			return ResData.FAILURE("根节点不允许修改");
			//本节点为根节点
		}
		
		Update ups = new Update("PRODUCT_CAT_USER");
		ups.setIf("text", text);
		ups.where().and("id=?", id);
		db.execute(ups);
		return ResData.SUCCESS_OPER();
	}


}
