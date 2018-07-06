package com.safeclass.xhg.xhg.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by tomdxs on 2018/6/4.
 */

public class MyToast {

    public static Toast toast = null;

    public static void showToast(Context context, String info){
        if(toast == null){
            toast = Toast.makeText(context, info, Toast.LENGTH_SHORT);
            toast.show();
        }else{
            toast.cancel();
            toast = Toast.makeText(context, info, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
