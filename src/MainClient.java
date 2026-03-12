import java.io.IOException;
import java.net.*;

public class MainClient {

    public static void main(String[] args) {

        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        try {
            ds.setSoTimeout(5000);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        String messaggio = "ciao server";
        byte[] buffer = messaggio.getBytes();

        InetAddress serverAddress = null;
        try {
            serverAddress = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        DatagramPacket packet =
                new DatagramPacket(buffer, buffer.length, serverAddress, 3989);

        try {
            ds.send(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        byte[] rispostaBuffer = new byte[256];
        DatagramPacket rispostaPacket =
                new DatagramPacket(rispostaBuffer, rispostaBuffer.length);

        try {
            ds.receive(rispostaPacket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String risposta = new String(
                rispostaPacket.getData(),
                0,
                rispostaPacket.getLength()
        );

        System.out.println("Risposta unicast: " + risposta);

        ds.close();

// versione multicast

        int multicastPort = 4446;

        MulticastSocket ms = null;
        try {
            ms = new MulticastSocket(multicastPort);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        InetAddress group = null;
        try {
            group = InetAddress.getByName("230.0.0.1");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        try {
            ms.joinGroup(group);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        byte[] multicastBuffer = new byte[256];

        DatagramPacket multicastPacket =
                new DatagramPacket(multicastBuffer, multicastBuffer.length);

        System.out.println("In attesa messaggio multicast");

        try {
            ms.receive(multicastPacket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String multicastMsg = new String(
                multicastPacket.getData(),
                0,
                multicastPacket.getLength()
        );

        System.out.println("Ricevuto multicast: " + multicastMsg);

        try {
            ms.leaveGroup(group);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ms.close();
    }
}