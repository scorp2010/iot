package cn.inovance.iotgp.cdsm.exception;

/**
 * 业务异常类
 * @author c2100
 */
public class BusinessException extends AppException {
    private static final long serialVersionUID = 3397561987844156273L;

    public BusinessException(String msgKey) {
        super(msgKey);
    }
    
    public BusinessException(String msgKey,Object[] params) {
        super(msgKey,params);
    }

    public BusinessException(String msgKey, Throwable cause) {
        super(msgKey, cause);
    }

}
