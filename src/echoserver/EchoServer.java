package echoserver;

import java.net.*;
import java.io.*;

public class EchoServer {
    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(6013);
            
            while (true) {
                Socket client = socket.accept();
                
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                
                String string;
                while((string = in.readLine()) != null) {
                    out.println(string);
                    //System.out.println(string/*in.readLine()*/);
                    if (string.equals("Fairwell")) {
                        break;
                    }
                }
                
                //client.close();
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}