package com.dt.module.mybatiesdemo.service;

import java.util.List;

import com.dt.module.mybatiesdemo.entity.DtUser;

/** 
 * @author: algernonking
 * @date: 2018年7月22日 上午9:17:25 
 * @Description: TODO 
 */
public interface IDtUserService {

	public int insertUser(DtUser user);
	 
	 public List<DtUser> selectUserList(DtUser user);
	 
	 public int deleteUserByUsername(String usrname);
}

