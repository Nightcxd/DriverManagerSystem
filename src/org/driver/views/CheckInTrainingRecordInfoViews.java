package org.driver.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import org.apache.log4j.Logger;
import org.driver.bean.Coach;
import org.driver.bean.DriverCar;
import org.driver.bean.Trainee;
import org.driver.bean.TrainingRecord;
import org.driver.service.AddTrainingRecordService;
import org.driver.service.GetCoachService;
import org.driver.service.GetDriverCarService;
import org.driver.service.GetTraineeService;
import org.driver.util.GenerateSequenceUtil;

import com.mysql.jdbc.StreamingNotifiable;

import javax.swing.ComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CheckInTrainingRecordInfoViews extends JFrame {
	private static final Logger logger = Logger.getLogger(CheckInTrainingRecordInfoViews.class);
	private JPanel contentPane;
	private List<Trainee> tList=null;
	private List<Coach> cList=null;
	private List<DriverCar> dcList=null;
	private JTextField date;
	private JTextField duration;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckInTrainingRecordInfoViews frame = new CheckInTrainingRecordInfoViews();
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
	public CheckInTrainingRecordInfoViews() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("请选择学员：");
		label.setBounds(33, 58, 91, 30);
		contentPane.add(label);
		
		 //学员下拉框
		 GetTraineeService traineeService=new GetTraineeService();
		 tList=traineeService.getAll();
		 String traineeNameList[];
		 String temp="";
		 for(Trainee t:tList){
			 temp+=""+t.getT_name()+",";
		 }
		 traineeNameList=temp.split("\\,");
		 DefaultComboBoxModel category = new DefaultComboBoxModel(traineeNameList);
		 final JComboBox tnameCombox = new JComboBox(category);
		 tnameCombox.setBounds(121, 61, 158, 25);
		 contentPane.add(tnameCombox);
		
		
		//教练下拉框
		String CoachNameList[];
		 String ctemp="";
		 GetCoachService getCoachService=new GetCoachService();
		  cList=getCoachService.getAll();
		 for(Coach c:cList){
			 ctemp+=""+c.getC_name()+",";
		 }
		 CoachNameList=ctemp.split("\\,");
		 DefaultComboBoxModel category1 = new DefaultComboBoxModel(CoachNameList);
		final JComboBox cnamecomboBox = new JComboBox(category1);
		cnamecomboBox.setBounds(121, 135, 158, 25);
		contentPane.add(cnamecomboBox);
		
		JLabel label_1 = new JLabel("请选择教练：");
		label_1.setBounds(33, 135, 91, 30);
		contentPane.add(label_1);
		
		//车辆下拉框
		String dcNameList[];
		String dcTemp="";
		GetDriverCarService getDriverCarService=new GetDriverCarService();
		dcList=getDriverCarService.getAll();
		for(DriverCar d:dcList){
			dcTemp+=""+d.getDc_carNumber()+",";
		}
		dcNameList=dcTemp.split("\\,");
		DefaultComboBoxModel category2 = new DefaultComboBoxModel(dcNameList);
		final JComboBox dccomboBox = new JComboBox(category2);
		dccomboBox.setBounds(121, 205, 158, 25);
		contentPane.add(dccomboBox);
		
		JLabel label_2 = new JLabel("请选择车辆：");
		label_2.setBounds(33, 205, 91, 30);
		contentPane.add(label_2);
		
		date = new JTextField();
		date.setBounds(426, 59, 134, 30);
		contentPane.add(date);
		date.setColumns(10);
		
		duration = new JTextField();
		duration.setColumns(10);
		duration.setBounds(426, 135, 134, 30);
		contentPane.add(duration);
		
		JLabel label_3 = new JLabel("练车日期：");
		label_3.setBounds(343, 66, 73, 15);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("练车时长：");
		label_4.setBounds(343, 145, 73, 15);
		contentPane.add(label_4);
		
		JButton button = new JButton("添加练车记录");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//获取选中的学员id
				String t_id="";
				int tindex=tnameCombox.getSelectedIndex();
				int tcount=tList.size();
				for(int i=0;i<tcount;i++){
					if (i==tindex) {
						t_id=tList.get(i).getT_id();
						logger.info("获取到学员Id"+t_id);
					}
				}
				//获取选中的教练id
				String c_id="";
				int cindex=cnamecomboBox.getSelectedIndex();
				int ccount=cList.size();
				for(int i=0;i<ccount;i++){
					if (i==cindex) {
						c_id=cList.get(i).getC_id();
						logger.info("获取到教练Id"+c_id);
					}
				}
				
				//获取选中的车辆id
				String dc_id="";
				int dcindex=dccomboBox.getSelectedIndex();
				int dccount=dcList.size();
				for(int i=0;i<dccount;i++){
					if (i==dcindex) {
						dc_id=dcList.get(i).getDc_id();
						logger.info("获取到车辆Id"+dc_id);
					}
				}
				
				//获取练车日期
				String tr_date=date.getText().trim();
				
				//获取练车时长
				String tr_duration=duration.getText().trim();
				
				TrainingRecord trainingRecord=new TrainingRecord();
				trainingRecord.setTr_id(GenerateSequenceUtil.generateSequenceNo());
				trainingRecord.setC_id(c_id);
				trainingRecord.setT_id(t_id);
				trainingRecord.setDc_id(dc_id);
				trainingRecord.setTr_date(tr_date);
				trainingRecord.setTr_duration(tr_duration);
				AddTrainingRecordService addTrainingRecordService=new AddTrainingRecordService();
				addTrainingRecordService.save(trainingRecord);
				JOptionPane.showMessageDialog(null, "添加成功！");	
				
			}
		});
		button.setBounds(272, 284, 114, 39);
		contentPane.add(button);
		
		JLabel label_5 = new JLabel("(分钟)");
		label_5.setBounds(570, 140, 54, 15);
		contentPane.add(label_5);
	}
}
