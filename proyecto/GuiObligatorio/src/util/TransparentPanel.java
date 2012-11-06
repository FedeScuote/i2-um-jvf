package util;

import java.awt.Graphics;

import javax.swing.JPanel;

public class TransparentPanel extends JPanel {

	public TransparentPanel(){
		this.setOpaque(false);
	}
	protected void painComponent(Graphics g){
		this.paintChildren(g);
	}
}
