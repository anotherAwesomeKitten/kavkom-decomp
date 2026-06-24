package com.facebook.react.bridge;

import com.facebook.common.logging.FLog;
import com.facebook.react.module.annotations.ReactModule;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ModuleSpec.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB%\b\u0002\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003J\b\u0010\r\u001a\u0004\u0018\u00010\u0006R\u001b\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u00038G¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\tR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u00068G¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000f"}, d2 = {"Lcom/facebook/react/bridge/ModuleSpec;", "", "provider", "Ljavax/inject/Provider;", "Lcom/facebook/react/bridge/NativeModule;", "name", "", "<init>", "(Ljavax/inject/Provider;Ljava/lang/String;)V", "()Ljavax/inject/Provider;", "moduleName", "()Ljava/lang/String;", "getProvider", "getName", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ModuleSpec {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "ModuleSpec";
    private final String name;
    private final Provider<? extends NativeModule> provider;

    public /* synthetic */ ModuleSpec(Provider provider, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(provider, str);
    }

    @JvmStatic
    public static final ModuleSpec nativeModuleSpec(Class<? extends NativeModule> cls, Provider<? extends NativeModule> provider) {
        return INSTANCE.nativeModuleSpec(cls, provider);
    }

    @JvmStatic
    public static final ModuleSpec nativeModuleSpec(String str, Provider<? extends NativeModule> provider) {
        return INSTANCE.nativeModuleSpec(str, provider);
    }

    @JvmStatic
    public static final ModuleSpec viewManagerSpec(Provider<? extends NativeModule> provider) {
        return INSTANCE.viewManagerSpec(provider);
    }

    private ModuleSpec(Provider<? extends NativeModule> provider, String str) {
        this.provider = provider;
        this.name = str;
    }

    /* synthetic */ ModuleSpec(Provider provider, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(provider, (i & 2) != 0 ? null : str);
    }

    public final Provider<? extends NativeModule> provider() {
        return this.provider;
    }

    public final String moduleName() {
        return this.name;
    }

    public final Provider<? extends NativeModule> getProvider() {
        return this.provider;
    }

    public final String getName() {
        return this.name;
    }

    /* JADX INFO: compiled from: ModuleSpec.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0006\u001a\u00020\u00072\u000e\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\tH\u0007J(\u0010\u000b\u001a\u00020\u00072\u000e\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\r2\u000e\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\tH\u0007J \u0010\u000b\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00052\u000e\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\tH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/facebook/react/bridge/ModuleSpec$Companion;", "", "<init>", "()V", "TAG", "", "viewManagerSpec", "Lcom/facebook/react/bridge/ModuleSpec;", "provider", "Ljavax/inject/Provider;", "Lcom/facebook/react/bridge/NativeModule;", "nativeModuleSpec", "type", "Ljava/lang/Class;", "className", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ModuleSpec viewManagerSpec(Provider<? extends NativeModule> provider) {
            Intrinsics.checkNotNullParameter(provider, "provider");
            return new ModuleSpec(provider, null, 2, 0 == true ? 1 : 0);
        }

        @JvmStatic
        public final ModuleSpec nativeModuleSpec(Class<? extends NativeModule> type, Provider<? extends NativeModule> provider) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(provider, "provider");
            ReactModule reactModule = (ReactModule) type.getAnnotation(ReactModule.class);
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (reactModule == null) {
                FLog.w(ModuleSpec.TAG, "Could not find @ReactModule annotation on " + type.getName() + ". Creating the module eagerly to get the name. Consider adding the annotation.");
                NativeModule nativeModule = provider.get();
                Intrinsics.checkNotNullExpressionValue(nativeModule, "get(...)");
                return new ModuleSpec(provider, nativeModule.getName(), defaultConstructorMarker);
            }
            return new ModuleSpec(provider, reactModule.name(), defaultConstructorMarker);
        }

        @JvmStatic
        public final ModuleSpec nativeModuleSpec(String className, Provider<? extends NativeModule> provider) {
            Intrinsics.checkNotNullParameter(className, "className");
            Intrinsics.checkNotNullParameter(provider, "provider");
            return new ModuleSpec(provider, className, null);
        }
    }
}
