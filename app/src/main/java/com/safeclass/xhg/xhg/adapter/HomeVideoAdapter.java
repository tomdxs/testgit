package com.safeclass.xhg.xhg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.safeclass.xhg.xhg.R;
import com.safeclass.xhg.xhg.bean.HomeVideoInfo;
import com.safeclass.xhg.xhg.network.ApiConstants;
import com.safeclass.xhg.xhg.utils.MyToast;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tomdxs on 2018/6/4.
 */

public class HomeVideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private static final int TYPE_BANNER_MODULE = 0;
    private static final int TYPE_VIDEO_LIST    = 1;

    public static List<HomeVideoInfo.HomeBeanVideo> videoList = new ArrayList<HomeVideoInfo.HomeBeanVideo>();

    public HomeVideoAdapter(Context context) {
        System.out.println("初始化 HomeViewAdapter()");
        mContext = context;
        videoList.clear();
    }

    public void addFiles(HomeVideoInfo.HomeBeanVideo loadinfo){
        videoList.add(loadinfo);
        //notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        System.out.println("getItemCount()");
        if (null != videoList) {
            return videoList.size();
        }
        return 0;
    }

    @Override
    //负责为Item创建视图
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("onCreateViewHolder");
        VideoListViewHolder mViewHolder = new VideoListViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.home_recyclerview_item, parent, false));
        return mViewHolder;
    }

    @Override
    //负责将数据绑定到Item的视图上
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        System.out.println("onBindViewHolder");
        final VideoListViewHolder mViewHolder = (VideoListViewHolder) holder;
        if (null != videoList) {

            HomeVideoInfo.HomeBeanVideo videoinfo = videoList.get(position);
            String path = ApiConstants.MODULE_PIC_BASE_URL + videoinfo.getPicPath();

            Glide.with(mContext)
                    .load(path)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.no_banner)
                    .dontAnimate()
                    .into(mViewHolder.itemVideoImg);

            mViewHolder.itemVideoTitle.setText(videoinfo.getTitleName());
            mViewHolder.itemScanCount.setText(videoinfo.getClicksNum());
            mViewHolder.itemZanCount.setText(videoinfo.getLaudNum());
            mViewHolder.itemVideoDate.setText("昨天");
            mViewHolder.itemVideoTime.setText(videoinfo.getVideoTime());

            mViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = mViewHolder.getLayoutPosition();
                    MyToast.showToast(mContext,"RecyelerView: "+pos);
                }
            });

        }
    }

    public class VideoListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_video_img)
        ImageView itemVideoImg;
        @BindView(R.id.home_video_title)
        TextView itemVideoTitle;
        @BindView(R.id.scancount)
        TextView itemScanCount;
        @BindView(R.id.dianzancount)
        TextView itemZanCount;
        @BindView(R.id.videodate)
        TextView itemVideoDate;
        @BindView(R.id.videotime)
        TextView itemVideoTime;
        View itemView;

        VideoListViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }

    }
}
