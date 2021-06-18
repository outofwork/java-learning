package com.outofwork.java.streams;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectSerialization {

    public static void main(String[] args) {

        List<Object> objectList = new ArrayList<>();
        objectList.add("0");
        objectList.add(0);
        objectList.add("ABCDEF");
        objectList.add(11.2f);
        objectList.add(false);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./serialized");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(objectList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileInputStream = new FileInputStream("./serialized");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<Object> resultList = (List<Object>) objectInputStream.readObject();
            for (Object object : resultList) {
                System.out.println(object.getClass().getTypeName() + " " + object);
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
