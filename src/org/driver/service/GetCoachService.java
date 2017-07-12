package org.driver.service;

import java.util.List;

import org.driver.bean.Coach;
import org.driver.dao.GetCoachDao;
import org.driver.util.CoachUtil;

public class GetCoachService implements GetCoachDao{

	@Override
	public List<Coach> getCoachList(int firstResult, int maxResult) {
		CoachUtil coachUtil=new CoachUtil();
		return coachUtil.getCoachList(firstResult, maxResult).getList();
	}

	@Override
	public List<Coach> getAll() {
		CoachUtil coachUtil=new CoachUtil();
		return coachUtil.findAll();
	}

	@Override
	public Coach getById(String c_id) {
		CoachUtil coachUtil=new CoachUtil();
		return coachUtil.getById(c_id);
	}

}
