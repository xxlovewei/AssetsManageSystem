package com.dt.dao.sql;

import java.math.BigDecimal;
import java.util.Date;

import com.dt.dao.Rcd;

public interface QueryableSQL 
{
	public Rcd record() ;
	
	public Integer intValue() ;
	
	public String stringValue() ;
	
	public Long longValue() ;
	
	public Date dateValue() ;
	
	public BigDecimal decimalValue() ;

}
