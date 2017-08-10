package com.dt.core.common.dao.sql;

import java.math.BigDecimal;
import java.util.Date;

import com.dt.core.common.dao.Rcd;

public interface QueryableSQL 
{
	public Rcd record() ;
	
	public Integer intValue() ;
	
	public String stringValue() ;
	
	public Long longValue() ;
	
	public Date dateValue() ;
	
	public BigDecimal decimalValue() ;

}
