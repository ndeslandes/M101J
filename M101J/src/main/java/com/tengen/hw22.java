package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;

public class hw22 {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("students");
        DBCollection grades = db.getCollection("grades");

        DBCursor cursor = grades.find(new BasicDBObject("type", "homework")).sort(new BasicDBObject("student_id", 1).append("score", 1));

        Integer storedStudentId = null;
        try {
            while (cursor.hasNext()) {
                DBObject cur = cursor.next();
                Integer studentId = (Integer) cur.get("student_id");
                if (storedStudentId == null || !storedStudentId.equals(studentId)) {
                    System.out.println("We remove ");
                    System.out.println(cur);
                    grades.remove(cur);
                    storedStudentId = studentId;
                }
            }
        } finally {
            cursor.close();
        }
    }
}
