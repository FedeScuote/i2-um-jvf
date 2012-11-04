package util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

//First technique: JComponent
public class CustomGlassPane extends JPanel {
	@Override
	protected void paintComponent(Graphics g) {
		// gets the current clipping area
		Rectangle clip = g.getClipBounds();
		Graphics2D g2 = (Graphics2D)g;
		// sets a 65% translucent composite
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				0.7f));
		// fills the background
		g2.setColor(Color.white);
		g2.fillRect(clip.x, clip.y, clip.width, clip.height);
	}

}