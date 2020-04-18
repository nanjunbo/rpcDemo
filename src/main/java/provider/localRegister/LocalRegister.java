package provider.localRegister;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocalRegister {

    private static Map<String, Class> map = new ConcurrentHashMap<String, Class>();

    public static void register(String interfaceName, Class TClass){
        map.put(interfaceName, TClass);
    }

    public static Class getClass(String interfaceName){
        return map.get(interfaceName);
    }
}
