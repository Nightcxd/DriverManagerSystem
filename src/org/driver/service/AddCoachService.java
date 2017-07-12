package org.driver.service;

import org.driver.bean.Coach;
import org.driver.dao.AddCoachDao;
import org.driver.util.CoachUtil;

public class AddCoachService  implements AddCoachDao {

	@Override
	public void save(Coach c) {
		CoachUtil cUtil=new CoachUtil();
		cUtil.save(c);
	}

}
