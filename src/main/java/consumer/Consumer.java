package consumer;

import provider.service.HelloService;
import proxy.ProxyFactory;

public class Consumer {

    public static void main(String[] args) {
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);

        System.out.println(helloService.sayHello("123"));

    }
}
