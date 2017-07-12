package org.driver.service;

import org.driver.bean.DriverCar;
import org.driver.dao.AddDriverCarDao;
import org.driver.util.DriverCarUtil;

public class AddDriverCarService implements AddDriverCarDao {

	@Override
	public void save(DriverCar d) {
		DriverCarUtil dCarUtil=new DriverCarUtil();
		dCarUtil.save(d);
	}

}
