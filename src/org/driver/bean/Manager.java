package org.driver.bean;

/*
 * 驾校负责人
 */
public class Manager {
	private String m_id;//负责人id
	private String m_account;//负责人账户
	private String m_name;//负责人名字
	private String m_pwd;//负责人密码
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_account() {
		return m_account;
	}
	public void setM_account(String m_account) {
		this.m_account = m_account;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_pwd() {
		return m_pwd;
	}
	public void setM_pwd(String m_pwd) {
		this.m_pwd = m_pwd;
	}
	
}