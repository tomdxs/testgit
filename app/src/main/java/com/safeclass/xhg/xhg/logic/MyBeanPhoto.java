package com.safeclass.xhg.xhg.logic;

import java.util.ArrayList;

public class MyBeanPhoto{

	public String name;
	public String path;
	public String start_time;
	public String end_time;

	@Override
	public String toString() {
		return "MyPhotoDates [name= " + name + ", path= " + path + ", start_time= " + start_time +  ", end_time= " + end_time + "]";
	}

	public String getPhotoName() {
		return name;
	}
	public void setPhotoName(String name) {
		this.name = name;
	}

	public String getPhotoPath() {
		return path;
	}
	public void setPhotoPath(String path) {
		this.path = path;
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
