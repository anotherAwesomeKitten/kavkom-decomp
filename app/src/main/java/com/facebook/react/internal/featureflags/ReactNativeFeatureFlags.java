package com.facebook.react.internal.featureflags;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactNativeFeatureFlags.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b>\n\u0002\u0010\u0006\n\u0002\b\u0017\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0007J\b\u0010\n\u001a\u00020\tH\u0007J\b\u0010\u000b\u001a\u00020\tH\u0007J\b\u0010\f\u001a\u00020\tH\u0007J\b\u0010\r\u001a\u00020\tH\u0007J\b\u0010\u000e\u001a\u00020\tH\u0007J\b\u0010\u000f\u001a\u00020\tH\u0007J\b\u0010\u0010\u001a\u00020\tH\u0007J\b\u0010\u0011\u001a\u00020\tH\u0007J\b\u0010\u0012\u001a\u00020\tH\u0007J\b\u0010\u0013\u001a\u00020\tH\u0007J\b\u0010\u0014\u001a\u00020\tH\u0007J\b\u0010\u0015\u001a\u00020\tH\u0007J\b\u0010\u0016\u001a\u00020\tH\u0007J\b\u0010\u0017\u001a\u00020\tH\u0007J\b\u0010\u0018\u001a\u00020\tH\u0007J\b\u0010\u0019\u001a\u00020\tH\u0007J\b\u0010\u001a\u001a\u00020\tH\u0007J\b\u0010\u001b\u001a\u00020\tH\u0007J\b\u0010\u001c\u001a\u00020\tH\u0007J\b\u0010\u001d\u001a\u00020\tH\u0007J\b\u0010\u001e\u001a\u00020\tH\u0007J\b\u0010\u001f\u001a\u00020\tH\u0007J\b\u0010 \u001a\u00020\tH\u0007J\b\u0010!\u001a\u00020\tH\u0007J\b\u0010\"\u001a\u00020\tH\u0007J\b\u0010#\u001a\u00020\tH\u0007J\b\u0010$\u001a\u00020\tH\u0007J\b\u0010%\u001a\u00020\tH\u0007J\b\u0010&\u001a\u00020\tH\u0007J\b\u0010'\u001a\u00020\tH\u0007J\b\u0010(\u001a\u00020\tH\u0007J\b\u0010)\u001a\u00020\tH\u0007J\b\u0010*\u001a\u00020\tH\u0007J\b\u0010+\u001a\u00020\tH\u0007J\b\u0010,\u001a\u00020\tH\u0007J\b\u0010-\u001a\u00020\tH\u0007J\b\u0010.\u001a\u00020\tH\u0007J\b\u0010/\u001a\u00020\tH\u0007J\b\u00100\u001a\u00020\tH\u0007J\b\u00101\u001a\u00020\tH\u0007J\b\u00102\u001a\u00020\tH\u0007J\b\u00103\u001a\u00020\tH\u0007J\b\u00104\u001a\u00020\tH\u0007J\b\u00105\u001a\u00020\tH\u0007J\b\u00106\u001a\u00020\tH\u0007J\b\u00107\u001a\u00020\tH\u0007J\b\u00108\u001a\u00020\tH\u0007J\b\u00109\u001a\u00020\tH\u0007J\b\u0010:\u001a\u00020\tH\u0007J\b\u0010;\u001a\u00020\tH\u0007J\b\u0010<\u001a\u00020\tH\u0007J\b\u0010=\u001a\u00020\tH\u0007J\b\u0010>\u001a\u00020\tH\u0007J\b\u0010?\u001a\u00020\tH\u0007J\b\u0010@\u001a\u00020\tH\u0007J\b\u0010A\u001a\u00020\tH\u0007J\b\u0010B\u001a\u00020\tH\u0007J\b\u0010C\u001a\u00020\tH\u0007J\b\u0010D\u001a\u00020\tH\u0007J\b\u0010E\u001a\u00020\tH\u0007J\b\u0010F\u001a\u00020\tH\u0007J\b\u0010G\u001a\u00020HH\u0007J\b\u0010I\u001a\u00020\tH\u0007J\b\u0010J\u001a\u00020\tH\u0007J\b\u0010K\u001a\u00020\tH\u0007J\b\u0010L\u001a\u00020\tH\u0007J\b\u0010M\u001a\u00020\tH\u0007J\b\u0010N\u001a\u00020\tH\u0007J\b\u0010O\u001a\u00020\tH\u0007J\b\u0010P\u001a\u00020\tH\u0007J\b\u0010Q\u001a\u00020\tH\u0007J\b\u0010R\u001a\u00020\tH\u0007J\b\u0010S\u001a\u00020\tH\u0007J\b\u0010T\u001a\u00020\tH\u0007J\b\u0010U\u001a\u00020\tH\u0007J\b\u0010V\u001a\u00020\tH\u0007J\b\u0010W\u001a\u00020\tH\u0007J\b\u0010X\u001a\u00020\tH\u0007J\b\u0010Y\u001a\u00020\tH\u0007J\b\u0010Z\u001a\u00020\tH\u0007J\b\u0010[\u001a\u00020\tH\u0007J\b\u0010\\\u001a\u00020HH\u0007J\b\u0010]\u001a\u00020HH\u0007J\b\u0010^\u001a\u00020HH\u0007J\u0010\u0010_\u001a\u00020`2\u0006\u0010a\u001a\u00020bH\u0007J\b\u0010c\u001a\u00020`H\u0007J\u0012\u0010d\u001a\u0004\u0018\u00010e2\u0006\u0010a\u001a\u00020bH\u0007J\u001b\u0010f\u001a\u00020`2\f\u0010g\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0001¢\u0006\u0002\bhR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006i"}, d2 = {"Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlags;", "", "<init>", "()V", "accessorProvider", "Lkotlin/Function0;", "Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlagsAccessor;", "accessor", "commonTestFlag", "", "cdpInteractionMetricsEnabled", "cxxNativeAnimatedEnabled", "cxxNativeAnimatedRemoveJsSync", "disableEarlyViewCommandExecution", "disableFabricCommitInCXXAnimated", "disableMountItemReorderingAndroid", "disableOldAndroidAttachmentMetricsWorkarounds", "disableTextLayoutManagerCacheAndroid", "enableAccessibilityOrder", "enableAccumulatedUpdatesInRawPropsAndroid", "enableAndroidLinearText", "enableAndroidTextMeasurementOptimizations", "enableBridgelessArchitecture", "enableCppPropsIteratorSetter", "enableCustomFocusSearchOnClippedElementsAndroid", "enableDestroyShadowTreeRevisionAsync", "enableDoubleMeasurementFixAndroid", "enableEagerMainQueueModulesOnIOS", "enableEagerRootViewAttachment", "enableFabricLogs", "enableFabricRenderer", "enableFontScaleChangesUpdatingLayout", "enableIOSTextBaselineOffsetPerLine", "enableIOSViewClipToPaddingBox", "enableImagePrefetchingAndroid", "enableImagePrefetchingOnUiThreadAndroid", "enableImmediateUpdateModeForContentOffsetChanges", "enableImperativeFocus", "enableInteropViewManagerClassLookUpOptimizationIOS", "enableIntersectionObserverByDefault", "enableKeyEvents", "enableLayoutAnimationsOnAndroid", "enableLayoutAnimationsOnIOS", "enableMainQueueCoordinatorOnIOS", "enableModuleArgumentNSNullConversionIOS", "enableNativeCSSParsing", "enableNetworkEventReporting", "enablePreparedTextLayout", "enablePropsUpdateReconciliationAndroid", "enableResourceTimingAPI", "enableSwiftUIBasedFilters", "enableViewCulling", "enableViewRecycling", "enableViewRecyclingForImage", "enableViewRecyclingForScrollView", "enableViewRecyclingForText", "enableViewRecyclingForView", "enableVirtualViewClippingWithoutScrollViewClipping", "enableVirtualViewContainerStateExperimental", "enableVirtualViewDebugFeatures", "enableVirtualViewRenderState", "enableVirtualViewWindowFocusDetection", "enableWebPerformanceAPIsByDefault", "fixMappingOfEventPrioritiesBetweenFabricAndReact", "fuseboxAssertSingleHostState", "fuseboxEnabledRelease", "fuseboxNetworkInspectionEnabled", "hideOffscreenVirtualViewsOnIOS", "overrideBySynchronousMountPropsAtMountingAndroid", "perfIssuesEnabled", "perfMonitorV2Enabled", "preparedTextCacheSize", "", "preventShadowTreeCommitExhaustion", "shouldPressibilityUseW3CPointerEventsForHover", "shouldTriggerResponderTransferOnScrollAndroid", "skipActivityIdentityAssertionOnHostPause", "sweepActiveTouchOnChildNativeGesturesAndroid", "traceTurboModulePromiseRejectionsOnAndroid", "updateRuntimeShadowNodeReferencesOnCommit", "useAlwaysAvailableJSErrorHandling", "useFabricInterop", "useNativeEqualsInNativeReadableArrayAndroid", "useNativeTransformHelperAndroid", "useNativeViewConfigsInBridgelessMode", "useOptimizedEventBatchingOnAndroid", "useRawPropsJsiValue", "useShadowNodeStateOnClone", "useSharedAnimatedBackend", "useTraitHiddenOnAndroid", "useTurboModuleInterop", "useTurboModules", "viewCullingOutsetRatio", "virtualViewHysteresisRatio", "virtualViewPrerenderRatio", "override", "", "provider", "Lcom/facebook/react/internal/featureflags/ReactNativeFeatureFlagsProvider;", "dangerouslyReset", "dangerouslyForceOverride", "", "setAccessorProvider", "newAccessorProvider", "setAccessorProvider$ReactAndroid_release", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ReactNativeFeatureFlags {
    public static final ReactNativeFeatureFlags INSTANCE = new ReactNativeFeatureFlags();
    private static ReactNativeFeatureFlagsAccessor accessor;
    private static Function0<? extends ReactNativeFeatureFlagsAccessor> accessorProvider;

    private ReactNativeFeatureFlags() {
    }

    static {
        Function0<? extends ReactNativeFeatureFlagsAccessor> function0 = new Function0() { // from class: com.facebook.react.internal.featureflags.ReactNativeFeatureFlags$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ReactNativeFeatureFlags.accessorProvider$lambda$0();
            }
        };
        accessorProvider = function0;
        accessor = function0.invoke();
    }

    public static final ReactNativeFeatureFlagsCxxAccessor accessorProvider$lambda$0() {
        return new ReactNativeFeatureFlagsCxxAccessor();
    }

    @JvmStatic
    public static final boolean commonTestFlag() {
        return accessor.commonTestFlag();
    }

    @JvmStatic
    public static final boolean cdpInteractionMetricsEnabled() {
        return accessor.cdpInteractionMetricsEnabled();
    }

    @JvmStatic
    public static final boolean cxxNativeAnimatedEnabled() {
        return accessor.cxxNativeAnimatedEnabled();
    }

    @JvmStatic
    public static final boolean cxxNativeAnimatedRemoveJsSync() {
        return accessor.cxxNativeAnimatedRemoveJsSync();
    }

    @JvmStatic
    public static final boolean disableEarlyViewCommandExecution() {
        return accessor.disableEarlyViewCommandExecution();
    }

    @JvmStatic
    public static final boolean disableFabricCommitInCXXAnimated() {
        return accessor.disableFabricCommitInCXXAnimated();
    }

    @JvmStatic
    public static final boolean disableMountItemReorderingAndroid() {
        return accessor.disableMountItemReorderingAndroid();
    }

    @JvmStatic
    public static final boolean disableOldAndroidAttachmentMetricsWorkarounds() {
        return accessor.disableOldAndroidAttachmentMetricsWorkarounds();
    }

    @JvmStatic
    public static final boolean disableTextLayoutManagerCacheAndroid() {
        return accessor.disableTextLayoutManagerCacheAndroid();
    }

    @JvmStatic
    public static final boolean enableAccessibilityOrder() {
        return accessor.enableAccessibilityOrder();
    }

    @JvmStatic
    public static final boolean enableAccumulatedUpdatesInRawPropsAndroid() {
        return accessor.enableAccumulatedUpdatesInRawPropsAndroid();
    }

    @JvmStatic
    public static final boolean enableAndroidLinearText() {
        return accessor.enableAndroidLinearText();
    }

    @JvmStatic
    public static final boolean enableAndroidTextMeasurementOptimizations() {
        return accessor.enableAndroidTextMeasurementOptimizations();
    }

    @JvmStatic
    public static final boolean enableBridgelessArchitecture() {
        return accessor.getNewArchitectureEnabled();
    }

    @JvmStatic
    public static final boolean enableCppPropsIteratorSetter() {
        return accessor.enableCppPropsIteratorSetter();
    }

    @JvmStatic
    public static final boolean enableCustomFocusSearchOnClippedElementsAndroid() {
        return accessor.enableCustomFocusSearchOnClippedElementsAndroid();
    }

    @JvmStatic
    public static final boolean enableDestroyShadowTreeRevisionAsync() {
        return accessor.enableDestroyShadowTreeRevisionAsync();
    }

    @JvmStatic
    public static final boolean enableDoubleMeasurementFixAndroid() {
        return accessor.enableDoubleMeasurementFixAndroid();
    }

    @JvmStatic
    public static final boolean enableEagerMainQueueModulesOnIOS() {
        return accessor.enableEagerMainQueueModulesOnIOS();
    }

    @JvmStatic
    public static final boolean enableEagerRootViewAttachment() {
        return accessor.enableEagerRootViewAttachment();
    }

    @JvmStatic
    public static final boolean enableFabricLogs() {
        return accessor.enableFabricLogs();
    }

    @JvmStatic
    public static final boolean enableFabricRenderer() {
        return accessor.enableFabricRenderer();
    }

    @JvmStatic
    public static final boolean enableFontScaleChangesUpdatingLayout() {
        return accessor.enableFontScaleChangesUpdatingLayout();
    }

    @JvmStatic
    public static final boolean enableIOSTextBaselineOffsetPerLine() {
        return accessor.enableIOSTextBaselineOffsetPerLine();
    }

    @JvmStatic
    public static final boolean enableIOSViewClipToPaddingBox() {
        return accessor.enableIOSViewClipToPaddingBox();
    }

    @JvmStatic
    public static final boolean enableImagePrefetchingAndroid() {
        return accessor.enableImagePrefetchingAndroid();
    }

    @JvmStatic
    public static final boolean enableImagePrefetchingOnUiThreadAndroid() {
        return accessor.enableImagePrefetchingOnUiThreadAndroid();
    }

    @JvmStatic
    public static final boolean enableImmediateUpdateModeForContentOffsetChanges() {
        return accessor.enableImmediateUpdateModeForContentOffsetChanges();
    }

    @JvmStatic
    public static final boolean enableImperativeFocus() {
        return accessor.enableImperativeFocus();
    }

    @JvmStatic
    public static final boolean enableInteropViewManagerClassLookUpOptimizationIOS() {
        return accessor.enableInteropViewManagerClassLookUpOptimizationIOS();
    }

    @JvmStatic
    public static final boolean enableIntersectionObserverByDefault() {
        return accessor.enableIntersectionObserverByDefault();
    }

    @JvmStatic
    public static final boolean enableKeyEvents() {
        return accessor.enableKeyEvents();
    }

    @JvmStatic
    public static final boolean enableLayoutAnimationsOnAndroid() {
        return accessor.enableLayoutAnimationsOnAndroid();
    }

    @JvmStatic
    public static final boolean enableLayoutAnimationsOnIOS() {
        return accessor.enableLayoutAnimationsOnIOS();
    }

    @JvmStatic
    public static final boolean enableMainQueueCoordinatorOnIOS() {
        return accessor.enableMainQueueCoordinatorOnIOS();
    }

    @JvmStatic
    public static final boolean enableModuleArgumentNSNullConversionIOS() {
        return accessor.enableModuleArgumentNSNullConversionIOS();
    }

    @JvmStatic
    public static final boolean enableNativeCSSParsing() {
        return accessor.enableNativeCSSParsing();
    }

    @JvmStatic
    public static final boolean enableNetworkEventReporting() {
        return accessor.enableNetworkEventReporting();
    }

    @JvmStatic
    public static final boolean enablePreparedTextLayout() {
        return accessor.enablePreparedTextLayout();
    }

    @JvmStatic
    public static final boolean enablePropsUpdateReconciliationAndroid() {
        return accessor.enablePropsUpdateReconciliationAndroid();
    }

    @JvmStatic
    public static final boolean enableResourceTimingAPI() {
        return accessor.enableResourceTimingAPI();
    }

    @JvmStatic
    public static final boolean enableSwiftUIBasedFilters() {
        return accessor.enableSwiftUIBasedFilters();
    }

    @JvmStatic
    public static final boolean enableViewCulling() {
        return accessor.enableViewCulling();
    }

    @JvmStatic
    public static final boolean enableViewRecycling() {
        return accessor.enableViewRecycling();
    }

    @JvmStatic
    public static final boolean enableViewRecyclingForImage() {
        return accessor.enableViewRecyclingForImage();
    }

    @JvmStatic
    public static final boolean enableViewRecyclingForScrollView() {
        return accessor.enableViewRecyclingForScrollView();
    }

    @JvmStatic
    public static final boolean enableViewRecyclingForText() {
        return accessor.enableViewRecyclingForText();
    }

    @JvmStatic
    public static final boolean enableViewRecyclingForView() {
        return accessor.enableViewRecyclingForView();
    }

    @JvmStatic
    public static final boolean enableVirtualViewClippingWithoutScrollViewClipping() {
        return accessor.enableVirtualViewClippingWithoutScrollViewClipping();
    }

    @JvmStatic
    public static final boolean enableVirtualViewContainerStateExperimental() {
        return accessor.enableVirtualViewContainerStateExperimental();
    }

    @JvmStatic
    public static final boolean enableVirtualViewDebugFeatures() {
        return accessor.enableVirtualViewDebugFeatures();
    }

    @JvmStatic
    public static final boolean enableVirtualViewRenderState() {
        return accessor.enableVirtualViewRenderState();
    }

    @JvmStatic
    public static final boolean enableVirtualViewWindowFocusDetection() {
        return accessor.enableVirtualViewWindowFocusDetection();
    }

    @JvmStatic
    public static final boolean enableWebPerformanceAPIsByDefault() {
        return accessor.enableWebPerformanceAPIsByDefault();
    }

    @JvmStatic
    public static final boolean fixMappingOfEventPrioritiesBetweenFabricAndReact() {
        return accessor.fixMappingOfEventPrioritiesBetweenFabricAndReact();
    }

    @JvmStatic
    public static final boolean fuseboxAssertSingleHostState() {
        return accessor.fuseboxAssertSingleHostState();
    }

    @JvmStatic
    public static final boolean fuseboxEnabledRelease() {
        return accessor.fuseboxEnabledRelease();
    }

    @JvmStatic
    public static final boolean fuseboxNetworkInspectionEnabled() {
        return accessor.fuseboxNetworkInspectionEnabled();
    }

    @JvmStatic
    public static final boolean hideOffscreenVirtualViewsOnIOS() {
        return accessor.hideOffscreenVirtualViewsOnIOS();
    }

    @JvmStatic
    public static final boolean overrideBySynchronousMountPropsAtMountingAndroid() {
        return accessor.overrideBySynchronousMountPropsAtMountingAndroid();
    }

    @JvmStatic
    public static final boolean perfIssuesEnabled() {
        return accessor.perfIssuesEnabled();
    }

    @JvmStatic
    public static final boolean perfMonitorV2Enabled() {
        return accessor.perfMonitorV2Enabled();
    }

    @JvmStatic
    public static final double preparedTextCacheSize() {
        return accessor.preparedTextCacheSize();
    }

    @JvmStatic
    public static final boolean preventShadowTreeCommitExhaustion() {
        return accessor.preventShadowTreeCommitExhaustion();
    }

    @JvmStatic
    public static final boolean shouldPressibilityUseW3CPointerEventsForHover() {
        return accessor.shouldPressibilityUseW3CPointerEventsForHover();
    }

    @JvmStatic
    public static final boolean shouldTriggerResponderTransferOnScrollAndroid() {
        return accessor.shouldTriggerResponderTransferOnScrollAndroid();
    }

    @JvmStatic
    public static final boolean skipActivityIdentityAssertionOnHostPause() {
        return accessor.skipActivityIdentityAssertionOnHostPause();
    }

    @JvmStatic
    public static final boolean sweepActiveTouchOnChildNativeGesturesAndroid() {
        return accessor.sweepActiveTouchOnChildNativeGesturesAndroid();
    }

    @JvmStatic
    public static final boolean traceTurboModulePromiseRejectionsOnAndroid() {
        return accessor.traceTurboModulePromiseRejectionsOnAndroid();
    }

    @JvmStatic
    public static final boolean updateRuntimeShadowNodeReferencesOnCommit() {
        return accessor.updateRuntimeShadowNodeReferencesOnCommit();
    }

    @JvmStatic
    public static final boolean useAlwaysAvailableJSErrorHandling() {
        return accessor.useAlwaysAvailableJSErrorHandling();
    }

    @JvmStatic
    public static final boolean useFabricInterop() {
        return accessor.useFabricInterop();
    }

    @JvmStatic
    public static final boolean useNativeEqualsInNativeReadableArrayAndroid() {
        return accessor.useNativeEqualsInNativeReadableArrayAndroid();
    }

    @JvmStatic
    public static final boolean useNativeTransformHelperAndroid() {
        return accessor.useNativeTransformHelperAndroid();
    }

    @JvmStatic
    public static final boolean useNativeViewConfigsInBridgelessMode() {
        return accessor.useNativeViewConfigsInBridgelessMode();
    }

    @JvmStatic
    public static final boolean useOptimizedEventBatchingOnAndroid() {
        return accessor.useOptimizedEventBatchingOnAndroid();
    }

    @JvmStatic
    public static final boolean useRawPropsJsiValue() {
        return accessor.useRawPropsJsiValue();
    }

    @JvmStatic
    public static final boolean useShadowNodeStateOnClone() {
        return accessor.useShadowNodeStateOnClone();
    }

    @JvmStatic
    public static final boolean useSharedAnimatedBackend() {
        return accessor.useSharedAnimatedBackend();
    }

    @JvmStatic
    public static final boolean useTraitHiddenOnAndroid() {
        return accessor.useTraitHiddenOnAndroid();
    }

    @JvmStatic
    public static final boolean useTurboModuleInterop() {
        return accessor.useTurboModuleInterop();
    }

    @JvmStatic
    public static final boolean useTurboModules() {
        return accessor.useTurboModules();
    }

    @JvmStatic
    public static final double viewCullingOutsetRatio() {
        return accessor.viewCullingOutsetRatio();
    }

    @JvmStatic
    public static final double virtualViewHysteresisRatio() {
        return accessor.virtualViewHysteresisRatio();
    }

    @JvmStatic
    public static final double virtualViewPrerenderRatio() {
        return accessor.virtualViewPrerenderRatio();
    }

    @JvmStatic
    public static final void override(ReactNativeFeatureFlagsProvider provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        accessor.override(provider);
    }

    @JvmStatic
    public static final void dangerouslyReset() {
        accessor.dangerouslyReset();
        accessor = accessorProvider.invoke();
    }

    @JvmStatic
    public static final String dangerouslyForceOverride(ReactNativeFeatureFlagsProvider provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        ReactNativeFeatureFlagsAccessor reactNativeFeatureFlagsAccessorInvoke = accessorProvider.invoke();
        String strDangerouslyForceOverride = reactNativeFeatureFlagsAccessorInvoke.dangerouslyForceOverride(provider);
        accessor = reactNativeFeatureFlagsAccessorInvoke;
        return strDangerouslyForceOverride;
    }

    public final void setAccessorProvider$ReactAndroid_release(Function0<? extends ReactNativeFeatureFlagsAccessor> newAccessorProvider) {
        Intrinsics.checkNotNullParameter(newAccessorProvider, "newAccessorProvider");
        accessorProvider = newAccessorProvider;
        accessor = newAccessorProvider.invoke();
    }
}
