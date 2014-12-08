package com.example.dongdong.util;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.telephony.TelephonyManager;

/**
 * HttpUtils
 *
 * @author deofly
 * @since 2014/9/ic_message_active
 */

public class HttpUtils {

    public static int NETWORK_TYPE_INVALID = 0;
    public static int NETWORK_TYPE_WAP = 1;
    public static int NETWORK_TYPE_2G = 2;
    public static int NETWORK_TYPE_3G = 3;
    public static int NETWORK_TYPE_WIFI = 4;

    private static Uri APN_URI = null;

    private HttpUtils() {
    }

    public static int getNetworkType(Context context) {
        // verify connectivity of network
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return HttpUtils.NETWORK_TYPE_INVALID;
        }

        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info == null || !info.isAvailable() || !info.isConnected()) {
            return HttpUtils.NETWORK_TYPE_INVALID;
        }

        // verify wifi
        String type = info.getTypeName();
        if (type.equalsIgnoreCase("WIFI")) {
            return HttpUtils.NETWORK_TYPE_WIFI;
        }

        // verify CMWAP
        APN_URI = Uri.parse("content://telephony/carriers/preferapn");
        Cursor uriCursor = context.getContentResolver().query(APN_URI, null, null, null, null);
        if (uriCursor != null && uriCursor.moveToFirst()) {
            String proxy = uriCursor.getString(uriCursor.getColumnIndex("proxy"));
            String port = uriCursor.getString(uriCursor.getColumnIndex("port"));
            String apn = uriCursor.getString(uriCursor.getColumnIndex("apn"));
            if (proxy != null && port != null && apn != null && apn.equalsIgnoreCase("CMWAP") &&
                    port.equals("80") && (proxy.equals("10.0.0.172") || proxy.equals("010.000.000.172"))) {
                return HttpUtils.NETWORK_TYPE_WAP;
            }
        }

        // distinguish 2G from 3G
        return isFastMobileNetWork(context) ? HttpUtils.NETWORK_TYPE_3G : HttpUtils.NETWORK_TYPE_2G;
    }

    private static boolean isFastMobileNetWork(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        switch (manager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_1xRTT: // 50-100 kbps

            case TelephonyManager.NETWORK_TYPE_CDMA: // 14-64 kbps

            case TelephonyManager.NETWORK_TYPE_EDGE: // 50-100 kbps

            case TelephonyManager.NETWORK_TYPE_GPRS: // 100 kbps

            case TelephonyManager.NETWORK_TYPE_IDEN: // 25 kbps

            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return false;

            case TelephonyManager.NETWORK_TYPE_EHRPD: // 1-2 mbps

            case TelephonyManager.NETWORK_TYPE_EVDO_0: // 400-1000 kbps

            case TelephonyManager.NETWORK_TYPE_EVDO_A: // 600-1400 kbps

            case TelephonyManager.NETWORK_TYPE_EVDO_B: // 5 mbps

            case TelephonyManager.NETWORK_TYPE_HSDPA: // 2-14 mbps

            case TelephonyManager.NETWORK_TYPE_HSPA: // 700-1700 kbps

            case TelephonyManager.NETWORK_TYPE_HSPAP: // 10-20 mbps

            case TelephonyManager.NETWORK_TYPE_HSUPA: // 1-23 mbps

            case TelephonyManager.NETWORK_TYPE_LTE: // 10+ mbps

            case TelephonyManager.NETWORK_TYPE_UMTS: // 400-7000 kbps
                return true;

            default:
                return false;
        }
    }
}
