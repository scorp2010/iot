package cn.inovance.iotgp.cdsm.util;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class ClassUtils {
	/**
	 * 获取属性名集合
	 * */
	@SuppressWarnings("rawtypes")
	public static Set<String> getFiledName(Class o) {
		Field[] fields = o.getDeclaredFields();
		Set<String> fieldNames = new HashSet<String>();
		for (int i = 0; i < fields.length; i++) {
			fieldNames.add(fields[i].getName());
		}
		return fieldNames;
	}

}
