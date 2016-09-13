package cn.inovance.iotgp.common.ketama;

import java.io.Serializable;
/**
 * Project Name:KetamaHashingAlgorithm
 * File Name:Node.java
 * Package Name:
 * Date:2014-7-11上午11:27:00
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

/**
 * ClassName:Node <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-7-11 上午11:27:00 <br/>
 * @author   z1979
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class Node implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nodeName;
	
	public Node(String name) {
		this.nodeName = name;
	}
	
	public String getName() {
		return this.nodeName;
	}
	
	@Override
	public String toString() {
		return this.nodeName;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Node) {
			return ((Node) obj).nodeName.equals(this.nodeName);
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.nodeName.hashCode();
	}
}
