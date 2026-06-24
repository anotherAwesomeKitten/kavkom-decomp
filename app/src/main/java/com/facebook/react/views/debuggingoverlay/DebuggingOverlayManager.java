package com.facebook.react.views.debuggingoverlay;

import android.graphics.RectF;
import com.facebook.react.bridge.NoSuchKeyException;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UnexpectedNativeTypeException;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.viewmanagers.DebuggingOverlayManagerDelegate;
import com.facebook.react.viewmanagers.DebuggingOverlayManagerInterface;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DebuggingOverlayManager.kt */
/* JADX INFO: loaded from: classes.dex */
@ReactModule(name = DebuggingOverlayManager.REACT_CLASS)
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0001\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003:\u0001\u0016B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007H\u0014J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\rH\u0016J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/facebook/react/views/debuggingoverlay/DebuggingOverlayManager;", "Lcom/facebook/react/uimanager/SimpleViewManager;", "Lcom/facebook/react/views/debuggingoverlay/DebuggingOverlay;", "Lcom/facebook/react/viewmanagers/DebuggingOverlayManagerInterface;", "<init>", "()V", "delegate", "Lcom/facebook/react/uimanager/ViewManagerDelegate;", "getDelegate", "highlightTraceUpdates", "", "view", "updates", "Lcom/facebook/react/bridge/ReadableArray;", "highlightElements", "elements", "clearElementsHighlights", "createViewInstance", "context", "Lcom/facebook/react/uimanager/ThemedReactContext;", "getName", "", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DebuggingOverlayManager extends SimpleViewManager<DebuggingOverlay> implements DebuggingOverlayManagerInterface<DebuggingOverlay> {
    public static final String REACT_CLASS = "DebuggingOverlay";
    private final ViewManagerDelegate<DebuggingOverlay> delegate = new DebuggingOverlayManagerDelegate(this);

    @Override // com.facebook.react.uimanager.ViewManager
    protected ViewManagerDelegate<DebuggingOverlay> getDelegate() {
        return this.delegate;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x00ba, code lost:
    
        if (r5 == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00bc, code lost:
    
        r20.setTraceUpdates(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00bf, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:?, code lost:
    
        return;
     */
    @Override // com.facebook.react.viewmanagers.DebuggingOverlayManagerInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void highlightTraceUpdates(com.facebook.react.views.debuggingoverlay.DebuggingOverlay r20, com.facebook.react.bridge.ReadableArray r21) throws java.lang.Exception {
        /*
            r19 = this;
            r1 = r20
            r2 = r21
            java.lang.String r0 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            java.lang.String r0 = "updates"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r3 = r0
            java.util.List r3 = (java.util.List) r3
            int r4 = r2.size()
            r0 = 1
            r6 = 0
        L1e:
            if (r6 >= r4) goto Lb9
            com.facebook.react.bridge.ReadableMap r7 = r2.getMap(r6)
            if (r7 != 0) goto L28
            goto Lb5
        L28:
            java.lang.String r8 = "rectangle"
            com.facebook.react.bridge.ReadableMap r8 = r7.getMap(r8)
            java.lang.String r9 = "DebuggingOverlay"
            if (r8 != 0) goto L41
            com.facebook.react.bridge.ReactNoCrashSoftException r0 = new com.facebook.react.bridge.ReactNoCrashSoftException
            java.lang.String r2 = "Unexpected payload for highlighting trace updates: rectangle field is null"
            r0.<init>(r2)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            com.facebook.react.bridge.ReactSoftExceptionLogger.logSoftException(r9, r0)
            r5 = 0
            goto Lba
        L41:
            java.lang.String r10 = "id"
            int r10 = r7.getInt(r10)
            java.lang.String r11 = "color"
            int r7 = r7.getInt(r11)
            java.lang.String r11 = "x"
            double r11 = r8.getDouble(r11)     // Catch: java.lang.Exception -> L9b
            float r11 = (float) r11     // Catch: java.lang.Exception -> L9b
            java.lang.String r12 = "y"
            double r12 = r8.getDouble(r12)     // Catch: java.lang.Exception -> L9b
            float r12 = (float) r12     // Catch: java.lang.Exception -> L9b
            double r13 = (double) r11     // Catch: java.lang.Exception -> L9b
            java.lang.String r15 = "width"
            double r15 = r8.getDouble(r15)     // Catch: java.lang.Exception -> L9b
            double r13 = r13 + r15
            float r13 = (float) r13     // Catch: java.lang.Exception -> L9b
            double r14 = (double) r12     // Catch: java.lang.Exception -> L9b
            java.lang.String r5 = "height"
            double r17 = r8.getDouble(r5)     // Catch: java.lang.Exception -> L9b
            double r14 = r14 + r17
            float r5 = (float) r14     // Catch: java.lang.Exception -> L9b
            android.graphics.RectF r8 = new android.graphics.RectF     // Catch: java.lang.Exception -> L9b
            com.facebook.react.uimanager.PixelUtil r14 = com.facebook.react.uimanager.PixelUtil.INSTANCE     // Catch: java.lang.Exception -> L9b
            float r11 = r14.dpToPx(r11)     // Catch: java.lang.Exception -> L9b
            com.facebook.react.uimanager.PixelUtil r14 = com.facebook.react.uimanager.PixelUtil.INSTANCE     // Catch: java.lang.Exception -> L9b
            float r12 = r14.dpToPx(r12)     // Catch: java.lang.Exception -> L9b
            com.facebook.react.uimanager.PixelUtil r14 = com.facebook.react.uimanager.PixelUtil.INSTANCE     // Catch: java.lang.Exception -> L9b
            float r13 = r14.dpToPx(r13)     // Catch: java.lang.Exception -> L9b
            com.facebook.react.uimanager.PixelUtil r14 = com.facebook.react.uimanager.PixelUtil.INSTANCE     // Catch: java.lang.Exception -> L9b
            float r5 = r14.dpToPx(r5)     // Catch: java.lang.Exception -> L9b
            r8.<init>(r11, r12, r13, r5)     // Catch: java.lang.Exception -> L9b
            com.facebook.react.views.debuggingoverlay.TraceUpdate r5 = new com.facebook.react.views.debuggingoverlay.TraceUpdate     // Catch: java.lang.Exception -> L9b
            r5.<init>(r10, r8, r7)     // Catch: java.lang.Exception -> L9b
            boolean r5 = r3.add(r5)     // Catch: java.lang.Exception -> L9b
            java.lang.Boolean.valueOf(r5)     // Catch: java.lang.Exception -> L9b
            goto Lb5
        L9b:
            r0 = move-exception
            boolean r5 = r0 instanceof com.facebook.react.bridge.NoSuchKeyException
            if (r5 != 0) goto La6
            boolean r5 = r0 instanceof com.facebook.react.bridge.UnexpectedNativeTypeException
            if (r5 == 0) goto La5
            goto La6
        La5:
            throw r0
        La6:
            com.facebook.react.bridge.ReactNoCrashSoftException r0 = new com.facebook.react.bridge.ReactNoCrashSoftException
            java.lang.String r5 = "Unexpected payload for highlighting trace updates: rectangle field should have x, y, width, height fields"
            r0.<init>(r5)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            com.facebook.react.bridge.ReactSoftExceptionLogger.logSoftException(r9, r0)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            r0 = 0
        Lb5:
            int r6 = r6 + 1
            goto L1e
        Lb9:
            r5 = r0
        Lba:
            if (r5 == 0) goto Lbf
            r1.setTraceUpdates(r3)
        Lbf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.debuggingoverlay.DebuggingOverlayManager.highlightTraceUpdates(com.facebook.react.views.debuggingoverlay.DebuggingOverlay, com.facebook.react.bridge.ReadableArray):void");
    }

    @Override // com.facebook.react.viewmanagers.DebuggingOverlayManagerInterface
    public void highlightElements(DebuggingOverlay view, ReadableArray elements) throws Exception {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(elements, "elements");
        ArrayList arrayList = new ArrayList();
        int size = elements.size();
        boolean z = true;
        for (int i = 0; i < size; i++) {
            ReadableMap map = elements.getMap(i);
            if (map != null) {
                try {
                    float f = (float) map.getDouble("x");
                    float f2 = (float) map.getDouble("y");
                    Boolean.valueOf(arrayList.add(new RectF(PixelUtil.INSTANCE.dpToPx(f), PixelUtil.INSTANCE.dpToPx(f2), PixelUtil.INSTANCE.dpToPx((float) (((double) f) + map.getDouble("width"))), PixelUtil.INSTANCE.dpToPx((float) (((double) f2) + map.getDouble("height"))))));
                } catch (Exception e) {
                    if ((e instanceof NoSuchKeyException) || (e instanceof UnexpectedNativeTypeException)) {
                        ReactSoftExceptionLogger.logSoftException(REACT_CLASS, new ReactNoCrashSoftException("Unexpected payload for highlighting elements: every element should have x, y, width, height fields"));
                        Unit unit = Unit.INSTANCE;
                        z = false;
                    } else {
                        throw e;
                    }
                }
            }
        }
        if (z) {
            view.setHighlightedElementsRectangles(arrayList);
        }
    }

    @Override // com.facebook.react.viewmanagers.DebuggingOverlayManagerInterface
    public void clearElementsHighlights(DebuggingOverlay view) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.clearElementsHighlights();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public DebuggingOverlay createViewInstance(ThemedReactContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new DebuggingOverlay(context);
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }
}
