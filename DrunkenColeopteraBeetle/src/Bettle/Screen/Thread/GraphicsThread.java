package Bettle.Screen.Thread;

import java.awt.Graphics;

/**
 * @deprecated
 * @author Jeongsam
 *
 */
public class GraphicsThread extends Thread{

	Graphics g = null;
	
	public GraphicsThread(Graphics g) {
		this.g = g;
	}

	
	
}
