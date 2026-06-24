package com.facebook.react.fabric.mounting;

import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.RetryableMountingLayerException;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.fabric.events.EventEmitterWrapper;
import com.facebook.react.fabric.mounting.mountitems.MountItem;
import com.facebook.react.touch.JSResponderHandler;
import com.facebook.react.uimanager.RootViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.facebook.yoga.YogaMeasureMode;
import io.sentry.protocol.Message;
import io.sentry.protocol.SentryThread;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MountingManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 L2\u00020\u0001:\u0002KLB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J$\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0007J$\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001c\u001a\u0004\u0018\u00010\u0017H\u0007J\u0010\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\nH\u0007J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0015\u001a\u00020\nJ\u0016\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020#2\u0006\u0010\u0015\u001a\u00020\nJ\u000e\u0010$\u001a\u00020#2\u0006\u0010\u0015\u001a\u00020\nJ\u0010\u0010%\u001a\u0004\u0018\u00010\u000b2\u0006\u0010&\u001a\u00020\nJ\u0010\u0010'\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020\nH\u0007J\u000e\u0010(\u001a\u00020#2\u0006\u0010&\u001a\u00020\nJ(\u0010)\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010&\u001a\u00020\n2\u0006\u0010*\u001a\u00020\n2\u0006\u0010+\u001a\u00020,H\u0007J(\u0010)\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010&\u001a\u00020\n2\b\u0010*\u001a\u0004\u0018\u00010!2\u0006\u0010+\u001a\u00020,J\u001e\u0010-\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010&\u001a\u00020\n2\u0006\u0010.\u001a\u00020\nJ\u001a\u0010/\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020\n2\b\u00100\u001a\u0004\u0018\u000101H\u0007J\u001a\u00102\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020\n2\b\u00100\u001a\u0004\u0018\u000101H\u0007J\b\u00103\u001a\u00020\u001bH\u0007J\u001a\u00104\u001a\u0004\u0018\u0001052\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010&\u001a\u00020\nH\u0007Jh\u00106\u001a\u0002072\b\u0010 \u001a\u0004\u0018\u0001082\b\u00109\u001a\u0004\u0018\u00010!2\b\u0010:\u001a\u0004\u0018\u0001012\b\u00100\u001a\u0004\u0018\u0001012\b\u0010;\u001a\u0004\u0018\u0001012\u0006\u0010<\u001a\u00020=2\b\u0010>\u001a\u0004\u0018\u00010?2\u0006\u0010@\u001a\u00020=2\b\u0010A\u001a\u0004\u0018\u00010?2\b\u0010B\u001a\u0004\u0018\u00010CH\u0007J:\u0010D\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010&\u001a\u00020\n2\b\u0010E\u001a\u0004\u0018\u00010!2\u0006\u0010F\u001a\u00020#2\b\u0010G\u001a\u0004\u0018\u00010H2\u0006\u0010I\u001a\u00020\nJ\u001a\u0010J\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010&\u001a\u00020\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006M"}, d2 = {"Lcom/facebook/react/fabric/mounting/MountingManager;", "", "viewManagerRegistry", "Lcom/facebook/react/uimanager/ViewManagerRegistry;", "mountItemExecutor", "Lcom/facebook/react/fabric/mounting/MountingManager$MountItemExecutor;", "<init>", "(Lcom/facebook/react/uimanager/ViewManagerRegistry;Lcom/facebook/react/fabric/mounting/MountingManager$MountItemExecutor;)V", "surfaceIdToManager", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/facebook/react/fabric/mounting/SurfaceMountingManager;", "stoppedSurfaceIds", "Ljava/util/concurrent/CopyOnWriteArrayList;", "mostRecentSurfaceMountingManager", "lastQueriedSurfaceMountingManager", "jsResponderHandler", "Lcom/facebook/react/touch/JSResponderHandler;", "rootViewManager", "Lcom/facebook/react/uimanager/RootViewManager;", "startSurface", "surfaceId", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "rootView", "Landroid/view/View;", "attachRootView", "", "themedReactContext", "stopSurface", "getSurfaceManager", "getSurfaceManagerEnforced", "context", "", "surfaceIsStopped", "", "isWaitingForViewAttach", "getSurfaceManagerForView", "reactTag", "getSurfaceManagerForViewEnforced", "getViewExists", "receiveCommand", "commandId", "commandArgs", "Lcom/facebook/react/bridge/ReadableArray;", "sendAccessibilityEvent", "eventType", "storeSynchronousMountPropsOverride", "props", "Lcom/facebook/react/bridge/ReadableMap;", "updatePropsSynchronously", "clearJSResponder", "getEventEmitter", "Lcom/facebook/react/fabric/events/EventEmitterWrapper;", "measure", "", "Lcom/facebook/react/bridge/ReactContext;", "componentName", "localData", SentryThread.JsonKeys.STATE, "width", "", "widthMode", "Lcom/facebook/yoga/YogaMeasureMode;", "height", "heightMode", "attachmentsPositions", "", "enqueuePendingEvent", "eventName", "canCoalesceEvent", Message.JsonKeys.PARAMS, "Lcom/facebook/react/bridge/WritableMap;", "eventCategory", "getSurfaceMountingManager", "MountItemExecutor", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class MountingManager {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int MAX_STOPPED_SURFACE_IDS_LENGTH = 15;
    private static final String TAG;
    private final JSResponderHandler jsResponderHandler;
    private SurfaceMountingManager lastQueriedSurfaceMountingManager;
    private SurfaceMountingManager mostRecentSurfaceMountingManager;
    private final MountItemExecutor mountItemExecutor;
    private final RootViewManager rootViewManager;
    private final CopyOnWriteArrayList<Integer> stoppedSurfaceIds;
    private final ConcurrentHashMap<Integer, SurfaceMountingManager> surfaceIdToManager;
    private final ViewManagerRegistry viewManagerRegistry;

    /* JADX INFO: compiled from: MountingManager.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bà\u0080\u0001\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H'ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Lcom/facebook/react/fabric/mounting/MountingManager$MountItemExecutor;", "", "executeItems", "", "items", "Ljava/util/Queue;", "Lcom/facebook/react/fabric/mounting/mountitems/MountItem;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface MountItemExecutor {
        void executeItems(Queue<MountItem> items);
    }

    public MountingManager(ViewManagerRegistry viewManagerRegistry, MountItemExecutor mountItemExecutor) {
        Intrinsics.checkNotNullParameter(viewManagerRegistry, "viewManagerRegistry");
        Intrinsics.checkNotNullParameter(mountItemExecutor, "mountItemExecutor");
        this.viewManagerRegistry = viewManagerRegistry;
        this.mountItemExecutor = mountItemExecutor;
        this.surfaceIdToManager = new ConcurrentHashMap<>();
        this.stoppedSurfaceIds = new CopyOnWriteArrayList<>();
        this.jsResponderHandler = new JSResponderHandler();
        this.rootViewManager = new RootViewManager();
    }

    public final SurfaceMountingManager startSurface(int surfaceId, ThemedReactContext reactContext, View rootView) {
        JSResponderHandler jSResponderHandler = this.jsResponderHandler;
        ViewManagerRegistry viewManagerRegistry = this.viewManagerRegistry;
        RootViewManager rootViewManager = this.rootViewManager;
        MountItemExecutor mountItemExecutor = this.mountItemExecutor;
        if (reactContext != null) {
            SurfaceMountingManager surfaceMountingManager = new SurfaceMountingManager(surfaceId, jSResponderHandler, viewManagerRegistry, rootViewManager, mountItemExecutor, reactContext);
            this.surfaceIdToManager.putIfAbsent(Integer.valueOf(surfaceId), surfaceMountingManager);
            if (this.surfaceIdToManager.get(Integer.valueOf(surfaceId)) != surfaceMountingManager) {
                ReactSoftExceptionLogger.logSoftException(TAG, new IllegalStateException("Called startSurface more than once for the SurfaceId [" + surfaceId + "]"));
            }
            this.mostRecentSurfaceMountingManager = this.surfaceIdToManager.get(Integer.valueOf(surfaceId));
            if (rootView != null) {
                surfaceMountingManager.attachRootView(rootView, reactContext);
            }
            return surfaceMountingManager;
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    public final void attachRootView(int surfaceId, View rootView, ThemedReactContext themedReactContext) {
        SurfaceMountingManager surfaceManagerEnforced = getSurfaceManagerEnforced(surfaceId, "attachView");
        if (surfaceManagerEnforced.isStopped()) {
            ReactSoftExceptionLogger.logSoftException(TAG, new IllegalStateException("Trying to attach a view to a stopped surface"));
        } else {
            surfaceManagerEnforced.attachRootView(rootView, themedReactContext);
        }
    }

    public final void stopSurface(int surfaceId) {
        SurfaceMountingManager surfaceMountingManager = this.surfaceIdToManager.get(Integer.valueOf(surfaceId));
        if (surfaceMountingManager != null) {
            while (this.stoppedSurfaceIds.size() >= 15) {
                Integer num = this.stoppedSurfaceIds.get(0);
                if (num == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                this.surfaceIdToManager.remove(num);
                this.stoppedSurfaceIds.remove(num);
                FLog.d(TAG, "Removing stale SurfaceMountingManager: [%d]", num);
            }
            this.stoppedSurfaceIds.add(Integer.valueOf(surfaceId));
            surfaceMountingManager.stopSurface();
            if (this.mostRecentSurfaceMountingManager == surfaceMountingManager) {
                this.mostRecentSurfaceMountingManager = null;
            }
            if (this.lastQueriedSurfaceMountingManager == surfaceMountingManager) {
                this.lastQueriedSurfaceMountingManager = null;
                return;
            }
            return;
        }
        ReactSoftExceptionLogger.logSoftException(TAG, new IllegalStateException("Cannot call stopSurface on non-existent surface: [" + surfaceId + "]"));
    }

    public final SurfaceMountingManager getSurfaceManager(int surfaceId) {
        SurfaceMountingManager surfaceMountingManager = this.lastQueriedSurfaceMountingManager;
        if (surfaceMountingManager != null && surfaceMountingManager.getSurfaceId() == surfaceId) {
            return this.lastQueriedSurfaceMountingManager;
        }
        SurfaceMountingManager surfaceMountingManager2 = this.mostRecentSurfaceMountingManager;
        if (surfaceMountingManager2 != null && surfaceMountingManager2.getSurfaceId() == surfaceId) {
            return this.mostRecentSurfaceMountingManager;
        }
        SurfaceMountingManager surfaceMountingManager3 = this.surfaceIdToManager.get(Integer.valueOf(surfaceId));
        this.lastQueriedSurfaceMountingManager = surfaceMountingManager3;
        return surfaceMountingManager3;
    }

    public final SurfaceMountingManager getSurfaceManagerEnforced(int surfaceId, String context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SurfaceMountingManager surfaceManager = getSurfaceManager(surfaceId);
        if (surfaceManager != null) {
            return surfaceManager;
        }
        throw new RetryableMountingLayerException("Unable to find SurfaceMountingManager for surfaceId: [" + surfaceId + "]. Context: " + context);
    }

    public final boolean surfaceIsStopped(int surfaceId) {
        if (this.stoppedSurfaceIds.contains(Integer.valueOf(surfaceId))) {
            return true;
        }
        SurfaceMountingManager surfaceManager = getSurfaceManager(surfaceId);
        return surfaceManager != null && surfaceManager.isStopped();
    }

    public final boolean isWaitingForViewAttach(int surfaceId) {
        SurfaceMountingManager surfaceManager = getSurfaceManager(surfaceId);
        if (surfaceManager == null || surfaceManager.isStopped()) {
            return false;
        }
        return !surfaceManager.isRootViewAttached();
    }

    public final SurfaceMountingManager getSurfaceManagerForView(int reactTag) {
        SurfaceMountingManager surfaceMountingManager = this.mostRecentSurfaceMountingManager;
        if (surfaceMountingManager != null && surfaceMountingManager.getViewExists(reactTag)) {
            return this.mostRecentSurfaceMountingManager;
        }
        Iterator<Map.Entry<Integer, SurfaceMountingManager>> it = this.surfaceIdToManager.entrySet().iterator();
        while (it.hasNext()) {
            SurfaceMountingManager value = it.next().getValue();
            if (value != this.mostRecentSurfaceMountingManager && value.getViewExists(reactTag)) {
                if (this.mostRecentSurfaceMountingManager == null) {
                    this.mostRecentSurfaceMountingManager = value;
                }
                return value;
            }
        }
        return null;
    }

    public final SurfaceMountingManager getSurfaceManagerForViewEnforced(int reactTag) {
        SurfaceMountingManager surfaceManagerForView = getSurfaceManagerForView(reactTag);
        if (surfaceManagerForView != null) {
            return surfaceManagerForView;
        }
        throw new RetryableMountingLayerException("Unable to find SurfaceMountingManager for tag: [" + reactTag + "]");
    }

    public final boolean getViewExists(int reactTag) {
        return getSurfaceManagerForView(reactTag) != null;
    }

    @Deprecated(message = "receiveCommand with Int is deprecated, you should use receiveCommand with commandId:String", replaceWith = @ReplaceWith(expression = "receiveCommand(Int,Int,String,ReadableArray)", imports = {}))
    public final void receiveCommand(int surfaceId, int reactTag, int commandId, ReadableArray commandArgs) {
        Intrinsics.checkNotNullParameter(commandArgs, "commandArgs");
        UiThreadUtil.assertOnUiThread();
        getSurfaceManagerEnforced(surfaceId, "receiveCommand:int").receiveCommand(reactTag, commandId, commandArgs);
    }

    public final void receiveCommand(int surfaceId, int reactTag, String commandId, ReadableArray commandArgs) {
        Intrinsics.checkNotNullParameter(commandArgs, "commandArgs");
        UiThreadUtil.assertOnUiThread();
        SurfaceMountingManager surfaceManagerEnforced = getSurfaceManagerEnforced(surfaceId, "receiveCommand:string");
        if (commandId == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        surfaceManagerEnforced.receiveCommand(reactTag, commandId, commandArgs);
    }

    public final void sendAccessibilityEvent(int surfaceId, int reactTag, int eventType) {
        UiThreadUtil.assertOnUiThread();
        if (surfaceId == -1) {
            getSurfaceManagerForViewEnforced(reactTag).sendAccessibilityEvent(reactTag, eventType);
        } else {
            getSurfaceManagerEnforced(surfaceId, "sendAccessibilityEvent").sendAccessibilityEvent(reactTag, eventType);
        }
    }

    public final void storeSynchronousMountPropsOverride(int reactTag, ReadableMap props) {
        UiThreadUtil.assertOnUiThread();
        if (props == null) {
            return;
        }
        getSurfaceManagerForViewEnforced(reactTag).storeSynchronousMountPropsOverride(reactTag, props);
    }

    public final void updatePropsSynchronously(int reactTag, ReadableMap props) {
        UiThreadUtil.assertOnUiThread();
        if (props == null) {
            return;
        }
        getSurfaceManagerForViewEnforced(reactTag).updatePropsSynchronously(reactTag, props);
    }

    public final void clearJSResponder() {
        this.jsResponderHandler.clearJSResponder();
    }

    public final EventEmitterWrapper getEventEmitter(int surfaceId, int reactTag) {
        SurfaceMountingManager surfaceMountingManager = getSurfaceMountingManager(surfaceId, reactTag);
        if (surfaceMountingManager != null) {
            return surfaceMountingManager.getEventEmitter(reactTag);
        }
        return null;
    }

    public final long measure(ReactContext context, String componentName, ReadableMap localData, ReadableMap props, ReadableMap readableMap, float width, YogaMeasureMode widthMode, float height, YogaMeasureMode heightMode, float[] attachmentsPositions) {
        ViewManagerRegistry viewManagerRegistry = this.viewManagerRegistry;
        if (componentName == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        return viewManagerRegistry.get(componentName).measure(context, localData, props, readableMap, width, widthMode, height, heightMode, attachmentsPositions);
    }

    public final void enqueuePendingEvent(int surfaceId, int reactTag, String eventName, boolean canCoalesceEvent, WritableMap writableMap, int eventCategory) {
        SurfaceMountingManager surfaceMountingManager = getSurfaceMountingManager(surfaceId, reactTag);
        if (surfaceMountingManager == null) {
            FLog.d(TAG, "Cannot queue event without valid surface mounting manager for tag: %d, surfaceId: %d", Integer.valueOf(reactTag), Integer.valueOf(surfaceId));
        } else {
            surfaceMountingManager.enqueuePendingEvent(reactTag, eventName, canCoalesceEvent, writableMap, eventCategory);
        }
    }

    private final SurfaceMountingManager getSurfaceMountingManager(int surfaceId, int reactTag) {
        if (surfaceId == -1) {
            return getSurfaceManagerForView(reactTag);
        }
        return getSurfaceManager(surfaceId);
    }

    /* JADX INFO: compiled from: MountingManager.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/facebook/react/fabric/mounting/MountingManager$Companion;", "", "<init>", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "MAX_STOPPED_SURFACE_IDS_LENGTH", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return MountingManager.TAG;
        }
    }

    static {
        Intrinsics.checkNotNullExpressionValue("MountingManager", "getSimpleName(...)");
        TAG = "MountingManager";
    }
}
