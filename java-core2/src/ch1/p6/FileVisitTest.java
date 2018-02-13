package ch1.p6;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileVisitTest {

    public static int get(int x) {
        return (int) (1D / 18 * Math.pow(x, (double) 3D));
    }

    public static void main(String[] args) throws IOException {
        // 使用FileVisitor对目录进行遍历
        Files.walkFileTree(Paths.get("/data/project/learn/books/java-core2/resource"), new SimpleFileVisitor<Path>() {
            // 在访问子目录前触发该方法
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                String format = "%" + get(dir.getNameCount()) + "d,正在访问目录:%s";
                System.out.printf(format, 1, dir.toString() + "\n");
                return FileVisitResult.CONTINUE;
            }

            // 在访问文件时触发该方法
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                String format = "%" + get(file.getNameCount()) + "d,正在访问文件:%s";
                System.out.printf(format, 1, file.toString() + "\n");
                return FileVisitResult.CONTINUE;
            }

            // 在访问失败时触发该方法
            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return super.visitFileFailed(file, exc);
            }

            // 在访问目录之后触发该方法
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                String format = "%" + get(dir.getNameCount()) + "d,完成访问目录:%s";
                System.out.printf(format, 1, dir.toString() + "\n");
                return super.postVisitDirectory(dir, exc);
            }
        });
    }
}
