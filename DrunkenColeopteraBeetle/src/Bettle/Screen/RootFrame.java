package Bettle.Screen;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

import Bettle.Screen.Controller.ControlPanel;
import Bettle.model.data.ResultData;

/**
 * 술취한 딱정벌레 프레임 관리 클래스
 * @author Jeongsam
 *
 */
public class RootFrame extends JFrame {
	
	private BeetleMovePanel screen;
	private ControlPanel controller;
	
	private ResultData data;
	
	private final int DOT_SIZE = 10;	//여기서 public static 으로 써도 되지만.. 전역변수 사용불가
	
	private JLabel messageLabel = new JLabel("");
	
	public RootFrame() {
		
		setTitle("술취한 딱정벌레 - 20140636 서정삼");
		setVisible(true);
		setResizable(false);
		setMinimumSize(new Dimension(50*DOT_SIZE + 210, 560));
		
		//JFrame을 종료 할 때 프로세서에 남지 않게 종료하도록 지시
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			//Metro 환경이나 일부 Aero 환경에서 GUI가 깨지는 현상을 방지하기 위해 
			//아래 명령어 사용
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());	
		}catch (Exception e) {
			//ignore
		}
		
		//데이터 저장 클래스를 초기화한다.
		data = new ResultData();
		
		//옵션 및 데이터가 출력될 구역의 Panel 을 선언한다.
		controller = new ControlPanel(this);
		
		
		//AnchorPane을 사용하기위해 Layout 제거
		getContentPane().setLayout(null);
		
		//레이아웃 설정
		controller.setBounds(10, 10, 165, 560);
		getContentPane().add(controller);
		
		messageLabel.setFont(new Font("", Font.BOLD, 20));
		messageLabel.setBounds(getWidth() / 2, getHeight() / 2, 400, 30);
		getContentPane().add(messageLabel);
		
		JLabel lblNewLabel = new JLabel("금오공과대학교 컴퓨터공학과 20140636 서정삼");
		lblNewLabel.setBounds(181, 546, 511, 24);
		getContentPane().add(lblNewLabel);
		
		setSize(new Dimension(50*DOT_SIZE + 210, 610));

	}
	
	/**
	 * 딱정 벌레 패널을 초기화한다.
	 * @param width 가로크기
	 * @param height 세로크기
	 * @param beetles 딱정벌레 수
	 * @param delay 딜레이 값
	 */
	public void initBettleScreen(int width, int height, int beetles, int delay){
		
		if(screen == null){
			screen = new BeetleMovePanel(this, width, height,beetles, delay);
			screen.setBounds(185, 10, width*DOT_SIZE, height*DOT_SIZE);
			getContentPane().add(screen);
		}else{
			removeBettleScreen();
			
		}
		
	}
	
	/**
	 * 현재 딱정벌레 패널을 제거한다.
	 */
	public void removeBettleScreen(){
		try{
			screen.setRunning(false);
			getContentPane().remove(screen);
			screen = null;
			
			repaint();
			
		}catch (Exception e){
			//ignore
		}
	}
	
	/**
	 * 딱정벌레 패널을 반환한다.
	 * @return {@link BeetleMovePanel}
	 */
	public BeetleMovePanel getBeetleScreen(){
		return screen;
	}
	
	/**
	 * 메세지 라벨을 보여준다.
	 * @param str {@link String}
	 */
	public void showMeesageLabel(String str){
		messageLabel.setVisible(true);
		messageLabel.setText(str);
	}
	
	/**
	 * 메세지 라벨을 숨긴다.
	 */
	public void hideMessageLabel(){
		messageLabel.setVisible(false);
	}
	
	/**
	 * 결과 데이터와 컨트롤러를 동기화한다.
	 */
	public void syncWithData(){
		controller.syncWithData();
	}
	
	/**
	 * 데이터 저장 객체를 반환한다.
	 * @return {@link ResultData}
	 */
	public ResultData getData(){
		return data;
	}
}
