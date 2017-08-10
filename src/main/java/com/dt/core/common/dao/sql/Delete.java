package com.dt.core.common.dao.sql;

import java.util.ArrayList;
import java.util.HashMap;

import com.dt.core.common.dao.SpringDAO;

public class Delete extends DML implements ExecutableSQL  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3055198883033819683L;
	private String table=null;
	private String tableAlias=null;
	private DeleteWhere where=new DeleteWhere();
	
	public static Delete init()
	{
		return new Delete();
	}
	
	 
	
	public DeleteWhere where()
	{
		return this.where;
	}
	
	public Delete()
	{
		this.where.setParent(this);
	}
	
	public Delete from(String table,String alias)
	{
		this.table=table;
		this.tableAlias=alias;
		return this;
	}
	
	public Delete from(String table)
	{
		this.table=table;
		this.tableAlias=null;
		return this;
	}
	
	
	public String getSQL() {
		if(this.isEmpty()) return "";
		StringBuffer sql=new StringBuffer();
		sql.append(SQLKeyword.DELETE.toString()+SQLKeyword.SPACER.toString()+SQLKeyword.FROM.toString()+this.table);
		if(this.tableAlias!=null)
		{
			sql.append(SQLKeyword.SPACER.toString()+this.tableAlias+SQLKeyword.SPACER.toString());
		}
		if(!this.where().isEmpty())
		{
			where().setIgnorColon(ignorColon);
			sql.append(SQLKeyword.SPACER.toString()+this.where().getSQL());
		}
		return sql.toString();
	}

	
	public String getParamedSQL() {
		if(this.isEmpty()) return "";
		StringBuffer sql=new StringBuffer();
		sql.append(SQLKeyword.DELETE.toString()+SQLKeyword.SPACER.toString()+SQLKeyword.FROM.toString()+this.table);
		if(this.tableAlias!=null)
		{
			sql.append(SQLKeyword.SPACER.toString()+this.tableAlias+SQLKeyword.SPACER.toString());
		}
		if(!this.where().isEmpty())
		{
			where().setIgnorColon(ignorColon);
			sql.append(SQLKeyword.SPACER.toString()+this.where().getParamedSQL());
		}
		return sql.toString();
	}

	
	public Object[] getParams() {
		if(this.isEmpty()) return new Object[]{};
		ArrayList<Object> ps=new ArrayList<Object>();
		if(!this.where().isEmpty())
		{
			where().setIgnorColon(ignorColon);
			ps.addAll(Utils.toArrayList(this.where().getParams()));
		}
		return ps.toArray(new Object[ps.size()]);
	}

	
	public String getParamNamedSQL() {
		if(this.isEmpty()) return "";
		this.beginParamNameSQL();
		StringBuffer sql=new StringBuffer();
		sql.append(SQLKeyword.DELETE.toString()+SQLKeyword.SPACER.toString()+SQLKeyword.FROM.toString()+this.table);
		if(this.tableAlias!=null)
		{
			sql.append(SQLKeyword.SPACER.toString()+this.tableAlias+SQLKeyword.SPACER.toString());
		}
		if(!this.where().isEmpty())
		{
			where().setIgnorColon(ignorColon);
			sql.append(SQLKeyword.SPACER.toString()+this.where().getParamNamedSQL());
		}
		this.endParamNameSQL();
		return sql.toString();
	}

	
	public HashMap<String, Object> getNamedParams() {
		HashMap<String, Object> ps=new HashMap<String, Object>();
		if(this.isEmpty()) return ps;
		this.beginParamNameSQL();
		if(!this.where().isEmpty())
		{
			where().setIgnorColon(ignorColon);
			ps.putAll(this.where().getNamedParams());
		}
		this.endParamNameSQL();
		return ps;
	}

	
	public boolean isEmpty() {
		return this.table==null;
	}

	
	public boolean isAllParamsEmpty() {
		if(this.isEmpty()) return false;
		if(!this.where().isEmpty())
		{
			where().setIgnorColon(ignorColon);
			return this.where().isAllParamsEmpty();
		}
		return true;
	}

	
	public boolean isAllParamsEmpty(boolean isCE)
	{
		return isAllParamsEmpty();
	}

	//==================================
	
	private SpringDAO dao= null;
 
	public SpringDAO getDao()
	{
		return dao;
	}
	public Delete setDao(SpringDAO dao) {
		this.dao = dao;
		return this;
	}



	public Integer execute()
	{
		return dao.execute(this);
	}
	
 

}
