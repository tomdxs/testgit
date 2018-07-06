package com.safeclass.xhg.xhg.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.safeclass.xhg.xhg.R;
import com.safeclass.xhg.xhg.bean.HomeBannerInfo;
import com.safeclass.xhg.xhg.network.ApiConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomdxs on 2018/6/4.
 */

public class HomeBannerAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    public static List<HomeBannerInfo.HomeBeanBanner> picList = new ArrayList<HomeBannerInfo.HomeBeanBanner>();

    public HomeBannerAdapter(Context context){
        System.out.println("----new ReviewAdapter()----");
        this.inflater = LayoutInflater.from(context);
        mContext = context;
        picList.clear();
    }

    public void addFiles(HomeBannerInfo.HomeBeanBanner loadImage){
        System.out.println("----addFiles()---- "+loadImage);
        picList.add(loadImage);
        //notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        System.out.println("----getCount()---- "+picList.size());
        return picList==null?0:picList.size();
    }

    @Override
    public HomeBannerInfo.HomeBeanBanner getItem(int position) {
        System.out.println("----getItem()---- "+position);
        return picList.get(position);
    }

    @Override
    public long getItemId(int position) {
        System.out.println("----getItemId()---- "+position);
        return position;
    }

    @SuppressLint({ "InflateParams", "ResourceAsColor" }) @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //TODO Auto-generated method stub
        HomeBannerHolder holder = null;
        if(convertView==null){
            holder = new HomeBannerHolder();
            convertView = inflater.inflate(R.layout.home_gridview_item, null);
            holder.image1 = (ImageView)convertView.findViewById(R.id.banner_module_img);
            holder.text1  = (TextView) convertView.findViewById(R.id.banner_module_text);
            convertView.setTag(holder);
        }else{
            holder = (HomeBannerHolder)convertView.getTag();
        }

        final HomeBannerInfo.HomeBeanBanner imageBean = getItem(position);
        String path = ApiConstants.MODULE_PIC_BASE_URL + imageBean.getPicPath();

        Glide.with(mContext)
                .load(path)
                .centerCrop()                                 //等比例缩放图片，直到图片的宽高都大于等于ImageView的宽度，然后截取中间的显示
                .diskCacheStrategy(DiskCacheStrategy.ALL)     //缓存所有的图片，默认
                .placeholder(R.drawable.no_banner)            //设置占位图
                .dontAnimate()                                //不显示动画效果
                .into(holder.image1);

        holder.text1.setText(imageBean.getPicName());
        return convertView;
    }

    public static class HomeBannerHolder{
        public ImageView image1;
        public TextView  text1;
    }

}
