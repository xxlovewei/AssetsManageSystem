package com.dt.core.common.dao.sql;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.dt.core.common.dao.SpringDAO;
import com.dt.core.common.util.support.TypedHashMap;

/**
 * 过程和函数都是用这个类进行处理
 * */
public class Procedure extends SubSQL  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -588109545247394266L;
	public static final String RETURN="RETURN";
	private String name;
	private boolean isFunction=false;

	public boolean isFunction() {
		return isFunction;
	}

	private ArrayList<String> parameterNames=new ArrayList<String>();
	private ArrayList<Object> parameterValues=new ArrayList<Object>();
	private ArrayList<ArgumentType> parameterTypes=new ArrayList<ArgumentType>();
	private ArrayList<ArgumentDataType> parameterDataTypes=new ArrayList<ArgumentDataType>();
	
	public Procedure(String name,boolean isFunction) {
		this.name=name;
		this.isFunction=isFunction;
		if(this.isFunction)
		{
			this.setParameter(RETURN,ArgumentType.OUT,ArgumentDataType.VARCHAR,RETURN);
		}
	}
	
	
	/**
	 * 设置参数：
	 * 设置参数时，setParameter的调用顺序要与实际参数的顺序一致
	 * */
	public Procedure setParameter(String name,ArgumentType paramType,ArgumentDataType dataType,Object value) {
		name=name.trim().toUpperCase();
		int i=parameterNames.indexOf(name);
		if(i==-1)
		{
			parameterNames.add(name);
			parameterValues.add(value);
			parameterTypes.add(paramType);
			parameterDataTypes.add(dataType);
		}
		else
		{
			parameterValues.set(i, value);
			parameterTypes.set(i, paramType);
			parameterDataTypes.set(i,dataType);
		}
		return this;
	}
	
	/**
	 * 当参数值不为null时设置参数，通过参数值自动识别参数类型：
	 * 设置参数时，setParameter的调用顺序要与实际参数的顺序一致
	 * */
	public Procedure setParameter(String name,ArgumentType paramType,Object value) {
		return setParameter(name, paramType, ArgumentDataType.getType(value), value);
	}
	
	
	/**
	 * 当参数值不为null时设置参数，通过参数值自动识别参数类型：
	 * 设置参数时，setParameter的调用顺序要与实际参数的顺序一致
	 * */
	public Procedure setInParameter(String name,Object value) {
		return setParameter(name, ArgumentType.IN, ArgumentDataType.getType(value), value);
	}
	
	/**
	 * 当参数值不为null时设置参数，通过参数值自动识别参数类型：
	 * 设置参数时，setParameter的调用顺序要与实际参数的顺序一致
	 * */
	public Procedure setOutParameter(String name,Object value) {
		return setParameter(name, ArgumentType.OUT, ArgumentDataType.getType(value), value);
	}
	
	/**
	 * 当参数值不为null时设置参数，通过参数值自动识别参数类型：
	 * 设置参数时，setParameter的调用顺序要与实际参数的顺序一致
	 * */
	public Procedure setInOutParameter(String name,Object value) {
		return setParameter(name, ArgumentType.INOUT, ArgumentDataType.getType(value), value);
	}
	 
	
	protected SE getSE() {
		String sql = getOrignalSQL();
		SE se=SE.get(sql,parameterValues.toArray());
		return se;
	}


	private String getOrignalSQL() {
		
		String sql="{ ";
		
		if(isFunction)
		{
			sql+="? =";
		}
		
		sql+=" call "+this.name +" (";
		for (int i = (isFunction?1:0); i < parameterNames.size(); i++) {
			sql+="?,";
		}
		
		if(parameterNames.size()>0)
		{
			sql=sql.substring(0,sql.length()-1);
		}
		
		sql +=")";
		sql += " } ";
		return sql;
		
	}
	
	
	public String getSQL() {
		return getSE().getSQL();
	}

	
	public String getParamedSQL() {
		return getSE().getParamedSQL();
	}
	
	
	public Object[] getParams() {
		return getSE().getParams();
	}
	
	public String getParamNamedSQL() {
		return getSE().getParamNamedSQL();
	}
	
	
	public HashMap<String, Object> getNamedParams() {
		return getSE().getNamedParams();
	}
	
	public boolean isEmpty() {
		return false;
	}
	
	public boolean isAllParamsEmpty(boolean isCE) {
		return false;
	}
 
	public TypedHashMap<String,Object> execute() throws Exception {
		System.out.println(this.getOrignalSQL());
		CallableStatement call=createCallableStatement(this.getOrignalSQL());
		int j=0;
		for (int i = 0; i < parameterNames.size(); i++) {
			j++;
			System.out.println(i+"\t"+j+"\t"+parameterDataTypes.get(i).name()+"\t"+parameterTypes.get(i));
			if(parameterTypes.get(i).isOut())
			{
				call.registerOutParameter(j, parameterDataTypes.get(i).getDbType());
			}
		}
		
		setCallableParameters(call);
		call.executeUpdate();
		TypedHashMap<String,Object> ret=new TypedHashMap<String,Object>();
		j=0;
		for (int i = 0; i < parameterNames.size(); i++) {
			j++;
			if(parameterTypes.get(i).isOut())
			{
				Object value=parameterDataTypes.get(i).getValue(call, j);
				ret.put(parameterNames.get(i), value);
			}
		}
		return ret;
	}
	
	
	
	private SpringDAO dao=null;
	
	 
	public SpringDAO getDao() {
		return dao;
	}
	
	 
	public Procedure setDao(SpringDAO dao) {
		this.dao=dao;
		return this;
	}
	
	private CallableStatement createCallableStatement(String sql) throws SQLException
	{
		Connection db=dao.getDataSource().getConnection();
		CallableStatement st=null;
		try {
			st = db.prepareCall(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return st;
	}
	
	private void setCallableParameters(CallableStatement st) throws Exception {
		int fld=0;
		for (int i = 0; i < parameterValues.size(); i++) 
		{
			fld++;
			if(isFunction && i==0)
			{
				
				continue; 
			}
				
			Object val=parameterValues.get(i);
 
			if(val==null)
			{
				ArgumentDataType adt=parameterDataTypes.get(i);
				st.setNull(fld, adt.getDbType());
			}
			else if(val instanceof String)
			{
				st.setString(fld, (String)val);
			}
			else if(val instanceof Character)
			{
				st.setString(fld, ((Character)val)+"");
			}
			else if(val instanceof Byte)
			{
				st.setByte(fld, (Byte)val);
			}
			else if(val instanceof Short)
			{
				st.setShort(fld, (Short)val);
			}
			else if(val instanceof Integer)
			{
				st.setInt(fld, (Integer)val);
			}
			else if(val instanceof Long)
			{
				st.setLong(fld, (Long)val);
			}
			else if(val instanceof Float)
			{
				st.setFloat(fld, (Float)val);
			}
			else if(val instanceof Double)
			{
				st.setDouble(fld, (Double)val);
			}
			else if(val instanceof BigDecimal)
			{
				st.setBigDecimal(fld, (BigDecimal)val);
			}
			else if(val instanceof java.sql.Date)
			{
				st.setDate(fld, (java.sql.Date)val);
			}
			else if(val instanceof java.util.Date)
			{
				st.setDate(fld, new java.sql.Date(((java.util.Date)val).getTime()));
			}
			else
			{
				throw new SQLException("Data Type Not Support:"+val.getClass().getName());
			}
			
		}
	}
 
	public static void main(String[] args) throws Exception {
		/*Procedure p=new Procedure("TEST_P",false);
		p.setParameter("A", ProcedureParameterType.BOTH,ProcedureParameterDataType.INTEGER,5);
		p.setParameter("B", ProcedureParameterType.IN,ProcedureParameterDataType.VARCHAR, "5XXX");
		p.setParameter("C", ProcedureParameterType.OUT,ProcedureParameterDataType.DATE,new Date());
		TypedHashMap<String, Object> ret=p.executeE();
		System.out.println(ret);*/
		
		
		
		/*Procedure f=new Procedure("TEST_F",true);
		f.setParameter("A", ProcedureParameterType.INOUT,ProcedureParameterDataType.INTEGER,5);
		f.setParameter("B", ProcedureParameterType.IN,ProcedureParameterDataType.VARCHAR, null);
		f.setParameter("C", ProcedureParameterType.OUT,ProcedureParameterDataType.DATE,new Date());
		TypedHashMap<String, Object> retF=f.executeE();
		System.out.println(retF);*/
		
		/*DAO dao=new DAO();
		CallableStatement call= dao.getConnection().prepareCall("{?=call TEST_F(?,?,?)}");
		call.registerOutParameter(1,Types.VARCHAR);
		call.registerOutParameter(2,Types.INTEGER);
		call.registerOutParameter(4,Types.DATE);
		
		call.setString(2,"9");
		call.setInt(3,9);
		call.setDate(4, new java.sql.Date(0));
		call.executeUpdate();*/
		
		
		/*Class.forName("oracle.jdbc.driver.OracleDriver");  
        Connection conn = DriverManager.getConnection(  
                "jdbc:oracle:thin:@192.168.202.98:1521:postest", "CROSSLINK", "CROSSLINK");  
        String procedure = "{?=call TEST_F(?,?,?)}";  
        procedure = "{ ? = call TEST_F ( ? , ? , ? ) }";  
        CallableStatement cs = conn.prepareCall(procedure);  
        cs.registerOutParameter(1, Types.VARCHAR);// 获得返回的字符串  
       
        
        
        cs.registerOutParameter(2, Types.INTEGER);// 获得返回的字符串
        cs.registerOutParameter(4, Types.DATE);// 获得返回的字符串  
        cs.setInt(2, 9); 
        cs.setString(3, "PPP");
        cs.setDate(4, new java.sql.Date(12));
        
        cs.executeUpdate();  
        String names = cs.getString(1);  
        Integer i = cs.getInt(2);
        java.sql.Date d = cs.getDate(4);
        
        System.out.println(names+"\t"+i+"\t"+d);  
        conn.close();*/
		
		
		/*Class.forName("oracle.jdbc.driver.OracleDriver");  
        Connection conn = DriverManager.getConnection(  
                "jdbc:oracle:thin:@192.168.202.98:1521:postest", "CROSSLINK", "CROSSLINK");  
        String procedure = "{?=call TEST_F(?,?,?)}";  
        CallableStatement cs = conn.prepareCall(procedure);  
        cs.registerOutParameter(1, Types.VARCHAR);// 获得返回的字符串  
        String sql = "select * from product";  
        cs.setString(2, sql);  
        cs.executeUpdate();  
        String names = cs.getString(1);  
        System.out.println(names);  
        conn.close(); */
		
	//	DAO dao=new DAO();
		/*TypedHashMap<String,Object> ret=dao.callProcedureE("XXB.UTILS.IF_EQ@SCM","XX","X");
		System.out.println(ret);*/
		//new Date()
	//	TypedHashMap<String,Object> ret2=dao.callProcedureByNameE("TEST_F",6,null,null);
	//	System.out.println(ret2);
		
		//dao.callProcedureE(SE.get("TEST_P(?,?,?)",6,"P2",new Date()));
	}
	

}
