package com.safeclass.xhg.xhg.logic;

import java.util.ArrayList;


public class SdVideoInfo {

	public int error;
	public int result;
	public ArrayList<MyBeanVideo> record;
	
	@Override
	public String toString() {
		return "MyVideoMessage [error= " + error + ", result= " + result + ", list= " + record + "]";
	}

	public int getErrorCode() {
		return error;
	}
	public void setErrorCode(int error) {
		this.error = error;
	}

	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}

	public ArrayList<MyBeanVideo> getVideoInfo() {
		return record;
	}
	public void setVideoInfo(ArrayList<MyBeanPhoto> snapshot) {
		this.record = record;
	}

}

