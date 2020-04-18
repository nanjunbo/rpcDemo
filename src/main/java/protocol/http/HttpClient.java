package protocol.http;


import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpClient {

    public String send(String hostName, Integer port, Invocation invocation){
        ObjectOutputStream oss = null;
        try {
            URL url = new URL("http",hostName,port,"/");

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);

            OutputStream outputStream = urlConnection.getOutputStream();
            oss = new ObjectOutputStream(outputStream);
            oss.writeObject(invocation);
            oss.flush();
            oss.close();

            InputStream inputStream = urlConnection.getInputStream();

            String result = IOUtils.toString(inputStream);

            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
        return null;
    }
}
