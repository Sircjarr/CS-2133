import java.io.*;
import java.net.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

//Interface should be implemented by any class whose 
//instances are to be executed by a thread.
public class ClientConnection implements Runnable { 

	final static String OK = "HTTP/1.1 200 OK\r\n";
	final static String TYPE_TEXT = "Content-type: text/html\r\n\r\n";
	final static String TYPE_IMG = "Content-type: image/jpeg\r\n\r\n";
	
	final static String FILE_DNE = "HTTP/1.1 404 Not Found\r\n\r\n"; 
	final static String ERROR = "HTTP/1.1 500 Internal Server Error\r\n\r\n";
	
	private File file; 
	final String dir = "C:\\server\\";
	
	private static PrintStream out; // allows writing of bytes AND strings
	private static BufferedInputStream textIn;
	
	private Socket socket;
	
	public ClientConnection(Socket socket) {
		this.socket = socket;
	}
	
	//When an object implementing interface Runnable is used to create a thread, starting the 
	//thread causes the object's run method to be called in that separately executing thread.
	public void run() {
		try {
			// Print out stream from the client and obtain GET command
			BufferedReader in =
					new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line;
			String fileName;
			String command = "";
			while ((line = in.readLine()) != null) {
				if (line.contains("GET")) {
					command = line;
				}
				System.out.println(line);
			}
			out = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
			
			// Creation of file from stream data
			fileName = command.substring(4, command.indexOf("HTTP") - 1);
			if (fileName.equals("/")) {
				fileName = "index.html";
			}
			file = new File(dir + fileName);
			
			// File is handled appropriately
			if (fileName.contains("..")) {
				out.println(ERROR);
			}
			else if (file.exists() && fileName.contains(".jpg")) { // Need byte arrays of binary files to successfully stream
				out.println(OK);
				out.println(TYPE_IMG);
				
				BufferedImage img = ImageIO.read(file);
				ByteArrayOutputStream imgOut = new ByteArrayOutputStream();
				ImageIO.write(img, "jpg", imgOut);
				imgOut.flush();
				
				byte[] imgBytes = imgOut.toByteArray(); // Byte[] taken from handy ImageIO class
				out.write(imgBytes, 0, imgBytes.length);
				out.flush();
				
				imgOut.close();
			}
			else if (file.exists()) {
				out.println(OK);
				out.println(TYPE_TEXT);
				
				textIn = new BufferedInputStream(new FileInputStream(file));
				byte[] bytes = new byte[(int)file.length()];
				textIn.read(bytes, 0, bytes.length); // Text file read in from an Input stream for making Byte[]
				
				out.write(bytes, 0, bytes.length);
				out.flush();
				
				textIn.close();
			}
			else if (!file.exists()) {
				out.println(FILE_DNE);
				out.flush();
			}
			else {
				out.println(ERROR);
				out.flush();
			}
			
			out.close();	
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}