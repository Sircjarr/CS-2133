//JSON message: text format for sending objects over the internet.
// a series of name-value pairs, with curly brakets denoting object nesting levels and quotes surrounding strings.  
//EX: {"name1":"stringvalue1", "name2":numericvalue, "object":{"instance":"value:}} 

import java.io.*;
import java.net.*;
import java.util.IllegalFormatException;

public class RModel {
	
	final static String host = "lear.cs.okstate.edu";
	final static String filePath = "robot_sim.html";
	
	public static String linSpeed = "0.25";
	public static String angSpeed = "1.00";
	
	static PrintWriter out;
	
	public static void connectToHost() {
		try {
			Socket socket = new Socket(host, 9095);
			out = new PrintWriter(socket.getOutputStream());
				
			out.print("GET /" + filePath + " HTTP/1.1\r\n");
			out.print("Host: " + host + "\r\n");
			out.println("\r\n");
			out.flush();
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
	
	public static void takeoff() {
		out.println("{\"op\":\"publish\",\"topic\":\"/ardrone/takeoff\",\"msg\":{}}"); // for JSON, needs backslash before every "
		out.flush();
	}
	
	public static void land() {
		out.println("{\"op\":\"publish\",\"topic\":\"/ardrone/land\",\"msg\":{}}");
		out.flush();
	}
	
	public static void forward() {
		out.println("{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":"
			+ linSpeed + ",\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0}}}");
		out.flush();
	}
	
	public static void left() {
		out.println("{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":"
			+ linSpeed + ",\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0}}}");
		out.flush();
	}
	
	public static void turnLeft() {
		out.println("{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":"
			+ angSpeed + "}}}");
		out.flush();
	}
	
	public static void turnRight() {
		out.println("{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":-"
			+ angSpeed + "}}}");
		out.flush();
	}
	
	public static void right() {
		out.println("{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":-"
			+ linSpeed + ",\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0}}}");
		out.flush();
	}
	
	public static void backward() {
		out.println("{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":-"
			+ linSpeed + ",\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0}}}");
		out.flush();
	}
	
	public static void up() {
		out.println("{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0,\"z\":"
			+ linSpeed + "},\"angular\":{\"x\":0,\"y\":0,\"z\":0}}}");
		out.flush();
	}
	
	public static void down() {
		out.println("{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0,\"z\":-"
			+ linSpeed + "},\"angular\":{\"x\":0,\"y\":0,\"z\":0}}}");
		out.flush();
	}
	
	public static void stop() {
		out.println("{\"op\":\"publish\",\"topic\":\"/cmd_vel\",\"msg\":{\"linear\":{\"x\":0,\"y\":0,\"z\":0},\"angular\":{\"x\":0,\"y\":0,\"z\":0}}}");
		out.flush();
	}
	
	public static void incLinSpeed() {
		double newLinSpeed = Double.parseDouble(RModel.linSpeed) + .01;
		RModel.linSpeed = (String)String.format("%.2f", newLinSpeed);
	}
	
	public static void decLinSpeed() {
		double newLinSpeed = Double.parseDouble(RModel.linSpeed) - .01;
		RModel.linSpeed = (String)String.format("%.2f", newLinSpeed);
	}
	
	public static void incAngSpeed() {
		double newAngSpeed = Double.parseDouble(RModel.angSpeed) + .01;
		RModel.angSpeed = (String)String.format("%.2f", newAngSpeed);
	}
	
	public static void decAngSpeed() {
		double newAngSpeed = Double.parseDouble(RModel.angSpeed) - .01;
		RModel.angSpeed = (String)String.format("%.2f", newAngSpeed);
	}
}