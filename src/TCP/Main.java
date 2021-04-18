package TCP;

public class Main {

    public static void main(String[] args) {
        int n = 2; // Number of threads
        for (int i = 0; i < n; i++) {
            Server object = new Server(i);
            object.start();
        }
    }
}
