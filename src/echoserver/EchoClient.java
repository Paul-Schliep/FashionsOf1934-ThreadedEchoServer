package echoserver;

import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        try {
        	String host = "localhost";
        	if (args.length != 0) {
        		host = args[0];
        	}
            Socket socket = new Socket(host, 6013);
            // You can change 127.0.0.1 to a machine name if you want to try this across
            // the network to another machine.
            // Socket socket = new Socket("avenger.morris.umn.edu", 6013);

            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            
            
            int inByte;
            while ((inByte = System.in.read()) != -1) {
                out.write(inByte);
                System.out.write(in.read());
                System.out.flush();
            }
            
            socket.close();
        } catch (IOException ioe) {
            System.out.println("We caught an exception");
            System.err.println(ioe);
        }
    }
}