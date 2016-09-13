/**
 * Project Name:iotgp-cd-simulator
 * File Name:SimulatorFrame.java
 * Package Name:cn.inovance.iotgp.cdSimulator.client
 * Date:2015-3-31下午6:46:41
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */
/**
 * Project Name:iotgp-cd-simulator
 * File Name:SimulatorFrame.java
 * Package Name:cn.inovance.iotgp.cdSimulator.client
 * Date:2015-3-31下午6:46:41
 * Copyright (c) 2015, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
 */

package cn.inovance.iotgp.cdSimulator.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.inovance.iotgp.cdSimulator.contants.StaticValues;
import cn.inovance.iotgp.cdSimulator.handler.TempClientIoHandler;
import cn.inovance.iotgp.cdSimulator.timerTask.SysTimerTask;

/**
 * ClassName:SimulatorFrame <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-3-31 下午6:46:41 <br/>
 * @author   z1979
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
/**
 * ClassName: SimulatorFrame <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2015-3-31 下午6:46:41 <br/>
 * 
 * @author z1979
 * @version
 * @since JDK 1.7
 */
public class SimulatorFrame extends JFrame implements ActionListener {
	/**
	  * 
	  */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 600;
	public static final int HEIGTH = 600;
	
	public static void main(String[] args) {
		// 加载配置
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"spring-context.xml");

		CdSimulatorMgr cdSimulatorMgr = (CdSimulatorMgr) ctx
				.getBean("cdSimulatorMgr");
		/*
		 * int mode = Integer
		 * .parseInt(readDataFromConsole("请选择运行模式 (1-单台   2-多台)："));
		 */

		System.out.println("当前运行模式为【单台模式】");
		// 启动模拟客户端管理器
		cdSimulatorMgr.start(StaticValues.CdSimulator_RegCode);

		SimulatorFrame gui = new SimulatorFrame();
		gui.setVisible(true);
	}

	JPanel buttonPanel = new JPanel();
	public SimulatorFrame() {
		super("模拟器");
		this.setSize(WIDTH, HEIGTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

			
		buttonPanel.setBackground(Color.LIGHT_GRAY);
		buttonPanel.setLayout(new FlowLayout());
		this.add(buttonPanel, BorderLayout.CENTER);
		addButton("登陆");
		addButton("心跳");
		addButton("版本通知");
		addButton("系统参数通知");
		addButton("事件通知");
	
	}
	//
	private void addButton(String name){
		JButton blueButton = new JButton(name);
		blueButton.addActionListener(this);
		buttonPanel.add(blueButton);
	}
	//
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals("登陆")){
			CdSimulatorMgr.globalNodeClient.sendLoginRequest();
			return;
		}
		if (str.equals("心跳")){
			CdSimulatorMgr.globalNodeClient.sendHeartBeat();
			return;
		}
		if (str.equals("版本通知")){
			CdSimulatorMgr.globalNodeClient.sendSoftwareVersionNotify();
			return;
		}
		if (str.equals("系统参数通知")){
			CdSimulatorMgr.globalNodeClient.sendParametersNotify();
			return;
		}
		if (str.equals("事件通知")){
			CdSimulatorMgr.globalNodeClient.sendCtrlEvent();
			return;
		}
	}
}
