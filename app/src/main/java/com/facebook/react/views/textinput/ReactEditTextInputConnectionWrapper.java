package com.facebook.react.views.textinput;

import android.text.Editable;
import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactEditTextInputConnectionWrapper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \"2\u00020\u0001:\u0001\"B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\u0010\u001a\u00020\rH\u0016J\b\u0010\u0011\u001a\u00020\rH\u0016J\u0018\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u0016H\u0016J\u0010\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u000fH\u0002J\u0010\u0010!\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u000fH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/facebook/react/views/textinput/ReactEditTextInputConnectionWrapper;", "Landroid/view/inputmethod/InputConnectionWrapper;", TypedValues.Attributes.S_TARGET, "Landroid/view/inputmethod/InputConnection;", "reactContext", "Lcom/facebook/react/bridge/ReactContext;", "editText", "Lcom/facebook/react/views/textinput/ReactEditText;", "eventDispatcher", "Lcom/facebook/react/uimanager/events/EventDispatcher;", "<init>", "(Landroid/view/inputmethod/InputConnection;Lcom/facebook/react/bridge/ReactContext;Lcom/facebook/react/views/textinput/ReactEditText;Lcom/facebook/react/uimanager/events/EventDispatcher;)V", "isBatchEdit", "", "key", "", "beginBatchEdit", "endBatchEdit", "setComposingText", ReactTextInputShadowNode.PROP_TEXT, "", "newCursorPosition", "", "commitText", "deleteSurroundingText", "beforeLength", "afterLength", "sendKeyEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "dispatchKeyEventOrEnqueue", "", "inputKey", "dispatchKeyEvent", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ReactEditTextInputConnectionWrapper extends InputConnectionWrapper {
    public static final String BACKSPACE_KEY_VALUE = "Backspace";
    public static final String ENTER_KEY_VALUE = "Enter";
    public static final String NEWLINE_RAW_VALUE = "\n";
    private final ReactEditText editText;
    private final EventDispatcher eventDispatcher;
    private boolean isBatchEdit;
    private String key;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReactEditTextInputConnectionWrapper(InputConnection target, ReactContext reactContext, ReactEditText editText, EventDispatcher eventDispatcher) {
        super(target, false);
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(editText, "editText");
        Intrinsics.checkNotNullParameter(eventDispatcher, "eventDispatcher");
        this.editText = editText;
        this.eventDispatcher = eventDispatcher;
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean beginBatchEdit() {
        this.isBatchEdit = true;
        return super.beginBatchEdit();
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean endBatchEdit() {
        this.isBatchEdit = false;
        String str = this.key;
        if (str != null) {
            dispatchKeyEvent(str);
            this.key = null;
        }
        return super.endBatchEdit();
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean setComposingText(CharSequence text, int newCursorPosition) {
        String strValueOf;
        Intrinsics.checkNotNullParameter(text, "text");
        int selectionStart = this.editText.getSelectionStart();
        int selectionEnd = this.editText.getSelectionEnd();
        boolean composingText = super.setComposingText(text, newCursorPosition);
        int selectionStart2 = this.editText.getSelectionStart();
        boolean z = selectionStart == selectionEnd;
        boolean z2 = selectionStart2 == selectionStart;
        if (selectionStart2 < selectionStart || selectionStart2 <= 0 || (!z && z2)) {
            strValueOf = BACKSPACE_KEY_VALUE;
        } else {
            Editable text2 = this.editText.getText();
            strValueOf = String.valueOf(text2 != null ? Character.valueOf(text2.charAt(selectionStart2 - 1)) : null);
        }
        dispatchKeyEventOrEnqueue(strValueOf);
        return composingText;
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean commitText(CharSequence text, int newCursorPosition) {
        Intrinsics.checkNotNullParameter(text, "text");
        String string = text.toString();
        if (string.length() <= 2) {
            if (string.length() == 0) {
                string = BACKSPACE_KEY_VALUE;
            }
            dispatchKeyEventOrEnqueue(string);
        }
        return super.commitText(text, newCursorPosition);
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean deleteSurroundingText(int beforeLength, int afterLength) {
        dispatchKeyEvent(BACKSPACE_KEY_VALUE);
        return super.deleteSurroundingText(beforeLength, afterLength);
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public boolean sendKeyEvent(KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getAction() == 0) {
            int unicodeChar = event.getUnicodeChar();
            boolean z = false;
            if (48 <= unicodeChar && unicodeChar < 58) {
                z = true;
            }
            int keyCode = event.getKeyCode();
            if (keyCode == 66) {
                dispatchKeyEvent(ENTER_KEY_VALUE);
            } else if (keyCode == 67) {
                dispatchKeyEvent(BACKSPACE_KEY_VALUE);
            } else if (z) {
                dispatchKeyEvent(String.valueOf(event.getNumber()));
            }
        }
        return super.sendKeyEvent(event);
    }

    private final void dispatchKeyEventOrEnqueue(String inputKey) {
        if (this.isBatchEdit) {
            this.key = inputKey;
        } else {
            dispatchKeyEvent(inputKey);
        }
    }

    private final void dispatchKeyEvent(String inputKey) {
        if (Intrinsics.areEqual(inputKey, NEWLINE_RAW_VALUE)) {
            inputKey = ENTER_KEY_VALUE;
        }
        this.eventDispatcher.dispatchEvent(new ReactTextInputKeyPressEvent(UIManagerHelper.getSurfaceId(this.editText), this.editText.getId(), inputKey));
    }
}
