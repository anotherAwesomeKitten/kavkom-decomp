package com.zoontek.rnlocalize;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class NativeRNLocalizeSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "RNLocalize";

    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract String getCalendar();

    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract String getCountry();

    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract WritableArray getCurrencies();

    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract WritableArray getLocales();

    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract WritableMap getNumberFormatSettings();

    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract String getTemperatureUnit();

    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract String getTimeZone();

    @ReactMethod
    public abstract void openAppLanguageSettings(Promise promise);

    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract boolean uses24HourClock();

    @ReactMethod(isBlockingSynchronousMethod = true)
    @Nullable
    public abstract Boolean usesAutoDateAndTime();

    @ReactMethod(isBlockingSynchronousMethod = true)
    @Nullable
    public abstract Boolean usesAutoTimeZone();

    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract boolean usesMetricSystem();

    public NativeRNLocalizeSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return "RNLocalize";
    }
}
