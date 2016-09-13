package cn.inovance.iotgp.cdsm.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.cdsm.bean.PageRequest;

@Repository
public class BaseDao {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public final String[] months = { "jan", "feb", "mar", "apr", "may", "jun",
			"jul", "aug", "sep", "oct", "nov", "dec" };

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * through the id find the entity
	 * 
	 * @param clazz
	 *            the entity's type
	 * @param id
	 *            the Pk
	 * */
	public <T> T get(Class<T> clazz, Serializable id) {

		T o = (T) this.entityManager.find(clazz, id);
		if (o == null) {
			String msg = clazz + "' object with id '" + id + "' not found...";
			logger.warn(msg);
			throw new EntityNotFoundException(msg);
		}
		return o;
	}

	/**
	 * through one property find the entity
	 * 
	 * @param clazz
	 *            the entity's type
	 * @param field
	 *            the bean's field
	 * @param value
	 * */
	public <T> T getUnique(Class<T> clazz, String field, Object value) {
		Object obj = null;
		T entity = null;
		StringBuffer sbQuery = new StringBuffer();
		sbQuery.append("select o from ").append(clazz.getSimpleName())
				.append(" o");
		if (field != null && !"".equals(field))
			sbQuery.append(" where o.").append(field).append("=?");

		try {
			obj = entityManager.createQuery(sbQuery.toString())
					.setParameter(1, value).getSingleResult();
			entity = clazz.cast(obj);
		} catch (javax.persistence.NoResultException e) {
			entity = null;
		}
		return entity;
	}

	/**
	 * total numbers
	 * 
	 * @param hql
	 * @param Object
	 *            ... values the item's values
	 * @return Long count
	 * */

	public int countResult(final String hql, final Object... values) {
		String fromHql = hql;
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");

		String countHql = "select count(*) " + fromHql;

		try {
			Query query = queryHandle(countHql, values);
			int count = Integer
					.valueOf(query.getResultList().get(0).toString());
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql语句无法计数:" + countHql, e);
		}
	}

	public int countResult(final String hql, final Map<String, Object> params) {
		String fromHql = hql;
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");

		String countHql = "select count(*) " + fromHql;

		try {
			Query query = queryHandle(countHql, params);
			int count = Integer
					.valueOf(query.getResultList().get(0).toString());
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql语句无法计数:" + countHql, e);
		}
	}

	public int countResultSql(final String sql, final Object... values) {
		String fromSql = sql;
		fromSql = "from " + StringUtils.substringAfter(fromSql, "from");
		fromSql = StringUtils.substringBefore(fromSql, "order by");

		String countSql = "select count(*) as totalCount " + fromSql;

		try {
			Map<String, Object> map = queryHandleSingalSql(countSql, values);
			int count = Integer.valueOf(map.get("totalCount").toString());
			return count;
		} catch (Exception e) {
			throw new RuntimeException("sql语句无法计数:" + countSql, e);
		}
	}

	public int countResultSql(final String sql, final Map<String, Object> params) {
		String fromSql = sql;
		fromSql = "from " + StringUtils.substringAfter(fromSql, "from");
		fromSql = StringUtils.substringBefore(fromSql, "order by");

		String countSql = "select count(*) as totalCount " + fromSql;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> list = this.queryHandleSql(countSql,
					params);
			int count = 0;
			if (list != null && !list.isEmpty()) {
				map = list.get(0);
				count = Integer.valueOf(map.get("totalCount").toString());
			}
			return count;
		} catch (Exception e) {
			throw new RuntimeException("sql语句无法计数:" + countSql, e);
		}
	}

	/**
	 * gain a query object
	 * 
	 * */
	public Query queryHandle(String hql) {
		Query query = entityManager.createQuery(hql);
		return query;
	}

	/**
	 * gain a query object
	 * 
	 * */
	@SuppressWarnings("rawtypes")
	public Query queryHandle(String hql, final Object... values) {
		Query query = entityManager.createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if (values[i] instanceof List) {
					query.setParameter(i + 1, (Collection) values[i]);
				} else {
					query.setParameter(i + 1, values[i]);
				}
			}
		}
		return query;
	}

	@SuppressWarnings("rawtypes")
	public Query queryHandle(String hql, final Map<String, Object> params) {
		Query query = entityManager.createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, Object> param : params.entrySet()) {
				if (param.getValue() instanceof List) {
					query.setParameter(param.getKey(),
							(Collection) param.getValue());
				} else {
					query.setParameter(param.getKey(), param.getValue());
				}
			}
		}
		return query;
	}

	public Query queryHandlePage(String hql, int offest, int size,
			final Object... values) {
		Query query = queryHandle(hql, values);
		query.setFirstResult(offest);
		query.setMaxResults(size);
		return query;
	}

	public Query queryHandlePage(String hql, int offest, int size,
			final Map<String, Object> params) {
		Query query = queryHandle(hql, params);
		query.setFirstResult(offest);
		query.setMaxResults(size);
		return query;
	}

	/**
	 * gain a list object
	 * 
	 * */
	public Object queryHandleObject(String hql) {
		Query query = queryHandle(hql);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * gain a list object
	 * 
	 * */
	@SuppressWarnings("unchecked")
	public <X> List<X> queryHandleList(String hql) {
		Query query = queryHandle(hql);
		return query.getResultList();
	}

	/**
	 * gain a list object
	 * 
	 * */
	@SuppressWarnings("unchecked")
	public <X> List<X> queryHandleList(String hql, final Object... values) {
		Query query = queryHandle(hql, values);
		return query.getResultList();
	}

	/**
	 * gain a list object
	 * 
	 * */
	@SuppressWarnings("unchecked")
	public <X> List<X> queryHandleList(String hql,
			final Map<String, Object> params) {
		Query query = queryHandle(hql, params);
		return query.getResultList();
	}

	/**
	 * 分页方法
	 * 
	 * @param hql
	 *            hql语句
	 * @param offest
	 *            起始位置
	 * @param size
	 *            每页显示条数
	 * @param values
	 *            参数
	 * @return Query对象
	 */
	@SuppressWarnings("unchecked")
	public <X> List<X> queryHandleOrder(String hql, int offest, int size,
			final Object... values) {
		Query query = queryHandle(hql, values);
		query.setFirstResult(offest);
		query.setMaxResults(size);
		return query.getResultList();
	}

	/**
	 * get the all items
	 * 
	 * @return Object
	 * */
	@SuppressWarnings("unchecked")
	public <X> List<X> getAll(Class<X> clazz) {
		String hql = "from className order by id desc";
		hql = hql.replaceAll("className", clazz.getSimpleName());
		Query query = entityManager.createQuery(hql);
		return query.getResultList();
	}

	/**
	 * get the all items
	 * 
	 * @return Object
	 * */
	@SuppressWarnings("unchecked")
	public <X> List<X> getAllAsc(Class<X> clazz) {
		String hql = "from className order by id asc";
		hql = hql.replaceAll("className", clazz.getSimpleName());
		Query query = entityManager.createQuery(hql);
		return query.getResultList();
	}

	/**
	 * get the all items and Pagination
	 * 
	 * @return Object
	 * */
	@SuppressWarnings("unchecked")
	public <X> List<X> getAll(Class<X> clazz, PageRequest pageRequest) {
		String hql = "from className";
		String dir = "desc";
		if (pageRequest.getOrderDir() != null
				&& pageRequest.getOrderDir().equalsIgnoreCase("ASC")) {
			dir = "asc";
		}
		if (pageRequest.getOrderBy() == null
				|| pageRequest.getOrderBy().equals("")) {
			hql += " order by id desc";
		} else {
			hql += " order by " + pageRequest.getOrderBy() + " " + dir;
		}
		hql = hql.replaceAll("className", clazz.getSimpleName());
		Query query = entityManager.createQuery(hql);
		query.setFirstResult(pageRequest.getOffset());
		query.setMaxResults(pageRequest.getPageSize());
		return query.getResultList();
	}

	/**
	 * 
	 * save
	 * 
	 * @param obj
	 * @return the save success,the persist object
	 **/
	public Object save(Object obj) {
		Object object = null;
		object = entityManager.merge(obj);
		return object;
	}

	/**
	 * 
	 * update
	 * 
	 * @param obj
	 * @return the update success,the persist object
	 **/
	public Object update(Object obj) {
		try {
			entityManager.merge(obj);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("update is wrong");
		}

	}

	/**
	 * 
	 * update or save
	 * 
	 * @param hql
	 * @param values
	 * @return the update success,the persist object
	 **/
	public Boolean update(String hql, final Object... values) {
		try {
			Query query = queryHandle(hql, values);
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("update is wrong");
		}

	}

	/**
	 * 
	 * through the PK id,delete the object
	 * 
	 * @param id
	 *            pk
	 * @return
	 **/

	public void delete(Class<?> clazz, Serializable id) {
		this.entityManager.remove(this.get(clazz, id));
	}

	public void delete(Object o) {
		this.entityManager.remove(o);
	}

	/**
	 * sql search
	 * 
	 * */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryHandleSql(String sql,
			final Object... values) {
		Session session = (Session) entityManager.getDelegate();
		org.hibernate.Query query = session.createSQLQuery(sql)
				.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.list();
	}

	/**
	 * sql search
	 * 
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map<String, Object>> queryHandleSql(String sql,
			final Map<String, Object> params) {
		Session session = (Session) entityManager.getDelegate();
		org.hibernate.Query query = session.createSQLQuery(sql)
				.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, Object> param : params.entrySet()) {
				if (param.getValue() instanceof List) {
					query.setParameterList(param.getKey(),
							(Collection) param.getValue());
				} else {
					query.setParameter(param.getKey(), param.getValue());
				}
			}
		}
		return query.list();
	}

	public Map<String, Object> queryHandleSingalSql(String sql,
			final Object... values) {
		List<Map<String, Object>> list = this.queryHandleSql(sql, values);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return new HashMap<String, Object>();
	}

	public int executeDeleteOrUpdateSql(String sql) {
		Session session = (Session) entityManager.getDelegate();
		org.hibernate.Query query = session.createSQLQuery(sql)
				.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.executeUpdate();
	}

	public int executeDeleteOrUpdateSql(String sql, final Object... values) {
		Session session = (Session) entityManager.getDelegate();
		org.hibernate.Query query = session.createSQLQuery(sql)
				.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query.executeUpdate();
	}

	@SuppressWarnings("rawtypes")
	public int executeDeleteOrUpdateSql(String sql,
			final Map<String, Object> params) {
		Session session = (Session) entityManager.getDelegate();
		org.hibernate.Query query = session.createSQLQuery(sql)
				.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, Object> param : params.entrySet()) {
				if (param.getValue() instanceof List) {
					query.setParameterList(param.getKey(),
							(Collection) param.getValue());
				} else {
					query.setParameter(param.getKey(), param.getValue());
				}
			}
		}
		return query.executeUpdate();
	}

	/**
	 * sql search (can page)
	 * 
	 * */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryHandleSql(String sql, Integer offest,
			Integer size, final Object... values) {
		Session session = (Session) entityManager.getDelegate();
		org.hibernate.Query query = session.createSQLQuery(sql)
				.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		if (offest != null && size != null) {
			query.setFirstResult(offest);
			query.setMaxResults(size);
		}
		return query.list();
	}

	/**
	 * sql search (can page)
	 * 
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map<String, Object>> queryHandleSql(String sql, Integer offest,
			Integer size, Map<String, Object> params) {
		Session session = (Session) entityManager.getDelegate();
		org.hibernate.Query query = session.createSQLQuery(sql)
				.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, Object> param : params.entrySet()) {
				if (param.getValue() instanceof List) {
					query.setParameterList(param.getKey(),
							(Collection) param.getValue());
				} else {
					query.setParameter(param.getKey(), param.getValue());
				}
			}
		}
		if (offest != null && size != null) {
			query.setFirstResult(offest);
			query.setMaxResults(size);
		}
		return query.list();
	}

}
