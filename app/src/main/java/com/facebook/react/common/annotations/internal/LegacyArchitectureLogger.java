package com.facebook.react.common.annotations.internal;

import com.facebook.react.bridge.AssertionException;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.common.build.ReactBuildConfig;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LegacyArchitectureLogger.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007J\u001a\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0002¨\u0006\u000b"}, d2 = {"Lcom/facebook/react/common/annotations/internal/LegacyArchitectureLogger;", "", "<init>", "()V", "assertLegacyArchitecture", "", "name", "", "logLevel", "Lcom/facebook/react/common/annotations/internal/LegacyArchitectureLogLevel;", "executeAssert", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LegacyArchitectureLogger {
    public static final LegacyArchitectureLogger INSTANCE = new LegacyArchitectureLogger();

    /* JADX INFO: compiled from: LegacyArchitectureLogger.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LegacyArchitectureLogLevel.values().length];
            try {
                iArr[LegacyArchitectureLogLevel.ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LegacyArchitectureLogLevel.WARNING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private LegacyArchitectureLogger() {
    }

    public static /* synthetic */ void assertLegacyArchitecture$default(String str, LegacyArchitectureLogLevel legacyArchitectureLogLevel, int i, Object obj) {
        if ((i & 2) != 0) {
            legacyArchitectureLogLevel = LegacyArchitectureLogLevel.WARNING;
        }
        assertLegacyArchitecture(str, legacyArchitectureLogLevel);
    }

    @JvmStatic
    public static final void assertLegacyArchitecture(String name, LegacyArchitectureLogLevel logLevel) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(logLevel, "logLevel");
        if (ReactBuildConfig.UNSTABLE_ENABLE_MINIFY_LEGACY_ARCHITECTURE) {
            INSTANCE.executeAssert(name, logLevel);
        }
    }

    static /* synthetic */ void executeAssert$default(LegacyArchitectureLogger legacyArchitectureLogger, String str, LegacyArchitectureLogLevel legacyArchitectureLogLevel, int i, Object obj) {
        if ((i & 2) != 0) {
            legacyArchitectureLogLevel = LegacyArchitectureLogLevel.WARNING;
        }
        legacyArchitectureLogger.executeAssert(str, legacyArchitectureLogLevel);
    }

    private final void executeAssert(String name, LegacyArchitectureLogLevel logLevel) {
        if (ReactBuildConfig.DEBUG) {
            int i = WhenMappings.$EnumSwitchMapping$0[logLevel.ordinal()];
            if (i == 1) {
                throw new AssertionException(name + " is being executed when app is fully running on the NEW Architecture.");
            }
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            ReactSoftExceptionLogger.logSoftException(ReactSoftExceptionLogger.Categories.SOFT_ASSERTIONS, new ReactNoCrashSoftException(name + " is being executed when app is fully running on the NEW Architecture."));
        }
    }
}
