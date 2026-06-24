package com.facebook.react.views.text.frescosupport;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import com.facebook.common.logging.FLog;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.annotations.internal.LegacyArchitectureLogLevel;
import com.facebook.react.common.annotations.internal.LegacyArchitectureLogger;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.internal.ReactTextInlineImageShadowNode;
import com.facebook.react.views.text.internal.span.TextInlineImageSpan;
import com.facebook.yoga.YogaConstants;
import java.util.Locale;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: FrescoBasedReactTextInlineImageShadowNode.kt */
/* JADX INFO: loaded from: classes.dex */
@Deprecated(level = DeprecationLevel.WARNING, message = "This class is part of Legacy Architecture and will be removed in a future release")
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 +2\u00020\u0001:\u0001+B+\u0012\u0018\u0010\u0002\u001a\u0014\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0007J\u0012\u0010\u0018\u001a\u00020\u00152\b\u0010\u0019\u001a\u0004\u0018\u00010\fH\u0007J\u0010\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u0013H\u0007J\u0010\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u001eH\u0016J\u0012\u0010!\u001a\u00020\u00152\b\u0010\"\u001a\u0004\u0018\u00010\u0010H\u0007J\b\u0010#\u001a\u0004\u0018\u00010\nJ\b\u0010$\u001a\u0004\u0018\u00010\fJ\b\u0010%\u001a\u00020&H\u0016J\b\u0010'\u001a\u00020(H\u0016J\u0018\u0010)\u001a\u0014\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003J\b\u0010*\u001a\u0004\u0018\u00010\u0006R \u0010\u0002\u001a\u0014\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/facebook/react/views/text/frescosupport/FrescoBasedReactTextInlineImageShadowNode;", "Lcom/facebook/react/views/text/internal/ReactTextInlineImageShadowNode;", "draweeControllerBuilder", "Lcom/facebook/drawee/controller/AbstractDraweeControllerBuilder;", "Lcom/facebook/imagepipeline/request/ImageRequest;", "callerContext", "", "<init>", "(Lcom/facebook/drawee/controller/AbstractDraweeControllerBuilder;Ljava/lang/Object;)V", "uri", "Landroid/net/Uri;", "headers", "Lcom/facebook/react/bridge/ReadableMap;", "width", "", ViewProps.RESIZE_MODE, "", "height", "tintColor", "", "setSource", "", "sources", "Lcom/facebook/react/bridge/ReadableArray;", "setHeaders", "newHeaders", "setTintColor", "newTintColor", "setWidth", "newWidth", "Lcom/facebook/react/bridge/Dynamic;", "setHeight", "newHeight", "setResizeMode", "newResizeMode", "getUri", "getHeaders", "isVirtual", "", "buildInlineImageSpan", "Lcom/facebook/react/views/text/internal/span/TextInlineImageSpan;", "getDraweeControllerBuilder", "getCallerContext", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class FrescoBasedReactTextInlineImageShadowNode extends ReactTextInlineImageShadowNode {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Object callerContext;
    private final AbstractDraweeControllerBuilder<?, ImageRequest, ?, ?> draweeControllerBuilder;
    private ReadableMap headers;
    private float height;
    private String resizeMode;
    private int tintColor;
    private Uri uri;
    private float width;

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public boolean isVirtual() {
        return true;
    }

    public FrescoBasedReactTextInlineImageShadowNode(AbstractDraweeControllerBuilder<?, ImageRequest, ?, ?> draweeControllerBuilder, Object obj) {
        Intrinsics.checkNotNullParameter(draweeControllerBuilder, "draweeControllerBuilder");
        this.draweeControllerBuilder = draweeControllerBuilder;
        this.callerContext = obj;
        this.width = YogaConstants.UNDEFINED;
        this.height = YogaConstants.UNDEFINED;
    }

    @ReactProp(name = "src")
    public final void setSource(ReadableArray sources) {
        String string;
        Uri resourceDrawableUri = null;
        if (sources == null || sources.size() == 0 || sources.getType(0) != ReadableType.Map) {
            string = null;
        } else {
            ReadableMap map = sources.getMap(0);
            if (map == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            string = map.getString("uri");
        }
        if (string != null) {
            try {
                Uri uri = Uri.parse(string);
                if (uri.getScheme() != null) {
                    resourceDrawableUri = uri;
                }
            } catch (Exception unused) {
            }
            if (resourceDrawableUri == null) {
                Companion companion = INSTANCE;
                ThemedReactContext themedContext = getThemedContext();
                Intrinsics.checkNotNullExpressionValue(themedContext, "getThemedContext(...)");
                resourceDrawableUri = companion.getResourceDrawableUri(themedContext, string);
            }
        }
        if (!Intrinsics.areEqual(resourceDrawableUri, this.uri)) {
            markUpdated();
        }
        this.uri = resourceDrawableUri;
    }

    @ReactProp(name = "headers")
    public final void setHeaders(ReadableMap newHeaders) {
        this.headers = newHeaders;
    }

    @ReactProp(customType = "Color", name = "tintColor")
    public final void setTintColor(int newTintColor) {
        this.tintColor = newTintColor;
    }

    @Override // com.facebook.react.uimanager.LayoutShadowNode
    public void setWidth(Dynamic newWidth) {
        Intrinsics.checkNotNullParameter(newWidth, "newWidth");
        if (newWidth.getType() == ReadableType.Number) {
            this.width = (float) newWidth.asDouble();
        } else {
            FLog.w(ReactConstants.TAG, "Inline images must not have percentage based width");
            this.width = YogaConstants.UNDEFINED;
        }
    }

    @Override // com.facebook.react.uimanager.LayoutShadowNode
    public void setHeight(Dynamic newHeight) {
        Intrinsics.checkNotNullParameter(newHeight, "newHeight");
        if (newHeight.getType() == ReadableType.Number) {
            this.height = (float) newHeight.asDouble();
        } else {
            FLog.w(ReactConstants.TAG, "Inline images must not have percentage based height");
            this.height = YogaConstants.UNDEFINED;
        }
    }

    @ReactProp(name = ViewProps.RESIZE_MODE)
    public final void setResizeMode(String newResizeMode) {
        this.resizeMode = newResizeMode;
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final ReadableMap getHeaders() {
        return this.headers;
    }

    @Override // com.facebook.react.views.text.internal.ReactTextInlineImageShadowNode
    public TextInlineImageSpan buildInlineImageSpan() {
        Resources resources = getThemedContext().getResources();
        int iCeil = (int) Math.ceil(this.width);
        int iCeil2 = (int) Math.ceil(this.height);
        Intrinsics.checkNotNull(resources);
        return new FrescoBasedReactTextInlineImageSpan(resources, iCeil2, iCeil, this.tintColor, getUri(), getHeaders(), getDraweeControllerBuilder(), getCallerContext(), this.resizeMode);
    }

    public final AbstractDraweeControllerBuilder<?, ImageRequest, ?, ?> getDraweeControllerBuilder() {
        return this.draweeControllerBuilder;
    }

    public final Object getCallerContext() {
        return this.callerContext;
    }

    /* JADX INFO: compiled from: FrescoBasedReactTextInlineImageShadowNode.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t¨\u0006\n"}, d2 = {"Lcom/facebook/react/views/text/frescosupport/FrescoBasedReactTextInlineImageShadowNode$Companion;", "", "<init>", "()V", "getResourceDrawableUri", "Landroid/net/Uri;", "context", "Landroid/content/Context;", "name", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Uri getResourceDrawableUri(Context context, String name) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (name == null || name.length() == 0) {
                return null;
            }
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
            String lowerCase = name.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            return new Uri.Builder().scheme(UriUtil.LOCAL_RESOURCE_SCHEME).path(String.valueOf(context.getResources().getIdentifier(StringsKt.replace$default(lowerCase, "-", "_", false, 4, (Object) null), "drawable", context.getPackageName()))).build();
        }
    }

    static {
        LegacyArchitectureLogger.assertLegacyArchitecture("FrescoBasedReactTextInlineImageShadowNode", LegacyArchitectureLogLevel.ERROR);
    }
}
