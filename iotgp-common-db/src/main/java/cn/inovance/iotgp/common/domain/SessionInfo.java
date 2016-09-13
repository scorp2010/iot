package cn.inovance.iotgp.common.domain;

/**
 * sessionInfo模型，只要登录成功，就需要设置到session里面，便于系统使用.
 */
public class SessionInfo implements java.io.Serializable {

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return user.getLoginAccount();
	}

}
