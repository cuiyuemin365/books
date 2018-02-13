import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest {

    private static String basePath = "/data/project/learn/java-base/java-core/Resource";


    public static void api() {
        Path aPath = Paths.get("java", "cay");

        System.out.println(aPath.toString());

        URI aURI = aPath.toUri();
        System.out.println(aURI.toString());

        Path absolutePath = aPath.toAbsolutePath();
        System.out.println(absolutePath.toString());

        System.out.println(aPath.endsWith("cay"));
        System.out.println(aPath.endsWith(Paths.get("cay")));
        System.out.println(aPath.getFileName().toString());
        System.out.println(aPath.getName(0).toString());
        System.out.println(aPath.getNameCount());
        System.out.println(aPath.getParent().toString());
        System.out.println(aPath.getRoot().toString());
        System.out.println(aPath.normalize().toString());






    }


    public static void main(String[] args) {
        api();
//        Path aPath = Paths.get("/home", "cay");
//        Path rPath = Paths.get("test/path");
//        System.out.println(rPath.toAbsolutePath().toString());
//        System.out.println(rPath.getFileName().toString());
//        try {
//            Path p = Paths.get(basePath, "nio.txt");
//            byte[] contents = Files.readAllBytes(p);
//            System.out.println(Arrays.toString(contents));
//            List<String> resultList = Files.readAllLines(p, Charset.forName("UTF-8"));
//            for (String item :
//                    resultList) {
//                System.out.println(item);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
