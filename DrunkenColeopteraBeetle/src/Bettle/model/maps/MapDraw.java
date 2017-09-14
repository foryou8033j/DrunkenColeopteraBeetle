package Bettle.model.maps;

import javax.swing.JOptionPane;

public class MapDraw {
	
	private int DRAW_MAP_SIZE = 50;
	
	private MapModel model[][];
	
	private final int B_WIDTH;
	private final int B_HEIGHT;
	
	public MapDraw(int mapWidth, int mapHeight) {
		
		B_WIDTH = mapWidth;
		B_HEIGHT = mapHeight;
		
		createMaps();
		
	}
	
	private void createMaps() {

		//전체 크기를 규격별로 나눈다
		int countX = B_WIDTH / DRAW_MAP_SIZE;
		int countY = B_HEIGHT / DRAW_MAP_SIZE;

		//규격별로 자른 후 남는 크기를 저장한다.
		int leastWidth = B_WIDTH % DRAW_MAP_SIZE;
		int leastHeight = B_HEIGHT % DRAW_MAP_SIZE;
		
		//남는 크기가 있을 경우 따로 저장한다.
		if(leastWidth > 0 || leastHeight > 0) {
			countX++;
			countY++;
		}
		
		model = new MapModel[countX][countY];
		
		int progress = 1;
		
		for(int j=0; j < countY; j++) {
			for(int i=0; i < countX; i++) {
				model[i][j] = new MapModel(i * DRAW_MAP_SIZE, j * DRAW_MAP_SIZE, DRAW_MAP_SIZE, DRAW_MAP_SIZE);
				
				if(leastWidth > 0 && i == countX-1)
					model[i][j] = new MapModel(i * DRAW_MAP_SIZE, j * DRAW_MAP_SIZE, leastWidth, DRAW_MAP_SIZE);
				if(leastHeight > 0 && j == countY-1)
					model[i][j] = new MapModel(i * DRAW_MAP_SIZE, j * DRAW_MAP_SIZE, DRAW_MAP_SIZE, leastHeight);
				if(leastWidth > 0 && leastHeight > 0 && i == countX-1 && j == countY-1)
					model[i][j] = new MapModel(i * DRAW_MAP_SIZE, j * DRAW_MAP_SIZE, leastWidth, leastHeight);
				
				System.out.println("MapLoaded " + progress++ + "/" + countX*countY);
			}
		}
		
	}
	
	public MapModel getThisMap(int x, int y) {
		
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
			if(x > DRAW_MAP_SIZE)
				boardX = x / DRAW_MAP_SIZE;
			if(y > DRAW_MAP_SIZE)
				boardY = y / DRAW_MAP_SIZE;
			
			return model[boardX][boardY];
			
		}catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(x + " " + y);
			System.out.println(boardX + " " + boardY);
			JOptionPane.showConfirmDialog(null, "오류");
			System.exit(0);
		}
		return model[boardX][boardY];
		
		
	}
	
	public MapModel[][] getMapModels(){
		return model;
	}
	
}
