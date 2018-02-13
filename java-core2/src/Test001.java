
import java.io.UnsupportedEncodingException;

public class Test001 {

    public static void main(String[] args) {
        String string = "abcdefg";
        try {
            System.out.println(string.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[]{
                97, 10, 11, 13, 14, 15, 16, 127, 126
        };
        System.out.println(new String(bytes));
        System.out.println(System.getProperty("user.dir"));
    }

}
