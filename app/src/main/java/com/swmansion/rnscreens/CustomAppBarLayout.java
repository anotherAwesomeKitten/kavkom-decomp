package com.swmansion.rnscreens;

import android.content.Context;
import android.view.View;
import com.google.android.material.appbar.AppBarLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomAppBarLayout.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\nJ\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\tH\u0002¨\u0006\r"}, d2 = {"Lcom/swmansion/rnscreens/CustomAppBarLayout;", "Lcom/google/android/material/appbar/AppBarLayout;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "applyToolbarLayoutCorrection", "", "toolbarPaddingTop", "", "applyToolbarLayoutCorrection$react_native_screens_release", "applyFrameCorrectionByTopInset", "topInset", "react-native-screens_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class CustomAppBarLayout extends AppBarLayout {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomAppBarLayout(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final void applyToolbarLayoutCorrection$react_native_screens_release(int toolbarPaddingTop) {
        applyFrameCorrectionByTopInset(toolbarPaddingTop);
    }

    private final void applyFrameCorrectionByTopInset(int topInset) {
        measure(View.MeasureSpec.makeMeasureSpec(getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getHeight() + topInset, 1073741824));
        layout(getLeft(), getTop(), getRight(), getBottom() + topInset);
    }
}
