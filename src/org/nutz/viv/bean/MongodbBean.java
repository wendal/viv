package org.nutz.viv.bean;

import java.io.Serializable;

import lombok.Setter;

import lombok.Getter;

public class MongodbBean implements Serializable {
	
	private static final long serialVersionUID = 5051833166861328841L;
	
	@Getter
	@Setter
	private transient String _id;

}
