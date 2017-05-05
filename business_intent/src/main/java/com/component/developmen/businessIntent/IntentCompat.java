package com.component.developmen.businessIntent;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

/**
 * Created by bobo on 2017/4/21.
 */

public class IntentCompat extends Intent {
    public IntentCompat(Context packageContext, String className) {
        if (packageContext == null)
            throw new NullPointerException("IntentCompat packageContext is null");
        if (className == null) throw new NullPointerException("IntentCompat className is null");
        setComponent(new ComponentName(packageContext, className));
    }

    public IntentCompat(Context packageContext, Class<?> cls) {
        super(packageContext, cls);
    }
}
