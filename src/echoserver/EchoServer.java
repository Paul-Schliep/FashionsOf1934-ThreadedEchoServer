import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class EchoServer {
	public static void main(String [] args){
		try{
			ServerSocket sock = new ServerSocket(6013);

			while(true){
				Socket client = sock.accept();
				InputStream input = client.getInputStream();
				OutputStream output = client.getOutputStream();

				output.write(input.read());
				client.close();
			}
			
		} catch(IOException ioe) {
			System.err.println(ioe);
		}
	}
}