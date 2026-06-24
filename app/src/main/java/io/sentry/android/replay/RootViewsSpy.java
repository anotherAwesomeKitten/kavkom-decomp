package io.sentry.android.replay;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Windows.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\u0000R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lio/sentry/android/replay/RootViewsSpy;", "", "()V", "delegatingViewList", "Ljava/util/ArrayList;", "Landroid/view/View;", "Lkotlin/collections/ArrayList;", "listeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lio/sentry/android/replay/OnRootViewsChangedListener;", "getListeners", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "install", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RootViewsSpy {
    public static final RootViewsSpy INSTANCE = new RootViewsSpy();
    private static final CopyOnWriteArrayList<OnRootViewsChangedListener> listeners = new CopyOnWriteArrayList<OnRootViewsChangedListener>() { // from class: io.sentry.android.replay.RootViewsSpy$listeners$1
        public /* bridge */ boolean contains(OnRootViewsChangedListener onRootViewsChangedListener) {
            return super.contains((Object) onRootViewsChangedListener);
        }

        @Override // java.util.concurrent.CopyOnWriteArrayList, java.util.List, java.util.Collection
        public final /* bridge */ boolean contains(Object obj) {
            if (obj == null ? true : obj instanceof OnRootViewsChangedListener) {
                return contains((OnRootViewsChangedListener) obj);
            }
            return false;
        }

        public /* bridge */ int getSize() {
            return super.size();
        }

        public /* bridge */ int indexOf(OnRootViewsChangedListener onRootViewsChangedListener) {
            return super.indexOf((Object) onRootViewsChangedListener);
        }

        @Override // java.util.concurrent.CopyOnWriteArrayList, java.util.List
        public final /* bridge */ int indexOf(Object obj) {
            if (obj == null ? true : obj instanceof OnRootViewsChangedListener) {
                return indexOf((OnRootViewsChangedListener) obj);
            }
            return -1;
        }

        public /* bridge */ int lastIndexOf(OnRootViewsChangedListener onRootViewsChangedListener) {
            return super.lastIndexOf((Object) onRootViewsChangedListener);
        }

        @Override // java.util.concurrent.CopyOnWriteArrayList, java.util.List
        public final /* bridge */ int lastIndexOf(Object obj) {
            if (obj == null ? true : obj instanceof OnRootViewsChangedListener) {
                return lastIndexOf((OnRootViewsChangedListener) obj);
            }
            return -1;
        }

        @Override // java.util.concurrent.CopyOnWriteArrayList, java.util.List
        public final /* bridge */ OnRootViewsChangedListener remove(int i) {
            return removeAt(i);
        }

        public /* bridge */ boolean remove(OnRootViewsChangedListener onRootViewsChangedListener) {
            return super.remove((Object) onRootViewsChangedListener);
        }

        @Override // java.util.concurrent.CopyOnWriteArrayList, java.util.List, java.util.Collection
        public final /* bridge */ boolean remove(Object obj) {
            if (obj == null ? true : obj instanceof OnRootViewsChangedListener) {
                return remove((OnRootViewsChangedListener) obj);
            }
            return false;
        }

        public /* bridge */ OnRootViewsChangedListener removeAt(int i) {
            return (OnRootViewsChangedListener) super.remove(i);
        }

        @Override // java.util.concurrent.CopyOnWriteArrayList, java.util.List, java.util.Collection
        public final /* bridge */ int size() {
            return getSize();
        }

        @Override // java.util.concurrent.CopyOnWriteArrayList, java.util.List, java.util.Collection
        public boolean add(OnRootViewsChangedListener element) {
            for (View view : RootViewsSpy.delegatingViewList) {
                if (element != null) {
                    element.onRootViewsChanged(view, true);
                }
            }
            return super.add(element);
        }
    };
    private static final ArrayList<View> delegatingViewList = new ArrayList<View>() { // from class: io.sentry.android.replay.RootViewsSpy$delegatingViewList$1
        public /* bridge */ boolean contains(View view) {
            return super.contains((Object) view);
        }

        @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public final /* bridge */ boolean contains(Object obj) {
            if (obj instanceof View) {
                return contains((View) obj);
            }
            return false;
        }

        public /* bridge */ int getSize() {
            return super.size();
        }

        public /* bridge */ int indexOf(View view) {
            return super.indexOf((Object) view);
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
        public final /* bridge */ int indexOf(Object obj) {
            if (obj instanceof View) {
                return indexOf((View) obj);
            }
            return -1;
        }

        public /* bridge */ int lastIndexOf(View view) {
            return super.lastIndexOf((Object) view);
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
        public final /* bridge */ int lastIndexOf(Object obj) {
            if (obj instanceof View) {
                return lastIndexOf((View) obj);
            }
            return -1;
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
        public final /* bridge */ View remove(int i) {
            return removeAt(i);
        }

        public /* bridge */ boolean remove(View view) {
            return super.remove((Object) view);
        }

        @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public final /* bridge */ boolean remove(Object obj) {
            if (obj instanceof View) {
                return remove((View) obj);
            }
            return false;
        }

        @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public final /* bridge */ int size() {
            return getSize();
        }

        @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean addAll(Collection<? extends View> elements) {
            Intrinsics.checkNotNullParameter(elements, "elements");
            for (OnRootViewsChangedListener onRootViewsChangedListener : RootViewsSpy.INSTANCE.getListeners()) {
                Iterator<T> it = elements.iterator();
                while (it.hasNext()) {
                    onRootViewsChangedListener.onRootViewsChanged((View) it.next(), true);
                }
            }
            return super.addAll(elements);
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean add(View element) {
            Intrinsics.checkNotNullParameter(element, "element");
            Iterator<T> it = RootViewsSpy.INSTANCE.getListeners().iterator();
            while (it.hasNext()) {
                ((OnRootViewsChangedListener) it.next()).onRootViewsChanged(element, true);
            }
            return super.add(element);
        }

        public View removeAt(int index) {
            Object objRemove = super.remove(index);
            Intrinsics.checkNotNullExpressionValue(objRemove, "super.removeAt(index)");
            View view = (View) objRemove;
            Iterator<T> it = RootViewsSpy.INSTANCE.getListeners().iterator();
            while (it.hasNext()) {
                ((OnRootViewsChangedListener) it.next()).onRootViewsChanged(view, false);
            }
            return view;
        }
    };

    private RootViewsSpy() {
    }

    public final CopyOnWriteArrayList<OnRootViewsChangedListener> getListeners() {
        return listeners;
    }

    public final RootViewsSpy install() {
        new Handler(Looper.getMainLooper()).postAtFrontOfQueue(new Runnable() { // from class: io.sentry.android.replay.RootViewsSpy$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                RootViewsSpy.install$lambda$1$lambda$0();
            }
        });
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void install$lambda$1$lambda$0() {
        WindowManagerSpy.INSTANCE.swapWindowManagerGlobalMViews(new Function1<ArrayList<View>, ArrayList<View>>() { // from class: io.sentry.android.replay.RootViewsSpy$install$1$1$1
            @Override // kotlin.jvm.functions.Function1
            public final ArrayList<View> invoke(ArrayList<View> mViews) {
                Intrinsics.checkNotNullParameter(mViews, "mViews");
                ArrayList<View> arrayList = RootViewsSpy.delegatingViewList;
                arrayList.addAll(mViews);
                return arrayList;
            }
        });
    }
}
