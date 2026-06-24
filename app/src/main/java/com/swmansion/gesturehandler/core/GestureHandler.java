package com.swmansion.gesturehandler.core;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import com.oblador.keychain.KeychainModule;
import com.swmansion.gesturehandler.RNSVGHitTester;
import com.swmansion.gesturehandler.react.ExtensionsKt;
import com.swmansion.gesturehandler.react.eventbuilders.GestureHandlerEventDataBuilder;
import io.sentry.protocol.SentryThread;
import io.sentry.rrweb.RRWebInteractionEvent;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: GestureHandler.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0002\u0010\n\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\b\u0016\u0018\u0000 È\u00012\u00020\u0001:\bÆ\u0001Ç\u0001È\u0001É\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010f\u001a\u00020g2\u0006\u0010h\u001a\u00020\u00072\u0006\u0010i\u001a\u00020\u0007H\u0016J\u0010\u0010j\u001a\u00020g2\u0006\u0010k\u001a\u00020lH\u0016J\b\u0010m\u001a\u00020gH\u0016J\b\u0010n\u001a\u00020gH\u0016J\u000e\u0010o\u001a\u00020\u001b2\u0006\u0010p\u001a\u00020\u0000J6\u0010q\u001a\u00020g2\u0006\u0010r\u001a\u00020\u00152\u0006\u0010s\u001a\u00020\u00152\u0006\u0010t\u001a\u00020\u00152\u0006\u0010u\u001a\u00020\u00152\u0006\u0010v\u001a\u00020\u00152\u0006\u0010w\u001a\u00020\u0015J\u000e\u0010q\u001a\u00020g2\u0006\u0010x\u001a\u00020\u0015J\u0010\u0010y\u001a\u00020g2\b\u0010z\u001a\u0004\u0018\u00010VJ\u001a\u0010{\u001a\u00020g2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010I\u001a\u0004\u0018\u00010JJ\b\u0010|\u001a\u00020gH\u0014J\u0015\u0010}\u001a\u0004\u0018\u00010~2\t\u0010\u007f\u001a\u0005\u0018\u00010\u0080\u0001H\u0002J\t\u0010\u0081\u0001\u001a\u00020\u0007H\u0002J\u0010\u0010\u0082\u0001\u001a\u00020g2\u0007\u0010\u0083\u0001\u001a\u00020\u0007J\u0010\u0010\u0084\u0001\u001a\u00020g2\u0007\u0010\u0083\u0001\u001a\u00020\u0007J\u0012\u0010\u0085\u0001\u001a\u00020\u001b2\u0007\u0010\u0083\u0001\u001a\u00020\u0007H\u0002J\u0011\u0010\u0086\u0001\u001a\u00020\u001b2\u0006\u0010k\u001a\u00020lH\u0002J\u0011\u0010\u0087\u0001\u001a\u00020l2\u0006\u0010k\u001a\u00020lH\u0002J\u0019\u0010\u0088\u0001\u001a\u00020g2\u0007\u0010\u0089\u0001\u001a\u00020l2\u0007\u0010\u008a\u0001\u001a\u00020lJ\u001a\u0010\u008b\u0001\u001a\u00020g2\u0006\u0010k\u001a\u00020l2\u0007\u0010\u008a\u0001\u001a\u00020lH\u0002J\u001a\u0010\u008c\u0001\u001a\u00020g2\u0006\u0010k\u001a\u00020l2\u0007\u0010\u008a\u0001\u001a\u00020lH\u0002J\u001a\u0010\u008d\u0001\u001a\u00020g2\u0006\u0010k\u001a\u00020l2\u0007\u0010\u008a\u0001\u001a\u00020lH\u0002J\u0018\u0010\u008e\u0001\u001a\u00020g2\u0006\u0010k\u001a\u00020l2\u0007\u0010\u008a\u0001\u001a\u00020lJ\t\u0010\u008f\u0001\u001a\u00020gH\u0002J\t\u0010\u0090\u0001\u001a\u00020gH\u0002J\u0012\u0010\u0091\u0001\u001a\u00020g2\u0007\u0010\u0092\u0001\u001a\u000201H\u0002J\u0012\u0010\u0093\u0001\u001a\u00020g2\u0007\u0010\u0092\u0001\u001a\u000201H\u0002J\u0013\u0010\u0094\u0001\u001a\u00030\u0095\u00012\u0007\u0010\u0092\u0001\u001a\u000201H\u0002J\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010%J\t\u0010\u0097\u0001\u001a\u0004\u0018\u00010%J\u0011\u0010\u0098\u0001\u001a\u00020g2\u0006\u0010h\u001a\u00020\u0007H\u0002J\u000f\u0010\u0099\u0001\u001a\u00020\u001b2\u0006\u0010k\u001a\u00020lJ\u0012\u0010\u009a\u0001\u001a\u00020\u001b2\u0007\u0010\u009b\u0001\u001a\u00020\u0000H\u0016J\u0010\u0010\u009c\u0001\u001a\u00020\u001b2\u0007\u0010\u009b\u0001\u001a\u00020\u0000J\u0012\u0010\u009d\u0001\u001a\u00020\u001b2\u0007\u0010\u009b\u0001\u001a\u00020\u0000H\u0016J\u0012\u0010\u009e\u0001\u001a\u00020\u001b2\u0007\u0010\u009b\u0001\u001a\u00020\u0000H\u0016J\"\u0010\u001c\u001a\u00020\u001b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0007\u0010\u009f\u0001\u001a\u00020\u00152\u0007\u0010 \u0001\u001a\u00020\u0015J\u0007\u0010¡\u0001\u001a\u00020gJ\u0007\u0010¢\u0001\u001a\u00020gJ\u0007\u0010£\u0001\u001a\u00020gJ\u0012\u0010£\u0001\u001a\u00020g2\u0007\u0010¤\u0001\u001a\u00020\u001bH\u0016J\u0007\u0010¥\u0001\u001a\u00020gJ\u0007\u0010¦\u0001\u001a\u00020gJ\u0010\u0010§\u0001\u001a\u00020\u001b2\u0007\u0010¨\u0001\u001a\u00020\u0000J\t\u0010©\u0001\u001a\u00020gH\u0016J\u001a\u0010ª\u0001\u001a\u00020g2\u0006\u0010k\u001a\u00020l2\u0007\u0010\u008a\u0001\u001a\u00020lH\u0014J\u001a\u0010«\u0001\u001a\u00020g2\u0006\u0010k\u001a\u00020l2\u0007\u0010\u008a\u0001\u001a\u00020lH\u0014J\u001a\u0010¬\u0001\u001a\u00020g2\u0006\u0010h\u001a\u00020\u00072\u0007\u0010\u00ad\u0001\u001a\u00020\u0007H\u0014J\t\u0010®\u0001\u001a\u00020gH\u0014J\t\u0010¯\u0001\u001a\u00020gH\u0014J\t\u0010°\u0001\u001a\u00020gH\u0014J\u0012\u0010±\u0001\u001a\u00020\u001b2\u0007\u0010²\u0001\u001a\u00020\u0007H\u0002J\u0012\u0010³\u0001\u001a\u00020\u001b2\u0007\u0010\u008a\u0001\u001a\u00020lH\u0004J\u0014\u0010´\u0001\u001a\u00030µ\u00012\b\u0010¶\u0001\u001a\u00030µ\u0001H\u0004J\u0007\u0010·\u0001\u001a\u00020gJ\u0017\u0010¸\u0001\u001a\u00020g2\u000e\u0010¹\u0001\u001a\t\u0012\u0004\u0012\u00020g0º\u0001J\u0011\u0010»\u0001\u001a\u00020g2\u0006\u0010k\u001a\u00020lH\u0002J\n\u0010¼\u0001\u001a\u00030½\u0001H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\"\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000bR\u001e\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u0015@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u0015@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u001e\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u000e\u001a\u00020\u001b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR$\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001b@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001d\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u000b\"\u0004\b$\u0010\rR\"\u0010&\u001a\u0004\u0018\u00010%2\b\u0010\u000e\u001a\u0004\u0018\u00010%@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\"\u0010)\u001a\u0004\u0018\u00010%2\b\u0010\u000e\u001a\u0004\u0018\u00010%@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b*\u0010(R\u001e\u0010+\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u000bR\u001e\u0010-\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u000bR\u0018\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010100X\u0082\u0004¢\u0006\u0004\n\u0002\u00102R\u001a\u00103\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u001d\"\u0004\b5\u0010!R\u0010\u00106\u001a\u0004\u0018\u000107X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u00109\u001a\u0002082\u0006\u0010\u000e\u001a\u000208@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u001e\u0010<\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u0015@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b=\u0010\u0018R\u001e\u0010>\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u0015@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b?\u0010\u0018R\u000e\u0010@\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010C\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\u000b\"\u0004\bE\u0010\rR\u001a\u0010F\u001a\u00020\u001bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010\u001d\"\u0004\bH\u0010!R\u001c\u0010I\u001a\u0004\u0018\u00010JX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010L\"\u0004\bM\u0010NR\u001c\u0010O\u001a\u0004\u0018\u00010PX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\u0010\u0010U\u001a\u0004\u0018\u00010VX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010W\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bX\u0010\u000bR\u001a\u0010Y\u001a\u00020\u0007X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010\u000b\"\u0004\b[\u0010\rR\u001a\u0010\\\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b]\u0010\u000b\"\u0004\b^\u0010\rR\u001a\u0010_\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010\u001d\"\u0004\b`\u0010!R\u001a\u0010a\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\ba\u0010\u001d\"\u0004\bb\u0010!R\u001a\u0010c\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010\u001d\"\u0004\be\u0010!R\u0013\u0010¾\u0001\u001a\u00020\u00158F¢\u0006\u0007\u001a\u0005\b¿\u0001\u0010\u0018R\u0013\u0010À\u0001\u001a\u00020\u00158F¢\u0006\u0007\u001a\u0005\bÁ\u0001\u0010\u0018R\u0013\u0010Â\u0001\u001a\u00020\u00158F¢\u0006\u0007\u001a\u0005\bÃ\u0001\u0010\u0018R\u0013\u0010Ä\u0001\u001a\u00020\u00158F¢\u0006\u0007\u001a\u0005\bÅ\u0001\u0010\u0018¨\u0006Ê\u0001"}, d2 = {"Lcom/swmansion/gesturehandler/core/GestureHandler;", "", "<init>", "()V", "trackedPointerIDs", "", "trackedPointersIDsCount", "", "windowOffset", "tag", "getTag", "()I", "setTag", "(I)V", "value", "Landroid/view/View;", "view", "getView", "()Landroid/view/View;", SentryThread.JsonKeys.STATE, "getState", "", "x", "getX", "()F", "y", "getY", "", "isWithinBounds", "()Z", ViewProps.ENABLED, "isEnabled", "setEnabled", "(Z)V", "actionType", "getActionType", "setActionType", "Lcom/facebook/react/bridge/WritableArray;", "changedTouchesPayload", "getChangedTouchesPayload", "()Lcom/facebook/react/bridge/WritableArray;", "allTouchesPayload", "getAllTouchesPayload", "touchEventType", "getTouchEventType", "trackedPointersCount", "getTrackedPointersCount", "trackedPointers", "", "Lcom/swmansion/gesturehandler/core/GestureHandler$PointerData;", "[Lcom/swmansion/gesturehandler/core/GestureHandler$PointerData;", "needsPointerData", "getNeedsPointerData", "setNeedsPointerData", "hitSlop", "", "", "eventCoalescingKey", "getEventCoalescingKey", "()S", "lastAbsolutePositionX", "getLastAbsolutePositionX", "lastAbsolutePositionY", "getLastAbsolutePositionY", "manualActivation", "lastEventOffsetX", "lastEventOffsetY", "numberOfPointers", "getNumberOfPointers", "setNumberOfPointers", "shouldCancelWhenOutside", "getShouldCancelWhenOutside", "setShouldCancelWhenOutside", "orchestrator", "Lcom/swmansion/gesturehandler/core/GestureHandlerOrchestrator;", "getOrchestrator", "()Lcom/swmansion/gesturehandler/core/GestureHandlerOrchestrator;", "setOrchestrator", "(Lcom/swmansion/gesturehandler/core/GestureHandlerOrchestrator;)V", "onTouchEventListener", "Lcom/swmansion/gesturehandler/core/OnTouchEventListener;", "getOnTouchEventListener", "()Lcom/swmansion/gesturehandler/core/OnTouchEventListener;", "setOnTouchEventListener", "(Lcom/swmansion/gesturehandler/core/OnTouchEventListener;)V", "interactionController", "Lcom/swmansion/gesturehandler/core/GestureHandlerInteractionController;", RRWebInteractionEvent.JsonKeys.POINTER_TYPE, "getPointerType", "mouseButton", "getMouseButton", "setMouseButton", "activationIndex", "getActivationIndex", "setActivationIndex", "isActive", "setActive", "isAwaiting", "setAwaiting", "shouldResetProgress", "getShouldResetProgress", "setShouldResetProgress", "dispatchStateChange", "", "newState", "prevState", "dispatchHandlerUpdate", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "dispatchTouchEvent", "resetConfig", "hasCommonPointers", "other", "setHitSlop", "leftPad", "topPad", "rightPad", "bottomPad", "width", "height", ViewProps.PADDING, "setInteractionController", "controller", "prepare", "onPrepare", "getActivity", "Landroid/app/Activity;", "context", "Landroid/content/Context;", "findNextLocalPointerId", "startTrackingPointer", "pointerId", "stopTrackingPointer", "isTrackingPointer", "needAdapt", "adaptEvent", "handle", "transformedEvent", "sourceEvent", "dispatchTouchDownEvent", "dispatchTouchUpEvent", "dispatchTouchMoveEvent", "updatePointerData", "extractAllPointersData", "cancelPointers", "addChangedPointer", "pointerData", "addPointerToAll", "createPointerData", "Lcom/facebook/react/bridge/WritableMap;", "consumeChangedTouchesPayload", "consumeAllTouchesPayload", "moveToState", "wantsEvent", "shouldRequireToWaitForFailure", "handler", "shouldWaitForHandlerFailure", "shouldRecognizeSimultaneously", "shouldBeCancelledBy", "posX", "posY", KeychainModule.AuthPromptOptions.CANCEL, "fail", "activate", "force", "begin", ViewProps.END, "isDescendantOf", "of", "resetProgress", "onHandle", "onHandleHover", "onStateChange", "previousState", "onReset", "onCancel", "onFail", "isButtonInConfig", "clickedButton", "shouldActivateWithMouse", "transformPoint", "Landroid/graphics/PointF;", "point", "reset", "withMarkedAsInBounds", "closure", "Lkotlin/Function0;", "setPointerType", "toString", "", "lastRelativePositionX", "getLastRelativePositionX", "lastRelativePositionY", "getLastRelativePositionY", "lastPositionInWindowX", "getLastPositionInWindowX", "lastPositionInWindowY", "getLastPositionInWindowY", "AdaptEventException", "Factory", "Companion", "PointerData", "react-native-gesture-handler_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class GestureHandler {
    public static final int ACTION_TYPE_JS_FUNCTION_NEW_API = 4;
    public static final int ACTION_TYPE_JS_FUNCTION_OLD_API = 3;
    public static final int ACTION_TYPE_NATIVE_ANIMATED_EVENT = 2;
    public static final int ACTION_TYPE_REANIMATED_WORKLET = 1;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Void DEFAULT_HIT_SLOP = null;
    private static final boolean DEFAULT_IS_ENABLED = true;
    private static final boolean DEFAULT_MANUAL_ACTIVATION = false;
    private static final int DEFAULT_MOUSE_BUTTON = 0;
    private static final boolean DEFAULT_NEEDS_POINTER_DATA = false;
    private static final boolean DEFAULT_SHOULD_CANCEL_WHEN_OUTSIDE = false;
    public static final int DIRECTION_DOWN = 8;
    public static final int DIRECTION_LEFT = 2;
    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_UP = 4;
    private static final int HIT_SLOP_BOTTOM_IDX = 3;
    private static final int HIT_SLOP_HEIGHT_IDX = 5;
    private static final int HIT_SLOP_LEFT_IDX = 0;
    public static final float HIT_SLOP_NONE = Float.NaN;
    private static final int HIT_SLOP_RIGHT_IDX = 2;
    private static final int HIT_SLOP_TOP_IDX = 1;
    private static final int HIT_SLOP_WIDTH_IDX = 4;
    private static final int MAX_POINTERS_COUNT = 12;
    public static final int POINTER_TYPE_MOUSE = 2;
    public static final int POINTER_TYPE_OTHER = 3;
    public static final int POINTER_TYPE_STYLUS = 1;
    public static final int POINTER_TYPE_TOUCH = 0;
    public static final int STATE_ACTIVE = 4;
    public static final int STATE_BEGAN = 2;
    public static final int STATE_CANCELLED = 3;
    public static final int STATE_END = 5;
    public static final int STATE_FAILED = 1;
    public static final int STATE_UNDETERMINED = 0;
    private static short nextEventCoalescingKey;
    private static MotionEvent.PointerCoords[] pointerCoords;
    private static MotionEvent.PointerProperties[] pointerProps;
    private int actionType;
    private int activationIndex;
    private WritableArray allTouchesPayload;
    private WritableArray changedTouchesPayload;
    private short eventCoalescingKey;
    private float[] hitSlop;
    private GestureHandlerInteractionController interactionController;
    private boolean isActive;
    private boolean isAwaiting;
    private boolean isEnabled;
    private boolean isWithinBounds;
    private float lastAbsolutePositionX;
    private float lastAbsolutePositionY;
    private float lastEventOffsetX;
    private float lastEventOffsetY;
    private boolean manualActivation;
    private int mouseButton;
    private boolean needsPointerData;
    private int numberOfPointers;
    private OnTouchEventListener onTouchEventListener;
    private GestureHandlerOrchestrator orchestrator;
    private int pointerType;
    private boolean shouldCancelWhenOutside;
    private boolean shouldResetProgress;
    private int state;
    private int tag;
    private int touchEventType;
    private final int[] trackedPointerIDs = new int[12];
    private final PointerData[] trackedPointers;
    private int trackedPointersCount;
    private int trackedPointersIDsCount;
    private View view;
    private final int[] windowOffset;
    private float x;
    private float y;

    protected void onCancel() {
    }

    protected void onFail() {
    }

    protected void onHandleHover(MotionEvent motionEvent, MotionEvent sourceEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        Intrinsics.checkNotNullParameter(sourceEvent, "sourceEvent");
    }

    protected void onPrepare() {
    }

    protected void onReset() {
    }

    protected void onStateChange(int newState, int previousState) {
    }

    public void resetProgress() {
    }

    public GestureHandler() {
        int[] iArr = new int[2];
        for (int i = 0; i < 2; i++) {
            iArr[i] = 0;
        }
        this.windowOffset = iArr;
        this.isEnabled = true;
        PointerData[] pointerDataArr = new PointerData[12];
        for (int i2 = 0; i2 < 12; i2++) {
            pointerDataArr[i2] = null;
        }
        this.trackedPointers = pointerDataArr;
        this.pointerType = 3;
    }

    public final int getTag() {
        return this.tag;
    }

    public final void setTag(int i) {
        this.tag = i;
    }

    public final View getView() {
        return this.view;
    }

    public final int getState() {
        return this.state;
    }

    public final float getX() {
        return this.x;
    }

    public final float getY() {
        return this.y;
    }

    /* JADX INFO: renamed from: isWithinBounds, reason: from getter */
    public final boolean getIsWithinBounds() {
        return this.isWithinBounds;
    }

    /* JADX INFO: renamed from: isEnabled, reason: from getter */
    public final boolean getIsEnabled() {
        return this.isEnabled;
    }

    public final void setEnabled(boolean z) {
        if (this.view != null && this.isEnabled != z) {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.swmansion.gesturehandler.core.GestureHandler$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.cancel();
                }
            });
        }
        this.isEnabled = z;
    }

    public final int getActionType() {
        return this.actionType;
    }

    public final void setActionType(int i) {
        this.actionType = i;
    }

    public final WritableArray getChangedTouchesPayload() {
        return this.changedTouchesPayload;
    }

    public final WritableArray getAllTouchesPayload() {
        return this.allTouchesPayload;
    }

    public final int getTouchEventType() {
        return this.touchEventType;
    }

    public final int getTrackedPointersCount() {
        return this.trackedPointersCount;
    }

    public final boolean getNeedsPointerData() {
        return this.needsPointerData;
    }

    public final void setNeedsPointerData(boolean z) {
        this.needsPointerData = z;
    }

    public final short getEventCoalescingKey() {
        return this.eventCoalescingKey;
    }

    public final float getLastAbsolutePositionX() {
        return this.lastAbsolutePositionX;
    }

    public final float getLastAbsolutePositionY() {
        return this.lastAbsolutePositionY;
    }

    public final int getNumberOfPointers() {
        return this.numberOfPointers;
    }

    protected final void setNumberOfPointers(int i) {
        this.numberOfPointers = i;
    }

    protected final boolean getShouldCancelWhenOutside() {
        return this.shouldCancelWhenOutside;
    }

    protected final void setShouldCancelWhenOutside(boolean z) {
        this.shouldCancelWhenOutside = z;
    }

    protected final GestureHandlerOrchestrator getOrchestrator() {
        return this.orchestrator;
    }

    protected final void setOrchestrator(GestureHandlerOrchestrator gestureHandlerOrchestrator) {
        this.orchestrator = gestureHandlerOrchestrator;
    }

    public final OnTouchEventListener getOnTouchEventListener() {
        return this.onTouchEventListener;
    }

    public final void setOnTouchEventListener(OnTouchEventListener onTouchEventListener) {
        this.onTouchEventListener = onTouchEventListener;
    }

    public final int getPointerType() {
        return this.pointerType;
    }

    protected final int getMouseButton() {
        return this.mouseButton;
    }

    protected final void setMouseButton(int i) {
        this.mouseButton = i;
    }

    public final int getActivationIndex() {
        return this.activationIndex;
    }

    public final void setActivationIndex(int i) {
        this.activationIndex = i;
    }

    /* JADX INFO: renamed from: isActive, reason: from getter */
    public final boolean getIsActive() {
        return this.isActive;
    }

    public final void setActive(boolean z) {
        this.isActive = z;
    }

    /* JADX INFO: renamed from: isAwaiting, reason: from getter */
    public final boolean getIsAwaiting() {
        return this.isAwaiting;
    }

    public final void setAwaiting(boolean z) {
        this.isAwaiting = z;
    }

    public final boolean getShouldResetProgress() {
        return this.shouldResetProgress;
    }

    public final void setShouldResetProgress(boolean z) {
        this.shouldResetProgress = z;
    }

    public void dispatchStateChange(int newState, int prevState) {
        OnTouchEventListener onTouchEventListener = this.onTouchEventListener;
        if (onTouchEventListener != null) {
            onTouchEventListener.onStateChange(this, newState, prevState);
        }
    }

    public void dispatchHandlerUpdate(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        OnTouchEventListener onTouchEventListener = this.onTouchEventListener;
        if (onTouchEventListener != null) {
            onTouchEventListener.onHandlerUpdate(this, motionEvent);
        }
    }

    public void dispatchTouchEvent() {
        OnTouchEventListener onTouchEventListener;
        if (this.changedTouchesPayload == null || (onTouchEventListener = this.onTouchEventListener) == null) {
            return;
        }
        onTouchEventListener.onTouchEvent(this);
    }

    public void resetConfig() {
        this.needsPointerData = false;
        this.manualActivation = false;
        this.shouldCancelWhenOutside = false;
        setEnabled(true);
        this.hitSlop = (float[]) DEFAULT_HIT_SLOP;
        this.mouseButton = 0;
    }

    public final boolean hasCommonPointers(GestureHandler other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int length = this.trackedPointerIDs.length;
        for (int i = 0; i < length; i++) {
            if (this.trackedPointerIDs[i] != -1 && other.trackedPointerIDs[i] != -1) {
                return true;
            }
        }
        return false;
    }

    public final void setHitSlop(float leftPad, float topPad, float rightPad, float bottomPad, float width, float height) {
        if (this.hitSlop == null) {
            this.hitSlop = new float[6];
        }
        float[] fArr = this.hitSlop;
        Intrinsics.checkNotNull(fArr);
        fArr[0] = leftPad;
        float[] fArr2 = this.hitSlop;
        Intrinsics.checkNotNull(fArr2);
        fArr2[1] = topPad;
        float[] fArr3 = this.hitSlop;
        Intrinsics.checkNotNull(fArr3);
        fArr3[2] = rightPad;
        float[] fArr4 = this.hitSlop;
        Intrinsics.checkNotNull(fArr4);
        fArr4[3] = bottomPad;
        float[] fArr5 = this.hitSlop;
        Intrinsics.checkNotNull(fArr5);
        fArr5[4] = width;
        float[] fArr6 = this.hitSlop;
        Intrinsics.checkNotNull(fArr6);
        fArr6[5] = height;
        Companion companion = INSTANCE;
        if (companion.hitSlopSet(width) && companion.hitSlopSet(leftPad) && companion.hitSlopSet(rightPad)) {
            throw new IllegalArgumentException("Cannot have all of left, right and width defined".toString());
        }
        if (companion.hitSlopSet(width) && !companion.hitSlopSet(leftPad) && !companion.hitSlopSet(rightPad)) {
            throw new IllegalArgumentException("When width is set one of left or right pads need to be defined".toString());
        }
        if (companion.hitSlopSet(height) && companion.hitSlopSet(bottomPad) && companion.hitSlopSet(topPad)) {
            throw new IllegalArgumentException("Cannot have all of top, bottom and height defined".toString());
        }
        if (companion.hitSlopSet(height) && !companion.hitSlopSet(bottomPad) && !companion.hitSlopSet(topPad)) {
            throw new IllegalArgumentException("When height is set one of top or bottom pads need to be defined".toString());
        }
    }

    public final void setHitSlop(float f) {
        setHitSlop(f, f, f, f, Float.NaN, Float.NaN);
    }

    public final void setInteractionController(GestureHandlerInteractionController controller) {
        this.interactionController = controller;
    }

    public final void prepare(View view, GestureHandlerOrchestrator orchestrator) {
        if (this.view != null || this.orchestrator != null) {
            throw new IllegalStateException("Already prepared or hasn't been reset".toString());
        }
        Arrays.fill(this.trackedPointerIDs, -1);
        this.trackedPointersIDsCount = 0;
        this.state = 0;
        this.view = view;
        this.orchestrator = orchestrator;
        Activity activity = getActivity(view != null ? view.getContext() : null);
        View viewFindViewById = activity != null ? activity.findViewById(R.id.content) : null;
        if (viewFindViewById != null) {
            viewFindViewById.getLocationOnScreen(this.windowOffset);
        } else {
            int[] iArr = this.windowOffset;
            iArr[0] = 0;
            iArr[1] = 0;
        }
        onPrepare();
    }

    private final Activity getActivity(Context context) {
        if (context instanceof ReactContext) {
            return ((ReactContext) context).getCurrentActivity();
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return getActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    private final int findNextLocalPointerId() {
        int[] iArr;
        int i = 0;
        while (i < this.trackedPointersIDsCount) {
            int i2 = 0;
            while (true) {
                iArr = this.trackedPointerIDs;
                if (i2 >= iArr.length || iArr[i2] == i) {
                    break;
                }
                i2++;
            }
            if (i2 == iArr.length) {
                break;
            }
            i++;
        }
        return i;
    }

    public final void startTrackingPointer(int pointerId) {
        if (isTrackingPointer(pointerId)) {
            return;
        }
        this.trackedPointerIDs[pointerId] = findNextLocalPointerId();
        this.trackedPointersIDsCount++;
    }

    public final void stopTrackingPointer(int pointerId) {
        if (isTrackingPointer(pointerId)) {
            this.trackedPointerIDs[pointerId] = -1;
            this.trackedPointersIDsCount--;
        }
    }

    private final boolean isTrackingPointer(int pointerId) {
        return this.trackedPointerIDs[pointerId] != -1;
    }

    private final boolean needAdapt(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() != this.trackedPointersIDsCount) {
            return true;
        }
        int length = this.trackedPointerIDs.length;
        for (int i = 0; i < length; i++) {
            int i2 = this.trackedPointerIDs[i];
            if (i2 != -1 && i2 != i) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final android.view.MotionEvent adaptEvent(android.view.MotionEvent r26) throws com.swmansion.gesturehandler.core.GestureHandler.AdaptEventException {
        /*
            Method dump skipped, instruction units count: 344
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.core.GestureHandler.adaptEvent(android.view.MotionEvent):android.view.MotionEvent");
    }

    /* JADX INFO: compiled from: GestureHandler.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00060\u0001j\u0002`\u0002B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\u0010\u0007\u001a\u00060\bj\u0002`\t¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/swmansion/gesturehandler/core/GestureHandler$AdaptEventException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "handler", "Lcom/swmansion/gesturehandler/core/GestureHandler;", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "e", "Ljava/lang/IllegalArgumentException;", "Lkotlin/IllegalArgumentException;", "<init>", "(Lcom/swmansion/gesturehandler/core/GestureHandler;Landroid/view/MotionEvent;Ljava/lang/IllegalArgumentException;)V", "react-native-gesture-handler_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class AdaptEventException extends Exception {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AdaptEventException(GestureHandler handler, MotionEvent event, IllegalArgumentException e) {
            super(StringsKt.trimIndent("\n    handler: " + Reflection.getOrCreateKotlinClass(handler.getClass()).getSimpleName() + "\n    state: " + handler.getState() + "\n    view: " + handler.getView() + "\n    orchestrator: " + handler.getOrchestrator() + "\n    isEnabled: " + handler.getIsEnabled() + "\n    isActive: " + handler.getIsActive() + "\n    isAwaiting: " + handler.getIsAwaiting() + "\n    trackedPointersCount: " + handler.trackedPointersIDsCount + "\n    trackedPointers: " + ArraysKt.joinToString$default(handler.trackedPointerIDs, (CharSequence) ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null) + "\n    while handling event: " + event + "\n      "), e);
            Intrinsics.checkNotNullParameter(handler, "handler");
            Intrinsics.checkNotNullParameter(event, "event");
            Intrinsics.checkNotNullParameter(e, "e");
        }
    }

    public final void handle(MotionEvent transformedEvent, MotionEvent sourceEvent) {
        int i;
        Intrinsics.checkNotNullParameter(transformedEvent, "transformedEvent");
        Intrinsics.checkNotNullParameter(sourceEvent, "sourceEvent");
        if (!this.isEnabled || (i = this.state) == 3 || i == 1 || i == 5 || this.trackedPointersIDsCount < 1) {
            return;
        }
        try {
            MotionEvent motionEventAdaptEvent = adaptEvent(sourceEvent);
            MotionEvent motionEvent = new MotionEvent[]{adaptEvent(transformedEvent), motionEventAdaptEvent}[0];
            this.x = motionEvent.getX();
            this.y = motionEvent.getY();
            this.numberOfPointers = motionEvent.getPointerCount();
            boolean zIsWithinBounds = isWithinBounds(this.view, this.x, this.y);
            this.isWithinBounds = zIsWithinBounds;
            if (this.shouldCancelWhenOutside && !zIsWithinBounds) {
                int i2 = this.state;
                if (i2 == 4) {
                    cancel();
                    return;
                } else {
                    if (i2 == 2) {
                        fail();
                        return;
                    }
                    return;
                }
            }
            this.lastAbsolutePositionX = GestureUtils.INSTANCE.getLastPointerX(motionEvent, true);
            this.lastAbsolutePositionY = GestureUtils.INSTANCE.getLastPointerY(motionEvent, true);
            this.lastEventOffsetX = motionEvent.getRawX() - motionEvent.getX();
            this.lastEventOffsetY = motionEvent.getRawY() - motionEvent.getY();
            if (sourceEvent.getAction() == 0 || sourceEvent.getAction() == 9 || sourceEvent.getAction() == 7) {
                setPointerType(sourceEvent);
            }
            if (ExtensionsKt.isHoverAction(sourceEvent)) {
                onHandleHover(motionEvent, motionEventAdaptEvent);
            } else {
                onHandle(motionEvent, motionEventAdaptEvent);
            }
            if (!Intrinsics.areEqual(motionEvent, transformedEvent)) {
                motionEvent.recycle();
            }
            if (Intrinsics.areEqual(motionEventAdaptEvent, sourceEvent)) {
                return;
            }
            motionEventAdaptEvent.recycle();
        } catch (AdaptEventException unused) {
            fail();
        }
    }

    private final void dispatchTouchDownEvent(MotionEvent motionEvent, MotionEvent sourceEvent) {
        this.changedTouchesPayload = null;
        this.touchEventType = 1;
        int pointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
        this.trackedPointers[pointerId] = new PointerData(pointerId, motionEvent.getX(motionEvent.getActionIndex()), motionEvent.getY(motionEvent.getActionIndex()), (sourceEvent.getX(motionEvent.getActionIndex()) + (sourceEvent.getRawX() - sourceEvent.getX())) - this.windowOffset[0], (sourceEvent.getY(motionEvent.getActionIndex()) + (sourceEvent.getRawY() - sourceEvent.getY())) - this.windowOffset[1]);
        this.trackedPointersCount++;
        PointerData pointerData = this.trackedPointers[pointerId];
        Intrinsics.checkNotNull(pointerData);
        addChangedPointer(pointerData);
        extractAllPointersData();
        dispatchTouchEvent();
    }

    private final void dispatchTouchUpEvent(MotionEvent motionEvent, MotionEvent sourceEvent) {
        extractAllPointersData();
        this.changedTouchesPayload = null;
        this.touchEventType = 3;
        int pointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
        this.trackedPointers[pointerId] = new PointerData(pointerId, motionEvent.getX(motionEvent.getActionIndex()), motionEvent.getY(motionEvent.getActionIndex()), (sourceEvent.getX(motionEvent.getActionIndex()) + (sourceEvent.getRawX() - sourceEvent.getX())) - this.windowOffset[0], (sourceEvent.getY(motionEvent.getActionIndex()) + (sourceEvent.getRawY() - sourceEvent.getY())) - this.windowOffset[1]);
        PointerData pointerData = this.trackedPointers[pointerId];
        Intrinsics.checkNotNull(pointerData);
        addChangedPointer(pointerData);
        this.trackedPointers[pointerId] = null;
        this.trackedPointersCount--;
        dispatchTouchEvent();
    }

    private final void dispatchTouchMoveEvent(MotionEvent motionEvent, MotionEvent sourceEvent) {
        this.changedTouchesPayload = null;
        this.touchEventType = 2;
        float rawX = sourceEvent.getRawX() - sourceEvent.getX();
        float rawY = sourceEvent.getRawY() - sourceEvent.getY();
        int pointerCount = motionEvent.getPointerCount();
        int i = 0;
        for (int i2 = 0; i2 < pointerCount; i2++) {
            PointerData pointerData = this.trackedPointers[motionEvent.getPointerId(i2)];
            if (pointerData != null && (pointerData.getX() != motionEvent.getX(i2) || pointerData.getY() != motionEvent.getY(i2))) {
                pointerData.setX(motionEvent.getX(i2));
                pointerData.setY(motionEvent.getY(i2));
                pointerData.setAbsoluteX((sourceEvent.getX(i2) + rawX) - this.windowOffset[0]);
                pointerData.setAbsoluteY((sourceEvent.getY(i2) + rawY) - this.windowOffset[1]);
                addChangedPointer(pointerData);
                i++;
            }
        }
        if (i > 0) {
            extractAllPointersData();
            dispatchTouchEvent();
        }
    }

    public final void updatePointerData(MotionEvent motionEvent, MotionEvent sourceEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        Intrinsics.checkNotNullParameter(sourceEvent, "sourceEvent");
        if (motionEvent.getActionMasked() == 0 || motionEvent.getActionMasked() == 5) {
            dispatchTouchDownEvent(motionEvent, sourceEvent);
            dispatchTouchMoveEvent(motionEvent, sourceEvent);
        } else if (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 6) {
            dispatchTouchMoveEvent(motionEvent, sourceEvent);
            dispatchTouchUpEvent(motionEvent, sourceEvent);
        } else if (motionEvent.getActionMasked() == 2) {
            dispatchTouchMoveEvent(motionEvent, sourceEvent);
        }
    }

    private final void extractAllPointersData() {
        this.allTouchesPayload = null;
        for (PointerData pointerData : this.trackedPointers) {
            if (pointerData != null) {
                addPointerToAll(pointerData);
            }
        }
    }

    private final void cancelPointers() {
        this.touchEventType = 4;
        this.changedTouchesPayload = null;
        extractAllPointersData();
        for (PointerData pointerData : this.trackedPointers) {
            if (pointerData != null) {
                addChangedPointer(pointerData);
            }
        }
        this.trackedPointersCount = 0;
        ArraysKt.fill$default(this.trackedPointers, (Object) null, 0, 0, 6, (Object) null);
        dispatchTouchEvent();
    }

    private final void addChangedPointer(PointerData pointerData) {
        if (this.changedTouchesPayload == null) {
            this.changedTouchesPayload = Arguments.createArray();
        }
        WritableArray writableArray = this.changedTouchesPayload;
        Intrinsics.checkNotNull(writableArray);
        writableArray.pushMap(createPointerData(pointerData));
    }

    private final void addPointerToAll(PointerData pointerData) {
        if (this.allTouchesPayload == null) {
            this.allTouchesPayload = Arguments.createArray();
        }
        WritableArray writableArray = this.allTouchesPayload;
        Intrinsics.checkNotNull(writableArray);
        writableArray.pushMap(createPointerData(pointerData));
    }

    private final WritableMap createPointerData(PointerData pointerData) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("id", pointerData.getPointerId());
        writableMapCreateMap.putDouble("x", PixelUtil.toDIPFromPixel(pointerData.getX()));
        writableMapCreateMap.putDouble("y", PixelUtil.toDIPFromPixel(pointerData.getY()));
        writableMapCreateMap.putDouble("absoluteX", PixelUtil.toDIPFromPixel(pointerData.getAbsoluteX()));
        writableMapCreateMap.putDouble("absoluteY", PixelUtil.toDIPFromPixel(pointerData.getAbsoluteY()));
        return writableMapCreateMap;
    }

    public final WritableArray consumeChangedTouchesPayload() {
        WritableArray writableArray = this.changedTouchesPayload;
        this.changedTouchesPayload = null;
        return writableArray;
    }

    public final WritableArray consumeAllTouchesPayload() {
        WritableArray writableArray = this.allTouchesPayload;
        this.allTouchesPayload = null;
        return writableArray;
    }

    private final void moveToState(int newState) {
        UiThreadUtil.assertOnUiThread();
        if (this.state == newState) {
            return;
        }
        if (this.trackedPointersCount > 0 && (newState == 5 || newState == 3 || newState == 1)) {
            cancelPointers();
        }
        int i = this.state;
        this.state = newState;
        if (newState == 4) {
            short s = nextEventCoalescingKey;
            nextEventCoalescingKey = (short) (s + 1);
            this.eventCoalescingKey = s;
        }
        GestureHandlerOrchestrator gestureHandlerOrchestrator = this.orchestrator;
        Intrinsics.checkNotNull(gestureHandlerOrchestrator);
        gestureHandlerOrchestrator.onHandlerStateChange(this, newState, i);
        onStateChange(newState, i);
    }

    public final boolean wantsEvent(MotionEvent motionEvent) {
        int i;
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        return (!this.isEnabled || (i = this.state) == 1 || i == 3 || i == 5 || !isTrackingPointer(motionEvent.getPointerId(motionEvent.getActionIndex()))) ? false : true;
    }

    public boolean shouldRequireToWaitForFailure(GestureHandler handler) {
        GestureHandlerInteractionController gestureHandlerInteractionController;
        Intrinsics.checkNotNullParameter(handler, "handler");
        if (handler == this || (gestureHandlerInteractionController = this.interactionController) == null) {
            return false;
        }
        return gestureHandlerInteractionController.shouldRequireHandlerToWaitForFailure(this, handler);
    }

    public final boolean shouldWaitForHandlerFailure(GestureHandler handler) {
        GestureHandlerInteractionController gestureHandlerInteractionController;
        Intrinsics.checkNotNullParameter(handler, "handler");
        if (handler == this || (gestureHandlerInteractionController = this.interactionController) == null) {
            return false;
        }
        return gestureHandlerInteractionController.shouldWaitForHandlerFailure(this, handler);
    }

    public boolean shouldRecognizeSimultaneously(GestureHandler handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        if (handler == this) {
            return true;
        }
        GestureHandlerInteractionController gestureHandlerInteractionController = this.interactionController;
        if (gestureHandlerInteractionController != null) {
            return gestureHandlerInteractionController.shouldRecognizeSimultaneously(this, handler);
        }
        return false;
    }

    public boolean shouldBeCancelledBy(GestureHandler handler) {
        GestureHandlerInteractionController gestureHandlerInteractionController;
        Intrinsics.checkNotNullParameter(handler, "handler");
        if (handler == this || (gestureHandlerInteractionController = this.interactionController) == null) {
            return false;
        }
        return gestureHandlerInteractionController.shouldHandlerBeCancelledBy(this, handler);
    }

    public final boolean isWithinBounds(View view, float posX, float posY) {
        float f;
        RNSVGHitTester.Companion companion = RNSVGHitTester.INSTANCE;
        Intrinsics.checkNotNull(view);
        if (companion.isSvgElement(view)) {
            return RNSVGHitTester.INSTANCE.hitTest(view, posX, posY);
        }
        float width = view.getWidth();
        float height = view.getHeight();
        float[] fArr = this.hitSlop;
        if (fArr != null) {
            float f2 = fArr[0];
            float f3 = fArr[1];
            float f4 = fArr[2];
            float f5 = fArr[3];
            Companion companion2 = INSTANCE;
            float f6 = companion2.hitSlopSet(f2) ? 0.0f - f2 : 0.0f;
            f = companion2.hitSlopSet(f3) ? 0.0f - f3 : 0.0f;
            if (companion2.hitSlopSet(f4)) {
                width += f4;
            }
            if (companion2.hitSlopSet(f5)) {
                height += f5;
            }
            float f7 = fArr[4];
            float f8 = fArr[5];
            if (companion2.hitSlopSet(f7)) {
                if (!companion2.hitSlopSet(f2)) {
                    f6 = width - f7;
                } else if (!companion2.hitSlopSet(f4)) {
                    width = f7 + f6;
                }
            }
            if (companion2.hitSlopSet(f8)) {
                if (!companion2.hitSlopSet(f3)) {
                    f = height - f8;
                } else if (!companion2.hitSlopSet(f5)) {
                    height = f8 + f;
                }
            }
            f = f;
            f = f6;
        } else {
            f = 0.0f;
        }
        return f <= posX && posX <= width && f <= posY && posY <= height;
    }

    public final void cancel() {
        int i = this.state;
        if (i == 4 || i == 0 || i == 2 || this.isAwaiting) {
            onCancel();
            moveToState(3);
        }
    }

    public final void fail() {
        int i = this.state;
        if (i == 4 || i == 0 || i == 2) {
            onFail();
            moveToState(1);
        }
    }

    public final void activate() {
        activate(false);
    }

    public void activate(boolean force) {
        if (!this.manualActivation || force) {
            int i = this.state;
            if (i == 0 || i == 2) {
                moveToState(4);
            }
        }
    }

    public final void begin() {
        if (this.state == 0) {
            moveToState(2);
        }
    }

    public final void end() {
        int i = this.state;
        if (i == 2 || i == 4) {
            moveToState(5);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x0018, code lost:
    
        r0 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean isDescendantOf(com.swmansion.gesturehandler.core.GestureHandler r4) {
        /*
            r3 = this;
            java.lang.String r0 = "of"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            android.view.View r0 = r3.view
            r1 = 0
            if (r0 == 0) goto Lf
            android.view.ViewParent r0 = r0.getParent()
            goto L10
        Lf:
            r0 = r1
        L10:
            boolean r2 = r0 instanceof android.view.View
            if (r2 == 0) goto L17
            android.view.View r0 = (android.view.View) r0
            goto L18
        L17:
            r0 = r1
        L18:
            if (r0 == 0) goto L2f
            android.view.View r2 = r4.view
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            if (r2 == 0) goto L24
            r4 = 1
            return r4
        L24:
            android.view.ViewParent r0 = r0.getParent()
            boolean r2 = r0 instanceof android.view.View
            if (r2 == 0) goto L17
            android.view.View r0 = (android.view.View) r0
            goto L18
        L2f:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.core.GestureHandler.isDescendantOf(com.swmansion.gesturehandler.core.GestureHandler):boolean");
    }

    protected void onHandle(MotionEvent motionEvent, MotionEvent sourceEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        Intrinsics.checkNotNullParameter(sourceEvent, "sourceEvent");
        moveToState(1);
    }

    private final boolean isButtonInConfig(int clickedButton) {
        int i = this.mouseButton;
        return i == 0 ? clickedButton == 1 : (clickedButton & i) != 0;
    }

    protected final boolean shouldActivateWithMouse(MotionEvent sourceEvent) {
        Intrinsics.checkNotNullParameter(sourceEvent, "sourceEvent");
        if (sourceEvent.getToolType(0) == 3) {
            if (sourceEvent.getAction() == 0 || sourceEvent.getAction() == 1 || sourceEvent.getAction() == 6 || sourceEvent.getAction() == 5 || !(sourceEvent.getAction() == 2 || isButtonInConfig(sourceEvent.getActionButton()))) {
                return false;
            }
            if (sourceEvent.getAction() == 2 && !isButtonInConfig(sourceEvent.getButtonState())) {
                return false;
            }
        }
        return true;
    }

    protected final PointF transformPoint(PointF point) {
        PointF pointFTransformPointToViewCoords;
        Intrinsics.checkNotNullParameter(point, "point");
        GestureHandlerOrchestrator gestureHandlerOrchestrator = this.orchestrator;
        if (gestureHandlerOrchestrator != null && (pointFTransformPointToViewCoords = gestureHandlerOrchestrator.transformPointToViewCoords(this.view, point)) != null) {
            return pointFTransformPointToViewCoords;
        }
        point.x = Float.NaN;
        point.y = Float.NaN;
        return point;
    }

    public final void reset() {
        this.view = null;
        this.orchestrator = null;
        Arrays.fill(this.trackedPointerIDs, -1);
        this.trackedPointersIDsCount = 0;
        this.trackedPointersCount = 0;
        ArraysKt.fill$default(this.trackedPointers, (Object) null, 0, 0, 6, (Object) null);
        this.touchEventType = 0;
        onReset();
    }

    public final void withMarkedAsInBounds(Function0<Unit> closure) {
        Intrinsics.checkNotNullParameter(closure, "closure");
        this.isWithinBounds = true;
        closure.invoke();
        this.isWithinBounds = false;
    }

    private final void setPointerType(MotionEvent motionEvent) {
        int toolType = motionEvent.getToolType(motionEvent.getActionIndex());
        int i = 1;
        if (toolType == 1) {
            i = 0;
        } else if (toolType != 2) {
            i = 3;
            if (toolType == 3) {
                i = 2;
            }
        }
        this.pointerType = i;
    }

    public String toString() {
        String simpleName;
        View view = this.view;
        if (view == null) {
            simpleName = null;
        } else {
            Intrinsics.checkNotNull(view);
            simpleName = view.getClass().getSimpleName();
        }
        return getClass().getSimpleName() + "@[" + this.tag + "]:" + simpleName;
    }

    public final float getLastRelativePositionX() {
        return this.lastAbsolutePositionX;
    }

    public final float getLastRelativePositionY() {
        return this.lastAbsolutePositionY;
    }

    public final float getLastPositionInWindowX() {
        return (this.lastAbsolutePositionX + this.lastEventOffsetX) - this.windowOffset[0];
    }

    public final float getLastPositionInWindowY() {
        return (this.lastAbsolutePositionY + this.lastEventOffsetY) - this.windowOffset[1];
    }

    /* JADX INFO: compiled from: GestureHandler.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 \u001e*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0001\u001eB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\u000e\u001a\u00028\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H$¢\u0006\u0002\u0010\u0011J\u001d\u0010\u000e\u001a\u00028\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\u001d\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u0019H\u0016¢\u0006\u0002\u0010\u001aJ\u001b\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001c2\u0006\u0010\u0017\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u001dR\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u001f"}, d2 = {"Lcom/swmansion/gesturehandler/core/GestureHandler$Factory;", "T", "Lcom/swmansion/gesturehandler/core/GestureHandler;", "", "<init>", "()V", "type", "Ljava/lang/Class;", "getType", "()Ljava/lang/Class;", "name", "", "getName", "()Ljava/lang/String;", "create", "context", "Landroid/content/Context;", "(Landroid/content/Context;)Lcom/swmansion/gesturehandler/core/GestureHandler;", "handlerTag", "", "(Landroid/content/Context;I)Lcom/swmansion/gesturehandler/core/GestureHandler;", "setConfig", "", "handler", "config", "Lcom/facebook/react/bridge/ReadableMap;", "(Lcom/swmansion/gesturehandler/core/GestureHandler;Lcom/facebook/react/bridge/ReadableMap;)V", "createEventBuilder", "Lcom/swmansion/gesturehandler/react/eventbuilders/GestureHandlerEventDataBuilder;", "(Lcom/swmansion/gesturehandler/core/GestureHandler;)Lcom/swmansion/gesturehandler/react/eventbuilders/GestureHandlerEventDataBuilder;", "Companion", "react-native-gesture-handler_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static abstract class Factory<T extends GestureHandler> {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final String KEY_ENABLED = "enabled";
        private static final String KEY_HIT_SLOP = "hitSlop";
        private static final String KEY_HIT_SLOP_BOTTOM = "bottom";
        private static final String KEY_HIT_SLOP_HEIGHT = "height";
        private static final String KEY_HIT_SLOP_HORIZONTAL = "horizontal";
        private static final String KEY_HIT_SLOP_LEFT = "left";
        private static final String KEY_HIT_SLOP_RIGHT = "right";
        private static final String KEY_HIT_SLOP_TOP = "top";
        private static final String KEY_HIT_SLOP_VERTICAL = "vertical";
        private static final String KEY_HIT_SLOP_WIDTH = "width";
        private static final String KEY_MANUAL_ACTIVATION = "manualActivation";
        private static final String KEY_MOUSE_BUTTON = "mouseButton";
        private static final String KEY_NEEDS_POINTER_DATA = "needsPointerData";
        private static final String KEY_SHOULD_CANCEL_WHEN_OUTSIDE = "shouldCancelWhenOutside";

        protected abstract T create(Context context);

        public abstract GestureHandlerEventDataBuilder<T> createEventBuilder(T handler);

        public abstract String getName();

        public abstract Class<T> getType();

        public final T create(Context context, int handlerTag) {
            T t = (T) create(context);
            t.setTag(handlerTag);
            return t;
        }

        public void setConfig(T handler, ReadableMap config) {
            Intrinsics.checkNotNullParameter(handler, "handler");
            Intrinsics.checkNotNullParameter(config, "config");
            handler.resetConfig();
            if (config.hasKey(KEY_SHOULD_CANCEL_WHEN_OUTSIDE)) {
                handler.setShouldCancelWhenOutside(config.getBoolean(KEY_SHOULD_CANCEL_WHEN_OUTSIDE));
            }
            if (config.hasKey("enabled")) {
                handler.setEnabled(config.getBoolean("enabled"));
            }
            if (config.hasKey(KEY_HIT_SLOP)) {
                INSTANCE.handleHitSlopProperty(handler, config);
            }
            if (config.hasKey(KEY_NEEDS_POINTER_DATA)) {
                handler.setNeedsPointerData(config.getBoolean(KEY_NEEDS_POINTER_DATA));
            }
            if (config.hasKey(KEY_MANUAL_ACTIVATION)) {
                ((GestureHandler) handler).manualActivation = config.getBoolean(KEY_MANUAL_ACTIVATION);
            }
            if (config.hasKey(KEY_MOUSE_BUTTON)) {
                handler.setMouseButton(config.getInt(KEY_MOUSE_BUTTON));
            }
        }

        /* JADX INFO: compiled from: GestureHandler.kt */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/swmansion/gesturehandler/core/GestureHandler$Factory$Companion;", "", "<init>", "()V", "KEY_SHOULD_CANCEL_WHEN_OUTSIDE", "", "KEY_ENABLED", "KEY_NEEDS_POINTER_DATA", "KEY_MANUAL_ACTIVATION", "KEY_MOUSE_BUTTON", "KEY_HIT_SLOP", "KEY_HIT_SLOP_LEFT", "KEY_HIT_SLOP_TOP", "KEY_HIT_SLOP_RIGHT", "KEY_HIT_SLOP_BOTTOM", "KEY_HIT_SLOP_VERTICAL", "KEY_HIT_SLOP_HORIZONTAL", "KEY_HIT_SLOP_WIDTH", "KEY_HIT_SLOP_HEIGHT", "handleHitSlopProperty", "", "handler", "Lcom/swmansion/gesturehandler/core/GestureHandler;", "config", "Lcom/facebook/react/bridge/ReadableMap;", "react-native-gesture-handler_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final void handleHitSlopProperty(GestureHandler handler, ReadableMap config) {
                if (config.getType(Factory.KEY_HIT_SLOP) == ReadableType.Number) {
                    float pixelFromDIP = PixelUtil.toPixelFromDIP(config.getDouble(Factory.KEY_HIT_SLOP));
                    handler.setHitSlop(pixelFromDIP, pixelFromDIP, pixelFromDIP, pixelFromDIP, Float.NaN, Float.NaN);
                    return;
                }
                ReadableMap map = config.getMap(Factory.KEY_HIT_SLOP);
                Intrinsics.checkNotNull(map);
                float pixelFromDIP2 = map.hasKey(Factory.KEY_HIT_SLOP_HORIZONTAL) ? PixelUtil.toPixelFromDIP(map.getDouble(Factory.KEY_HIT_SLOP_HORIZONTAL)) : Float.NaN;
                float pixelFromDIP3 = pixelFromDIP2;
                float pixelFromDIP4 = map.hasKey(Factory.KEY_HIT_SLOP_VERTICAL) ? PixelUtil.toPixelFromDIP(map.getDouble(Factory.KEY_HIT_SLOP_VERTICAL)) : Float.NaN;
                float pixelFromDIP5 = pixelFromDIP4;
                if (map.hasKey("left")) {
                    pixelFromDIP2 = PixelUtil.toPixelFromDIP(map.getDouble("left"));
                }
                float f = pixelFromDIP2;
                if (map.hasKey("top")) {
                    pixelFromDIP4 = PixelUtil.toPixelFromDIP(map.getDouble("top"));
                }
                float f2 = pixelFromDIP4;
                if (map.hasKey("right")) {
                    pixelFromDIP3 = PixelUtil.toPixelFromDIP(map.getDouble("right"));
                }
                float f3 = pixelFromDIP3;
                if (map.hasKey("bottom")) {
                    pixelFromDIP5 = PixelUtil.toPixelFromDIP(map.getDouble("bottom"));
                }
                handler.setHitSlop(f, f2, f3, pixelFromDIP5, map.hasKey("width") ? PixelUtil.toPixelFromDIP(map.getDouble("width")) : Float.NaN, map.hasKey("height") ? PixelUtil.toPixelFromDIP(map.getDouble("height")) : Float.NaN);
            }
        }
    }

    /* JADX INFO: compiled from: GestureHandler.kt */
    @Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0014\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\fH\u0002J\u0010\u00104\u001a\u00020\u00052\u0006\u00105\u001a\u00020\u0014H\u0002J\u0010\u00106\u001a\u0004\u0018\u0001072\u0006\u00108\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u0018\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010*0)X\u0082.¢\u0006\u0004\n\u0002\u0010+R\u0018\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010-0)X\u0082.¢\u0006\u0004\n\u0002\u0010.R\u000e\u00102\u001a\u000203X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/swmansion/gesturehandler/core/GestureHandler$Companion;", "", "<init>", "()V", "DEFAULT_NEEDS_POINTER_DATA", "", "DEFAULT_MANUAL_ACTIVATION", "DEFAULT_SHOULD_CANCEL_WHEN_OUTSIDE", "DEFAULT_IS_ENABLED", "DEFAULT_HIT_SLOP", "", "DEFAULT_MOUSE_BUTTON", "", "STATE_UNDETERMINED", "STATE_FAILED", "STATE_BEGAN", "STATE_CANCELLED", "STATE_ACTIVE", "STATE_END", "HIT_SLOP_NONE", "", "HIT_SLOP_LEFT_IDX", "HIT_SLOP_TOP_IDX", "HIT_SLOP_RIGHT_IDX", "HIT_SLOP_BOTTOM_IDX", "HIT_SLOP_WIDTH_IDX", "HIT_SLOP_HEIGHT_IDX", "DIRECTION_RIGHT", "DIRECTION_LEFT", "DIRECTION_UP", "DIRECTION_DOWN", "ACTION_TYPE_REANIMATED_WORKLET", "ACTION_TYPE_NATIVE_ANIMATED_EVENT", "ACTION_TYPE_JS_FUNCTION_OLD_API", "ACTION_TYPE_JS_FUNCTION_NEW_API", "POINTER_TYPE_TOUCH", "POINTER_TYPE_STYLUS", "POINTER_TYPE_MOUSE", "POINTER_TYPE_OTHER", "MAX_POINTERS_COUNT", "pointerProps", "", "Landroid/view/MotionEvent$PointerProperties;", "[Landroid/view/MotionEvent$PointerProperties;", "pointerCoords", "Landroid/view/MotionEvent$PointerCoords;", "[Landroid/view/MotionEvent$PointerCoords;", "initPointerProps", "", RRWebVideoEvent.JsonKeys.SIZE, "nextEventCoalescingKey", "", "hitSlopSet", "value", "stateToString", "", SentryThread.JsonKeys.STATE, "react-native-gesture-handler_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void initPointerProps(int i) {
            if (GestureHandler.pointerProps == null) {
                GestureHandler.pointerProps = new MotionEvent.PointerProperties[12];
                GestureHandler.pointerCoords = new MotionEvent.PointerCoords[12];
            }
            while (i > 0) {
                MotionEvent.PointerProperties[] pointerPropertiesArr = GestureHandler.pointerProps;
                MotionEvent.PointerCoords[] pointerCoordsArr = null;
                if (pointerPropertiesArr == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("pointerProps");
                    pointerPropertiesArr = null;
                }
                int i2 = i - 1;
                if (pointerPropertiesArr[i2] != null) {
                    return;
                }
                MotionEvent.PointerProperties[] pointerPropertiesArr2 = GestureHandler.pointerProps;
                if (pointerPropertiesArr2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("pointerProps");
                    pointerPropertiesArr2 = null;
                }
                pointerPropertiesArr2[i2] = new MotionEvent.PointerProperties();
                MotionEvent.PointerCoords[] pointerCoordsArr2 = GestureHandler.pointerCoords;
                if (pointerCoordsArr2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("pointerCoords");
                } else {
                    pointerCoordsArr = pointerCoordsArr2;
                }
                pointerCoordsArr[i2] = new MotionEvent.PointerCoords();
                i--;
            }
        }

        public final boolean hitSlopSet(float value) {
            return !Float.isNaN(value);
        }

        public final String stateToString(int i) {
            if (i == 0) {
                return "UNDETERMINED";
            }
            if (i == 1) {
                return "FAILED";
            }
            if (i == 2) {
                return "BEGIN";
            }
            if (i == 3) {
                return "CANCELLED";
            }
            if (i == 4) {
                return "ACTIVE";
            }
            if (i != 5) {
                return null;
            }
            return "END";
        }
    }

    /* JADX INFO: compiled from: GestureHandler.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J;\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\u0003HÖ\u0001J\t\u0010!\u001a\u00020\"HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010R\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010¨\u0006#"}, d2 = {"Lcom/swmansion/gesturehandler/core/GestureHandler$PointerData;", "", "pointerId", "", "x", "", "y", "absoluteX", "absoluteY", "<init>", "(IFFFF)V", "getPointerId", "()I", "getX", "()F", "setX", "(F)V", "getY", "setY", "getAbsoluteX", "setAbsoluteX", "getAbsoluteY", "setAbsoluteY", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "", "react-native-gesture-handler_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final /* data */ class PointerData {
        private float absoluteX;
        private float absoluteY;
        private final int pointerId;
        private float x;
        private float y;

        public static /* synthetic */ PointerData copy$default(PointerData pointerData, int i, float f, float f2, float f3, float f4, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = pointerData.pointerId;
            }
            if ((i2 & 2) != 0) {
                f = pointerData.x;
            }
            if ((i2 & 4) != 0) {
                f2 = pointerData.y;
            }
            if ((i2 & 8) != 0) {
                f3 = pointerData.absoluteX;
            }
            if ((i2 & 16) != 0) {
                f4 = pointerData.absoluteY;
            }
            float f5 = f4;
            float f6 = f2;
            return pointerData.copy(i, f, f6, f3, f5);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getPointerId() {
            return this.pointerId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final float getX() {
            return this.x;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final float getY() {
            return this.y;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final float getAbsoluteX() {
            return this.absoluteX;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final float getAbsoluteY() {
            return this.absoluteY;
        }

        public final PointerData copy(int pointerId, float x, float y, float absoluteX, float absoluteY) {
            return new PointerData(pointerId, x, y, absoluteX, absoluteY);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PointerData)) {
                return false;
            }
            PointerData pointerData = (PointerData) other;
            return this.pointerId == pointerData.pointerId && Float.compare(this.x, pointerData.x) == 0 && Float.compare(this.y, pointerData.y) == 0 && Float.compare(this.absoluteX, pointerData.absoluteX) == 0 && Float.compare(this.absoluteY, pointerData.absoluteY) == 0;
        }

        public int hashCode() {
            return (((((((Integer.hashCode(this.pointerId) * 31) + Float.hashCode(this.x)) * 31) + Float.hashCode(this.y)) * 31) + Float.hashCode(this.absoluteX)) * 31) + Float.hashCode(this.absoluteY);
        }

        public String toString() {
            return "PointerData(pointerId=" + this.pointerId + ", x=" + this.x + ", y=" + this.y + ", absoluteX=" + this.absoluteX + ", absoluteY=" + this.absoluteY + ")";
        }

        public PointerData(int i, float f, float f2, float f3, float f4) {
            this.pointerId = i;
            this.x = f;
            this.y = f2;
            this.absoluteX = f3;
            this.absoluteY = f4;
        }

        public final int getPointerId() {
            return this.pointerId;
        }

        public final float getX() {
            return this.x;
        }

        public final void setX(float f) {
            this.x = f;
        }

        public final float getY() {
            return this.y;
        }

        public final void setY(float f) {
            this.y = f;
        }

        public final float getAbsoluteX() {
            return this.absoluteX;
        }

        public final void setAbsoluteX(float f) {
            this.absoluteX = f;
        }

        public final float getAbsoluteY() {
            return this.absoluteY;
        }

        public final void setAbsoluteY(float f) {
            this.absoluteY = f;
        }
    }
}
