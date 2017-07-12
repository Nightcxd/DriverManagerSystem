package org.driver.dao;

import java.util.List;

import org.driver.bean.DriverCar;

public interface GetDriverCarDao {
		public List<DriverCar> getDriverCarList(int firstResult, int maxResult);
		public List<DriverCar> getAll();
		public DriverCar getById(String dc_id);
}
