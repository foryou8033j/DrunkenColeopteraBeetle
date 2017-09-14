package Bettle.model.data;



/**
 * 결과 데이터 관리 클래스
 * @author Jeongsam
 *
 */
public class ResultData{
	
	private ResultDataModel[] model = null;
	
	private int dataCount = 0;
	
	public ResultData() {
		model = new ResultDataModel[100];
	}

	public void saveData(int width, int height, String time, int count, int delay){
		
		model[dataCount] = new ResultDataModel(width, height, time, count, delay);
		
		dataCount++;
		
	}
	
	public ResultDataModel[] getData(){
		return model;
	}
	
	public int dataCount(){
		return model.length;
	}
	
	
}