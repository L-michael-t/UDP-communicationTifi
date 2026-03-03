import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MainServer {

    public static void main(String[] args) throws Exception {

        DatagramSocket ds = new DatagramSocket(3989);
        System.out.println("il server attende");

        byte[] buffer = new byte[256];

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        ds.receive(packet);

        String messaggio = new String(packet.getData(), 0, packet.getLength());
        System.out.println("ricevuto: " + messaggio);

        String risposta = "ho ricevuto: " + messaggio;
        byte[] rispostaBytes = risposta.getBytes();

        InetAddress clientAddress = packet.getAddress();
        int clientPort = packet.getPort();

        DatagramPacket rispostaPacket =
                new DatagramPacket(rispostaBytes, rispostaBytes.length, clientAddress, clientPort);

        ds.send(rispostaPacket);

        ds.close();
    }
}