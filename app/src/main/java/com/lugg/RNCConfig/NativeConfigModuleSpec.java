package com.lugg.RNCConfig;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class NativeConfigModuleSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "RNCConfigModule";

    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract WritableMap getConfig();

    public NativeConfigModuleSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return "RNCConfigModule";
    }
}
