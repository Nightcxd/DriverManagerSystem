package org.driver.dao;

import java.util.List;

import org.driver.bean.Trainee;


public interface GetTraineeDao {
	public List<Trainee> getTraineeList(int firstResult, int maxResult);
	public List<Trainee> getAll();
}
