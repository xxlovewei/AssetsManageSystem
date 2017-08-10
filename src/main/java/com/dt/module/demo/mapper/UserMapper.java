package com.dt.module.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.dt.module.demo.entity.User;

public interface UserMapper extends BaseMapper<User> {
	@Select("selectUserList")
	
	List<User> selectUserList(Pagination page, String state);
}
