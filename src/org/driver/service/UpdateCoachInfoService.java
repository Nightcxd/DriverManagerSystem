package org.driver.service;

import org.driver.bean.Coach;
import org.driver.dao.UpdateCoachInfoDao;
import org.driver.util.CoachUtil;

public class UpdateCoachInfoService  implements UpdateCoachInfoDao{

	@Override
	public void update(Coach c) {
		CoachUtil cuCoachUtil=new CoachUtil();
		cuCoachUtil.update(c);
		
	}

}
