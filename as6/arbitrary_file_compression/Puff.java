import java.io.*; 
import java.util.*;

public class Puff {
	
	static int count = 0;
	static HuffmanNode tree;
	static List<Integer> bitList;
	
	public static void main(String[] args) {
		try {
			String fileName = args[0];
			if(!fileName.contains(".huff")) {
				usage();
				System.exit(0);
			}
			
			// Get data from encoded file
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
			
			tree = (HuffmanNode)in.readObject();
			int fileSize = in.readInt();
			int codedBytesLength = in.readInt();
			byte[] codedBytes = new byte[codedBytesLength];
			in.readFully(codedBytes);
			in.close();
			
			// Turn bytes into a bit sequence
			bitList = new ArrayList<Integer>();
			bitList = Twiddle.bytesToBits(codedBytes);
			
			// Get decodedBytes[] 
			byte[] decodedBytes = new byte[fileSize];
			for (int i = 0; i < fileSize; i++) {
				decodedBytes[i] = getDecodedByte(tree);
			}
			
			// Write decoded file
			FileOutputStream out = new FileOutputStream(fileName.replace(".huff", ""));
			out.write(decodedBytes);
			out.flush();
			out.close();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	public static byte getDecodedByte(HuffmanNode current) {
		HuffmanNode z = current.getZero();
		HuffmanNode o = current.getOne();
		
		byte result = 0;
		
		if(z == null && o == null) {
			return current.getByt();
		}
		else if (bitList.get(count) == 0) {
			count++;
			result = getDecodedByte(z);
		}
		else if (bitList.get(count) == 1) {
			count++;
			result = getDecodedByte(o);
		}
		
		return result;
	}
	
	public static void usage() {
		System.out.println("USAGE: java Puff <inFile.huff>");
	}
}