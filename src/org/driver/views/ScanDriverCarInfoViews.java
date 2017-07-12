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
import org.driver.bean.Coach;
import org.driver.bean.DriverCar;
import org.driver.bean.Trainee;
import org.driver.service.GetCoachService;
import org.driver.service.GetDriverCarService;
import org.driver.service.GetTraineeService;
import org.driver.util.DriverCarUtil;
import org.driver.util.FileOperation;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class ScanDriverCarInfoViews extends JFrame {
	private static final Logger logger = Logger.getLogger(ScanDriverCarInfoViews.class);

	private JPanel contentPane;
	private JTable table;
	public int fromResult=0;
	private JButton button;
	private JButton button_1;
	private JButton button_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScanDriverCarInfoViews frame = new ScanDriverCarInfoViews();
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
	public ScanDriverCarInfoViews() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 692, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		Object[][] cellData=null;
		String [] colName={"车辆ID","车牌号","车型","车龄"};
		final DefaultTableModel defaultTableModel=new DefaultTableModel(cellData, colName){
			public boolean isCellEditable(int row,int colunm){
				return false;
			}
		};
		table = new JTable(defaultTableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int selectrow=table.getSelectedRows().length;
				if (selectrow==1) {
					int selectedRowindex=table.getSelectedRow();
					String dc_id=(String) table.getValueAt(selectedRowindex, 0);
					DriverCarUtil driverCarUtil=new DriverCarUtil();
					DriverCar dCar=driverCarUtil.getById(dc_id.trim());
					JOptionPane.showMessageDialog(null, "车牌号："+dCar.getDc_carNumber()+"\r\n"
							+"车型："+dCar.getDc_type()+"\r\n"
							+"车龄："+dCar.getDc_age());
					  					 
				}
				else{
					JOptionPane.showMessageDialog(null, "请选择一条信息！");
				}
			}
		});
		defaultTableModel.setRowCount(0);
		GetDriverCarService getDriverCarService=new GetDriverCarService();
		List<DriverCar> list=getDriverCarService.getDriverCarList(0, 10);
		for(DriverCar t:list){
			String[] info=new String[4];
			info[0]=t.getDc_id();
			info[1]=t.getDc_carNumber();
			info[2]=t.getDc_type();
			info[3]=t.getDc_age();
			defaultTableModel.addRow(info);
		}
		table.invalidate();
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setVisible(true);
		table.setBounds(45, 25, 596, 368);
		JScrollPane jsPane=new JScrollPane(table);
		jsPane.setBounds(41, 38, 305, 297);
		contentPane.add(jsPane);
		
		button = new JButton("上一页");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(fromResult==0){
					JOptionPane.showMessageDialog(null, "已是第一页！");	
				}
				else {
					fromResult=fromResult-10;
					GetDriverCarService getDriverCarService=new GetDriverCarService();
					List<DriverCar> list=getDriverCarService.getDriverCarList(fromResult, 10);
					for(DriverCar t:list){
						String[] info=new String[3];
						info[0]=t.getDc_carNumber();
						info[1]=t.getDc_type();
						info[2]=t.getDc_age();
						defaultTableModel.addRow(info);
					}
					table.invalidate();
				}
			}
		});
		button.setBounds(84, 345, 93, 23);
		contentPane.add(button);
		
		button_1 = new JButton("下一页");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				GetDriverCarService getDriverCarService=new GetDriverCarService();
				fromResult=fromResult+10;
				List<DriverCar> list=getDriverCarService.getDriverCarList(fromResult, 10);
				for(DriverCar t:list){
					String[] info=new String[3];
					info[0]=t.getDc_carNumber();
					info[1]=t.getDc_type();
					info[2]=t.getDc_age();
					defaultTableModel.addRow(info);
				}
				table.invalidate();
			}
		});
		button_1.setBounds(239, 345, 93, 23);
		contentPane.add(button_1);
		
		button_2 = new JButton("修改信息");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectrow=table.getSelectedRows().length;
				if (selectrow==1) {
					int selectedRowindex=table.getSelectedRow();
					String id=(String) table.getValueAt(selectedRowindex, 0);
					try {
						  File file=new File("temp\\DriverCarInfo.txt");
						  FileOperation.createFile(file);
						  FileOperation.writeTxtFile(""+id+"", file);
						  UpdateDriverCarInfoViews driverCarInfoViews=new UpdateDriverCarInfoViews();
						  driverCarInfoViews.setVisible(true);
					} catch (Exception e1) {
						logger.error("异常信息---保存失败",e1);
					}

				}
			}
		});
		button_2.setBounds(441, 157, 93, 23);
		contentPane.add(button_2);
		
	}

}
