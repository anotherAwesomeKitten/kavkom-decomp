package com.facebook.react.uimanager;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.common.logging.FLog;
import com.facebook.react.common.ReactConstants;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ViewGroupDrawingOrderHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rJ\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007J\u0006\u0010\u0014\u001a\u00020\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/facebook/react/uimanager/ViewGroupDrawingOrderHelper;", "", "viewGroup", "Landroid/view/ViewGroup;", "<init>", "(Landroid/view/ViewGroup;)V", "numberOfChildrenWithZIndex", "", "drawingOrderIndices", "", "handleAddView", "", "view", "Landroid/view/View;", "handleRemoveView", "shouldEnableCustomDrawingOrder", "", "getChildDrawingOrder", "childCount", FirebaseAnalytics.Param.INDEX, "update", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ViewGroupDrawingOrderHelper {
    private int[] drawingOrderIndices;
    private int numberOfChildrenWithZIndex;
    private final ViewGroup viewGroup;

    public ViewGroupDrawingOrderHelper(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        this.viewGroup = viewGroup;
    }

    public final void handleAddView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (ViewGroupManager.INSTANCE.getViewZIndex(view) != null) {
            this.numberOfChildrenWithZIndex++;
        }
        this.drawingOrderIndices = null;
    }

    public final void handleRemoveView(View view) {
        if (ViewGroupManager.INSTANCE.getViewZIndex(view) != null) {
            this.numberOfChildrenWithZIndex--;
        }
        this.drawingOrderIndices = null;
    }

    public final boolean shouldEnableCustomDrawingOrder() {
        return this.numberOfChildrenWithZIndex > 0;
    }

    public final int getChildDrawingOrder(int childCount, int index) {
        int[] iArr = this.drawingOrderIndices;
        if (iArr != null && (index >= iArr.length || iArr[index] >= childCount)) {
            FLog.w(ReactConstants.TAG, "getChildDrawingOrder index out of bounds! Please check any custom view manipulations you may have done. childCount = %d, index = %d", Integer.valueOf(childCount), Integer.valueOf(index));
            update();
        }
        if (iArr == null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < childCount; i++) {
                arrayList.add(this.viewGroup.getChildAt(i));
            }
            final Function2 function2 = new Function2() { // from class: com.facebook.react.uimanager.ViewGroupDrawingOrderHelper$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return Integer.valueOf(ViewGroupDrawingOrderHelper.getChildDrawingOrder$lambda$0((View) obj, (View) obj2));
                }
            };
            CollectionsKt.sortWith(arrayList, new Comparator() { // from class: com.facebook.react.uimanager.ViewGroupDrawingOrderHelper$$ExternalSyntheticLambda1
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return ViewGroupDrawingOrderHelper.getChildDrawingOrder$lambda$1(function2, obj, obj2);
                }
            });
            int[] iArr2 = new int[childCount];
            for (int i2 = 0; i2 < childCount; i2++) {
                Object obj = arrayList.get(i2);
                Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                iArr2[i2] = this.viewGroup.indexOfChild((View) obj);
            }
            this.drawingOrderIndices = iArr2;
            iArr = iArr2;
        }
        return iArr[index];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int getChildDrawingOrder$lambda$1(Function2 function2, Object obj, Object obj2) {
        return ((Number) function2.invoke(obj, obj2)).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int getChildDrawingOrder$lambda$0(View view, View view2) {
        Integer viewZIndex = ViewGroupManager.INSTANCE.getViewZIndex(view);
        int iIntValue = viewZIndex != null ? viewZIndex.intValue() : 0;
        Integer viewZIndex2 = ViewGroupManager.INSTANCE.getViewZIndex(view2);
        return iIntValue - (viewZIndex2 != null ? viewZIndex2.intValue() : 0);
    }

    public final void update() {
        this.numberOfChildrenWithZIndex = 0;
        int childCount = this.viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (ViewGroupManager.INSTANCE.getViewZIndex(this.viewGroup.getChildAt(i)) != null) {
                this.numberOfChildrenWithZIndex++;
            }
        }
        this.drawingOrderIndices = null;
    }
}
