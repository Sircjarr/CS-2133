// Leaf nodes: null children, filled byte; Internal nodes: null byte; Tree root: null Parent
import java.io.Serializable;
import java.util.*;

public class HuffmanNode implements Comparable, Serializable { //Objects that do not have comparable must implement them
	
	private HuffmanNode parent;
	private HuffmanNode zero;
	private HuffmanNode one;
	
	int frequency;
	byte byt;
	
	public HuffmanNode(byte byt, int frequencey) {
		this.byt = byt; 
		frequency = 0;
	}
	
	public HuffmanNode(HuffmanNode parent, HuffmanNode zero, HuffmanNode one, int frequency) {
		this.parent = parent;
		this.zero = zero;
		this.one = one;
		this.frequency = frequency;
	}
	
	public HuffmanNode getParent() {
		return parent;
	}
	
	public void setParent(HuffmanNode parent) {
		this.parent = parent;
	}
	
	public HuffmanNode getZero() {
		return zero;
	}
	
	public HuffmanNode getOne() {
		return one;
	}
	
	public byte getByt() {
		return byt;
	}
	
	public void increaseFrequency() {
		frequency++;
	}
	
	@Override
	public int compareTo(Object o) {
		HuffmanNode n = (HuffmanNode)o;
		if(frequency < n.frequency) {
			return -1;
		}
		else if (frequency > n.frequency) {
			return 1;
		}
		
		return 0;
	}
	
	public String getBitString() {
		HuffmanNode p = getParent();
		
		String result = "";
		
		if (p == null) {
			return result;
		}
		else if (this == p.getZero()) {
			result += "0";
		}
		else if (this == p.getOne()) {
			result += "1";
		}
		
		return result = p.getBitString() + result;
	}
}