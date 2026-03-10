/*
javadoc
il client se viene avviato controlla se ce il server avviato/disponibile in caso negativo scrive server non disponibile
in caso avviato dopo il server comincia la comunicazione UDP
 */
import java.net.*;

public class MainClient {

    public static void main(String[] args) throws Exception {

        DatagramSocket ds = new DatagramSocket();
        ds.setSoTimeout(5000); // attende massimo 5 secondi

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

            System.out.println("Risposta: " + risposta);

        } catch (SocketTimeoutException e) {
            System.out.println("Server non disponibile.");
        }

        ds.close();
    }
}