package echoserver;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import org.omg.CORBA.portable.InputStream;

public class EchoClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("serenity.morris.umn.edu", 6013);
            // You can change 127.0.0.1 to a machine name if you want to try this across
            // the network to another machine.
            // Socket socket = new Socket("avenger.morris.umn.edu", 6013);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            
            String string;
            while ((string = stdIn.readLine()) != null) {
                out.println(string);
                System.out.println(in.readLine());
            }
            
            //socket.close();
        } catch (IOException ioe) {
            System.out.println("We caught an exception");
            System.err.println(ioe);
        }
    }
}