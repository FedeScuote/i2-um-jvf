package guiObligatorio;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;

public class BatallaNavalVentana extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel PanelJugador = null;

	private JPanel PanelContrincante = null;

	private JButton BotonContrincanteA2 = null;

	private JButton BotonContrincanteA3 = null;

	private JButton BotonContrincanteA4 = null;

	private JButton BotonContrincanteA5 = null;

	private JButton BotonContrincanteA6 = null;

	private JButton BotonContrincanteA7 = null;

	private JButton BotonContrincanteA1 = null;

	private JButton BotonContrincanteA8 = null;

	private JButton BotonContrincanteA9 = null;

	private JButton BotonContrincanteA10 = null;

	private JButton BotonContrincanteB1 = null;

	private JButton BotonContrincanteB2 = null;

	private JButton BotonContrincanteB3 = null;

	private JButton BotonContrincanteB4 = null;

	private JButton BotonContrincanteB5 = null;

	private JButton BotonContrincanteB6 = null;

	private JButton BotonContrincanteB7 = null;

	private JButton BotonContrincanteB8 = null;

	private JButton BotonContrincanteB9 = null;

	private JButton BotonContrincanteB10 = null;

	private JButton BotonContrincanteC1 = null;

	private JButton BotonContrincanteC2 = null;

	private JButton BotonContrincanteC3 = null;

	private JButton BotonContrincanteC4 = null;

	private JButton BotonContrincanteC5 = null;

	private JButton BotonContrincanteC6 = null;

	private JButton BotonContrincanteC7 = null;

	private JButton BotonContrincanteC8 = null;

	private JButton BotonContrincanteC9 = null;

	private JButton BotonContrincanteC10 = null;

	private JButton BotonJugadorA1 = null;

	private JButton BotonJugadorA2 = null;

	private JButton BotonJugadorA3 = null;

	private JButton BotonJugadorA4 = null;

	private JButton BotonJugadorA5 = null;

	private JButton BotonJugadorA6 = null;

	private JButton BotonJugadorA7 = null;

	private JButton BotonJugadorA8 = null;

	private JButton BotonJugadorA9 = null;

	private JButton BotonJugadorA10 = null;

	/**
	 * This is the default constructor
	 */
	public BatallaNavalVentana() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(728, 353);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanelJugador(), BorderLayout.WEST);
			jContentPane.add(getPanelContrincante(), BorderLayout.EAST);
		}
		return jContentPane;
	}

	/**
	 * This method initializes PanelJugador
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelJugador() {
		if (PanelJugador == null) {
			GridBagConstraints gridBagConstraints38 = new GridBagConstraints();
			gridBagConstraints38.gridx = 9;
			gridBagConstraints38.gridy = 0;
			GridBagConstraints gridBagConstraints37 = new GridBagConstraints();
			gridBagConstraints37.gridx = 8;
			gridBagConstraints37.gridy = 0;
			GridBagConstraints gridBagConstraints36 = new GridBagConstraints();
			gridBagConstraints36.gridx = 7;
			gridBagConstraints36.gridy = 0;
			GridBagConstraints gridBagConstraints35 = new GridBagConstraints();
			gridBagConstraints35.gridx = 6;
			gridBagConstraints35.gridy = 0;
			GridBagConstraints gridBagConstraints34 = new GridBagConstraints();
			gridBagConstraints34.gridx = 5;
			gridBagConstraints34.gridy = 0;
			GridBagConstraints gridBagConstraints33 = new GridBagConstraints();
			gridBagConstraints33.gridx = 4;
			gridBagConstraints33.gridy = 0;
			GridBagConstraints gridBagConstraints32 = new GridBagConstraints();
			gridBagConstraints32.gridx = 3;
			gridBagConstraints32.gridy = 0;
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.gridx = 2;
			gridBagConstraints31.gridy = 0;
			GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
			gridBagConstraints30.gridx = 1;
			gridBagConstraints30.gridy = 0;
			PanelJugador = new JPanel();
			PanelJugador.setLayout(new GridBagLayout());
			PanelJugador.setEnabled(true);
			PanelJugador.add(getBotonJugadorA1(), new GridBagConstraints());
			PanelJugador.add(getBotonJugadorA2(), gridBagConstraints30);
			PanelJugador.add(getBotonJugadorA3(), gridBagConstraints31);
			PanelJugador.add(getBotonJugadorA4(), gridBagConstraints32);
			PanelJugador.add(getBotonJugadorA5(), gridBagConstraints33);
			PanelJugador.add(getBotonJugadorA6(), gridBagConstraints34);
			PanelJugador.add(getBotonJugadorA7(), gridBagConstraints35);
			PanelJugador.add(getBotonJugadorA8(), gridBagConstraints36);
			PanelJugador.add(getBotonJugadorA9(), gridBagConstraints37);
			PanelJugador.add(getBotonJugadorA10(), gridBagConstraints38);
		}
		return PanelJugador;
	}

	/**
	 * This method initializes PanelContrincante
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanelContrincante() {
		if (PanelContrincante == null) {
			GridBagConstraints gridBagConstraints29 = new GridBagConstraints();
			gridBagConstraints29.gridx = 9;
			gridBagConstraints29.gridy = 3;
			GridBagConstraints gridBagConstraints28 = new GridBagConstraints();
			gridBagConstraints28.gridx = 8;
			gridBagConstraints28.gridy = 3;
			GridBagConstraints gridBagConstraints27 = new GridBagConstraints();
			gridBagConstraints27.gridx = 7;
			gridBagConstraints27.gridy = 3;
			GridBagConstraints gridBagConstraints26 = new GridBagConstraints();
			gridBagConstraints26.gridx = 6;
			gridBagConstraints26.gridy = 3;
			GridBagConstraints gridBagConstraints25 = new GridBagConstraints();
			gridBagConstraints25.gridx = 5;
			gridBagConstraints25.gridy = 3;
			GridBagConstraints gridBagConstraints24 = new GridBagConstraints();
			gridBagConstraints24.gridx = 4;
			gridBagConstraints24.gridy = 3;
			GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
			gridBagConstraints23.gridx = 3;
			gridBagConstraints23.gridy = 3;
			GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
			gridBagConstraints22.gridx = 2;
			gridBagConstraints22.gridy = 3;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 1;
			gridBagConstraints21.gridy = 3;
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.gridx = 0;
			gridBagConstraints20.gridy = 3;
			GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
			gridBagConstraints19.gridx = 9;
			gridBagConstraints19.gridy = 2;
			GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
			gridBagConstraints18.gridx = 8;
			gridBagConstraints18.gridy = 2;
			GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
			gridBagConstraints17.gridx = 7;
			gridBagConstraints17.gridy = 2;
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.gridx = 6;
			gridBagConstraints16.gridy = 2;
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.gridx = 5;
			gridBagConstraints15.gridy = 2;
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.gridx = 4;
			gridBagConstraints14.gridy = 2;
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.gridx = 3;
			gridBagConstraints13.gridy = 2;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridx = 2;
			gridBagConstraints12.gridy = 2;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.gridy = 2;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 2;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 9;
			gridBagConstraints10.gridy = 1;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 8;
			gridBagConstraints9.gridy = 1;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 7;
			gridBagConstraints8.gridy = 1;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 1;
			gridBagConstraints7.gridy = 1;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridy = 1;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 6;
			gridBagConstraints5.gridy = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 5;
			gridBagConstraints4.gridy = 1;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 4;
			gridBagConstraints3.gridy = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 3;
			gridBagConstraints2.gridy = 1;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 2;
			gridBagConstraints11.gridy = 1;
			PanelContrincante = new JPanel();
			PanelContrincante.setLayout(new GridBagLayout());
			PanelContrincante.add(getBotonContrincanteA2(), gridBagConstraints7);
			PanelContrincante.add(getBotonContrincanteA3(), gridBagConstraints11);
			PanelContrincante.add(getBotonContrincanteA4(), gridBagConstraints2);
			PanelContrincante.add(getBotonContrincanteA5(), gridBagConstraints3);
			PanelContrincante.add(getBotonContrincanteA6(), gridBagConstraints4);
			PanelContrincante.add(getBotonContrincanteA7(), gridBagConstraints5);
			PanelContrincante.add(getBotonContrincanteA1(), gridBagConstraints6);
			PanelContrincante.add(getBotonContrincanteA8(), gridBagConstraints8);
			PanelContrincante.add(getBotonContrincanteA9(), gridBagConstraints9);
			PanelContrincante.add(getBotonContrincanteA10(), gridBagConstraints10);
			PanelContrincante.add(getBotonContrincanteB1(), gridBagConstraints);
			PanelContrincante.add(getBotonContrincanteB2(), gridBagConstraints1);
			PanelContrincante.add(getBotonContrincanteB3(), gridBagConstraints12);
			PanelContrincante.add(getBotonContrincanteB4(), gridBagConstraints13);
			PanelContrincante.add(getBotonContrincanteB5(), gridBagConstraints14);
			PanelContrincante.add(getBotonContrincanteB6(), gridBagConstraints15);
			PanelContrincante.add(getBotonContrincanteB7(), gridBagConstraints16);
			PanelContrincante.add(getBotonContrincanteB8(), gridBagConstraints17);
			PanelContrincante.add(getBotonContrincanteB9(), gridBagConstraints18);
			PanelContrincante.add(getBotonContrincanteB10(), gridBagConstraints19);
			PanelContrincante.add(getBotonContrincanteC1(), gridBagConstraints20);
			PanelContrincante.add(getBotonContrincanteC2(), gridBagConstraints21);
			PanelContrincante.add(getBotonContrincanteC3(), gridBagConstraints22);
			PanelContrincante.add(getBotonContrincanteC4(), gridBagConstraints23);
			PanelContrincante.add(getBotonContrincanteC5(), gridBagConstraints24);
			PanelContrincante.add(getBotonContrincanteC6(), gridBagConstraints25);
			PanelContrincante.add(getBotonContrincanteC7(), gridBagConstraints26);
			PanelContrincante.add(getBotonContrincanteC8(), gridBagConstraints27);
			PanelContrincante.add(getBotonContrincanteC9(), gridBagConstraints28);
			PanelContrincante.add(getBotonContrincanteC10(), gridBagConstraints29);
		}
		return PanelContrincante;
	}

	/**
	 * This method initializes BotonContrincanteA2
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteA2() {
		if (BotonContrincanteA2 == null) {
			BotonContrincanteA2 = new JButton();
		}
		return BotonContrincanteA2;
	}

	/**
	 * This method initializes BotonContrincanteA3
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteA3() {
		if (BotonContrincanteA3 == null) {
			BotonContrincanteA3 = new JButton();
		}
		return BotonContrincanteA3;
	}

	/**
	 * This method initializes BotonContrincanteA4
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteA4() {
		if (BotonContrincanteA4 == null) {
			BotonContrincanteA4 = new JButton();
		}
		return BotonContrincanteA4;
	}

	/**
	 * This method initializes BotonContrincanteA5
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteA5() {
		if (BotonContrincanteA5 == null) {
			BotonContrincanteA5 = new JButton();
		}
		return BotonContrincanteA5;
	}

	/**
	 * This method initializes BotonContrincanteA6
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteA6() {
		if (BotonContrincanteA6 == null) {
			BotonContrincanteA6 = new JButton();
		}
		return BotonContrincanteA6;
	}

	/**
	 * This method initializes BotonContrincanteA7
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteA7() {
		if (BotonContrincanteA7 == null) {
			BotonContrincanteA7 = new JButton();
		}
		return BotonContrincanteA7;
	}

	/**
	 * This method initializes BotonContrincanteA1
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteA1() {
		if (BotonContrincanteA1 == null) {
			BotonContrincanteA1 = new JButton();
		}
		return BotonContrincanteA1;
	}

	/**
	 * This method initializes BotonContrincanteA8
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteA8() {
		if (BotonContrincanteA8 == null) {
			BotonContrincanteA8 = new JButton();
		}
		return BotonContrincanteA8;
	}

	/**
	 * This method initializes BotonContrincanteA9
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteA9() {
		if (BotonContrincanteA9 == null) {
			BotonContrincanteA9 = new JButton();
		}
		return BotonContrincanteA9;
	}

	/**
	 * This method initializes BotonContrincanteA10
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteA10() {
		if (BotonContrincanteA10 == null) {
			BotonContrincanteA10 = new JButton();
		}
		return BotonContrincanteA10;
	}

	/**
	 * This method initializes BotonContrincanteB1
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteB1() {
		if (BotonContrincanteB1 == null) {
			BotonContrincanteB1 = new JButton();
		}
		return BotonContrincanteB1;
	}

	/**
	 * This method initializes BotonContrincanteB2
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteB2() {
		if (BotonContrincanteB2 == null) {
			BotonContrincanteB2 = new JButton();
		}
		return BotonContrincanteB2;
	}

	/**
	 * This method initializes BotonContrincanteB3
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteB3() {
		if (BotonContrincanteB3 == null) {
			BotonContrincanteB3 = new JButton();
		}
		return BotonContrincanteB3;
	}

	/**
	 * This method initializes BotonContrincanteB4
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteB4() {
		if (BotonContrincanteB4 == null) {
			BotonContrincanteB4 = new JButton();
		}
		return BotonContrincanteB4;
	}

	/**
	 * This method initializes BotonContrincanteB5
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteB5() {
		if (BotonContrincanteB5 == null) {
			BotonContrincanteB5 = new JButton();
		}
		return BotonContrincanteB5;
	}

	/**
	 * This method initializes BotonContrincanteB6
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteB6() {
		if (BotonContrincanteB6 == null) {
			BotonContrincanteB6 = new JButton();
		}
		return BotonContrincanteB6;
	}

	/**
	 * This method initializes BotonContrincanteB7
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteB7() {
		if (BotonContrincanteB7 == null) {
			BotonContrincanteB7 = new JButton();
		}
		return BotonContrincanteB7;
	}

	/**
	 * This method initializes BotonContrincanteB8
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteB8() {
		if (BotonContrincanteB8 == null) {
			BotonContrincanteB8 = new JButton();
		}
		return BotonContrincanteB8;
	}

	/**
	 * This method initializes BotonContrincanteB9
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteB9() {
		if (BotonContrincanteB9 == null) {
			BotonContrincanteB9 = new JButton();
		}
		return BotonContrincanteB9;
	}

	/**
	 * This method initializes BotonContrincanteB10
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteB10() {
		if (BotonContrincanteB10 == null) {
			BotonContrincanteB10 = new JButton();
		}
		return BotonContrincanteB10;
	}

	/**
	 * This method initializes BotonContrincanteC1
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteC1() {
		if (BotonContrincanteC1 == null) {
			BotonContrincanteC1 = new JButton();
		}
		return BotonContrincanteC1;
	}

	/**
	 * This method initializes BotonContrincanteC2
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteC2() {
		if (BotonContrincanteC2 == null) {
			BotonContrincanteC2 = new JButton();
		}
		return BotonContrincanteC2;
	}

	/**
	 * This method initializes BotonContrincanteC3
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteC3() {
		if (BotonContrincanteC3 == null) {
			BotonContrincanteC3 = new JButton();
		}
		return BotonContrincanteC3;
	}

	/**
	 * This method initializes BotonContrincanteC4
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteC4() {
		if (BotonContrincanteC4 == null) {
			BotonContrincanteC4 = new JButton();
		}
		return BotonContrincanteC4;
	}

	/**
	 * This method initializes BotonContrincanteC5
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteC5() {
		if (BotonContrincanteC5 == null) {
			BotonContrincanteC5 = new JButton();
		}
		return BotonContrincanteC5;
	}

	/**
	 * This method initializes BotonContrincanteC6
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteC6() {
		if (BotonContrincanteC6 == null) {
			BotonContrincanteC6 = new JButton();
		}
		return BotonContrincanteC6;
	}

	/**
	 * This method initializes BotonContrincanteC7
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteC7() {
		if (BotonContrincanteC7 == null) {
			BotonContrincanteC7 = new JButton();
		}
		return BotonContrincanteC7;
	}

	/**
	 * This method initializes BotonContrincanteC8
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteC8() {
		if (BotonContrincanteC8 == null) {
			BotonContrincanteC8 = new JButton();
		}
		return BotonContrincanteC8;
	}

	/**
	 * This method initializes BotonContrincanteC9
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteC9() {
		if (BotonContrincanteC9 == null) {
			BotonContrincanteC9 = new JButton();
		}
		return BotonContrincanteC9;
	}

	/**
	 * This method initializes BotonContrincanteC10
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonContrincanteC10() {
		if (BotonContrincanteC10 == null) {
			BotonContrincanteC10 = new JButton();
		}
		return BotonContrincanteC10;
	}

	/**
	 * This method initializes BotonJugadorA1
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonJugadorA1() {
		if (BotonJugadorA1 == null) {
			BotonJugadorA1 = new JButton();
			BotonJugadorA1.setEnabled(true);
			BotonJugadorA1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return BotonJugadorA1;
	}

	/**
	 * This method initializes BotonJugadorA2
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonJugadorA2() {
		if (BotonJugadorA2 == null) {
			BotonJugadorA2 = new JButton();
		}
		return BotonJugadorA2;
	}

	/**
	 * This method initializes BotonJugadorA3
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonJugadorA3() {
		if (BotonJugadorA3 == null) {
			BotonJugadorA3 = new JButton();
		}
		return BotonJugadorA3;
	}

	/**
	 * This method initializes BotonJugadorA4
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonJugadorA4() {
		if (BotonJugadorA4 == null) {
			BotonJugadorA4 = new JButton();
		}
		return BotonJugadorA4;
	}

	/**
	 * This method initializes BotonJugadorA5
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonJugadorA5() {
		if (BotonJugadorA5 == null) {
			BotonJugadorA5 = new JButton();
		}
		return BotonJugadorA5;
	}

	/**
	 * This method initializes BotonJugadorA6
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonJugadorA6() {
		if (BotonJugadorA6 == null) {
			BotonJugadorA6 = new JButton();
		}
		return BotonJugadorA6;
	}

	/**
	 * This method initializes BotonJugadorA7
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonJugadorA7() {
		if (BotonJugadorA7 == null) {
			BotonJugadorA7 = new JButton();
		}
		return BotonJugadorA7;
	}

	/**
	 * This method initializes BotonJugadorA8
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonJugadorA8() {
		if (BotonJugadorA8 == null) {
			BotonJugadorA8 = new JButton();
		}
		return BotonJugadorA8;
	}

	/**
	 * This method initializes BotonJugadorA9
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonJugadorA9() {
		if (BotonJugadorA9 == null) {
			BotonJugadorA9 = new JButton();
		}
		return BotonJugadorA9;
	}

	/**
	 * This method initializes BotonJugadorA10
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBotonJugadorA10() {
		if (BotonJugadorA10 == null) {
			BotonJugadorA10 = new JButton();
		}
		return BotonJugadorA10;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
