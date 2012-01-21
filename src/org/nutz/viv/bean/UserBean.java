package org.nutz.viv.bean;

import java.util.Map;

import com.bugull.mongo.annotations.Entity;
import com.bugull.mongo.annotations.Property;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity(name="vuser")
public class UserBean extends MongodbBean {

	@Property
	private String email;
	@Property
	private String openid;
	@Property
	private Map<String, String> notifyConfig;
}
