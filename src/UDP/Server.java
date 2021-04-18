package UDP;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server extends Thread {
    private DataInputStream dataInputStream = null;
    private final int port;

    public Server(int i) {
        port = 5000 + i;
    }

    public void run(){
        try(DatagramSocket serverSocket = new DatagramSocket(port)){

            System.out.println("listening to port:" + port);
            receiveFile(serverSocket, "C:\\Users\\Willem\\IdeaProjects\\6-DistributedSystems\\lab1\\src\\received"+ Thread.currentThread().getId()+".txt");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void receiveFile(DatagramSocket socket, String fileName) throws Exception{
        byte[] buffer = new byte[4*1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);

        byte[] data = packet.getData();
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        FileEvent fileEvent = (FileEvent) is.readObject();

        createAndWriteFile(); // writing the file to hard disk
        InetAddress IPAddress = incomingPacket.getAddress();
        int port = incomingPacket.getPort();
        String reply = "Thank you for the message";
        byte[] replyBytea = reply.getBytes();
        DatagramPacket replyPacket =
                new DatagramPacket(replyBytea, replyBytea.length, IPAddress, port);
        socket.send(replyPacket);
        Thread.sleep(3000);


        int bytes;
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        long size = this.dataInputStream.readLong();     // read file size

        while (size > 0 && (bytes = this.dataInputStream.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1) {
            fileOutputStream.write(buffer,0,bytes);
            size -= bytes;      // read upto file size
        }
        fileOutputStream.close();
    }
}


