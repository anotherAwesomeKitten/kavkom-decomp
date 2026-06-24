package com.facebook.react.fabric.internal.interop;

import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UIManagerListener;
import com.facebook.react.fabric.interop.UIBlock;
import com.facebook.react.fabric.interop.UIBlockViewResolver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InteropUiBlockListener.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006J\u000e\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\"\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\u0003\u001a\u0004\b\b\u0010\tR\"\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\u0003\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/facebook/react/fabric/internal/interop/InteropUIBlockListener;", "Lcom/facebook/react/bridge/UIManagerListener;", "<init>", "()V", "beforeUIBlocks", "", "Lcom/facebook/react/fabric/interop/UIBlock;", "getBeforeUIBlocks$ReactAndroid_release$annotations", "getBeforeUIBlocks$ReactAndroid_release", "()Ljava/util/List;", "afterUIBlocks", "getAfterUIBlocks$ReactAndroid_release$annotations", "getAfterUIBlocks$ReactAndroid_release", "prependUIBlock", "", "block", "addUIBlock", "willMountItems", "uiManager", "Lcom/facebook/react/bridge/UIManager;", "didMountItems", "didDispatchMountItems", "willDispatchViewUpdates", "didScheduleMountItems", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class InteropUIBlockListener implements UIManagerListener {
    private final List<UIBlock> beforeUIBlocks = new ArrayList();
    private final List<UIBlock> afterUIBlocks = new ArrayList();

    public static /* synthetic */ void getAfterUIBlocks$ReactAndroid_release$annotations() {
    }

    public static /* synthetic */ void getBeforeUIBlocks$ReactAndroid_release$annotations() {
    }

    @Override // com.facebook.react.bridge.UIManagerListener
    public void didScheduleMountItems(UIManager uiManager) {
        Intrinsics.checkNotNullParameter(uiManager, "uiManager");
    }

    public final List<UIBlock> getBeforeUIBlocks$ReactAndroid_release() {
        return this.beforeUIBlocks;
    }

    public final List<UIBlock> getAfterUIBlocks$ReactAndroid_release() {
        return this.afterUIBlocks;
    }

    public final synchronized void prependUIBlock(UIBlock block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.beforeUIBlocks.add(block);
    }

    public final synchronized void addUIBlock(UIBlock block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.afterUIBlocks.add(block);
    }

    @Override // com.facebook.react.bridge.UIManagerListener
    public void willMountItems(UIManager uiManager) {
        Intrinsics.checkNotNullParameter(uiManager, "uiManager");
        if (uiManager instanceof UIBlockViewResolver) {
            synchronized (this) {
                if (this.beforeUIBlocks.isEmpty()) {
                    return;
                }
                List list = CollectionsKt.toList(this.beforeUIBlocks);
                this.beforeUIBlocks.clear();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    ((UIBlock) it.next()).execute((UIBlockViewResolver) uiManager);
                }
            }
        }
    }

    @Override // com.facebook.react.bridge.UIManagerListener
    public void didMountItems(UIManager uiManager) {
        Intrinsics.checkNotNullParameter(uiManager, "uiManager");
        if (uiManager instanceof UIBlockViewResolver) {
            synchronized (this) {
                if (this.afterUIBlocks.isEmpty()) {
                    return;
                }
                List list = CollectionsKt.toList(this.afterUIBlocks);
                this.afterUIBlocks.clear();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    ((UIBlock) it.next()).execute((UIBlockViewResolver) uiManager);
                }
            }
        }
    }

    @Override // com.facebook.react.bridge.UIManagerListener
    public void didDispatchMountItems(UIManager uiManager) {
        Intrinsics.checkNotNullParameter(uiManager, "uiManager");
        didMountItems(uiManager);
    }

    @Override // com.facebook.react.bridge.UIManagerListener
    public void willDispatchViewUpdates(UIManager uiManager) {
        Intrinsics.checkNotNullParameter(uiManager, "uiManager");
        willMountItems(uiManager);
    }
}
