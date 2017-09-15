package Bettle.Screen;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import Bettle.model.bettle.Beetle;
import Bettle.model.bettle.Beetle.Pos;
import Bettle.model.maps.MapData;
import Bettle.model.maps.MapCompartmentDesigner;
import Bettle.model.maps.MapDataModel;


enum DIRECTION {LEFT, RIGHT, UP, DOWN};


/**
 * 딱정벌레 이동 경로 표시 그래픽 클래스
 * @author Jeongsam
 *
 */
public class BeetleMovePanel extends JPanel implements ActionListener
{

	RootFrame frame;
	
	public final static int DOT_SIZE = 10;
	
	public static Color CLR_BACKGROUND = new Color(0, 172, 255);
	
	private Color[] CLR_HEAD = {
			Color.YELLOW,
			Color.RED,
			Color.BLACK,
			Color.PINK,
			Color.CYAN,
			Color.MAGENTA
	};
	
	private int B_WIDTH;
	private int B_HEIGHT;
	private int DELAY;
	
	private int beetleLock = 0;
	private int beetleCount = 0;
	
	private Beetle beetles[] = null;
	private MapData mapData = null;
	
	private boolean _run = true;
	private boolean _pause = false;
	private boolean _debugMode = false;
	private boolean _end = false;
	
	private Timer timer;
	private long startTime;
	private long endTime;
	private long defTime;
	private long pauseTime;
	
	private String endTimeString = ""; 
	
	private Graphics _g;
	
	public BeetleMovePanel(RootFrame frame, int boardWidth, int boardHeight, int beetleCount,  int delay)
	{
		this.frame = frame;
		
		setFocusable(true);
		
		this.beetleCount = beetleCount;
		beetles = new Beetle[beetleCount];
		
		B_WIDTH = boardWidth;
		B_HEIGHT = boardHeight;
		DELAY = delay;
		
		init();
	}
	
	public void showFrameAndInit(){
		
	}
	
	private Graphics getGraphicsComponent(){
		return _g;
	}
	
	/**
	 * 딱정벌레 보드 정보를 초기화한다.
	 */
	private void init()
	{
		_run = true;
		_end = false;
		
		for(int i=0; i<beetleCount; i++){
			
			if(i >= CLR_HEAD.length){
				beetles[i] = new Beetle(new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)), B_WIDTH, B_HEIGHT);
				continue;
			}
			
			beetles[i] = new Beetle(CLR_HEAD[i], B_WIDTH, B_HEIGHT);
		}
		
		mapData = new MapData(frame, B_WIDTH, B_HEIGHT);
		
		startTime = Calendar.getInstance().getTimeInMillis();
		
		timer = new Timer(DELAY, this);
		timer.setDelay(DELAY);
		timer.start();

	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		_g = g;
		
		if(_g == null)
			return;
		
		doDrawBackground(g);
		doDrawing(g);
	}
	
	/**
	 * 배경을 그려준다.
	 * @param g 그래픽스 컴포넌트
	 */
	private void doDrawBackground(Graphics g) {
		
		Font asd = new Font("", Font.BOLD, 10);
		FontMetrics metr = getFontMetrics(asd);
		g.setFont(asd);
		
		//배경 그리기
		try{
			
			MapDataModel mapModel = mapData.getThisMap(beetles[beetleLock].getX(), beetles[beetleLock].getY());
			
			int tmpDrawWidth = mapModel.getwidth();
			int tmpDrawHeight = mapModel.getHeight();
			
			for(int i=0; i < tmpDrawHeight; i++) {
				for(int j=0; j < tmpDrawWidth; j++) {
					
					g.setColor(CLR_BACKGROUND);
					g.fillRect(i*DOT_SIZE, j*DOT_SIZE, DOT_SIZE, DOT_SIZE);
					
				}
			}
			
		}catch (Exception e){
			
		}
		
		

		
	}
	
	/**
	 * 딱정벌레가 이동한 경로와 
	 * @param g
	 */
	private void doDrawing(Graphics g)
	{

		if(_run)
		{
			//지나왔던 경로 그리기.
			
			
			Font asd = new Font("", Font.BOLD, 10);
			FontMetrics metr = getFontMetrics(asd);
			g.setFont(asd);
			
			final MapDataModel mapModel = mapData.getThisMap(beetles[beetleLock].getX(), beetles[beetleLock].getY());
			
			//if()
			
			final int tmpDrawX = mapModel.getX();
			final int tmpDrawY = mapModel.getY();
			int tmpDrawWidth = tmpDrawX + mapModel.getwidth();
			int tmpDrawHeight = tmpDrawY + mapModel.getHeight();
				
			for(int i=tmpDrawY; i < tmpDrawHeight; i++) {
				for(int j=tmpDrawX; j < tmpDrawWidth; j++) {
					if(mapData.isVisitCell(j, i)){
						
						g.setColor(new Color(0, 121, 178));
						g.fillRect((j - tmpDrawX)*DOT_SIZE, (i - tmpDrawY)*DOT_SIZE, DOT_SIZE, DOT_SIZE);
						
						if(_debugMode){
							g.setColor(Color.RED);
							g.drawString("O", (j - tmpDrawX)*DOT_SIZE, (i - tmpDrawY)*DOT_SIZE+10);
						}
					}else{
						
						if(_debugMode){
							g.setColor(Color.RED);
							g.drawString("X", (j - tmpDrawX)*DOT_SIZE, (i - tmpDrawY)*DOT_SIZE+10);
						}
					}
						
				}
			}
			
			
			//현재 딱정벌레 위치 그리기
			
			for(int i=0; i<beetles.length; i++){
				
				if(!mapModel.isInThisMap(beetles[i].getX(), beetles[i].getY()))
					continue;
				
				g.setColor(beetles[i].getBeetleColor());
				g.fillRect((beetles[i].getX() - mapModel.getX())*DOT_SIZE, (beetles[i].getY() - mapModel.getY())*DOT_SIZE, DOT_SIZE, DOT_SIZE);
			}
			
			
			drawMiniMap(g);

			drawVisitCells(g);
			drawTime(g);
			//drawCurrentCordinate(g);
			
			if(_pause) drawPauseScreen(g);
			
			Toolkit.getDefaultToolkit().sync();			

		}
		
	}
	
	private void drawVisitCells(Graphics g) {

		
		int noVisitCellCount = mapData.getNoVisitCell();
		
		drawNoVisitCellCount(g, noVisitCellCount);
		
		if(noVisitCellCount <= 0){
			drawEndScreen(g);
			
			quit();
		}

		
	}
	
	private void drawEndScreen(Graphics g){
		
		Font asd = new Font("", Font.BOLD, 14);
		FontMetrics metr = getFontMetrics(asd);
		
		g.setColor(Color.WHITE);
		g.setFont(asd);
		String msg = "End Of Cells";
		
		g.drawString(msg, (B_WIDTH*DOT_SIZE)/2 - metr.stringWidth(msg)/2,
				(B_HEIGHT*DOT_SIZE) / 2 - metr.getHeight());
	}
	
	private void drawMiniMap(Graphics g) {
		
		if(B_WIDTH < 100 || B_HEIGHT < 100)
			return;
		
		int posX = 10;
		int posY = 40;
		
		
		int drawDistance = B_WIDTH / 100;
		
		Graphics2D g2=(Graphics2D)g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
		
		MapDataModel mapModel = mapData.getThisMap(beetles[beetleLock].getX(), beetles[beetleLock].getY());	
		
		
		int widthMax = B_WIDTH / 100;
		int heightMax = B_HEIGHT / 100;
		
		g.setColor(Color.BLACK);
		for(int i=0; i<B_HEIGHT; i+=drawDistance) {
			for(int j=0; j<B_WIDTH; j+=drawDistance) {
				g.fillRect((j / widthMax + posX), (i / heightMax + posY), 1, 1);
			}
		}
		
		//지나왔던 경로 그리기
		
		for(int i=0; i<B_HEIGHT; i+=drawDistance) {
			for (int j=0; j<B_WIDTH; j+=drawDistance) {
				if(mapData.isVisitCell(j, i)){
					g.setColor(CLR_HEAD[0]);
					g.fillRect(j / widthMax + posX, i / heightMax + posY, 1, 1);
				}
						
			}
		}
		
		//딱정벌레 그리기
		
		for(int i=0; i<beetles.length; i++){
			
			g.setColor(beetles[i].getBeetleColor());
			g.fillRect(beetles[i].getX() / widthMax + posX, beetles[i].getY() / heightMax + posY, 1, 1);
			
		}
		
		
		g.setColor(Color.RED);
		
		int tmpDrawX = mapModel.getX()/widthMax + posX;
		int tmpDrawY = mapModel.getY()/heightMax + posY;
		int tmpDrawWidth = tmpDrawX + mapModel.getwidth()/widthMax;
		int tmpDrawHeight = tmpDrawY + mapModel.getHeight()/heightMax;
		
		g.drawLine(tmpDrawX, tmpDrawY, tmpDrawWidth, tmpDrawY);
		g.drawLine(tmpDrawX, tmpDrawY, tmpDrawX, tmpDrawHeight);
		g.drawLine(tmpDrawWidth, tmpDrawY, tmpDrawWidth, tmpDrawHeight);
		g.drawLine(tmpDrawX, tmpDrawHeight, tmpDrawWidth, tmpDrawHeight);
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		
	}
	
	
	private void drawPauseScreen(Graphics g)
	{
		
		Font small = new Font("", Font.BOLD, 20);
		FontMetrics metr = getFontMetrics(small);
		
		g.setColor(Color.RED);
		g.setFont(small);
		String msg = "Pause";
		g.drawString(msg, (B_WIDTH*DOT_SIZE)/2 - metr.stringWidth(msg)/2,
				(B_HEIGHT*DOT_SIZE) / 2 - metr.getHeight());
	}
	
	
	
	private void drawTime(Graphics g)
	{
		
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = getFontMetrics(small);
		g.setColor(Color.WHITE);
		
		g.setFont(small);
		
		long defTime;
		
		if(_pause)
			defTime = (pauseTime - startTime);
		else
			defTime = (Calendar.getInstance().getTimeInMillis() - startTime);
		
		SimpleDateFormat formatter = new SimpleDateFormat ( "mm:ss:SSS", Locale.KOREA );
		String dTime = formatter.format ( defTime );
		
		endTimeString = dTime;
		
		g.drawString("경과 시간 : " + dTime,  10, metr.getHeight());
		
	}
	
	private void drawNoVisitCellCount(Graphics g, int noVisitCell) {
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = getFontMetrics(small);
		g.setColor(Color.WHITE);
		
		g.setFont(small);
		
		g.drawString("남은지점 : " + noVisitCell, 10, metr.getHeight()+14);
	}
	
	private void drawCurrentCordinate(Graphics g) {
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = getFontMetrics(small);
		g.setColor(Color.WHITE);
		
		g.setFont(small);
		
		g.drawString("현재 좌표 : " + beetles[beetleLock].getX() + " x " + beetles[beetleLock].getY(), 10, metr.getHeight()+28);
	}
	
	
	/**
	 * 딱정벌레를 좌표상에서 이동시킨다.
	 */
	private void move(int beetleNumber)
	{
		
		//이동했던 지점 저장
		mapData.setThisCellVisit(beetles[beetleNumber].getX(), beetles[beetleNumber].getY());
		
		//이동 할 지점
		if(beetles[beetleNumber].isDirection(Pos.LEFT))
			beetles[beetleNumber].set_X_Minus();
		if(beetles[beetleNumber].isDirection(Pos.RIGHT))
			beetles[beetleNumber].set_X_Plus();
		if(beetles[beetleNumber].isDirection(Pos.UP))
			beetles[beetleNumber].set_Y_Minus();
		if(beetles[beetleNumber].isDirection(Pos.DOWN))
			beetles[beetleNumber].set_Y_Plus();
		
		if(checkCollision(beetleNumber))
			return;
		
		//다음 랜덤 이동 지점
		beetles[beetleNumber].makeDirection();
		
		//이동 수 추가
		beetles[beetleNumber].addMoveCount();
		


	}
	
	/**
	 * 벽과 충돌 하였을 경우를 검사한다.
	 * @return {@link boolean}
	 */
	private boolean checkCollision(int beetleNumber)
	{
		
		if(beetles[beetleNumber].getY()+1 >= B_HEIGHT) {
			
			beetles[beetleNumber].makeDirection(Pos.UP);
			return true;
		}
		if(beetles[beetleNumber].getY()-1 < 0) {
			beetles[beetleNumber].makeDirection(Pos.DOWN);
			return true;
		}
		if(beetles[beetleNumber].getX()+1 >= B_WIDTH) {
			beetles[beetleNumber].makeDirection(Pos.LEFT);
			return true;
		}
		if(beetles[beetleNumber].getX()-1 < 0) {
			beetles[beetleNumber].makeDirection(Pos.RIGHT);
			return true;
		}
		
		return false;
	}
	
	
	
	private void quit()
	{
		timer.stop();
		
		_run = false;
		_pause = false;
		_end = true;
		
		
		//데이터 뷰 프레임과 결과 데이터 정보를 연동한다.
		endTime = Calendar.getInstance().getTimeInMillis();
		
		frame.getData().saveData(B_WIDTH, B_HEIGHT, endTimeString, beetleCount, DELAY);
		frame.syncWithData();
	}


	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		
		
		if(_run)
		{
			//이동
			if(!_pause){
				
				for(int i=0; i<beetles.length; i++){
					
					final int a = i;
					
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							move(a);							
						}
					}).start();
					
				}
					
			}
	
			repaint();
		}
	}
	
	public int getCurrentViewBeetle(){
		return beetleLock;
	}
	
	public void setBeetleLookView(int view){
		if(view > -1 && view < beetles.length)
			beetleLock = view;
	}
	
	public Color getCurrentViewBeetleColor(){
		return beetles[beetleLock].getBeetleColor();
	}
	
	public void setRunning(boolean runStatus){
		_run = runStatus;
	}
	
	public boolean isRunning(){
		return _run;
	}

	public boolean isPause(){
		return _pause;
	}
	
	public void setPause(boolean pauseStatus){
		pauseTime = Calendar.getInstance().getTimeInMillis();
		_pause = pauseStatus;
	}
	
	
	public void setDebugMode(boolean debugModeStatus){
		_debugMode = debugModeStatus;
	}
	
	public boolean isEnd() {
		return _end;
	}
	
	public int getDotSize() {
		return DOT_SIZE;
	}
	
	public int getMaxCells(){
		return B_WIDTH * B_HEIGHT;
	}
	
	public int getNoVisitCellsCount(){
		return mapData.getNoVisitCell();
	}

	

	
}
