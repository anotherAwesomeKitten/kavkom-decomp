package com.facebook.react.views.textinput;

import android.text.SpannableStringBuilder;
import android.widget.EditText;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactTextInputLocalData.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u0003R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/facebook/react/views/textinput/ReactTextInputLocalData;", "", "editText", "Landroid/widget/EditText;", "<init>", "(Landroid/widget/EditText;)V", ReactTextInputShadowNode.PROP_TEXT, "Landroid/text/SpannableStringBuilder;", "textSize", "", "minLines", "", "maxLines", "inputType", "breakStrategy", ReactTextInputShadowNode.PROP_PLACEHOLDER, "", "apply", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ReactTextInputLocalData {
    private final int breakStrategy;
    private final int inputType;
    private final int maxLines;
    private final int minLines;
    private final CharSequence placeholder;
    private final SpannableStringBuilder text;
    private final float textSize;

    public ReactTextInputLocalData(EditText editText) {
        Intrinsics.checkNotNullParameter(editText, "editText");
        this.text = new SpannableStringBuilder(editText.getText());
        this.textSize = editText.getTextSize();
        this.minLines = editText.getMinLines();
        this.maxLines = editText.getMaxLines();
        this.inputType = editText.getInputType();
        this.breakStrategy = editText.getBreakStrategy();
        this.placeholder = editText.getHint();
    }

    public final void apply(EditText editText) {
        Intrinsics.checkNotNullParameter(editText, "editText");
        editText.setText(this.text);
        editText.setTextSize(0, this.textSize);
        editText.setMinLines(this.minLines);
        editText.setMaxLines(this.maxLines);
        editText.setInputType(this.inputType);
        editText.setHint(this.placeholder);
        editText.setBreakStrategy(this.breakStrategy);
    }
}
