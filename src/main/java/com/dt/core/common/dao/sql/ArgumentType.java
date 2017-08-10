package com.dt.core.common.dao.sql;

public enum ArgumentType {
	IN,OUT,INOUT;
	
	public boolean isOut()
	{
		return this==OUT || this==INOUT;
	}
	
	public boolean isIn()
	{
		return this==IN || this==INOUT;
	}
}
