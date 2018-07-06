package com.safeclass.xhg.xhg.logic;

public class MyBeanVideo{

	public String name;
	public String path;
	public String preview_path;
	public int flag;
	public String start_time;
	public String end_time;
	public long size;

	@Override
	public String toString() {
		return "MyVideoDates [name= " + name + ", path= " + path + ", start_time= " + start_time +  ", end_time= " + end_time + "]";
	}

	public String getVideoName() {
		return name;
	}
	public void setVideoName(String name) {
		this.name = name;
	}

	public String getVideoPath() {
		return path;
	}
	public void setVideoPath(String path) {
		this.path = path;
	}

	public String getPrePhotoPath() {
		return preview_path;
	}
	public void setPrePhotoPath(String preview_path) {
		this.preview_path = preview_path;
	}

	public String getStartTime() {
		return start_time;
	}
	public void setStartTime(String start_time) {
		this.start_time = start_time;
	}

	public String getEndTime() {
		return end_time;
	}
	public void setEndTime(String end_time) {
		this.end_time = end_time;
	}

}
