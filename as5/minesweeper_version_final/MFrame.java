import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;

public class MFrame extends JFrame {
	private CounterPanel c;
	MPanel panel; 
	
	private JMenuBar menu;
	private JMenu file;
	private JMenuItem newGame;
	private JMenuItem save;
	private JMenuItem load;
	private JMenuItem quit;
	
	private JMenuItem easy;
	private JMenuItem normal;
	private JMenuItem hard;
	
	private JFileChooser fc;
	
	public MFrame(Save savedGame) { // detects if loaded file and creates panel based on the loaded object
		setTitle("Minesweeper");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menu = new JMenuBar();
		this.setJMenuBar(menu);
		JMenu file = new JMenu("File"); // JMenu can use MenuListener, but does not in this program. 
		menu.add(file);
		
		newGame = new JMenu("New");
		
		save = new JMenuItem("Save");
		load = new JMenuItem("Load");
		quit = new JMenuItem("Quit");
		newGame.addActionListener(new MenuItemHandler()); //JMenuItems need ActionListeners
		save.addActionListener(new MenuItemHandler());
		load.addActionListener(new MenuItemHandler());
		quit.addActionListener(new MenuItemHandler());
		file.add(newGame);
		file.add(save);
		file.add(load);
		file.add(quit);
		
		easy = new JMenuItem("Easy");
		normal = new JMenuItem("Normal");
		hard = new JMenuItem("Hard");
		easy.addActionListener(new MenuItemHandler());
		normal.addActionListener(new MenuItemHandler());
		hard.addActionListener(new MenuItemHandler());
		newGame.add(easy);
		newGame.add(normal);
		newGame.add(hard);
		
		// Add panels, Determine if MPanel should be fresh or loaded
		if (savedGame != null) {
			panel = new MPanel(savedGame);
			add(panel, BorderLayout.CENTER);
		}
		else {
			panel = new MPanel();
			add(panel, BorderLayout.CENTER);
		}
        c = new CounterPanel(); 
		add(c, BorderLayout.NORTH);
		
		setVisible(true);
		setResizable(false);
		
		// Get button dimensions for graphics
		MModel.buttonDim = MPanel.buttonGrid[0][0].getWidth();
		MModel.setDefaultIm(MPanel.buttonGrid);
		
		// Buttons are set as revealed or flagged according to save file data
		if (savedGame != null) { 
			MModel.setSavedCells();
		}
	}
	
	private class MenuItemHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == easy) {
				dispose(); // disposes of current resources
			    MModel.probIsMine = .04;
				MModel.gameWon = false;
				new MFrame(null);
			}
			else if (e.getSource() == normal) {
				dispose();
			    MModel.probIsMine = .10;
				MModel.gameWon = false;
				new MFrame(null);
			}
			else if (e.getSource() == hard) {
				dispose();
			    MModel.probIsMine = .20;
				MModel.gameWon = false;
				new MFrame(null);
			}
			else if (e.getSource() == save) {
				fc = new JFileChooser();
				int response = fc.showSaveDialog(null);
				if (response == JFileChooser.APPROVE_OPTION) { // If user pressed "Save"...
					try {
						ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fc.getSelectedFile()));
						MModel model = new MModel();
						Save savedGame = new Save(model);
						out.writeObject(savedGame);
						out.flush();
						out.close();
					}
					catch(IOException ex) {
						ex.printStackTrace();
					}
				}
				else { }
			}
			else if (e.getSource() == load) {
				fc = new JFileChooser();
				int response = fc.showOpenDialog(null);
				if (response == JFileChooser.APPROVE_OPTION) {
					try {
						ObjectInputStream in = new ObjectInputStream(new FileInputStream(fc.getSelectedFile()));
						Save savedGame = ((Save)in.readObject());
						dispose(); // Had Issues with panel.removeAll(), so MFrame is able to start with the panel from the file. 
						new MFrame(savedGame);
						// using removeAll() would require a validate() here.
					}
					catch(IOException ex) {
						System.out.println("ERROR: could not read save.");
					}
					catch(ClassNotFoundException ex) {
						System.out.println("ERROR: file is not a valid save file.");
					}
				}
				else { }
			}
			else if (e.getSource() == quit) {
				System.exit(0);
			}
		}
	}
}