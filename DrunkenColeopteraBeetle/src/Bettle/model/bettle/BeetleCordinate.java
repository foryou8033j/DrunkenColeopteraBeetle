package Bettle.model.bettle;

import java.util.Random;

/**
 * µüÁ¤¹ú·¹ÀÇ ÁÂÇ¥ Á¤º¸¸¦ °ü¸®ÇÑ´Ù.
 * @author Jeongsam
 *
 */
public class BeetleCordinate{

	//µüÁ¤¹ú·¹ÀÇ ÁÂÇ¥
	private int x;
	private int y;
	
	
	/**
	 * µüÁ¤¹ú·¹ÀÇ ÁÂÇ¥ Á¤º¸¸¦ ÃÊ±âÈ­ÇÑ´Ù.
	 * @param width {@link int}
	 * @param height {@link int}
	 */
	public BeetleCordinate(int width, int height) {
		
		//µüÁ¤¹ú·¹ÀÇ ÃÊ±â ÁÂÇ¥¸¦ ·£´ýÇÏ°Ô ÁöÁ¤ÇÑ´Ù.
		x = new Random().nextInt(width);
		y = new Random().nextInt(height);
	}
	
	public void set_X_Plus() {
		

		x++;
	}
	
	public void set_X_Minus() {

		x--;
	}
	
	public void set_Y_Plus() {
		

		y++;
	}
	
	public void set_Y_Minus() {
		

		y--;
	}
	
	/**
	 * µüÁ¤¹ú·¹ÀÇ XÁÂÇ¥¸¦ ÁöÁ¤ÇÑ´Ù.
	 * @param x ÁÂÇ¥
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * µüÁ¤¹ú·¹ÀÇ YÁÂÇ¥¸¦ ÁöÁ¤ÇÑ´Ù.
	 * @param y ÁÂÇ¥
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * µüÁ¤¹ú·¹ÀÇ ÁÂÇ¥¸¦ ÁöÁ¤ÇÑ´Ù.
	 * @param x ÁÂÇ¥
	 * @param y ÁÂÇ¥
	 */
	public void setCordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * µüÁ¤¹ú·¹ÀÇ X ÁÂÇ¥¸¦ ¹ÝÈ¯ÇÑ´Ù.
	 * @return {@link int}
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * µüÁ¤¹ú·¹ÀÇ Y ÁÂÇ¥¸¦ ¹ÝÈ¯ÇÑ´Ù.
	 * @return ({@link int}
	 */
	public int getY() {
		return y;
	}
	
}
