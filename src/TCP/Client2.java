package TCP;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Client2 {
    private static DataOutputStream dataOutputStream = null;

    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost",5001)) {

            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            sendFile("C:\\Users\\Willem\\IdeaProjects\\6-DistributedSystems\\lab1\\src\\data.txt");
            dataOutputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void sendFile(String path) throws Exception{
        int bytes;
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);

        TimeUnit.SECONDS.sleep(2);

        // send file size
        dataOutputStream.writeLong(file.length());
        // break file into chunks
        byte[] buffer = new byte[4*1024];
        while ((bytes=fileInputStream.read(buffer))!=-1){
            dataOutputStream.write(buffer,0,bytes);
            dataOutputStream.flush();
        }
        fileInputStream.close();
    }
}