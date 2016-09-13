package cn.inovance.iotgp.cdsm.util;

/**
 * 因为手机不能存储第一位为0的随机码,因此在随机码生成时，限制第一位不能为0
 */
public class RandomCode {
	
	public static String generateCodeN(int N) {
		// 定义验证码的字符表
		String chars = "0123456789";

		char[] rands = new char[N];

		// 生成随机数第一位不能为0
		int rand = (int) (Math.random() * 10);
		while (rand == 0)
			rand = (int) (Math.random() * 10);
		rands[0] = chars.charAt(rand);

		for (int i = 1; i < N; i++) {
			rand = (int) (Math.random() * 10);
			rands[i] = chars.charAt(rand);
		}

		return new String(rands);
	}
	
	public static char[] generateCode4() {
		// 定义验证码的字符表
		String chars = "0123456789";

		char[] rands = new char[4];

		// 生成随机数第一位不能为0
		int rand = (int) (Math.random() * 10);
		while (rand == 0)
			rand = (int) (Math.random() * 10);
		rands[0] = chars.charAt(rand);

		for (int i = 1; i < 4; i++) {
			rand = (int) (Math.random() * 10);
			rands[i] = chars.charAt(rand);
		}

		return rands;
	}

	public static char[] generateCode8() {
		// 定义验证码的字符表
		String chars = "0123456789";

		char[] rands = new char[8];

		// 生成随机数第一位不能为0
		int rand = (int) (Math.random() * 10);
		while (rand == 0)
			rand = (int) (Math.random() * 10);
		rands[0] = chars.charAt(rand);

		for (int i = 1; i < 8; i++) {
			rand = (int) (Math.random() * 10);
			rands[i] = chars.charAt(rand);
		}

		return rands;
	}

	public static char[] generateCode16() {
		// 定义验证码的字符表
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		char[] rands = new char[16];
		// 生成随机数第一位不能为0
		int rand = (int) (Math.random() * 36);
		while (rand == 0)
			rand = (int) (Math.random() * 36);
		rands[0] = chars.charAt(rand);

		for (int i = 1; i < 16; i++) {
			rand = (int) (Math.random() * 36);
			rands[i] = chars.charAt(rand);
		}

		return rands;
	}

	public static char[] generateCode18() {
		// 定义验证码的字符表
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		char[] rands = new char[18];
		// 生成随机数第一位不能为0
		int rand = (int) (Math.random() * 36);
		while (rand == 0)
			rand = (int) (Math.random() * 36);
		rands[0] = chars.charAt(rand);

		for (int i = 1; i < 18; i++) {
			rand = (int) (Math.random() * 36);
			rands[i] = chars.charAt(rand);
		}

		return rands;
	}

	public static void main(String[] args) {
		java.util.Random r = new java.util.Random(System.currentTimeMillis());
		for (int i = 0; i < 10; i++) {
			System.out.println(r.nextLong());
		}
	}

}
