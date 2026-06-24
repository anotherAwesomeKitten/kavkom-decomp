package com.facebook.react.views.textinput;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactTextContentSizeWatcher.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/facebook/react/views/textinput/ReactTextContentSizeWatcher;", "Lcom/facebook/react/views/textinput/ContentSizeWatcher;", "editText", "Lcom/facebook/react/views/textinput/ReactEditText;", "<init>", "(Lcom/facebook/react/views/textinput/ReactEditText;)V", "eventDispatcher", "Lcom/facebook/react/uimanager/events/EventDispatcher;", "surfaceId", "", "previousContentWidth", "previousContentHeight", "onLayout", "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ReactTextContentSizeWatcher implements ContentSizeWatcher {
    private final ReactEditText editText;
    private final EventDispatcher eventDispatcher;
    private int previousContentHeight;
    private int previousContentWidth;
    private final int surfaceId;

    public ReactTextContentSizeWatcher(ReactEditText editText) {
        Intrinsics.checkNotNullParameter(editText, "editText");
        this.editText = editText;
        ReactContext reactContext = UIManagerHelper.getReactContext(editText);
        this.eventDispatcher = UIManagerHelper.getEventDispatcherForReactTag(reactContext, editText.getId());
        this.surfaceId = UIManagerHelper.getSurfaceId(reactContext);
    }

    @Override // com.facebook.react.views.textinput.ContentSizeWatcher
    public void onLayout() {
        int width = this.editText.getWidth();
        int height = this.editText.getHeight();
        if (this.editText.getLayout() != null) {
            width = this.editText.getCompoundPaddingLeft() + this.editText.getLayout().getWidth() + this.editText.getCompoundPaddingRight();
            height = this.editText.getCompoundPaddingTop() + this.editText.getLayout().getHeight() + this.editText.getCompoundPaddingBottom();
        }
        if (width == this.previousContentWidth && height == this.previousContentHeight) {
            return;
        }
        this.previousContentHeight = height;
        this.previousContentWidth = width;
        EventDispatcher eventDispatcher = this.eventDispatcher;
        if (eventDispatcher != null) {
            eventDispatcher.dispatchEvent(new ReactContentSizeChangedEvent(this.surfaceId, this.editText.getId(), PixelUtil.toDIPFromPixel(width), PixelUtil.toDIPFromPixel(height)));
        }
    }
}
