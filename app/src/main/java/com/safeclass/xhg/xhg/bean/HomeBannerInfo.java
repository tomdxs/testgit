package com.safeclass.xhg.xhg.bean;

import java.util.ArrayList;

public class HomeBannerInfo {
	
	public int status;
	public int code;
	public String msg;
	public ArrayList<HomeBeanBanner> datas;

	@Override
	public String toString() {
		return "HomeBannerMessage [status= " + status + ", code= " + code + ", msg= " + msg + ", snapshot= " + datas +"]";
	}

	public int getRequestStatus() {
		return status;
	}
	public void setRequestStatus(int status) {
		this.status = status;
	}

	public int getErrorCode() {
		return code;
	}
	public void setErrorCode(int code) {
		this.code = code;
	}

	public String getErrorMsg() {
		return msg;
	}
	public void setErrorMsg(String msg) {
		this.msg = msg;
	}

	public ArrayList<HomeBeanBanner> getBannerInfo() {
		return datas;
	}
	public void setBannerInfo(ArrayList<HomeBeanBanner> snapshot) {
		this.datas = snapshot;
	}

	public class HomeBeanBanner{

		public int id;
		public String name;
		public String pic;
		public String address;

		@Override
		public String toString() {
			return "MyBannerDates [id= " + id + ", name= " + name + ", pic= " + pic +  ", address= " + address + "]";
		}

		public int getInfoId() {
			return id;
		}
		public void setInfoId(int id) {
			this.id = id;
		}

		public String getPicName() {
			return name;
		}
		public void setPicName(String name) {
			this.name = name;
		}

		public String getPicPath() {
			return pic;
		}
		public void setPicPath(String pic) {
			this.pic = pic;
		}

		public String getLinkAddress() {
			return address;
		}
		public void setLinkAddress(String address) {
			this.address = address;
		}
	}

}

