package com.swmansion.rnscreens.gamma.tabs.event;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.swmansion.rnscreens.gamma.common.NamingAwareEventType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TabsHostNativeFocusChangeEvent.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001a2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u001aB/\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\b\u0010\u0014\u001a\u00020\bH\u0016J\b\u0010\u0015\u001a\u00020\bH\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001b"}, d2 = {"Lcom/swmansion/rnscreens/gamma/tabs/event/TabsHostNativeFocusChangeEvent;", "Lcom/facebook/react/uimanager/events/Event;", "Lcom/swmansion/rnscreens/gamma/tabs/event/TabScreenDidAppearEvent;", "Lcom/swmansion/rnscreens/gamma/common/NamingAwareEventType;", "surfaceId", "", "viewId", TabsHostNativeFocusChangeEvent.EVENT_KEY_TAB_KEY, "", "tabNumber", TabsHostNativeFocusChangeEvent.EVENT_KEY_REPEATED_SELECTION_HANDLED_BY_SPECIAL_EFFECT, "", "<init>", "(IILjava/lang/String;IZ)V", "getTabKey", "()Ljava/lang/String;", "getTabNumber", "()I", "getRepeatedSelectionHandledBySpecialEffect", "()Z", "getEventName", "getEventRegistrationName", "getCoalescingKey", "", "getEventData", "Lcom/facebook/react/bridge/WritableMap;", "Companion", "react-native-screens_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TabsHostNativeFocusChangeEvent extends Event<TabScreenDidAppearEvent> implements NamingAwareEventType {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String EVENT_KEY_REPEATED_SELECTION_HANDLED_BY_SPECIAL_EFFECT = "repeatedSelectionHandledBySpecialEffect";
    private static final String EVENT_KEY_TAB_KEY = "tabKey";
    public static final String EVENT_NAME = "topNativeFocusChange";
    public static final String EVENT_REGISTRATION_NAME = "onNativeFocusChange";
    private final boolean repeatedSelectionHandledBySpecialEffect;
    private final String tabKey;
    private final int tabNumber;

    public final String getTabKey() {
        return this.tabKey;
    }

    public final int getTabNumber() {
        return this.tabNumber;
    }

    public final boolean getRepeatedSelectionHandledBySpecialEffect() {
        return this.repeatedSelectionHandledBySpecialEffect;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TabsHostNativeFocusChangeEvent(int i, int i2, String tabKey, int i3, boolean z) {
        super(i, i2);
        Intrinsics.checkNotNullParameter(tabKey, "tabKey");
        this.tabKey = tabKey;
        this.tabNumber = i3;
        this.repeatedSelectionHandledBySpecialEffect = z;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return EVENT_NAME;
    }

    @Override // com.swmansion.rnscreens.gamma.common.NamingAwareEventType
    public String getEventRegistrationName() {
        return EVENT_REGISTRATION_NAME;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return (short) ((this.tabNumber * 10) + (this.repeatedSelectionHandledBySpecialEffect ? 1 : 0));
    }

    @Override // com.facebook.react.uimanager.events.Event
    protected WritableMap getEventData() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString(EVENT_KEY_TAB_KEY, this.tabKey);
        writableMapCreateMap.putBoolean(EVENT_KEY_REPEATED_SELECTION_HANDLED_BY_SPECIAL_EFFECT, this.repeatedSelectionHandledBySpecialEffect);
        return writableMapCreateMap;
    }

    /* JADX INFO: compiled from: TabsHostNativeFocusChangeEvent.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\t\u001a\u00020\u0005H\u0016J\b\u0010\n\u001a\u00020\u0005H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/swmansion/rnscreens/gamma/tabs/event/TabsHostNativeFocusChangeEvent$Companion;", "Lcom/swmansion/rnscreens/gamma/common/NamingAwareEventType;", "<init>", "()V", "EVENT_NAME", "", "EVENT_REGISTRATION_NAME", "EVENT_KEY_TAB_KEY", "EVENT_KEY_REPEATED_SELECTION_HANDLED_BY_SPECIAL_EFFECT", "getEventName", "getEventRegistrationName", "react-native-screens_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion implements NamingAwareEventType {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Override // com.swmansion.rnscreens.gamma.common.NamingAwareEventType
        public String getEventName() {
            return TabsHostNativeFocusChangeEvent.EVENT_NAME;
        }

        @Override // com.swmansion.rnscreens.gamma.common.NamingAwareEventType
        public String getEventRegistrationName() {
            return TabsHostNativeFocusChangeEvent.EVENT_REGISTRATION_NAME;
        }
    }
}
