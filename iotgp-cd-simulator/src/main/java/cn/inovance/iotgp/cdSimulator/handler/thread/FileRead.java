package cn.inovance.iotgp.cdSimulator.handler.thread;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import cn.inovance.iotgp.cdSimulator.contants.StaticValues;
import cn.inovance.iotgp.common.enums.SysParameterKeys;
import cn.inovance.iotgp.common.msg.util.ByteOps;

public class FileRead {
	public static void main(String[] args) {
		BufferedInputStream reader=null;
		BufferedOutputStream writer=null;
		try {
			reader=new BufferedInputStream(new FileInputStream("E:/xy项目/06-支撑项目/iframe"));
			byte[] stream=FileRead.getContent("E:/xy项目/06-支撑项目/iframe");
			byte[] stream2=FileRead.getContent("E:/xy项目/06-支撑项目/iframe2");
			byte[] stream3=FileRead.getContent("E:/xy项目/06-支撑项目/iframe3");
			String ss =ByteOps.bytesToHexString(stream);
			int size=stream.length;
			String s2 =ByteOps.bytesToHexString(stream2);
			String s3 =ByteOps.bytesToHexString(stream3);
			byte[] streamData = ByteOps
					.hexStringToBytes("010000000100000001419b90209cbd5439d6a5e5aae23bd6984fad429d6a27ad429ccc7c85ff2f568fcdad5f774b15cdadb176e11e2b5b8d040903834012f35f711abeeab939ab29a939a61017af9719335c6f26ddc6f275511c1132c9f6ae69203c3cbae4e5c6fcf5f16730c193ba638217be6cc56560bcdddd7365a2fe5a3189067daeb61e12f277bdf34916083684c20bdec874917eb57cb19bf1ab0bd00627bbc8665e09af794b0fc4d7db5f2d28d129a180ae5cbe81ae13bbd9012ef3b6571bcb49b437e4a18d03c482485253e13a3cc743cd2da68a4cc204800161088ead244293bc3c68f100bc3362231c5b57f5d5d7c4e8139e9443671719f343a9e77842f3593b87b5cfbf8249fc8bda2bbd506df92d87755fe12dc6ede7349435c11d022088d1291deed8938bc9cc7e1debe4b0919b717cd7b224bde4832fc27a36c96664ea7f370c303ed71f4b73a02d3a446fe5cd038c77cba6b5c116432753d7d6a2b822a3b4ac1f7bb32ab2f34ba58eb9af9a8f823aa1d7a279393a7e3e95e4232ecf7a5eb55c92423996b92cc389da0e765cf842cdbc42c664a92e271e1bba41d0fead5c12537a1c17cb");
//			System.out.println(new String(ss));
//			System.out.println(new String(s2));
			System.out.println(new String(s3));
			while (reader.read()!=-1) {
			}
			
			int pollCount=0;
			while (true&&pollCount<=15) {
				pollCount++;
				//System.out.println(pollCount+"*****"+(pollCount%2==0));
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				reader.close();
				//writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static byte[] getContent(String filePath) throws IOException {
		File file = new File(filePath);
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too big...");
			return null;
		}
		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) fileSize];
		int offset = 0;
		int numRead = 0;
		while (offset < buffer.length
		&& (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
			offset += numRead;
		}
		// 确保所有数据均被读取
		if (offset != buffer.length) {
		throw new IOException("Could not completely read file "
					+ file.getName());
		}
		fi.close();
		return buffer;
	}
}
