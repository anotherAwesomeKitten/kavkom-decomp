package com.swmansion.gesturehandler.core;

import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;

/* JADX INFO: compiled from: ViewConfigurationHelper.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\bH&¨\u0006\r"}, d2 = {"Lcom/swmansion/gesturehandler/core/ViewConfigurationHelper;", "", "getPointerEventsConfigForView", "Lcom/swmansion/gesturehandler/core/PointerEventsConfig;", "view", "Landroid/view/View;", "getChildInDrawingOrderAtIndex", "parent", "Landroid/view/ViewGroup;", FirebaseAnalytics.Param.INDEX, "", "isViewClippingChildren", "", "react-native-gesture-handler_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface ViewConfigurationHelper {
    View getChildInDrawingOrderAtIndex(ViewGroup parent, int index);

    PointerEventsConfig getPointerEventsConfigForView(View view);

    boolean isViewClippingChildren(ViewGroup view);
}
