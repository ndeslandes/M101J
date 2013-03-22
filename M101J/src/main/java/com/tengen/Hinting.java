package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;

public class Hinting {
   public static void main(String[] args) throws UnknownHostException {
      MongoClient client = new MongoClient();
      DB db = client.getDB("test");

      BasicDBObject query = new BasicDBObject("a",40000);
      query.append("b", 40000);
      query.append("c", 40000);

      DBCollection c = db.getCollection("foo");

      DBObject doc = c.find(query).hint("a_1_b_-1_c_1").explain();

//      BasicDBObject myHint = new BasicDBObject("a", 1).append("b", -1).append("c", 1);
//      DBObject doc = c.find(query).hint(myHint).explain();

      for (String s : doc.keySet()) {
         System.out.printf("%25s:%s\n", doc.get(s));
      }
   }
}
