package Bettle.Util;

public class NumberChecker {

	public static boolean Check(String str){
		
		try {
	        Double.parseDouble(str);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
		
	}
	
}
