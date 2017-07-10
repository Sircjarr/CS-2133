import javax.swing.*;
import java.awt.*; 

public class RFrame extends JFrame {
	
	public RPanel panel;
	
	public RFrame() {
		setTitle("Robot Controller");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new RPanel();
		add(panel, BorderLayout.CENTER);
		setVisible(true);
		setResizable(false);
	}
}