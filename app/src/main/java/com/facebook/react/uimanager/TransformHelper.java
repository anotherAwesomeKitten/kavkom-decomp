package com.facebook.react.uimanager;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.NativeArray;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.ReactConstants;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: TransformHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0006H\u0007J2\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u0010H\u0007J:\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0016\u001a\u00020\u0017H\u0007J\u0018\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\bH\u0002J,\u0010\u001b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J3\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u001d2\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u001dH\u0083 R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/facebook/react/uimanager/TransformHelper;", "", "<init>", "()V", "helperMatrix", "Ljava/lang/ThreadLocal;", "", "convertToRadians", "", "transformMap", "Lcom/facebook/react/bridge/ReadableMap;", "key", "", "processTransform", "", "transforms", "Lcom/facebook/react/bridge/ReadableArray;", "result", "viewWidth", "", "viewHeight", ViewProps.TRANSFORM_ORIGIN, "allowPercentageResolution", "", "parseTranslateValue", "stringValue", TypedValues.Custom.S_DIMENSION, "getTranslateForTransformOrigin", "nativeProcessTransform", "Lcom/facebook/react/bridge/NativeArray;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TransformHelper {
    public static final TransformHelper INSTANCE = new TransformHelper();
    private static final ThreadLocal<double[]> helperMatrix = new ThreadLocal<double[]>() { // from class: com.facebook.react.uimanager.TransformHelper$helperMatrix$1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public double[] initialValue() {
            return new double[16];
        }
    };

    /* JADX INFO: compiled from: TransformHelper.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ReadableType.values().length];
            try {
                iArr[ReadableType.Number.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReadableType.String.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmStatic
    private static final native void nativeProcessTransform(NativeArray transforms, double[] result, float viewWidth, float viewHeight, NativeArray transformOrigin);

    private TransformHelper() {
    }

    private final double convertToRadians(ReadableMap transformMap, String key) {
        double d;
        boolean z = true;
        if (transformMap.getType(key) == ReadableType.String) {
            String string = transformMap.getString(key);
            Intrinsics.checkNotNull(string);
            if (StringsKt.endsWith$default(string, "rad", false, 2, (Object) null)) {
                string = StringsKt.dropLast(string, 3);
            } else if (StringsKt.endsWith$default(string, "deg", false, 2, (Object) null)) {
                string = StringsKt.dropLast(string, 3);
                z = false;
            }
            d = Double.parseDouble(string);
        } else {
            d = transformMap.getDouble(key);
        }
        return z ? d : MatrixMathHelper.degreesToRadians(d);
    }

    @Deprecated(message = "Use processTransform(ReadableArray, DoubleArray, Float, Float, ReadableArray, Boolean) instead", replaceWith = @ReplaceWith(expression = "processTransform(...)", imports = {}))
    @JvmStatic
    public static final void processTransform(ReadableArray transforms, double[] result) {
        Intrinsics.checkNotNullParameter(transforms, "transforms");
        Intrinsics.checkNotNullParameter(result, "result");
        processTransform(transforms, result, 0.0f, 0.0f, null, false);
    }

    @Deprecated(message = "Use processTransform(ReadableArray, DoubleArray, Float, Float, ReadableArray, Boolean) instead", replaceWith = @ReplaceWith(expression = "processTransform(...)", imports = {}))
    @JvmStatic
    public static final void processTransform(ReadableArray transforms, double[] result, float viewWidth, float viewHeight, ReadableArray transformOrigin) {
        Intrinsics.checkNotNullParameter(transforms, "transforms");
        Intrinsics.checkNotNullParameter(result, "result");
        processTransform(transforms, result, viewWidth, viewHeight, transformOrigin, false);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0297 A[PHI: r6 r7 r18
      0x0297: PHI (r6v20 char) = 
      (r6v4 char)
      (r6v5 char)
      (r6v6 char)
      (r6v7 char)
      (r6v8 char)
      (r6v9 char)
      (r6v10 char)
      (r6v11 char)
      (r6v12 char)
      (r6v13 char)
      (r6v21 char)
     binds: [B:105:0x0271, B:96:0x023f, B:90:0x021c, B:86:0x0202, B:82:0x01ea, B:78:0x01d2, B:74:0x01b7, B:70:0x019d, B:66:0x0183, B:62:0x016b, B:29:0x00a6] A[DONT_GENERATE, DONT_INLINE]
      0x0297: PHI (r7v26 int) = 
      (r7v4 int)
      (r7v5 int)
      (r7v6 int)
      (r7v7 int)
      (r7v8 int)
      (r7v9 int)
      (r7v10 int)
      (r7v11 int)
      (r7v12 int)
      (r7v13 int)
      (r7v27 int)
     binds: [B:105:0x0271, B:96:0x023f, B:90:0x021c, B:86:0x0202, B:82:0x01ea, B:78:0x01d2, B:74:0x01b7, B:70:0x019d, B:66:0x0183, B:62:0x016b, B:29:0x00a6] A[DONT_GENERATE, DONT_INLINE]
      0x0297: PHI (r18v18 int) = 
      (r18v0 int)
      (r18v1 int)
      (r18v2 int)
      (r18v3 int)
      (r18v4 int)
      (r18v5 int)
      (r18v6 int)
      (r18v7 int)
      (r18v8 int)
      (r18v9 int)
      (r18v19 int)
     binds: [B:105:0x0271, B:96:0x023f, B:90:0x021c, B:86:0x0202, B:82:0x01ea, B:78:0x01d2, B:74:0x01b7, B:70:0x019d, B:66:0x0183, B:62:0x016b, B:29:0x00a6] A[DONT_GENERATE, DONT_INLINE]] */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void processTransform(com.facebook.react.bridge.ReadableArray r22, double[] r23, float r24, float r25, com.facebook.react.bridge.ReadableArray r26, boolean r27) {
        /*
            Method dump skipped, instruction units count: 778
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.TransformHelper.processTransform(com.facebook.react.bridge.ReadableArray, double[], float, float, com.facebook.react.bridge.ReadableArray, boolean):void");
    }

    private final double parseTranslateValue(String stringValue, double dimension) {
        try {
            if (StringsKt.endsWith$default(stringValue, "%", false, 2, (Object) null)) {
                return (Double.parseDouble(StringsKt.dropLast(stringValue, 1)) * dimension) / 100.0d;
            }
            return Double.parseDouble(stringValue);
        } catch (NumberFormatException unused) {
            FLog.w(ReactConstants.TAG, "Invalid translate value: " + stringValue);
            return 0.0d;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final double[] getTranslateForTransformOrigin(float r21, float r22, com.facebook.react.bridge.ReadableArray r23, boolean r24) {
        /*
            r20 = this;
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = 0
            if (r2 == 0) goto L99
            r4 = 0
            int r5 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r5 != 0) goto L14
            int r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r4 != 0) goto L14
            goto L99
        L14:
            double r4 = (double) r0
            r6 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r4 = r4 / r6
            double r8 = (double) r1
            double r8 = r8 / r6
            r6 = 3
            double[] r7 = new double[r6]
            r10 = 0
            r7[r10] = r4
            r11 = 1
            r7[r11] = r8
            r12 = 0
            r14 = 2
            r7[r14] = r12
            int r12 = r2.size()
            int r12 = java.lang.Math.min(r12, r6)
            r13 = r10
        L31:
            if (r13 >= r12) goto L82
            com.facebook.react.bridge.ReadableType r15 = r2.getType(r13)
            int[] r16 = com.facebook.react.uimanager.TransformHelper.WhenMappings.$EnumSwitchMapping$0
            int r15 = r15.ordinal()
            r15 = r16[r15]
            if (r15 == r11) goto L72
            if (r15 == r14) goto L47
        L43:
            r15 = r10
            r19 = r11
            goto L7b
        L47:
            if (r24 == 0) goto L43
            java.lang.String r15 = r2.getString(r13)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r15)
            java.lang.String r6 = "%"
            boolean r6 = kotlin.text.StringsKt.endsWith$default(r15, r6, r10, r14, r3)
            if (r6 == 0) goto L43
            java.lang.String r6 = kotlin.text.StringsKt.dropLast(r15, r11)
            double r17 = java.lang.Double.parseDouble(r6)
            if (r13 != 0) goto L64
            r6 = r0
            goto L65
        L64:
            r6 = r1
        L65:
            r15 = r10
            r19 = r11
            double r10 = (double) r6
            double r10 = r10 * r17
            r17 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r10 = r10 / r17
            r7[r13] = r10
            goto L7b
        L72:
            r15 = r10
            r19 = r11
            double r10 = r2.getDouble(r13)
            r7[r13] = r10
        L7b:
            int r13 = r13 + 1
            r10 = r15
            r11 = r19
            r6 = 3
            goto L31
        L82:
            r15 = r10
            r19 = r11
            double r0 = -r4
            r2 = r7[r15]
            double r0 = r0 + r2
            double r2 = -r8
            r4 = r7[r19]
            double r2 = r2 + r4
            r4 = r7[r14]
            r6 = 3
            double[] r6 = new double[r6]
            r6[r15] = r0
            r6[r19] = r2
            r6[r14] = r4
            return r6
        L99:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.TransformHelper.getTranslateForTransformOrigin(float, float, com.facebook.react.bridge.ReadableArray, boolean):double[]");
    }
}
