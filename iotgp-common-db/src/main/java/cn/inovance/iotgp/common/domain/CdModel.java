/**
 * Project Name:iotgp-common-db
 * File Name:CdModel.java
 * Package Name:cn.inovance.iotgp.common.domain
 * Date:2015-7-2上午10:32:52
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ClassName:CdModel
 * Date:     2015-7-2 上午10:32:52
 * @author   w1898
 * @version
 * @since    JDK 1.7
 * @see
 */
@Entity(name = "CdModel")
@Table(name = "t_cds_cd_model",indexes ={ @Index(name="cd_model_materiel_index",columnList= "materiel")  })
public class CdModel implements java.io.Serializable {
	/** 编号*/
	private String id;
	/**物料*/
	private String materiel;
	/**整机 */
	private String packageModel;
	/**描述 */
	private String desc;
	/**3G Or 2G */
	private String netWork;
	/**接入模式（中继或主机） */
	private Short accessMode;
	/**接入模式（中继或主机） */
	private String accessModeName;
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "cn.inovance.iotgp.common.uuid.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 36)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Basic(optional = true)
	@Column(name = "materiel", insertable = true, updatable = true, length = 50)
	public String getMateriel() {
		return materiel;
	}
	public void setMateriel(String materiel) {
		this.materiel = materiel;
	}
	@Basic(optional = true)
	@Column(name = "package_model", insertable = true, updatable = true, length = 50)
	public String getPackageModel() {
		return packageModel;
	}
	public void setPackageModel(String packageModel) {
		this.packageModel = packageModel;
	}
	@Basic(optional = true)
	@Column(name = "remark", insertable = true, updatable = true, length = 200)
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Basic(optional = true)
	@Column(name = "net_work", insertable = true, updatable = true, length = 20)
	public String getNetWork() {
		return netWork;
	}
	public void setNetWork(String netWork) {
		this.netWork = netWork;
	}
	@Basic(optional = true)
	@Column(name = "access_model", insertable = true, updatable = true, length = 10)
	public Short getAccessMode() {
		return accessMode;
	}
	public void setAccessMode(Short accessMode) {
		this.accessMode = accessMode;
	}
	@Basic(optional = true)
	@Column(name = "access_model_name", insertable = true, updatable = true, length = 50)
	public String getAccessModeName() {
		return accessModeName;
	}
	public void setAccessModeName(String accessModeName) {
		this.accessModeName = accessModeName;
	}
	
	
	
}

