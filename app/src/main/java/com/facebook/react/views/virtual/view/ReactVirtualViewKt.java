package com.facebook.react.views.virtual.view;

import com.facebook.react.common.build.ReactBuildConfig;
import kotlin.Metadata;

/* JADX INFO: compiled from: ReactVirtualView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"DEBUG_TAG", "", "IS_DEBUG_BUILD", "", "ReactAndroid_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ReactVirtualViewKt {
    private static final String DEBUG_TAG = "ReactVirtualView";
    private static final boolean IS_DEBUG_BUILD;

    static {
        IS_DEBUG_BUILD = ReactBuildConfig.DEBUG || ReactBuildConfig.IS_INTERNAL_BUILD || ReactBuildConfig.ENABLE_PERFETTO;
    }
}
