package com.facebook.react.runtime;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import com.facebook.react.ReactHost;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.fabric.SurfaceHandlerBinding;
import com.facebook.react.interfaces.TaskInterface;
import com.facebook.react.interfaces.fabric.ReactSurface;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.runtime.internal.bolts.Task;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.EventDispatcher;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactSurfaceImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0007\u0018\u0000 @2\u00020\u0001:\u0001@B\u0019\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B#\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u0014J\u000e\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\u000bJ\b\u0010\"\u001a\u00020\u001bH\u0016J\u000e\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&H\u0016J\u000e\u0010(\u001a\b\u0012\u0004\u0012\u00020'0&H\u0016J\u000e\u0010)\u001a\b\u0012\u0004\u0012\u00020'0&H\u0016J\b\u00100\u001a\u00020\u001bH\u0016J-\u00101\u001a\u00020\u001b2\u0006\u00102\u001a\u00020+2\u0006\u00103\u001a\u00020+2\u0006\u00104\u001a\u00020+2\u0006\u00105\u001a\u00020+H\u0001¢\u0006\u0002\b6R\u0016\u0010\u0002\u001a\u00020\u00038AX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0017\u001a\u0004\u0018\u00010\u00168@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001f\u001a\u0004\u0018\u00010\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0014\u0010*\u001a\u00020+8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0016\u00107\u001a\u0004\u0018\u0001088@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010:R\u0014\u0010;\u001a\u00020<8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b=\u0010>R\u0014\u0010?\u001a\u00020<8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b?\u0010>¨\u0006A"}, d2 = {"Lcom/facebook/react/runtime/ReactSurfaceImpl;", "Lcom/facebook/react/interfaces/fabric/ReactSurface;", "surfaceHandler", "Lcom/facebook/react/fabric/SurfaceHandlerBinding;", "context", "Landroid/content/Context;", "<init>", "(Lcom/facebook/react/fabric/SurfaceHandlerBinding;Landroid/content/Context;)V", "moduleName", "", "initialProps", "Landroid/os/Bundle;", "(Landroid/content/Context;Ljava/lang/String;Landroid/os/Bundle;)V", "getSurfaceHandler", "()Lcom/facebook/react/fabric/SurfaceHandlerBinding;", "value", "getContext", "()Landroid/content/Context;", "surfaceViewRef", "Ljava/util/concurrent/atomic/AtomicReference;", "Lcom/facebook/react/runtime/ReactSurfaceView;", "reactHostRef", "Lcom/facebook/react/runtime/ReactHostImpl;", "reactHost", "getReactHost$ReactAndroid_release", "()Lcom/facebook/react/runtime/ReactHostImpl;", "attach", "", "host", "Lcom/facebook/react/ReactHost;", "attachView", "view", "updateInitProps", "newProps", "detach", "getView", "()Lcom/facebook/react/runtime/ReactSurfaceView;", "prerender", "Lcom/facebook/react/interfaces/TaskInterface;", "Ljava/lang/Void;", ViewProps.START, "stop", "surfaceID", "", "getSurfaceID", "()I", "getModuleName", "()Ljava/lang/String;", "clear", "updateLayoutSpecs", "widthMeasureSpec", "heightMeasureSpec", "offsetX", "offsetY", "updateLayoutSpecs$ReactAndroid_release", "eventDispatcher", "Lcom/facebook/react/uimanager/events/EventDispatcher;", "getEventDispatcher$ReactAndroid_release", "()Lcom/facebook/react/uimanager/events/EventDispatcher;", "isAttached", "", "isAttached$ReactAndroid_release", "()Z", "isRunning", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ReactSurfaceImpl implements ReactSurface {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private Context context;
    private final AtomicReference<ReactHostImpl> reactHostRef;
    private final SurfaceHandlerBinding surfaceHandler;
    private final AtomicReference<ReactSurfaceView> surfaceViewRef;

    @JvmStatic
    public static final ReactSurfaceImpl createWithView(Context context, String str, Bundle bundle) {
        return INSTANCE.createWithView(context, str, bundle);
    }

    @VisibleForTesting
    public ReactSurfaceImpl(SurfaceHandlerBinding surfaceHandler, Context context) {
        Intrinsics.checkNotNullParameter(surfaceHandler, "surfaceHandler");
        Intrinsics.checkNotNullParameter(context, "context");
        this.surfaceHandler = surfaceHandler;
        this.context = context;
        this.surfaceViewRef = new AtomicReference<>(null);
        this.reactHostRef = new AtomicReference<>(null);
    }

    public final SurfaceHandlerBinding getSurfaceHandler() {
        return this.surfaceHandler;
    }

    @Override // com.facebook.react.interfaces.fabric.ReactSurface
    public Context getContext() {
        return this.context;
    }

    public final ReactHostImpl getReactHost$ReactAndroid_release() {
        return this.reactHostRef.get();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ReactSurfaceImpl(Context context, String moduleName, Bundle bundle) {
        NativeMap nativeMap;
        this(new SurfaceHandlerBinding(moduleName), context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        if (bundle != null) {
            Object objFromBundle = Arguments.fromBundle(bundle);
            Intrinsics.checkNotNull(objFromBundle, "null cannot be cast to non-null type com.facebook.react.bridge.NativeMap");
            nativeMap = (NativeMap) objFromBundle;
        } else {
            nativeMap = null;
        }
        this.surfaceHandler.setProps(nativeMap);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        SurfaceHandlerBinding surfaceHandlerBinding = this.surfaceHandler;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(displayMetrics.widthPixels, Integer.MIN_VALUE);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(displayMetrics.heightPixels, Integer.MIN_VALUE);
        Companion companion = INSTANCE;
        surfaceHandlerBinding.setLayoutConstraints(iMakeMeasureSpec, iMakeMeasureSpec2, 0, 0, companion.doRTLSwap(context), companion.isRTL(context), displayMetrics.density, companion.getFontScale(context));
    }

    public final void attach(ReactHost host) {
        Intrinsics.checkNotNullParameter(host, "host");
        if (!(host instanceof ReactHostImpl)) {
            throw new IllegalArgumentException("ReactSurfaceImpl.attach can only attach to ReactHostImpl.".toString());
        }
        if (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.reactHostRef, null, host)) {
            throw new IllegalStateException("This surface is already attached to a host!".toString());
        }
    }

    public final void attachView(ReactSurfaceView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.surfaceViewRef, null, view)) {
            throw new IllegalStateException("Trying to call ReactSurface.attachView(), but the view is already attached.".toString());
        }
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        this.context = context;
    }

    public final void updateInitProps(Bundle newProps) {
        Intrinsics.checkNotNullParameter(newProps, "newProps");
        SurfaceHandlerBinding surfaceHandlerBinding = this.surfaceHandler;
        Object objFromBundle = Arguments.fromBundle(newProps);
        Intrinsics.checkNotNull(objFromBundle, "null cannot be cast to non-null type com.facebook.react.bridge.NativeMap");
        surfaceHandlerBinding.setProps((NativeMap) objFromBundle);
    }

    @Override // com.facebook.react.interfaces.fabric.ReactSurface
    public void detach() {
        this.reactHostRef.set(null);
    }

    @Override // com.facebook.react.interfaces.fabric.ReactSurface
    public ReactSurfaceView getView() {
        return this.surfaceViewRef.get();
    }

    @Override // com.facebook.react.interfaces.fabric.ReactSurface
    public TaskInterface<Void> prerender() {
        ReactHostImpl reactHost$ReactAndroid_release = getReactHost$ReactAndroid_release();
        if (reactHost$ReactAndroid_release == null) {
            return Task.INSTANCE.forError(new IllegalStateException("Trying to call ReactSurface.prerender(), but no ReactHost is attached."));
        }
        return reactHost$ReactAndroid_release.prerenderSurface$ReactAndroid_release(this);
    }

    @Override // com.facebook.react.interfaces.fabric.ReactSurface
    public TaskInterface<Void> start() {
        if (this.surfaceViewRef.get() == null) {
            return Task.INSTANCE.forError(new IllegalStateException("Trying to call ReactSurface.start(), but view is not created."));
        }
        ReactHostImpl reactHost$ReactAndroid_release = getReactHost$ReactAndroid_release();
        if (reactHost$ReactAndroid_release == null) {
            return Task.INSTANCE.forError(new IllegalStateException("Trying to call ReactSurface.start(), but no ReactHost is attached."));
        }
        return reactHost$ReactAndroid_release.startSurface$ReactAndroid_release(this);
    }

    @Override // com.facebook.react.interfaces.fabric.ReactSurface
    public TaskInterface<Void> stop() {
        ReactHostImpl reactHost$ReactAndroid_release = getReactHost$ReactAndroid_release();
        if (reactHost$ReactAndroid_release == null) {
            return Task.INSTANCE.forError(new IllegalStateException("Trying to call ReactSurface.stop(), but no ReactHost is attached."));
        }
        return reactHost$ReactAndroid_release.stopSurface$ReactAndroid_release(this);
    }

    @Override // com.facebook.react.interfaces.fabric.ReactSurface
    public int getSurfaceID() {
        return this.surfaceHandler.getSurfaceId();
    }

    @Override // com.facebook.react.interfaces.fabric.ReactSurface
    public String getModuleName() {
        return this.surfaceHandler.getModuleName();
    }

    @Override // com.facebook.react.interfaces.fabric.ReactSurface
    public void clear() {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.runtime.ReactSurfaceImpl$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ReactSurfaceImpl.clear$lambda$4(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void clear$lambda$4(ReactSurfaceImpl reactSurfaceImpl) {
        ReactSurfaceView view = reactSurfaceImpl.getView();
        if (view != null) {
            view.removeAllViews();
            view.setId(-1);
        }
    }

    public final synchronized void updateLayoutSpecs$ReactAndroid_release(int widthMeasureSpec, int heightMeasureSpec, int offsetX, int offsetY) {
        SurfaceHandlerBinding surfaceHandlerBinding = this.surfaceHandler;
        Companion companion = INSTANCE;
        surfaceHandlerBinding.setLayoutConstraints(widthMeasureSpec, heightMeasureSpec, offsetX, offsetY, companion.doRTLSwap(getContext()), companion.isRTL(getContext()), getContext().getResources().getDisplayMetrics().density, companion.getFontScale(getContext()));
    }

    public final EventDispatcher getEventDispatcher$ReactAndroid_release() {
        ReactHostImpl reactHost$ReactAndroid_release = getReactHost$ReactAndroid_release();
        if (reactHost$ReactAndroid_release != null) {
            return reactHost$ReactAndroid_release.getEventDispatcher$ReactAndroid_release();
        }
        return null;
    }

    public final boolean isAttached$ReactAndroid_release() {
        return getReactHost$ReactAndroid_release() != null;
    }

    @Override // com.facebook.react.interfaces.fabric.ReactSurface
    public boolean isRunning() {
        return this.surfaceHandler.isRunning();
    }

    /* JADX INFO: compiled from: ReactSurfaceImpl.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¨\u0006\u0011"}, d2 = {"Lcom/facebook/react/runtime/ReactSurfaceImpl$Companion;", "", "<init>", "()V", "createWithView", "Lcom/facebook/react/runtime/ReactSurfaceImpl;", "context", "Landroid/content/Context;", "moduleName", "", "initialProps", "Landroid/os/Bundle;", "isRTL", "", "getFontScale", "", "doRTLSwap", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ReactSurfaceImpl createWithView(Context context, String moduleName, Bundle initialProps) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(moduleName, "moduleName");
            ReactSurfaceImpl reactSurfaceImpl = new ReactSurfaceImpl(context, moduleName, initialProps);
            reactSurfaceImpl.attachView(new ReactSurfaceView(context, reactSurfaceImpl));
            return reactSurfaceImpl;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isRTL(Context context) {
            return I18nUtil.INSTANCE.getInstance().isRTL(context);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final float getFontScale(Context context) {
            if (ReactNativeFeatureFlags.enableFontScaleChangesUpdatingLayout()) {
                return context.getResources().getConfiguration().fontScale;
            }
            return 1.0f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean doRTLSwap(Context context) {
            return I18nUtil.INSTANCE.getInstance().doLeftAndRightSwapInRTL(context);
        }
    }
}
