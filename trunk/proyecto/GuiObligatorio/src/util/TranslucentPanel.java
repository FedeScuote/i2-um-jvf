package util;

import java.awt.Color;

import javax.swing.JPanel;

public class TranslucentPanel extends JPanel {

	public TranslucentPanel(){
		super();
		Color translucent = new Color(0,0,0,0.5f);
		this.setBackground(translucent);
	}

}
