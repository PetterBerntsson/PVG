package Server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class HttpServerHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        String response = readFile();
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }


    public String readFile() throws FileNotFoundException {

        File file = new File("./ServerResult.txt");
        Scanner sc = new Scanner(file);

        String result = "";
        while(sc.hasNext()) {
            result = result + sc.nextLine();
        }
        sc.close();
        return result;
    }
}