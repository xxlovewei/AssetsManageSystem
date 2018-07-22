package com.dt.module.mybatiesdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dt.module.mybatiesdemo.entity.DtUser;
import com.dt.module.mybatiesdemo.mapper.DtUserMapper;

/** 
 * @author: algernonking
 * @date: 2018年7月22日 上午9:17:25 
 * @Description: TODO 
 */
@Service
public class DtUserServiceImpl implements IDtUserService{

	@Autowired
    private DtUserMapper dtUserMapper;
	
	/* (non Javadoc) 
	 * @Title: insertUser
	 * @Description: TODO
	 * @param user
	 * @return 
	 * @see com.dt.module.mybatiesdemo.service.IDtUserService#insertUser(com.dt.module.mybatiesdemo.entity.DtUser) 
	 */
	@Override
	public int insertUser(DtUser user) {
		// TODO Auto-generated method stub
		return dtUserMapper.insertUser(user);
	}

	/* (non Javadoc) 
	 * @Title: selectUserList
	 * @Description: TODO
	 * @param user
	 * @return 
	 * @see com.dt.module.mybatiesdemo.service.IDtUserService#selectUserList(com.dt.module.mybatiesdemo.entity.DtUser) 
	 */
	@Override
	public List<DtUser> selectUserList(DtUser user) {
		// TODO Auto-generated method stub
		return dtUserMapper.selectUserList(user);
	}

	/* (non Javadoc) 
	 * @Title: deleteUserByUsername
	 * @Description: TODO
	 * @param usrname
	 * @return 
	 * @see com.dt.module.mybatiesdemo.service.IDtUserService#deleteUserByUsername(java.lang.String) 
	 */
	@Override
	public int deleteUserByUsername(String usrname) {
		// TODO Auto-generated method stub
		return dtUserMapper.deleteUserByUsername(usrname);
	}

}

