package com.facebook.react.uimanager.events;

import android.view.MotionEvent;
import android.view.View;
import androidx.core.app.NotificationCompat;
import com.facebook.react.R;
import io.sentry.rrweb.RRWebInteractionEvent;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PointerEventHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001,B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0014\u001a\u00020\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\nH\u0007J \u0010\u0018\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\nH\u0007J\u0010\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\nH\u0007J\u001a\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\"H\u0007J\u0012\u0010#\u001a\u00020\n2\b\u0010$\u001a\u0004\u0018\u00010\u0005H\u0007J\u000e\u0010%\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020'J\u0010\u0010(\u001a\u00020\u001e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005J\u001a\u0010)\u001a\u00020*2\u0006\u0010\u0017\u001a\u00020\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005H\u0007J\u0012\u0010+\u001a\u00020\u001e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/facebook/react/uimanager/events/PointerEventHelper;", "", "<init>", "()V", "POINTER_TYPE_TOUCH", "", "POINTER_TYPE_PEN", "POINTER_TYPE_MOUSE", "POINTER_TYPE_UNKNOWN", "X_FLAG_SUPPORTS_HOVER", "", "POINTER_CANCEL", "POINTER_DOWN", "POINTER_ENTER", "POINTER_LEAVE", "POINTER_MOVE", "POINTER_UP", "POINTER_OVER", "POINTER_OUT", "CLICK", "getButtons", "eventName", RRWebInteractionEvent.JsonKeys.POINTER_TYPE, "buttonState", "getButtonChange", "lastButtonState", "currentButtonState", "getW3CPointerType", "toolType", "isListening", "", "view", "Landroid/view/View;", NotificationCompat.CATEGORY_EVENT, "Lcom/facebook/react/uimanager/events/PointerEventHelper$EVENT;", "getEventCategory", "pointerEventType", "supportsHover", "motionEvent", "Landroid/view/MotionEvent;", "isExitEvent", "getPressure", "", "isBubblingEvent", "EVENT", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PointerEventHelper {
    public static final String CLICK = "topClick";
    public static final PointerEventHelper INSTANCE = new PointerEventHelper();
    public static final String POINTER_CANCEL = "topPointerCancel";
    public static final String POINTER_DOWN = "topPointerDown";
    public static final String POINTER_ENTER = "topPointerEnter";
    public static final String POINTER_LEAVE = "topPointerLeave";
    public static final String POINTER_MOVE = "topPointerMove";
    public static final String POINTER_OUT = "topPointerOut";
    public static final String POINTER_OVER = "topPointerOver";
    public static final String POINTER_TYPE_MOUSE = "mouse";
    public static final String POINTER_TYPE_PEN = "pen";
    public static final String POINTER_TYPE_TOUCH = "touch";
    public static final String POINTER_TYPE_UNKNOWN = "";
    public static final String POINTER_UP = "topPointerUp";
    public static final int X_FLAG_SUPPORTS_HOVER = 16777216;

    /* JADX INFO: compiled from: PointerEventHelper.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EVENT.values().length];
            try {
                iArr[EVENT.DOWN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[EVENT.DOWN_CAPTURE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[EVENT.UP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[EVENT.UP_CAPTURE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[EVENT.CANCEL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[EVENT.CANCEL_CAPTURE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[EVENT.CLICK.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[EVENT.CLICK_CAPTURE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private PointerEventHelper() {
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: PointerEventHelper.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0015\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0016"}, d2 = {"Lcom/facebook/react/uimanager/events/PointerEventHelper$EVENT;", "", "<init>", "(Ljava/lang/String;I)V", "CANCEL", "CANCEL_CAPTURE", "CLICK", "CLICK_CAPTURE", "DOWN", "DOWN_CAPTURE", "ENTER", "ENTER_CAPTURE", "LEAVE", "LEAVE_CAPTURE", "MOVE", "MOVE_CAPTURE", "UP", "UP_CAPTURE", "OUT", "OUT_CAPTURE", "OVER", "OVER_CAPTURE", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class EVENT {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ EVENT[] $VALUES;
        public static final EVENT CANCEL = new EVENT("CANCEL", 0);
        public static final EVENT CANCEL_CAPTURE = new EVENT("CANCEL_CAPTURE", 1);
        public static final EVENT CLICK = new EVENT("CLICK", 2);
        public static final EVENT CLICK_CAPTURE = new EVENT("CLICK_CAPTURE", 3);
        public static final EVENT DOWN = new EVENT("DOWN", 4);
        public static final EVENT DOWN_CAPTURE = new EVENT("DOWN_CAPTURE", 5);
        public static final EVENT ENTER = new EVENT("ENTER", 6);
        public static final EVENT ENTER_CAPTURE = new EVENT("ENTER_CAPTURE", 7);
        public static final EVENT LEAVE = new EVENT("LEAVE", 8);
        public static final EVENT LEAVE_CAPTURE = new EVENT("LEAVE_CAPTURE", 9);
        public static final EVENT MOVE = new EVENT("MOVE", 10);
        public static final EVENT MOVE_CAPTURE = new EVENT("MOVE_CAPTURE", 11);
        public static final EVENT UP = new EVENT("UP", 12);
        public static final EVENT UP_CAPTURE = new EVENT("UP_CAPTURE", 13);
        public static final EVENT OUT = new EVENT("OUT", 14);
        public static final EVENT OUT_CAPTURE = new EVENT("OUT_CAPTURE", 15);
        public static final EVENT OVER = new EVENT("OVER", 16);
        public static final EVENT OVER_CAPTURE = new EVENT("OVER_CAPTURE", 17);

        private static final /* synthetic */ EVENT[] $values() {
            return new EVENT[]{CANCEL, CANCEL_CAPTURE, CLICK, CLICK_CAPTURE, DOWN, DOWN_CAPTURE, ENTER, ENTER_CAPTURE, LEAVE, LEAVE_CAPTURE, MOVE, MOVE_CAPTURE, UP, UP_CAPTURE, OUT, OUT_CAPTURE, OVER, OVER_CAPTURE};
        }

        public static EnumEntries<EVENT> getEntries() {
            return $ENTRIES;
        }

        private EVENT(String str, int i) {
        }

        static {
            EVENT[] eventArr$values = $values();
            $VALUES = eventArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(eventArr$values);
        }

        public static EVENT valueOf(String str) {
            return (EVENT) Enum.valueOf(EVENT.class, str);
        }

        public static EVENT[] values() {
            return (EVENT[]) $VALUES.clone();
        }
    }

    @JvmStatic
    public static final int getButtons(String eventName, String pointerType, int buttonState) {
        Intrinsics.checkNotNullParameter(pointerType, "pointerType");
        if (INSTANCE.isExitEvent(eventName)) {
            return 0;
        }
        if (Intrinsics.areEqual(POINTER_TYPE_TOUCH, pointerType)) {
            return 1;
        }
        return buttonState;
    }

    @JvmStatic
    public static final int getButtonChange(String pointerType, int lastButtonState, int currentButtonState) {
        Intrinsics.checkNotNullParameter(pointerType, "pointerType");
        int i = 0;
        if (Intrinsics.areEqual(POINTER_TYPE_TOUCH, pointerType)) {
            return 0;
        }
        int i2 = currentButtonState ^ lastButtonState;
        if (i2 == 0) {
            return -1;
        }
        if (i2 != 1) {
            i = 2;
            if (i2 != 2) {
                if (i2 == 4) {
                    return 1;
                }
                if (i2 != 8) {
                    return i2 != 16 ? -1 : 4;
                }
                return 3;
            }
        }
        return i;
    }

    @JvmStatic
    public static final String getW3CPointerType(int toolType) {
        if (toolType == 1) {
            return POINTER_TYPE_TOUCH;
        }
        if (toolType == 2) {
            return POINTER_TYPE_PEN;
        }
        if (toolType == 3) {
            return POINTER_TYPE_MOUSE;
        }
        return "";
    }

    @JvmStatic
    public static final boolean isListening(View view, EVENT event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (view == null) {
            return true;
        }
        switch (WhenMappings.$EnumSwitchMapping$0[event.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                break;
            default:
                Object tag = view.getTag(R.id.pointer_events);
                Integer num = tag instanceof Integer ? (Integer) tag : null;
                if (num == null || (num.intValue() & (1 << event.ordinal())) == 0) {
                }
                break;
        }
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0060 A[RETURN] */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final int getEventCategory(java.lang.String r2) {
        /*
            r0 = 2
            if (r2 != 0) goto L4
            return r0
        L4:
            int r1 = r2.hashCode()
            switch(r1) {
                case -1786514288: goto L54;
                case -1780335505: goto L4a;
                case -1304584214: goto L3e;
                case -1304316135: goto L34;
                case -1304250340: goto L2a;
                case -1065042973: goto L20;
                case 383186882: goto L16;
                case 1343400710: goto Lc;
                default: goto Lb;
            }
        Lb:
            goto L60
        Lc:
            java.lang.String r1 = "topPointerOut"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L5e
            goto L60
        L16:
            java.lang.String r1 = "topPointerCancel"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L48
            goto L60
        L20:
            java.lang.String r1 = "topPointerUp"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L48
            goto L60
        L2a:
            java.lang.String r1 = "topPointerOver"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L5e
            goto L60
        L34:
            java.lang.String r1 = "topPointerMove"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L5e
            goto L60
        L3e:
            java.lang.String r1 = "topPointerDown"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L48
            goto L60
        L48:
            r2 = 3
            return r2
        L4a:
            java.lang.String r1 = "topPointerLeave"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L5e
            goto L60
        L54:
            java.lang.String r1 = "topPointerEnter"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L5e
            goto L60
        L5e:
            r2 = 4
            return r2
        L60:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.events.PointerEventHelper.getEventCategory(java.lang.String):int");
    }

    public final boolean supportsHover(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "motionEvent");
        if ((motionEvent.getFlags() & 16777216) != 0) {
            return true;
        }
        return motionEvent.isFromSource(8194);
    }

    public final boolean isExitEvent(String eventName) {
        if (eventName == null) {
            return false;
        }
        int iHashCode = eventName.hashCode();
        return iHashCode != -1780335505 ? iHashCode != -1065042973 ? iHashCode == 1343400710 && eventName.equals(POINTER_OUT) : eventName.equals(POINTER_UP) : eventName.equals(POINTER_LEAVE);
    }

    @JvmStatic
    public static final double getPressure(int buttonState, String eventName) {
        return (INSTANCE.isExitEvent(eventName) || buttonState == 0) ? 0.0d : 0.5d;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @JvmStatic
    public static final boolean isBubblingEvent(String eventName) {
        if (eventName == null) {
            return false;
        }
        switch (eventName.hashCode()) {
            case -1304584214:
                return eventName.equals(POINTER_DOWN);
            case -1304316135:
                return eventName.equals(POINTER_MOVE);
            case -1304250340:
                return eventName.equals(POINTER_OVER);
            case -1065042973:
                return eventName.equals(POINTER_UP);
            case 383186882:
                return eventName.equals(POINTER_CANCEL);
            case 1343400710:
                return eventName.equals(POINTER_OUT);
            default:
                return false;
        }
    }
}
