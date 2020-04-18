package provider.provider;

import protocol.http.HttpServer;
import provider.localRegister.LocalRegister;
import provider.service.HelloService;
import register.RemoteRegister;
import register.URL;

public class Provider {
    public static void main(String[] args) {

        LocalRegister.register(HelloService.class.getName(), HelloService.class);
        RemoteRegister.register(HelloService.class.getName(), URL.builder().hostname("localhost").port(8080).build());

        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost", 8080);
    }
}
