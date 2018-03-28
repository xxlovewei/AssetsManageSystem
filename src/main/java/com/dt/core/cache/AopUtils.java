package com.dt.core.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.asm.*;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AopUtils {

	public static String[] getMethodParamNames(final Method m) {
		final String[] paramNames = new String[m.getParameterTypes().length];
		final String n = m.getDeclaringClass().getName();
		final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		String className = m.getDeclaringClass().getSimpleName();
		ClassReader cr = null;
		InputStream resourceAsStream = null;
		try {
			// cr = new ClassReader(n);
			// String filePathName =
			// Class.forName(n).getResource("EDayHqbProcessManagerImpl.class").getPath();
			resourceAsStream = Class.forName(n).getResourceAsStream(className + ".class");
			cr = new ClassReader(resourceAsStream);
			// cr = new ClassReader(ClassLoader.getSystemResourceAsStream(n +
			// ".class"));
		} catch (IOException e) {
			// e.printStackTrace();
			// Exceptions.uncheck(e);
		} catch (ClassNotFoundException e) {
			// e.printStackTrace();
		} finally {
			if (resourceAsStream != null) {
				try {
					resourceAsStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		assert cr != null;
		cr.accept(new ClassVisitor(Opcodes.ASM4, cw) {
			@Override
			public MethodVisitor visitMethod(final int access, final String name, final String desc,
					final String signature, final String[] exceptions) {
				final Type[] args = Type.getArgumentTypes(desc);
				// 方法名相同并且参数个数相同
				if (!name.equals(m.getName()) || !sameType(args, m.getParameterTypes())) {
					return super.visitMethod(access, name, desc, signature, exceptions);
				}
				MethodVisitor v = cv.visitMethod(access, name, desc, signature, exceptions);
				return new MethodVisitor(Opcodes.ASM4, v) {
					@Override
					public void visitLocalVariable(String name, String desc, String signature, Label start, Label end,
							int index) {
						int i = index - 1;
						// 如果是静态方法，则第一就是参数
						// 如果不是静态方法，则第一个是"this"，然后才是方法的参数
						if (Modifier.isStatic(m.getModifiers())) {
							i = index;
						}
						if (i >= 0 && i < paramNames.length) {
							paramNames[i] = name;
						}
						super.visitLocalVariable(name, desc, signature, start, end, index);
					}

				};
			}
		}, 0);

		return paramNames;
	}

	/**
	 * <p>
	 * 比较参数类型是否一致
	 * </p>
	 * 
	 * @param types
	 *            asm的类型({@link Type})
	 * @param clazzes
	 *            java 类型({@link Class})
	 * @return
	 */
	private static boolean sameType(Type[] types, Class<?>[] clazzes) {
		// 个数不同
		if (types.length != clazzes.length) {
			return false;
		}

		for (int i = 0; i < types.length; i++) {
			if (!Type.getType(clazzes[i]).equals(types[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 取得切面调用的方法
	 * 
	 * @param pjp
	 * @return
	 */
	public static Method getMethod(ProceedingJoinPoint pjp) {
		Signature sig = pjp.getSignature();
		MethodSignature msig = null;
		if (!(sig instanceof MethodSignature)) {
			throw new IllegalArgumentException("该注解只能用于方法");
		}
		msig = (MethodSignature) sig;
		Object target = pjp.getTarget();
		Method currentMethod = null;
		try {
			currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
		} catch (NoSuchMethodException e) {
		} catch (SecurityException e) {
		}
		return currentMethod;
	}

	public static List<String> getMatcher(String regex, String source) {
		List<String> list = new ArrayList<String>();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		while (matcher.find()) {
			list.add(matcher.group());
		}
		return list;
	}

	/**
	 * 取得注解参数 (?=exp) 匹配exp前面的位置 (?<=exp) 匹配exp后面的位置 (?!exp) 匹配后面跟的不是exp的位置
	 * (?<!exp) 匹配前面不是exp的位置
	 * 
	 * @param managers
	 * @return
	 */
	public static List<String> getAnnoParams(String source) {
		String regex = "(?<=\\{)(.+?)(?=\\})";
		return getMatcher(regex, source);
	}

}