package cn.inovance.iotgp.cdsm.constants;

public class AppConstants {

	/** 查询：通配符 */
    public static final String SEARCH_ALL_SYMBOL = "*";
    /** 包根路径 */
    public static final String BASE_PACKAGE = "cn.inovance.iotgp.cdsm";
	/** 全局异常处理使用 */
    public static final String EXCEPTION_KEY = "exceptionKey";
    
	/** =========================系统级错误代号========================= */
    /** 系统内部错误 */
    public static final String ERROR_CODE_E1SP0001 = "E1SP0001";
    /** 参数错误，请参考API文档 */
    public static final String ERROR_CODE_E1SP0002 = "E1SP0002";
    /** 请求的HTTP METHOD不支持，请检查是否选择了正确的POST/GET方式 */
    public static final String ERROR_CODE_E1SP0003 = "E1SP0003";
    /** 权限不足 */
    public static final String ERROR_CODE_E1SP0004 = "E1SP0004";
    /** 没有登录 */
    public static final String ERROR_CODE_E1SP0005 = "E1SP0005";
    /** 当前登录用户数已经达到最大值 */
    public static final String ERROR_CODE_E1SP0006 = "E1SP0006";

    /** =========================业务级错误代号========================= */
    /** 用户名不存在 */
    public static final String ERROR_CODE_E2SP0100 = "E2SP0100";
    /** 用户名与密码不匹配 */
    public static final String ERROR_CODE_E2SP0101 = "E2SP0101";
    /** 请使用注册时绑定的手机登录 */
    public static final String ERROR_CODE_E2SP0102 = "E2SP0102";
    /** 请求超时 */
    public static final String ERROR_CODE_E2SP0103 = "E2SP0103";
    /** 用户没有得到授权，请授权后再登陆 */
    public static final String ERROR_CODE_E2SP0104 = "E2SP0104";
    /** 登陆密码不正确 */
    public static final String ERROR_CODE_E2SP0105 = "E2SP0105";
    /** 账号已被停用，不允许登陆 */
    public static final String ERROR_CODE_E2SP0106 = "E2SP0106";

}
