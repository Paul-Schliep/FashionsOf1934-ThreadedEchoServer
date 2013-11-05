package echoserver;

/**
 * Summary of our results:
 * After testing a text file of 53306 characters, and rerunning it 10 times, it was extremely fast.  Seemed slightly faster than what our regular would do.
 * After doing the same thing as the last test, and rerunning it 1000 times, bad things happen.  We ran out of memory, as expected, and errors happened.
 * When we ran it with a MUCH larger file 10 times, it did take a few more seconds longer, but considerably more so without the threading.  
 * 
 * We noticed it was pretty easy to tell if we passed in a larger file, as well as when we ran it more times the server starts to scale up with more runs.
 * So, as we run it more times, it takes considerably more time to run as the server begins to throttle and the time it takes begins to scale up.
 */


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
//                client.close();

            } catch (IOException ioe) {
                System.err.println(ioe);
            }

//            Finally necessary?
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