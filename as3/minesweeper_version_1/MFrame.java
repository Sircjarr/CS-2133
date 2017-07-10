import javax.swing.*;
import java.awt.*;

public class MFrame extends JFrame 
{
	public static CounterPanel c;
	
	public MFrame()
	{
		setTitle("Minesweeper");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MPanel panel = new MPanel();
		add(panel, BorderLayout.CENTER);
        c = new CounterPanel(); 
		add(c, BorderLayout.NORTH);
		setVisible(true);
		MModel.buttonDim = MPanel.buttonGrid[0][0].getWidth();
		MModel.setDefaultIm(MPanel.buttonGrid);
		setResizable(false);
	}
}