import java.util.regex.Pattern;

public class ObjectCase {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("^[\\\u4e00-\\\u9fa5]$");
        System.out.println(pattern.matcher("崔").matches());
    }

}
