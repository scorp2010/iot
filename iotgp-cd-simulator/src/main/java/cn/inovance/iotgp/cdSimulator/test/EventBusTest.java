package cn.inovance.iotgp.cdSimulator.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * @Title: EventBusTest.java
 * @Package com.welab.agent
 * @Description:
 * @author xuy
 * @date 2016年9月12日 下午5:10:22
 * @version V1.0
 */
public class EventBusTest {
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		EventBus eventBus = new EventBus();
		EventBus eventBus1 = EventBus.class.newInstance();
		System.out.println(eventBus);
		System.out.println(eventBus1.equals(eventBus));
		eventBus.register(new Object() {
			@Subscribe
			@AllowConcurrentEvents
			public void handleUserInfoChangeEvent(UserInfoChangeEvent userInfoChangeEvent) {
				System.out.println("处理用户信息变化事件：" + userInfoChangeEvent.getUserName());
			}

			/*@Subscribe
			public void handleUserInfoChangeEvent(BaseEventBusEvent userInfoChangeEvent) {
				System.out.println("所有事件的父类");
			}*/
		});
		eventBus.post(new UserInfoChangeEvent("apple"));
	}

	static class BaseEventBusEvent {

	}

	static class UserInfoChangeEvent extends BaseEventBusEvent {
		private String userName;

		public UserInfoChangeEvent(String userName) {
			this.userName = userName;
		}

		public String getUserName() {
			return userName;
		}
	}

	public static void main1(String[] args) {
		testAsyncEventBus();
	}

	/**
	 * 异步的EventBus
	 */
	public static void testAsyncEventBus() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		AsyncEventBus asyncEventBus = new AsyncEventBus("asyncEventBus", executor);
		/**
		 * 注册事件处理器
		 */
		asyncEventBus.register(new Object() {
			@Subscribe
			@AllowConcurrentEvents
			public void handleUserInfoChangeEvent(UserInfoChangeEvent userInfoChangeEvent) {
				System.out.println("处理用户信息变化事件：" + userInfoChangeEvent.getUserName());
			}

			@Subscribe
			public void handleUserInfoChangeEvent(BaseEventBusEvent userInfoChangeEvent) {
				System.out.println("所有事件的父类");
			}

			@Subscribe
			public void handleUserInfoChangeEvent2(UserInfoChangeEvent userInfoChangeEvent) {
				System.out.println("处理用户信息变化事件1：" + userInfoChangeEvent.getUserName());
			}

			@Subscribe
			public void handleUserInfoChangeEvent3(BaseEventBusEvent userInfoChangeEvent) {
				System.out.println("所有事件的父类4");
			}

		});
		asyncEventBus.post(new UserInfoChangeEvent("apple1"));
		System.out.println("异步EventBus");
	}
}
