package Bettle.Screen.dataView;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalExclusionType;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import Bettle.File.FileManagement;
import Bettle.model.data.ResultData;
import Bettle.model.data.ResultDataModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.awt.event.ActionEvent;

/**
 * 결과 데이터 출력 프레임 클래스
 * @author Jeongsam
 *
 */
public class DataViewFrame extends JFrame {

	private String columnNames[] = { "가로 크기", "세로 크기", "소요 시간", "딱정 벌레 수", "딜레이"};
	
	private JPanel contentPane;
	private JButton btnExportFile;
	private JButton btnShowGraph;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnResetData;

	
	private ResultData resultData = null;

	/**
	 * Create the frame.
	 */
	public DataViewFrame() {
		
		setType(Type.UTILITY);
		setTitle("데이터 테이블");
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnExportFile = new JButton("파일 내보내기(*.csv)");
		btnExportFile.setFont(new Font("굴림", Font.BOLD, 14));
		btnExportFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//TODO 데이터 없을 경우 저장이 불가능 해야함.
				if(resultData == null || resultData.getData().length==0 || table.getRowCount()==0){
					JOptionPane.showMessageDialog(rootPane, "저장 할 데이터가 없습니다.", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				}
					
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new FileNameExtensionFilter("CSV File(*.csv)", "csv"));
				fileChooser.showSaveDialog(null);
				
				if(fileChooser.getSelectedFile() != null)
					new FileManagement().generateFile(fileChooser.getSelectedFile(), resultData);
				
				
			}
		});
		panel.add(btnExportFile);
		
		btnShowGraph = new JButton("그래프 보기");
		btnShowGraph.setFont(new Font("굴림", Font.BOLD, 14));
		panel.add(btnShowGraph);
		
		btnResetData = new JButton("데이터 삭제");
		btnResetData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int response = JOptionPane.showConfirmDialog(rootPane, "<html>데이터를 삭제하면 되돌릴 수 없습니다.<br>"
						+ "그래도 삭제 하시겠습니까?", "경고", JOptionPane.YES_NO_OPTION);
				
				if(response==0){
					resultData.resetData();
					setDataTable(resultData);
				}	
				
			}
		});
		btnResetData.setFont(new Font("굴림", Font.BOLD, 14));
		panel.add(btnResetData);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		table = new JTable();
		contentPane.add(table, BorderLayout.WEST);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		
	}
	
	/**
	 * 테이블 데이터 동기화
	 * @param data
	 */
	public void setDataTable(ResultData data){
		
		this.resultData = data;
		
		int length = data.dataCount();
		
		Object rowData[][] = new Object[length][5];
		
		for(int i=0; i<length; i++){
			
			
			
			try{
				ResultDataModel modelData = data.getData()[i];
				
				SimpleDateFormat formatter = new SimpleDateFormat ( "mm:ss:SSS", Locale.KOREA );
				String resultTime = formatter.format ( modelData.getTime() );
				
				Object objectData[] = {modelData.getWidth(), modelData.getHeight(), resultTime, modelData.getBeetleCount(), modelData.getDalay()};
				
				for(int j=0; j<5; j++)
					rowData[i][j] = objectData[j];
				
			}catch (Exception e){
				//ignore
			}
		}
		
		DefaultTableModel defaultTableModel = new DefaultTableModel(rowData, columnNames);
		table.setModel(defaultTableModel);
		
	}

}
