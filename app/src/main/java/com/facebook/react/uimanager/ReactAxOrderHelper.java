package com.facebook.react.uimanager;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.R;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactAxOrderHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u001a\u0010\t\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000bJ7\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b2\u000e\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000f¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/facebook/react/uimanager/ReactAxOrderHelper;", "", "<init>", "()V", "cleanUpAxOrder", "", "view", "Landroid/view/View;", "restoreFocusability", "disableFocusForSubtree", "axOrderList", "", "buildAxOrderList", "parent", "result", "", "(Landroid/view/View;Landroid/view/View;Ljava/util/List;[Landroid/view/View;)V", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ReactAxOrderHelper {
    public static final ReactAxOrderHelper INSTANCE = new ReactAxOrderHelper();

    private ReactAxOrderHelper() {
    }

    @JvmStatic
    public static final void cleanUpAxOrder(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        Boolean bool = (Boolean) view.getTag(R.id.original_focusability);
        if (bool != null) {
            view.setFocusable(bool.booleanValue());
        }
        if (((View) view.getTag(R.id.accessibility_order_parent)) != null) {
            view.setTag(R.id.accessibility_order_parent, null);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(...)");
                cleanUpAxOrder(childAt);
            }
        }
    }

    @JvmStatic
    public static final void restoreFocusability(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        Boolean bool = (Boolean) view.getTag(R.id.original_focusability);
        if (bool != null) {
            view.setFocusable(bool.booleanValue());
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(...)");
                restoreFocusability(childAt);
            }
        }
    }

    public final void disableFocusForSubtree(View view, List<?> axOrderList) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(axOrderList, "axOrderList");
        if (!CollectionsKt.contains(axOrderList, view.getTag(R.id.view_tag_native_id))) {
            if (view.getTag(R.id.original_focusability) == null) {
                view.setTag(R.id.original_focusability, Boolean.valueOf(view.isFocusable()));
            }
            view.setFocusable(false);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(...)");
                disableFocusForSubtree(childAt, axOrderList);
            }
        }
    }

    public final void buildAxOrderList(View view, View parent, List<?> axOrderList, View[] result) {
        int iIndexOf;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(axOrderList, "axOrderList");
        Intrinsics.checkNotNullParameter(result, "result");
        Object tag = view.getTag(R.id.view_tag_native_id);
        view.setTag(R.id.accessibility_order_parent, parent);
        if (CollectionsKt.contains(axOrderList, tag) && (iIndexOf = CollectionsKt.indexOf((List<? extends Object>) axOrderList, tag)) != -1) {
            result[iIndexOf] = view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(...)");
                buildAxOrderList(childAt, parent, axOrderList, result);
            }
        }
    }
}
