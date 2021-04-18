package TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private DataInputStream dataInputStream = null;
    private int port;

    public Server(int i) {
        port = 5000 + i;
    }

    public void run(){
        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("listening to port:5000");
            Socket clientSocket = serverSocket.accept();
            System.out.println(clientSocket+" connected.");
            dataInputStream = new DataInputStream(clientSocket.getInputStream());

            receiveFile("C:\\Users\\Willem\\IdeaProjects\\6-DistributedSystems\\lab1\\src\\received"+ Thread.currentThread().getId()+".txt");

            dataInputStream.close();
            clientSocket.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void receiveFile(String fileName) throws Exception{
        int bytes;
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        long size = this.dataInputStream.readLong();     // read file size
        byte[] buffer = new byte[4*1024];
        while (size > 0 && (bytes = this.dataInputStream.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1) {
            fileOutputStream.write(buffer,0,bytes);
            size -= bytes;      // read upto file size
        }
        fileOutputStream.close();
    }
}
