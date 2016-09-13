/**
 * Project Name:iotgp-common-db
 * File Name:ClassUtils.java
 * Package Name:cn.inovance.iotgp.common.util
 * Date:2014-8-15上午9:36:43
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.util;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import cn.inovance.iotgp.common.domain.CdRegisterCode;

/**
 * ClassName:ClassUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-8-15 上午9:36:43 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 * @see
 */
public class ClassUtils {

	/**
	 * 获取属性名集合
	 * */
	public static Set<String> getFiledName(Class o) {
		Field[] fields = o.getDeclaredFields();
		Set<String> fieldNames = new HashSet<String>();
		for (int i = 0; i < fields.length; i++) {
			fieldNames.add(fields[i].getName());
		}
		return fieldNames;
	}
	
	public static void main (String[] args){
		System.out.print(ClassUtils.getFiledName(CdRegisterCode.class));
	}
}
