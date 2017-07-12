package org.driver.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import org.driver.bean.Coach;
import org.driver.service.AddCoachService;
import org.driver.util.FileCanChooser;
import org.driver.util.GenerateSequenceUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JLabel;

public class CheckInCocahInfoViews extends JFrame {

	private JPanel contentPane;
	private JTextField c_name;
	private JTextField c_tel;
	private JTextField c_icon;
	JFileChooser fc = new JFileChooser();
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JButton button_1;
	private JLabel imgLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckInCocahInfoViews frame = new CheckInCocahInfoViews();
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
	public CheckInCocahInfoViews() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 554, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		c_name = new JTextField();
		c_name.setBounds(85, 31, 131, 30);
		contentPane.add(c_name);
		c_name.setColumns(10);
		
		c_tel = new JTextField();
		c_tel.setColumns(10);
		c_tel.setBounds(85, 88, 131, 30);
		contentPane.add(c_tel);
		
		c_icon = new JTextField();
		c_icon.setColumns(10);
		c_icon.setBounds(85, 149, 131, 30);
		contentPane.add(c_icon);
		
		JButton button = new JButton("上传");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int intRetVal = fc.showOpenDialog(fc); 
			      if( intRetVal == JFileChooser.APPROVE_OPTION){ 
			        String iconUrl=fc.getSelectedFile().getPath().trim();
			        File f=new File(iconUrl);
			        FileCanChooser fileCanChooser=new FileCanChooser();
			        boolean ifisicon=fileCanChooser.accept(f);
			        if (ifisicon==true) {
			        	c_icon.setText(iconUrl);
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
		button.setBounds(224, 152, 93, 23);
		contentPane.add(button);
		
		label = new JLabel("姓 名：");
		label.setBounds(22, 38, 54, 15);
		contentPane.add(label);
		
		label_1 = new JLabel("电 话：");
		label_1.setBounds(22, 95, 54, 15);
		contentPane.add(label_1);
		
		label_2 = new JLabel("证件照：");
		label_2.setBounds(21, 156, 54, 15);
		contentPane.add(label_2);
		
		imgLabel = new JLabel("");
		imgLabel.setBounds(381, 10, 93, 111);
		contentPane.add(imgLabel);
		
		button_1 = new JButton("添加教练");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String c_id=GenerateSequenceUtil.generateSequenceNo();
				String name=c_name.getText().trim();
				String icon=c_icon.getText().trim();
				String tel=c_tel.getText().trim();
				Coach c=new Coach();
				c.setC_id(c_id);
				c.setC_name(name);
				c.setC_tel(tel);
				c.setC_icon(icon);
				AddCoachService addCoachService=new AddCoachService();
				addCoachService.save(c);
				JOptionPane.showMessageDialog(null, "添加成功！");	
				c_name.setText("");
				c_icon.setText("");
				c_tel.setText("");
				
			}
		});
		button_1.setBounds(224, 252, 93, 23);
		contentPane.add(button_1);
	}

}
