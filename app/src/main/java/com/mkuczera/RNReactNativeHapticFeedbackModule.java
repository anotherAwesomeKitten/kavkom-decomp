package com.mkuczera;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;

/* JADX INFO: loaded from: classes2.dex */
public class RNReactNativeHapticFeedbackModule extends NativeHapticFeedbackSpec {
    ReactApplicationContext reactContext;

    RNReactNativeHapticFeedbackModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
    }

    @Override // com.mkuczera.NativeHapticFeedbackSpec, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNHapticFeedback";
    }

    @Override // com.mkuczera.NativeHapticFeedbackSpec
    public void trigger(String str, ReadableMap readableMap) {
        RNReactNativeHapticFeedbackModuleImpl.trigger(this.reactContext, str, readableMap);
    }
}
