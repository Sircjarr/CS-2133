import javax.swing.*;
import java.awt.*; // contains Toolkit
import java.awt.geom.*;

public class SierpinskiFrame extends JFrame
{
	public SierpinskiFrame()
	{
		setTitle("Sierpinski's Triangle");
		Toolkit kit = Toolkit.getDefaultToolkit(); // Toolkit grants access to computer details
		Dimension screenSize = kit.getScreenSize();
		setSize(screenSize.width, screenSize.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SierpinskiPanel panel = new SierpinskiPanel();
		add(panel);
		setVisible(true);
	}
}