package com.facebook.react.views.text;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import com.facebook.common.callercontext.ContextChain;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.common.mapbuffer.MapBuffer;
import com.facebook.react.common.mapbuffer.ReadableMapBuffer;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactAccessibilityDelegate;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.text.internal.span.CustomLetterSpacingSpan;
import com.facebook.react.views.text.internal.span.CustomLineHeightSpan;
import com.facebook.react.views.text.internal.span.CustomStyleSpan;
import com.facebook.react.views.text.internal.span.ReactAbsoluteSizeSpan;
import com.facebook.react.views.text.internal.span.ReactBackgroundColorSpan;
import com.facebook.react.views.text.internal.span.ReactClickableSpan;
import com.facebook.react.views.text.internal.span.ReactForegroundColorSpan;
import com.facebook.react.views.text.internal.span.ReactFragmentIndexSpan;
import com.facebook.react.views.text.internal.span.ReactLinkSpan;
import com.facebook.react.views.text.internal.span.ReactOpacitySpan;
import com.facebook.react.views.text.internal.span.ReactStrikethroughSpan;
import com.facebook.react.views.text.internal.span.ReactTagSpan;
import com.facebook.react.views.text.internal.span.ReactTextPaintHolderSpan;
import com.facebook.react.views.text.internal.span.ReactUnderlineSpan;
import com.facebook.react.views.text.internal.span.ShadowStyleSpan;
import com.facebook.react.views.text.internal.span.TextInlineViewPlaceholderSpan;
import com.facebook.react.views.textinput.ReactTextInputShadowNode;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import io.sentry.SentryEnvelopeItemHeader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextLayoutManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Ò\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001:\u0002{|B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00052\u0006\u0010'\u001a\u00020#J\u000e\u0010(\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0005J\u000e\u0010)\u001a\u00020\u001f2\u0006\u0010*\u001a\u00020+J\u0012\u0010,\u001a\u0004\u0018\u00010\u001a2\u0006\u0010*\u001a\u00020+H\u0002J\u0012\u0010-\u001a\u00020\u00052\b\u0010.\u001a\u0004\u0018\u00010\u001aH\u0002J\"\u0010/\u001a\u0002002\u0006\u0010*\u001a\u00020+2\u0006\u00101\u001a\u00020#2\b\u0010.\u001a\u0004\u0018\u00010\u001aH\u0002J\u0018\u00102\u001a\u00020\u00052\u0006\u0010*\u001a\u00020+2\u0006\u00101\u001a\u00020#H\u0007J8\u00103\u001a\u00020%2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u00020+2\u0006\u00107\u001a\u0002082\f\u00109\u001a\b\u0012\u0004\u0012\u00020;0:2\b\u0010<\u001a\u0004\u0018\u00010=H\u0002J\"\u0010>\u001a\u00020#2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u00020+2\b\u0010<\u001a\u0004\u0018\u00010=H\u0002J \u0010?\u001a\u00020#2\u0006\u00104\u001a\u0002052\u0006\u0010*\u001a\u00020+2\b\u0010@\u001a\u0004\u0018\u00010AJ,\u0010B\u001a\u00020#2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u00020+2\b\u0010@\u001a\u0004\u0018\u00010A2\b\u0010<\u001a\u0004\u0018\u00010=H\u0002Jl\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020#2\b\u0010F\u001a\u0004\u0018\u00010G2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020\u001f2\u0006\u0010M\u001a\u00020\u00052\u0006\u0010N\u001a\u00020\u00052\u0006\u0010O\u001a\u0002002\u0006\u0010P\u001a\u00020\u00052\b\u0010Q\u001a\u0004\u0018\u00010R2\u0006\u0010S\u001a\u00020\u00052\u0006\u0010T\u001a\u00020\u001dH\u0002J \u0010U\u001a\u00020%2\u0006\u0010T\u001a\u00020\u001d2\u0006\u0010V\u001a\u00020W2\u0006\u00104\u001a\u000205H\u0002J\u0018\u0010X\u001a\u00020\u001d2\u0006\u0010V\u001a\u00020W2\u0006\u00104\u001a\u000205H\u0002J\u0018\u0010Y\u001a\u00020\u001d2\u0006\u0010V\u001a\u00020W2\u0006\u00104\u001a\u000205H\u0002JJ\u0010Z\u001a\u00020D2\u0006\u00104\u001a\u0002052\u0006\u0010*\u001a\u00020+2\u0006\u0010[\u001a\u00020+2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020K2\u0006\u0010\\\u001a\u00020I2\u0006\u0010]\u001a\u00020K2\b\u0010@\u001a\u0004\u0018\u00010AH\u0002JH\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020#2\u0006\u0010T\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020+2\u0006\u0010[\u001a\u00020+2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020K2\u0006\u0010\\\u001a\u00020I2\u0006\u0010]\u001a\u00020KH\u0002JJ\u0010^\u001a\u00020_2\u0006\u00104\u001a\u0002052\u0006\u0010*\u001a\u00020`2\u0006\u0010[\u001a\u00020`2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020K2\u0006\u0010\\\u001a\u00020I2\u0006\u0010]\u001a\u00020K2\b\u0010@\u001a\u0004\u0018\u00010AH\u0007Jp\u0010a\u001a\u00020%2\u0006\u0010E\u001a\u00020#2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020K2\u0006\u0010\\\u001a\u00020I2\u0006\u0010]\u001a\u00020K2\u0006\u0010b\u001a\u00020I2\u0006\u0010c\u001a\u00020\u00052\u0006\u0010L\u001a\u00020\u001f2\u0006\u0010M\u001a\u00020\u00052\u0006\u0010N\u001a\u00020\u00052\u0006\u0010O\u001a\u0002002\u0006\u0010P\u001a\u00020\u00052\u0006\u0010T\u001a\u00020\u001dH\u0007JT\u0010d\u001a\u00020e2\u0006\u00104\u001a\u0002052\u0006\u0010*\u001a\u00020+2\u0006\u0010[\u001a\u00020+2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020K2\u0006\u0010\\\u001a\u00020I2\u0006\u0010]\u001a\u00020K2\b\u0010@\u001a\u0004\u0018\u00010A2\b\u0010f\u001a\u0004\u0018\u00010gH\u0007J0\u0010h\u001a\u00020g2\u0006\u0010i\u001a\u00020_2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020K2\u0006\u0010\\\u001a\u00020I2\u0006\u0010]\u001a\u00020KH\u0007J0\u0010j\u001a\u00020I2\u0006\u0010k\u001a\u00020D2\u0006\u0010[\u001a\u00020`2\u0006\u0010\\\u001a\u00020I2\u0006\u0010l\u001a\u00020K2\u0006\u0010c\u001a\u00020\u0005H\u0002J\u0018\u0010m\u001a\u00020\u00052\u0006\u0010k\u001a\u00020D2\u0006\u0010c\u001a\u00020\u0005H\u0002J0\u0010n\u001a\u00020I2\u0006\u0010k\u001a\u00020D2\u0006\u0010E\u001a\u00020o2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020K2\u0006\u0010p\u001a\u00020\u0005H\u0002J(\u0010q\u001a\u00020I2\u0006\u0010k\u001a\u00020D2\u0006\u0010\\\u001a\u00020I2\u0006\u0010]\u001a\u00020K2\u0006\u0010p\u001a\u00020\u0005H\u0002J@\u0010r\u001a\u00020\u00052\u0006\u0010k\u001a\u00020D2\u0006\u0010E\u001a\u00020o2\u0006\u0010s\u001a\u00020I2\u0006\u0010p\u001a\u00020\u00052\u0006\u0010t\u001a\u00020\u00052\u0006\u0010u\u001a\u00020I2\u0006\u0010v\u001a\u00020wH\u0002J:\u0010x\u001a\u00020y2\u0006\u00104\u001a\u0002052\u0006\u0010*\u001a\u00020+2\u0006\u0010[\u001a\u00020+2\u0006\u0010H\u001a\u00020I2\u0006\u0010\\\u001a\u00020I2\b\u0010@\u001a\u0004\u0018\u00010AH\u0007J\u001a\u0010z\u001a\u0004\u0018\u00010G2\u0006\u0010E\u001a\u00020#2\u0006\u0010T\u001a\u00020\u001dH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001fX\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020#0\"X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006}"}, d2 = {"Lcom/facebook/react/views/text/TextLayoutManager;", "", "<init>", "()V", "AS_KEY_HASH", "", "AS_KEY_STRING", "AS_KEY_FRAGMENTS", "AS_KEY_CACHE_ID", "AS_KEY_BASE_ATTRIBUTES", "FR_KEY_STRING", "FR_KEY_REACT_TAG", "FR_KEY_IS_ATTACHMENT", "FR_KEY_WIDTH", "FR_KEY_HEIGHT", "FR_KEY_TEXT_ATTRIBUTES", "PA_KEY_MAX_NUMBER_OF_LINES", "PA_KEY_ELLIPSIZE_MODE", "PA_KEY_TEXT_BREAK_STRATEGY", "PA_KEY_ADJUST_FONT_SIZE_TO_FIT", "PA_KEY_INCLUDE_FONT_PADDING", "PA_KEY_HYPHENATION_FREQUENCY", "PA_KEY_MINIMUM_FONT_SIZE", "PA_KEY_MAXIMUM_FONT_SIZE", "PA_KEY_TEXT_ALIGN_VERTICAL", "TAG", "", "textPaintInstance", "Ljava/lang/ThreadLocal;", "Landroid/text/TextPaint;", "DEFAULT_INCLUDE_FONT_PADDING", "", "DEFAULT_ADJUST_FONT_SIZE_TO_FIT", "tagToSpannableCache", "Ljava/util/concurrent/ConcurrentHashMap;", "Landroid/text/Spannable;", "setCachedSpannableForTag", "", "reactTag", "sp", "deleteCachedSpannableForTag", "isRTL", "attributedString", "Lcom/facebook/react/common/mapbuffer/MapBuffer;", "getTextAlignmentAttr", "getTextJustificationMode", "alignmentAttr", "getTextAlignment", "Landroid/text/Layout$Alignment;", "spanned", "getTextGravity", "buildSpannableFromFragments", "context", "Landroid/content/Context;", "fragments", "sb", "Landroid/text/SpannableStringBuilder;", "ops", "", "Lcom/facebook/react/views/text/internal/span/SetSpanOperation;", "outputReactTags", "", "buildSpannableFromFragmentsOptimized", "getOrCreateSpannableForText", "reactTextViewManagerCallback", "Lcom/facebook/react/views/text/ReactTextViewManagerCallback;", "createSpannableFromAttributedString", "createLayout", "Landroid/text/Layout;", ReactTextInputShadowNode.PROP_TEXT, "boring", "Landroid/text/BoringLayout$Metrics;", "width", "", "widthYogaMeasureMode", "Lcom/facebook/yoga/YogaMeasureMode;", ViewProps.INCLUDE_FONT_PADDING, ViewProps.TEXT_BREAK_STRATEGY, "hyphenationFrequency", "alignment", "justificationMode", ViewProps.ELLIPSIZE_MODE, "Landroid/text/TextUtils$TruncateAt;", "maxNumberOfLines", "paint", "updateTextPaint", "baseTextAttributes", "Lcom/facebook/react/views/text/TextAttributeProps;", "scratchPaintWithAttributes", "newPaintWithAttributes", "createLayoutForMeasurement", "paragraphAttributes", "height", "heightYogaMeasureMode", "createPreparedLayout", "Lcom/facebook/react/views/text/PreparedLayout;", "Lcom/facebook/react/common/mapbuffer/ReadableMapBuffer;", "adjustSpannableFontToFit", "minimumFontSizeAttr", "maximumNumberOfLines", "measureText", "", "attachmentsPositions", "", "measurePreparedLayout", "preparedLayout", "getVerticalOffset", "layout", "heightMeasureMode", "calculateLineCount", "calculateWidth", "Landroid/text/Spanned;", "calculatedLineCount", "calculateHeight", "nextAttachmentMetrics", "calculatedWidth", ContextChain.TAG_INFRA, "verticalOffset", "metrics", "Lcom/facebook/react/views/text/TextLayoutManager$AttachmentMetrics;", "measureLines", "Lcom/facebook/react/bridge/WritableArray;", "isBoring", "FragmentAttributes", "AttachmentMetrics", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TextLayoutManager {
    public static final int AS_KEY_BASE_ATTRIBUTES = 4;
    public static final int AS_KEY_CACHE_ID = 3;
    public static final int AS_KEY_FRAGMENTS = 2;
    public static final int AS_KEY_HASH = 0;
    public static final int AS_KEY_STRING = 1;
    private static final boolean DEFAULT_ADJUST_FONT_SIZE_TO_FIT = false;
    private static final boolean DEFAULT_INCLUDE_FONT_PADDING = true;
    public static final int FR_KEY_HEIGHT = 4;
    public static final int FR_KEY_IS_ATTACHMENT = 2;
    public static final int FR_KEY_REACT_TAG = 1;
    public static final int FR_KEY_STRING = 0;
    public static final int FR_KEY_TEXT_ATTRIBUTES = 5;
    public static final int FR_KEY_WIDTH = 3;
    public static final TextLayoutManager INSTANCE = new TextLayoutManager();
    public static final int PA_KEY_ADJUST_FONT_SIZE_TO_FIT = 3;
    public static final int PA_KEY_ELLIPSIZE_MODE = 1;
    public static final int PA_KEY_HYPHENATION_FREQUENCY = 5;
    public static final int PA_KEY_INCLUDE_FONT_PADDING = 4;
    public static final int PA_KEY_MAXIMUM_FONT_SIZE = 7;
    public static final int PA_KEY_MAX_NUMBER_OF_LINES = 0;
    public static final int PA_KEY_MINIMUM_FONT_SIZE = 6;
    public static final int PA_KEY_TEXT_ALIGN_VERTICAL = 8;
    public static final int PA_KEY_TEXT_BREAK_STRATEGY = 2;
    private static final String TAG;
    private static final ConcurrentHashMap<Integer, Spannable> tagToSpannableCache;
    private static final ThreadLocal<TextPaint> textPaintInstance;

    /* JADX INFO: compiled from: TextLayoutManager.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Layout.Alignment.values().length];
            try {
                iArr[Layout.Alignment.ALIGN_NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Layout.Alignment.ALIGN_OPPOSITE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Layout.Alignment.ALIGN_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[YogaMeasureMode.values().length];
            try {
                iArr2[YogaMeasureMode.EXACTLY.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[YogaMeasureMode.AT_MOST.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private TextLayoutManager() {
    }

    static {
        Intrinsics.checkNotNullExpressionValue("TextLayoutManager", "getSimpleName(...)");
        TAG = "TextLayoutManager";
        textPaintInstance = new ThreadLocal<TextPaint>() { // from class: com.facebook.react.views.text.TextLayoutManager$textPaintInstance$1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.lang.ThreadLocal
            public TextPaint initialValue() {
                return new TextPaint(1);
            }
        };
        tagToSpannableCache = new ConcurrentHashMap<>();
    }

    public final void setCachedSpannableForTag(int reactTag, Spannable sp) {
        Intrinsics.checkNotNullParameter(sp, "sp");
        tagToSpannableCache.put(Integer.valueOf(reactTag), sp);
    }

    public final void deleteCachedSpannableForTag(int reactTag) {
        tagToSpannableCache.remove(Integer.valueOf(reactTag));
    }

    public final boolean isRTL(MapBuffer attributedString) {
        Intrinsics.checkNotNullParameter(attributedString, "attributedString");
        if (!attributedString.contains(2)) {
            return false;
        }
        MapBuffer mapBuffer = attributedString.getMapBuffer(2);
        if (mapBuffer.getCount() == 0) {
            return false;
        }
        MapBuffer mapBuffer2 = mapBuffer.getMapBuffer(0).getMapBuffer(5);
        return mapBuffer2.contains(23) && TextAttributeProps.INSTANCE.getLayoutDirection(mapBuffer2.getString(23)) == 1;
    }

    private final String getTextAlignmentAttr(MapBuffer attributedString) {
        if (!attributedString.contains(2)) {
            return null;
        }
        MapBuffer mapBuffer = attributedString.getMapBuffer(2);
        if (mapBuffer.getCount() != 0) {
            MapBuffer mapBuffer2 = mapBuffer.getMapBuffer(0).getMapBuffer(5);
            if (mapBuffer2.contains(12)) {
                return mapBuffer2.getString(12);
            }
        }
        return null;
    }

    private final int getTextJustificationMode(String alignmentAttr) {
        if (Build.VERSION.SDK_INT < 26) {
            return -1;
        }
        return (alignmentAttr == null || !Intrinsics.areEqual(alignmentAttr, "justified")) ? 0 : 1;
    }

    private final Layout.Alignment getTextAlignment(MapBuffer attributedString, Spannable spanned, String alignmentAttr) {
        Layout.Alignment alignment;
        boolean z = isRTL(attributedString) != TextDirectionHeuristics.FIRSTSTRONG_LTR.isRtl(spanned, 0, spanned.length());
        if (z) {
            alignment = Layout.Alignment.ALIGN_OPPOSITE;
        } else {
            alignment = Layout.Alignment.ALIGN_NORMAL;
        }
        if (alignmentAttr == null) {
            return alignment;
        }
        if (Intrinsics.areEqual(alignmentAttr, "center")) {
            return Layout.Alignment.ALIGN_CENTER;
        }
        if (!Intrinsics.areEqual(alignmentAttr, ViewProps.RIGHT)) {
            return alignment;
        }
        if (z) {
            return Layout.Alignment.ALIGN_NORMAL;
        }
        return Layout.Alignment.ALIGN_OPPOSITE;
    }

    @JvmStatic
    public static final int getTextGravity(MapBuffer attributedString, Spannable spanned) {
        Intrinsics.checkNotNullParameter(attributedString, "attributedString");
        Intrinsics.checkNotNullParameter(spanned, "spanned");
        TextLayoutManager textLayoutManager = INSTANCE;
        Layout.Alignment textAlignment = textLayoutManager.getTextAlignment(attributedString, spanned, textLayoutManager.getTextAlignmentAttr(attributedString));
        boolean zIsRtl = TextDirectionHeuristics.FIRSTSTRONG_LTR.isRtl(spanned, 0, spanned.length());
        int i = WhenMappings.$EnumSwitchMapping$0[textAlignment.ordinal()];
        if (i == 1) {
            return zIsRtl ? 5 : 3;
        }
        if (i == 2) {
            return zIsRtl ? 3 : 5;
        }
        if (i == 3) {
            return 1;
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x009e, code lost:
    
        if (com.facebook.react.internal.featureflags.ReactNativeFeatureFlags.enablePreparedTextLayout() == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00a0, code lost:
    
        r22.add(new com.facebook.react.views.text.internal.span.SetSpanOperation(r6, r8, new com.facebook.react.views.text.internal.span.ReactLinkSpan(r3)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00b0, code lost:
    
        r22.add(new com.facebook.react.views.text.internal.span.SetSpanOperation(r6, r8, new com.facebook.react.views.text.internal.span.ReactClickableSpan(r11)));
     */
    /* JADX WARN: Removed duplicated region for block: B:37:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x022e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void buildSpannableFromFragments(android.content.Context r19, com.facebook.react.common.mapbuffer.MapBuffer r20, android.text.SpannableStringBuilder r21, java.util.List<com.facebook.react.views.text.internal.span.SetSpanOperation> r22, int[] r23) {
        /*
            Method dump skipped, instruction units count: 579
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.TextLayoutManager.buildSpannableFromFragments(android.content.Context, com.facebook.react.common.mapbuffer.MapBuffer, android.text.SpannableStringBuilder, java.util.List, int[]):void");
    }

    /* JADX INFO: compiled from: TextLayoutManager.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\r\b\u0002\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015¨\u0006\u0017"}, d2 = {"Lcom/facebook/react/views/text/TextLayoutManager$FragmentAttributes;", "", "props", "Lcom/facebook/react/views/text/TextAttributeProps;", SentryEnvelopeItemHeader.JsonKeys.LENGTH, "", "reactTag", "isAttachment", "", "width", "", "height", "<init>", "(Lcom/facebook/react/views/text/TextAttributeProps;IIZDD)V", "getProps", "()Lcom/facebook/react/views/text/TextAttributeProps;", "getLength", "()I", "getReactTag", "()Z", "getWidth", "()D", "getHeight", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class FragmentAttributes {
        private final double height;
        private final boolean isAttachment;
        private final int length;
        private final TextAttributeProps props;
        private final int reactTag;
        private final double width;

        public FragmentAttributes(TextAttributeProps props, int i, int i2, boolean z, double d, double d2) {
            Intrinsics.checkNotNullParameter(props, "props");
            this.props = props;
            this.length = i;
            this.reactTag = i2;
            this.isAttachment = z;
            this.width = d;
            this.height = d2;
        }

        public final TextAttributeProps getProps() {
            return this.props;
        }

        public final int getLength() {
            return this.length;
        }

        public final int getReactTag() {
            return this.reactTag;
        }

        /* JADX INFO: renamed from: isAttachment, reason: from getter */
        public final boolean getIsAttachment() {
            return this.isAttachment;
        }

        public final double getWidth() {
            return this.width;
        }

        public final double getHeight() {
            return this.height;
        }
    }

    private final Spannable buildSpannableFromFragmentsOptimized(Context context, MapBuffer fragments, int[] outputReactTags) {
        StringBuilder sb = new StringBuilder();
        ArrayList<FragmentAttributes> arrayList = new ArrayList(fragments.getCount());
        int count = fragments.getCount();
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= count) {
                break;
            }
            MapBuffer mapBuffer = fragments.getMapBuffer(i2);
            TextAttributeProps textAttributePropsFromMapBuffer = TextAttributeProps.INSTANCE.fromMapBuffer(mapBuffer.getMapBuffer(5));
            String strApply = TextTransform.INSTANCE.apply(mapBuffer.getString(0), textAttributePropsFromMapBuffer.getTextTransform());
            sb.append(strApply);
            int length = strApply.length();
            int i3 = mapBuffer.contains(1) ? mapBuffer.getInt(1) : -1;
            boolean z = mapBuffer.contains(2) && mapBuffer.getBoolean(2);
            double d = Double.NaN;
            double d2 = mapBuffer.contains(3) ? mapBuffer.getDouble(3) : Double.NaN;
            if (mapBuffer.contains(4)) {
                d = mapBuffer.getDouble(4);
            }
            arrayList.add(new FragmentAttributes(textAttributePropsFromMapBuffer, length, i3, z, d2, d));
            i2++;
        }
        SpannableString spannableString = new SpannableString(sb);
        int i4 = 0;
        for (FragmentAttributes fragmentAttributes : arrayList) {
            int i5 = i + 1;
            int length2 = fragmentAttributes.getLength() + i4;
            int i6 = i4 == 0 ? 18 : 34;
            if (fragmentAttributes.getIsAttachment()) {
                spannableString.setSpan(new TextInlineViewPlaceholderSpan(fragmentAttributes.getReactTag(), (int) PixelUtil.toPixelFromSP(fragmentAttributes.getWidth()), (int) PixelUtil.toPixelFromSP(fragmentAttributes.getHeight())), i4, length2, i6);
            } else {
                if (fragmentAttributes.getProps().getRole() == null ? fragmentAttributes.getProps().getAccessibilityRole() == ReactAccessibilityDelegate.AccessibilityRole.LINK : fragmentAttributes.getProps().getRole() == ReactAccessibilityDelegate.Role.LINK) {
                    if (ReactNativeFeatureFlags.enablePreparedTextLayout()) {
                        spannableString.setSpan(new ReactLinkSpan(i), i4, length2, i6);
                    } else {
                        spannableString.setSpan(new ReactClickableSpan(fragmentAttributes.getReactTag()), i4, length2, i6);
                    }
                }
                if (fragmentAttributes.getProps().getIsColorSet()) {
                    Integer color = fragmentAttributes.getProps().getColor();
                    spannableString.setSpan(color != null ? new ReactForegroundColorSpan(color.intValue()) : null, i4, length2, i6);
                }
                if (fragmentAttributes.getProps().getIsBackgroundColorSet()) {
                    Integer backgroundColor = fragmentAttributes.getProps().getBackgroundColor();
                    spannableString.setSpan(backgroundColor != null ? new ReactBackgroundColorSpan(backgroundColor.intValue()) : null, i4, length2, i6);
                }
                if (!Float.isNaN(fragmentAttributes.getProps().getOpacity())) {
                    spannableString.setSpan(new ReactOpacitySpan(fragmentAttributes.getProps().getOpacity()), i4, length2, i6);
                }
                if (!Float.isNaN(fragmentAttributes.getProps().getLetterSpacing())) {
                    spannableString.setSpan(new CustomLetterSpacingSpan(fragmentAttributes.getProps().getLetterSpacing()), i4, length2, i6);
                }
                spannableString.setSpan(new ReactAbsoluteSizeSpan(fragmentAttributes.getProps().getFontSize()), i4, length2, i6);
                if (fragmentAttributes.getProps().getFontStyle() != -1 || fragmentAttributes.getProps().getFontWeight() != -1 || fragmentAttributes.getProps().getFontFamily() != null) {
                    int fontStyle = fragmentAttributes.getProps().getFontStyle();
                    int fontWeight = fragmentAttributes.getProps().getFontWeight();
                    String fontFeatureSettings = fragmentAttributes.getProps().getFontFeatureSettings();
                    String fontFamily = fragmentAttributes.getProps().getFontFamily();
                    AssetManager assets = context.getAssets();
                    Intrinsics.checkNotNullExpressionValue(assets, "getAssets(...)");
                    spannableString.setSpan(new CustomStyleSpan(fontStyle, fontWeight, fontFeatureSettings, fontFamily, assets), i4, length2, i6);
                }
                if (fragmentAttributes.getProps().getIsUnderlineTextDecorationSet()) {
                    spannableString.setSpan(new ReactUnderlineSpan(), i4, length2, i6);
                }
                if (fragmentAttributes.getProps().getIsLineThroughTextDecorationSet()) {
                    spannableString.setSpan(new ReactStrikethroughSpan(), i4, length2, i6);
                }
                if ((fragmentAttributes.getProps().getTextShadowOffsetDx() != 0.0f || fragmentAttributes.getProps().getTextShadowOffsetDy() != 0.0f || fragmentAttributes.getProps().getTextShadowRadius() != 0.0f) && Color.alpha(fragmentAttributes.getProps().getTextShadowColor()) != 0) {
                    spannableString.setSpan(new ShadowStyleSpan(fragmentAttributes.getProps().getTextShadowOffsetDx(), fragmentAttributes.getProps().getTextShadowOffsetDy(), fragmentAttributes.getProps().getTextShadowRadius(), fragmentAttributes.getProps().getTextShadowColor()), i4, length2, i6);
                }
                if (!Float.isNaN(fragmentAttributes.getProps().getLineHeight())) {
                    spannableString.setSpan(new CustomLineHeightSpan(fragmentAttributes.getProps().getLineHeight()), i4, length2, i6);
                }
                if (ReactNativeFeatureFlags.enablePreparedTextLayout()) {
                    spannableString.setSpan(new ReactFragmentIndexSpan(i), i4, length2, i6);
                    if (outputReactTags != null) {
                        outputReactTags[i] = fragmentAttributes.getReactTag();
                    }
                } else {
                    spannableString.setSpan(new ReactTagSpan(fragmentAttributes.getReactTag()), i4, length2, i6);
                }
            }
            i = i5;
            i4 = length2;
        }
        return spannableString;
    }

    public final Spannable getOrCreateSpannableForText(Context context, MapBuffer attributedString, ReactTextViewManagerCallback reactTextViewManagerCallback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributedString, "attributedString");
        if (attributedString.contains(3)) {
            Spannable spannable = tagToSpannableCache.get(Integer.valueOf(attributedString.getInt(3)));
            if (spannable != null) {
                return spannable;
            }
            throw new IllegalStateException("Required value was null.".toString());
        }
        return createSpannableFromAttributedString(context, attributedString.getMapBuffer(2), reactTextViewManagerCallback, null);
    }

    private final Spannable createSpannableFromAttributedString(Context context, MapBuffer fragments, ReactTextViewManagerCallback reactTextViewManagerCallback, int[] outputReactTags) {
        if (ReactNativeFeatureFlags.enableAndroidTextMeasurementOptimizations()) {
            Spannable spannableBuildSpannableFromFragmentsOptimized = buildSpannableFromFragmentsOptimized(context, fragments, outputReactTags);
            if (reactTextViewManagerCallback != null) {
                reactTextViewManagerCallback.onPostProcessSpannable(spannableBuildSpannableFromFragmentsOptimized);
            }
            return spannableBuildSpannableFromFragmentsOptimized;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        ArrayList arrayList = new ArrayList();
        buildSpannableFromFragments(context, fragments, spannableStringBuilder, arrayList, outputReactTags);
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            arrayList.get((arrayList.size() - i) - 1).execute(spannableStringBuilder, i);
        }
        if (reactTextViewManagerCallback != null) {
            reactTextViewManagerCallback.onPostProcessSpannable(spannableStringBuilder);
        }
        return spannableStringBuilder;
    }

    private final Layout createLayout(Spannable text, BoringLayout.Metrics boring, float width, YogaMeasureMode widthYogaMeasureMode, boolean includeFontPadding, int textBreakStrategy, int hyphenationFrequency, Layout.Alignment alignment, int justificationMode, TextUtils.TruncateAt ellipsizeMode, int maxNumberOfLines, TextPaint paint) {
        if (boring != null && (widthYogaMeasureMode == YogaMeasureMode.UNDEFINED || boring.width <= ((float) Math.floor(width)))) {
            BoringLayout boringLayoutMake = BoringLayout.make(text, paint, widthYogaMeasureMode == YogaMeasureMode.EXACTLY ? (int) Math.floor(width) : boring.width, alignment, 1.0f, 0.0f, boring, includeFontPadding);
            Intrinsics.checkNotNullExpressionValue(boringLayoutMake, "make(...)");
            return boringLayoutMake;
        }
        Spannable spannable = text;
        int iCeil = (int) Math.ceil(Layout.getDesiredWidth(spannable, paint));
        int i = WhenMappings.$EnumSwitchMapping$1[widthYogaMeasureMode.ordinal()];
        if (i == 1) {
            iCeil = (int) Math.floor(width);
        } else if (i == 2) {
            iCeil = Math.min(iCeil, (int) Math.floor(width));
        }
        StaticLayout.Builder hyphenationFrequency2 = StaticLayout.Builder.obtain(spannable, 0, text.length(), paint, iCeil).setAlignment(alignment).setLineSpacing(0.0f, 1.0f).setIncludePad(includeFontPadding).setBreakStrategy(textBreakStrategy).setHyphenationFrequency(hyphenationFrequency);
        Intrinsics.checkNotNullExpressionValue(hyphenationFrequency2, "setHyphenationFrequency(...)");
        if (maxNumberOfLines != -1 && maxNumberOfLines != 0) {
            hyphenationFrequency2.setEllipsize(ellipsizeMode).setMaxLines(maxNumberOfLines);
        }
        if (Build.VERSION.SDK_INT >= 26) {
            hyphenationFrequency2.setJustificationMode(justificationMode);
        }
        if (Build.VERSION.SDK_INT >= 28) {
            hyphenationFrequency2.setUseLineSpacingFromFallbacks(true);
        }
        StaticLayout staticLayoutBuild = hyphenationFrequency2.build();
        Intrinsics.checkNotNullExpressionValue(staticLayoutBuild, "build(...)");
        return staticLayoutBuild;
    }

    private final void updateTextPaint(TextPaint paint, TextAttributeProps baseTextAttributes, Context context) {
        if (baseTextAttributes.getFontSize() != -1) {
            paint.setTextSize(baseTextAttributes.getFontSize());
        }
        if (baseTextAttributes.getFontStyle() == -1 && baseTextAttributes.getFontWeight() == -1 && baseTextAttributes.getFontFamily() == null) {
            return;
        }
        int fontStyle = baseTextAttributes.getFontStyle();
        int fontWeight = baseTextAttributes.getFontWeight();
        String fontFamily = baseTextAttributes.getFontFamily();
        AssetManager assets = context.getAssets();
        Intrinsics.checkNotNullExpressionValue(assets, "getAssets(...)");
        Typeface typefaceApplyStyles = ReactTypefaceUtils.applyStyles(null, fontStyle, fontWeight, fontFamily, assets);
        paint.setTypeface(typefaceApplyStyles);
        if (baseTextAttributes.getFontStyle() == -1 || baseTextAttributes.getFontStyle() == typefaceApplyStyles.getStyle()) {
            return;
        }
        int fontStyle2 = baseTextAttributes.getFontStyle() & (~typefaceApplyStyles.getStyle());
        paint.setFakeBoldText((fontStyle2 & 1) != 0);
        paint.setTextSkewX((fontStyle2 & 2) != 0 ? -0.25f : 0.0f);
    }

    private final TextPaint scratchPaintWithAttributes(TextAttributeProps baseTextAttributes, Context context) {
        TextPaint textPaint = textPaintInstance.get();
        if (textPaint == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        TextPaint textPaint2 = textPaint;
        textPaint2.setTypeface(null);
        textPaint2.setTextSize(12.0f);
        textPaint2.setFakeBoldText(false);
        textPaint2.setTextSkewX(0.0f);
        updateTextPaint(textPaint2, baseTextAttributes, context);
        return textPaint2;
    }

    private final TextPaint newPaintWithAttributes(TextAttributeProps baseTextAttributes, Context context) {
        TextPaint textPaint = new TextPaint(1);
        updateTextPaint(textPaint, baseTextAttributes, context);
        return textPaint;
    }

    private final Layout createLayoutForMeasurement(Context context, MapBuffer attributedString, MapBuffer paragraphAttributes, float width, YogaMeasureMode widthYogaMeasureMode, float height, YogaMeasureMode heightYogaMeasureMode, ReactTextViewManagerCallback reactTextViewManagerCallback) {
        TextPaint textPaintScratchPaintWithAttributes;
        Spannable orCreateSpannableForText = getOrCreateSpannableForText(context, attributedString, reactTextViewManagerCallback);
        if (attributedString.contains(3)) {
            textPaintScratchPaintWithAttributes = ((ReactTextPaintHolderSpan[]) orCreateSpannableForText.getSpans(0, 0, ReactTextPaintHolderSpan.class))[0].getTextPaint();
        } else {
            textPaintScratchPaintWithAttributes = scratchPaintWithAttributes(TextAttributeProps.INSTANCE.fromMapBuffer(attributedString.getMapBuffer(4)), context);
        }
        return createLayout(orCreateSpannableForText, textPaintScratchPaintWithAttributes, attributedString, paragraphAttributes, width, widthYogaMeasureMode, height, heightYogaMeasureMode);
    }

    private final Layout createLayout(Spannable text, TextPaint paint, MapBuffer attributedString, MapBuffer paragraphAttributes, float width, YogaMeasureMode widthYogaMeasureMode, float height, YogaMeasureMode heightYogaMeasureMode) {
        Spannable spannable;
        TextPaint textPaint;
        YogaMeasureMode yogaMeasureMode;
        BoringLayout.Metrics metrics;
        TextUtils.TruncateAt truncateAt;
        boolean z;
        int i;
        Layout.Alignment alignment;
        BoringLayout.Metrics metricsIsBoring = isBoring(text, paint);
        int textBreakStrategy = TextAttributeProps.INSTANCE.getTextBreakStrategy(paragraphAttributes.getString(2));
        boolean z2 = paragraphAttributes.contains(4) ? paragraphAttributes.getBoolean(4) : true;
        int hyphenationFrequency = TextAttributeProps.INSTANCE.getHyphenationFrequency(paragraphAttributes.getString(5));
        boolean z3 = paragraphAttributes.contains(3) ? paragraphAttributes.getBoolean(3) : false;
        int i2 = paragraphAttributes.contains(0) ? paragraphAttributes.getInt(0) : -1;
        TextUtils.TruncateAt ellipsizeMode = paragraphAttributes.contains(1) ? TextAttributeProps.INSTANCE.getEllipsizeMode(paragraphAttributes.getString(1)) : null;
        String textAlignmentAttr = getTextAlignmentAttr(attributedString);
        Layout.Alignment textAlignment = getTextAlignment(attributedString, text, textAlignmentAttr);
        int textJustificationMode = getTextJustificationMode(textAlignmentAttr);
        if (z3) {
            adjustSpannableFontToFit(text, width, YogaMeasureMode.EXACTLY, height, heightYogaMeasureMode, paragraphAttributes.contains(6) ? (float) paragraphAttributes.getDouble(6) : Float.NaN, i2, z2, textBreakStrategy, hyphenationFrequency, textAlignment, textJustificationMode, paint);
            i2 = i2;
            hyphenationFrequency = hyphenationFrequency;
            textJustificationMode = textJustificationMode;
            spannable = text;
            yogaMeasureMode = widthYogaMeasureMode;
            metrics = metricsIsBoring;
            truncateAt = ellipsizeMode;
            z = z2;
            i = textBreakStrategy;
            alignment = textAlignment;
            textPaint = paint;
        } else {
            spannable = text;
            textPaint = paint;
            yogaMeasureMode = widthYogaMeasureMode;
            metrics = metricsIsBoring;
            truncateAt = ellipsizeMode;
            z = z2;
            i = textBreakStrategy;
            alignment = textAlignment;
        }
        return createLayout(spannable, metrics, width, yogaMeasureMode, z, i, hyphenationFrequency, alignment, textJustificationMode, truncateAt, i2, textPaint);
    }

    @JvmStatic
    public static final PreparedLayout createPreparedLayout(Context context, ReadableMapBuffer attributedString, ReadableMapBuffer paragraphAttributes, float width, YogaMeasureMode widthYogaMeasureMode, float height, YogaMeasureMode heightYogaMeasureMode, ReactTextViewManagerCallback reactTextViewManagerCallback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributedString, "attributedString");
        Intrinsics.checkNotNullParameter(paragraphAttributes, "paragraphAttributes");
        Intrinsics.checkNotNullParameter(widthYogaMeasureMode, "widthYogaMeasureMode");
        Intrinsics.checkNotNullParameter(heightYogaMeasureMode, "heightYogaMeasureMode");
        ReadableMapBuffer mapBuffer = attributedString.getMapBuffer(2);
        int[] iArr = new int[mapBuffer.getCount()];
        TextLayoutManager textLayoutManager = INSTANCE;
        Layout layoutCreateLayout = textLayoutManager.createLayout(textLayoutManager.createSpannableFromAttributedString(context, mapBuffer, reactTextViewManagerCallback, iArr), textLayoutManager.newPaintWithAttributes(TextAttributeProps.INSTANCE.fromMapBuffer(attributedString.getMapBuffer(4)), context), attributedString, paragraphAttributes, width, widthYogaMeasureMode, height, heightYogaMeasureMode);
        int i = paragraphAttributes.contains(0) ? paragraphAttributes.getInt(0) : -1;
        return new PreparedLayout(layoutCreateLayout, i, textLayoutManager.getVerticalOffset(layoutCreateLayout, paragraphAttributes, height, heightYogaMeasureMode, i), iArr);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @JvmStatic
    public static final void adjustSpannableFontToFit(Spannable text, float width, YogaMeasureMode widthYogaMeasureMode, float height, YogaMeasureMode heightYogaMeasureMode, float minimumFontSizeAttr, int maximumNumberOfLines, boolean includeFontPadding, int textBreakStrategy, int hyphenationFrequency, Layout.Alignment alignment, int justificationMode, TextPaint paint) {
        Spannable text2 = text;
        TextPaint paint2 = paint;
        Intrinsics.checkNotNullParameter(text2, "text");
        Intrinsics.checkNotNullParameter(widthYogaMeasureMode, "widthYogaMeasureMode");
        Intrinsics.checkNotNullParameter(heightYogaMeasureMode, "heightYogaMeasureMode");
        Intrinsics.checkNotNullParameter(alignment, "alignment");
        Intrinsics.checkNotNullParameter(paint2, "paint");
        TextLayoutManager textLayoutManager = INSTANCE;
        BoringLayout.Metrics metricsIsBoring = textLayoutManager.isBoring(text2, paint2);
        Layout layoutCreateLayout = textLayoutManager.createLayout(text2, metricsIsBoring, width, widthYogaMeasureMode, includeFontPadding, textBreakStrategy, hyphenationFrequency, alignment, justificationMode, null, -1, paint2);
        int iDpToPx = (int) (Float.isNaN(minimumFontSizeAttr) ? PixelUtil.INSTANCE.dpToPx(4) : minimumFontSizeAttr);
        int i = 0;
        Iterator it = ArrayIteratorKt.iterator((ReactAbsoluteSizeSpan[]) text2.getSpans(0, text2.length(), ReactAbsoluteSizeSpan.class));
        int iMax = iDpToPx;
        while (it.hasNext()) {
            iMax = Math.max(iMax, ((ReactAbsoluteSizeSpan) it.next()).getSize());
        }
        int i2 = iMax;
        while (i2 > iDpToPx) {
            if ((maximumNumberOfLines == -1 || maximumNumberOfLines == 0 || layoutCreateLayout.getLineCount() <= maximumNumberOfLines) && ((heightYogaMeasureMode == YogaMeasureMode.UNDEFINED || layoutCreateLayout.getHeight() <= height) && (text2.length() != 1 || layoutCreateLayout.getLineWidth(i) <= width))) {
                return;
            }
            int iMax2 = i2 - Math.max(1, (int) PixelUtil.INSTANCE.dpToPx(1));
            float f = iMax2 / iMax;
            paint2.setTextSize(Math.max((int) (paint2.getTextSize() * f), iDpToPx));
            Iterator it2 = ArrayIteratorKt.iterator((ReactAbsoluteSizeSpan[]) text2.getSpans(i, text2.length(), ReactAbsoluteSizeSpan.class));
            while (it2.hasNext()) {
                ReactAbsoluteSizeSpan reactAbsoluteSizeSpan = (ReactAbsoluteSizeSpan) it2.next();
                text2.setSpan(new ReactAbsoluteSizeSpan(Math.max((int) (reactAbsoluteSizeSpan.getSize() * f), iDpToPx)), text2.getSpanStart(reactAbsoluteSizeSpan), text2.getSpanEnd(reactAbsoluteSizeSpan), text2.getSpanFlags(reactAbsoluteSizeSpan));
                text2.removeSpan(reactAbsoluteSizeSpan);
            }
            if (metricsIsBoring != null) {
                metricsIsBoring = INSTANCE.isBoring(text2, paint2);
            }
            layoutCreateLayout = INSTANCE.createLayout(text2, metricsIsBoring, width, widthYogaMeasureMode, includeFontPadding, textBreakStrategy, hyphenationFrequency, alignment, justificationMode, null, -1, paint2);
            text2 = text;
            paint2 = paint;
            i2 = iMax2;
            iMax = iMax;
            i = i;
        }
    }

    @JvmStatic
    public static final long measureText(Context context, MapBuffer attributedString, MapBuffer paragraphAttributes, float width, YogaMeasureMode widthYogaMeasureMode, float height, YogaMeasureMode heightYogaMeasureMode, ReactTextViewManagerCallback reactTextViewManagerCallback, float[] attachmentsPositions) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributedString, "attributedString");
        Intrinsics.checkNotNullParameter(paragraphAttributes, "paragraphAttributes");
        Intrinsics.checkNotNullParameter(widthYogaMeasureMode, "widthYogaMeasureMode");
        Intrinsics.checkNotNullParameter(heightYogaMeasureMode, "heightYogaMeasureMode");
        TextLayoutManager textLayoutManager = INSTANCE;
        Layout layoutCreateLayoutForMeasurement = textLayoutManager.createLayoutForMeasurement(context, attributedString, paragraphAttributes, width, widthYogaMeasureMode, height, heightYogaMeasureMode, reactTextViewManagerCallback);
        int iNextAttachmentMetrics = 0;
        int i = paragraphAttributes.contains(0) ? paragraphAttributes.getInt(0) : -1;
        CharSequence text = layoutCreateLayoutForMeasurement.getText();
        Intrinsics.checkNotNull(text, "null cannot be cast to non-null type android.text.Spanned");
        Spanned spanned = (Spanned) text;
        int iCalculateLineCount = textLayoutManager.calculateLineCount(layoutCreateLayoutForMeasurement, i);
        float fCalculateWidth = textLayoutManager.calculateWidth(layoutCreateLayoutForMeasurement, spanned, width, widthYogaMeasureMode, iCalculateLineCount);
        float fCalculateHeight = textLayoutManager.calculateHeight(layoutCreateLayoutForMeasurement, height, heightYogaMeasureMode, iCalculateLineCount);
        if (attachmentsPositions != null) {
            AttachmentMetrics attachmentMetrics = new AttachmentMetrics();
            int i2 = 0;
            while (iNextAttachmentMetrics < spanned.length()) {
                iNextAttachmentMetrics = INSTANCE.nextAttachmentMetrics(layoutCreateLayoutForMeasurement, spanned, fCalculateWidth, iCalculateLineCount, iNextAttachmentMetrics, 0.0f, attachmentMetrics);
                if (attachmentMetrics.getWasFound()) {
                    attachmentsPositions[i2] = PixelUtil.INSTANCE.pxToDp(attachmentMetrics.getTop());
                    attachmentsPositions[i2 + 1] = PixelUtil.INSTANCE.pxToDp(attachmentMetrics.getLeft());
                    i2 += 2;
                }
            }
        }
        return YogaMeasureOutput.make(PixelUtil.INSTANCE.pxToDp(fCalculateWidth), PixelUtil.INSTANCE.pxToDp(fCalculateHeight));
    }

    @JvmStatic
    public static final float[] measurePreparedLayout(PreparedLayout preparedLayout, float width, YogaMeasureMode widthYogaMeasureMode, float height, YogaMeasureMode heightYogaMeasureMode) {
        Intrinsics.checkNotNullParameter(preparedLayout, "preparedLayout");
        Intrinsics.checkNotNullParameter(widthYogaMeasureMode, "widthYogaMeasureMode");
        Intrinsics.checkNotNullParameter(heightYogaMeasureMode, "heightYogaMeasureMode");
        Layout layout = preparedLayout.getLayout();
        CharSequence text = layout.getText();
        Intrinsics.checkNotNull(text, "null cannot be cast to non-null type android.text.Spanned");
        Spanned spanned = (Spanned) text;
        int maximumNumberOfLines = preparedLayout.getMaximumNumberOfLines();
        TextLayoutManager textLayoutManager = INSTANCE;
        int iCalculateLineCount = textLayoutManager.calculateLineCount(layout, maximumNumberOfLines);
        float fCalculateWidth = textLayoutManager.calculateWidth(layout, spanned, width, widthYogaMeasureMode, iCalculateLineCount);
        float fCalculateHeight = textLayoutManager.calculateHeight(layout, height, heightYogaMeasureMode, iCalculateLineCount);
        ArrayList arrayList = new ArrayList();
        arrayList.add(Float.valueOf(PixelUtil.INSTANCE.pxToDp(fCalculateWidth)));
        arrayList.add(Float.valueOf(PixelUtil.INSTANCE.pxToDp(fCalculateHeight)));
        AttachmentMetrics attachmentMetrics = new AttachmentMetrics();
        int iNextAttachmentMetrics = 0;
        while (iNextAttachmentMetrics < spanned.length()) {
            iNextAttachmentMetrics = textLayoutManager.nextAttachmentMetrics(layout, spanned, fCalculateWidth, iCalculateLineCount, iNextAttachmentMetrics, preparedLayout.getVerticalOffset(), attachmentMetrics);
            if (attachmentMetrics.getWasFound()) {
                arrayList.add(Float.valueOf(PixelUtil.INSTANCE.pxToDp(attachmentMetrics.getTop())));
                arrayList.add(Float.valueOf(PixelUtil.INSTANCE.pxToDp(attachmentMetrics.getLeft())));
                arrayList.add(Float.valueOf(PixelUtil.INSTANCE.pxToDp(attachmentMetrics.getWidth())));
                arrayList.add(Float.valueOf(PixelUtil.INSTANCE.pxToDp(attachmentMetrics.getHeight())));
            }
        }
        float[] fArr = new float[arrayList.size()];
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Object obj = arrayList.get(i);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            fArr[i] = ((Number) obj).floatValue();
        }
        return fArr;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0032, code lost:
    
        if (r4.equals("auto") == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003c, code lost:
    
        if (r4.equals("top") == false) goto L32;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final float getVerticalOffset(android.text.Layout r3, com.facebook.react.common.mapbuffer.ReadableMapBuffer r4, float r5, com.facebook.yoga.YogaMeasureMode r6, int r7) {
        /*
            r2 = this;
            r0 = 8
            boolean r1 = r4.contains(r0)
            if (r1 == 0) goto Ld
            java.lang.String r4 = r4.getString(r0)
            goto Le
        Ld:
            r4 = 0
        Le:
            r0 = 0
            if (r4 != 0) goto L12
            return r0
        L12:
            int r1 = r3.getHeight()
            int r7 = r2.calculateLineCount(r3, r7)
            float r3 = r2.calculateHeight(r3, r5, r6, r7)
            float r5 = (float) r1
            int r6 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r6 <= 0) goto L24
            return r0
        L24:
            int r6 = r4.hashCode()
            switch(r6) {
                case -1383228885: goto L4e;
                case -1364013995: goto L40;
                case 115029: goto L35;
                case 3005871: goto L2c;
                default: goto L2b;
            }
        L2b:
            goto L59
        L2c:
            java.lang.String r3 = "auto"
            boolean r3 = r4.equals(r3)
            if (r3 != 0) goto L3f
            goto L59
        L35:
            java.lang.String r3 = "top"
            boolean r3 = r4.equals(r3)
            if (r3 != 0) goto L3f
            goto L59
        L3f:
            return r0
        L40:
            java.lang.String r6 = "center"
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L49
            goto L59
        L49:
            float r3 = r3 - r5
            r4 = 1073741824(0x40000000, float:2.0)
            float r3 = r3 / r4
            return r3
        L4e:
            java.lang.String r6 = "bottom"
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L57
            goto L59
        L57:
            float r3 = r3 - r5
            return r3
        L59:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = "Invalid textAlignVertical: "
            r3.<init>(r5)
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "ReactNative"
            com.facebook.common.logging.FLog.w(r4, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.TextLayoutManager.getVerticalOffset(android.text.Layout, com.facebook.react.common.mapbuffer.ReadableMapBuffer, float, com.facebook.yoga.YogaMeasureMode, int):float");
    }

    private final int calculateLineCount(Layout layout, int maximumNumberOfLines) {
        if (maximumNumberOfLines == -1 || maximumNumberOfLines == 0) {
            return layout.getLineCount();
        }
        return Math.min(maximumNumberOfLines, layout.getLineCount());
    }

    private final float calculateWidth(Layout layout, Spanned text, float width, YogaMeasureMode widthYogaMeasureMode, int calculatedLineCount) {
        return widthYogaMeasureMode == YogaMeasureMode.EXACTLY ? width : layout.getWidth();
    }

    private final float calculateHeight(Layout layout, float height, YogaMeasureMode heightYogaMeasureMode, int calculatedLineCount) {
        if (heightYogaMeasureMode != YogaMeasureMode.EXACTLY) {
            float lineBottom = layout.getLineBottom(calculatedLineCount - 1);
            if (heightYogaMeasureMode != YogaMeasureMode.AT_MOST || lineBottom <= height) {
                return lineBottom;
            }
        }
        return height;
    }

    private final int nextAttachmentMetrics(Layout layout, Spanned text, float calculatedWidth, int calculatedLineCount, int i, float verticalOffset, AttachmentMetrics metrics) {
        float secondaryHorizontal;
        int iNextSpanTransition = text.nextSpanTransition(i, text.length(), TextInlineViewPlaceholderSpan.class);
        TextInlineViewPlaceholderSpan[] textInlineViewPlaceholderSpanArr = (TextInlineViewPlaceholderSpan[]) text.getSpans(i, iNextSpanTransition, TextInlineViewPlaceholderSpan.class);
        if (textInlineViewPlaceholderSpanArr.length == 0) {
            metrics.setWasFound(false);
            return iNextSpanTransition;
        }
        Assertions.assertCondition(textInlineViewPlaceholderSpanArr.length == 1);
        TextInlineViewPlaceholderSpan textInlineViewPlaceholderSpan = textInlineViewPlaceholderSpanArr[0];
        int spanStart = text.getSpanStart(textInlineViewPlaceholderSpan);
        int lineForOffset = layout.getLineForOffset(spanStart);
        boolean z = layout.getEllipsisCount(lineForOffset) > 0;
        if (lineForOffset > calculatedLineCount || (z && spanStart >= layout.getLineStart(lineForOffset) + layout.getEllipsisStart(lineForOffset))) {
            metrics.setTop(Float.NaN);
            metrics.setLeft(Float.NaN);
        } else {
            float width = textInlineViewPlaceholderSpan.getWidth();
            float height = textInlineViewPlaceholderSpan.getHeight();
            boolean zIsRtlCharAt = layout.isRtlCharAt(spanStart);
            boolean z2 = layout.getParagraphDirection(lineForOffset) == -1;
            if (!ReactNativeFeatureFlags.disableOldAndroidAttachmentMetricsWorkarounds() && spanStart == text.length() - 1) {
                float lineWidth = (text.length() <= 0 || text.charAt(layout.getLineEnd(lineForOffset) - 1) != '\n') ? layout.getLineWidth(lineForOffset) : layout.getLineMax(lineForOffset);
                if (!z2) {
                    secondaryHorizontal = layout.getLineRight(lineForOffset);
                    secondaryHorizontal -= width;
                    metrics.setTop(layout.getLineBaseline(lineForOffset) - height);
                    metrics.setLeft(secondaryHorizontal);
                } else {
                    secondaryHorizontal = calculatedWidth - lineWidth;
                    metrics.setTop(layout.getLineBaseline(lineForOffset) - height);
                    metrics.setLeft(secondaryHorizontal);
                }
            } else {
                if (z2 == zIsRtlCharAt) {
                    secondaryHorizontal = layout.getPrimaryHorizontal(spanStart);
                } else {
                    secondaryHorizontal = layout.getSecondaryHorizontal(spanStart);
                }
                if (!ReactNativeFeatureFlags.disableOldAndroidAttachmentMetricsWorkarounds() && z2 && !zIsRtlCharAt) {
                    secondaryHorizontal = calculatedWidth - (layout.getLineRight(lineForOffset) - secondaryHorizontal);
                }
                if (zIsRtlCharAt) {
                    secondaryHorizontal -= width;
                }
                metrics.setTop(layout.getLineBaseline(lineForOffset) - height);
                metrics.setLeft(secondaryHorizontal);
            }
        }
        metrics.setTop(metrics.getTop() + verticalOffset);
        metrics.setWasFound(true);
        metrics.setWidth(textInlineViewPlaceholderSpan.getWidth());
        metrics.setHeight(textInlineViewPlaceholderSpan.getHeight());
        return iNextSpanTransition;
    }

    @JvmStatic
    public static final WritableArray measureLines(Context context, MapBuffer attributedString, MapBuffer paragraphAttributes, float width, float height, ReactTextViewManagerCallback reactTextViewManagerCallback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributedString, "attributedString");
        Intrinsics.checkNotNullParameter(paragraphAttributes, "paragraphAttributes");
        Layout layoutCreateLayoutForMeasurement = INSTANCE.createLayoutForMeasurement(context, attributedString, paragraphAttributes, width, YogaMeasureMode.EXACTLY, height, YogaMeasureMode.EXACTLY, reactTextViewManagerCallback);
        CharSequence text = layoutCreateLayoutForMeasurement.getText();
        Intrinsics.checkNotNullExpressionValue(text, "getText(...)");
        return FontMetricsUtil.getFontMetrics(text, layoutCreateLayoutForMeasurement, context);
    }

    private final BoringLayout.Metrics isBoring(Spannable text, TextPaint paint) {
        if (Build.VERSION.SDK_INT < 33) {
            return BoringLayout.isBoring(text, paint);
        }
        return BoringLayout.isBoring(text, paint, TextDirectionHeuristics.FIRSTSTRONG_LTR, true, null);
    }

    /* JADX INFO: compiled from: TextLayoutManager.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u000e\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u001a\u0010\u0013\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR\u001a\u0010\u0016\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\r\"\u0004\b\u0018\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/facebook/react/views/text/TextLayoutManager$AttachmentMetrics;", "", "<init>", "()V", "wasFound", "", "getWasFound", "()Z", "setWasFound", "(Z)V", "top", "", "getTop", "()F", "setTop", "(F)V", "left", "getLeft", "setLeft", "width", "getWidth", "setWidth", "height", "getHeight", "setHeight", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class AttachmentMetrics {
        private float height;
        private float left;
        private float top;
        private boolean wasFound;
        private float width;

        public final boolean getWasFound() {
            return this.wasFound;
        }

        public final void setWasFound(boolean z) {
            this.wasFound = z;
        }

        public final float getTop() {
            return this.top;
        }

        public final void setTop(float f) {
            this.top = f;
        }

        public final float getLeft() {
            return this.left;
        }

        public final void setLeft(float f) {
            this.left = f;
        }

        public final float getWidth() {
            return this.width;
        }

        public final void setWidth(float f) {
            this.width = f;
        }

        public final float getHeight() {
            return this.height;
        }

        public final void setHeight(float f) {
            this.height = f;
        }
    }
}
