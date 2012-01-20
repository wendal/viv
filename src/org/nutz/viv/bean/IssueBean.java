package org.nutz.viv.bean;

import java.util.Date;

import lombok.Data;

@Data
public class IssueBean {

	private String id;
	private UserBean reportBy;
	private Date createTime;
	private UserBean assigneTo;
	private String[] tags;
	private UserBean[] watchers;
	private String[] attachments;
	private CommentBean[] comments;
}
