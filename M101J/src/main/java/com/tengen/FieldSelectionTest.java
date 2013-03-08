package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

public class FieldSelectionTest {
   public static void main(String[] args) throws UnknownHostException {
      MongoClient client = new MongoClient();
      DB db = client.getDB("course");
      DBCollection collection = db.getCollection("fieldSelectionTest");
      collection.drop();
      Random rand = new Random();

      for (int i = 0; i < 10; i++) {
         collection.insert(
               new BasicDBObject("x", rand.nextInt(2))
                     .append("y", rand.nextInt(100))
                     .append("z", rand.nextInt(1000)));
      }

      DBObject query = QueryBuilder.start("x").is(0)
            .and("y").greaterThan(10).lessThan(70).get();

      DBCursor cursor = collection.find(query,
            new BasicDBObject("y", true).append("_id", false));
      try {
         while (cursor.hasNext()) {
            DBObject cur = cursor.next();
            System.out.println(cur);
         }
      } finally {
         cursor.close();
      }
   }
}
