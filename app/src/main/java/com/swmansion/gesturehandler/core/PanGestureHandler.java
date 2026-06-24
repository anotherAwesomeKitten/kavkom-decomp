package com.swmansion.gesturehandler.core;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.swmansion.gesturehandler.core.GestureHandler;
import com.swmansion.gesturehandler.react.eventbuilders.PanGestureHandlerEventDataBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PanGestureHandler.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 C2\u00020\u0001:\u0002BCB\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u00105\u001a\u000206H\u0016J\b\u00107\u001a\u00020*H\u0002J\b\u00108\u001a\u00020*H\u0002J\u0018\u00109\u001a\u0002062\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020;H\u0014J\u0010\u0010=\u001a\u0002062\u0006\u0010>\u001a\u00020*H\u0016J\b\u0010?\u001a\u000206H\u0014J\b\u0010@\u001a\u000206H\u0014J\b\u0010A\u001a\u000206H\u0016R\u001e\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\r\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\nR\u0011\u0010\u000f\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\nR\u000e\u0010\u0011\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u0004\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u00102\u001a\u0002012\u0006\u0010\u0006\u001a\u000201@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b3\u00104¨\u0006D"}, d2 = {"Lcom/swmansion/gesturehandler/core/PanGestureHandler;", "Lcom/swmansion/gesturehandler/core/GestureHandler;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "value", "", "velocityX", "getVelocityX", "()F", "velocityY", "getVelocityY", "translationX", "getTranslationX", "translationY", "getTranslationY", "defaultMinDist", "minDist", "activeOffsetXStart", "activeOffsetXEnd", "failOffsetXStart", "failOffsetXEnd", "activeOffsetYStart", "activeOffsetYEnd", "failOffsetYStart", "failOffsetYEnd", "minVelocityX", "minVelocityY", "minVelocity", "minPointers", "", "maxPointers", "startX", "startY", "offsetX", "offsetY", "lastX", "lastY", "velocityTracker", "Landroid/view/VelocityTracker;", "averageTouches", "", "activateAfterLongPress", "", "activateDelayed", "Ljava/lang/Runnable;", "handler", "Landroid/os/Handler;", "Lcom/swmansion/gesturehandler/core/StylusData;", "stylusData", "getStylusData", "()Lcom/swmansion/gesturehandler/core/StylusData;", "resetConfig", "", "shouldActivate", "shouldFail", "onHandle", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "sourceEvent", "activate", "force", "onCancel", "onReset", "resetProgress", "Factory", "Companion", "react-native-gesture-handler_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PanGestureHandler extends GestureHandler {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long DEFAULT_ACTIVATE_AFTER_LONG_PRESS = 0;
    private static final float DEFAULT_ACTIVE_OFFSET_X_END = Float.MIN_VALUE;
    private static final float DEFAULT_ACTIVE_OFFSET_X_START = Float.MAX_VALUE;
    private static final float DEFAULT_ACTIVE_OFFSET_Y_END = Float.MIN_VALUE;
    private static final float DEFAULT_ACTIVE_OFFSET_Y_START = Float.MAX_VALUE;
    private static final boolean DEFAULT_AVERAGE_TOUCHES = false;
    private static final float DEFAULT_FAIL_OFFSET_X_END = Float.MAX_VALUE;
    private static final float DEFAULT_FAIL_OFFSET_X_START = Float.MIN_VALUE;
    private static final float DEFAULT_FAIL_OFFSET_Y_END = Float.MAX_VALUE;
    private static final float DEFAULT_FAIL_OFFSET_Y_START = Float.MIN_VALUE;
    private static final int DEFAULT_MAX_POINTERS = 10;
    private static final int DEFAULT_MIN_POINTERS = 1;
    private static final float DEFAULT_MIN_VELOCITY = Float.MAX_VALUE;
    private static final float DEFAULT_MIN_VELOCITY_X = Float.MAX_VALUE;
    private static final float DEFAULT_MIN_VELOCITY_Y = Float.MAX_VALUE;
    private static final float MAX_VALUE_IGNORE = Float.MIN_VALUE;
    private static final float MIN_VALUE_IGNORE = Float.MAX_VALUE;
    private long activateAfterLongPress;
    private boolean averageTouches;
    private final float defaultMinDist;
    private Handler handler;
    private float lastX;
    private float lastY;
    private float minDist;
    private float offsetX;
    private float offsetY;
    private float startX;
    private float startY;
    private VelocityTracker velocityTracker;
    private float velocityX;
    private float velocityY;
    private float activeOffsetXStart = Float.MAX_VALUE;
    private float activeOffsetXEnd = Float.MIN_VALUE;
    private float failOffsetXStart = Float.MIN_VALUE;
    private float failOffsetXEnd = Float.MAX_VALUE;
    private float activeOffsetYStart = Float.MAX_VALUE;
    private float activeOffsetYEnd = Float.MIN_VALUE;
    private float failOffsetYStart = Float.MIN_VALUE;
    private float failOffsetYEnd = Float.MAX_VALUE;
    private float minVelocityX = Float.MAX_VALUE;
    private float minVelocityY = Float.MAX_VALUE;
    private float minVelocity = Float.MAX_VALUE;
    private int minPointers = 1;
    private int maxPointers = 10;
    private final Runnable activateDelayed = new Runnable() { // from class: com.swmansion.gesturehandler.core.PanGestureHandler$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.activate();
        }
    };
    private StylusData stylusData = new StylusData(0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 31, null);

    public PanGestureHandler(Context context) {
        this.minDist = Float.MIN_VALUE;
        Intrinsics.checkNotNull(context);
        float scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.defaultMinDist = scaledTouchSlop;
        this.minDist = scaledTouchSlop;
    }

    public final float getVelocityX() {
        return this.velocityX;
    }

    public final float getVelocityY() {
        return this.velocityY;
    }

    public final float getTranslationX() {
        return (this.lastX - this.startX) + this.offsetX;
    }

    public final float getTranslationY() {
        return (this.lastY - this.startY) + this.offsetY;
    }

    public final StylusData getStylusData() {
        return this.stylusData;
    }

    @Override // com.swmansion.gesturehandler.core.GestureHandler
    public void resetConfig() {
        super.resetConfig();
        this.activeOffsetXStart = Float.MAX_VALUE;
        this.activeOffsetXEnd = Float.MIN_VALUE;
        this.failOffsetXStart = Float.MIN_VALUE;
        this.failOffsetXEnd = Float.MAX_VALUE;
        this.activeOffsetYStart = Float.MAX_VALUE;
        this.activeOffsetYEnd = Float.MIN_VALUE;
        this.failOffsetYStart = Float.MIN_VALUE;
        this.failOffsetYEnd = Float.MAX_VALUE;
        this.minVelocityX = Float.MAX_VALUE;
        this.minVelocityY = Float.MAX_VALUE;
        this.minVelocity = Float.MAX_VALUE;
        this.minDist = this.defaultMinDist;
        this.minPointers = 1;
        this.maxPointers = 10;
        this.activateAfterLongPress = 0L;
        this.averageTouches = false;
    }

    private final boolean shouldActivate() {
        float f = (this.lastX - this.startX) + this.offsetX;
        float f2 = this.activeOffsetXStart;
        if (f2 != Float.MAX_VALUE && f < f2) {
            return true;
        }
        float f3 = this.activeOffsetXEnd;
        if (f3 != Float.MIN_VALUE && f > f3) {
            return true;
        }
        float f4 = (this.lastY - this.startY) + this.offsetY;
        float f5 = this.activeOffsetYStart;
        if (f5 != Float.MAX_VALUE && f4 < f5) {
            return true;
        }
        float f6 = this.activeOffsetYEnd;
        if (f6 != Float.MIN_VALUE && f4 > f6) {
            return true;
        }
        float f7 = (f * f) + (f4 * f4);
        float f8 = this.minDist;
        if (f8 != Float.MAX_VALUE && f7 >= f8 * f8) {
            return true;
        }
        float f9 = this.velocityX;
        float f10 = this.minVelocityX;
        if (f10 != Float.MAX_VALUE && ((f10 < 0.0f && f9 <= f10) || (0.0f <= f10 && f10 <= f9))) {
            return true;
        }
        float f11 = this.velocityY;
        float f12 = this.minVelocityY;
        if (f12 != Float.MAX_VALUE && ((f12 < 0.0f && f9 <= f12) || (0.0f <= f12 && f12 <= f9))) {
            return true;
        }
        float f13 = (f9 * f9) + (f11 * f11);
        float f14 = this.minVelocity;
        return f14 != Float.MAX_VALUE && f13 >= f14 * f14;
    }

    private final boolean shouldFail() {
        float f = (this.lastX - this.startX) + this.offsetX;
        float f2 = (this.lastY - this.startY) + this.offsetY;
        if (this.activateAfterLongPress > 0) {
            float f3 = (f * f) + (f2 * f2);
            float f4 = this.defaultMinDist;
            if (f3 > f4 * f4) {
                Handler handler = this.handler;
                if (handler != null) {
                    handler.removeCallbacksAndMessages(null);
                }
                return true;
            }
        }
        float f5 = this.failOffsetXStart;
        if (f5 != Float.MIN_VALUE && f < f5) {
            return true;
        }
        float f6 = this.failOffsetXEnd;
        if (f6 != Float.MAX_VALUE && f > f6) {
            return true;
        }
        float f7 = this.failOffsetYStart;
        if (f7 != Float.MIN_VALUE && f2 < f7) {
            return true;
        }
        float f8 = this.failOffsetYEnd;
        return f8 != Float.MAX_VALUE && f2 > f8;
    }

    @Override // com.swmansion.gesturehandler.core.GestureHandler
    protected void onHandle(MotionEvent motionEvent, MotionEvent sourceEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        Intrinsics.checkNotNullParameter(sourceEvent, "sourceEvent");
        if (shouldActivateWithMouse(sourceEvent)) {
            if (motionEvent.getToolType(0) == 2) {
                this.stylusData = StylusData.INSTANCE.fromEvent(motionEvent);
            }
            int state = getState();
            int actionMasked = sourceEvent.getActionMasked();
            if (actionMasked == 5 || actionMasked == 6) {
                this.offsetX += this.lastX - this.startX;
                this.offsetY += this.lastY - this.startY;
                this.lastX = GestureUtils.INSTANCE.getLastPointerX(sourceEvent, this.averageTouches);
                float lastPointerY = GestureUtils.INSTANCE.getLastPointerY(sourceEvent, this.averageTouches);
                this.lastY = lastPointerY;
                this.startX = this.lastX;
                this.startY = lastPointerY;
            } else {
                this.lastX = GestureUtils.INSTANCE.getLastPointerX(sourceEvent, this.averageTouches);
                this.lastY = GestureUtils.INSTANCE.getLastPointerY(sourceEvent, this.averageTouches);
            }
            if (state == 0 && sourceEvent.getPointerCount() >= this.minPointers) {
                resetProgress();
                this.offsetX = 0.0f;
                this.offsetY = 0.0f;
                this.velocityX = 0.0f;
                this.velocityY = 0.0f;
                VelocityTracker velocityTrackerObtain = VelocityTracker.obtain();
                this.velocityTracker = velocityTrackerObtain;
                INSTANCE.addVelocityMovement(velocityTrackerObtain, sourceEvent);
                begin();
                if (this.activateAfterLongPress > 0) {
                    if (this.handler == null) {
                        this.handler = new Handler(Looper.getMainLooper());
                    }
                    Handler handler = this.handler;
                    Intrinsics.checkNotNull(handler);
                    handler.postDelayed(this.activateDelayed, this.activateAfterLongPress);
                }
            } else {
                VelocityTracker velocityTracker = this.velocityTracker;
                if (velocityTracker != null) {
                    INSTANCE.addVelocityMovement(velocityTracker, sourceEvent);
                    VelocityTracker velocityTracker2 = this.velocityTracker;
                    Intrinsics.checkNotNull(velocityTracker2);
                    velocityTracker2.computeCurrentVelocity(1000);
                    VelocityTracker velocityTracker3 = this.velocityTracker;
                    Intrinsics.checkNotNull(velocityTracker3);
                    this.velocityX = velocityTracker3.getXVelocity();
                    VelocityTracker velocityTracker4 = this.velocityTracker;
                    Intrinsics.checkNotNull(velocityTracker4);
                    this.velocityY = velocityTracker4.getYVelocity();
                }
            }
            if (actionMasked == 1 || actionMasked == 12) {
                if (state == 4) {
                    end();
                    return;
                } else {
                    fail();
                    return;
                }
            }
            if (actionMasked == 5 && sourceEvent.getPointerCount() > this.maxPointers) {
                if (state == 4) {
                    cancel();
                    return;
                } else {
                    fail();
                    return;
                }
            }
            if (actionMasked == 6 && state == 4 && sourceEvent.getPointerCount() < this.minPointers) {
                fail();
                return;
            }
            if (state == 2) {
                if (shouldFail()) {
                    fail();
                } else if (shouldActivate()) {
                    activate();
                }
            }
        }
    }

    @Override // com.swmansion.gesturehandler.core.GestureHandler
    public void activate(boolean force) {
        if (getState() != 4) {
            resetProgress();
        }
        super.activate(force);
    }

    @Override // com.swmansion.gesturehandler.core.GestureHandler
    protected void onCancel() {
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.swmansion.gesturehandler.core.GestureHandler
    protected void onReset() {
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.velocityTracker = null;
        }
        this.stylusData = new StylusData(0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 31, null);
    }

    @Override // com.swmansion.gesturehandler.core.GestureHandler
    public void resetProgress() {
        this.startX = this.lastX;
        this.startY = this.lastY;
    }

    /* JADX INFO: compiled from: PanGestureHandler.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0017B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\r\u001a\u00020\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0002H\u0016R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/swmansion/gesturehandler/core/PanGestureHandler$Factory;", "Lcom/swmansion/gesturehandler/core/GestureHandler$Factory;", "Lcom/swmansion/gesturehandler/core/PanGestureHandler;", "<init>", "()V", "type", "Ljava/lang/Class;", "getType", "()Ljava/lang/Class;", "name", "", "getName", "()Ljava/lang/String;", "create", "context", "Landroid/content/Context;", "setConfig", "", "handler", "config", "Lcom/facebook/react/bridge/ReadableMap;", "createEventBuilder", "Lcom/swmansion/gesturehandler/react/eventbuilders/PanGestureHandlerEventDataBuilder;", "Companion", "react-native-gesture-handler_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Factory extends GestureHandler.Factory<PanGestureHandler> {
        private static final String KEY_ACTIVATE_AFTER_LONG_PRESS = "activateAfterLongPress";
        private static final String KEY_ACTIVE_OFFSET_X_END = "activeOffsetXEnd";
        private static final String KEY_ACTIVE_OFFSET_X_START = "activeOffsetXStart";
        private static final String KEY_ACTIVE_OFFSET_Y_END = "activeOffsetYEnd";
        private static final String KEY_ACTIVE_OFFSET_Y_START = "activeOffsetYStart";
        private static final String KEY_AVG_TOUCHES = "avgTouches";
        private static final String KEY_FAIL_OFFSET_RANGE_X_END = "failOffsetXEnd";
        private static final String KEY_FAIL_OFFSET_RANGE_X_START = "failOffsetXStart";
        private static final String KEY_FAIL_OFFSET_RANGE_Y_END = "failOffsetYEnd";
        private static final String KEY_FAIL_OFFSET_RANGE_Y_START = "failOffsetYStart";
        private static final String KEY_MAX_POINTERS = "maxPointers";
        private static final String KEY_MIN_DIST = "minDist";
        private static final String KEY_MIN_POINTERS = "minPointers";
        private static final String KEY_MIN_VELOCITY = "minVelocity";
        private static final String KEY_MIN_VELOCITY_X = "minVelocityX";
        private static final String KEY_MIN_VELOCITY_Y = "minVelocityY";
        private final Class<PanGestureHandler> type = PanGestureHandler.class;
        private final String name = "PanGestureHandler";

        @Override // com.swmansion.gesturehandler.core.GestureHandler.Factory
        public Class<PanGestureHandler> getType() {
            return this.type;
        }

        @Override // com.swmansion.gesturehandler.core.GestureHandler.Factory
        public String getName() {
            return this.name;
        }

        @Override // com.swmansion.gesturehandler.core.GestureHandler.Factory
        public PanGestureHandler create(Context context) {
            return new PanGestureHandler(context);
        }

        @Override // com.swmansion.gesturehandler.core.GestureHandler.Factory
        public void setConfig(PanGestureHandler handler, ReadableMap config) {
            boolean z;
            Intrinsics.checkNotNullParameter(handler, "handler");
            Intrinsics.checkNotNullParameter(config, "config");
            super.setConfig(handler, config);
            boolean z2 = true;
            if (config.hasKey(KEY_ACTIVE_OFFSET_X_START)) {
                handler.activeOffsetXStart = PixelUtil.toPixelFromDIP(config.getDouble(KEY_ACTIVE_OFFSET_X_START));
                z = true;
            } else {
                z = false;
            }
            if (config.hasKey(KEY_ACTIVE_OFFSET_X_END)) {
                handler.activeOffsetXEnd = PixelUtil.toPixelFromDIP(config.getDouble(KEY_ACTIVE_OFFSET_X_END));
                z = true;
            }
            if (config.hasKey(KEY_FAIL_OFFSET_RANGE_X_START)) {
                handler.failOffsetXStart = PixelUtil.toPixelFromDIP(config.getDouble(KEY_FAIL_OFFSET_RANGE_X_START));
                z = true;
            }
            if (config.hasKey(KEY_FAIL_OFFSET_RANGE_X_END)) {
                handler.failOffsetXEnd = PixelUtil.toPixelFromDIP(config.getDouble(KEY_FAIL_OFFSET_RANGE_X_END));
                z = true;
            }
            if (config.hasKey(KEY_ACTIVE_OFFSET_Y_START)) {
                handler.activeOffsetYStart = PixelUtil.toPixelFromDIP(config.getDouble(KEY_ACTIVE_OFFSET_Y_START));
                z = true;
            }
            if (config.hasKey(KEY_ACTIVE_OFFSET_Y_END)) {
                handler.activeOffsetYEnd = PixelUtil.toPixelFromDIP(config.getDouble(KEY_ACTIVE_OFFSET_Y_END));
                z = true;
            }
            if (config.hasKey(KEY_FAIL_OFFSET_RANGE_Y_START)) {
                handler.failOffsetYStart = PixelUtil.toPixelFromDIP(config.getDouble(KEY_FAIL_OFFSET_RANGE_Y_START));
                z = true;
            }
            if (config.hasKey(KEY_FAIL_OFFSET_RANGE_Y_END)) {
                handler.failOffsetYEnd = PixelUtil.toPixelFromDIP(config.getDouble(KEY_FAIL_OFFSET_RANGE_Y_END));
                z = true;
            }
            if (config.hasKey(KEY_MIN_VELOCITY)) {
                handler.minVelocity = PixelUtil.toPixelFromDIP(config.getDouble(KEY_MIN_VELOCITY));
                z = true;
            }
            if (config.hasKey(KEY_MIN_VELOCITY_X)) {
                handler.minVelocityX = PixelUtil.toPixelFromDIP(config.getDouble(KEY_MIN_VELOCITY_X));
                z = true;
            }
            if (config.hasKey(KEY_MIN_VELOCITY_Y)) {
                handler.minVelocityY = PixelUtil.toPixelFromDIP(config.getDouble(KEY_MIN_VELOCITY_Y));
            } else {
                z2 = z;
            }
            if (config.hasKey(KEY_MIN_DIST)) {
                handler.minDist = PixelUtil.toPixelFromDIP(config.getDouble(KEY_MIN_DIST));
            } else if (z2) {
                handler.minDist = Float.MAX_VALUE;
            }
            if (config.hasKey(KEY_MIN_POINTERS)) {
                handler.minPointers = config.getInt(KEY_MIN_POINTERS);
            }
            if (config.hasKey(KEY_MAX_POINTERS)) {
                handler.maxPointers = config.getInt(KEY_MAX_POINTERS);
            }
            if (config.hasKey(KEY_AVG_TOUCHES)) {
                handler.averageTouches = config.getBoolean(KEY_AVG_TOUCHES);
            }
            if (config.hasKey(KEY_ACTIVATE_AFTER_LONG_PRESS)) {
                handler.activateAfterLongPress = config.getInt(KEY_ACTIVATE_AFTER_LONG_PRESS);
            }
        }

        @Override // com.swmansion.gesturehandler.core.GestureHandler.Factory
        public PanGestureHandlerEventDataBuilder createEventBuilder(PanGestureHandler handler) {
            Intrinsics.checkNotNullParameter(handler, "handler");
            return new PanGestureHandlerEventDataBuilder(handler);
        }
    }

    /* JADX INFO: compiled from: PanGestureHandler.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/swmansion/gesturehandler/core/PanGestureHandler$Companion;", "", "<init>", "()V", "MIN_VALUE_IGNORE", "", "MAX_VALUE_IGNORE", "DEFAULT_MIN_POINTERS", "", "DEFAULT_MAX_POINTERS", "DEFAULT_ACTIVATE_AFTER_LONG_PRESS", "", "DEFAULT_AVERAGE_TOUCHES", "", "DEFAULT_ACTIVE_OFFSET_X_START", "DEFAULT_ACTIVE_OFFSET_X_END", "DEFAULT_FAIL_OFFSET_X_START", "DEFAULT_FAIL_OFFSET_X_END", "DEFAULT_ACTIVE_OFFSET_Y_START", "DEFAULT_ACTIVE_OFFSET_Y_END", "DEFAULT_FAIL_OFFSET_Y_START", "DEFAULT_FAIL_OFFSET_Y_END", "DEFAULT_MIN_VELOCITY_X", "DEFAULT_MIN_VELOCITY_Y", "DEFAULT_MIN_VELOCITY", "addVelocityMovement", "", "tracker", "Landroid/view/VelocityTracker;", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "react-native-gesture-handler_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void addVelocityMovement(VelocityTracker tracker, MotionEvent motionEvent) {
            float rawX = motionEvent.getRawX() - motionEvent.getX();
            float rawY = motionEvent.getRawY() - motionEvent.getY();
            motionEvent.offsetLocation(rawX, rawY);
            Intrinsics.checkNotNull(tracker);
            tracker.addMovement(motionEvent);
            motionEvent.offsetLocation(-rawX, -rawY);
        }
    }
}
