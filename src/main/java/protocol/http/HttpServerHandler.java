package protocol.http;

import org.apache.commons.io.IOUtils;
import protocol.Invocation;
import provider.localRegister.LocalRegister;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;

public class HttpServerHandler {

    public void handler(HttpServletRequest req, HttpServletResponse resp){
        try {
            ServletInputStream inputStream = req.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Invocation invocation = (Invocation)objectInputStream.readObject();
            String interfaceName = invocation.getInterfaceName();
            Class aClass = LocalRegister.getClass(interfaceName);
            Method method = aClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            String result = (String) method.invoke(aClass.newInstance(), invocation.getParams());
            IOUtils.write(result,resp.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
