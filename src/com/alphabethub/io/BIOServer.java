package com.alphabethub.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 文章：https://time.geekbang.org/column/article/8369
 * 1.服务器端启动 ServerSocket，端口 0 表示自动绑定一个空闲端口。
 * 2.调用 accept 方法，阻塞等待客户端连接。
 * 3.利用 Socket 模拟了一个简单的客户端，只进行连接、读取、打印。
 * 4.当连接建立后，启动一个单独线程负责回复客户端请求。
 * 这样，一个简单的 Socket 服务器就被实现出来了。
 *
 *
 * 存在的问题：
 * 如果连接数并不是非常多，只有最多几百个连接的普通应用，这种模式往往可以工作的很好。
 * 但是，如果连接数量急剧上升，这种实现方式就无法很好地工作了，因为线程上下文切换开销会在高并发时变得很明显，这是同步阻塞方式的低扩展性劣势。
 * 这种时候就可以考虑NIO引入的多路复用机制，提供另外一种思路：com.alphabethub.io.NIOServer
 */
public class BIOServer extends Thread {
    private ServerSocket serverSocket;

    public int getPort() {
        return serverSocket.getLocalPort();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(0);
            //Java目前的线程实现是比较重量级的，引入线程池来避免启动和销毁线程的开销
            ExecutorService executor = Executors.newFixedThreadPool(8);
            while (true) {
                Socket socket = serverSocket.accept();
                RequestHandler requestHandler = new RequestHandler(socket);
                executor.execute(requestHandler);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BIOServer server = new BIOServer();
        server.start();
        try (Socket client = new Socket(InetAddress.getLocalHost(), server.getPort())) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            bufferedReader.lines().forEach(s -> System.out.println(s));
        }
    }
}

// 简化实现，不做读取，直接发送字符串
class RequestHandler extends Thread {
    private Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream())) {
            out.println("Hello World");
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
