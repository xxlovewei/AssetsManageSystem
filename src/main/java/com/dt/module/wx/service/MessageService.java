package com.dt.module.wx.service;

import org.springframework.stereotype.Service;

import com.dt.core.common.base.BaseService;
import com.dt.core.common.base.R;
import com.dt.core.dao.sql.Insert;
import com.dt.core.dao.sql.Update;
import com.dt.core.dao.util.TypedHashMap;
import com.dt.core.tool.util.DbUtil;
import com.dt.core.tool.util.ToolUtil;

/**
 * @author: jinjie
 * @date: 2018年5月7日 下午2:46:28
 * @Description: TODO
 */
@Service
public class MessageService extends BaseService {

    public R deleteMessage(String id) {
        Update me = new Update("wx_msg_def");
        me.setIf("dr", 1);
        me.where().and("id=?", id);
        db.execute(me);
        return R.SUCCESS_OPER();
    }

    public R updateMessage(TypedHashMap<String, Object> ps) {
        Update me = new Update("wx_msg_def");
        me.setIf("mark", ps.getString("mark", ""));
        me.setIf("code", ps.getString("code", ""));
        me.setIf("name", ps.getString("name", ""));
        me.setIf("funtype", ps.getString("funtype", ""));
        me.setIf("msgtype", ps.getString("msgtype", ""));
        me.setIf("value", ps.getString("value", ""));
        me.where().and("id=?", ps.getString("id"));
        db.execute(me);
        return R.SUCCESS_OPER();
    }

    // funtype:reply(自动回复),action(动作),push(推送类)
    // msgtype:text(普通消息),6(图文消息)
    public R addMessage(TypedHashMap<String, Object> ps) {
        Insert me = new Insert("wx_msg_def");
        me.set("dr", 0);
        me.set("id", db.getUUID());
        me.set("group_id", db.getUUID());
        me.setIf("mark", ps.getString("mark", ""));
        me.setIf("code", ps.getString("code", ""));
        me.setIf("funtype", ps.getString("funtype", ""));
        me.setIf("name", ps.getString("name", ""));
        me.setIf("msgtype", ps.getString("msgtype", ""));
        me.setIf("value", ps.getString("value", ""));
        db.execute(me);
        return R.SUCCESS_OPER();
    }

    public R queryMessageById(String id) {
        return R.SUCCESS_OPER(db.uniqueRecord("select * from wx_msg_def where id=?", id).toJsonObject());
    }

    public R queryMessages(String funtype) {
        String sql = "select * from wx_msg_def where dr=0 ";
        if (ToolUtil.isNotEmpty(funtype)) {
            sql = sql + " and funtype='" + funtype + "'";
        }
        sql = sql + " order by msgtype";
        return R.SUCCESS_OPER(db.query(sql).toJsonArrayWithJsonObject());
    }

    // 图文
    public R queryImageTextMessagesGroup() {
        return R.SUCCESS_OPER(
                db.query("select * from wx_msg_def where dr=0 and msgtype='6'").toJsonArrayWithJsonObject());
    }

    public R queryImageTextMessages(String group_id) {
        return R.SUCCESS_OPER(db.query("select * from wx_msg_imgitem where group_id=? and  dr=0 order by rn", group_id)
                .toJsonArrayWithJsonObject());
    }

    public R deleteImageTextMessage(String id) {
        Update me = new Update("wx_msg_imgitem");
        me.setIf("dr", 1);
        me.where().and("id=?", id);
        db.execute(me);
        return R.SUCCESS_OPER();
    }

    public R updateImageTextMessage(TypedHashMap<String, Object> ps) {
        Update me = new Update("wx_msg_imgitem");
        me.setIf("title", ps.getString("title", ""));
        me.setIf("msgdesc", ps.getString("msgdesc", ""));
        me.setIf("docurl", ps.getString("docurl", ""));
        me.setIf("imgurl", ps.getString("imgurl", ""));
        me.setIf("rn", ps.getString("rn", ""));
        me.setIf("mark", ps.getString("mark", ""));
        me.where().and("id=?", ps.getString("id"));
        db.execute(me);
        return R.SUCCESS_OPER();
    }

    public R addImageTextMessage(TypedHashMap<String, Object> ps) {
        Insert me = new Insert("wx_msg_imgitem");
        me.set("dr", 0);
        me.set("id", db.getUUID());
        me.setIf("title", ps.getString("title", ""));
        me.setIf("msgdesc", ps.getString("msgdesc", ""));
        me.setIf("docurl", ps.getString("docurl", ""));
        me.setIf("imgurl", ps.getString("imgurl", ""));
        me.setIf("group_id", ps.getString("group_id", ""));
        me.setIf("rn", ps.getString("rn", ""));
        me.setIf("mark", ps.getString("mark", ""));
        db.execute(me);
        return R.SUCCESS_OPER();
    }

    public R queryImageTextMessageById(String id) {
        return R.SUCCESS_OPER(db.uniqueRecord("select * from wx_msg_imgitem where id=?", id).toJsonObject());
    }

    //
    public R addSc(TypedHashMap<String, Object> ps) {
        Insert me = new Insert("wx_msg_sc");
        me.set("dr", 0);
        me.set("id", db.getUUID());
        me.set("pic_id", ps.getString("pic_id", ""));
        me.set("sctype", ps.getString("sctype", "image"));
        me.setSE("ctime", DbUtil.getDbDateString(db.getDBType()));
        db.execute(me);
        return R.SUCCESS_OPER();
    }

    public R queryScs() {
        return R.SUCCESS_OPER(
                db.query("select * from wx_msg_sc where dr=0 order by ctime desc").toJsonArrayWithJsonObject());
    }
}
