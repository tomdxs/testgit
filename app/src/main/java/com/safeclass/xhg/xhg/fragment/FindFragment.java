package com.safeclass.xhg.xhg.fragment;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.safeclass.xhg.xhg.R;

/**
 * Created by tomdxs on 2018/5/30.
 */

public class FindFragment extends BaseFragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View messageLayout = inflater.inflate(R.layout.find_layout, container, false);
        return messageLayout;
    }
}
