package Bettle.Screen.Controller.File;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * 저장된 결과 데이터를 파일로의 출력을 지원한다.
 * @author Jeongsam
 *
 */
public class FileManagement {

	
	public void generateFile(File saveFile){
		
		String csvFilePath = saveFile.getAbsolutePath();
		
		String head = "제목, 내용";
		String firstValue = "베이비 드라이버, 재밌다";
		String secondValue = "청년경찰, 유쾌하다";
		
		BufferedWriter writer;
		
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFilePath), "UTF-8"));
			
			writer.write(head);
			writer.write(firstValue);
			writer.write(secondValue);
			
			writer.close();
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
}
