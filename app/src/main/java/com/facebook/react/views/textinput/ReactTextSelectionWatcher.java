package com.facebook.react.views.textinput;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.EventDispatcher;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactTextSelectionWatcher.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/facebook/react/views/textinput/ReactTextSelectionWatcher;", "Lcom/facebook/react/views/textinput/SelectionWatcher;", "editText", "Lcom/facebook/react/views/textinput/ReactEditText;", "<init>", "(Lcom/facebook/react/views/textinput/ReactEditText;)V", "eventDispatcher", "Lcom/facebook/react/uimanager/events/EventDispatcher;", "surfaceId", "", "previousSelectionStart", "previousSelectionEnd", "onSelectionChanged", "", ViewProps.START, ViewProps.END, "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ReactTextSelectionWatcher implements SelectionWatcher {
    private final ReactEditText editText;
    private final EventDispatcher eventDispatcher;
    private int previousSelectionEnd;
    private int previousSelectionStart;
    private final int surfaceId;

    public ReactTextSelectionWatcher(ReactEditText editText) {
        Intrinsics.checkNotNullParameter(editText, "editText");
        this.editText = editText;
        ReactContext reactContext = UIManagerHelper.getReactContext(editText);
        this.eventDispatcher = UIManagerHelper.getEventDispatcherForReactTag(reactContext, editText.getId());
        this.surfaceId = UIManagerHelper.getSurfaceId(reactContext);
    }

    @Override // com.facebook.react.views.textinput.SelectionWatcher
    public void onSelectionChanged(int start, int end) {
        double d = start;
        double d2 = end;
        int iMin = (int) Math.min(d, d2);
        int iMax = (int) Math.max(d, d2);
        if (this.previousSelectionStart == iMin && this.previousSelectionEnd == iMax) {
            return;
        }
        EventDispatcher eventDispatcher = this.eventDispatcher;
        if (eventDispatcher != null) {
            eventDispatcher.dispatchEvent(new ReactTextInputSelectionEvent(this.surfaceId, this.editText.getId(), iMin, iMax));
        }
        this.previousSelectionStart = iMin;
        this.previousSelectionEnd = iMax;
    }
}
