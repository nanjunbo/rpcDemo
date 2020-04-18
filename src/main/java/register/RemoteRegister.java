package register;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class RemoteRegister {

    private static Map<String, List<URL>> registerMap = new ConcurrentHashMap<String, List<URL>>();

    public static void register(String interfaceName, URL url) {
        List<URL> urls = registerMap.get(interfaceName);
        if (CollectionUtils.isEmpty(urls)){
            urls = new ArrayList<URL>();
            urls.add(url);
        }else {
            registerMap.get(interfaceName).add(url);
        }
    }

    public static List<URL> get(String interfaceName){
        return registerMap.get(interfaceName);
    }
}
