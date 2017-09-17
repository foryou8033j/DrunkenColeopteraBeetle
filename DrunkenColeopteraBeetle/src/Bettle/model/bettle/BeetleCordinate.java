package Bettle.model.bettle;

import java.util.Random;

/**
 * 딱정벌레의 좌표 정보를 관리한다.
 * @author Jeongsam
 *
 */
public class BeetleCordinate{

	//딱정벌레의 좌표
	private int x;
	private int y;
	
	
	/**
	 * 딱정벌레의 좌표 정보를 초기화한다.
	 * @param width {@link int}
	 * @param height {@link int}
	 */
	public BeetleCordinate(int width, int height) {
		//딱정벌레의 초기 좌표를 랜덤하게 지정한다.
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
	 * 딱정벌레의 X좌표를 지정한다.
	 * @param x 좌표
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * 딱정벌레의 Y좌표를 지정한다.
	 * @param y 좌표
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * 딱정벌레의 좌표를 지정한다.
	 * @param x 좌표
	 * @param y 좌표
	 */
	public void setCordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 딱정벌레의 X 좌표를 반환한다.
	 * @return {@link int}
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * 딱정벌레의 Y 좌표를 반환한다.
	 * @return ({@link int}
	 */
	public int getY() {
		return y;
	}
	
}
