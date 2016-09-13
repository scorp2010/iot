package cn.inovance.iotgp.common.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.CdAllocationLogDao;
import cn.inovance.iotgp.common.dao.SuperBaseDao;
import cn.inovance.iotgp.common.domain.CdAllocationLog;
/**
 * ClassName:CdAllocationLogDaoImpl <br/>
 * Date: 2014-10-13 上午11:44:20 <br/>
 * 
 * @author fb2112
 * @version
 * @since JDK 1.7
 * @see
 */
@Repository
public class CdAllocationLogDaoImpl extends BaseDaoImpl<CdAllocationLog> implements CdAllocationLogDao {

	@Autowired
	private SuperBaseDao superBaseDao;
	
	@Override
	public List<CdAllocationLog> getByCdRegCodeList(List<String> cdRegCodeList) {
		
		String hql = "from CdAllocationLog t where t.cdRegCode in (:cdRegCodeList)";
		
		Query q = superBaseDao.getCurrentSession().createQuery(hql);
		q.setParameterList("cdRegCodeList", cdRegCodeList);
		List<CdAllocationLog> list = q.list();
		return list;
	}

	@Override
	public int saveAll(List<CdAllocationLog> cdAllocationLogList) {
		
		 for(int i=0;i<cdAllocationLogList.size();i++){
			 CdAllocationLog cdAllocationLog = cdAllocationLogList.get(i);
			 superBaseDao.getCurrentSession().save(cdAllocationLog);
			 if(i % 20 == 0){
				 superBaseDao.getCurrentSession().flush();
				 superBaseDao.getCurrentSession().clear();
			 }
		 }
		
		
		return 0;
	}

	@Override
	public int updateAll(List<CdAllocationLog> cdAllocationLogList) {
		
		for(int i=0;i<cdAllocationLogList.size();i++){
			 CdAllocationLog cdAllocationLog = cdAllocationLogList.get(i);
			 superBaseDao.getCurrentSession().update(cdAllocationLog);
			 if(i % 20 == 0){
				 superBaseDao.getCurrentSession().flush();
				 superBaseDao.getCurrentSession().clear();
			 }
		 }
		return 0;
	}

	

}
