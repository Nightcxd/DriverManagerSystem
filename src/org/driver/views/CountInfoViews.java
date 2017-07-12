package org.driver.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import org.apache.log4j.Logger;
import org.driver.bean.Trainee;
import org.driver.bean.TrainingRecord;
import org.driver.service.GetTraineeService;
import org.driver.service.GetTrainingRecordService;
import org.driver.util.TraineeUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CountInfoViews extends JFrame {
	private static final Logger logger = Logger.getLogger(CountInfoViews.class);
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CountInfoViews frame = new CountInfoViews();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CountInfoViews() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 614, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final DateChooserJButton fromTime = new DateChooserJButton();
		fromTime.setBounds(147, 110, 104, 51);
		contentPane.add(fromTime);
		
		JLabel label = new JLabel("起始时间");
		label.setBounds(56, 119, 89, 33);
		contentPane.add(label);
		
		final DateChooserJButton toTime = new DateChooserJButton();
		toTime.setBounds(388, 110, 104, 51);
		contentPane.add(toTime);
		
		JLabel label_1 = new JLabel("终止时间");
		label_1.setBounds(315, 119, 89, 33);
		contentPane.add(label_1);
		
		JButton btnNewButton = new JButton("统计");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String fromdate=fromTime.getText().trim();
				String todate=toTime.getText().trim();
				int time=0;
				int money=0;
				try{
				GetTrainingRecordService getTrainingRecordService=new GetTrainingRecordService();
				List<TrainingRecord> trList=getTrainingRecordService.getRecordByDate(fromdate,todate);
				for(TrainingRecord tr:trList){
					TraineeUtil tUtil=new TraineeUtil();
					Trainee trainee=tUtil.getById(tr.getT_id());
					time+=Integer.parseInt(tr.getTr_duration());
					money+=Integer.parseInt(trainee.getT_schooling());
				}
				logger.info("练车总时间；\r\n"+time+"分钟\r\n"+"驾校报名总收入：\r\n"+money);
				JOptionPane.showMessageDialog(null, "练车总时间；\r\n"+time+"分钟\r\n"+"驾校报名总收入：\r\n"+money);	
				}
				catch(Exception e1){
					logger.error("异常信息位置---",e1);
					JOptionPane.showMessageDialog(null, "统计失败！");	
				}
				
			}
		});
		btnNewButton.setBounds(250, 220, 79, 37);
		contentPane.add(btnNewButton);
		
		
	}
}
