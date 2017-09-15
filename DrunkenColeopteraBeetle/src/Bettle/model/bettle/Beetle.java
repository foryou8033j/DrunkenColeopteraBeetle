package Bettle.model.bettle;

import java.awt.Color;
import java.util.Random;

/**
 * 딱정벌레의 속성 및 움직임을 지정한다.
 * @author Jeongsam
 */
public class Beetle extends BeetleCordinate {
	
	public enum Pos{UP, DOWN, LEFT, RIGHT};

	//gui 상에 표현 될 딱정벌레의 색상
	private Color beetleColor;
	
	//방향 지시
	private boolean upDirection;
	private boolean downDirection;
	private boolean leftDirection;
	private boolean rightDirection;
	
	//셀 관련
	private int beetleMoveCount;
	
	//보드 크기 정보
	private final int B_WIDTH;
	private final int B_HEIGHT;
	
	/**
	 * 딱정 벌레의 속성을 초기화한다.
	 * @param color	GUI 상에 표현 될 딱정벌레의 색상
	 * @param mapWidth 맵의 가로 크기
	 * @param mapHeight 맵의 세로 크기
	 */
	public Beetle(Color color, int mapWidth, int mapHeight) {
		
		super(mapWidth, mapHeight);
		
		if(color == null)
			this.beetleColor = Color.RED;
		else
			this.beetleColor = color;
		
		this.B_WIDTH = mapWidth;
		this.B_HEIGHT = mapHeight;

		
		
		beetleMoveCount = 0;
		
		//초기 딱정벌레의 방향을 지정한다.
		makeDirection();
	}
	
	/**
	 * 딱정벌레의 색상을 반환한다.
	 * @return {@link Color}
	 */
	public Color getBeetleColor() {
		return beetleColor;
	}
	
	
	
	/**
	 * 방문 횟수 카운트를 증가시킨다.
	 */
	public void addMoveCount() {
		beetleMoveCount++;
	}
	
	/**
	 * 방문 횟수 카운트를 반환받는다.
	 * @return {@link int}
	 */
	public int getMoveCount() {
		return beetleMoveCount; 
	}
	
	/**
	 * 딱정벌레가 움직일 방향을 랜덤하게 지정한다.
	 */
	public void makeDirection() {
		
		//여기에 랜덤 알고리즘이 적용 되어야한다.
		int randNum = new Random().nextInt(8);
		
		switch(randNum) {
		case 0:
			upDirection = false;
			downDirection = false;
			leftDirection = true;
			rightDirection = false;
			break;
		case 1:
			upDirection = false;
			downDirection = false;
			leftDirection = false;
			rightDirection = true;
			break;
		case 2:
			upDirection = true;
			downDirection = false;
			leftDirection = false;
			rightDirection = false;
			break;
		case 3:
			upDirection = false;
			downDirection = true;
			leftDirection = false;
			rightDirection = false;
			break;
		case 4:
			upDirection = true;
			downDirection = false;
			leftDirection = true;
			rightDirection = false;
			break;
		case 5:
			upDirection = true;
			downDirection = false;
			leftDirection = false;
			rightDirection = true;
			break;
		case 6:
			upDirection = false;
			downDirection = true;
			leftDirection = true;
			rightDirection = false;
			break;
		case 7:
			upDirection = false;
			downDirection = true;
			leftDirection = false;
			rightDirection = true;
			break;
		}
	}
	
	/**
	 * 강제적으로 이동할 방향과 함께 무작위 방향을 지정한다.
	 * @param pos 강제로 이동할 방향
	 */
	public void makeDirection(Pos pos) {
		
		upDirection = false;
		downDirection = false;
		leftDirection = false;
		rightDirection = false;
		
		switch (pos) {
			case UP:
				upDirection = true;
				break;
				
			case DOWN:
				downDirection = true;
				break;
				
			case LEFT:
				leftDirection = true;
				break;
				
			case RIGHT:
				rightDirection = true;
				break;
		}
		
	}
	
	
	/**
	 * 움직일 랜덤 방향을 반환한다.
	 * @return {@link boolean[]<br><br>}
	 * {@code boolean[] = {upDirectiom, downDirection, leftDirection, rightDirection }
	 */
	public boolean isDirection(Pos pos) {
		
		if(pos == Pos.UP)
			return upDirection;
		if(pos == Pos.DOWN)
			return downDirection;
		if(pos == Pos.LEFT)
			return leftDirection;
		if(pos == Pos.RIGHT)
			return rightDirection;
		
		return false;
	}

}
