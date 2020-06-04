import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    //InetAddress bruges til at finde IP adressen
    InetAddress ip = InetAddress.getLocalHost();

    public static void main(String[] args) throws IOException {
        new Client();
    }

    public Client() throws IOException {

        Socket socket = new Socket("localhost", 3500);

        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());

        System.out.println("com-0 " + ip.getHostAddress());
        String firstmsg = "com-0 " + ip.getHostAddress();
        outputStream.writeUTF(firstmsg);

        // Motager input fra server.
        // textIn's værdi bliver sat til væridien DataInputStream opfanger.
        String textIn = inputStream.readUTF();
        if (textIn.startsWith("com-0 accept ")){

            String accptmsg = "com-0 accept " + ip;
            outputStream.writeUTF(accptmsg);

        }
        Chatmessages();

    }

    public void Chatmessages() throws IOException {

        while (true) {
            String message = "";
            Scanner scn = new Scanner(System.in);
            System.out.println("Enter message: ");

            message = scn.nextLine();
            outputStream.writeUTF(message);

            String resp = inputStream.readUTF();
            System.out.println("S: " + resp);
        }
    }

}
