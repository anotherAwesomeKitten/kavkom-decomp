package com.facebook.react.views.scroll;

import android.graphics.Rect;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.common.logging.FLog;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.google.android.gms.actions.SearchIntents;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableCollection;

/* JADX INFO: compiled from: VirtualViewContainerStateExperimental.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0010#\n\u0000\n\u0002\u0010!\n\u0002\b\t\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010)\n\u0002\b\n\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001c\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000bH\u0002J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\bH\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\bH\u0002J\u0010\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\bH\u0002J\u0012\u0010\u0017\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\bH\u0002J\u0010\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0002J\u0010\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0002J\u0010\u0010\u001b\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH\u0002J\u0018\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\rH\u0002J\u001a\u0010\u001f\u001a\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\b2\u0006\u0010 \u001a\u00020\bH\u0002J\u0010\u0010!\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH\u0002J\u001c\u0010\"\u001a\u0004\u0018\u00010\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\b2\u0006\u0010#\u001a\u00020\bH\u0002J(\u0010$\u001a\u00020\u00152\b\u0010\u0013\u001a\u0004\u0018\u00010\b2\u0006\u0010%\u001a\u00020\r2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u000b0'H\u0002J \u0010(\u001a\u00020\u00152\b\u0010\u0013\u001a\u0004\u0018\u00010\b2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00020)H\u0002J\u0014\u0010*\u001a\b\u0012\u0004\u0012\u00020\u000b0'2\u0006\u0010+\u001a\u00020\u000fJ\u0010\u0010,\u001a\u0004\u0018\u00010\u00022\u0006\u0010-\u001a\u00020\u000bJ\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00020)J\u0010\u0010/\u001a\u00020\u00042\u0006\u00100\u001a\u00020\u0002H\u0016J\u0016\u00101\u001a\u00020\u00042\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u000203H\u0016J\b\u00104\u001a\u00020\u0015H\u0016J\u000f\u00105\u001a\b\u0012\u0004\u0012\u00020\u000206H\u0096\u0002J\u0010\u00107\u001a\u00020\u00042\u0006\u00100\u001a\u00020\u0002H\u0016J\u0016\u00108\u001a\u00020\u00042\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u000203H\u0016J\u0016\u00109\u001a\u00020\u00042\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u000203H\u0016J\u0011\u0010=\u001a\u00020\u00042\u0006\u00100\u001a\u00020\u0002H\u0096\u0002J\u0016\u0010>\u001a\u00020\u00042\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u000203H\u0016J\b\u0010?\u001a\u00020\u0004H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010:\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b;\u0010<¨\u0006@"}, d2 = {"Lcom/facebook/react/views/scroll/IntervalTree;", "", "Lcom/facebook/react/views/scroll/VirtualView;", "horizontal", "", "<init>", "(Z)V", "root", "Lcom/facebook/react/views/scroll/IntervalNode;", "idToIntervalNode", "", "", "rectToInterval", "Lcom/facebook/react/views/scroll/Interval;", "rect", "Landroid/graphics/Rect;", "id", "height", "", "node", "updateHeight", "", "updateMax", "balanceFactor", "rotateRight", "parent", "rotateLeft", "balance", "compareIntervals", "i1", "i2", "insert", "intervalNode", "findMin", "delete", TypedValues.Attributes.S_TARGET, "queryHelper", "interval", "results", "", "inorderTraversal", "", SearchIntents.EXTRA_QUERY, "queryRect", "getVirtualView", "virtualViewID", "traverse", "add", "element", "addAll", "elements", "", "clear", "iterator", "", "remove", "removeAll", "retainAll", RRWebVideoEvent.JsonKeys.SIZE, "getSize", "()I", "contains", "containsAll", "isEmpty", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class IntervalTree implements Collection<VirtualView>, KMutableCollection {
    private final boolean horizontal;
    private final Map<String, IntervalNode> idToIntervalNode = new LinkedHashMap();
    private IntervalNode root;

    @Override // java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        return (T[]) CollectionToArray.toArray(this, array);
    }

    public IntervalTree(boolean z) {
        this.horizontal = z;
    }

    @Override // java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof VirtualView) {
            return contains((VirtualView) obj);
        }
        return false;
    }

    @Override // java.util.Collection
    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof VirtualView) {
            return remove((VirtualView) obj);
        }
        return false;
    }

    @Override // java.util.Collection
    public final /* bridge */ int size() {
        return getSize();
    }

    static /* synthetic */ Interval rectToInterval$default(IntervalTree intervalTree, Rect rect, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "";
        }
        return intervalTree.rectToInterval(rect, str);
    }

    private final Interval rectToInterval(Rect rect, String id) {
        if (this.horizontal) {
            int i = rect.left;
            int i2 = rect.right;
            if (id == null) {
                id = "";
            }
            return new Interval(i, i2, id);
        }
        int i3 = rect.top;
        int i4 = rect.bottom;
        if (id == null) {
            id = "";
        }
        return new Interval(i3, i4, id);
    }

    private final int height(IntervalNode node) {
        if (node != null) {
            return node.getHeight();
        }
        return 0;
    }

    private final void updateHeight(IntervalNode node) {
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
    }

    private final void updateMax(IntervalNode node) {
        int end = node.getInterval().getEnd();
        IntervalNode left = node.getLeft();
        int max = left != null ? left.getMax() : Integer.MIN_VALUE;
        IntervalNode right = node.getRight();
        node.setMax(Math.max(end, Math.max(max, right != null ? right.getMax() : Integer.MIN_VALUE)));
    }

    private final int balanceFactor(IntervalNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }

    private final IntervalNode rotateRight(IntervalNode parent) {
        IntervalNode left = parent.getLeft();
        if (left == null) {
            throw new IllegalArgumentException("[IntervalTree] AVL node's left must not be null when rotating right.".toString());
        }
        IntervalNode right = left.getRight();
        left.setRight(parent);
        parent.setLeft(right);
        updateHeight(parent);
        updateMax(parent);
        updateHeight(left);
        updateMax(left);
        return left;
    }

    private final IntervalNode rotateLeft(IntervalNode parent) {
        IntervalNode right = parent.getRight();
        if (right == null) {
            throw new IllegalArgumentException("[IntervalTree] AVL node's right must not be null when rotating left.".toString());
        }
        IntervalNode left = right.getLeft();
        right.setLeft(parent);
        parent.setRight(left);
        updateHeight(parent);
        updateMax(parent);
        updateHeight(right);
        updateMax(right);
        return right;
    }

    private final IntervalNode balance(IntervalNode node) {
        updateHeight(node);
        updateMax(node);
        int iBalanceFactor = balanceFactor(node);
        if (iBalanceFactor > 1) {
            if (balanceFactor(node.getLeft()) < 0) {
                IntervalNode left = node.getLeft();
                if (left != null) {
                    node.setLeft(rotateLeft(left));
                } else {
                    throw new IllegalArgumentException("[IntervalTree] node.left must not be null when performing left rotation around it".toString());
                }
            }
            return rotateRight(node);
        }
        if (iBalanceFactor >= -1) {
            return node;
        }
        if (balanceFactor(node.getRight()) > 0) {
            IntervalNode right = node.getRight();
            if (right != null) {
                node.setRight(rotateRight(right));
            } else {
                throw new IllegalArgumentException("[IntervalTree] node.right must not be null when performing right rotation around it".toString());
            }
        }
        return rotateLeft(node);
    }

    private final int compareIntervals(Interval i1, Interval i2) {
        return i1.getStart() != i2.getStart() ? Intrinsics.compare(i1.getStart(), i2.getStart()) : i1.getEnd() != i2.getEnd() ? Intrinsics.compare(i1.getEnd(), i2.getEnd()) : i1.getId().compareTo(i2.getId());
    }

    private final IntervalNode insert(IntervalNode node, IntervalNode intervalNode) {
        if (node == null) {
            return intervalNode;
        }
        if (compareIntervals(intervalNode.getInterval(), node.getInterval()) < 0) {
            node.setLeft(insert(node.getLeft(), intervalNode));
        } else {
            node.setRight(insert(node.getRight(), intervalNode));
        }
        return balance(node);
    }

    private final IntervalNode findMin(IntervalNode node) {
        IntervalNode intervalNodeFindMin;
        IntervalNode left = node.getLeft();
        return (left == null || (intervalNodeFindMin = findMin(left)) == null) ? node : intervalNodeFindMin;
    }

    private final IntervalNode delete(IntervalNode node, IntervalNode target) {
        if (node == null) {
            return null;
        }
        if (compareIntervals(target.getInterval(), node.getInterval()) < 0) {
            node.setLeft(delete(node.getLeft(), target));
        } else if (compareIntervals(target.getInterval(), node.getInterval()) > 0) {
            node.setRight(delete(node.getRight(), target));
        } else if (node.getLeft() == null) {
            node = node.getRight();
        } else if (node.getRight() == null) {
            node = node.getLeft();
        } else {
            IntervalNode right = node.getRight();
            if (right != null) {
                IntervalNode intervalNodeFindMin = findMin(right);
                node.setVirtualView(intervalNodeFindMin.getVirtualView());
                node.setInterval(intervalNodeFindMin.getInterval());
                node.setRight(delete(node.getRight(), intervalNodeFindMin));
            } else {
                throw new IllegalArgumentException("[IntervalTree] node.right must not be null when finding node's successor".toString());
            }
        }
        if (node == null) {
            return null;
        }
        return balance(node);
    }

    private final void inorderTraversal(IntervalNode node, List<VirtualView> results) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.getLeft(), results);
        results.add(node.getVirtualView());
        inorderTraversal(node.getRight(), results);
    }

    public final Set<String> query(Rect queryRect) {
        Intrinsics.checkNotNullParameter(queryRect, "queryRect");
        Interval intervalRectToInterval$default = rectToInterval$default(this, queryRect, null, 2, null);
        HashSet hashSet = new HashSet();
        if (VirtualViewContainerKt.getIS_DEBUG_BUILD() && ReactNativeFeatureFlags.enableVirtualViewDebugFeatures()) {
            FLog.d("VirtualViewContainerStateExperimental:query", "Querying tree for rect " + queryRect);
        }
        HashSet hashSet2 = hashSet;
        queryHelper(this.root, intervalRectToInterval$default, hashSet2);
        if (VirtualViewContainerKt.getIS_DEBUG_BUILD() && ReactNativeFeatureFlags.enableVirtualViewDebugFeatures()) {
            FLog.d("VirtualViewContainerStateExperimental:query", "Query results: " + hashSet);
        }
        return hashSet2;
    }

    public final VirtualView getVirtualView(String virtualViewID) {
        Intrinsics.checkNotNullParameter(virtualViewID, "virtualViewID");
        IntervalNode intervalNode = this.idToIntervalNode.get(virtualViewID);
        if (intervalNode != null) {
            return intervalNode.getVirtualView();
        }
        return null;
    }

    public final List<VirtualView> traverse() {
        ArrayList arrayList = new ArrayList();
        inorderTraversal(this.root, arrayList);
        return arrayList;
    }

    @Override // java.util.Collection
    public boolean add(VirtualView element) {
        boolean z;
        Intrinsics.checkNotNullParameter(element, "element");
        String virtualViewID = element.getVirtualViewID();
        IntervalNode intervalNode = this.idToIntervalNode.get(virtualViewID);
        if (intervalNode != null) {
            this.root = delete(this.root, intervalNode);
            z = false;
        } else {
            z = true;
        }
        IntervalNode intervalNode2 = new IntervalNode(rectToInterval(element.getContainerRelativeRect(), virtualViewID), element, 0, 0, null, null, 60, null);
        this.root = insert(this.root, intervalNode2);
        this.idToIntervalNode.put(virtualViewID, intervalNode2);
        if (VirtualViewContainerKt.getIS_DEBUG_BUILD() && ReactNativeFeatureFlags.enableVirtualViewDebugFeatures()) {
            FLog.d("VirtualViewContainerStateExperimental:IntervalTree: add", "New VirtualView: (" + element.getVirtualViewID() + ", " + element.getContainerRelativeRect() + "). Node interval " + intervalNode2.getInterval().getId());
        }
        return z;
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends VirtualView> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Iterator<T> it = elements.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (add((VirtualView) it.next())) {
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.Collection
    public void clear() {
        this.root = null;
        this.idToIntervalNode.clear();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<VirtualView> iterator() {
        ArrayList arrayList = new ArrayList();
        inorderTraversal(this.root, arrayList);
        return arrayList.iterator();
    }

    public boolean remove(VirtualView element) {
        Intrinsics.checkNotNullParameter(element, "element");
        IntervalNode intervalNode = this.idToIntervalNode.get(element.getVirtualViewID());
        if (intervalNode == null) {
            return false;
        }
        this.root = delete(this.root, intervalNode);
        this.idToIntervalNode.remove(element.getVirtualViewID());
        return true;
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<?> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Iterator<T> it = elements.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (remove(it.next())) {
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<?> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        throw new Error("IntervalTree does not support retainAll yet");
    }

    public int getSize() {
        return this.idToIntervalNode.size();
    }

    public boolean contains(VirtualView element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return this.idToIntervalNode.containsKey(element.getVirtualViewID());
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        Collection<?> collection = elements;
        if (collection.isEmpty()) {
            return true;
        }
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return size() == 0;
    }

    private final void queryHelper(IntervalNode node, Interval interval, Set<String> results) {
        VirtualView virtualView;
        VirtualView virtualView2;
        if (VirtualViewContainerKt.getIS_DEBUG_BUILD() && ReactNativeFeatureFlags.enableVirtualViewDebugFeatures()) {
            Rect containerRelativeRect = null;
            String virtualViewID = (node == null || (virtualView2 = node.getVirtualView()) == null) ? null : virtualView2.getVirtualViewID();
            if (node != null && (virtualView = node.getVirtualView()) != null) {
                containerRelativeRect = virtualView.getContainerRelativeRect();
            }
            FLog.d("VirtualViewContainerStateExperimental:queryHelper", "Check node (" + virtualViewID + ", " + containerRelativeRect + ") against interval(" + interval.getStart() + ", " + interval.getEnd() + ")");
        }
        if (node == null || node.getMax() <= interval.getStart()) {
            return;
        }
        queryHelper(node.getLeft(), interval, results);
        if (node.getInterval().intersects(interval)) {
            results.add(node.getVirtualView().getVirtualViewID());
        }
        if (node.getInterval().getStart() < interval.getEnd()) {
            queryHelper(node.getRight(), interval, results);
        }
    }
}
