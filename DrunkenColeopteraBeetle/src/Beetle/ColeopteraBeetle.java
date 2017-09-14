package Beetle;

import javax.swing.SwingUtilities;

import Bettle.Screen.Frame;

public class ColeopteraBeetle {

	
	private static float sppedBettle = 0.1f;
	
	
	public ColeopteraBeetle() {
		
		
		
	}
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				new Frame();
				
			}
		});
	}
	
	
}
