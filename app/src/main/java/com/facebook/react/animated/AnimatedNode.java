package com.facebook.react.animated;

import io.sentry.protocol.ViewHierarchyNode;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AnimatedNode.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0005\b&\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0000H\u0000¢\u0006\u0002\b\rJ\u0015\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0000H\u0000¢\u0006\u0002\b\u000fJ\u0015\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0000H\u0010¢\u0006\u0002\b\u0012J\u0015\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0000H\u0010¢\u0006\u0002\b\u0014J\r\u0010\u0015\u001a\u00020\u000bH\u0010¢\u0006\u0002\b\u0016J\r\u0010\u0017\u001a\u00020\u0018H ¢\u0006\u0002\b\u0019J\r\u0010\u001a\u001a\u00020\u0018H\u0000¢\u0006\u0002\b\u001bR\u001a\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u00058\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00020\u00078\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00020\u00078\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00020\u00078\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/facebook/react/animated/AnimatedNode;", "", "<init>", "()V", ViewHierarchyNode.JsonKeys.CHILDREN, "", "activeIncomingNodes", "", "BFSColor", "tag", "addChild", "", "child", "addChild$ReactAndroid_release", "removeChild", "removeChild$ReactAndroid_release", "onAttachedToNode", "parent", "onAttachedToNode$ReactAndroid_release", "onDetachedFromNode", "onDetachedFromNode$ReactAndroid_release", "update", "update$ReactAndroid_release", "prettyPrint", "", "prettyPrint$ReactAndroid_release", "prettyPrintWithChildren", "prettyPrintWithChildren$ReactAndroid_release", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class AnimatedNode {
    public static final int DEFAULT_ANIMATED_NODE_CHILD_COUNT = 1;
    public static final int INITIAL_BFS_COLOR = 0;
    public int BFSColor;
    public int activeIncomingNodes;
    public List<AnimatedNode> children;
    public int tag = -1;

    public void onAttachedToNode$ReactAndroid_release(AnimatedNode parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
    }

    public void onDetachedFromNode$ReactAndroid_release(AnimatedNode parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
    }

    public abstract String prettyPrint$ReactAndroid_release();

    public void update$ReactAndroid_release() {
    }

    public final void addChild$ReactAndroid_release(AnimatedNode child) {
        Intrinsics.checkNotNullParameter(child, "child");
        ArrayList arrayList = this.children;
        if (arrayList == null) {
            arrayList = new ArrayList(1);
            this.children = arrayList;
        }
        arrayList.add(child);
        child.onAttachedToNode$ReactAndroid_release(this);
    }

    public final void removeChild$ReactAndroid_release(AnimatedNode child) {
        Intrinsics.checkNotNullParameter(child, "child");
        List<AnimatedNode> list = this.children;
        if (list == null) {
            return;
        }
        child.onDetachedFromNode$ReactAndroid_release(this);
        list.remove(child);
    }

    public final String prettyPrintWithChildren$ReactAndroid_release() {
        List<AnimatedNode> list = this.children;
        String strJoinToString$default = list != null ? CollectionsKt.joinToString$default(list, " ", null, null, 0, null, null, 62, null) : null;
        String str = strJoinToString$default;
        return prettyPrint$ReactAndroid_release() + ((str == null || StringsKt.isBlank(str)) ? "" : " children: " + strJoinToString$default);
    }
}
