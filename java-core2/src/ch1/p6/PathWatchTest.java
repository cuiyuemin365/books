package ch1.p6;

import java.nio.file.*;
import java.util.List;

public class PathWatchTest {

    public static void main(String[] args) throws Exception {

        final String pathName = "/data/project/learn/books/java-core2/resource";
        // 文件监控线程
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    //观察者
                    WatchService watchService = FileSystems.getDefault().newWatchService();
                    Path path = Paths.get(pathName);
                    // 注册监听器：创建事件，删除事件
                    path.register(watchService,
                            StandardWatchEventKinds.ENTRY_CREATE,
                            StandardWatchEventKinds.ENTRY_DELETE);
                    while (true) {
                        System.out.println("----------------");
                        // 阻塞方式，消费事件
                        List<WatchEvent<?>> watchEvents = watchService.take().pollEvents();
                        for (WatchEvent<?> watchEvent : watchEvents) {
                            System.out.printf("[%s]文件发生了[%s]事件。%n",
                                    watchEvent.context(), watchEvent.kind());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "test");
        thread.start();
        Thread.sleep(5000);

        Path path2 = Paths.get(pathName, "test.xml");
        System.out.println("-------创建文件---------");
        Files.createFile(path2);
        System.out.println("-------完成创建文件---------");
        Thread.sleep(5000);
        System.out.println("-------删除文件---------");
        Files.delete(path2);
        System.out.println("-------完成删除文件---------");
        Thread.sleep(5000);
        thread.join();
        System.out.println("-------执行完成---------");
    }

}
