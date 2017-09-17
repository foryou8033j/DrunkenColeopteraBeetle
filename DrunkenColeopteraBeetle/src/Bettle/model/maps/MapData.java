package Bettle.model.maps;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Bettle.Screen.RootFrame;
import Bettle.Util.Dialog.ProgressbarDialog;

/**
 * 맵 전체 데이터 관리 클래스
 * @author Jeongsam
 *
 */
public class MapData {
	
	//방문하지 않은 셀의 갯수를 저장한다.
	private int countOfNoVisitCells;
	
	//맵 세부 데이터 저장 객체 선언
	private MapDataModel models[][];
	
	private final int B_WIDTH;
	private final int B_HEIGHT;
	
	ProgressbarDialog dialog = null;
	
	RootFrame frame = null;
	
	public MapData(RootFrame frame, int mapWidth, int mapHeight){
		
		B_WIDTH = mapWidth;
		B_HEIGHT = mapHeight;
		
		//프레임와 연동한다.
		this.frame = frame;
		
		//방문 하지 않은 셀의 초기값을 설정한다.
		countOfNoVisitCells = B_WIDTH * B_HEIGHT;
		
		
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
			}
		});
		
		dialog = new ProgressbarDialog(getFrame(), "데이터 초기화 중...", "데이터 초기화 준비중");
		dialog.setTitle("맵 데이터 구성 중...");
		
		MapCompartmentDesigner designerTask = new MapCompartmentDesigner(frame, mapWidth, mapHeight);
		
		designerTask.addPropertyChangeListener(
			     new PropertyChangeListener() {
			         public  void propertyChange(PropertyChangeEvent evt) {
			             if ("progress".equals(evt.getPropertyName())) {
			            	 if(dialog != null){
			            		 dialog.getProgressBar().setValue((Integer)evt.getNewValue());
			            		 
			            		 if((Integer)evt.getNewValue() > 50)
			            			 dialog.setContentsText("<html><center>맵 구성 중... " + (Integer)evt.getNewValue() + "%<br>메모리가 부족할 경우 중지될 수 있습니다.");
			            		 else
			            			 dialog.setContentsText("맵 구성 중... " + (Integer)evt.getNewValue() + "%");
			            	 }
									
			             }
			         }
			     });
		
		try {
			
			
			designerTask.execute();
			this.models = designerTask.get();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				if(dialog != null){
					dialog.dispose();
					dialog.setVisible(false);
					dialog = null; //물론 gc가 없애주겠지만 혹시를 위하여..
				}
			}
		});
		
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
			/*e.printStackTrace();
			System.out.println(x + " " + y);
			System.out.println(boardX + " " + boardY);
			JOptionPane.showConfirmDialog(null, "오류");*/
			System.exit(0);
		}
		return models[boardX][boardY];

	}
	
	public MapDataModel[][] getMapModels(){
		return models;
	}
	
	private RootFrame getFrame(){
		return frame;
	}
	
	private ProgressbarDialog getDialog(){
		return dialog;
	}
	
}
