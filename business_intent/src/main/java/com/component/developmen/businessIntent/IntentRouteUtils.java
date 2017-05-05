package com.component.developmen.businessIntent;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;


import com.common.utils.LogUtil;

import java.util.List;


/**
 * Created by bobo on 2017/4/21.
 */

public class IntentRouteUtils {

    public static void startMainActivityClearTop(Context context) {
        IntentCompat intent = new IntentCompat(context, IntentRouteParam.Intent_Route_XrzMainActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }


    public static void startMainActivity(Activity context, int animationType) {
        IntentCompat intent = new IntentCompat(context.getApplicationContext()
                , IntentRouteParam.Intent_Route_XrzMainActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        // intent.putExtra("animationType", ANI_SWITCH_FADE);
        intent.putExtra("animationType", animationType);
        context.getApplicationContext().startActivity(intent);
        context.finish();
    }

    public static void startMainActivityPushMode(Context context, int pushModeType) {
        IntentCompat mainIntent = new IntentCompat(context, IntentRouteParam.Intent_Route_XrzMainActivity);
        ActivityManager am = (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> appTask = am.getRunningTasks(1);
        if (appTask.size() > 0
                && appTask.get(0).baseActivity.equals(mainIntent
                .getComponent())) {
            LogUtil.i("ActionBarLayout onClick appTask.get(0).baseActivity="
                    + appTask.get(0).baseActivity);
//            Tabbar3Fragment.isPushFallBack = true;
        } else {
            mainIntent.putExtra("isPushMode", true);
            if (pushModeType > 0) {
                mainIntent.putExtra("type", pushModeType);
            }
            context.startActivity(mainIntent);
        }
    }
}
