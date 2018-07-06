package com.safeclass.xhg.xhg.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.safeclass.xhg.xhg.MyApplication;
import com.safeclass.xhg.xhg.bean.HomeBannerInfo;
import com.safeclass.xhg.xhg.bean.HomeVideoInfo;
import com.safeclass.xhg.xhg.fragment.HomeFragment;
import com.safeclass.xhg.xhg.logic.SdPhotoInfo;
import com.safeclass.xhg.xhg.logic.SdVideoInfo;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeInfoThread extends Thread{

	private Handler mHandler;

	/*xhg*/
	public static HomeBannerInfo banner_info;
	public static HomeBannerInfo banner_module_info;
	public static HomeVideoInfo video_info;
	
	public HomeInfoThread(Handler mHandler){
        this.mHandler = mHandler;
    }
	 
	@Override
	public void run()
	{
		try {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) MyApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();

			//有网络连接
			if(mNetworkInfo != null && mNetworkInfo.isAvailable()){
				if (mNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI || mNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {

					getHomeInfo();

				} else {
					//网络错误
					mHandler.sendEmptyMessage(1);
				}
			}
			//无网络连接
			else {
				mHandler.sendEmptyMessage(1);
			}
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    }  
    }
	
	private void getHomeInfo() throws Exception {

		 /*xhg*/
		String d = getHtml(ApiConstants.XHG_BANNER_URL);
		System.out.println("轮播信息 = "+d);
		String e = getHtml(ApiConstants.XHG_BANNER_MODULE_URL);
		System.out.println("轮播模块信息 = "+e);
		String f = getHtml(ApiConstants.XHG_VIDEO_LIST_URL);
		System.out.println("视频列表信息 = "+f);

		System.out.println("Gson解析！！！");
		Gson gson = new Gson();
		banner_info = gson.fromJson(d, HomeBannerInfo.class);
		banner_module_info = gson.fromJson(e, HomeBannerInfo.class);
		video_info = gson.fromJson(f, HomeVideoInfo.class);

		System.out.println("banner_info = "+banner_info);
		System.out.println("banner_module_info = "+banner_module_info);
		System.out.println("video_info = "+video_info);
		System.out.println("Gson解析成功！！！");

		if((banner_info.status != 0) && (banner_module_info.status != 0) && (video_info.status != 0)){
			// 网络出问题
			mHandler.sendEmptyMessage(2);
		}
		else{
			// 网络连接成功
			mHandler.sendEmptyMessage(3);
		}
	}

	//获取URL代码数据
	private String getHtml(String path) throws Exception{
		HttpURLConnection connection = null;
		//创建URL对象
		URL url = new URL(path);
		//通过HttpURLConnection对象,向网络地址发送请求
		connection = (HttpURLConnection) url.openConnection();
		//设置连接超时
		connection.setConnectTimeout(5000);
		connection.setRequestMethod("GET");
		System.out.println("connection.getResponseCode() = "+connection.getResponseCode());
		if (connection.getResponseCode() == 200) {
			// 得到网络返回的输入流
			byte[] data = read(connection.getInputStream());
			System.out.println("HomeInfoThread 1");
			return new String(data, "UTF-8");
		}
		return null;
	}

	//读取数据输入流
	private byte[] read(InputStream inStream) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, len);
		}		
		inStream.close();
		outputStream.close();
		return outputStream.toByteArray();
	}
	
}
