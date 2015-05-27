import javax.swing.JComponent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

/**
 * This class represents a pipe in the Flappy Bird game.
 * @author Kesav Viswanadha
 * @contributor Ofek Gila
 * @version 1.8
 * @lastedited May 24, 2015
*/

public class FlappyPipe implements ActionListener {

	private double x;
	private double y; // (x, y) represents the bottom left corner of the pipe
	private double velocity;
	private final int WIDTH;
	private Color color;
	private Timer oscillator;
	private double oscillation;
	private final int HEIGHT;
	private boolean isInvincible;
	private int count;
	private boolean redding;
	private boolean blueing;
	private boolean greening;
	private boolean reset;
	private int score;

	public FlappyPipe(JComponent comp, double velocity, double x) {
		setColor();
		if (Math.random() < 0.1) isInvincible = true;
		else isInvincible = false;
		WIDTH = comp.getWidth();
		HEIGHT = comp.getHeight();
		this.x = x;
		y = Math.random() * (comp.getHeight() - 800) + 400;
		this.velocity = velocity;
		oscillation = Math.random() * 5 + 1;
		oscillator = new Timer(20, this);
		oscillator.start();
		if (Math.random() > 0.5) oscillation = 0;
		count = 0;
		if (isInvincible && color.equals(Color.YELLOW)) color = Color.RED;
		if (color.equals(Color.RED)) {
			redding = false;
			greening = true;
			blueing = false;
		}
		else if (color.equals(Color.GREEN)) {
			redding = false;
			greening = false;
			blueing = true;
		}
		else {
			redding = true;
			greening = false;
			blueing = false;
		}
	}

	public void move() {
		x -= velocity;
		count++;
		if (x <= -50) {
			score++;
			reset = true;
			x = WIDTH;
			setColor();
			if (Math.random() < 0.1) isInvincible = true;
			else isInvincible = false;
			if (isInvincible && color.equals(Color.YELLOW)) color = Color.RED;
			if (color.equals(Color.RED)) {
				redding = false;
				greening = true;
				blueing = false;
			}
			else if (color.equals(Color.GREEN)) {
				redding = false;
				greening = false;
				blueing = true;
			}
			else {
				redding = true;
				greening = false;
				blueing = false;
			}
			oscillation = Math.random() * 5 + 1;
			if (Math.random() > 0.5) oscillation = 0;
			if (isInvincible) oscillation = 10;
		}
		else reset = false;
		if (isInvincible)
			fadeColor();
	}

	public void setX(int newX) {
		x = newX;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setOscillation(double osc) {
		oscillation = osc;
	}

	public int getScore() {
		return score;
	}

	public void fadeColor()	{
		if (redding) {
			if (color.getRed() >= 255) {
				redding = false;
				greening = true;
				blueing = false;
			}
			else color = new Color(color.getRed() + 5, 0, color.getBlue() - 5);
		}
		else if (greening) {
			if (color.getGreen() >= 255) {
				greening = false;
				blueing = true;
				redding = false;
			}
			else color = new Color(color.getRed() - 5, color.getGreen() + 5, 0);
		}
		else if (blueing) {
			if (color.getBlue() >= 255) {
				blueing = false;
				redding = true;
				greening = false;
			}
			else color = new Color(0, color.getGreen() - 5, color.getBlue() + 5);
		}
	}

	public void setColor() {
		switch ((int)(Math.random() * 4)) {
			case 0: color = Color.RED; break;
			case 1: color = Color.BLUE; break;
			case 2: color = Color.YELLOW; break;
			case 3: color = Color.GREEN; break;
		}
	}

	public void actionPerformed(ActionEvent e) {
		y -= oscillation;
		if (y < 400 || y > HEIGHT - 400) {
			oscillation *= -1;
		}
	}

	public int getX() {
		return (int)(x + 0.5);
	}

	public int getY() {
		return (int)(y + 0.5);
	}

	public void incVelocity(double amt) {
		velocity += amt;
	}

	public void setVelocity(double vel)	{
		velocity = vel;
	}

	public double getVelocity() {
		return velocity;
	}

	public Color getColor() {
		return color;
	}

	public boolean isInvincible()	{
		return isInvincible;
	}

	public boolean isReset()	{
		return reset;
	}
}