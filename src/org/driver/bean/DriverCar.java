package org.driver.bean;

/*
 * 驾校车辆
 */
public class DriverCar {
	private String dc_id;//id
	private String dc_carNumber;//车牌号
	private String dc_type;//车型
	private String dc_age;//车龄
	public String getDc_id() {
		return dc_id;
	}
	public void setDc_id(String dc_id) {
		this.dc_id = dc_id;
	}
	public String getDc_carNumber() {
		return dc_carNumber;
	}
	public void setDc_carNumber(String dc_carNumber) {
		this.dc_carNumber = dc_carNumber;
	}
	public String getDc_type() {
		return dc_type;
	}
	public void setDc_type(String dc_type) {
		this.dc_type = dc_type;
	}
	public String getDc_age() {
		return dc_age;
	}
	public void setDc_age(String dc_age) {
		this.dc_age = dc_age;
	}
	
}
