package org.driver.dao;

import java.util.List;

import org.driver.bean.Coach;



public interface GetCoachDao {
    public List<Coach> getCoachList(int firstResult, int maxResult);
    public List<Coach> getAll();
    public Coach getById(String c_id);
}
