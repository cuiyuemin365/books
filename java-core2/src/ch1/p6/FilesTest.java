package ch1.p6;

import java.nio.file.*;

public class FilesTest {

    public static void t1() throws Exception {
        Path path1 = Paths.get("temp1.txt");
        Files.deleteIfExists(path1);
        Files.createFile(path1);
        String content = "Java ";
        Files.write(path1, content.getBytes(), StandardOpenOption.APPEND);
        Files.write(path1, "\n".getBytes(), StandardOpenOption.APPEND);
        Files.write(path1, content.getBytes(), StandardOpenOption.APPEND);
    }

    public static void t2() throws Exception {
        for (StandardOpenOption openOption : StandardOpenOption.values()) {
            System.out.println(openOption.name());
        }
    }

    public static void t3() throws Exception {
        Path path1 = Paths.get("temp2.txt");
        Path path2 = Paths.get("temp3.txt");
        System.out.println(Files.copy(path1,path2));
    }


    public static void main(String[] args) throws Exception {
//        t1();
//        t2();
        t3();
    }
}
