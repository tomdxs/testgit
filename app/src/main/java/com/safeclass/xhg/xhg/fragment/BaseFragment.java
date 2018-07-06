package com.safeclass.xhg.xhg.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.safeclass.xhg.xhg.manager.ViewManager;

import java.util.List;

/**
 * Created by tomdxs on 2018/5/30.
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public abstract class BaseFragment extends Fragment
{
    private final static String TAG = "BaseFragment";

    protected View mRootView;

    @Nullable
    @Override
    //为Fragment加载布局时调用
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //mRootView = initView(inflater);
        Log.d(TAG,"BaseFragment onCreateView");
        return mRootView;
    }

    public View getRootView() {
        if (mRootView != null) {
            return mRootView;
        }
        return null;
    }

    @Override
    //当Activity中的onCreate方法执行完后调用
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initData();
        initListener();
        loadData();
        Log.d(TAG,"BaseFragment onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
    }

    /*
     * @param inflater
     * @return
     * @des 初始化view，需要子类复写
     */
    //protected abstract View initView(LayoutInflater inflater);

    public void loadData() {
        Log.d(TAG,"BaseFragment loadData()");
    }

    /**
     * @des 初始化数据
     */
    public void initData() {
        Log.d(TAG,"BaseFragment initData()");
    }

    /**
     * @des 初始化事件
     */
    public void initListener() {
        Log.d(TAG,"BaseFragment initListener()");
    }

}
