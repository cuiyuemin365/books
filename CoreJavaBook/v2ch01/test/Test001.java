package test;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class Test001 {

    public static void test001() throws Exception {
        System.out.print(System.getProperty("user.dir"));
        try (OutputStream outputStream = new FileOutputStream("hello.txt")) {
            outputStream.write("hello world".getBytes("utf-8"));
        }
    }

    public static void main(String[] args) throws Exception {
        test001();
//        String a = "34,a,b,c,d";
//        byte[] bytes = a.getBytes("utf-8");
//        for (byte item : bytes) {
//            System.out.print(Integer.toHexString(item) + " ");
//        }
    }
}
