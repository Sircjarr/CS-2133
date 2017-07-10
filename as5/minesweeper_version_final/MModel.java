import javax.swing.*;
import java.awt.*; 
import java.io.Serializable;

public class MModel {
	public static int dimension = 12;
	public static double probIsMine = 0.10; 
	public static int numMines = 0;
	
	public static int buttonDim;
	public static int minesClear;
	
	public static boolean[][] mineGrid; // These grids keep track of the state of the game for save/load purposes.
	public static boolean[][] revealGrid;
	public static boolean[][] flagGrid;
	
	public static boolean gameWon = false;

	// Determines where mines should be set
	public static boolean[][] createBoolGrids() {
		MModel.revealGrid = new boolean[dimension][dimension];
		MModel.flagGrid = new boolean[dimension][dimension];
		mineGrid = new boolean[dimension][dimension];
		numMines = (int)((dimension * dimension) * probIsMine);
		
		for (int i = 0; i < numMines; i++) {
			int randX = (int)(Math.random() * dimension);
			int randY = (int)(Math.random() * dimension);
			
			if (mineGrid[randX][randY] == true) {
				i--;
				continue;
			}
			else {
				mineGrid[randX][randY] = true;
			}
		}
		minesClear = (dimension * dimension) - numMines;
		return mineGrid;
	}
	
	// Assigns buttons with the appropiate number
	public static void setButtons(XButton[][] buttonGrid) {
		byte count = 0;
		for (int row = 0; row < dimension; row++) {
			for (int col = 0; col < dimension; col++) {
				if (buttonGrid[row][col].getMine() == true) {
					continue; 
				}
				if (row != 0 && row != (dimension - 1) && col != 0 && col != (dimension - 1)) { //middle squares
					if (buttonGrid[row - 1][col -1].getMine() == true) { //top left 
						count++;
					}
					if (buttonGrid[row - 1][col].getMine() == true) { //top mid
						count++;
					}
					if (buttonGrid[row - 1][col +1].getMine() == true) { //top right
						count++;
					}
					if (buttonGrid[row][col + 1].getMine() == true) { //mid right
						count++;
					}
					if (buttonGrid[row + 1][col + 1].getMine() == true) { //bottom right
						count++;
					}
					if (buttonGrid[row + 1][col].getMine() == true) { //bottom mid
						count++;
					}
					if (buttonGrid[row + 1][col - 1].getMine() == true) { //bottom left
						count++;
					}
					if (buttonGrid[row][col -1].getMine() == true) { //mid left
						count++;
					}
				}
				else if (row == 0  && col != 0 && col != (dimension - 1)) { //top border squares
					if (buttonGrid[row][col-1].getMine() == true) {
						count++;
					}
					if (buttonGrid[row + 1][col - 1].getMine() == true) {
						count++;
					}
					if (buttonGrid[row + 1][col].getMine() == true) {
						count++;
					}
					if (buttonGrid[row + 1][col + 1].getMine() == true) {
						count++; 
					}
					if (buttonGrid[row][col+1].getMine() == true) {
						count++;
					}
				}
				else if (col == 0 && row != 0 && row != (dimension - 1)) { //left border
					if (buttonGrid[row - 1][col].getMine() == true) {
						count++;
					}
					if (buttonGrid[row - 1][col + 1].getMine() == true) {
						count++;
					}
					if (buttonGrid[row][col + 1].getMine() == true) {
						count++;
					}
					if (buttonGrid[row + 1][col + 1].getMine() == true) {
						count++; 
					}
					if (buttonGrid[row + 1][col].getMine() == true) {
						count++;
					}
				}
				else if (col == (dimension - 1) && row != 0 && row != (dimension - 1)) {//right border
					if (buttonGrid[row - 1][col].getMine() == true) {
						count++;
					}
					if (buttonGrid[row - 1][col - 1].getMine() == true) {
						count++;
					}
					if (buttonGrid[row][col - 1].getMine() == true) {
						count++;
					}
					if (buttonGrid[row + 1][col - 1].getMine() == true) {
						count++; 
					}
					if (buttonGrid[row + 1][col].getMine() == true) {
						count++;
					}
				}
				else if (row == (dimension - 1) && col != 0 && col != (dimension - 1)) {//bottom border
					if (buttonGrid[row][col-1].getMine() == true) {
						count++;
					}
					if (buttonGrid[row - 1][col - 1].getMine() == true) {
						count++;
					}
					if (buttonGrid[row - 1][col].getMine() == true) {
						count++;
					}
					if (buttonGrid[row - 1][col + 1].getMine() == true) {
						count++; 
					}
					if (buttonGrid[row][col+1].getMine() == true) {
						count++;
					}
				}
				else if (row == 0 && col == 0) { // top left corner square
					if (buttonGrid[row][col+1].getMine() == true) {
						count++;
					}
					if (buttonGrid[row + 1][col + 1].getMine() == true) {
						count++;
					}
					if (buttonGrid[row + 1][col].getMine() == true) {
						count++;
					}
			    }
				else if (row == 0 && col == (dimension - 1)) { // top right corner square
					if (buttonGrid[row][col-1].getMine() == true) {
						count++;
					}
					if (buttonGrid[row + 1][col-1].getMine() == true) {
						count++;
					}
					if (buttonGrid[row + 1][col].getMine() == true) {
						count++;
					}
				}
				else if (row == (dimension - 1) && col == 0) { // bottom left corner square
					if (buttonGrid[row - 1][col].getMine() == true) {
						count++;
					}
					if (buttonGrid[row - 1][col + 1].getMine() == true) {
						count++;
					}
					if (buttonGrid[row][col+1].getMine() == true) {
						count++;
					}
				}
				else if (row == (dimension - 1) && col == (dimension - 1)) { // bottom right corner square
					if (buttonGrid[row - 1][col].getMine() == true) {
						count++;
					}
					if (buttonGrid[row - 1][col - 1].getMine() == true) {
						count++;
					}
					if (buttonGrid[row][col - 1].getMine() == true) {
						count++;
					}
				}
				buttonGrid[row][col].setValue(count);
				count = 0;
			}
		}
	}
	
	// Triggered when a game is lost
	public static void revealAll(XButton[][] buttonGrid) {
		for (int i = 0; i < dimension; i++) {
			for (int x = 0; x < dimension; x++) {
				buttonGrid[i][x].setRevealed(); 
			}
		}
	}
	
	// Method that reveals squares surrounded by a zero XButton
	public static void autoReveal(int row, int col, XButton[][] buttonGrid) {
		if ((row != 0 && row != (dimension - 1)) && (col != 0 && col != (dimension - 1))) { //Middle squares
			if (buttonGrid[row - 1][col - 1].getRevealed() == false) { //top left
				buttonGrid[row - 1][col - 1].setRevealed();
			}
			if (buttonGrid[row - 1][col].getRevealed() == false) { //top mid
				buttonGrid[row - 1][col].setRevealed();
			}
			if (buttonGrid[row - 1][col + 1].getRevealed() == false) { //top right
				buttonGrid[row - 1][col +1].setRevealed();
			}
			if (buttonGrid[row][col + 1].getRevealed() == false) { //mid right
				buttonGrid[row][col + 1].setRevealed();
			}
			if (buttonGrid[row + 1][col + 1].getRevealed() == false) { //bottom right
				buttonGrid[row + 1][col + 1].setRevealed();
			}
			if (buttonGrid[row + 1][col].getRevealed() == false) { //bottom mid
				buttonGrid[row + 1][col].setRevealed();
			}
			if (buttonGrid[row + 1][col - 1].getRevealed() == false) { //bottom left
				buttonGrid[row + 1][col - 1].setRevealed();
			}
			if (buttonGrid[row][col - 1].getRevealed() == false) { //mid left
				buttonGrid[row][col - 1].setRevealed();
			}
		}
		else if (row == 0  && col != 0 && col != (dimension - 1)) { //top border squares
			if (buttonGrid[row][col - 1].getRevealed() == false) {
				buttonGrid[row][col - 1].setRevealed();
			}
			if (buttonGrid[row + 1][col - 1].getRevealed() == false) {
				buttonGrid[row + 1][col - 1].setRevealed();
			}
			if (buttonGrid[row + 1][col].getRevealed() == false) {
				buttonGrid[row + 1][col].setRevealed();
			}
			if (buttonGrid[row + 1][col + 1].getRevealed() == false) {
				buttonGrid[row + 1][col + 1].setRevealed(); 
			}
			if (buttonGrid[row][col + 1].getRevealed() == false) {
				buttonGrid[row][col + 1].setRevealed();
			}
		}
		else if (col == 0 && row != 0 && row != (dimension - 1)) { //left border
			if (buttonGrid[row - 1][col].getRevealed() == false) {
				buttonGrid[row - 1][col].setRevealed();
			}
			if (buttonGrid[row - 1][col + 1].getRevealed() == false) {
				buttonGrid[row - 1][col + 1].setRevealed();
			}
			if (buttonGrid[row - 1][col + 1].getRevealed() == false) {
				buttonGrid[row][col + 1].setRevealed();
			}
			if (buttonGrid[row + 1][col + 1].getRevealed() == false) {
				buttonGrid[row + 1][col + 1].setRevealed(); 
			}
			if (buttonGrid[row + 1][col].getRevealed() == false) {
				buttonGrid[row + 1][col].setRevealed();
			}
		}
		else if (col == (dimension - 1) && row != 0 && row != (dimension - 1)) { //right border
			if (buttonGrid[row - 1][col].getRevealed() == false) {
				buttonGrid[row - 1][col].setRevealed();
			}
			if (buttonGrid[row - 1][col - 1].getRevealed() == false) {
				buttonGrid[row - 1][col - 1].setRevealed();
			}
			if (buttonGrid[row][col - 1].getRevealed() == false) {
				buttonGrid[row][col - 1].getRevealed();
			}
			if (buttonGrid[row + 1][col - 1].getRevealed() == false) {
				buttonGrid[row + 1][col - 1].getRevealed(); 
			}
			if (buttonGrid[row + 1][col].getRevealed() == false) {
				buttonGrid[row + 1][col].setRevealed();
			}
		}
		else if (row == (dimension - 1) && col != 0 && col != (dimension - 1)) { //bottom border
			if (buttonGrid[row][col - 1].getRevealed() == false) {
				buttonGrid[row][col - 1].setRevealed();
			}
			if (buttonGrid[row - 1][col - 1].getRevealed() == false) {
				buttonGrid[row - 1][col - 1].setRevealed();
			}
			if (buttonGrid[row - 1][col].getRevealed() == false) {
				buttonGrid[row - 1][col].setRevealed();
			}
			if (buttonGrid[row - 1][col + 1].getRevealed() == false) {
				buttonGrid[row - 1][col + 1].setRevealed(); 
			}
			if (buttonGrid[row][col + 1].getRevealed() == false) {
				buttonGrid[row][col + 1].setRevealed();
			}
		}
		else if (row == 0 && col == 0) { // top left corner square
			if (buttonGrid[row][col + 1].getRevealed() == false)
			{
				buttonGrid[row][col + 1].setRevealed();
			}
			if (buttonGrid[row + 1][col + 1].getRevealed() == false)
			{
				buttonGrid[row + 1][col + 1].setRevealed();
			}
			if (buttonGrid[row + 1][col].getRevealed() == false)
			{
				buttonGrid[row + 1][col].setRevealed();
			}
		}
		else if (row == 0 && col == (dimension - 1)) { // top right corner square
			if (buttonGrid[row][col - 1].getRevealed() == false) {
				buttonGrid[row][col-1].setRevealed();
			}
			if (buttonGrid[row + 1][col - 1].getRevealed() == false) {
				buttonGrid[row + 1][col - 1].setRevealed();
			}
			if (buttonGrid[row + 1][col].getRevealed() == false) {
				buttonGrid[row + 1][col].setRevealed();
			}
		}
		else if (row == (dimension - 1) && col == 0) { // bottom left corner square
			if (buttonGrid[row - 1][col].getRevealed() == false) {
				buttonGrid[row - 1][col].setRevealed();
			}
			if (buttonGrid[row - 1][col + 1].getRevealed() == false) {
				buttonGrid[row - 1][col + 1].setRevealed();
			}
			if (buttonGrid[row][col + 1].getRevealed() == false) {
				buttonGrid[row][col + 1].setRevealed();
			}
		}
		else if (row == (dimension - 1) && col == (dimension - 1)) { // bottom right corner square
			if (buttonGrid[row - 1][col].getRevealed() == false) {
				buttonGrid[row - 1][col].setRevealed();
			}
			if (buttonGrid[row - 1][col - 1].getRevealed() == false) {
				buttonGrid[row - 1][col - 1].setRevealed();
			}
			if (buttonGrid[row][col - 1].getRevealed() == false) {
				buttonGrid[row][col - 1].setRevealed();
			}
		}
	}
	
	// Assigns default image to the buttons at start of game
	public static void setDefaultIm(XButton[][] buttonGrid) {
		for (int i = 0; i < dimension; i++) {
			for (int x = 0; x < dimension; x++) {
				buttonGrid[i][x].setImage("img/default.jpg");
			}
		}
	}
	
	public static void victory() {
		gameWon = true;
		JOptionPane.showMessageDialog(null, "Thank you for playing my game!");
	}
	
	// Called when a game is loaded
	public static void setSavedCells() {
		for (int i = 0; i < dimension; i++) {
			for (int x = 0; x < dimension; x++) {
				if (MModel.revealGrid[i][x] == true && MPanel.buttonGrid[i][x].getRevealed() == false) {
					MPanel.buttonGrid[i][x].setRevealed();
				}
				if (MModel.flagGrid[i][x] == true) {
					MPanel.buttonGrid[i][x].setFlag("img/flag.jpg");
				}
			}
		}
	}
}