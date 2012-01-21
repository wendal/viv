package org.nutz.viv.mvc;

import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import com.bugull.mongo.BuguConnection;

public class VivSetup implements Setup {

	private static final Log log = Logs.get();

	@Override
	public void init(NutConfig config) {
		config.getIoc().get(BuguConnection.class).connect();
		log.debug("Viv init complete");
	}

	@Override
	public void destroy(NutConfig config) {
	}

}
