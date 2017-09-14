package Bettle.model.data;


/**
 * 결과 데이터 저장 모델 클래스이다.
 * @author Jeongsam
 *
 */
public class ResultModel {

	private int width;
	private int height;
	
	private String endTime;
	private int beetleCount;
	private int delay;
	
	
	
	/**
	 * 결과 데이터 정보를 저장한다.
	 * @param time {@link long}
	 * @param beetleCount {@link int}
	 * @param delay {@link int}
	 */
	public ResultModel(int width, int height, String time, int beetleCount, int delay) {
		
		this.width = width;
		this.height = height;
		
		this.endTime = time;
		this.beetleCount = beetleCount;
		this.delay = delay;
		
		System.out.println(time + " " + beetleCount + " " +delay);
		
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public String getTime(){
		return endTime;
	}
	
	public int getBeetleCount(){
		return beetleCount;
	}
	
	public int getDalay(){
		return delay;
	}
}
