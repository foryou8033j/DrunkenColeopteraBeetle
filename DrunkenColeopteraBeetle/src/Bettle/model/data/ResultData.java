package Bettle.model.data;




public class ResultData{
	
	private ResultModel[] model = null;
	
	private int dataCount = 0;
	
	public ResultData() {
		model = new ResultModel[100];
	}

	public void saveData(int width, int height, String time, int count, int delay){
		
		model[dataCount] = new ResultModel(width, height, time, count, delay);
		
		dataCount++;
		
	}
	
	public ResultModel[] getData(){
		return model;
	}
	
	public int dataCount(){
		return model.length;
	}
	
	
}