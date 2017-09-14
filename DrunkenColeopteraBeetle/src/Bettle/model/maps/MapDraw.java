package Bettle.model.maps;

import javax.swing.JOptionPane;

/**
 * ���� ũ�⺰�� �и��ϰ� �����ϴ� Ŭ����
 * @author Jeongsam
 *
 */
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

		//��ü ũ�⸦ �԰ݺ��� ������
		int countX = B_WIDTH / DRAW_MAP_SIZE;
		int countY = B_HEIGHT / DRAW_MAP_SIZE;

		//�԰ݺ��� �ڸ� �� ���� ũ�⸦ �����Ѵ�.
		int leastWidth = B_WIDTH % DRAW_MAP_SIZE;
		int leastHeight = B_HEIGHT % DRAW_MAP_SIZE;
		
		//���� ũ�Ⱑ ���� ��� ���� �����Ѵ�.
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
			
			//x ��ǥ ����
			if(x < 0)
				x = 0;
			if(x >= B_WIDTH)
				x = B_WIDTH - 1;
			
			//y ��ǥ ����
			if(y < 0)
				y = 0;
			if(y >= B_HEIGHT)
				y = B_HEIGHT - 1;
			
			//���� ��ǥ�� �´� MapModel �� ã�´�.
			if(x > DRAW_MAP_SIZE)
				boardX = x / DRAW_MAP_SIZE;
			if(y > DRAW_MAP_SIZE)
				boardY = y / DRAW_MAP_SIZE;
			
			return model[boardX][boardY];
			
		}catch (Exception e)
		{
			//TODO 170915 ��ǥ ����� ���� ���� �ذ� �ʿ�
			e.printStackTrace();
			System.out.println(x + " " + y);
			System.out.println(boardX + " " + boardY);
			JOptionPane.showConfirmDialog(null, "����");
			System.exit(0);
		}
		return model[boardX][boardY];

	}
	
	public MapModel[][] getMapModels(){
		return model;
	}
	
}
