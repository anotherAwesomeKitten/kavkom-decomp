package com.swmansion.rnscreens.utils;

import com.facebook.react.uimanager.BackgroundStyleApplicator;
import com.facebook.react.views.view.ReactViewGroup;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ViewBackgroundUtils.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0013\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0000¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"resolveBackgroundColor", "", "Lcom/facebook/react/views/view/ReactViewGroup;", "(Lcom/facebook/react/views/view/ReactViewGroup;)Ljava/lang/Integer;", "react-native-screens_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ViewBackgroundUtilsKt {
    public static final Integer resolveBackgroundColor(ReactViewGroup reactViewGroup) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "<this>");
        return BackgroundStyleApplicator.getBackgroundColor(reactViewGroup);
    }
}
