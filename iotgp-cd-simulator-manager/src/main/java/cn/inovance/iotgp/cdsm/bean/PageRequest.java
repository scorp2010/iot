package cn.inovance.iotgp.cdsm.bean;

import org.apache.commons.lang3.StringUtils;

/**
 * 分页参数封装类.
 */
public class PageRequest {
	
	public static class Sort {
		public static final String ASC = "asc";
		public static final String DESC = "desc";

		private final String dir;
		private final String property;

		public Sort(String property, String dir) {
			this.property = property;
			this.dir = dir;
		}

		public String getDir() {
			return this.dir;
		}

		public String getProperty() {
			return this.property;
		}
	}

	protected boolean countTotal = true;

	protected String orderBy = null;
	protected String orderDir = null;

	protected int pageNo = 1;

	protected int pageSize = 10;

	protected int count = 0;

	protected int pageTotal = 0;

	public long getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
		if (this.pageSize <= 0) {
			this.pageSize = 1;
		}
		this.pageTotal = count % this.pageSize == 0 ? count / this.pageSize
				: count / this.pageSize + 1;

		if (this.pageNo > this.pageTotal) {
			this.pageNo = this.pageTotal;
		}
		if (this.pageNo <= 0) {
			this.pageNo = 1;
		}
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public PageRequest() {
	}

	public PageRequest(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置, 序号从0开始.
	 */
	public int getOffset() {
		return (this.pageNo - 1) * this.pageSize;
	}

	/**
	 * 获得排序字段, 无默认值. 多个排序字段时用','分隔.
	 */
	public String getOrderBy() {
		return this.orderBy;
	}

	/**
	 * 获得排序方向, 无默认值.
	 */
	public String getOrderDir() {
		return this.orderDir;
	}

	/**
	 * 获得当前页的页号, 序号从1开始, 默认为1.
	 */
	public int getPageNo() {
		return this.pageNo;
	}

	/**
	 * 获得每页的记录数量, 默认为10.
	 */
	public int getPageSize() {
		return this.pageSize;
	}

	/**
	 * 是否默认计算总记录数.
	 */
	public boolean isCountTotal() {
		return this.countTotal;
	}

	/**
	 * 是否已设置排序字段,无默认值.
	 */
	public boolean isOrderBySetted() {
		return StringUtils.isNotBlank(this.orderBy)
				&& StringUtils.isNotBlank(this.orderDir);
	}

	/**
	 * 设置是否默认计算总记录数.
	 */
	public void setCountTotal(boolean countTotal) {
		this.countTotal = countTotal;
	}

	/**
	 * 设置排序字段, 多个排序字段时用','分隔.
	 */
	public void setOrderBy(final String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 设置排序方向.
	 * 
	 * @param orderDir
	 *            可选值为desc或asc,多个排序字段时用','分隔.
	 */
	public void setOrderDir(final String orderDir) {
		final String lowcaseOrderDir = StringUtils.lowerCase(orderDir);
		// 检查order字符串的合法值
		final String[] orderDirs = StringUtils.split(lowcaseOrderDir, ',');
		for (final String orderDirStr : orderDirs) {
			if (!StringUtils.equals(Sort.DESC, orderDirStr)
					&& !StringUtils.equals(Sort.ASC, orderDirStr)) {
				throw new IllegalArgumentException("排序方向" + orderDirStr
						+ "不是合法值");
			}
		}

		this.orderDir = lowcaseOrderDir;
	}

	/**
	 * 设置当前页的页号, 序号从1开始, 低于1时自动调整为1.
	 */
	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	/**
	 * 设置每页的记录数量, 低于1时自动调整为1.
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;

		if (pageSize < 1) {
			this.pageSize = 1;
		}
	}
}
