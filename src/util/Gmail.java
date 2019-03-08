package util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

//Authenticator은 인증을 도와주는 것
public class Gmail extends Authenticator {
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("a01084668556@gmail.com","rlatnfls00");
	}
	
}
