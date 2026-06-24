package com.facebook.react.views.image;

import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: ImageResizeMethod.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u0000 \b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\t"}, d2 = {"Lcom/facebook/react/views/image/ImageResizeMethod;", "", "<init>", "(Ljava/lang/String;I)V", "AUTO", "RESIZE", "SCALE", "NONE", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ImageResizeMethod {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ ImageResizeMethod[] $VALUES;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    public static final ImageResizeMethod AUTO = new ImageResizeMethod("AUTO", 0);
    public static final ImageResizeMethod RESIZE = new ImageResizeMethod("RESIZE", 1);
    public static final ImageResizeMethod SCALE = new ImageResizeMethod("SCALE", 2);
    public static final ImageResizeMethod NONE = new ImageResizeMethod("NONE", 3);

    private static final /* synthetic */ ImageResizeMethod[] $values() {
        return new ImageResizeMethod[]{AUTO, RESIZE, SCALE, NONE};
    }

    public static EnumEntries<ImageResizeMethod> getEntries() {
        return $ENTRIES;
    }

    @JvmStatic
    public static final ImageResizeMethod parse(String str) {
        return INSTANCE.parse(str);
    }

    private ImageResizeMethod(String str, int i) {
    }

    static {
        ImageResizeMethod[] imageResizeMethodArr$values = $values();
        $VALUES = imageResizeMethodArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(imageResizeMethodArr$values);
        INSTANCE = new Companion(null);
    }

    /* JADX INFO: compiled from: ImageResizeMethod.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/facebook/react/views/image/ImageResizeMethod$Companion;", "", "<init>", "()V", "parse", "Lcom/facebook/react/views/image/ImageResizeMethod;", ViewProps.RESIZE_METHOD, "", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:24:0x003b, code lost:
        
            if (r3.equals("auto") == false) goto L31;
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x0044, code lost:
        
            if (r3.equals("") == false) goto L31;
         */
        @kotlin.jvm.JvmStatic
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final com.facebook.react.views.image.ImageResizeMethod parse(java.lang.String r3) {
            /*
                r2 = this;
                if (r3 == 0) goto L6f
                int r0 = r3.hashCode()
                r1 = -934437708(0xffffffffc84d9cb4, float:-210546.81)
                if (r0 == r1) goto L47
                if (r0 == 0) goto L3e
                r1 = 3005871(0x2dddaf, float:4.212122E-39)
                if (r0 == r1) goto L35
                r1 = 3387192(0x33af38, float:4.746467E-39)
                if (r0 == r1) goto L29
                r1 = 109250890(0x683094a, float:4.929037E-35)
                if (r0 == r1) goto L1d
                goto L4f
            L1d:
                java.lang.String r0 = "scale"
                boolean r0 = r3.equals(r0)
                if (r0 != 0) goto L26
                goto L4f
            L26:
                com.facebook.react.views.image.ImageResizeMethod r3 = com.facebook.react.views.image.ImageResizeMethod.SCALE
                return r3
            L29:
                java.lang.String r0 = "none"
                boolean r0 = r3.equals(r0)
                if (r0 != 0) goto L32
                goto L4f
            L32:
                com.facebook.react.views.image.ImageResizeMethod r3 = com.facebook.react.views.image.ImageResizeMethod.NONE
                return r3
            L35:
                java.lang.String r0 = "auto"
                boolean r0 = r3.equals(r0)
                if (r0 != 0) goto L6f
                goto L4f
            L3e:
                java.lang.String r0 = ""
                boolean r0 = r3.equals(r0)
                if (r0 != 0) goto L6f
                goto L4f
            L47:
                java.lang.String r0 = "resize"
                boolean r0 = r3.equals(r0)
                if (r0 != 0) goto L6c
            L4f:
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = "Invalid resize method: '"
                r0.<init>(r1)
                java.lang.StringBuilder r3 = r0.append(r3)
                java.lang.String r0 = "'"
                java.lang.StringBuilder r3 = r3.append(r0)
                java.lang.String r3 = r3.toString()
                java.lang.String r0 = "ReactNative"
                com.facebook.common.logging.FLog.w(r0, r3)
                com.facebook.react.views.image.ImageResizeMethod r3 = com.facebook.react.views.image.ImageResizeMethod.AUTO
                return r3
            L6c:
                com.facebook.react.views.image.ImageResizeMethod r3 = com.facebook.react.views.image.ImageResizeMethod.RESIZE
                return r3
            L6f:
                com.facebook.react.views.image.ImageResizeMethod r3 = com.facebook.react.views.image.ImageResizeMethod.AUTO
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.image.ImageResizeMethod.Companion.parse(java.lang.String):com.facebook.react.views.image.ImageResizeMethod");
        }
    }

    public static ImageResizeMethod valueOf(String str) {
        return (ImageResizeMethod) Enum.valueOf(ImageResizeMethod.class, str);
    }

    public static ImageResizeMethod[] values() {
        return (ImageResizeMethod[]) $VALUES.clone();
    }
}
