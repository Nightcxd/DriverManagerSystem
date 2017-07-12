package org.driver.bean;

/*
 * 练车记录
 */
public class TrainingRecord {
	private String  tr_id;//练车记录id
	private String  t_id;//学员id
	private String  c_id;//教练id
	private String  dc_id;//车辆id
	private String  tr_duration;//练车时长
	private String  tr_date;//练车日期
	public String getTr_id() {
		return tr_id;
	}
	public void setTr_id(String tr_id) {
		this.tr_id = tr_id;
	}
	public String getT_id() {
		return t_id;
	}
	public void setT_id(String t_id) {
		this.t_id = t_id;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getDc_id() {
		return dc_id;
	}
	public void setDc_id(String dc_id) {
		this.dc_id = dc_id;
	}
	public String getTr_duration() {
		return tr_duration;
	}
	public void setTr_duration(String tr_duration) {
		this.tr_duration = tr_duration;
	}
	public String getTr_date() {
		return tr_date;
	}
	public void setTr_date(String tr_date) {
		this.tr_date = tr_date;
	}
	
	
}
