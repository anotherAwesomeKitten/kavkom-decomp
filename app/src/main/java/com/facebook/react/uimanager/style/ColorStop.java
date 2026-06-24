package com.facebook.react.uimanager.style;

import com.facebook.react.uimanager.LengthPercentage;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ColorStop.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/facebook/react/uimanager/style/ColorStop;", "", "color", "", ViewProps.POSITION, "Lcom/facebook/react/uimanager/LengthPercentage;", "<init>", "(Ljava/lang/Integer;Lcom/facebook/react/uimanager/LengthPercentage;)V", "getColor", "()Ljava/lang/Integer;", "setColor", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getPosition", "()Lcom/facebook/react/uimanager/LengthPercentage;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ColorStop {
    private Integer color;
    private final LengthPercentage position;

    public ColorStop() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public ColorStop(Integer num, LengthPercentage lengthPercentage) {
        this.color = num;
        this.position = lengthPercentage;
    }

    public /* synthetic */ ColorStop(Integer num, LengthPercentage lengthPercentage, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : lengthPercentage);
    }

    public final Integer getColor() {
        return this.color;
    }

    public final LengthPercentage getPosition() {
        return this.position;
    }

    public final void setColor(Integer num) {
        this.color = num;
    }
}
