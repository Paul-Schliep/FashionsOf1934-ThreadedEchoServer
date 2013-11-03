package echoserver;

import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
            String host = "localhost";
            if (args.length != 0) {
                host = args[0];
            }
            Socket socket = new Socket(host, 6013);
            Thread threadServer = new Thread(new serverWriter(socket));
            threadServer.start();
            InputStream in = socket.getInputStream();

            int inByte;
            while ((inByte = System.in.read()) != -1) {
                System.out.write(in.read());

            }
            System.out.flush();
            socket.shutdownOutput();
            socket.close();
    }
    private static class serverWriter extends Thread {
        Socket socket;

        public serverWriter(Socket socket) {
            this.socket = socket;
        }

        public void run(){

            try {
                OutputStream out = socket.getOutputStream();
                
                int inByte;
                while((inByte = System.in.read()) != -1) {
                    out.write(inByte);
                }
                out.flush();
                socket.shutdownOutput();
               // client.close();

            } catch (IOException ioe) {
                System.err.println(ioe);
            }

            //Finally necessary?
            finally {
                try {
                    socket.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}