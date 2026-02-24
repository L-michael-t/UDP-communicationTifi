import java.net.DatagramSocket;
import java.net.SocketException;

public class MainClient {
        DatagramSocket ds;

        {
            try {
                ds = new DatagramSocket();
            } catch (SocketException e) {
                throw new RuntimeException(e);
            }
        }


}