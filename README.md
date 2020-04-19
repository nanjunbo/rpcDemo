搭建一个简易rpc框架的逻辑：
1.首先需要有一个服务提供者provider
2.其次服务提供者需要告诉消费者他提供的服务地址等信息，所以就需要一个注册中心来注册这个信息，让消费者到注册中心查找该服务的地址
3.然后服务提供者与消费者是不同进程间的服务调用，就需要有请求协议，比如http，netty，webservice等
4.然后消费者需要从注册中心获取需要的服务的地址列表，然后在消费端使用负载均衡算法，来选择其中一个地址进行请求
5.使用与服务提供者相同的协议进行服务调用
6.为了使调用服务方的代码逻辑与调用本地代码保持一直，调用服务端的代码肯定是调用的代理对象，里面包含选择服务地址，负载均衡，调用协议等逻辑

目前实现的协议有基于netty的dubbo协议，http协议，socket协议。

按着如上逻辑写代码，可以写出来一个简易的rpc框架。如果是更复杂的，可以多看看dubbo的源码，共有10个分层，扩展性也很好。

