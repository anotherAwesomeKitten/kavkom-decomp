package com.swmansion.rnscreens.safearea;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.view.ReactViewGroup;
import com.swmansion.rnscreens.safearea.paper.SafeAreaViewEdges;
import java.lang.ref.WeakReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: SafeAreaView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 +2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001+B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u0016\u001a\u0004\u0018\u00010\u0011J\u0010\u0010\u0017\u001a\u00020\u00182\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\b\u0010\u0019\u001a\u00020\u0018H\u0014J\b\u0010\u001a\u001a\u00020\u0018H\u0014J\n\u0010\u001b\u001a\u0004\u0018\u00010\nH\u0002J\u000e\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\fJ\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u001fH\u0016J\b\u0010#\u001a\u00020\u000fH\u0002J\b\u0010$\u001a\u00020\u0018H\u0002J\b\u0010%\u001a\u00020\u0018H\u0002J\u0010\u0010&\u001a\u00020'2\u0006\u0010\"\u001a\u00020'H\u0002J\u000e\u0010(\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010)\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010*\u001a\u00020\u000fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/swmansion/rnscreens/safearea/SafeAreaView;", "Lcom/facebook/react/views/view/ReactViewGroup;", "Landroidx/core/view/OnApplyWindowInsetsListener;", "Landroid/view/ViewTreeObserver$OnPreDrawListener;", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "<init>", "(Lcom/facebook/react/uimanager/ThemedReactContext;)V", "provider", "Ljava/lang/ref/WeakReference;", "Lcom/swmansion/rnscreens/safearea/SafeAreaProvider;", "currentInterfaceInsets", "Lcom/swmansion/rnscreens/safearea/EdgeInsets;", "currentSystemInsets", "needsInsetsUpdate", "", "stateWrapper", "Lcom/facebook/react/uimanager/StateWrapper;", "edges", "Lcom/swmansion/rnscreens/safearea/paper/SafeAreaViewEdges;", "insetType", "Lcom/swmansion/rnscreens/safearea/InsetType;", "getStateWrapper", "setStateWrapper", "", "onAttachedToWindow", "onDetachedFromWindow", "findAncestorProvider", "onInterfaceInsetsChange", "newInterfaceInsets", "onApplyWindowInsets", "Landroidx/core/view/WindowInsetsCompat;", "view", "Landroid/view/View;", "insets", "updateInsetsIfNeeded", "updateInsets", "waitForReactLayout", "getConsumedInsetsFromSelectedEdges", "Landroidx/core/graphics/Insets;", "setEdges", "setInsetType", "onPreDraw", "Companion", "react-native-screens_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SafeAreaView extends ReactViewGroup implements OnApplyWindowInsetsListener, ViewTreeObserver.OnPreDrawListener {
    public static final String TAG = "SafeAreaView";
    private EdgeInsets currentInterfaceInsets;
    private EdgeInsets currentSystemInsets;
    private SafeAreaViewEdges edges;
    private InsetType insetType;
    private boolean needsInsetsUpdate;
    private WeakReference<SafeAreaProvider> provider;
    private final ThemedReactContext reactContext;
    private StateWrapper stateWrapper;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SafeAreaView(ThemedReactContext reactContext) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.reactContext = reactContext;
        this.provider = new WeakReference<>(null);
        this.currentInterfaceInsets = EdgeInsets.INSTANCE.getZERO();
        this.currentSystemInsets = EdgeInsets.INSTANCE.getZERO();
        this.insetType = InsetType.ALL;
        ViewCompat.setOnApplyWindowInsetsListener(this, this);
    }

    public final StateWrapper getStateWrapper() {
        return this.stateWrapper;
    }

    public final void setStateWrapper(StateWrapper stateWrapper) {
        this.stateWrapper = stateWrapper;
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        getViewTreeObserver().addOnPreDrawListener(this);
        SafeAreaProvider safeAreaProviderFindAncestorProvider = findAncestorProvider();
        if (safeAreaProviderFindAncestorProvider == null) {
            super.onAttachedToWindow();
            return;
        }
        safeAreaProviderFindAncestorProvider.setOnInterfaceInsetsChangeListener(this);
        this.provider = new WeakReference<>(safeAreaProviderFindAncestorProvider);
        this.currentInterfaceInsets = safeAreaProviderFindAncestorProvider.getInterfaceInsets();
        updateInsets();
        super.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        SafeAreaProvider safeAreaProvider = this.provider.get();
        if (safeAreaProvider != null) {
            safeAreaProvider.removeOnInterfaceInsetsChangeListener(this);
        }
        getViewTreeObserver().removeOnPreDrawListener(this);
        super.onDetachedFromWindow();
    }

    private final SafeAreaProvider findAncestorProvider() {
        ViewParent parent = getParent();
        while (parent != null && !(parent instanceof SafeAreaProvider)) {
            parent = parent.getParent();
        }
        if (parent instanceof SafeAreaProvider) {
            return (SafeAreaProvider) parent;
        }
        return null;
    }

    public final void onInterfaceInsetsChange(EdgeInsets newInterfaceInsets) {
        Intrinsics.checkNotNullParameter(newInterfaceInsets, "newInterfaceInsets");
        if (Intrinsics.areEqual(newInterfaceInsets, this.currentInterfaceInsets)) {
            return;
        }
        this.currentInterfaceInsets = newInterfaceInsets;
        if (this.insetType.containsInterface()) {
            this.needsInsetsUpdate = true;
        }
    }

    @Override // androidx.core.view.OnApplyWindowInsetsListener
    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat insets) {
        boolean zAreEqual;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(insets, "insets");
        Insets insets2 = insets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout());
        Intrinsics.checkNotNullExpressionValue(insets2, "getInsets(...)");
        if (!Intrinsics.areEqual(insets2, this.currentSystemInsets)) {
            this.currentSystemInsets = EdgeInsets.INSTANCE.fromInsets(insets2);
            if (this.insetType.containsSystem()) {
                this.needsInsetsUpdate = true;
            }
        }
        WindowInsetsCompat.Builder builder = new WindowInsetsCompat.Builder(insets);
        if (this.insetType.containsSystem()) {
            Insets insets3 = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            Intrinsics.checkNotNullExpressionValue(insets3, "getInsets(...)");
            Insets consumedInsetsFromSelectedEdges = getConsumedInsetsFromSelectedEdges(insets3);
            Insets insets4 = insets.getInsets(WindowInsetsCompat.Type.displayCutout());
            Intrinsics.checkNotNullExpressionValue(insets4, "getInsets(...)");
            Insets consumedInsetsFromSelectedEdges2 = getConsumedInsetsFromSelectedEdges(insets4);
            zAreEqual = Intrinsics.areEqual(consumedInsetsFromSelectedEdges2, Insets.NONE);
            builder.setInsets(WindowInsetsCompat.Type.systemBars(), consumedInsetsFromSelectedEdges);
            builder.setInsets(WindowInsetsCompat.Type.displayCutout(), consumedInsetsFromSelectedEdges2);
        } else {
            zAreEqual = false;
        }
        WindowInsetsCompat windowInsetsCompatBuild = builder.build();
        Intrinsics.checkNotNullExpressionValue(windowInsetsCompatBuild, "build(...)");
        if (Build.VERSION.SDK_INT >= 30 || !zAreEqual) {
            return windowInsetsCompatBuild;
        }
        WindowInsetsCompat windowInsetsCompatConsumeDisplayCutout = windowInsetsCompatBuild.consumeDisplayCutout();
        Intrinsics.checkNotNullExpressionValue(windowInsetsCompatConsumeDisplayCutout, "consumeDisplayCutout(...)");
        return windowInsetsCompatConsumeDisplayCutout;
    }

    private final boolean updateInsetsIfNeeded() {
        if (!this.needsInsetsUpdate) {
            return false;
        }
        this.needsInsetsUpdate = false;
        updateInsets();
        return true;
    }

    private final void updateInsets() {
        EdgeInsets edgeInsetsMax = EdgeInsets.INSTANCE.max(this.insetType.containsInterface() ? this.currentInterfaceInsets : EdgeInsets.INSTANCE.getZERO(), this.insetType.containsSystem() ? this.currentSystemInsets : EdgeInsets.INSTANCE.getZERO());
        StateWrapper stateWrapper = getStateWrapper();
        if (stateWrapper != null) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putDouble("left", PixelUtil.toDIPFromPixel(edgeInsetsMax.getLeft()));
            writableMapCreateMap.putDouble("top", PixelUtil.toDIPFromPixel(edgeInsetsMax.getTop()));
            writableMapCreateMap.putDouble(ViewProps.RIGHT, PixelUtil.toDIPFromPixel(edgeInsetsMax.getRight()));
            writableMapCreateMap.putDouble(ViewProps.BOTTOM, PixelUtil.toDIPFromPixel(edgeInsetsMax.getBottom()));
            WritableMap writableMapCreateMap2 = Arguments.createMap();
            writableMapCreateMap2.putMap("insets", writableMapCreateMap);
            stateWrapper.updateState(writableMapCreateMap2);
        }
    }

    private final void waitForReactLayout() {
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        final ReentrantLock reentrantLock = new ReentrantLock();
        final Condition conditionNewCondition = reentrantLock.newCondition();
        long jNanoTime = System.nanoTime();
        UIManagerHelper.getReactContext(this).runOnNativeModulesQueueThread(new Runnable() { // from class: com.swmansion.rnscreens.safearea.SafeAreaView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SafeAreaView.waitForReactLayout$lambda$3(reentrantLock, booleanRef, conditionNewCondition);
            }
        });
        ReentrantLock reentrantLock2 = reentrantLock;
        reentrantLock2.lock();
        long jNanoTime2 = 0;
        while (!booleanRef.element && jNanoTime2 < 500000000) {
            try {
                try {
                    conditionNewCondition.awaitNanos(500000000L);
                } catch (InterruptedException unused) {
                    booleanRef.element = true;
                }
                jNanoTime2 += System.nanoTime() - jNanoTime;
            } catch (Throwable th) {
                reentrantLock2.unlock();
                throw th;
            }
        }
        Unit unit = Unit.INSTANCE;
        reentrantLock2.unlock();
        if (jNanoTime2 >= 500000000) {
            Log.w(TAG, "Timed out waiting for layout.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void waitForReactLayout$lambda$3(ReentrantLock reentrantLock, Ref.BooleanRef booleanRef, Condition condition) {
        ReentrantLock reentrantLock2 = reentrantLock;
        reentrantLock2.lock();
        try {
            if (!booleanRef.element) {
                booleanRef.element = true;
                condition.signal();
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock2.unlock();
        }
    }

    private final Insets getConsumedInsetsFromSelectedEdges(Insets insets) {
        SafeAreaViewEdges safeAreaViewEdges = this.edges;
        int i = safeAreaViewEdges != null ? safeAreaViewEdges.getLeft() : false ? 0 : insets.left;
        SafeAreaViewEdges safeAreaViewEdges2 = this.edges;
        int i2 = safeAreaViewEdges2 != null ? safeAreaViewEdges2.getTop() : false ? 0 : insets.top;
        SafeAreaViewEdges safeAreaViewEdges3 = this.edges;
        int i3 = safeAreaViewEdges3 != null ? safeAreaViewEdges3.getRight() : false ? 0 : insets.right;
        SafeAreaViewEdges safeAreaViewEdges4 = this.edges;
        Insets insetsOf = Insets.of(i, i2, i3, safeAreaViewEdges4 != null ? safeAreaViewEdges4.getBottom() : false ? 0 : insets.bottom);
        Intrinsics.checkNotNullExpressionValue(insetsOf, "of(...)");
        return insetsOf;
    }

    public final void setEdges(SafeAreaViewEdges edges) {
        Intrinsics.checkNotNullParameter(edges, "edges");
        this.edges = edges;
        requestApplyInsets();
        this.needsInsetsUpdate = true;
    }

    public final void setInsetType(InsetType insetType) {
        Intrinsics.checkNotNullParameter(insetType, "insetType");
        this.insetType = insetType;
        requestApplyInsets();
        this.needsInsetsUpdate = true;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        boolean zUpdateInsetsIfNeeded = updateInsetsIfNeeded();
        if (zUpdateInsetsIfNeeded) {
            requestLayout();
        }
        return !zUpdateInsetsIfNeeded;
    }
}
