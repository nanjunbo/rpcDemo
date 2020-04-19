package provider.provider;

import protocol.dubbo.NettyServer;
import protocol.http.HttpServer;
import protocol.socket.SocketServer;
import provider.impl.HelloServiceImpl;
import provider.localRegister.LocalRegister;
import provider.service.HelloService;
import register.RemoteRegister;
import register.URL;

public class Provider {
    public static void main(String[] args) {

        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);
        RemoteRegister.register(HelloService.class.getName(), URL.builder().hostname("localhost").port(8080).build());

//        HttpServer httpServer = new HttpServer();
//        httpServer.start("localhost", 8080);
//        NettyServer nettyServer = new NettyServer();
//        nettyServer.start("localhost", 8080);
        SocketServer socketServer = new SocketServer();
        socketServer.start("localhost", 8080);
    }
}
