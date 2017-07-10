import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CounterPanel extends JPanel
{
	JLabel label;
	
	public CounterPanel()
	{
		label = new JLabel(); 
		add(label); 
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		label.setText(Integer.toString(MModel.numMines));
		repaint();
	}
}