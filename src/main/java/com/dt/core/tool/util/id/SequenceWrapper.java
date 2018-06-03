package com.dt.core.tool.util.id;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ResourceBundle;

public class SequenceWrapper {
	
	private static ResourceBundle resb = ResourceBundle.getBundle("config.serverInfo");

	private static String workerId = null;
	private static String datacenterId = null;
	private static Sequence sequence = null;


	public static String getNextId() {
		if (null == sequence) {
			workerId = getWorkerId();
			System.out.println("workerId: " + workerId);
			datacenterId = getDatacenterId();
			System.out.println("datacenterId: " + datacenterId);
			sequence = new Sequence(Long.valueOf(workerId), Long.valueOf(datacenterId));
		}
		return String.valueOf(sequence.nextId());
	}
	


	private static String getWorkerId() {
		FileReader fileReader;
		StringBuffer sb = new StringBuffer();

		try {
			String myidPath = resb.getString("api.path.myid");
			System.out.println("读取myidPath为:" + myidPath);
			if ( null == myidPath || "".equals(myidPath) ) {
				sb.append("0");
			} else {
				fileReader = new FileReader(myidPath);
				int ch = 0;
				while ((ch = fileReader.read()) != -1) {
					sb.append((char) ch);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("api.path.myid文件没有找到!");
			//e.printStackTrace();
			sb.append("0");
		} catch (IOException e) {
			System.out.println("读取api.path.myid文件出错!");
			//e.printStackTrace();
			sb.append("0");
		} catch (Exception e) {
			System.out.println("其它异常,如resb读取api.path.myid异常");
			//e.printStackTrace();
			sb.append("0");
		}
		String result;
		result = sb.toString();
		System.out.println("result:" + result);
		return result;
	}
	
	
	private static String getDatacenterId() {
		FileReader fileReader;
		StringBuffer sb = new StringBuffer();

		try {
			String myidPath = resb.getString("api.path.myid");
			System.out.println("读取myidPath为:" + myidPath);
			if ( null == myidPath || "".equals(myidPath) ) {
				sb.append("0");
			} else {
				fileReader = new FileReader(myidPath);
				int ch = 0;
				while ((ch = fileReader.read()) != -1) {
					sb.append((char) ch);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("api.path.myid文件没有找到!");
			//e.printStackTrace();
			sb.append("0");
		} catch (IOException e) {
			System.out.println("读取api.path.myid文件出错!");
			//e.printStackTrace();
			sb.append("0");
		} catch (Exception e) {
			System.out.println("其它异常,如resb读取api.path.myid异常");
			//e.printStackTrace();
			sb.append("0");
		}
		String result;
		result = sb.toString();
		System.out.println("result:" + result);
		return result;
	}

}