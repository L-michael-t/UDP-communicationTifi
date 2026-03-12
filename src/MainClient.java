import java.net.*;

public class MainClient {

    public static void main(String[] args) throws Exception {

        DatagramSocket ds = new DatagramSocket();
        ds.setSoTimeout(5000);

        String messaggio = "ciao server";
        byte[] buffer = messaggio.getBytes();

        InetAddress serverAddress = InetAddress.getByName("localhost");

        DatagramPacket packet =
                new DatagramPacket(buffer, buffer.length, serverAddress, 3989);

        ds.send(packet);

        byte[] rispostaBuffer = new byte[256];
        DatagramPacket rispostaPacket =
                new DatagramPacket(rispostaBuffer, rispostaBuffer.length);

        try {

            ds.receive(rispostaPacket);

            String risposta = new String(
                    rispostaPacket.getData(),
                    0,
                    rispostaPacket.getLength()
            );

            System.out.println("Risposta unicast: " + risposta);

        } catch (SocketTimeoutException e) {
            System.out.println("Server non disponibile");
        }

        ds.close();

// versione multicast

        int multicastPort = 4446;

        MulticastSocket ms = new MulticastSocket(multicastPort);

        InetAddress group = InetAddress.getByName("230.0.0.1");

        ms.joinGroup(group);

        byte[] multicastBuffer = new byte[256];

        DatagramPacket multicastPacket =
                new DatagramPacket(multicastBuffer, multicastBuffer.length);

        System.out.println("In attesa messaggio multicast");

        ms.receive(multicastPacket);

        String multicastMsg = new String(
                multicastPacket.getData(),
                0,
                multicastPacket.getLength()
        );

        System.out.println("Ricevuto multicast: " + multicastMsg);

        ms.leaveGroup(group);

        ms.close();
    }
}