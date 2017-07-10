import java.lang.*; // contains most exceptions
import java.io.*; // contains IOException
import java.util.*;
//FIXME: implement file.canWrite()

public class Caesar
{
	public static void main(String[] args)
	{
		if (args.length < 2) {
			usage();
		}
		
		int charValue;
		char outputChar;
		
		try {
			int key = Integer.parseInt(args[0]);
			Scanner scan = new Scanner(new File(args[1]));
			String encrypted = "";
			
			while(scan.hasNext()) {
				String token = scan.next();
				for (int i = 0; i < token.length(); i++) {
					charValue = (int)(token.charAt(i));
					if (charValue < 32 || charValue > 126) { outputChar = (char)charValue; }
					charValue = charValue + key; 
					// We include both of these so that we can encode by using a poitive number
					// and decode using the same number as a negative
					
					if (charValue > 221 || charValue < -63) {
						throw new IllegalArgumentException();
					}
					
					if (charValue > 126) {
						charValue = charValue - 95; //(127 - 32)
					}
					if (charValue < 32) {
						charValue = charValue + 95;
					}
					outputChar = (char)charValue;
					encrypted += outputChar;
				}
				encrypted += " ";
			}
			
			if (args.length > 2) {
				if (!args[2].contains(".txt")) {
					throw new Exception(); // throwing exceptions are needed when specific conditions are user-defined. 
				}
				
				PrintWriter writer = new PrintWriter(new File(args[2]));
				writer.println(encrypted);
				writer.close();
			}
			else {
				System.out.print(encrypted);
			}
		}
		catch (IOException e) {
			System.out.println("File not found or readable!");
		}
		catch (IllegalArgumentException e) {
			System.out.println("Illegal argument!");
		}
		catch (Exception e)
		{
			System.out.print("File not writable!");
		}
	}
	
	public static void usage() {
		System.out.println("USAGE: java Caesar <key> <infile> <outfile>");
		System.out.println("To Decrypt: use <-key>");
		System.out.println("Recommended highest key: 94");
		System.exit(0);
	}
}