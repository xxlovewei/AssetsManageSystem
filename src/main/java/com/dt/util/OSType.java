package com.dt.util;

public enum OSType {
	Windows, Linux,Mac, Unknow;

	public static OSType getOSType() {
		String osName = System.getProperties().get("os.name").toString().toUpperCase();
		for(OSType os:OSType.values())
		{
			if(osName.indexOf(os.name().toUpperCase())!=-1) 
			{
				return os;
			}
		}
		return Unknow;

	}
	
	public static OSType parse(String str)
	{
		if(str==null) return OSType.Unknow;
		for(OSType os:OSType.values())
		{
			if(str.equalsIgnoreCase(os.name()))
			{
				return os;
			}
		}
		return Unknow;
	}
	
}
