package org.nutz.viv.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

import com.bugull.mongo.BuguEntity;
import com.bugull.mongo.annotations.Id;

public abstract class MongodbBean implements Serializable, BuguEntity {
	
	private static final long serialVersionUID = 5051833166861328841L;
	
	@Getter
	@Setter
	@Id
	private transient String id;

}
