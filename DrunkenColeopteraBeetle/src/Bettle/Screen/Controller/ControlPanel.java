package Bettle.Screen.Controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Bettle.Screen.BeetleMovePanel;
import Bettle.Screen.Frame;
import Bettle.Screen.dataView.DataViewFrame;

public class ControlPanel extends JPanel{

	
	private Frame frame = null;
	
	private DataViewFrame dataViewFrame = new DataViewFrame();
	
	private String[] beetlesName = {"노랭이", "빨강이", "간지검정", "핑크핑크", "아오눈부셔슈발", "마젠타갓",
			"a","b","c","d","e","f","g","h","i","j","k","l","m","n", "o", "p", "q", "r", "s" ,"t", "u" ,"v", "w", "x" ,"y","z"};
	
	private JButton btnAction;
	private JButton btnReset;
	private JButton btnShowDataFrame;
	private JProgressBar progressCurrent;
	private JProgressBar progressWhore;
	private JButton btnBeetlePre;
	private JButton btnBeetleNext;
	private JLabel lblLockBeetleNumber;
	private JComboBox cmBoxBeetleCount;
	private JComboBox cmBoxDelay;
	private JComboBox cmBoxWidth;
	private JComboBox cmBoxHeight;
	private JComboBox cmBoxLoop;
	private JToggleButton tglbtnDebug;
	
	private int loopPlay = 1;
	
	/**
	 * Create the panel.
	 */
	public ControlPanel(Frame beetleFrame) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		this.frame = beetleFrame;
		setLayout(null);
		
		JLabel lblMapConf = new JLabel("맵 설정");
		lblMapConf.setFont(new Font("굴림", Font.BOLD, 13));
		lblMapConf.setBounds(12, 10, 176, 15);
		add(lblMapConf);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 28, 141, 15);
		add(separator_1);
		
		JLabel lblWidth = new JLabel("가로");
		lblWidth.setBounds(22, 38, 31, 15);
		add(lblWidth);
		
		cmBoxWidth = new JComboBox();
		cmBoxWidth.setModel(new DefaultComboBoxModel(new String[] {"20", "30", "50", "100", "200", "300", "500", "1000", "10000", "100000", "1000000", "10000000", "100000000", "1000000000", "10000000000"}));
		cmBoxWidth.setBounds(84, 35, 69, 21);
		add(cmBoxWidth);
		
		JLabel lblHeight = new JLabel("\uC138\uB85C");
		lblHeight.setBounds(22, 66, 31, 15);
		add(lblHeight);
		
		cmBoxHeight = new JComboBox();
		cmBoxHeight.setModel(new DefaultComboBoxModel(new String[] {"20", "30", "50", "100", "200", "300", "500", "1000", "10000", "100000", "1000000", "10000000", "100000000", "1000000000", "10000000000"}));
		cmBoxHeight.setBounds(84, 63, 69, 21);
		add(cmBoxHeight);
		
		JLabel lblLoop = new JLabel("\uBC18\uBCF5 \uC218");
		lblLoop.setBounds(22, 94, 45, 15);
		add(lblLoop);
		
		cmBoxLoop = new JComboBox();
		cmBoxLoop.setModel(new DefaultComboBoxModel(new String[] {"1", "5", "10", "50", "100", "1000"}));
		cmBoxLoop.setBounds(84, 91, 69, 21);
		add(cmBoxLoop);
		
		JLabel lblBeetleConf = new JLabel("\uB531\uC815\uBC8C\uB808 \uC124\uC815");
		lblBeetleConf.setFont(new Font("굴림", Font.BOLD, 13));
		lblBeetleConf.setBounds(12, 134, 176, 15);
		add(lblBeetleConf);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 154, 141, 15);
		add(separator);
		
		JLabel lblBeetleCount = new JLabel("\uB9C8\uB9AC \uC218");
		lblBeetleCount.setHorizontalAlignment(SwingConstants.LEFT);
		lblBeetleCount.setBounds(22, 162, 45, 15);
		add(lblBeetleCount);
		
		cmBoxBeetleCount = new JComboBox();
		cmBoxBeetleCount.setToolTipText("\uB9F5\uC744 \uC21C\uD68C\uD558\uB294 \uB531\uC815\uBC8C\uB808\uC758 \uC218\uB97C \uACB0\uC815\uD569\uB2C8\uB2E4");
		
		int maxBeetleCount = 2048;
		String[] maxBeetleCountArrayString = new String[maxBeetleCount];
		for(int i=0; i<maxBeetleCount; i++) {
			maxBeetleCountArrayString[i] = String.valueOf(i+1);
		}
		
		cmBoxBeetleCount.setModel(new DefaultComboBoxModel(maxBeetleCountArrayString));
		cmBoxBeetleCount.setBounds(84, 159, 69, 21);
		add(cmBoxBeetleCount);
		
		JLabel lblDelay = new JLabel("\uB51C\uB808\uC774");
		lblDelay.setHorizontalAlignment(SwingConstants.LEFT);
		lblDelay.setBounds(22, 190, 45, 15);
		add(lblDelay);
		
		cmBoxDelay = new JComboBox();
		cmBoxDelay.setToolTipText("\uB531\uC815\uBC8C\uB808\uAC00 \uBB34\uC791\uC704 \uBC29\uD5A5\uC744 \uACB0\uC815\uD558\uB294\uB370 \uAC78\uB9AC\uB294 \uC2DC\uAC04\uC744 \uC9C0\uC815\uD569\uB2C8\uB2E4.");
		cmBoxDelay.setModel(new DefaultComboBoxModel(new String[] {"0", "10", "20", "50", "100", "200"}));
		cmBoxDelay.setBounds(84, 187, 69, 21);
		add(cmBoxDelay);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(12, 222, 141, 15);
		add(separator_2);
		
		JLabel lblLookBeetleTitle = new JLabel("\uD604\uC7AC \uC9C0\uCF1C\uBCF4\uB294 \uB531\uC815\uC774");
		lblLookBeetleTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblLookBeetleTitle.setFont(new Font("굴림", Font.BOLD, 13));
		lblLookBeetleTitle.setBounds(12, 237, 141, 15);
		add(lblLookBeetleTitle);
		
		lblLockBeetleNumber = new JLabel("");
		lblLockBeetleNumber.setFont(new Font("굴림", Font.BOLD, 13));
		lblLockBeetleNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblLockBeetleNumber.setBounds(12, 262, 141, 15);
		lblLockBeetleNumber.setBackground(BeetleMovePanel.CLR_BACKGROUND);
		add(lblLockBeetleNumber);
		
		btnBeetlePre = new JButton("\u25C0");
		btnBeetlePre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int currentViewBeetle = frame.getBeetleScreen().getCurrentViewBeetle();
				int beetleCount = Integer.valueOf((String) cmBoxBeetleCount.getSelectedItem());
				
				if(currentViewBeetle -1 < 0)
					frame.getBeetleScreen().setBeetleLookView(beetleCount-1);
				else
					frame.getBeetleScreen().setBeetleLookView(currentViewBeetle-1);
					
				lblLockBeetleNumber.setText("■");
				lblLockBeetleNumber.setForeground(frame.getBeetleScreen().getCurrentViewBeetleColor());
				
			}
		});
		btnBeetlePre.setFont(new Font("굴림", Font.BOLD, 14));
		btnBeetlePre.setBounds(29, 287, 50, 50);
		add(btnBeetlePre);
		
		btnBeetleNext = new JButton("\u25B6");
		btnBeetleNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int currentViewBeetle = frame.getBeetleScreen().getCurrentViewBeetle();
				int beetleCount = Integer.valueOf((String) cmBoxBeetleCount.getSelectedItem());
				
				if(currentViewBeetle + 1 > beetleCount-1)
					frame.getBeetleScreen().setBeetleLookView(0);
				else
					frame.getBeetleScreen().setBeetleLookView(currentViewBeetle+1);
				
				lblLockBeetleNumber.setText("■");
				lblLockBeetleNumber.setForeground(frame.getBeetleScreen().getCurrentViewBeetleColor());
					
			}
		});
		btnBeetleNext.setFont(new Font("굴림", Font.BOLD, 14));
		btnBeetleNext.setBounds(84, 287, 50, 50);
		add(btnBeetleNext);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(12, 347, 141, 15);
		add(separator_3);
		
		JLabel lblCurrentProgress = new JLabel("\uD604\uC7AC \uC9C4\uD589\uB3C4");
		lblCurrentProgress.setHorizontalAlignment(SwingConstants.LEFT);
		lblCurrentProgress.setBounds(22, 358, 166, 15);
		add(lblCurrentProgress);
		
		progressCurrent = new JProgressBar();
		progressCurrent.setBounds(42, 379, 111, 14);
		add(progressCurrent);
		
		JLabel lblWhoreProgress = new JLabel("\uC804\uCCB4 \uC9C4\uD589\uB3C4");
		lblWhoreProgress.setHorizontalAlignment(SwingConstants.LEFT);
		lblWhoreProgress.setBounds(22, 403, 166, 15);
		add(lblWhoreProgress);
		
		progressWhore = new JProgressBar();
		progressWhore.setBounds(42, 424, 111, 14);
		add(progressWhore);
		
		btnAction = new JButton("\uC2E4\uD589");
		btnAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(frame.getBeetleScreen() == null){
					
					loopPlay = 1;
					
					startRunning();
					
				}else{
					
					if(frame.getBeetleScreen().isRunning()){
						
						if(frame.getBeetleScreen().isPause()){
							frame.getBeetleScreen().setPause(false);
						}else{
							frame.getBeetleScreen().setPause(true);
						}
						
						
						
					}
					
					
				}
				
				
				
			}
		});
		btnAction.setFont(new Font("굴림", Font.BOLD, 14));
		btnAction.setBounds(12, 448, 141, 25);
		add(btnAction);
		
		btnReset = new JButton("\uC911\uC9C0/\uCD08\uAE30\uD654");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopAndClearRunning();
			}
		});
		btnReset.setFont(new Font("굴림", Font.BOLD, 14));
		btnReset.setBounds(12, 476, 141, 25);
		add(btnReset);
		
		btnShowDataFrame = new JButton("\uB370\uC774\uD130 \uBCF4\uAE30");
		btnShowDataFrame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(dataViewFrame.isVisible())
					return;
				else
					dataViewFrame.setVisible(true);
				
				
			}
		});
		btnShowDataFrame.setFont(new Font("굴림", Font.BOLD, 14));
		btnShowDataFrame.setBounds(12, 504, 141, 42);
		add(btnShowDataFrame);
		
		tglbtnDebug = new JToggleButton("!");
		tglbtnDebug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(frame.getBeetleScreen() == null)
					return;
				
				JToggleButton a = (JToggleButton) e.getSource();
				if(a.isSelected()){
					tglbtnDebug.setForeground(Color.RED);
					frame.getBeetleScreen().setDebugMode(true);
				}
					
				else{
					tglbtnDebug.setForeground(Color.BLACK);
					frame.getBeetleScreen().setDebugMode(false);
				}
					
			}
		});
		tglbtnDebug.setBounds(128, 0, 37, 23);
		add(tglbtnDebug);
		
		gameChangeListener();
		cmBoxHeight.setEnabled(false);
		
		cmBoxWidth.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				cmBoxHeight.setSelectedItem(cmBoxWidth.getSelectedItem());
			}
		});
		
		
		syncWithData();
		
		//데이터 뷰 프레임의 위치를 메인 프레임과 연동한다.
		 dataViewFrameLocationChangeListener();
		
	}
	
	public void syncWithData(){
		//데이터 뷰 프레임과 결과 데이터 정보를 연동한다.
		dataViewFrame.setDataTable(frame.getData());
	}
	
	private void setControllersUseable(boolean set){
		  cmBoxBeetleCount.setEnabled(set);
		  cmBoxDelay.setEnabled(set);
		  cmBoxWidth.setEnabled(set);
		  //cmBoxHeight.setEnabled(set);
		  cmBoxLoop.setEnabled(set);
	}
	
	private void dataViewFrameLocationChangeListener(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					dataViewFrame.setLocation(frame.getX() + frame.getWidth() - 10, frame.getY());
					
					if(dataViewFrame.isVisible())
						btnShowDataFrame.setEnabled(false);
					else
						btnShowDataFrame.setEnabled(true);
				}
					
				
			}
		}).start();
	}
	
	private void startRunning(){
		int width = Integer.valueOf((String) cmBoxWidth.getSelectedItem());
		int height = Integer.valueOf((String) cmBoxHeight.getSelectedItem());
		int beetleCount = Integer.valueOf((String) cmBoxBeetleCount.getSelectedItem());
		int delay = Integer.valueOf((String) cmBoxDelay.getSelectedItem());
		
		frame.initBettleScreen(width, height, beetleCount, delay);
		setControllersUseable(false);
		
		lblLockBeetleNumber.setText("■");
		lblLockBeetleNumber.setForeground(frame.getBeetleScreen().getCurrentViewBeetleColor());
	}
	
	private void stopAndClearRunning(){
		frame.removeBettleScreen();
		setControllersUseable(true);
		tglbtnDebug.setForeground(Color.BLACK);
		tglbtnDebug.setSelected(false);
		lblLockBeetleNumber.setText("");
		lblLockBeetleNumber.setForeground(Color.BLACK);
	}
	
	/**
	 * 컴포넌트의 상태 변화를 관리한다.
	 */
	private void gameChangeListener(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {

				while(true){
					if(frame.getBeetleScreen() != null){
						
						frame.hideMessageLabel();
						btnReset.setEnabled(true);
						
						try{
							if(frame.getBeetleScreen().isRunning()){
								if(frame.getBeetleScreen().isPause())
									btnAction.setText("실행");
								else
									btnAction.setText("일시 중지");
							}
							else{
								btnAction.setText("실행");
								if(frame.getBeetleScreen().isEnd()){
									
									int loopMax = Integer.valueOf((String)cmBoxLoop.getSelectedItem());
									
									if( loopMax > 1)
									{
										if(loopPlay < loopMax){
											stopAndClearRunning();
											startRunning();
											loopPlay++;
										}
									}
									
									btnAction.setEnabled(false);
									btnReset.setText("초기화");
								}
							}
								
						}catch (NullPointerException ne){
							
							
						}catch (Exception e){
							
							
						}
						
					}else if(frame.getBeetleScreen() == null) {
						tglbtnDebug.setForeground(Color.BLACK);						
						btnReset.setText("중지/초기화");
						btnAction.setEnabled(true);
						btnReset.setEnabled(false);
						setControllersUseable(true);
						frame.showMeesageLabel("딱정벌레가 없또용 > v <");
					}
					
					repaint();
				}
					
				
			}
		}).start();
	}

	
	
}
