import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.LinkedList;

public class Huff {
	
	public static void main(String[] args) {
		if (args.length == 0) {
			usage();
			System.exit(0);
		}
		
		try {
			// Get bytes from file
			String fileName = args[0];
			FileInputStream in = new FileInputStream(args[0]);
			int fileSize = in.available();
			byte[] bytes = new byte[fileSize]; // available() returns number of bytes left in stream
			in.read(bytes); // fills bytes
			in.close();
			
			// Create empty nodes for every byte
			HashMap<Byte, HuffmanNode> nodes = new HashMap<Byte, HuffmanNode>();
			int count = 256;
			for(byte i = -128; count > 0 ; i++) { // a byte loops back to -128 with addition
				HuffmanNode node = new HuffmanNode(i, 0);
				nodes.put(i, node);
				count--;
			}
			
			// Set frequencies of nodes
			for (int i = 0; i < bytes.length; i++) {
				nodes.get(bytes[i]).increaseFrequency();
			}
			
			// Add nodes to a priority queue
			count = 256;
			PriorityQueue<HuffmanNode> pq = new PriorityQueue<HuffmanNode>();
			for(byte i = -128; count > 0; i++) {
				if(nodes.get(i).frequency != 0) {
					pq.add(nodes.get(i));
				}
				count--;
			}
			
			// Create tree
			while(true) {
				if (pq.size() == 1) {
					break;
				}
				else {
					HuffmanNode zero = pq.poll();
					HuffmanNode one = pq.poll();
					HuffmanNode parent = new HuffmanNode(null, zero, one, (zero.frequency + one.frequency));
					zero.setParent(parent);
					one.setParent(parent);
					pq.add(parent);
				}
			}
			HuffmanNode tree = pq.poll();

			// Call recursive method in HuffmanNode to get bit string
			String bitString = "";
			for (int i = 0; i < bytes.length; i ++) {
				bitString += nodes.get(bytes[i]).getBitString();
			}
			
			// Pad the bitString
			int padNum = (8 - (bitString.length() % 8));
			for (int i = 0; i < padNum; i++) {
				bitString += "0";
			}
			
			// Convert bitString to byte[]
			ArrayList<Integer> bitList = new ArrayList<Integer>();
			for (int i = 0; i < bitString.length(); i++) {
				String bit = bitString.substring(i, i + 1);
				bitList.add(Integer.parseInt(bit));
			}
			byte[] codedBytes = Twiddle.bitsToBytes(bitList);
			
			// Write data into new encoded .huff file
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName + ".huff"));
			out.writeObject(tree);
			out.writeInt(fileSize);
			out.writeInt(codedBytes.length);
			out.write(codedBytes);
			out.flush();
			out.close();
		}
		catch(IOException ex) {
			System.out.println("ERROR: could not access or open file.");
		}
	}
	
	public static void usage() {
		System.out.println("USAGE: java Huff <inFile>");
	}
}