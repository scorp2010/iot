/***********************************************************************
 * Module:  TdType.java
 * Author:  w1898
 * Purpose: Defines the Class TdType
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;

/**
 * 目标设备类型（核心产品型号类型）
 * 
 */
@Entity(name = "TdProductModel")
@Table(name = "t_mms_td_product_model",indexes ={ @Index(name="td_product_model_index",columnList= "product_name")})
public class TdProductModel implements java.io.Serializable {
	private static final long serialVersionUID = 4000249193495753934L;
	/** 编号 */
	private String id;
	/** 产品型号名称 */
	private String productName;
	/** 厂家类型   */
	private String productFactory;
	/** 产品型号版本 */
	private String version;
	/** 备注描述 */
	private String remark;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/** 更新类型 */
	private Short updateType;
	
    /** 业务类型 */
	private String businessType;
	private String businessTypeName;
	/** 需要给相关设备创建更新单板软件的标记*/
	private String waitNoticeTdUpdateFlag;
    public  final static String NO_NOTICE = "1";
    public  final static String HAS_NOTICE = "2";
    /**软件信息 */
    private SoftwareInfo softWareInfo;
    private String newSoftwareInfoId;
    /** 软件变更状态审批开关（0：表示待审批，1：表示已经审批,2：未提交） */
    private String approvalOpen;
	private String sdate;
	private String edate;
	public TdProductModel() {
	}
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "cn.inovance.iotgp.common.uuid.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 36)
	public String getId() {
		return id;
	}

	public void setId(String newId) {
		this.id = newId;
	}
	@Basic(optional = true)
	@Column(name = "product_factory", insertable = true, updatable = true, length = 50)
	public String getProductFactory() {
		return productFactory;
	}
	public void setProductFactory(String productFactory) {
		this.productFactory = productFactory;
	}
	@Basic(optional = true)
	@Column(name = "product_name", insertable = true, updatable = true, length = 100)
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Basic(optional = true)
	@Column(name = "remark", insertable = true, updatable = true, length = 50)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", insertable = true, updatable = true, length = 7)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date newCreateTime) {
		this.createTime = newCreateTime;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", insertable = true, updatable = true)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date newUpdateTime) {
		this.updateTime = newUpdateTime;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "software_info_id", referencedColumnName = "id", nullable = false)
	public SoftwareInfo getSoftWareInfo() {
		return softWareInfo;
	}

	public void setSoftWareInfo(SoftwareInfo softWareInfo) {
		this.softWareInfo = softWareInfo;
	}
	
	@Basic(optional = true)
	@Column(name = "new_software_info_id", insertable = true, updatable = true,length=50)
	public String getNewSoftwareInfoId() {
		return newSoftwareInfoId;
	}
	public void setNewSoftwareInfoId(String newSoftwareInfoId) {
		this.newSoftwareInfoId = newSoftwareInfoId;
	}
	@Basic(optional = true)
	@Column(name = "approval_open", insertable = true, updatable = true,length=10)
	public String getApprovalOpen() {
		return approvalOpen;
	}
	public void setApprovalOpen(String approvalOpen) {
		this.approvalOpen = approvalOpen;
	}
	@Basic(optional = true)
	@Column(name = "business_type", insertable = true, updatable = true,length=20)
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	@Basic(optional = true)
	@Column(name = "notice_flag", insertable = true, updatable = true,length=2)
	public String getWaitNoticeTdUpdateFlag() {
		return waitNoticeTdUpdateFlag;
	}

	public void setWaitNoticeTdUpdateFlag(String waitNoticeTdUpdateFlag) {
		this.waitNoticeTdUpdateFlag = waitNoticeTdUpdateFlag;
	}
	
	@Basic(optional = true)
	@Column(name = "update_type", insertable = true,length = 10)
	public Short getUpdateType() {
		return updateType;
	}
	public void setUpdateType(Short updateType) {
		this.updateType = updateType;
	}
	@Transient
	public String getBusinessTypeName(){
		return this.businessTypeName;
		
	}
	public void setBusinessTypeName(String businessTypeName) {
		this.businessTypeName = businessTypeName;
	}

	@Transient
	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	@Transient
	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}
	@Basic(optional = true)
	@Column(name = "version", insertable = true, updatable = true, length = 50)
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((businessType == null) ? 0 : businessType.hashCode());
		result = prime
				* result
				+ ((businessTypeName == null) ? 0 : businessTypeName.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((edate == null) ? 0 : edate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((sdate == null) ? 0 : sdate.hashCode());
		result = prime * result
				+ ((softWareInfo == null) ? 0 : softWareInfo.hashCode());
		result = prime * result
				+ ((productName == null) ? 0 : productName.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		result = prime
				* result
				+ ((waitNoticeTdUpdateFlag == null) ? 0
						: waitNoticeTdUpdateFlag.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TdProductModel other = (TdProductModel) obj;
		if (businessType == null) {
			if (other.businessType != null)
				return false;
		} else if (!businessType.equals(other.businessType))
			return false;
		if (businessTypeName == null) {
			if (other.businessTypeName != null)
				return false;
		} else if (!businessTypeName.equals(other.businessTypeName))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (edate == null) {
			if (other.edate != null)
				return false;
		} else if (!edate.equals(other.edate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (sdate == null) {
			if (other.sdate != null)
				return false;
		} else if (!sdate.equals(other.sdate))
			return false;
		if (softWareInfo == null) {
			if (other.softWareInfo != null)
				return false;
		} else if (!softWareInfo.equals(other.softWareInfo))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		if (waitNoticeTdUpdateFlag == null) {
			if (other.waitNoticeTdUpdateFlag != null)
				return false;
		} else if (!waitNoticeTdUpdateFlag.equals(other.waitNoticeTdUpdateFlag))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TdControllerType [id=" + id + ", productName=" + productName
				+ ", version=" + version + ", remark=" + remark
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", sdate=" + sdate + ", edate=" + edate + ", businessType="
				+ businessType + ", businessTypeName=" + businessTypeName
				+ ", waitNoticeTdUpdateFlag=" + waitNoticeTdUpdateFlag
				+ ", softWareInfo=" + softWareInfo + "]";
	}
	
}