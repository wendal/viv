package org.nutz.viv.module;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;

@IocBean
@At("/user")
public class UserModule {

	@At("/login/?")
	public void login(String openId) {
		//TODO
	}
	
	@At("/logout")
	public void logout() {
		//TODO
	}
	
	@At("/login/callback") 
	public void loginCallback() {
		//TODO
	}
	
	@At
	public void queryEmailConfig() {}

	@At
	public void setEmailConfig() {}
}
