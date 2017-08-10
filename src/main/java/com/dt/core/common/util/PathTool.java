package com.dt.core.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URL;

 

public class PathTool {
	 

	@SuppressWarnings("rawtypes")
	public static String get(Class cls) {
		String strURL="";
		try{
		String strClassName = cls.getName();
		String strPackageName = "";
		if (cls.getPackage() != null) {
			strPackageName = cls.getPackage().getName();
		}

		String strClassFileName = "";
		if (!"".equals(strPackageName)) {
			strClassFileName = strClassName.substring(
					strPackageName.length() + 1, strClassName.length());
		} else {
			strClassFileName = strClassName;
		}

		URL url = null;
		url = cls.getResource(strClassFileName + ".class");
		strURL = url.toString();
		strURL = strURL.substring(strURL.indexOf('/') + 1, strURL
				.lastIndexOf('/'));

		try {
			strURL = java.net.URLDecoder.decode(strURL, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		OSType os = OSType.getOSType(); 
		if (os == OSType.Windows) {

		} else if (os == OSType.Linux) {
			strURL = "/" + strURL;
		}
		} catch(Exception c)
		{

		}
		return strURL;
	}

	public static String getRealPathInWebApp(String relativePath) {
		String path = PathTool.get(PathTool.class);
		path = path.substring(0, path.indexOf("/WEB-INF/"));
		return path + "/" + relativePath;
	}
}
