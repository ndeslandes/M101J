package com.tengen.hw;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.List;

public class hw31 {
   public static void main(String[] args) throws UnknownHostException {
      MongoClient client = new MongoClient();
      DB db = client.getDB("school");
      DBCollection students = db.getCollection("students");

      System.out.println(students.count());

      DBCursor cursor = students.find();
      try {
         while (cursor.hasNext()) {
            DBObject student = cursor.next();
            Integer studentId = (Integer) student.get("_id");
            List<DBObject> scores = (List<DBObject>) student.get("scores");
            DBObject lowestHomeworkScoreDoc = getLowestHoweworkScoreDocument(scores);
            scores.remove(lowestHomeworkScoreDoc);
            students.update(new BasicDBObject("_id", studentId), new BasicDBObject("$set", new BasicDBObject("scores", scores)));
         }
      } finally {
         cursor.close();
      }
   }

   private static DBObject getLowestHoweworkScoreDocument(List<DBObject> scores) {
      DBObject lowestHomeworkScoreDoc = null;
      for (DBObject scoreDoc : scores) {
         String type = (String) scoreDoc.get("type");
         if (type.equals("homework")) {
            Double score = (Double) scoreDoc.get("score");
            if (lowestHomeworkScoreDoc == null || score < (Double) lowestHomeworkScoreDoc.get("score")) {
               lowestHomeworkScoreDoc = scoreDoc;
            }
         }
      }
      return lowestHomeworkScoreDoc;
   }
}
