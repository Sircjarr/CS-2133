import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RPanel extends JPanel{
	
	JPanel[][] pGrid;
	public JLabel linSpeedCounter;
	public JLabel angSpeedCounter;
	
	public RPanel() {
		pGrid = new JPanel[7][3];
		setLayout(new GridLayout(7, 3));
		for (int i = 0; i < pGrid.length; i ++) {
			for (int x = 0; x < pGrid[0].length; x++){
				pGrid[i][x] = new JPanel(new BorderLayout());
				add(pGrid[i][x]);
			}
		}
		
		JButton takeoff = new JButton("takeoff");
		takeoff.addActionListener(new ButtonHandler());
		pGrid[0][2].add(takeoff);
		
		JButton land = new JButton("land");
		land.addActionListener(new ButtonHandler());
		pGrid[1][2].add(land);
		
		JButton forward = new JButton("forward");
		forward.addMouseListener(new MouseHandler());
		pGrid[2][1].add(forward);
		
		JButton left = new JButton("left");
		left.addMouseListener(new MouseHandler());
		pGrid[3][0].add(left);
		
		pGrid[3][1].setLayout(new BorderLayout());
		JButton turnLeft = new JButton("turn left");
		turnLeft.addMouseListener(new MouseHandler());
		pGrid[3][1].add(turnLeft, BorderLayout.WEST);
		
		JButton turnRight = new JButton("turn right");
		turnRight.addMouseListener(new MouseHandler());
		pGrid[3][1].add(turnRight, BorderLayout.EAST);
		
		JButton right = new JButton("right");
		right.addMouseListener(new MouseHandler());
		pGrid[3][2].add(right);
		
		JButton backward = new JButton("backward");
		backward.addMouseListener(new MouseHandler());
		pGrid[4][1].add(backward);
		
		JButton up = new JButton("up");
		up.addMouseListener(new MouseHandler());
		pGrid[5][2].add(up);
		
		JButton down = new JButton("down");
		down.addMouseListener(new MouseHandler());
		pGrid[6][2].add(down);
		
		// Speed controls
		// Linear speed
		pGrid[0][1].setLayout(new GridLayout(2, 2));
		pGrid[0][1].setBackground(Color.YELLOW);
		JButton incLin = new JButton("+");
		incLin.addActionListener(new ButtonHandler());
		JLabel linSpeedLab = new JLabel("Linear speed:");
		JButton decLin = new JButton("-");
		decLin.addActionListener(new ButtonHandler());
		linSpeedCounter = new JLabel(RModel.linSpeed);
		linSpeedLab.setHorizontalAlignment(JLabel.CENTER);
		linSpeedCounter.setHorizontalAlignment(JLabel.CENTER);
		pGrid[0][1].add(incLin);
		pGrid[0][1].add(linSpeedLab);
		pGrid[0][1].add(decLin);
		pGrid[0][1].add(linSpeedCounter);
		
		// Angular speed
		pGrid[1][1].setLayout(new GridLayout(2, 2));
		pGrid[1][1].setBackground(Color.CYAN);
		JButton incAng = new JButton("+");
		incAng.addActionListener(new ButtonHandler());
		incAng.setActionCommand("other+");
		JLabel angSpeedLab = new JLabel("Angular speed:");
		JButton decAng = new JButton("-");
		decAng.setActionCommand("other-");
		decAng.addActionListener(new ButtonHandler());
		angSpeedCounter = new JLabel(RModel.angSpeed);
		angSpeedLab.setHorizontalAlignment(JLabel.CENTER);
		angSpeedCounter.setHorizontalAlignment(JLabel.CENTER);
		pGrid[1][1].add(incAng);
		pGrid[1][1].add(angSpeedLab);
		pGrid[1][1].add(decAng);
		pGrid[1][1].add(angSpeedCounter);
	}
	
	// Update speed panels
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		linSpeedCounter.setText(RModel.linSpeed);
		angSpeedCounter.setText(RModel.angSpeed);
		repaint();
	}
	
	// Takeoff, land, and speed listeners
	private class ButtonHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			String button = e.getActionCommand();
			
			if (button.equals("takeoff")) {
				RModel.takeoff();
			}
			else if (button.equals("land")) {
				RModel.land();
			}
			else if (button.equals("+")) { // NOTE: Hovering over these and pressing spacebar adjusts faster.
				RModel.incLinSpeed();
			}
			else if (button.equals("-")) {
				RModel.decLinSpeed();
			}
			else if (button.equals("other+")) {
				RModel.incAngSpeed();
			}
			else if (button.equals("other-")) {
				RModel.decAngSpeed();
			}
		}
	}
	
	// Movement listeners
	private class MouseHandler extends MouseAdapter implements MouseListener {
		
		public void mousePressed(MouseEvent e) {
			
			JButton button = (JButton)(e.getSource());
			
			if (button.getActionCommand().equals("forward")) {
				RModel.forward();
			}
			else if (button.getActionCommand().equals("left")) {
				RModel.left();
			}
			else if (button.getActionCommand().equals("turn left")) {
				RModel.turnLeft();
			}
			else if (button.getActionCommand().equals("turn right")) {
				RModel.turnRight();
			}
			else if (button.getActionCommand().equals("right")) {
				RModel.right();
			}
			else if (button.getActionCommand().equals("backward")) {
				RModel.backward();
			}
			else if (button.getActionCommand().equals("up")) {
				RModel.up();
			}
			else if (button.getActionCommand().equals("down")) {
				RModel.down();
			}
		}
		
		public void mouseReleased(MouseEvent e) { // Any button released will make robot stop
		
			JButton button = (JButton)(e.getSource());
			
			if (!button.getActionCommand().equals(null)) {
				RModel.stop();
			}
		}
	}
}