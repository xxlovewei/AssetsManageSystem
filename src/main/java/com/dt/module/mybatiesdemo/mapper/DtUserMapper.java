package com.dt.module.mybatiesdemo.mapper;

import java.util.List;

import com.dt.core.common.base.BaseEntity;
import com.dt.module.mybatiesdemo.entity.DtUser;

/** 
 * @author: algernonking
 * @date: 2018年7月22日 上午9:04:05 
 * @Description: TODO 
 */

public interface DtUserMapper{
	
	 public int insertUser(DtUser user);
	 
	 public List<DtUser> selectUserList(DtUser user);
	 
	 public int deleteUserByUsername(String usrname);
	 
	
}

