package com.facebook.react.animated;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import com.facebook.fbreact.specs.NativeAnimatedModuleSpec;
import com.facebook.react.animated.NativeAnimatedModule;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableArrayBuilder;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapBuilder;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UIManagerListener;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.common.build.ReactBuildConfig;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.uimanager.GuardedFrameCallback;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.common.ViewUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NativeAnimatedModule.kt */
/* JADX INFO: loaded from: classes.dex */
@ReactModule(name = "NativeAnimatedModule")
@Metadata(d1 = {"\u0000\u008d\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\b\n\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005*\u00017\b\u0007\u0018\u0000 o2\u00020\u00012\u00020\u00022\u00020\u0003:\u0004lmnoB\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0018J\b\u0010 \u001a\u00020\u001eH\u0016J\b\u0010!\u001a\u00020\u001eH\u0016J\u0014\u0010\"\u001a\u00020\u001e2\n\u0010#\u001a\u00060$R\u00020\u0000H\u0002J\u0014\u0010%\u001a\u00020\u001e2\n\u0010#\u001a\u00060$R\u00020\u0000H\u0002J\u0014\u0010&\u001a\u00020\u001e2\n\u0010#\u001a\u00060$R\u00020\u0000H\u0002J\u0010\u0010'\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020)H\u0016J\u0010\u0010*\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020)H\u0016J\u0010\u0010+\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020)H\u0016J\u0010\u0010,\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020)H\u0017J\u0010\u0010-\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020)H\u0017J\b\u0010.\u001a\u00020\u001eH\u0016J\b\u0010/\u001a\u00020\u001eH\u0016J\b\u00109\u001a\u00020\u001eH\u0002J\b\u0010:\u001a\u00020\u001eH\u0002J\u0010\u0010;\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0018H\u0002J\u0010\u0010<\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0018H\u0002J\b\u0010=\u001a\u00020\u001eH\u0016J\b\u0010>\u001a\u00020\u001eH\u0016J\u0018\u0010?\u001a\u00020\u001e2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020CH\u0016J\u0018\u0010D\u001a\u00020\u001e2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020CH\u0016J\u0010\u0010E\u001a\u00020\u001e2\u0006\u0010@\u001a\u00020AH\u0016J\u0010\u0010F\u001a\u00020\u001e2\u0006\u0010@\u001a\u00020AH\u0016J\u0010\u0010G\u001a\u00020\u001e2\u0006\u0010@\u001a\u00020AH\u0016J\u0018\u0010H\u001a\u00020\u001e2\u0006\u0010@\u001a\u00020A2\u0006\u0010I\u001a\u00020AH\u0016J\u0018\u0010J\u001a\u00020\u001e2\u0006\u0010@\u001a\u00020A2\u0006\u0010I\u001a\u00020AH\u0016J\u0010\u0010K\u001a\u00020\u001e2\u0006\u0010@\u001a\u00020AH\u0016J\u0010\u0010L\u001a\u00020\u001e2\u0006\u0010@\u001a\u00020AH\u0016J(\u0010M\u001a\u00020\u001e2\u0006\u0010N\u001a\u00020A2\u0006\u0010O\u001a\u00020A2\u0006\u0010P\u001a\u00020C2\u0006\u0010Q\u001a\u00020RH\u0016J\u0010\u0010S\u001a\u00020\u001e2\u0006\u0010N\u001a\u00020AH\u0016J\u0018\u0010T\u001a\u00020\u001e2\u0006\u0010U\u001a\u00020A2\u0006\u0010V\u001a\u00020AH\u0016J\u0018\u0010W\u001a\u00020\u001e2\u0006\u0010U\u001a\u00020A2\u0006\u0010V\u001a\u00020AH\u0016J\u0018\u0010X\u001a\u00020\u001e2\u0006\u0010O\u001a\u00020A2\u0006\u0010Y\u001a\u00020AH\u0016J\u0018\u0010Z\u001a\u00020\u001e2\u0006\u0010O\u001a\u00020A2\u0006\u0010Y\u001a\u00020AH\u0016J\u0010\u0010[\u001a\u00020\u001e2\u0006\u0010O\u001a\u00020AH\u0016J \u0010\\\u001a\u00020\u001e2\u0006\u0010Y\u001a\u00020A2\u0006\u0010]\u001a\u00020^2\u0006\u0010_\u001a\u00020CH\u0016J \u0010`\u001a\u00020\u001e2\u0006\u0010Y\u001a\u00020A2\u0006\u0010]\u001a\u00020^2\u0006\u0010a\u001a\u00020AH\u0016J\u0010\u0010b\u001a\u00020\u001e2\u0006\u0010]\u001a\u00020^H\u0016J\u0010\u0010c\u001a\u00020\u001e2\u0006\u0010d\u001a\u00020AH\u0016J\u0018\u0010e\u001a\u00020\u001e2\u0006\u0010f\u001a\u00020A2\u0006\u0010g\u001a\u00020RH\u0016J\b\u0010h\u001a\u00020\u001eH\u0016J\u0010\u0010i\u001a\u00020\u001e2\u0006\u0010j\u001a\u00020kH\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00060\u000bR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00060\u000bR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R(\u00100\u001a\u0004\u0018\u00010\u000f2\b\u00100\u001a\u0004\u0018\u00010\u000f8F@GX\u0086\u000e¢\u0006\f\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u000e\u00105\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00106\u001a\u000207X\u0082\u0004¢\u0006\u0004\n\u0002\u00108¨\u0006p"}, d2 = {"Lcom/facebook/react/animated/NativeAnimatedModule;", "Lcom/facebook/fbreact/specs/NativeAnimatedModuleSpec;", "Lcom/facebook/react/bridge/LifecycleEventListener;", "Lcom/facebook/react/bridge/UIManagerListener;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "<init>", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "reactChoreographer", "Lcom/facebook/react/modules/core/ReactChoreographer;", "operations", "Lcom/facebook/react/animated/NativeAnimatedModule$ConcurrentOperationQueue;", "preOperations", "nodesManagerRef", "Ljava/util/concurrent/atomic/AtomicReference;", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "batchingControlledByJS", "", "currentFrameNumber", "", "currentBatchNumber", "initializedForFabric", "initializedForNonFabric", "uiManagerType", "", "getUiManagerType$annotations", "()V", "numFabricAnimations", "numNonFabricAnimations", "userDrivenScrollEnded", "", "viewTag", "initialize", "onHostResume", "addOperation", "operation", "Lcom/facebook/react/animated/NativeAnimatedModule$UIThreadOperation;", "addUnbatchedOperation", "addPreOperation", "didScheduleMountItems", "uiManager", "Lcom/facebook/react/bridge/UIManager;", "willMountItems", "didMountItems", "didDispatchMountItems", "willDispatchViewUpdates", "onHostPause", "onHostDestroy", "nodesManager", "getNodesManager", "()Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "setNodesManager", "(Lcom/facebook/react/animated/NativeAnimatedNodesManager;)V", "enqueuedAnimationOnFrame", "animatedFrameCallback", "com/facebook/react/animated/NativeAnimatedModule$animatedFrameCallback$1", "Lcom/facebook/react/animated/NativeAnimatedModule$animatedFrameCallback$1;", "clearFrameCallback", "enqueueFrameCallback", "initializeLifecycleEventListenersForViewTag", "decrementInFlightAnimationsForViewTag", "startOperationBatch", "finishOperationBatch", "createAnimatedNode", "tagDouble", "", "config", "Lcom/facebook/react/bridge/ReadableMap;", "updateAnimatedNodeConfig", "startListeningToAnimatedNodeValue", "stopListeningToAnimatedNodeValue", "dropAnimatedNode", "setAnimatedNodeValue", "value", "setAnimatedNodeOffset", "flattenAnimatedNodeOffset", "extractAnimatedNodeOffset", "startAnimatingNode", "animationIdDouble", "animatedNodeTagDouble", "animationConfig", "endCallback", "Lcom/facebook/react/bridge/Callback;", "stopAnimation", "connectAnimatedNodes", "parentNodeTagDouble", "childNodeTagDouble", "disconnectAnimatedNodes", "connectAnimatedNodeToView", "viewTagDouble", "disconnectAnimatedNodeFromView", "restoreDefaultValues", "addAnimatedEventToView", "eventName", "", "eventMapping", "removeAnimatedEventFromView", "animatedValueTagDouble", "addListener", "removeListeners", "count", "getValue", "animatedValueNodeTagDouble", "callback", "invalidate", "queueAndExecuteBatchedOperations", "opsAndArgs", "Lcom/facebook/react/bridge/ReadableArray;", "BatchExecutionOpCodes", "UIThreadOperation", "ConcurrentOperationQueue", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class NativeAnimatedModule extends NativeAnimatedModuleSpec implements LifecycleEventListener, UIManagerListener {
    public static final boolean ANIMATED_MODULE_DEBUG = false;
    public static final String NAME = "NativeAnimatedModule";
    private final NativeAnimatedModule$animatedFrameCallback$1 animatedFrameCallback;
    private boolean batchingControlledByJS;
    private volatile long currentBatchNumber;
    private volatile long currentFrameNumber;
    private boolean enqueuedAnimationOnFrame;
    private boolean initializedForFabric;
    private boolean initializedForNonFabric;
    private final AtomicReference<NativeAnimatedNodesManager> nodesManagerRef;
    private int numFabricAnimations;
    private int numNonFabricAnimations;
    private final ConcurrentOperationQueue operations;
    private final ConcurrentOperationQueue preOperations;
    private final ReactChoreographer reactChoreographer;
    private int uiManagerType;

    /* JADX INFO: compiled from: NativeAnimatedModule.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BatchExecutionOpCodes.values().length];
            try {
                iArr[BatchExecutionOpCodes.OP_CODE_GET_VALUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[BatchExecutionOpCodes.OP_START_LISTENING_TO_ANIMATED_NODE_VALUE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[BatchExecutionOpCodes.OP_STOP_LISTENING_TO_ANIMATED_NODE_VALUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[BatchExecutionOpCodes.OP_CODE_STOP_ANIMATION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[BatchExecutionOpCodes.OP_CODE_FLATTEN_ANIMATED_NODE_OFFSET.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[BatchExecutionOpCodes.OP_CODE_EXTRACT_ANIMATED_NODE_OFFSET.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[BatchExecutionOpCodes.OP_CODE_RESTORE_DEFAULT_VALUES.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[BatchExecutionOpCodes.OP_CODE_DROP_ANIMATED_NODE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[BatchExecutionOpCodes.OP_CODE_ADD_LISTENER.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[BatchExecutionOpCodes.OP_CODE_REMOVE_LISTENERS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[BatchExecutionOpCodes.OP_CODE_CREATE_ANIMATED_NODE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[BatchExecutionOpCodes.OP_CODE_UPDATE_ANIMATED_NODE_CONFIG.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[BatchExecutionOpCodes.OP_CODE_CONNECT_ANIMATED_NODES.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[BatchExecutionOpCodes.OP_CODE_DISCONNECT_ANIMATED_NODES.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[BatchExecutionOpCodes.OP_CODE_SET_ANIMATED_NODE_VALUE.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[BatchExecutionOpCodes.OP_CODE_SET_ANIMATED_NODE_OFFSET.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[BatchExecutionOpCodes.OP_CODE_DISCONNECT_ANIMATED_NODE_FROM_VIEW.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr[BatchExecutionOpCodes.OP_CODE_START_ANIMATING_NODE.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr[BatchExecutionOpCodes.OP_CODE_REMOVE_ANIMATED_EVENT_FROM_VIEW.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr[BatchExecutionOpCodes.OP_CODE_CONNECT_ANIMATED_NODE_TO_VIEW.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr[BatchExecutionOpCodes.OP_CODE_ADD_ANIMATED_EVENT_TO_VIEW.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private static /* synthetic */ void getUiManagerType$annotations() {
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void addListener(String eventName) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
    }

    @Override // com.facebook.react.bridge.UIManagerListener
    public void didMountItems(UIManager uiManager) {
        Intrinsics.checkNotNullParameter(uiManager, "uiManager");
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void removeListeners(double count) {
    }

    @Override // com.facebook.react.bridge.UIManagerListener
    public void willMountItems(UIManager uiManager) {
        Intrinsics.checkNotNullParameter(uiManager, "uiManager");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r0v7, types: [com.facebook.react.animated.NativeAnimatedModule$animatedFrameCallback$1] */
    public NativeAnimatedModule(final ReactApplicationContext reactContext) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.reactChoreographer = ReactChoreographer.INSTANCE.getInstance();
        this.operations = new ConcurrentOperationQueue();
        this.preOperations = new ConcurrentOperationQueue();
        this.nodesManagerRef = new AtomicReference<>();
        this.uiManagerType = 1;
        this.animatedFrameCallback = new GuardedFrameCallback(reactContext, this) { // from class: com.facebook.react.animated.NativeAnimatedModule$animatedFrameCallback$1
            final /* synthetic */ NativeAnimatedModule this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(reactContext);
                this.this$0 = this;
            }

            @Override // com.facebook.react.uimanager.GuardedFrameCallback
            protected void doFrameGuarded(long frameTimeNanos) {
                try {
                    this.this$0.enqueuedAnimationOnFrame = false;
                    NativeAnimatedNodesManager nodesManager = this.this$0.getNodesManager();
                    if (nodesManager == null) {
                        return;
                    }
                    if (nodesManager.hasActiveAnimations()) {
                        nodesManager.runUpdates(frameTimeNanos);
                    }
                    this.this$0.enqueueFrameCallback();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    /* JADX INFO: compiled from: NativeAnimatedModule.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0019\b\u0082\u0081\u0002\u0018\u0000 \u001b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001bB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001a¨\u0006\u001c"}, d2 = {"Lcom/facebook/react/animated/NativeAnimatedModule$BatchExecutionOpCodes;", "", "value", "", "<init>", "(Ljava/lang/String;II)V", "OP_CODE_CREATE_ANIMATED_NODE", "OP_CODE_UPDATE_ANIMATED_NODE_CONFIG", "OP_CODE_GET_VALUE", "OP_START_LISTENING_TO_ANIMATED_NODE_VALUE", "OP_STOP_LISTENING_TO_ANIMATED_NODE_VALUE", "OP_CODE_CONNECT_ANIMATED_NODES", "OP_CODE_DISCONNECT_ANIMATED_NODES", "OP_CODE_START_ANIMATING_NODE", "OP_CODE_STOP_ANIMATION", "OP_CODE_SET_ANIMATED_NODE_VALUE", "OP_CODE_SET_ANIMATED_NODE_OFFSET", "OP_CODE_FLATTEN_ANIMATED_NODE_OFFSET", "OP_CODE_EXTRACT_ANIMATED_NODE_OFFSET", "OP_CODE_CONNECT_ANIMATED_NODE_TO_VIEW", "OP_CODE_DISCONNECT_ANIMATED_NODE_FROM_VIEW", "OP_CODE_RESTORE_DEFAULT_VALUES", "OP_CODE_DROP_ANIMATED_NODE", "OP_CODE_ADD_ANIMATED_EVENT_TO_VIEW", "OP_CODE_REMOVE_ANIMATED_EVENT_FROM_VIEW", "OP_CODE_ADD_LISTENER", "OP_CODE_REMOVE_LISTENERS", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class BatchExecutionOpCodes extends Enum<BatchExecutionOpCodes> {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ BatchExecutionOpCodes[] $VALUES;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE;
        private static BatchExecutionOpCodes[] valueMap;
        public static final BatchExecutionOpCodes OP_CODE_CREATE_ANIMATED_NODE = new BatchExecutionOpCodes("OP_CODE_CREATE_ANIMATED_NODE", 0, 1);
        public static final BatchExecutionOpCodes OP_CODE_UPDATE_ANIMATED_NODE_CONFIG = new BatchExecutionOpCodes("OP_CODE_UPDATE_ANIMATED_NODE_CONFIG", 1, 2);
        public static final BatchExecutionOpCodes OP_CODE_GET_VALUE = new BatchExecutionOpCodes("OP_CODE_GET_VALUE", 2, 3);
        public static final BatchExecutionOpCodes OP_START_LISTENING_TO_ANIMATED_NODE_VALUE = new BatchExecutionOpCodes("OP_START_LISTENING_TO_ANIMATED_NODE_VALUE", 3, 4);
        public static final BatchExecutionOpCodes OP_STOP_LISTENING_TO_ANIMATED_NODE_VALUE = new BatchExecutionOpCodes("OP_STOP_LISTENING_TO_ANIMATED_NODE_VALUE", 4, 5);
        public static final BatchExecutionOpCodes OP_CODE_CONNECT_ANIMATED_NODES = new BatchExecutionOpCodes("OP_CODE_CONNECT_ANIMATED_NODES", 5, 6);
        public static final BatchExecutionOpCodes OP_CODE_DISCONNECT_ANIMATED_NODES = new BatchExecutionOpCodes("OP_CODE_DISCONNECT_ANIMATED_NODES", 6, 7);
        public static final BatchExecutionOpCodes OP_CODE_START_ANIMATING_NODE = new BatchExecutionOpCodes("OP_CODE_START_ANIMATING_NODE", 7, 8);
        public static final BatchExecutionOpCodes OP_CODE_STOP_ANIMATION = new BatchExecutionOpCodes("OP_CODE_STOP_ANIMATION", 8, 9);
        public static final BatchExecutionOpCodes OP_CODE_SET_ANIMATED_NODE_VALUE = new BatchExecutionOpCodes("OP_CODE_SET_ANIMATED_NODE_VALUE", 9, 10);
        public static final BatchExecutionOpCodes OP_CODE_SET_ANIMATED_NODE_OFFSET = new BatchExecutionOpCodes("OP_CODE_SET_ANIMATED_NODE_OFFSET", 10, 11);
        public static final BatchExecutionOpCodes OP_CODE_FLATTEN_ANIMATED_NODE_OFFSET = new BatchExecutionOpCodes("OP_CODE_FLATTEN_ANIMATED_NODE_OFFSET", 11, 12);
        public static final BatchExecutionOpCodes OP_CODE_EXTRACT_ANIMATED_NODE_OFFSET = new BatchExecutionOpCodes("OP_CODE_EXTRACT_ANIMATED_NODE_OFFSET", 12, 13);
        public static final BatchExecutionOpCodes OP_CODE_CONNECT_ANIMATED_NODE_TO_VIEW = new BatchExecutionOpCodes("OP_CODE_CONNECT_ANIMATED_NODE_TO_VIEW", 13, 14);
        public static final BatchExecutionOpCodes OP_CODE_DISCONNECT_ANIMATED_NODE_FROM_VIEW = new BatchExecutionOpCodes("OP_CODE_DISCONNECT_ANIMATED_NODE_FROM_VIEW", 14, 15);
        public static final BatchExecutionOpCodes OP_CODE_RESTORE_DEFAULT_VALUES = new BatchExecutionOpCodes("OP_CODE_RESTORE_DEFAULT_VALUES", 15, 16);
        public static final BatchExecutionOpCodes OP_CODE_DROP_ANIMATED_NODE = new BatchExecutionOpCodes("OP_CODE_DROP_ANIMATED_NODE", 16, 17);
        public static final BatchExecutionOpCodes OP_CODE_ADD_ANIMATED_EVENT_TO_VIEW = new BatchExecutionOpCodes("OP_CODE_ADD_ANIMATED_EVENT_TO_VIEW", 17, 18);
        public static final BatchExecutionOpCodes OP_CODE_REMOVE_ANIMATED_EVENT_FROM_VIEW = new BatchExecutionOpCodes("OP_CODE_REMOVE_ANIMATED_EVENT_FROM_VIEW", 18, 19);
        public static final BatchExecutionOpCodes OP_CODE_ADD_LISTENER = new BatchExecutionOpCodes("OP_CODE_ADD_LISTENER", 19, 20);
        public static final BatchExecutionOpCodes OP_CODE_REMOVE_LISTENERS = new BatchExecutionOpCodes("OP_CODE_REMOVE_LISTENERS", 20, 21);

        private static final /* synthetic */ BatchExecutionOpCodes[] $values() {
            return new BatchExecutionOpCodes[]{OP_CODE_CREATE_ANIMATED_NODE, OP_CODE_UPDATE_ANIMATED_NODE_CONFIG, OP_CODE_GET_VALUE, OP_START_LISTENING_TO_ANIMATED_NODE_VALUE, OP_STOP_LISTENING_TO_ANIMATED_NODE_VALUE, OP_CODE_CONNECT_ANIMATED_NODES, OP_CODE_DISCONNECT_ANIMATED_NODES, OP_CODE_START_ANIMATING_NODE, OP_CODE_STOP_ANIMATION, OP_CODE_SET_ANIMATED_NODE_VALUE, OP_CODE_SET_ANIMATED_NODE_OFFSET, OP_CODE_FLATTEN_ANIMATED_NODE_OFFSET, OP_CODE_EXTRACT_ANIMATED_NODE_OFFSET, OP_CODE_CONNECT_ANIMATED_NODE_TO_VIEW, OP_CODE_DISCONNECT_ANIMATED_NODE_FROM_VIEW, OP_CODE_RESTORE_DEFAULT_VALUES, OP_CODE_DROP_ANIMATED_NODE, OP_CODE_ADD_ANIMATED_EVENT_TO_VIEW, OP_CODE_REMOVE_ANIMATED_EVENT_FROM_VIEW, OP_CODE_ADD_LISTENER, OP_CODE_REMOVE_LISTENERS};
        }

        @JvmStatic
        public static final BatchExecutionOpCodes fromId(int i) {
            return INSTANCE.fromId(i);
        }

        public static EnumEntries<BatchExecutionOpCodes> getEntries() {
            return $ENTRIES;
        }

        private BatchExecutionOpCodes(String str, int i, int i2) {
            super(str, i);
        }

        static {
            BatchExecutionOpCodes[] batchExecutionOpCodesArr$values = $values();
            $VALUES = batchExecutionOpCodesArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(batchExecutionOpCodesArr$values);
            INSTANCE = new Companion(null);
        }

        /* JADX INFO: compiled from: NativeAnimatedModule.kt */
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0007R\u0018\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u000b"}, d2 = {"Lcom/facebook/react/animated/NativeAnimatedModule$BatchExecutionOpCodes$Companion;", "", "<init>", "()V", "valueMap", "", "Lcom/facebook/react/animated/NativeAnimatedModule$BatchExecutionOpCodes;", "[Lcom/facebook/react/animated/NativeAnimatedModule$BatchExecutionOpCodes;", "fromId", "id", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            @JvmStatic
            public final BatchExecutionOpCodes fromId(int id) {
                BatchExecutionOpCodes[] batchExecutionOpCodesArrValues = BatchExecutionOpCodes.valueMap;
                if (batchExecutionOpCodesArrValues == null) {
                    batchExecutionOpCodesArrValues = BatchExecutionOpCodes.values();
                }
                if (BatchExecutionOpCodes.valueMap == null) {
                    BatchExecutionOpCodes.valueMap = batchExecutionOpCodesArrValues;
                }
                return batchExecutionOpCodesArrValues[id - 1];
            }
        }

        public static BatchExecutionOpCodes valueOf(String str) {
            return (BatchExecutionOpCodes) Enum.valueOf(BatchExecutionOpCodes.class, str);
        }

        public static BatchExecutionOpCodes[] values() {
            return (BatchExecutionOpCodes[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: NativeAnimatedModule.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\b¢\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/facebook/react/animated/NativeAnimatedModule$UIThreadOperation;", "", "<init>", "(Lcom/facebook/react/animated/NativeAnimatedModule;)V", "execute", "", "animatedNodesManager", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "batchNumber", "", "getBatchNumber", "()J", "setBatchNumber", "(J)V", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private abstract class UIThreadOperation {
        private long batchNumber = -1;

        public abstract void execute(NativeAnimatedNodesManager animatedNodesManager);

        public UIThreadOperation() {
        }

        public final long getBatchNumber() {
            return this.batchNumber;
        }

        public final void setBatchNumber(long j) {
            this.batchNumber = j;
        }
    }

    /* JADX INFO: compiled from: NativeAnimatedModule.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u00060\u0006R\u00020\u0007H\u0007J\u001a\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0007J\u001c\u0010\u0014\u001a\u000e\u0012\b\u0012\u00060\u0006R\u00020\u0007\u0018\u00010\u00152\u0006\u0010\u0010\u001a\u00020\u0011H\u0007R\u0018\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0006R\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0018\u00010\u0006R\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n8G¢\u0006\u0006\u001a\u0004\b\t\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/facebook/react/animated/NativeAnimatedModule$ConcurrentOperationQueue;", "", "<init>", "(Lcom/facebook/react/animated/NativeAnimatedModule;)V", "queue", "Ljava/util/Queue;", "Lcom/facebook/react/animated/NativeAnimatedModule$UIThreadOperation;", "Lcom/facebook/react/animated/NativeAnimatedModule;", "peekedOperation", "isEmpty", "", "()Z", "add", "", "operation", "executeBatch", "maxBatchNumber", "", "nodesManager", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "drainQueueIntoList", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private final class ConcurrentOperationQueue {
        private UIThreadOperation peekedOperation;
        private final Queue<UIThreadOperation> queue = new ConcurrentLinkedQueue();

        public ConcurrentOperationQueue() {
        }

        public final boolean isEmpty() {
            return this.queue.isEmpty() && this.peekedOperation == null;
        }

        public final void add(UIThreadOperation operation) {
            Intrinsics.checkNotNullParameter(operation, "operation");
            this.queue.add(operation);
        }

        public final void executeBatch(long maxBatchNumber, NativeAnimatedNodesManager nodesManager) {
            List<UIThreadOperation> listDrainQueueIntoList = drainQueueIntoList(maxBatchNumber);
            if (listDrainQueueIntoList != null) {
                for (UIThreadOperation uIThreadOperation : listDrainQueueIntoList) {
                    if (nodesManager == null) {
                        throw new IllegalStateException("Required value was null.".toString());
                    }
                    uIThreadOperation.execute(nodesManager);
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:37:0x002b, code lost:
        
            return r0;
         */
        /* JADX WARN: Removed duplicated region for block: B:38:0x002c  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x002b A[EDGE_INSN: B:44:0x002b->B:37:0x002b BREAK  A[LOOP:0: B:29:0x000f->B:42:0x0037], SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.util.List<com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation> drainQueueIntoList(long r6) {
            /*
                r5 = this;
                boolean r0 = r5.isEmpty()
                r1 = 0
                if (r0 == 0) goto L8
                return r1
            L8:
                java.util.ArrayList r0 = new java.util.ArrayList
                r0.<init>()
                java.util.List r0 = (java.util.List) r0
            Lf:
                com.facebook.react.animated.NativeAnimatedModule$UIThreadOperation r2 = r5.peekedOperation
                if (r2 == 0) goto L21
                long r3 = r2.getBatchNumber()
                int r3 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
                if (r3 <= 0) goto L1c
                goto L2b
            L1c:
                r0.add(r2)
                r5.peekedOperation = r1
            L21:
                java.util.Queue<com.facebook.react.animated.NativeAnimatedModule$UIThreadOperation> r2 = r5.queue
                java.lang.Object r2 = r2.poll()
                com.facebook.react.animated.NativeAnimatedModule$UIThreadOperation r2 = (com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation) r2
                if (r2 != 0) goto L2c
            L2b:
                return r0
            L2c:
                long r3 = r2.getBatchNumber()
                int r3 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
                if (r3 <= 0) goto L37
                r5.peekedOperation = r2
                return r0
            L37:
                r0.add(r2)
                goto Lf
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.animated.NativeAnimatedModule.ConcurrentOperationQueue.drainQueueIntoList(long):java.util.List");
        }
    }

    public final void userDrivenScrollEnded(int viewTag) {
        NativeAnimatedNodesManager nativeAnimatedNodesManager = this.nodesManagerRef.get();
        if (nativeAnimatedNodesManager == null) {
            return;
        }
        final Set<Integer> tagsOfConnectedNodes$ReactAndroid_release = nativeAnimatedNodesManager.getTagsOfConnectedNodes$ReactAndroid_release(viewTag, "topScrollEnded");
        if (tagsOfConnectedNodes$ReactAndroid_release.isEmpty()) {
            return;
        }
        WritableMap writableMapCreateMap = Arguments.createMap();
        new ReadableMapBuilder(writableMapCreateMap).putArray("tags", new Function1() { // from class: com.facebook.react.animated.NativeAnimatedModule$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NativeAnimatedModule.userDrivenScrollEnded$lambda$2$lambda$1(tagsOfConnectedNodes$ReactAndroid_release, (ReadableArrayBuilder) obj);
            }
        });
        WritableMap writableMap = writableMapCreateMap;
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn != null) {
            reactApplicationContextIfActiveOrWarn.emitDeviceEvent("onUserDrivenAnimationEnded", writableMap);
        }
    }

    public static final Unit userDrivenScrollEnded$lambda$2$lambda$1(Set set, ReadableArrayBuilder putArray) {
        Intrinsics.checkNotNullParameter(putArray, "$this$putArray");
        Iterator it = set.iterator();
        while (it.hasNext()) {
            putArray.add(((Number) it.next()).intValue());
        }
        return Unit.INSTANCE;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void initialize() {
        super.initialize();
        getReactApplicationContext().addLifecycleEventListener(this);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        enqueueFrameCallback();
    }

    private final void addOperation(UIThreadOperation operation) {
        operation.setBatchNumber(this.currentBatchNumber);
        this.operations.add(operation);
    }

    private final void addUnbatchedOperation(UIThreadOperation operation) {
        operation.setBatchNumber(-1L);
        this.operations.add(operation);
    }

    private final void addPreOperation(UIThreadOperation operation) {
        operation.setBatchNumber(this.currentBatchNumber);
        this.preOperations.add(operation);
    }

    @Override // com.facebook.react.bridge.UIManagerListener
    public void didScheduleMountItems(UIManager uiManager) {
        Intrinsics.checkNotNullParameter(uiManager, "uiManager");
        this.currentFrameNumber++;
    }

    @Override // com.facebook.react.bridge.UIManagerListener
    public void didDispatchMountItems(UIManager uiManager) {
        Intrinsics.checkNotNullParameter(uiManager, "uiManager");
        if (this.uiManagerType != 2) {
            return;
        }
        long j = this.currentBatchNumber - 1;
        if (!this.batchingControlledByJS) {
            this.currentFrameNumber++;
            if (this.currentFrameNumber - this.currentBatchNumber > 2) {
                this.currentBatchNumber = this.currentFrameNumber;
                j = this.currentBatchNumber;
            }
        }
        this.preOperations.executeBatch(j, getNodesManager());
        this.operations.executeBatch(j, getNodesManager());
    }

    @Override // com.facebook.react.bridge.UIManagerListener
    public void willDispatchViewUpdates(UIManager uiManager) {
        Intrinsics.checkNotNullParameter(uiManager, "uiManager");
        if ((this.operations.isEmpty() && this.preOperations.isEmpty()) || this.uiManagerType == 2 || ReactBuildConfig.UNSTABLE_ENABLE_MINIFY_LEGACY_ARCHITECTURE) {
            return;
        }
        final long j = this.currentBatchNumber;
        this.currentBatchNumber = 1 + j;
        UIBlock uIBlock = new UIBlock() { // from class: com.facebook.react.animated.NativeAnimatedModule$$ExternalSyntheticLambda2
            @Override // com.facebook.react.uimanager.UIBlock
            public final void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                NativeAnimatedModule.willDispatchViewUpdates$lambda$3(this.f$0, j, nativeViewHierarchyManager);
            }
        };
        UIBlock uIBlock2 = new UIBlock() { // from class: com.facebook.react.animated.NativeAnimatedModule$$ExternalSyntheticLambda3
            @Override // com.facebook.react.uimanager.UIBlock
            public final void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                NativeAnimatedModule.willDispatchViewUpdates$lambda$4(this.f$0, j, nativeViewHierarchyManager);
            }
        };
        boolean z = uiManager instanceof UIManagerModule;
        UIManagerModule uIManagerModule = (UIManagerModule) uiManager;
        uIManagerModule.prependUIBlock(uIBlock);
        uIManagerModule.addUIBlock(uIBlock2);
    }

    public static final void willDispatchViewUpdates$lambda$3(NativeAnimatedModule nativeAnimatedModule, long j, NativeViewHierarchyManager it) {
        Intrinsics.checkNotNullParameter(it, "it");
        nativeAnimatedModule.preOperations.executeBatch(j, nativeAnimatedModule.getNodesManager());
    }

    public static final void willDispatchViewUpdates$lambda$4(NativeAnimatedModule nativeAnimatedModule, long j, NativeViewHierarchyManager it) {
        Intrinsics.checkNotNullParameter(it, "it");
        nativeAnimatedModule.operations.executeBatch(j, nativeAnimatedModule.getNodesManager());
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        clearFrameCallback();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        clearFrameCallback();
    }

    public final NativeAnimatedNodesManager getNodesManager() {
        ReactApplicationContext reactApplicationContextIfActiveOrWarn;
        if (this.nodesManagerRef.get() == null && (reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn()) != null) {
            LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.nodesManagerRef, null, new NativeAnimatedNodesManager(reactApplicationContextIfActiveOrWarn));
        }
        return this.nodesManagerRef.get();
    }

    @VisibleForTesting
    public final void setNodesManager(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        this.nodesManagerRef.set(nativeAnimatedNodesManager);
    }

    private final void clearFrameCallback() {
        this.reactChoreographer.removeFrameCallback(ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE, this.animatedFrameCallback);
        this.enqueuedAnimationOnFrame = false;
    }

    public final void enqueueFrameCallback() {
        if (this.enqueuedAnimationOnFrame) {
            return;
        }
        this.reactChoreographer.postFrameCallback(ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE, this.animatedFrameCallback);
        this.enqueuedAnimationOnFrame = true;
    }

    private final void initializeLifecycleEventListenersForViewTag(int viewTag) {
        ReactApplicationContext reactApplicationContextIfActiveOrWarn;
        UIManager uIManager;
        int uIManagerType = ViewUtil.getUIManagerType(viewTag);
        this.uiManagerType = uIManagerType;
        if (uIManagerType == 2) {
            this.numFabricAnimations++;
        } else {
            this.numNonFabricAnimations++;
        }
        NativeAnimatedNodesManager nodesManager = getNodesManager();
        if (nodesManager != null) {
            nodesManager.initializeEventListenerForUIManagerType(this.uiManagerType);
        } else {
            ReactSoftExceptionLogger.logSoftException("NativeAnimatedModule", new RuntimeException("initializeLifecycleEventListenersForViewTag could not get NativeAnimatedNodesManager"));
        }
        if ((this.uiManagerType == 2 ? this.initializedForFabric : this.initializedForNonFabric) || (reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn()) == null || (uIManager = UIManagerHelper.getUIManager(reactApplicationContextIfActiveOrWarn, this.uiManagerType)) == null) {
            return;
        }
        uIManager.addUIManagerEventListener(this);
        if (this.uiManagerType == 2) {
            this.initializedForFabric = true;
        } else {
            this.initializedForNonFabric = true;
        }
    }

    public final void decrementInFlightAnimationsForViewTag(int viewTag) {
        if (ViewUtil.getUIManagerType(viewTag) == 2) {
            this.numFabricAnimations--;
        } else {
            this.numNonFabricAnimations--;
        }
        int i = this.numNonFabricAnimations;
        if (i == 0 && this.numFabricAnimations > 0 && this.uiManagerType != 2) {
            this.uiManagerType = 2;
        } else {
            if (this.numFabricAnimations != 0 || i <= 0 || this.uiManagerType == 1) {
                return;
            }
            this.uiManagerType = 1;
        }
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void startOperationBatch() {
        this.batchingControlledByJS = true;
        this.currentBatchNumber++;
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void finishOperationBatch() {
        this.batchingControlledByJS = false;
        this.currentBatchNumber++;
    }

    /* JADX INFO: renamed from: com.facebook.react.animated.NativeAnimatedModule$createAnimatedNode$1 */
    /* JADX INFO: compiled from: NativeAnimatedModule.kt */
    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/facebook/react/animated/NativeAnimatedModule$createAnimatedNode$1", "Lcom/facebook/react/animated/NativeAnimatedModule$UIThreadOperation;", "Lcom/facebook/react/animated/NativeAnimatedModule;", "execute", "", "animatedNodesManager", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class C00741 extends UIThreadOperation {
        final /* synthetic */ ReadableMap $config;
        final /* synthetic */ int $tag;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00741(NativeAnimatedModule nativeAnimatedModule, int i, ReadableMap readableMap) {
            super();
            i = i;
            readableMap = readableMap;
        }

        @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
        public void execute(NativeAnimatedNodesManager animatedNodesManager) {
            Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
            animatedNodesManager.createAnimatedNode(i, readableMap);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void createAnimatedNode(double tagDouble, ReadableMap config) {
        Intrinsics.checkNotNullParameter(config, "config");
        addOperation(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.createAnimatedNode.1
            final /* synthetic */ ReadableMap $config;
            final /* synthetic */ int $tag;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00741(NativeAnimatedModule this, int i, ReadableMap config2) {
                super();
                i = i;
                readableMap = config2;
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
                animatedNodesManager.createAnimatedNode(i, readableMap);
            }
        });
    }

    /* JADX INFO: renamed from: com.facebook.react.animated.NativeAnimatedModule$updateAnimatedNodeConfig$1 */
    /* JADX INFO: compiled from: NativeAnimatedModule.kt */
    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/facebook/react/animated/NativeAnimatedModule$updateAnimatedNodeConfig$1", "Lcom/facebook/react/animated/NativeAnimatedModule$UIThreadOperation;", "Lcom/facebook/react/animated/NativeAnimatedModule;", "execute", "", "animatedNodesManager", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class C00901 extends UIThreadOperation {
        final /* synthetic */ ReadableMap $config;
        final /* synthetic */ int $tag;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00901(NativeAnimatedModule nativeAnimatedModule, int i, ReadableMap readableMap) {
            super();
            i = i;
            readableMap = readableMap;
        }

        @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
        public void execute(NativeAnimatedNodesManager animatedNodesManager) {
            Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
            animatedNodesManager.updateAnimatedNodeConfig(i, readableMap);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void updateAnimatedNodeConfig(double tagDouble, ReadableMap config) {
        Intrinsics.checkNotNullParameter(config, "config");
        addOperation(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.updateAnimatedNodeConfig.1
            final /* synthetic */ ReadableMap $config;
            final /* synthetic */ int $tag;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00901(NativeAnimatedModule this, int i, ReadableMap config2) {
                super();
                i = i;
                readableMap = config2;
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
                animatedNodesManager.updateAnimatedNodeConfig(i, readableMap);
            }
        });
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void startListeningToAnimatedNodeValue(double tagDouble) {
        final int i = (int) tagDouble;
        addOperation(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.startListeningToAnimatedNodeValue.1
            final /* synthetic */ AnimatedNodeValueListener $listener;
            final /* synthetic */ int $tag;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00871(final NativeAnimatedModule this, final int i2, AnimatedNodeValueListener animatedNodeValueListener) {
                super();
                i = i2;
                animatedNodeValueListener = animatedNodeValueListener;
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
                animatedNodesManager.startListeningToAnimatedNodeValue(i, animatedNodeValueListener);
            }
        });
    }

    /* JADX INFO: renamed from: com.facebook.react.animated.NativeAnimatedModule$startListeningToAnimatedNodeValue$1 */
    /* JADX INFO: compiled from: NativeAnimatedModule.kt */
    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/facebook/react/animated/NativeAnimatedModule$startListeningToAnimatedNodeValue$1", "Lcom/facebook/react/animated/NativeAnimatedModule$UIThreadOperation;", "Lcom/facebook/react/animated/NativeAnimatedModule;", "execute", "", "animatedNodesManager", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class C00871 extends UIThreadOperation {
        final /* synthetic */ AnimatedNodeValueListener $listener;
        final /* synthetic */ int $tag;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00871(final NativeAnimatedModule this, final int i2, AnimatedNodeValueListener animatedNodeValueListener) {
            super();
            i = i2;
            animatedNodeValueListener = animatedNodeValueListener;
        }

        @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
        public void execute(NativeAnimatedNodesManager animatedNodesManager) {
            Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
            animatedNodesManager.startListeningToAnimatedNodeValue(i, animatedNodeValueListener);
        }
    }

    /* JADX INFO: renamed from: com.facebook.react.animated.NativeAnimatedModule$stopListeningToAnimatedNodeValue$1 */
    /* JADX INFO: compiled from: NativeAnimatedModule.kt */
    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/facebook/react/animated/NativeAnimatedModule$stopListeningToAnimatedNodeValue$1", "Lcom/facebook/react/animated/NativeAnimatedModule$UIThreadOperation;", "Lcom/facebook/react/animated/NativeAnimatedModule;", "execute", "", "animatedNodesManager", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class C00891 extends UIThreadOperation {
        final /* synthetic */ int $tag;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00891(NativeAnimatedModule nativeAnimatedModule, int i) {
            super();
            i = i;
        }

        @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
        public void execute(NativeAnimatedNodesManager animatedNodesManager) {
            Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
            animatedNodesManager.stopListeningToAnimatedNodeValue(i);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void stopListeningToAnimatedNodeValue(double tagDouble) {
        addOperation(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.stopListeningToAnimatedNodeValue.1
            final /* synthetic */ int $tag;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00891(NativeAnimatedModule this, int i) {
                super();
                i = i;
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
                animatedNodesManager.stopListeningToAnimatedNodeValue(i);
            }
        });
    }

    /* JADX INFO: renamed from: com.facebook.react.animated.NativeAnimatedModule$dropAnimatedNode$1 */
    /* JADX INFO: compiled from: NativeAnimatedModule.kt */
    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/facebook/react/animated/NativeAnimatedModule$dropAnimatedNode$1", "Lcom/facebook/react/animated/NativeAnimatedModule$UIThreadOperation;", "Lcom/facebook/react/animated/NativeAnimatedModule;", "execute", "", "animatedNodesManager", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class C00771 extends UIThreadOperation {
        final /* synthetic */ int $tag;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00771(NativeAnimatedModule nativeAnimatedModule, int i) {
            super();
            i = i;
        }

        @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
        public void execute(NativeAnimatedNodesManager animatedNodesManager) {
            Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
            animatedNodesManager.dropAnimatedNode(i);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void dropAnimatedNode(double tagDouble) {
        addOperation(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.dropAnimatedNode.1
            final /* synthetic */ int $tag;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00771(NativeAnimatedModule this, int i) {
                super();
                i = i;
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
                animatedNodesManager.dropAnimatedNode(i);
            }
        });
    }

    /* JADX INFO: renamed from: com.facebook.react.animated.NativeAnimatedModule$setAnimatedNodeValue$1 */
    /* JADX INFO: compiled from: NativeAnimatedModule.kt */
    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/facebook/react/animated/NativeAnimatedModule$setAnimatedNodeValue$1", "Lcom/facebook/react/animated/NativeAnimatedModule$UIThreadOperation;", "Lcom/facebook/react/animated/NativeAnimatedModule;", "execute", "", "animatedNodesManager", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class C00851 extends UIThreadOperation {
        final /* synthetic */ int $tag;
        final /* synthetic */ double $value;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00851(NativeAnimatedModule nativeAnimatedModule, int i, double d) {
            super();
            i = i;
            d = d;
        }

        @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
        public void execute(NativeAnimatedNodesManager animatedNodesManager) {
            Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
            animatedNodesManager.setAnimatedNodeValue(i, d);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void setAnimatedNodeValue(double tagDouble, double value) {
        addOperation(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.setAnimatedNodeValue.1
            final /* synthetic */ int $tag;
            final /* synthetic */ double $value;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00851(NativeAnimatedModule this, int i, double value2) {
                super();
                i = i;
                d = value2;
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
                animatedNodesManager.setAnimatedNodeValue(i, d);
            }
        });
    }

    /* JADX INFO: renamed from: com.facebook.react.animated.NativeAnimatedModule$setAnimatedNodeOffset$1 */
    /* JADX INFO: compiled from: NativeAnimatedModule.kt */
    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/facebook/react/animated/NativeAnimatedModule$setAnimatedNodeOffset$1", "Lcom/facebook/react/animated/NativeAnimatedModule$UIThreadOperation;", "Lcom/facebook/react/animated/NativeAnimatedModule;", "execute", "", "animatedNodesManager", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class C00841 extends UIThreadOperation {
        final /* synthetic */ int $tag;
        final /* synthetic */ double $value;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00841(NativeAnimatedModule nativeAnimatedModule, int i, double d) {
            super();
            i = i;
            d = d;
        }

        @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
        public void execute(NativeAnimatedNodesManager animatedNodesManager) {
            Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
            animatedNodesManager.setAnimatedNodeOffset(i, d);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void setAnimatedNodeOffset(double tagDouble, double value) {
        addOperation(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.setAnimatedNodeOffset.1
            final /* synthetic */ int $tag;
            final /* synthetic */ double $value;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00841(NativeAnimatedModule this, int i, double value2) {
                super();
                i = i;
                d = value2;
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
                animatedNodesManager.setAnimatedNodeOffset(i, d);
            }
        });
    }

    /* JADX INFO: renamed from: com.facebook.react.animated.NativeAnimatedModule$flattenAnimatedNodeOffset$1 */
    /* JADX INFO: compiled from: NativeAnimatedModule.kt */
    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/facebook/react/animated/NativeAnimatedModule$flattenAnimatedNodeOffset$1", "Lcom/facebook/react/animated/NativeAnimatedModule$UIThreadOperation;", "Lcom/facebook/react/animated/NativeAnimatedModule;", "execute", "", "animatedNodesManager", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class C00791 extends UIThreadOperation {
        final /* synthetic */ int $tag;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00791(NativeAnimatedModule nativeAnimatedModule, int i) {
            super();
            i = i;
        }

        @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
        public void execute(NativeAnimatedNodesManager animatedNodesManager) {
            Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
            animatedNodesManager.flattenAnimatedNodeOffset(i);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void flattenAnimatedNodeOffset(double tagDouble) {
        addOperation(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.flattenAnimatedNodeOffset.1
            final /* synthetic */ int $tag;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00791(NativeAnimatedModule this, int i) {
                super();
                i = i;
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
                animatedNodesManager.flattenAnimatedNodeOffset(i);
            }
        });
    }

    /* JADX INFO: renamed from: com.facebook.react.animated.NativeAnimatedModule$extractAnimatedNodeOffset$1 */
    /* JADX INFO: compiled from: NativeAnimatedModule.kt */
    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/facebook/react/animated/NativeAnimatedModule$extractAnimatedNodeOffset$1", "Lcom/facebook/react/animated/NativeAnimatedModule$UIThreadOperation;", "Lcom/facebook/react/animated/NativeAnimatedModule;", "execute", "", "animatedNodesManager", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class C00781 extends UIThreadOperation {
        final /* synthetic */ int $tag;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00781(NativeAnimatedModule nativeAnimatedModule, int i) {
            super();
            i = i;
        }

        @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
        public void execute(NativeAnimatedNodesManager animatedNodesManager) {
            Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
            animatedNodesManager.extractAnimatedNodeOffset(i);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void extractAnimatedNodeOffset(double tagDouble) {
        addOperation(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.extractAnimatedNodeOffset.1
            final /* synthetic */ int $tag;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00781(NativeAnimatedModule this, int i) {
                super();
                i = i;
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
                animatedNodesManager.extractAnimatedNodeOffset(i);
            }
        });
    }

    /* JADX INFO: renamed from: com.facebook.react.animated.NativeAnimatedModule$startAnimatingNode$1 */
    /* JADX INFO: compiled from: NativeAnimatedModule.kt */
    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/facebook/react/animated/NativeAnimatedModule$startAnimatingNode$1", "Lcom/facebook/react/animated/NativeAnimatedModule$UIThreadOperation;", "Lcom/facebook/react/animated/NativeAnimatedModule;", "execute", "", "animatedNodesManager", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class C00861 extends UIThreadOperation {
        final /* synthetic */ int $animatedNodeTag;
        final /* synthetic */ ReadableMap $animationConfig;
        final /* synthetic */ int $animationId;
        final /* synthetic */ Callback $endCallback;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00861(NativeAnimatedModule nativeAnimatedModule, int i, int i2, ReadableMap readableMap, Callback callback) {
            super();
            i = i;
            i = i2;
            readableMap = readableMap;
            callback = callback;
        }

        @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
        public void execute(NativeAnimatedNodesManager animatedNodesManager) {
            Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
            animatedNodesManager.startAnimatingNode(i, i, readableMap, callback);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void startAnimatingNode(double animationIdDouble, double animatedNodeTagDouble, ReadableMap animationConfig, Callback endCallback) {
        Intrinsics.checkNotNullParameter(animationConfig, "animationConfig");
        Intrinsics.checkNotNullParameter(endCallback, "endCallback");
        addUnbatchedOperation(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.startAnimatingNode.1
            final /* synthetic */ int $animatedNodeTag;
            final /* synthetic */ ReadableMap $animationConfig;
            final /* synthetic */ int $animationId;
            final /* synthetic */ Callback $endCallback;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00861(NativeAnimatedModule this, int i, int i2, ReadableMap animationConfig2, Callback endCallback2) {
                super();
                i = i;
                i = i2;
                readableMap = animationConfig2;
                callback = endCallback2;
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
                animatedNodesManager.startAnimatingNode(i, i, readableMap, callback);
            }
        });
    }

    /* JADX INFO: renamed from: com.facebook.react.animated.NativeAnimatedModule$stopAnimation$1 */
    /* JADX INFO: compiled from: NativeAnimatedModule.kt */
    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/facebook/react/animated/NativeAnimatedModule$stopAnimation$1", "Lcom/facebook/react/animated/NativeAnimatedModule$UIThreadOperation;", "Lcom/facebook/react/animated/NativeAnimatedModule;", "execute", "", "animatedNodesManager", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class C00881 extends UIThreadOperation {
        final /* synthetic */ int $animationId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00881(NativeAnimatedModule nativeAnimatedModule, int i) {
            super();
            i = i;
        }

        @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
        public void execute(NativeAnimatedNodesManager animatedNodesManager) {
            Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
            animatedNodesManager.stopAnimation(i);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void stopAnimation(double animationIdDouble) {
        addOperation(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.stopAnimation.1
            final /* synthetic */ int $animationId;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00881(NativeAnimatedModule this, int i) {
                super();
                i = i;
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
                animatedNodesManager.stopAnimation(i);
            }
        });
    }

    /* JADX INFO: renamed from: com.facebook.react.animated.NativeAnimatedModule$connectAnimatedNodes$1 */
    /* JADX INFO: compiled from: NativeAnimatedModule.kt */
    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/facebook/react/animated/NativeAnimatedModule$connectAnimatedNodes$1", "Lcom/facebook/react/animated/NativeAnimatedModule$UIThreadOperation;", "Lcom/facebook/react/animated/NativeAnimatedModule;", "execute", "", "animatedNodesManager", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class C00731 extends UIThreadOperation {
        final /* synthetic */ int $childNodeTag;
        final /* synthetic */ int $parentNodeTag;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00731(NativeAnimatedModule nativeAnimatedModule, int i, int i2) {
            super();
            i = i;
            i = i2;
        }

        @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
        public void execute(NativeAnimatedNodesManager animatedNodesManager) {
            Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
            animatedNodesManager.connectAnimatedNodes(i, i);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void connectAnimatedNodes(double parentNodeTagDouble, double childNodeTagDouble) {
        addOperation(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.connectAnimatedNodes.1
            final /* synthetic */ int $childNodeTag;
            final /* synthetic */ int $parentNodeTag;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00731(NativeAnimatedModule this, int i, int i2) {
                super();
                i = i;
                i = i2;
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
                animatedNodesManager.connectAnimatedNodes(i, i);
            }
        });
    }

    /* JADX INFO: renamed from: com.facebook.react.animated.NativeAnimatedModule$disconnectAnimatedNodes$1 */
    /* JADX INFO: compiled from: NativeAnimatedModule.kt */
    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/facebook/react/animated/NativeAnimatedModule$disconnectAnimatedNodes$1", "Lcom/facebook/react/animated/NativeAnimatedModule$UIThreadOperation;", "Lcom/facebook/react/animated/NativeAnimatedModule;", "execute", "", "animatedNodesManager", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class C00761 extends UIThreadOperation {
        final /* synthetic */ int $childNodeTag;
        final /* synthetic */ int $parentNodeTag;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00761(NativeAnimatedModule nativeAnimatedModule, int i, int i2) {
            super();
            i = i;
            i = i2;
        }

        @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
        public void execute(NativeAnimatedNodesManager animatedNodesManager) {
            Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
            animatedNodesManager.disconnectAnimatedNodes(i, i);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void disconnectAnimatedNodes(double parentNodeTagDouble, double childNodeTagDouble) {
        addOperation(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.disconnectAnimatedNodes.1
            final /* synthetic */ int $childNodeTag;
            final /* synthetic */ int $parentNodeTag;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00761(NativeAnimatedModule this, int i, int i2) {
                super();
                i = i;
                i = i2;
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
                animatedNodesManager.disconnectAnimatedNodes(i, i);
            }
        });
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void connectAnimatedNodeToView(double animatedNodeTagDouble, double viewTagDouble) {
        int i = (int) viewTagDouble;
        initializeLifecycleEventListenersForViewTag(i);
        addOperation(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.connectAnimatedNodeToView.1
            final /* synthetic */ int $animatedNodeTag;
            final /* synthetic */ int $viewTag;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00721(NativeAnimatedModule this, int i2, int i3) {
                super();
                i = i2;
                i = i3;
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
                animatedNodesManager.connectAnimatedNodeToView(i, i);
            }
        });
    }

    /* JADX INFO: renamed from: com.facebook.react.animated.NativeAnimatedModule$connectAnimatedNodeToView$1 */
    /* JADX INFO: compiled from: NativeAnimatedModule.kt */
    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/facebook/react/animated/NativeAnimatedModule$connectAnimatedNodeToView$1", "Lcom/facebook/react/animated/NativeAnimatedModule$UIThreadOperation;", "Lcom/facebook/react/animated/NativeAnimatedModule;", "execute", "", "animatedNodesManager", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class C00721 extends UIThreadOperation {
        final /* synthetic */ int $animatedNodeTag;
        final /* synthetic */ int $viewTag;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00721(NativeAnimatedModule this, int i2, int i3) {
            super();
            i = i2;
            i = i3;
        }

        @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
        public void execute(NativeAnimatedNodesManager animatedNodesManager) {
            Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
            animatedNodesManager.connectAnimatedNodeToView(i, i);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void disconnectAnimatedNodeFromView(double animatedNodeTagDouble, double viewTagDouble) {
        int i = (int) viewTagDouble;
        decrementInFlightAnimationsForViewTag(i);
        addOperation(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.disconnectAnimatedNodeFromView.1
            final /* synthetic */ int $animatedNodeTag;
            final /* synthetic */ int $viewTag;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00751(NativeAnimatedModule this, int i2, int i3) {
                super();
                i = i2;
                i = i3;
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
                animatedNodesManager.disconnectAnimatedNodeFromView(i, i);
            }
        });
    }

    /* JADX INFO: renamed from: com.facebook.react.animated.NativeAnimatedModule$disconnectAnimatedNodeFromView$1 */
    /* JADX INFO: compiled from: NativeAnimatedModule.kt */
    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/facebook/react/animated/NativeAnimatedModule$disconnectAnimatedNodeFromView$1", "Lcom/facebook/react/animated/NativeAnimatedModule$UIThreadOperation;", "Lcom/facebook/react/animated/NativeAnimatedModule;", "execute", "", "animatedNodesManager", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class C00751 extends UIThreadOperation {
        final /* synthetic */ int $animatedNodeTag;
        final /* synthetic */ int $viewTag;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00751(NativeAnimatedModule this, int i2, int i3) {
            super();
            i = i2;
            i = i3;
        }

        @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
        public void execute(NativeAnimatedNodesManager animatedNodesManager) {
            Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
            animatedNodesManager.disconnectAnimatedNodeFromView(i, i);
        }
    }

    /* JADX INFO: renamed from: com.facebook.react.animated.NativeAnimatedModule$restoreDefaultValues$1 */
    /* JADX INFO: compiled from: NativeAnimatedModule.kt */
    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/facebook/react/animated/NativeAnimatedModule$restoreDefaultValues$1", "Lcom/facebook/react/animated/NativeAnimatedModule$UIThreadOperation;", "Lcom/facebook/react/animated/NativeAnimatedModule;", "execute", "", "animatedNodesManager", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class C00831 extends UIThreadOperation {
        final /* synthetic */ int $animatedNodeTag;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00831(NativeAnimatedModule nativeAnimatedModule, int i) {
            super();
            i = i;
        }

        @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
        public void execute(NativeAnimatedNodesManager animatedNodesManager) {
            Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
            animatedNodesManager.restoreDefaultValues(i);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void restoreDefaultValues(double animatedNodeTagDouble) {
        addPreOperation(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.restoreDefaultValues.1
            final /* synthetic */ int $animatedNodeTag;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00831(NativeAnimatedModule this, int i) {
                super();
                i = i;
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
                animatedNodesManager.restoreDefaultValues(i);
            }
        });
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void addAnimatedEventToView(double viewTagDouble, String eventName, ReadableMap eventMapping) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(eventMapping, "eventMapping");
        int i = (int) viewTagDouble;
        initializeLifecycleEventListenersForViewTag(i);
        addOperation(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.addAnimatedEventToView.1
            final /* synthetic */ ReadableMap $eventMapping;
            final /* synthetic */ String $eventName;
            final /* synthetic */ int $viewTag;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(NativeAnimatedModule this, int i2, String eventName2, ReadableMap eventMapping2) {
                super();
                i = i2;
                str = eventName2;
                readableMap = eventMapping2;
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
                animatedNodesManager.addAnimatedEventToView(i, str, readableMap);
            }
        });
    }

    /* JADX INFO: renamed from: com.facebook.react.animated.NativeAnimatedModule$addAnimatedEventToView$1 */
    /* JADX INFO: compiled from: NativeAnimatedModule.kt */
    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/facebook/react/animated/NativeAnimatedModule$addAnimatedEventToView$1", "Lcom/facebook/react/animated/NativeAnimatedModule$UIThreadOperation;", "Lcom/facebook/react/animated/NativeAnimatedModule;", "execute", "", "animatedNodesManager", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class AnonymousClass1 extends UIThreadOperation {
        final /* synthetic */ ReadableMap $eventMapping;
        final /* synthetic */ String $eventName;
        final /* synthetic */ int $viewTag;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(NativeAnimatedModule this, int i2, String eventName2, ReadableMap eventMapping2) {
            super();
            i = i2;
            str = eventName2;
            readableMap = eventMapping2;
        }

        @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
        public void execute(NativeAnimatedNodesManager animatedNodesManager) {
            Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
            animatedNodesManager.addAnimatedEventToView(i, str, readableMap);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void removeAnimatedEventFromView(double viewTagDouble, String eventName, double animatedValueTagDouble) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        int i = (int) viewTagDouble;
        decrementInFlightAnimationsForViewTag(i);
        addOperation(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.removeAnimatedEventFromView.1
            final /* synthetic */ int $animatedValueTag;
            final /* synthetic */ String $eventName;
            final /* synthetic */ int $viewTag;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00821(NativeAnimatedModule this, int i2, String eventName2, int i3) {
                super();
                i = i2;
                str = eventName2;
                i = i3;
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
                animatedNodesManager.removeAnimatedEventFromView(i, str, i);
            }
        });
    }

    /* JADX INFO: renamed from: com.facebook.react.animated.NativeAnimatedModule$removeAnimatedEventFromView$1 */
    /* JADX INFO: compiled from: NativeAnimatedModule.kt */
    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/facebook/react/animated/NativeAnimatedModule$removeAnimatedEventFromView$1", "Lcom/facebook/react/animated/NativeAnimatedModule$UIThreadOperation;", "Lcom/facebook/react/animated/NativeAnimatedModule;", "execute", "", "animatedNodesManager", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class C00821 extends UIThreadOperation {
        final /* synthetic */ int $animatedValueTag;
        final /* synthetic */ String $eventName;
        final /* synthetic */ int $viewTag;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00821(NativeAnimatedModule this, int i2, String eventName2, int i3) {
            super();
            i = i2;
            str = eventName2;
            i = i3;
        }

        @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
        public void execute(NativeAnimatedNodesManager animatedNodesManager) {
            Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
            animatedNodesManager.removeAnimatedEventFromView(i, str, i);
        }
    }

    /* JADX INFO: renamed from: com.facebook.react.animated.NativeAnimatedModule$getValue$1 */
    /* JADX INFO: compiled from: NativeAnimatedModule.kt */
    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/facebook/react/animated/NativeAnimatedModule$getValue$1", "Lcom/facebook/react/animated/NativeAnimatedModule$UIThreadOperation;", "Lcom/facebook/react/animated/NativeAnimatedModule;", "execute", "", "animatedNodesManager", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class C00801 extends UIThreadOperation {
        final /* synthetic */ int $animatedValueNodeTag;
        final /* synthetic */ Callback $callback;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00801(NativeAnimatedModule nativeAnimatedModule, int i, Callback callback) {
            super();
            i = i;
            callback = callback;
        }

        @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
        public void execute(NativeAnimatedNodesManager animatedNodesManager) {
            Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
            animatedNodesManager.getValue(i, callback);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void getValue(double animatedValueNodeTagDouble, Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        addOperation(new UIThreadOperation(this) { // from class: com.facebook.react.animated.NativeAnimatedModule.getValue.1
            final /* synthetic */ int $animatedValueNodeTag;
            final /* synthetic */ Callback $callback;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00801(NativeAnimatedModule this, int i, Callback callback2) {
                super();
                i = i;
                callback = callback2;
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager animatedNodesManager) {
                Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
                animatedNodesManager.getValue(i, callback);
            }
        });
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void invalidate() {
        super.invalidate();
        getReactApplicationContext().removeLifecycleEventListener(this);
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void queueAndExecuteBatchedOperations(ReadableArray opsAndArgs) {
        Intrinsics.checkNotNullParameter(opsAndArgs, "opsAndArgs");
        int size = opsAndArgs.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            switch (WhenMappings.$EnumSwitchMapping$0[BatchExecutionOpCodes.INSTANCE.fromId(opsAndArgs.getInt(i)).ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                    i += 2;
                    continue;
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                    i += 3;
                    continue;
                case 18:
                case 19:
                    break;
                case 20:
                    int i3 = i + 2;
                    i += 3;
                    initializeLifecycleEventListenersForViewTag(opsAndArgs.getInt(i3));
                    continue;
                case 21:
                    initializeLifecycleEventListenersForViewTag(opsAndArgs.getInt(i2));
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            i += 4;
        }
        startOperationBatch();
        addUnbatchedOperation(new C00811(size, opsAndArgs));
        finishOperationBatch();
    }

    /* JADX INFO: renamed from: com.facebook.react.animated.NativeAnimatedModule$queueAndExecuteBatchedOperations$1 */
    /* JADX INFO: compiled from: NativeAnimatedModule.kt */
    @Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00060\u0001R\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"com/facebook/react/animated/NativeAnimatedModule$queueAndExecuteBatchedOperations$1", "Lcom/facebook/react/animated/NativeAnimatedModule$UIThreadOperation;", "Lcom/facebook/react/animated/NativeAnimatedModule;", "execute", "", "animatedNodesManager", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class C00811 extends UIThreadOperation {
        final /* synthetic */ int $opBufferSize;
        final /* synthetic */ ReadableArray $opsAndArgs;

        /* JADX INFO: renamed from: com.facebook.react.animated.NativeAnimatedModule$queueAndExecuteBatchedOperations$1$WhenMappings */
        /* JADX INFO: compiled from: NativeAnimatedModule.kt */
        @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[BatchExecutionOpCodes.values().length];
                try {
                    iArr[BatchExecutionOpCodes.OP_CODE_CREATE_ANIMATED_NODE.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[BatchExecutionOpCodes.OP_CODE_UPDATE_ANIMATED_NODE_CONFIG.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[BatchExecutionOpCodes.OP_CODE_GET_VALUE.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[BatchExecutionOpCodes.OP_START_LISTENING_TO_ANIMATED_NODE_VALUE.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[BatchExecutionOpCodes.OP_STOP_LISTENING_TO_ANIMATED_NODE_VALUE.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[BatchExecutionOpCodes.OP_CODE_CONNECT_ANIMATED_NODES.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr[BatchExecutionOpCodes.OP_CODE_DISCONNECT_ANIMATED_NODES.ordinal()] = 7;
                } catch (NoSuchFieldError unused7) {
                }
                try {
                    iArr[BatchExecutionOpCodes.OP_CODE_START_ANIMATING_NODE.ordinal()] = 8;
                } catch (NoSuchFieldError unused8) {
                }
                try {
                    iArr[BatchExecutionOpCodes.OP_CODE_STOP_ANIMATION.ordinal()] = 9;
                } catch (NoSuchFieldError unused9) {
                }
                try {
                    iArr[BatchExecutionOpCodes.OP_CODE_SET_ANIMATED_NODE_VALUE.ordinal()] = 10;
                } catch (NoSuchFieldError unused10) {
                }
                try {
                    iArr[BatchExecutionOpCodes.OP_CODE_SET_ANIMATED_NODE_OFFSET.ordinal()] = 11;
                } catch (NoSuchFieldError unused11) {
                }
                try {
                    iArr[BatchExecutionOpCodes.OP_CODE_FLATTEN_ANIMATED_NODE_OFFSET.ordinal()] = 12;
                } catch (NoSuchFieldError unused12) {
                }
                try {
                    iArr[BatchExecutionOpCodes.OP_CODE_EXTRACT_ANIMATED_NODE_OFFSET.ordinal()] = 13;
                } catch (NoSuchFieldError unused13) {
                }
                try {
                    iArr[BatchExecutionOpCodes.OP_CODE_CONNECT_ANIMATED_NODE_TO_VIEW.ordinal()] = 14;
                } catch (NoSuchFieldError unused14) {
                }
                try {
                    iArr[BatchExecutionOpCodes.OP_CODE_DISCONNECT_ANIMATED_NODE_FROM_VIEW.ordinal()] = 15;
                } catch (NoSuchFieldError unused15) {
                }
                try {
                    iArr[BatchExecutionOpCodes.OP_CODE_RESTORE_DEFAULT_VALUES.ordinal()] = 16;
                } catch (NoSuchFieldError unused16) {
                }
                try {
                    iArr[BatchExecutionOpCodes.OP_CODE_DROP_ANIMATED_NODE.ordinal()] = 17;
                } catch (NoSuchFieldError unused17) {
                }
                try {
                    iArr[BatchExecutionOpCodes.OP_CODE_ADD_ANIMATED_EVENT_TO_VIEW.ordinal()] = 18;
                } catch (NoSuchFieldError unused18) {
                }
                try {
                    iArr[BatchExecutionOpCodes.OP_CODE_REMOVE_ANIMATED_EVENT_FROM_VIEW.ordinal()] = 19;
                } catch (NoSuchFieldError unused19) {
                }
                try {
                    iArr[BatchExecutionOpCodes.OP_CODE_ADD_LISTENER.ordinal()] = 20;
                } catch (NoSuchFieldError unused20) {
                }
                try {
                    iArr[BatchExecutionOpCodes.OP_CODE_REMOVE_LISTENERS.ordinal()] = 21;
                } catch (NoSuchFieldError unused21) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00811(int i, ReadableArray readableArray) {
            super();
            this.$opBufferSize = i;
            this.$opsAndArgs = readableArray;
        }

        @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
        public void execute(NativeAnimatedNodesManager animatedNodesManager) {
            Intrinsics.checkNotNullParameter(animatedNodesManager, "animatedNodesManager");
            NativeAnimatedModule.this.getReactApplicationContextIfActiveOrWarn();
            int i = 0;
            while (i < this.$opBufferSize) {
                int i2 = i + 1;
                switch (WhenMappings.$EnumSwitchMapping$0[BatchExecutionOpCodes.INSTANCE.fromId(this.$opsAndArgs.getInt(i)).ordinal()]) {
                    case 1:
                        int i3 = i + 2;
                        int i4 = this.$opsAndArgs.getInt(i2);
                        i += 3;
                        ReadableMap map = this.$opsAndArgs.getMap(i3);
                        if (map != null) {
                            animatedNodesManager.createAnimatedNode(i4, map);
                        } else {
                            throw new IllegalStateException("Required value was null.".toString());
                        }
                        break;
                    case 2:
                        int i5 = i + 2;
                        int i6 = this.$opsAndArgs.getInt(i2);
                        i += 3;
                        ReadableMap map2 = this.$opsAndArgs.getMap(i5);
                        if (map2 != null) {
                            animatedNodesManager.updateAnimatedNodeConfig(i6, map2);
                        } else {
                            throw new IllegalStateException("Required value was null.".toString());
                        }
                        break;
                    case 3:
                        i += 2;
                        animatedNodesManager.getValue(this.$opsAndArgs.getInt(i2), null);
                        break;
                    case 4:
                        i += 2;
                        final int i7 = this.$opsAndArgs.getInt(i2);
                        final NativeAnimatedModule nativeAnimatedModule = NativeAnimatedModule.this;
                        animatedNodesManager.startListeningToAnimatedNodeValue(i7, new AnimatedNodeValueListener() { // from class: com.facebook.react.animated.NativeAnimatedModule$queueAndExecuteBatchedOperations$1$$ExternalSyntheticLambda0
                            @Override // com.facebook.react.animated.AnimatedNodeValueListener
                            public final void onValueUpdate(double d, double d2) {
                                NativeAnimatedModule.C00811.execute$lambda$1(nativeAnimatedModule, i7, d, d2);
                            }
                        });
                        break;
                    case 5:
                        i += 2;
                        animatedNodesManager.stopListeningToAnimatedNodeValue(this.$opsAndArgs.getInt(i2));
                        break;
                    case 6:
                        int i8 = i + 2;
                        i += 3;
                        animatedNodesManager.connectAnimatedNodes(this.$opsAndArgs.getInt(i2), this.$opsAndArgs.getInt(i8));
                        break;
                    case 7:
                        int i9 = i + 2;
                        i += 3;
                        animatedNodesManager.disconnectAnimatedNodes(this.$opsAndArgs.getInt(i2), this.$opsAndArgs.getInt(i9));
                        break;
                    case 8:
                        int i10 = this.$opsAndArgs.getInt(i2);
                        int i11 = i + 3;
                        int i12 = this.$opsAndArgs.getInt(i + 2);
                        i += 4;
                        ReadableMap map3 = this.$opsAndArgs.getMap(i11);
                        if (map3 != null) {
                            animatedNodesManager.startAnimatingNode(i10, i12, map3, null);
                        } else {
                            throw new IllegalStateException("Required value was null.".toString());
                        }
                        break;
                    case 9:
                        i += 2;
                        animatedNodesManager.stopAnimation(this.$opsAndArgs.getInt(i2));
                        break;
                    case 10:
                        int i13 = i + 2;
                        i += 3;
                        animatedNodesManager.setAnimatedNodeValue(this.$opsAndArgs.getInt(i2), this.$opsAndArgs.getDouble(i13));
                        break;
                    case 11:
                        int i14 = i + 2;
                        i += 3;
                        animatedNodesManager.setAnimatedNodeOffset(this.$opsAndArgs.getInt(i2), this.$opsAndArgs.getDouble(i14));
                        break;
                    case 12:
                        i += 2;
                        animatedNodesManager.flattenAnimatedNodeOffset(this.$opsAndArgs.getInt(i2));
                        break;
                    case 13:
                        i += 2;
                        animatedNodesManager.extractAnimatedNodeOffset(this.$opsAndArgs.getInt(i2));
                        break;
                    case 14:
                        int i15 = i + 2;
                        i += 3;
                        animatedNodesManager.connectAnimatedNodeToView(this.$opsAndArgs.getInt(i2), this.$opsAndArgs.getInt(i15));
                        break;
                    case 15:
                        int i16 = i + 2;
                        int i17 = this.$opsAndArgs.getInt(i2);
                        i += 3;
                        int i18 = this.$opsAndArgs.getInt(i16);
                        NativeAnimatedModule.this.decrementInFlightAnimationsForViewTag(i18);
                        animatedNodesManager.disconnectAnimatedNodeFromView(i17, i18);
                        break;
                    case 16:
                        i += 2;
                        animatedNodesManager.restoreDefaultValues(this.$opsAndArgs.getInt(i2));
                        break;
                    case 17:
                        i += 2;
                        animatedNodesManager.dropAnimatedNode(this.$opsAndArgs.getInt(i2));
                        break;
                    case 18:
                        int i19 = this.$opsAndArgs.getInt(i2);
                        int i20 = i + 3;
                        String string = this.$opsAndArgs.getString(i + 2);
                        if (string == null) {
                            throw new IllegalStateException("Required value was null.".toString());
                        }
                        i += 4;
                        ReadableMap map4 = this.$opsAndArgs.getMap(i20);
                        if (map4 != null) {
                            animatedNodesManager.addAnimatedEventToView(i19, string, map4);
                        } else {
                            throw new IllegalStateException("Required value was null.".toString());
                        }
                        break;
                        break;
                    case 19:
                        int i21 = this.$opsAndArgs.getInt(i2);
                        NativeAnimatedModule.this.decrementInFlightAnimationsForViewTag(i21);
                        int i22 = i + 3;
                        String string2 = this.$opsAndArgs.getString(i + 2);
                        if (string2 != null) {
                            i += 4;
                            animatedNodesManager.removeAnimatedEventFromView(i21, string2, this.$opsAndArgs.getInt(i22));
                        } else {
                            throw new IllegalStateException("Required value was null.".toString());
                        }
                        break;
                    case 20:
                    case 21:
                        i += 2;
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
            }
        }

        public static final void execute$lambda$1(NativeAnimatedModule nativeAnimatedModule, int i, double d, double d2) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            ReadableMapBuilder readableMapBuilder = new ReadableMapBuilder(writableMapCreateMap);
            readableMapBuilder.put("tag", i);
            readableMapBuilder.put("value", d);
            readableMapBuilder.put(TypedValues.Cycle.S_WAVE_OFFSET, d2);
            WritableMap writableMap = writableMapCreateMap;
            ReactApplicationContext reactApplicationContextIfActiveOrWarn = nativeAnimatedModule.getReactApplicationContextIfActiveOrWarn();
            if (reactApplicationContextIfActiveOrWarn != null) {
                reactApplicationContextIfActiveOrWarn.emitDeviceEvent("onAnimatedValueUpdate", writableMap);
            }
        }
    }

    public static final void startListeningToAnimatedNodeValue$lambda$6(NativeAnimatedModule nativeAnimatedModule, int i, double d, double d2) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        ReadableMapBuilder readableMapBuilder = new ReadableMapBuilder(writableMapCreateMap);
        readableMapBuilder.put("tag", i);
        readableMapBuilder.put("value", d);
        readableMapBuilder.put(TypedValues.Cycle.S_WAVE_OFFSET, d2);
        WritableMap writableMap = writableMapCreateMap;
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = nativeAnimatedModule.getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn != null) {
            reactApplicationContextIfActiveOrWarn.emitDeviceEvent("onAnimatedValueUpdate", writableMap);
        }
    }
}
