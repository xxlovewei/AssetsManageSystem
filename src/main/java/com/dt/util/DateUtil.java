package com.dt.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil
{
 
	public static long getSystemMilles()
	{
		return System.currentTimeMillis();
	}

	/**
	 * ��ȡ��ʽ��ϵͳʱ�� yyyy��MM��dd�� hh:mm:ss
	 */
	public static String getFormatTime()
	{
		String pattern = "yyyy��MM��dd�� hh:mm:ss";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(new Date());
	}

	private static SimpleDateFormat formatter = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static long MICRO_SECOND = 1;
	public static long SECOND = 1000;
	public static long MINUTE = 60000;
	public static long HOUR = 3600000;
	public static long DAY = 86400000;
	public static long WEEK = 604800000;

	/**
	 * ��ʽ��һ������
	 */
	public static String format(Date date, String format)
	{
		String mDateTime;
		try
		{
			formatter.applyPattern(format);
			mDateTime = formatter.format(date);
		} catch (Exception e)
		{
			formatter.applyPattern("yyyy-MM-dd HH:mm:ss");
			mDateTime = formatter.format(date);
		}
		return mDateTime;
	}
	public static String format(Date date)
	{
		if(date==null){
			return "";
		}
		return format(date,"yyyy-MM-dd");
	}
//	
//	public static Date parse(String date)
//	{
//		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
//		return formatter1.parse(date);
//	}

	/**
	 * �õ���ǰʱ�䣬���������գ�ʱ���룬yyyy,MM,dd,HH,mm,ss
	 */
	public static String getCurrTime(String format)
	{
		Calendar cal = Calendar.getInstance();
		return format(cal.getTime(), format);
	}

	public static int getCurrentYear()
	{

		return Integer.parseInt(getCurrTime("yyyy"));

	}

	public static int getCurrentMonth()
	{

		return Integer.parseInt(getCurrTime("MM"));

	}
	
	public static int getYearPart(Date dt)
	{
		return Integer.parseInt(format(dt, "yyyy"));
	}
	public static int getMonthPart(Date dt)
	{
		return Integer.parseInt(format(dt, "MM"));
	}
	public static int getDayPart(Date dt)
	{
		return Integer.parseInt(format(dt, "dd"));
	}
	public static int getHourPart(Date dt)
	{
		return Integer.parseInt(format(dt, "HH"));
	}
	public static int getMinutePart(Date dt)
	{
		return Integer.parseInt(format(dt, "mm"));
	}
	public static int getSecondPart(Date dt)
	{
		return Integer.parseInt(format(dt, "ss"));
	}

	/**
	 * �õ�ָ��������n�ܺ�����ڣ����w�Ǹ�ֵ���ʾn��ǰ ���籾������12��1�ţ���ô��n�ܵ�������ʱ���ţ�
	 */
	public static Date getSameDayByWeek(Date date, int w)
	{
		long myTime = date.getTime() + WEEK * w;
		date.setTime(myTime);
		return date;
	}

	/**
	 * @deprecated �õ�ָ���������ڵ�����һ���еĵڼ���
	 */
	public static int getWeekSNInYear(Date date, int w)
	{
		return 0;
	}

	/**
	 * @deprecated �õ�ָ���������ڵ����Ǳ����еĵڼ���
	 */
	public static int getWeekSNInMonth(Date date, int w)
	{
		return 0;
	}

 
	public static Date getSameDateByYear(Date date, int y)
	{
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		int year=cal.get(Calendar.YEAR);
		year=year+y;
		cal.set(Calendar.YEAR, year);
		return cal.getTime();
	}

	/**
	 * �õ���ǰָ�����������ڼ�
	 */
	public static String getChineseWeek(Calendar date)
	{
		final String dayNames[] =
		{ "������", "����һ", "���ڶ�", "������", "������", "������", "������" };
		int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
		System.out.println(dayNames[dayOfWeek - 1]);
		return dayNames[dayOfWeek - 1];

	}
	public static Date parse(String dateStr,String fmt)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		Date dt = null;
		try
		{
			dt = sdf.parse(dateStr);
		} catch (Exception e)
		{
			
		}
		return dt;
	}
	public static Date parse(String dateStr)
	{
		
		Date dt = parse(dateStr,"yyyy-MM-dd HH:mm:ss");
		if(dt==null)
		{
			dt = parse(dateStr,"yyyy/MM/dd HH:mm:ss");
		}
		
		if(dt==null)
		{
			dt = parse(dateStr,"yyyy-MM-dd");
		}
		
		if(dt==null)
		{
			dt = parse(dateStr,"yyyy/MM/dd");
		}
		
		 
		return dt;
	}

	public static void main(String[] args)
	{
		//Date d = getSameDayByWeek(new Date(), -2);

		//String t = getChineseWeek(Calendar.getInstance());// format(d,
		// "YYYY-MM-DD");

		//System.out.println(getTwoYear());
		
		
		System.out.println(getSameDateByYear(new Date(), -1));
		
		
		/*Date d=new Date();
		Date d2=DateUtil.addDay(d, -30);
		System.out.println(DateUtil.format(d,"yyyy-M-d"));
		System.out.println(DateUtil.format(d2,"yyyy-M-d"));*/
	}
	
	public static String formatTime(Timestamp time) {
		if (time == null) {
			return "";
		}
		return time.toString().substring(0,11);
	}
	/**
	 * ��ʽ��2008-10-08 �����ʽ��ʱ��?OCT, 08. 2008 
	 * @return
	 */
	public static String formateDate(String date) {
		if (date.length()!=10) {
			return "";
		}

		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM , Locale.US);
		String year = date.substring(0,4);
		String month=date.substring(5,7);
		String dayOFmonth=date.substring(8);
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(dayOFmonth) );
		return df.format(cal.getTime());

	}
	/**
	 * ��ʽ��2008-10-08 �����ʽ��ʱ��?OCT, 08. 2008 
	 * @return
	 */
	public static String formateDate(Date date) {
		return formateDate(format(date));

	}
	/**
	 * ��ȡ��ݵ���λ��?
	 * @return
	 */
	public static String getTwoYear() {
		Calendar rightNow = Calendar.getInstance();
		return (rightNow.get(Calendar.YEAR)+"").substring(2, 4);

	}
	/**
	 * ��õ�ǰʱ��?
	 * */
	public static Date getUtilDateNow()
	{
		return new Date();
	}
	
	/**
	 * ��õ�ǰʱ��?
	 * */
	public static java.sql.Date getSqlDateNow()
	{
		return new java.sql.Date(getUtilDateNow().getTime());
	}
	
	/**
	 * ��õ�ǰʱ��?
	 * */
	public static Timestamp getTimestampNow()
	{
		return new Timestamp(getUtilDateNow().getTime());
	}
	
	
	/**
	 * �������ǰ����������?
	 * */
	
	public static Date addDay(String datestr,String datefmt, int day) {  
        SimpleDateFormat df = new SimpleDateFormat(datefmt);  
        Date olddate = null;  
        try {  
            df.setLenient(false);  
            olddate = new Date(df.parse(datestr).getTime());  
        } catch (ParseException e) {  
            throw new RuntimeException("����ת������");  
        }  
        
        return addDay(olddate,day);  
    }
	
	

	/**
	 * �������ǰ����������?
	 * */
	public static Date addDay(Date olddate,int day)
	{
		Calendar cal = new GregorianCalendar();  
        cal.setTime(olddate);  
        int Year = cal.get(Calendar.YEAR);  
        int Month = cal.get(Calendar.MONTH);  
        int Day = cal.get(Calendar.DAY_OF_MONTH);  
  
        int NewDay = Day + day;  
        cal.set(Calendar.YEAR, Year);  
        cal.set(Calendar.MONTH, Month);  
        cal.set(Calendar.DAY_OF_MONTH, NewDay);  
  
        return new Date(cal.getTimeInMillis());
	}
	
	 
	
	
	
	
	public static Date addSecond(Date olddate,int sec)
	{
		long m=olddate.getTime();
		m+=sec*1000;
		return new Date(m);
	}
	
	/**
	 * �ж��Ƿ���ͬһ��
	 * */
	public static boolean isSameDay(Date d1,Date d2)
	{
		Calendar c1=Calendar.getInstance();
		c1.setTime(d1);
		

		Calendar c2=Calendar.getInstance();
		c2.setTime(d2);
		
		return c1.get(Calendar.YEAR)==c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH)==c2.get(Calendar.MONTH) && c1.get(Calendar.DATE)==c2.get(Calendar.DATE);
		
		
	}
	
	 
	

}
