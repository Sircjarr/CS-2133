import java.io.Serializable;
import java.util.*;

public class Save implements Serializable { // For objects meant to go through a stream
	
	private int dimension;
	private double probIsMine; 
	private static int numMines;
	private int buttonDim;
	private int minesClear;
	
	public boolean[][] mineGrid;
	public boolean[][] revealGrid;
	public boolean[][] flagGrid;
	
	public boolean gameWon;
	
	public Save(MModel model) {
		setDimension(model.dimension);
		setProbIsMine(model.probIsMine);
		setNumMines(model.numMines);
		setButtonDim(model.buttonDim);
		setMinesClear(model.minesClear);
		
		mineGrid = MModel.mineGrid;
		revealGrid = MModel.revealGrid;
		flagGrid = MModel.flagGrid;
		
		gameWon = MModel.gameWon;
	}
	
	public int getDimension() {
		return dimension;
	}
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	
	public double getProbIsMine() {
		return probIsMine;
	}
	public void setProbIsMine(double probIsMine) {
		this.probIsMine = probIsMine;
	}
	
	public int getNumMines() {
		return numMines;
	}
	public void setNumMines(int numMines) {
		this.numMines = numMines;
	}
	
	public int getButtonDim() {
		return buttonDim;
	}
	public void setButtonDim(int buttonDim) {
		this.buttonDim = buttonDim;
	}
	
	public int getMinesClear() {
		return minesClear;
	}
	public void setMinesClear(int minesClear) {
		this.minesClear = minesClear;
	}
}