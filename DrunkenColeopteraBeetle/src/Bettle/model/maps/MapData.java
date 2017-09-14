package Bettle.model.maps;

import java.awt.Color;

/**
 * 분리 되어진 각 맵의 데이터 저장 클래스
 * @author Jeongsam
 *
 */
public class MapData {

	//private boolean beetleVisitCell[][];
	
	//보드 크기 정보
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
	 * 해당 셀의 방문 여부를 <b>true</b> 로 수정한다.
	 * @param x 좌표
	 * @param y 좌표
	 */
	//TODO 좌표 값 -1 오류 발생, 충돌 연산 불량 추정 170910:114100
	public void setThisCellVisit(int x, int y) {
		
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
	 * 해당 셀의 방문 여부를 반환한다.
	 * @param x 좌표
	 * @param y 좌표
	 * @return {@link boolean}
	 */
	//TODO 많은 연산 소요, 수정 필요
	public boolean isVisitCell(int x, int y) {
		
		try{
			return mapDraw.getThisMap(x, y).isThisCellVisit(x, y);
		}catch (Exception e){
			return false;
		}
		
		
	}
	
}
