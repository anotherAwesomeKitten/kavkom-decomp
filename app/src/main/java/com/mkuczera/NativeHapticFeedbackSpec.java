package com.mkuczera;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class NativeHapticFeedbackSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "RNHapticFeedback";

    @ReactMethod
    public abstract void trigger(String str, @Nullable ReadableMap readableMap);

    public NativeHapticFeedbackSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return "RNHapticFeedback";
    }
}
