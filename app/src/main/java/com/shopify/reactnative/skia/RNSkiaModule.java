package com.shopify.reactnative.skia;

import android.util.Log;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
@ReactModule(name = "RNSkiaModule")
public class RNSkiaModule extends NativeSkiaModuleSpec {
    public static final String NAME = "RNSkiaModule";
    private SkiaManager skiaManager;
    private final WeakReference<ReactApplicationContext> weakReactContext;

    public RNSkiaModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.weakReactContext = new WeakReference<>(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void invalidate() {
        super.invalidate();
        SkiaManager skiaManager = this.skiaManager;
        if (skiaManager != null) {
            skiaManager.invalidate();
            this.skiaManager.destroy();
            this.skiaManager = null;
        }
    }

    @Override // com.shopify.reactnative.skia.NativeSkiaModuleSpec, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNSkiaModule";
    }

    public SkiaManager getSkiaManager() {
        return this.skiaManager;
    }

    @Override // com.shopify.reactnative.skia.NativeSkiaModuleSpec
    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean install() {
        if (this.skiaManager != null) {
            return true;
        }
        try {
            System.loadLibrary("rnskia");
            ReactApplicationContext reactApplicationContext = this.weakReactContext.get();
            if (reactApplicationContext == null) {
                Log.e("RNSkiaModule", "React Application Context was null!");
                return false;
            }
            this.skiaManager = new SkiaManager(reactApplicationContext);
            return true;
        } catch (Exception e) {
            Log.e("RNSkiaModule", "Failed to initialize Skia Manager!", e);
            return false;
        }
    }
}
