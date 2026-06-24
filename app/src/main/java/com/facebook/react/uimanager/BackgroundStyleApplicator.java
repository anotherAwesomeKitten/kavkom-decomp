package com.facebook.react.uimanager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import androidx.core.view.ViewCompat;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.common.ViewUtil;
import com.facebook.react.uimanager.drawable.BackgroundDrawable;
import com.facebook.react.uimanager.drawable.BackgroundImageDrawable;
import com.facebook.react.uimanager.drawable.BorderDrawable;
import com.facebook.react.uimanager.drawable.CompositeBackgroundDrawable;
import com.facebook.react.uimanager.drawable.InsetBoxShadowDrawable;
import com.facebook.react.uimanager.drawable.OutlineDrawable;
import com.facebook.react.uimanager.drawable.OutsetBoxShadowDrawable;
import com.facebook.react.uimanager.style.BackgroundImageLayer;
import com.facebook.react.uimanager.style.BackgroundPosition;
import com.facebook.react.uimanager.style.BackgroundRepeat;
import com.facebook.react.uimanager.style.BackgroundSize;
import com.facebook.react.uimanager.style.BorderInsets;
import com.facebook.react.uimanager.style.BorderRadiusProp;
import com.facebook.react.uimanager.style.BorderRadiusStyle;
import com.facebook.react.uimanager.style.BorderStyle;
import com.facebook.react.uimanager.style.BoxShadow;
import com.facebook.react.uimanager.style.ComputedBorderRadius;
import com.facebook.react.uimanager.style.CornerRadii;
import com.facebook.react.uimanager.style.LogicalEdge;
import com.facebook.react.uimanager.style.OutlineStyle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: BackgroundStyleApplicator.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Ä\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0002\u0010\nJ \u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rH\u0007J%\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\rH\u0001¢\u0006\u0002\b\u0012J%\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\rH\u0001¢\u0006\u0002\b\u0016J%\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\rH\u0001¢\u0006\u0002\b\u001aJ\u0017\u0010\u001b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\u001cJ'\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0007¢\u0006\u0002\u0010\"J\u001f\u0010#\u001a\u0004\u0018\u00010!2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001fH\u0007¢\u0006\u0002\u0010$J)\u0010%\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001f2\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0002\u0010&J\u001f\u0010'\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001fH\u0007¢\u0006\u0002\u0010(J\"\u0010)\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-H\u0007J\u001a\u0010.\u001a\u0004\u0018\u00010-2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010*\u001a\u00020+H\u0007J\u001a\u0010/\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u00100\u001a\u0004\u0018\u000101H\u0007J\u0012\u00102\u001a\u0004\u0018\u0001012\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J!\u00103\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0001\u00104\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0002\u0010\nJ\u0017\u00105\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\u001cJ\u0018\u00106\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u00107\u001a\u00020!H\u0007J\u0015\u00108\u001a\u0004\u0018\u00010!2\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u00109J\u001a\u0010:\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010;\u001a\u0004\u0018\u00010<H\u0007J\u0010\u0010=\u001a\u0004\u0018\u00010<2\u0006\u0010\u0006\u001a\u00020\u0007J\u0018\u0010>\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010 \u001a\u00020!H\u0007J\u0015\u0010?\u001a\u0004\u0018\u00010!2\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u00109J\u001e\u0010@\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010A\u001a\b\u0012\u0004\u0012\u00020B0\rH\u0007J\u001a\u0010@\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010A\u001a\u0004\u0018\u00010CH\u0007J\u001a\u0010D\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010E\u001a\u0004\u0018\u00010FH\u0007J\u0018\u0010G\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010H\u001a\u00020IH\u0007J\u0010\u0010J\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010K\u001a\u00020L2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0012\u0010M\u001a\u0004\u0018\u00010L2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010N\u001a\u00020O2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0012\u0010P\u001a\u0004\u0018\u00010O2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010Q\u001a\u00020R2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0012\u0010S\u001a\u0004\u0018\u00010R2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0012\u0010T\u001a\u0004\u0018\u00010U2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010V\u001a\u00020U2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010W\u001a\u00020X2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0012\u0010Y\u001a\u0004\u0018\u00010X2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J!\u0010Z\u001a\u00020!2\b\u0010[\u001a\u0004\u0018\u00010!2\b\u0010\\\u001a\u0004\u0018\u00010!H\u0002¢\u0006\u0002\u0010]J*\u0010^\u001a\u00020_2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010`\u001a\u00020L2\u0006\u0010a\u001a\u00020b2\b\u0010c\u001a\u0004\u0018\u00010bH\u0002¨\u0006d"}, d2 = {"Lcom/facebook/react/uimanager/BackgroundStyleApplicator;", "", "<init>", "()V", "setBackgroundColor", "", "view", "Landroid/view/View;", "color", "", "(Landroid/view/View;Ljava/lang/Integer;)V", "setBackgroundImage", "backgroundImageLayers", "", "Lcom/facebook/react/uimanager/style/BackgroundImageLayer;", "setBackgroundSize", "backgroundSizes", "Lcom/facebook/react/uimanager/style/BackgroundSize;", "setBackgroundSize$ReactAndroid_release", "setBackgroundPosition", "backgroundPositions", "Lcom/facebook/react/uimanager/style/BackgroundPosition;", "setBackgroundPosition$ReactAndroid_release", "setBackgroundRepeat", "backgroundRepeats", "Lcom/facebook/react/uimanager/style/BackgroundRepeat;", "setBackgroundRepeat$ReactAndroid_release", "getBackgroundColor", "(Landroid/view/View;)Ljava/lang/Integer;", "setBorderWidth", "edge", "Lcom/facebook/react/uimanager/style/LogicalEdge;", "width", "", "(Landroid/view/View;Lcom/facebook/react/uimanager/style/LogicalEdge;Ljava/lang/Float;)V", "getBorderWidth", "(Landroid/view/View;Lcom/facebook/react/uimanager/style/LogicalEdge;)Ljava/lang/Float;", "setBorderColor", "(Landroid/view/View;Lcom/facebook/react/uimanager/style/LogicalEdge;Ljava/lang/Integer;)V", "getBorderColor", "(Landroid/view/View;Lcom/facebook/react/uimanager/style/LogicalEdge;)Ljava/lang/Integer;", "setBorderRadius", "corner", "Lcom/facebook/react/uimanager/style/BorderRadiusProp;", "radius", "Lcom/facebook/react/uimanager/LengthPercentage;", "getBorderRadius", "setBorderStyle", "borderStyle", "Lcom/facebook/react/uimanager/style/BorderStyle;", "getBorderStyle", "setOutlineColor", ViewProps.OUTLINE_COLOR, "getOutlineColor", "setOutlineOffset", ViewProps.OUTLINE_OFFSET, "getOutlineOffset", "(Landroid/view/View;)Ljava/lang/Float;", "setOutlineStyle", ViewProps.OUTLINE_STYLE, "Lcom/facebook/react/uimanager/style/OutlineStyle;", "getOutlineStyle", "setOutlineWidth", "getOutlineWidth", "setBoxShadow", "shadows", "Lcom/facebook/react/uimanager/style/BoxShadow;", "Lcom/facebook/react/bridge/ReadableArray;", "setFeedbackUnderlay", "drawable", "Landroid/graphics/drawable/Drawable;", "clipToPaddingBox", "canvas", "Landroid/graphics/Canvas;", "reset", "ensureCompositeBackgroundDrawable", "Lcom/facebook/react/uimanager/drawable/CompositeBackgroundDrawable;", "getCompositeBackgroundDrawable", "ensureBackgroundDrawable", "Lcom/facebook/react/uimanager/drawable/BackgroundDrawable;", "getBackground", "ensureBackgroundImageDrawable", "Lcom/facebook/react/uimanager/drawable/BackgroundImageDrawable;", "getBackgroundImage", "getBorder", "Lcom/facebook/react/uimanager/drawable/BorderDrawable;", "ensureBorderDrawable", "ensureOutlineDrawable", "Lcom/facebook/react/uimanager/drawable/OutlineDrawable;", "getOutlineDrawable", "getInnerBorderRadius", "computedRadius", ViewProps.BORDER_WIDTH, "(Ljava/lang/Float;Ljava/lang/Float;)F", "createPaddingBoxPath", "Landroid/graphics/Path;", "composite", "paddingBoxRect", "Landroid/graphics/RectF;", "computedBorderInsets", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class BackgroundStyleApplicator {
    public static final BackgroundStyleApplicator INSTANCE = new BackgroundStyleApplicator();

    private BackgroundStyleApplicator() {
    }

    @JvmStatic
    public static final void setBackgroundColor(View view, Integer color) {
        Intrinsics.checkNotNullParameter(view, "view");
        if ((color == null || color.intValue() == 0) && !(view.getBackground() instanceof CompositeBackgroundDrawable)) {
            return;
        }
        INSTANCE.ensureBackgroundDrawable(view).setBackgroundColor(color != null ? color.intValue() : 0);
    }

    @JvmStatic
    public static final void setBackgroundImage(View view, List<BackgroundImageLayer> backgroundImageLayers) {
        Intrinsics.checkNotNullParameter(view, "view");
        INSTANCE.ensureBackgroundImageDrawable(view).setBackgroundImageLayers(backgroundImageLayers);
    }

    @JvmStatic
    public static final void setBackgroundSize$ReactAndroid_release(View view, List<? extends BackgroundSize> backgroundSizes) {
        Intrinsics.checkNotNullParameter(view, "view");
        INSTANCE.ensureBackgroundImageDrawable(view).setBackgroundSize(backgroundSizes);
    }

    @JvmStatic
    public static final void setBackgroundPosition$ReactAndroid_release(View view, List<BackgroundPosition> backgroundPositions) {
        Intrinsics.checkNotNullParameter(view, "view");
        INSTANCE.ensureBackgroundImageDrawable(view).setBackgroundPosition(backgroundPositions);
    }

    @JvmStatic
    public static final void setBackgroundRepeat$ReactAndroid_release(View view, List<BackgroundRepeat> backgroundRepeats) {
        Intrinsics.checkNotNullParameter(view, "view");
        INSTANCE.ensureBackgroundImageDrawable(view).setBackgroundRepeat(backgroundRepeats);
    }

    @JvmStatic
    public static final Integer getBackgroundColor(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        BackgroundDrawable background = INSTANCE.getBackground(view);
        if (background != null) {
            return Integer.valueOf(background.getBackgroundColor());
        }
        return null;
    }

    @JvmStatic
    public static final void setBorderWidth(View view, LogicalEdge edge, Float width) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(edge, "edge");
        BackgroundStyleApplicator backgroundStyleApplicator = INSTANCE;
        CompositeBackgroundDrawable compositeBackgroundDrawableEnsureCompositeBackgroundDrawable = backgroundStyleApplicator.ensureCompositeBackgroundDrawable(view);
        BorderInsets borderInsets = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderInsets();
        if (borderInsets == null) {
            borderInsets = new BorderInsets();
        }
        compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.setBorderInsets(borderInsets);
        BorderInsets borderInsets2 = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderInsets();
        if (borderInsets2 != null) {
            borderInsets2.setBorderWidth(edge, width);
        }
        backgroundStyleApplicator.ensureBorderDrawable(view).setBorderWidth(edge.toSpacingType(), width != null ? PixelUtil.INSTANCE.dpToPx(width.floatValue()) : Float.NaN);
        BackgroundDrawable background = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBackground();
        if (background != null) {
            background.setBorderInsets(compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderInsets());
        }
        BackgroundImageDrawable backgroundImage = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBackgroundImage();
        if (backgroundImage != null) {
            backgroundImage.setBorderInsets(compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderInsets());
        }
        BorderDrawable border = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorder();
        if (border != null) {
            border.setBorderInsets(compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderInsets());
        }
        BackgroundDrawable background2 = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBackground();
        if (background2 != null) {
            background2.invalidateSelf();
        }
        BackgroundImageDrawable backgroundImage2 = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBackgroundImage();
        if (backgroundImage2 != null) {
            backgroundImage2.invalidateSelf();
        }
        BorderDrawable border2 = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorder();
        if (border2 != null) {
            border2.invalidateSelf();
        }
        BorderInsets borderInsets3 = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderInsets();
        if (borderInsets3 == null) {
            borderInsets3 = new BorderInsets();
        }
        compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.setBorderInsets(borderInsets3);
        BorderInsets borderInsets4 = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderInsets();
        if (borderInsets4 != null) {
            borderInsets4.setBorderWidth(edge, width);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            List<Drawable> innerShadows = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getInnerShadows();
            ArrayList arrayList = new ArrayList();
            for (Object obj : innerShadows) {
                if (obj instanceof InsetBoxShadowDrawable) {
                    arrayList.add(obj);
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((InsetBoxShadowDrawable) it.next()).setBorderInsets(compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderInsets());
            }
        }
    }

    @JvmStatic
    public static final Float getBorderWidth(View view, LogicalEdge edge) {
        Spacing borderWidth;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(edge, "edge");
        BorderDrawable border = INSTANCE.getBorder(view);
        Float fValueOf = (border == null || (borderWidth = border.getBorderWidth()) == null) ? null : Float.valueOf(borderWidth.getRaw(edge.toSpacingType()));
        if (fValueOf == null || Float.isNaN(fValueOf.floatValue())) {
            return null;
        }
        return Float.valueOf(PixelUtil.INSTANCE.pxToDp(fValueOf.floatValue()));
    }

    @JvmStatic
    public static final void setBorderColor(View view, LogicalEdge edge, Integer color) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(edge, "edge");
        INSTANCE.ensureBorderDrawable(view).setBorderColor(edge, color);
    }

    @JvmStatic
    public static final Integer getBorderColor(View view, LogicalEdge edge) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(edge, "edge");
        BorderDrawable border = INSTANCE.getBorder(view);
        if (border != null) {
            return Integer.valueOf(border.getBorderColor(edge));
        }
        return null;
    }

    @JvmStatic
    public static final void setBorderRadius(View view, BorderRadiusProp corner, LengthPercentage radius) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(corner, "corner");
        BackgroundStyleApplicator backgroundStyleApplicator = INSTANCE;
        CompositeBackgroundDrawable compositeBackgroundDrawableEnsureCompositeBackgroundDrawable = backgroundStyleApplicator.ensureCompositeBackgroundDrawable(view);
        BorderRadiusStyle borderRadius = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderRadius();
        if (borderRadius == null) {
            borderRadius = new BorderRadiusStyle(null, null, null, null, null, null, null, null, null, null, null, null, null, 8191, null);
        }
        compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.setBorderRadius(borderRadius);
        BorderRadiusStyle borderRadius2 = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderRadius();
        if (borderRadius2 != null) {
            borderRadius2.set(corner, radius);
        }
        if (view instanceof ImageView) {
            backgroundStyleApplicator.ensureBackgroundDrawable(view);
        }
        BackgroundDrawable background = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBackground();
        if (background != null) {
            background.setBorderRadius(compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderRadius());
        }
        BackgroundImageDrawable backgroundImage = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBackgroundImage();
        if (backgroundImage != null) {
            backgroundImage.setBorderRadius(compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderRadius());
        }
        BorderDrawable border = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorder();
        if (border != null) {
            border.setBorderRadius(compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderRadius());
        }
        BackgroundDrawable background2 = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBackground();
        if (background2 != null) {
            background2.invalidateSelf();
        }
        BackgroundImageDrawable backgroundImage2 = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBackgroundImage();
        if (backgroundImage2 != null) {
            backgroundImage2.invalidateSelf();
        }
        BorderDrawable border2 = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorder();
        if (border2 != null) {
            border2.invalidateSelf();
        }
        if (Build.VERSION.SDK_INT >= 28) {
            List<Drawable> outerShadows = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getOuterShadows();
            ArrayList arrayList = new ArrayList();
            for (Object obj : outerShadows) {
                if (obj instanceof OutsetBoxShadowDrawable) {
                    arrayList.add(obj);
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((OutsetBoxShadowDrawable) it.next()).setBorderRadius(compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderRadius());
            }
        }
        if (Build.VERSION.SDK_INT >= 29) {
            List<Drawable> innerShadows = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getInnerShadows();
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : innerShadows) {
                if (obj2 instanceof InsetBoxShadowDrawable) {
                    arrayList2.add(obj2);
                }
            }
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                ((InsetBoxShadowDrawable) it2.next()).setBorderRadius(compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderRadius());
            }
        }
        OutlineDrawable outline = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getOutline();
        if (outline != null) {
            outline.setBorderRadius(compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderRadius());
        }
        compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.invalidateSelf();
    }

    @JvmStatic
    public static final LengthPercentage getBorderRadius(View view, BorderRadiusProp corner) {
        BorderRadiusStyle borderRadius;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(corner, "corner");
        CompositeBackgroundDrawable compositeBackgroundDrawable = INSTANCE.getCompositeBackgroundDrawable(view);
        if (compositeBackgroundDrawable == null || (borderRadius = compositeBackgroundDrawable.getBorderRadius()) == null) {
            return null;
        }
        return borderRadius.get(corner);
    }

    @JvmStatic
    public static final void setBorderStyle(View view, BorderStyle borderStyle) {
        Intrinsics.checkNotNullParameter(view, "view");
        INSTANCE.ensureBorderDrawable(view).setBorderStyle(borderStyle);
    }

    @JvmStatic
    public static final BorderStyle getBorderStyle(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        BorderDrawable border = INSTANCE.getBorder(view);
        if (border != null) {
            return border.getBorderStyle();
        }
        return null;
    }

    @JvmStatic
    public static final void setOutlineColor(View view, Integer num) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (ViewUtil.getUIManagerType(view) != 2) {
            return;
        }
        OutlineDrawable outlineDrawableEnsureOutlineDrawable = INSTANCE.ensureOutlineDrawable(view);
        if (num != null) {
            outlineDrawableEnsureOutlineDrawable.setOutlineColor(num.intValue());
        }
    }

    @JvmStatic
    public static final Integer getOutlineColor(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        OutlineDrawable outlineDrawable = INSTANCE.getOutlineDrawable(view);
        if (outlineDrawable != null) {
            return Integer.valueOf(outlineDrawable.getOutlineColor());
        }
        return null;
    }

    @JvmStatic
    public static final void setOutlineOffset(View view, float f) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (ViewUtil.getUIManagerType(view) != 2) {
            return;
        }
        INSTANCE.ensureOutlineDrawable(view).setOutlineOffset(PixelUtil.INSTANCE.dpToPx(f));
    }

    public final Float getOutlineOffset(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        OutlineDrawable outlineDrawable = getOutlineDrawable(view);
        if (outlineDrawable != null) {
            return Float.valueOf(outlineDrawable.getOutlineOffset());
        }
        return null;
    }

    @JvmStatic
    public static final void setOutlineStyle(View view, OutlineStyle outlineStyle) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (ViewUtil.getUIManagerType(view) != 2) {
            return;
        }
        OutlineDrawable outlineDrawableEnsureOutlineDrawable = INSTANCE.ensureOutlineDrawable(view);
        if (outlineStyle != null) {
            outlineDrawableEnsureOutlineDrawable.setOutlineStyle(outlineStyle);
        }
    }

    public final OutlineStyle getOutlineStyle(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        OutlineDrawable outlineDrawable = getOutlineDrawable(view);
        if (outlineDrawable != null) {
            return outlineDrawable.getOutlineStyle();
        }
        return null;
    }

    @JvmStatic
    public static final void setOutlineWidth(View view, float width) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (ViewUtil.getUIManagerType(view) != 2) {
            return;
        }
        INSTANCE.ensureOutlineDrawable(view).setOutlineWidth(PixelUtil.INSTANCE.dpToPx(width));
    }

    public final Float getOutlineWidth(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        OutlineDrawable outlineDrawable = getOutlineDrawable(view);
        if (outlineDrawable != null) {
            return Float.valueOf(outlineDrawable.getOutlineOffset());
        }
        return null;
    }

    @JvmStatic
    public static final void setBoxShadow(View view, List<BoxShadow> shadows) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(shadows, "shadows");
        if (ViewUtil.getUIManagerType(view) != 2) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        CompositeBackgroundDrawable compositeBackgroundDrawableEnsureCompositeBackgroundDrawable = INSTANCE.ensureCompositeBackgroundDrawable(view);
        BorderInsets borderInsets = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderInsets();
        BorderRadiusStyle borderRadius = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderRadius();
        for (BoxShadow boxShadow : shadows) {
            float offsetX = boxShadow.getOffsetX();
            float offsetY = boxShadow.getOffsetY();
            Integer color = boxShadow.getColor();
            int iIntValue = color != null ? color.intValue() : ViewCompat.MEASURED_STATE_MASK;
            Float blurRadius = boxShadow.getBlurRadius();
            float fFloatValue = blurRadius != null ? blurRadius.floatValue() : 0.0f;
            Float spreadDistance = boxShadow.getSpreadDistance();
            float fFloatValue2 = spreadDistance != null ? spreadDistance.floatValue() : 0.0f;
            Boolean inset = boxShadow.getInset();
            boolean zBooleanValue = inset != null ? inset.booleanValue() : false;
            if (zBooleanValue && Build.VERSION.SDK_INT >= 29) {
                Context context = view.getContext();
                Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
                arrayList.add(new InsetBoxShadowDrawable(context, iIntValue, offsetX, offsetY, fFloatValue, fFloatValue2, borderInsets, borderRadius));
            } else if (!zBooleanValue && Build.VERSION.SDK_INT >= 28) {
                Context context2 = view.getContext();
                Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                arrayList2.add(new OutsetBoxShadowDrawable(context2, iIntValue, offsetX, offsetY, fFloatValue, fFloatValue2, borderRadius));
            }
        }
        view.setBackground(INSTANCE.ensureCompositeBackgroundDrawable(view).withNewShadows(arrayList2, arrayList));
    }

    @JvmStatic
    public static final void setBoxShadow(View view, ReadableArray shadows) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (shadows == null) {
            setBoxShadow(view, (List<BoxShadow>) CollectionsKt.emptyList());
            return;
        }
        ArrayList arrayList = new ArrayList();
        int size = shadows.size();
        for (int i = 0; i < size; i++) {
            BoxShadow.Companion companion = BoxShadow.INSTANCE;
            ReadableMap map = shadows.getMap(i);
            Context context = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            BoxShadow boxShadow = companion.parse(map, context);
            if (boxShadow == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            arrayList.add(boxShadow);
        }
        setBoxShadow(view, arrayList);
    }

    @JvmStatic
    public static final void setFeedbackUnderlay(View view, Drawable drawable) {
        Intrinsics.checkNotNullParameter(view, "view");
        INSTANCE.ensureCompositeBackgroundDrawable(view).withNewFeedbackUnderlay(drawable);
    }

    @JvmStatic
    public static final void clipToPaddingBox(View view, Canvas canvas) {
        RectF rectFResolve;
        float fDpToPx;
        float fDpToPx2;
        float fDpToPx3;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Rect rect = new Rect();
        view.getDrawingRect(rect);
        BackgroundStyleApplicator backgroundStyleApplicator = INSTANCE;
        CompositeBackgroundDrawable compositeBackgroundDrawable = backgroundStyleApplicator.getCompositeBackgroundDrawable(view);
        if (compositeBackgroundDrawable == null) {
            canvas.clipRect(rect);
            return;
        }
        RectF rectF = new RectF();
        BorderInsets borderInsets = compositeBackgroundDrawable.getBorderInsets();
        if (borderInsets != null) {
            int layoutDirection = compositeBackgroundDrawable.getLayoutDirection();
            Context context = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            rectFResolve = borderInsets.resolve(layoutDirection, context);
        } else {
            rectFResolve = null;
        }
        float f = compositeBackgroundDrawable.getBounds().left;
        float fDpToPx4 = 0.0f;
        if (rectFResolve != null) {
            fDpToPx = PixelUtil.INSTANCE.dpToPx(rectFResolve.left);
        } else {
            fDpToPx = 0.0f;
        }
        rectF.left = f + fDpToPx;
        float f2 = compositeBackgroundDrawable.getBounds().top;
        if (rectFResolve != null) {
            fDpToPx2 = PixelUtil.INSTANCE.dpToPx(rectFResolve.top);
        } else {
            fDpToPx2 = 0.0f;
        }
        rectF.top = f2 + fDpToPx2;
        float f3 = compositeBackgroundDrawable.getBounds().right;
        if (rectFResolve != null) {
            fDpToPx3 = PixelUtil.INSTANCE.dpToPx(rectFResolve.right);
        } else {
            fDpToPx3 = 0.0f;
        }
        rectF.right = f3 - fDpToPx3;
        float f4 = compositeBackgroundDrawable.getBounds().bottom;
        if (rectFResolve != null) {
            fDpToPx4 = PixelUtil.INSTANCE.dpToPx(rectFResolve.bottom);
        }
        rectF.bottom = f4 - fDpToPx4;
        BorderRadiusStyle borderRadius = compositeBackgroundDrawable.getBorderRadius();
        if (borderRadius != null && borderRadius.hasRoundedBorders()) {
            Path pathCreatePaddingBoxPath = backgroundStyleApplicator.createPaddingBoxPath(view, compositeBackgroundDrawable, rectF, rectFResolve);
            pathCreatePaddingBoxPath.offset(rect.left, rect.top);
            canvas.clipPath(pathCreatePaddingBoxPath);
        } else {
            rectF.offset(rect.left, rect.top);
            canvas.clipRect(rectF);
        }
    }

    @JvmStatic
    public static final void reset(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (view.getBackground() instanceof CompositeBackgroundDrawable) {
            Drawable background = view.getBackground();
            Intrinsics.checkNotNull(background, "null cannot be cast to non-null type com.facebook.react.uimanager.drawable.CompositeBackgroundDrawable");
            view.setBackground(((CompositeBackgroundDrawable) background).getOriginalBackground());
        }
    }

    private final CompositeBackgroundDrawable ensureCompositeBackgroundDrawable(View view) {
        if (view.getBackground() instanceof CompositeBackgroundDrawable) {
            Drawable background = view.getBackground();
            Intrinsics.checkNotNull(background, "null cannot be cast to non-null type com.facebook.react.uimanager.drawable.CompositeBackgroundDrawable");
            return (CompositeBackgroundDrawable) background;
        }
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        CompositeBackgroundDrawable compositeBackgroundDrawable = new CompositeBackgroundDrawable(context, view.getBackground(), null, null, null, null, null, null, null, null, null, 2044, null);
        view.setBackground(compositeBackgroundDrawable);
        return compositeBackgroundDrawable;
    }

    private final CompositeBackgroundDrawable getCompositeBackgroundDrawable(View view) {
        Drawable background = view.getBackground();
        if (background instanceof CompositeBackgroundDrawable) {
            return (CompositeBackgroundDrawable) background;
        }
        return null;
    }

    private final BackgroundDrawable ensureBackgroundDrawable(View view) {
        CompositeBackgroundDrawable compositeBackgroundDrawableEnsureCompositeBackgroundDrawable = ensureCompositeBackgroundDrawable(view);
        BackgroundDrawable background = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBackground();
        if (background != null) {
            return background;
        }
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        BackgroundDrawable backgroundDrawable = new BackgroundDrawable(context, compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderRadius(), compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderInsets());
        view.setBackground(compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.withNewBackground(backgroundDrawable));
        return backgroundDrawable;
    }

    private final BackgroundDrawable getBackground(View view) {
        CompositeBackgroundDrawable compositeBackgroundDrawable = getCompositeBackgroundDrawable(view);
        if (compositeBackgroundDrawable != null) {
            return compositeBackgroundDrawable.getBackground();
        }
        return null;
    }

    private final BackgroundImageDrawable ensureBackgroundImageDrawable(View view) {
        CompositeBackgroundDrawable compositeBackgroundDrawableEnsureCompositeBackgroundDrawable = ensureCompositeBackgroundDrawable(view);
        BackgroundImageDrawable backgroundImage = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBackgroundImage();
        if (backgroundImage != null) {
            return backgroundImage;
        }
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        BackgroundImageDrawable backgroundImageDrawable = new BackgroundImageDrawable(context, compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderRadius(), compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderInsets());
        view.setBackground(compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.withNewBackgroundImage(backgroundImageDrawable));
        return backgroundImageDrawable;
    }

    private final BackgroundImageDrawable getBackgroundImage(View view) {
        CompositeBackgroundDrawable compositeBackgroundDrawable = getCompositeBackgroundDrawable(view);
        if (compositeBackgroundDrawable != null) {
            return compositeBackgroundDrawable.getBackgroundImage();
        }
        return null;
    }

    private final BorderDrawable getBorder(View view) {
        CompositeBackgroundDrawable compositeBackgroundDrawable = getCompositeBackgroundDrawable(view);
        if (compositeBackgroundDrawable != null) {
            return compositeBackgroundDrawable.getBorder();
        }
        return null;
    }

    private final BorderDrawable ensureBorderDrawable(View view) {
        CompositeBackgroundDrawable compositeBackgroundDrawableEnsureCompositeBackgroundDrawable = ensureCompositeBackgroundDrawable(view);
        BorderDrawable border = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorder();
        if (border != null) {
            return border;
        }
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        BorderRadiusStyle borderRadius = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderRadius();
        BorderDrawable borderDrawable = new BorderDrawable(context, new Spacing(0.0f), borderRadius, compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderInsets(), BorderStyle.SOLID);
        view.setBackground(compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.withNewBorder(borderDrawable));
        return borderDrawable;
    }

    private final OutlineDrawable ensureOutlineDrawable(View view) {
        CompositeBackgroundDrawable compositeBackgroundDrawableEnsureCompositeBackgroundDrawable = ensureCompositeBackgroundDrawable(view);
        OutlineDrawable outline = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getOutline();
        if (outline != null) {
            return outline;
        }
        BorderRadiusStyle borderRadius = compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.getBorderRadius();
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        OutlineDrawable outlineDrawable = new OutlineDrawable(context, borderRadius, ViewCompat.MEASURED_STATE_MASK, 0.0f, OutlineStyle.SOLID, 0.0f);
        view.setBackground(compositeBackgroundDrawableEnsureCompositeBackgroundDrawable.withNewOutline(outlineDrawable));
        return outlineDrawable;
    }

    private final OutlineDrawable getOutlineDrawable(View view) {
        CompositeBackgroundDrawable compositeBackgroundDrawable = getCompositeBackgroundDrawable(view);
        if (compositeBackgroundDrawable != null) {
            return compositeBackgroundDrawable.getOutline();
        }
        return null;
    }

    private final float getInnerBorderRadius(Float computedRadius, Float f) {
        return RangesKt.coerceAtLeast((computedRadius != null ? computedRadius.floatValue() : 0.0f) - (f != null ? f.floatValue() : 0.0f), 0.0f);
    }

    private final Path createPaddingBoxPath(View view, CompositeBackgroundDrawable composite, RectF paddingBoxRect, RectF computedBorderInsets) {
        ComputedBorderRadius computedBorderRadiusResolve;
        CornerRadii bottomLeft;
        CornerRadii bottomLeft2;
        CornerRadii bottomRight;
        CornerRadii bottomRight2;
        CornerRadii topRight;
        CornerRadii topRight2;
        CornerRadii topLeft;
        CornerRadii topLeft2;
        BorderRadiusStyle borderRadius = composite.getBorderRadius();
        if (borderRadius != null) {
            int layoutDirection = composite.getLayoutDirection();
            Context context = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            computedBorderRadiusResolve = borderRadius.resolve(layoutDirection, context, PixelUtil.toDIPFromPixel(composite.getBounds().width()), PixelUtil.toDIPFromPixel(composite.getBounds().height()));
        } else {
            computedBorderRadiusResolve = null;
        }
        Path path = new Path();
        path.addRoundRect(paddingBoxRect, new float[]{getInnerBorderRadius((computedBorderRadiusResolve == null || (topLeft2 = computedBorderRadiusResolve.getTopLeft()) == null) ? null : Float.valueOf(PixelUtil.INSTANCE.dpToPx(topLeft2.getHorizontal())), computedBorderInsets != null ? Float.valueOf(PixelUtil.INSTANCE.dpToPx(computedBorderInsets.left)) : null), getInnerBorderRadius((computedBorderRadiusResolve == null || (topLeft = computedBorderRadiusResolve.getTopLeft()) == null) ? null : Float.valueOf(PixelUtil.INSTANCE.dpToPx(topLeft.getVertical())), computedBorderInsets != null ? Float.valueOf(PixelUtil.INSTANCE.dpToPx(computedBorderInsets.top)) : null), getInnerBorderRadius((computedBorderRadiusResolve == null || (topRight2 = computedBorderRadiusResolve.getTopRight()) == null) ? null : Float.valueOf(PixelUtil.INSTANCE.dpToPx(topRight2.getHorizontal())), computedBorderInsets != null ? Float.valueOf(PixelUtil.INSTANCE.dpToPx(computedBorderInsets.right)) : null), getInnerBorderRadius((computedBorderRadiusResolve == null || (topRight = computedBorderRadiusResolve.getTopRight()) == null) ? null : Float.valueOf(PixelUtil.INSTANCE.dpToPx(topRight.getVertical())), computedBorderInsets != null ? Float.valueOf(PixelUtil.INSTANCE.dpToPx(computedBorderInsets.top)) : null), getInnerBorderRadius((computedBorderRadiusResolve == null || (bottomRight2 = computedBorderRadiusResolve.getBottomRight()) == null) ? null : Float.valueOf(PixelUtil.INSTANCE.dpToPx(bottomRight2.getHorizontal())), computedBorderInsets != null ? Float.valueOf(PixelUtil.INSTANCE.dpToPx(computedBorderInsets.right)) : null), getInnerBorderRadius((computedBorderRadiusResolve == null || (bottomRight = computedBorderRadiusResolve.getBottomRight()) == null) ? null : Float.valueOf(PixelUtil.INSTANCE.dpToPx(bottomRight.getVertical())), computedBorderInsets != null ? Float.valueOf(PixelUtil.INSTANCE.dpToPx(computedBorderInsets.bottom)) : null), getInnerBorderRadius((computedBorderRadiusResolve == null || (bottomLeft2 = computedBorderRadiusResolve.getBottomLeft()) == null) ? null : Float.valueOf(PixelUtil.INSTANCE.dpToPx(bottomLeft2.getHorizontal())), computedBorderInsets != null ? Float.valueOf(PixelUtil.INSTANCE.dpToPx(computedBorderInsets.left)) : null), getInnerBorderRadius((computedBorderRadiusResolve == null || (bottomLeft = computedBorderRadiusResolve.getBottomLeft()) == null) ? null : Float.valueOf(PixelUtil.INSTANCE.dpToPx(bottomLeft.getVertical())), computedBorderInsets != null ? Float.valueOf(PixelUtil.INSTANCE.dpToPx(computedBorderInsets.bottom)) : null)}, Path.Direction.CW);
        return path;
    }
}
