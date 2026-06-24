package com.facebook.react.uimanager.style;

import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.LengthPercentage;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: BackgroundSize.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\u000bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u000e"}, d2 = {"Lcom/facebook/react/uimanager/style/BackgroundSizeLengthPercentage;", "", "x", "Lcom/facebook/react/uimanager/LengthPercentage;", "y", "<init>", "(Lcom/facebook/react/uimanager/LengthPercentage;Lcom/facebook/react/uimanager/LengthPercentage;)V", "getX", "()Lcom/facebook/react/uimanager/LengthPercentage;", "getY", "isXAuto", "", "isYAuto", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class BackgroundSizeLengthPercentage {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final LengthPercentage x;
    private final LengthPercentage y;

    public BackgroundSizeLengthPercentage(LengthPercentage lengthPercentage, LengthPercentage lengthPercentage2) {
        this.x = lengthPercentage;
        this.y = lengthPercentage2;
    }

    public final LengthPercentage getX() {
        return this.x;
    }

    public final LengthPercentage getY() {
        return this.y;
    }

    public final boolean isXAuto() {
        return this.x == null;
    }

    public final boolean isYAuto() {
        return this.y == null;
    }

    /* JADX INFO: compiled from: BackgroundSize.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lcom/facebook/react/uimanager/style/BackgroundSizeLengthPercentage$Companion;", "", "<init>", "()V", "parse", "Lcom/facebook/react/uimanager/style/BackgroundSizeLengthPercentage;", "backgroundSizeMap", "Lcom/facebook/react/bridge/ReadableMap;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: BackgroundSize.kt */
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

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0056  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final com.facebook.react.uimanager.style.BackgroundSizeLengthPercentage parse(com.facebook.react.bridge.ReadableMap r11) {
            /*
                r10 = this;
                r0 = 0
                if (r11 != 0) goto L4
                return r0
            L4:
                java.lang.String r1 = "x"
                boolean r2 = r11.hasKey(r1)
                java.lang.String r3 = "%"
                java.lang.String r4 = "auto"
                r5 = 1
                r6 = 0
                r7 = 2
                if (r2 == 0) goto L56
                com.facebook.react.bridge.ReadableType r2 = r11.getType(r1)
                com.facebook.react.bridge.ReadableType r8 = com.facebook.react.bridge.ReadableType.Null
                if (r2 == r8) goto L56
                com.facebook.react.bridge.ReadableType r2 = r11.getType(r1)
                int[] r8 = com.facebook.react.uimanager.style.BackgroundSizeLengthPercentage.Companion.WhenMappings.$EnumSwitchMapping$0
                int r2 = r2.ordinal()
                r2 = r8[r2]
                if (r2 == r5) goto L4b
                if (r2 == r7) goto L2d
                goto L56
            L2d:
                java.lang.String r2 = r11.getString(r1)
                boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r4)
                if (r8 == 0) goto L38
                goto L56
            L38:
                if (r2 == 0) goto L56
                boolean r2 = kotlin.text.StringsKt.endsWith$default(r2, r3, r6, r7, r0)
                if (r2 == 0) goto L56
                com.facebook.react.uimanager.LengthPercentage$Companion r2 = com.facebook.react.uimanager.LengthPercentage.INSTANCE
                com.facebook.react.bridge.Dynamic r1 = r11.getDynamic(r1)
                com.facebook.react.uimanager.LengthPercentage r1 = com.facebook.react.uimanager.LengthPercentage.Companion.setFromDynamic$default(r2, r1, r6, r7, r0)
                goto L57
            L4b:
                com.facebook.react.uimanager.LengthPercentage$Companion r2 = com.facebook.react.uimanager.LengthPercentage.INSTANCE
                com.facebook.react.bridge.Dynamic r1 = r11.getDynamic(r1)
                com.facebook.react.uimanager.LengthPercentage r1 = com.facebook.react.uimanager.LengthPercentage.Companion.setFromDynamic$default(r2, r1, r6, r7, r0)
                goto L57
            L56:
                r1 = r0
            L57:
                java.lang.String r2 = "y"
                boolean r8 = r11.hasKey(r2)
                if (r8 == 0) goto La1
                com.facebook.react.bridge.ReadableType r8 = r11.getType(r2)
                com.facebook.react.bridge.ReadableType r9 = com.facebook.react.bridge.ReadableType.Null
                if (r8 == r9) goto La1
                com.facebook.react.bridge.ReadableType r8 = r11.getType(r2)
                int[] r9 = com.facebook.react.uimanager.style.BackgroundSizeLengthPercentage.Companion.WhenMappings.$EnumSwitchMapping$0
                int r8 = r8.ordinal()
                r8 = r9[r8]
                if (r8 == r5) goto L97
                if (r8 == r7) goto L79
                goto La1
            L79:
                java.lang.String r5 = r11.getString(r2)
                boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r4)
                if (r4 == 0) goto L84
                goto La1
            L84:
                if (r5 == 0) goto La1
                boolean r3 = kotlin.text.StringsKt.endsWith$default(r5, r3, r6, r7, r0)
                if (r3 == 0) goto La1
                com.facebook.react.uimanager.LengthPercentage$Companion r3 = com.facebook.react.uimanager.LengthPercentage.INSTANCE
                com.facebook.react.bridge.Dynamic r11 = r11.getDynamic(r2)
                com.facebook.react.uimanager.LengthPercentage r0 = com.facebook.react.uimanager.LengthPercentage.Companion.setFromDynamic$default(r3, r11, r6, r7, r0)
                goto La1
            L97:
                com.facebook.react.uimanager.LengthPercentage$Companion r3 = com.facebook.react.uimanager.LengthPercentage.INSTANCE
                com.facebook.react.bridge.Dynamic r11 = r11.getDynamic(r2)
                com.facebook.react.uimanager.LengthPercentage r0 = com.facebook.react.uimanager.LengthPercentage.Companion.setFromDynamic$default(r3, r11, r6, r7, r0)
            La1:
                com.facebook.react.uimanager.style.BackgroundSizeLengthPercentage r11 = new com.facebook.react.uimanager.style.BackgroundSizeLengthPercentage
                r11.<init>(r1, r0)
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.style.BackgroundSizeLengthPercentage.Companion.parse(com.facebook.react.bridge.ReadableMap):com.facebook.react.uimanager.style.BackgroundSizeLengthPercentage");
        }
    }
}
