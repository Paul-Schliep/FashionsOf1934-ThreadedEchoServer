package echoserver;

import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

public class EchoServer {
    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        ServerSocket server = new ServerSocket(6013);
        ExecutorService threadPool = Executors.newCachedThreadPool();

        while(true) {
            Socket client = server.accept();
            threadPool.submit(new clientWriter(client));
        }

    }

    private static class clientWriter extends Thread {
        Socket client;

        public clientWriter(Socket client) {
            this.client = client;
        }

        public void run(){
            try {
                OutputStream out = client.getOutputStream();
                InputStream in = client.getInputStream();

                int inByte;
                while((inByte = in.read()) != -1) {
                    out.write(inByte);
                }
                out.flush();
                client.shutdownOutput();
               // client.close();

            } catch (IOException ioe) {
                System.err.println(ioe);
            }

            //Finally necessary?
            finally {
                try {
                    client.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}