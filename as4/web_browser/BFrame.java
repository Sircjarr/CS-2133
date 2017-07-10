import javax.swing.*;
import java.awt.*;

public class BFrame extends JFrame {
	
	public static BPanel panel;
	public static WebPanel panel2;
	
	public BFrame() {
		setTitle("Browser");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new BPanel();
		add(panel, BorderLayout.NORTH);
		panel2 = new WebPanel();
		add(panel2, BorderLayout.CENTER);
		setVisible(true);
	}
}