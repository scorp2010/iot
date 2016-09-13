package cn.inovance.iotgp.common.msg.cdSimulator;


/**   
 * 批量处理标志类
 * @Title: BatchRequest.java 
 * @Package cn.inovance.iotgp.common.msg.cdSimulator 
 * @Description: 
 * @author xy2126 
 * @date 2015-12-1 上午10:55:49 
 * @version V1.0   
 */
public class BatchHandlerFlag {
	private boolean rundataTest;

	public boolean isRundataTest() {
		return rundataTest;
	}

	public void setRundataTest(boolean rundataTest) {
		this.rundataTest = rundataTest;
	}
	
	
	
	
	
	
	
//	private final boolean rundataTest;
//	
//	
//	/**
//	 * @param rundataTest
//	 */
//	public BatchHandlerFlag(boolean rundataTest) {
//		this.rundataTest = rundataTest;
//	}
//	
//	//builder模式
//	public static class Builder{
//		private  boolean rundataTest;
//		
//		public Builder(){
//			
//		}
//		
//		public Builder rundataTest(boolean flag){
//			this.rundataTest=flag;
//			return this;
//		}
//		public BatchHandlerFlag build(){
//			return new BatchHandlerFlag(this);
//		}
//	}
//	
//	
//	private BatchHandlerFlag(Builder b){
//		rundataTest=b.rundataTest;
//	}
//	public boolean isRundataTest() {
//		return rundataTest;
//	}
}
