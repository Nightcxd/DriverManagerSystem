package org.driver.service;

import java.util.List;

import org.driver.bean.TrainingRecord;
import org.driver.dao.GetTrainingRecordDao;
import org.driver.util.TrainingRecordUtil;

public class GetTrainingRecordService implements GetTrainingRecordDao{

	@Override
	public List<TrainingRecord> getAll() {
		TrainingRecordUtil trainingRecordUtil=new TrainingRecordUtil();
		return trainingRecordUtil.getAll();
	}

	@Override
	public List<TrainingRecord> getRecordByDate(String fromDate,String toDate) {
		TrainingRecordUtil trainingRecordUtil=new TrainingRecordUtil();
		return trainingRecordUtil.getRecordByDate(fromDate.trim(),toDate.trim());
	}

}
