package com.tengen;

import com.mongodb.*;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;

public class InsertTest {
   public static void main(String[] args) throws UnknownHostException {
      MongoClient client = new MongoClient();
      DB courseDB = client.getDB("course");
      DBCollection collection = courseDB.getCollection("insertTest");

      collection.drop();

      DBObject doc = new BasicDBObject().append("x", 1);

      collection.insert(doc);
      collection.insert(doc);
   }

}
