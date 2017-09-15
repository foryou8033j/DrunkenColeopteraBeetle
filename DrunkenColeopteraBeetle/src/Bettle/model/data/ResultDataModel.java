package Bettle.model.data;


/**
 * 결과 데이터 저장 모델 클래스
 * @author Jeongsam
 *
 */
public class ResultDataModel {

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
	public ResultDataModel(int width, int height, String time, int beetleCount, int delay) {
		
		this.width = width;
		this.height = height;
		
		this.endTime = time;
		this.beetleCount = beetleCount;
		this.delay = delay;
		
		System.out.println(time + " " + beetleCount + " " +delay);
		
	}
	
	/**
	 * 저장 된 가로 크기를 반환한다.
	 * @return {@link int}
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * 저장 된 세로 크기를 반환한다.
	 * @return {@link int}
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * 저장 된 결과 시간 값을 반환한다.
	 * @return {@link String}
	 */
	public String getTime(){
		return endTime;
	}
	
	/**
	 * 저장 된 딱정벌레 마리 수 값을 반환한다.
	 * @return {@link int}
	 */
	public int getBeetleCount(){
		return beetleCount;
	}
	
	/**
	 * 저장 된 딜레이 시간 값을 반환한다.
	 * @return {@link int}
	 */
	public int getDalay(){
		return delay;
	}
}
