package com.dt.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;

public class ArrayUtil {
	
	/**
	 * 把多个数组按顺序合并成一个数组
	 * */
	public static Object[] merege(Object[]... arrs)
	{
		int total=0;
		for(Object[] arr:arrs)
		{
			total+=arr.length;
		}
		Object[] newArr=new Object[total];
		
		int i=0;
		for(Object[] arr:arrs)
		{
			System.arraycopy(arr,0, newArr, i, arr.length);
			i+=arr.length;
		}
		return newArr;
	}
	
	/**
	 * 把元素合并到数组的最前面
	 * */
	public static Object[] meregeBefore(Object[] arr,Object... os)
	{
		 return merege(os,arr);
	}
	
	/**
	 * 把元素合并到数组的最后面
	 * */
	public static Object[] meregeAfter(Object[] arr,Object... os)
	{
		 return merege(arr,os);
	}
	
	/**
	 * 把几个数组合并成一个ArrayList
	 * */
	public static ArrayList<Object> toList(Object[] arr)
	{
		ArrayList<Object> ret = new ArrayList<Object>();
		for (int i = 0; i < arr.length; i++) {
			ret.add(arr[i]);
		}
		return ret;
	}
	
	/**
	 * 把几个数组合并成一个ArrayList
	 * */
	public static ArrayList<String> toStringList(String[] arr)
	{
		ArrayList<String> ret = new ArrayList<String>();
		for (int i = 0; i < arr.length; i++) {
			ret.add(arr[i]);
		}
		return ret;
	}
	
	public static boolean contains(Object[] arr,Object obj)
	{
		for(Object o:arr)
		{
			if(o.equals(obj)) return true;
		}
		return false;
	}
	
	public static boolean contains(String[] arr,String obj)
	{
		for(String o:arr)
		{
			if(o.equalsIgnoreCase(obj)) return true;
		}
		return false;
	}
	
	
	public static String[] castString(Object[] oarr)
	{
		if(oarr==null) return null;
		String[] arr=new String[oarr.length];
		System.arraycopy(oarr, 0, arr, 0, oarr.length);
		return arr;
	}
	
	public static Boolean[] castBoolean(Object[] oarr)
	{
		if(oarr==null) return null;
		Boolean[] arr=new Boolean[oarr.length];
		System.arraycopy(oarr, 0, arr, 0, oarr.length);
		return arr;
	}
	
	public static Byte[] castByte(Object[] oarr)
	{
		if(oarr==null) return null;
		Byte[] arr=new Byte[oarr.length];
		System.arraycopy(oarr, 0, arr, 0, oarr.length);
		return arr;
	}
	
	public static Short[] castShort(Object[] oarr)
	{
		if(oarr==null) return null;
		Short[] arr=new Short[oarr.length];
		System.arraycopy(oarr, 0, arr, 0, oarr.length);
		return arr;
	}
	
	
	public static Integer[] castInteger(Object[] oarr)
	{
		if(oarr==null) return null;
		Integer[] arr=new Integer[oarr.length];
		System.arraycopy(oarr, 0, arr, 0, oarr.length);
		return arr;
	}
	
	public static Long[] castLong(Object[] oarr)
	{
		if(oarr==null) return null;
		Long[] arr=new Long[oarr.length];
		System.arraycopy(oarr, 0, arr, 0, oarr.length);
		return arr;
	}
	
	public static Double[] castDouble(Object[] oarr)
	{
		if(oarr==null) return null;
		Double[] arr=new Double[oarr.length];
		System.arraycopy(oarr, 0, arr, 0, oarr.length);
		return arr;
	}
	
	public static Float[] castFloat(Object[] oarr)
	{
		if(oarr==null) return null;
		Float[] arr=new Float[oarr.length];
		System.arraycopy(oarr, 0, arr, 0, oarr.length);
		return arr;
	}
	
	public static BigDecimal[] castBigDecimal(Object[] oarr)
	{
		if(oarr==null) return null;
		BigDecimal[] arr=new BigDecimal[oarr.length];
		System.arraycopy(oarr, 0, arr, 0, oarr.length);
		return arr;
	}
	
	public static Date[] castDate(Object[] oarr)
	{
		if(oarr==null) return null;
		Date[] arr=new Date[oarr.length];
		System.arraycopy(oarr, 0, arr, 0, oarr.length);
		return arr;
	}
	
	public static void main(String[] args) {
		Object a[]={new JButton("1"),new JButton("2"),new JButton("3")};
		Object b[]={4,5,6,7};
		Object[] x=(Object[])merege(a,b);
		for (Object p:x) {
			System.out.println(p);
		}
		
	}
	
}
	
