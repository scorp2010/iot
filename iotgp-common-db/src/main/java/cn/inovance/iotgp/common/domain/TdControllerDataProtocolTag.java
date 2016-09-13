/***********************************************************************
 * Module:  TdDataProtocolTag.java
 * Author:  w1898
 * Purpose: Defines the Class TdDataProtocolTag
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
 * 核心控制器数据协议标签
 * 
 */
@Entity(name = "TdControllerDataProtocolTag")
@Table(name = "t_mms_td_controller_data_protocol_tag",indexes ={ @Index(name="tag_value_index",columnList= "value"),@Index(name="tag_name_index",columnList= "name") })
public class TdControllerDataProtocolTag implements java.io.Serializable {

	private static final long serialVersionUID = -7387756126789497120L;
	/** 编码 */
	private String id;
	/** 采集周期 */
	private Integer smaplingPeriod;
	/** tag名称 */
	private String name;
	/** 标签值，即seq */
	private Integer value;
	/** 标签描述 */
	private String describe;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;

	public Set<TdControllerDataProtocolValue> tdControllerDataProtocolValueList = new HashSet<TdControllerDataProtocolValue>(
			0);
	public TdControllerType tdControllerType;
	private String sdate;
	private String edate;
	private String fileUrl;

	public TdControllerDataProtocolTag() {
	}

	@OneToMany(mappedBy = "tdControllerDataProtocolTag", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<TdControllerDataProtocolValue> getTdControllerDataProtocolValueList() {
		return tdControllerDataProtocolValueList;
	}

	public void setTdControllerDataProtocolValueList(
			Set<TdControllerDataProtocolValue> newTdContrllerDataProtocolValueList) {
		this.tdControllerDataProtocolValueList = newTdContrllerDataProtocolValueList;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "td_controller_type_id_fk", referencedColumnName = "id", nullable = true)
	public TdControllerType getTdControllerType() {
		return tdControllerType;
	}

	public void setTdControllerType(TdControllerType tdControllerType) {
		this.tdControllerType = tdControllerType;
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
	@Column(name = "sample_period", insertable = true, updatable = true)
	public Integer getSmaplingPeriod() {
		return smaplingPeriod;
	}

	public void setSmaplingPeriod(Integer smaplingPeriod) {
		this.smaplingPeriod = smaplingPeriod;
	}
	@Basic(optional = true)
	@Column(name = "name", insertable = true, updatable = true, length = 100)
	public String getName() {
		return name;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	@Basic(optional = true)
	@Column(name = "value", insertable = true, updatable = true)
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer newValue) {
		this.value = newValue;
	}

	@Basic(optional = true)
	@Column(name = "remark", insertable = true, updatable = true, length = 100)
	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String newDescribe) {
		this.describe = newDescribe;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", insertable = true, updatable = true, length = 7)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date newCreateTime) {
		this.createTime = newCreateTime;
	}

	@Basic(optional = true)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", insertable = true, updatable = true)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date newUpdateTime) {
		this.updateTime = newUpdateTime;
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

	@Transient
	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

}