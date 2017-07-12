package org.driver.service;

import org.driver.bean.DriverCar;
import org.driver.dao.UpdateDriverCarInfoDao;
import org.driver.util.DriverCarUtil;

public class UpdateDriverCarInfoService implements UpdateDriverCarInfoDao{

	@Override
	public void update(DriverCar dc) {
		DriverCarUtil driverCarUtil=new DriverCarUtil();
		driverCarUtil.update(dc);
		
	}

}
