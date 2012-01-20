package org.nutz.viv.bean;

import java.util.Date;

import lombok.Data;

@Data
public class CommentBean {

	private UserBean user;
	private Date createTime;
	private String content;
}
