package org.nutz.viv.bean;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.bugull.mongo.annotations.EnsureIndex;
import com.bugull.mongo.annotations.Entity;
import com.bugull.mongo.annotations.Property;
import com.bugull.mongo.annotations.Ref;


@Data
@EqualsAndHashCode(callSuper=false)
@Entity(name="viv_attachment")
@EnsureIndex("{'name':1}")
public class AttachmentBean extends MongodbBean {
	
	@Ref(cascade="R")
	private UserBean uploadBy;
	@Property
	private String name;
	@Property
	private long size;
	@Property
	private String mimeType;
	@Property
	private Date createTime;
	
	public static AttachmentBean create(UserBean user, String name, long size) {
		AttachmentBean attachment = new AttachmentBean();
		attachment.setCreateTime(new Date());
		attachment.setName(name);
		attachment.setSize(size);
		attachment.setUploadBy(user);
		return attachment;
	}
}
