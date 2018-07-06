package com.safeclass.xhg.xhg.logic;

import java.util.ArrayList;


public class SdPhotoInfo {
	
	public int error;
	public int result;
	public ArrayList<MyBeanPhoto> snapshot;

	@Override
	public String toString() {
		return "MyPhotoMessage [error= " + error + ", result= " + result + ", list= " + snapshot + "]";
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

	public ArrayList<MyBeanPhoto> getPhotoInfo() {
		return snapshot;
	}
	public void setPhotoInfo(ArrayList<MyBeanPhoto> snapshot) {
		this.snapshot = snapshot;
	}
	
}

