package com.safeclass.xhg.xhg;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.safeclass.xhg.xhg.fragment.ClassifyFragment;
import com.safeclass.xhg.xhg.fragment.FindFragment;
import com.safeclass.xhg.xhg.fragment.HomeFragment;
import com.safeclass.xhg.xhg.fragment.UserFragment;
import com.safeclass.xhg.xhg.manager.ViewManager;

/**
 * Created by tomdxs on 2018/5/24.
 */

public class StartActivity extends Activity implements View.OnClickListener {

    private final static String TAG = "StartActivity";

    private FragmentManager fragmentManager;
    private ViewManager mViewManager = null;    //视图管理器

    private ImageView start_home_img;
    private ImageView start_classify_img;
    private ImageView start_find_img;
    private ImageView start_user_img;

    private HomeFragment homefragment;
    private ClassifyFragment classifyfragment;
    private FindFragment findfragment;
    private UserFragment userfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        start_home_img     = (ImageView) findViewById(R.id.start_home_img);
        start_classify_img = (ImageView) findViewById(R.id.start_classify_img);
        start_find_img     = (ImageView) findViewById(R.id.start_find_img);
        start_user_img     = (ImageView) findViewById(R.id.start_user_img);

        fragmentManager = getFragmentManager();
        //mViewManager = ViewManager.getInstance(); //初始化view界面管理器
        setTabSelection(0);                         // 第一次启动时选中第0个tab

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_home_layout:
                // 当点击了消息tab时，选中第1个tab
                Log.d(TAG, "选中第1个tab");
                setTabSelection(0);
                break;
            case R.id.start_classify_layout:
                // 当点击了联系人tab时，选中第2个tab
                setTabSelection(1);
                break;
            case R.id.start_find_layout:
                // 当点击了动态tab时，选中第3个tab
                setTabSelection(2);
                break;
            case R.id.start_user_layout:
                // 当点击了设置tab时，选中第4个tab
                setTabSelection(3);
                break;
            default:
                break;
        }
    }

    private void setTabSelection(int index) {

        //每次选中之前先清除掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);

        switch (index) {
            case 0:
                start_home_img.setBackgroundResource(R.mipmap.shouye_sel);

                if (homefragment == null) {
                    // 如果HomeFragment为空，则创建一个并添加到界面上
                    homefragment = new HomeFragment();
                    transaction.add(R.id.content, homefragment);
                } else {
                    // 如果HomeFragment不为空，则直接将它显示出来
                    transaction.show(homefragment);
                }
                break;
            case 1:
                start_classify_img.setBackgroundResource(R.mipmap.fenlei_sel);

                if (classifyfragment == null) {
                    classifyfragment = new ClassifyFragment();
                    transaction.add(R.id.content, classifyfragment);
                } else {
                    transaction.show(classifyfragment);
                }
                break;
            case 2:
                start_find_img.setBackgroundResource(R.mipmap.faxian_sel);

                if (findfragment == null) {
                    findfragment = new FindFragment();
                    transaction.add(R.id.content, findfragment);
                } else {
                    transaction.show(findfragment);
                }
                break;
            case 3:
            default:
                start_user_img.setBackgroundResource(R.mipmap.myaccount_sel);

                if (userfragment == null) {
                    userfragment = new UserFragment();
                    transaction.add(R.id.content, userfragment);
                } else {
                    transaction.show(userfragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void clearSelection() {
        start_home_img.setBackgroundResource(R.mipmap.shouye_nor);
        start_classify_img.setBackgroundResource(R.mipmap.fenlei_nor);
        start_find_img.setBackgroundResource(R.mipmap.faxian_nor);
        start_user_img.setBackgroundResource(R.mipmap.myaccount_nor);
    }

    private void hideFragments(FragmentTransaction transaction) {
        /*homefragment     = (HomeFragment) mViewManager.getFragment(ViewManager.ViewPageType.HOME_VIEW);
        classifyfragment = (ClassifyFragment) mViewManager.getFragment(ViewManager.ViewPageType.CLASSIFY_VIEW);
        findfragment     = (FindFragment) mViewManager.getFragment(ViewManager.ViewPageType.FIND_VIEW);
        userfragment     = (UserFragment) mViewManager.getFragment(ViewManager.ViewPageType.USER_VIEW);*/

        if (homefragment != null) {
            transaction.hide(homefragment);
        }
        if (classifyfragment != null) {
            transaction.hide(classifyfragment);
        }
        if (findfragment != null) {
            transaction.hide(findfragment);
        }
        if (userfragment != null) {
            transaction.hide(userfragment);
        }
    }

}
