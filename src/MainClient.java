import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MainClient {

    public static void main(String[] args) throws Exception {

        DatagramSocket ds = new DatagramSocket();

        String messaggio = "bella a tutti ragzzi";
        byte[] buffer = messaggio.getBytes();

        InetAddress serverAddress = InetAddress.getByName("tifuxhost");

        DatagramPacket packet =
                new DatagramPacket(buffer, buffer.length, serverAddress, 3989);

        ds.send(packet);

        byte[] rispostaBuffer = new byte[256];
        DatagramPacket rispostaPacket =
                new DatagramPacket(rispostaBuffer, rispostaBuffer.length);

        ds.receive(rispostaPacket);

        String risposta = new String(rispostaPacket.getData(), 0, rispostaPacket.getLength());
        System.out.println("risposta dal server: " + risposta);

        ds.close();
    }
}