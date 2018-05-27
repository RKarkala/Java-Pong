import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StartScreen extends JPanel{	
	final private Object[] options = {"1 Player", "2 Player"};
	private GameWindow window;
	public StartScreen(int height, int width, GameWindow window) {
		this.window = window;
		setLayout(null);
		setBackground(Color.BLACK);
		JButton play = new JButton("Play");
		play.setBounds((width-150)/2, height*1/2, 150, 50);
		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showOptionDialog(window, "", "",
															JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
														null, options, options[0]);
				//2 Player
				
				removeAll();
				revalidate();
				repaint();
				GamePanel gamePanel = new GamePanel(window, choice == 0 ? true : false);
				window.setContentPane(gamePanel);
				System.out.println(choice);
				System.out.println("Play");
			}
		}); 
		add(play);
		repaint();

	}
	 @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
			Font myFont = new Font("Courier New", 1, 150);
			g.setColor(Color.WHITE);
			g.setFont(myFont);
			FontMetrics fontMetrics = g.getFontMetrics();
			g.drawString("Pong",(window.width-fontMetrics.stringWidth("Pong"))/2, 200);
	    }
}
