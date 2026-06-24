package com.dieam.reactnativepushnotification.helpers;

import android.content.ComponentName;
import android.content.Context;
import com.facebook.common.logging.FLog;
import me.leolin.shortcutbadger.ShortcutBadger;

/* JADX INFO: loaded from: classes.dex */
public class ApplicationBadgeHelper {
    public static final ApplicationBadgeHelper INSTANCE = new ApplicationBadgeHelper();
    private static final String LOG_TAG = "ApplicationBadgeHelper";
    private Boolean applyAutomaticBadger;
    private ComponentName componentName;

    private ApplicationBadgeHelper() {
    }

    public void setApplicationIconBadgeNumber(Context context, int i) {
        if (this.componentName == null) {
            this.componentName = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).getComponent();
        }
        tryAutomaticBadge(context, i);
    }

    private void tryAutomaticBadge(Context context, int i) {
        Boolean bool = this.applyAutomaticBadger;
        if (bool == null) {
            Boolean boolValueOf = Boolean.valueOf(ShortcutBadger.applyCount(context, i));
            this.applyAutomaticBadger = boolValueOf;
            if (boolValueOf.booleanValue()) {
                FLog.i(LOG_TAG, "First attempt to use automatic badger succeeded; permanently enabling method.");
                return;
            } else {
                FLog.i(LOG_TAG, "First attempt to use automatic badger failed; permanently disabling method.");
                return;
            }
        }
        if (bool.booleanValue()) {
            ShortcutBadger.applyCount(context, i);
        }
    }
}
