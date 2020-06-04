import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

    private ServerSocket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    //InetAddress bruges til at finde IP adressen
    InetAddress ip = InetAddress.getLocalHost();

    public static void main(String[] args) throws IOException {
        new Server();

    }

    public Server() throws IOException {

        try {
            System.out.println("Server running....");

            // Ny serversocket der lytter efter nye forbindelser på port 3500.
            socket = new ServerSocket(3500);

            while (true) {

                // Når der bliver fundet en, tager den dens socket.
                Socket sock = socket.accept();

                //DataInputStream & DataOutputStream oprettes og sættes til at lytte på en socket.
                inputStream = new DataInputStream(sock.getInputStream());
                outputStream = new DataOutputStream(sock.getOutputStream());

                // indputStream motager input fra klienter.
                // textIn's værdi bliver sat til væridien DataInputStream opfanger.
                String textIn = inputStream.readUTF();
                System.out.println(textIn);

                if (textIn.startsWith("com-0")) {

                    String accptmsg = "com-0 accept " + ip;
                    //OutputSteam sender beskeden til clienten
                    outputStream.writeUTF(accptmsg);

                    String accptClient = inputStream.readUTF();
                    if (accptClient.startsWith("com-0 accept ")){

                        System.out.println("Handshake successful");
                        send_msg();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*public void first_msg(){

        try {

            String msg1 = inputStream.readUTF();
            if (msg1.startsWith("msg-0")){

                send_msg(msg1);
                msg_funktion();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    public void send_msg() throws IOException {

        while (true) {

            String msgClient = inputStream.readUTF();
            System.out.println("C: " + msgClient);

            String auto_res = "res: I am server";
            outputStream.writeUTF(auto_res);
            outputStream.flush();

        }
    }

    public void msg_funktion(){


    }

}
