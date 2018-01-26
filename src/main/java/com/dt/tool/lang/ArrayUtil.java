package com.dt.tool.lang;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;

public class ArrayUtil {

	/**
	 * 把多个数组按顺序合并成一个数组
	 */
	public static Object[] merege(Object[]... arrs) {
		int total = 0;
		for (Object[] arr : arrs) {
			total += arr.length;
		}
		Object[] newArr = new Object[total];

		int i = 0;
		for (Object[] arr : arrs) {
			System.arraycopy(arr, 0, newArr, i, arr.length);
			i += arr.length;
		}
		return newArr;
	}

	/**
	 * 把元素合并到数组的最前面
	 */
	public static Object[] meregeBefore(Object[] arr, Object... os) {
		return merege(os, arr);
	}

	/**
	 * 把元素合并到数组的最后面
	 */
	public static Object[] meregeAfter(Object[] arr, Object... os) {
		return merege(arr, os);
	}

	/**
	 * 把几个数组合并成一个ArrayList
	 */
	public static ArrayList<Object> toList(Object[] arr) {
		ArrayList<Object> ret = new ArrayList<Object>();
		for (int i = 0; i < arr.length; i++) {
			ret.add(arr[i]);
		}
		return ret;
	}

	/**
	 * 把几个数组合并成一个ArrayList
	 */
	public static ArrayList<String> toStringList(String[] arr) {
		ArrayList<String> ret = new ArrayList<String>();
		for (int i = 0; i < arr.length; i++) {
			ret.add(arr[i]);
		}
		return ret;
	}

	public static boolean contains(Object[] arr, Object obj) {
		for (Object o : arr) {
			if (o.equals(obj))
				return true;
		}
		return false;
	}

	public static boolean contains(String[] arr, String obj) {
		for (String o : arr) {
			if (o.equalsIgnoreCase(obj))
				return true;
		}
		return false;
	}

	public static String[] castString(Object[] oarr) {
		if (oarr == null)
			return null;
		String[] arr = new String[oarr.length];
		System.arraycopy(oarr, 0, arr, 0, oarr.length);
		return arr;
	}

	public static Boolean[] castBoolean(Object[] oarr) {
		if (oarr == null)
			return null;
		Boolean[] arr = new Boolean[oarr.length];
		System.arraycopy(oarr, 0, arr, 0, oarr.length);
		return arr;
	}

	public static Byte[] castByte(Object[] oarr) {
		if (oarr == null)
			return null;
		Byte[] arr = new Byte[oarr.length];
		System.arraycopy(oarr, 0, arr, 0, oarr.length);
		return arr;
	}

	public static Short[] castShort(Object[] oarr) {
		if (oarr == null)
			return null;
		Short[] arr = new Short[oarr.length];
		System.arraycopy(oarr, 0, arr, 0, oarr.length);
		return arr;
	}

	public static Integer[] castInteger(Object[] oarr) {
		if (oarr == null)
			return null;
		Integer[] arr = new Integer[oarr.length];
		System.arraycopy(oarr, 0, arr, 0, oarr.length);
		return arr;
	}

	public static Long[] castLong(Object[] oarr) {
		if (oarr == null)
			return null;
		Long[] arr = new Long[oarr.length];
		System.arraycopy(oarr, 0, arr, 0, oarr.length);
		return arr;
	}

	public static Double[] castDouble(Object[] oarr) {
		if (oarr == null)
			return null;
		Double[] arr = new Double[oarr.length];
		System.arraycopy(oarr, 0, arr, 0, oarr.length);
		return arr;
	}

	public static Float[] castFloat(Object[] oarr) {
		if (oarr == null)
			return null;
		Float[] arr = new Float[oarr.length];
		System.arraycopy(oarr, 0, arr, 0, oarr.length);
		return arr;
	}

	public static BigDecimal[] castBigDecimal(Object[] oarr) {
		if (oarr == null)
			return null;
		BigDecimal[] arr = new BigDecimal[oarr.length];
		System.arraycopy(oarr, 0, arr, 0, oarr.length);
		return arr;
	}

	public static Date[] castDate(Object[] oarr) {
		if (oarr == null)
			return null;
		Date[] arr = new Date[oarr.length];
		System.arraycopy(oarr, 0, arr, 0, oarr.length);
		return arr;
	}

	public static void main(String[] args) {
		Object a[] = { new JButton("1"), new JButton("2"), new JButton("3") };
		Object b[] = { 4, 5, 6, 7 };
		Object[] x = (Object[]) merege(a, b);
		for (Object p : x) {
			System.out.println(p);
		}

	}
	// ----------------------------------------------------------------------
	// isEmpty

	/**
	 * 
	 * 数组是否为空
	 * 
	 * 
	 * 
	 * @param <T>
	 *            数组元素类型
	 * 
	 * @param array
	 *            数组
	 * 
	 * @return 是否为空
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static <T> boolean isEmpty(final T... array) {
		return array == null || array.length == 0;
	}

	/**
	 * 
	 * 数组是否为空<br>
	 * 
	 * 此方法会匹配单一对象，如果此对象为{@code null}则返回true<br>
	 * 
	 * 如果此对象为非数组，理解为此对象为数组的第一个元素，则返回false<br>
	 * 
	 * 如果此对象为数组对象，数组长度大于0情况下返回false，否则返回true
	 * 
	 * 
	 * 
	 * @param array
	 *            数组
	 * 
	 * @return 是否为空
	 * 
	 */
//	public static boolean isEmpty(final Object array) {
//		if (null == array) {
//			return true;
//		} else if (isArray(array)) {
//			return 0 == Array.getLength(array);
//		}
//		throw new UtilException("Object to provide is not a Array !");
//	}

	/**
	 * 
	 * 数组是否为空
	 * 
	 * 
	 * 
	 * @param array
	 *            数组
	 * 
	 * @return 是否为空
	 * 
	 */
	public static boolean isEmpty(final long... array) {
		return array == null || array.length == 0;
	}

	/**
	 * 
	 * 数组是否为空
	 * 
	 * 
	 * 
	 * @param array
	 *            数组
	 * 
	 * @return 是否为空
	 * 
	 */
	public static boolean isEmpty(final int... array) {
		return array == null || array.length == 0;
	}

	/**
	 * 
	 * 数组是否为空
	 * 
	 * 
	 * 
	 * @param array
	 *            数组
	 * 
	 * @return 是否为空
	 * 
	 */
	public static boolean isEmpty(final short... array) {
		return array == null || array.length == 0;
	}

	/**
	 * 
	 * 数组是否为空
	 * 
	 * 
	 * 
	 * @param array
	 *            数组
	 * 
	 * @return 是否为空
	 * 
	 */
	public static boolean isEmpty(final char... array) {
		return array == null || array.length == 0;
	}

	/**
	 * 
	 * 数组是否为空
	 * 
	 * 
	 * 
	 * @param array
	 *            数组
	 * 
	 * @return 是否为空
	 * 
	 */
	public static boolean isEmpty(final byte... array) {
		return array == null || array.length == 0;
	}

	/**
	 * 
	 * 数组是否为空
	 * 
	 * 
	 * 
	 * @param array
	 *            数组
	 * 
	 * @return 是否为空
	 * 
	 */
	public static boolean isEmpty(final double... array) {
		return array == null || array.length == 0;
	}

	/**
	 * 
	 * 数组是否为空
	 * 
	 * 
	 * 
	 * @param array
	 *            数组
	 * 
	 * @return 是否为空
	 * 
	 */
	public static boolean isEmpty(final float... array) {
		return array == null || array.length == 0;
	}

	/**
	 * 
	 * 数组是否为空
	 * 
	 * 
	 * 
	 * @param array
	 *            数组
	 * 
	 * @return 是否为空
	 * 
	 */
	public static boolean isEmpty(final boolean... array) {
		return array == null || array.length == 0;
	}

	// ----------------------------------------------------------------------
	// isNotEmpty

	/**
	 * 
	 * 数组是否为非空
	 * 
	 * 
	 * 
	 * @param <T>
	 *            数组元素类型
	 * 
	 * @param array
	 *            数组
	 * 
	 * @return 是否为非空
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static <T> boolean isNotEmpty(final T... array) {
		return (array != null && array.length != 0);
	}

	/**
	 * 
	 * 数组是否为非空<br>
	 * 
	 * 此方法会匹配单一对象，如果此对象为{@code null}则返回false<br>
	 * 
	 * 如果此对象为非数组，理解为此对象为数组的第一个元素，则返回true<br>
	 * 
	 * 如果此对象为数组对象，数组长度大于0情况下返回true，否则返回false
	 * 
	 * 
	 * 
	 * @param array
	 *            数组
	 * 
	 * @return 是否为非空
	 * 
	 */
	public static boolean isNotEmpty(final Object array) {
		return false == isEmpty((Object) array);
	}

	/**
	 * 
	 * 数组是否为非空
	 * 
	 * 
	 * 
	 * @param array
	 *            数组
	 * 
	 * @return 是否为非空
	 * 
	 */
	public static boolean isNotEmpty(final long... array) {
		return (array != null && array.length != 0);
	}

	/**
	 * 
	 * 数组是否为非空
	 * 
	 * 
	 * 
	 * @param array
	 *            数组
	 * 
	 * @return 是否为非空
	 * 
	 */
	public static boolean isNotEmpty(final int... array) {
		return (array != null && array.length != 0);
	}

	/**
	 * 
	 * 数组是否为非空
	 * 
	 * 
	 * 
	 * @param array
	 *            数组
	 * 
	 * @return 是否为非空
	 * 
	 */
	public static boolean isNotEmpty(final short... array) {
		return (array != null && array.length != 0);
	}

	/**
	 * 
	 * 数组是否为非空
	 * 
	 * 
	 * 
	 * @param array
	 *            数组
	 * 
	 * @return 是否为非空
	 * 
	 */
	public static boolean isNotEmpty(final char... array) {
		return (array != null && array.length != 0);
	}

	/**
	 * 
	 * 数组是否为非空
	 * 
	 * 
	 * 
	 * @param array
	 *            数组
	 * 
	 * @return 是否为非空
	 * 
	 */
	public static boolean isNotEmpty(final byte... array) {
		return (array != null && array.length != 0);
	}

	/**
	 * 
	 * 数组是否为非空
	 * 
	 * 
	 * 
	 * @param array
	 *            数组
	 * 
	 * @return 是否为非空
	 * 
	 */
	public static boolean isNotEmpty(final double... array) {
		return (array != null && array.length != 0);
	}

	/**
	 * 
	 * 数组是否为非空
	 * 
	 * 
	 * 
	 * @param array
	 *            数组
	 * 
	 * @return 是否为非空
	 * 
	 */
	public static boolean isNotEmpty(final float... array) {
		return (array != null && array.length != 0);
	}

	/**
	 * 
	 * 数组是否为非空
	 * 
	 * 
	 * 
	 * @param array
	 *            数组
	 * 
	 * @return 是否为非空
	 * 
	 */
	public static boolean isNotEmpty(final boolean... array) {
		return (array != null && array.length != 0);
	}

	/**
	 * 获取数组长度
	 * 
	 * @param array
	 * @return
	 */
	public static <T> int length(T[] array) {
		if (array == null || array.length == 0)
			return 0;
		return array.length;
	}

	/**
	 * 数组转String
	 * 
	 * @param array
	 * @return
	 */
	public static <T> String toString(T[] array) {
		StringBuffer sb = new StringBuffer("");
		if (isEmpty(array))
			return sb.toString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int i = 0;
		for (T t : array) {
			if ((t instanceof Integer) || (t instanceof Long) || (t instanceof Short) || (t instanceof Boolean)
					|| (t instanceof Byte) || (t instanceof String) || (t instanceof Character) || (t instanceof Float)
					|| (t instanceof Double) || (t instanceof Date)) {
				if (t instanceof Date) {
					sb.append((i++ == 0) ? sdf.format(t) : ("," + sdf.format(t)));
				} else {
					sb.append((i++ == 0) ? t.toString() : ("," + t.toString()));
				}
			} else {
				try {
					throw new Exception(
							"Array.toString()方法仅支持Integer,Short,Long,Boolean,Byte,String,Character,Float,Double,Date");
				} catch (Exception e) {
					e.printStackTrace();
					return sb.toString();
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * 是否包含{@code null}元素
	 * 
	 * 
	 * 
	 * @param <T>
	 *            数组元素类型
	 * 
	 * @param array
	 *            被检查的数组
	 * 
	 * @return 是否包含{@code null}元素
	 * 
	 * @since 3.0.7
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static <T> boolean hasNull(T... array) {
		if (isNotEmpty(array)) {
			for (T element : array) {
				if (null == element) {
					return true;
				}
			}
		}
		return false;
	}

	 
 
 
	
}
