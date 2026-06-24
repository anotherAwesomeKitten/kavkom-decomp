package com.facebook.react.uimanager;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import com.facebook.react.bridge.UiThreadUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ViewManagerRegistry.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B'\b\u0016\u0012\u001c\u0010\u0006\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0006\b\u0000\u0012\u00020\t\u0012\u0006\b\u0000\u0012\u00020\t0\b0\u0007¢\u0006\u0004\b\u0004\u0010\nB'\b\u0016\u0012\u001c\u0010\u000b\u001a\u0018\u0012\u0004\u0012\u00020\r\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\b\u0018\u00010\f¢\u0006\u0004\b\u0004\u0010\u000eJ\u0016\u0010\u0011\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\b2\u0006\u0010\u0012\u001a\u00020\rJ\u001a\u0010\u0013\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\b2\u0006\u0010\u0012\u001a\u00020\rH\u0002J\u001a\u0010\u0014\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\b2\u0006\u0010\u0012\u001a\u00020\rH\u0001J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0016J\u0010\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u0018H\u0016J\u0010\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0016H\u0017R\"\u0010\u000f\u001a\u0016\u0012\u0004\u0012\u00020\r\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\b0\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/facebook/react/uimanager/ViewManagerRegistry;", "Landroid/content/ComponentCallbacks2;", "viewManagerResolver", "Lcom/facebook/react/uimanager/ViewManagerResolver;", "<init>", "(Lcom/facebook/react/uimanager/ViewManagerResolver;)V", "viewManagerList", "", "Lcom/facebook/react/uimanager/ViewManager;", "", "(Ljava/util/List;)V", "viewManagerMap", "", "", "(Ljava/util/Map;)V", "viewManagersMap", "", "get", "className", "getViewManagerFromResolver", "getViewManagerIfExists", "onSurfaceStopped", "", "surfaceId", "", "invalidate", "onTrimMemory", "level", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onLowMemory", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ViewManagerRegistry implements ComponentCallbacks2 {
    private final ViewManagerResolver viewManagerResolver;
    private final Map<String, ViewManager<?, ?>> viewManagersMap;

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
    }

    public ViewManagerRegistry(ViewManagerResolver viewManagerResolver) {
        Intrinsics.checkNotNullParameter(viewManagerResolver, "viewManagerResolver");
        this.viewManagersMap = new LinkedHashMap();
        this.viewManagerResolver = viewManagerResolver;
    }

    public ViewManagerRegistry(List<? extends ViewManager> viewManagerList) {
        Intrinsics.checkNotNullParameter(viewManagerList, "viewManagerList");
        List<? extends ViewManager> list = viewManagerList;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list, 10)), 16));
        for (Object obj : list) {
            linkedHashMap.put(((ViewManager) obj).getName(), obj);
        }
        this.viewManagersMap = MapsKt.toMutableMap(linkedHashMap);
        this.viewManagerResolver = null;
    }

    public ViewManagerRegistry(Map<String, ? extends ViewManager<?, ?>> map) {
        LinkedHashMap mutableMap;
        this.viewManagersMap = (map == null || (mutableMap = MapsKt.toMutableMap(map)) == null) ? new LinkedHashMap() : mutableMap;
        this.viewManagerResolver = null;
    }

    public final synchronized ViewManager<?, ?> get(String className) {
        Intrinsics.checkNotNullParameter(className, "className");
        ViewManager<?, ?> viewManager = this.viewManagersMap.get(className);
        if (viewManager != null) {
            return viewManager;
        }
        String str = "RCT" + className;
        ViewManager<?, ?> viewManager2 = this.viewManagersMap.get(str);
        if (viewManager2 != null) {
            return viewManager2;
        }
        if (this.viewManagerResolver != null) {
            ViewManager<?, ?> viewManagerFromResolver = getViewManagerFromResolver(className);
            if (viewManagerFromResolver != null) {
                return viewManagerFromResolver;
            }
            ViewManager<?, ?> viewManagerFromResolver2 = getViewManagerFromResolver(str);
            if (viewManagerFromResolver2 != null) {
                return viewManagerFromResolver2;
            }
            throw new IllegalViewOperationException("Can't find ViewManager '" + className + "' nor '" + str + "' in ViewManagerRegistry, existing names are: " + this.viewManagerResolver.getViewManagerNames());
        }
        throw new IllegalViewOperationException("No ViewManager found for class " + className);
    }

    private final ViewManager<?, ?> getViewManagerFromResolver(String className) {
        ViewManagerResolver viewManagerResolver = this.viewManagerResolver;
        ViewManager<?, ?> viewManager = viewManagerResolver != null ? viewManagerResolver.getViewManager(className) : null;
        if (viewManager != null) {
            this.viewManagersMap.put(className, viewManager);
        }
        return viewManager;
    }

    public final synchronized ViewManager<?, ?> getViewManagerIfExists(String className) {
        Intrinsics.checkNotNullParameter(className, "className");
        ViewManager<?, ?> viewManager = this.viewManagersMap.get(className);
        if (viewManager != null) {
            return viewManager;
        }
        return this.viewManagerResolver != null ? getViewManagerFromResolver(className) : null;
    }

    public final void onSurfaceStopped(final int surfaceId) {
        ArrayList arrayList;
        synchronized (this) {
            arrayList = new ArrayList(this.viewManagersMap.values());
        }
        final ArrayList arrayList2 = arrayList;
        final Function0 function0 = new Function0() { // from class: com.facebook.react.uimanager.ViewManagerRegistry$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ViewManagerRegistry.onSurfaceStopped$lambda$6(arrayList2, surfaceId);
            }
        };
        if (UiThreadUtil.isOnUiThread()) {
            function0.invoke();
        } else {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.uimanager.ViewManagerRegistry$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    function0.invoke();
                }
            });
        }
    }

    public static final Unit onSurfaceStopped$lambda$6(List list, int i) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((ViewManager) it.next()).onSurfaceStopped(i);
        }
        return Unit.INSTANCE;
    }

    public final void invalidate() {
        ArrayList arrayList;
        synchronized (this) {
            arrayList = new ArrayList(this.viewManagersMap.values());
        }
        final ArrayList arrayList2 = arrayList;
        final Function0 function0 = new Function0() { // from class: com.facebook.react.uimanager.ViewManagerRegistry$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ViewManagerRegistry.invalidate$lambda$9(arrayList2);
            }
        };
        if (UiThreadUtil.isOnUiThread()) {
            function0.invoke();
        } else {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.uimanager.ViewManagerRegistry$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    function0.invoke();
                }
            });
        }
    }

    public static final Unit invalidate$lambda$9(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((ViewManager) it.next()).invalidate();
        }
        return Unit.INSTANCE;
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int level) {
        ArrayList arrayList;
        synchronized (this) {
            arrayList = new ArrayList(this.viewManagersMap.values());
        }
        final ArrayList arrayList2 = arrayList;
        final Function0 function0 = new Function0() { // from class: com.facebook.react.uimanager.ViewManagerRegistry$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ViewManagerRegistry.onTrimMemory$lambda$12(arrayList2);
            }
        };
        if (UiThreadUtil.isOnUiThread()) {
            function0.invoke();
        } else {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.uimanager.ViewManagerRegistry$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    function0.invoke();
                }
            });
        }
    }

    public static final Unit onTrimMemory$lambda$12(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((ViewManager) it.next()).trimMemory();
        }
        return Unit.INSTANCE;
    }

    @Override // android.content.ComponentCallbacks
    @Deprecated(message = "Overrides deprecated ComponentCallbacks2.onLowMemory()")
    public void onLowMemory() {
        onTrimMemory(40);
    }
}
