/**
 * Project Name:iotgp-common-db
 * File Name:TdSimpleDataProtocolValue.java
 * Package Name:cn.inovance.iotgp.common.util
 * Date:2014-7-25下午3:47:15
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.util;
/**
 * ClassName:TdSimpleDataProtocolValue <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-7-25 下午3:47:15 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class TdSimpleDataProtocolValue {

	/** 字段名称 .*/
	private String name;
	/** 功能吗*/
	private String functionCode;
	
	/** 描述. */
	private String remark;
	/** 顺序. */
	private Integer seq;
	
	/** 字节索引 . */
	private Integer byteIndex;
	/** 位索引 . */
	private Integer bitIndex;
	/** 字段长度 */
	private Integer length;
	/** 长度形式 .
	 * 1. 按字节
	 * 2. 按bit位
	 * */
	private String lengthUnit;
	/** 转值类型  .
	 * 1. 有符号十进制 
	 * 2. 无符号十进制 
	 * 3. 二进制
	 * 4. 字符串 
	 * 5. ASCII码
	 * 6. BCD码
	 * */
	private String transferDataType;
	/**转义方式 .
	 * 1.不转换
	 * 2.精度转换
	 * 3.值枚举
	 * 4.范围枚举
	 * 5.日期
	 * */
	private String transferMethod;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFunctionCode() {
		return functionCode;
	}
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public Integer getByteIndex() {
		return byteIndex;
	}
	public void setByteIndex(Integer byteIndex) {
		this.byteIndex = byteIndex;
	}
	public Integer getBitIndex() {
		return bitIndex;
	}
	public void setBitIndex(Integer bitIndex) {
		this.bitIndex = bitIndex;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public String getLengthUnit() {
		return lengthUnit;
	}
	public void setLengthUnit(String lengthUnit) {
		this.lengthUnit = lengthUnit;
	}
	public String getTransferDataType() {
		return transferDataType;
	}
	public void setTransferDataType(String transferDataType) {
		this.transferDataType = transferDataType;
	}
	public String getTransferMethod() {
		return transferMethod;
	}
	public void setTransferMethod(String transferMethod) {
		this.transferMethod = transferMethod;
	}
	public String getTransferContent() {
		return transferContent;
	}
	public void setTransferContent(String transferContent) {
		this.transferContent = transferContent;
	}
	public String getValueUnit() {
		return valueUnit;
	}
	public void setValueUnit(String valueUnit) {
		this.valueUnit = valueUnit;
	}
	public String getEndian() {
		return endian;
	}
	public void setEndian(String endian) {
		this.endian = endian;
	}
}

