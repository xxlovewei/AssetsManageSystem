package com.dt.core.common.dao.sql;

import com.dt.core.common.dao.SpringDAO;


 

public interface ExecutableSQL extends SQL
{
	public Integer execute();
	public SpringDAO getDao() ;
	public ExecutableSQL setDao(SpringDAO dao);
}
