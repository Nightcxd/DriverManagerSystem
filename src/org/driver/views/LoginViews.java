package org.driver.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JPasswordField;

import org.apache.log4j.Logger;
import org.driver.bean.Manager;
import org.driver.service.LoginService;
import org.driver.util.FileOperation;
import org.driver.util.ManagerUtil;

public class LoginViews extends JFrame {
	private static final Logger logger = Logger.getLogger(LoginViews.class);

	private JPanel contentPane;
	private JTextField m_account;
	private JPasswordField m_pwd;
	private static JFrame frame=new LoginViews() ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginViews();
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
	public LoginViews() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 255, 204));
		panel.setBounds(0, 0, 434, 31);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 255, 204));
		panel_1.setBounds(0, 230, 434, 31);
		contentPane.add(panel_1);
		
		m_account = new JTextField();
		m_account.setBounds(158, 74, 134, 31);
		contentPane.add(m_account);
		m_account.setColumns(10);
		
		m_pwd = new JPasswordField();
		m_pwd.setBounds(158, 154, 134, 28);
		contentPane.add(m_pwd);
		
		JLabel label = new JLabel("用户名：");
		label.setBounds(108, 85, 54, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("密 码：");
		label_1.setBounds(106, 154, 54, 15);
		contentPane.add(label_1);
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String username=m_account.getText().toString().trim();
				String password=m_pwd.getText().toString().trim();
				  LoginService loginService=new LoginService();
		    	  int m= loginService.check(username,password);
		    	  if(m==1){
		    		  JOptionPane.showMessageDialog(null, "登录成功！");
		    		  frame.dispose();
		    		  DriverManagerMainViews dmian=new DriverManagerMainViews();
		    		  dmian.setVisible(true);
		    		  try {
		    			  File file=new File("temp\\managerlogintemp.txt");
						  FileOperation.createFile(file);
						  FileOperation.writeTxtFile(""+username.trim()+","+password.trim()+"", file);
					} catch (Exception e) {
						logger.error("异常信息---临时信息记录失败",e);
					
					}
		    		  
		    	  }
		    	  else {
		    		  m_pwd.setText("");
		    		  JOptionPane.showMessageDialog(null, "用户名或者密码错误！");
		    	  }
			}
		});
		btnNewButton.setBounds(170, 197, 93, 23);
		contentPane.add(btnNewButton);
		
		
	}
}
