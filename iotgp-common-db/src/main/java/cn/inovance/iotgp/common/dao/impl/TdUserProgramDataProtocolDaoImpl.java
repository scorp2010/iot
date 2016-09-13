/**
 * Project Name:iotgp-common-db
 * File Name:TdUserProgramDataProtocolDaoImpl.java
 * Package Name:cn.inovance.iotgp.common.dao.impl
 * Date:2014-9-5上午10:46:02
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.SuperBaseDao;
import cn.inovance.iotgp.common.dao.TdUserProgramDataProtocolDao;
import cn.inovance.iotgp.common.nopersistence.domain.TdUserProgramDataProtocol;

/**
 * ClassName:TdUserProgramDataProtocolDaoImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-9-5 上午10:46:02 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
@Repository
public class TdUserProgramDataProtocolDaoImpl implements TdUserProgramDataProtocolDao{

	@Resource
	SuperBaseDao<TdUserProgramDataProtocol> superBaseDao;
	
	public List<TdUserProgramDataProtocol> findByCdRegCodeAndType(String cdRegCode, int typeCode)
	{
		if(cdRegCode == null || cdRegCode.isEmpty()){
			return null;
		}
		
		String sql = "SELECT tmtd.td_type_code,tmtd.address_code,tmtct.type_name,tmtct.id FROM t_mms_target_device tmtd LEFT JOIN "+
	    " t_mms_td_controller_type tmtct on tmtct.id = tmtd.td_type_controller_id_fk LEFT JOIN "+
	    " where tmtd.cd_reg_code ='" + cdRegCode +"' and tmtd.td_type_code='" + String.valueOf(typeCode)+"'";
		
		List<Map> list = superBaseDao.findBySql(sql);
		
		List<TdUserProgramDataProtocol> tdUserProgramDataProtocolList = new ArrayList<TdUserProgramDataProtocol>(0);
		
		for(Map map:list){
			TdUserProgramDataProtocol  tdUserProgramDataProtocol = new TdUserProgramDataProtocol();
			
			if(map.get("id") != null )
			{
				tdUserProgramDataProtocol.setId(map.get("id").toString());
			}
			if(map.get("td_type_code") != null )
			{
				tdUserProgramDataProtocol.setTdControllerType(Integer.valueOf(map.get("td_type_code").toString()));
			}
			if(map.get("address_code") != null )
			{
				tdUserProgramDataProtocol.setTdAddressCode(Integer.valueOf(map.get("address_code").toString()));
			}
			if(map.get("type_name") != null )
			{
				tdUserProgramDataProtocol.setTypeName(map.get("type_name").toString());
			}
			tdUserProgramDataProtocolList.add(tdUserProgramDataProtocol);
		}
		return tdUserProgramDataProtocolList;
	}
}

