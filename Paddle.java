import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class Paddle extends JPanel{
	private final int height = 60;
	private final int width = 10;
	public int x;
	private int score;
	public int y;
	private GamePanel panel;
	public Paddle(GamePanel panel, int x, int y, int score) {
		this.x = x;
		this.y = y;
		this.panel = panel;
		this.score = 0;
		
	}
	public void checkY() {
		if(y <= 0) {
			y = 0;
		}
		if(y >= 510) {
			y = 510;
		}
	}
	public void increaseScore() {
		score++;
	}
	public int getScore() {
		return score;
	}
	public void drawPaddle(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}
	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);
	}
}
