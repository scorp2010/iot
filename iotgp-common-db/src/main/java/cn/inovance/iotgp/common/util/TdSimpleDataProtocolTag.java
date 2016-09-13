/**
gr * Project Name:iotgp-common-db
 * File Name:TdDataProtocol.java
 * Package Name:cn.inovance.iotgp.common.util
 * Date:2014-7-25下午3:40:15
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:TdDataProtocol <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-7-25 下午3:40:15 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class TdSimpleDataProtocolTag {
 
	
	private String text;

	private Integer id;
	private boolean checked = true;
	private String iconCls;
	private String parentId;
	private String state = "open";
	private Integer samplingperiod;
	

	private List<TdSimpleDataProtocolValue> valueList = new ArrayList<TdSimpleDataProtocolValue>(0);
	
	public String getText() {
		return text;
	}
	public Integer getSamplingperiod() {
		return samplingperiod;
	}

	public void setSamplingperiod(Integer samplingperiod) {
		this.samplingperiod = samplingperiod;
	}
	public void setText(String text) {
		this.text = text;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<TdSimpleDataProtocolValue> getValueList() {
		return valueList;
	}

	public void setValueList(List<TdSimpleDataProtocolValue> valueList) {
		this.valueList = valueList;
	}

	

}

