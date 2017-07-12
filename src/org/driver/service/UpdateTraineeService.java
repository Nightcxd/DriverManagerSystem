package org.driver.service;

import org.driver.bean.Trainee;
import org.driver.dao.UpdateTraineeInfoDao;
import org.driver.util.TraineeUtil;

public class UpdateTraineeService implements UpdateTraineeInfoDao{

	@Override
	public void update(Trainee t) {
		TraineeUtil tUtil=new TraineeUtil();
		tUtil.update(t);
	}

}
