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
                List<DBObject> scores = (List<DBObject>) student.get("scores");
                for (DBObject scoreDoc : scores) {
                    String type = (String) scoreDoc.get("type");
                    Double score = (Double) scoreDoc.get("score");

                }
            }
        } finally {
            cursor.close();
        }
    }
}
