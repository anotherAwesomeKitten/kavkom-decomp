package com.facebook.react.shell;

import com.facebook.react.BaseReactPackage;
import com.facebook.react.ViewManagerOnDemandReactPackage;
import com.facebook.react.animated.NativeAnimatedModule;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.ClassFinder;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.modules.accessibilityinfo.AccessibilityInfoModule;
import com.facebook.react.modules.appearance.AppearanceModule;
import com.facebook.react.modules.appstate.AppStateModule;
import com.facebook.react.modules.blob.BlobModule;
import com.facebook.react.modules.blob.FileReaderModule;
import com.facebook.react.modules.camera.ImageStoreManager;
import com.facebook.react.modules.clipboard.ClipboardModule;
import com.facebook.react.modules.devloading.DevLoadingModule;
import com.facebook.react.modules.devtoolsruntimesettings.ReactDevToolsRuntimeSettingsModule;
import com.facebook.react.modules.dialog.DialogModule;
import com.facebook.react.modules.fresco.FrescoModule;
import com.facebook.react.modules.i18nmanager.I18nManagerModule;
import com.facebook.react.modules.image.ImageLoaderModule;
import com.facebook.react.modules.intent.IntentModule;
import com.facebook.react.modules.network.NetworkingModule;
import com.facebook.react.modules.permissions.PermissionsModule;
import com.facebook.react.modules.reactdevtoolssettings.ReactDevToolsSettingsManagerModule;
import com.facebook.react.modules.share.ShareModule;
import com.facebook.react.modules.sound.SoundManagerModule;
import com.facebook.react.modules.statusbar.StatusBarModule;
import com.facebook.react.modules.toast.ToastModule;
import com.facebook.react.modules.vibration.VibrationModule;
import com.facebook.react.modules.websocket.WebSocketModule;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.views.drawer.ReactDrawerLayoutManager;
import com.facebook.react.views.image.ReactImageManager;
import com.facebook.react.views.modal.ReactModalHostManager;
import com.facebook.react.views.progressbar.ReactProgressBarViewManager;
import com.facebook.react.views.safeareaview.ReactSafeAreaViewManager;
import com.facebook.react.views.scroll.ReactHorizontalScrollContainerViewManager;
import com.facebook.react.views.scroll.ReactHorizontalScrollViewManager;
import com.facebook.react.views.scroll.ReactScrollViewManager;
import com.facebook.react.views.swiperefresh.SwipeRefreshLayoutManager;
import com.facebook.react.views.switchview.ReactSwitchManager;
import com.facebook.react.views.text.PreparedLayoutTextViewManager;
import com.facebook.react.views.text.ReactTextViewManager;
import com.facebook.react.views.text.frescosupport.FrescoBasedReactTextInlineImageViewManager;
import com.facebook.react.views.textinput.ReactTextInputManager;
import com.facebook.react.views.unimplementedview.ReactUnimplementedViewManager;
import com.facebook.react.views.view.ReactViewManager;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: MainReactPackage.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\b\u0007\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001e\u0010\r\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000f0\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00120\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u00192\u0006\u0010\u000b\u001a\u00020\fH\u0016J\"\u0010\u001a\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\u000f2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\nH\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001dH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00120\u00118\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001f"}, d2 = {"Lcom/facebook/react/shell/MainReactPackage;", "Lcom/facebook/react/BaseReactPackage;", "Lcom/facebook/react/ViewManagerOnDemandReactPackage;", "config", "Lcom/facebook/react/shell/MainPackageConfig;", "<init>", "(Lcom/facebook/react/shell/MainPackageConfig;)V", "getModule", "Lcom/facebook/react/bridge/NativeModule;", "name", "", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "createViewManagers", "", "Lcom/facebook/react/uimanager/ViewManager;", "viewManagersMap", "", "Lcom/facebook/react/bridge/ModuleSpec;", "getViewManagersMap$annotations", "()V", "getViewManagersMap", "()Ljava/util/Map;", "getViewManagers", "getViewManagerNames", "", "createViewManager", "viewManagerName", "getReactModuleInfoProvider", "Lcom/facebook/react/module/model/ReactModuleInfoProvider;", "fallbackForMissingClass", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class MainReactPackage extends BaseReactPackage implements ViewManagerOnDemandReactPackage {
    private final MainPackageConfig config;
    private final Map<String, ModuleSpec> viewManagersMap;

    public MainReactPackage() {
        this(null, 1, null);
    }

    public static final Map fallbackForMissingClass$lambda$17(Map map) {
        return map;
    }

    public static /* synthetic */ void getViewManagersMap$annotations() {
    }

    public /* synthetic */ MainReactPackage(MainPackageConfig mainPackageConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : mainPackageConfig);
    }

    public MainReactPackage(MainPackageConfig mainPackageConfig) {
        this.config = mainPackageConfig;
        this.viewManagersMap = MapsKt.mapOf(TuplesKt.to(ReactDrawerLayoutManager.REACT_CLASS, ModuleSpec.INSTANCE.viewManagerSpec(new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda7
            @Override // javax.inject.Provider
            public final Object get() {
                return MainReactPackage.viewManagersMap$lambda$0();
            }
        })), TuplesKt.to(ReactHorizontalScrollViewManager.REACT_CLASS, ModuleSpec.INSTANCE.viewManagerSpec(new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda13
            @Override // javax.inject.Provider
            public final Object get() {
                return MainReactPackage.viewManagersMap$lambda$1();
            }
        })), TuplesKt.to(ReactHorizontalScrollContainerViewManager.REACT_CLASS, ModuleSpec.INSTANCE.viewManagerSpec(new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda14
            @Override // javax.inject.Provider
            public final Object get() {
                return MainReactPackage.viewManagersMap$lambda$2();
            }
        })), TuplesKt.to(ReactProgressBarViewManager.REACT_CLASS, ModuleSpec.INSTANCE.viewManagerSpec(new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda15
            @Override // javax.inject.Provider
            public final Object get() {
                return MainReactPackage.viewManagersMap$lambda$3();
            }
        })), TuplesKt.to(ReactSafeAreaViewManager.REACT_CLASS, ModuleSpec.INSTANCE.viewManagerSpec(new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda1
            @Override // javax.inject.Provider
            public final Object get() {
                return MainReactPackage.viewManagersMap$lambda$4();
            }
        })), TuplesKt.to(ReactScrollViewManager.REACT_CLASS, ModuleSpec.INSTANCE.viewManagerSpec(new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda2
            @Override // javax.inject.Provider
            public final Object get() {
                return MainReactPackage.viewManagersMap$lambda$5();
            }
        })), TuplesKt.to(ReactSwitchManager.REACT_CLASS, ModuleSpec.INSTANCE.viewManagerSpec(new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda3
            @Override // javax.inject.Provider
            public final Object get() {
                return MainReactPackage.viewManagersMap$lambda$6();
            }
        })), TuplesKt.to(SwipeRefreshLayoutManager.REACT_CLASS, ModuleSpec.INSTANCE.viewManagerSpec(new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda4
            @Override // javax.inject.Provider
            public final Object get() {
                return MainReactPackage.viewManagersMap$lambda$7();
            }
        })), TuplesKt.to(FrescoBasedReactTextInlineImageViewManager.REACT_CLASS, ModuleSpec.INSTANCE.viewManagerSpec(new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda5
            @Override // javax.inject.Provider
            public final Object get() {
                return MainReactPackage.viewManagersMap$lambda$8();
            }
        })), TuplesKt.to(ReactImageManager.REACT_CLASS, ModuleSpec.INSTANCE.viewManagerSpec(new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda6
            @Override // javax.inject.Provider
            public final Object get() {
                return MainReactPackage.viewManagersMap$lambda$9();
            }
        })), TuplesKt.to(ReactModalHostManager.REACT_CLASS, ModuleSpec.INSTANCE.viewManagerSpec(new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda8
            @Override // javax.inject.Provider
            public final Object get() {
                return MainReactPackage.viewManagersMap$lambda$10();
            }
        })), TuplesKt.to(ReactTextInputManager.REACT_CLASS, ModuleSpec.INSTANCE.viewManagerSpec(new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda9
            @Override // javax.inject.Provider
            public final Object get() {
                return MainReactPackage.viewManagersMap$lambda$11();
            }
        })), TuplesKt.to("RCTText", ModuleSpec.INSTANCE.viewManagerSpec(new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda10
            @Override // javax.inject.Provider
            public final Object get() {
                return MainReactPackage.viewManagersMap$lambda$12();
            }
        })), TuplesKt.to("RCTView", ModuleSpec.INSTANCE.viewManagerSpec(new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda11
            @Override // javax.inject.Provider
            public final Object get() {
                return MainReactPackage.viewManagersMap$lambda$13();
            }
        })), TuplesKt.to(ReactUnimplementedViewManager.REACT_CLASS, ModuleSpec.INSTANCE.viewManagerSpec(new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda12
            @Override // javax.inject.Provider
            public final Object get() {
                return MainReactPackage.viewManagersMap$lambda$14();
            }
        })));
    }

    @Override // com.facebook.react.BaseReactPackage, com.facebook.react.ReactPackage
    public NativeModule getModule(String name, ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        if (Intrinsics.areEqual(name, "AccessibilityInfo")) {
            return new AccessibilityInfoModule(reactContext);
        }
        if (Intrinsics.areEqual(name, "Appearance")) {
            return new AppearanceModule(reactContext, null, 2, null);
        }
        if (Intrinsics.areEqual(name, "AppState")) {
            return new AppStateModule(reactContext);
        }
        if (Intrinsics.areEqual(name, "BlobModule")) {
            return new BlobModule(reactContext);
        }
        if (Intrinsics.areEqual(name, "DevLoadingView")) {
            return new DevLoadingModule(reactContext);
        }
        if (Intrinsics.areEqual(name, FileReaderModule.INSTANCE.getNAME())) {
            return new FileReaderModule(reactContext);
        }
        if (Intrinsics.areEqual(name, "Clipboard")) {
            return new ClipboardModule(reactContext);
        }
        if (Intrinsics.areEqual(name, "DialogManagerAndroid")) {
            return new DialogModule(reactContext);
        }
        if (Intrinsics.areEqual(name, FrescoModule.NAME)) {
            MainPackageConfig mainPackageConfig = this.config;
            return new FrescoModule(reactContext, true, mainPackageConfig != null ? mainPackageConfig.getFrescoConfig() : null);
        }
        if (Intrinsics.areEqual(name, "I18nManager")) {
            return new I18nManagerModule(reactContext);
        }
        if (Intrinsics.areEqual(name, "ImageLoader")) {
            return new ImageLoaderModule(reactContext);
        }
        if (Intrinsics.areEqual(name, "ImageStoreManager")) {
            return new ImageStoreManager(reactContext);
        }
        if (Intrinsics.areEqual(name, "IntentAndroid")) {
            return new IntentModule(reactContext);
        }
        if (Intrinsics.areEqual(name, "NativeAnimatedModule")) {
            return ReactNativeFeatureFlags.cxxNativeAnimatedEnabled() ? null : new NativeAnimatedModule(reactContext);
        }
        if (Intrinsics.areEqual(name, "Networking")) {
            return new NetworkingModule(reactContext);
        }
        if (Intrinsics.areEqual(name, "PermissionsAndroid")) {
            return new PermissionsModule(reactContext);
        }
        if (Intrinsics.areEqual(name, "ShareModule")) {
            return new ShareModule(reactContext);
        }
        if (Intrinsics.areEqual(name, "StatusBarManager")) {
            return new StatusBarModule(reactContext);
        }
        if (Intrinsics.areEqual(name, "SoundManager")) {
            return new SoundManagerModule(reactContext);
        }
        if (Intrinsics.areEqual(name, "ToastAndroid")) {
            return new ToastModule(reactContext);
        }
        if (Intrinsics.areEqual(name, "Vibration")) {
            return new VibrationModule(reactContext);
        }
        if (Intrinsics.areEqual(name, "WebSocketModule")) {
            return new WebSocketModule(reactContext);
        }
        if (Intrinsics.areEqual(name, "ReactDevToolsSettingsManager")) {
            return new ReactDevToolsSettingsManagerModule(reactContext);
        }
        if (Intrinsics.areEqual(name, "ReactDevToolsRuntimeSettingsModule")) {
            return new ReactDevToolsRuntimeSettingsModule(reactContext);
        }
        return null;
    }

    @Override // com.facebook.react.BaseReactPackage, com.facebook.react.ReactPackage
    public List<ViewManager<?, ?>> createViewManagers(ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        BaseViewManager[] baseViewManagerArr = new BaseViewManager[15];
        baseViewManagerArr[0] = new ReactDrawerLayoutManager();
        baseViewManagerArr[1] = new ReactHorizontalScrollViewManager(null, 1, null);
        baseViewManagerArr[2] = new ReactHorizontalScrollContainerViewManager();
        baseViewManagerArr[3] = new ReactProgressBarViewManager();
        baseViewManagerArr[4] = new ReactScrollViewManager(null, 1, null);
        baseViewManagerArr[5] = new ReactSwitchManager();
        baseViewManagerArr[6] = new ReactSafeAreaViewManager();
        baseViewManagerArr[7] = new SwipeRefreshLayoutManager();
        baseViewManagerArr[8] = new FrescoBasedReactTextInlineImageViewManager(null, null, 3, null);
        baseViewManagerArr[9] = new ReactImageManager(null, null, null, 7, null);
        baseViewManagerArr[10] = new ReactModalHostManager();
        baseViewManagerArr[11] = new ReactTextInputManager();
        baseViewManagerArr[12] = ReactNativeFeatureFlags.enablePreparedTextLayout() ? new PreparedLayoutTextViewManager(null, 1, null) : new ReactTextViewManager(null, 1, null);
        baseViewManagerArr[13] = new ReactViewManager();
        baseViewManagerArr[14] = new ReactUnimplementedViewManager();
        return CollectionsKt.listOf((Object[]) baseViewManagerArr);
    }

    public final Map<String, ModuleSpec> getViewManagersMap() {
        return this.viewManagersMap;
    }

    public static final NativeModule viewManagersMap$lambda$0() {
        return new ReactDrawerLayoutManager();
    }

    public static final NativeModule viewManagersMap$lambda$1() {
        return new ReactHorizontalScrollViewManager(null, 1, null);
    }

    public static final NativeModule viewManagersMap$lambda$2() {
        return new ReactHorizontalScrollContainerViewManager();
    }

    public static final NativeModule viewManagersMap$lambda$3() {
        return new ReactProgressBarViewManager();
    }

    public static final NativeModule viewManagersMap$lambda$4() {
        return new ReactSafeAreaViewManager();
    }

    public static final NativeModule viewManagersMap$lambda$5() {
        return new ReactScrollViewManager(null, 1, null);
    }

    public static final NativeModule viewManagersMap$lambda$6() {
        return new ReactSwitchManager();
    }

    public static final NativeModule viewManagersMap$lambda$7() {
        return new SwipeRefreshLayoutManager();
    }

    public static final NativeModule viewManagersMap$lambda$8() {
        return new FrescoBasedReactTextInlineImageViewManager(null, null, 3, null);
    }

    public static final NativeModule viewManagersMap$lambda$9() {
        return new ReactImageManager(null, null, null, 7, null);
    }

    public static final NativeModule viewManagersMap$lambda$10() {
        return new ReactModalHostManager();
    }

    public static final NativeModule viewManagersMap$lambda$11() {
        return new ReactTextInputManager();
    }

    public static final NativeModule viewManagersMap$lambda$12() {
        if (ReactNativeFeatureFlags.enablePreparedTextLayout()) {
            return new PreparedLayoutTextViewManager(null, 1, null);
        }
        return new ReactTextViewManager(null, 1, null);
    }

    public static final NativeModule viewManagersMap$lambda$13() {
        return new ReactViewManager();
    }

    public static final NativeModule viewManagersMap$lambda$14() {
        return new ReactUnimplementedViewManager();
    }

    @Override // com.facebook.react.BaseReactPackage
    public List<ModuleSpec> getViewManagers(ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        return CollectionsKt.toList(this.viewManagersMap.values());
    }

    @Override // com.facebook.react.ViewManagerOnDemandReactPackage
    public Collection<String> getViewManagerNames(ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        return this.viewManagersMap.keySet();
    }

    @Override // com.facebook.react.ViewManagerOnDemandReactPackage
    public ViewManager<?, ?> createViewManager(ReactApplicationContext reactContext, String viewManagerName) {
        Provider<? extends NativeModule> provider;
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(viewManagerName, "viewManagerName");
        ModuleSpec moduleSpec = this.viewManagersMap.get(viewManagerName);
        NativeModule nativeModule = (moduleSpec == null || (provider = moduleSpec.provider()) == null) ? null : provider.get();
        if (nativeModule instanceof ViewManager) {
            return (ViewManager) nativeModule;
        }
        return null;
    }

    @Override // com.facebook.react.BaseReactPackage
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        if (!ClassFinder.canLoadClassesFromAnnotationProcessors()) {
            return fallbackForMissingClass();
        }
        try {
            Class<?> clsFindClass = ClassFinder.findClass("com.facebook.react.shell.MainReactPackage$$ReactModuleInfoProvider");
            Object objNewInstance = clsFindClass != null ? clsFindClass.newInstance() : null;
            ReactModuleInfoProvider reactModuleInfoProvider = objNewInstance instanceof ReactModuleInfoProvider ? (ReactModuleInfoProvider) objNewInstance : null;
            return reactModuleInfoProvider == null ? fallbackForMissingClass() : reactModuleInfoProvider;
        } catch (ClassNotFoundException unused) {
            return fallbackForMissingClass();
        } catch (IllegalAccessException e) {
            throw new RuntimeException("No ReactModuleInfoProvider for MainReactPackage$$ReactModuleInfoProvider", e);
        } catch (InstantiationException e2) {
            throw new RuntimeException("No ReactModuleInfoProvider for MainReactPackage$$ReactModuleInfoProvider", e2);
        }
    }

    private final ReactModuleInfoProvider fallbackForMissingClass() {
        Class[] clsArr = new Class[24];
        clsArr[0] = AccessibilityInfoModule.class;
        clsArr[1] = AppearanceModule.class;
        clsArr[2] = AppStateModule.class;
        clsArr[3] = BlobModule.class;
        clsArr[4] = DevLoadingModule.class;
        clsArr[5] = FileReaderModule.class;
        clsArr[6] = ClipboardModule.class;
        clsArr[7] = DialogModule.class;
        clsArr[8] = FrescoModule.class;
        clsArr[9] = I18nManagerModule.class;
        clsArr[10] = ImageLoaderModule.class;
        clsArr[11] = ImageStoreManager.class;
        clsArr[12] = IntentModule.class;
        clsArr[13] = ReactNativeFeatureFlags.cxxNativeAnimatedEnabled() ? null : NativeAnimatedModule.class;
        clsArr[14] = NetworkingModule.class;
        clsArr[15] = PermissionsModule.class;
        clsArr[16] = ReactDevToolsSettingsManagerModule.class;
        clsArr[17] = ReactDevToolsRuntimeSettingsModule.class;
        clsArr[18] = ShareModule.class;
        clsArr[19] = StatusBarModule.class;
        clsArr[20] = SoundManagerModule.class;
        clsArr[21] = ToastModule.class;
        clsArr[22] = VibrationModule.class;
        clsArr[23] = WebSocketModule.class;
        Class[] clsArr2 = (Class[]) ArraysKt.filterNotNull(clsArr).toArray(new Class[0]);
        ArrayList arrayList = new ArrayList();
        for (Class cls : clsArr2) {
            if (cls.isAnnotationPresent(ReactModule.class)) {
                arrayList.add(cls);
            }
        }
        ArrayList<Class<?>> arrayList2 = arrayList;
        final LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList2, 10)), 16));
        for (Class<?> cls2 : arrayList2) {
            Annotation annotation = cls2.getAnnotation(ReactModule.class);
            if (annotation == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            ReactModule reactModule = (ReactModule) annotation;
            String strName = reactModule.name();
            String strName2 = reactModule.name();
            String name = cls2.getName();
            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
            Pair pair = TuplesKt.to(strName, new ReactModuleInfo(strName2, name, reactModule.canOverrideExistingModule(), reactModule.needsEagerInit(), reactModule.isCxxModule(), ReactModuleInfo.INSTANCE.classIsTurboModule(cls2)));
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        return new ReactModuleInfoProvider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda0
            @Override // com.facebook.react.module.model.ReactModuleInfoProvider
            public final Map getReactModuleInfos() {
                return MainReactPackage.fallbackForMissingClass$lambda$17(linkedHashMap);
            }
        };
    }
}
