package com.facebook.yoga;

import com.facebook.react.uimanager.ViewProps;
import io.sentry.SentryEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: YogaConfig.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b&\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\tH&J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH&J\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H&J\b\u0010\u0012\u001a\u00020\u0011H&J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0015H&J\b\u0010\u0016\u001a\u00020\u0015H&J\b\u0010\u0017\u001a\u00020\u0018H$¨\u0006\u001a"}, d2 = {"Lcom/facebook/yoga/YogaConfig;", "", "<init>", "()V", "setExperimentalFeatureEnabled", "", "feature", "Lcom/facebook/yoga/YogaExperimentalFeature;", ViewProps.ENABLED, "", "setUseWebDefaults", "useWebDefaults", "setPointScaleFactor", "pixelsInPoint", "", "setErrata", "errata", "Lcom/facebook/yoga/YogaErrata;", "getErrata", "setLogger", SentryEvent.JsonKeys.LOGGER, "Lcom/facebook/yoga/YogaLogger;", "getLogger", "getNativePointer", "", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class YogaConfig {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static int SPACING_TYPE = 1;

    public abstract YogaErrata getErrata();

    public abstract YogaLogger getLogger();

    protected abstract long getNativePointer();

    public abstract void setErrata(YogaErrata errata);

    public abstract void setExperimentalFeatureEnabled(YogaExperimentalFeature feature, boolean z);

    public abstract void setLogger(YogaLogger yogaLogger);

    public abstract void setPointScaleFactor(float pixelsInPoint);

    public abstract void setUseWebDefaults(boolean useWebDefaults);

    /* JADX INFO: compiled from: YogaConfig.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/facebook/yoga/YogaConfig$Companion;", "", "<init>", "()V", "SPACING_TYPE", "", "getSPACING_TYPE", "()I", "setSPACING_TYPE", "(I)V", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getSPACING_TYPE() {
            return YogaConfig.SPACING_TYPE;
        }

        public final void setSPACING_TYPE(int i) {
            YogaConfig.SPACING_TYPE = i;
        }
    }
}
