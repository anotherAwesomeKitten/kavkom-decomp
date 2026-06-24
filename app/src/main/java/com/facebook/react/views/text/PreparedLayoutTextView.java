package com.facebook.react.views.text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build;
import android.text.Layout;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewGroup;
import androidx.core.app.NotificationCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import com.facebook.react.uimanager.BackgroundStyleApplicator;
import com.facebook.react.uimanager.ReactCompoundView;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.style.Overflow;
import com.facebook.react.views.text.internal.span.ReactFragmentIndexSpan;
import com.facebook.react.views.textinput.ReactTextInputShadowNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: PreparedLayoutTextView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\b\u0001\u0018\u0000 O2\u00020\u00012\u00020\u0002:\u0003MNOB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010$\u001a\u00020%H\u0002J\u0006\u0010&\u001a\u00020%J\u0010\u0010'\u001a\u00020%2\u0006\u0010(\u001a\u00020)H\u0014J0\u0010*\u001a\u00020%2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020\u001a2\u0006\u0010/\u001a\u00020\u001a2\u0006\u00100\u001a\u00020\u001aH\u0014J\u0016\u00101\u001a\u00020%2\u0006\u00102\u001a\u00020\u001a2\u0006\u00103\u001a\u00020\u001aJ\u0006\u00104\u001a\u00020%J\u0010\u00105\u001a\u00020,2\u0006\u00106\u001a\u000207H\u0016J3\u00108\u001a\u0004\u0018\u0001H9\"\u0004\b\u0000\u001092\u0006\u0010:\u001a\u00020\u001a2\u0006\u0010;\u001a\u00020\u001a2\f\u0010<\u001a\b\u0012\u0004\u0012\u0002H90=H\u0002¢\u0006\u0002\u0010>J\u0018\u0010?\u001a\u00020\u001a2\u0006\u0010:\u001a\u00020\u001a2\u0006\u0010;\u001a\u00020\u001aH\u0002J\u0010\u0010@\u001a\u00020,2\u0006\u00106\u001a\u000207H\u0016J\"\u0010A\u001a\u00020%2\u0006\u0010B\u001a\u00020,2\u0006\u0010C\u001a\u00020\u001a2\b\u0010D\u001a\u0004\u0018\u00010EH\u0016J\u0010\u0010F\u001a\u00020,2\u0006\u00106\u001a\u00020GH\u0016J\b\u0010H\u001a\u00020,H\u0016J\u0018\u0010I\u001a\u00020\u001a2\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020KH\u0016R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\f\u001a\u0004\u0018\u00010\r@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R$\u0010\u0014\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\u0013@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\"\u0010\u0019\u001a\u0004\u0018\u00010\u001a8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0013\u0010 \u001a\u0004\u0018\u00010!8G¢\u0006\u0006\u001a\u0004\b\"\u0010#¨\u0006P"}, d2 = {"Lcom/facebook/react/views/text/PreparedLayoutTextView;", "Landroid/view/ViewGroup;", "Lcom/facebook/react/uimanager/ReactCompoundView;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "clickableSpans", "", "Landroid/text/style/ClickableSpan;", "selection", "Lcom/facebook/react/views/text/PreparedLayoutTextView$TextSelection;", "value", "Lcom/facebook/react/views/text/PreparedLayout;", "preparedLayout", "getPreparedLayout", "()Lcom/facebook/react/views/text/PreparedLayout;", "setPreparedLayout", "(Lcom/facebook/react/views/text/PreparedLayout;)V", "Lcom/facebook/react/uimanager/style/Overflow;", ViewProps.OVERFLOW, "getOverflow", "()Lcom/facebook/react/uimanager/style/Overflow;", "setOverflow", "(Lcom/facebook/react/uimanager/style/Overflow;)V", "selectionColor", "", "getSelectionColor", "()Ljava/lang/Integer;", "setSelectionColor", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", ReactTextInputShadowNode.PROP_TEXT, "", "getText", "()Ljava/lang/CharSequence;", "initView", "", "recycleView", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onLayout", "changed", "", "l", "t", "r", "b", "setSelection", ViewProps.START, ViewProps.END, "clearSelection", "onTouchEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "getSpanInCoords", "T", "x", "y", "clazz", "Ljava/lang/Class;", "(IILjava/lang/Class;)Ljava/lang/Object;", "getTextOffsetAt", "dispatchHoverEvent", "onFocusChanged", "gainFocus", "direction", "previouslyFocusedRect", "Landroid/graphics/Rect;", "dispatchKeyEvent", "Landroid/view/KeyEvent;", "hasOverlappingRendering", "reactTagForTouch", "touchX", "", "touchY", "Api34Utils", "TextSelection", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PreparedLayoutTextView extends ViewGroup implements ReactCompoundView {
    private static final Companion Companion = new Companion(null);
    private static final Paint selectionPaint = new Paint();
    private List<? extends ClickableSpan> clickableSpans;
    private Overflow overflow;
    private PreparedLayout preparedLayout;
    private TextSelection selection;
    private Integer selectionColor;

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PreparedLayoutTextView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.clickableSpans = CollectionsKt.emptyList();
        this.overflow = Overflow.HIDDEN;
        initView();
        setWillNotDraw(false);
    }

    public final PreparedLayout getPreparedLayout() {
        return this.preparedLayout;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void setPreparedLayout(com.facebook.react.views.text.PreparedLayout r5) {
        /*
            r4 = this;
            com.facebook.react.views.text.PreparedLayout r0 = r4.preparedLayout
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r5)
            if (r0 != 0) goto L6c
            com.facebook.react.views.text.PreparedLayoutTextView$TextSelection r0 = r4.selection
            if (r0 == 0) goto L4b
            if (r5 == 0) goto L48
            com.facebook.react.views.text.PreparedLayout r1 = r4.preparedLayout
            if (r1 == 0) goto L1d
            android.text.Layout r1 = r1.getLayout()
            if (r1 == 0) goto L1d
            java.lang.CharSequence r1 = r1.getText()
            goto L1e
        L1d:
            r1 = 0
        L1e:
            java.lang.String r1 = java.lang.String.valueOf(r1)
            android.text.Layout r2 = r5.getLayout()
            java.lang.CharSequence r2 = r2.getText()
            java.lang.String r2 = r2.toString()
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r2)
            if (r1 == 0) goto L48
            android.text.Layout r1 = r5.getLayout()
            int r2 = r0.getStart()
            int r3 = r0.getEnd()
            android.graphics.Path r0 = r0.getPath()
            r1.getSelectionPath(r2, r3, r0)
            goto L4b
        L48:
            r4.clearSelection()
        L4b:
            if (r5 == 0) goto L61
            android.text.Layout r0 = r5.getLayout()
            if (r0 == 0) goto L61
            java.lang.CharSequence r0 = r0.getText()
            if (r0 == 0) goto L61
            com.facebook.react.views.text.PreparedLayoutTextView$Companion r1 = com.facebook.react.views.text.PreparedLayoutTextView.Companion
            java.util.List r0 = com.facebook.react.views.text.PreparedLayoutTextView.Companion.access$filterClickableSpans(r1, r0)
            if (r0 != 0) goto L65
        L61:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
        L65:
            r4.clickableSpans = r0
            r4.preparedLayout = r5
            r4.invalidate()
        L6c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.PreparedLayoutTextView.setPreparedLayout(com.facebook.react.views.text.PreparedLayout):void");
    }

    public final Overflow getOverflow() {
        return this.overflow;
    }

    public final void setOverflow(Overflow value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (this.overflow != value) {
            this.overflow = value;
            invalidate();
        }
    }

    public final Integer getSelectionColor() {
        return this.selectionColor;
    }

    public final void setSelectionColor(Integer num) {
        this.selectionColor = num;
    }

    public final CharSequence getText() {
        Layout layout;
        PreparedLayout preparedLayout = this.preparedLayout;
        if (preparedLayout == null || (layout = preparedLayout.getLayout()) == null) {
            return null;
        }
        return layout.getText();
    }

    private final void initView() {
        this.clickableSpans = CollectionsKt.emptyList();
        this.selection = null;
        setPreparedLayout(null);
    }

    public final void recycleView() {
        initView();
        BackgroundStyleApplicator.reset(this);
        setOverflow(Overflow.HIDDEN);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int defaultTextColorHighlight;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (this.overflow != Overflow.VISIBLE) {
            BackgroundStyleApplicator.clipToPaddingBox(this, canvas);
        }
        super.onDraw(canvas);
        float paddingLeft = getPaddingLeft();
        float paddingTop = getPaddingTop();
        PreparedLayout preparedLayout = this.preparedLayout;
        canvas.translate(paddingLeft, paddingTop + (preparedLayout != null ? preparedLayout.getVerticalOffset() : 0.0f));
        PreparedLayout preparedLayout2 = this.preparedLayout;
        Layout layout = preparedLayout2 != null ? preparedLayout2.getLayout() : null;
        if (layout != null) {
            if (this.selection != null) {
                Paint paint = selectionPaint;
                Integer num = this.selectionColor;
                if (num != null) {
                    defaultTextColorHighlight = num.intValue();
                } else {
                    Context context = getContext();
                    Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
                    defaultTextColorHighlight = DefaultStyleValuesUtil.getDefaultTextColorHighlight(context);
                }
                paint.setColor(defaultTextColorHighlight);
            }
            if (Build.VERSION.SDK_INT >= 34) {
                Api34Utils api34Utils = Api34Utils.INSTANCE;
                TextSelection textSelection = this.selection;
                api34Utils.draw(layout, canvas, textSelection != null ? textSelection.getPath() : null, selectionPaint);
            } else {
                TextSelection textSelection2 = this.selection;
                layout.draw(canvas, textSelection2 != null ? textSelection2.getPath() : null, selectionPaint, 0);
            }
        }
    }

    public final void setSelection(int start, int end) {
        PreparedLayout preparedLayout = this.preparedLayout;
        if (preparedLayout == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        Layout layout = preparedLayout.getLayout();
        if (start < 0 || end > layout.getText().length() || start >= end) {
            throw new IllegalArgumentException("setSelection start and end are not in valid range. start: " + start + ", end: " + end + ", text length: " + layout.getText().length());
        }
        TextSelection textSelection = this.selection;
        if (textSelection == null) {
            Path path = new Path();
            layout.getSelectionPath(start, end, path);
            this.selection = new TextSelection(start, end, path);
        } else {
            textSelection.setStart(start);
            textSelection.setEnd(end);
            layout.getSelectionPath(start, end, textSelection.getPath());
        }
        invalidate();
    }

    public final void clearSelection() {
        this.selection = null;
        invalidate();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (!isEnabled() || this.clickableSpans.isEmpty()) {
            return super.onTouchEvent(event);
        }
        int actionMasked = event.getActionMasked();
        if (actionMasked == 3) {
            clearSelection();
            return false;
        }
        ClickableSpan clickableSpan = (ClickableSpan) getSpanInCoords((int) event.getX(), (int) event.getY(), ClickableSpan.class);
        if (clickableSpan == null) {
            clearSelection();
            return super.onTouchEvent(event);
        }
        if (actionMasked == 0) {
            PreparedLayout preparedLayout = this.preparedLayout;
            if (preparedLayout == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            Layout layout = preparedLayout.getLayout();
            CharSequence text = layout.getText();
            Intrinsics.checkNotNull(text, "null cannot be cast to non-null type android.text.Spanned");
            int spanStart = ((Spanned) text).getSpanStart(clickableSpan);
            CharSequence text2 = layout.getText();
            Intrinsics.checkNotNull(text2, "null cannot be cast to non-null type android.text.Spanned");
            setSelection(spanStart, ((Spanned) text2).getSpanEnd(clickableSpan));
        } else if (actionMasked == 1) {
            clearSelection();
            clickableSpan.onClick(this);
        }
        return true;
    }

    private final <T> T getSpanInCoords(int x, int y, Class<T> clazz) {
        int spanStart;
        int spanEnd;
        int textOffsetAt = getTextOffsetAt(x, y);
        if (textOffsetAt < 0) {
            return null;
        }
        CharSequence text = getText();
        Spanned spanned = text instanceof Spanned ? (Spanned) text : null;
        if (spanned == null) {
            return null;
        }
        Object[] spans = spanned.getSpans(textOffsetAt, textOffsetAt, clazz);
        Intrinsics.checkNotNull(spans);
        if (spans.length == 0) {
            return null;
        }
        if (spans.length > 2) {
            throw new IllegalStateException("Check failed.");
        }
        Iterator it = ArrayIteratorKt.iterator(spans);
        while (it.hasNext()) {
            T t = (T) it.next();
            int spanFlags = spanned.getSpanFlags(t);
            int i = spanFlags & 18;
            if (i != 0 || (spanFlags & 17) != 0) {
                spanStart = spanned.getSpanStart(t);
            } else {
                spanStart = spanned.getSpanStart(t) + 1;
            }
            if (i != 0 || (spanFlags & 34) != 0) {
                spanEnd = spanned.getSpanEnd(t);
            } else {
                spanEnd = spanned.getSpanEnd(t) - 1;
            }
            if (textOffsetAt >= spanStart && textOffsetAt <= spanEnd) {
                return t;
            }
        }
        return null;
    }

    private final int getTextOffsetAt(int x, int y) {
        Layout layout;
        float paragraphLeft;
        float paragraphRight;
        float lineLeft;
        int paddingLeft = x - getPaddingLeft();
        int paddingTop = getPaddingTop();
        PreparedLayout preparedLayout = this.preparedLayout;
        int iRoundToInt = y - (paddingTop + (preparedLayout != null ? MathKt.roundToInt(preparedLayout.getVerticalOffset()) : 0));
        PreparedLayout preparedLayout2 = this.preparedLayout;
        if (preparedLayout2 != null && (layout = preparedLayout2.getLayout()) != null) {
            int lineForVertical = layout.getLineForVertical(iRoundToInt);
            if (layout.getAlignment() == Layout.Alignment.ALIGN_CENTER) {
                lineLeft = layout.getLineLeft(lineForVertical);
                paragraphRight = layout.getLineRight(lineForVertical);
            } else {
                boolean z = layout.getParagraphDirection(lineForVertical) == -1;
                if (z) {
                    paragraphLeft = layout.getWidth() - layout.getLineMax(lineForVertical);
                } else {
                    paragraphLeft = layout.getParagraphLeft(lineForVertical);
                }
                float f = paragraphLeft;
                paragraphRight = z ? layout.getParagraphRight(lineForVertical) : layout.getLineMax(lineForVertical);
                lineLeft = f;
            }
            float f2 = paddingLeft;
            if (f2 >= lineLeft && f2 <= paragraphRight) {
                try {
                    return layout.getOffsetForHorizontal(lineForVertical, f2);
                } catch (ArrayIndexOutOfBoundsException unused) {
                }
            }
        }
        return -1;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchHoverEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        return super.dispatchHoverEvent(event);
    }

    @Override // android.view.View
    public void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        if (!this.clickableSpans.isEmpty() && !gainFocus) {
            clearSelection();
        }
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        AccessibilityDelegateCompat accessibilityDelegate = ViewCompat.getAccessibilityDelegate(this);
        if (accessibilityDelegate == null || !(accessibilityDelegate instanceof ReactTextViewAccessibilityDelegate)) {
            return;
        }
        ((ReactTextViewAccessibilityDelegate) accessibilityDelegate).onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        AccessibilityDelegateCompat accessibilityDelegate = ViewCompat.getAccessibilityDelegate(this);
        return ((accessibilityDelegate instanceof ReactTextViewAccessibilityDelegate) && ((ReactTextViewAccessibilityDelegate) accessibilityDelegate).dispatchKeyEvent(event)) || super.dispatchKeyEvent(event);
    }

    @Override // com.facebook.react.uimanager.ReactCompoundView
    public int reactTagForTouch(float touchX, float touchY) {
        ReactFragmentIndexSpan reactFragmentIndexSpan = (ReactFragmentIndexSpan) getSpanInCoords(MathKt.roundToInt(touchX), MathKt.roundToInt(touchY), ReactFragmentIndexSpan.class);
        if (reactFragmentIndexSpan != null) {
            int fragmentIndex = reactFragmentIndexSpan.getFragmentIndex();
            PreparedLayout preparedLayout = this.preparedLayout;
            Integer numValueOf = preparedLayout != null ? Integer.valueOf(preparedLayout.getReactTags()[fragmentIndex]) : null;
            if (numValueOf != null) {
                return numValueOf.intValue();
            }
        }
        return getId();
    }

    /* JADX INFO: compiled from: PreparedLayoutTextView.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J,\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\bH\u0007R\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/facebook/react/views/text/PreparedLayoutTextView$Api34Utils;", "", "<init>", "()V", "highlightPaths", "", "Landroid/graphics/Path;", "highlightPaints", "Landroid/graphics/Paint;", "draw", "", "layout", "Landroid/text/Layout;", "canvas", "Landroid/graphics/Canvas;", "selectionPath", "selectionPaint", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Api34Utils {
        public static final Api34Utils INSTANCE = new Api34Utils();
        private static List<? extends Paint> highlightPaints;
        private static List<? extends Path> highlightPaths;

        private Api34Utils() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void draw(Layout layout, Canvas canvas, Path selectionPath, Paint selectionPaint) {
            Intrinsics.checkNotNullParameter(layout, "layout");
            Intrinsics.checkNotNullParameter(canvas, "canvas");
            if (selectionPath != null) {
                if (highlightPaths == null) {
                    highlightPaths = new ArrayList();
                }
                if (highlightPaints == null) {
                    highlightPaints = new ArrayList();
                }
            }
            layout.draw(canvas, highlightPaths, highlightPaints, selectionPath, selectionPaint, 0);
        }
    }

    /* JADX INFO: compiled from: PreparedLayoutTextView.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/facebook/react/views/text/PreparedLayoutTextView$TextSelection;", "", ViewProps.START, "", ViewProps.END, "path", "Landroid/graphics/Path;", "<init>", "(IILandroid/graphics/Path;)V", "getStart", "()I", "setStart", "(I)V", "getEnd", "setEnd", "getPath", "()Landroid/graphics/Path;", "setPath", "(Landroid/graphics/Path;)V", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class TextSelection {
        private int end;
        private Path path;
        private int start;

        public TextSelection(int i, int i2, Path path) {
            Intrinsics.checkNotNullParameter(path, "path");
            this.start = i;
            this.end = i2;
            this.path = path;
        }

        public final int getStart() {
            return this.start;
        }

        public final void setStart(int i) {
            this.start = i;
        }

        public final int getEnd() {
            return this.end;
        }

        public final void setEnd(int i) {
            this.end = i;
        }

        public final Path getPath() {
            return this.path;
        }

        public final void setPath(Path path) {
            Intrinsics.checkNotNullParameter(path, "<set-?>");
            this.path = path;
        }
    }

    /* JADX INFO: compiled from: PreparedLayoutTextView.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/facebook/react/views/text/PreparedLayoutTextView$Companion;", "", "<init>", "()V", "selectionPaint", "Landroid/graphics/Paint;", "filterClickableSpans", "", "Landroid/text/style/ClickableSpan;", ReactTextInputShadowNode.PROP_TEXT, "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<ClickableSpan> filterClickableSpans(CharSequence text) {
            if (!(text instanceof Spanned)) {
                return CollectionsKt.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (i < text.length()) {
                Spanned spanned = (Spanned) text;
                int iNextSpanTransition = spanned.nextSpanTransition(i, text.length(), ClickableSpan.class);
                Object[] spans = spanned.getSpans(i, iNextSpanTransition, ClickableSpan.class);
                Intrinsics.checkNotNullExpressionValue(spans, "getSpans(...)");
                CollectionsKt.addAll(arrayList, spans);
                i = iNextSpanTransition;
            }
            return arrayList;
        }
    }
}
