package Bettle.model.maps;

import java.util.List;

import javax.swing.SwingWorker;

import Bettle.Screen.RootFrame;

/**
 * 맵을 구획별로 분리하고 관리하는 클래스
 * @author Jeongsam
 *
 */
public class MapCompartmentDesigner extends SwingWorker<MapDataModel[][], Object> {
	
	//어느 규격으로 맵을 분리 할지 결정한다.
	public static int DRAW_MAP_SIZE = 50;
	
	private int B_WIDTH;
	private int B_HEIGHT;
	
	
	
	public MapCompartmentDesigner(final RootFrame frame, int width, int height) {
		
		B_WIDTH = width;
		B_HEIGHT = height;
	}
	
	@Override
	protected MapDataModel[][] doInBackground() throws Exception {

		
		
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
		
		MapDataModel[][] models = new MapDataModel[countX][countY];
		
		int progress = 1;
		
		//규격별로 크기를 자르고 MapDataModel 객체를 생성한다.
		for(int j=0; j < countY; j++) {
			for(int i=0; i < countX; i++) {
				models[i][j] = new MapDataModel(i * DRAW_MAP_SIZE, j * DRAW_MAP_SIZE, DRAW_MAP_SIZE, DRAW_MAP_SIZE);
				
				if(leastWidth > 0 && i == countX-1)
					models[i][j] = new MapDataModel(i * DRAW_MAP_SIZE, j * DRAW_MAP_SIZE, leastWidth, DRAW_MAP_SIZE);
				if(leastHeight > 0 && j == countY-1)
					models[i][j] = new MapDataModel(i * DRAW_MAP_SIZE, j * DRAW_MAP_SIZE, DRAW_MAP_SIZE, leastHeight);
				if(leastWidth > 0 && leastHeight > 0 && i == countX-1 && j == countY-1)
					models[i][j] = new MapDataModel(i * DRAW_MAP_SIZE, j * DRAW_MAP_SIZE, leastWidth, leastHeight);
				
				float result = (float) progress++ / (float) (countX * countY) * 100;
				setProgress(Integer.valueOf((int) result));
				
				publish(result);
				
				
			}
		}
		
		setProgress(100);
		
		return models;
	}
	
	
	
	@Override
	protected void done() {
		
		super.done();
	}
	
	
	
	
}
