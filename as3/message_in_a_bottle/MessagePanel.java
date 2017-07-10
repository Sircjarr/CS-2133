import java.awt.*; //contains Graphics
import javax.swing.*;
public class MessagePanel extends JPanel
{
	int[] polyX = {250, 260, 340, 350};
	int[] polyY = {100, 50, 50, 100};
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g); 
		g.setColor(Color.GREEN);
		g.drawRoundRect(200, 100, 200, 400, 50, 50); // (200, 100), (400, 100), 
		g.drawPolygon(polyX, polyY, 4);
		g.drawRoundRect(250, 40, 100, 10, 25, 25);
		g.drawArc(200, 200, 200, 50, 0, -180);
		g.drawArc(200, 200, 200, 50, 0, 180);
		g.drawLine(200, 200, 400, 200);
		g.drawLine(200, 250, 400, 250);
		g.setColor(Color.BLACK);
		g.clearRect(250, 100, 100, 1);
		g.drawString("Sending out an S.O.S", 245, 280);
	}
}