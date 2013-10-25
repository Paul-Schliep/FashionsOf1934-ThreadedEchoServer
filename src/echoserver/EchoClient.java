import java.net.*;
import java.io.*;

public class EchoClient {
	public static void main(String [] args){
		try{
			Socket socket = new Socket(args[0], 6013);
			OutputStream output = socket.getOutputStream();
			byte [] ch = new byte[1];
			while(System.in.read(ch) != 0){
				output.write(ch);

			}
				
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println(reader);
			socket.close();
		}catch(IOException ioe){
			System.out.println("IOException");
			System.err.println(ioe);
		}
	}
}