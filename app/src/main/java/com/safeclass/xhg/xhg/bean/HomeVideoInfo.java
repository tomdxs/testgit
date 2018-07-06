package com.safeclass.xhg.xhg.bean;

import java.util.ArrayList;


public class HomeVideoInfo {

	public int status;
	public int code;
	public String msg;
	public ArrayList<HomeBeanVideo> datas;

	@Override
	public String toString() {
		return "HomeVideoMessage [status= " + status + ", code= " + code + ", msg= " + msg + ", video= " + datas +"]";
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

	public ArrayList<HomeBeanVideo> getVideoInfo() {
		return datas;
	}
	public void setVideoInfo(ArrayList<HomeBeanVideo> video) {
		this.datas = datas;
	}

	public class HomeBeanVideo{

		public int id;
		public String title;    //标题
		public String pic;      //图片
		public String subtitle; //副标题
		public int edit_time;   //时间
		public int laud_num;	//点赞量
		public int clicks_num;  //点击量
		public int type;        //种类：0为文本，1为视频

		@Override
		public String toString() {
			return "MyVideoDates [id= " +id +", title= " +title +", pic= " +pic +", subtitle= " +subtitle +", edit_time= " +edit_time +
					", laud_num= " + laud_num + ", clicks_num= " + clicks_num +", type= " + type +"]";
		}

		public int getInfoId() {
			return id;
		}
		public void setInfoId(int id) {
			this.id = id;
		}

		public String getTitleName() {
			return title;
		}
		public void setTitleName(String title) {
			this.title = title;
		}

		public String getPicPath() {
			return pic;
		}
		public void setPicPath(String pic) {
			this.pic = pic;
		}

		/*public String getLinkAddress() {
			return address;
		}
		public void setLinkAddress(String address) {
			this.address = address;
		}*/

		public int getVideoTime() {
			return edit_time;
		}
		public void setVideoTime(int edit_time) {
			this.edit_time = edit_time;
		}

		public int getLaudNum() {
			return laud_num;
		}
		public void setLaudNum(int laud_num) {
			this.laud_num = laud_num;
		}

		public int getClicksNum() {
			return clicks_num;
		}
		public void setClicksNum(int clicks_num) {
			this.clicks_num = clicks_num;
		}

	}

}

