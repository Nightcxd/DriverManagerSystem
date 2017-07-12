package org.driver.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import org.apache.log4j.Logger;
import org.driver.bean.Coach;
import org.driver.bean.DriverCar;
import org.driver.bean.Trainee;
import org.driver.bean.TrainingRecord;
import org.driver.service.GetTraineeService;
import org.driver.service.GetTrainingRecordService;
import org.driver.util.CoachUtil;
import org.driver.util.DriverCarUtil;
import org.driver.util.FileOperation;
import org.driver.util.TraineeUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

public class DriverManagerMainViews extends JFrame {
	private static final Logger logger = Logger.getLogger(DriverManagerMainViews.class);
	private JPanel contentPane;
    private static JFrame frame1=new DriverManagerMainViews();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					 frame1= new DriverManagerMainViews();
					 frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DriverManagerMainViews() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 537, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("录入学员信息");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CheckInTraineeInfoViews checkInTraineeInfoViews=new CheckInTraineeInfoViews();
				checkInTraineeInfoViews.setVisible(true);
				
				
			}
		});
		btnNewButton.setBounds(43, 41, 170, 35);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("录入教练信息");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CheckInCocahInfoViews checkInCocahInfoViews=new CheckInCocahInfoViews();
				checkInCocahInfoViews.setVisible(true);
				
			}
		});
		button.setBounds(278, 41, 170, 35);
		contentPane.add(button);
		
		JButton button_1 = new JButton("录入车辆信息");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CheckInDriverCarInfoViews carInfoViews=new CheckInDriverCarInfoViews();
				carInfoViews.setVisible(true);
				
			}
		});
		button_1.setBounds(43, 108, 170, 35);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("浏览学员信息");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ScanTraineeInfoViews scanTraineeInfoViews=new ScanTraineeInfoViews();
				scanTraineeInfoViews.setVisible(true);
				
			}
		});
		button_2.setBounds(278, 108, 170, 35);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("浏览教练信息");
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ScanCoachInfoViews scanCoachInfoViews=new ScanCoachInfoViews();
				scanCoachInfoViews.setVisible(true);
				
			}
		});
		button_3.setBounds(43, 179, 170, 35);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("浏览车辆信息");
		button_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ScanDriverCarInfoViews scanDriverCarInfoViews=new ScanDriverCarInfoViews();
				scanDriverCarInfoViews.setVisible(true);
				;
			}
		});
		button_4.setBounds(278, 179, 170, 35);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("添加练车记录");
		button_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CheckInTrainingRecordInfoViews checkInTrainingRecordInfoViews=new CheckInTrainingRecordInfoViews();
				checkInTrainingRecordInfoViews.setVisible(true);
				
			}
		});
		button_5.setBounds(43, 244, 170, 35);
		contentPane.add(button_5);
		
		JButton button_6 = new JButton("导出练车记录");
		button_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Result="学员姓名       证件号      练车日期      练车时长      教练姓名      车牌号\r\n";
				GetTrainingRecordService getTrainingRecordService=new GetTrainingRecordService();
				List<TrainingRecord> trList=getTrainingRecordService.getAll();
				for(TrainingRecord trainingRecord:trList){
					TraineeUtil tUtil=new TraineeUtil();
					Trainee t=tUtil.getById(trainingRecord.getT_id());
					CoachUtil cUtil=new CoachUtil();
					Coach coach=cUtil.getById(trainingRecord.getC_id());
					DriverCarUtil dUtil=new DriverCarUtil();
				    DriverCar dCar=dUtil.getById(trainingRecord.getDc_id());
					Result+=t.getT_name()+","+t.getT_idcard()+","+trainingRecord.getTr_date()+","+trainingRecord.getTr_duration()
							+","+coach.getC_name()+","+dCar.getDc_carNumber()+"\r\n";
				}
				try {
	    			  File file=new File("temp\\TrainingRecord.txt");
					  FileOperation.createFile(file);
					  FileOperation.writeTxtFile(""+Result+"", file);
					  JOptionPane.showMessageDialog(null, "导出成功！");
				} catch (Exception e1) {
					logger.error("异常信息---导出失败",e1);
					JOptionPane.showMessageDialog(null, "导出失败！");
				
				}
			}
		});
		button_6.setBounds(278, 244, 170, 35);
		contentPane.add(button_6);
		
		JButton button_7 = new JButton("统计信息");
		button_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CountInfoViews countInfoViews=new CountInfoViews();
				countInfoViews.setVisible(true);
				
			}
		});
		button_7.setBounds(43, 306, 170, 35);
		contentPane.add(button_7);
	}
}
