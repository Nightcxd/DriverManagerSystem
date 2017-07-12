package org.driver.service;

import org.driver.bean.Trainee;
import org.driver.dao.AddTraineeDao;
import org.driver.util.TraineeUtil;

public class AddTraineeService implements AddTraineeDao{

	@Override
	public void sava(Trainee t) {
		TraineeUtil tUtil=new TraineeUtil();
		tUtil.save(t);
	}

}
