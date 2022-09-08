package com.cienet.zheng.stock;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @description:
 * @author: zhengshan1
 * @create: 2021-12-22 14:02
 **/
public class SelectorMain {
    ServerSocketChannel serverSocketChannel = null;
    Selector selector = null;

    public static void main1(String[] args) throws Exception {
        SelectorMain selectorIo = new SelectorMain();
        selectorIo.start();
    }

    void init() throws Exception {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8090));
        serverSocketChannel.configureBlocking(false);
        selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    void start() throws Exception {
        init();
        System.out.println("服务已启动");
        while (true) {
            Set<SelectionKey> keys = selector.keys();
            //阻塞调用select，返回值为fd个数
            while (selector.select(500) > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    System.out.println("存在有数据变动的连接...");
                    SelectionKey next = iterator.next();
                    if (next.isAcceptable()) {
                        acceptHandle(next);
                    } else if (next.isReadable()) {
                        readHandle(next);
                    }
                    iterator.remove();
                }
            }
        }
    }

    void acceptHandle(SelectionKey selectionKey) throws Exception {
        ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
        SocketChannel client = channel.accept();
        client.configureBlocking(false);

        ByteBuffer buffer = ByteBuffer.allocate(8192);
        client.register(selector, SelectionKey.OP_READ, buffer);
        System.out.println("新客户端连接，端口：" + client.socket().getPort());
    }

    void readHandle(SelectionKey selectionKey) throws Exception {
        SocketChannel client = (SocketChannel) selectionKey.channel();
        ByteBuffer buffer = ByteBuffer.allocate(2048);
        while (client.read(buffer) > 0) {
            buffer.flip();
            byte[] arr = new byte[buffer.limit()];
            buffer.get(arr);
            String content = new String(arr);
            System.out.println(String.format("端口为:%s, 获取到内容:%s", client.socket().getPort(), content));
        }

        writeHandle(client, "返回值：" + System.currentTimeMillis());
    }

    void writeHandle(SocketChannel client, String response) throws Exception {
        byte[] arr = response.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
        byteBuffer.put(arr);
        byteBuffer.flip();
        client.write(byteBuffer);
        byteBuffer.clear();
    }
}
