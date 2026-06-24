package com.swmansion.gesturehandler;

import android.view.View;
import android.view.ViewParent;
import com.horcrux.svg.SvgView;
import com.horcrux.svg.VirtualView;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RNSVGHitTester.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/swmansion/gesturehandler/RNSVGHitTester;", "", "<init>", "()V", "Companion", "react-native-gesture-handler_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class RNSVGHitTester {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: RNSVGHitTester.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0001J\u001e\u0010\n\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f¨\u0006\u000e"}, d2 = {"Lcom/swmansion/gesturehandler/RNSVGHitTester$Companion;", "", "<init>", "()V", "getRootSvgView", "Lcom/horcrux/svg/SvgView;", "view", "Landroid/view/View;", "isSvgElement", "", "hitTest", "posX", "", "posY", "react-native-gesture-handler_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final SvgView getRootSvgView(View view) {
            SvgView svgView;
            if (view instanceof VirtualView) {
                svgView = ((VirtualView) view).getSvgView();
                Intrinsics.checkNotNull(svgView);
            } else {
                Intrinsics.checkNotNull(view, "null cannot be cast to non-null type com.horcrux.svg.SvgView");
                svgView = (SvgView) view;
            }
            while (true) {
                ViewParent parent = svgView.getParent();
                Intrinsics.checkNotNullExpressionValue(parent, "getParent(...)");
                if (!isSvgElement(parent)) {
                    return svgView;
                }
                if (svgView.getParent() instanceof VirtualView) {
                    ViewParent parent2 = svgView.getParent();
                    Intrinsics.checkNotNull(parent2, "null cannot be cast to non-null type com.horcrux.svg.VirtualView");
                    svgView = ((VirtualView) parent2).getSvgView();
                    Intrinsics.checkNotNull(svgView);
                } else {
                    ViewParent parent3 = svgView.getParent();
                    Intrinsics.checkNotNull(parent3, "null cannot be cast to non-null type com.horcrux.svg.SvgView");
                    svgView = (SvgView) parent3;
                }
            }
        }

        public final boolean isSvgElement(Object view) {
            Intrinsics.checkNotNullParameter(view, "view");
            return (view instanceof VirtualView) || (view instanceof SvgView);
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0056  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean hitTest(android.view.View r11, float r12, float r13) {
            /*
                r10 = this;
                java.lang.String r0 = "view"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
                com.horcrux.svg.SvgView r0 = r10.getRootSvgView(r11)
                r1 = 0
                int[] r2 = new int[]{r1, r1}
                int[] r3 = new int[]{r1, r1}
                r11.getLocationOnScreen(r2)
                r0.getLocationOnScreen(r3)
                r4 = r2[r1]
                float r4 = (float) r4
                float r4 = r4 + r12
                r5 = r3[r1]
                float r5 = (float) r5
                float r4 = r4 - r5
                r5 = 1
                r2 = r2[r5]
                float r2 = (float) r2
                float r2 = r2 + r13
                r3 = r3[r5]
                float r3 = (float) r3
                float r2 = r2 - r3
                int r0 = r0.reactTagForTouch(r4, r2)
                int r2 = r11.getId()
                if (r2 != r0) goto L35
                r2 = r5
                goto L36
            L35:
                r2 = r1
            L36:
                int r3 = r11.getWidth()
                double r3 = (double) r3
                double r6 = (double) r12
                r8 = 0
                int r12 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
                if (r12 > 0) goto L56
                int r12 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
                if (r12 > 0) goto L56
                int r12 = r11.getHeight()
                double r3 = (double) r12
                double r12 = (double) r13
                int r6 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
                if (r6 > 0) goto L56
                int r12 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
                if (r12 > 0) goto L56
                r12 = r5
                goto L57
            L56:
                r12 = r1
            L57:
                boolean r13 = r11 instanceof com.horcrux.svg.SvgView
                if (r13 == 0) goto L7a
                android.view.ViewGroup r11 = (android.view.ViewGroup) r11
                kotlin.sequences.Sequence r11 = androidx.core.view.ViewGroupKt.getChildren(r11)
                com.swmansion.gesturehandler.RNSVGHitTester$Companion$$ExternalSyntheticLambda0 r13 = new com.swmansion.gesturehandler.RNSVGHitTester$Companion$$ExternalSyntheticLambda0
                r13.<init>()
                kotlin.sequences.Sequence r11 = kotlin.sequences.SequencesKt.map(r11, r13)
                java.lang.Integer r13 = java.lang.Integer.valueOf(r0)
                boolean r11 = kotlin.sequences.SequencesKt.contains(r11, r13)
                if (r2 != 0) goto L76
                if (r11 == 0) goto L79
            L76:
                if (r12 == 0) goto L79
                return r5
            L79:
                return r1
            L7a:
                if (r2 == 0) goto L7f
                if (r12 == 0) goto L7f
                return r5
            L7f:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.RNSVGHitTester.Companion.hitTest(android.view.View, float, float):boolean");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final int hitTest$lambda$0(View it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return it.getId();
        }
    }
}
