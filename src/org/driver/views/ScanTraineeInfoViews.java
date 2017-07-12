package org.driver.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import org.apache.log4j.Logger;
import org.driver.bean.Trainee;
import org.driver.service.GetTraineeService;
import org.driver.util.FileOperation;
import org.driver.util.TraineeUtil;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;


public class ScanTraineeInfoViews extends JFrame {
	private static final Logger logger = Logger.getLogger(ScanTraineeInfoViews.class);

	private JPanel contentPane;
	private JTable table;
	public int fromResult=0;
	private JButton button;
	private JButton button_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScanTraineeInfoViews frame = new ScanTraineeInfoViews();
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
	public ScanTraineeInfoViews() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 692, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		Object[][] cellData=null;
		String [] colName={"学员ID","学员姓名","学员性别","学员电话","考试进度"};
		final DefaultTableModel defaultTableModel=new DefaultTableModel(cellData, colName){
			public boolean isCellEditable(int row,int colunm){
				return false;
			}
		};
		table = new JTable(defaultTableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int selectrow=table.getSelectedRows().length;
				if (selectrow==1) {
					int selectedRowindex=table.getSelectedRow();
					String id=(String) table.getValueAt(selectedRowindex, 0);
					TraineeUtil traineeUtil=new TraineeUtil();
					Trainee trainee=traineeUtil.getById(id.trim());
					JOptionPane.showMessageDialog(null, "姓名："+trainee.getT_name()+"\r\n"
							+ "性别："+trainee.getT_sex()+"\r\n"
									+ "电话："+trainee.getT_tel()+"\r\n"
											+ "证件号："+trainee.getT_idcard()+"\r\n"
													+ "报名时间："+trainee.getT_signTime()+"\r\n"
															+ "学费金额："+trainee.getT_schooling()+"\r\n"
																	+ "考试进度："+trainee.getT_trainStatus()+"\r\n"
																			+ "证件照："+trainee.getT_icon()+"");
					 
					  					 
				}
				else{
					JOptionPane.showMessageDialog(null, "请选择一条信息！");
				}
			}
		});
		defaultTableModel.setRowCount(0);
		GetTraineeService getTraineeService=new GetTraineeService();
		List<Trainee> list=getTraineeService.getTraineeList(0, 10);
		for(Trainee t:list){
			String[] info=new String[5];
			info[0]=t.getT_id();
			info[1]=t.getT_name();
			info[2]=t.getT_sex();
			info[3]=t.getT_tel();
			info[4]=t.getT_trainStatus();
			
			defaultTableModel.addRow(info);
		}
		table.invalidate();
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setVisible(true);
		table.setBounds(45, 25, 596, 368);
		JScrollPane jsPane=new JScrollPane(table);
		jsPane.setBounds(45, 27, 376, 295);
		contentPane.add(jsPane);
		
		button = new JButton("上一页");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(fromResult==0){
					JOptionPane.showMessageDialog(null, "已是第一页！");	
				}
				else {
					GetTraineeService getTraineeService=new GetTraineeService();
					fromResult=fromResult-10;
					List<Trainee> list=getTraineeService.getTraineeList(fromResult, 10);
					defaultTableModel.setRowCount(0);
					for(Trainee t:list){
						String[] info=new String[5];
						info[0]=t.getT_id();
						info[1]=t.getT_name();
						info[2]=t.getT_sex();
						info[3]=t.getT_tel();
						info[4]=t.getT_trainStatus();
						
						defaultTableModel.addRow(info);
					}
					table.invalidate();
				}
			}
		});
		button.setBounds(101, 353, 93, 23);
		contentPane.add(button);
		
		button_1 = new JButton("下一页");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				GetTraineeService getTraineeService=new GetTraineeService();
				fromResult=fromResult+10;
				List<Trainee> list=getTraineeService.getTraineeList(fromResult, 10);
				defaultTableModel.setRowCount(0);
				for(Trainee t:list){
					String[] info=new String[5];
					info[0]=t.getT_id();
					info[1]=t.getT_name();
					info[2]=t.getT_sex();
					info[3]=t.getT_tel();
					info[4]=t.getT_trainStatus();
					
					defaultTableModel.addRow(info);
				}
				table.invalidate();
			}
		});
		button_1.setBounds(273, 353, 93, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("修改信息");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int selectrow=table.getSelectedRows().length;
				if (selectrow==1) {
					int selectedRowindex=table.getSelectedRow();
					String id=(String) table.getValueAt(selectedRowindex, 0);
					try {
						  File file=new File("temp\\traineeInfo.txt");
						  FileOperation.createFile(file);
						  FileOperation.writeTxtFile(""+id+"", file);
						  UpdateTraineeInfoViews traineeInfoViews=new UpdateTraineeInfoViews();
						  traineeInfoViews.setVisible(true);
					} catch (Exception e) {
						logger.error("异常信息---保存失败",e);
					}

				}
			}
		});
		button_2.setBounds(498, 86, 130, 30);
		contentPane.add(button_2);
		
	}
}
