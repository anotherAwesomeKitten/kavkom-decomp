package com.lugg.RNCConfig;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;

/* JADX INFO: loaded from: classes2.dex */
public class RNCConfigModule extends NativeConfigModuleSpec {
    private final RNCConfigModuleImpl implementation;

    public RNCConfigModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.implementation = new RNCConfigModuleImpl(reactApplicationContext);
    }

    @Override // com.lugg.RNCConfig.NativeConfigModuleSpec, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNCConfigModule";
    }

    @Override // com.lugg.RNCConfig.NativeConfigModuleSpec
    public WritableMap getConfig() {
        return this.implementation.getConfig();
    }
}
