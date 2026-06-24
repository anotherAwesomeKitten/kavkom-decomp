package com.facebook.react.views.text.internal.span;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.text.PreparedLayout;
import com.facebook.react.views.text.PreparedLayoutTextView;
import com.facebook.react.views.view.ViewGroupClickEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactLinkSpan.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Lcom/facebook/react/views/text/internal/span/ReactLinkSpan;", "Landroid/text/style/ClickableSpan;", "Lcom/facebook/react/views/text/internal/span/ReactSpan;", "fragmentIndex", "", "<init>", "(I)V", "getFragmentIndex", "()I", ViewProps.ON_CLICK, "", "view", "Landroid/view/View;", "updateDrawState", "ds", "Landroid/text/TextPaint;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ReactLinkSpan extends ClickableSpan implements ReactSpan {
    private final int fragmentIndex;

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint ds) {
        Intrinsics.checkNotNullParameter(ds, "ds");
    }

    public ReactLinkSpan(int i) {
        this.fragmentIndex = i;
    }

    public final int getFragmentIndex() {
        return this.fragmentIndex;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        PreparedLayout preparedLayout;
        int i;
        EventDispatcher eventDispatcherForReactTag;
        Intrinsics.checkNotNullParameter(view, "view");
        Context context = view.getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        ReactContext reactContext = (ReactContext) context;
        PreparedLayoutTextView preparedLayoutTextView = view instanceof PreparedLayoutTextView ? (PreparedLayoutTextView) view : null;
        if (preparedLayoutTextView == null || (preparedLayout = preparedLayoutTextView.getPreparedLayout()) == null || (eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(reactContext, (i = preparedLayout.getReactTags()[this.fragmentIndex]))) == null) {
            return;
        }
        eventDispatcherForReactTag.dispatchEvent(new ViewGroupClickEvent(UIManagerHelper.getSurfaceId(reactContext), i));
    }
}
