import javax.swing.*;
import java.awt.*; 

public class MModel
{
	public static final int dimension = 13;
	public static final double probIsMine = 0.12; 
	public static int numMines = 0;
	public static boolean[][] grid;
	public static int buttonDim;
	public static int minesClear;

	public static boolean[][] createBoolGrid()
	{
		grid = new boolean[dimension][dimension];
		for (int i = 0; i < dimension; i++)
		{
			for (int x = 0; x < dimension; x++)
			{
				if (chanceOfMine() == false)
				{
					continue;
				}
				else
				{
					grid[i][x] = true;
					numMines++;
				}
			}
		}
		minesClear = (dimension * dimension) - numMines;
		return grid;
	}
	
	public static boolean chanceOfMine()
	{
		double chance = Math.random(); //In Java, Math.Random() returns a double between 0 and 1;
		if (chance <= probIsMine)
		{
			return true;
		}
		else
		{
			return false; 
		}
	}
	
	public static void setButtons(XButton[][] buttonGrid)
	{
		byte count = 0;
		for (int row = 0; row < dimension; row++)
		{
			for (int col = 0; col < dimension; col++)
			{
				if (buttonGrid[row][col].getMine() == true)
				{
					continue; 
				}
				if (row != 0 && row != (dimension - 1) && col != 0 && col != (dimension - 1))  //middle squares
				{
					if (buttonGrid[row - 1][col -1].getMine() == true) //top left
					{
						count++;
					}
					if (buttonGrid[row - 1][col].getMine() == true) //top mid
					{
						count++;
					}
					if (buttonGrid[row - 1][col +1].getMine() == true) //top right
					{
						count++;
					}
					if (buttonGrid[row][col + 1].getMine() == true) //mid right
					{
						count++;
					}
					if (buttonGrid[row + 1][col + 1].getMine() == true) //bottom right
					{
						count++;
					}
					if (buttonGrid[row + 1][col].getMine() == true) //bottom mid
					{
						count++;
					}
					if (buttonGrid[row + 1][col - 1].getMine() == true) //bottom left
					{
						count++;
					}
					if (buttonGrid[row][col -1].getMine() == true) //mid left
					{
						count++;
					}
				}
				else if (row == 0  && col != 0 && col != (dimension - 1)) //top border squares
				{
					if (buttonGrid[row][col-1].getMine() == true)
					{
						count++;
					}
					if (buttonGrid[row + 1][col - 1].getMine() == true)
					{
						count++;
					}
					if (buttonGrid[row + 1][col].getMine() == true)
					{
						count++;
					}
					if (buttonGrid[row + 1][col + 1].getMine() == true)
					{
						count++; 
					}
					if (buttonGrid[row][col+1].getMine() == true)
					{
						count++;
					}
				}
				else if (col == 0 && row != 0 && row != (dimension - 1)) //left border
				{
					if (buttonGrid[row - 1][col].getMine() == true)
					{
						count++;
					}
					if (buttonGrid[row - 1][col + 1].getMine() == true)
					{
						count++;
					}
					if (buttonGrid[row][col + 1].getMine() == true)
					{
						count++;
					}
					if (buttonGrid[row + 1][col + 1].getMine() == true)
					{
						count++; 
					}
					if (buttonGrid[row + 1][col].getMine() == true)
					{
						count++;
					}
				}
				else if (col == (dimension - 1) && row != 0 && row != (dimension - 1)) //right border
				{
					if (buttonGrid[row - 1][col].getMine() == true)
					{
						count++;
					}
					if (buttonGrid[row - 1][col - 1].getMine() == true)
					{
						count++;
					}
					if (buttonGrid[row][col - 1].getMine() == true)
					{
						count++;
					}
					if (buttonGrid[row + 1][col - 1].getMine() == true)
					{
						count++; 
					}
					if (buttonGrid[row + 1][col].getMine() == true)
					{
						count++;
					}
				}
				else if (row == (dimension - 1) && col != 0 && col != (dimension - 1)) //bottom border
				{
					if (buttonGrid[row][col-1].getMine() == true)
					{
						count++;
					}
					if (buttonGrid[row - 1][col - 1].getMine() == true)
					{
						count++;
					}
					if (buttonGrid[row - 1][col].getMine() == true)
					{
						count++;
					}
					if (buttonGrid[row - 1][col + 1].getMine() == true)
					{
						count++; 
					}
					if (buttonGrid[row][col+1].getMine() == true)
					{
						count++;
					}
				}
				else if (row == 0 && col == 0) // top left corner square
				{
					if (buttonGrid[row][col+1].getMine() == true)
					{
						count++;
					}
					if (buttonGrid[row + 1][col + 1].getMine() == true)
					{
						count++;
					}
					if (buttonGrid[row + 1][col].getMine() == true)
					{
						count++;
					}
			    }
				else if (row == 0 && col == (dimension - 1)) // top right corner square
				{
					if (buttonGrid[row][col-1].getMine() == true)
					{
						count++;
					}
					if (buttonGrid[row + 1][col-1].getMine() == true)
					{
						count++;
					}
					if (buttonGrid[row + 1][col].getMine() == true)
					{
						count++;
					}
				}
				else if (row == (dimension - 1) && col == 0)// bottom left corner square
				{
					if (buttonGrid[row - 1][col].getMine() == true)
					{
						count++;
					}
					if (buttonGrid[row - 1][col + 1].getMine() == true)
					{
						count++;
					}
					if (buttonGrid[row][col+1].getMine() == true)
					{
						count++;
					}
				}
				else if (row == (dimension - 1) && col == (dimension - 1)) // bottom right corner square
				{
					if (buttonGrid[row - 1][col].getMine() == true)
					{
						count++;
					}
					if (buttonGrid[row - 1][col - 1].getMine() == true)
					{
						count++;
					}
					if (buttonGrid[row][col - 1].getMine() == true)
					{
						count++;
					}
				}
				buttonGrid[row][col].setValue(count);
				count = 0;
			}
		}
	}
	
	public static void revealAll(XButton[][] buttonGrid)
	{
		for (int i = 0; i < dimension; i++)
		{
			for (int x = 0; x < dimension; x++)
			{
				buttonGrid[i][x].setRevealed(); 
			}
		}
	}
	
	public static void autoReveal(int row, int col, XButton[][] buttonGrid)
	{
		if ((row != 0 && row != (dimension - 1)) && (col != 0 && col != (dimension - 1)))  //Middle squares
		{
			if (buttonGrid[row - 1][col - 1].getRevealed() == false) //top left
			{
				buttonGrid[row - 1][col - 1].setRevealed();
			}
			if (buttonGrid[row - 1][col].getRevealed() == false) //top mid
			{
				buttonGrid[row - 1][col].setRevealed();
			}
			if (buttonGrid[row - 1][col + 1].getRevealed() == false) //top right
			{
				buttonGrid[row - 1][col +1].setRevealed();
			}
			if (buttonGrid[row][col + 1].getRevealed() == false) //mid right
			{
				buttonGrid[row][col + 1].setRevealed();
			}
			if (buttonGrid[row + 1][col + 1].getRevealed() == false) //bottom right
			{
				buttonGrid[row + 1][col + 1].setRevealed();
			}
			if (buttonGrid[row + 1][col].getRevealed() == false) //bottom mid
			{
				buttonGrid[row + 1][col].setRevealed();
			}
			if (buttonGrid[row + 1][col - 1].getRevealed() == false) //bottom left
			{
				buttonGrid[row + 1][col - 1].setRevealed();
			}
			if (buttonGrid[row][col - 1].getRevealed() == false) //mid left
			{
				buttonGrid[row][col - 1].setRevealed();
			}
		}
		else if (row == 0  && col != 0 && col != (dimension - 1)) //top border squares
		{
			if (buttonGrid[row][col - 1].getRevealed() == false)
			{
				buttonGrid[row][col - 1].setRevealed();
			}
			if (buttonGrid[row + 1][col - 1].getRevealed() == false)
			{
				buttonGrid[row + 1][col - 1].setRevealed();
			}
			if (buttonGrid[row + 1][col].getRevealed() == false)
			{
				buttonGrid[row + 1][col].setRevealed();
			}
			if (buttonGrid[row + 1][col + 1].getRevealed() == false)
			{
				buttonGrid[row + 1][col + 1].setRevealed(); 
			}
			if (buttonGrid[row][col + 1].getRevealed() == false)
			{
				buttonGrid[row][col + 1].setRevealed();
			}
		}
		else if (col == 0 && row != 0 && row != (dimension - 1)) //left border
		{
			if (buttonGrid[row - 1][col].getRevealed() == false)
			{
				buttonGrid[row - 1][col].setRevealed();
			}
			if (buttonGrid[row - 1][col + 1].getRevealed() == false)
			{
				buttonGrid[row - 1][col + 1].setRevealed();
			}
			if (buttonGrid[row - 1][col + 1].getRevealed() == false)
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
		else if (col == (dimension - 1) && row != 0 && row != (dimension - 1)) //right border
		{
			if (buttonGrid[row - 1][col].getRevealed() == false)
			{
				buttonGrid[row - 1][col].setRevealed();
			}
			if (buttonGrid[row - 1][col - 1].getRevealed() == false)
			{
				buttonGrid[row - 1][col - 1].setRevealed();
			}
			if (buttonGrid[row][col - 1].getRevealed() == false)
			{
				buttonGrid[row][col - 1].getRevealed();
			}
			if (buttonGrid[row + 1][col - 1].getRevealed() == false)
			{
				buttonGrid[row + 1][col - 1].getRevealed(); 
			}
			if (buttonGrid[row + 1][col].getRevealed() == false)
			{
				buttonGrid[row + 1][col].setRevealed();
			}
		}
		else if (row == (dimension - 1) && col != 0 && col != (dimension - 1)) //bottom border
		{
			if (buttonGrid[row][col - 1].getRevealed() == false)
			{
				buttonGrid[row][col - 1].setRevealed();
			}
			if (buttonGrid[row - 1][col - 1].getRevealed() == false)
			{
				buttonGrid[row - 1][col - 1].setRevealed();
			}
			if (buttonGrid[row - 1][col].getRevealed() == false)
			{
				buttonGrid[row - 1][col].setRevealed();
			}
			if (buttonGrid[row - 1][col + 1].getRevealed() == false)
			{
				buttonGrid[row - 1][col + 1].setRevealed(); 
			}
			if (buttonGrid[row][col + 1].getRevealed() == false)
			{
				buttonGrid[row][col + 1].setRevealed();
			}
		}
		else if (row == 0 && col == 0) // top left corner square
		{
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
		else if (row == 0 && col == (dimension - 1)) // top right corner square
		{
			if (buttonGrid[row][col - 1].getRevealed() == false)
			{
				buttonGrid[row][col-1].setRevealed();
			}
			if (buttonGrid[row + 1][col - 1].getRevealed() == false)
			{
				buttonGrid[row + 1][col - 1].setRevealed();
			}
			if (buttonGrid[row + 1][col].getRevealed() == false)
			{
				buttonGrid[row + 1][col].setRevealed();
			}
		}
		else if (row == (dimension - 1) && col == 0)// bottom left corner square
		{
			if (buttonGrid[row - 1][col].getRevealed() == false)
			{
				buttonGrid[row - 1][col].setRevealed();
			}
			if (buttonGrid[row - 1][col + 1].getRevealed() == false)
			{
				buttonGrid[row - 1][col + 1].setRevealed();
			}
			if (buttonGrid[row][col + 1].getRevealed() == false)
			{
				buttonGrid[row][col + 1].setRevealed();
			}
		}
		else if (row == (dimension - 1) && col == (dimension - 1)) // bottom right corner square
		{
			if (buttonGrid[row - 1][col].getRevealed() == false)
			{
				buttonGrid[row - 1][col].setRevealed();
			}
			if (buttonGrid[row - 1][col - 1].getRevealed() == false)
			{
				buttonGrid[row - 1][col - 1].setRevealed();
			}
			if (buttonGrid[row][col - 1].getRevealed() == false)
			{
				buttonGrid[row][col - 1].setRevealed();
			}
		}
	}
	
	public static void setDefaultIm(XButton[][] buttonGrid)
	{
		for (int i = 0; i < dimension; i++)
		{
			for (int x = 0; x < dimension; x++)
			{
				buttonGrid[i][x].setImage("img/default.jpg");
			}
		}
	}
	
	public static void victory()
	{
		JOptionPane.showMessageDialog(null, "Thank you for playing my game!");
		System.exit(0);
	}
}