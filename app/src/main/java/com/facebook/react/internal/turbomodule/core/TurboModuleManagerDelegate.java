package com.facebook.react.internal.turbomodule.core;

import com.facebook.jni.HybridData;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import com.facebook.soloader.SoLoader;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TurboModuleManagerDelegate.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\t\b\u0014¢\u0006\u0004\b\u0002\u0010\u0003B\u0011\b\u0014\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\u0005H$J\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH&J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\rH&J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\rH\u0016J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u000fH\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0004R\u0016\u0010\u0007\u001a\u00020\u00058\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0003¨\u0006\u0019"}, d2 = {"Lcom/facebook/react/internal/turbomodule/core/TurboModuleManagerDelegate;", "", "<init>", "()V", "hybridData", "Lcom/facebook/jni/HybridData;", "(Lcom/facebook/jni/HybridData;)V", "mHybridData", "getMHybridData$annotations", "initHybrid", "getModule", "Lcom/facebook/react/turbomodule/core/interfaces/TurboModule;", "moduleName", "", "unstable_isModuleRegistered", "", "getLegacyModule", "Lcom/facebook/react/bridge/NativeModule;", "unstable_isLegacyModuleRegistered", "getEagerInitModuleNames", "", "unstable_shouldEnableLegacyModuleInterop", "maybeLoadOtherSoLibraries", "", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class TurboModuleManagerDelegate {
    private static final Companion Companion = new Companion(null);
    private final HybridData mHybridData;

    private static /* synthetic */ void getMHybridData$annotations() {
    }

    public NativeModule getLegacyModule(String moduleName) {
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        return null;
    }

    public abstract TurboModule getModule(String moduleName);

    protected abstract HybridData initHybrid();

    public boolean unstable_isLegacyModuleRegistered(String moduleName) {
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        return false;
    }

    public abstract boolean unstable_isModuleRegistered(String moduleName);

    public boolean unstable_shouldEnableLegacyModuleInterop() {
        return false;
    }

    protected TurboModuleManagerDelegate() {
        maybeLoadOtherSoLibraries();
        this.mHybridData = initHybrid();
    }

    protected TurboModuleManagerDelegate(HybridData hybridData) {
        Intrinsics.checkNotNullParameter(hybridData, "hybridData");
        maybeLoadOtherSoLibraries();
        this.mHybridData = hybridData;
    }

    public List<String> getEagerInitModuleNames() {
        return CollectionsKt.emptyList();
    }

    protected final synchronized void maybeLoadOtherSoLibraries() {
    }

    /* JADX INFO: compiled from: TurboModuleManagerDelegate.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/facebook/react/internal/turbomodule/core/TurboModuleManagerDelegate$Companion;", "", "<init>", "()V", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        SoLoader.loadLibrary("turbomodulejsijni");
    }
}
