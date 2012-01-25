package org.nutz.viv.dao;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.viv.bean.UserBean;

@IocBean
public class UserDao extends EnhandBuguDao<UserBean> {

	public UserDao() {
		super(UserBean.class);
	}
}
