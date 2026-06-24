package com.facebook.react;

import android.content.Context;
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint;
import com.facebook.react.soloader.OpenSourceMergedSoMapping;
import com.facebook.soloader.SoLoader;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class ReactNativeApplicationEntryPoint {
    public static void loadReactNative(Context context) {
        try {
            SoLoader.init(context, OpenSourceMergedSoMapping.INSTANCE);
            DefaultNewArchitectureEntryPoint.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
