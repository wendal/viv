package org.nutz.viv.module;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.expressme.openid.Association;
import org.expressme.openid.Authentication;
import org.expressme.openid.Endpoint;
import org.expressme.openid.OpenIdException;
import org.expressme.openid.OpenIdManager;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.viv.bean.UserBean;
import org.nutz.viv.dao.UserDao;

@IocBean
@InjectName
@At("/user")
public class UserModule {

    static final long _5min = 300000L;
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
		manager.setTimeOut(300 * 1000);
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
	public String returnPoint(HttpServletRequest request) {
		checkNonce(request.getParameter("openid.response_nonce"));
        // get authentication:
        byte[] mac_key = (byte[]) request.getSession().getAttribute(ATTR_MAC);
        String alias = (String) request.getSession().getAttribute(ATTR_ALIAS);
        Authentication authentication = manager.getAuthentication(request, mac_key, alias);
        authentication.getEmail();
        UserBean user = userDao.findOne("email", authentication.getEmail());
        if (user == null) {
        	user = new UserBean();
        	user.setEmail(authentication.getEmail());
        	user.setOpenid("Google");
        	user.setNotifyConfig(new HashMap<String, String>());
        	userDao.insert(user);
        }
        request.getSession().setAttribute("me", user);
        return "Login success!";
	}
	
	protected void checkNonce(String nonce) {
        // check response_nonce to prevent replay-attack:
        if (nonce==null || nonce.length()<20)
            throw new OpenIdException("Verify failed.");
        long nonceTime = getNonceTime(nonce);
        long diff = System.currentTimeMillis() - nonceTime;
        if (diff < 0)
            diff = (-diff);
        if (diff > _5min)
            throw new OpenIdException("Bad nonce time.");
		
	}
	
    private static long getNonceTime(String nonce) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .parse(nonce.substring(0, 19) + "+0000")
                    .getTime();
        }
        catch(ParseException e) {
            throw new OpenIdException("Bad nonce time.");
        }
    }

}