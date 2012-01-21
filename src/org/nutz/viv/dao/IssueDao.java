package org.nutz.viv.dao;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.viv.bean.IssueBean;

import com.bugull.mongo.AdvancedDao;

@IocBean
public class IssueDao extends AdvancedDao {
	
	public IssueDao() {
		super(IssueBean.class);
	}
}
