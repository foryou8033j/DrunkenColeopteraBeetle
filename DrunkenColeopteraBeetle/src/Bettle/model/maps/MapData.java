package Bettle.model.maps;

import java.awt.Color;

import javax.swing.JOptionPane;

/**
 * 분리 되어진 각 맵의 데이터 저장 클래스
 * @author Jeongsam
 *
 */
public class MapData {
	
	private int countOfNoVisitCells;
	
	private MapDataModel models[][];
	
	private final int B_WIDTH;
	private final int B_HEIGHT;
	
	public MapData(int mapWidth, int mapHeight) {
		
		B_WIDTH = mapWidth;
		B_HEIGHT = mapHeight;
		
		//방문 하지 않은 셀의 초기값을 설정한다.
		countOfNoVisitCells = B_WIDTH * B_HEIGHT;
		
		this.models = new MapCompartmentDesigner().createMaps(mapWidth, mapHeight);
	}
	
	/**
	 * 해당 셀의 방문 여부를 <b>true</b> 로 수정한다.
	 * @param x 좌표
	 * @param y 좌표
	 */
	//TODO 좌표 값 -1 오류 발생, 충돌 연산 불량 추정 170910:114100
	public synchronized void setThisCellVisit(int x, int y) {
		
		try{
			
			if(getThisMap(x, y).setThisCellVisit(x, y))
				countOfNoVisitCells--;
			
			//beetleVisitCell[x][y] = true;
			
		}catch (Exception e) {
			//ignore
			if (x < 0 || y <0 ) 
				System.out.println(x + " " + y);
		}
		
		
	}
	
	/**
	 * 방문하지 않은 셀의 갯수 값을 반환한다.
	 * @return
	 */
	public int getNoVisitCell() {
		return countOfNoVisitCells;
	}
	
	/**
	 * 해당 셀의 방문 여부를 반환한다.
	 * @param x 좌표
	 * @param y 좌표
	 * @return {@link boolean}
	 */
	public boolean isVisitCell(int x, int y) {
		
		try{
			return getThisMap(x, y).isThisCellVisit(x, y);
		}catch (Exception e){
			return false;
		}
		
		
	}
	
public MapDataModel getThisMap(int x, int y) {
		
		int boardX = 0;
		int boardY = 0;
		
		try{
			
			//x 좌표 보정
			if(x < 0)
				x = 0;
			if(x >= B_WIDTH)
				x = B_WIDTH - 1;
			
			//y 좌표 보정
			if(y < 0)
				y = 0;
			if(y >= B_HEIGHT)
				y = B_HEIGHT - 1;
			
			//현재 좌표에 맞는 MapModel 을 찾는다.
			if(x > MapCompartmentDesigner.DRAW_MAP_SIZE)
				boardX = x / MapCompartmentDesigner.DRAW_MAP_SIZE;
			if(y > MapCompartmentDesigner.DRAW_MAP_SIZE)
				boardY = y / MapCompartmentDesigner.DRAW_MAP_SIZE;
			
			return models[boardX][boardY];
			
		}catch (Exception e)
		{
			//TODO 170915 좌표 벗어나는 오류 원인 해결 필요
			e.printStackTrace();
			System.out.println(x + " " + y);
			System.out.println(boardX + " " + boardY);
			JOptionPane.showConfirmDialog(null, "오류");
			System.exit(0);
		}
		return models[boardX][boardY];

	}
	
	public MapDataModel[][] getMapModels(){
		return models;
	}
	
}
