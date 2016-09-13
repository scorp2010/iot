package cn.inovance.iotgp.cdsm.service;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.inovance.iotgp.cdsm.dao.BaseDao;
import cn.inovance.iotgp.cdsm.dao.DaoCenter;

@Service
public class BaseService extends BaseDao {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	protected SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
	protected SimpleDateFormat formatDateTime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	@Autowired
	protected DaoCenter dc;

}
