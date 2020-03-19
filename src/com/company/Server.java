package com.company;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class Server
{
    private static final String hui = "hui";


    public static void main(String args[]) throws Exception
    {
        DatagramChannel chan = DatagramChannel.open();
        chan.socket().bind( new InetSocketAddress( 7877 ) );
        chan.configureBlocking(false);

        Selector selector = Selector.open();
        chan.register(selector,SelectionKey.OP_READ);
        ByteBuffer buffer = ByteBuffer.allocate(4*1024);

        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {

                SelectionKey key = iter.next();

                if (key.isReadable()) {
                    buffer.clear();
                    buffer.put(new byte[4*1024]);
                    buffer.clear();
                    action(buffer, key);
                }
                iter.remove();
            }
        }

    }

    private static void action(ByteBuffer buffer, SelectionKey key) throws IOException, InterruptedException {

        DatagramChannel channel = (DatagramChannel)key.channel();



        buffer.clear();
        System.out.println("патключаус к нон блок аналу");
        SocketAddress from = channel.receive(buffer);
        System.out.println("внатуре успех");

        if (from != null) {
            buffer.flip();
            String val = new String(buffer.array());
            System.out.println(from);
            System.out.println(channel);
            System.out.println("Сервер получил по ебалу: "+val);

            channel.send(ByteBuffer.wrap(hui.getBytes()),from);
        }

        System.out.println( "отрубаюсь" );
        TimeUnit.SECONDS.sleep( 5 );



    }

}