package com.facebook.react;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.facebook.common.logging.FLog;
import com.facebook.hermes.reactexecutor.HermesExecutor;
import com.facebook.hermes.reactexecutor.HermesExecutorFactory;
import com.facebook.react.ReactPackageTurboModuleManagerDelegate;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JSExceptionHandler;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.bridge.UIManagerProvider;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.common.SurfaceDelegateFactory;
import com.facebook.react.common.annotations.internal.LegacyArchitectureLogLevel;
import com.facebook.react.common.annotations.internal.LegacyArchitectureLogger;
import com.facebook.react.devsupport.DefaultDevSupportManagerFactory;
import com.facebook.react.devsupport.DevSupportManagerFactory;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.DevLoadingViewManager;
import com.facebook.react.devsupport.interfaces.PausedInDebuggerOverlayManager;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import com.facebook.react.internal.ChoreographerProvider;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import com.facebook.react.packagerconnection.RequestHandler;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.sentry.protocol.OperatingSystem;
import io.sentry.protocol.SdkVersion;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ReactInstanceManagerBuilder.kt */
/* JADX INFO: loaded from: classes.dex */
@Deprecated(level = DeprecationLevel.WARNING, message = "This class is part of Legacy Architecture and will be removed in a future release")
@Metadata(d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 d2\u00020\u0001:\u0001dB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u00107\u001a\u00020\u00002\b\u0010#\u001a\u0004\u0018\u00010$J\u0010\u00108\u001a\u00020\u00002\b\u0010(\u001a\u0004\u0018\u00010)J\u0010\u00109\u001a\u00020\u00002\b\u0010:\u001a\u0004\u0018\u00010\bJ\u000e\u0010;\u001a\u00020\u00002\u0006\u0010<\u001a\u00020\bJ\u000e\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010>\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\bJ\u000e\u0010?\u001a\u00020\u00002\u0006\u0010@\u001a\u00020\u0006J\u0014\u0010A\u001a\u00020\u00002\f\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00060CJ\u000e\u0010D\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010E\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010F\u001a\u00020\u00002\u0006\u0010G\u001a\u00020\u001bJ\u000e\u0010H\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010I\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010J\u001a\u00020\u00002\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\u000e\u0010K\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0011J\u000e\u0010L\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0011J\u0010\u0010M\u001a\u00020\u00002\b\u0010/\u001a\u0004\u0018\u000100J\u0010\u0010N\u001a\u00020\u00002\b\u00101\u001a\u0004\u0018\u000102J\u0010\u0010O\u001a\u00020\u00002\b\u00105\u001a\u0004\u0018\u000106J\u000e\u0010P\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0017J\u0010\u0010Q\u001a\u00020\u00002\b\u0010R\u001a\u0004\u0018\u00010\u0019J\u0010\u0010S\u001a\u00020\u00002\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fJ\u000e\u0010T\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\u0011J\u0010\u0010U\u001a\u00020\u00002\b\u0010V\u001a\u0004\u0018\u00010\"J\u000e\u0010W\u001a\u00020\u00002\u0006\u0010%\u001a\u00020&J\u000e\u0010X\u001a\u00020\u00002\u0006\u0010'\u001a\u00020&J\u001c\u0010Y\u001a\u00020\u00002\u0014\u0010*\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020,\u0018\u00010+J\u0010\u0010Z\u001a\u00020\u00002\b\u0010[\u001a\u0004\u0018\u00010.J\u0010\u0010\\\u001a\u00020\u00002\b\u00103\u001a\u0004\u0018\u000104J\u0006\u0010]\u001a\u00020^J\"\u0010_\u001a\u0004\u0018\u00010$2\u0006\u0010`\u001a\u00020\b2\u0006\u0010a\u001a\u00020\b2\u0006\u0010b\u001a\u00020cH\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010*\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020,\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u0004\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u000104X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006e"}, d2 = {"Lcom/facebook/react/ReactInstanceManagerBuilder;", "", "<init>", "()V", SdkVersion.JsonKeys.PACKAGES, "", "Lcom/facebook/react/ReactPackage;", "jsBundleAssetUrl", "", "jsBundleLoader", "Lcom/facebook/react/bridge/JSBundleLoader;", "jsMainModulePath", "bridgeIdleDebugListener", "Lcom/facebook/react/bridge/NotThreadSafeBridgeIdleDebugListener;", "application", "Landroid/app/Application;", "useDeveloperSupport", "", "devSupportManagerFactory", "Lcom/facebook/react/devsupport/DevSupportManagerFactory;", "requireActivity", "keepActivity", "initialLifecycleState", "Lcom/facebook/react/common/LifecycleState;", "jsExceptionHandler", "Lcom/facebook/react/bridge/JSExceptionHandler;", "currentActivity", "Landroid/app/Activity;", "defaultHardwareBackBtnHandler", "Lcom/facebook/react/modules/core/DefaultHardwareBackBtnHandler;", "redBoxHandler", "Lcom/facebook/react/devsupport/interfaces/RedBoxHandler;", "lazyViewManagersEnabled", "devBundleDownloadListener", "Lcom/facebook/react/devsupport/interfaces/DevBundleDownloadListener;", "javaScriptExecutorFactory", "Lcom/facebook/react/bridge/JavaScriptExecutorFactory;", "minNumShakes", "", "minTimeLeftInFrameForNonBatchedOperationMs", "uiManagerProvider", "Lcom/facebook/react/bridge/UIManagerProvider;", "customPackagerCommandHandlers", "", "Lcom/facebook/react/packagerconnection/RequestHandler;", "tmmDelegateBuilder", "Lcom/facebook/react/ReactPackageTurboModuleManagerDelegate$Builder;", "surfaceDelegateFactory", "Lcom/facebook/react/common/SurfaceDelegateFactory;", "devLoadingViewManager", "Lcom/facebook/react/devsupport/interfaces/DevLoadingViewManager;", "choreographerProvider", "Lcom/facebook/react/internal/ChoreographerProvider;", "pausedInDebuggerOverlayManager", "Lcom/facebook/react/devsupport/interfaces/PausedInDebuggerOverlayManager;", "setJavaScriptExecutorFactory", "setUIManagerProvider", "setBundleAssetName", "bundleAssetName", "setJSBundleFile", "jsBundleFile", "setJSBundleLoader", "setJSMainModulePath", "addPackage", "reactPackage", "addPackages", "reactPackages", "", "setBridgeIdleDebugListener", "setApplication", "setCurrentActivity", "activity", "setDefaultHardwareBackBtnHandler", "setUseDeveloperSupport", "setDevSupportManagerFactory", "setRequireActivity", "setKeepActivity", "setSurfaceDelegateFactory", "setDevLoadingViewManager", "setPausedInDebuggerOverlayManager", "setInitialLifecycleState", "setJSExceptionHandler", "handler", "setRedBoxHandler", "setLazyViewManagersEnabled", "setDevBundleDownloadListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setMinNumShakes", "setMinTimeLeftInFrameForNonBatchedOperationMs", "setCustomPackagerCommandHandlers", "setReactPackageTurboModuleManagerDelegateBuilder", "builder", "setChoreographerProvider", OperatingSystem.JsonKeys.BUILD, "Lcom/facebook/react/ReactInstanceManager;", "getDefaultJSExecutorFactory", "appName", "deviceName", "applicationContext", "Landroid/content/Context;", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ReactInstanceManagerBuilder {
    private static final Companion Companion = new Companion(null);
    private static final String TAG;
    private Application application;
    private NotThreadSafeBridgeIdleDebugListener bridgeIdleDebugListener;
    private ChoreographerProvider choreographerProvider;
    private Activity currentActivity;
    private Map<String, ? extends RequestHandler> customPackagerCommandHandlers;
    private DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler;
    private DevBundleDownloadListener devBundleDownloadListener;
    private DevLoadingViewManager devLoadingViewManager;
    private DevSupportManagerFactory devSupportManagerFactory;
    private LifecycleState initialLifecycleState;
    private JavaScriptExecutorFactory javaScriptExecutorFactory;
    private String jsBundleAssetUrl;
    private JSBundleLoader jsBundleLoader;
    private JSExceptionHandler jsExceptionHandler;
    private String jsMainModulePath;
    private boolean keepActivity;
    private boolean lazyViewManagersEnabled;
    private PausedInDebuggerOverlayManager pausedInDebuggerOverlayManager;
    private RedBoxHandler redBoxHandler;
    private boolean requireActivity;
    private SurfaceDelegateFactory surfaceDelegateFactory;
    private ReactPackageTurboModuleManagerDelegate.Builder tmmDelegateBuilder;
    private UIManagerProvider uiManagerProvider;
    private boolean useDeveloperSupport;
    private final List<ReactPackage> packages = new ArrayList();
    private int minNumShakes = 1;
    private int minTimeLeftInFrameForNonBatchedOperationMs = -1;

    public final ReactInstanceManagerBuilder setJavaScriptExecutorFactory(JavaScriptExecutorFactory javaScriptExecutorFactory) {
        this.javaScriptExecutorFactory = javaScriptExecutorFactory;
        return this;
    }

    public final ReactInstanceManagerBuilder setUIManagerProvider(UIManagerProvider uiManagerProvider) {
        this.uiManagerProvider = uiManagerProvider;
        return this;
    }

    public final ReactInstanceManagerBuilder setBundleAssetName(String bundleAssetName) {
        this.jsBundleAssetUrl = bundleAssetName == null ? null : "assets://" + bundleAssetName;
        this.jsBundleLoader = null;
        return this;
    }

    public final ReactInstanceManagerBuilder setJSBundleFile(String jsBundleFile) {
        Intrinsics.checkNotNullParameter(jsBundleFile, "jsBundleFile");
        if (StringsKt.startsWith$default(jsBundleFile, "assets://", false, 2, (Object) null)) {
            this.jsBundleAssetUrl = jsBundleFile;
            this.jsBundleLoader = null;
            return this;
        }
        return setJSBundleLoader(JSBundleLoader.INSTANCE.createFileLoader(jsBundleFile));
    }

    public final ReactInstanceManagerBuilder setJSBundleLoader(JSBundleLoader jsBundleLoader) {
        Intrinsics.checkNotNullParameter(jsBundleLoader, "jsBundleLoader");
        this.jsBundleLoader = jsBundleLoader;
        this.jsBundleAssetUrl = null;
        return this;
    }

    public final ReactInstanceManagerBuilder setJSMainModulePath(String jsMainModulePath) {
        Intrinsics.checkNotNullParameter(jsMainModulePath, "jsMainModulePath");
        this.jsMainModulePath = jsMainModulePath;
        return this;
    }

    public final ReactInstanceManagerBuilder addPackage(ReactPackage reactPackage) {
        Intrinsics.checkNotNullParameter(reactPackage, "reactPackage");
        this.packages.add(reactPackage);
        return this;
    }

    public final ReactInstanceManagerBuilder addPackages(List<? extends ReactPackage> reactPackages) {
        Intrinsics.checkNotNullParameter(reactPackages, "reactPackages");
        this.packages.addAll(reactPackages);
        return this;
    }

    public final ReactInstanceManagerBuilder setBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener bridgeIdleDebugListener) {
        Intrinsics.checkNotNullParameter(bridgeIdleDebugListener, "bridgeIdleDebugListener");
        this.bridgeIdleDebugListener = bridgeIdleDebugListener;
        return this;
    }

    public final ReactInstanceManagerBuilder setApplication(Application application) {
        Intrinsics.checkNotNullParameter(application, "application");
        this.application = application;
        return this;
    }

    public final ReactInstanceManagerBuilder setCurrentActivity(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.currentActivity = activity;
        return this;
    }

    public final ReactInstanceManagerBuilder setDefaultHardwareBackBtnHandler(DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler) {
        Intrinsics.checkNotNullParameter(defaultHardwareBackBtnHandler, "defaultHardwareBackBtnHandler");
        this.defaultHardwareBackBtnHandler = defaultHardwareBackBtnHandler;
        return this;
    }

    public final ReactInstanceManagerBuilder setUseDeveloperSupport(boolean useDeveloperSupport) {
        this.useDeveloperSupport = useDeveloperSupport;
        return this;
    }

    public final ReactInstanceManagerBuilder setDevSupportManagerFactory(DevSupportManagerFactory devSupportManagerFactory) {
        this.devSupportManagerFactory = devSupportManagerFactory;
        return this;
    }

    public final ReactInstanceManagerBuilder setRequireActivity(boolean requireActivity) {
        this.requireActivity = requireActivity;
        return this;
    }

    public final ReactInstanceManagerBuilder setKeepActivity(boolean keepActivity) {
        this.keepActivity = keepActivity;
        return this;
    }

    public final ReactInstanceManagerBuilder setSurfaceDelegateFactory(SurfaceDelegateFactory surfaceDelegateFactory) {
        this.surfaceDelegateFactory = surfaceDelegateFactory;
        return this;
    }

    public final ReactInstanceManagerBuilder setDevLoadingViewManager(DevLoadingViewManager devLoadingViewManager) {
        this.devLoadingViewManager = devLoadingViewManager;
        return this;
    }

    public final ReactInstanceManagerBuilder setPausedInDebuggerOverlayManager(PausedInDebuggerOverlayManager pausedInDebuggerOverlayManager) {
        this.pausedInDebuggerOverlayManager = pausedInDebuggerOverlayManager;
        return this;
    }

    public final ReactInstanceManagerBuilder setInitialLifecycleState(LifecycleState initialLifecycleState) {
        Intrinsics.checkNotNullParameter(initialLifecycleState, "initialLifecycleState");
        this.initialLifecycleState = initialLifecycleState;
        return this;
    }

    public final ReactInstanceManagerBuilder setJSExceptionHandler(JSExceptionHandler handler) {
        this.jsExceptionHandler = handler;
        return this;
    }

    public final ReactInstanceManagerBuilder setRedBoxHandler(RedBoxHandler redBoxHandler) {
        this.redBoxHandler = redBoxHandler;
        return this;
    }

    public final ReactInstanceManagerBuilder setLazyViewManagersEnabled(boolean lazyViewManagersEnabled) {
        this.lazyViewManagersEnabled = lazyViewManagersEnabled;
        return this;
    }

    public final ReactInstanceManagerBuilder setDevBundleDownloadListener(DevBundleDownloadListener listener) {
        this.devBundleDownloadListener = listener;
        return this;
    }

    public final ReactInstanceManagerBuilder setMinNumShakes(int minNumShakes) {
        this.minNumShakes = minNumShakes;
        return this;
    }

    public final ReactInstanceManagerBuilder setMinTimeLeftInFrameForNonBatchedOperationMs(int minTimeLeftInFrameForNonBatchedOperationMs) {
        this.minTimeLeftInFrameForNonBatchedOperationMs = minTimeLeftInFrameForNonBatchedOperationMs;
        return this;
    }

    public final ReactInstanceManagerBuilder setCustomPackagerCommandHandlers(Map<String, ? extends RequestHandler> customPackagerCommandHandlers) {
        this.customPackagerCommandHandlers = customPackagerCommandHandlers;
        return this;
    }

    public final ReactInstanceManagerBuilder setReactPackageTurboModuleManagerDelegateBuilder(ReactPackageTurboModuleManagerDelegate.Builder builder) {
        this.tmmDelegateBuilder = builder;
        return this;
    }

    public final ReactInstanceManagerBuilder setChoreographerProvider(ChoreographerProvider choreographerProvider) {
        this.choreographerProvider = choreographerProvider;
        return this;
    }

    public final ReactInstanceManager build() {
        Application application = this.application;
        if (application == null) {
            throw new IllegalStateException("Application property has not been set with this builder".toString());
        }
        if (this.initialLifecycleState == LifecycleState.RESUMED && this.currentActivity == null) {
            throw new IllegalStateException("Activity needs to be set if initial lifecycle state is resumed".toString());
        }
        if (!this.useDeveloperSupport && this.jsBundleAssetUrl == null && this.jsBundleLoader == null) {
            throw new IllegalStateException("JS Bundle File or Asset URL has to be provided when dev support is disabled".toString());
        }
        if (this.jsMainModulePath == null && this.jsBundleAssetUrl == null && this.jsBundleLoader == null) {
            throw new IllegalStateException("Either MainModulePath or JS Bundle File needs to be provided".toString());
        }
        String packageName = application.getPackageName();
        String friendlyDeviceName = AndroidInfoHelpers.getFriendlyDeviceName();
        String str = this.jsBundleAssetUrl;
        Application application2 = application;
        Activity activity = this.currentActivity;
        DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler = this.defaultHardwareBackBtnHandler;
        JavaScriptExecutorFactory defaultJSExecutorFactory = this.javaScriptExecutorFactory;
        if (defaultJSExecutorFactory == null) {
            Intrinsics.checkNotNull(packageName);
            Context applicationContext = application.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            defaultJSExecutorFactory = getDefaultJSExecutorFactory(packageName, friendlyDeviceName, applicationContext);
        }
        JavaScriptExecutorFactory javaScriptExecutorFactory = defaultJSExecutorFactory;
        JSBundleLoader jSBundleLoaderCreateAssetLoader = this.jsBundleLoader;
        if (jSBundleLoaderCreateAssetLoader == null && str != null) {
            jSBundleLoaderCreateAssetLoader = JSBundleLoader.INSTANCE.createAssetLoader(application2, str, false);
        }
        JSBundleLoader jSBundleLoader = jSBundleLoaderCreateAssetLoader;
        String str2 = this.jsMainModulePath;
        List<ReactPackage> list = this.packages;
        boolean z = this.useDeveloperSupport;
        DefaultDevSupportManagerFactory defaultDevSupportManagerFactory = this.devSupportManagerFactory;
        if (defaultDevSupportManagerFactory == null) {
            defaultDevSupportManagerFactory = new DefaultDevSupportManagerFactory();
        }
        DevSupportManagerFactory devSupportManagerFactory = defaultDevSupportManagerFactory;
        boolean z2 = this.requireActivity;
        boolean z3 = this.keepActivity;
        NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener = this.bridgeIdleDebugListener;
        LifecycleState lifecycleState = this.initialLifecycleState;
        if (lifecycleState != null) {
            return new ReactInstanceManager(application2, activity, defaultHardwareBackBtnHandler, javaScriptExecutorFactory, jSBundleLoader, str2, list, z, devSupportManagerFactory, z2, z3, notThreadSafeBridgeIdleDebugListener, lifecycleState, this.jsExceptionHandler, this.redBoxHandler, this.lazyViewManagersEnabled, this.devBundleDownloadListener, this.minNumShakes, this.minTimeLeftInFrameForNonBatchedOperationMs, this.uiManagerProvider, this.customPackagerCommandHandlers, this.tmmDelegateBuilder, this.surfaceDelegateFactory, this.devLoadingViewManager, this.choreographerProvider, this.pausedInDebuggerOverlayManager);
        }
        throw new IllegalStateException("Initial lifecycle state was not set".toString());
    }

    private final JavaScriptExecutorFactory getDefaultJSExecutorFactory(String appName, String deviceName, Context applicationContext) {
        ReactInstanceManager.initializeSoLoaderIfNecessary(applicationContext);
        try {
            HermesExecutor.INSTANCE.loadLibrary();
            return new HermesExecutorFactory();
        } catch (UnsatisfiedLinkError unused) {
            FLog.e(TAG, "Unable to load Hermes. Your application is not built correctly and will fail to execute");
            return null;
        }
    }

    /* JADX INFO: compiled from: ReactInstanceManagerBuilder.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/facebook/react/ReactInstanceManagerBuilder$Companion;", "", "<init>", "()V", "TAG", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        LegacyArchitectureLogger.assertLegacyArchitecture("ReactInstanceManagerBuilder", LegacyArchitectureLogLevel.ERROR);
        Intrinsics.checkNotNullExpressionValue("ReactInstanceManagerBuilder", "getSimpleName(...)");
        TAG = "ReactInstanceManagerBuilder";
    }
}
