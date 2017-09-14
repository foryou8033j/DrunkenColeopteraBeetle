package Bettle.model.maps;

import java.awt.Color;

/**
 * �и� �Ǿ��� �� ���� ������ ���� Ŭ����
 * @author Jeongsam
 *
 */
public class MapData {

	//private boolean beetleVisitCell[][];
	
	//���� ũ�� ����
	private final int B_WIDTH;
	private final int B_HEIGHT;
	
	private int countOfNoVisitCells;
	
	MapDraw mapDraw = null;
	
	public MapData(MapDraw mapDraw, int mapWidth, int mapHeight) {
		
		this.B_WIDTH = mapWidth;
		this.B_HEIGHT = mapHeight;
		
		countOfNoVisitCells = mapWidth * mapHeight;
		
		
		this.mapDraw = mapDraw;
		
		//beetleVisitCell = new boolean[B_WIDTH+1][B_HEIGHT+1];
	}
	
	/**
	 * �ش� ���� �湮 ���θ� <b>true</b> �� �����Ѵ�.
	 * @param x ��ǥ
	 * @param y ��ǥ
	 */
	//TODO ��ǥ �� -1 ���� �߻�, �浹 ���� �ҷ� ���� 170910:114100
	public synchronized void setThisCellVisit(int x, int y) {
		
		try{
			
			if(mapDraw.getThisMap(x, y).setThisCellVisit(x, y))
				countOfNoVisitCells--;
			
			//beetleVisitCell[x][y] = true;
			
		}catch (Exception e) {
			//ignore
			if (x < 0 || y <0 ) 
				System.out.println(x + " " + y);
		}
		
		
	}
	
	public int getNoVisitCell() {
		
		return countOfNoVisitCells;
	}
	
	/**
	 * �ش� ���� �湮 ���θ� ��ȯ�Ѵ�.
	 * @param x ��ǥ
	 * @param y ��ǥ
	 * @return {@link boolean}
	 */
	//TODO ���� ���� �ҿ�, ���� �ʿ�
	public boolean isVisitCell(int x, int y) {
		
		try{
			return mapDraw.getThisMap(x, y).isThisCellVisit(x, y);
		}catch (Exception e){
			return false;
		}
		
		
	}
	
}
