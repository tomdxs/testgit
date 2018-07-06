package com.safeclass.xhg.xhg.manager;

import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.safeclass.xhg.xhg.R;
import com.safeclass.xhg.xhg.StartActivity;
import com.safeclass.xhg.xhg.fragment.BaseFragment;
import com.safeclass.xhg.xhg.fragment.ClassifyFragment;
import com.safeclass.xhg.xhg.fragment.FindFragment;
import com.safeclass.xhg.xhg.fragment.HomeFragment;
import com.safeclass.xhg.xhg.fragment.UserFragment;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tomdxs on 2018/5/30.
 */

public class ViewManager
{
    //UI管理器
    private final static String TAG = "ViewManager";

    private static ViewManager instance;

    private Map<ViewPageType, BaseFragment> fragmentMap = new HashMap<ViewPageType, BaseFragment>(); //界面列表
    private ViewPageType mViewPage = ViewPageType.HOME_VIEW; //界面种类,4种

    private ViewManager()
    {
        initFragment();
    }

    //初始化ViewManager
    public static synchronized ViewManager getInstance()
    {
        if (null == instance)
        {
            instance = new ViewManager();
        }
        return instance;
    }

    //添加fragment到列表中
    public void initFragment()
    {
        Log.d(TAG, "initFragment");
        addFragment(ViewPageType.HOME_VIEW, new HomeFragment());
        addFragment(ViewPageType.CLASSIFY_VIEW, new ClassifyFragment());
        addFragment(ViewPageType.FIND_VIEW, new FindFragment());
        addFragment(ViewPageType.USER_VIEW, new UserFragment());
    }

    //向列表中增加fragment
    public void addFragment(ViewPageType viewPageType, BaseFragment baseFragment)
    {
        fragmentMap.put(viewPageType, baseFragment);
    }

    //获取当前视图
    public BaseFragment getFragment()
    {
        Log.d(TAG, "getFragment " + mViewPage);
        return fragmentMap.get(mViewPage);
    }

    //获取指定类型的视图
    public BaseFragment getFragment(ViewPageType viewPageType)
    {
        return fragmentMap.get(viewPageType);
    }

    /*public Map<ViewPageType, BaseFragment> getFragments()
    {
        return fragmentMap;
    }*/

    //设置当前视图种类
    public synchronized void setCurrentView(ViewPageType viewPageType)
    {
        synchronized (mViewPage)
        {
            this.mViewPage = viewPageType;
        }
    }

    //获取当前视图种类
    public ViewPageType getViewPageType()
    {
        return mViewPage;
    }

    public enum ViewPageType
    {
        HOME_VIEW, CLASSIFY_VIEW, FIND_VIEW, USER_VIEW
    }

}
