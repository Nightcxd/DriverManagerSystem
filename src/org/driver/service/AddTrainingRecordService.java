package org.driver.service;

import org.driver.bean.TrainingRecord;
import org.driver.dao.AddTrainingRecordDao;
import org.driver.util.TrainingRecordUtil;

public class AddTrainingRecordService implements AddTrainingRecordDao {

	@Override
	public void save(TrainingRecord tr) {
		TrainingRecordUtil tUtil=new TrainingRecordUtil();
		tUtil.save(tr);
	}

}
