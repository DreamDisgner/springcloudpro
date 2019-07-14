package com.bawie.netty;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NettyClient {
    public static void main(String[] args) {
        //创建服务对象
        ClientBootstrap clientBootstrap = new ClientBootstrap();
        //设置线程池监听
        ExecutorService boos = Executors.newCachedThreadPool();
        ExecutorService wook = Executors.newCachedThreadPool();
        //将线程池放入工程
        clientBootstrap.setFactory(new NioClientSocketChannelFactory(boos,wook));
        //设置管道工程
        clientBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                //传输数据的时候为string类型
                pipeline.addLast("decoder",new StringDecoder());
                pipeline.addLast("encoder",new StringEncoder());
                //设置事件监听类
                pipeline.addLast("clientHanlder",new ClientHanlder());
                return pipeline;
            }
        });
        //绑定端口号
        ChannelFuture connect = clientBootstrap.connect(new InetSocketAddress("127.0.0.1", 8080));
        System.out.println("客户端已启动。。。");
        System.out.println("我在做改动11111。");
        Channel channel = connect.getChannel();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            System.out.println("please write something...");
            channel.write(scanner.next());
    }
    }
}
class ClientHanlder extends SimpleChannelHandler{
    //接收数据
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        super.messageReceived(ctx, e);
        System.out.println("服务端反馈数据来了"+e.getMessage());

    }
    //接收端出现异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        super.exceptionCaught(ctx, e);
        System.out.println("exceptionCaught");
    }
    //通道关闭触发
    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelClosed(ctx, e);
        System.out.println("channelClosed");
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelDisconnected(ctx, e);
        System.out.println("channelDisconnected");
    }
}