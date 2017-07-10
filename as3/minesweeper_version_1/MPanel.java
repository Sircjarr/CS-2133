import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MPanel extends JPanel
{
	static XButton[][] buttonGrid; // FIXME include in MModel
	
	public MPanel()
	{
		MModel.createBoolGrid(); //FIXME can be put in MModel
		int dimension = MModel.dimension; 
		buttonGrid = new XButton[dimension][dimension];
		setLayout(new GridLayout(dimension, dimension));
		for (int i = 0; i < dimension; i++)
		{
			for (int x = 0; x < dimension; x++)
			{
				XButton button = new XButton(null);
				button.addActionListener(new ButtonHandler());
				button.addMouseListener(new MouseHandler());
				add(button);
				buttonGrid[i][x] = button;
				button.setRow(i);
				button.setCol(x);
				if (MModel.grid[i][x] == true)
				{
					button.setMine();
				}
			}
		}
		MModel.setButtons(buttonGrid);
	}
	
	private class ButtonHandler extends MModel // CONTROLLER elements/handlers for listeners are private classes with implemented methods.
		implements ActionListener {

		public void actionPerformed(ActionEvent e) // override the only method in ActionListener Interface (ActionListener extends EventListener)
		{
			XButton j = (XButton)(e.getSource());
			if (j.getFlag() || j.getRevealed() == true)  // prevents a flag from being clicked on. Conditions are also needed in MouseHandler. 
			{
				;
			}
			else if (j.getMine() == true)
			{
				revealAll(buttonGrid); // method revealAll() from extended class MModel
				JOptionPane.showMessageDialog(null, "You lose! Try again!");
				System.exit(0);
			}
			else
			{
				j.setIcon(null);
				j.setRevealed();
			}
			if ((MModel.numMines == 0) && (MModel.minesClear == 0)) // Case where you win
			{
				MModel.victory();
			}
		}
	}
	
	private class MouseHandler extends MouseAdapter // extending MouseAdapter allows implementing only methods of interest.
		implements MouseListener {
			
		public void mousePressed(MouseEvent e) // contains four other methods like MouseClicked and MouseReleased.
		{
			XButton j = (XButton)(e.getSource());
			if (e.getButton() == 1 && j.getFlag() == true) // e.getButton() == 1 detects left click
			{
				;
			}
			else if (e.getButton() == 3 && j.getRevealed() == false && j.getFlag() == false) // e.getButton()  == 3 detects a right click
			{
				j.setFlag("img/flag.jpg");
			}
            else if (e.getButton() == 3 && j.getFlag() == true) // e.getActionCommand.equals(string) would check buttons String value
			{
				j.removeFlag();
			}		
			if ((MModel.numMines == 0) && (MModel.minesClear == 0))
			{
				MModel.victory();
			}
		}
	}
}