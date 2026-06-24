package com.facebook.react.runtime;

import android.app.Activity;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.LifecycleState;
import io.sentry.protocol.SentryThread;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactLifecycleStateManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0007J\u001c\u0010\u0011\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0007J\u001c\u0010\u0012\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0007J\u0012\u0010\u0013\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/facebook/react/runtime/ReactLifecycleStateManager;", "", "stateTracker", "Lcom/facebook/react/runtime/ReactHostStateTracker;", "<init>", "(Lcom/facebook/react/runtime/ReactHostStateTracker;)V", SentryThread.JsonKeys.STATE, "Lcom/facebook/react/common/LifecycleState;", "lifecycleState", "getLifecycleState", "()Lcom/facebook/react/common/LifecycleState;", "resumeReactContextIfHostResumed", "", "currentContext", "Lcom/facebook/react/bridge/ReactContext;", "activity", "Landroid/app/Activity;", "moveToOnHostResume", "moveToOnHostPause", "moveToOnHostDestroy", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ReactLifecycleStateManager {
    private LifecycleState state;
    private final ReactHostStateTracker stateTracker;

    /* JADX INFO: compiled from: ReactLifecycleStateManager.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LifecycleState.values().length];
            try {
                iArr[LifecycleState.BEFORE_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LifecycleState.RESUMED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[LifecycleState.BEFORE_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public ReactLifecycleStateManager(ReactHostStateTracker stateTracker) {
        Intrinsics.checkNotNullParameter(stateTracker, "stateTracker");
        this.stateTracker = stateTracker;
        this.state = LifecycleState.BEFORE_CREATE;
    }

    /* JADX INFO: renamed from: getLifecycleState, reason: from getter */
    public final LifecycleState getState() {
        return this.state;
    }

    public final void resumeReactContextIfHostResumed(ReactContext currentContext, Activity activity) {
        Intrinsics.checkNotNullParameter(currentContext, "currentContext");
        if (this.state == LifecycleState.RESUMED) {
            ReactHostStateTracker.enterState$default(this.stateTracker, "ReactContext.onHostResume()", null, 2, null);
            currentContext.onHostResume(activity);
        }
    }

    public final void moveToOnHostResume(ReactContext currentContext, Activity activity) {
        if (this.state == LifecycleState.RESUMED) {
            return;
        }
        if (currentContext != null) {
            ReactHostStateTracker.enterState$default(this.stateTracker, "ReactContext.onHostResume()", null, 2, null);
            currentContext.onHostResume(activity);
        }
        this.state = LifecycleState.RESUMED;
    }

    public final void moveToOnHostPause(ReactContext currentContext, Activity activity) {
        if (currentContext != null) {
            int i = WhenMappings.$EnumSwitchMapping$0[this.state.ordinal()];
            if (i == 1) {
                ReactHostStateTracker.enterState$default(this.stateTracker, "ReactContext.onHostResume()", null, 2, null);
                currentContext.onHostResume(activity);
                ReactHostStateTracker.enterState$default(this.stateTracker, "ReactContext.onHostPause()", null, 2, null);
                currentContext.onHostPause();
            } else if (i == 2) {
                ReactHostStateTracker.enterState$default(this.stateTracker, "ReactContext.onHostPause()", null, 2, null);
                currentContext.onHostPause();
            }
        }
        this.state = LifecycleState.BEFORE_RESUME;
    }

    public final void moveToOnHostDestroy(ReactContext currentContext) {
        if (currentContext != null) {
            int i = WhenMappings.$EnumSwitchMapping$0[this.state.ordinal()];
            if (i == 2) {
                ReactHostStateTracker.enterState$default(this.stateTracker, "ReactContext.onHostPause()", null, 2, null);
                currentContext.onHostPause();
                ReactHostStateTracker.enterState$default(this.stateTracker, "ReactContext.onHostDestroy()", null, 2, null);
                currentContext.onHostDestroy();
            } else if (i == 3) {
                ReactHostStateTracker.enterState$default(this.stateTracker, "ReactContext.onHostDestroy()", null, 2, null);
                currentContext.onHostDestroy();
            }
        }
        this.state = LifecycleState.BEFORE_CREATE;
    }
}
