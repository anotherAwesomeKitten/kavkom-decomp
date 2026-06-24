package com.facebook.react.devsupport.perfmonitor;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: PerfMonitorOverlayManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* synthetic */ class PerfMonitorOverlayManager$enable$1$1 extends FunctionReferenceImpl implements Function0<Unit> {
    PerfMonitorOverlayManager$enable$1$1(Object obj) {
        super(0, obj, PerfMonitorOverlayManager.class, "handleRecordingButtonPress", "handleRecordingButtonPress()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        ((PerfMonitorOverlayManager) this.receiver).handleRecordingButtonPress();
    }
}
