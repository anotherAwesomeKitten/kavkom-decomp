package com.facebook.react.views.text;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.NativeViewHierarchyOptimizer;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactAccessibilityDelegate;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.ReactShadowNodeImpl;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.internal.ReactTextInlineImageShadowNode;
import com.facebook.react.views.text.internal.span.CustomLetterSpacingSpan;
import com.facebook.react.views.text.internal.span.CustomLineHeightSpan;
import com.facebook.react.views.text.internal.span.CustomStyleSpan;
import com.facebook.react.views.text.internal.span.ReactAbsoluteSizeSpan;
import com.facebook.react.views.text.internal.span.ReactBackgroundColorSpan;
import com.facebook.react.views.text.internal.span.ReactClickableSpan;
import com.facebook.react.views.text.internal.span.ReactForegroundColorSpan;
import com.facebook.react.views.text.internal.span.ReactSpan;
import com.facebook.react.views.text.internal.span.ReactStrikethroughSpan;
import com.facebook.react.views.text.internal.span.ReactTagSpan;
import com.facebook.react.views.text.internal.span.ReactUnderlineSpan;
import com.facebook.react.views.text.internal.span.SetSpanOperation;
import com.facebook.react.views.text.internal.span.ShadowStyleSpan;
import com.facebook.react.views.text.internal.span.TextInlineImageSpan;
import com.facebook.react.views.text.internal.span.TextInlineViewPlaceholderSpan;
import com.facebook.react.views.textinput.ReactTextInputShadowNode;
import com.facebook.yoga.YogaDirection;
import com.facebook.yoga.YogaUnit;
import com.facebook.yoga.YogaValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactBaseTextShadowNode.kt */
/* JADX INFO: loaded from: classes.dex */
@Deprecated(message = "This class is part of Legacy Architecture and will be removed in a future release")
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u0007\n\u0002\b\u0016\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\b'\u0018\u0000 \u0091\u00012\u00020\u0001:\u0002\u0091\u0001B\u0015\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J,\u0010i\u001a\u00020j2\u0006\u0010k\u001a\u00020\u00002\b\u0010l\u001a\u0004\u0018\u00010?2\u0006\u0010m\u001a\u00020\u00102\b\u0010n\u001a\u0004\u0018\u00010oH\u0004J\u0010\u0010p\u001a\u00020q2\u0006\u0010,\u001a\u00020\u0015H\u0007J\u0010\u0010r\u001a\u00020q2\u0006\u0010s\u001a\u00020LH\u0007J\u0010\u0010t\u001a\u00020q2\u0006\u0010u\u001a\u00020LH\u0007J\u0010\u0010v\u001a\u00020q2\u0006\u0010w\u001a\u00020\u0010H\u0007J\u0010\u0010x\u001a\u00020q2\u0006\u0010y\u001a\u00020LH\u0007J\u0012\u0010z\u001a\u00020q2\b\u00107\u001a\u0004\u0018\u00010?H\u0007J\u0010\u0010{\u001a\u00020q2\u0006\u0010|\u001a\u00020LH\u0007J\u0017\u0010\u0018\u001a\u00020q2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007¢\u0006\u0002\u0010}J\u0017\u0010\u001e\u001a\u00020q2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0007¢\u0006\u0002\u0010}J\u0012\u0010#\u001a\u00020q2\b\u0010\u001f\u001a\u0004\u0018\u00010?H\u0007J\u0012\u0010)\u001a\u00020q2\b\u0010%\u001a\u0004\u0018\u00010?H\u0007J\u0012\u0010~\u001a\u00020q2\b\u0010@\u001a\u0004\u0018\u00010?H\u0007J\u0012\u0010>\u001a\u00020q2\b\u0010\u007f\u001a\u0004\u0018\u00010?H\u0007J\u0015\u0010\u0080\u0001\u001a\u00020q2\n\u0010\u0081\u0001\u001a\u0005\u0018\u00010\u0082\u0001H\u0007J\u0013\u0010;\u001a\u00020q2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010?H\u0007J\u0012\u0010\u0084\u0001\u001a\u00020q2\u0007\u0010\u0085\u0001\u001a\u00020\u0010H\u0007J\u0014\u0010\u0086\u0001\u001a\u00020q2\t\u0010\u0087\u0001\u001a\u0004\u0018\u00010?H\u0007J\u0012\u00100\u001a\u00020q2\b\u0010.\u001a\u0004\u0018\u00010?H\u0017J\u0015\u0010\u0088\u0001\u001a\u00020q2\n\u0010\u0089\u0001\u001a\u0005\u0018\u00010\u008a\u0001H\u0007J\u0011\u0010\u008b\u0001\u001a\u00020q2\u0006\u0010W\u001a\u00020LH\u0007J\u0011\u0010\u008c\u0001\u001a\u00020q2\u0006\u0010Y\u001a\u00020\u0015H\u0007J\u0014\u0010\u008d\u0001\u001a\u00020q2\t\u0010\u008e\u0001\u001a\u0004\u0018\u00010?H\u0007J\u0011\u0010\u008f\u0001\u001a\u00020q2\u0006\u0010I\u001a\u00020\u0010H\u0007J\u0011\u0010\u0090\u0001\u001a\u00020q2\u0006\u0010M\u001a\u00020LH\u0007R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005R\u001a\u0010\t\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0010X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0011\"\u0004\b\u001b\u0010\u0013R\u001a\u0010\u001c\u001a\u00020\u0015X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0017\"\u0004\b\u001e\u0010\u0019R\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010%\u001a\u0004\u0018\u00010&X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001e\u0010,\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u0015@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0017R\u001a\u0010.\u001a\u00020\u0015X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0017\"\u0004\b0\u0010\u0019R\u001a\u00101\u001a\u00020\u0015X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0017\"\u0004\b3\u0010\u0019R\u001a\u00104\u001a\u00020\u0015X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u0017\"\u0004\b6\u0010\u0019R \u00107\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u00158D@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u0017R\u001a\u00109\u001a\u00020\u0015X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u0017\"\u0004\b;\u0010\u0019R\u001a\u0010<\u001a\u00020\u0015X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u0017\"\u0004\b>\u0010\u0019R\"\u0010@\u001a\u0004\u0018\u00010?2\b\u0010+\u001a\u0004\u0018\u00010?@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\bA\u0010BR\u001c\u0010C\u001a\u0004\u0018\u00010?X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010B\"\u0004\bE\u0010FR\u001e\u0010G\u001a\u00020\u00102\u0006\u0010+\u001a\u00020\u0010@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\bH\u0010\u0011R\u001a\u0010I\u001a\u00020\u0010X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\u0011\"\u0004\bK\u0010\u0013R\u001e\u0010M\u001a\u00020L2\u0006\u0010+\u001a\u00020L@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\bN\u0010OR\u001a\u0010P\u001a\u00020LX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010O\"\u0004\bR\u0010SR\u001a\u0010T\u001a\u00020LX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010O\"\u0004\bV\u0010SR\u001e\u0010W\u001a\u00020L2\u0006\u0010+\u001a\u00020L@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\bX\u0010OR\u001e\u0010Y\u001a\u00020\u00152\u0006\u0010+\u001a\u00020\u0015@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010\u0017R\u001a\u0010[\u001a\u00020\u0010X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010\u0011\"\u0004\b\\\u0010\u0013R\u001a\u0010]\u001a\u00020\u0010X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b]\u0010\u0011\"\u0004\b^\u0010\u0013R\u001a\u0010_\u001a\u00020\u0010X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010\u0011\"\u0004\ba\u0010\u0013R,\u0010b\u001a\u0014\u0012\u0004\u0012\u00020\u0015\u0012\b\u0012\u0006\u0012\u0002\b\u00030d\u0018\u00010cX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010f\"\u0004\bg\u0010h¨\u0006\u0092\u0001"}, d2 = {"Lcom/facebook/react/views/text/ReactBaseTextShadowNode;", "Lcom/facebook/react/uimanager/LayoutShadowNode;", "reactTextViewManagerCallback", "Lcom/facebook/react/views/text/ReactTextViewManagerCallback;", "<init>", "(Lcom/facebook/react/views/text/ReactTextViewManagerCallback;)V", "getReactTextViewManagerCallback", "()Lcom/facebook/react/views/text/ReactTextViewManagerCallback;", "setReactTextViewManagerCallback", "textAttributes", "Lcom/facebook/react/views/text/TextAttributes;", "getTextAttributes", "()Lcom/facebook/react/views/text/TextAttributes;", "setTextAttributes", "(Lcom/facebook/react/views/text/TextAttributes;)V", "isColorSet", "", "()Z", "setColorSet", "(Z)V", "color", "", "getColor", "()I", "setColor", "(I)V", "isBackgroundColorSet", "setBackgroundColorSet", ViewProps.BACKGROUND_COLOR, "getBackgroundColor", "setBackgroundColor", ViewProps.ACCESSIBILITY_ROLE, "Lcom/facebook/react/uimanager/ReactAccessibilityDelegate$AccessibilityRole;", "getAccessibilityRole", "()Lcom/facebook/react/uimanager/ReactAccessibilityDelegate$AccessibilityRole;", "setAccessibilityRole", "(Lcom/facebook/react/uimanager/ReactAccessibilityDelegate$AccessibilityRole;)V", ViewProps.ROLE, "Lcom/facebook/react/uimanager/ReactAccessibilityDelegate$Role;", "getRole", "()Lcom/facebook/react/uimanager/ReactAccessibilityDelegate$Role;", "setRole", "(Lcom/facebook/react/uimanager/ReactAccessibilityDelegate$Role;)V", "value", ViewProps.NUMBER_OF_LINES, "getNumberOfLines", ViewProps.TEXT_BREAK_STRATEGY, "getTextBreakStrategy", "setTextBreakStrategy", "hyphenationFrequency", "getHyphenationFrequency", "setHyphenationFrequency", "justificationMode", "getJustificationMode", "setJustificationMode", ViewProps.TEXT_ALIGN, "getTextAlign", ViewProps.FONT_STYLE, "getFontStyle", "setFontStyle", ViewProps.FONT_WEIGHT, "getFontWeight", "setFontWeight", "", ViewProps.FONT_FAMILY, "getFontFamily", "()Ljava/lang/String;", "fontFeatureSettings", "getFontFeatureSettings", "setFontFeatureSettings", "(Ljava/lang/String;)V", ViewProps.INCLUDE_FONT_PADDING, "getIncludeFontPadding", ViewProps.ADJUSTS_FONT_SIZE_TO_FIT, "getAdjustsFontSizeToFit", "setAdjustsFontSizeToFit", "", ViewProps.MINIMUM_FONT_SCALE, "getMinimumFontScale", "()F", "textShadowOffsetDx", "getTextShadowOffsetDx", "setTextShadowOffsetDx", "(F)V", "textShadowOffsetDy", "getTextShadowOffsetDy", "setTextShadowOffsetDy", ReactBaseTextShadowNode.PROP_SHADOW_RADIUS, "getTextShadowRadius", ReactBaseTextShadowNode.PROP_SHADOW_COLOR, "getTextShadowColor", "isUnderlineTextDecorationSet", "setUnderlineTextDecorationSet", "isLineThroughTextDecorationSet", "setLineThroughTextDecorationSet", "containsImages", "getContainsImages", "setContainsImages", "inlineViews", "", "Lcom/facebook/react/uimanager/ReactShadowNode;", "getInlineViews", "()Ljava/util/Map;", "setInlineViews", "(Ljava/util/Map;)V", "spannedFromShadowNode", "Landroid/text/Spannable;", "textShadowNode", ReactTextInputShadowNode.PROP_TEXT, "supportsInlineViews", "nativeViewHierarchyOptimizer", "Lcom/facebook/react/uimanager/NativeViewHierarchyOptimizer;", "setNumberOfLines", "", "setLineHeight", ViewProps.LINE_HEIGHT, "setLetterSpacing", ViewProps.LETTER_SPACING, "setAllowFontScaling", ViewProps.ALLOW_FONT_SCALING, "setMaxFontSizeMultiplier", ViewProps.MAX_FONT_SIZE_MULTIPLIER, "setTextAlign", "setFontSize", ViewProps.FONT_SIZE, "(Ljava/lang/Integer;)V", "setFontFamily", "fontWeightString", "setFontVariant", "fontVariantArray", "Lcom/facebook/react/bridge/ReadableArray;", "fontStyleString", "setIncludeFontPadding", "includepad", "setTextDecorationLine", "textDecorationLineString", "setTextShadowOffset", "offsetMap", "Lcom/facebook/react/bridge/ReadableMap;", "setTextShadowRadius", "setTextShadowColor", "setTextTransform", ReactBaseTextShadowNode.PROP_TEXT_TRANSFORM, "setAdjustFontSizeToFit", "setMinimumFontScale", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class ReactBaseTextShadowNode extends LayoutShadowNode {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int DEFAULT_TEXT_SHADOW_COLOR = 1426063360;
    private static final String INLINE_VIEW_PLACEHOLDER = "0";
    public static final String PROP_SHADOW_COLOR = "textShadowColor";
    public static final String PROP_SHADOW_OFFSET = "textShadowOffset";
    public static final String PROP_SHADOW_OFFSET_HEIGHT = "height";
    public static final String PROP_SHADOW_OFFSET_WIDTH = "width";
    public static final String PROP_SHADOW_RADIUS = "textShadowRadius";
    public static final String PROP_TEXT_TRANSFORM = "textTransform";
    private ReactAccessibilityDelegate.AccessibilityRole accessibilityRole;
    private boolean adjustsFontSizeToFit;
    private int backgroundColor;
    private int color;
    private boolean containsImages;
    private String fontFamily;
    private String fontFeatureSettings;
    private int fontStyle;
    private int fontWeight;
    private int hyphenationFrequency;
    private boolean includeFontPadding;
    private Map<Integer, ? extends ReactShadowNode<?>> inlineViews;
    private boolean isBackgroundColorSet;
    private boolean isColorSet;
    private boolean isLineThroughTextDecorationSet;
    private boolean isUnderlineTextDecorationSet;
    private int justificationMode;
    private float minimumFontScale;
    private int numberOfLines;
    private ReactTextViewManagerCallback reactTextViewManagerCallback;
    private ReactAccessibilityDelegate.Role role;
    private int textAlign;
    private TextAttributes textAttributes;
    private int textBreakStrategy;
    private int textShadowColor;
    private float textShadowOffsetDx;
    private float textShadowOffsetDy;
    private float textShadowRadius;

    public ReactBaseTextShadowNode() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public /* synthetic */ ReactBaseTextShadowNode(ReactTextViewManagerCallback reactTextViewManagerCallback, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : reactTextViewManagerCallback);
    }

    protected final ReactTextViewManagerCallback getReactTextViewManagerCallback() {
        return this.reactTextViewManagerCallback;
    }

    protected final void setReactTextViewManagerCallback(ReactTextViewManagerCallback reactTextViewManagerCallback) {
        this.reactTextViewManagerCallback = reactTextViewManagerCallback;
    }

    public ReactBaseTextShadowNode(ReactTextViewManagerCallback reactTextViewManagerCallback) {
        this.reactTextViewManagerCallback = reactTextViewManagerCallback;
        this.textAttributes = new TextAttributes();
        this.numberOfLines = -1;
        this.textBreakStrategy = 1;
        this.justificationMode = 0;
        this.fontStyle = -1;
        this.fontWeight = -1;
        this.includeFontPadding = true;
        this.textShadowColor = DEFAULT_TEXT_SHADOW_COLOR;
    }

    protected final TextAttributes getTextAttributes() {
        return this.textAttributes;
    }

    protected final void setTextAttributes(TextAttributes textAttributes) {
        Intrinsics.checkNotNullParameter(textAttributes, "<set-?>");
        this.textAttributes = textAttributes;
    }

    /* JADX INFO: renamed from: isColorSet, reason: from getter */
    protected final boolean getIsColorSet() {
        return this.isColorSet;
    }

    protected final void setColorSet(boolean z) {
        this.isColorSet = z;
    }

    protected final int getColor() {
        return this.color;
    }

    protected final void setColor(int i) {
        this.color = i;
    }

    /* JADX INFO: renamed from: isBackgroundColorSet, reason: from getter */
    protected final boolean getIsBackgroundColorSet() {
        return this.isBackgroundColorSet;
    }

    protected final void setBackgroundColorSet(boolean z) {
        this.isBackgroundColorSet = z;
    }

    protected final int getBackgroundColor() {
        return this.backgroundColor;
    }

    protected final void setBackgroundColor(int i) {
        this.backgroundColor = i;
    }

    protected final ReactAccessibilityDelegate.AccessibilityRole getAccessibilityRole() {
        return this.accessibilityRole;
    }

    protected final void setAccessibilityRole(ReactAccessibilityDelegate.AccessibilityRole accessibilityRole) {
        this.accessibilityRole = accessibilityRole;
    }

    protected final ReactAccessibilityDelegate.Role getRole() {
        return this.role;
    }

    protected final void setRole(ReactAccessibilityDelegate.Role role) {
        this.role = role;
    }

    protected final int getNumberOfLines() {
        return this.numberOfLines;
    }

    protected final int getTextBreakStrategy() {
        return this.textBreakStrategy;
    }

    protected final void setTextBreakStrategy(int i) {
        this.textBreakStrategy = i;
    }

    protected final int getHyphenationFrequency() {
        return this.hyphenationFrequency;
    }

    protected final void setHyphenationFrequency(int i) {
        this.hyphenationFrequency = i;
    }

    protected final int getJustificationMode() {
        return this.justificationMode;
    }

    protected final void setJustificationMode(int i) {
        this.justificationMode = i;
    }

    protected final int getTextAlign() {
        if (getLayoutDirection() == YogaDirection.RTL) {
            int i = this.textAlign;
            if (i == 3) {
                return 5;
            }
            if (i != 5) {
                return i;
            }
            return 3;
        }
        return this.textAlign;
    }

    protected final int getFontStyle() {
        return this.fontStyle;
    }

    protected final void setFontStyle(int i) {
        this.fontStyle = i;
    }

    protected final int getFontWeight() {
        return this.fontWeight;
    }

    protected final void setFontWeight(int i) {
        this.fontWeight = i;
    }

    protected final String getFontFamily() {
        return this.fontFamily;
    }

    protected final String getFontFeatureSettings() {
        return this.fontFeatureSettings;
    }

    protected final void setFontFeatureSettings(String str) {
        this.fontFeatureSettings = str;
    }

    protected final boolean getIncludeFontPadding() {
        return this.includeFontPadding;
    }

    protected final boolean getAdjustsFontSizeToFit() {
        return this.adjustsFontSizeToFit;
    }

    protected final void setAdjustsFontSizeToFit(boolean z) {
        this.adjustsFontSizeToFit = z;
    }

    protected final float getMinimumFontScale() {
        return this.minimumFontScale;
    }

    protected final float getTextShadowOffsetDx() {
        return this.textShadowOffsetDx;
    }

    protected final void setTextShadowOffsetDx(float f) {
        this.textShadowOffsetDx = f;
    }

    protected final float getTextShadowOffsetDy() {
        return this.textShadowOffsetDy;
    }

    protected final void setTextShadowOffsetDy(float f) {
        this.textShadowOffsetDy = f;
    }

    protected final float getTextShadowRadius() {
        return this.textShadowRadius;
    }

    protected final int getTextShadowColor() {
        return this.textShadowColor;
    }

    /* JADX INFO: renamed from: isUnderlineTextDecorationSet, reason: from getter */
    protected final boolean getIsUnderlineTextDecorationSet() {
        return this.isUnderlineTextDecorationSet;
    }

    protected final void setUnderlineTextDecorationSet(boolean z) {
        this.isUnderlineTextDecorationSet = z;
    }

    /* JADX INFO: renamed from: isLineThroughTextDecorationSet, reason: from getter */
    protected final boolean getIsLineThroughTextDecorationSet() {
        return this.isLineThroughTextDecorationSet;
    }

    protected final void setLineThroughTextDecorationSet(boolean z) {
        this.isLineThroughTextDecorationSet = z;
    }

    protected final boolean getContainsImages() {
        return this.containsImages;
    }

    protected final void setContainsImages(boolean z) {
        this.containsImages = z;
    }

    protected final Map<Integer, ReactShadowNode<?>> getInlineViews() {
        return this.inlineViews;
    }

    protected final void setInlineViews(Map<Integer, ? extends ReactShadowNode<?>> map) {
        this.inlineViews = map;
    }

    protected final Spannable spannedFromShadowNode(ReactBaseTextShadowNode textShadowNode, String text, boolean supportsInlineViews, NativeViewHierarchyOptimizer nativeViewHierarchyOptimizer) {
        int i;
        Intrinsics.checkNotNullParameter(textShadowNode, "textShadowNode");
        if (supportsInlineViews && nativeViewHierarchyOptimizer == null) {
            throw new IllegalStateException("nativeViewHierarchyOptimizer is required when inline views are supported".toString());
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        ArrayList arrayList = new ArrayList();
        HashMap map = supportsInlineViews ? new HashMap() : null;
        if (text != null) {
            spannableStringBuilder.append((CharSequence) TextTransform.INSTANCE.apply(text, textShadowNode.textAttributes.textTransform));
        }
        INSTANCE.buildSpannedFromShadowNode(textShadowNode, spannableStringBuilder, arrayList, null, supportsInlineViews, map, 0);
        textShadowNode.containsImages = false;
        textShadowNode.inlineViews = map;
        int size = arrayList.size();
        float f = Float.NaN;
        for (int i2 = 0; i2 < size; i2++) {
            SetSpanOperation setSpanOperation = (SetSpanOperation) arrayList.get((arrayList.size() - i2) - 1);
            ReactSpan reactSpan = setSpanOperation.what;
            boolean z = reactSpan instanceof TextInlineImageSpan;
            if (z || (reactSpan instanceof TextInlineViewPlaceholderSpan)) {
                if (z) {
                    i = ((TextInlineImageSpan) reactSpan).get_height();
                    textShadowNode.containsImages = true;
                } else {
                    Intrinsics.checkNotNull(reactSpan, "null cannot be cast to non-null type com.facebook.react.views.text.internal.span.TextInlineViewPlaceholderSpan");
                    TextInlineViewPlaceholderSpan textInlineViewPlaceholderSpan = (TextInlineViewPlaceholderSpan) reactSpan;
                    int height = textInlineViewPlaceholderSpan.getHeight();
                    if (map == null) {
                        throw new IllegalStateException("Required value was null.".toString());
                    }
                    ReactShadowNode<?> reactShadowNode = map.get(Integer.valueOf(textInlineViewPlaceholderSpan.getReactTag()));
                    if (reactShadowNode == null) {
                        throw new IllegalStateException("Required value was null.".toString());
                    }
                    if (nativeViewHierarchyOptimizer == null) {
                        throw new IllegalStateException("Required value was null.".toString());
                    }
                    nativeViewHierarchyOptimizer.handleForceViewToBeNonLayoutOnly(reactShadowNode);
                    reactShadowNode.setLayoutParent(textShadowNode);
                    i = height;
                }
                if (Float.isNaN(f) || i > f) {
                    f = i;
                }
            }
            setSpanOperation.execute(spannableStringBuilder, i2);
        }
        textShadowNode.textAttributes.setHeightOfTallestInlineViewOrImage(f);
        ReactTextViewManagerCallback reactTextViewManagerCallback = this.reactTextViewManagerCallback;
        if (reactTextViewManagerCallback != null) {
            reactTextViewManagerCallback.onPostProcessSpannable(spannableStringBuilder);
        }
        return spannableStringBuilder;
    }

    @ReactProp(defaultInt = -1, name = ViewProps.NUMBER_OF_LINES)
    public final void setNumberOfLines(int numberOfLines) {
        if (numberOfLines == 0) {
            numberOfLines = -1;
        }
        this.numberOfLines = numberOfLines;
        markUpdated();
    }

    @ReactProp(defaultFloat = Float.NaN, name = ViewProps.LINE_HEIGHT)
    public final void setLineHeight(float lineHeight) {
        this.textAttributes.setLineHeight(lineHeight);
        markUpdated();
    }

    @ReactProp(defaultFloat = 0.0f, name = ViewProps.LETTER_SPACING)
    public final void setLetterSpacing(float letterSpacing) {
        this.textAttributes.setLetterSpacing(letterSpacing);
        markUpdated();
    }

    @ReactProp(defaultBoolean = true, name = ViewProps.ALLOW_FONT_SCALING)
    public final void setAllowFontScaling(boolean allowFontScaling) {
        if (allowFontScaling != this.textAttributes.getAllowFontScaling()) {
            this.textAttributes.setAllowFontScaling(allowFontScaling);
            markUpdated();
        }
    }

    @ReactProp(defaultFloat = Float.NaN, name = ViewProps.MAX_FONT_SIZE_MULTIPLIER)
    public final void setMaxFontSizeMultiplier(float maxFontSizeMultiplier) {
        if (maxFontSizeMultiplier == this.textAttributes.getMaxFontSizeMultiplier()) {
            return;
        }
        this.textAttributes.setMaxFontSizeMultiplier(maxFontSizeMultiplier);
        markUpdated();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004b, code lost:
    
        if (r6.equals("center") == false) goto L28;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004d  */
    @com.facebook.react.uimanager.annotations.ReactProp(name = com.facebook.react.uimanager.ViewProps.TEXT_ALIGN)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void setTextAlign(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.String r0 = "justify"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r6)
            r1 = 1
            r2 = 3
            r3 = 26
            if (r0 == 0) goto L15
            int r6 = android.os.Build.VERSION.SDK_INT
            if (r6 < r3) goto L12
            r5.justificationMode = r1
        L12:
            r5.textAlign = r2
            goto L64
        L15:
            int r0 = android.os.Build.VERSION.SDK_INT
            r4 = 0
            if (r0 < r3) goto L1c
            r5.justificationMode = r4
        L1c:
            if (r6 == 0) goto L61
            int r0 = r6.hashCode()
            switch(r0) {
                case -1364013995: goto L45;
                case 3005871: goto L3c;
                case 3317767: goto L31;
                case 108511772: goto L26;
                default: goto L25;
            }
        L25:
            goto L4d
        L26:
            java.lang.String r0 = "right"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L2f
            goto L4d
        L2f:
            r1 = 5
            goto L62
        L31:
            java.lang.String r0 = "left"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L3a
            goto L4d
        L3a:
            r1 = r2
            goto L62
        L3c:
            java.lang.String r0 = "auto"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L61
            goto L4d
        L45:
            java.lang.String r0 = "center"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L62
        L4d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Invalid textAlign: "
            r0.<init>(r1)
            java.lang.StringBuilder r6 = r0.append(r6)
            java.lang.String r6 = r6.toString()
            java.lang.String r0 = "ReactNative"
            com.facebook.common.logging.FLog.w(r0, r6)
        L61:
            r1 = r4
        L62:
            r5.textAlign = r1
        L64:
            r5.markUpdated()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.ReactBaseTextShadowNode.setTextAlign(java.lang.String):void");
    }

    @ReactProp(defaultFloat = Float.NaN, name = ViewProps.FONT_SIZE)
    public final void setFontSize(float fontSize) {
        this.textAttributes.setFontSize(fontSize);
        markUpdated();
    }

    @ReactProp(customType = "Color", name = "color")
    public final void setColor(Integer color) {
        if (color != null) {
            int iIntValue = color.intValue();
            this.isColorSet = true;
            this.color = iIntValue;
        }
        markUpdated();
    }

    @ReactProp(customType = "Color", name = ViewProps.BACKGROUND_COLOR)
    public final void setBackgroundColor(Integer color) {
        if (isVirtual()) {
            if (color != null) {
                int iIntValue = color.intValue();
                this.isBackgroundColorSet = true;
                this.backgroundColor = iIntValue;
            }
            markUpdated();
        }
    }

    @ReactProp(name = ViewProps.ACCESSIBILITY_ROLE)
    public final void setAccessibilityRole(String accessibilityRole) {
        if (isVirtual()) {
            this.accessibilityRole = ReactAccessibilityDelegate.AccessibilityRole.INSTANCE.fromValue(accessibilityRole);
            markUpdated();
        }
    }

    @ReactProp(name = ViewProps.ROLE)
    public final void setRole(String role) {
        if (isVirtual()) {
            this.role = ReactAccessibilityDelegate.Role.INSTANCE.fromValue(role);
            markUpdated();
        }
    }

    @ReactProp(name = ViewProps.FONT_FAMILY)
    public final void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
        markUpdated();
    }

    @ReactProp(name = ViewProps.FONT_WEIGHT)
    public final void setFontWeight(String fontWeightString) {
        int fontWeight = ReactTypefaceUtils.parseFontWeight(fontWeightString);
        if (fontWeight != this.fontWeight) {
            this.fontWeight = fontWeight;
            markUpdated();
        }
    }

    @ReactProp(name = ViewProps.FONT_VARIANT)
    public final void setFontVariant(ReadableArray fontVariantArray) {
        String fontVariant = ReactTypefaceUtils.parseFontVariant(fontVariantArray);
        if (Intrinsics.areEqual(fontVariant, this.fontFeatureSettings)) {
            return;
        }
        this.fontFeatureSettings = fontVariant;
        markUpdated();
    }

    @ReactProp(name = ViewProps.FONT_STYLE)
    public final void setFontStyle(String fontStyleString) {
        int fontStyle = ReactTypefaceUtils.parseFontStyle(fontStyleString);
        if (fontStyle != this.fontStyle) {
            this.fontStyle = fontStyle;
            markUpdated();
        }
    }

    @ReactProp(defaultBoolean = true, name = ViewProps.INCLUDE_FONT_PADDING)
    public final void setIncludeFontPadding(boolean includepad) {
        this.includeFontPadding = includepad;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0059  */
    @com.facebook.react.uimanager.annotations.ReactProp(name = com.facebook.react.uimanager.ViewProps.TEXT_DECORATION_LINE)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void setTextDecorationLine(java.lang.String r8) {
        /*
            r7 = this;
            r0 = 0
            r7.isUnderlineTextDecorationSet = r0
            r7.isLineThroughTextDecorationSet = r0
            if (r8 == 0) goto L74
            r1 = r8
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r8 = 1
            java.lang.String[] r2 = new java.lang.String[r8]
            java.lang.String r3 = " "
            r2[r0] = r3
            r5 = 6
            r6 = 0
            r3 = 0
            r4 = 0
            java.util.List r1 = kotlin.text.StringsKt.split$default(r1, r2, r3, r4, r5, r6)
            boolean r2 = r1.isEmpty()
            if (r2 != 0) goto L48
            int r2 = r1.size()
            java.util.ListIterator r2 = r1.listIterator(r2)
        L27:
            boolean r3 = r2.hasPrevious()
            if (r3 == 0) goto L48
            java.lang.Object r3 = r2.previous()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            int r3 = r3.length()
            if (r3 != 0) goto L3c
            goto L27
        L3c:
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            int r2 = r2.nextIndex()
            int r2 = r2 + r8
            java.util.List r1 = kotlin.collections.CollectionsKt.take(r1, r2)
            goto L4c
        L48:
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
        L4c:
            java.util.Collection r1 = (java.util.Collection) r1
            java.lang.String[] r2 = new java.lang.String[r0]
            java.lang.Object[] r1 = r1.toArray(r2)
            java.lang.String[] r1 = (java.lang.String[]) r1
            int r2 = r1.length
        L57:
            if (r0 >= r2) goto L74
            r3 = r1[r0]
            java.lang.String r4 = "underline"
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r3)
            if (r4 == 0) goto L67
            r7.isUnderlineTextDecorationSet = r8
            goto L71
        L67:
            java.lang.String r4 = "line-through"
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r3)
            if (r3 == 0) goto L71
            r7.isLineThroughTextDecorationSet = r8
        L71:
            int r0 = r0 + 1
            goto L57
        L74:
            r7.markUpdated()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.ReactBaseTextShadowNode.setTextDecorationLine(java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0033  */
    @com.facebook.react.uimanager.annotations.ReactProp(name = com.facebook.react.uimanager.ViewProps.TEXT_BREAK_STRATEGY)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setTextBreakStrategy(java.lang.String r4) {
        /*
            r3 = this;
            r0 = 1
            if (r4 == 0) goto L49
            int r1 = r4.hashCode()
            r2 = -1924829944(0xffffffff8d456d08, float:-6.0836553E-31)
            if (r1 == r2) goto L2b
            r2 = -902286926(0xffffffffca3831b2, float:-3017836.5)
            if (r1 == r2) goto L20
            r2 = 336871677(0x141440fd, float:7.484907E-27)
            if (r1 == r2) goto L17
            goto L33
        L17:
            java.lang.String r1 = "highQuality"
            boolean r1 = r4.equals(r1)
            if (r1 != 0) goto L49
            goto L33
        L20:
            java.lang.String r1 = "simple"
            boolean r1 = r4.equals(r1)
            if (r1 != 0) goto L29
            goto L33
        L29:
            r0 = 0
            goto L49
        L2b:
            java.lang.String r1 = "balanced"
            boolean r1 = r4.equals(r1)
            if (r1 != 0) goto L48
        L33:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Invalid textBreakStrategy: "
            r1.<init>(r2)
            java.lang.StringBuilder r4 = r1.append(r4)
            java.lang.String r4 = r4.toString()
            java.lang.String r1 = "ReactNative"
            com.facebook.common.logging.FLog.w(r1, r4)
            goto L49
        L48:
            r0 = 2
        L49:
            r3.textBreakStrategy = r0
            r3.markUpdated()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.ReactBaseTextShadowNode.setTextBreakStrategy(java.lang.String):void");
    }

    @ReactProp(name = PROP_SHADOW_OFFSET)
    public final void setTextShadowOffset(ReadableMap offsetMap) {
        this.textShadowOffsetDx = 0.0f;
        this.textShadowOffsetDy = 0.0f;
        if (offsetMap != null) {
            if (offsetMap.hasKey("width") && !offsetMap.isNull("width")) {
                this.textShadowOffsetDx = PixelUtil.toPixelFromDIP(offsetMap.getDouble("width"));
            }
            if (offsetMap.hasKey("height") && !offsetMap.isNull("height")) {
                this.textShadowOffsetDy = PixelUtil.toPixelFromDIP(offsetMap.getDouble("height"));
            }
        }
        markUpdated();
    }

    @ReactProp(defaultInt = 1, name = PROP_SHADOW_RADIUS)
    public final void setTextShadowRadius(float textShadowRadius) {
        if (textShadowRadius == this.textShadowRadius) {
            return;
        }
        this.textShadowRadius = textShadowRadius;
        markUpdated();
    }

    @ReactProp(customType = "Color", defaultInt = DEFAULT_TEXT_SHADOW_COLOR, name = PROP_SHADOW_COLOR)
    public final void setTextShadowColor(int textShadowColor) {
        if (textShadowColor != this.textShadowColor) {
            this.textShadowColor = textShadowColor;
            markUpdated();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:22:0x003b  */
    @com.facebook.react.uimanager.annotations.ReactProp(name = com.facebook.react.views.text.ReactBaseTextShadowNode.PROP_TEXT_TRANSFORM)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void setTextTransform(java.lang.String r3) {
        /*
            r2 = this;
            if (r3 == 0) goto L52
            int r0 = r3.hashCode()
            switch(r0) {
                case -1765638420: goto L2f;
                case -514507343: goto L23;
                case 3387192: goto L17;
                case 223523538: goto La;
                default: goto L9;
            }
        L9:
            goto L3b
        La:
            java.lang.String r0 = "uppercase"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L14
            goto L3b
        L14:
            com.facebook.react.views.text.TextTransform r3 = com.facebook.react.views.text.TextTransform.UPPERCASE
            goto L54
        L17:
            java.lang.String r0 = "none"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L20
            goto L3b
        L20:
            com.facebook.react.views.text.TextTransform r3 = com.facebook.react.views.text.TextTransform.NONE
            goto L54
        L23:
            java.lang.String r0 = "lowercase"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L2c
            goto L3b
        L2c:
            com.facebook.react.views.text.TextTransform r3 = com.facebook.react.views.text.TextTransform.LOWERCASE
            goto L54
        L2f:
            java.lang.String r0 = "capitalize"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L38
            goto L3b
        L38:
            com.facebook.react.views.text.TextTransform r3 = com.facebook.react.views.text.TextTransform.CAPITALIZE
            goto L54
        L3b:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Invalid textTransform: "
            r0.<init>(r1)
            java.lang.StringBuilder r3 = r0.append(r3)
            java.lang.String r3 = r3.toString()
            java.lang.String r0 = "ReactNative"
            com.facebook.common.logging.FLog.w(r0, r3)
            com.facebook.react.views.text.TextTransform r3 = com.facebook.react.views.text.TextTransform.UNSET
            goto L54
        L52:
            com.facebook.react.views.text.TextTransform r3 = com.facebook.react.views.text.TextTransform.UNSET
        L54:
            com.facebook.react.views.text.TextAttributes r0 = r2.textAttributes
            r0.textTransform = r3
            r2.markUpdated()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.ReactBaseTextShadowNode.setTextTransform(java.lang.String):void");
    }

    @ReactProp(name = ViewProps.ADJUSTS_FONT_SIZE_TO_FIT)
    public final void setAdjustFontSizeToFit(boolean adjustsFontSizeToFit) {
        if (adjustsFontSizeToFit != this.adjustsFontSizeToFit) {
            this.adjustsFontSizeToFit = adjustsFontSizeToFit;
            markUpdated();
        }
    }

    @ReactProp(name = ViewProps.MINIMUM_FONT_SCALE)
    public final void setMinimumFontScale(float minimumFontScale) {
        if (minimumFontScale == this.minimumFontScale) {
            return;
        }
        this.minimumFontScale = minimumFontScale;
        markUpdated();
    }

    /* JADX INFO: compiled from: ReactBaseTextShadowNode.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JZ\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0018\u0010\u001b\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001d\u0018\u00010\u001c2\u0006\u0010\u001e\u001a\u00020\rH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/facebook/react/views/text/ReactBaseTextShadowNode$Companion;", "", "<init>", "()V", "INLINE_VIEW_PLACEHOLDER", "", "PROP_SHADOW_OFFSET", "PROP_SHADOW_OFFSET_WIDTH", "PROP_SHADOW_OFFSET_HEIGHT", "PROP_SHADOW_RADIUS", "PROP_SHADOW_COLOR", "PROP_TEXT_TRANSFORM", "DEFAULT_TEXT_SHADOW_COLOR", "", "buildSpannedFromShadowNode", "", "textShadowNode", "Lcom/facebook/react/views/text/ReactBaseTextShadowNode;", "sb", "Landroid/text/SpannableStringBuilder;", "ops", "", "Lcom/facebook/react/views/text/internal/span/SetSpanOperation;", "parentTextAttributes", "Lcom/facebook/react/views/text/TextAttributes;", "supportsInlineViews", "", "inlineViews", "", "Lcom/facebook/react/uimanager/ReactShadowNode;", ViewProps.START, "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void buildSpannedFromShadowNode(ReactBaseTextShadowNode textShadowNode, SpannableStringBuilder sb, List<SetSpanOperation> ops, TextAttributes parentTextAttributes, boolean supportsInlineViews, Map<Integer, ReactShadowNode<?>> inlineViews, int start) {
            TextAttributes textAttributes;
            float layoutWidth;
            float layoutHeight;
            if (parentTextAttributes == null || (textAttributes = parentTextAttributes.applyChild(textShadowNode.getTextAttributes())) == null) {
                textAttributes = textShadowNode.getTextAttributes();
            }
            TextAttributes textAttributes2 = textAttributes;
            int childCount = textShadowNode.getChildCount();
            for (int i = 0; i < childCount; i++) {
                ReactShadowNodeImpl childAt = textShadowNode.getChildAt(i);
                Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(...)");
                ReactShadowNodeImpl reactShadowNodeImpl = childAt;
                if (reactShadowNodeImpl instanceof ReactBaseTextShadowNode) {
                    buildSpannedFromShadowNode((ReactBaseTextShadowNode) reactShadowNodeImpl, sb, ops, textAttributes2, supportsInlineViews, inlineViews, sb.length());
                } else if (reactShadowNodeImpl instanceof ReactTextInlineImageShadowNode) {
                    sb.append(ReactBaseTextShadowNode.INLINE_VIEW_PLACEHOLDER);
                    ops.add(new SetSpanOperation(sb.length() - 1, sb.length(), ((ReactTextInlineImageShadowNode) reactShadowNodeImpl).buildInlineImageSpan()));
                } else if (supportsInlineViews) {
                    int reactTag = reactShadowNodeImpl.getReactTag();
                    YogaValue styleWidth = reactShadowNodeImpl.getStyleWidth();
                    YogaValue styleHeight = reactShadowNodeImpl.getStyleHeight();
                    if (styleWidth.unit != YogaUnit.POINT || styleHeight.unit != YogaUnit.POINT) {
                        reactShadowNodeImpl.calculateLayout();
                        layoutWidth = reactShadowNodeImpl.getLayoutWidth();
                        layoutHeight = reactShadowNodeImpl.getLayoutHeight();
                    } else {
                        layoutWidth = styleWidth.value;
                        layoutHeight = styleHeight.value;
                    }
                    sb.append(ReactBaseTextShadowNode.INLINE_VIEW_PLACEHOLDER);
                    ops.add(new SetSpanOperation(sb.length() - 1, sb.length(), new TextInlineViewPlaceholderSpan(reactTag, (int) layoutWidth, (int) layoutHeight)));
                    if (inlineViews != null) {
                        inlineViews.put(Integer.valueOf(reactTag), reactShadowNodeImpl);
                    } else {
                        throw new IllegalStateException("Required value was null.".toString());
                    }
                } else {
                    throw new IllegalViewOperationException("Unexpected view type nested under a <Text> or <TextInput> node: " + reactShadowNodeImpl.getClass());
                }
                reactShadowNodeImpl.markUpdateSeen();
            }
            int length = sb.length();
            if (length >= start) {
                if (textShadowNode.getIsColorSet()) {
                    ops.add(new SetSpanOperation(start, length, new ReactForegroundColorSpan(textShadowNode.getColor())));
                }
                if (textShadowNode.getIsBackgroundColorSet()) {
                    ops.add(new SetSpanOperation(start, length, new ReactBackgroundColorSpan(textShadowNode.getBackgroundColor())));
                }
                if (textShadowNode.getRole() == null ? textShadowNode.getAccessibilityRole() == ReactAccessibilityDelegate.AccessibilityRole.LINK : textShadowNode.getRole() == ReactAccessibilityDelegate.Role.LINK) {
                    ops.add(new SetSpanOperation(start, length, new ReactClickableSpan(textShadowNode.getReactTag())));
                }
                float effectiveLetterSpacing = textAttributes2.getEffectiveLetterSpacing();
                if (!Float.isNaN(effectiveLetterSpacing) && (parentTextAttributes == null || parentTextAttributes.getEffectiveLetterSpacing() != effectiveLetterSpacing)) {
                    ops.add(new SetSpanOperation(start, length, new CustomLetterSpacingSpan(effectiveLetterSpacing)));
                }
                int effectiveFontSize = textAttributes2.getEffectiveFontSize();
                if (parentTextAttributes == null || parentTextAttributes.getEffectiveFontSize() != effectiveFontSize) {
                    ops.add(new SetSpanOperation(start, length, new ReactAbsoluteSizeSpan(effectiveFontSize)));
                }
                if (textShadowNode.getFontStyle() != -1 || textShadowNode.getFontWeight() != -1 || textShadowNode.getFontFamily() != null) {
                    int fontStyle = textShadowNode.getFontStyle();
                    int fontWeight = textShadowNode.getFontWeight();
                    String fontFeatureSettings = textShadowNode.getFontFeatureSettings();
                    String fontFamily = textShadowNode.getFontFamily();
                    AssetManager assets = textShadowNode.getThemedContext().getAssets();
                    Intrinsics.checkNotNullExpressionValue(assets, "getAssets(...)");
                    ops.add(new SetSpanOperation(start, length, new CustomStyleSpan(fontStyle, fontWeight, fontFeatureSettings, fontFamily, assets)));
                }
                if (textShadowNode.getIsUnderlineTextDecorationSet()) {
                    ops.add(new SetSpanOperation(start, length, new ReactUnderlineSpan()));
                }
                if (textShadowNode.getIsLineThroughTextDecorationSet()) {
                    ops.add(new SetSpanOperation(start, length, new ReactStrikethroughSpan()));
                }
                if ((textShadowNode.getTextShadowOffsetDx() != 0.0f || textShadowNode.getTextShadowOffsetDy() != 0.0f || textShadowNode.getTextShadowRadius() != 0.0f) && Color.alpha(textShadowNode.getTextShadowColor()) != 0) {
                    ops.add(new SetSpanOperation(start, length, new ShadowStyleSpan(textShadowNode.getTextShadowOffsetDx(), textShadowNode.getTextShadowOffsetDy(), textShadowNode.getTextShadowRadius(), textShadowNode.getTextShadowColor())));
                }
                float effectiveLineHeight = textAttributes2.getEffectiveLineHeight();
                if (!Float.isNaN(effectiveLineHeight) && (parentTextAttributes == null || parentTextAttributes.getEffectiveLineHeight() != effectiveLineHeight)) {
                    ops.add(new SetSpanOperation(start, length, new CustomLineHeightSpan(effectiveLineHeight)));
                }
                ops.add(new SetSpanOperation(start, length, new ReactTagSpan(textShadowNode.getReactTag())));
            }
        }
    }
}
