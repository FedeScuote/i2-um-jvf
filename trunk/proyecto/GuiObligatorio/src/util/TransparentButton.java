package util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TransparentButton extends JButton {

	private Image img;
	private Boolean clicked;

	public Boolean getClicked() {
		return clicked;
	}

	public void setClicked(Boolean clicked) {
		this.clicked = clicked;
		this.repaint();
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public TransparentButton(String img) {
		this(new ImageIcon(img).getImage());
	}

	public TransparentButton(Image img) {
		setOpaque(false);
		clicked = false;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		this.img = img;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// Creates a picture with an alpha channel
		// This image could be cached for better performance
		// gets the current clipping area
		Graphics2D g2 = (Graphics2D) g;
		Graphics2D g3 = (Graphics2D) g;
		Graphics2D g4 = (Graphics2D) g;
		g4.setColor(Color.RED);
		if(clicked==false){
		g3.setColor(Color.black);
		}
		if(clicked==true){
			g3.setColor(Color.white);
		}

		//		 fills the background
		g3.fill3DRect(0, 0, this.getWidth(), this.getHeight(), true);
		//		 sets a 50% translucent composite
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				0.5f));


		g2.setClip(0, 0, this.getWidth(), this.getHeight());
		//		Set Font
		Font newFont = g4.getFont().deriveFont(Font.BOLD, 32f);
		g4.setFont(newFont);
		g4.setClip(0, 0, this.getWidth(), this.getHeight());
		//DRAW
		g2.drawImage(img, 0, 0, null);

		g4.drawString("HOLA",  0, 0);
		g2.dispose();
		g3.dispose();
		g4.dispose();
	}

}
