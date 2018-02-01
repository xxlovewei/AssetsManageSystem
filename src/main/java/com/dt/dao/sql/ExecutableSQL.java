package com.dt.dao.sql;

import com.dt.dao.SpringDAO;


 

public interface ExecutableSQL extends SQL
{
	public Integer execute();
	public SpringDAO getDao() ;
	public ExecutableSQL setDao(SpringDAO dao);
}
