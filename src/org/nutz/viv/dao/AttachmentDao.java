package org.nutz.viv.dao;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.viv.bean.AttachmentBean;

import com.bugull.mongo.BuguDao;

@IocBean
public class AttachmentDao extends BuguDao {
	
	public AttachmentDao() {
		super(AttachmentBean.class);
	}
}
