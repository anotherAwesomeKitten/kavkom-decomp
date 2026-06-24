package com.facebook.react.uimanager;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.common.annotations.internal.LegacyArchitectureLogLevel;
import com.facebook.react.common.annotations.internal.LegacyArchitectureLogger;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ShadowNodeRegistry.kt */
/* JADX INFO: loaded from: classes.dex */
@Deprecated(level = DeprecationLevel.WARNING, message = "This class is part of Legacy Architecture and will be removed in a future release")
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\b\u0001\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001b\u001cB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u0006J\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010J\u0012\u0010\u0011\u001a\u00020\f2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u0006J\u000e\u0010\u0012\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010J\u0014\u0010\u0013\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010R\u0018\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00060\nR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0016\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001d"}, d2 = {"Lcom/facebook/react/uimanager/ShadowNodeRegistry;", "", "<init>", "()V", "tagsToCSSNodes", "Landroid/util/SparseArray;", "Lcom/facebook/react/uimanager/ReactShadowNode;", "rootTags", "Landroid/util/SparseBooleanArray;", "threadAsserter", "Lcom/facebook/react/uimanager/ShadowNodeRegistry$SingleThreadAsserter;", "addRootNode", "", "node", "removeRootNode", "tag", "", "addNode", "removeNode", "getNode", "isRootNode", "", "rootNodeCount", "getRootNodeCount", "()I", "getRootTag", FirebaseAnalytics.Param.INDEX, "Companion", "SingleThreadAsserter", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ShadowNodeRegistry {
    private static final Companion Companion = new Companion(null);
    private final SparseArray<ReactShadowNode<?>> tagsToCSSNodes = new SparseArray<>();
    private final SparseBooleanArray rootTags = new SparseBooleanArray();
    private final SingleThreadAsserter threadAsserter = new SingleThreadAsserter();

    public final void addRootNode(ReactShadowNode<?> node) {
        Intrinsics.checkNotNullParameter(node, "node");
        this.threadAsserter.assertNow();
        int reactTag = node.getReactTag();
        this.tagsToCSSNodes.put(reactTag, node);
        this.rootTags.put(reactTag, true);
    }

    public final void removeRootNode(int tag) {
        this.threadAsserter.assertNow();
        if (tag == -1) {
            return;
        }
        if (!this.rootTags.get(tag)) {
            throw new IllegalViewOperationException("View with tag " + tag + " is not registered as a root view");
        }
        this.tagsToCSSNodes.remove(tag);
        this.rootTags.delete(tag);
    }

    public final void addNode(ReactShadowNode<?> node) {
        Intrinsics.checkNotNullParameter(node, "node");
        this.threadAsserter.assertNow();
        this.tagsToCSSNodes.put(node.getReactTag(), node);
    }

    public final void removeNode(int tag) {
        this.threadAsserter.assertNow();
        if (this.rootTags.get(tag)) {
            throw new IllegalViewOperationException("Trying to remove root node " + tag + " without using removeRootNode!");
        }
        this.tagsToCSSNodes.remove(tag);
    }

    public final ReactShadowNode<?> getNode(int tag) {
        this.threadAsserter.assertNow();
        return this.tagsToCSSNodes.get(tag);
    }

    public final boolean isRootNode(int tag) {
        this.threadAsserter.assertNow();
        return this.rootTags.get(tag);
    }

    public final int getRootNodeCount() {
        this.threadAsserter.assertNow();
        return this.rootTags.size();
    }

    public final int getRootTag(int index) {
        this.threadAsserter.assertNow();
        return this.rootTags.keyAt(index);
    }

    /* JADX INFO: compiled from: ShadowNodeRegistry.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/facebook/react/uimanager/ShadowNodeRegistry$Companion;", "", "<init>", "()V", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        LegacyArchitectureLogger.assertLegacyArchitecture("ShadowNodeRegistry", LegacyArchitectureLogLevel.ERROR);
    }

    /* JADX INFO: compiled from: ShadowNodeRegistry.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/facebook/react/uimanager/ShadowNodeRegistry$SingleThreadAsserter;", "", "<init>", "(Lcom/facebook/react/uimanager/ShadowNodeRegistry;)V", "thread", "Ljava/lang/Thread;", "assertNow", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public final class SingleThreadAsserter {
        private Thread thread;

        public SingleThreadAsserter() {
        }

        public final void assertNow() {
            Thread threadCurrentThread = Thread.currentThread();
            if (this.thread == null) {
                this.thread = threadCurrentThread;
            }
            Assertions.assertCondition(Intrinsics.areEqual(this.thread, threadCurrentThread));
        }
    }
}
