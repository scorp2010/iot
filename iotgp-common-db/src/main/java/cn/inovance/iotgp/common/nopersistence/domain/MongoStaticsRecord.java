/**
 * Project Name:iotgp-common-db
 * File Name:MongoStaticsRecord.java
 * Package Name:cn.inovance.iotgp.common.nopersistence.domain
 * Date:2015-3-25下午2:46:06
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.nopersistence.domain;

import cn.inovance.iotgp.common.nopersistence.domain.MongoAutoIncSeq.Oid;

/**
 * ClassName:MongoStaticsRecord <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	用于记录和检索
 * Date:     2015-3-25 下午2:46:06 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class MongoStaticsRecord {

	 /** mongodb id */
		private Oid _id;
		/**
		 * 开始日期.
		 */
		private java.lang.Long startDate;
		/**
		 * 结束日期.
		 */
		private java.lang.Long endDate;
		/**
		 * log id
		 */
		private java.lang.Long id;
		/**
		 * 名称
		 */
		private java.lang.String name ;
		
		public java.lang.Long getStartDate() {
			return startDate;
		}
		public void setStartDate(java.lang.Long startDate) {
			this.startDate = startDate;
		}
		public java.lang.Long getEndDate() {
			return endDate;
		}
		public void setEndDate(java.lang.Long endDate) {
			this.endDate = endDate;
		}
		public java.lang.Long getId() {
			return id;
		}
		public void setId(java.lang.Long id) {
			this.id = id;
		}
        
		public java.lang.String getName() {
			return name;
		}
		public void setName(java.lang.String name) {
			this.name = name;
		}

		/**
		 * Mongodb会自动生成ObjectId
		 * @author fhp
		 *
		 */
		public class Oid{
			String $oid;
			public String get$oid() {
				return $oid;
			}
	 
			public void set$oid(String $oid) {
				this.$oid = $oid;
			}
			
		}
}

