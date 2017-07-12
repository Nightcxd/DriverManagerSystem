package org.driver.dao;

import java.util.List;

import org.driver.bean.TrainingRecord;

public interface GetTrainingRecordDao {
	public List<TrainingRecord> getAll();
	public List<TrainingRecord> getRecordByDate(String fromDate,String toDate);
}
