package com.facebook.react.views.scroll;

import android.graphics.Rect;
import com.facebook.common.logging.FLog;
import com.facebook.react.common.build.ReactBuildConfig;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VirtualViewContainer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0000\u001a!\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u000eH\u0082\b\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\"\u0014\u0010\u0007\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000f"}, d2 = {"rectsOverlap", "", "rect1", "Landroid/graphics/Rect;", "rect2", "DEBUG_TAG", "", "IS_DEBUG_BUILD", "getIS_DEBUG_BUILD", "()Z", "debugLog", "", "subtag", "block", "Lkotlin/Function0;", "ReactAndroid_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class VirtualViewContainerKt {
    private static final String DEBUG_TAG = "VirtualViewContainerState";
    private static final boolean IS_DEBUG_BUILD;

    public static final boolean rectsOverlap(Rect rect1, Rect rect2) {
        Intrinsics.checkNotNullParameter(rect1, "rect1");
        Intrinsics.checkNotNullParameter(rect2, "rect2");
        return rect1.top < rect2.bottom && rect2.top < rect1.bottom && rect1.left < rect2.right && rect2.left < rect1.right;
    }

    public static final boolean getIS_DEBUG_BUILD() {
        return IS_DEBUG_BUILD;
    }

    static {
        IS_DEBUG_BUILD = ReactBuildConfig.DEBUG || ReactBuildConfig.IS_INTERNAL_BUILD || ReactBuildConfig.ENABLE_PERFETTO;
    }

    /* JADX INFO: renamed from: com.facebook.react.views.scroll.VirtualViewContainerKt$debugLog$1 */
    /* JADX INFO: compiled from: VirtualViewContainer.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class AnonymousClass1 implements Function0<String> {
        public static final AnonymousClass1 INSTANCE = ;

        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            return "";
        }
    }

    static /* synthetic */ void debugLog$default(String str, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = AnonymousClass1.INSTANCE;
        }
        if (getIS_DEBUG_BUILD() && ReactNativeFeatureFlags.enableVirtualViewDebugFeatures()) {
            FLog.d("VirtualViewContainerState:" + str, (String) function0.invoke());
        }
    }

    private static final void debugLog(String str, Function0<String> function0) {
        if (getIS_DEBUG_BUILD() && ReactNativeFeatureFlags.enableVirtualViewDebugFeatures()) {
            FLog.d("VirtualViewContainerState:" + str, function0.invoke());
        }
    }
}
