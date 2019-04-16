package ServerSide;


import com.github.sarxos.webcam.WebcamPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;


import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class ServerWorker extends Thread {
    private final OutputStream output;
    private final DataInputStream input;
    byte[] imgbytes;
    int length;
    ByteArrayInputStream imginput;
    BufferedImage img;
    JFrame frame;
    boolean init = false;
    final String clientName;

    public ServerWorker(Socket client) throws IOException {
        output = new DataOutputStream(client.getOutputStream());
        input = new DataInputStream(client.getInputStream());
        frame = new JFrame();

        clientName = input.readUTF();
    }

    @Override
    public void run() {



        while(true) {
            recieveVidFrame();
        }
    }

    public void recieveVidFrame(){
        try {

            length = input.readInt();
            imgbytes = new byte[length];
            input.read(imgbytes);
            imginput = new ByteArrayInputStream(imgbytes);
            img = ImageIO.read(imginput);
            if(init == false){
                init = true;

            }else {
                frame.getContentPane().remove(0);
            }
            frame.getContentPane().add(new JLabel(new ImageIcon(img)));
            frame.pack();
            frame.repaint();
            frame.setVisible(true);



            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}




