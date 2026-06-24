package io.wazo.callkeep;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.telecom.Connection;
import android.telecom.ConnectionRequest;
import android.telecom.ConnectionService;
import android.telecom.DisconnectCause;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.facebook.react.HeadlessJsTaskService;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public class VoiceConnectionService extends ConnectionService {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static ConnectionRequest currentConnectionRequest;
    private static String notReachableCallUuid;
    private static PhoneAccountHandle phoneAccountHandle;
    private static Boolean isAvailable = false;
    private static Boolean isInitialized = false;
    private static Boolean isReachable = false;
    private static Boolean canMakeMultipleCalls = true;
    private static String TAG = "RNCallKeep";
    private static int NOTIFICATION_ID = -4567;
    private static List<Bundle> delayedEvents = new ArrayList();
    public static Map<String, VoiceConnection> currentConnections = new HashMap();
    public static Boolean hasOutgoingCall = false;
    public static VoiceConnectionService currentConnectionService = null;

    public static Connection getConnection(String str) {
        if (currentConnections.containsKey(str)) {
            return currentConnections.get(str);
        }
        return null;
    }

    public VoiceConnectionService() {
        Log.d(TAG, "[VoiceConnectionService] Constructor");
        currentConnectionRequest = null;
        currentConnectionService = this;
    }

    public static void setPhoneAccountHandle(PhoneAccountHandle phoneAccountHandle2) {
        phoneAccountHandle = phoneAccountHandle2;
    }

    public static void setAvailable(Boolean bool) {
        Log.d(TAG, "[VoiceConnectionService] setAvailable: ".concat(bool.booleanValue() ? "true" : "false"));
        if (bool.booleanValue()) {
            setInitialized(true);
        }
        isAvailable = bool;
    }

    public static WritableMap getSettings(Context context) {
        return RNCallKeepModule.getSettings(context);
    }

    public static ReadableMap getForegroundSettings(Context context) {
        WritableMap settings = getSettings(context);
        if (settings == null) {
            return null;
        }
        return settings.getMap("foregroundService");
    }

    public static void setCanMakeMultipleCalls(Boolean bool) {
        Log.d(TAG, "[VoiceConnectionService] setCanMakeMultipleCalls: ".concat(bool.booleanValue() ? "true" : "false"));
        canMakeMultipleCalls = bool;
    }

    public static void setReachable() {
        Log.d(TAG, "[VoiceConnectionService] setReachable");
        isReachable = true;
        currentConnectionRequest = null;
    }

    public static void setInitialized(boolean z) {
        Log.d(TAG, "[VoiceConnectionService] setInitialized: ".concat(z ? "true" : "false"));
        isInitialized = Boolean.valueOf(z);
    }

    public static void deinitConnection(String str) {
        Log.d(TAG, "[VoiceConnectionService] deinitConnection:" + str);
        hasOutgoingCall = false;
        currentConnectionService.stopForegroundService();
        if (currentConnections.containsKey(str)) {
            currentConnections.remove(str);
        }
    }

    public static void setState(String str, int i) {
        Connection connection = getConnection(str);
        if (connection == null) {
            Log.w(TAG, "[VoiceConnectionService] setState ignored because no connection found, uuid: " + str);
            return;
        }
        if (i == 0) {
            connection.setInitializing();
            return;
        }
        if (i == 2) {
            connection.setRinging();
            return;
        }
        if (i == 3) {
            connection.setDialing();
        } else if (i == 4) {
            connection.setActive();
        } else {
            if (i != 5) {
                return;
            }
            connection.setOnHold();
        }
    }

    @Override // android.telecom.ConnectionService
    public Connection onCreateIncomingConnection(PhoneAccountHandle phoneAccountHandle2, ConnectionRequest connectionRequest) {
        Bundle extras = connectionRequest.getExtras();
        Uri address = connectionRequest.getAddress();
        String string = extras.getString(Constants.EXTRA_CALLER_NAME);
        String string2 = extras.getString(Constants.EXTRA_CALL_UUID);
        Boolean boolValueOf = Boolean.valueOf(isRunning(getApplicationContext()));
        WritableMap settings = getSettings(this);
        Integer numValueOf = settings.hasKey("displayCallReachabilityTimeout") ? Integer.valueOf(settings.getInt("displayCallReachabilityTimeout")) : null;
        Log.d(TAG, "[VoiceConnectionService] onCreateIncomingConnection, name:" + string + ", number" + address + ", isForeground: " + boolValueOf + ", isReachable:" + isReachable + ", timeout: " + numValueOf);
        Connection connectionCreateConnection = createConnection(connectionRequest);
        connectionCreateConnection.setRinging();
        connectionCreateConnection.setInitialized();
        startForegroundService();
        if (numValueOf != null) {
            checkForAppReachability(string2, numValueOf);
        }
        return connectionCreateConnection;
    }

    @Override // android.telecom.ConnectionService
    public Connection onCreateOutgoingConnection(PhoneAccountHandle phoneAccountHandle2, ConnectionRequest connectionRequest) {
        hasOutgoingCall = true;
        String string = connectionRequest.getExtras().getString(Constants.EXTRA_CALL_UUID);
        if (string == null || string == "") {
            string = UUID.randomUUID().toString();
        }
        Log.d(TAG, "[VoiceConnectionService] onCreateOutgoingConnection, uuid:" + string);
        if (!isInitialized.booleanValue() && !isReachable.booleanValue()) {
            notReachableCallUuid = string;
            currentConnectionRequest = connectionRequest;
            checkReachability();
        }
        return makeOutgoingCall(connectionRequest, string, false);
    }

    private Connection makeOutgoingCall(ConnectionRequest connectionRequest, String str, Boolean bool) {
        Bundle extras = connectionRequest.getExtras();
        String schemeSpecificPart = connectionRequest.getAddress().getSchemeSpecificPart();
        String string = extras.getString(Constants.EXTRA_CALL_NUMBER);
        String string2 = extras.getString(Constants.EXTRA_CALLER_NAME);
        Boolean boolValueOf = Boolean.valueOf(isRunning(getApplicationContext()));
        Log.d(TAG, "[VoiceConnectionService] makeOutgoingCall, uuid:" + str + ", number: " + schemeSpecificPart + ", displayName:" + string2);
        if (!boolValueOf.booleanValue() || bool.booleanValue()) {
            Log.d(TAG, "[VoiceConnectionService] onCreateOutgoingConnection: Waking up application");
            wakeUpApplication(str, schemeSpecificPart, string2);
        } else if (!canMakeOutgoingCall().booleanValue() && isReachable.booleanValue()) {
            Log.d(TAG, "[VoiceConnectionService] onCreateOutgoingConnection: not available");
            return Connection.createFailedConnection(new DisconnectCause(2));
        }
        if (string == null || !string.equals(schemeSpecificPart)) {
            extras.putString(Constants.EXTRA_CALL_UUID, str);
            extras.putString(Constants.EXTRA_CALLER_NAME, string2);
            extras.putString(Constants.EXTRA_CALL_NUMBER, schemeSpecificPart);
        }
        if (!canMakeMultipleCalls.booleanValue()) {
            Log.d(TAG, "[VoiceConnectionService] onCreateOutgoingConnection: disabling multi calls");
            extras.putBoolean(Constants.EXTRA_DISABLE_ADD_CALL, true);
        }
        Connection connectionCreateConnection = createConnection(connectionRequest);
        connectionCreateConnection.setDialing();
        connectionCreateConnection.setAudioModeIsVoip(true);
        connectionCreateConnection.setCallerDisplayName(string2, 1);
        startForegroundService();
        if (!Build.MANUFACTURER.equalsIgnoreCase("Samsung")) {
            Log.d(TAG, "[VoiceConnectionService] onCreateOutgoingConnection: initializing connection on non-Samsung device");
            connectionCreateConnection.setInitialized();
        }
        HashMap<String, String> mapBundleToMap = bundleToMap(extras);
        sendCallRequestToActivity(Constants.ACTION_ONGOING_CALL, mapBundleToMap, true);
        sendCallRequestToActivity(Constants.ACTION_AUDIO_SESSION, mapBundleToMap, true);
        Log.d(TAG, "[VoiceConnectionService] onCreateOutgoingConnection: done");
        return connectionCreateConnection;
    }

    private void startForegroundService() {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        Log.d(TAG, "[VoiceConnectionService] startForegroundService");
        ReadableMap foregroundSettings = getForegroundSettings(null);
        if (!isForegroundServiceConfigured()) {
            Log.w(TAG, "[VoiceConnectionService] Not creating foregroundService because not configured");
            return;
        }
        String string = foregroundSettings.getString("channelId");
        NotificationChannel notificationChannel = new NotificationChannel(string, foregroundSettings.getString("channelName"), 0);
        notificationChannel.setLockscreenVisibility(0);
        ((NotificationManager) getSystemService("notification")).createNotificationChannel(notificationChannel);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, string);
        builder.setOngoing(true).setContentTitle(foregroundSettings.getString("notificationTitle")).setPriority(1).setCategory("service");
        Activity currentReactActivity = RNCallKeepModule.instance.getCurrentReactActivity();
        if (currentReactActivity != null) {
            Intent intent = new Intent(this, currentReactActivity.getClass());
            intent.addFlags(536870912);
            builder.setContentIntent(PendingIntent.getActivity(this, NOTIFICATION_ID, intent, 201326592));
        }
        if (foregroundSettings.hasKey("notificationIcon")) {
            Context applicationContext = getApplicationContext();
            builder.setSmallIcon(applicationContext.getResources().getIdentifier(foregroundSettings.getString("notificationIcon"), "mipmap", applicationContext.getPackageName()));
        }
        Log.d(TAG, "[VoiceConnectionService] Starting foreground service");
        try {
            startForeground(128, builder.build());
        } catch (Exception e) {
            Log.w(TAG, "[VoiceConnectionService] Can't start foreground service : " + e.toString());
        }
    }

    private void stopForegroundService() {
        Log.d(TAG, "[VoiceConnectionService] stopForegroundService");
        getForegroundSettings(null);
        if (!isForegroundServiceConfigured()) {
            Log.w(TAG, "[VoiceConnectionService] Not creating foregroundService because not configured");
            return;
        }
        try {
            stopForeground(128);
        } catch (Exception e) {
            Log.w(TAG, "[VoiceConnectionService] can't stop foreground service :" + e.toString());
        }
    }

    private boolean isForegroundServiceConfigured() {
        ReadableMap foregroundSettings = getForegroundSettings(null);
        if (foregroundSettings != null) {
            try {
                if (foregroundSettings.hasKey("channelId")) {
                    return true;
                }
            } catch (Exception e) {
                Log.w(TAG, "[VoiceConnectionService] Not creating foregroundService due to configuration retrieval error" + e.toString());
            }
        }
        return false;
    }

    private void wakeUpApplication(String str, String str2, String str3) {
        Log.d(TAG, "[VoiceConnectionService] wakeUpApplication, uuid:" + str + ", number :" + str2 + ", displayName:" + str3);
        currentConnectionRequest = null;
        try {
            Intent intent = new Intent(getApplicationContext(), (Class<?>) RNCallKeepBackgroundMessagingService.class);
            intent.putExtra("callUUID", str);
            intent.putExtra("name", str3);
            intent.putExtra("handle", str2);
            ComponentName componentNameStartService = getApplicationContext().startService(intent);
            if (componentNameStartService != null) {
                Log.d(TAG, "[VoiceConnectionService] wakeUpApplication, acquiring lock for application:" + componentNameStartService);
                HeadlessJsTaskService.acquireWakeLockNow(getApplicationContext());
            }
        } catch (Exception e) {
            Log.w(TAG, "[VoiceConnectionService] wakeUpApplication, error" + e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void wakeUpAfterReachabilityTimeout(ConnectionRequest connectionRequest) {
        if (currentConnectionRequest == null) {
            return;
        }
        Bundle extras = connectionRequest.getExtras();
        String schemeSpecificPart = connectionRequest.getAddress().getSchemeSpecificPart();
        String string = extras.getString(Constants.EXTRA_CALLER_NAME);
        Log.d(TAG, "[VoiceConnectionService] checkReachability timeout, force wakeup, number :" + schemeSpecificPart + ", displayName: " + string);
        wakeUpApplication(notReachableCallUuid, schemeSpecificPart, string);
        currentConnectionRequest = null;
    }

    private void checkReachability() {
        Log.d(TAG, "[VoiceConnectionService] checkReachability");
        sendCallRequestToActivity(Constants.ACTION_CHECK_REACHABILITY, null, true);
        new Handler().postDelayed(new Runnable() { // from class: io.wazo.callkeep.VoiceConnectionService.1
            @Override // java.lang.Runnable
            public void run() {
                this.wakeUpAfterReachabilityTimeout(VoiceConnectionService.currentConnectionRequest);
            }
        }, 2000L);
    }

    private Boolean canMakeOutgoingCall() {
        return isAvailable;
    }

    private Connection createConnection(ConnectionRequest connectionRequest) {
        Bundle extras = connectionRequest.getExtras();
        if (connectionRequest.getAddress() == null) {
            return null;
        }
        HashMap<String, String> mapBundleToMap = bundleToMap(extras);
        String string = connectionRequest.getAddress().toString();
        Log.d(TAG, "[VoiceConnectionService] createConnection, callerNumber:" + string);
        if (string.contains(":")) {
            int iIndexOf = string.indexOf(":");
            String strSubstring = string.substring(iIndexOf + 1);
            String strSubstring2 = string.substring(0, iIndexOf);
            mapBundleToMap.put(Constants.EXTRA_CALL_NUMBER, strSubstring);
            mapBundleToMap.put(Constants.EXTRA_CALL_NUMBER_SCHEMA, strSubstring2);
        } else {
            mapBundleToMap.put(Constants.EXTRA_CALL_NUMBER, string);
        }
        VoiceConnection voiceConnection = new VoiceConnection(this, mapBundleToMap);
        voiceConnection.setConnectionCapabilities(66);
        if (Build.VERSION.SDK_INT >= 26) {
            if ((((TelecomManager) getApplicationContext().getSystemService("telecom")).getPhoneAccount(connectionRequest.getAccountHandle()).getCapabilities() & 2048) == 2048) {
                Log.d(TAG, "[VoiceConnectionService] PhoneAccount is SELF_MANAGED, so connection will be too");
                voiceConnection.setConnectionProperties(128);
            } else {
                Log.d(TAG, "[VoiceConnectionService] PhoneAccount is not SELF_MANAGED, so connection won't be either");
            }
        }
        voiceConnection.setInitializing();
        voiceConnection.setExtras(extras);
        currentConnections.put(extras.getString(Constants.EXTRA_CALL_UUID), voiceConnection);
        HashMap map = new HashMap();
        for (Map.Entry<String, VoiceConnection> entry : currentConnections.entrySet()) {
            if (!extras.getString(Constants.EXTRA_CALL_UUID).equals(entry.getKey())) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
        voiceConnection.setConferenceableConnections(new ArrayList(map.values()));
        return voiceConnection;
    }

    @Override // android.telecom.ConnectionService
    public void onConference(Connection connection, Connection connection2) {
        Log.d(TAG, "[VoiceConnectionService] onConference");
        super.onConference(connection, connection2);
        VoiceConference voiceConference = new VoiceConference(phoneAccountHandle);
        voiceConference.addConnection((VoiceConnection) connection);
        voiceConference.addConnection((VoiceConnection) connection2);
        connection.onUnhold();
        connection2.onUnhold();
        addConference(voiceConference);
    }

    @Override // android.telecom.ConnectionService
    public void onCreateIncomingConnectionFailed(PhoneAccountHandle phoneAccountHandle2, ConnectionRequest connectionRequest) {
        super.onCreateIncomingConnectionFailed(phoneAccountHandle2, connectionRequest);
        Log.w(TAG, "[VoiceConnectionService] onCreateIncomingConnectionFailed: " + connectionRequest);
        HashMap<String, String> mapBundleToMap = bundleToMap(connectionRequest.getExtras());
        String string = connectionRequest.getAddress().toString();
        if (string.contains(":")) {
            int iIndexOf = string.indexOf(":");
            String strSubstring = string.substring(iIndexOf + 1);
            String strSubstring2 = string.substring(0, iIndexOf);
            mapBundleToMap.put(Constants.EXTRA_CALL_NUMBER, strSubstring);
            mapBundleToMap.put(Constants.EXTRA_CALL_NUMBER_SCHEMA, strSubstring2);
        } else {
            mapBundleToMap.put(Constants.EXTRA_CALL_NUMBER, string);
        }
        sendCallRequestToActivity(Constants.ACTION_ON_CREATE_CONNECTION_FAILED, mapBundleToMap, true);
    }

    public static void startObserving() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: io.wazo.callkeep.VoiceConnectionService.2
            @Override // java.lang.Runnable
            public void run() {
                Log.d(VoiceConnectionService.TAG, "[VoiceConnectionService] startObserving, event count: " + VoiceConnectionService.delayedEvents.size());
                for (Bundle bundle : VoiceConnectionService.delayedEvents) {
                    VoiceConnectionService.currentConnectionService.sendCallRequestToActivity(bundle.getString("action"), (HashMap) bundle.getSerializable("attributeMap"), false);
                }
                VoiceConnectionService.delayedEvents = new ArrayList();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendCallRequestToActivity(final String str, final HashMap map, final boolean z) {
        Handler handler = new Handler();
        Log.d(TAG, "[VoiceConnectionService] sendCallRequestToActivity, action:" + str);
        handler.post(new Runnable() { // from class: io.wazo.callkeep.VoiceConnectionService.3
            @Override // java.lang.Runnable
            public void run() {
                Intent intent = new Intent(str);
                Bundle bundle = new Bundle();
                bundle.putString("action", str);
                HashMap map2 = map;
                if (map2 != null) {
                    bundle.putSerializable("attributeMap", map2);
                    intent.putExtras(bundle);
                }
                if (LocalBroadcastManager.getInstance(this).sendBroadcast(intent) || !z) {
                    return;
                }
                VoiceConnectionService.delayedEvents.add(bundle);
            }
        });
    }

    private HashMap<String, String> bundleToMap(Bundle bundle) {
        HashMap<String, String> map = new HashMap<>();
        for (String str : bundle.keySet()) {
            if (bundle.get(str) != null) {
                map.put(str, bundle.get(str).toString());
            }
        }
        return map;
    }

    public static boolean isRunning(Context context) {
        Iterator<ActivityManager.RunningTaskInfo> it = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(Integer.MAX_VALUE).iterator();
        while (it.hasNext()) {
            if (context.getPackageName().equalsIgnoreCase(it.next().baseActivity.getPackageName())) {
                return true;
            }
        }
        Log.d(TAG, "[VoiceConnectionService] isRunning: no running package found.");
        return false;
    }

    private void checkForAppReachability(final String str, final Integer num) {
        new Handler().postDelayed(new Runnable() { // from class: io.wazo.callkeep.VoiceConnectionService.4
            @Override // java.lang.Runnable
            public void run() {
                if (VoiceConnectionService.isReachable.booleanValue()) {
                    return;
                }
                Connection connection = VoiceConnectionService.getConnection(str);
                Log.w(VoiceConnectionService.TAG, "[VoiceConnectionService] checkForAppReachability timeout after " + num + " ms, isReachable:" + VoiceConnectionService.isReachable + ", uuid: " + str);
                if (connection == null) {
                    Log.w(VoiceConnectionService.TAG, "[VoiceConnectionService] checkForAppReachability timeout, no connection to close with uuid: " + str);
                } else {
                    connection.onDisconnect();
                }
            }
        }, num.intValue());
    }
}
