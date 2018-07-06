package com.safeclass.xhg.xhg.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import com.safeclass.xhg.xhg.GlideImageLoader;
import com.safeclass.xhg.xhg.MyApplication;
import com.safeclass.xhg.xhg.R;
import com.safeclass.xhg.xhg.adapter.HomeBannerAdapter;
import com.safeclass.xhg.xhg.adapter.HomeVideoAdapter;
import com.safeclass.xhg.xhg.bean.HomeBannerInfo;
import com.safeclass.xhg.xhg.bean.HomeVideoInfo;
import com.safeclass.xhg.xhg.logic.MyBeanVideo;
import com.safeclass.xhg.xhg.network.ApiConstants;
import com.safeclass.xhg.xhg.network.HomeInfoThread;
import com.safeclass.xhg.xhg.utils.MyToast;
import com.safeclass.xhg.xhg.utils.SuperSwipeRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tomdxs on 2018/5/30.
 */

public class HomeFragment extends BaseFragment implements OnBannerListener, SwipeRefreshLayout.OnRefreshListener{

    private final static String TAG = "HOME_Fragment";

    private View view;
    private Banner banner;

    /*banner模块显示*/
    private GridView ModuleView;
    private HomeBannerAdapter saImageItems;
    private HomeVideoAdapter saVideoItems;

    private SuperSwipeRefreshLayout mRefresh;
    private RecyclerView mRecyclerView;
    private HomeInfoThread homethread;
    private Context context;

    private HomeBannerInfo banner_info;
    private HomeBannerInfo banner_module_info;
    private HomeVideoInfo video_info;

    final String path = "http://192.168.1.2/api.php?m=Api&c=banner&a=bannerModule";
    final String modulepicpath = "http://192.168.1.2/Uploads/Picture/";
    private List<Map<String, String>> slist;
    public static String[] projectpicstring;
    public static List<Bitmap> projectpicbitmap;

    List<String> banner_images = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_layout, container, false);

        context = MyApplication.getContext();
        ModuleView    = (GridView) view.findViewById(R.id.home_gridview_module);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        mRefresh = (SuperSwipeRefreshLayout) view.findViewById(R.id.home_refresh);
        mRefresh.setColorSchemeResources(R.color.statusBarColor);
        mRefresh.setOnRefreshListener(this);
        banner = (Banner)view.findViewById(R.id.home_banner);

        //recyclerView的设置，2列
        mRecyclerView.setLayoutManager(new GridLayoutManager(context,2));
        saImageItems = new HomeBannerAdapter(getActivity());
        saVideoItems = new HomeVideoAdapter(getActivity());

        homethread = new HomeInfoThread(handler);
        homethread.start();

        return view;
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    MyToast.showToast(context,"网络未连接！");
                    setBannerImage();
                    break;
                case 2:
                    MyToast.showToast(context,"获取信息失败！");
                    setBannerImage();
                    break;
                case 3:
                    MyToast.showToast(context,"获取信息成功！");
                    banner_info = HomeInfoThread.banner_info;
                    banner_module_info = HomeInfoThread.banner_module_info;
                    video_info = HomeInfoThread.video_info;
                    setBannerImage();
                    new InfoLoadimg().execute();
                    break;
                default:
                    break;
            }
        }
    };

    private void setBannerImage(){
        //Log.d(TAG, "infob = "+infob);
        if(banner_info != null) {
            banner_images.clear();
            for (int i = 0; i < banner_info.datas.size(); i++) {
                String path = ApiConstants.MODULE_PIC_BASE_URL + banner_info.datas.get(i).getPicPath();
                Log.d(TAG, "banner picpath : "+path);
                banner_images.add(path);
            }
        }
        //简单使用
        banner.setImages(banner_images)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(this)
                .start();
    }

    // 加载图片,信息
    class InfoLoadimg extends AsyncTask<Object, MyBeanVideo, Boolean> {

        @Override
        protected void onPreExecute() {
            //这里是开始线程之前执行的,是在UI线程
            super.onPreExecute();
        }
        @Override
        protected void onCancelled() {
            //当任务被取消时回调
            super.onCancelled();
        }

        @Override
        protected Boolean doInBackground(Object... params) {

            //这是在后台子线程中执行的
            HomeBannerInfo.HomeBeanBanner photo;
            HomeVideoInfo.HomeBeanVideo video;
            boolean result = false;

            //Banner模块信息
            for(int i=0; i<banner_module_info.datas.size(); i++){
                photo = banner_module_info.datas.get(i);
                if(photo != null){
                    saImageItems.addFiles(photo);
                }
            }
            //视频列表详情
            for(int i=0; i<video_info.datas.size(); i++){
                video = video_info.datas.get(i);
                if(video != null){
                    saVideoItems.addFiles(video);
                }
            }
            return result;
        }

        @Override
        public void onProgressUpdate(MyBeanVideo... value) {

        }

        @Override
        protected void onPostExecute(Boolean result) {
            //当任务执行完成时调用,在UI线程
            System.out.println("图片加载结束！");
            ModuleView.setAdapter(saImageItems);
            ModuleView.setOnItemClickListener(new ItemClickListener());
            mRecyclerView.setAdapter(saVideoItems);
        }
    }

    //GridView的点击事件
    class ItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> arg0, View view, int position,
                                long arg3) {
            Log.d(TAG, "gridView : "+position);
            MyToast.showToast(context,"GridView: "+position);
        }
    }

    @Override
    public void OnBannerClick(int position) {
        Log.d(TAG, "position = "+position+"activity = "+getActivity());
        MyToast.showToast(context,"Banner:"+position);
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
        Log.d(TAG, "HF onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
        Log.d(TAG, "HF onStop");
    }

    /*@Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "HF onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "HF onPause");
    }*/

    @Override
    //Fragment中的布局被移除时调用
    public void onDestroyView() {
        super.onDestroyView();
        projectpicstring = null;
        if(projectpicbitmap != null)
            projectpicbitmap.clear();
        Log.d(TAG, "HF onDestroyView");
    }

   /* @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "HF onDestroy");
    }*/

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefresh.setRefreshing(false);
                MyToast.showToast(context,"刷新成功");
            }
        }, 2000);

        //handler.sendEmptyMessageDelayed(1, 2000);
    }

}
