package com.ybj.crawler.Learn.Web.Udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @Author Server
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class Server {

    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(10000);
        byte[] bytes= new byte[1024];
        DatagramPacket dp = new DatagramPacket(bytes,bytes.length );
        ds.receive(dp);
        int length = dp.getLength();
        System.out.println(new String(bytes, 0, length));
    }
}
