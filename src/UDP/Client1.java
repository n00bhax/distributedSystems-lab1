package UDP;

import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.file.Files;

public class Client1 {

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");

            File file = new File("C:\\Users\\Willem\\IdeaProjects\\6-DistributedSystems\\lab1\\src\\data.txt");

            byte[] buffer = Files.readAllBytes(file.toPath());
            DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, IPAddress, 5005);
            socket.send(sendPacket);
            System.out.println("File sent from client");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
