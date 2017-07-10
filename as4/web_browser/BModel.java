import java.io.*;
import java.net.*;
import java.util.IllegalFormatException;

public class BModel {
	
	public static String host;
	public static String filePath;
	
	public static void extractHost(String input) {
		host = "";
		for (int i = 7; i < input.length(); i++) { //assumes users will always enter "http://" 
			if (input.charAt(i) != '/') {          //browser cannot access encrypted "https://" sites.
				host += input.charAt(i); 
			}
			else {
				break;
			}
		}
	}
	
	public static void extractFilePath(String input, String host) {
		int filePathIndex = 7 + host.length();
		if (input.length() != filePathIndex) {
			filePath = input.substring(filePathIndex);
		}
		else {
			filePath = "";
		}
	}
	
	public static void loadWebPage(String host, String filePath) { //FIXME - use InetAddress Object?
		try {
			Socket socket = new Socket(host, 80);
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			BufferedReader in =
				new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
			out.print("GET /" + filePath + " HTTP/1.1\r\n");
			out.print("Host: " + host + "\r\n");
			out.println("\r\n");
			out.flush();
			
			String line;
			String webPageText = "";
			while ((line = in.readLine()) != null) {
				webPageText += (line + "\n");
			}
			BFrame.panel2.createWebPage(webPageText);
		}
		catch (UnknownHostException ex) {
			System.out.println("ERROR: Host URL not recognized.");
		}
		catch (IllegalFormatException ex) {
			System.out.println("ERROR: HTTP response formatted incorrectly.");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}