package io.wazo.callkeep;

import android.telecom.Conference;
import android.telecom.Connection;
import android.telecom.PhoneAccountHandle;

/* JADX INFO: loaded from: classes3.dex */
public class VoiceConference extends Conference {
    VoiceConference(PhoneAccountHandle phoneAccountHandle) {
        super(phoneAccountHandle);
        setActive();
    }

    @Override // android.telecom.Conference
    public void onMerge() {
        super.onMerge();
    }

    @Override // android.telecom.Conference
    public void onSeparate(Connection connection) {
        super.onSeparate(connection);
    }

    @Override // android.telecom.Conference
    public void onDisconnect() {
        super.onDisconnect();
    }

    @Override // android.telecom.Conference
    public void onConnectionAdded(Connection connection) {
        super.onConnectionAdded(connection);
    }

    @Override // android.telecom.Conference
    public void onHold() {
        super.onHold();
    }

    @Override // android.telecom.Conference
    public void onUnhold() {
        super.onUnhold();
    }
}
