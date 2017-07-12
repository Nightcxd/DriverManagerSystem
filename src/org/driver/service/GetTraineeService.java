package org.driver.service;

import java.util.List;

import org.driver.bean.Trainee;
import org.driver.dao.GetTraineeDao;
import org.driver.util.TraineeUtil;

public class GetTraineeService implements GetTraineeDao{

	@Override
	public List<Trainee> getTraineeList(int firstResult, int maxResult) {
		TraineeUtil traineeUtil=new TraineeUtil();
		return traineeUtil.getTraineeList(firstResult, maxResult).getList();
	}

	@Override
	public List<Trainee> getAll() {
		TraineeUtil tUtil=new TraineeUtil();
		return tUtil.getAll();
	}


}
