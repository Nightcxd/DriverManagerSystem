package org.driver.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;

import org.apache.log4j.Logger;
import org.driver.bean.Trainee;
import org.driver.service.AddTraineeService;
import org.driver.util.FileCanChooser;
import org.driver.util.GenerateSequenceUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class CheckInTraineeInfoViews extends JFrame {
	private static final Logger logger = Logger.getLogger(CheckInTraineeInfoViews.class);

	private JPanel contentPane;
	private JTextField t_name;
	private JTextField t_tel;
	private JTextField t_idCard;
	private JLabel label_2;
	JFileChooser fc = new JFileChooser();
	private JTextField t_icon;
	private JLabel label_3;
	private JTextField t_signTime;
	private JLabel label_4;
	private JTextField t_schooling;
	private JTextField t_trainStatus;
	private JLabel imgLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckInTraineeInfoViews frame = new CheckInTraineeInfoViews();
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
	public CheckInTraineeInfoViews() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 558, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		t_name = new JTextField();
		t_name.setBounds(71, 27, 137, 27);
		contentPane.add(t_name);
		t_name.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("姓 名：");
		lblNewLabel.setBounds(10, 33, 54, 15);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("性 别：");
		label.setBounds(10, 81, 54, 15);
		contentPane.add(label);
		
		
		 String sex[] = {"男","女"};
		 DefaultComboBoxModel category = new DefaultComboBoxModel(sex);
		final JComboBox comboBox = new JComboBox(category);
		comboBox.setBounds(74, 78, 88, 21);
		contentPane.add(comboBox);
		
		t_tel = new JTextField();
		t_tel.setColumns(10);
		t_tel.setBounds(71, 131, 137, 27);
		contentPane.add(t_tel);
		
		JLabel label_1 = new JLabel("手机号码：");
		label_1.setBounds(10, 137, 72, 15);
		contentPane.add(label_1);
		
		t_idCard = new JTextField();
		t_idCard.setColumns(10);
		t_idCard.setBounds(71, 181, 137, 27);
		contentPane.add(t_idCard);
		
		label_2 = new JLabel("证件号：");
		label_2.setBounds(10, 187, 72, 15);
		contentPane.add(label_2);
		
		t_icon = new JTextField();
		t_icon.setColumns(10);
		t_icon.setBounds(71, 232, 137, 27);
		contentPane.add(t_icon);
		
		label_3 = new JLabel("证件照：");
		label_3.setBounds(10, 238, 72, 15);
		contentPane.add(label_3);
		
		imgLabel = new JLabel("");
		imgLabel.setBounds(381, 10, 93, 111);
		contentPane.add(imgLabel);
		
		JButton button = new JButton("上传");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
//				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //只选择目录
				int intRetVal = fc.showOpenDialog(fc); 
			      if( intRetVal == JFileChooser.APPROVE_OPTION){ 
			        String iconUrl=fc.getSelectedFile().getPath().trim();
			        File f=new File(iconUrl);
			        FileCanChooser fileCanChooser=new FileCanChooser();
			        boolean ifisicon=fileCanChooser.accept(f);
			        if (ifisicon==true) {
						t_icon.setText(iconUrl);
						int width=93;
						int height=111;
						ImageIcon image = new ImageIcon(iconUrl);
				        image.setImage(image.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));
				        imgLabel.setIcon(image);
					}
			        else {
			        	JOptionPane.showMessageDialog(null, "图片格式不正确！");
					}
			      } 
			}
		});
		button.setBounds(218, 234, 66, 25);
		contentPane.add(button);
		
		t_signTime = new JTextField();
		t_signTime.setColumns(10);
		t_signTime.setBounds(351, 131, 137, 27);
		contentPane.add(t_signTime);
		
		label_4 = new JLabel("报名时间：");
		label_4.setBounds(291, 137, 72, 15);
		contentPane.add(label_4);
		
		t_schooling = new JTextField();
		t_schooling.setColumns(10);
		t_schooling.setBounds(351, 184, 137, 27);
		contentPane.add(t_schooling);
		
		JLabel label_5 = new JLabel("学费金额：");
		label_5.setBounds(291, 193, 72, 15);
		contentPane.add(label_5);
		
		t_trainStatus = new JTextField();
		t_trainStatus.setColumns(10);
		t_trainStatus.setBounds(351, 235, 137, 27);
		contentPane.add(t_trainStatus);
		
		JLabel label_6 = new JLabel("考试进度：");
		label_6.setBounds(291, 241, 72, 15);
		contentPane.add(label_6);
		
		JButton btnNewButton = new JButton("添加学员");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String t_id=GenerateSequenceUtil.generateSequenceNo();
				String name=t_name.getText().trim();
				String sex=comboBox.getSelectedItem().toString().trim();
				String tel=t_tel.getText().trim();
				String idCard=t_idCard.getText().trim();
				String icon=t_icon.getText().trim();
				String signTime=t_signTime.getText().trim();
				String schooling=t_schooling.getText().trim();
				String trainStatus=t_trainStatus.getText().trim();
				Trainee trainee=new Trainee();
				trainee.setT_id(t_id);
				trainee.setT_name(name);
				trainee.setT_icon(icon);
				trainee.setT_sex(sex);
				trainee.setT_tel(tel);
				trainee.setT_idcard(idCard);
				trainee.setT_signTime(signTime);
				trainee.setT_schooling(schooling);
				trainee.setT_trainStatus(trainStatus);
				AddTraineeService addTraineeService=new AddTraineeService();
				addTraineeService.sava(trainee);
				JOptionPane.showMessageDialog(null, "添加成功！");	
				t_name.setText("");
				t_tel.setText("");
				t_idCard.setText("");
				t_icon.setText("");
				t_signTime.setText("");
				t_trainStatus.setText("");
				t_schooling.setText("");
			}
		});
		btnNewButton.setBounds(218, 295, 93, 23);
		contentPane.add(btnNewButton);
		
		
	}
}
