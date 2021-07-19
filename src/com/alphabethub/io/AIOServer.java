package com.alphabethub.io;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;


/**
 * 文章：https://time.geekbang.org/column/article/8369
 */
public class AIOServer extends Thread {
    @Override
    public void run() {
        try {
            InetSocketAddress socketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 8888);
            AsynchronousServerSocketChannel serverSock = AsynchronousServerSocketChannel.open().bind(socketAddress);
            serverSock.accept(serverSock, new CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel>() {
                @Override
                public void completed(AsynchronousSocketChannel sockChannel, AsynchronousServerSocketChannel serverSock) {
                    serverSock.accept(serverSock, this);
                    //另一个write(sock,CompletionHandler{})
                    try {
                        sayHelloWorld(sockChannel, Charset.defaultCharset().encode("Hello World"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void failed(Throwable exc, AsynchronousServerSocketChannel attachment) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sayHelloWorld(AsynchronousSocketChannel server, ByteBuffer byteBuffer) throws IOException {

    }
}
