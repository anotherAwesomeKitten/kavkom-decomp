package com.facebook.react.uimanager.style;

import com.facebook.react.uimanager.LengthPercentage;
import com.facebook.react.uimanager.LengthPercentageType;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ColorStop.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\u0006\u0010\t\u001a\u00020\nJ!\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\rH\u0002¢\u0006\u0002\u0010\u000eJ!\u0010\u000f\u001a\u0004\u0018\u00010\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\t\u001a\u00020\nH\u0002¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/facebook/react/uimanager/style/ColorStopUtils;", "", "<init>", "()V", "getFixedColorStops", "", "Lcom/facebook/react/uimanager/style/ProcessedColorStop;", "colorStops", "Lcom/facebook/react/uimanager/style/ColorStop;", "gradientLineLength", "", "processColorTransitionHints", "originalStops", "", "([Lcom/facebook/react/uimanager/style/ProcessedColorStop;)Ljava/util/List;", "resolveColorStopPosition", ViewProps.POSITION, "Lcom/facebook/react/uimanager/LengthPercentage;", "(Lcom/facebook/react/uimanager/LengthPercentage;F)Ljava/lang/Float;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ColorStopUtils {
    public static final ColorStopUtils INSTANCE = new ColorStopUtils();

    /* JADX INFO: compiled from: ColorStop.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LengthPercentageType.values().length];
            try {
                iArr[LengthPercentageType.POINT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LengthPercentageType.PERCENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private ColorStopUtils() {
    }

    public final List<ProcessedColorStop> getFixedColorStops(List<ColorStop> colorStops, float gradientLineLength) {
        Intrinsics.checkNotNullParameter(colorStops, "colorStops");
        int size = colorStops.size();
        ProcessedColorStop[] processedColorStopArr = new ProcessedColorStop[size];
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            processedColorStopArr[i2] = new ProcessedColorStop(null, null, 3, null);
        }
        Float fResolveColorStopPosition = resolveColorStopPosition(colorStops.get(0).getPosition(), gradientLineLength);
        float fFloatValue = fResolveColorStopPosition != null ? fResolveColorStopPosition.floatValue() : 0.0f;
        int size2 = colorStops.size();
        int i3 = 0;
        boolean z = false;
        while (i3 < size2) {
            ColorStop colorStop = colorStops.get(i3);
            Float fResolveColorStopPosition2 = resolveColorStopPosition(colorStop.getPosition(), gradientLineLength);
            if (fResolveColorStopPosition2 == null) {
                if (i3 == 0) {
                    fResolveColorStopPosition2 = Float.valueOf(0.0f);
                } else {
                    fResolveColorStopPosition2 = i3 == colorStops.size() - 1 ? Float.valueOf(1.0f) : null;
                }
            }
            if (fResolveColorStopPosition2 != null) {
                Float fValueOf = Float.valueOf(Math.max(fResolveColorStopPosition2.floatValue(), fFloatValue));
                processedColorStopArr[i3] = new ProcessedColorStop(colorStop.getColor(), fValueOf);
                fFloatValue = fValueOf.floatValue();
            } else {
                z = true;
            }
            i3++;
        }
        if (z) {
            for (int i4 = 1; i4 < size; i4++) {
                Float position = processedColorStopArr[i4].getPosition();
                Float position2 = processedColorStopArr[i].getPosition();
                int i5 = i4 - i;
                int i6 = i5 - 1;
                if (position != null && position2 != null && i6 > 0) {
                    float fFloatValue2 = (position.floatValue() - position2.floatValue()) / i5;
                    if (1 <= i6) {
                        int i7 = 1;
                        while (true) {
                            int i8 = i + i7;
                            processedColorStopArr[i8] = new ProcessedColorStop(colorStops.get(i8).getColor(), Float.valueOf(position2.floatValue() + (i7 * fFloatValue2)));
                            if (i7 == i6) {
                                break;
                            }
                            i7++;
                        }
                    }
                    i = i4;
                }
            }
        }
        return processColorTransitionHints(processedColorStopArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:90:0x007e A[PHI: r6
      0x007e: PHI (r6v2 int) = (r6v1 int), (r6v1 int), (r6v1 int), (r6v1 int), (r6v1 int), (r6v1 int), (r6v1 int), (r6v5 int) binds: [B:77:0x0013, B:80:0x0018, B:83:0x003d, B:84:0x003f, B:85:0x0041, B:96:0x0099, B:93:0x0089, B:89:0x0079] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.util.List<com.facebook.react.uimanager.style.ProcessedColorStop> processColorTransitionHints(com.facebook.react.uimanager.style.ProcessedColorStop[] r23) {
        /*
            Method dump skipped, instruction units count: 473
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.style.ColorStopUtils.processColorTransitionHints(com.facebook.react.uimanager.style.ProcessedColorStop[]):java.util.List");
    }

    private final Float resolveColorStopPosition(LengthPercentage lengthPercentage, float gradientLineLength) {
        if (lengthPercentage == null) {
            return null;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[lengthPercentage.getType().ordinal()];
        if (i == 1) {
            return Float.valueOf(PixelUtil.toPixelFromDIP(lengthPercentage.resolve(0.0f)) / gradientLineLength);
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        return Float.valueOf(lengthPercentage.resolve(1.0f));
    }
}
