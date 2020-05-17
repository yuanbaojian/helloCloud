package com.ybj.crawler.Learn.Web.Udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @Author Client
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class Client {

  /**  无需创建连接
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket();
        String s = "发送的信息";
        byte[] bytes = s.getBytes();
        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 10000;
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, address, port );
        ds.send(dp);
        ds.close();
    }
}
