package com.horcrux.svg;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class NativeSvgRenderableModuleSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "RNSVGRenderableModule";

    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract WritableMap getBBox(@Nullable Double d, @Nullable ReadableMap readableMap);

    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract WritableMap getCTM(@Nullable Double d);

    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract WritableMap getPointAtLength(@Nullable Double d, @Nullable ReadableMap readableMap);

    @ReactMethod
    public abstract void getRawResource(String str, Promise promise);

    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract WritableMap getScreenCTM(@Nullable Double d);

    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract double getTotalLength(@Nullable Double d);

    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract boolean isPointInFill(@Nullable Double d, @Nullable ReadableMap readableMap);

    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract boolean isPointInStroke(@Nullable Double d, @Nullable ReadableMap readableMap);

    public NativeSvgRenderableModuleSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return "RNSVGRenderableModule";
    }
}
