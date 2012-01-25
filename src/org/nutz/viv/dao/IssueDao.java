package org.nutz.viv.dao;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.viv.bean.IssueBean;

@IocBean
public class IssueDao extends EnhandBuguDao<IssueBean> {

	public IssueDao() {
		super(IssueBean.class);
	}
}
