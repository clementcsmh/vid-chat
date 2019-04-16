package ServerSide;


import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {

    private final int port;
    private ArrayList<ServerWorker> clients = new ArrayList<ServerWorker>();




    private ArrayList<String> onlineClients;

    Server(int port) throws IOException {
        this.port = port;
        ServerSocket socket = new ServerSocket(port);
        InetAddress address = InetAddress.getLocalHost();

        onlineClients = new ArrayList<>();

        System.out.println("New Server Succesfully at "+address.getHostAddress() + " at port " + port );
        System.out.println("Ready for connections");
            while(true){
            Socket client = socket.accept();
            System.out.println("Connected");
            ServerWorker worker = new ServerWorker(client);
            clients.add(worker);
            worker.run();

        }
    }
    public ArrayList<ServerWorker> getClients() {
        return clients;
    }


    public ArrayList<String> getOnlineClients() {
        return onlineClients;
    }
}
