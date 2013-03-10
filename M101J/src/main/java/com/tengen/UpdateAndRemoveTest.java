package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public class UpdateAndRemoveTest {
    public static void main(String[] args) throws UnknownHostException {
        DBCollection collection = createCollection();

        List<String> names = Arrays.asList("alice", "bobby", "cathy", "david", "ethan");
        for (String name : names) {
            collection.insert(new BasicDBObject("_id", name));
        }

        collection.update(new BasicDBObject("_id", "alice"), new BasicDBObject("age", 24));
        collection.update(new BasicDBObject("_id", "alice"), new BasicDBObject("$set", new BasicDBObject("gender", "F")));
        collection.update(new BasicDBObject("_id", "frank"), new BasicDBObject("$set", new BasicDBObject("gender", "M")), true, false);
        collection.update(new BasicDBObject(), new BasicDBObject("$set", new BasicDBObject("title", "Dr.")), false, true);

        collection.remove(new BasicDBObject("_id", "alice"));

        printCollection(collection);
    }

    private static DBCollection createCollection() throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("course");
        DBCollection collection = db.getCollection("updateTest");
        collection.drop();
        return collection;
    }

    private static void printCollection(DBCollection collection) {
        DBCursor cursor = collection.find();

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
