package util;

import java.security.MessageDigest;

public class SHA256 {
//이메일에 해쉬를 적용한 값을 반환하는 함수
	public static String getSHA256(String input) {
		StringBuffer result=new StringBuffer();
	
		try {
			MessageDigest digest=MessageDigest.getInstance("SHA-256");
			//사용자가 입력한 값을 알고리즘으로
			//해킹에 대비한 코드
			byte[] salt="Hello! This is Salt".getBytes();
			digest.reset();
			digest.update(salt);
			byte[] chars=digest.digest(input.getBytes("UTF-8"));
			for(int i=0;i<chars.length;i++) {
				String hex=Integer.toHexString(0xff&chars[i]);
				if(hex.length()==1)result.append("0");
				result.append(hex);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
}
