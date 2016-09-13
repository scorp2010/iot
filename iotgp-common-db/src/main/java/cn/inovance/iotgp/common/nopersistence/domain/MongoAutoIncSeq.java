/**
 * Project Name:iotgp-common-db
 * File Name:MongoAutoIncSeq.java
 * Package Name:cn.inovance.iotgp.common.nopersistence.domain
 * Date:2015-3-19下午4:29:17
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.nopersistence.domain;
/**
 * ClassName:MongoAutoIncSeq <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-19 下午4:29:17 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class MongoAutoIncSeq {

	 /** mongodb id */
	private Oid _id;
		
	private String seqName;
	
	private Long curSeq;

	public Oid get_id() {
		return _id;
	}

	public void set_id(Oid _id) {
		this._id = _id;
	}
	public String getSeqName() {
		return seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	public Long getCurSeq() {
		return curSeq;
	}

	public void setCurSeq(Long curSeq) {
		this.curSeq = curSeq;
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

