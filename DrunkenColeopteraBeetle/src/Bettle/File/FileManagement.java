package Bettle.File;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import Bettle.model.data.ResultData;
import Bettle.model.data.ResultDataModel;

/**
 * 저장된 결과 데이터를 파일로의 출력을 지원한다.
 * @author Jeongsam
 *
 */
public class FileManagement {

	
	public void generateFile(File saveFile, ResultData resultDatas){
		
		//사용자가 확장자를 붙였을 때 제거한다.
		saveFile.getName().replaceAll(".csv", "");
		
		String csvFilePath = saveFile.getPath() + ".csv";
		
		/*if(!saveFile.exists()){
			try {
				saveFile.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}*/
			
		
		int length = resultDatas.getData().length;
		
		String rowData[] = new String[length];
		
		for(int i=0; i<length; i++){
			
			try{
				ResultDataModel modelData = resultDatas.getData()[i];
				
				if(modelData == null){
					length = i;
					break;
				}
					
				
				rowData[i] = new String(modelData.getWidth() + "," 
						+ modelData.getHeight() + "," 
						+ modelData.getTime() + "," 
						+ modelData.getBeetleCount() + "," 
						+ modelData.getDalay());
				
			}catch (Exception e){
				//ignore
			}
		}
		
		String head = "가로 크기,세로 크기,소요 시간,딱정 벌레 수,딜레이";
		
		
		BufferedWriter writer;
		
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFilePath), "MS949"));
			
			writer.write(head);
			writer.newLine();
			
			for(int i=0; i<length; i++){
				System.out.println(rowData[i]);
				writer.write(rowData[i]);
				writer.newLine();
			}
			
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
