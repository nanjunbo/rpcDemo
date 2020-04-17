package provider.impl;

import provider.service.HelloService;

public class HelloServiceImpl implements HelloService {
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
