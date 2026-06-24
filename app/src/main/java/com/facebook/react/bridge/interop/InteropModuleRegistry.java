package com.facebook.react.bridge.interop;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.common.annotations.internal.LegacyArchitectureLogger;
import com.facebook.react.internal.featureflags.ReactNativeNewArchitectureFeatureFlags;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InteropModuleRegistry.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\u0007\u001a\u0004\u0018\u0001H\b\"\n\b\u0000\u0010\b*\u0004\u0018\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u0006¢\u0006\u0002\u0010\u000bJ(\u0010\f\u001a\u00020\r\"\n\b\u0000\u0010\b*\u0004\u0018\u00010\t2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\b0\u00062\u0006\u0010\u000f\u001a\u00020\u0001J\b\u0010\u0010\u001a\u00020\u0011H\u0002R \u0010\u0004\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/facebook/react/bridge/interop/InteropModuleRegistry;", "", "<init>", "()V", "supportedModules", "", "Ljava/lang/Class;", "getInteropModule", "T", "Lcom/facebook/react/bridge/JavaScriptModule;", "requestedModule", "(Ljava/lang/Class;)Lcom/facebook/react/bridge/JavaScriptModule;", "registerInteropModule", "", "interopModuleInterface", "interopModule", "checkReactFeatureFlagsConditions", "", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class InteropModuleRegistry {
    private static final Companion Companion = new Companion(null);
    private final Map<Class<?>, Object> supportedModules = new LinkedHashMap();

    public final <T extends JavaScriptModule> T getInteropModule(Class<T> requestedModule) {
        Intrinsics.checkNotNullParameter(requestedModule, "requestedModule");
        if (checkReactFeatureFlagsConditions()) {
            Object obj = this.supportedModules.get(requestedModule);
            if (obj instanceof JavaScriptModule) {
                return (T) obj;
            }
        }
        return null;
    }

    public final <T extends JavaScriptModule> void registerInteropModule(Class<T> interopModuleInterface, Object interopModule) {
        Intrinsics.checkNotNullParameter(interopModuleInterface, "interopModuleInterface");
        Intrinsics.checkNotNullParameter(interopModule, "interopModule");
        if (checkReactFeatureFlagsConditions()) {
            this.supportedModules.put(interopModuleInterface, interopModule);
        }
    }

    private final boolean checkReactFeatureFlagsConditions() {
        return ReactNativeNewArchitectureFeatureFlags.enableFabricRenderer() && ReactNativeNewArchitectureFeatureFlags.useFabricInterop();
    }

    /* JADX INFO: compiled from: InteropModuleRegistry.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/facebook/react/bridge/interop/InteropModuleRegistry$Companion;", "", "<init>", "()V", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        LegacyArchitectureLogger.assertLegacyArchitecture$default("InteropModuleRegistry", null, 2, null);
    }
}
