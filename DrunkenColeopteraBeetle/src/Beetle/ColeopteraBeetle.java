package Beetle;

import javax.swing.SwingUtilities;

import Bettle.Screen.RootFrame;

/**
 * 딱정벌레 시뮬레이션을 시작하는 Main 클레스
 * @author Jeongsam
 *
 */
public class ColeopteraBeetle {
	
	
	public static void main(String[] args) {
		
		//Swing Que 안에서 시작한다.
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				//RootFrame을 띄워준다.
				new RootFrame();
				
			}
		});
	}
	
	
}
