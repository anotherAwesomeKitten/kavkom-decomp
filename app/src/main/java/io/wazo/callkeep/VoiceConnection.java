package io.wazo.callkeep;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telecom.CallAudioState;
import android.telecom.Connection;
import android.telecom.DisconnectCause;
import android.util.Log;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class VoiceConnection extends Connection {
    private static final String TAG = "RNCallKeep";
    private Context context;
    private HashMap<String, String> handle;
    private boolean isMuted = false;
    private boolean answered = false;
    private boolean rejected = false;

    VoiceConnection(Context context, HashMap<String, String> map) {
        this.handle = map;
        this.context = context;
        String str = map.get(Constants.EXTRA_CALL_NUMBER);
        String str2 = map.get(Constants.EXTRA_CALLER_NAME);
        if (str != null) {
            setAddress(Uri.parse(str), 1);
        }
        if (str2 == null || str2.equals("")) {
            return;
        }
        setCallerDisplayName(str2, 1);
    }

    @Override // android.telecom.Connection
    public void onExtrasChanged(Bundle bundle) {
        super.onExtrasChanged(bundle);
        HashMap<String, String> map = (HashMap) bundle.getSerializable("attributeMap");
        if (map != null) {
            this.handle = map;
        }
    }

    @Override // android.telecom.Connection
    public void onCallAudioStateChanged(CallAudioState callAudioState) {
        Log.d(TAG, "[VoiceConnection] onCallAudioStateChanged muted :".concat(callAudioState.isMuted() ? "true" : "false"));
        this.handle.put("output", CallAudioState.audioRouteToString(callAudioState.getRoute()));
        sendCallRequestToActivity(Constants.ACTION_DID_CHANGE_AUDIO_ROUTE, this.handle);
        if (callAudioState.isMuted() == this.isMuted) {
            return;
        }
        boolean zIsMuted = callAudioState.isMuted();
        this.isMuted = zIsMuted;
        sendCallRequestToActivity(zIsMuted ? Constants.ACTION_MUTE_CALL : Constants.ACTION_UNMUTE_CALL, this.handle);
    }

    @Override // android.telecom.Connection
    public void onAnswer(int i) {
        super.onAnswer(i);
        Log.d(TAG, "[VoiceConnection] onAnswer(int) executed");
        _onAnswer(i);
    }

    @Override // android.telecom.Connection
    public void onAnswer() {
        super.onAnswer();
        Log.d(TAG, "[VoiceConnection] onAnswer() executed");
        _onAnswer(0);
    }

    @Override // android.telecom.Connection
    public void onPlayDtmfTone(char c) {
        Log.d(TAG, "[VoiceConnection] Playing DTMF : " + c);
        try {
            this.handle.put("DTMF", Character.toString(c));
        } catch (Throwable th) {
            Log.e(TAG, "[VoiceConnection] Handle map error", th);
        }
        sendCallRequestToActivity(Constants.ACTION_DTMF_TONE, this.handle);
    }

    @Override // android.telecom.Connection
    public void onDisconnect() {
        super.onDisconnect();
        setDisconnected(new DisconnectCause(2));
        sendCallRequestToActivity(Constants.ACTION_END_CALL, this.handle);
        Log.d(TAG, "[VoiceConnection] onDisconnect executed");
        try {
            VoiceConnectionService.deinitConnection(this.handle.get(Constants.EXTRA_CALL_UUID));
        } catch (Throwable th) {
            Log.e(TAG, "[VoiceConnection] onDisconnect handle map error", th);
        }
        destroy();
    }

    public void reportDisconnect(int i) {
        super.onDisconnect();
        switch (i) {
            case 1:
                setDisconnected(new DisconnectCause(1));
                break;
            case 2:
            case 5:
                setDisconnected(new DisconnectCause(3));
                break;
            case 3:
                setDisconnected(new DisconnectCause(7));
                break;
            case 4:
                setDisconnected(new DisconnectCause(11));
                break;
            case 6:
                setDisconnected(new DisconnectCause(5));
                break;
        }
        VoiceConnectionService.deinitConnection(this.handle.get(Constants.EXTRA_CALL_UUID));
        destroy();
    }

    @Override // android.telecom.Connection
    public void onAbort() {
        super.onAbort();
        setDisconnected(new DisconnectCause(6));
        sendCallRequestToActivity(Constants.ACTION_END_CALL, this.handle);
        Log.d(TAG, "[VoiceConnection] onAbort executed");
        try {
            VoiceConnectionService.deinitConnection(this.handle.get(Constants.EXTRA_CALL_UUID));
        } catch (Throwable th) {
            Log.e(TAG, "[VoiceConnection] onAbort handle map error", th);
        }
        destroy();
    }

    @Override // android.telecom.Connection
    public void onHold() {
        Log.d(TAG, "[VoiceConnection] onHold");
        super.onHold();
        setOnHold();
        sendCallRequestToActivity(Constants.ACTION_HOLD_CALL, this.handle);
    }

    @Override // android.telecom.Connection
    public void onUnhold() {
        Log.d(TAG, "[VoiceConnection] onUnhold");
        super.onUnhold();
        sendCallRequestToActivity(Constants.ACTION_UNHOLD_CALL, this.handle);
        setActive();
    }

    @Override // android.telecom.Connection
    public void onReject(int i) {
        Log.d(TAG, "[VoiceConnection] onReject(int) executed");
        _onReject(i, null);
    }

    @Override // android.telecom.Connection
    public void onReject() {
        super.onReject();
        Log.d(TAG, "[VoiceConnection] onReject() executed");
        _onReject(0, null);
    }

    @Override // android.telecom.Connection
    public void onReject(String str) {
        super.onReject(str);
        Log.d(TAG, "[VoiceConnection] onReject(String) executed");
        _onReject(0, str);
    }

    @Override // android.telecom.Connection
    public void onCallEvent(String str, Bundle bundle) {
        super.onCallEvent(str, bundle);
        Log.d(TAG, "[VoiceConnection] onCallEvent called, event: " + str);
    }

    @Override // android.telecom.Connection
    public void onDeflect(Uri uri) {
        super.onDeflect(uri);
        Log.d(TAG, "[VoiceConnection] onDeflect called, address: " + uri);
    }

    @Override // android.telecom.Connection
    public void onHandoverComplete() {
        super.onHandoverComplete();
        Log.d(TAG, "[VoiceConnection] onHandoverComplete called");
    }

    @Override // android.telecom.Connection
    public void onPostDialContinue(boolean z) {
        super.onPostDialContinue(z);
        Log.d(TAG, "[VoiceConnection] onPostDialContinue called, proceed: " + z);
    }

    @Override // android.telecom.Connection
    public void onPullExternalCall() {
        super.onPullExternalCall();
        Log.d(TAG, "[VoiceConnection] onPullExternalCall called");
    }

    @Override // android.telecom.Connection
    public void onSeparate() {
        super.onSeparate();
        Log.d(TAG, "[VoiceConnection] onSeparate called");
    }

    @Override // android.telecom.Connection
    public void onStateChanged(int i) {
        super.onStateChanged(i);
        Log.d(TAG, "[VoiceConnection] onStateChanged called, state : " + i);
    }

    @Override // android.telecom.Connection
    public void onSilence() {
        if (Build.VERSION.SDK_INT < 29) {
            return;
        }
        super.onSilence();
        sendCallRequestToActivity(Constants.ACTION_ON_SILENCE_INCOMING_CALL, this.handle);
        Log.d(TAG, "[VoiceConnection] onSilence called");
    }

    @Override // android.telecom.Connection
    public void onStopDtmfTone() {
        super.onStopDtmfTone();
        Log.d(TAG, "[VoiceConnection] onStopDtmfTone called");
    }

    @Override // android.telecom.Connection
    public void onStopRtt() {
        super.onStopRtt();
        Log.d(TAG, "[VoiceConnection] onStopRtt called");
    }

    private void _onAnswer(int i) {
        Log.d(TAG, "[VoiceConnection] onAnswer called, videoState: " + i + ", answered: " + this.answered);
        if (this.answered) {
            return;
        }
        this.answered = true;
        setConnectionCapabilities(getConnectionCapabilities() | 1);
        setAudioModeIsVoip(true);
        sendCallRequestToActivity(Constants.ACTION_ANSWER_CALL, this.handle);
        sendCallRequestToActivity(Constants.ACTION_AUDIO_SESSION, this.handle);
        Log.d(TAG, "[VoiceConnection] onAnswer executed");
    }

    private void _onReject(int i, String str) {
        Log.d(TAG, "[VoiceConnection] onReject executed, rejectReason: " + i + ", replyMessage: " + str + ", rejected:" + this.rejected);
        if (this.rejected) {
            return;
        }
        this.rejected = true;
        setDisconnected(new DisconnectCause(6));
        sendCallRequestToActivity(Constants.ACTION_END_CALL, this.handle);
        Log.d(TAG, "[VoiceConnection] onReject executed");
        try {
            VoiceConnectionService.deinitConnection(this.handle.get(Constants.EXTRA_CALL_UUID));
        } catch (Throwable th) {
            Log.e(TAG, "[VoiceConnection] onReject, handle map error", th);
        }
        destroy();
    }

    @Override // android.telecom.Connection
    public void onShowIncomingCallUi() {
        Log.d(TAG, "[VoiceConnection] onShowIncomingCallUi");
        sendCallRequestToActivity(Constants.ACTION_SHOW_INCOMING_CALL_UI, this.handle);
    }

    private void sendCallRequestToActivity(String str, HashMap map) {
        new Handler().post(new Runnable() { // from class: io.wazo.callkeep.VoiceConnection.1
            final /* synthetic */ String val$action;
            final /* synthetic */ HashMap val$attributeMap;

            AnonymousClass1(String str2, HashMap map2) {
                str = str2;
                map = map2;
            }

            @Override // java.lang.Runnable
            public void run() {
                Intent intent = new Intent(str);
                if (map != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("attributeMap", map);
                    intent.putExtras(bundle);
                }
                LocalBroadcastManager.getInstance(VoiceConnection.this.context).sendBroadcast(intent);
            }
        });
    }

    /* JADX INFO: renamed from: io.wazo.callkeep.VoiceConnection$1 */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ String val$action;
        final /* synthetic */ HashMap val$attributeMap;

        AnonymousClass1(String str2, HashMap map2) {
            str = str2;
            map = map2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Intent intent = new Intent(str);
            if (map != null) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("attributeMap", map);
                intent.putExtras(bundle);
            }
            LocalBroadcastManager.getInstance(VoiceConnection.this.context).sendBroadcast(intent);
        }
    }
}
