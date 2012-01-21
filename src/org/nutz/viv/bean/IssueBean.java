package org.nutz.viv.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bugull.mongo.annotations.Entity;
import com.bugull.mongo.annotations.Property;
import com.bugull.mongo.annotations.Ref;
import com.bugull.mongo.annotations.RefList;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity(name="viv_issue")
public class IssueBean extends MongodbBean {

	@Property
	private long issueNo;
	@Property
	private String title;
	@Ref(cascade="R")
	private UserBean reportBy;
	@Property
	private Date createTime;
	@Ref(cascade="R")
	private UserBean assigneTo;
	@Property
	private List<String> tags;
	@RefList
	private List<UserBean> watchers;
	@RefList(sort="{createTime:1}",cascade="CR")
	private List<AttachmentBean> attachments;
	@RefList(sort="{createTime:1}",cascade="CR")
	private List<CommentBean> comments;
	@Property
	private String status;
	
	public static IssueBean create(String title, String content,UserBean user) {
		IssueBean issue = new IssueBean();
		issue.setTitle(title);
		issue.setReportBy(user);
		issue.setCreateTime(new Date());
		issue.setWatchers(new ArrayList<UserBean>());
		issue.getWatchers().add(user);
		issue.setTags(new ArrayList<String>());
		issue.setStatus("OPEN");
		CommentBean comment = CommentBean.create(user, content);
		issue.setComments(new ArrayList<CommentBean>());
		issue.getComments().add(comment);
		return issue;
	}
}
