import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JOptionPane;

public class Ball {
	private int radius = 15;
	private int x;
	private int y;
	private int vx;
	private int vy;
	private GamePanel panel;
	private int width;
	private int height;
	public Ball(GamePanel panel, int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.vx = 2;
		this.vy = -2;
		this.width = width;
		this.height = height;
		this.panel = panel;
		
	}
	public Ball(GamePanel panel, int x, int y, int width, int height, int vx, int vy) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.width = width;
		this.height = height;
		this.panel = panel;
	}
	public void drawPaddle(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, radius, radius);
	}
	
	public void update() {
		y += vy;
		x += vx;
	}
	public void checkCollision() {
		if(panel.player1.getRect().intersects(getRect()) || panel.player2.getRect().intersects(getRect())) {
			vx *= -1;
				
		}
	}
	public void checkBounds() {
		if(y <= 0 || y >= height-radius-30) {
			vy*=-1;
		}
		if(x <= 0) {
			panel.player2.increaseScore();
			x = width/2;
			y = height/2;
			vx = 2;
			vy = 2;
		}
		if(x >= width-radius) {
			panel.player1.increaseScore();
			x = width/2;
			y = height/2;
			vx = -2;
			vy = -2;
		}
	}
	private Rectangle getRect() {
		return new Rectangle(x, y, radius, radius);
	}
	public int getY() {
		return y;
	}

}
