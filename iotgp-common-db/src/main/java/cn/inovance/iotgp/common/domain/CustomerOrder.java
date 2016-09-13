/**
 * Project Name:iotgp-common-db
 * File Name:CustomerOrder.java
 * Package Name:cn.inovance.iotgp.common.domain
 * Date:2015-4-20上午11:48:57
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.common.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * ClassName:CustomerOrder <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-4-20 上午11:48:57 <br/>
 * 
 * @author w1898
 * @version
 * @since JDK 1.7
 * @see
 */
@Entity(name = "CustomerOrder")
@Table(name = "t_oms_customer_order", indexes = { 
		@Index(name = "t_oms_customer_order_ciodt_idx", columnList = "cio_date"),
		@Index(name = "t_oms_customer_order_cln_id_idx", columnList = "cln_id"),
		@Index(name = "t_oms_customer_order_cio_single_idx", columnList = "cio_single")
		})
public class CustomerOrder {

	/**
	 * 编号
	 */
	private String id;
	/**
	 * "凭证号"
	 */
	private String proid;
	/**
	 * 订单号
	 */
	private String proorderno;
	/**
	 * 出库时间
	 */
	private Date ciodt;
	/**
	 * 出库编码
	 */
	private String ciomaterial;
	/**
	 * 编码描述
	 */
	private String matname;
	/**
	 * 出库批次
	 */
	private String ciobatch;
	/**
	 * 出库条码
	 */
	private String ciosingle;
	/**
	 * "型号"
	 */
	private String mattypedsc;
	/**
	 * "非标号"
	 */
	private String standdsc;
	/**
	 * "出库数
	 */
	private Integer ciosum;
	/**
	 * 客户代码
	 */
	private String clnId;
	/**
	 * 客户名称
	 */
	private String clnName;
	/**
	 * 发货地址
	 */
	private String proDescription;

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "cn.inovance.iotgp.common.uuid.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Basic(optional = true)
	@Column(name = "pro_id", insertable = true, updatable = true, length = 30)
	public String getProid() {
		return proid;
	}
	
	public void setProid(String proid) {
		this.proid = proid;
	}
	@Basic(optional = true)
	@Column(name = "pro_orderno", insertable = true, updatable = true, length = 30)
	public String getProorderno() {
		return proorderno;
	}

	public void setProorderno(String proorderno) {
		this.proorderno = proorderno;
	}
	@Basic(optional = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cio_date", insertable = true, updatable = true, length = 7)
	public Date getCiodt() {
		return ciodt;
	}

	public void setCiodt(Date ciodt) {
		this.ciodt = ciodt;
	}
	@Basic(optional = true)
	@Column(name = "cio_material", insertable = true, updatable = true, length = 20)
	public String getCiomaterial() {
		return ciomaterial;
	}

	public void setCiomaterial(String ciomaterial) {
		this.ciomaterial = ciomaterial;
	}
	@Basic(optional = true)
	@Column(name = "mat_name", insertable = true, updatable = true, length = 50)
	public String getMatname() {
		return matname;
	}

	public void setMatname(String matname) {
		this.matname = matname;
	}
	@Basic(optional = true)
	@Column(name = "cio_batch", insertable = true, updatable = true, length = 20)
	public String getCiobatch() {
		return ciobatch;
	}

	public void setCiobatch(String ciobatch) {
		this.ciobatch = ciobatch;
	}
	@Basic(optional = true)
	@Column(name = "cio_single", insertable = true, updatable = true, length = 30)
	public String getCiosingle() {
		return ciosingle;
	}

	public void setCiosingle(String ciosingle) {
		this.ciosingle = ciosingle;
	}
	@Basic(optional = true)
	@Column(name = "mat_type_dsc", insertable = true, updatable = true, length = 50)
	public String getMattypedsc() {
		return mattypedsc;
	}

	public void setMattypedsc(String mattypedsc) {
		this.mattypedsc = mattypedsc;
	}
	@Basic(optional = true)
	@Column(name = "stand_dsc", insertable = true, updatable = true, length = 50)
	public String getStanddsc() {
		return standdsc;
	}

	public void setStanddsc(String standdsc) {
		this.standdsc = standdsc;
	}
	@Basic(optional = true)
	@Column(name = "cio_num", insertable = true, updatable = true,length = 10)
	public Integer getCiosum() {
		return ciosum;
	}

	public void setCiosum(Integer ciosum) {
		this.ciosum = ciosum;
	}
	@Basic(optional = true)
	@Column(name = "cln_id", insertable = true, updatable = true,length = 50)
	public String getClnId() {
		return clnId;
	}

	public void setClnId(String clnId) {
		this.clnId = clnId;
	}
	@Basic(optional = true)
	@Column(name = "cln_name", insertable = true, updatable = true,length = 50)
	public String getClnName() {
		return clnName;
	}
	
	public void setClnName(String clnName) {
		this.clnName = clnName;
	}
	@Basic(optional = true)
	@Column(name = "pro_description", insertable = true, updatable = true,length = 100)
	public String getProDescription() {
		return proDescription;
	}

	public void setProDescription(String proDescription) {
		this.proDescription = proDescription;
	}

}
