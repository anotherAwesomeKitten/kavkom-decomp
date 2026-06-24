package com.facebook.react.internal.featureflags;

import kotlin.Metadata;

/* JADX INFO: compiled from: ReactNativeFeatureFlagsProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b>\n\u0002\u0010\u0006\n\u0002\b\u0017\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H'J\b\u0010\u0004\u001a\u00020\u0003H'J\b\u0010\u0005\u001a\u00020\u0003H'J\b\u0010\u0006\u001a\u00020\u0003H'J\b\u0010\u0007\u001a\u00020\u0003H'J\b\u0010\b\u001a\u00020\u0003H'J\b\u0010\t\u001a\u00020\u0003H'J\b\u0010\n\u001a\u00020\u0003H'J\b\u0010\u000b\u001a\u00020\u0003H'J\b\u0010\f\u001a\u00020\u0003H'J\b\u0010\r\u001a\u00020\u0003H'J\b\u0010\u000e\u001a\u00020\u0003H'J\b\u0010\u000f\u001a\u00020\u0003H'J\b\u0010\u0010\u001a\u00020\u0003H'J\b\u0010\u0011\u001a\u00020\u0003H'J\b\u0010\u0012\u001a\u00020\u0003H'J\b\u0010\u0013\u001a\u00020\u0003H'J\b\u0010\u0014\u001a\u00020\u0003H'J\b\u0010\u0015\u001a\u00020\u0003H'J\b\u0010\u0016\u001a\u00020\u0003H'J\b\u0010\u0017\u001a\u00020\u0003H'J\b\u0010\u0018\u001a\u00020\u0003H'J\b\u0010\u0019\u001a\u00020\u0003H'J\b\u0010\u001a\u001a\u00020\u0003H'J\b\u0010\u001b\u001a\u00020\u0003H'J\b\u0010\u001c\u001a\u00020\u0003H'J\b\u0010\u001d\u001a\u00020\u0003H'J\b\u0010\u001e\u001a\u00020\u0003H'J\b\u0010\u001f\u001a\u00020\u0003H'J\b\u0010 \u001a\u00020\u0003H'J\b\u0010!\u001a\u00020\u0003H'J\b\u0010\"\u001a\u00020\u0003H'J\b\u0010#\u001a\u00020\u0003H'J\b\u0010$\u001a\u00020\u0003H'J\b\u0010%\u001a\u00020\u0003H'J\b\u0010&\u001a\u00020\u0003H'J\b\u0010'\u001a\u00020\u0003H'J\b\u0010(\u001a\u00020\u0003H'J\b\u0010)\u001a\u00020\u0003H'J\b\u0010*\u001a\u00020\u0003H'J\b\u0010+\u001a\u00020\u0003H'J\b\u0010,\u001a\u00020\u0003H'J\b\u0010-\u001a\u00020\u0003H'J\b\u0010.\u001a\u00020\u0003H'J\b\u0010/\u001a\u00020\u0003H'J\b\u00100\u001a\u00020\u0003H'J\b\u00101\u001a\u00020\u0003H'J\b\u00102\u001a\u00020\u0003H'J\b\u00103\u001a\u00020\u0003H'J\b\u00104\u001a\u00020\u0003H'J\b\u00105\u001a\u00020\u0003H'J\b\u00106\u001a\u00020\u0003H'J\b\u00107\u001a\u00020\u0003H'J\b\u00108\u001a\u00020\u0003H'J\b\u00109\u001a\u00020\u0003H'J\b\u0010:\u001a\u00020\u0003H'J\b\u0010;\u001a\u00020\u0003H'J\b\u0010<\u001a\u00020\u0003H'J\b\u0010=\u001a\u00020\u0003H'J\b\u0010>\u001a\u00020\u0003H'J\b\u0010?\u001a\u00020\u0003H'J\b\u0010@\u001a\u00020\u0003H'J\b\u0010A\u001a\u00020BH'J\b\u0010C\u001a\u00020\u0003H'J\b\u0010D\u001a\u00020\u0003H'J\b\u0010E\u001a\u00020\u0003H'J\b\u0010F\u001a\u00020\u0003H'J\b\u0010G\u001a\u00020\u0003H'J\b\u0010H\u001a\u00020\u0003H'J\b\u0010I\u001a\u00020\u0003H'J\b\u0010J\u001a\u00020\u0003H'J\b\u0010K\u001a\u00020\u0003H'J\b\u0010L\u001a\u00020\u0003H'J\b\u0010M\u001a\u00020\u0003H'J\b\u0010N\u001a\u00020\u0003H'J\b\u0010O\u001a\u00020\u0003H'J\b\u0010P\u001a\u00020\u0003H'J\b\u0010Q\u001a\u00020\u0003H'J\b\u0010R\u001a\u00020\u0003H'J\b\u0010S\u001a\u00020\u0003H'J\b\u0010T\u001a\u00020\u0003H'J\b\u0010U\u001a\u00020\u0003H'J\b\u0010V\u001a\u00020BH'J\b\u0010W\u001a\u00020BH'J\b\u0010X\u001a\u00020BH'ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006YÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlagsProvider;", "", "commonTestFlag", "", "cdpInteractionMetricsEnabled", "cxxNativeAnimatedEnabled", "cxxNativeAnimatedRemoveJsSync", "disableEarlyViewCommandExecution", "disableFabricCommitInCXXAnimated", "disableMountItemReorderingAndroid", "disableOldAndroidAttachmentMetricsWorkarounds", "disableTextLayoutManagerCacheAndroid", "enableAccessibilityOrder", "enableAccumulatedUpdatesInRawPropsAndroid", "enableAndroidLinearText", "enableAndroidTextMeasurementOptimizations", "enableBridgelessArchitecture", "enableCppPropsIteratorSetter", "enableCustomFocusSearchOnClippedElementsAndroid", "enableDestroyShadowTreeRevisionAsync", "enableDoubleMeasurementFixAndroid", "enableEagerMainQueueModulesOnIOS", "enableEagerRootViewAttachment", "enableFabricLogs", "enableFabricRenderer", "enableFontScaleChangesUpdatingLayout", "enableIOSTextBaselineOffsetPerLine", "enableIOSViewClipToPaddingBox", "enableImagePrefetchingAndroid", "enableImagePrefetchingOnUiThreadAndroid", "enableImmediateUpdateModeForContentOffsetChanges", "enableImperativeFocus", "enableInteropViewManagerClassLookUpOptimizationIOS", "enableIntersectionObserverByDefault", "enableKeyEvents", "enableLayoutAnimationsOnAndroid", "enableLayoutAnimationsOnIOS", "enableMainQueueCoordinatorOnIOS", "enableModuleArgumentNSNullConversionIOS", "enableNativeCSSParsing", "enableNetworkEventReporting", "enablePreparedTextLayout", "enablePropsUpdateReconciliationAndroid", "enableResourceTimingAPI", "enableSwiftUIBasedFilters", "enableViewCulling", "enableViewRecycling", "enableViewRecyclingForImage", "enableViewRecyclingForScrollView", "enableViewRecyclingForText", "enableViewRecyclingForView", "enableVirtualViewClippingWithoutScrollViewClipping", "enableVirtualViewContainerStateExperimental", "enableVirtualViewDebugFeatures", "enableVirtualViewRenderState", "enableVirtualViewWindowFocusDetection", "enableWebPerformanceAPIsByDefault", "fixMappingOfEventPrioritiesBetweenFabricAndReact", "fuseboxAssertSingleHostState", "fuseboxEnabledRelease", "fuseboxNetworkInspectionEnabled", "hideOffscreenVirtualViewsOnIOS", "overrideBySynchronousMountPropsAtMountingAndroid", "perfIssuesEnabled", "perfMonitorV2Enabled", "preparedTextCacheSize", "", "preventShadowTreeCommitExhaustion", "shouldPressibilityUseW3CPointerEventsForHover", "shouldTriggerResponderTransferOnScrollAndroid", "skipActivityIdentityAssertionOnHostPause", "sweepActiveTouchOnChildNativeGesturesAndroid", "traceTurboModulePromiseRejectionsOnAndroid", "updateRuntimeShadowNodeReferencesOnCommit", "useAlwaysAvailableJSErrorHandling", "useFabricInterop", "useNativeEqualsInNativeReadableArrayAndroid", "useNativeTransformHelperAndroid", "useNativeViewConfigsInBridgelessMode", "useOptimizedEventBatchingOnAndroid", "useRawPropsJsiValue", "useShadowNodeStateOnClone", "useSharedAnimatedBackend", "useTraitHiddenOnAndroid", "useTurboModuleInterop", "useTurboModules", "viewCullingOutsetRatio", "virtualViewHysteresisRatio", "virtualViewPrerenderRatio", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface ReactNativeFeatureFlagsProvider {
    boolean cdpInteractionMetricsEnabled();

    boolean commonTestFlag();

    boolean cxxNativeAnimatedEnabled();

    boolean cxxNativeAnimatedRemoveJsSync();

    boolean disableEarlyViewCommandExecution();

    boolean disableFabricCommitInCXXAnimated();

    boolean disableMountItemReorderingAndroid();

    boolean disableOldAndroidAttachmentMetricsWorkarounds();

    boolean disableTextLayoutManagerCacheAndroid();

    boolean enableAccessibilityOrder();

    boolean enableAccumulatedUpdatesInRawPropsAndroid();

    boolean enableAndroidLinearText();

    boolean enableAndroidTextMeasurementOptimizations();

    boolean enableBridgelessArchitecture();

    boolean enableCppPropsIteratorSetter();

    boolean enableCustomFocusSearchOnClippedElementsAndroid();

    boolean enableDestroyShadowTreeRevisionAsync();

    boolean enableDoubleMeasurementFixAndroid();

    boolean enableEagerMainQueueModulesOnIOS();

    boolean enableEagerRootViewAttachment();

    boolean enableFabricLogs();

    boolean enableFabricRenderer();

    boolean enableFontScaleChangesUpdatingLayout();

    boolean enableIOSTextBaselineOffsetPerLine();

    boolean enableIOSViewClipToPaddingBox();

    boolean enableImagePrefetchingAndroid();

    boolean enableImagePrefetchingOnUiThreadAndroid();

    boolean enableImmediateUpdateModeForContentOffsetChanges();

    boolean enableImperativeFocus();

    boolean enableInteropViewManagerClassLookUpOptimizationIOS();

    boolean enableIntersectionObserverByDefault();

    boolean enableKeyEvents();

    boolean enableLayoutAnimationsOnAndroid();

    boolean enableLayoutAnimationsOnIOS();

    boolean enableMainQueueCoordinatorOnIOS();

    boolean enableModuleArgumentNSNullConversionIOS();

    boolean enableNativeCSSParsing();

    boolean enableNetworkEventReporting();

    boolean enablePreparedTextLayout();

    boolean enablePropsUpdateReconciliationAndroid();

    boolean enableResourceTimingAPI();

    boolean enableSwiftUIBasedFilters();

    boolean enableViewCulling();

    boolean enableViewRecycling();

    boolean enableViewRecyclingForImage();

    boolean enableViewRecyclingForScrollView();

    boolean enableViewRecyclingForText();

    boolean enableViewRecyclingForView();

    boolean enableVirtualViewClippingWithoutScrollViewClipping();

    boolean enableVirtualViewContainerStateExperimental();

    boolean enableVirtualViewDebugFeatures();

    boolean enableVirtualViewRenderState();

    boolean enableVirtualViewWindowFocusDetection();

    boolean enableWebPerformanceAPIsByDefault();

    boolean fixMappingOfEventPrioritiesBetweenFabricAndReact();

    boolean fuseboxAssertSingleHostState();

    boolean fuseboxEnabledRelease();

    boolean fuseboxNetworkInspectionEnabled();

    boolean hideOffscreenVirtualViewsOnIOS();

    boolean overrideBySynchronousMountPropsAtMountingAndroid();

    boolean perfIssuesEnabled();

    boolean perfMonitorV2Enabled();

    double preparedTextCacheSize();

    boolean preventShadowTreeCommitExhaustion();

    boolean shouldPressibilityUseW3CPointerEventsForHover();

    boolean shouldTriggerResponderTransferOnScrollAndroid();

    boolean skipActivityIdentityAssertionOnHostPause();

    boolean sweepActiveTouchOnChildNativeGesturesAndroid();

    boolean traceTurboModulePromiseRejectionsOnAndroid();

    boolean updateRuntimeShadowNodeReferencesOnCommit();

    boolean useAlwaysAvailableJSErrorHandling();

    boolean useFabricInterop();

    boolean useNativeEqualsInNativeReadableArrayAndroid();

    boolean useNativeTransformHelperAndroid();

    boolean useNativeViewConfigsInBridgelessMode();

    boolean useOptimizedEventBatchingOnAndroid();

    boolean useRawPropsJsiValue();

    boolean useShadowNodeStateOnClone();

    boolean useSharedAnimatedBackend();

    boolean useTraitHiddenOnAndroid();

    boolean useTurboModuleInterop();

    boolean useTurboModules();

    double viewCullingOutsetRatio();

    double virtualViewHysteresisRatio();

    double virtualViewPrerenderRatio();
}
