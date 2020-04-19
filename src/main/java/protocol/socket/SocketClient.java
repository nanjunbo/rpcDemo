package protocol.socket;

import protocol.Invocation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClient {

    public String send(String hostName, Integer port, Invocation invocation) throws IOException {
        Socket socket = new Socket(hostName, port);
        try {
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            try {
                output.writeObject(invocation);
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                String result = (String) input.readObject();
                return result;
            } finally {
                output.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            socket.close();
        }
        return null;
    }
}
