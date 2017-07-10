import javax.swing.*; //contains JFrame and JPanel
public class MessageFrame extends JFrame
{
	public MessageFrame()
	{
		setTitle("Message in a Bottle"); 
		setSize(600, 600);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MessagePanel panel = new MessagePanel();
		add(panel);
		
		setVisible(true); 
	}
}