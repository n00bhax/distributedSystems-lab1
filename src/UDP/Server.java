package UDP;

import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;


public class Server {

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(5005);
            System.out.println("listening to port: " + socket.getLocalPort());
            InetAddress IPAddress = InetAddress.getByName("localhost");

            byte[] receive = new byte[65535];
            DatagramPacket incomingPacket = new DatagramPacket(receive, receive.length, IPAddress, 5000);
            socket.receive(incomingPacket);

            byte[] trimmedIncomingPacket = Arrays.copyOfRange(incomingPacket.getData(), 0, incomingPacket.getLength());

            try (FileOutputStream fos = new FileOutputStream("C:\\Users\\Willem\\IdeaProjects\\6-DistributedSystems\\lab1\\src\\received.txt")) {
                fos.write(trimmedIncomingPacket);
            }

            System.out.println("File received from client");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


