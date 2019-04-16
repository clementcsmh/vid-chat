package ClientSide;


import java.io.IOException;

public class ClientMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(System.getProperty("java.library.path"));

        Client client = new Client("f","localhost",8888);
        client.run();

}
}
