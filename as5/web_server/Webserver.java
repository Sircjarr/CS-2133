import java.io.*;
import java.net.*;

public class Webserver {
	
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(8080); 
			Socket socket = ss.accept();
			Thread conn = new Thread(new ClientConnection(socket));
			conn.start();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}