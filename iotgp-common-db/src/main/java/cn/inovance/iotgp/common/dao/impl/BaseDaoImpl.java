/**
 * Project Name:bms
 * File Name:BaseDaoImpl.java
 * Package Name:cn.inovance.iotgp.bms.dao.impl
 * Date:2014-4-15上午10:11:15
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.BaseDao;
import cn.inovance.iotgp.common.dao.SuperBaseDao;
import cn.inovance.iotgp.common.filter.HqlFilter;

/**
 * ClassName:BaseDaoImpl.
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
@Repository
public class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired
	private SuperBaseDao<T> baseDao;
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 获得当前事物的session.
	 * 
	 * @return org.hibernate.Session
	 */
	@Override
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Serializable save(T o) {
		return baseDao.save(o);
	}

	@Override
	public void delete(T o) {
		baseDao.delete(o);
	}

	@Override
	public void update(T o) {
		baseDao.update(o);
	}

	@Override
	public void saveOrUpdate(T o) {
		baseDao.saveOrUpdate(o);
	}

	@Override
	public T getById(Serializable id) {
		Class<T> c = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		return baseDao.getById(c, id);
	}

	@Override
	public T getByHql(String hql) {
		return baseDao.getByHql(hql);
	}

	@Override
	public T getByHql(String hql, Map<String, Object> params) {
		return baseDao.getByHql(hql, params);
	}

	@Override
	public T getByFilter(HqlFilter hqlFilter) {
		String className = ((Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0]).getName();
		String hql = "select distinct t from " + className + " t";
		return getByHql(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public List<T> find() {
		return findByFilter(new HqlFilter());
	}

	@Override
	public List<T> find(String hql) {
		return baseDao.find(hql);
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		return baseDao.find(hql, params);
	}

	@Override
	public List<T> findByFilter(HqlFilter hqlFilter) {
		String className = ((Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0]).getName();
		String hql = "select distinct t from " + className + " t";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public List<T> find(String hql, int page, int rows) {
		return baseDao.find(hql, page, rows);
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params, int page,
			int rows) {
		return baseDao.find(hql, params, page, rows);
	}

	@Override
	public List<T> find(int page, int rows) {
		return findByFilter(new HqlFilter(), page, rows);
	}

	@Override
	public List<T> findByFilter(HqlFilter hqlFilter, int page, int rows) {
		String className = ((Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0]).getName();
		String hql = "select distinct t from " + className + " t";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public Long count(String hql) {
		return baseDao.count(hql);
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {
		return baseDao.count(hql, params);
	}

	@Override
	public Long countByFilter(HqlFilter hqlFilter) {
		String className = ((Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0]).getName();
		String hql = "select count(distinct t) from " + className + " t";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public Long count() {
		return countByFilter(new HqlFilter());
	}

	@Override
	public int executeHql(String hql) {
		return baseDao.executeHql(hql);
	}

	@Override
	public int executeHql(String hql, Map<String, Object> params) {
		return baseDao.executeHql(hql, params);
	}

	@Override
	public List findBySql(String sql) {
		return baseDao.findBySql(sql);
	}

	@Override
	public List findBySql(String sql, int page, int rows) {
		return baseDao.findBySql(sql, page, rows);
	}

	@Override
	public List findBySql(String sql, Map<String, Object> params) {
		return baseDao.findBySql(sql, params);
	}

	@Override
	public List findBySql(String sql, Map<String, Object> params, int page,
			int rows) {
		return baseDao.findBySql(sql, params, page, rows);
	}

	@Override
	public int executeSql(String sql) {
		return baseDao.executeSql(sql);
	}

	@Override
	public int executeSql(String sql, Map<String, Object> params) {
		return baseDao.executeSql(sql, params);
	}

	@Override
	public BigInteger countBySql(String sql) {
		return baseDao.countBySql(sql);
	}

	@Override
	public BigInteger countBySql(String sql, Map<String, Object> params) {
		return baseDao.countBySql(sql, params);
	}
	
	@Override
	public List<T> findObjListBySql(String sql, Map<String, Object> params
			, Map<String, Type> resultValueTypeMap, Class<T> entityClass) {
			SQLQuery query = getCurrentSession().createSQLQuery(sql);
			if (params != null && !params.isEmpty()) {
				for (String key : params.keySet()) {
					query.setParameter(key, params.get(key));
				}
			}
			if (resultValueTypeMap != null) {
		        Iterator it = resultValueTypeMap.keySet().iterator();
		        while (it.hasNext()) {
		           String key = (String)it.next();
		           query.addScalar(key, (Type)resultValueTypeMap.get(key));
		         }
		       }
			
			 
			 long start = Calendar.getInstance().getTimeInMillis();
		      List list = query.setResultTransformer(new AliasToBeanResultTransformer(entityClass)).list();
		      long end = Calendar.getInstance().getTimeInMillis();
		      System.out.println("queryListBySQLPage SQL: " + sql);
		      System.out.println("SQL took time: " + (end - start) + "millsec");

		     List localList1 = list;
		    return localList1; 
	}
	
	
	@Override
	public List<T> queryListByPage(String hql, Map paramMap
			) {
		
	       String strhql = " " + hql + " order ";
	       String hqlTemp = strhql.toLowerCase(Locale.getDefault());
	 
	       Query query =  getCurrentSession().createQuery(hql);
	      if (paramMap != null) {
	    	 Iterator it = paramMap.keySet().iterator();
	         while (it.hasNext()) {
	           Object key = it.next();
	          setParam(query, key.toString(), paramMap.get(key));
	         }
	       }
	       List localList1= query.list();
	       return localList1;
	}

	 public int getTotalCount(String hql, Map paramMap)
	   {
	       Query query = getCurrentSession().createQuery(hql);
	       if (paramMap != null) {
	    	 Iterator it = paramMap.keySet().iterator();
	         while (it.hasNext()) {
	           Object key = it.next();
	           setParam(query, key.toString(), paramMap.get(key));
	         }
	       }
	       List list = query.list();
	       int i =0;
	       if(null != list &&list.size() >=1)
		     {
		    	 i = Integer.parseInt(list.get(0).toString());
		     }
		      return i;
	 
	   }
	 
	  private void setParam(Query query, String key, Object value)
	  {
	     if ((value instanceof List))
	      query.setParameterList(key, (List)value);
	    else if (value.getClass().isArray())
	      query.setParameterList(key, (Object[])(Object[])value);
	    else
	       query.setParameter(key, value);
	  }
}
