package ch1.p6;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class PathTest {

    public static void t1() {
        Path absolute = Paths.get("/home", "cay");
        Path relative = Paths.get("myconfig", "conf", "user.properties");
        System.out.println(absolute.toString());
        ///home/cay
        System.out.println(relative.toString());
        //myconfig/conf/user.properties
    }

    public static void t2() {
        Path homeRelative = Paths.get("/home/test");
        System.out.println(homeRelative.resolve("pwd").toString());
        ///home/test/pwd
        System.out.println(homeRelative.resolveSibling("pwd").toString());
        ///home/pwd
    }

    public static void t3() {
        Path homeRelative = Paths.get("home");
        System.out.println(homeRelative.toAbsolutePath().toString());
        ///data/project/learn/books/java-core2/home
    }

    public static void t4() {
        Path homeRelative = Paths.get("/home/test");
        System.out.println(homeRelative.relativize(Paths.get("/home/java/ll")).toString());
        //../java/ll
    }


    public static void t5() {
        Path homeRelative = Paths.get("/home/cay/../fred/./muyprog").normalize();
        System.out.println(homeRelative);
        ///home/fred/muyprog
    }

    public static void t6() {
        Path absolute = Paths.get("/home", "cay");
        Path relative = Paths.get("myconfig", "conf", "user.properties");
        System.out.println(absolute.getRoot().toString());
        ///
        System.out.println(relative.getRoot().toString());
        //java.lang.NullPointerException
    }

    public static void t7() {
        Path absolute = Paths.get("/home", "cay");
        Path relative = Paths.get("myconfig", "conf", "user.properties");
        System.out.println(absolute.getFileName().toString());
        //cay
        System.out.println(relative.getFileName().toString());
        //user.properties
    }

    public static void t8() {
        Path absolute = Paths.get("/home", "cay");
        Path relative = Paths.get("myconfig", "conf", "user.properties");
        Iterator iterator1 = absolute.iterator();
        while (iterator1.hasNext()) {
            System.out.print(iterator1.next() + " ");
        }
        // home cay
        System.out.println();
        Iterator iterator2 = relative.iterator();
        while (iterator2.hasNext()) {
            System.out.print(iterator2.next() + " ");
        }
        // myconfig conf user.properties
    }

    public static void t9() {
        Path path = FileSystems.getDefault().getPath("/test/jdk7", "test.txt");
        System.out.println(path.getNameCount());
        //3
        File file = new File("/test/jdk7/test.txt");
        Path pathOther = file.toPath();
        System.out.println(path.compareTo(pathOther));
        //0
        Path path1 = Paths.get("home", "1");
        Path path2 = Paths.get("home", "2");
        Path path3 = Paths.get("home", "3");
        System.out.println(path2.compareTo(path1));//1
        System.out.println(path2.compareTo(path3));//-1
    }


    public static void main(String[] args) throws Exception {
//        t3();
//        t2();
//        t4();
//        t5();
//        t6();
//        t7();
//        t8();
//        t9();
    }

}
