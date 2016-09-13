/***********************************************************************
 * Module:  TdDataProtocolValue.java
 * Author:  w1898
 * Purpose: Defines the Class TdDataProtocolValue
 ***********************************************************************/

package cn.inovance.iotgp.common.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 数据协议详细.
 *
 */
@Entity(name = "TdControllerDataProtocolValue")
@Table(name = "t_mms_td_controller_data_protocol_value",indexes ={ @Index(name="data_protocol_value_index",columnList= "t_name")})
public class TdControllerDataProtocolValue implements java.io.Serializable {

	private static final long serialVersionUID = -6433895356282778371L;
	/** 编号 . */
	private String id;
	/** 字段名称. */
	private String name;
	
	/**功能码**/
	private String functionCode;
	/** 顺序 . */
	private Integer seq;
	/** 字节索引 . */
	private Integer byteIndex;
	/** 位索引 . */
    private Integer bitIndex;
    /** 字段长度 . */
	private Integer length;
	/** 长度形式 .
	 * 1. 按字节
	 * 2. 按bit位
	 * */
	private String lengthUnit;
	private String lengthUnitName;
	/** 描述 . */
	private String remark;
	/** 转值类型  .
	 * 1. 有符号十进制 
	 * 2. 无符号十进制 
	 * 3. 二进制
	 * 4. 字符串 
	 * 5. ASCII码
	 * 6. BCD码
	 * */
	private String transferDataType;
	private String transferDataTypeName;
	/**转义方式 .
	 * 1.不转换
	 * 2.精度转换
	 * 3.值枚举
	 * 4.范围枚举
	 * 5.日期
	 * */
	private String transferMethod;
	private String transferMethodName;
	/**转义内容.
	 * 是对象的需要为json格式存储
	 */
	private String transferContent;
	/** 值单位 . */
	private String valueUnit;
	/** 大小端.
	 * 1.大端
	 * 2.小端
	 * */
	private String endian;
	private String endianName;
	/** 是否保留？ */
	private Boolean reserve;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	public TdControllerDataProtocolTag tdControllerDataProtocolTag;
	private String sdate;
	private String edate;

	/**
	 * 默认值
	 */
	private String defaultValue;
	
	public TdControllerDataProtocolValue() {
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "td_controller_data_protocol_tag_id_fk", referencedColumnName = "id", nullable = true)
	public TdControllerDataProtocolTag getTdControllerDataProtocolTag() {
		return tdControllerDataProtocolTag;
	}

	public void setTdControllerDataProtocolTag(TdControllerDataProtocolTag newTdControllerDataProtocolTag) {
		this.tdControllerDataProtocolTag = newTdControllerDataProtocolTag;
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
	@Column(name = "t_name", insertable = true, updatable = true, length = 100)
	public String getName() {
		return name;
	}

	public void setName(String newName) {
		this.name = newName;
	}
	@Basic(optional = true)
	@Column(name = "function_code", insertable = true, updatable = true, length = 20)
	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	@Basic(optional = true)
	@Column(name = "length", insertable = true, updatable = true)
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer newLength) {
		this.length = newLength;
	}
	@Basic(optional = true)
	@Column(name = "value_unit", insertable = true, updatable = true, length = 20)
	public String getValueUnit() {
		return valueUnit;
	}
	public void setValueUnit(String valueUnit) {
		this.valueUnit = valueUnit;
	}
	@Basic(optional = true)
	@Column(name = "reserve", insertable = true, updatable = true, columnDefinition = "bit DEFAULT 0" )
	public Boolean getReserve() {
		return reserve;
	}

	public void setReserve(Boolean newReserve) {
		this.reserve = newReserve;
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

	@Basic(optional = true)
	@Column(name = "remark", insertable = true, updatable = true, length = 100)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Basic(optional = true)
	@Column(name = "seq", insertable = true, updatable = true)
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	@Basic(optional = true)
	@Column(name = "byte_index", insertable = true, updatable = true)
	public Integer getByteIndex() {
		return byteIndex;
	}

	public void setByteIndex(Integer byteIndex) {
		this.byteIndex = byteIndex;
	}
	@Basic(optional = true)
	@Column(name = "bit_index", insertable = true, updatable = true)
	public Integer getBitIndex() {
		return bitIndex;
	}

	public void setBitIndex(Integer bitIndex) {
		this.bitIndex = bitIndex;
	}
	@Basic(optional = true)
	@Column(name = "length_unit", insertable = true, updatable = true, length = 1)
	public String getLengthUnit() {
		return lengthUnit;
	}

	public void setLengthUnit(String lengthUnit) {
		this.lengthUnit = lengthUnit;
	}
	@Transient
	public String getLengthUnitName() {
		return lengthUnitName;
	}

	public void setLengthUnitName(String lengthUnitName) {
		this.lengthUnitName = lengthUnitName;
	}

	@Basic(optional = true)
	@Column(name = "transfer_data_type", insertable = true, updatable = true, length = 1)
	public String getTransferDataType() {
		return transferDataType;
	}

	public void setTransferDataType(String transferDataType) {
		this.transferDataType = transferDataType;
	}
	@Transient
	public String getTransferDataTypeName() {
		return transferDataTypeName;
	}

	public void setTransferDataTypeName(String transferDataTypeName) {
		this.transferDataTypeName = transferDataTypeName;
	}
	@Basic(optional = true)
	@Column(name = "transfer_method", insertable = true, updatable = true, length = 1)
	public String getTransferMethod() {
		return transferMethod;
	}

	public void setTransferMethod(String transferMethod) {
		this.transferMethod = transferMethod;
	}
	@Transient
	public String getTransferMethodName() {
		return transferMethodName;
	}

	public void setTransferMethodName(String transferMethodName) {
		this.transferMethodName = transferMethodName;
	}
	@Lob()
	@Basic(optional = true)
	@Column(name = "transfer_content", insertable = true, columnDefinition = "text DEFAULT NULL COMMENT '转义内容'")
	public String getTransferContent() {
		return transferContent;
	}

	public void setTransferContent(String transferContent) {
		this.transferContent = transferContent;
	}
	@Basic(optional = true)
	@Column(name = "endian", insertable = true, updatable = true, length = 1)
	public String getEndian() {
		return endian;
	}

	public void setEndian(String endian) {
		this.endian = endian;
	}
	@Transient
	public String getEndianName() {
		return endianName;
	}

	public void setEndianName(String endianName) {
		this.endianName = endianName;
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
	@Column(name = "default_value", insertable = true, updatable = true, length = 20)
	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	

}