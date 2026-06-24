package com.swmansion.rnscreens.bottomsheet;

import android.view.View;
import com.swmansion.rnscreens.Screen;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SheetUtils.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0006¨\u0006\u0007"}, d2 = {"isSheetFitToContents", "", "Lcom/swmansion/rnscreens/Screen;", "usesFormSheetPresentation", "requiresEnterTransitionPostponing", "isLaidOutOrHasCachedLayout", "Landroid/view/View;", "react-native-screens_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SheetUtilsKt {
    public static final boolean isSheetFitToContents(Screen screen) {
        Intrinsics.checkNotNullParameter(screen, "<this>");
        return screen.getStackPresentation() == Screen.StackPresentation.FORM_SHEET && screen.getSheetDetents().getCount$react_native_screens_release() == 1 && screen.getSheetDetents().shortest$react_native_screens_release() == -1.0d;
    }

    public static final boolean usesFormSheetPresentation(Screen screen) {
        Intrinsics.checkNotNullParameter(screen, "<this>");
        return screen.getStackPresentation() == Screen.StackPresentation.FORM_SHEET;
    }

    public static final boolean requiresEnterTransitionPostponing(Screen screen) {
        Intrinsics.checkNotNullParameter(screen, "<this>");
        return !screen.getSheetShouldOverflowTopInset() && usesFormSheetPresentation(screen);
    }

    public static final boolean isLaidOutOrHasCachedLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return view.isLaidOut() || view.getHeight() > 0 || view.getWidth() > 0;
    }
}
