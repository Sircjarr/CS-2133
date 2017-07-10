import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class XButton extends JButton //This class is essentially a custom JButton with additional functionality
{
	private boolean mine; 
	private boolean revealed; 
	private byte value; // byte ideal for saving memory since max value is 127, min value -128
    private int row;
    private int col;
	private String imageName;
	JLabel flag;
	private boolean hasFlag;

	public XButton(String x) {
		super(x);//Accesses JButtons constructor
		mine = false; 
		revealed = false;
		hasFlag = false;
		this.setBackground(Color.BLACK);
	}	
	
	public String getImage() {
		return imageName;
	}
	
	public void setImage(String imName) {
		imageName = imName;
		ImageIcon icon = new ImageIcon(imName);
		Image im = icon.getImage();
		Image im2 = im.getScaledInstance(MModel.buttonDim, MModel.buttonDim,
		Image.SCALE_DEFAULT);
		this.setIcon(new ImageIcon(im2));
	}
	
	public void setFlag(String flagName) {
		flag = new JLabel();
		this.add(flag);
		ImageIcon icon = new ImageIcon(flagName);
		Image im = icon.getImage();
		Image im2 = im.getScaledInstance(MModel.buttonDim/2, MModel.buttonDim/2,
		Image.SCALE_DEFAULT);
		flag.setIcon(new ImageIcon(im2));
		hasFlag = true;
		MModel.numMines--;
		
		MModel.flagGrid[row][col] = true;
	}
	
	public void removeFlag() {
		flag.setIcon(null);
		hasFlag = false;
		MModel.numMines++;
		
		MModel.flagGrid[row][col] = false;
	}
	
	public boolean getFlag() {
		return hasFlag;
	}
	
	public boolean getMine() {
		return mine; 
	}
	
	public void setMine() {
		mine = true; 
	}
	
	public String getValue() {
		return Byte.toString(value);
	}
	
	public void setValue(byte x) {
		value = x;
	}
	
	public boolean getRevealed() {
		return revealed;
	}
	
	public void setRevealed() {
		revealed = true;
		setIcon(null);
		if(getFlag()) {
			removeFlag();
		}
		if (getMine() == true) {
			setImage("img/mine.jpg");
		}
		else if (getValue().equals("0")) {
			MModel.autoReveal(row, col, MPanel.buttonGrid);
		}
		else if (getValue().equals("1")) {
			this.setForeground(Color.BLUE);
			setText(Byte.toString(value));
		}
		else if (getValue().equals("2")) {
			this.setForeground(Color.MAGENTA);
			setText(Byte.toString(value));
		}
		else if (getValue().equals("3")) {
			this.setForeground(Color.GREEN);
			setText(Byte.toString(value));
		}
		else if (getValue().equals("4")) {
			this.setForeground(Color.PINK);
			setText(Byte.toString(value));
		}
		else if (getValue().equals("5")) {
			this.setForeground(Color.ORANGE);
			setText(Byte.toString(value));
		}
		else if (getValue().equals("6")) {
			this.setForeground(Color.RED);
			setText(Byte.toString(value));
		}
		else if (getValue().equals("7")) {
			this.setForeground(Color.YELLOW);
			setText(Byte.toString(value));
		}
		else if (getValue().equals("8")) {
			this.setForeground(Color.GRAY);
			setText(Byte.toString(value));
		}
		MModel.minesClear--;
		MModel.revealGrid[row][col] = true;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int r) {
		row = r;
	}
	
	public int getCol() {
		return col;
	}
	
	public void setCol(int c) {
		col = c;
	}
}