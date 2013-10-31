package echoserver;

import java.net.*;
import java.io.*;

public class EchoServer {
    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(6013);
            
            while (true) {
                Socket client = socket.accept();
                
                OutputStream out = client.getOutputStream();
                InputStream in = client.getInputStream();
                
                int inByte;
                while((inByte = in.read()) != -1) {
                    out.write(inByte);
                }
                
                client.close();
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}