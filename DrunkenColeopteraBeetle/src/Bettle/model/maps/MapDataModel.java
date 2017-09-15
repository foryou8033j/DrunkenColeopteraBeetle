package Bettle.model.maps;

import java.awt.Color;

/**
 * 맵의 세부 데이터 저장 클래스
 * @author Jeongsam
 *
 */
public class MapDataModel {

	private int x;
	private int y;
	private int width;
	private int height;
	
	private boolean visitCells[][];
	
	public MapDataModel(int x, int y, int width, int height) {
		
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		
		visitCells = new boolean[width+1][height+1];
		
	}
	
	/**
	 * 현재 셀을 방문 처리한다.
	 * @param x 좌표
	 * @param y 좌표
	 * @return {@link boolean}
	 */
	public boolean setThisCellVisit(int x, int y){
		
		if(isThisCellVisit(x, y))
			return false;
		
		//여러 딱정이가 한번에 접근하여 카운트를 수정하지 못하도록 동기화한다.
		synchronized (visitCells) {
			visitCells[x - this.x][y - this.y] = true;
		}
		
		return true;
	}
	
	/**
	 * 현재 셀의 방문 여부를 확인한다.
	 * @param x 좌표
	 * @param y 좌표
	 * @return {@link boolean}
	 */
	public boolean isThisCellVisit(int x, int y){
		
		return visitCells[x - this.x][y - this.y];
		
	}
	
	/**
	 * 출력이 시작 될 X 좌표
	 * @return
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * 출력이 시작 될 Y 좌표
	 * @return
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * 가로 크기를 반환한다.
	 * @return {@link int}
	 */
	public int getwidth() {
		return width;
	}
	
	/**
	 * 세로 크기를 반환한다.
	 * @return {@link int}
	 */
	public int getHeight() {
		return height;
	}
	
	public boolean isInThisMap(int x, int y) {
		
		if(x >= this.x && x < this.x + width) {
			if(y >= this.y && y < this.y + height)
				return true;
		}
		
		return false;
	}
	
}
