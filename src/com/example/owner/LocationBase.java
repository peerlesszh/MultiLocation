package com.example.owner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * ��λBean
 * @author Acer
 *
 */
public class LocationBase {
	
	//private long latitude;//����
	//private long longitude;//γ��
	LagLng lagLngBean;//��γ��
	String lastSavingTime;//�ϴα���ʱ��
	int locationServiceType;//��λ����  1��ʾGPS��2��ʾNetWork Wifi,3��ʾNetwork �ֻ�����
	
	/*
	public long getLatitude() {
		return latitude;
	}
	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}
	public long getLongitude() {
		return longitude;
	}
	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}
	*/
	
	public String getLastSavingTime() {
		return lastSavingTime;
	}
	public LagLng getLagLngBean() {
		return lagLngBean;
	}
	public void setLagLngBean(LagLng lagLngBean) {
		this.lagLngBean = lagLngBean;
	}
	public void setLastSavingTime(String lastSavingTime) {
		this.lastSavingTime = lastSavingTime;
	}
	public int getLocationServiceType() {
		return locationServiceType;
	}
	public void setLocationServiceType(int locationServiceType) {
		this.locationServiceType = locationServiceType;
	}
	
	public static String getCurrentDate(){
		String currentDate = "";
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" ,Locale.CHINA);
		currentDate = format.format(date);
		return currentDate;
	}
}
