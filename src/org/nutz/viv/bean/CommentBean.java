package org.nutz.viv.bean;

import java.util.Date;

import com.bugull.mongo.annotations.Entity;
import com.bugull.mongo.annotations.Property;
import com.bugull.mongo.annotations.Ref;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity(name="viv_issue")
public class CommentBean extends MongodbBean {

	@Ref(cascade="R")
	private UserBean user;
	@Property
	private Date createTime;
	@Property
	private String content;
	
	public static CommentBean create(UserBean user, String content) {
		CommentBean comment = new CommentBean();
		comment.setContent(content);
		comment.setUser(user);
		comment.setCreateTime(new Date());
		return comment;
	}
}
