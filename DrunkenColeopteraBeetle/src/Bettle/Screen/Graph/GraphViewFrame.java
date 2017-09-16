package Bettle.Screen.Graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;

import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.jfree.ui.TextAnchor;

import Bettle.model.data.ResultData;
import Bettle.model.data.ResultDataModel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Rectangle;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;

public class GraphViewFrame extends JFrame{

	ChartPanel chartPanel = null;
	private JComboBox cmBoxMapSize;
	private JComboBox cmBoxBeetleCounts;
	private JCheckBox chckbxMapSize;
	private JCheckBox chckbxBeetleCounts;
	private JCheckBox chckbxResultTime;
	
	public GraphViewFrame(ResultData resultData) {
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(200, 200));
		panel.setMinimumSize(new Dimension(200, 200));
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		panel.setBounds(new Rectangle(0, 0, 200, 0));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GraphSetting");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 12));
		lblNewLabel.setBounds(5, 10, 113, 15);
		panel_1.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(5, 28, 120, 15);
		panel_1.add(separator);
		
		JLabel label = new JLabel("표시 할 데이터");
		label.setFont(new Font("굴림", Font.BOLD, 12));
		label.setBounds(10, 35, 112, 15);
		panel_1.add(label);
		
		chckbxMapSize = new JCheckBox("맵 크기");
		chckbxMapSize.setSelected(true);
		chckbxMapSize.setBounds(5, 49, 115, 23);
		panel_1.add(chckbxMapSize);
		
		chckbxBeetleCounts = new JCheckBox("딱정 벌레 수");
		chckbxBeetleCounts.setSelected(true);
		chckbxBeetleCounts.setBounds(5, 74, 115, 23);
		panel_1.add(chckbxBeetleCounts);
		
		chckbxResultTime = new JCheckBox("소요 시간");
		chckbxResultTime.setSelected(true);
		chckbxResultTime.setBounds(5, 99, 115, 23);
		panel_1.add(chckbxResultTime);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(5, 128, 120, 15);
		panel_1.add(separator_1);
		
		JLabel label_1 = new JLabel("데이터 정리");
		label_1.setFont(new Font("굴림", Font.BOLD, 12));
		label_1.setBounds(10, 135, 112, 15);
		panel_1.add(label_1);
		
		JLabel lblNewLabel_1 = new JLabel("맵 크기");
		lblNewLabel_1.setBounds(5, 153, 40, 15);
		panel_1.add(lblNewLabel_1);
		
		JLabel label_2 = new JLabel("딱정 벌레 수");
		label_2.setBounds(5, 203, 75, 15);
		panel_1.add(label_2);
		
		cmBoxMapSize = new JComboBox();
		cmBoxMapSize.setModel(new DefaultComboBoxModel(new String[] {"전체보기"}));
		cmBoxMapSize.setBounds(15, 172, 103, 21);
		panel_1.add(cmBoxMapSize);
		
		cmBoxBeetleCounts = new JComboBox();
		cmBoxBeetleCounts.setModel(new DefaultComboBoxModel(new String[] {"전체보기"}));
		cmBoxBeetleCounts.setBounds(15, 226, 103, 21);
		panel_1.add(cmBoxBeetleCounts);
		
		Component horizontalStrut = Box.createHorizontalStrut(130);
		horizontalStrut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(horizontalStrut, BorderLayout.NORTH);
		
		chartPanel = new ChartPanel(getChart(resultData));
		getContentPane().add(chartPanel, BorderLayout.CENTER);
		
		show();
		
	}
	
	public void reDrawGraph(ResultData resultData){
		
		chartPanel.removeAll();
		chartPanel.setChart(getChart(resultData));
		
	}
	
	public JFreeChart getChart(ResultData resultData) {
	      
        // 데이터 생성
        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();          // bar chart 1
        DefaultCategoryDataset dataset12 = new DefaultCategoryDataset();         // bar chart 2
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();          // line chart 1
 
        
        int length = resultData.dataCount();
		
		for(int i=0; i<length; i++){
			try{
				ResultDataModel modelData = resultData.getData()[i];
				
				dataset12.addValue(modelData.getWidth(), "맵 사이즈", String.valueOf(i+1) +"회");
				dataset1.addValue(modelData.getBeetleCount(), "딱정 벌레 수", String.valueOf(i+1) +"회");
				dataset2.addValue(modelData.getTime(), "결과 시간(ms)", String.valueOf(i+1) +"회");
				
			}catch (Exception e){
				//ignore
			}
		}
 
        // 렌더링 생성 및 세팅
        // 렌더링 생성
        final BarRenderer renderer = new BarRenderer();
        final BarRenderer renderer12 = new BarRenderer();
        final LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
       
        // 공통 옵션 정의
        final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
        final ItemLabelPosition p_center = new ItemLabelPosition(
                ItemLabelAnchor.CENTER, TextAnchor.CENTER
            );
        final ItemLabelPosition p_below = new ItemLabelPosition(
                     ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT
                     );
        Font f = new Font("Gulim", Font.BOLD, 14);
        Font axisF = new Font("Gulim", Font.PLAIN, 14);
       
        // 렌더링 세팅
        // 그래프 1
        renderer.setBaseItemLabelGenerator(generator);
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBasePositiveItemLabelPosition(p_center);
        renderer.setBaseItemLabelFont(f);
        renderer.setGradientPaintTransformer(new StandardGradientPaintTransformer(
                GradientPaintTransformType.VERTICAL));
        renderer.setSeriesPaint(0, new GradientPaint(1.0f, 1.0f, Color.white, 0.0f, 0.0f, Color.blue));
        renderer.setSeriesPaint(0, new Color(0,162,255));
 
        // 그래프 2       
        renderer12.setSeriesPaint(0, new Color(232,168,255));
        renderer12.setBaseItemLabelFont(f);
        renderer12.setBasePositiveItemLabelPosition(p_center);
        renderer12.setBaseItemLabelGenerator(generator);
        renderer12.setBaseItemLabelsVisible(true);
       
        // 그래프 3       
        renderer2.setBaseItemLabelGenerator(generator);
        renderer2.setBaseItemLabelsVisible(true);
        renderer2.setBaseShapesVisible(true);
        renderer2.setDrawOutlines(true);
        renderer2.setUseFillPaint(true);
        renderer2.setBaseFillPaint(Color.WHITE);
        renderer2.setBaseItemLabelFont(f);
        renderer2.setBasePositiveItemLabelPosition(p_below);
        renderer2.setSeriesPaint(0,new Color(219,121,22));
        renderer2.setSeriesStroke(0,new BasicStroke(
                                               2.0f,
                                               BasicStroke.CAP_ROUND,
                                               BasicStroke.JOIN_ROUND,
                                               3.0f)
        );
       
        // plot 생성
        final CategoryPlot plot = new CategoryPlot();
       
        // plot 에 데이터 적재
        plot.setDataset(dataset1);
        plot.setRenderer(renderer);
        plot.setDataset(1,dataset12);
        plot.setRenderer(1,renderer12);
        plot.setDataset(2, dataset2);
        plot.setRenderer(2, renderer2);
 
        // plot 기본 설정
        plot.setOrientation(PlotOrientation.VERTICAL);             // 그래프 표시 방향
        plot.setRangeGridlinesVisible(true);                       // X축 가이드 라인 표시여부
        plot.setDomainGridlinesVisible(true);                      // Y축 가이드 라인 표시여부
 
        // 렌더링 순서 정의 : dataset 등록 순서대로 렌더링 ( 즉, 먼저 등록한게 아래로 깔림 )
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
       
        // X축 세팅
        plot.setDomainAxis(new CategoryAxis());              // X축 종류 설정
        plot.getDomainAxis().setTickLabelFont(axisF); // X축 눈금라벨 폰트 조정
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD);       // 카테고리 라벨 위치 조정
 
        // Y축 세팅
        plot.setRangeAxis(new NumberAxis());                 // Y축 종류 설정
        plot.getRangeAxis().setTickLabelFont(axisF);  // Y축 눈금라벨 폰트 조정
       
        // 세팅된 plot을 바탕으로 chart 생성
        final JFreeChart chart = new JFreeChart(plot);
//        chart.setTitle("Overlaid Bar Chart"); // 차트 타이틀
//        TextTitle copyright = new TextTitle("JFreeChart WaferMapPlot", new Font("SansSerif", Font.PLAIN, 9));
//        copyright.setHorizontalAlignment(HorizontalAlignment.RIGHT);
//        chart.addSubtitle(copyright);  // 차트 서브 타이틀
        return chart;
    }
}
