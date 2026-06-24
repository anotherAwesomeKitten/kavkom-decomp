package com.facebook.react.defaults;

import com.facebook.soloader.SoLoader;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX INFO: compiled from: DefaultSoLoader.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0007¨\u0006\u0006"}, d2 = {"Lcom/facebook/react/defaults/DefaultSoLoader;", "", "<init>", "()V", "maybeLoadSoLibrary", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DefaultSoLoader {
    public static final DefaultSoLoader INSTANCE = new DefaultSoLoader();

    private DefaultSoLoader() {
    }

    @JvmStatic
    public static final synchronized void maybeLoadSoLibrary() {
        SoLoader.loadLibrary("react_newarchdefaults");
        try {
            SoLoader.loadLibrary("appmodules");
        } catch (UnsatisfiedLinkError unused) {
        }
    }
}
