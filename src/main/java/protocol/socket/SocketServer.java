package protocol.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public void start(String hostName, Integer port) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        for(;;){
            try {
                final Socket socket = server.accept();
                new SocketServerHandler().handler(socket);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
