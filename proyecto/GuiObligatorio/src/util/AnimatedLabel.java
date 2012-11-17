package util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.Timer;

public class AnimatedLabel extends JLabel implements ActionListener {


	Color startColor = Color.BLUE; // where we start
	Color endColor = Color.RED; // where we end
	Color currentColor = startColor;
	int animationDuration = 300;
	long animStartTime;

	public AnimatedLabel() {
		Timer timer = new Timer(10, this);
		// initial delay while window gets set up
		timer.setInitialDelay(1000);
		animStartTime = 1000 + System.nanoTime() / 1000000;
		timer.start();
	}

	public void actionPerformed(ActionEvent ae) {
		// calculate elapsed fraction of animation
		long currentTime = System.nanoTime() / 1000000;
		long totalTime = currentTime - animStartTime;
		if (totalTime > animationDuration) {
			animStartTime = currentTime;
		}
		float fraction = (float) totalTime / animationDuration;
		fraction = Math.min(1.0f, fraction);
		// interpolate between start and end colors with current fraction
		int red = (int) (fraction * endColor.getRed() + (1 - fraction)
				* startColor.getRed());
		int green = (int) (fraction * endColor.getGreen() + (1 - fraction)
				* startColor.getGreen());
		int blue = (int) (fraction * endColor.getBlue() + (1 - fraction)
				* startColor.getBlue());
		// set our new color appropriately
		currentColor = new Color(red, green, blue);
		// force a repaint to display our oval with its new color
		repaint();
	}
	@Override
	protected void paintComponent(Graphics g) {
		Font newFont = g.getFont().deriveFont(Font.BOLD, 32f);
		g.setFont(newFont);
		g.setColor(currentColor);
		g.drawString(this.getText(), this.getWidth()/3, this.getHeight()/2);
	}

}
