package com.tingyun.controller.thriftdemo2;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;

/**
 * Created by Administrator on 2016/5/9.
 */
public class ServerMain extends Thread {
    private int port;

    public ServerMain(int port) {
        this.port = port;
    }
    public void run() {
        startServer(port);
    }

    public void startServer(int port) {
        TServer server = null;

        try {
            if (port == 7912) {
                //非阻塞 使用非阻塞式IO，服务端和客户端需要指定 TFramedTransport 数据传输的方式。
                System.out.println("TNonblockingServerSocket start .7912.");
                TProcessor tprocessor = new ThriftServer.Processor<ThriftServer.Iface>(
                        new ThriftServerImpl());
                TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(port);
                TNonblockingServer.Args tnbArgs = new TNonblockingServer.Args(tnbSocketTransport);
                tnbArgs.processor(tprocessor);
                tnbArgs.transportFactory(new TFramedTransport.Factory());
                tnbArgs.protocolFactory(new TCompactProtocol.Factory());
                // 使用非阻塞式IO，服务端和客户端需要指定TFramedTransport数据传输的方式
                server = new TNonblockingServer(tnbArgs);
            } else {
                //阻塞 线程池服务模型，使用标准的阻塞式IO，预先创建一组线程处理请求。
                System.out.println("TProcessor start .7911.");
                TProcessor tprocessor = new ThriftServer.Processor<ThriftServer.Iface>(
                        new ThriftServerImpl());
                TServerSocket serverTransport = new TServerSocket(port);
                TThreadPoolServer.Args ttpsArgs = new TThreadPoolServer.Args(
                        serverTransport);
                ttpsArgs.processor(tprocessor);
                ttpsArgs.protocolFactory(new TBinaryProtocol.Factory());
                // 线程池服务模型，使用标准的阻塞式IO，预先创建一组线程处理请求。
                server = new TThreadPoolServer(ttpsArgs);
            }
            server.serve();
        } catch (Exception e) {
            System.out.println("Server start error!!!");
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        ServerMain server1=new ServerMain(7911);
        ServerMain server2=new ServerMain(7912);
        server1.start();
        server2.start();
    }
}
