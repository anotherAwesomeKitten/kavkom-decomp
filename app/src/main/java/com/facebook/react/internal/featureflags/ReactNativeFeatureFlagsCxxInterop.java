package com.facebook.react.internal.featureflags;

import com.facebook.soloader.SoLoader;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX INFO: compiled from: ReactNativeFeatureFlagsCxxInterop.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b>\n\u0002\u0010\u0006\n\u0002\b\u0017\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\t\u0010\u0004\u001a\u00020\u0005H\u0087 J\t\u0010\u0006\u001a\u00020\u0005H\u0087 J\t\u0010\u0007\u001a\u00020\u0005H\u0087 J\t\u0010\b\u001a\u00020\u0005H\u0087 J\t\u0010\t\u001a\u00020\u0005H\u0087 J\t\u0010\n\u001a\u00020\u0005H\u0087 J\t\u0010\u000b\u001a\u00020\u0005H\u0087 J\t\u0010\f\u001a\u00020\u0005H\u0087 J\t\u0010\r\u001a\u00020\u0005H\u0087 J\t\u0010\u000e\u001a\u00020\u0005H\u0087 J\t\u0010\u000f\u001a\u00020\u0005H\u0087 J\t\u0010\u0010\u001a\u00020\u0005H\u0087 J\t\u0010\u0011\u001a\u00020\u0005H\u0087 J\t\u0010\u0012\u001a\u00020\u0005H\u0087 J\t\u0010\u0013\u001a\u00020\u0005H\u0087 J\t\u0010\u0014\u001a\u00020\u0005H\u0087 J\t\u0010\u0015\u001a\u00020\u0005H\u0087 J\t\u0010\u0016\u001a\u00020\u0005H\u0087 J\t\u0010\u0017\u001a\u00020\u0005H\u0087 J\t\u0010\u0018\u001a\u00020\u0005H\u0087 J\t\u0010\u0019\u001a\u00020\u0005H\u0087 J\t\u0010\u001a\u001a\u00020\u0005H\u0087 J\t\u0010\u001b\u001a\u00020\u0005H\u0087 J\t\u0010\u001c\u001a\u00020\u0005H\u0087 J\t\u0010\u001d\u001a\u00020\u0005H\u0087 J\t\u0010\u001e\u001a\u00020\u0005H\u0087 J\t\u0010\u001f\u001a\u00020\u0005H\u0087 J\t\u0010 \u001a\u00020\u0005H\u0087 J\t\u0010!\u001a\u00020\u0005H\u0087 J\t\u0010\"\u001a\u00020\u0005H\u0087 J\t\u0010#\u001a\u00020\u0005H\u0087 J\t\u0010$\u001a\u00020\u0005H\u0087 J\t\u0010%\u001a\u00020\u0005H\u0087 J\t\u0010&\u001a\u00020\u0005H\u0087 J\t\u0010'\u001a\u00020\u0005H\u0087 J\t\u0010(\u001a\u00020\u0005H\u0087 J\t\u0010)\u001a\u00020\u0005H\u0087 J\t\u0010*\u001a\u00020\u0005H\u0087 J\t\u0010+\u001a\u00020\u0005H\u0087 J\t\u0010,\u001a\u00020\u0005H\u0087 J\t\u0010-\u001a\u00020\u0005H\u0087 J\t\u0010.\u001a\u00020\u0005H\u0087 J\t\u0010/\u001a\u00020\u0005H\u0087 J\t\u00100\u001a\u00020\u0005H\u0087 J\t\u00101\u001a\u00020\u0005H\u0087 J\t\u00102\u001a\u00020\u0005H\u0087 J\t\u00103\u001a\u00020\u0005H\u0087 J\t\u00104\u001a\u00020\u0005H\u0087 J\t\u00105\u001a\u00020\u0005H\u0087 J\t\u00106\u001a\u00020\u0005H\u0087 J\t\u00107\u001a\u00020\u0005H\u0087 J\t\u00108\u001a\u00020\u0005H\u0087 J\t\u00109\u001a\u00020\u0005H\u0087 J\t\u0010:\u001a\u00020\u0005H\u0087 J\t\u0010;\u001a\u00020\u0005H\u0087 J\t\u0010<\u001a\u00020\u0005H\u0087 J\t\u0010=\u001a\u00020\u0005H\u0087 J\t\u0010>\u001a\u00020\u0005H\u0087 J\t\u0010?\u001a\u00020\u0005H\u0087 J\t\u0010@\u001a\u00020\u0005H\u0087 J\t\u0010A\u001a\u00020\u0005H\u0087 J\t\u0010B\u001a\u00020\u0005H\u0087 J\t\u0010C\u001a\u00020DH\u0087 J\t\u0010E\u001a\u00020\u0005H\u0087 J\t\u0010F\u001a\u00020\u0005H\u0087 J\t\u0010G\u001a\u00020\u0005H\u0087 J\t\u0010H\u001a\u00020\u0005H\u0087 J\t\u0010I\u001a\u00020\u0005H\u0087 J\t\u0010J\u001a\u00020\u0005H\u0087 J\t\u0010K\u001a\u00020\u0005H\u0087 J\t\u0010L\u001a\u00020\u0005H\u0087 J\t\u0010M\u001a\u00020\u0005H\u0087 J\t\u0010N\u001a\u00020\u0005H\u0087 J\t\u0010O\u001a\u00020\u0005H\u0087 J\t\u0010P\u001a\u00020\u0005H\u0087 J\t\u0010Q\u001a\u00020\u0005H\u0087 J\t\u0010R\u001a\u00020\u0005H\u0087 J\t\u0010S\u001a\u00020\u0005H\u0087 J\t\u0010T\u001a\u00020\u0005H\u0087 J\t\u0010U\u001a\u00020\u0005H\u0087 J\t\u0010V\u001a\u00020\u0005H\u0087 J\t\u0010W\u001a\u00020\u0005H\u0087 J\t\u0010X\u001a\u00020DH\u0087 J\t\u0010Y\u001a\u00020DH\u0087 J\t\u0010Z\u001a\u00020DH\u0087 J\u0011\u0010[\u001a\u00020\\2\u0006\u0010]\u001a\u00020\u0001H\u0087 J\t\u0010^\u001a\u00020\\H\u0087 J\u0013\u0010_\u001a\u0004\u0018\u00010`2\u0006\u0010]\u001a\u00020\u0001H\u0087 ¨\u0006a"}, d2 = {"Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlagsCxxInterop;", "", "<init>", "()V", "commonTestFlag", "", "cdpInteractionMetricsEnabled", "cxxNativeAnimatedEnabled", "cxxNativeAnimatedRemoveJsSync", "disableEarlyViewCommandExecution", "disableFabricCommitInCXXAnimated", "disableMountItemReorderingAndroid", "disableOldAndroidAttachmentMetricsWorkarounds", "disableTextLayoutManagerCacheAndroid", "enableAccessibilityOrder", "enableAccumulatedUpdatesInRawPropsAndroid", "enableAndroidLinearText", "enableAndroidTextMeasurementOptimizations", "enableBridgelessArchitecture", "enableCppPropsIteratorSetter", "enableCustomFocusSearchOnClippedElementsAndroid", "enableDestroyShadowTreeRevisionAsync", "enableDoubleMeasurementFixAndroid", "enableEagerMainQueueModulesOnIOS", "enableEagerRootViewAttachment", "enableFabricLogs", "enableFabricRenderer", "enableFontScaleChangesUpdatingLayout", "enableIOSTextBaselineOffsetPerLine", "enableIOSViewClipToPaddingBox", "enableImagePrefetchingAndroid", "enableImagePrefetchingOnUiThreadAndroid", "enableImmediateUpdateModeForContentOffsetChanges", "enableImperativeFocus", "enableInteropViewManagerClassLookUpOptimizationIOS", "enableIntersectionObserverByDefault", "enableKeyEvents", "enableLayoutAnimationsOnAndroid", "enableLayoutAnimationsOnIOS", "enableMainQueueCoordinatorOnIOS", "enableModuleArgumentNSNullConversionIOS", "enableNativeCSSParsing", "enableNetworkEventReporting", "enablePreparedTextLayout", "enablePropsUpdateReconciliationAndroid", "enableResourceTimingAPI", "enableSwiftUIBasedFilters", "enableViewCulling", "enableViewRecycling", "enableViewRecyclingForImage", "enableViewRecyclingForScrollView", "enableViewRecyclingForText", "enableViewRecyclingForView", "enableVirtualViewClippingWithoutScrollViewClipping", "enableVirtualViewContainerStateExperimental", "enableVirtualViewDebugFeatures", "enableVirtualViewRenderState", "enableVirtualViewWindowFocusDetection", "enableWebPerformanceAPIsByDefault", "fixMappingOfEventPrioritiesBetweenFabricAndReact", "fuseboxAssertSingleHostState", "fuseboxEnabledRelease", "fuseboxNetworkInspectionEnabled", "hideOffscreenVirtualViewsOnIOS", "overrideBySynchronousMountPropsAtMountingAndroid", "perfIssuesEnabled", "perfMonitorV2Enabled", "preparedTextCacheSize", "", "preventShadowTreeCommitExhaustion", "shouldPressibilityUseW3CPointerEventsForHover", "shouldTriggerResponderTransferOnScrollAndroid", "skipActivityIdentityAssertionOnHostPause", "sweepActiveTouchOnChildNativeGesturesAndroid", "traceTurboModulePromiseRejectionsOnAndroid", "updateRuntimeShadowNodeReferencesOnCommit", "useAlwaysAvailableJSErrorHandling", "useFabricInterop", "useNativeEqualsInNativeReadableArrayAndroid", "useNativeTransformHelperAndroid", "useNativeViewConfigsInBridgelessMode", "useOptimizedEventBatchingOnAndroid", "useRawPropsJsiValue", "useShadowNodeStateOnClone", "useSharedAnimatedBackend", "useTraitHiddenOnAndroid", "useTurboModuleInterop", "useTurboModules", "viewCullingOutsetRatio", "virtualViewHysteresisRatio", "virtualViewPrerenderRatio", "override", "", "provider", "dangerouslyReset", "dangerouslyForceOverride", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ReactNativeFeatureFlagsCxxInterop {
    public static final ReactNativeFeatureFlagsCxxInterop INSTANCE = new ReactNativeFeatureFlagsCxxInterop();

    @JvmStatic
    public static final native boolean cdpInteractionMetricsEnabled();

    @JvmStatic
    public static final native boolean commonTestFlag();

    @JvmStatic
    public static final native boolean cxxNativeAnimatedEnabled();

    @JvmStatic
    public static final native boolean cxxNativeAnimatedRemoveJsSync();

    @JvmStatic
    public static final native String dangerouslyForceOverride(Object provider);

    @JvmStatic
    public static final native void dangerouslyReset();

    @JvmStatic
    public static final native boolean disableEarlyViewCommandExecution();

    @JvmStatic
    public static final native boolean disableFabricCommitInCXXAnimated();

    @JvmStatic
    public static final native boolean disableMountItemReorderingAndroid();

    @JvmStatic
    public static final native boolean disableOldAndroidAttachmentMetricsWorkarounds();

    @JvmStatic
    public static final native boolean disableTextLayoutManagerCacheAndroid();

    @JvmStatic
    public static final native boolean enableAccessibilityOrder();

    @JvmStatic
    public static final native boolean enableAccumulatedUpdatesInRawPropsAndroid();

    @JvmStatic
    public static final native boolean enableAndroidLinearText();

    @JvmStatic
    public static final native boolean enableAndroidTextMeasurementOptimizations();

    @JvmStatic
    public static final native boolean enableBridgelessArchitecture();

    @JvmStatic
    public static final native boolean enableCppPropsIteratorSetter();

    @JvmStatic
    public static final native boolean enableCustomFocusSearchOnClippedElementsAndroid();

    @JvmStatic
    public static final native boolean enableDestroyShadowTreeRevisionAsync();

    @JvmStatic
    public static final native boolean enableDoubleMeasurementFixAndroid();

    @JvmStatic
    public static final native boolean enableEagerMainQueueModulesOnIOS();

    @JvmStatic
    public static final native boolean enableEagerRootViewAttachment();

    @JvmStatic
    public static final native boolean enableFabricLogs();

    @JvmStatic
    public static final native boolean enableFabricRenderer();

    @JvmStatic
    public static final native boolean enableFontScaleChangesUpdatingLayout();

    @JvmStatic
    public static final native boolean enableIOSTextBaselineOffsetPerLine();

    @JvmStatic
    public static final native boolean enableIOSViewClipToPaddingBox();

    @JvmStatic
    public static final native boolean enableImagePrefetchingAndroid();

    @JvmStatic
    public static final native boolean enableImagePrefetchingOnUiThreadAndroid();

    @JvmStatic
    public static final native boolean enableImmediateUpdateModeForContentOffsetChanges();

    @JvmStatic
    public static final native boolean enableImperativeFocus();

    @JvmStatic
    public static final native boolean enableInteropViewManagerClassLookUpOptimizationIOS();

    @JvmStatic
    public static final native boolean enableIntersectionObserverByDefault();

    @JvmStatic
    public static final native boolean enableKeyEvents();

    @JvmStatic
    public static final native boolean enableLayoutAnimationsOnAndroid();

    @JvmStatic
    public static final native boolean enableLayoutAnimationsOnIOS();

    @JvmStatic
    public static final native boolean enableMainQueueCoordinatorOnIOS();

    @JvmStatic
    public static final native boolean enableModuleArgumentNSNullConversionIOS();

    @JvmStatic
    public static final native boolean enableNativeCSSParsing();

    @JvmStatic
    public static final native boolean enableNetworkEventReporting();

    @JvmStatic
    public static final native boolean enablePreparedTextLayout();

    @JvmStatic
    public static final native boolean enablePropsUpdateReconciliationAndroid();

    @JvmStatic
    public static final native boolean enableResourceTimingAPI();

    @JvmStatic
    public static final native boolean enableSwiftUIBasedFilters();

    @JvmStatic
    public static final native boolean enableViewCulling();

    @JvmStatic
    public static final native boolean enableViewRecycling();

    @JvmStatic
    public static final native boolean enableViewRecyclingForImage();

    @JvmStatic
    public static final native boolean enableViewRecyclingForScrollView();

    @JvmStatic
    public static final native boolean enableViewRecyclingForText();

    @JvmStatic
    public static final native boolean enableViewRecyclingForView();

    @JvmStatic
    public static final native boolean enableVirtualViewClippingWithoutScrollViewClipping();

    @JvmStatic
    public static final native boolean enableVirtualViewContainerStateExperimental();

    @JvmStatic
    public static final native boolean enableVirtualViewDebugFeatures();

    @JvmStatic
    public static final native boolean enableVirtualViewRenderState();

    @JvmStatic
    public static final native boolean enableVirtualViewWindowFocusDetection();

    @JvmStatic
    public static final native boolean enableWebPerformanceAPIsByDefault();

    @JvmStatic
    public static final native boolean fixMappingOfEventPrioritiesBetweenFabricAndReact();

    @JvmStatic
    public static final native boolean fuseboxAssertSingleHostState();

    @JvmStatic
    public static final native boolean fuseboxEnabledRelease();

    @JvmStatic
    public static final native boolean fuseboxNetworkInspectionEnabled();

    @JvmStatic
    public static final native boolean hideOffscreenVirtualViewsOnIOS();

    @JvmStatic
    public static final native void override(Object provider);

    @JvmStatic
    public static final native boolean overrideBySynchronousMountPropsAtMountingAndroid();

    @JvmStatic
    public static final native boolean perfIssuesEnabled();

    @JvmStatic
    public static final native boolean perfMonitorV2Enabled();

    @JvmStatic
    public static final native double preparedTextCacheSize();

    @JvmStatic
    public static final native boolean preventShadowTreeCommitExhaustion();

    @JvmStatic
    public static final native boolean shouldPressibilityUseW3CPointerEventsForHover();

    @JvmStatic
    public static final native boolean shouldTriggerResponderTransferOnScrollAndroid();

    @JvmStatic
    public static final native boolean skipActivityIdentityAssertionOnHostPause();

    @JvmStatic
    public static final native boolean sweepActiveTouchOnChildNativeGesturesAndroid();

    @JvmStatic
    public static final native boolean traceTurboModulePromiseRejectionsOnAndroid();

    @JvmStatic
    public static final native boolean updateRuntimeShadowNodeReferencesOnCommit();

    @JvmStatic
    public static final native boolean useAlwaysAvailableJSErrorHandling();

    @JvmStatic
    public static final native boolean useFabricInterop();

    @JvmStatic
    public static final native boolean useNativeEqualsInNativeReadableArrayAndroid();

    @JvmStatic
    public static final native boolean useNativeTransformHelperAndroid();

    @JvmStatic
    public static final native boolean useNativeViewConfigsInBridgelessMode();

    @JvmStatic
    public static final native boolean useOptimizedEventBatchingOnAndroid();

    @JvmStatic
    public static final native boolean useRawPropsJsiValue();

    @JvmStatic
    public static final native boolean useShadowNodeStateOnClone();

    @JvmStatic
    public static final native boolean useSharedAnimatedBackend();

    @JvmStatic
    public static final native boolean useTraitHiddenOnAndroid();

    @JvmStatic
    public static final native boolean useTurboModuleInterop();

    @JvmStatic
    public static final native boolean useTurboModules();

    @JvmStatic
    public static final native double viewCullingOutsetRatio();

    @JvmStatic
    public static final native double virtualViewHysteresisRatio();

    @JvmStatic
    public static final native double virtualViewPrerenderRatio();

    private ReactNativeFeatureFlagsCxxInterop() {
    }

    static {
        SoLoader.loadLibrary("react_featureflagsjni");
    }
}
