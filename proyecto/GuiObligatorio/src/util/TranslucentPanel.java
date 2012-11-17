package util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class TranslucentPanel extends JPanel {

	public TranslucentPanel() {
		super();
		this.setOpaque(false);
		Color translucent = new Color(0, 0, 0, 0.5f);
		this.setBackground(translucent);
	}

	public TranslucentPanel(Color color) {
		super();
		this.setOpaque(false);
		Color translucent = new Color(color.getRed(), color.getGreen(), color
				.getBlue(), 128);
		this.setBackground(translucent);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(getBackground());
		Rectangle r = g.getClipBounds();
		g.fillRect(r.x, r.y, r.width, r.height);
	}

}
