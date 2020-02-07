package Server;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.net.httpserver.HttpServer;

public class Server {
    public static final int HTTP_PORT = 8000;
    public static final int IP = 0; // localhost

    private static ServerSocket server = null;
    private static Socket socket = null;
    private static DataInputStream in = null;

    public static void main(String[] args) throws Exception {
        startHttpServer();
        startPostServer();
    }





    public static void startHttpServer() throws Exception {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(HTTP_PORT), IP);
        httpServer.createContext("/resultat", new HttpServerHandler());                     //detta blir locahost:8000/resultat
        httpServer.setExecutor(null);                                                          // creates a default executor
        httpServer.start();
    }


    public static void startPostServer() {
        try {
            server = new ServerSocket(8001);

            System.out.println("Starting Server");
            System.out.println("Waiting for Client");

            socket = server.accept();
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String line = "";
            String html = "";
            HTMLGenerator generator = new HTMLGenerator();


            // Här lägger vi "stop"-kommandon
            while(!line.equals("Over")) {
                try {
                    line = in.readUTF();
                    BufferedWriter writer = new BufferedWriter(new FileWriter("./ServerResult.txt", false));

                    // System.out.println(line); //Debug

                    html = generator.parseToHTML(line);
                    writer.write(html);
                    writer.newLine();
                    writer.close();

                } catch(IOException e) {
                    //System.out.println(e);
                }
            }


        } catch(IOException e) {
            //System.out.println(e);
        }


    }

}