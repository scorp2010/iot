/**
 * Project Name:iotgp-common-db
 * File Name:TdUserProgramDataProtocol.java
 * Package Name:cn.inovance.iotgp.common.dao
 * Date:2014-9-5上午10:45:40
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.dao;

import java.util.List;

import cn.inovance.iotgp.common.nopersistence.domain.TdUserProgramDataProtocol;

/**
 * ClassName:TdUserProgramDataProtocol <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-9-5 上午10:45:40 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public interface TdUserProgramDataProtocolDao {

	public List<TdUserProgramDataProtocol> findByCdRegCodeAndType(String cdRegCode, int typeCode);
}

