package cn.inovance.iotgp.cdsm.bean.easyui;

import java.util.ArrayList;
import java.util.List;

/**
 * EasyUI DataGrid模型.
 * 
 * @param <T>
 */
@SuppressWarnings("rawtypes")
public class Grid implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long total = 0L;

	private List rows = new ArrayList();

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

}
