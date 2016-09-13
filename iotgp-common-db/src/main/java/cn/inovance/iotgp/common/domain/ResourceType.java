package cn.inovance.iotgp.common.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * ClassName: ResourceType. date: 2014-4-9 上午10:38:06
 * 
 * @author w1898
 * @version 1.0
 * @since JDK 1.7
 */
@Entity
@Table(name = "t_sys_resource_type", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ResourceType extends BaseEntity implements java.io.Serializable {

	/**
	 * 创建时间.
	 */
	private Date createDatetime;
	/**
	 * 更新时间.
	 */
	private Date updateDatetime;
	/**
	 * 资源类型名称.
	 */
	private String resTypeName;
	/**
	 * 资源类型描述.
	 */
	private String resTypeDesc;
	/**
	 * set.
	 */
	private Set<Resource> resourceSet = new HashSet<Resource>(0);

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 7)
	public Date getCreateDatetime() {
		if (this.createDatetime != null)
			return this.createDatetime;
		return new Date();
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", length = 7)
	public Date getUpdateDatetime() {
		if (this.updateDatetime != null)
			return this.updateDatetime;
		return new Date();
	}

	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	@Column(name = "res_type_name", nullable = false, length = 100)
	public String getResTypeName() {
		return this.resTypeName;
	}

	public void setResTypeName(String resTypeName) {
		this.resTypeName = resTypeName;
	}

	@Column(name = "res_type_desc", length = 200)
	public String getResTypeDesc() {
		return this.resTypeDesc;
	}

	public void setResTypeDesc(String resTypeDesc) {
		this.resTypeDesc = resTypeDesc;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "resourceType")
	public Set<Resource> getResourceSet() {
		return this.resourceSet;
	}

	public void setResourceSet(Set<Resource> resourceSet) {
		this.resourceSet = resourceSet;
	}

}
