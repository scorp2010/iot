package cn.inovance.iotgp.common.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.common.dao.ApplicationSoftWareInfoDao;
import cn.inovance.iotgp.common.dao.ApplicationSoftWareUpdateResultDao;
import cn.inovance.iotgp.common.dao.SoftWareInfoDao;
import cn.inovance.iotgp.common.domain.ApplicationSoftwareInfo;
import cn.inovance.iotgp.common.domain.ApplicationSoftwareUpdateResult;
import cn.inovance.iotgp.common.domain.SoftwareInfo;
import cn.inovance.iotgp.common.filter.HqlFilter;
import cn.inovance.iotgp.common.util.BeanUtils;

@Repository
public class ApplicationSoftWareUpdateResultDaoImpl extends BaseDaoImpl<ApplicationSoftwareUpdateResult> implements
ApplicationSoftWareUpdateResultDao {
	@Override
	public Long countApplicationSoftWareUpdateResultByParam(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from ApplicationSoftwareUpdateResult t ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public void saveApplicationSoftwareUpdateResult(ApplicationSoftwareUpdateResult data) {
		save(data);
	}
}
