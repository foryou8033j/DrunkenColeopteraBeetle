package Bettle.Util.Dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JProgressBar;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProgressbarDialog extends JDialog{
	
	
	private JLabel label;
	private JLabel label_1;
	private JProgressBar progressBar;
	private JButton btnNewButton;
	
	public ProgressbarDialog(JFrame frame, String title, String contents) {
		
		super(frame);
		getContentPane().setBackground(Color.GRAY);
		setBackground(Color.GRAY);
		setUndecorated(true);
		setBounds(0, 0, 320, 192);
		setLocationRelativeTo(frame);
		setType(Type.UTILITY);
		setAlwaysOnTop(true);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(frame);
		getContentPane().setLayout(null);
		setVisible(true);
		setComponents(title, contents);
	}
	
	private void setComponents(String title, String contents){
		
		label = new JLabel("LabelTitle");
		label.setForeground(Color.WHITE);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("굴림", Font.BOLD, 16));
		label.setBounds(0, 20, 320, 19);
		label.setText(title);
		getContentPane().add(label);
		
		label_1 = new JLabel("LabelContents");
		label_1.setForeground(Color.WHITE);
		label_1.setHorizontalTextPosition(SwingConstants.CENTER);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("굴림", Font.PLAIN, 14));
		label_1.setBounds(0, 86, 320, 40);
		label_1.setText(contents);
		getContentPane().add(label_1);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(12, 62, 296, 19);
		getContentPane().add(progressBar);
		
		btnNewButton = new JButton("<html><CENTER>강제 종료<BR>System.exit(1)</html>");
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setBounds(50, 130, 218, 42);
		getContentPane().add(btnNewButton);
		
		repaint();
	}
	
	public void setTitleText(String title){
		label.setText(title);
	}
	
	public void setContentsText(String contents){
		label_1.setText(contents);
	}
	
	public JProgressBar getProgressBar(){
		return progressBar;
	}
}
