package com.facebook.react.views.text;

import android.os.Build;
import android.text.TextUtils;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.mapbuffer.MapBuffer;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactAccessibilityDelegate;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.ProfilingTraceData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* JADX INFO: compiled from: TextAttributeProps.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u0000 p2\u00020\u0001:\u0001pB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010A\u001a\u00020B2\u0006\u0010\u0019\u001a\u00020\u0018H\u0002J\u0010\u0010H\u001a\u00020B2\u0006\u0010\u001c\u001a\u00020\u0005H\u0002J\u0012\u0010R\u001a\u00020B2\b\u0010S\u001a\u0004\u0018\u00010TH\u0002J\u0012\u0010R\u001a\u00020B2\b\u0010S\u001a\u0004\u0018\u00010UH\u0002J\u0012\u0010V\u001a\u00020B2\b\u0010W\u001a\u0004\u0018\u000108H\u0002J\u0012\u0010X\u001a\u00020B2\b\u0010Y\u001a\u0004\u0018\u000108H\u0002J\u0012\u0010Z\u001a\u00020B2\b\u0010[\u001a\u0004\u0018\u000108H\u0002J\u0012\u0010\\\u001a\u00020B2\b\u0010]\u001a\u0004\u0018\u00010^H\u0002J\u0012\u0010e\u001a\u00020B2\b\u0010!\u001a\u0004\u0018\u000108H\u0002J\u0012\u0010m\u001a\u00020B2\b\u0010#\u001a\u0004\u0018\u000108H\u0002J\u0012\u0010n\u001a\u00020B2\b\u0010-\u001a\u0004\u0018\u000108H\u0002J\u0012\u0010o\u001a\u00020B2\b\u00101\u001a\u0004\u0018\u000108H\u0002J\u0010\u0010o\u001a\u00020B2\u0006\u00101\u001a\u000200H\u0002R$\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR$\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\r\"\u0004\b\u0010\u0010\u0011R$\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\b\"\u0004\b\u0014\u0010\nR\u001e\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\rR\u001e\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\bR\u001e\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0004\u001a\u00020\u0018@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001e\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u0004\u001a\u00020\u0018@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u000e\u0010\u001e\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010!\u001a\u00020\u00182\u0006\u0010\u0004\u001a\u00020\u0018@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001bR\u001a\u0010#\u001a\u00020$X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001e\u0010)\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\rR\u001e\u0010*\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\rR\u000e\u0010+\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010-\u001a\u0004\u0018\u00010,2\b\u0010\u0004\u001a\u0004\u0018\u00010,@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\"\u00101\u001a\u0004\u0018\u0001002\b\u0010\u0004\u001a\u0004\u0018\u000100@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u001e\u00104\u001a\u00020\u00182\u0006\u0010\u0004\u001a\u00020\u0018@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u001bR\u001e\u00106\u001a\u00020\u00182\u0006\u0010\u0004\u001a\u00020\u0018@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\u001bR\"\u00109\u001a\u0004\u0018\u0001082\b\u0010\u0004\u001a\u0004\u0018\u000108@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\"\u0010<\u001a\u0004\u0018\u0001082\b\u0010\u0004\u001a\u0004\u0018\u000108@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b=\u0010;R\u001a\u0010>\u001a\u00020\u00058FX\u0087\u0004¢\u0006\f\u0012\u0004\b?\u0010\u0003\u001a\u0004\b@\u0010\bR$\u0010C\u001a\u00020\u00052\u0006\u0010C\u001a\u00020\u00058F@BX\u0086\u000e¢\u0006\f\u001a\u0004\bD\u0010\b\"\u0004\bE\u0010\nR\u0011\u0010F\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\bG\u0010\bR*\u0010I\u001a\u0004\u0018\u00010\u00182\b\u0010\u0004\u001a\u0004\u0018\u00010\u0018@BX\u0086\u000e¢\u0006\u0010\n\u0002\u0010N\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR*\u0010O\u001a\u0004\u0018\u00010\u00182\b\u0010I\u001a\u0004\u0018\u00010\u0018@BX\u0086\u000e¢\u0006\u0010\n\u0002\u0010N\u001a\u0004\bP\u0010K\"\u0004\bQ\u0010MR$\u0010_\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010\b\"\u0004\ba\u0010\nR$\u0010b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010\b\"\u0004\bd\u0010\nR$\u0010f\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bg\u0010\b\"\u0004\bh\u0010\nR$\u0010i\u001a\u00020\u00182\u0006\u0010\u0004\u001a\u00020\u0018@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bj\u0010\u001b\"\u0004\bk\u0010l¨\u0006q"}, d2 = {"Lcom/facebook/react/views/text/TextAttributeProps;", "", "<init>", "()V", "value", "", ViewProps.LINE_HEIGHT, "getLineHeight", "()F", "setLineHeight", "(F)V", "", "isColorSet", "()Z", ViewProps.ALLOW_FONT_SCALING, "getAllowFontScaling", "setAllowFontScaling", "(Z)V", ViewProps.MAX_FONT_SIZE_MULTIPLIER, "getMaxFontSizeMultiplier", "setMaxFontSizeMultiplier", "isBackgroundColorSet", ViewProps.OPACITY, "getOpacity", "", ViewProps.NUMBER_OF_LINES, "getNumberOfLines", "()I", ViewProps.FONT_SIZE, "getFontSize", "fontSizeInput", "lineHeightInput", "letterSpacingInput", ViewProps.LAYOUT_DIRECTION, "getLayoutDirection", "textTransform", "Lcom/facebook/react/views/text/TextTransform;", "getTextTransform$ReactAndroid_release", "()Lcom/facebook/react/views/text/TextTransform;", "setTextTransform$ReactAndroid_release", "(Lcom/facebook/react/views/text/TextTransform;)V", "isUnderlineTextDecorationSet", "isLineThroughTextDecorationSet", ViewProps.INCLUDE_FONT_PADDING, "Lcom/facebook/react/uimanager/ReactAccessibilityDelegate$AccessibilityRole;", ViewProps.ACCESSIBILITY_ROLE, "getAccessibilityRole", "()Lcom/facebook/react/uimanager/ReactAccessibilityDelegate$AccessibilityRole;", "Lcom/facebook/react/uimanager/ReactAccessibilityDelegate$Role;", ViewProps.ROLE, "getRole", "()Lcom/facebook/react/uimanager/ReactAccessibilityDelegate$Role;", ViewProps.FONT_STYLE, "getFontStyle", ViewProps.FONT_WEIGHT, "getFontWeight", "", ViewProps.FONT_FAMILY, "getFontFamily", "()Ljava/lang/String;", "fontFeatureSettings", "getFontFeatureSettings", "effectiveLineHeight", "getEffectiveLineHeight$annotations", "getEffectiveLineHeight", "setNumberOfLines", "", ViewProps.LETTER_SPACING, "getLetterSpacing", "setLetterSpacing", "effectiveLetterSpacing", "getEffectiveLetterSpacing", "setFontSize", "color", "getColor", "()Ljava/lang/Integer;", "setColor", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", ViewProps.BACKGROUND_COLOR, "getBackgroundColor", "setBackgroundColor", "setFontVariant", ViewProps.FONT_VARIANT, "Lcom/facebook/react/bridge/ReadableArray;", "Lcom/facebook/react/common/mapbuffer/MapBuffer;", "setFontWeight", "fontWeightString", "setFontStyle", "fontStyleString", "setTextDecorationLine", "textDecorationLineString", "setTextShadowOffset", "offsetMap", "Lcom/facebook/react/bridge/ReadableMap;", "textShadowOffsetDx", "getTextShadowOffsetDx", "setTextShadowOffsetDx", "textShadowOffsetDy", "getTextShadowOffsetDy", "setTextShadowOffsetDy", "setLayoutDirection", "textShadowRadius", "getTextShadowRadius", "setTextShadowRadius", "textShadowColor", "getTextShadowColor", "setTextShadowColor", "(I)V", "setTextTransform", "setAccessibilityRole", "setRole", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TextAttributeProps {
    private static final int DEFAULT_BREAK_STRATEGY = 1;
    private static final int DEFAULT_HYPHENATION_FREQUENCY = 0;
    private static final int DEFAULT_TEXT_SHADOW_COLOR = 1426063360;
    private static final String PROP_SHADOW_COLOR = "textShadowColor";
    private static final String PROP_SHADOW_OFFSET = "textShadowOffset";
    private static final String PROP_SHADOW_OFFSET_HEIGHT = "height";
    private static final String PROP_SHADOW_OFFSET_WIDTH = "width";
    private static final String PROP_SHADOW_RADIUS = "textShadowRadius";
    private static final String PROP_TEXT_TRANSFORM = "textTransform";
    public static final int TA_KEY_ACCESSIBILITY_ROLE = 24;
    public static final int TA_KEY_ALIGNMENT = 12;
    public static final int TA_KEY_ALLOW_FONT_SCALING = 9;
    public static final int TA_KEY_BACKGROUND_COLOR = 1;
    public static final int TA_KEY_BEST_WRITING_DIRECTION = 13;
    public static final int TA_KEY_FONT_FAMILY = 3;
    public static final int TA_KEY_FONT_SIZE = 4;
    public static final int TA_KEY_FONT_SIZE_MULTIPLIER = 5;
    public static final int TA_KEY_FONT_STYLE = 7;
    public static final int TA_KEY_FONT_VARIANT = 8;
    public static final int TA_KEY_FONT_WEIGHT = 6;
    public static final int TA_KEY_FOREGROUND_COLOR = 0;
    public static final int TA_KEY_IS_HIGHLIGHTED = 22;
    public static final int TA_KEY_LAYOUT_DIRECTION = 23;
    public static final int TA_KEY_LETTER_SPACING = 10;
    public static final int TA_KEY_LINE_BREAK_STRATEGY = 25;
    public static final int TA_KEY_LINE_HEIGHT = 11;
    public static final int TA_KEY_MAX_FONT_SIZE_MULTIPLIER = 29;
    public static final int TA_KEY_OPACITY = 2;
    public static final int TA_KEY_ROLE = 26;
    public static final int TA_KEY_TEXT_DECORATION_COLOR = 14;
    public static final int TA_KEY_TEXT_DECORATION_LINE = 15;
    public static final int TA_KEY_TEXT_DECORATION_STYLE = 16;
    public static final int TA_KEY_TEXT_SHADOW_COLOR = 19;
    public static final int TA_KEY_TEXT_SHADOW_OFFSET_DX = 20;
    public static final int TA_KEY_TEXT_SHADOW_OFFSET_DY = 21;
    public static final int TA_KEY_TEXT_SHADOW_RADIUS = 18;
    public static final int TA_KEY_TEXT_TRANSFORM = 27;
    public static final int UNSET = -1;
    private ReactAccessibilityDelegate.AccessibilityRole accessibilityRole;
    private boolean allowFontScaling;
    private Integer backgroundColor;
    private Integer color;
    private String fontFamily;
    private String fontFeatureSettings;
    private int fontSize;
    private float fontSizeInput;
    private int fontStyle;
    private int fontWeight;
    private boolean includeFontPadding;
    private boolean isBackgroundColorSet;
    private boolean isColorSet;
    private boolean isLineThroughTextDecorationSet;
    private boolean isUnderlineTextDecorationSet;
    private int layoutDirection;
    private float letterSpacingInput;
    private float lineHeight;
    private float lineHeightInput;
    private float maxFontSizeMultiplier;
    private int numberOfLines;
    private float opacity;
    private ReactAccessibilityDelegate.Role role;
    private int textShadowColor;
    private float textShadowOffsetDx;
    private float textShadowOffsetDy;
    private float textShadowRadius;
    private TextTransform textTransform;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int DEFAULT_JUSTIFICATION_MODE = 0;

    public /* synthetic */ TextAttributeProps(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Deprecated(message = "Use lineHeight instead", replaceWith = @ReplaceWith(expression = ViewProps.LINE_HEIGHT, imports = {}))
    public static /* synthetic */ void getEffectiveLineHeight$annotations() {
    }

    private TextAttributeProps() {
        this.lineHeight = Float.NaN;
        this.allowFontScaling = true;
        this.maxFontSizeMultiplier = Float.NaN;
        this.opacity = Float.NaN;
        this.numberOfLines = -1;
        this.fontSize = -1;
        this.fontSizeInput = -1.0f;
        this.lineHeightInput = -1.0f;
        this.letterSpacingInput = Float.NaN;
        this.layoutDirection = -1;
        this.textTransform = TextTransform.NONE;
        this.includeFontPadding = true;
        this.fontStyle = -1;
        this.fontWeight = -1;
        this.backgroundColor = 0;
        this.textShadowColor = 1426063360;
    }

    public final float getLineHeight() {
        return this.lineHeight;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLineHeight(float f) {
        float pixelFromSP$default;
        this.lineHeightInput = f;
        if (f == -1.0f) {
            pixelFromSP$default = Float.NaN;
        } else {
            pixelFromSP$default = this.allowFontScaling ? PixelUtil.toPixelFromSP$default(f, 0.0f, 2, null) : PixelUtil.toPixelFromDIP(f);
        }
        this.lineHeight = pixelFromSP$default;
    }

    /* JADX INFO: renamed from: isColorSet, reason: from getter */
    public final boolean getIsColorSet() {
        return this.isColorSet;
    }

    public final boolean getAllowFontScaling() {
        return this.allowFontScaling;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAllowFontScaling(boolean z) {
        if (z != this.allowFontScaling) {
            this.allowFontScaling = z;
            setFontSize(this.fontSizeInput);
            setLineHeight(this.lineHeightInput);
        }
    }

    public final float getMaxFontSizeMultiplier() {
        return this.maxFontSizeMultiplier;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setMaxFontSizeMultiplier(float f) {
        if (f == this.maxFontSizeMultiplier) {
            return;
        }
        this.maxFontSizeMultiplier = f;
        setFontSize(this.fontSizeInput);
        setLineHeight(this.lineHeightInput);
    }

    /* JADX INFO: renamed from: isBackgroundColorSet, reason: from getter */
    public final boolean getIsBackgroundColorSet() {
        return this.isBackgroundColorSet;
    }

    public final float getOpacity() {
        return this.opacity;
    }

    public final int getNumberOfLines() {
        return this.numberOfLines;
    }

    public final int getFontSize() {
        return this.fontSize;
    }

    public final int getLayoutDirection() {
        return this.layoutDirection;
    }

    /* JADX INFO: renamed from: getTextTransform$ReactAndroid_release, reason: from getter */
    public final TextTransform getTextTransform() {
        return this.textTransform;
    }

    public final void setTextTransform$ReactAndroid_release(TextTransform textTransform) {
        Intrinsics.checkNotNullParameter(textTransform, "<set-?>");
        this.textTransform = textTransform;
    }

    /* JADX INFO: renamed from: isUnderlineTextDecorationSet, reason: from getter */
    public final boolean getIsUnderlineTextDecorationSet() {
        return this.isUnderlineTextDecorationSet;
    }

    /* JADX INFO: renamed from: isLineThroughTextDecorationSet, reason: from getter */
    public final boolean getIsLineThroughTextDecorationSet() {
        return this.isLineThroughTextDecorationSet;
    }

    public final ReactAccessibilityDelegate.AccessibilityRole getAccessibilityRole() {
        return this.accessibilityRole;
    }

    public final ReactAccessibilityDelegate.Role getRole() {
        return this.role;
    }

    public final int getFontStyle() {
        return this.fontStyle;
    }

    public final int getFontWeight() {
        return this.fontWeight;
    }

    public final String getFontFamily() {
        return this.fontFamily;
    }

    public final String getFontFeatureSettings() {
        return this.fontFeatureSettings;
    }

    /* JADX INFO: renamed from: getEffectiveLineHeight, reason: from getter */
    public final float getLineHeight() {
        return this.lineHeight;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setNumberOfLines(int numberOfLines) {
        if (numberOfLines == 0) {
            numberOfLines = -1;
        }
        this.numberOfLines = numberOfLines;
    }

    public final float getLetterSpacing() {
        float pixelFromSP$default = this.allowFontScaling ? PixelUtil.toPixelFromSP$default(this.letterSpacingInput, 0.0f, 2, null) : PixelUtil.toPixelFromDIP(this.letterSpacingInput);
        int i = this.fontSize;
        if (i > 0) {
            return pixelFromSP$default / i;
        }
        throw new IllegalArgumentException(("FontSize should be a positive value. Current value: " + i).toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLetterSpacing(float f) {
        this.letterSpacingInput = f;
    }

    public final float getEffectiveLetterSpacing() {
        return getLetterSpacing();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFontSize(float fontSize) {
        double dCeil;
        this.fontSizeInput = fontSize;
        if (fontSize != -1.0f) {
            if (this.allowFontScaling) {
                dCeil = Math.ceil(PixelUtil.toPixelFromSP(fontSize, this.maxFontSizeMultiplier));
            } else {
                dCeil = Math.ceil(PixelUtil.toPixelFromDIP(fontSize));
            }
            fontSize = (float) dCeil;
        }
        this.fontSize = (int) fontSize;
    }

    public final Integer getColor() {
        return this.color;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setColor(Integer num) {
        this.isColorSet = num != null;
        if (num != null) {
            this.color = num;
        }
    }

    public final Integer getBackgroundColor() {
        return this.backgroundColor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBackgroundColor(Integer num) {
        this.isBackgroundColorSet = num != null;
        if (num != null) {
            this.backgroundColor = num;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFontVariant(ReadableArray fontVariant) {
        this.fontFeatureSettings = ReactTypefaceUtils.parseFontVariant(fontVariant);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void setFontVariant(MapBuffer fontVariant) {
        if (fontVariant == null || fontVariant.getCount() == 0) {
            this.fontFeatureSettings = null;
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<MapBuffer.Entry> it = fontVariant.iterator();
        while (it.hasNext()) {
            String stringValue = it.next().getStringValue();
            if (stringValue != null) {
                switch (stringValue.hashCode()) {
                    case -1983120972:
                        if (stringValue.equals("stylistic-thirteen")) {
                            arrayList.add("'ss13'");
                        }
                        break;
                    case -1933522176:
                        if (stringValue.equals("stylistic-fifteen")) {
                            arrayList.add("'ss15'");
                        }
                        break;
                    case -1534462052:
                        if (stringValue.equals("stylistic-eighteen")) {
                            arrayList.add("'ss18'");
                        }
                        break;
                    case -1195362251:
                        if (stringValue.equals("proportional-nums")) {
                            arrayList.add("'pnum'");
                        }
                        break;
                    case -1061392823:
                        if (stringValue.equals("lining-nums")) {
                            arrayList.add("'lnum'");
                        }
                        break;
                    case -771984547:
                        if (stringValue.equals("tabular-nums")) {
                            arrayList.add("'tnum'");
                        }
                        break;
                    case -659678800:
                        if (stringValue.equals("oldstyle-nums")) {
                            arrayList.add("'onum'");
                        }
                        break;
                    case 296506098:
                        if (stringValue.equals("stylistic-eight")) {
                            arrayList.add("'ss08'");
                        }
                        break;
                    case 309330544:
                        if (stringValue.equals("stylistic-seven")) {
                            arrayList.add("'ss07'");
                        }
                        break;
                    case 310339585:
                        if (stringValue.equals("stylistic-three")) {
                            arrayList.add("'ss03'");
                        }
                        break;
                    case 604478526:
                        if (stringValue.equals("stylistic-eleven")) {
                            arrayList.add("'ss11'");
                        }
                        break;
                    case 979426287:
                        if (stringValue.equals("stylistic-five")) {
                            arrayList.add("'ss05'");
                        }
                        break;
                    case 979432035:
                        if (stringValue.equals("stylistic-four")) {
                            arrayList.add("'ss04'");
                        }
                        break;
                    case 979664367:
                        if (stringValue.equals("stylistic-nine")) {
                            arrayList.add("'ss09'");
                        }
                        break;
                    case 1001434505:
                        if (stringValue.equals("stylistic-one")) {
                            arrayList.add("'ss01'");
                        }
                        break;
                    case 1001438213:
                        if (stringValue.equals("stylistic-six")) {
                            arrayList.add("'ss06'");
                        }
                        break;
                    case 1001439040:
                        if (stringValue.equals("stylistic-ten")) {
                            arrayList.add("'ss10'");
                        }
                        break;
                    case 1001439599:
                        if (stringValue.equals("stylistic-two")) {
                            arrayList.add("'ss02'");
                        }
                        break;
                    case 1030714463:
                        if (stringValue.equals("stylistic-sixteen")) {
                            arrayList.add("'ss16'");
                        }
                        break;
                    case 1044065430:
                        if (stringValue.equals("stylistic-twelve")) {
                            arrayList.add("'ss12'");
                        }
                        break;
                    case 1044067310:
                        if (stringValue.equals("stylistic-twenty")) {
                            arrayList.add("'ss20'");
                        }
                        break;
                    case 1183323111:
                        if (stringValue.equals("small-caps")) {
                            arrayList.add("'smcp'");
                        }
                        break;
                    case 1463562569:
                        if (stringValue.equals("stylistic-nineteen")) {
                            arrayList.add("'ss19'");
                        }
                        break;
                    case 1648446397:
                        if (stringValue.equals("stylistic-fourteen")) {
                            arrayList.add("'ss14'");
                        }
                        break;
                    case 2097122634:
                        if (stringValue.equals("stylistic-seventeen")) {
                            arrayList.add("'ss17'");
                        }
                        break;
                }
            }
        }
        this.fontFeatureSettings = TextUtils.join(", ", arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFontWeight(String fontWeightString) {
        this.fontWeight = ReactTypefaceUtils.parseFontWeight(fontWeightString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFontStyle(String fontStyleString) {
        this.fontStyle = ReactTypefaceUtils.parseFontStyle(fontStyleString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTextDecorationLine(String textDecorationLineString) {
        List listEmptyList;
        this.isUnderlineTextDecorationSet = false;
        this.isLineThroughTextDecorationSet = false;
        if (textDecorationLineString != null) {
            List<String> listSplit = new Regex("-").split(textDecorationLineString, 0);
            if (!listSplit.isEmpty()) {
                ListIterator<String> listIterator = listSplit.listIterator(listSplit.size());
                while (listIterator.hasPrevious()) {
                    if (listIterator.previous().length() != 0) {
                        listEmptyList = CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                        break;
                    }
                }
                listEmptyList = CollectionsKt.emptyList();
            } else {
                listEmptyList = CollectionsKt.emptyList();
            }
            for (String str : (String[]) listEmptyList.toArray(new String[0])) {
                if (Intrinsics.areEqual("underline", str)) {
                    this.isUnderlineTextDecorationSet = true;
                } else if (Intrinsics.areEqual("strikethrough", str)) {
                    this.isLineThroughTextDecorationSet = true;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTextShadowOffset(ReadableMap offsetMap) {
        setTextShadowOffsetDx(0.0f);
        setTextShadowOffsetDy(0.0f);
        if (offsetMap != null) {
            if (offsetMap.hasKey("width") && !offsetMap.isNull("width")) {
                setTextShadowOffsetDx(PixelUtil.toPixelFromDIP(offsetMap.getDouble("width")));
            }
            if (!offsetMap.hasKey("height") || offsetMap.isNull("height")) {
                return;
            }
            setTextShadowOffsetDy(PixelUtil.toPixelFromDIP(offsetMap.getDouble("height")));
        }
    }

    public final float getTextShadowOffsetDx() {
        return this.textShadowOffsetDx;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTextShadowOffsetDx(float f) {
        this.textShadowOffsetDx = PixelUtil.toPixelFromDIP(f);
    }

    public final float getTextShadowOffsetDy() {
        return this.textShadowOffsetDy;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTextShadowOffsetDy(float f) {
        this.textShadowOffsetDy = PixelUtil.toPixelFromDIP(f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLayoutDirection(String layoutDirection) {
        this.layoutDirection = INSTANCE.getLayoutDirection(layoutDirection);
    }

    public final float getTextShadowRadius() {
        return this.textShadowRadius;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTextShadowRadius(float f) {
        if (f == this.textShadowRadius) {
            return;
        }
        this.textShadowRadius = f;
    }

    public final int getTextShadowColor() {
        return this.textShadowColor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTextShadowColor(int i) {
        if (i != this.textShadowColor) {
            this.textShadowColor = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void setTextTransform(java.lang.String r3) {
        /*
            r2 = this;
            if (r3 == 0) goto L4f
            int r0 = r3.hashCode()
            switch(r0) {
                case -1765638420: goto L2c;
                case -514507343: goto L20;
                case 3387192: goto L17;
                case 223523538: goto La;
                default: goto L9;
            }
        L9:
            goto L38
        La:
            java.lang.String r0 = "uppercase"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L14
            goto L38
        L14:
            com.facebook.react.views.text.TextTransform r3 = com.facebook.react.views.text.TextTransform.UPPERCASE
            goto L51
        L17:
            java.lang.String r0 = "none"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L4f
            goto L38
        L20:
            java.lang.String r0 = "lowercase"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L29
            goto L38
        L29:
            com.facebook.react.views.text.TextTransform r3 = com.facebook.react.views.text.TextTransform.LOWERCASE
            goto L51
        L2c:
            java.lang.String r0 = "capitalize"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L35
            goto L38
        L35:
            com.facebook.react.views.text.TextTransform r3 = com.facebook.react.views.text.TextTransform.CAPITALIZE
            goto L51
        L38:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Invalid textTransform: "
            r0.<init>(r1)
            java.lang.StringBuilder r3 = r0.append(r3)
            java.lang.String r3 = r3.toString()
            java.lang.String r0 = "ReactNative"
            com.facebook.common.logging.FLog.w(r0, r3)
            com.facebook.react.views.text.TextTransform r3 = com.facebook.react.views.text.TextTransform.NONE
            goto L51
        L4f:
            com.facebook.react.views.text.TextTransform r3 = com.facebook.react.views.text.TextTransform.NONE
        L51:
            r2.textTransform = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.TextAttributeProps.setTextTransform(java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAccessibilityRole(String accessibilityRole) {
        this.accessibilityRole = accessibilityRole == null ? null : ReactAccessibilityDelegate.AccessibilityRole.INSTANCE.fromValue(accessibilityRole);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRole(String role) {
        if (role == null) {
            this.role = null;
        } else {
            this.role = ReactAccessibilityDelegate.Role.INSTANCE.fromValue(role);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRole(ReactAccessibilityDelegate.Role role) {
        this.role = role;
    }

    /* JADX INFO: compiled from: TextAttributeProps.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u001d\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200J\u000e\u00101\u001a\u00020.2\u0006\u0010/\u001a\u000202J\u001e\u00103\u001a\u00020\u00052\u0006\u0010/\u001a\u0002022\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\u0005J\u0016\u00107\u001a\u00020\u00052\u0006\u0010/\u001a\u0002022\u0006\u00106\u001a\u00020\u0005J \u00108\u001a\u0002052\u0006\u0010/\u001a\u0002022\u0006\u00109\u001a\u00020#2\u0006\u00106\u001a\u000205H\u0002J\u001a\u0010:\u001a\u0004\u0018\u00010#2\u0006\u0010/\u001a\u0002022\u0006\u00109\u001a\u00020#H\u0002J \u0010;\u001a\u00020\u00052\u0006\u0010/\u001a\u0002022\u0006\u00109\u001a\u00020#2\u0006\u00106\u001a\u00020\u0005H\u0002J \u0010<\u001a\u00020=2\u0006\u0010/\u001a\u0002022\u0006\u00109\u001a\u00020#2\u0006\u00106\u001a\u00020=H\u0002J\u001a\u0010>\u001a\u0004\u0018\u00010?2\u0006\u0010/\u001a\u0002022\u0006\u00109\u001a\u00020#H\u0002J\u0010\u0010@\u001a\u00020\u00052\b\u0010A\u001a\u0004\u0018\u00010#J\u0010\u0010B\u001a\u00020\u00052\b\u0010C\u001a\u0004\u0018\u00010#J\u0010\u0010D\u001a\u00020\u00052\b\u0010E\u001a\u0004\u0018\u00010#J\u0012\u0010F\u001a\u0004\u0018\u00010G2\b\u0010H\u001a\u0004\u0018\u00010#R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020#X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020#X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020#X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020#X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020#X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006I"}, d2 = {"Lcom/facebook/react/views/text/TextAttributeProps$Companion;", "", "<init>", "()V", "TA_KEY_FOREGROUND_COLOR", "", "TA_KEY_BACKGROUND_COLOR", "TA_KEY_OPACITY", "TA_KEY_FONT_FAMILY", "TA_KEY_FONT_SIZE", "TA_KEY_FONT_SIZE_MULTIPLIER", "TA_KEY_FONT_WEIGHT", "TA_KEY_FONT_STYLE", "TA_KEY_FONT_VARIANT", "TA_KEY_ALLOW_FONT_SCALING", "TA_KEY_LETTER_SPACING", "TA_KEY_LINE_HEIGHT", "TA_KEY_ALIGNMENT", "TA_KEY_BEST_WRITING_DIRECTION", "TA_KEY_TEXT_DECORATION_COLOR", "TA_KEY_TEXT_DECORATION_LINE", "TA_KEY_TEXT_DECORATION_STYLE", "TA_KEY_TEXT_SHADOW_RADIUS", "TA_KEY_TEXT_SHADOW_COLOR", "TA_KEY_TEXT_SHADOW_OFFSET_DX", "TA_KEY_TEXT_SHADOW_OFFSET_DY", "TA_KEY_IS_HIGHLIGHTED", "TA_KEY_LAYOUT_DIRECTION", "TA_KEY_ACCESSIBILITY_ROLE", "TA_KEY_LINE_BREAK_STRATEGY", "TA_KEY_ROLE", "TA_KEY_TEXT_TRANSFORM", "TA_KEY_MAX_FONT_SIZE_MULTIPLIER", "UNSET", "PROP_SHADOW_OFFSET", "", "PROP_SHADOW_OFFSET_WIDTH", "PROP_SHADOW_OFFSET_HEIGHT", "PROP_SHADOW_RADIUS", "PROP_SHADOW_COLOR", "PROP_TEXT_TRANSFORM", "DEFAULT_TEXT_SHADOW_COLOR", "DEFAULT_JUSTIFICATION_MODE", "DEFAULT_BREAK_STRATEGY", "DEFAULT_HYPHENATION_FREQUENCY", "fromMapBuffer", "Lcom/facebook/react/views/text/TextAttributeProps;", "props", "Lcom/facebook/react/common/mapbuffer/MapBuffer;", "fromReadableMap", "Lcom/facebook/react/uimanager/ReactStylesDiffMap;", "getTextAlignment", "isRTL", "", "defaultValue", "getJustificationMode", "getBooleanProp", "name", "getStringProp", "getIntProp", "getFloatProp", "", "getArrayProp", "Lcom/facebook/react/bridge/ReadableArray;", "getLayoutDirection", ViewProps.LAYOUT_DIRECTION, "getTextBreakStrategy", ViewProps.TEXT_BREAK_STRATEGY, "getHyphenationFrequency", "hyphenationFrequency", "getEllipsizeMode", "Landroid/text/TextUtils$TruncateAt;", ViewProps.ELLIPSIZE_MODE, "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TextAttributeProps fromMapBuffer(MapBuffer props) {
            Intrinsics.checkNotNullParameter(props, "props");
            TextAttributeProps textAttributeProps = new TextAttributeProps(null);
            for (MapBuffer.Entry entry : props) {
                switch (entry.getKey()) {
                    case 0:
                        textAttributeProps.setColor(Integer.valueOf(entry.getIntValue()));
                        break;
                    case 1:
                        textAttributeProps.setBackgroundColor(Integer.valueOf(entry.getIntValue()));
                        break;
                    case 2:
                        textAttributeProps.opacity = (float) entry.getDoubleValue();
                        break;
                    case 3:
                        textAttributeProps.fontFamily = entry.getStringValue();
                        break;
                    case 4:
                        textAttributeProps.setFontSize((float) entry.getDoubleValue());
                        break;
                    case 6:
                        textAttributeProps.setFontWeight(entry.getStringValue());
                        break;
                    case 7:
                        textAttributeProps.setFontStyle(entry.getStringValue());
                        break;
                    case 8:
                        textAttributeProps.setFontVariant(entry.getMapBufferValue());
                        break;
                    case 9:
                        textAttributeProps.setAllowFontScaling(entry.getBooleanValue());
                        break;
                    case 10:
                        textAttributeProps.setLetterSpacing((float) entry.getDoubleValue());
                        break;
                    case 11:
                        textAttributeProps.setLineHeight((float) entry.getDoubleValue());
                        break;
                    case 15:
                        textAttributeProps.setTextDecorationLine(entry.getStringValue());
                        break;
                    case 18:
                        textAttributeProps.setTextShadowRadius((float) entry.getDoubleValue());
                        break;
                    case 19:
                        textAttributeProps.setTextShadowColor(entry.getIntValue());
                        break;
                    case 20:
                        textAttributeProps.setTextShadowOffsetDx((float) entry.getDoubleValue());
                        break;
                    case 21:
                        textAttributeProps.setTextShadowOffsetDy((float) entry.getDoubleValue());
                        break;
                    case 23:
                        textAttributeProps.setLayoutDirection(entry.getStringValue());
                        break;
                    case 24:
                        textAttributeProps.setAccessibilityRole(entry.getStringValue());
                        break;
                    case 26:
                        textAttributeProps.setRole(ReactAccessibilityDelegate.Role.getEntries().get(entry.getIntValue()));
                        break;
                    case 27:
                        textAttributeProps.setTextTransform(entry.getStringValue());
                        break;
                    case 29:
                        textAttributeProps.setMaxFontSizeMultiplier((float) entry.getDoubleValue());
                        break;
                }
            }
            return textAttributeProps;
        }

        public final TextAttributeProps fromReadableMap(ReactStylesDiffMap props) {
            Intrinsics.checkNotNullParameter(props, "props");
            TextAttributeProps textAttributeProps = new TextAttributeProps(0 == true ? 1 : 0);
            textAttributeProps.setNumberOfLines(getIntProp(props, ViewProps.NUMBER_OF_LINES, -1));
            textAttributeProps.setLineHeight(getFloatProp(props, ViewProps.LINE_HEIGHT, -1.0f));
            textAttributeProps.setLetterSpacing(getFloatProp(props, ViewProps.LETTER_SPACING, Float.NaN));
            textAttributeProps.setAllowFontScaling(getBooleanProp(props, ViewProps.ALLOW_FONT_SCALING, true));
            textAttributeProps.setMaxFontSizeMultiplier(getFloatProp(props, ViewProps.MAX_FONT_SIZE_MULTIPLIER, Float.NaN));
            textAttributeProps.setFontSize(getFloatProp(props, ViewProps.FONT_SIZE, -1.0f));
            textAttributeProps.setColor(props.hasKey("color") ? Integer.valueOf(props.getInt("color", 0)) : null);
            textAttributeProps.setColor(props.hasKey(ViewProps.FOREGROUND_COLOR) ? Integer.valueOf(props.getInt(ViewProps.FOREGROUND_COLOR, 0)) : null);
            textAttributeProps.setBackgroundColor(props.hasKey(ViewProps.BACKGROUND_COLOR) ? Integer.valueOf(props.getInt(ViewProps.BACKGROUND_COLOR, 0)) : null);
            textAttributeProps.opacity = getFloatProp(props, ViewProps.OPACITY, Float.NaN);
            textAttributeProps.fontFamily = getStringProp(props, ViewProps.FONT_FAMILY);
            textAttributeProps.setFontWeight(getStringProp(props, ViewProps.FONT_WEIGHT));
            textAttributeProps.setFontStyle(getStringProp(props, ViewProps.FONT_STYLE));
            textAttributeProps.setFontVariant(getArrayProp(props, ViewProps.FONT_VARIANT));
            textAttributeProps.includeFontPadding = getBooleanProp(props, ViewProps.INCLUDE_FONT_PADDING, true);
            textAttributeProps.setTextDecorationLine(getStringProp(props, ViewProps.TEXT_DECORATION_LINE));
            textAttributeProps.setTextShadowOffset(props.hasKey("textShadowOffset") ? props.getMap("textShadowOffset") : null);
            textAttributeProps.setTextShadowRadius(getFloatProp(props, "textShadowRadius", 1.0f));
            textAttributeProps.setTextShadowColor(getIntProp(props, "textShadowColor", 1426063360));
            textAttributeProps.setTextTransform(getStringProp(props, "textTransform"));
            textAttributeProps.setLayoutDirection(getStringProp(props, ViewProps.LAYOUT_DIRECTION));
            textAttributeProps.setAccessibilityRole(getStringProp(props, ViewProps.ACCESSIBILITY_ROLE));
            textAttributeProps.setRole(getStringProp(props, ViewProps.ROLE));
            return textAttributeProps;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:34:0x0058  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final int getTextAlignment(com.facebook.react.uimanager.ReactStylesDiffMap r4, boolean r5, int r6) {
            /*
                r3 = this;
                java.lang.String r0 = "props"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                java.lang.String r0 = "textAlign"
                boolean r1 = r4.hasKey(r0)
                if (r1 != 0) goto Lf
                return r6
            Lf:
                java.lang.String r4 = r4.getString(r0)
                r6 = 0
                if (r4 == 0) goto L6c
                int r0 = r4.hashCode()
                r1 = 5
                r2 = 3
                switch(r0) {
                    case -1364013995: goto L4d;
                    case -1249482096: goto L43;
                    case 3005871: goto L3a;
                    case 3317767: goto L2d;
                    case 108511772: goto L20;
                    default: goto L1f;
                }
            L1f:
                goto L58
            L20:
                java.lang.String r0 = "right"
                boolean r0 = r4.equals(r0)
                if (r0 != 0) goto L29
                goto L58
            L29:
                if (r5 == 0) goto L2c
                return r2
            L2c:
                return r1
            L2d:
                java.lang.String r0 = "left"
                boolean r0 = r4.equals(r0)
                if (r0 != 0) goto L36
                goto L58
            L36:
                if (r5 == 0) goto L39
                return r1
            L39:
                return r2
            L3a:
                java.lang.String r5 = "auto"
                boolean r5 = r4.equals(r5)
                if (r5 != 0) goto L6c
                goto L58
            L43:
                java.lang.String r5 = "justify"
                boolean r5 = r4.equals(r5)
                if (r5 != 0) goto L4c
                goto L58
            L4c:
                return r2
            L4d:
                java.lang.String r5 = "center"
                boolean r5 = r4.equals(r5)
                if (r5 != 0) goto L56
                goto L58
            L56:
                r4 = 1
                return r4
            L58:
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                java.lang.String r0 = "Invalid textAlign: "
                r5.<init>(r0)
                java.lang.StringBuilder r4 = r5.append(r4)
                java.lang.String r4 = r4.toString()
                java.lang.String r5 = "ReactNative"
                com.facebook.common.logging.FLog.w(r5, r4)
            L6c:
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.TextAttributeProps.Companion.getTextAlignment(com.facebook.react.uimanager.ReactStylesDiffMap, boolean, int):int");
        }

        public final int getJustificationMode(ReactStylesDiffMap props, int defaultValue) {
            Intrinsics.checkNotNullParameter(props, "props");
            if (!props.hasKey(ViewProps.TEXT_ALIGN)) {
                return defaultValue;
            }
            if (!Intrinsics.areEqual("justify", props.getString(ViewProps.TEXT_ALIGN)) || Build.VERSION.SDK_INT < 26) {
                return TextAttributeProps.DEFAULT_JUSTIFICATION_MODE;
            }
            return 1;
        }

        private final boolean getBooleanProp(ReactStylesDiffMap props, String name, boolean defaultValue) {
            return props.hasKey(name) ? props.getBoolean(name, defaultValue) : defaultValue;
        }

        private final String getStringProp(ReactStylesDiffMap props, String name) {
            if (props.hasKey(name)) {
                return props.getString(name);
            }
            return null;
        }

        private final int getIntProp(ReactStylesDiffMap props, String name, int defaultValue) {
            return props.hasKey(name) ? props.getInt(name, defaultValue) : defaultValue;
        }

        private final float getFloatProp(ReactStylesDiffMap props, String name, float defaultValue) {
            return props.hasKey(name) ? props.getFloat(name, defaultValue) : defaultValue;
        }

        private final ReadableArray getArrayProp(ReactStylesDiffMap props, String name) {
            if (props.hasKey(name)) {
                return props.getArray(name);
            }
            return null;
        }

        public final int getLayoutDirection(String layoutDirection) {
            if (layoutDirection != null) {
                int iHashCode = layoutDirection.hashCode();
                if (iHashCode != -1038130864) {
                    if (iHashCode != 107498) {
                        if (iHashCode == 113258 && layoutDirection.equals("rtl")) {
                            return 1;
                        }
                    } else if (layoutDirection.equals("ltr")) {
                        return 0;
                    }
                } else if (!layoutDirection.equals("undefined")) {
                }
                FLog.w(ReactConstants.TAG, "Invalid layoutDirection: " + layoutDirection);
            }
            return -1;
        }

        public final int getTextBreakStrategy(String textBreakStrategy) {
            if (textBreakStrategy == null) {
                return 1;
            }
            if (Intrinsics.areEqual(textBreakStrategy, "simple")) {
                return 0;
            }
            return Intrinsics.areEqual(textBreakStrategy, "balanced") ? 2 : 1;
        }

        public final int getHyphenationFrequency(String hyphenationFrequency) {
            if (hyphenationFrequency == null || Intrinsics.areEqual(hyphenationFrequency, "none")) {
                return 0;
            }
            return Intrinsics.areEqual(hyphenationFrequency, ProfilingTraceData.TRUNCATION_REASON_NORMAL) ? 1 : 2;
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        public final TextUtils.TruncateAt getEllipsizeMode(String ellipsizeMode) {
            if (ellipsizeMode == null) {
                return null;
            }
            switch (ellipsizeMode.hashCode()) {
                case -1074341483:
                    if (ellipsizeMode.equals("middle")) {
                        return TextUtils.TruncateAt.MIDDLE;
                    }
                    return null;
                case 3056464:
                    ellipsizeMode.equals("clip");
                    return null;
                case 3198432:
                    if (ellipsizeMode.equals("head")) {
                        return TextUtils.TruncateAt.START;
                    }
                    return null;
                case 3552336:
                    if (ellipsizeMode.equals("tail")) {
                        return TextUtils.TruncateAt.END;
                    }
                    return null;
                default:
                    return null;
            }
        }
    }
}
