import java.net.DatagramSocket;
import java.net.SocketException;

public class MainServer {
    DatagramSocket ds;

    {
        try {
            ds = new DatagramSocket(3989);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
}
