package Bettle.Screen;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

import Bettle.Screen.Controller.ControlPanel;
import Bettle.model.data.ResultData;

/**
 * ������ �������� ������ ���� Ŭ����
 * @author Jeongsam
 *
 */
public class Frame extends JFrame {
	
	private BeetleMovePanel screen;
	private ControlPanel controller;
	
	private ResultData data;
	
	private JLabel messageLabel = new JLabel("");
	
	public Frame() {
		
		setTitle("������ �������� - 20140636 ������");
		setVisible(true);
		setResizable(false);
		setMinimumSize(new Dimension(50*BeetleMovePanel.DOT_SIZE + 210, 560));
		
		//JFrame�� ���� �� �� ���μ����� ���� �ʰ� �����ϵ��� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			//Metro ȯ���̳� �Ϻ� Aero ȯ�濡�� GUI�� ������ ������ �����ϱ� ���� 
			//�Ʒ� ��ɾ� ���
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());	
		}catch (Exception e) {
			//ignore
		}
		
		//������ ���� Ŭ������ �ʱ�ȭ�Ѵ�.
		data = new ResultData();
		
		//�ɼ� �� �����Ͱ� ��µ� ������ Panel �� �����Ѵ�.
		controller = new ControlPanel(this);
		
		
		//AnchorPane�� ����ϱ����� Layout ����
		getContentPane().setLayout(null);
		
		//���̾ƿ� ����
		controller.setBounds(10, 10, 165, 560);
		getContentPane().add(controller);
		
		messageLabel.setFont(new Font("", Font.BOLD, 20));
		messageLabel.setBounds(getWidth() / 2, getHeight() / 2, 400, 30);
		getContentPane().add(messageLabel);
		
		JLabel lblNewLabel = new JLabel("�ݿ��������б� ��ǻ�Ͱ��а� 20140636 ������");
		lblNewLabel.setBounds(181, 546, 511, 24);
		getContentPane().add(lblNewLabel);
		
		setSize(new Dimension(50*BeetleMovePanel.DOT_SIZE + 210, 610));

	}
	
	public void initBettleScreen(int width, int height, int beetles, int delay){
		
		if(screen == null){
			screen = new BeetleMovePanel(this, width, height,beetles, delay);
			screen.setBounds(185, 10, width*BeetleMovePanel.DOT_SIZE, height*BeetleMovePanel.DOT_SIZE);
			getContentPane().add(screen);
		}else{
			removeBettleScreen();
			
		}
		
	}
	
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
	
	public BeetleMovePanel getBeetleScreen(){
		return screen;
	}
	
	public void showMeesageLabel(String str){
		messageLabel.setVisible(true);
		messageLabel.setText(str);
	}
	
	public void hideMessageLabel(){
		messageLabel.setVisible(false);
	}
	
	public void syncWithData(){
		
		controller.syncWithData();
		
	}
	
	
	/**
	 * ������ ���� ��ü�� ��ȯ�Ѵ�.
	 * @return {@link ResultData}
	 */
	public ResultData getData(){
		return data;
	}
}
