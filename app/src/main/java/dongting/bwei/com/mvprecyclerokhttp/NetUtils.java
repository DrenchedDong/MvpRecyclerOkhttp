package dongting.bwei.com.mvprecyclerokhttp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * 作者:${董婷}
 * 日期:2017/6/14
 * 描述:
 */

public class NetUtils {
    /**
     * 判断当前是否有网络可用
     * @param mActivity
     * @return
     */
    public static boolean isNetworkAvailable(Context mActivity) {

        ConnectivityManager connectivity = (ConnectivityManager) mActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            //获取所有网络信息
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * 返回当前网络类型
     * @param context
     * @return
     */
    public static String GetNetype(Context context)
    {

        //state 默认未知
        String strNetworkType = "unknown";
        //获取 连接管理器
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        //获取可用网络
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        //网络已经连接
        if (networkInfo != null && networkInfo.isConnected())
        {
            //获取连接类型
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI)
            {
                strNetworkType = "WIFI";
            }
            //当前链接的是手机网络   手机管理器的枚举类型是wife

            else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
            {
                String _strSubTypeName = networkInfo.getSubtypeName();


                // TD-SCDMA   networkType is 17
                int networkType = networkInfo.getSubtype();
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                        //以上类型都是2G
                        strNetworkType = "2G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                        strNetworkType = "3G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                        strNetworkType = "4G";
                        break;
                    default:
                        // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                        if (_strSubTypeName.equalsIgnoreCase("TD-SCDMA") || _strSubTypeName.equalsIgnoreCase("WCDMA") || _strSubTypeName.equalsIgnoreCase("CDMA2000"))
                        {
                            strNetworkType = "3G";
                        }
                        else
                        {
                            strNetworkType = _strSubTypeName;
                        }

                        break;
                }
            }
        }
        //如果是空，返回当前网络是未知的
        return strNetworkType;
    }

    /**
     * 跳转到网络设置界面
     * @param context
     */
    public static void toSystemSetting(Context context){
        Intent intent=new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
        context.startActivity(intent);

    }
}
