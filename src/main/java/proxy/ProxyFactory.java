package proxy;

import protocol.Invocation;
import protocol.socket.SocketClient;
import register.RemoteRegister;
import register.URL;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Random;

public class ProxyFactory {

    public static <T> T getProxy(final Class classT){
        return (T) Proxy.newProxyInstance(classT.getClassLoader(), new Class[]{classT}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                SocketClient httpClient = new SocketClient();
                List<URL> urls = RemoteRegister.get(classT.getName());
                //负载直接用随机
                URL url = urls.get(new Random().nextInt(urls.size()));
                Invocation invocation = Invocation.builder().interfaceName(classT.getName()).methodName(method.getName())
                        .paramTypes(method.getParameterTypes()).params(args).build();
                return httpClient.send(url.getHostname(), url.getPort(),invocation);
            }
        });
    }
}
