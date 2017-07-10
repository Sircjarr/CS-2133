import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BPanel extends JPanel{
	
	private JTextField tf;
	
	public BPanel() {
		setLayout(new BorderLayout());
		tf = new JTextField();
		tf.addKeyListener(new KeyBoardHandler());
		add(tf);
	}
	
	private class KeyBoardHandler extends KeyAdapter
		implements KeyListener {
			
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					BFrame.panel2.clearTa();
					
					String input = tf.getText();
					BModel.extractHost(input);
					BModel.extractFilePath(input, BModel.host);
					
					BModel.loadWebPage(BModel.host, BModel.filePath);
				}
			}
		}
}