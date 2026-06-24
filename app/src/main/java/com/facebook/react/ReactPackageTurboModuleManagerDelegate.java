package com.facebook.react;

import com.facebook.jni.HybridData;
import com.facebook.react.bridge.CxxModuleWrapper;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.internal.featureflags.ReactNativeNewArchitectureFeatureFlags;
import com.facebook.react.internal.turbomodule.core.TurboModuleManagerDelegate;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import io.sentry.protocol.OperatingSystem;
import io.sentry.protocol.SdkVersion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactPackageTurboModuleManagerDelegate.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001:\u0002\"#B\u001f\b\u0014\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bB'\b\u0014\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u0007\u0010\u000bJ\u001e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002J\b\u0010\u0018\u001a\u00020\u0015H\u0016J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u0012H\u0016J\u0010\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u0012H\u0016J\u0010\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u0012H\u0016J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u001b\u001a\u00020\u0012H\u0016J\u000e\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00120\u0005H\u0016J\b\u0010!\u001a\u00020\u0015H\u0002R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u000e\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/facebook/react/ReactPackageTurboModuleManagerDelegate;", "Lcom/facebook/react/internal/turbomodule/core/TurboModuleManagerDelegate;", "reactApplicationContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", SdkVersion.JsonKeys.PACKAGES, "", "Lcom/facebook/react/ReactPackage;", "<init>", "(Lcom/facebook/react/bridge/ReactApplicationContext;Ljava/util/List;)V", "hybridData", "Lcom/facebook/jni/HybridData;", "(Lcom/facebook/react/bridge/ReactApplicationContext;Ljava/util/List;Lcom/facebook/jni/HybridData;)V", "moduleProviders", "", "Lcom/facebook/react/ReactPackageTurboModuleManagerDelegate$ModuleProvider;", "packageModuleInfos", "", "", "", "Lcom/facebook/react/module/model/ReactModuleInfo;", "shouldEnableLegacyModuleInterop", "", "initialize", "", "unstable_shouldEnableLegacyModuleInterop", "getModule", "Lcom/facebook/react/turbomodule/core/interfaces/TurboModule;", "moduleName", "unstable_isModuleRegistered", "unstable_isLegacyModuleRegistered", "getLegacyModule", "Lcom/facebook/react/bridge/NativeModule;", "getEagerInitModuleNames", "shouldSupportLegacyPackages", "ModuleProvider", "Builder", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class ReactPackageTurboModuleManagerDelegate extends TurboModuleManagerDelegate {
    private final List<ModuleProvider> moduleProviders;
    private final Map<ModuleProvider, Map<String, ReactModuleInfo>> packageModuleInfos;
    private final boolean shouldEnableLegacyModuleInterop;

    /* JADX INFO: compiled from: ReactPackageTurboModuleManagerDelegate.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bà\u0080\u0001\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Lcom/facebook/react/ReactPackageTurboModuleManagerDelegate$ModuleProvider;", "", "getModule", "Lcom/facebook/react/bridge/NativeModule;", "moduleName", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface ModuleProvider {
        NativeModule getModule(String moduleName);
    }

    protected ReactPackageTurboModuleManagerDelegate(ReactApplicationContext reactApplicationContext, List<? extends ReactPackage> packages) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactApplicationContext");
        Intrinsics.checkNotNullParameter(packages, "packages");
        this.moduleProviders = new ArrayList();
        this.packageModuleInfos = new LinkedHashMap();
        this.shouldEnableLegacyModuleInterop = ReactNativeNewArchitectureFeatureFlags.enableBridgelessArchitecture() && ReactNativeNewArchitectureFeatureFlags.useTurboModuleInterop();
        initialize(reactApplicationContext, packages);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    protected ReactPackageTurboModuleManagerDelegate(ReactApplicationContext reactApplicationContext, List<? extends ReactPackage> packages, HybridData hybridData) {
        super(hybridData);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactApplicationContext");
        Intrinsics.checkNotNullParameter(packages, "packages");
        Intrinsics.checkNotNullParameter(hybridData, "hybridData");
        this.moduleProviders = new ArrayList();
        this.packageModuleInfos = new LinkedHashMap();
        this.shouldEnableLegacyModuleInterop = ReactNativeNewArchitectureFeatureFlags.enableBridgelessArchitecture() && ReactNativeNewArchitectureFeatureFlags.useTurboModuleInterop();
        initialize(reactApplicationContext, packages);
    }

    private final void initialize(final ReactApplicationContext reactApplicationContext, List<? extends ReactPackage> packages) {
        String name;
        ReactModuleInfo reactModuleInfo;
        for (final ReactPackage reactPackage : packages) {
            if (reactPackage instanceof BaseReactPackage) {
                ModuleProvider moduleProvider = new ModuleProvider() { // from class: com.facebook.react.ReactPackageTurboModuleManagerDelegate$$ExternalSyntheticLambda0
                    @Override // com.facebook.react.ReactPackageTurboModuleManagerDelegate.ModuleProvider
                    public final NativeModule getModule(String str) {
                        return ReactPackageTurboModuleManagerDelegate.initialize$lambda$0(reactPackage, reactApplicationContext, str);
                    }
                };
                this.moduleProviders.add(moduleProvider);
                this.packageModuleInfos.put(moduleProvider, ((BaseReactPackage) reactPackage).getReactModuleInfoProvider().getReactModuleInfos());
            } else if (shouldSupportLegacyPackages() && (reactPackage instanceof LazyReactPackage)) {
                LazyReactPackage lazyReactPackage = (LazyReactPackage) reactPackage;
                List<ModuleSpec> listInternal_getNativeModules$ReactAndroid_release = lazyReactPackage.internal_getNativeModules$ReactAndroid_release(reactApplicationContext);
                final LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (ModuleSpec moduleSpec : listInternal_getNativeModules$ReactAndroid_release) {
                    linkedHashMap.put(moduleSpec.getName(), moduleSpec.getProvider());
                }
                ModuleProvider moduleProvider2 = new ModuleProvider() { // from class: com.facebook.react.ReactPackageTurboModuleManagerDelegate$$ExternalSyntheticLambda1
                    @Override // com.facebook.react.ReactPackageTurboModuleManagerDelegate.ModuleProvider
                    public final NativeModule getModule(String str) {
                        return ReactPackageTurboModuleManagerDelegate.initialize$lambda$1(linkedHashMap, str);
                    }
                };
                this.moduleProviders.add(moduleProvider2);
                this.packageModuleInfos.put(moduleProvider2, lazyReactPackage.getReactModuleInfoProvider().getReactModuleInfos());
            } else if (shouldSupportLegacyPackages()) {
                List<NativeModule> listCreateNativeModules = reactPackage.createNativeModules(reactApplicationContext);
                final LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                LinkedHashMap linkedHashMap3 = new LinkedHashMap();
                for (NativeModule nativeModule : listCreateNativeModules) {
                    Class<?> cls = nativeModule.getClass();
                    ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
                    if (reactModule == null || (name = reactModule.name()) == null) {
                        name = nativeModule.getName();
                        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                    }
                    String str = name;
                    if (reactModule != null) {
                        String name2 = cls.getName();
                        Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
                        reactModuleInfo = new ReactModuleInfo(str, name2, reactModule.canOverrideExistingModule(), true, reactModule.isCxxModule(), ReactModuleInfo.INSTANCE.classIsTurboModule(cls));
                    } else {
                        String name3 = cls.getName();
                        Intrinsics.checkNotNullExpressionValue(name3, "getName(...)");
                        reactModuleInfo = new ReactModuleInfo(str, name3, nativeModule.canOverrideExistingModule(), true, CxxModuleWrapper.class.isAssignableFrom(cls), ReactModuleInfo.INSTANCE.classIsTurboModule(cls));
                    }
                    linkedHashMap3.put(str, reactModuleInfo);
                    linkedHashMap2.put(str, nativeModule);
                }
                ModuleProvider moduleProvider3 = new ModuleProvider() { // from class: com.facebook.react.ReactPackageTurboModuleManagerDelegate$$ExternalSyntheticLambda2
                    @Override // com.facebook.react.ReactPackageTurboModuleManagerDelegate.ModuleProvider
                    public final NativeModule getModule(String str2) {
                        return ReactPackageTurboModuleManagerDelegate.initialize$lambda$2(linkedHashMap2, str2);
                    }
                };
                this.moduleProviders.add(moduleProvider3);
                this.packageModuleInfos.put(moduleProvider3, linkedHashMap3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NativeModule initialize$lambda$0(ReactPackage reactPackage, ReactApplicationContext reactApplicationContext, String moduleName) {
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        return ((BaseReactPackage) reactPackage).getModule(moduleName, reactApplicationContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NativeModule initialize$lambda$1(Map map, String moduleName) {
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        Provider provider = (Provider) map.get(moduleName);
        if (provider != null) {
            return (NativeModule) provider.get();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NativeModule initialize$lambda$2(Map map, String module) {
        Intrinsics.checkNotNullParameter(module, "module");
        return (NativeModule) map.get(module);
    }

    @Override // com.facebook.react.internal.turbomodule.core.TurboModuleManagerDelegate
    /* JADX INFO: renamed from: unstable_shouldEnableLegacyModuleInterop, reason: from getter */
    public boolean getShouldEnableLegacyModuleInterop() {
        return this.shouldEnableLegacyModuleInterop;
    }

    @Override // com.facebook.react.internal.turbomodule.core.TurboModuleManagerDelegate
    public TurboModule getModule(String moduleName) {
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        NativeModule nativeModule = null;
        for (ModuleProvider moduleProvider : this.moduleProviders) {
            Map<String, ReactModuleInfo> map = this.packageModuleInfos.get(moduleProvider);
            ReactModuleInfo reactModuleInfo = map != null ? map.get(moduleName) : null;
            if (reactModuleInfo != null && reactModuleInfo.getIsTurboModule() && (nativeModule == null || reactModuleInfo.getCanOverrideExistingModule())) {
                NativeModule module = moduleProvider.getModule(moduleName);
                if (module != null) {
                    nativeModule = module;
                }
            }
        }
        if (!(nativeModule instanceof TurboModule)) {
            return null;
        }
        Intrinsics.checkNotNull(nativeModule, "null cannot be cast to non-null type com.facebook.react.turbomodule.core.interfaces.TurboModule");
        return (TurboModule) nativeModule;
    }

    @Override // com.facebook.react.internal.turbomodule.core.TurboModuleManagerDelegate
    public boolean unstable_isModuleRegistered(String moduleName) {
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        Iterator<ModuleProvider> it = this.moduleProviders.iterator();
        while (it.hasNext()) {
            Map<String, ReactModuleInfo> map = this.packageModuleInfos.get(it.next());
            ReactModuleInfo reactModuleInfo = map != null ? map.get(moduleName) : null;
            if (reactModuleInfo != null && reactModuleInfo.getIsTurboModule()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.facebook.react.internal.turbomodule.core.TurboModuleManagerDelegate
    public boolean unstable_isLegacyModuleRegistered(String moduleName) {
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        Iterator<ModuleProvider> it = this.moduleProviders.iterator();
        while (it.hasNext()) {
            Map<String, ReactModuleInfo> map = this.packageModuleInfos.get(it.next());
            ReactModuleInfo reactModuleInfo = map != null ? map.get(moduleName) : null;
            if (reactModuleInfo != null && !reactModuleInfo.getIsTurboModule()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.facebook.react.internal.turbomodule.core.TurboModuleManagerDelegate
    public NativeModule getLegacyModule(String moduleName) {
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        if (!getShouldEnableLegacyModuleInterop()) {
            return null;
        }
        NativeModule nativeModule = null;
        for (ModuleProvider moduleProvider : this.moduleProviders) {
            Map<String, ReactModuleInfo> map = this.packageModuleInfos.get(moduleProvider);
            ReactModuleInfo reactModuleInfo = map != null ? map.get(moduleName) : null;
            if (reactModuleInfo != null && !reactModuleInfo.getIsTurboModule() && (nativeModule == null || reactModuleInfo.getCanOverrideExistingModule())) {
                NativeModule module = moduleProvider.getModule(moduleName);
                if (module != null) {
                    nativeModule = module;
                }
            }
        }
        if (nativeModule instanceof TurboModule) {
            return null;
        }
        return nativeModule;
    }

    @Override // com.facebook.react.internal.turbomodule.core.TurboModuleManagerDelegate
    public List<String> getEagerInitModuleNames() {
        List listEmptyList;
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        Iterator<ModuleProvider> it = this.moduleProviders.iterator();
        while (it.hasNext()) {
            Map<String, ReactModuleInfo> map = this.packageModuleInfos.get(it.next());
            if (map == null || (listEmptyList = map.values()) == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            for (ReactModuleInfo reactModuleInfo : listEmptyList) {
                if (reactModuleInfo.getIsTurboModule() && reactModuleInfo.getNeedsEagerInit()) {
                    listCreateListBuilder.add(reactModuleInfo.getName());
                }
            }
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    private final boolean shouldSupportLegacyPackages() {
        return getShouldEnableLegacyModuleInterop();
    }

    /* JADX INFO: compiled from: ReactPackageTurboModuleManagerDelegate.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\t\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005J\u0010\u0010\n\u001a\u00020\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H$J\u0006\u0010\u000b\u001a\u00020\fR\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/facebook/react/ReactPackageTurboModuleManagerDelegate$Builder;", "", "<init>", "()V", SdkVersion.JsonKeys.PACKAGES, "", "Lcom/facebook/react/ReactPackage;", "context", "Lcom/facebook/react/bridge/ReactApplicationContext;", "setPackages", "setReactApplicationContext", OperatingSystem.JsonKeys.BUILD, "Lcom/facebook/react/ReactPackageTurboModuleManagerDelegate;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static abstract class Builder {
        private ReactApplicationContext context;
        private List<? extends ReactPackage> packages;

        protected abstract ReactPackageTurboModuleManagerDelegate build(ReactApplicationContext context, List<? extends ReactPackage> packages);

        public final Builder setPackages(List<? extends ReactPackage> packages) {
            Intrinsics.checkNotNullParameter(packages, "packages");
            this.packages = CollectionsKt.toList(packages);
            return this;
        }

        public final Builder setReactApplicationContext(ReactApplicationContext context) {
            this.context = context;
            return this;
        }

        public final ReactPackageTurboModuleManagerDelegate build() {
            ReactApplicationContext reactApplicationContext = this.context;
            if (reactApplicationContext == null) {
                throw new IllegalArgumentException("The ReactApplicationContext must be provided to create ReactPackageTurboModuleManagerDelegate".toString());
            }
            List<? extends ReactPackage> list = this.packages;
            if (list == null) {
                throw new IllegalArgumentException("A set of ReactPackages must be provided to create ReactPackageTurboModuleManagerDelegate".toString());
            }
            return build(reactApplicationContext, list);
        }
    }
}
