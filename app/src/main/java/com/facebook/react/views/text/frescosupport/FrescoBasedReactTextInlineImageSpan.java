package com.facebook.react.views.text.frescosupport;

import android.content.res.Resources;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.widget.TextView;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.modules.fresco.ReactNetworkImageRequest;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.image.ImageResizeMode;
import com.facebook.react.views.text.internal.span.TextInlineImageSpan;
import com.facebook.react.views.textinput.ReactTextInputShadowNode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FrescoBasedReactTextInlineImageSpan.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001Bi\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0018\u0010\f\u001a\u0014\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u00030\r\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\b\u0010%\u001a\u00020&H\u0016J\b\u0010'\u001a\u00020&H\u0016J\b\u0010(\u001a\u00020&H\u0016J\b\u0010)\u001a\u00020&H\u0016J2\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u00052\u0006\u00100\u001a\u00020\u00052\b\u00101\u001a\u0004\u0018\u000102H\u0016J\u0012\u00103\u001a\u00020&2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016JP\u00104\u001a\u00020&2\u0006\u00105\u001a\u0002062\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u00052\u0006\u00100\u001a\u00020\u00052\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u00052\u0006\u0010:\u001a\u00020\u00052\u0006\u0010;\u001a\u00020\u00052\u0006\u0010+\u001a\u00020,H\u0016R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\f\u001a\u0014\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u00030\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001eR\"\u0010\"\u001a\u0004\u0018\u00010!2\b\u0010 \u001a\u0004\u0018\u00010!@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$¨\u0006<"}, d2 = {"Lcom/facebook/react/views/text/frescosupport/FrescoBasedReactTextInlineImageSpan;", "Lcom/facebook/react/views/text/internal/span/TextInlineImageSpan;", "resources", "Landroid/content/res/Resources;", "height", "", "width", "tintColor", "uri", "Landroid/net/Uri;", "headers", "Lcom/facebook/react/bridge/ReadableMap;", "draweeControllerBuilder", "Lcom/facebook/drawee/controller/AbstractDraweeControllerBuilder;", "Lcom/facebook/imagepipeline/request/ImageRequest;", "callerContext", "", ViewProps.RESIZE_MODE, "", "<init>", "(Landroid/content/res/Resources;IIILandroid/net/Uri;Lcom/facebook/react/bridge/ReadableMap;Lcom/facebook/drawee/controller/AbstractDraweeControllerBuilder;Ljava/lang/Object;Ljava/lang/String;)V", "textView", "Landroid/widget/TextView;", "_uri", "_width", "_height", "draweeHolder", "Lcom/facebook/drawee/view/DraweeHolder;", "Lcom/facebook/drawee/generic/GenericDraweeHierarchy;", "getWidth", "()I", "getHeight", "value", "Landroid/graphics/drawable/Drawable;", "drawable", "getDrawable", "()Landroid/graphics/drawable/Drawable;", "onDetachedFromWindow", "", "onStartTemporaryDetach", "onAttachedToWindow", "onFinishTemporaryDetach", "getSize", "paint", "Landroid/graphics/Paint;", ReactTextInputShadowNode.PROP_TEXT, "", ViewProps.START, ViewProps.END, "fm", "Landroid/graphics/Paint$FontMetricsInt;", "setTextView", "draw", "canvas", "Landroid/graphics/Canvas;", "x", "", "top", "y", ViewProps.BOTTOM, "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class FrescoBasedReactTextInlineImageSpan extends TextInlineImageSpan {
    private final int _height;
    private final Uri _uri;
    private final int _width;
    private final Object callerContext;
    private Drawable drawable;
    private final AbstractDraweeControllerBuilder<?, ImageRequest, ?, ?> draweeControllerBuilder;
    private final DraweeHolder<GenericDraweeHierarchy> draweeHolder;
    private final ReadableMap headers;
    private final String resizeMode;
    private TextView textView;
    private final int tintColor;

    public FrescoBasedReactTextInlineImageSpan(Resources resources, int i, int i2, int i3, Uri EMPTY, ReadableMap readableMap, AbstractDraweeControllerBuilder<?, ImageRequest, ?, ?> draweeControllerBuilder, Object obj, String str) {
        Intrinsics.checkNotNullParameter(resources, "resources");
        Intrinsics.checkNotNullParameter(draweeControllerBuilder, "draweeControllerBuilder");
        this.tintColor = i3;
        this.headers = readableMap;
        this.draweeControllerBuilder = draweeControllerBuilder;
        this.callerContext = obj;
        this.resizeMode = str;
        if (EMPTY == null) {
            EMPTY = Uri.EMPTY;
            Intrinsics.checkNotNullExpressionValue(EMPTY, "EMPTY");
        }
        this._uri = EMPTY;
        this._width = (int) PixelUtil.toPixelFromDIP(i2);
        this._height = (int) PixelUtil.toPixelFromDIP(i);
        this.draweeHolder = new DraweeHolder<>(GenericDraweeHierarchyBuilder.newInstance(resources).build());
    }

    @Override // com.facebook.react.views.text.internal.span.TextInlineImageSpan
    /* JADX INFO: renamed from: getWidth, reason: from getter */
    public int get_width() {
        return this._width;
    }

    @Override // com.facebook.react.views.text.internal.span.TextInlineImageSpan
    /* JADX INFO: renamed from: getHeight, reason: from getter */
    public int get_height() {
        return this._height;
    }

    @Override // com.facebook.react.views.text.internal.span.TextInlineImageSpan
    public Drawable getDrawable() {
        return this.drawable;
    }

    @Override // com.facebook.react.views.text.internal.span.TextInlineImageSpan
    public void onDetachedFromWindow() {
        this.draweeHolder.onDetach();
    }

    @Override // com.facebook.react.views.text.internal.span.TextInlineImageSpan
    public void onStartTemporaryDetach() {
        this.draweeHolder.onDetach();
    }

    @Override // com.facebook.react.views.text.internal.span.TextInlineImageSpan
    public void onAttachedToWindow() {
        this.draweeHolder.onAttach();
    }

    @Override // com.facebook.react.views.text.internal.span.TextInlineImageSpan
    public void onFinishTemporaryDetach() {
        this.draweeHolder.onAttach();
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        Intrinsics.checkNotNullParameter(paint, "paint");
        Intrinsics.checkNotNullParameter(text, "text");
        if (fm != null) {
            fm.ascent = -this._height;
            fm.descent = 0;
            fm.top = fm.ascent;
            fm.bottom = 0;
        }
        return this._width;
    }

    @Override // com.facebook.react.views.text.internal.span.TextInlineImageSpan
    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(paint, "paint");
        if (getDrawable() == null) {
            ImageRequestBuilder imageRequestBuilderNewBuilderWithSource = ImageRequestBuilder.newBuilderWithSource(this._uri);
            ReactNetworkImageRequest.Companion companion = ReactNetworkImageRequest.INSTANCE;
            Intrinsics.checkNotNull(imageRequestBuilderNewBuilderWithSource);
            ReactNetworkImageRequest reactNetworkImageRequestFromBuilderWithHeaders$default = ReactNetworkImageRequest.Companion.fromBuilderWithHeaders$default(companion, imageRequestBuilderNewBuilderWithSource, this.headers, null, 4, null);
            ((GenericDraweeHierarchy) this.draweeHolder.getHierarchy()).setActualImageScaleType(ImageResizeMode.toScaleType(this.resizeMode));
            this.draweeControllerBuilder.reset();
            this.draweeControllerBuilder.setOldController(this.draweeHolder.getController());
            Object obj = this.callerContext;
            if (obj != null) {
                Intrinsics.checkNotNullExpressionValue(this.draweeControllerBuilder.setCallerContext(obj), "setCallerContext(...)");
            }
            this.draweeControllerBuilder.setImageRequest(reactNetworkImageRequestFromBuilderWithHeaders$default);
            AbstractDraweeController abstractDraweeControllerBuild = this.draweeControllerBuilder.build();
            Intrinsics.checkNotNullExpressionValue(abstractDraweeControllerBuild, "build(...)");
            this.draweeHolder.setController(abstractDraweeControllerBuild);
            this.draweeControllerBuilder.reset();
            Drawable topLevelDrawable = this.draweeHolder.getTopLevelDrawable();
            if (topLevelDrawable == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            topLevelDrawable.setBounds(0, 0, this._width, this._height);
            if (this.tintColor != 0) {
                if (Build.VERSION.SDK_INT >= 29) {
                    topLevelDrawable.setColorFilter(new BlendModeColorFilter(this.tintColor, BlendMode.SRC_IN));
                } else {
                    topLevelDrawable.setColorFilter(new PorterDuffColorFilter(this.tintColor, PorterDuff.Mode.SRC_IN));
                }
            }
            topLevelDrawable.setCallback(this.textView);
            this.drawable = topLevelDrawable;
        }
        canvas.save();
        Drawable drawable = getDrawable();
        if (drawable == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        canvas.translate(x, ((y + ((int) paint.descent())) - (((int) (paint.descent() - paint.ascent())) / 2)) - (drawable.getBounds().height() / 2));
        drawable.draw(canvas);
        canvas.restore();
    }
}
