package org.driver.service;

import org.driver.dao.LoginDao;
import org.driver.util.ManagerUtil;

public class LoginService implements LoginDao{

	@Override
	public int check(String m_account, String m_pwd) {
		ManagerUtil mUtil=new ManagerUtil();
		int result=mUtil.check(m_account, m_pwd);
		if (result==1) {
			return 1;
		}
		else {
			return 0;
		}
		
	}

}
