package com.facebook.react.views.textinput;

import android.view.ViewGroup;
import android.widget.EditText;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.view.ViewCompat;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.R;
import com.facebook.react.common.annotations.LegacyArchitectureShadowNodeWithCxxImpl;
import com.facebook.react.common.annotations.internal.LegacyArchitectureLogLevel;
import com.facebook.react.common.annotations.internal.LegacyArchitectureLogger;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.ReactBaseTextShadowNode;
import com.facebook.react.views.text.ReactTextUpdate;
import com.facebook.react.views.text.ReactTextViewManagerCallback;
import com.facebook.react.views.view.MeasureUtil;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import com.facebook.yoga.YogaNode;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactTextInputShadowNode.kt */
/* JADX INFO: loaded from: classes.dex */
@LegacyArchitectureShadowNodeWithCxxImpl
@Deprecated(level = DeprecationLevel.WARNING, message = "This class is part of Legacy Architecture and will be removed in a future release")
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0001\u0018\u0000 52\u00020\u00012\u00020\u0002:\u00015B\u0015\b\u0007\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J0\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020\"H\u0016J\b\u0010%\u001a\u00020&H\u0016J\b\u0010'\u001a\u00020&H\u0016J\u0010\u0010(\u001a\u00020\u00182\u0006\u0010)\u001a\u00020*H\u0016J\u0010\u0010+\u001a\u00020\u00182\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0012\u0010,\u001a\u00020\u00182\b\u0010-\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010.\u001a\u00020\u00182\u0006\u0010/\u001a\u000200H\u0016J\u0018\u00101\u001a\u00020\u00182\u0006\u00102\u001a\u00020\b2\u0006\u00103\u001a\u00020 H\u0016J\b\u00104\u001a\u00020\nH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u000e@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R(\u0010\u0014\u001a\u0004\u0018\u00010\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u000e@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013¨\u00066"}, d2 = {"Lcom/facebook/react/views/textinput/ReactTextInputShadowNode;", "Lcom/facebook/react/views/text/ReactBaseTextShadowNode;", "Lcom/facebook/yoga/YogaMeasureFunction;", "reactTextViewManagerCallback", "Lcom/facebook/react/views/text/ReactTextViewManagerCallback;", "<init>", "(Lcom/facebook/react/views/text/ReactTextViewManagerCallback;)V", "mostRecentEventCount", "", "internalEditText", "Landroid/widget/EditText;", "localData", "Lcom/facebook/react/views/textinput/ReactTextInputLocalData;", "value", "", ReactTextInputShadowNode.PROP_TEXT, "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", ReactTextInputShadowNode.PROP_PLACEHOLDER, "getPlaceholder", "setPlaceholder", "setThemedContext", "", "themedContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "measure", "", "node", "Lcom/facebook/yoga/YogaNode;", "width", "", "widthMode", "Lcom/facebook/yoga/YogaMeasureMode;", "height", "heightMode", "isVirtualAnchor", "", "isYogaLeafNode", "setLocalData", "data", "", "setMostRecentEventCount", "setTextBreakStrategy", ViewProps.TEXT_BREAK_STRATEGY, "onCollectExtraUpdates", "uiViewOperationQueue", "Lcom/facebook/react/uimanager/UIViewOperationQueue;", "setPadding", "spacingType", ViewProps.PADDING, "createInternalEditText", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ReactTextInputShadowNode extends ReactBaseTextShadowNode implements YogaMeasureFunction {
    public static final String PROP_PLACEHOLDER = "placeholder";
    public static final String PROP_TEXT = "text";
    private EditText internalEditText;
    private ReactTextInputLocalData localData;
    private int mostRecentEventCount;
    private String placeholder;
    private String text;

    public ReactTextInputShadowNode() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public boolean isVirtualAnchor() {
        return true;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public boolean isYogaLeafNode() {
        return true;
    }

    public /* synthetic */ ReactTextInputShadowNode(ReactTextViewManagerCallback reactTextViewManagerCallback, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : reactTextViewManagerCallback);
    }

    public ReactTextInputShadowNode(ReactTextViewManagerCallback reactTextViewManagerCallback) {
        super(reactTextViewManagerCallback);
        this.mostRecentEventCount = -1;
        setTextBreakStrategy(1);
        setMeasureFunction(this);
    }

    public final String getText() {
        return this.text;
    }

    @ReactProp(name = PROP_TEXT)
    public final void setText(String str) {
        this.text = str;
        markUpdated();
    }

    public final String getPlaceholder() {
        return this.placeholder;
    }

    @ReactProp(name = PROP_PLACEHOLDER)
    public final void setPlaceholder(String str) {
        this.placeholder = str;
        markUpdated();
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void setThemedContext(ThemedReactContext themedContext) {
        Intrinsics.checkNotNullParameter(themedContext, "themedContext");
        super.setThemedContext(themedContext);
        EditText editTextCreateInternalEditText = createInternalEditText();
        EditText editText = editTextCreateInternalEditText;
        setDefaultPadding(4, ViewCompat.getPaddingStart(editText));
        setDefaultPadding(1, editTextCreateInternalEditText.getPaddingTop());
        setDefaultPadding(5, ViewCompat.getPaddingEnd(editText));
        setDefaultPadding(3, editTextCreateInternalEditText.getPaddingBottom());
        this.internalEditText = editTextCreateInternalEditText;
        if (editTextCreateInternalEditText != null) {
            editTextCreateInternalEditText.setPadding(0, 0, 0, 0);
        }
        EditText editText2 = this.internalEditText;
        if (editText2 != null) {
            editText2.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        }
    }

    @Override // com.facebook.yoga.YogaMeasureFunction
    public long measure(YogaNode node, float width, YogaMeasureMode widthMode, float height, YogaMeasureMode heightMode) {
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(widthMode, "widthMode");
        Intrinsics.checkNotNullParameter(heightMode, "heightMode");
        EditText editText = this.internalEditText;
        if (editText == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        ReactTextInputLocalData reactTextInputLocalData = this.localData;
        if (reactTextInputLocalData == null) {
            editText.setTextSize(0, getTextAttributes().getEffectiveFontSize());
            if (getNumberOfLines() != -1) {
                editText.setLines(getNumberOfLines());
            }
            if (editText.getBreakStrategy() != getTextBreakStrategy()) {
                editText.setBreakStrategy(getTextBreakStrategy());
            }
        } else if (reactTextInputLocalData != null) {
            reactTextInputLocalData.apply(editText);
        }
        editText.setHint(this.placeholder);
        editText.measure(MeasureUtil.getMeasureSpec(width, widthMode), MeasureUtil.getMeasureSpec(height, heightMode));
        return YogaMeasureOutput.make(editText.getMeasuredWidth(), editText.getMeasuredHeight());
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void setLocalData(Object data) {
        Intrinsics.checkNotNullParameter(data, "data");
        Assertions.assertCondition(data instanceof ReactTextInputLocalData);
        this.localData = (ReactTextInputLocalData) data;
        dirty();
    }

    @ReactProp(name = "mostRecentEventCount")
    public final void setMostRecentEventCount(int mostRecentEventCount) {
        this.mostRecentEventCount = mostRecentEventCount;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x002b, code lost:
    
        if (r4.equals("simple") == false) goto L21;
     */
    @Override // com.facebook.react.views.text.ReactBaseTextShadowNode
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setTextBreakStrategy(java.lang.String r4) {
        /*
            r3 = this;
            r0 = 0
            if (r4 == 0) goto L53
            int r1 = r4.hashCode()
            r2 = -1924829944(0xffffffff8d456d08, float:-6.0836553E-31)
            if (r1 == r2) goto L2e
            r2 = -902286926(0xffffffffca3831b2, float:-3017836.5)
            if (r1 == r2) goto L25
            r2 = 336871677(0x141440fd, float:7.484907E-27)
            if (r1 == r2) goto L17
            goto L36
        L17:
            java.lang.String r1 = "highQuality"
            boolean r1 = r4.equals(r1)
            if (r1 != 0) goto L20
            goto L36
        L20:
            r4 = 1
            r3.setTextBreakStrategy(r4)
            return
        L25:
            java.lang.String r1 = "simple"
            boolean r1 = r4.equals(r1)
            if (r1 != 0) goto L53
            goto L36
        L2e:
            java.lang.String r1 = "balanced"
            boolean r1 = r4.equals(r1)
            if (r1 != 0) goto L4e
        L36:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Invalid textBreakStrategy: "
            r1.<init>(r2)
            java.lang.StringBuilder r4 = r1.append(r4)
            java.lang.String r4 = r4.toString()
            java.lang.String r1 = "ReactNative"
            com.facebook.common.logging.FLog.w(r1, r4)
            r3.setTextBreakStrategy(r0)
            return
        L4e:
            r4 = 2
            r3.setTextBreakStrategy(r4)
            return
        L53:
            r3.setTextBreakStrategy(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.textinput.ReactTextInputShadowNode.setTextBreakStrategy(java.lang.String):void");
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void onCollectExtraUpdates(UIViewOperationQueue uiViewOperationQueue) {
        Intrinsics.checkNotNullParameter(uiViewOperationQueue, "uiViewOperationQueue");
        super.onCollectExtraUpdates(uiViewOperationQueue);
        if (this.mostRecentEventCount != -1) {
            uiViewOperationQueue.enqueueUpdateExtraData(getReactTag(), new ReactTextUpdate(spannedFromShadowNode(this, this.text, false, null), this.mostRecentEventCount, getContainsImages(), getPadding(0), getPadding(1), getPadding(2), getPadding(3), getTextAlign(), getTextBreakStrategy(), getJustificationMode()));
        }
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void setPadding(int spacingType, float padding) {
        super.setPadding(spacingType, padding);
        markUpdated();
    }

    private final EditText createInternalEditText() {
        return new EditText(new ContextThemeWrapper(getThemedContext(), R.style.Theme_ReactNative_TextInput_DefaultBackground));
    }

    static {
        LegacyArchitectureLogger.assertLegacyArchitecture("ReactTextInputShadowNode", LegacyArchitectureLogLevel.ERROR);
    }
}
