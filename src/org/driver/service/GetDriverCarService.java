package org.driver.service;

import java.util.List;

import org.driver.bean.DriverCar;
import org.driver.dao.GetDriverCarDao;
import org.driver.util.DriverCarUtil;

public class GetDriverCarService implements GetDriverCarDao{

	@Override
	public List<DriverCar> getDriverCarList(int firstResult, int maxResult) {
		DriverCarUtil dcCarUtil=new DriverCarUtil();
		return dcCarUtil.getDriverCarList(firstResult, maxResult).getList();
	}

	@Override
	public List<DriverCar> getAll() {
		DriverCarUtil dcCarUtil=new DriverCarUtil();
		return dcCarUtil.findAll();
	}

	@Override
	public DriverCar getById(String dc_id) {
		DriverCarUtil dcCarUtil=new DriverCarUtil();
		return dcCarUtil.getById(dc_id);
	}

}
