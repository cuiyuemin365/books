package charset;

import java.nio.charset.Charset;
import java.util.Map;

public class Test001 {

    public static void main(String[] args) {
        Map<String, Charset> map = Charset.availableCharsets();
        System.out.println(map.size());//169
        for (String name : map.keySet()) {
            System.out.println(String.format("name:%s,", name));
        }
        System.out.println();
        Charset charset = Charset.forName("utf-8");
        for (String name : charset.aliases()) {
            System.out.println("UTF-8:" + name);
        }
    }


}
