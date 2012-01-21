package org.nutz.viv.module;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.expressme.openid.Association;
import org.expressme.openid.Authentication;
import org.expressme.openid.Endpoint;
import org.expressme.openid.OpenIdManager;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.viv.bean.UserBean;
import org.nutz.viv.dao.UserDao;

import com.mongodb.BasicDBObject;

@IocBean
@InjectName
@At("/user")
public class UserModule {

    static final long ONE_HOUR = 3600000L;
    static final long TWO_HOUR = ONE_HOUR * 2L;
    static final String ATTR_MAC = "openid_mac";
    static final String ATTR_ALIAS = "openid_alias";
	
	private String enpoint = "Google";
	
	private OpenIdManager manager = new OpenIdManager();
	
	@Inject
	private UserDao userDao;
	
	@At("/login")
	@Ok(">>:${obj}")
	public String login(HttpSession session) {
		manager.setReturnTo(Mvcs.getReq().getRequestURL().toString() + "/callback");
		manager.setRealm("http://"+Mvcs.getReq().getHeader("Host") + "/");
		Endpoint endpoint = manager.lookupEndpoint(enpoint);
        Association association = manager.lookupAssociation(endpoint);
        session.setAttribute(ATTR_MAC, association.getRawMacKey());
        session.setAttribute(ATTR_ALIAS, endpoint.getAlias());
        return manager.getAuthenticationUrl(endpoint, association);
	}
	
	@At("/logout")
	@Ok("void")
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
	@At("/login/callback")
	public void returnPoint(HttpServletRequest request) {
        // get authentication:
        byte[] mac_key = (byte[]) request.getSession().getAttribute(ATTR_MAC);
        String alias = (String) request.getSession().getAttribute(ATTR_ALIAS);
        Authentication authentication = manager.getAuthentication(request, mac_key, alias);
        authentication.getEmail();
        UserBean user = (UserBean) userDao.findOne("email", authentication.getEmail());
        if (user == null) {
        	user = new UserBean();
        	user.setEmail(authentication.getEmail());
        	user.setOpenid("Google");
        	user.setNotifyConfig(new HashMap<String, String>());
        	userDao.insert(user);
        }
        request.getSession().setAttribute("me", user);
	}

}