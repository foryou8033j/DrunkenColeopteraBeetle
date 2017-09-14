package Beetle;

import javax.swing.SwingUtilities;

import Bettle.Screen.Frame;

/**
 * 딱정벌레 시뮬레이션을 시작하는 Main 클레스
 * @author Jeongsam
 *
 */
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
