package org.nutz.viv.util;

import com.bugull.mongo.BuguConnection;
import com.mongodb.BasicDBObject;

public class MongodbHeper {

	public static Integer getAutoIncreaseID(String idName) {  
        BasicDBObject query = new BasicDBObject();  
        query.put("name", idName);
  
        BasicDBObject update = new BasicDBObject();  
        update.put("$inc", new BasicDBObject("id", 1));  
  
        return (Integer) BuguConnection.getInstance().getDB().getCollection("inc_ids").findAndModify(query,  
                null, null, false, update, true, true).get("id");
    } 
}
