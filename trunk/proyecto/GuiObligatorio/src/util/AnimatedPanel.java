package util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimatedPanel extends JPanel implements ActionListener {

	Color startColor = Color.BLUE; // where we start
	Color endColor = Color.BLACK; // where we end
	Color currentColor = startColor;
	int animationDuration = 1000; // each animation will take 2 seconds
	long animStartTime;

	public AnimatedPanel() {
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
		// Creates a picture with an alpha channel
		// This image could be cached for better performance
		BufferedImage image = new BufferedImage(getWidth(), getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = image.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		// Sets the composite
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				0.7f));
		g2.setColor(getBackground());
        g2.setColor(currentColor);
		g2.fillOval(4 + (getWidth() / 4), 4, getWidth() / 2, getHeight() - 8);
		g2.dispose();
		// Draws the image onto the screen
		g.drawImage(image, 0, 0, null);
		this.paintChildren(g);
	}


}
