package cn.inovance.iotgp.common.util;

public class CRCUtil {
	public static final int evalCRC16(byte[] data) {
		int crc = 0xFFFF;
		for (int i = 0; i < data.length; i++) {
			crc = (data[i] << 8) ^ crc;
			for (int j = 0; j < 8; ++j)
				if ((crc & 0x8000) != 0)
					crc = (crc << 1) ^ 0x1021;
				else
					crc <<= 1;
		}
		return (crc ^ 0xFFFF) & 0xFFFF;
	}
	
}