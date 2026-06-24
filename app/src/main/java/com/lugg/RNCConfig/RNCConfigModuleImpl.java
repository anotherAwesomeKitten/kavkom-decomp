package com.lugg.RNCConfig;

import android.content.res.Resources;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import java.lang.reflect.Field;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class RNCConfigModuleImpl {
    public static final String NAME = "RNCConfigModule";
    private ReactApplicationContext context;

    public RNCConfigModuleImpl(ReactApplicationContext reactApplicationContext) {
        this.context = reactApplicationContext;
    }

    public WritableMap getConfig() {
        String packageName;
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        try {
            try {
                packageName = this.context.getString(this.context.getResources().getIdentifier("build_config_package", TypedValues.Custom.S_STRING, this.context.getPackageName()));
            } catch (Resources.NotFoundException unused) {
                packageName = this.context.getApplicationContext().getPackageName();
            }
            for (Field field : Class.forName(packageName + ".BuildConfig").getDeclaredFields()) {
                try {
                    map2.put(field.getName(), field.get(null));
                } catch (IllegalAccessException unused2) {
                    Log.d(ReactConstants.TAG, "ReactConfig: Could not access BuildConfig field " + field.getName());
                }
            }
        } catch (ClassNotFoundException unused3) {
            Log.d(ReactConstants.TAG, "ReactConfig: Could not find BuildConfig class");
        }
        map.put("config", map2);
        return MapConverter.convertMapToWritableMap(map);
    }
}
