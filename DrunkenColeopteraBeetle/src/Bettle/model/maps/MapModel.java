package Bettle.model.maps;

import java.awt.Color;

/**
 * ���� ���� ������ ���� Ŭ����
 * @author Jeongsam
 *
 */
public class MapModel {

	private int x;
	private int y;
	private int width;
	private int height;
	
	private boolean visitCells[][];
	
	public MapModel(int x, int y, int width, int height) {
		
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		
		visitCells = new boolean[width+1][height+1];
		
	}
	
	/**
	 * ���� ���� �湮 ó���Ѵ�.
	 * @param x ��ǥ
	 * @param y ��ǥ
	 * @return {@link boolean}
	 */
	public synchronized boolean setThisCellVisit(int x, int y){
		if(isThisCellVisit(x, y))
			return false;
		
		visitCells[x - this.x][y - this.y] = true;
		
		return true;
	}
	
	/**
	 * ���� ���� �湮 ���θ� Ȯ���Ѵ�.
	 * @param x ��ǥ
	 * @param y ��ǥ
	 * @return {@link boolean}
	 */
	public boolean isThisCellVisit(int x, int y){
		
		return visitCells[x - this.x][y - this.y];
		
	}
	
	/**
	 * ����� ���� �� X ��ǥ
	 * @return
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * ����� ���� �� Y ��ǥ
	 * @return
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * ���� ũ�⸦ ��ȯ�Ѵ�.
	 * @return {@link int}
	 */
	public int getwidth() {
		return width;
	}
	
	/**
	 * ���� ũ�⸦ ��ȯ�Ѵ�.
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
