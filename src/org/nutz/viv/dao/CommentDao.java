package org.nutz.viv.dao;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.viv.bean.CommentBean;

import com.bugull.mongo.BuguDao;

@IocBean
public class CommentDao extends BuguDao {
	
	public CommentDao() {
		super(CommentBean.class);
	}
}
