package io;

import java.nio.charset.Charset;

public class CharsetCase {

    public static void main(String[] args) {
        String str = "我";
        System.out.println(str);
        byte[] bytes1 = str.getBytes();
        System.out.println("UTF-8:" + bytesToHex(bytes1));
        byte[] bytes2 = str.getBytes(Charset.forName("UTF-16"));
        System.out.println("UTF-16:" + bytesToHex(bytes2));
        byte[] bytes3 = str.getBytes(Charset.forName("UTF-32"));
        System.out.println("UTF-32:" + bytesToHex(bytes3));
    }

    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String bytesToHex(byte[] bytes) {
        // 一个byte为8位，可用两个十六进制位标识
        char[] buf = new char[bytes.length * 3];
        int a = 0;
        int index = 0;
        for (byte b : bytes) { // 使用除与取余进行转换
            if (b < 0) {
                a = 256 + b;
            } else {
                a = b;
            }

            buf[index++] = HEX_CHAR[a / 16];
            buf[index++] = HEX_CHAR[a % 16];
            buf[index++] = '-';
        }

        return new String(buf);
    }

}
