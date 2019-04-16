package ClientSide;




import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import com.github.sarxos.webcam.Webcam;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Client extends Thread{

    private final DataInputStream in;
    private final DataOutputStream out;
    private int port;
    private String host;
    String name;
    Socket socket;
    Webcam webcam;
    BufferedImage currentimg;
    ByteArrayOutputStream imgoutputstream;
    AudioInputStream audio;
    byte[] imgbytes;
    Client(String name, String host, int port) throws IOException, InterruptedException {
        this.port = port;
        this.host = host;
        socket = new Socket(host,port);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        this.name = name;

        webcam = Webcam.getDefault();
        imgoutputstream = new ByteArrayOutputStream();
        webcam.open();

        out.writeUTF(name);



    }

    @Override
    public void run() {

        /*
        while(true) {
            try {
                    imgoutputstream.reset();
                    currentimg = webcam.getImage();
                    ImageIO.write(currentimg, "jpg", imgoutputstream);

                    imgbytes = imgoutputstream.toByteArray();
                    out.writeInt(imgbytes.length);
                    out.write(imgbytes);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        */

        while (true){
            sendvidframe();
        }


    }


    public void sendvidframe(){
        try {
            imgoutputstream.reset();
            currentimg = webcam.getImage();
            ImageIO.write(currentimg, "jpg", imgoutputstream);

            imgbytes = imgoutputstream.toByteArray();

            out.writeInt(imgbytes.length);
            out.write(imgbytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void text(String name,String content){

    }

    public void send_sound(){

    }

}
