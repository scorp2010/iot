package cn.inovance.iotgp.cdsm.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import cn.inovance.iotgp.cdsm.bean.CdSimulatorAjaxInfo;
import cn.inovance.iotgp.cdsm.bean.CdSimulatorInfo;
import cn.inovance.iotgp.cdsm.bean.JsonResponse;
import cn.inovance.iotgp.cdsm.bean.easyui.Grid;
import cn.inovance.iotgp.cdsm.constants.StaticValues;
import cn.inovance.iotgp.cdsm.server.CdsmServer;
import cn.inovance.iotgp.cdsm.util.CDMSessionService;
import cn.inovance.iotgp.cdsm.util.DatetimeUtil;
import cn.inovance.iotgp.cdsm.util.StringUtil;
import cn.inovance.iotgp.common.msg.cd.SimulateDeviceInfo;
import cn.inovance.iotgp.common.msg.cdSimulator.BatchHandlerFlag;
import cn.inovance.iotgp.common.msg.cdSimulator.ConfigNofity;

@Controller
@RequestMapping("/cdSimulator")
public class CdSimulatorController extends BaseController {

	@RequestMapping("/init")
	public String init(HttpServletRequest request,
			HttpServletResponse response, HttpSession httpSession)
			throws Exception {
		logger.info("/cdSimulator/init");
		return "cdSimulator";
	}

	@RequestMapping("/deatil")
	public String deatil(@RequestParam(value="clientCode",required=true) String clientCode,
						HttpServletRequest request,HttpServletResponse response
						){
		Map<String, SimulateDeviceInfo> map = CDMSessionService.getInstance().getDeviceLoginInfos(clientCode);
		System.out.println("-manager-lenght-"+map.size()+"-valu1e-");
		System.out.println("**********"+clientCode);
		return "cdSimulatorDeatil";
	}
	
	@ResponseBody
	@RequestMapping("/deviceInfo")
	public Grid deviceInfo(@RequestParam(value="clientCode",required=true) String clientCode,
			HttpServletRequest request,HttpServletResponse response){
		List<SimulateDeviceInfo> deviceInfoList=new ArrayList<SimulateDeviceInfo>();
		Map<String, SimulateDeviceInfo> map = CDMSessionService.getInstance().getDeviceLoginInfos(clientCode);
//		for (CdSimulatorInfo value : CdsmServer.clientOnlineList.values()) {
			if (map != null) {
				Iterator<Entry<String, SimulateDeviceInfo>>  it=map.entrySet().iterator();
				while (it.hasNext()) {
					Entry<String, SimulateDeviceInfo> entry=it.next();
					System.out.println("*----***"+entry.getKey()+" regcode:"+entry.getValue().getRegCode()+" reconnect conunt:"+entry.getValue().getReconnectCount());
					deviceInfoList.add(entry.getValue());
				}
			}
			
//		}
		
		return ajaxGrid(deviceInfoList);
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public Grid list(HttpServletRequest request, HttpServletResponse response,
			HttpSession httpSession) {
		logger.info("/cdSimulator/list");
		List<CdSimulatorAjaxInfo> cdSimulatorList = new ArrayList<CdSimulatorAjaxInfo>();
		System.out.println("****clientOnlineList size:"+CdsmServer.clientOnlineList.size());
		for (CdSimulatorInfo value : CdsmServer.clientOnlineList.values()) {
			CdSimulatorAjaxInfo obj = new CdSimulatorAjaxInfo();
			obj.setClientCode(value.getClientCode());
			obj.setgID(value.getgID());
			obj.setLoginedStr(value.getLogined() ? "已登录" : "未登录");
			obj.setRemoteAddress(value.getRemoteAddress());
			obj.setDistributeCount(value.getDistributeCount());
			obj.setConnectCount(value.getConnectCount());
			obj.setOnlineCount(value.getOnlineCount());
			obj.setConnectTimeStr(DatetimeUtil.dateTime2Str(value
					.getConnectTime()));
			obj.setLastInteractiveTimeStr(DatetimeUtil.dateTime2Str(value
					.getLastInteractiveTime()));

			cdSimulatorList.add(obj);
		}
//		System.out.println("--**clientLinkList size:"+CdsmServer.clientLinkList.size());
//		for (CdSimulatorInfo value : CdsmServer.clientLinkList.values()) {
//			if (value.getSimulateDeviceInfoMap() != null) {
//				Iterator<Entry<String, SimulateDeviceInfo>>  it=value.getSimulateDeviceInfoMap().entrySet().iterator();
//				while (it.hasNext()) {
//					Entry<String, SimulateDeviceInfo> entry=it.next();
//					System.out.println("*----***"+entry.getKey()+" regcode:"+entry.getValue().getRegCode()+" reconnect conunt:"+entry.getValue().getReconnectCount());
//				}
//			}
//		}
//		
		return ajaxGrid(cdSimulatorList);
	}

	@ResponseBody
	@RequestMapping("/update")
	public JsonResponse update(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("/cdSimulator/update");

		try {
			String configListStr = request.getParameter("configListStr");
			if (configListStr.endsWith(";"))
				configListStr = configListStr.substring(0,
						configListStr.length() - 1); // 去掉最后一个";"
			System.out.println("configListStr:" + configListStr);
			String[] configList = configListStr.split(";");
			String selectSql = "select distinct t from CdRegisterCode t where t.regCode like 'szinovancf%' order by t.regCode asc";
			int start = 0;
			for (String config : configList) {
				String securityCode = config.split(":")[0];
				int newDistributeCount = Integer.parseInt(config.split(":")[1]);
				if (StringUtil.isNullOrEmptyString(securityCode)
						|| newDistributeCount <= 0)
					continue;

				CdSimulatorInfo cdSimulatorInfo = CdsmServer.clientOnlineList
						.get(securityCode);
				if (cdSimulatorInfo != null) {
					// 更新分配数
					cdSimulatorInfo.setDistributeCount(newDistributeCount);

					// 发送模拟器参数配置通知消息
					ConfigNofity notify = new ConfigNofity();
					notify.setFrom(StaticValues.sysAccount);
					notify.setSelectSql(selectSql);
					notify.setStart(start);
					notify.setCount(newDistributeCount);
					notify.setOnlyTestLogin(false); // 暂时只测试登录
					BatchHandlerFlag batchHandler=new BatchHandlerFlag();//批量处理类
					batchHandler.setRundataTest(true);//rundataTest:实时数据上报测试 1、true 启动 2、false 不启动;
					notify.setBatchHandlerFlag(batchHandler);
					//rundataTest:实时数据上报测试 1、true 启动 2、false 不启动;
					//notify.setBatchHandlerFlag(new BatchHandlerFlag.Builder().rundataTest(true).build());
					cdSimulatorInfo.getSession().write(notify.toJsonString(notify));
					logger.info("发送模拟器参数配置通知消息:{}", notify.toJsonString(notify));

					start += newDistributeCount;
				} else {
					logger.warn("所配置的模拟器客户端[{}]不在线", securityCode);
				}
			}
		} catch (Exception e) {
			return ajaxFailure(e.getMessage());
		}

		return ajaxSuccess();
	}
}
