package Bettle.model.data;



/**
 * 결과 데이터 관리 클레스
 * @author Jeongsam
 *
 */
public class ResultData{
	
	private ResultDataModel[] model = null;
	
	private int dataCount = 0;
	
	public ResultData() {
		model = new ResultDataModel[100];
	}

	/**
	 * 데이터를 저장한다.
	 * 
	 * @param width 가로
	 * @param height 세로
	 * @param time 시간
	 * @param count 딱정벌레 갯수
	 * @param delay 사용한 딜레이 시간
	 */
	public void saveData(int width, int height, String time, int count, int delay){
		
		model[dataCount] = new ResultDataModel(width, height, time, count, delay);
		
		dataCount++;
		
	}
	
	/**
	 * 데이터 객체를 반환한다.
	 * @return {@link ResultDataModel}
	 */
	public ResultDataModel[] getData(){
		return model;
	}
	
	/**
	 * 데이터 갯수를 반환한다.
	 * @return
	 */
	public int dataCount(){
		return model.length;
	}
	
	/**
	 * 데이터 초기화
	 */
	public void resetData(){
		model = new ResultDataModel[100];
	}
	
	
}
