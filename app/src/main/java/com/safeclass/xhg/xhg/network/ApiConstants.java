package com.safeclass.xhg.xhg.network;

/**
 * Created by tomdxs on 2018/6/03.
 *
 * API常量类
 */

public class ApiConstants {

    /*Logic*/
    public static final String LOGIC_PHOTO_URL = "http://192.168.1.1/search_snapshot.cgi?user=admin&pwd=&json=1";
    public static final String LOGIC_VIDEO_URL = "http://192.168.1.1/search_record.cgi?user=admin&pwd=&json=1";

    /*xhg*/

    public static final String XHG_BANNER_URL= "http://192.168.1.2//api.php?m=Api&c=banner&a=banner";
    public static final String XHG_BANNER_MODULE_URL = "http://192.168.1.2/api.php?m=Api&c=banner&a=bannerModule";
    public static final String XHG_VIDEO_LIST_URL = "http://192.168.1.2/api.php?m=Api&c=video&a=video";
    public static final String MODULE_PIC_BASE_URL = "http://192.168.1.2/Uploads/Picture/";

    /*原*/
    public static final String BILI_GO_BASE_URL = "http://bilibili-service.daoapp.io/";
    public static final String RANK_BASE_URL = "http://www.bilibili.com/";
    public static final String APP_BASE_URL = "http://app.bilibili.com/";
    public static final String LIVE_BASE_URL = "http://live.bilibili.com/";
    public static final String API_BASE_URL = "http://api.bilibili.cn/";
    public static final String BANGUMI_BASE_URL = "http://bangumi.bilibili.com/";
    public static final String SEARCH_BASE_URL = "http://s.search.bilibili.com/";
    public static final String ACCOUNT_BASE_URL = "https://account.bilibili.com/";
    public static final String USER_BASE_URL = "http://space.bilibili.com/";
    public static final String VIP_BASE_URL = "http://vip.bilibili.com/";
    public static final String IM9_BASE_URL = "http://www.im9.com/";
    public static final String COMMON_UA_STR = "OhMyBiliBili Android Client/2.1 (100332338@qq.com)";
}
