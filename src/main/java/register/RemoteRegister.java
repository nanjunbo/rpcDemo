package register;

import org.apache.commons.collections4.CollectionUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RemoteRegister {

    private static Map<String, List<URL>> registerMap = new ConcurrentHashMap<String, List<URL>>();

    private static final String filePath = "/Users/nanjunbo/Desktop/register.txt";

    public static void register(String interfaceName, URL url) {
        List<URL> urls = registerMap.get(interfaceName);
        if (CollectionUtils.isEmpty(urls)){
            urls = new ArrayList<URL>();
            urls.add(url);
            registerMap.put(interfaceName,urls);
        }else {
            registerMap.get(interfaceName).add(url);
        }
        loadToFile();
    }

    public static List<URL> get(String interfaceName){
        registerMap = getFile();
        return registerMap.get(interfaceName);
    }

    private static void loadToFile(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(registerMap);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<URL>> getFile(){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fileInputStream);
            registerMap = (Map<String, List<URL>>) ois.readObject();
            return registerMap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
