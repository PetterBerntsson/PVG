package Client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;


    public Client(String address, int port) {

        try {
            socket = new Socket(address, port);
            System.out.println("Connected");

            input = new DataInputStream(System.in);

            out = new DataOutputStream(socket.getOutputStream());
        } catch(UnknownHostException e) {
            System.out.println(e);
        } catch(IOException e) {
            System.out.println(e);
        }

        String line = "";
        try {
            Scanner sc = new Scanner(new File("./ClientResult.txt"));
            while(sc.hasNext()) {
                line = line + "\n" + sc.nextLine();
            }

            out.writeUTF(line); // Här skrivs datan till Server-Socket
            out.writeUTF("Over");

        } catch(FileNotFoundException e) {
            System.out.println(e);
        } catch(IOException e) {
            System.out.println(e);
        }


        //Vi kan använda stop-kommandon på det viset sen
        // Här kan man läsa multi-inputs



        try {
            input.close();
            out.close();
            socket.close();
        } catch(IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args0) {
        Client client = new Client("localhost", 8001);
    }
}
