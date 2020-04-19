package protocol.socket;

import org.apache.commons.io.IOUtils;
import protocol.http.Invocation;
import provider.localRegister.LocalRegister;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class SocketServerHandler {

    public void handler(Socket socket){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    try {
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        try {
                            Invocation invocation = (Invocation)input.readObject();
                            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                            try {
                                Class implClass = LocalRegister.getClass(invocation.getInterfaceName());
                                Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
                                String result = (String) method.invoke(implClass.newInstance(), invocation.getParams());
                                output.writeObject(result);
                            } catch (Throwable t) {
                                output.writeObject(t);
                            } finally {
                                output.close();
                            }
                        } finally {
                            input.close();
                        }
                    } finally {
                        socket.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
