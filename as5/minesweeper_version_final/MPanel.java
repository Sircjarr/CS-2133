import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

public class MPanel extends JPanel {
	public static XButton[][] buttonGrid;
	
	public MPanel() {
		MModel.createBoolGrids();
		int dimension = MModel.dimension;
		
		makeButtonGrid(dimension);
	}
	
	public MPanel(Save savedGame) {
		// Update MModel accoring to the save
		MModel.probIsMine = savedGame.getProbIsMine();
		MModel.numMines = savedGame.getNumMines();
		MModel.minesClear = savedGame.getMinesClear();
		MModel.dimension = savedGame.getDimension();
		int dimension = MModel.dimension;
		MModel.numMines = (int)((dimension * dimension) * MModel.probIsMine);
		MModel.minesClear = (dimension * dimension) - MModel.numMines;
		
		MModel.mineGrid = new boolean[dimension][dimension];
		MModel.revealGrid = new boolean[dimension][dimension];
		MModel.flagGrid = new boolean[dimension][dimension];
		MModel.mineGrid = savedGame.mineGrid;
		MModel.revealGrid = savedGame.revealGrid;
		MModel.flagGrid = savedGame.flagGrid;
		
		MModel.gameWon = savedGame.gameWon;
		
		makeButtonGrid(dimension);
	}
	
	// This code executes whether there is a saved file or not. 
	public void makeButtonGrid(int dimension) {
		
		setBackground(Color.GRAY);
		buttonGrid = new XButton[dimension][dimension];
		setLayout(new GridLayout(dimension, dimension));
		for (int i = 0; i < dimension; i++) {
			for (int x = 0; x < dimension; x++) {
				XButton button = new XButton(null);
				button.addActionListener(new ButtonHandler());
				button.addMouseListener(new MouseHandler());
				add(button);
				buttonGrid[i][x] = button;
				button.setRow(i);
				button.setCol(x);
				if (MModel.mineGrid[i][x] == true) {
					button.setMine();
				}
			}
		}
		MModel.setButtons(buttonGrid);
	}

	private class ButtonHandler extends MModel
		implements ActionListener {

		public void actionPerformed(ActionEvent e) { // override the only method in ActionListener Interface (ActionListener extends EventListener)
			XButton j = (XButton)(e.getSource());
			if (j.getFlag() || j.getRevealed() == true || MModel.gameWon == true)  { // prevents a flag from being clicked on. Conditions are also needed in MouseHandler. 
				;
			}
			else if (j.getMine() == true){
				revealAll(buttonGrid); // method revealAll() from extended class MModel
				JOptionPane.showMessageDialog(null, "You lose! Try again!");
			}
			else {
				j.setRevealed();
			}
			
			if (MModel.gameWon == true) {
				;
			}
			else if ((MModel.numMines == 0) && (MModel.minesClear == 0)) { // Case where you win
				MModel.victory();
			}
		}
	}
	
	private class MouseHandler extends MouseAdapter // extending MouseAdapter allows implementing only methods of interest.
		implements MouseListener {
			
		public void mousePressed(MouseEvent e) {
			XButton j = (XButton)(e.getSource());
	
			if (e.getButton() == 1 && j.getFlag() == true || MModel.gameWon == true) { // e.getButton() == 1 detects left click
				;
			}
			else if (e.getButton() == 3 && j.getRevealed() == false && j.getFlag() == false) { // e.getButton()  == 3 detects a right click
				j.setFlag("img/flag.jpg");
			}
            else if (e.getButton() == 3 && j.getFlag() == true) {
				j.removeFlag();
			}		
			
			if (MModel.gameWon == true) {
				;
			}
			else if ((MModel.numMines == 0) && (MModel.minesClear == 0)) {
				MModel.victory();
			}
		}
	}
}