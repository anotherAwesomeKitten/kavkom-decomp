package com.facebook.react.runtime;

import android.content.res.AssetManager;
import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeExceptionsManagerSpec;
import com.facebook.jni.HybridData;
import com.facebook.react.DebugCorePackage;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactPackageTurboModuleManagerDelegate;
import com.facebook.react.ViewManagerOnDemandReactPackage;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JSBundleLoaderDelegate;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.NativeArray;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.RuntimeExecutor;
import com.facebook.react.bridge.RuntimeScheduler;
import com.facebook.react.bridge.queue.MessageQueueThread;
import com.facebook.react.bridge.queue.MessageQueueThreadSpec;
import com.facebook.react.bridge.queue.QueueThreadExceptionHandler;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.bridge.queue.ReactQueueConfigurationImpl;
import com.facebook.react.bridge.queue.ReactQueueConfigurationSpec;
import com.facebook.react.common.annotations.FrameworkAPI;
import com.facebook.react.common.annotations.UnstableReactNativeAPI;
import com.facebook.react.devsupport.InspectorFlags;
import com.facebook.react.devsupport.StackTraceHelper;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.fabric.ComponentFactory;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.fabric.FabricUIManagerBinding;
import com.facebook.react.fabric.events.EventBeatManager;
import com.facebook.react.interfaces.exceptionmanager.ReactJsExceptionHandler;
import com.facebook.react.internal.AndroidChoreographerProvider;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.internal.turbomodule.core.TurboModuleManager;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.JavaTimerManager;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.turbomodule.core.CallInvokerHolderImpl;
import com.facebook.react.turbomodule.core.NativeMethodCallInvokerHolderImpl;
import com.facebook.react.uimanager.ComponentNameResolver;
import com.facebook.react.uimanager.ComponentNameResolverBinding;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.UIConstantsProviderBinding;
import com.facebook.react.uimanager.UIManagerModuleConstantsHelper;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.facebook.react.uimanager.ViewManagerResolver;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.util.RNLog;
import com.facebook.soloader.SoLoader;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactInstance.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0080\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\b\u0001\u0018\u0000 w2\u00020\u0001:\u0003uvwBA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\u0006\u0010(\u001a\u00020)J\u000e\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020,J\u001e\u0010-\u001a\u00020\r\"\b\b\u0000\u0010.*\u00020/2\f\u00100\u001a\b\u0012\u0004\u0012\u0002H.01J%\u00106\u001a\u0004\u0018\u0001H.\"\b\b\u0000\u0010.*\u00020/2\f\u00100\u001a\b\u0012\u0004\u0012\u0002H.01¢\u0006\u0002\u00107J\u001f\u00106\u001a\u0004\u0018\u0001H.\"\b\b\u0000\u0010.*\u00020/2\u0006\u00108\u001a\u000209¢\u0006\u0002\u0010:J\u0010\u0010;\u001a\u00020)2\u0006\u0010<\u001a\u00020=H\u0007J\u0010\u0010>\u001a\u00020)2\u0006\u0010<\u001a\u00020=H\u0007J\u0010\u0010?\u001a\u00020)2\u0006\u0010<\u001a\u00020=H\u0007J\b\u0010@\u001a\u00020)H\u0007JU\u0010A\u001a\u00020\u00132\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020E2\u0006\u0010G\u001a\u00020\u00192\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020K2\b\u0010L\u001a\u0004\u0018\u00010M2\u0006\u0010N\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0083 J\u0019\u0010O\u001a\u00020)2\u0006\u0010P\u001a\u0002092\u0006\u0010Q\u001a\u000209H\u0082 J\u0019\u0010R\u001a\u00020)2\u0006\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u000209H\u0082 J\t\u0010V\u001a\u00020WH\u0086 J\t\u0010X\u001a\u00020YH\u0082 J\t\u0010Z\u001a\u00020[H\u0082 J\t\u0010\\\u001a\u00020[H\u0086 J\t\u0010]\u001a\u00020^H\u0082 J\t\u0010_\u001a\u00020`H\u0082 J!\u0010a\u001a\u00020)2\u0006\u0010b\u001a\u0002092\u0006\u0010c\u001a\u0002092\u0006\u0010d\u001a\u00020eH\u0086 J\u0019\u0010f\u001a\u00020)2\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u000209H\u0082 J\u0011\u0010j\u001a\u00020)2\u0006\u0010k\u001a\u00020hH\u0082 J\t\u0010l\u001a\u00020)H\u0087 J\u000e\u0010m\u001a\u00020)2\u0006\u0010n\u001a\u00020hJ\u0016\u0010s\u001a\u00020)2\u0006\u0010g\u001a\u00020h2\u0006\u0010t\u001a\u000209R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\u00020\u00138\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020!¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010$\u001a\u00020%¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0017\u00102\u001a\b\u0012\u0004\u0012\u00020/038F¢\u0006\u0006\u001a\u0004\b4\u00105R\u0011\u0010o\u001a\u00020p8F¢\u0006\u0006\u001a\u0004\bq\u0010r¨\u0006x"}, d2 = {"Lcom/facebook/react/runtime/ReactInstance;", "", "context", "Lcom/facebook/react/runtime/BridgelessReactContext;", "delegate", "Lcom/facebook/react/runtime/ReactHostDelegate;", "componentFactory", "Lcom/facebook/react/fabric/ComponentFactory;", "devSupportManager", "Lcom/facebook/react/devsupport/interfaces/DevSupportManager;", "exceptionHandler", "Lcom/facebook/react/bridge/queue/QueueThreadExceptionHandler;", "useDevSupport", "", "reactHostInspectorTarget", "Lcom/facebook/react/runtime/ReactHostInspectorTarget;", "<init>", "(Lcom/facebook/react/runtime/BridgelessReactContext;Lcom/facebook/react/runtime/ReactHostDelegate;Lcom/facebook/react/fabric/ComponentFactory;Lcom/facebook/react/devsupport/interfaces/DevSupportManager;Lcom/facebook/react/bridge/queue/QueueThreadExceptionHandler;ZLcom/facebook/react/runtime/ReactHostInspectorTarget;)V", "mHybridData", "Lcom/facebook/jni/HybridData;", "getMHybridData$annotations", "()V", "turboModuleManager", "Lcom/facebook/react/internal/turbomodule/core/TurboModuleManager;", "javaTimerManager", "Lcom/facebook/react/modules/core/JavaTimerManager;", "viewManagerResolver", "Lcom/facebook/react/runtime/ReactInstance$BridgelessViewManagerResolver;", "reactQueueConfiguration", "Lcom/facebook/react/bridge/queue/ReactQueueConfiguration;", "getReactQueueConfiguration", "()Lcom/facebook/react/bridge/queue/ReactQueueConfiguration;", "fabricUIManager", "Lcom/facebook/react/fabric/FabricUIManager;", "getFabricUIManager", "()Lcom/facebook/react/fabric/FabricUIManager;", "javaScriptContextHolder", "Lcom/facebook/react/bridge/JavaScriptContextHolder;", "getJavaScriptContextHolder", "()Lcom/facebook/react/bridge/JavaScriptContextHolder;", "initializeEagerTurboModules", "", "loadJSBundle", "bundleLoader", "Lcom/facebook/react/bridge/JSBundleLoader;", "hasNativeModule", "T", "Lcom/facebook/react/bridge/NativeModule;", "nativeModuleInterface", "Ljava/lang/Class;", "nativeModules", "", "getNativeModules", "()Ljava/util/Collection;", "getNativeModule", "(Ljava/lang/Class;)Lcom/facebook/react/bridge/NativeModule;", "nativeModuleName", "", "(Ljava/lang/String;)Lcom/facebook/react/bridge/NativeModule;", "prerenderSurface", "surface", "Lcom/facebook/react/runtime/ReactSurfaceImpl;", "startSurface", "stopSurface", "destroy", "initHybrid", "jsRuntimeFactory", "Lcom/facebook/react/runtime/JSRuntimeFactory;", "jsMessageQueueThread", "Lcom/facebook/react/bridge/queue/MessageQueueThread;", "nativeModulesMessageQueueThread", "timerManager", "jsTimerExecutor", "Lcom/facebook/react/runtime/JSTimerExecutor;", "jReactExceptionsManager", "Lcom/facebook/react/interfaces/exceptionmanager/ReactJsExceptionHandler;", "jBindingsInstaller", "Lcom/facebook/react/runtime/BindingsInstaller;", "isProfiling", "loadJSBundleFromFile", "fileName", "sourceURL", "loadJSBundleFromAssets", "assetManager", "Landroid/content/res/AssetManager;", "assetURL", "getJSCallInvokerHolder", "Lcom/facebook/react/turbomodule/core/CallInvokerHolderImpl;", "getNativeMethodCallInvokerHolder", "Lcom/facebook/react/turbomodule/core/NativeMethodCallInvokerHolderImpl;", "getUnbufferedRuntimeExecutor", "Lcom/facebook/react/bridge/RuntimeExecutor;", "getBufferedRuntimeExecutor", "getRuntimeScheduler", "Lcom/facebook/react/bridge/RuntimeScheduler;", "getJavaScriptContext", "", "callFunctionOnModule", "moduleName", "methodName", "args", "Lcom/facebook/react/bridge/NativeArray;", "registerSegmentNative", RRWebVideoEvent.JsonKeys.SEGMENT_ID, "", "segmentPath", "handleMemoryPressureJs", "pressureLevel", "unregisterFromInspector", "handleMemoryPressure", "level", "eventDispatcher", "Lcom/facebook/react/uimanager/events/EventDispatcher;", "getEventDispatcher", "()Lcom/facebook/react/uimanager/events/EventDispatcher;", "registerSegment", "path", "ReactJsExceptionHandlerImpl", "BridgelessViewManagerResolver", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
@FrameworkAPI
@UnstableReactNativeAPI
public final class ReactInstance {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG;
    private final BridgelessReactContext context;
    private final FabricUIManager fabricUIManager;
    private final JavaScriptContextHolder javaScriptContextHolder;
    private final JavaTimerManager javaTimerManager;
    private final HybridData mHybridData;
    private final ReactQueueConfiguration reactQueueConfiguration;
    private final TurboModuleManager turboModuleManager;
    private final BridgelessViewManagerResolver viewManagerResolver;

    private final native long getJavaScriptContext();

    private static /* synthetic */ void getMHybridData$annotations() {
    }

    private final native NativeMethodCallInvokerHolderImpl getNativeMethodCallInvokerHolder();

    private final native RuntimeScheduler getRuntimeScheduler();

    private final native RuntimeExecutor getUnbufferedRuntimeExecutor();

    private final native void handleMemoryPressureJs(int pressureLevel);

    private final native HybridData initHybrid(JSRuntimeFactory jsRuntimeFactory, MessageQueueThread jsMessageQueueThread, MessageQueueThread nativeModulesMessageQueueThread, JavaTimerManager timerManager, JSTimerExecutor jsTimerExecutor, ReactJsExceptionHandler jReactExceptionsManager, BindingsInstaller jBindingsInstaller, boolean isProfiling, ReactHostInspectorTarget reactHostInspectorTarget);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void loadJSBundleFromAssets(AssetManager assetManager, String assetURL);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void loadJSBundleFromFile(String fileName, String sourceURL);

    private final native void registerSegmentNative(int segmentId, String segmentPath);

    public final native void callFunctionOnModule(String moduleName, String methodName, NativeArray args);

    public final native RuntimeExecutor getBufferedRuntimeExecutor();

    public final native CallInvokerHolderImpl getJSCallInvokerHolder();

    public final native void unregisterFromInspector();

    public ReactInstance(BridgelessReactContext context, ReactHostDelegate delegate, ComponentFactory componentFactory, DevSupportManager devSupportManager, QueueThreadExceptionHandler exceptionHandler, boolean z, ReactHostInspectorTarget reactHostInspectorTarget) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(componentFactory, "componentFactory");
        Intrinsics.checkNotNullParameter(devSupportManager, "devSupportManager");
        Intrinsics.checkNotNullParameter(exceptionHandler, "exceptionHandler");
        this.context = context;
        Systrace.beginSection(0L, "ReactInstance.initialize");
        ReactQueueConfigurationImpl reactQueueConfigurationImplCreate = ReactQueueConfigurationImpl.INSTANCE.create(new ReactQueueConfigurationSpec(MessageQueueThreadSpec.INSTANCE.newBackgroundThreadSpec("v_native"), MessageQueueThreadSpec.INSTANCE.newBackgroundThreadSpec("v_js")), exceptionHandler);
        this.reactQueueConfiguration = reactQueueConfigurationImplCreate;
        FLog.d(TAG, "Calling initializeMessageQueueThreads()");
        context.initializeMessageQueueThreads(reactQueueConfigurationImplCreate);
        MessageQueueThread jSQueueThread = reactQueueConfigurationImplCreate.getJSQueueThread();
        MessageQueueThread nativeModulesQueueThread = reactQueueConfigurationImplCreate.getNativeModulesQueueThread();
        ReactChoreographer.INSTANCE.initialize(AndroidChoreographerProvider.getInstance());
        devSupportManager.startInspector();
        JSTimerExecutor jSTimerExecutor = new JSTimerExecutor();
        JavaTimerManager javaTimerManager = new JavaTimerManager(context, jSTimerExecutor, ReactChoreographer.INSTANCE.getInstance(), devSupportManager);
        this.javaTimerManager = javaTimerManager;
        this.mHybridData = initHybrid(delegate.getJsRuntimeFactory(), jSQueueThread, nativeModulesQueueThread, javaTimerManager, jSTimerExecutor, new ReactJsExceptionHandlerImpl(this, exceptionHandler), delegate.getBindingsInstaller(), Systrace.isTracing(0L) || InspectorFlags.getIsProfilingBuild(), reactHostInspectorTarget);
        this.javaScriptContextHolder = new JavaScriptContextHolder(getJavaScriptContext());
        Systrace.beginSection(0L, "ReactInstance.initialize#initTurboModules");
        ArrayList arrayList = new ArrayList();
        arrayList.add(new CoreReactPackage(context.getDevSupportManager(), context.getDefaultHardwareBackBtnHandler()));
        if (z) {
            arrayList.add(new DebugCorePackage());
        }
        arrayList.addAll(delegate.getReactPackages());
        ReactPackageTurboModuleManagerDelegate reactPackageTurboModuleManagerDelegateBuild = delegate.getTurboModuleManagerDelegateBuilder().setPackages(arrayList).setReactApplicationContext(context).build();
        RuntimeExecutor unbufferedRuntimeExecutor = getUnbufferedRuntimeExecutor();
        this.turboModuleManager = new TurboModuleManager(unbufferedRuntimeExecutor, reactPackageTurboModuleManagerDelegateBuild, getJSCallInvokerHolder(), getNativeMethodCallInvokerHolder());
        Systrace.endSection(0L);
        Systrace.beginSection(0L, "ReactInstance.initialize#initFabric");
        BridgelessViewManagerResolver bridgelessViewManagerResolver = new BridgelessViewManagerResolver(arrayList, context);
        this.viewManagerResolver = bridgelessViewManagerResolver;
        ComponentNameResolverBinding.install(unbufferedRuntimeExecutor, new ComponentNameResolver() { // from class: com.facebook.react.runtime.ReactInstance.1
            @Override // com.facebook.react.uimanager.ComponentNameResolver
            public String[] getComponentNames() {
                Collection<String> viewManagerNames = ReactInstance.this.viewManagerResolver.getViewManagerNames();
                if (viewManagerNames.isEmpty()) {
                    FLog.e(ReactInstance.TAG, "No ViewManager names found");
                    return new String[0];
                }
                return (String[]) viewManagerNames.toArray(new String[0]);
            }
        });
        if (ReactNativeFeatureFlags.useNativeViewConfigsInBridgelessMode()) {
            final HashMap map = new HashMap();
            UIConstantsProviderBinding.install(unbufferedRuntimeExecutor, new UIConstantsProviderBinding.DefaultEventTypesProvider() { // from class: com.facebook.react.runtime.ReactInstance$$ExternalSyntheticLambda1
                @Override // com.facebook.react.uimanager.UIConstantsProviderBinding.DefaultEventTypesProvider
                public final NativeMap getDefaultEventTypes() {
                    return ReactInstance._init_$lambda$0();
                }
            }, new UIConstantsProviderBinding.ConstantsForViewManagerProvider() { // from class: com.facebook.react.runtime.ReactInstance$$ExternalSyntheticLambda2
                @Override // com.facebook.react.uimanager.UIConstantsProviderBinding.ConstantsForViewManagerProvider
                public final NativeMap getConstantsForViewManager(String str) {
                    return ReactInstance._init_$lambda$1(this.f$0, map, str);
                }
            }, new UIConstantsProviderBinding.ConstantsProvider() { // from class: com.facebook.react.runtime.ReactInstance$$ExternalSyntheticLambda3
                @Override // com.facebook.react.uimanager.UIConstantsProviderBinding.ConstantsProvider
                public final NativeMap getConstants() {
                    return ReactInstance._init_$lambda$2(this.f$0, map);
                }
            });
        }
        EventBeatManager eventBeatManager = new EventBeatManager();
        FabricUIManager fabricUIManager = new FabricUIManager(context, new ViewManagerRegistry(bridgelessViewManagerResolver), eventBeatManager);
        this.fabricUIManager = fabricUIManager;
        DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(context);
        new FabricUIManagerBinding().register(getBufferedRuntimeExecutor(), getRuntimeScheduler(), fabricUIManager, eventBeatManager, componentFactory);
        fabricUIManager.initialize();
        Systrace.endSection(0L);
        Systrace.endSection(0L);
    }

    public final ReactQueueConfiguration getReactQueueConfiguration() {
        return this.reactQueueConfiguration;
    }

    public final FabricUIManager getFabricUIManager() {
        return this.fabricUIManager;
    }

    public final JavaScriptContextHolder getJavaScriptContextHolder() {
        return this.javaScriptContextHolder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NativeMap _init_$lambda$0() {
        return Arguments.makeNativeMap((Map<String, ? extends Object>) UIManagerModuleConstantsHelper.getDefaultExportableEventTypes());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NativeMap _init_$lambda$1(ReactInstance reactInstance, Map map, String viewManagerName) {
        Intrinsics.checkNotNullParameter(viewManagerName, "viewManagerName");
        ViewManager<?, ?> viewManager = reactInstance.viewManagerResolver.getViewManager(viewManagerName);
        if (viewManager == null) {
            return null;
        }
        return INSTANCE.getConstantsForViewManager(viewManager, map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NativeMap _init_$lambda$2(ReactInstance reactInstance, Map map) {
        Map mapCreateConstants = INSTANCE.createConstants(new ArrayList(reactInstance.viewManagerResolver.getEagerViewManagerMap().values()), map);
        Collection<String> lazyViewManagerNames = reactInstance.viewManagerResolver.getLazyViewManagerNames();
        if (!lazyViewManagerNames.isEmpty()) {
            mapCreateConstants.put("ViewManagerNames", new ArrayList(lazyViewManagerNames));
            mapCreateConstants.put("LazyViewManagersEnabled", true);
        }
        return Arguments.makeNativeMap((Map<String, ? extends Object>) mapCreateConstants);
    }

    public final void initializeEagerTurboModules() {
        this.reactQueueConfiguration.getNativeModulesQueueThread().runOnQueue(new Runnable() { // from class: com.facebook.react.runtime.ReactInstance$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ReactInstance.initializeEagerTurboModules$lambda$3(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initializeEagerTurboModules$lambda$3(ReactInstance reactInstance) {
        Systrace.beginSection(0L, "initializeEagerTurboModules");
        Iterator<String> it = reactInstance.turboModuleManager.getEagerInitModuleNames().iterator();
        while (it.hasNext()) {
            reactInstance.turboModuleManager.getModule(it.next());
        }
        Systrace.endSection(0L);
    }

    /* JADX INFO: compiled from: ReactInstance.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/facebook/react/runtime/ReactInstance$ReactJsExceptionHandlerImpl;", "Lcom/facebook/react/interfaces/exceptionmanager/ReactJsExceptionHandler;", "queueThreadExceptionHandler", "Lcom/facebook/react/bridge/queue/QueueThreadExceptionHandler;", "<init>", "(Lcom/facebook/react/runtime/ReactInstance;Lcom/facebook/react/bridge/queue/QueueThreadExceptionHandler;)V", "reportJsException", "", "errorMap", "Lcom/facebook/react/interfaces/exceptionmanager/ReactJsExceptionHandler$ProcessedError;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private final class ReactJsExceptionHandlerImpl implements ReactJsExceptionHandler {
        private final QueueThreadExceptionHandler queueThreadExceptionHandler;
        final /* synthetic */ ReactInstance this$0;

        public ReactJsExceptionHandlerImpl(ReactInstance reactInstance, QueueThreadExceptionHandler queueThreadExceptionHandler) {
            Intrinsics.checkNotNullParameter(queueThreadExceptionHandler, "queueThreadExceptionHandler");
            this.this$0 = reactInstance;
            this.queueThreadExceptionHandler = queueThreadExceptionHandler;
        }

        @Override // com.facebook.react.interfaces.exceptionmanager.ReactJsExceptionHandler
        public void reportJsException(ReactJsExceptionHandler.ProcessedError errorMap) {
            Intrinsics.checkNotNullParameter(errorMap, "errorMap");
            JavaOnlyMap javaOnlyMapConvertProcessedError$ReactAndroid_release = StackTraceHelper.convertProcessedError$ReactAndroid_release(errorMap);
            try {
                NativeModule nativeModule = this.this$0.getNativeModule("ExceptionsManager");
                if (nativeModule == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                ((NativeExceptionsManagerSpec) nativeModule).reportException(javaOnlyMapConvertProcessedError$ReactAndroid_release);
            } catch (Exception e) {
                this.queueThreadExceptionHandler.handleException(e);
            }
        }
    }

    public final void loadJSBundle(JSBundleLoader bundleLoader) {
        Intrinsics.checkNotNullParameter(bundleLoader, "bundleLoader");
        Systrace.beginSection(0L, "ReactInstance.loadJSBundle");
        bundleLoader.loadScript(new JSBundleLoaderDelegate() { // from class: com.facebook.react.runtime.ReactInstance.loadJSBundle.1
            @Override // com.facebook.react.bridge.JSBundleLoaderDelegate
            public void loadScriptFromFile(String fileName, String sourceURL, boolean loadSynchronously) {
                Intrinsics.checkNotNullParameter(fileName, "fileName");
                Intrinsics.checkNotNullParameter(sourceURL, "sourceURL");
                ReactInstance.this.context.setSourceURL(sourceURL);
                ReactInstance.this.loadJSBundleFromFile(fileName, sourceURL);
            }

            @Override // com.facebook.react.bridge.JSBundleLoaderDelegate
            public void loadSplitBundleFromFile(String fileName, String sourceURL) {
                Intrinsics.checkNotNullParameter(fileName, "fileName");
                Intrinsics.checkNotNullParameter(sourceURL, "sourceURL");
                ReactInstance.this.loadJSBundleFromFile(fileName, sourceURL);
            }

            @Override // com.facebook.react.bridge.JSBundleLoaderDelegate
            public void loadScriptFromAssets(AssetManager assetManager, String assetURL, boolean loadSynchronously) {
                Intrinsics.checkNotNullParameter(assetManager, "assetManager");
                Intrinsics.checkNotNullParameter(assetURL, "assetURL");
                ReactInstance.this.context.setSourceURL(assetURL);
                ReactInstance.this.loadJSBundleFromAssets(assetManager, assetURL);
            }

            @Override // com.facebook.react.bridge.JSBundleLoaderDelegate
            public void setSourceURLs(String deviceURL, String remoteURL) {
                Intrinsics.checkNotNullParameter(deviceURL, "deviceURL");
                Intrinsics.checkNotNullParameter(remoteURL, "remoteURL");
                ReactInstance.this.context.setSourceURL(deviceURL);
            }
        });
        Systrace.endSection(0L);
    }

    public final <T extends NativeModule> boolean hasNativeModule(Class<T> nativeModuleInterface) {
        Intrinsics.checkNotNullParameter(nativeModuleInterface, "nativeModuleInterface");
        ReactModule reactModule = (ReactModule) nativeModuleInterface.getAnnotation(ReactModule.class);
        if (reactModule != null) {
            return this.turboModuleManager.hasModule(reactModule.name());
        }
        return false;
    }

    public final Collection<NativeModule> getNativeModules() {
        return this.turboModuleManager.getModules();
    }

    public final <T extends NativeModule> T getNativeModule(Class<T> nativeModuleInterface) {
        Intrinsics.checkNotNullParameter(nativeModuleInterface, "nativeModuleInterface");
        ReactModule reactModule = (ReactModule) nativeModuleInterface.getAnnotation(ReactModule.class);
        if (reactModule != null) {
            return (T) getNativeModule(reactModule.name());
        }
        return null;
    }

    public final <T extends NativeModule> T getNativeModule(String nativeModuleName) {
        T t;
        Intrinsics.checkNotNullParameter(nativeModuleName, "nativeModuleName");
        synchronized (this.turboModuleManager) {
            t = (T) this.turboModuleManager.getModule(nativeModuleName);
        }
        return t;
    }

    public final void prerenderSurface(ReactSurfaceImpl surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
        Systrace.beginSection(0L, "ReactInstance.prerenderSurface");
        FLog.d(TAG, "call prerenderSurface with surface: " + surface.getModuleName());
        this.fabricUIManager.startSurface(surface.getSurfaceHandler(), surface.getContext(), null);
        Systrace.endSection(0L);
    }

    public final void startSurface(ReactSurfaceImpl surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
        String str = TAG;
        FLog.d(str, "startSurface() is called with surface: " + surface.getSurfaceID());
        Systrace.beginSection(0L, "ReactInstance.startSurface");
        ReactSurfaceView view = surface.getView();
        if (view == null) {
            throw new IllegalStateException("Starting surface without a view is not supported, use prerenderSurface instead.".toString());
        }
        if (view.getId() != -1) {
            ReactSoftExceptionLogger.logSoftException(str, new IllegalViewOperationException("surfaceView's is NOT equal to View.NO_ID before calling startSurface."));
            view.setId(-1);
        }
        if (surface.isRunning()) {
            this.fabricUIManager.attachRootView(surface.getSurfaceHandler(), view);
        } else {
            this.fabricUIManager.startSurface(surface.getSurfaceHandler(), surface.getContext(), view);
        }
        Systrace.endSection(0L);
    }

    public final void stopSurface(ReactSurfaceImpl surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
        FLog.d(TAG, "stopSurface() is called with surface: " + surface.getSurfaceID());
        this.fabricUIManager.stopSurface(surface.getSurfaceHandler());
    }

    public final void destroy() {
        FLog.d(TAG, "ReactInstance.destroy() is called.");
        this.reactQueueConfiguration.destroy();
        this.turboModuleManager.invalidate();
        this.fabricUIManager.invalidate();
        this.javaTimerManager.onInstanceDestroy();
        this.mHybridData.resetNative();
        this.javaScriptContextHolder.clear();
    }

    public final void handleMemoryPressure(int level) {
        try {
            handleMemoryPressureJs(level);
        } catch (NullPointerException unused) {
            ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("Native method handleMemoryPressureJs is called earlier than librninstance.so got ready."));
        }
    }

    public final EventDispatcher getEventDispatcher() {
        EventDispatcher eventDispatcher = this.fabricUIManager.getEventDispatcher();
        Intrinsics.checkNotNullExpressionValue(eventDispatcher, "<get-eventDispatcher>(...)");
        return eventDispatcher;
    }

    public final void registerSegment(int segmentId, String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        registerSegmentNative(segmentId, path);
    }

    /* JADX INFO: compiled from: ReactInstance.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010$\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\r\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\f2\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0010H\u0016J\u0018\u0010\u0016\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\f2\u0006\u0010\u000e\u001a\u00020\u000bR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0011\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\f0\u0012X\u0082.¢\u0006\u0002\n\u0000R%\u0010\u0013\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\f0\u00128F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00108F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lcom/facebook/react/runtime/ReactInstance$BridgelessViewManagerResolver;", "Lcom/facebook/react/uimanager/ViewManagerResolver;", "reactPackages", "", "Lcom/facebook/react/ReactPackage;", "context", "Lcom/facebook/react/runtime/BridgelessReactContext;", "<init>", "(Ljava/util/List;Lcom/facebook/react/runtime/BridgelessReactContext;)V", "lazyViewManagerMap", "", "", "Lcom/facebook/react/uimanager/ViewManager;", "getViewManager", "viewManagerName", "getViewManagerNames", "", "_eagerViewManagerMap", "", "eagerViewManagerMap", "getEagerViewManagerMap", "()Ljava/util/Map;", "getLazyViewManager", "lazyViewManagerNames", "getLazyViewManagerNames", "()Ljava/util/Collection;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class BridgelessViewManagerResolver implements ViewManagerResolver {
        private Map<String, ? extends ViewManager<?, ?>> _eagerViewManagerMap;
        private final BridgelessReactContext context;
        private final Map<String, ViewManager<?, ?>> lazyViewManagerMap;
        private final List<ReactPackage> reactPackages;

        /* JADX WARN: Multi-variable type inference failed */
        public BridgelessViewManagerResolver(List<? extends ReactPackage> reactPackages, BridgelessReactContext context) {
            Intrinsics.checkNotNullParameter(reactPackages, "reactPackages");
            Intrinsics.checkNotNullParameter(context, "context");
            this.reactPackages = reactPackages;
            this.context = context;
            this.lazyViewManagerMap = new HashMap();
        }

        @Override // com.facebook.react.uimanager.ViewManagerResolver
        public ViewManager<?, ?> getViewManager(String viewManagerName) {
            Intrinsics.checkNotNullParameter(viewManagerName, "viewManagerName");
            ViewManager<?, ?> lazyViewManager = getLazyViewManager(viewManagerName);
            return lazyViewManager != null ? lazyViewManager : getEagerViewManagerMap().get(viewManagerName);
        }

        @Override // com.facebook.react.uimanager.ViewManagerResolver
        public Collection<String> getViewManagerNames() {
            HashSet hashSet = new HashSet();
            hashSet.addAll(getLazyViewManagerNames());
            hashSet.addAll(getEagerViewManagerMap().keySet());
            return hashSet;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final synchronized Map<String, ViewManager<?, ?>> getEagerViewManagerMap() {
            Map map = this._eagerViewManagerMap;
            Map map2 = map;
            if (map != null) {
                if (map == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("_eagerViewManagerMap");
                    map2 = null;
                }
                return map2;
            }
            HashMap map3 = new HashMap();
            for (ReactPackage reactPackage : this.reactPackages) {
                if (!(reactPackage instanceof ViewManagerOnDemandReactPackage)) {
                    for (ViewManager viewManager : reactPackage.createViewManagers(this.context)) {
                        map3.put(viewManager.getName(), viewManager);
                    }
                }
            }
            this._eagerViewManagerMap = map3;
            return map3;
        }

        public final synchronized ViewManager<?, ?> getLazyViewManager(String viewManagerName) {
            ViewManager<?, ?> viewManagerCreateViewManager;
            Intrinsics.checkNotNullParameter(viewManagerName, "viewManagerName");
            if (this.lazyViewManagerMap.containsKey(viewManagerName)) {
                return this.lazyViewManagerMap.get(viewManagerName);
            }
            for (ReactPackage reactPackage : this.reactPackages) {
                if ((reactPackage instanceof ViewManagerOnDemandReactPackage) && (viewManagerCreateViewManager = ((ViewManagerOnDemandReactPackage) reactPackage).createViewManager(this.context, viewManagerName)) != null) {
                    this.lazyViewManagerMap.put(viewManagerName, viewManagerCreateViewManager);
                    return viewManagerCreateViewManager;
                }
            }
            return null;
        }

        public final synchronized Collection<String> getLazyViewManagerNames() {
            HashSet hashSet;
            hashSet = new HashSet();
            for (ReactPackage reactPackage : this.reactPackages) {
                if (reactPackage instanceof ViewManagerOnDemandReactPackage) {
                    Collection<String> viewManagerNames = ((ViewManagerOnDemandReactPackage) reactPackage).getViewManagerNames(this.context);
                    if (viewManagerNames == null) {
                        RNLog.w(this.context, "The ReactPackage called: `" + reactPackage.getClass().getSimpleName() + "` is returning null for getViewManagerNames(). This is violating the signature of the method. That method should be updated to return an empty collection.");
                    } else {
                        hashSet.addAll(viewManagerNames);
                    }
                }
            }
            return hashSet;
        }
    }

    /* JADX INFO: compiled from: ReactInstance.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00072\u001c\u0010\b\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0006\b\u0000\u0012\u00020\u000b\u0012\u0006\b\u0000\u0012\u00020\u000b0\n0\t2\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007H\u0002J,\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\n2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/facebook/react/runtime/ReactInstance$Companion;", "", "<init>", "()V", "TAG", "", "createConstants", "", "viewManagers", "", "Lcom/facebook/react/uimanager/ViewManager;", "", "customDirectEvents", "getConstantsForViewManager", "Lcom/facebook/react/bridge/NativeMap;", "viewManager", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Map<String, Object> createConstants(List<? extends ViewManager> viewManagers, Map<String, Object> customDirectEvents) {
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_START);
            SystraceMessage.beginSection(0L, "CreateUIManagerConstants").arg("Lazy", (Object) false).flush();
            try {
                return UIManagerModuleConstantsHelper.internal_createConstants(viewManagers, null, customDirectEvents);
            } finally {
                Systrace.endSection(0L);
                ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_END);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final NativeMap getConstantsForViewManager(ViewManager<?, ?> viewManager, Map<String, Object> customDirectEvents) {
            SystraceMessage.Builder builderBeginSection = SystraceMessage.beginSection(0L, "ReactInstance.getConstantsForViewManager");
            String name = viewManager.getName();
            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
            builderBeginSection.arg("ViewManager", name).arg("Lazy", (Object) true).flush();
            try {
                return Arguments.makeNativeMap((Map<String, ? extends Object>) UIManagerModuleConstantsHelper.internal_createConstantsForViewManager(viewManager, null, null, null, customDirectEvents));
            } finally {
                SystraceMessage.endSection(0L).flush();
            }
        }
    }

    static {
        Intrinsics.checkNotNullExpressionValue("ReactInstance", "getSimpleName(...)");
        TAG = "ReactInstance";
        SoLoader.loadLibrary("rninstance");
    }
}
