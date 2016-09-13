package cn.inovance.iotgp.cdsm.dao;

import org.springframework.stereotype.Repository;

import cn.inovance.iotgp.cdsm.constants.StaticValues;

@Repository
public class CdRegisterCodeDao extends BaseDao {

	public boolean deleteCDSMCdRegisterCode() {

		String hql = "delete from t_cds_cd_register_code where reg_code like '"
				+ StaticValues.REGCODE_CORPCODE + "%'";
		logger.info("deleteCDSMCdRegisterCode() hql:{}", hql);
		executeDeleteOrUpdateSql(hql);
		if (StaticValues.geneZhujiFlag){
			hql = "delete from t_cds_cd_register_code where reg_code like '"
					+ "zjinovance" + "%'";
			logger.info("deleteCDSMCdRegisterCode() hql:{}", hql);
			executeDeleteOrUpdateSql(hql);
		}
		return true;
	}

}
