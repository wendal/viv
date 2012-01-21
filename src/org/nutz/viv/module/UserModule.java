package org.nutz.viv.module;

import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Attr;
import org.nutz.viv.bean.UserBean;
import org.nutz.viv.dao.UserDao;

@IocBean
@At("/user")
public class UserModule {
	
	@Inject
	private UserDao userDao;

	@At("/login/?")
	public void login(String openId) {
		//TODO
	}
	
	@At("/logout")
	public void logout() {
		Mvcs.getReq().getSession().invalidate();
	}
	
	@At("/login/callback") 
	public void loginCallback() {
		//TODO
	}
	
	@At("/email/config/get")
	public Object queryEmailConfig(@Attr("me") String uid) {
		return ((UserBean)userDao.findOne(uid)).getNotifyConfig();
	}

	@At("/email/config/set")
	public void setEmailConfig(@Attr("me") String uid, Map<String, String> notifyConfig) {
		userDao.set(uid, "notifyConfig", notifyConfig);
	}
}
