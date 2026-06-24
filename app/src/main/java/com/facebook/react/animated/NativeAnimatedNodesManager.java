package com.facebook.react.animated;

import android.util.SparseArray;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapBuilder;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcherListener;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.sentry.protocol.SentryStackTrace;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: NativeAnimatedNodesManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\u0018\u0000 U2\u00020\u0001:\u0001UB\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0010J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001a\u001a\u00020\u0010J\u0006\u0010\u001b\u001a\u00020\u0013J\u0018\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001fH\u0007J\u001a\u0010 \u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u00102\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0007J\u0010\u0010!\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0010H\u0007J\u001a\u0010\"\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u00102\b\u0010#\u001a\u0004\u0018\u00010$H\u0007J\u0010\u0010%\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0010H\u0007J\u0018\u0010&\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010'\u001a\u00020(H\u0007J\u0018\u0010)\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010*\u001a\u00020(H\u0007J\u0010\u0010+\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0010H\u0007J\u0010\u0010,\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0010H\u0007J*\u0010-\u001a\u00020\u00172\u0006\u0010.\u001a\u00020\u00102\u0006\u0010/\u001a\u00020\u00102\u0006\u00100\u001a\u00020\u001f2\b\u00101\u001a\u0004\u0018\u000102H\u0007J\u0010\u00103\u001a\u00020\u00172\u0006\u00104\u001a\u00020\bH\u0003J\u0010\u00105\u001a\u00020\u00172\u0006\u0010.\u001a\u00020\u0010H\u0007J\u0018\u00106\u001a\u00020\u00172\u0006\u00107\u001a\u00020\u00102\u0006\u00108\u001a\u00020\u0010H\u0007J\u0016\u00109\u001a\u00020\u00172\u0006\u00107\u001a\u00020\u00102\u0006\u00108\u001a\u00020\u0010J\u0018\u0010:\u001a\u00020\u00172\u0006\u0010/\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u0010H\u0007J\u0018\u0010<\u001a\u00020\u00172\u0006\u0010/\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u0010H\u0007J\u001a\u0010=\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u00102\b\u0010>\u001a\u0004\u0018\u000102H\u0007J\u0010\u0010?\u001a\u00020\u00172\u0006\u0010/\u001a\u00020\u0010H\u0007J \u0010@\u001a\u00020\u00172\u0006\u0010;\u001a\u00020\u00102\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020\u001fH\u0007J \u0010D\u001a\u00020\u00172\u0006\u0010;\u001a\u00020\u00102\u0006\u0010A\u001a\u00020B2\u0006\u0010E\u001a\u00020\u0010H\u0007J\u0014\u0010F\u001a\u00020\u00172\n\u0010G\u001a\u0006\u0012\u0002\b\u00030HH\u0016J\u0014\u0010I\u001a\u00020\u00172\n\u0010G\u001a\u0006\u0012\u0002\b\u00030HH\u0003J\u0010\u0010J\u001a\u00020\u00172\u0006\u0010K\u001a\u00020LH\u0007J#\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00100N2\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010O\u001a\u00020BH\u0000¢\u0006\u0002\bPJ\u0016\u0010Q\u001a\u00020\u00172\f\u0010R\u001a\b\u0012\u0004\u0012\u00020\b0SH\u0003J\u0010\u0010T\u001a\u00020B2\u0006\u0010A\u001a\u00020BH\u0002R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006V"}, d2 = {"Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "Lcom/facebook/react/uimanager/events/EventDispatcherListener;", "reactApplicationContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "<init>", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "animatedNodes", "Landroid/util/SparseArray;", "Lcom/facebook/react/animated/AnimatedNode;", "activeAnimations", "Lcom/facebook/react/animated/AnimationDriver;", "updatedNodes", "eventDrivers", "", "Lcom/facebook/react/animated/EventAnimationDriver;", "animatedGraphBFSColor", "", "runUpdateNodeList", "eventListenerInitializedForFabric", "", "eventListenerInitializedForNonFabric", "warnedAboutGraphTraversal", "initializeEventListenerForUIManagerType", "", "uiManagerType", "getNodeById", "id", "hasActiveAnimations", "createAnimatedNode", "tag", "config", "Lcom/facebook/react/bridge/ReadableMap;", "updateAnimatedNodeConfig", "dropAnimatedNode", "startListeningToAnimatedNodeValue", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/facebook/react/animated/AnimatedNodeValueListener;", "stopListeningToAnimatedNodeValue", "setAnimatedNodeValue", "value", "", "setAnimatedNodeOffset", TypedValues.Cycle.S_WAVE_OFFSET, "flattenAnimatedNodeOffset", "extractAnimatedNodeOffset", "startAnimatingNode", "animationId", "animatedNodeTag", "animationConfig", "endCallback", "Lcom/facebook/react/bridge/Callback;", "stopAnimationsForNode", "animatedNode", "stopAnimation", "connectAnimatedNodes", "parentNodeTag", "childNodeTag", "disconnectAnimatedNodes", "connectAnimatedNodeToView", "viewTag", "disconnectAnimatedNodeFromView", "getValue", "callback", "restoreDefaultValues", "addAnimatedEventToView", "eventHandlerName", "", "eventMapping", "removeAnimatedEventFromView", "animatedValueTag", "onEventDispatch", NotificationCompat.CATEGORY_EVENT, "Lcom/facebook/react/uimanager/events/Event;", "handleEvent", "runUpdates", "frameTimeNanos", "", "getTagsOfConnectedNodes", "", "eventName", "getTagsOfConnectedNodes$ReactAndroid_release", "updateNodes", "nodes", "", "normalizeEventName", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class NativeAnimatedNodesManager implements EventDispatcherListener {
    private static final Companion Companion = new Companion(null);
    private static final String TAG = "NativeAnimatedNodesManager";
    private int animatedGraphBFSColor;
    private boolean eventListenerInitializedForFabric;
    private boolean eventListenerInitializedForNonFabric;
    private final ReactApplicationContext reactApplicationContext;
    private boolean warnedAboutGraphTraversal;
    private final SparseArray<AnimatedNode> animatedNodes = new SparseArray<>();
    private final SparseArray<AnimationDriver> activeAnimations = new SparseArray<>();
    private final SparseArray<AnimatedNode> updatedNodes = new SparseArray<>();
    private final List<EventAnimationDriver> eventDrivers = new ArrayList();
    private final List<AnimatedNode> runUpdateNodeList = new LinkedList();

    public NativeAnimatedNodesManager(ReactApplicationContext reactApplicationContext) {
        this.reactApplicationContext = reactApplicationContext;
    }

    public final void initializeEventListenerForUIManagerType(int uiManagerType) {
        boolean z;
        if (uiManagerType == 2) {
            z = this.eventListenerInitializedForFabric;
        } else {
            z = this.eventListenerInitializedForNonFabric;
        }
        if (z) {
            return;
        }
        ReactApplicationContext reactApplicationContext = this.reactApplicationContext;
        if (reactApplicationContext == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        UIManager uIManager = UIManagerHelper.getUIManager(reactApplicationContext, uiManagerType);
        if (uIManager != null) {
            uIManager.getEventDispatcher().addListener(this);
            if (uiManagerType == 2) {
                this.eventListenerInitializedForFabric = true;
            } else {
                this.eventListenerInitializedForNonFabric = true;
            }
        }
    }

    public final AnimatedNode getNodeById(int id) {
        return this.animatedNodes.get(id);
    }

    public final boolean hasActiveAnimations() {
        return this.activeAnimations.size() > 0 || this.updatedNodes.size() > 0;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void createAnimatedNode(int tag, ReadableMap config) {
        SubtractionAnimatedNode subtractionAnimatedNode;
        Intrinsics.checkNotNullParameter(config, "config");
        if (this.animatedNodes.get(tag) != null) {
            throw new JSApplicationIllegalArgumentException("createAnimatedNode: Animated node [" + tag + "] already exists");
        }
        String string = config.getString("type");
        if (string != null) {
            switch (string.hashCode()) {
                case -1774341004:
                    if (string.equals("subtraction")) {
                        subtractionAnimatedNode = new SubtractionAnimatedNode(config, this);
                        subtractionAnimatedNode.tag = tag;
                        this.animatedNodes.put(tag, subtractionAnimatedNode);
                        this.updatedNodes.put(tag, subtractionAnimatedNode);
                        return;
                    }
                    break;
                case -1226589444:
                    if (string.equals("addition")) {
                        subtractionAnimatedNode = new AdditionAnimatedNode(config, this);
                        subtractionAnimatedNode.tag = tag;
                        this.animatedNodes.put(tag, subtractionAnimatedNode);
                        this.updatedNodes.put(tag, subtractionAnimatedNode);
                        return;
                    }
                    break;
                case -1023368385:
                    if (string.equals("object")) {
                        subtractionAnimatedNode = new ObjectAnimatedNode(config, this);
                        subtractionAnimatedNode.tag = tag;
                        this.animatedNodes.put(tag, subtractionAnimatedNode);
                        this.updatedNodes.put(tag, subtractionAnimatedNode);
                        return;
                    }
                    break;
                case 94842723:
                    if (string.equals("color")) {
                        ReactApplicationContext reactApplicationContext = this.reactApplicationContext;
                        if (reactApplicationContext == null) {
                            throw new IllegalStateException("Required value was null.".toString());
                        }
                        subtractionAnimatedNode = new ColorAnimatedNode(config, this, reactApplicationContext);
                        subtractionAnimatedNode.tag = tag;
                        this.animatedNodes.put(tag, subtractionAnimatedNode);
                        this.updatedNodes.put(tag, subtractionAnimatedNode);
                        return;
                    }
                    break;
                case 106940784:
                    if (string.equals("props")) {
                        subtractionAnimatedNode = new PropsAnimatedNode(config, this);
                        subtractionAnimatedNode.tag = tag;
                        this.animatedNodes.put(tag, subtractionAnimatedNode);
                        this.updatedNodes.put(tag, subtractionAnimatedNode);
                        return;
                    }
                    break;
                case 109780401:
                    if (string.equals("style")) {
                        subtractionAnimatedNode = new StyleAnimatedNode(config, this);
                        subtractionAnimatedNode.tag = tag;
                        this.animatedNodes.put(tag, subtractionAnimatedNode);
                        this.updatedNodes.put(tag, subtractionAnimatedNode);
                        return;
                    }
                    break;
                case 111972721:
                    if (string.equals("value")) {
                        subtractionAnimatedNode = new ValueAnimatedNode(config);
                        subtractionAnimatedNode.tag = tag;
                        this.animatedNodes.put(tag, subtractionAnimatedNode);
                        this.updatedNodes.put(tag, subtractionAnimatedNode);
                        return;
                    }
                    break;
                case 364720301:
                    if (string.equals("division")) {
                        subtractionAnimatedNode = new DivisionAnimatedNode(config, this);
                        subtractionAnimatedNode.tag = tag;
                        this.animatedNodes.put(tag, subtractionAnimatedNode);
                        this.updatedNodes.put(tag, subtractionAnimatedNode);
                        return;
                    }
                    break;
                case 559331748:
                    if (string.equals("interpolation")) {
                        subtractionAnimatedNode = new InterpolationAnimatedNode(config);
                        subtractionAnimatedNode.tag = tag;
                        this.animatedNodes.put(tag, subtractionAnimatedNode);
                        this.updatedNodes.put(tag, subtractionAnimatedNode);
                        return;
                    }
                    break;
                case 668845958:
                    if (string.equals("multiplication")) {
                        subtractionAnimatedNode = new MultiplicationAnimatedNode(config, this);
                        subtractionAnimatedNode.tag = tag;
                        this.animatedNodes.put(tag, subtractionAnimatedNode);
                        this.updatedNodes.put(tag, subtractionAnimatedNode);
                        return;
                    }
                    break;
                case 1052666732:
                    if (string.equals(ViewProps.TRANSFORM)) {
                        subtractionAnimatedNode = new TransformAnimatedNode(config, this);
                        subtractionAnimatedNode.tag = tag;
                        this.animatedNodes.put(tag, subtractionAnimatedNode);
                        this.updatedNodes.put(tag, subtractionAnimatedNode);
                        return;
                    }
                    break;
                case 1227434359:
                    if (string.equals("modulus")) {
                        subtractionAnimatedNode = new ModulusAnimatedNode(config, this);
                        subtractionAnimatedNode.tag = tag;
                        this.animatedNodes.put(tag, subtractionAnimatedNode);
                        this.updatedNodes.put(tag, subtractionAnimatedNode);
                        return;
                    }
                    break;
                case 1270488759:
                    if (string.equals("tracking")) {
                        subtractionAnimatedNode = new TrackingAnimatedNode(config, this);
                        subtractionAnimatedNode.tag = tag;
                        this.animatedNodes.put(tag, subtractionAnimatedNode);
                        this.updatedNodes.put(tag, subtractionAnimatedNode);
                        return;
                    }
                    break;
                case 1300649942:
                    if (string.equals("diffclamp")) {
                        subtractionAnimatedNode = new DiffClampAnimatedNode(config, this);
                        subtractionAnimatedNode.tag = tag;
                        this.animatedNodes.put(tag, subtractionAnimatedNode);
                        this.updatedNodes.put(tag, subtractionAnimatedNode);
                        return;
                    }
                    break;
            }
        }
        throw new JSApplicationIllegalArgumentException("Unsupported node type: " + string);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateAnimatedNodeConfig(int tag, ReadableMap config) {
        AnimatedNode animatedNode = this.animatedNodes.get(tag);
        if (animatedNode == 0) {
            throw new JSApplicationIllegalArgumentException("updateAnimatedNode: Animated node [" + tag + "] does not exist");
        }
        if (animatedNode instanceof AnimatedNodeWithUpdateableConfig) {
            stopAnimationsForNode(animatedNode);
            ((AnimatedNodeWithUpdateableConfig) animatedNode).onUpdateConfig(config);
            this.updatedNodes.put(tag, animatedNode);
        }
    }

    public final void dropAnimatedNode(int tag) {
        this.animatedNodes.remove(tag);
        this.updatedNodes.remove(tag);
    }

    public final void startListeningToAnimatedNodeValue(int tag, AnimatedNodeValueListener listener) {
        AnimatedNode animatedNode = this.animatedNodes.get(tag);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("startListeningToAnimatedNodeValue: Animated node [" + tag + "] does not exist, or is not a 'value' node");
        }
        ((ValueAnimatedNode) animatedNode).setValueListener(listener);
    }

    public final void stopListeningToAnimatedNodeValue(int tag) {
        AnimatedNode animatedNode = this.animatedNodes.get(tag);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("startListeningToAnimatedNodeValue: Animated node [" + tag + "] does not exist, or is not a 'value' node");
        }
        ((ValueAnimatedNode) animatedNode).setValueListener(null);
    }

    public final void setAnimatedNodeValue(int tag, double value) {
        AnimatedNode animatedNode = this.animatedNodes.get(tag);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("setAnimatedNodeValue: Animated node [" + tag + "] does not exist, or is not a 'value' node");
        }
        stopAnimationsForNode(animatedNode);
        ((ValueAnimatedNode) animatedNode).nodeValue = value;
        this.updatedNodes.put(tag, animatedNode);
    }

    public final void setAnimatedNodeOffset(int tag, double offset) {
        AnimatedNode animatedNode = this.animatedNodes.get(tag);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("setAnimatedNodeOffset: Animated node [" + tag + "] does not exist, or is not a 'value' node");
        }
        ((ValueAnimatedNode) animatedNode).offset = offset;
        this.updatedNodes.put(tag, animatedNode);
    }

    public final void flattenAnimatedNodeOffset(int tag) {
        AnimatedNode animatedNode = this.animatedNodes.get(tag);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("flattenAnimatedNodeOffset: Animated node [" + tag + "] does not exist, or is not a 'value' node");
        }
        ((ValueAnimatedNode) animatedNode).flattenOffset();
    }

    public final void extractAnimatedNodeOffset(int tag) {
        AnimatedNode animatedNode = this.animatedNodes.get(tag);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("extractAnimatedNodeOffset: Animated node [" + tag + "] does not exist, or is not a 'value' node");
        }
        ((ValueAnimatedNode) animatedNode).extractOffset();
    }

    public final void startAnimatingNode(int animationId, int animatedNodeTag, ReadableMap animationConfig, Callback endCallback) {
        FrameBasedAnimationDriver frameBasedAnimationDriver;
        Intrinsics.checkNotNullParameter(animationConfig, "animationConfig");
        AnimatedNode animatedNode = this.animatedNodes.get(animatedNodeTag);
        if (animatedNode == null) {
            throw new JSApplicationIllegalArgumentException("startAnimatingNode: Animated node [" + animatedNodeTag + "] does not exist");
        }
        if (!(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("startAnimatingNode: Animated node [" + animatedNodeTag + "] should be of type " + ValueAnimatedNode.class.getName());
        }
        AnimationDriver animationDriver = this.activeAnimations.get(animationId);
        if (animationDriver != null) {
            animationDriver.resetConfig(animationConfig);
            return;
        }
        String string = animationConfig.getString("type");
        if (string != null) {
            int iHashCode = string.hashCode();
            if (iHashCode != -1266514778) {
                if (iHashCode != -895679987) {
                    if (iHashCode == 95459258 && string.equals("decay")) {
                        frameBasedAnimationDriver = new DecayAnimation(animationConfig);
                        frameBasedAnimationDriver.id = animationId;
                        frameBasedAnimationDriver.endCallback = endCallback;
                        frameBasedAnimationDriver.animatedValue = (ValueAnimatedNode) animatedNode;
                        this.activeAnimations.put(animationId, frameBasedAnimationDriver);
                        return;
                    }
                } else if (string.equals("spring")) {
                    frameBasedAnimationDriver = new SpringAnimation(animationConfig);
                    frameBasedAnimationDriver.id = animationId;
                    frameBasedAnimationDriver.endCallback = endCallback;
                    frameBasedAnimationDriver.animatedValue = (ValueAnimatedNode) animatedNode;
                    this.activeAnimations.put(animationId, frameBasedAnimationDriver);
                    return;
                }
            } else if (string.equals(SentryStackTrace.JsonKeys.FRAMES)) {
                frameBasedAnimationDriver = new FrameBasedAnimationDriver(animationConfig);
                frameBasedAnimationDriver.id = animationId;
                frameBasedAnimationDriver.endCallback = endCallback;
                frameBasedAnimationDriver.animatedValue = (ValueAnimatedNode) animatedNode;
                this.activeAnimations.put(animationId, frameBasedAnimationDriver);
                return;
            }
        }
        throw new JSApplicationIllegalArgumentException("startAnimatingNode: Unsupported animation type [" + animatedNodeTag + "]: " + string);
    }

    private final void stopAnimationsForNode(AnimatedNode animatedNode) {
        ReactApplicationContext reactApplicationContext;
        WritableArray writableArrayCreateArray = null;
        int i = 0;
        while (i < this.activeAnimations.size()) {
            AnimationDriver animationDriverValueAt = this.activeAnimations.valueAt(i);
            if (Intrinsics.areEqual(animatedNode, animationDriverValueAt.animatedValue)) {
                ValueAnimatedNode valueAnimatedNode = animationDriverValueAt.animatedValue;
                if (valueAnimatedNode == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                if (animationDriverValueAt.endCallback == null) {
                    if (this.reactApplicationContext != null) {
                        WritableMap writableMapCreateMap = Arguments.createMap();
                        ReadableMapBuilder readableMapBuilder = new ReadableMapBuilder(writableMapCreateMap);
                        readableMapBuilder.put("animationId", animationDriverValueAt.id);
                        readableMapBuilder.put("finished", false);
                        readableMapBuilder.put("value", valueAnimatedNode.nodeValue);
                        readableMapBuilder.put(TypedValues.Cycle.S_WAVE_OFFSET, valueAnimatedNode.offset);
                        WritableMap writableMap = writableMapCreateMap;
                        if (writableArrayCreateArray == null) {
                            writableArrayCreateArray = Arguments.createArray();
                        }
                        writableArrayCreateArray.pushMap(writableMap);
                    }
                } else {
                    WritableMap writableMapCreateMap2 = Arguments.createMap();
                    ReadableMapBuilder readableMapBuilder2 = new ReadableMapBuilder(writableMapCreateMap2);
                    readableMapBuilder2.put("finished", false);
                    readableMapBuilder2.put("value", valueAnimatedNode.nodeValue);
                    readableMapBuilder2.put(TypedValues.Cycle.S_WAVE_OFFSET, valueAnimatedNode.offset);
                    WritableMap writableMap2 = writableMapCreateMap2;
                    Callback callback = animationDriverValueAt.endCallback;
                    if (callback != null) {
                        callback.invoke(writableMap2);
                    }
                }
                this.activeAnimations.removeAt(i);
                i--;
            }
            i++;
        }
        if (writableArrayCreateArray == null || (reactApplicationContext = this.reactApplicationContext) == null) {
            return;
        }
        reactApplicationContext.emitDeviceEvent("onNativeAnimatedModuleAnimationFinished", writableArrayCreateArray);
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00c0, code lost:
    
        if (r3 == null) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00c2, code lost:
    
        r12 = r11.reactApplicationContext;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00c4, code lost:
    
        if (r12 == null) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00c6, code lost:
    
        r12.emitDeviceEvent("onNativeAnimatedModuleAnimationFinished", r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00cb, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:?, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void stopAnimation(int r12) {
        /*
            Method dump skipped, instruction units count: 204
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.animated.NativeAnimatedNodesManager.stopAnimation(int):void");
    }

    public final void connectAnimatedNodes(int parentNodeTag, int childNodeTag) {
        AnimatedNode animatedNode = this.animatedNodes.get(parentNodeTag);
        if (animatedNode == null) {
            throw new JSApplicationIllegalArgumentException("connectAnimatedNodes: Animated node with tag (parent) [" + parentNodeTag + "] does not exist");
        }
        AnimatedNode animatedNode2 = this.animatedNodes.get(childNodeTag);
        if (animatedNode2 == null) {
            throw new JSApplicationIllegalArgumentException("connectAnimatedNodes: Animated node with tag (child) [" + childNodeTag + "] does not exist");
        }
        animatedNode.addChild$ReactAndroid_release(animatedNode2);
        this.updatedNodes.put(childNodeTag, animatedNode2);
    }

    public final void disconnectAnimatedNodes(int parentNodeTag, int childNodeTag) {
        AnimatedNode animatedNode = this.animatedNodes.get(parentNodeTag);
        if (animatedNode == null) {
            throw new JSApplicationIllegalArgumentException("disconnectAnimatedNodes: Animated node with tag (parent) [" + parentNodeTag + "] does not exist");
        }
        AnimatedNode animatedNode2 = this.animatedNodes.get(childNodeTag);
        if (animatedNode2 == null) {
            throw new JSApplicationIllegalArgumentException("disconnectAnimatedNodes: Animated node with tag (child) [" + childNodeTag + "] does not exist");
        }
        animatedNode.removeChild$ReactAndroid_release(animatedNode2);
        this.updatedNodes.put(childNodeTag, animatedNode2);
    }

    public final void connectAnimatedNodeToView(int animatedNodeTag, int viewTag) {
        AnimatedNode animatedNode = this.animatedNodes.get(animatedNodeTag);
        if (animatedNode == null) {
            throw new JSApplicationIllegalArgumentException("connectAnimatedNodeToView: Animated node with tag [" + animatedNodeTag + "] does not exist");
        }
        if (!(animatedNode instanceof PropsAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("connectAnimatedNodeToView: Animated node connected to view [" + viewTag + "] should be of type " + PropsAnimatedNode.class.getName());
        }
        ReactApplicationContext reactApplicationContext = this.reactApplicationContext;
        if (reactApplicationContext == null) {
            throw new IllegalStateException(("connectAnimatedNodeToView: Animated node could not be connected, no ReactApplicationContext: " + viewTag).toString());
        }
        UIManager uIManagerForReactTag = UIManagerHelper.getUIManagerForReactTag(reactApplicationContext, viewTag);
        if (uIManagerForReactTag == null) {
            ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("connectAnimatedNodeToView: Animated node could not be connected to UIManager - uiManager disappeared for tag: " + viewTag));
        } else {
            ((PropsAnimatedNode) animatedNode).connectToView(viewTag, uIManagerForReactTag);
            this.updatedNodes.put(animatedNodeTag, animatedNode);
        }
    }

    public final void disconnectAnimatedNodeFromView(int animatedNodeTag, int viewTag) {
        AnimatedNode animatedNode = this.animatedNodes.get(animatedNodeTag);
        if (animatedNode == null) {
            throw new JSApplicationIllegalArgumentException("disconnectAnimatedNodeFromView: Animated node with tag [" + animatedNodeTag + "] does not exist");
        }
        if (!(animatedNode instanceof PropsAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("disconnectAnimatedNodeFromView: Animated node connected to view [" + viewTag + "] should be of type " + PropsAnimatedNode.class.getName());
        }
        ((PropsAnimatedNode) animatedNode).disconnectFromView(viewTag);
    }

    public final void getValue(int tag, Callback callback) {
        AnimatedNode animatedNode = this.animatedNodes.get(tag);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("getValue: Animated node with tag [" + tag + "] does not exist or is not a 'value' node");
        }
        double value = ((ValueAnimatedNode) animatedNode).getValue();
        if (callback != null) {
            callback.invoke(Double.valueOf(value));
            return;
        }
        if (this.reactApplicationContext == null) {
            return;
        }
        WritableMap writableMapCreateMap = Arguments.createMap();
        ReadableMapBuilder readableMapBuilder = new ReadableMapBuilder(writableMapCreateMap);
        readableMapBuilder.put("tag", tag);
        readableMapBuilder.put("value", value);
        this.reactApplicationContext.emitDeviceEvent("onNativeAnimatedModuleGetValue", writableMapCreateMap);
    }

    public final void restoreDefaultValues(int animatedNodeTag) {
        AnimatedNode animatedNode = this.animatedNodes.get(animatedNodeTag);
        if (animatedNode == null) {
            return;
        }
        if (!(animatedNode instanceof PropsAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("Animated node connected to view [?] should be of type " + PropsAnimatedNode.class.getName());
        }
        ((PropsAnimatedNode) animatedNode).restoreDefaultValues();
    }

    public final void addAnimatedEventToView(int viewTag, String eventHandlerName, ReadableMap eventMapping) {
        Intrinsics.checkNotNullParameter(eventHandlerName, "eventHandlerName");
        Intrinsics.checkNotNullParameter(eventMapping, "eventMapping");
        int i = eventMapping.getInt("animatedValueTag");
        AnimatedNode animatedNode = this.animatedNodes.get(i);
        if (animatedNode == null) {
            throw new JSApplicationIllegalArgumentException("addAnimatedEventToView: Animated node with tag [" + i + "] does not exist");
        }
        if (!(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("addAnimatedEventToView: Animated node on view [" + viewTag + "] connected to event handler (" + eventHandlerName + ") should be of type " + ValueAnimatedNode.class.getName());
        }
        ReadableArray array = eventMapping.getArray("nativeEventPath");
        if (array == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        ArrayList arrayList = new ArrayList(array.size());
        int size = array.size();
        for (int i2 = 0; i2 < size; i2++) {
            String string = array.getString(i2);
            if (string == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            arrayList.add(string);
        }
        String strNormalizeEventName = normalizeEventName(eventHandlerName);
        this.eventDrivers.add(new EventAnimationDriver(strNormalizeEventName, viewTag, arrayList, (ValueAnimatedNode) animatedNode));
        if (Intrinsics.areEqual(strNormalizeEventName, "topScroll")) {
            addAnimatedEventToView(viewTag, "topScrollEnded", eventMapping);
        }
    }

    public final void removeAnimatedEventFromView(int viewTag, String eventHandlerName, int animatedValueTag) {
        Object next;
        Intrinsics.checkNotNullParameter(eventHandlerName, "eventHandlerName");
        String strNormalizeEventName = normalizeEventName(eventHandlerName);
        Iterator<T> it = this.eventDrivers.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            EventAnimationDriver eventAnimationDriver = (EventAnimationDriver) next;
            if (Intrinsics.areEqual(strNormalizeEventName, eventAnimationDriver.eventName) && viewTag == eventAnimationDriver.viewTag && animatedValueTag == eventAnimationDriver.valueNode.tag) {
                break;
            }
        }
        EventAnimationDriver eventAnimationDriver2 = (EventAnimationDriver) next;
        if (eventAnimationDriver2 != null) {
            this.eventDrivers.remove(eventAnimationDriver2);
        }
        if (Intrinsics.areEqual(strNormalizeEventName, "topScroll")) {
            removeAnimatedEventFromView(viewTag, "topScrollEnded", animatedValueTag);
        }
    }

    @Override // com.facebook.react.uimanager.events.EventDispatcherListener
    public void onEventDispatch(final Event<?> event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (UiThreadUtil.isOnUiThread()) {
            handleEvent(event);
        } else {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.animated.NativeAnimatedNodesManager$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.handleEvent(event);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleEvent(Event<?> event) {
        if (this.eventDrivers.isEmpty()) {
            return;
        }
        Event.EventAnimationDriverMatchSpec eventAnimationDriverMatchSpec = event.getEventAnimationDriverMatchSpec();
        boolean z = false;
        for (EventAnimationDriver eventAnimationDriver : this.eventDrivers) {
            if (eventAnimationDriverMatchSpec != null && eventAnimationDriverMatchSpec.match(eventAnimationDriver.viewTag, eventAnimationDriver.eventName)) {
                stopAnimationsForNode(eventAnimationDriver.valueNode);
                event.dispatchModern(eventAnimationDriver);
                this.runUpdateNodeList.add(eventAnimationDriver.valueNode);
                z = true;
            }
        }
        if (z) {
            updateNodes(this.runUpdateNodeList);
            this.runUpdateNodeList.clear();
        }
    }

    public final void runUpdates(long frameTimeNanos) {
        ReactApplicationContext reactApplicationContext;
        UiThreadUtil.assertOnUiThread();
        int size = this.updatedNodes.size();
        for (int i = 0; i < size; i++) {
            AnimatedNode animatedNodeValueAt = this.updatedNodes.valueAt(i);
            List<AnimatedNode> list = this.runUpdateNodeList;
            Intrinsics.checkNotNull(animatedNodeValueAt);
            list.add(animatedNodeValueAt);
        }
        this.updatedNodes.clear();
        int size2 = this.activeAnimations.size();
        boolean z = false;
        for (int i2 = 0; i2 < size2; i2++) {
            AnimationDriver animationDriverValueAt = this.activeAnimations.valueAt(i2);
            animationDriverValueAt.runAnimationStep(frameTimeNanos);
            ValueAnimatedNode valueAnimatedNode = animationDriverValueAt.animatedValue;
            if (valueAnimatedNode != null) {
                this.runUpdateNodeList.add(valueAnimatedNode);
            }
            if (animationDriverValueAt.hasFinished) {
                z = true;
            }
        }
        updateNodes(this.runUpdateNodeList);
        this.runUpdateNodeList.clear();
        if (z) {
            WritableArray writableArrayCreateArray = null;
            for (int size3 = this.activeAnimations.size() - 1; -1 < size3; size3--) {
                AnimationDriver animationDriverValueAt2 = this.activeAnimations.valueAt(size3);
                if (animationDriverValueAt2.hasFinished) {
                    ValueAnimatedNode valueAnimatedNode2 = animationDriverValueAt2.animatedValue;
                    if (valueAnimatedNode2 == null) {
                        throw new IllegalStateException("Required value was null.".toString());
                    }
                    if (animationDriverValueAt2.endCallback == null) {
                        if (this.reactApplicationContext != null) {
                            WritableMap writableMapCreateMap = Arguments.createMap();
                            ReadableMapBuilder readableMapBuilder = new ReadableMapBuilder(writableMapCreateMap);
                            readableMapBuilder.put("animationId", animationDriverValueAt2.id);
                            readableMapBuilder.put("finished", true);
                            readableMapBuilder.put("value", valueAnimatedNode2.nodeValue);
                            readableMapBuilder.put(TypedValues.Cycle.S_WAVE_OFFSET, valueAnimatedNode2.offset);
                            WritableMap writableMap = writableMapCreateMap;
                            if (writableArrayCreateArray == null) {
                                writableArrayCreateArray = Arguments.createArray();
                            }
                            writableArrayCreateArray.pushMap(writableMap);
                        }
                    } else {
                        WritableMap writableMapCreateMap2 = Arguments.createMap();
                        ReadableMapBuilder readableMapBuilder2 = new ReadableMapBuilder(writableMapCreateMap2);
                        readableMapBuilder2.put("finished", true);
                        readableMapBuilder2.put("value", valueAnimatedNode2.nodeValue);
                        readableMapBuilder2.put(TypedValues.Cycle.S_WAVE_OFFSET, valueAnimatedNode2.offset);
                        WritableMap writableMap2 = writableMapCreateMap2;
                        Callback callback = animationDriverValueAt2.endCallback;
                        if (callback != null) {
                            callback.invoke(writableMap2);
                        }
                    }
                    this.activeAnimations.removeAt(size3);
                }
            }
            if (writableArrayCreateArray == null || (reactApplicationContext = this.reactApplicationContext) == null) {
                return;
            }
            reactApplicationContext.emitDeviceEvent("onNativeAnimatedModuleAnimationFinished", writableArrayCreateArray);
        }
    }

    public final Set<Integer> getTagsOfConnectedNodes$ReactAndroid_release(int tag, String eventName) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        HashSet hashSet = new HashSet();
        for (EventAnimationDriver eventAnimationDriver : this.eventDrivers) {
            if (Intrinsics.areEqual(eventName, eventAnimationDriver.eventName) && tag == eventAnimationDriver.viewTag) {
                hashSet.add(Integer.valueOf(eventAnimationDriver.viewTag));
                List<AnimatedNode> list = eventAnimationDriver.valueNode.children;
                if (list != null) {
                    Iterator<T> it = list.iterator();
                    while (it.hasNext()) {
                        hashSet.add(Integer.valueOf(((AnimatedNode) it.next()).tag));
                    }
                }
            }
        }
        return hashSet;
    }

    private final void updateNodes(List<? extends AnimatedNode> nodes) {
        List<AnimatedNode> list;
        List<AnimatedNode> list2;
        int i = this.animatedGraphBFSColor;
        int i2 = i + 1;
        this.animatedGraphBFSColor = i2;
        if (i2 == 0) {
            this.animatedGraphBFSColor = i + 2;
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        int i3 = 0;
        for (AnimatedNode animatedNode : nodes) {
            int i4 = animatedNode.BFSColor;
            int i5 = this.animatedGraphBFSColor;
            if (i4 != i5) {
                animatedNode.BFSColor = i5;
                i3++;
                arrayDeque.add(animatedNode);
            }
        }
        while (!arrayDeque.isEmpty()) {
            AnimatedNode animatedNode2 = (AnimatedNode) arrayDeque.poll();
            if (animatedNode2 != null && (list2 = animatedNode2.children) != null) {
                for (AnimatedNode animatedNode3 : list2) {
                    animatedNode3.activeIncomingNodes++;
                    int i6 = animatedNode3.BFSColor;
                    int i7 = this.animatedGraphBFSColor;
                    if (i6 != i7) {
                        animatedNode3.BFSColor = i7;
                        i3++;
                        arrayDeque.add(animatedNode3);
                    }
                }
            }
        }
        int i8 = this.animatedGraphBFSColor;
        int i9 = i8 + 1;
        this.animatedGraphBFSColor = i9;
        if (i9 == 0) {
            this.animatedGraphBFSColor = i8 + 2;
        }
        int i10 = 0;
        for (AnimatedNode animatedNode4 : nodes) {
            if (animatedNode4.activeIncomingNodes == 0) {
                int i11 = animatedNode4.BFSColor;
                int i12 = this.animatedGraphBFSColor;
                if (i11 != i12) {
                    animatedNode4.BFSColor = i12;
                    i10++;
                    arrayDeque.add(animatedNode4);
                }
            }
        }
        int i13 = 0;
        while (!arrayDeque.isEmpty()) {
            AnimatedNode animatedNode5 = (AnimatedNode) arrayDeque.poll();
            if (animatedNode5 != null) {
                try {
                    animatedNode5.update$ReactAndroid_release();
                } catch (JSApplicationCausedNativeException e) {
                    FLog.e(TAG, "Native animation workaround, frame lost as result of race condition", e);
                }
            }
            if (animatedNode5 instanceof PropsAnimatedNode) {
                ((PropsAnimatedNode) animatedNode5).updateView();
            }
            if (animatedNode5 instanceof ValueAnimatedNode) {
                ((ValueAnimatedNode) animatedNode5).onValueUpdate();
            }
            if (animatedNode5 != null && (list = animatedNode5.children) != null) {
                for (AnimatedNode animatedNode6 : list) {
                    animatedNode6.activeIncomingNodes--;
                    if (animatedNode6.BFSColor != this.animatedGraphBFSColor && animatedNode6.activeIncomingNodes == 0) {
                        animatedNode6.BFSColor = this.animatedGraphBFSColor;
                        i10++;
                        arrayDeque.add(animatedNode6);
                    } else if (animatedNode6.BFSColor == this.animatedGraphBFSColor) {
                        i13++;
                    }
                }
            }
        }
        if (i3 != i10) {
            if (this.warnedAboutGraphTraversal) {
                return;
            }
            this.warnedAboutGraphTraversal = true;
            FLog.e(TAG, "Detected animation cycle or disconnected graph. ");
            Iterator<? extends AnimatedNode> it = nodes.iterator();
            while (it.hasNext()) {
                FLog.e(TAG, it.next().prettyPrintWithChildren$ReactAndroid_release());
            }
            IllegalStateException illegalStateException = new IllegalStateException("Looks like animated nodes graph has " + (i13 > 0 ? "cycles (" + i13 + ")" : "disconnected regions") + ", there are " + i3 + " but toposort visited only " + i10);
            boolean z = this.eventListenerInitializedForFabric;
            if (z && i13 == 0) {
                ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException(illegalStateException));
                return;
            } else {
                if (z) {
                    ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException(illegalStateException));
                    return;
                }
                throw illegalStateException;
            }
        }
        this.warnedAboutGraphTraversal = false;
    }

    private final String normalizeEventName(String eventHandlerName) {
        if (!StringsKt.startsWith$default(eventHandlerName, "on", false, 2, (Object) null)) {
            return eventHandlerName;
        }
        String strSubstring = eventHandlerName.substring(2);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return "top" + strSubstring;
    }

    /* JADX INFO: compiled from: NativeAnimatedNodesManager.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/facebook/react/animated/NativeAnimatedNodesManager$Companion;", "", "<init>", "()V", "TAG", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
