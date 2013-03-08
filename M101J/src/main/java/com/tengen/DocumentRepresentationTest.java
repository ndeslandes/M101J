package com.tengen;

import com.mongodb.BasicDBObject;

import java.util.Arrays;
import java.util.Date;

public class DocumentRepresentationTest {
   public static void main(String[] args) {
      BasicDBObject doc = new BasicDBObject();
      doc.put("username", "ndeslandes");
      doc.put("birthDate", new Date(234832423));
      doc.put("programmer", true);
      doc.put("age", 8);
      doc.put("languages", Arrays.asList("Java", "C++"));
      doc.put("address", new BasicDBObject("street", "5699 15e avenue").append("Town", "Montreal").append("Zip", "H1X2V1"));

   }
}
