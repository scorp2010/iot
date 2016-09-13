/**
 * Project Name:iotgp-common-db
 * File Name:MyBlobImpl.java
 * Package Name:cn.inovance.iotgp.common.util
 * Date:2014-9-26上午9:21:04
 * Copyright (c) 2014, Shenzhen Inovance technology Co., Ltd  All Rights Reserved.
 *
*/

package cn.inovance.iotgp.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javassist.expr.NewArray;

/**
 * ClassName:MyBlobImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-9-26 上午9:21:04 <br/>
 * @author   w1898
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class MyBlobImpl implements Blob {

	private InputStream stream;
	private int length;
	
	private boolean needsRest;
	
	public MyBlobImpl(byte [] bytes){
		
		this.stream = new ByteArrayInputStream(bytes);
		this.length = bytes.length;
	}
	public MyBlobImpl(InputStream stream,int length){
		
		this.stream = stream;
		this.length = length;
	}
	public InputStream getStream() {
		return stream;
	}

	public void setStream(InputStream stream) {
		this.stream = stream;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isNeedsRest() {
		return needsRest;
	}

	public void setNeedsRest(boolean needsRest) {
		this.needsRest = needsRest;
	}

	@Override
	public long length() throws SQLException {

		return this.length;
	}

	@Override
	public byte[] getBytes(long pos, int length) throws SQLException {

		excep();
		return null;
	}

	@Override
	public InputStream getBinaryStream() throws SQLException {

		try{
			
			if(this.needsRest){
				
				this.stream.reset();
			}
		}catch( IOException e){
			throw new SQLException("could not reset reader");
		}
		this.needsRest = true;
		return this.stream;
	}

	@Override
	public long position(byte[] pattern, long start) throws SQLException {

		excep();
		return 0;
	}

	@Override
	public long position(Blob pattern, long start) throws SQLException {

		excep();
		return 0;
	}

	@Override
	public int setBytes(long pos, byte[] bytes) throws SQLException {

		excep();
		return 0;
	}

	@Override
	public int setBytes(long pos, byte[] bytes, int offset, int len)
			throws SQLException {

		excep();
		return 0;
	}

	@Override
	public OutputStream setBinaryStream(long pos) throws SQLException {

		excep();
		return null;
	}

	@Override
	public void truncate(long len) throws SQLException {

		excep();

	}

	@Override
	public void free() throws SQLException {

		try{
			this.stream.close();
			this.length = 0;
		}catch(IOException e){
			throw new SQLException("could not free stream");
		}

	}

	@Override
	public InputStream getBinaryStream(long pos, long length)
			throws SQLException {

		try{
			
			if(this.needsRest){
				
				this.stream.reset();
			}
		}catch( IOException e){
			throw new SQLException("could not reset reader");
		}
		this.needsRest = true;
		return this.stream;
	}

	private static  void excep(){
		throw new UnsupportedOperationException("Blob my not be maniupulated from creating session");
	}
}

