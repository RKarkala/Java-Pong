import java.awt.Color;

import javax.swing.JFrame;

public class GameWindow extends JFrame{

	final int height = 600;
	final int width = 800;

	public GameWindow() {
		setSize(width, height);
		setTitle("Pong");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		StartScreen startScreen = new StartScreen(height, width, this);
		setBackground(Color.BLACK);
		add(startScreen);
		setVisible(true);

		
	}
	public static void main(String[] args) {
		new GameWindow();
	}


}

