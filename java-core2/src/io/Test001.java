package io;

import java.io.*;

public class Test001 {

    public static void t1() {
        try (FileInputStream fileInputStream = new FileInputStream("/data/project/learn/java-base/java-core/src/test001.dat")) {
            int i;
            while ((i = fileInputStream.read()) != -1) {
                System.out.println(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void t2() {
        try {
            DataOutputStream out = new DataOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(
                                    "/data/project/learn/java-base/java-core/src/test001.dat")));
            out.writeInt(123);
            out.writeUTF("Aa");
            out.writeBoolean(true);
            out.flush();
            out.close();
            DataInputStream in = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream(
                                    "/data/project/learn/java-base/java-core/src/test001.dat")));
            int a = in.readInt();
            System.out.println("first int is " + a);
            String b = in.readUTF();
            System.out.println("second string is " + b);
            boolean c = in.readBoolean();
            System.out.println("third boolean is " + c);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        t1();
//        t2();
    }
}
