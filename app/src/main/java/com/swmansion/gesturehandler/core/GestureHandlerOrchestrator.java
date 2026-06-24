package com.swmansion.gesturehandler.core;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import androidx.core.app.NotificationCompat;
import com.swmansion.gesturehandler.react.ExtensionsKt;
import com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper;
import com.swmansion.gesturehandler.react.RNGestureHandlerRootView;
import io.sentry.protocol.SentryThread;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GestureHandlerOrchestrator.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0002\b\f\u0018\u0000 L2\u00020\u0001:\u0001LB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020!J\u0016\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00122\u0006\u0010#\u001a\u00020$J\b\u0010%\u001a\u00020&H\u0002J\b\u0010'\u001a\u00020&H\u0002J\u0010\u0010(\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020\u0013H\u0002J\u0010\u0010*\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020\u0013H\u0002J\u0010\u0010+\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020\u0013H\u0002J\u0010\u0010,\u001a\u00020&2\u0006\u0010)\u001a\u00020\u0013H\u0002J\b\u0010-\u001a\u00020&H\u0002J\u001e\u0010.\u001a\u00020&2\u0006\u0010)\u001a\u00020\u00132\u0006\u0010/\u001a\u00020\u00192\u0006\u00100\u001a\u00020\u0019J\u0010\u00101\u001a\u00020&2\u0006\u0010)\u001a\u00020\u0013H\u0002J\u0010\u00102\u001a\u00020&2\u0006\u0010 \u001a\u00020!H\u0002J\b\u00103\u001a\u00020&H\u0002J\u0018\u00104\u001a\u00020&2\u0006\u0010)\u001a\u00020\u00132\u0006\u00105\u001a\u00020!H\u0002J\u0012\u00106\u001a\u00020\u001b2\b\u0010#\u001a\u0004\u0018\u00010$H\u0002J\u0006\u00107\u001a\u00020\u001bJ\u0018\u00108\u001a\u00020!2\b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010 \u001a\u00020!J\u0018\u00109\u001a\u00020:2\b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010;\u001a\u00020:J\u0010\u0010<\u001a\u00020&2\u0006\u0010)\u001a\u00020\u0013H\u0002J\u0018\u0010=\u001a\u00020&2\u0006\u0010)\u001a\u00020\u00132\u0006\u0010#\u001a\u00020$H\u0002J\u0010\u0010>\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020$H\u0002J \u0010?\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020$2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\u0019H\u0002J\u0018\u0010C\u001a\u00020\u001b2\u0006\u0010)\u001a\u00020\u00132\u0006\u0010 \u001a\u00020!H\u0002J(\u0010D\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020$2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010E\u001a\u00020&2\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010F\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020$H\u0002J(\u0010E\u001a\u00020\u001b2\u0006\u0010G\u001a\u00020\u00032\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!H\u0002J(\u0010H\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020$2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010I\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020$H\u0002J\u0010\u0010J\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020$H\u0002J\u000e\u0010K\u001a\u00020&2\u0006\u0010#\u001a\u00020$R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0012j\b\u0012\u0004\u0012\u00020\u0013`\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0012j\b\u0012\u0004\u0012\u00020\u0013`\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0012j\b\u0012\u0004\u0012\u00020\u0013`\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006M"}, d2 = {"Lcom/swmansion/gesturehandler/core/GestureHandlerOrchestrator;", "", "wrapperView", "Landroid/view/ViewGroup;", "handlerRegistry", "Lcom/swmansion/gesturehandler/core/GestureHandlerRegistry;", "viewConfigHelper", "Lcom/swmansion/gesturehandler/core/ViewConfigurationHelper;", "rootView", "<init>", "(Landroid/view/ViewGroup;Lcom/swmansion/gesturehandler/core/GestureHandlerRegistry;Lcom/swmansion/gesturehandler/core/ViewConfigurationHelper;Landroid/view/ViewGroup;)V", "minimumAlphaForTraversal", "", "getMinimumAlphaForTraversal", "()F", "setMinimumAlphaForTraversal", "(F)V", "gestureHandlers", "Ljava/util/ArrayList;", "Lcom/swmansion/gesturehandler/core/GestureHandler;", "Lkotlin/collections/ArrayList;", "awaitingHandlers", "preparedHandlers", "awaitingHandlersTags", "Ljava/util/HashSet;", "", "isHandlingTouch", "", "handlingChangeSemaphore", "finishedHandlersCleanupScheduled", "activationIndex", "onTouchEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "getHandlersForView", "view", "Landroid/view/View;", "scheduleFinishedHandlersCleanup", "", "cleanupFinishedHandlers", "hasOtherHandlerToWaitFor", "handler", "shouldBeCancelledByFinishedHandler", "shouldBeCancelledByActiveHandler", "tryActivate", "cleanupAwaitingHandlers", "onHandlerStateChange", "newState", "prevState", "makeActive", "deliverEventToGestureHandlers", "cancelAll", "deliverEventToGestureHandler", "sourceEvent", "isViewAttachedUnderWrapper", "isAnyHandlerActive", "transformEventToViewCoords", "transformPointToViewCoords", "Landroid/graphics/PointF;", "point", "addAwaitingHandler", "recordHandlerIfNotPresent", "isViewOverflowingParent", "extractAncestorHandlers", "coords", "", "pointerId", "shouldHandlerSkipHoverEvents", "recordViewHandlersForPointer", "extractGestureHandlers", "shouldIgnoreSubtreeIfGestureHandlerRootView", "viewGroup", "traverseWithPointerEvents", "canReceiveEvents", "isClipping", "activateNativeHandlersForView", "Companion", "react-native-gesture-handler_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class GestureHandlerOrchestrator {
    private static final float DEFAULT_MIN_ALPHA_FOR_TRAVERSAL = 0.0f;
    private int activationIndex;
    private final ArrayList<GestureHandler> awaitingHandlers;
    private final HashSet<Integer> awaitingHandlersTags;
    private boolean finishedHandlersCleanupScheduled;
    private final ArrayList<GestureHandler> gestureHandlers;
    private final GestureHandlerRegistry handlerRegistry;
    private int handlingChangeSemaphore;
    private boolean isHandlingTouch;
    private float minimumAlphaForTraversal;
    private final ArrayList<GestureHandler> preparedHandlers;
    private final ViewGroup rootView;
    private final ViewConfigurationHelper viewConfigHelper;
    private final ViewGroup wrapperView;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final PointF tempPoint = new PointF();
    private static final float[] matrixTransformCoords = new float[2];
    private static final Matrix inverseMatrix = new Matrix();
    private static final float[] tempCoords = new float[2];
    private static final Comparator<GestureHandler> handlersComparator = new Comparator() { // from class: com.swmansion.gesturehandler.core.GestureHandlerOrchestrator$$ExternalSyntheticLambda0
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return GestureHandlerOrchestrator.handlersComparator$lambda$15((GestureHandler) obj, (GestureHandler) obj2);
        }
    };

    /* JADX INFO: compiled from: GestureHandlerOrchestrator.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PointerEventsConfig.values().length];
            try {
                iArr[PointerEventsConfig.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PointerEventsConfig.BOX_ONLY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PointerEventsConfig.BOX_NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[PointerEventsConfig.AUTO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public GestureHandlerOrchestrator(ViewGroup wrapperView, GestureHandlerRegistry handlerRegistry, ViewConfigurationHelper viewConfigHelper, ViewGroup rootView) {
        Intrinsics.checkNotNullParameter(wrapperView, "wrapperView");
        Intrinsics.checkNotNullParameter(handlerRegistry, "handlerRegistry");
        Intrinsics.checkNotNullParameter(viewConfigHelper, "viewConfigHelper");
        Intrinsics.checkNotNullParameter(rootView, "rootView");
        this.wrapperView = wrapperView;
        this.handlerRegistry = handlerRegistry;
        this.viewConfigHelper = viewConfigHelper;
        this.rootView = rootView;
        this.gestureHandlers = new ArrayList<>();
        this.awaitingHandlers = new ArrayList<>();
        this.preparedHandlers = new ArrayList<>();
        this.awaitingHandlersTags = new HashSet<>();
    }

    public final float getMinimumAlphaForTraversal() {
        return this.minimumAlphaForTraversal;
    }

    public final void setMinimumAlphaForTraversal(float f) {
        this.minimumAlphaForTraversal = f;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            java.lang.String r0 = "event"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            r0 = 1
            r4.isHandlingTouch = r0
            int r1 = r5.getActionMasked()
            r2 = 3
            if (r1 == 0) goto L1c
            if (r1 == r2) goto L18
            r3 = 5
            if (r1 == r3) goto L1c
            r3 = 7
            if (r1 == r3) goto L1c
            goto L1f
        L18:
            r4.cancelAll()
            goto L1f
        L1c:
            r4.extractGestureHandlers(r5)
        L1f:
            r4.deliverEventToGestureHandlers(r5)
            r3 = 0
            r4.isHandlingTouch = r3
            boolean r3 = r4.finishedHandlersCleanupScheduled
            if (r3 == 0) goto L30
            int r3 = r4.handlingChangeSemaphore
            if (r3 != 0) goto L30
            r4.cleanupFinishedHandlers()
        L30:
            if (r1 == r0) goto L39
            if (r1 == r2) goto L39
            r2 = 10
            if (r1 == r2) goto L39
            goto L4f
        L39:
            java.util.ArrayList<com.swmansion.gesturehandler.core.GestureHandler> r1 = r4.gestureHandlers
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L4f
            android.view.ViewGroup r1 = r4.rootView
            boolean r2 = r1 instanceof com.facebook.react.uimanager.RootView
            if (r2 == 0) goto L4f
            r2 = r1
            com.facebook.react.uimanager.RootView r2 = (com.facebook.react.uimanager.RootView) r2
            android.view.View r1 = (android.view.View) r1
            r2.onChildEndedNativeGesture(r1, r5)
        L4f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.core.GestureHandlerOrchestrator.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final ArrayList<GestureHandler> getHandlersForView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        return this.handlerRegistry.getHandlersForView(view);
    }

    private final void scheduleFinishedHandlersCleanup() {
        if (this.isHandlingTouch || this.handlingChangeSemaphore != 0) {
            this.finishedHandlersCleanupScheduled = true;
        } else {
            cleanupFinishedHandlers();
        }
    }

    private final void cleanupFinishedHandlers() {
        for (GestureHandler gestureHandler : CollectionsKt.asReversedMutable(this.gestureHandlers)) {
            if (INSTANCE.isFinished(gestureHandler.getState()) && !gestureHandler.getIsAwaiting()) {
                gestureHandler.reset();
                gestureHandler.setActive(false);
                gestureHandler.setAwaiting(false);
                gestureHandler.setActivationIndex(Integer.MAX_VALUE);
            }
        }
        CollectionsKt.removeAll((List) this.gestureHandlers, new Function1() { // from class: com.swmansion.gesturehandler.core.GestureHandlerOrchestrator$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(GestureHandlerOrchestrator.cleanupFinishedHandlers$lambda$1((GestureHandler) obj));
            }
        });
        this.finishedHandlersCleanupScheduled = false;
    }

    public static final boolean cleanupFinishedHandlers$lambda$1(GestureHandler it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return INSTANCE.isFinished(it.getState()) && !it.getIsAwaiting();
    }

    private final boolean hasOtherHandlerToWaitFor(GestureHandler handler) {
        ArrayList<GestureHandler> arrayList = this.gestureHandlers;
        if ((arrayList instanceof Collection) && arrayList.isEmpty()) {
            return false;
        }
        for (GestureHandler gestureHandler : arrayList) {
            Companion companion = INSTANCE;
            if (!companion.isFinished(gestureHandler.getState()) && companion.shouldHandlerWaitForOther(handler, gestureHandler)) {
                return true;
            }
        }
        return false;
    }

    private final boolean shouldBeCancelledByFinishedHandler(GestureHandler handler) {
        ArrayList<GestureHandler> arrayList = this.gestureHandlers;
        if ((arrayList instanceof Collection) && arrayList.isEmpty()) {
            return false;
        }
        for (GestureHandler gestureHandler : arrayList) {
            if (INSTANCE.shouldHandlerWaitForOther(handler, gestureHandler) && gestureHandler.getState() == 5) {
                return true;
            }
        }
        return false;
    }

    private final boolean shouldBeCancelledByActiveHandler(GestureHandler handler) {
        ArrayList<GestureHandler> arrayList = this.gestureHandlers;
        if ((arrayList instanceof Collection) && arrayList.isEmpty()) {
            return false;
        }
        for (GestureHandler gestureHandler : arrayList) {
            if (handler.hasCommonPointers(gestureHandler) && gestureHandler.getState() == 4 && !INSTANCE.canRunSimultaneously(handler, gestureHandler) && handler.isDescendantOf(gestureHandler)) {
                return true;
            }
        }
        return false;
    }

    private final void tryActivate(GestureHandler handler) {
        if (shouldBeCancelledByFinishedHandler(handler) || shouldBeCancelledByActiveHandler(handler)) {
            handler.cancel();
        } else if (hasOtherHandlerToWaitFor(handler)) {
            addAwaitingHandler(handler);
        } else {
            makeActive(handler);
            handler.setAwaiting(false);
        }
    }

    private final void cleanupAwaitingHandlers() {
        for (GestureHandler gestureHandler : CollectionsKt.toList(this.awaitingHandlers)) {
            if (!gestureHandler.getIsAwaiting()) {
                this.awaitingHandlers.remove(gestureHandler);
                this.awaitingHandlersTags.remove(Integer.valueOf(gestureHandler.getTag()));
            }
        }
    }

    public final void onHandlerStateChange(GestureHandler handler, int newState, int prevState) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.handlingChangeSemaphore++;
        if (INSTANCE.isFinished(newState)) {
            for (GestureHandler gestureHandler : CollectionsKt.toList(this.awaitingHandlers)) {
                if (INSTANCE.shouldHandlerWaitForOther(gestureHandler, handler) && this.awaitingHandlersTags.contains(Integer.valueOf(gestureHandler.getTag()))) {
                    if (newState == 5) {
                        gestureHandler.cancel();
                        if (gestureHandler.getState() == 5) {
                            gestureHandler.dispatchStateChange(3, 2);
                        }
                        gestureHandler.setAwaiting(false);
                    } else {
                        tryActivate(gestureHandler);
                    }
                }
            }
            cleanupAwaitingHandlers();
        }
        if (newState == 4) {
            tryActivate(handler);
        } else if (prevState == 4 || prevState == 5) {
            if (handler.getIsActive()) {
                handler.dispatchStateChange(newState, prevState);
            } else if (prevState == 4 && (newState == 3 || newState == 1)) {
                handler.dispatchStateChange(newState, 2);
            }
        } else if (prevState != 0 || newState != 3) {
            handler.dispatchStateChange(newState, prevState);
        }
        this.handlingChangeSemaphore--;
        scheduleFinishedHandlersCleanup();
    }

    private final void makeActive(GestureHandler handler) {
        int state = handler.getState();
        handler.setAwaiting(false);
        handler.setActive(true);
        handler.setShouldResetProgress(true);
        int i = this.activationIndex;
        this.activationIndex = i + 1;
        handler.setActivationIndex(i);
        for (GestureHandler gestureHandler : CollectionsKt.asReversedMutable(this.gestureHandlers)) {
            if (INSTANCE.shouldHandlerBeCancelledBy(gestureHandler, handler)) {
                gestureHandler.cancel();
            }
        }
        for (GestureHandler gestureHandler2 : CollectionsKt.asReversedMutable(this.awaitingHandlers)) {
            if (INSTANCE.shouldHandlerBeCancelledBy(gestureHandler2, handler)) {
                gestureHandler2.setAwaiting(false);
            }
        }
        cleanupAwaitingHandlers();
        if (state == 1 || state == 3) {
            return;
        }
        handler.dispatchStateChange(4, 2);
        if (state != 4) {
            handler.dispatchStateChange(5, 4);
            if (state != 5) {
                handler.dispatchStateChange(0, 5);
            }
        }
    }

    private final void deliverEventToGestureHandlers(MotionEvent motionEvent) {
        this.preparedHandlers.clear();
        this.preparedHandlers.addAll(this.gestureHandlers);
        CollectionsKt.sortWith(this.preparedHandlers, handlersComparator);
        Iterator<GestureHandler> it = this.preparedHandlers.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            deliverEventToGestureHandler(it.next(), motionEvent);
        }
    }

    private final void cancelAll() {
        Iterator it = CollectionsKt.toList(CollectionsKt.asReversedMutable(this.awaitingHandlers)).iterator();
        while (it.hasNext()) {
            ((GestureHandler) it.next()).cancel();
        }
        this.preparedHandlers.clear();
        this.preparedHandlers.addAll(this.gestureHandlers);
        Iterator it2 = CollectionsKt.asReversedMutable(this.gestureHandlers).iterator();
        while (it2.hasNext()) {
            ((GestureHandler) it2.next()).cancel();
        }
    }

    private final void deliverEventToGestureHandler(GestureHandler handler, MotionEvent sourceEvent) {
        if (!isViewAttachedUnderWrapper(handler.getView())) {
            handler.cancel();
            return;
        }
        if (handler.wantsEvent(sourceEvent)) {
            int actionMasked = sourceEvent.getActionMasked();
            View view = handler.getView();
            MotionEvent motionEventObtain = MotionEvent.obtain(sourceEvent);
            Intrinsics.checkNotNullExpressionValue(motionEventObtain, "obtain(...)");
            MotionEvent motionEventTransformEventToViewCoords = transformEventToViewCoords(view, motionEventObtain);
            if (handler.getNeedsPointerData() && handler.getState() != 0) {
                handler.updatePointerData(motionEventTransformEventToViewCoords, sourceEvent);
            }
            if (!handler.getIsAwaiting() || actionMasked != 2) {
                boolean z = handler.getState() == 0;
                handler.handle(motionEventTransformEventToViewCoords, sourceEvent);
                if (handler.getIsActive()) {
                    if (handler.getShouldResetProgress()) {
                        handler.setShouldResetProgress(false);
                        handler.resetProgress();
                    }
                    handler.dispatchHandlerUpdate(motionEventTransformEventToViewCoords);
                }
                if (handler.getNeedsPointerData() && z) {
                    handler.updatePointerData(motionEventTransformEventToViewCoords, sourceEvent);
                }
                if (actionMasked == 1 || actionMasked == 6 || actionMasked == 10) {
                    handler.stopTrackingPointer(motionEventTransformEventToViewCoords.getPointerId(motionEventTransformEventToViewCoords.getActionIndex()));
                }
            }
            motionEventTransformEventToViewCoords.recycle();
        }
    }

    private final boolean isViewAttachedUnderWrapper(View view) {
        if (view == null) {
            return false;
        }
        if (view == this.wrapperView) {
            return true;
        }
        ViewParent parent = view.getParent();
        while (parent != null && parent != this.wrapperView) {
            parent = parent.getParent();
        }
        return parent == this.wrapperView;
    }

    public final boolean isAnyHandlerActive() {
        ArrayList<GestureHandler> arrayList = this.gestureHandlers;
        if ((arrayList instanceof Collection) && arrayList.isEmpty()) {
            return false;
        }
        Iterator<T> it = arrayList.iterator();
        while (it.hasNext()) {
            if (((GestureHandler) it.next()).getState() == 4) {
                return true;
            }
        }
        return false;
    }

    public final MotionEvent transformEventToViewCoords(View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        if (view != null) {
            ViewParent parent = view.getParent();
            ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (!Intrinsics.areEqual(viewGroup, this.wrapperView)) {
                transformEventToViewCoords(viewGroup, motionEvent);
            }
            if (viewGroup != null) {
                motionEvent.setLocation((motionEvent.getX() + viewGroup.getScrollX()) - view.getLeft(), (motionEvent.getY() + viewGroup.getScrollY()) - view.getTop());
            }
            if (!view.getMatrix().isIdentity()) {
                Matrix matrix = view.getMatrix();
                Matrix matrix2 = inverseMatrix;
                matrix.invert(matrix2);
                motionEvent.transform(matrix2);
            }
        }
        return motionEvent;
    }

    public final PointF transformPointToViewCoords(View view, PointF point) {
        Intrinsics.checkNotNullParameter(point, "point");
        if (view != null) {
            ViewParent parent = view.getParent();
            ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (!Intrinsics.areEqual(viewGroup, this.wrapperView)) {
                transformPointToViewCoords(viewGroup, point);
            }
            if (viewGroup != null) {
                point.x += viewGroup.getScrollX() - view.getLeft();
                point.y += viewGroup.getScrollY() - view.getTop();
            }
            if (!view.getMatrix().isIdentity()) {
                Matrix matrix = view.getMatrix();
                Matrix matrix2 = inverseMatrix;
                matrix.invert(matrix2);
                float[] fArr = tempCoords;
                fArr[0] = point.x;
                fArr[1] = point.y;
                matrix2.mapPoints(fArr);
                point.x = fArr[0];
                point.y = fArr[1];
            }
        }
        return point;
    }

    private final void addAwaitingHandler(GestureHandler handler) {
        if (this.awaitingHandlers.contains(handler)) {
            return;
        }
        this.awaitingHandlers.add(handler);
        this.awaitingHandlersTags.add(Integer.valueOf(handler.getTag()));
        handler.setAwaiting(true);
        int i = this.activationIndex;
        this.activationIndex = i + 1;
        handler.setActivationIndex(i);
    }

    private final void recordHandlerIfNotPresent(GestureHandler handler, View view) {
        if (this.gestureHandlers.contains(handler)) {
            return;
        }
        this.gestureHandlers.add(handler);
        handler.setActive(false);
        handler.setAwaiting(false);
        handler.setActivationIndex(Integer.MAX_VALUE);
        handler.prepare(view, this);
    }

    private final boolean isViewOverflowingParent(View view) {
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup == null) {
            return false;
        }
        Matrix matrix = view.getMatrix();
        float[] fArr = matrixTransformCoords;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        matrix.mapPoints(fArr);
        float left = fArr[0] + view.getLeft();
        float top = fArr[1] + view.getTop();
        return left < 0.0f || left + ((float) view.getWidth()) > ((float) viewGroup.getWidth()) || top < 0.0f || top + ((float) view.getHeight()) > ((float) viewGroup.getHeight());
    }

    private final boolean extractAncestorHandlers(View view, float[] coords, int pointerId) {
        boolean z = false;
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof ViewGroup) {
                if ((parent instanceof RNGestureHandlerRootView) && ((RNGestureHandlerRootView) parent).getRootViewEnabled()) {
                    break;
                }
                ViewGroup viewGroup = (ViewGroup) parent;
                ArrayList<GestureHandler> handlersForView = this.handlerRegistry.getHandlersForView((View) parent);
                if (handlersForView != null) {
                    synchronized (handlersForView) {
                        Iterator<GestureHandler> it = handlersForView.iterator();
                        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
                        while (it.hasNext()) {
                            GestureHandler next = it.next();
                            if (next.getIsEnabled() && next.isWithinBounds(view, coords[0], coords[1])) {
                                recordHandlerIfNotPresent(next, viewGroup);
                                next.startTrackingPointer(pointerId);
                                z = true;
                            }
                        }
                        Unit unit = Unit.INSTANCE;
                    }
                } else {
                    continue;
                }
            }
        }
        return z;
    }

    private final boolean shouldHandlerSkipHoverEvents(GestureHandler handler, MotionEvent motionEvent) {
        return ((handler instanceof HoverGestureHandler) || (handler instanceof RNGestureHandlerRootHelper.RootViewGestureHandler) || !ExtensionsKt.isHoverAction(motionEvent)) ? false : true;
    }

    private final boolean recordViewHandlersForPointer(View view, float[] coords, int pointerId, MotionEvent motionEvent) {
        boolean z;
        ArrayList<GestureHandler> handlersForView = this.handlerRegistry.getHandlersForView(view);
        if (handlersForView != null) {
            synchronized (handlersForView) {
                Iterator<GestureHandler> it = handlersForView.iterator();
                Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
                z = false;
                while (it.hasNext()) {
                    GestureHandler next = it.next();
                    if (next.getIsEnabled() && next.isWithinBounds(view, coords[0], coords[1]) && !shouldHandlerSkipHoverEvents(next, motionEvent)) {
                        recordHandlerIfNotPresent(next, view);
                        next.startTrackingPointer(pointerId);
                        z = true;
                    }
                }
                Unit unit = Unit.INSTANCE;
            }
        } else {
            z = false;
        }
        float width = view.getWidth();
        float f = coords[0];
        if (0.0f <= f && f <= width) {
            float height = view.getHeight();
            float f2 = coords[1];
            if (0.0f <= f2 && f2 <= height && isViewOverflowingParent(view) && extractAncestorHandlers(view, coords, pointerId)) {
                return true;
            }
        }
        return z;
    }

    private final void extractGestureHandlers(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        int pointerId = motionEvent.getPointerId(actionIndex);
        float[] fArr = tempCoords;
        fArr[0] = motionEvent.getX(actionIndex);
        fArr[1] = motionEvent.getY(actionIndex);
        traverseWithPointerEvents(this.wrapperView, fArr, pointerId, motionEvent);
        extractGestureHandlers(this.wrapperView, fArr, pointerId, motionEvent);
    }

    private final boolean shouldIgnoreSubtreeIfGestureHandlerRootView(View view) {
        return (view instanceof RNGestureHandlerRootView) && !Intrinsics.areEqual(view, this.wrapperView) && ((RNGestureHandlerRootView) view).getRootViewEnabled();
    }

    private final boolean extractGestureHandlers(ViewGroup viewGroup, float[] coords, int pointerId, MotionEvent motionEvent) {
        ViewGroup viewGroup2;
        if (shouldIgnoreSubtreeIfGestureHandlerRootView(viewGroup)) {
            return false;
        }
        int childCount = viewGroup.getChildCount() - 1;
        while (-1 < childCount) {
            View childInDrawingOrderAtIndex = this.viewConfigHelper.getChildInDrawingOrderAtIndex(viewGroup, childCount);
            if (canReceiveEvents(childInDrawingOrderAtIndex)) {
                PointF pointF = tempPoint;
                Companion companion = INSTANCE;
                viewGroup2 = viewGroup;
                companion.transformPointToChildViewCoords(coords[0], coords[1], viewGroup2, childInDrawingOrderAtIndex, pointF);
                float f = coords[0];
                float f2 = coords[1];
                coords[0] = pointF.x;
                coords[1] = pointF.y;
                boolean zTraverseWithPointerEvents = (!isClipping(childInDrawingOrderAtIndex) || companion.isTransformedTouchPointInView(coords[0], coords[1], childInDrawingOrderAtIndex)) ? traverseWithPointerEvents(childInDrawingOrderAtIndex, coords, pointerId, motionEvent) : false;
                coords[0] = f;
                coords[1] = f2;
                if (zTraverseWithPointerEvents) {
                    return true;
                }
            } else {
                viewGroup2 = viewGroup;
            }
            childCount--;
            viewGroup = viewGroup2;
        }
        return false;
    }

    private final boolean traverseWithPointerEvents(View view, float[] coords, int pointerId, MotionEvent motionEvent) {
        if (shouldIgnoreSubtreeIfGestureHandlerRootView(view)) {
            return false;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[this.viewConfigHelper.getPointerEventsConfigForView(view).ordinal()];
        if (i == 1) {
            return false;
        }
        if (i == 2) {
            return recordViewHandlersForPointer(view, coords, pointerId, motionEvent) || INSTANCE.shouldHandlerlessViewBecomeTouchTarget(view, coords);
        }
        if (i != 3) {
            if (i == 4) {
                return recordViewHandlersForPointer(view, coords, pointerId, motionEvent) || (view instanceof ViewGroup ? extractGestureHandlers((ViewGroup) view, coords, pointerId, motionEvent) : false) || INSTANCE.shouldHandlerlessViewBecomeTouchTarget(view, coords);
            }
            throw new NoWhenBranchMatchedException();
        }
        if (view instanceof ViewGroup) {
            boolean zExtractGestureHandlers = extractGestureHandlers((ViewGroup) view, coords, pointerId, motionEvent);
            if (zExtractGestureHandlers) {
                recordViewHandlersForPointer(view, coords, pointerId, motionEvent);
            }
            return zExtractGestureHandlers;
        }
        if (view instanceof EditText) {
            return recordViewHandlersForPointer(view, coords, pointerId, motionEvent);
        }
        return false;
    }

    private final boolean canReceiveEvents(View view) {
        return view.getVisibility() == 0 && view.getAlpha() >= this.minimumAlphaForTraversal;
    }

    private final boolean isClipping(View view) {
        return !(view instanceof ViewGroup) || this.viewConfigHelper.isViewClippingChildren((ViewGroup) view);
    }

    public final void activateNativeHandlersForView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        ArrayList<GestureHandler> handlersForView = this.handlerRegistry.getHandlersForView(view);
        if (handlersForView != null) {
            for (final GestureHandler gestureHandler : handlersForView) {
                if (gestureHandler instanceof NativeViewGestureHandler) {
                    recordHandlerIfNotPresent(gestureHandler, view);
                    gestureHandler.withMarkedAsInBounds(new Function0() { // from class: com.swmansion.gesturehandler.core.GestureHandlerOrchestrator$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return GestureHandlerOrchestrator.activateNativeHandlersForView$lambda$14$lambda$13(gestureHandler);
                        }
                    });
                }
            }
        }
    }

    public static final Unit activateNativeHandlersForView$lambda$14$lambda$13(GestureHandler gestureHandler) {
        gestureHandler.begin();
        gestureHandler.activate();
        gestureHandler.end();
        return Unit.INSTANCE;
    }

    /* JADX INFO: compiled from: GestureHandlerOrchestrator.kt */
    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\tH\u0002J0\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u0007H\u0002J \u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u0013H\u0002J\u0018\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u000fH\u0002J\u0018\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\u000fH\u0002J\u0018\u0010$\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u000fH\u0002J\u0010\u0010%\u001a\u00020\u00112\u0006\u0010&\u001a\u00020'H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/swmansion/gesturehandler/core/GestureHandlerOrchestrator$Companion;", "", "<init>", "()V", "DEFAULT_MIN_ALPHA_FOR_TRAVERSAL", "", "tempPoint", "Landroid/graphics/PointF;", "matrixTransformCoords", "", "inverseMatrix", "Landroid/graphics/Matrix;", "tempCoords", "handlersComparator", "Ljava/util/Comparator;", "Lcom/swmansion/gesturehandler/core/GestureHandler;", "shouldHandlerlessViewBecomeTouchTarget", "", "view", "Landroid/view/View;", "coords", "transformPointToChildViewCoords", "", "x", "y", "parent", "Landroid/view/ViewGroup;", "child", "outLocalPoint", "isTransformedTouchPointInView", "shouldHandlerWaitForOther", "handler", "other", "canRunSimultaneously", "a", "b", "shouldHandlerBeCancelledBy", "isFinished", SentryThread.JsonKeys.STATE, "", "react-native-gesture-handler_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isFinished(int i) {
            return i == 3 || i == 1 || i == 5;
        }

        private Companion() {
        }

        public final boolean shouldHandlerlessViewBecomeTouchTarget(View view, float[] coords) {
            return !((view instanceof ViewGroup) && view.getBackground() == null) && isTransformedTouchPointInView(coords[0], coords[1], view);
        }

        public final void transformPointToChildViewCoords(float x, float y, ViewGroup parent, View child, PointF outLocalPoint) {
            float scrollX = (x + parent.getScrollX()) - child.getLeft();
            float scrollY = (y + parent.getScrollY()) - child.getTop();
            Matrix matrix = child.getMatrix();
            if (!matrix.isIdentity()) {
                float[] fArr = GestureHandlerOrchestrator.matrixTransformCoords;
                fArr[0] = scrollX;
                fArr[1] = scrollY;
                matrix.invert(GestureHandlerOrchestrator.inverseMatrix);
                GestureHandlerOrchestrator.inverseMatrix.mapPoints(fArr);
                float f = fArr[0];
                scrollY = fArr[1];
                scrollX = f;
            }
            outLocalPoint.set(scrollX, scrollY);
        }

        public final boolean isTransformedTouchPointInView(float x, float y, View child) {
            return 0.0f <= x && x <= ((float) child.getWidth()) && 0.0f <= y && y <= ((float) child.getHeight());
        }

        public final boolean shouldHandlerWaitForOther(GestureHandler handler, GestureHandler other) {
            if (handler != other) {
                return handler.shouldWaitForHandlerFailure(other) || other.shouldRequireToWaitForFailure(handler);
            }
            return false;
        }

        public final boolean canRunSimultaneously(GestureHandler a, GestureHandler b) {
            return a == b || a.shouldRecognizeSimultaneously(b) || b.shouldRecognizeSimultaneously(a);
        }

        public final boolean shouldHandlerBeCancelledBy(GestureHandler handler, GestureHandler other) {
            if (!handler.hasCommonPointers(other) || canRunSimultaneously(handler, other)) {
                return false;
            }
            if (handler == other) {
                return true;
            }
            if (handler.getIsAwaiting() || handler.getState() == 4) {
                return handler.shouldBeCancelledBy(other);
            }
            return true;
        }
    }

    public static final int handlersComparator$lambda$15(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        if ((gestureHandler.getIsActive() && gestureHandler2.getIsActive()) || (gestureHandler.getIsAwaiting() && gestureHandler2.getIsAwaiting())) {
            return Integer.signum(gestureHandler2.getActivationIndex() - gestureHandler.getActivationIndex());
        }
        if (gestureHandler.getIsActive()) {
            return -1;
        }
        if (gestureHandler2.getIsActive()) {
            return 1;
        }
        if (gestureHandler.getIsAwaiting()) {
            return -1;
        }
        return gestureHandler2.getIsAwaiting() ? 1 : 0;
    }
}
