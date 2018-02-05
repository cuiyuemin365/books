package ex03.pyrmont.connector.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * HTTP连接器
 */
public class HttpConnector implements Runnable {

    boolean stopped;
    private String scheme = "http";

    /**
     * 返回协议名称
     *
     * @return
     */
    public String getScheme() {
        return scheme;
    }

    public void run() {
        // 创建服务器套接字
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!stopped) {
            // Accept the next incoming connection from the server socket
            // 接受来自服务器套接字的下一个连接
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                socket.getInetAddress();
            } catch (Exception e) {
                continue;
            }
            // Hand this socket off to an HttpProcessor
            // 将这个套接字交给一个HTTP处理器
            HttpProcessor processor = new HttpProcessor(this);
            processor.process(socket);
        }
    }

    /**
     * 启动
     * 在单独线程中执行
     */
    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }
}