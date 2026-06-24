package com.facebook.react.animated;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.views.view.ColorUtil;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ColorAnimatedNode.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000  2\u00020\u00012\u00020\u0002:\u0001 B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\r\u0010\u0018\u001a\u00020\u0019H\u0010¢\u0006\u0002\b\u001aJ\b\u0010\u001b\u001a\u00020\u0017H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0013\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u001c\u001a\u0004\u0018\u00010\u001d8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006!"}, d2 = {"Lcom/facebook/react/animated/ColorAnimatedNode;", "Lcom/facebook/react/animated/AnimatedNode;", "Lcom/facebook/react/animated/AnimatedNodeWithUpdateableConfig;", "config", "Lcom/facebook/react/bridge/ReadableMap;", "nativeAnimatedNodesManager", "Lcom/facebook/react/animated/NativeAnimatedNodesManager;", "reactApplicationContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "<init>", "(Lcom/facebook/react/bridge/ReadableMap;Lcom/facebook/react/animated/NativeAnimatedNodesManager;Lcom/facebook/react/bridge/ReactApplicationContext;)V", "rNodeId", "", "gNodeId", "bNodeId", "aNodeId", "nativeColor", "nativeColorApplied", "", "color", "getColor", "()I", "onUpdateConfig", "", "prettyPrint", "", "prettyPrint$ReactAndroid_release", "tryApplyNativeColor", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ColorAnimatedNode extends AnimatedNode implements AnimatedNodeWithUpdateableConfig {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private int aNodeId;
    private int bNodeId;
    private int gNodeId;
    private final NativeAnimatedNodesManager nativeAnimatedNodesManager;
    private ReadableMap nativeColor;
    private boolean nativeColorApplied;
    private int rNodeId;
    private final ReactApplicationContext reactApplicationContext;

    public ColorAnimatedNode(ReadableMap config, NativeAnimatedNodesManager nativeAnimatedNodesManager, ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(nativeAnimatedNodesManager, "nativeAnimatedNodesManager");
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactApplicationContext");
        this.nativeAnimatedNodesManager = nativeAnimatedNodesManager;
        this.reactApplicationContext = reactApplicationContext;
        onUpdateConfig(config);
    }

    public final int getColor() {
        tryApplyNativeColor();
        ValueAnimatedNode valueAnimatedNode = (ValueAnimatedNode) this.nativeAnimatedNodesManager.getNodeById(this.rNodeId);
        ValueAnimatedNode valueAnimatedNode2 = (ValueAnimatedNode) this.nativeAnimatedNodesManager.getNodeById(this.gNodeId);
        ValueAnimatedNode valueAnimatedNode3 = (ValueAnimatedNode) this.nativeAnimatedNodesManager.getNodeById(this.bNodeId);
        ValueAnimatedNode valueAnimatedNode4 = (ValueAnimatedNode) this.nativeAnimatedNodesManager.getNodeById(this.aNodeId);
        return ColorUtil.normalize(valueAnimatedNode != null ? valueAnimatedNode.nodeValue : 0.0d, valueAnimatedNode2 != null ? valueAnimatedNode2.nodeValue : 0.0d, valueAnimatedNode3 != null ? valueAnimatedNode3.nodeValue : 0.0d, valueAnimatedNode4 != null ? valueAnimatedNode4.nodeValue : 0.0d);
    }

    @Override // com.facebook.react.animated.AnimatedNodeWithUpdateableConfig
    public void onUpdateConfig(ReadableMap config) {
        if (config != null) {
            this.rNodeId = config.getInt("r");
            this.gNodeId = config.getInt("g");
            this.bNodeId = config.getInt("b");
            this.aNodeId = config.getInt("a");
            this.nativeColor = config.getMap("nativeColor");
            this.nativeColorApplied = false;
            tryApplyNativeColor();
            return;
        }
        this.rNodeId = 0;
        this.gNodeId = 0;
        this.bNodeId = 0;
        this.aNodeId = 0;
        this.nativeColor = null;
        this.nativeColorApplied = false;
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public String prettyPrint$ReactAndroid_release() {
        return "ColorAnimatedNode[" + this.tag + "]: r: " + this.rNodeId + "  g: " + this.gNodeId + " b: " + this.bNodeId + " a: " + this.aNodeId;
    }

    private final void tryApplyNativeColor() {
        Context context;
        Integer color;
        if (this.nativeColor == null || this.nativeColorApplied || (context = getContext()) == null || (color = ColorPropConverter.getColor(this.nativeColor, context)) == null) {
            return;
        }
        int iIntValue = color.intValue();
        ValueAnimatedNode valueAnimatedNode = (ValueAnimatedNode) this.nativeAnimatedNodesManager.getNodeById(this.rNodeId);
        ValueAnimatedNode valueAnimatedNode2 = (ValueAnimatedNode) this.nativeAnimatedNodesManager.getNodeById(this.gNodeId);
        ValueAnimatedNode valueAnimatedNode3 = (ValueAnimatedNode) this.nativeAnimatedNodesManager.getNodeById(this.bNodeId);
        ValueAnimatedNode valueAnimatedNode4 = (ValueAnimatedNode) this.nativeAnimatedNodesManager.getNodeById(this.aNodeId);
        if (valueAnimatedNode != null) {
            valueAnimatedNode.nodeValue = Color.red(iIntValue);
        }
        if (valueAnimatedNode2 != null) {
            valueAnimatedNode2.nodeValue = Color.green(iIntValue);
        }
        if (valueAnimatedNode3 != null) {
            valueAnimatedNode3.nodeValue = Color.blue(iIntValue);
        }
        if (valueAnimatedNode4 != null) {
            valueAnimatedNode4.nodeValue = ((double) Color.alpha(iIntValue)) / 255.0d;
        }
        this.nativeColorApplied = true;
    }

    private final Context getContext() {
        Activity currentActivity = this.reactApplicationContext.getCurrentActivity();
        return currentActivity != null ? currentActivity : INSTANCE.getContextHelper(this);
    }

    /* JADX INFO: compiled from: ColorAnimatedNode.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¨\u0006\b"}, d2 = {"Lcom/facebook/react/animated/ColorAnimatedNode$Companion;", "", "<init>", "()V", "getContextHelper", "Landroid/content/Context;", "node", "Lcom/facebook/react/animated/AnimatedNode;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Context getContextHelper(AnimatedNode node) {
            List<AnimatedNode> list = node.children;
            if (list != null) {
                Iterator<AnimatedNode> it = list.iterator();
                if (it.hasNext()) {
                    AnimatedNode next = it.next();
                    if (next instanceof PropsAnimatedNode) {
                        View connectedView = ((PropsAnimatedNode) next).getConnectedView();
                        if (connectedView != null) {
                            return connectedView.getContext();
                        }
                        return null;
                    }
                    return ColorAnimatedNode.INSTANCE.getContextHelper(next);
                }
            }
            return null;
        }
    }
}
