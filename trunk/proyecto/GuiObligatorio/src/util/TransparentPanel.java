package util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class TransparentPanel extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
	// Creates a picture with an alpha channel
	// This image could be cached for better performance
	BufferedImage image = new BufferedImage(getWidth(),
	getHeight(), BufferedImage.TYPE_INT_ARGB);
	Graphics2D g2 = image.createGraphics();
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	RenderingHints.VALUE_ANTIALIAS_ON);
	// Sets the composite
	g2.setComposite(AlphaComposite.getInstance(
			AlphaComposite.SRC_OVER, 0.7f));
	g2.setColor(Color.black);
	g2.fillOval(4 + (getWidth() / 4), 4,
			getWidth() / 2, getHeight() - 8);
	g2.dispose();
	// Draws the image onto the screen
	g.drawImage(image, 0, 0, null);
	}
	@Override
	public void paint(Graphics g){
		this.paintComponent(g);
		this.paintChildren(g);
	}

}
