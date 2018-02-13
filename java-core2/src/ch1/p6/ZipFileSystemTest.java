package ch1.p6;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class ZipFileSystemTest {

    public static void main(String[] args) throws Exception {
        FileSystem fs = FileSystems.newFileSystem(
                Paths.get(
                        "/data/project/learn/books/java-core2/resource/this.zip"), null);

        Files.walkFileTree(fs.getPath("/"),
                new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        System.out.println(file.toString());
                        return FileVisitResult.CONTINUE;
                    }
                });
    }

}
