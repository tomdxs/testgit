package com.safeclass.xhg.xhg.logic;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import com.google.gson.Gson;
import com.safeclass.xhg.xhg.MyApplication;
import com.safeclass.xhg.xhg.bean.HomeBannerInfo;
import com.safeclass.xhg.xhg.bean.HomeVideoInfo;
import com.safeclass.xhg.xhg.network.ApiConstants;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SdcardInfoThread extends Thread{

	private Handler mHandler;

	/*logic*/
	public SdPhotoInfo infob;
    public SdVideoInfo infoc;

	public SdcardInfoThread(Handler mHandler){
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
	
	private void getHomeInfo(){

		 /*logic*/
		 String b = getHtml("http://192.168.1.1/search_snapshot.cgi?user=admin&pwd=&json=1");
		 String c = getHtml("http://192.168.1.1/search_record.cgi?user=admin&pwd=&json=1");
		 System.out.println("照片文件解析后  = "+b);
		 System.out.println("视频文件  = \n"+c);

		 /*logic*/
		 if(b == null){
			 // 设备未连接
			 mHandler.sendEmptyMessage(1);
		 }
		 else{
			 Gson gson = new Gson();
			 /*logic*/
			 infob = gson.fromJson(b, SdPhotoInfo.class);
			 infoc = gson.fromJson(c, SdVideoInfo.class);
			 System.out.println("照片个数 = \n"+infob.result);
			 System.out.println("视频个数  = \n"+infoc.result);

			 /*logic*/
			 if(infob.result <= -1){
				 // SD卡未插入
				 mHandler.sendEmptyMessage(2);
			 }
			 else{
				 // 加载SD卡中的文件
				 mHandler.sendEmptyMessage(3);
			 }

		 }
	}

	//获取URL代码数据
	private String getHtml(String path) {
		HttpURLConnection connection = null;
		try {
			//创建URL对象
			URL url = new URL(path);
			//通过HttpURLConnection对象,向网络地址发送请求
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			//设置连接超时
			connection.setConnectTimeout(5000);
			System.out.println("connection.getResponseCode() = "+connection.getResponseCode());
			if (connection.getResponseCode() == 200) {
				// 得到网络返回的输入流
				byte[] data = read(connection.getInputStream());
				return new String(data, "UTF-8");
			}

		} catch (IOException e) {
			connection = null;
			e.printStackTrace();
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
