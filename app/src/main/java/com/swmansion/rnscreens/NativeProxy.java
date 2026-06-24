package com.swmansion.rnscreens;

import android.util.Log;
import com.facebook.jni.HybridData;
import com.facebook.react.fabric.FabricUIManager;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NativeProxy.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\t\u0010\u0007\u001a\u00020\u0005H\u0082 J\u0011\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0086 J\t\u0010\f\u001a\u00020\tH\u0086 J\t\u0010\r\u001a\u00020\tH\u0086 J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007R\u0016\u0010\u0004\u001a\u00020\u00058\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0003¨\u0006\u0012"}, d2 = {"Lcom/swmansion/rnscreens/NativeProxy;", "", "<init>", "()V", "mHybridData", "Lcom/facebook/jni/HybridData;", "getMHybridData$annotations", "initHybrid", "nativeAddMutationsListener", "", "fabricUIManager", "Lcom/facebook/react/fabric/FabricUIManager;", "cleanupExpiredMountingCoordinators", "invalidateNative", "notifyScreenRemoved", "screenTag", "", "Companion", "react-native-screens_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class NativeProxy {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ConcurrentHashMap<Integer, WeakReference<Screen>> viewsMap = new ConcurrentHashMap<>();
    private final HybridData mHybridData = initHybrid();

    private static /* synthetic */ void getMHybridData$annotations() {
    }

    private final native HybridData initHybrid();

    public final native void cleanupExpiredMountingCoordinators();

    public final native void invalidateNative();

    public final native void nativeAddMutationsListener(FabricUIManager fabricUIManager);

    /* JADX INFO: compiled from: NativeProxy.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\bJ\u000e\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006J\u0006\u0010\u000e\u001a\u00020\nR \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/swmansion/rnscreens/NativeProxy$Companion;", "", "<init>", "()V", "viewsMap", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Ljava/lang/ref/WeakReference;", "Lcom/swmansion/rnscreens/Screen;", "addScreenToMap", "", "tag", "view", "removeScreenFromMap", "clearMapOnInvalidate", "react-native-screens_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void addScreenToMap(int tag, Screen view) {
            Intrinsics.checkNotNullParameter(view, "view");
            NativeProxy.viewsMap.put(Integer.valueOf(tag), new WeakReference(view));
        }

        public final void removeScreenFromMap(int tag) {
            NativeProxy.viewsMap.remove(Integer.valueOf(tag));
        }

        public final void clearMapOnInvalidate() {
            NativeProxy.viewsMap.clear();
        }
    }

    public final void notifyScreenRemoved(int screenTag) {
        WeakReference<Screen> weakReference = viewsMap.get(Integer.valueOf(screenTag));
        if (weakReference == null) {
            return;
        }
        final Screen screen = weakReference.get();
        if (screen == null) {
            Log.w("[RNScreens]", "Reference stored in NativeProxy for tag " + screenTag + " no longer points to valid object.");
        } else {
            if (screen.post(new Runnable() { // from class: com.swmansion.rnscreens.NativeProxy$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    screen.startRemovalTransition();
                }
            })) {
                return;
            }
            Log.w("[RNScreens]", "Failed to schedule removal transition start for screen with tag " + screenTag);
        }
    }
}
