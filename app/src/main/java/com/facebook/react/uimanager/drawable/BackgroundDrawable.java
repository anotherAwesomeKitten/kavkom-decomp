package com.facebook.react.uimanager.drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.style.BorderInsets;
import com.facebook.react.uimanager.style.BorderRadiusStyle;
import com.facebook.react.uimanager.style.ComputedBorderRadius;
import com.facebook.react.uimanager.style.CornerRadii;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: BackgroundDrawable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010&\u001a\u00020'H\u0016J\u0010\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020*H\u0014J\u0010\u0010+\u001a\u00020'2\u0006\u0010,\u001a\u00020\u001bH\u0016J\u0012\u0010-\u001a\u00020'2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\b\u00100\u001a\u00020\u001bH\u0017J\u0010\u00101\u001a\u00020'2\u0006\u00102\u001a\u000203H\u0016J\b\u00104\u001a\u00020\u0015H\u0002J\b\u00105\u001a\u00020'H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u001b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lcom/facebook/react/uimanager/drawable/BackgroundDrawable;", "Landroid/graphics/drawable/Drawable;", "context", "Landroid/content/Context;", ViewProps.BORDER_RADIUS, "Lcom/facebook/react/uimanager/style/BorderRadiusStyle;", "borderInsets", "Lcom/facebook/react/uimanager/style/BorderInsets;", "<init>", "(Landroid/content/Context;Lcom/facebook/react/uimanager/style/BorderRadiusStyle;Lcom/facebook/react/uimanager/style/BorderInsets;)V", "getBorderRadius", "()Lcom/facebook/react/uimanager/style/BorderRadiusStyle;", "setBorderRadius", "(Lcom/facebook/react/uimanager/style/BorderRadiusStyle;)V", "getBorderInsets", "()Lcom/facebook/react/uimanager/style/BorderInsets;", "setBorderInsets", "(Lcom/facebook/react/uimanager/style/BorderInsets;)V", "pathAdjustment", "", "computedBorderInsets", "Landroid/graphics/RectF;", "computedBorderRadius", "Lcom/facebook/react/uimanager/style/ComputedBorderRadius;", "needUpdatePath", "", "value", "", ViewProps.BACKGROUND_COLOR, "getBackgroundColor", "()I", "setBackgroundColor", "(I)V", "backgroundRect", "backgroundRenderPath", "Landroid/graphics/Path;", "backgroundPaint", "Landroid/graphics/Paint;", "invalidateSelf", "", "onBoundsChange", "bounds", "Landroid/graphics/Rect;", "setAlpha", "alpha", "setColorFilter", "colorFilter", "Landroid/graphics/ColorFilter;", "getOpacity", "draw", "canvas", "Landroid/graphics/Canvas;", "computeBorderInsets", "updatePath", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class BackgroundDrawable extends Drawable {
    private int backgroundColor;
    private final Paint backgroundPaint;
    private RectF backgroundRect;
    private Path backgroundRenderPath;
    private BorderInsets borderInsets;
    private BorderRadiusStyle borderRadius;
    private RectF computedBorderInsets;
    private ComputedBorderRadius computedBorderRadius;
    private final Context context;
    private boolean needUpdatePath;
    private final float pathAdjustment;

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public /* synthetic */ BackgroundDrawable(Context context, BorderRadiusStyle borderRadiusStyle, BorderInsets borderInsets, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : borderRadiusStyle, (i & 4) != 0 ? null : borderInsets);
    }

    public final BorderRadiusStyle getBorderRadius() {
        return this.borderRadius;
    }

    public final void setBorderRadius(BorderRadiusStyle borderRadiusStyle) {
        this.borderRadius = borderRadiusStyle;
    }

    public final BorderInsets getBorderInsets() {
        return this.borderInsets;
    }

    public final void setBorderInsets(BorderInsets borderInsets) {
        this.borderInsets = borderInsets;
    }

    public BackgroundDrawable(Context context, BorderRadiusStyle borderRadiusStyle, BorderInsets borderInsets) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.borderRadius = borderRadiusStyle;
        this.borderInsets = borderInsets;
        this.pathAdjustment = 0.8f;
        this.needUpdatePath = true;
        this.backgroundRect = new RectF();
        Paint paint = new Paint(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(this.backgroundColor);
        this.backgroundPaint = paint;
    }

    public final int getBackgroundColor() {
        return this.backgroundColor;
    }

    public final void setBackgroundColor(int i) {
        if (this.backgroundColor != i) {
            this.backgroundColor = i;
            this.backgroundPaint.setColor(i);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        this.needUpdatePath = true;
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect bounds) {
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        super.onBoundsChange(bounds);
        this.needUpdatePath = true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        this.backgroundPaint.setAlpha(MathKt.roundToInt((alpha / 255.0f) * (Color.alpha(this.backgroundColor) / 255.0f) * 255.0f));
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    @Deprecated(message = "Deprecated in Java")
    public int getOpacity() {
        int alpha = this.backgroundPaint.getAlpha();
        if (alpha == 255) {
            return -1;
        }
        return (1 > alpha || alpha >= 255) ? -2 : -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        BorderRadiusStyle borderRadiusStyle;
        CornerRadii topLeft;
        CornerRadii topLeft2;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        updatePath();
        canvas.save();
        if (this.backgroundPaint.getAlpha() != 0) {
            ComputedBorderRadius computedBorderRadius = this.computedBorderRadius;
            if (computedBorderRadius != null && computedBorderRadius.isUniform() && (borderRadiusStyle = this.borderRadius) != null && borderRadiusStyle.hasRoundedBorders()) {
                RectF rectF = this.backgroundRect;
                ComputedBorderRadius computedBorderRadius2 = this.computedBorderRadius;
                float fDpToPx = 0.0f;
                float fDpToPx2 = (computedBorderRadius2 == null || (topLeft2 = computedBorderRadius2.getTopLeft()) == null) ? 0.0f : PixelUtil.INSTANCE.dpToPx(topLeft2.getHorizontal());
                ComputedBorderRadius computedBorderRadius3 = this.computedBorderRadius;
                if (computedBorderRadius3 != null && (topLeft = computedBorderRadius3.getTopLeft()) != null) {
                    fDpToPx = PixelUtil.INSTANCE.dpToPx(topLeft.getVertical());
                }
                canvas.drawRoundRect(rectF, fDpToPx2, fDpToPx, this.backgroundPaint);
            } else {
                BorderRadiusStyle borderRadiusStyle2 = this.borderRadius;
                if (borderRadiusStyle2 == null || !borderRadiusStyle2.hasRoundedBorders()) {
                    canvas.drawRect(this.backgroundRect, this.backgroundPaint);
                } else {
                    Path path = this.backgroundRenderPath;
                    if (path == null) {
                        throw new IllegalStateException("Required value was null.".toString());
                    }
                    canvas.drawPath(path, this.backgroundPaint);
                }
            }
        }
        canvas.restore();
    }

    private final RectF computeBorderInsets() {
        BorderInsets borderInsets = this.borderInsets;
        RectF rectFResolve = borderInsets != null ? borderInsets.resolve(getLayoutDirection(), this.context) : null;
        return new RectF(rectFResolve != null ? PixelUtil.INSTANCE.dpToPx(rectFResolve.left) : 0.0f, rectFResolve != null ? PixelUtil.INSTANCE.dpToPx(rectFResolve.top) : 0.0f, rectFResolve != null ? PixelUtil.INSTANCE.dpToPx(rectFResolve.right) : 0.0f, rectFResolve != null ? PixelUtil.INSTANCE.dpToPx(rectFResolve.bottom) : 0.0f);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0092  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void updatePath() {
        /*
            Method dump skipped, instruction units count: 464
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.drawable.BackgroundDrawable.updatePath():void");
    }
}
