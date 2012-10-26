package test;

import java.awt.AWTException;
import java.awt.Robot;

import junit.framework.TestCase;
import login.LoginVentana;

public class PruebasRobot extends TestCase{
	private Robot r;

	public void setUp(){
		LoginVentana l = new LoginVentana();
		try {
			r = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}


}
