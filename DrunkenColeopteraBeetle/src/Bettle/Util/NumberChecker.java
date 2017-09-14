package Bettle.Util;

/**
 * 정수 확인 클래스
 * @author Jeongsam
 *
 */
public class NumberChecker {

	/**
	 * 입력된 문자열이 정수형태인지 확인한다.
	 * @param str
	 * @return {@link boolean}
	 */
	public static boolean Check(String str){
		
		try {
	        Double.parseDouble(str);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
		
	}
	
}
