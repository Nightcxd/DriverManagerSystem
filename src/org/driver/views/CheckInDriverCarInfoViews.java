package org.driver.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;

import org.driver.bean.DriverCar;
import org.driver.service.AddDriverCarService;
import org.driver.util.GenerateSequenceUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CheckInDriverCarInfoViews extends JFrame {

	private JPanel contentPane;
	private JTextField dc_carNumber;
	private JTextField dc_age;
	private JLabel label;
	private JLabel label_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckInDriverCarInfoViews frame = new CheckInDriverCarInfoViews();
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
	public CheckInDriverCarInfoViews() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 553, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		dc_carNumber = new JTextField();
		dc_carNumber.setBounds(104, 33, 121, 32);
		contentPane.add(dc_carNumber);
		dc_carNumber.setColumns(10);
		
		String type[] = {"小型","中型","大型"};
		DefaultComboBoxModel category = new DefaultComboBoxModel(type);
		final JComboBox dc_type = new JComboBox(category);
		dc_type.setBounds(114, 93, 98, 32);
		contentPane.add(dc_type);
		
		dc_age = new JTextField();
		dc_age.setColumns(10);
		dc_age.setBounds(104, 159, 121, 32);
		contentPane.add(dc_age);
		
		JLabel lblNewLabel = new JLabel("车牌号：");
		lblNewLabel.setBounds(29, 41, 54, 15);
		contentPane.add(lblNewLabel);
		
		label = new JLabel("车 型：");
		label.setBounds(29, 102, 54, 15);
		contentPane.add(label);
		
		label_1 = new JLabel("车 龄：");
		label_1.setBounds(29, 167, 54, 15);
		contentPane.add(label_1);
		
		JButton button = new JButton("添加车辆");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String carNumber=dc_carNumber.getText().trim();
				String carType=dc_type.getSelectedItem().toString().trim();
				String age=dc_age.getText().trim();
				DriverCar dCar=new DriverCar();
				dCar.setDc_id(GenerateSequenceUtil.generateSequenceNo());
				dCar.setDc_carNumber(carNumber);
				dCar.setDc_type(carType);
				dCar.setDc_age(age);
				AddDriverCarService aDriverCarService=new AddDriverCarService();
				aDriverCarService.save(dCar);
				JOptionPane.showMessageDialog(null, "添加成功！");	
				dc_carNumber.setText("");
				dc_age.setText("");
			}
		});
		button.setBounds(218, 247, 93, 23);
		contentPane.add(button);
	}
}
