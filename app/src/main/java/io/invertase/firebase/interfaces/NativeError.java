package io.invertase.firebase.interfaces;

import com.facebook.react.bridge.WritableMap;

/* JADX INFO: loaded from: classes2.dex */
public interface NativeError {
    String getErrorCode();

    String getErrorMessage();

    String getFirebaseAppName();

    String getFirebaseServiceName();

    WritableMap getUserInfo();
}
