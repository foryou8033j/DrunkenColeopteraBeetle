package Bettle.File;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.JOptionPane;

import Bettle.model.data.ResultData;
import Bettle.model.data.ResultDataModel;

/**
 * 저장된 결과 데이터를 파일로의 출력을 지원한다.
 * @author Jeongsam
 *
 */
public class FileManagement {

	
	/**
	 * 파일을 생성하고 저장한다.
	 * @param saveFile 저장 파일 객체
	 * @param resultDatas 저장 될 결과 데이터
	 */
	public void generateFile(File saveFile, ResultData resultDatas){
		
		//사용자가 확장자를 붙였을 때 제거한다.
		saveFile.getName().replaceAll(".csv", "");
		
		String csvFilePath = saveFile.getPath() + ".csv";
		
		int length = resultDatas.dataCount();
		
		String rowData[] = new String[length];
		
		//결과 데이터를 String 형 배열 객체에 쓴다.
		for(int i=0; i<length; i++){
			
			try{
				ResultDataModel modelData = resultDatas.getData()[i];	
				
				SimpleDateFormat formatter = new SimpleDateFormat ( "mm.ss.SSS", Locale.KOREA );
				String resultTime = formatter.format ( modelData.getTime() );
				
				rowData[i] = new String(modelData.getWidth() + "," 
						+ modelData.getHeight() + "," 
						+ resultTime + "," 
						+ modelData.getBeetleCount() + "," 
						+ modelData.getDalay());
				
			}catch (Exception e){
				//ignore
			}
		}
		
		String head = "가로 크기,세로 크기,소요 시간,딱정 벌레 수,딜레이";
		
		//파일을 쓴다.
		BufferedWriter writer;
		
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFilePath), "MS949"));
			
			//헤드 부분을 쓴다.
			writer.write(head);
			writer.newLine();
			
			for(int i=0; i<length; i++){
				System.out.println(rowData[i]);
				writer.write(rowData[i]);
				writer.newLine();
			}
			
			writer.newLine();
			writer.write("술취한 딱정벌레 시뮬레이터에서 생성 된 데이터입니다. 20140636 서정삼");
			
			writer.close();
			
			
		} catch (UnsupportedEncodingException e) {
			JOptionPane.showMessageDialog(null, "파일 저장 중 오류가 발생하였습니다.", "저장 오류", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "파일 저장 중 오류가 발생하였습니다.", "저장 오류", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "파일 저장 중 오류가 발생하였습니다.", "저장 오류", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		
		//저장 된 데이터를 확인한다.
		showResultDialog(csvFilePath);
		
	}
	
	/**
	 * 저장 후 결과 Dialog를 보여준다.
	 * @param savedPath 저장 된 경로 파일
	 */
	private void showResultDialog(String savedPath){
		
		File file = new File(savedPath);
		
		if(!file.exists()){
			
			JOptionPane.showMessageDialog(null, "파일이 없거나 손상되었습니다.", "저장 오류", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(savedPath), "MS949"));
			
			String messageString = "<html>성공적으로 다음의 데이터가 저장되었습니다.<br><br>";
			String readString;
			while ((readString = reader.readLine()) != null) {
		        messageString = messageString +"<br>" + readString;
		      }
			
			messageString = messageString +"<html>";
			
			JOptionPane.showMessageDialog(null, messageString, "저장 성공", JOptionPane.INFORMATION_MESSAGE);
			
			reader.close();
			
		} catch (UnsupportedEncodingException e) {
			JOptionPane.showMessageDialog(null, "저장 된 데이터 확인 중 오류가 발생하였습니다.", "저장 오류", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "저장 된 데이터 확인 중 오류가 발생하였습니다.", "저장 오류", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "저장 된 데이터 확인 중 오류가 발생하였습니다.", "저장 오류", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		
	}
	
	
}
