import java.net.*;

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

        System.out.println("Risposta unicast inviata");

   // versione multicast

        MulticastSocket ms = new MulticastSocket();

        InetAddress group = InetAddress.getByName("230.0.0.1");

        int multicastPort = 4446;

        String multicastMsg = "Messaggio multicast dal server";

        byte[] multicastBytes = multicastMsg.getBytes();

        DatagramPacket multicastPacket =
                new DatagramPacket(multicastBytes, multicastBytes.length, group, multicastPort);

        ms.send(multicastPacket);

        System.out.println("Messaggio multicast inviato");

        ms.close();

        ds.close();
    }
}