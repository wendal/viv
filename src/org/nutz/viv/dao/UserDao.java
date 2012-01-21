package org.nutz.viv.dao;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.viv.bean.UserBean;

import com.bugull.mongo.BuguDao;

@IocBean
public class UserDao extends BuguDao {

	public UserDao() {
		super(UserBean.class);
	}
}
