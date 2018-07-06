package com.safeclass.xhg.xhg;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tomdxs on 2018/5/24.
 */

public class MyApplication extends Application {

    public static int width;
    public static int height;
    public static List<?> images=new ArrayList<>();
    public static List<String> titles=new ArrayList<>();

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        DisplayMetrics dm = getResources().getDisplayMetrics();
        width = dm.widthPixels;//宽度
        height = dm.heightPixels ;//高度

        String[] urls = getResources().getStringArray(R.array.url);
        String[] tips = getResources().getStringArray(R.array.title);
        List list = Arrays.asList(urls);
        images = new ArrayList(list);
        List list1 = Arrays.asList(tips);
        titles= new ArrayList(list1);

        mContext = this;

        System.out.println("-----APP OnCreate-----");
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onTerminate() {
        System.out.println("-----APP onTerminate-----");
        super.onTerminate();
    }
}