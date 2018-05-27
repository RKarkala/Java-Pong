import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
	public Paddle player1;
	public Paddle player2;
	private Ball ball;
	private HashSet<Integer> keys;
	private int width;
	private int height;
	private GameWindow window;
	private boolean isComputer;
	Font myFont;
	Timer timer = new Timer(5, this);
	boolean first = true;
	private Object options[] = { "Back to Menu", "Play Again" };

	public GamePanel(GameWindow window, boolean computer) {
		System.out.println("In Game Panel");
		setBackground(Color.BLACK);
		keys = new HashSet<>();
		player1 = new Paddle(this, 2, 230, 0);
		player2 = new Paddle(this, 782, 230, 0);
		this.width = window.width;
		this.window = window;
		this.height = window.height;
		this.isComputer = computer;
		this.ball = new Ball(this, window.width / 2, window.height / 2, window.width, window.height);
		myFont = new Font("Courier New", 1, 50);
		timer.start();
		addKeyListener(this);
		setFocusable(true);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.setFont(myFont);
		FontMetrics metrics = g.getFontMetrics();
		String print = player1.getScore() + ":" + player2.getScore();
		g.drawString(print, (width - metrics.stringWidth(print)) / 2, 100);
		player1.drawPaddle(g);
		player2.drawPaddle(g);
		ball.drawPaddle(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 87) {
			keys.add(e.getKeyCode());
		}
		if (e.getKeyCode() == 83) {
			keys.add(e.getKeyCode());
		}
		if (!isComputer) {
			if (e.getKeyCode() == 38) {
				keys.add(e.getKeyCode());
			}
			if (e.getKeyCode() == 40) {
				keys.add(e.getKeyCode());
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println(e.getKeyCode());
		keys.remove(e.getKeyCode());
		System.out.println(keys);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == timer) {
			if (keys.contains(87)) {
				player1.y -= 3;
			}
			if (keys.contains(83)) {
				player1.y += 3;
			}
			if (!isComputer) {
				if (keys.contains(38)) {
					player2.y -= 3;
				}
				if (keys.contains(40)) {
					player2.y += 3;
				}
			}
			else {
				if(ball.getY() > player2.y ) {
					player2.y += 2;
				}else if(ball.getY() < player2.y) {
					player2.y -= 2;
				}
			}
			player1.checkY();
			player2.checkY();
			ball.checkBounds();
			ball.update();
			ball.checkCollision();
			repaint();
			if(first) {
				if(isComputer) {
					JOptionPane.showMessageDialog(window, "W to move up, S to move down. \nFirst to 10 points wins.",
							"How to Play", JOptionPane.INFORMATION_MESSAGE);
					first = false;
				}else {
					JOptionPane.showMessageDialog(window, "Player #1: W to move up, S to move down. \nPlayer #2: "
							+ "Up arrow to move up, Down arrow to move down. \nFirst to 10 points wins.",
							"How to Play", JOptionPane.INFORMATION_MESSAGE);
					first = false;
				}

			}
			if (player1.getScore() >= 10 || player2.getScore() >= 10) {
				timer.stop();
				int choice = JOptionPane.showOptionDialog(window, "",
						"Player #" + (player1.getScore() > player2.getScore() ? "1" : "2" + " wins"),
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if (choice == 0) {
					removeAll();
					revalidate();
					repaint();
					StartScreen startScreen = new StartScreen(window.height, window.width, window);
					window.setContentPane(startScreen);
				}
				if (choice == 1) {
					removeAll();
					revalidate();
					repaint();
					GamePanel gamePanel = new GamePanel(window, isComputer);
					window.setContentPane(gamePanel);
				}
			}
		}

	}

}
