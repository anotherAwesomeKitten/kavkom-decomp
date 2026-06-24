package com.dieam.reactnativepushnotification.modules;

import android.os.Bundle;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class RNPushNotificationAttributes {
    private static final String ACTIONS = "actions";
    private static final String ALLOW_WHILE_IDLE = "allowWhileIdle";
    private static final String AUTO_CANCEL = "autoCancel";
    private static final String BIG_PICTURE_URL = "bigPictureUrl";
    private static final String BIG_TEXT = "bigText";
    private static final String CHANNEL_ID = "channelId";
    private static final String COLOR = "color";
    private static final String FIRE_DATE = "fireDate";
    private static final String GROUP = "group";
    private static final String GROUP_SUMMARY = "groupSummary";
    private static final String ID = "id";
    private static final String IGNORE_IN_FOREGROUND = "ignoreInForeground";
    private static final String INVOKE_APP = "invokeApp";
    private static final String LARGE_ICON = "largeIcon";
    private static final String LARGE_ICON_URL = "largeIconUrl";
    private static final String MESSAGE = "message";
    private static final String MESSAGE_ID = "messageId";
    private static final String NUMBER = "number";
    private static final String ONGOING = "ongoing";
    private static final String ONLY_ALERT_ONCE = "onlyAlertOnce";
    private static final String PLAY_SOUND = "playSound";
    private static final String REPEAT_TIME = "repeatTime";
    private static final String REPEAT_TYPE = "repeatType";
    private static final String REPLAY_PLACEHOLDER_TEXT = "reply_placeholder_text";
    private static final String REPLY_BUTTON_TEXT = "reply_button_text";
    private static final String SHORTCUT_ID = "shortcutId";
    private static final String SHOW_WHEN = "showWhen";
    private static final String SMALL_ICON = "smallIcon";
    private static final String SOUND = "sound";
    private static final String SUB_TEXT = "subText";
    private static final String TAG = "tag";
    private static final String TICKER = "ticker";
    private static final String TIMEOUT_AFTER = "timeoutAfter";
    private static final String TITLE = "title";
    private static final String USER_INFO = "userInfo";
    private static final String USES_CHRONOMETER = "usesChronometer";
    private static final String VIBRATE = "vibrate";
    private static final String VIBRATION = "vibration";
    private static final String WHEN = "when";
    private final String actions;
    private final boolean allowWhileIdle;
    private final boolean autoCancel;
    private final String bigPictureUrl;
    private final String bigText;
    private final String channelId;
    private final String color;
    private final double fireDate;
    private final String group;
    private final boolean groupSummary;
    private final String id;
    private final boolean ignoreInForeground;
    private final boolean invokeApp;
    private final String largeIcon;
    private final String largeIconUrl;
    private final String message;
    private final String messageId;
    private final String number;
    private final boolean ongoing;
    private final boolean onlyAlertOnce;
    private final boolean playSound;
    private final double repeatTime;
    private final String repeatType;
    private final String reply_button_text;
    private final String reply_placeholder_text;
    private final String shortcutId;
    private final boolean showWhen;
    private final String smallIcon;
    private final String sound;
    private final String subText;
    private final String tag;
    private final String ticker;
    private final double timeoutAfter;
    private final String title;
    private final String userInfo;
    private final boolean usesChronometer;
    private final boolean vibrate;
    private final double vibration;
    private final double when;

    public RNPushNotificationAttributes(Bundle bundle) {
        this.id = bundle.getString("id");
        this.message = bundle.getString("message");
        this.fireDate = bundle.getDouble(FIRE_DATE);
        this.title = bundle.getString("title");
        this.ticker = bundle.getString(TICKER);
        this.showWhen = bundle.getBoolean(SHOW_WHEN);
        this.autoCancel = bundle.getBoolean(AUTO_CANCEL);
        this.largeIcon = bundle.getString(LARGE_ICON);
        this.largeIconUrl = bundle.getString(LARGE_ICON_URL);
        this.smallIcon = bundle.getString(SMALL_ICON);
        this.bigText = bundle.getString(BIG_TEXT);
        this.subText = bundle.getString(SUB_TEXT);
        this.bigPictureUrl = bundle.getString(BIG_PICTURE_URL);
        this.shortcutId = bundle.getString(SHORTCUT_ID);
        this.number = bundle.getString(NUMBER);
        this.channelId = bundle.getString(CHANNEL_ID);
        this.sound = bundle.getString(SOUND);
        this.color = bundle.getString("color");
        this.group = bundle.getString(GROUP);
        this.groupSummary = bundle.getBoolean(GROUP_SUMMARY);
        this.messageId = bundle.getString(MESSAGE_ID);
        this.playSound = bundle.getBoolean(PLAY_SOUND);
        this.vibrate = bundle.getBoolean(VIBRATE);
        this.vibration = bundle.getDouble(VIBRATION);
        this.actions = bundle.getString(ACTIONS);
        this.invokeApp = bundle.getBoolean(INVOKE_APP);
        this.tag = bundle.getString("tag");
        this.repeatType = bundle.getString(REPEAT_TYPE);
        this.repeatTime = bundle.getDouble(REPEAT_TIME);
        this.when = bundle.getDouble(WHEN);
        this.usesChronometer = bundle.getBoolean(USES_CHRONOMETER);
        this.timeoutAfter = bundle.getDouble(TIMEOUT_AFTER);
        this.onlyAlertOnce = bundle.getBoolean(ONLY_ALERT_ONCE);
        this.ongoing = bundle.getBoolean(ONGOING);
        this.reply_button_text = bundle.getString(REPLY_BUTTON_TEXT);
        this.reply_placeholder_text = bundle.getString(REPLAY_PLACEHOLDER_TEXT);
        this.allowWhileIdle = bundle.getBoolean(ALLOW_WHILE_IDLE);
        this.ignoreInForeground = bundle.getBoolean(IGNORE_IN_FOREGROUND);
        this.userInfo = bundle.getString(USER_INFO);
    }

    private RNPushNotificationAttributes(JSONObject jSONObject) {
        try {
            this.id = jSONObject.has("id") ? jSONObject.getString("id") : null;
            this.message = jSONObject.has("message") ? jSONObject.getString("message") : null;
            this.fireDate = jSONObject.has(FIRE_DATE) ? jSONObject.getDouble(FIRE_DATE) : 0.0d;
            this.title = jSONObject.has("title") ? jSONObject.getString("title") : null;
            this.ticker = jSONObject.has(TICKER) ? jSONObject.getString(TICKER) : null;
            this.showWhen = jSONObject.has(SHOW_WHEN) ? jSONObject.getBoolean(SHOW_WHEN) : true;
            this.autoCancel = jSONObject.has(AUTO_CANCEL) ? jSONObject.getBoolean(AUTO_CANCEL) : true;
            this.largeIcon = jSONObject.has(LARGE_ICON) ? jSONObject.getString(LARGE_ICON) : null;
            this.largeIconUrl = jSONObject.has(LARGE_ICON_URL) ? jSONObject.getString(LARGE_ICON_URL) : null;
            this.smallIcon = jSONObject.has(SMALL_ICON) ? jSONObject.getString(SMALL_ICON) : null;
            this.bigText = jSONObject.has(BIG_TEXT) ? jSONObject.getString(BIG_TEXT) : null;
            this.subText = jSONObject.has(SUB_TEXT) ? jSONObject.getString(SUB_TEXT) : null;
            this.bigPictureUrl = jSONObject.has(BIG_PICTURE_URL) ? jSONObject.getString(BIG_PICTURE_URL) : null;
            this.shortcutId = jSONObject.has(SHORTCUT_ID) ? jSONObject.getString(SHORTCUT_ID) : null;
            this.number = jSONObject.has(NUMBER) ? jSONObject.getString(NUMBER) : null;
            this.channelId = jSONObject.has(CHANNEL_ID) ? jSONObject.getString(CHANNEL_ID) : null;
            this.sound = jSONObject.has(SOUND) ? jSONObject.getString(SOUND) : null;
            this.color = jSONObject.has("color") ? jSONObject.getString("color") : null;
            this.group = jSONObject.has(GROUP) ? jSONObject.getString(GROUP) : null;
            this.groupSummary = jSONObject.has(GROUP_SUMMARY) ? jSONObject.getBoolean(GROUP_SUMMARY) : false;
            this.messageId = jSONObject.has(MESSAGE_ID) ? jSONObject.getString(MESSAGE_ID) : null;
            this.playSound = jSONObject.has(PLAY_SOUND) ? jSONObject.getBoolean(PLAY_SOUND) : true;
            this.vibrate = jSONObject.has(VIBRATE) ? jSONObject.getBoolean(VIBRATE) : true;
            this.vibration = jSONObject.has(VIBRATION) ? jSONObject.getDouble(VIBRATION) : 1000.0d;
            this.actions = jSONObject.has(ACTIONS) ? jSONObject.getString(ACTIONS) : null;
            this.invokeApp = jSONObject.has(INVOKE_APP) ? jSONObject.getBoolean(INVOKE_APP) : true;
            this.tag = jSONObject.has("tag") ? jSONObject.getString("tag") : null;
            this.repeatType = jSONObject.has(REPEAT_TYPE) ? jSONObject.getString(REPEAT_TYPE) : null;
            this.repeatTime = jSONObject.has(REPEAT_TIME) ? jSONObject.getDouble(REPEAT_TIME) : 0.0d;
            this.when = jSONObject.has(WHEN) ? jSONObject.getDouble(WHEN) : -1.0d;
            this.usesChronometer = jSONObject.has(USES_CHRONOMETER) ? jSONObject.getBoolean(USES_CHRONOMETER) : false;
            this.timeoutAfter = jSONObject.has(TIMEOUT_AFTER) ? jSONObject.getDouble(TIMEOUT_AFTER) : -1.0d;
            this.onlyAlertOnce = jSONObject.has(ONLY_ALERT_ONCE) ? jSONObject.getBoolean(ONLY_ALERT_ONCE) : false;
            this.ongoing = jSONObject.has(ONGOING) ? jSONObject.getBoolean(ONGOING) : false;
            this.reply_button_text = jSONObject.has(REPLY_BUTTON_TEXT) ? jSONObject.getString(REPLY_BUTTON_TEXT) : null;
            this.reply_placeholder_text = jSONObject.has(REPLAY_PLACEHOLDER_TEXT) ? jSONObject.getString(REPLAY_PLACEHOLDER_TEXT) : null;
            this.allowWhileIdle = jSONObject.has(ALLOW_WHILE_IDLE) ? jSONObject.getBoolean(ALLOW_WHILE_IDLE) : false;
            this.ignoreInForeground = jSONObject.has(IGNORE_IN_FOREGROUND) ? jSONObject.getBoolean(IGNORE_IN_FOREGROUND) : false;
            this.userInfo = jSONObject.has(USER_INFO) ? jSONObject.getString(USER_INFO) : null;
        } catch (JSONException e) {
            throw new IllegalStateException("Exception while initializing RNPushNotificationAttributes from JSON", e);
        }
    }

    public static RNPushNotificationAttributes fromJson(String str) throws JSONException {
        return new RNPushNotificationAttributes(new JSONObject(str));
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("id", this.id);
        bundle.putString("message", this.message);
        bundle.putDouble(FIRE_DATE, this.fireDate);
        bundle.putString("title", this.title);
        bundle.putString(TICKER, this.ticker);
        bundle.putBoolean(SHOW_WHEN, this.showWhen);
        bundle.putBoolean(AUTO_CANCEL, this.autoCancel);
        bundle.putString(LARGE_ICON, this.largeIcon);
        bundle.putString(LARGE_ICON_URL, this.largeIconUrl);
        bundle.putString(SMALL_ICON, this.smallIcon);
        bundle.putString(BIG_TEXT, this.bigText);
        bundle.putString(SUB_TEXT, this.subText);
        bundle.putString(BIG_PICTURE_URL, this.bigPictureUrl);
        bundle.putString(SHORTCUT_ID, this.shortcutId);
        bundle.putString(NUMBER, this.number);
        bundle.putString(CHANNEL_ID, this.channelId);
        bundle.putString(SOUND, this.sound);
        bundle.putString("color", this.color);
        bundle.putString(GROUP, this.group);
        bundle.putBoolean(GROUP_SUMMARY, this.groupSummary);
        bundle.putString(MESSAGE_ID, this.messageId);
        bundle.putBoolean(PLAY_SOUND, this.playSound);
        bundle.putBoolean(VIBRATE, this.vibrate);
        bundle.putDouble(VIBRATION, this.vibration);
        bundle.putString(ACTIONS, this.actions);
        bundle.putBoolean(INVOKE_APP, this.invokeApp);
        bundle.putString("tag", this.tag);
        bundle.putString(REPEAT_TYPE, this.repeatType);
        bundle.putDouble(REPEAT_TIME, this.repeatTime);
        bundle.putDouble(WHEN, this.when);
        bundle.putBoolean(USES_CHRONOMETER, this.usesChronometer);
        bundle.putDouble(TIMEOUT_AFTER, this.timeoutAfter);
        bundle.putBoolean(ONLY_ALERT_ONCE, this.onlyAlertOnce);
        bundle.putBoolean(ONGOING, this.ongoing);
        bundle.putString(REPLY_BUTTON_TEXT, this.reply_button_text);
        bundle.putString(REPLAY_PLACEHOLDER_TEXT, this.reply_placeholder_text);
        bundle.putBoolean(ALLOW_WHILE_IDLE, this.allowWhileIdle);
        bundle.putBoolean(IGNORE_IN_FOREGROUND, this.ignoreInForeground);
        bundle.putString(USER_INFO, this.userInfo);
        return bundle;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.id);
            jSONObject.put("message", this.message);
            jSONObject.put(FIRE_DATE, this.fireDate);
            jSONObject.put("title", this.title);
            jSONObject.put(TICKER, this.ticker);
            jSONObject.put(SHOW_WHEN, this.showWhen);
            jSONObject.put(AUTO_CANCEL, this.autoCancel);
            jSONObject.put(LARGE_ICON, this.largeIcon);
            jSONObject.put(LARGE_ICON_URL, this.largeIconUrl);
            jSONObject.put(SMALL_ICON, this.smallIcon);
            jSONObject.put(BIG_TEXT, this.bigText);
            jSONObject.put(BIG_PICTURE_URL, this.bigPictureUrl);
            jSONObject.put(SUB_TEXT, this.subText);
            jSONObject.put(SHORTCUT_ID, this.shortcutId);
            jSONObject.put(NUMBER, this.number);
            jSONObject.put(CHANNEL_ID, this.channelId);
            jSONObject.put(SOUND, this.sound);
            jSONObject.put("color", this.color);
            jSONObject.put(GROUP, this.group);
            jSONObject.put(GROUP_SUMMARY, this.groupSummary);
            jSONObject.put(MESSAGE_ID, this.messageId);
            jSONObject.put(PLAY_SOUND, this.playSound);
            jSONObject.put(VIBRATE, this.vibrate);
            jSONObject.put(VIBRATION, this.vibration);
            jSONObject.put(ACTIONS, this.actions);
            jSONObject.put(INVOKE_APP, this.invokeApp);
            jSONObject.put("tag", this.tag);
            jSONObject.put(REPEAT_TYPE, this.repeatType);
            jSONObject.put(REPEAT_TIME, this.repeatTime);
            jSONObject.put(WHEN, this.when);
            jSONObject.put(USES_CHRONOMETER, this.usesChronometer);
            jSONObject.put(TIMEOUT_AFTER, this.timeoutAfter);
            jSONObject.put(ONLY_ALERT_ONCE, this.onlyAlertOnce);
            jSONObject.put(ONGOING, this.ongoing);
            jSONObject.put(REPLY_BUTTON_TEXT, this.reply_button_text);
            jSONObject.put(REPLAY_PLACEHOLDER_TEXT, this.reply_placeholder_text);
            jSONObject.put(ALLOW_WHILE_IDLE, this.allowWhileIdle);
            jSONObject.put(IGNORE_IN_FOREGROUND, this.ignoreInForeground);
            jSONObject.put(USER_INFO, this.userInfo);
            return jSONObject;
        } catch (JSONException e) {
            Log.e(RNPushNotification.LOG_TAG, "Exception while converting RNPushNotificationAttributes to JSON. Returning an empty object", e);
            return new JSONObject();
        }
    }

    public String toString() {
        return "RNPushNotificationAttributes{id='" + this.id + "', message='" + this.message + "', fireDate=" + this.fireDate + ", title='" + this.title + "', ticker='" + this.ticker + "', showWhen=" + this.showWhen + ", autoCancel=" + this.autoCancel + ", largeIcon='" + this.largeIcon + "', largeIconUrl='" + this.largeIconUrl + "', smallIcon='" + this.smallIcon + "', bigText='" + this.bigText + "', subText='" + this.subText + "', bigPictureUrl='" + this.bigPictureUrl + "', shortcutId='" + this.shortcutId + "', number='" + this.number + "', channelId='" + this.channelId + "', sound='" + this.sound + "', color='" + this.color + "', group='" + this.group + "', groupSummary='" + this.groupSummary + "', messageId='" + this.messageId + "', playSound=" + this.playSound + ", vibrate=" + this.vibrate + ", vibration=" + this.vibration + ", actions='" + this.actions + "', invokeApp=" + this.invokeApp + ", tag='" + this.tag + "', repeatType='" + this.repeatType + "', repeatTime=" + this.repeatTime + ", when=" + this.when + ", usesChronometer=" + this.usesChronometer + ", timeoutAfter=" + this.timeoutAfter + ", onlyAlertOnce=" + this.onlyAlertOnce + ", ongoing=" + this.ongoing + ", reply_button_text=" + this.reply_button_text + ", reply_placeholder_text=" + this.reply_placeholder_text + ", allowWhileIdle=" + this.allowWhileIdle + ", ignoreInForeground=" + this.ignoreInForeground + ", userInfo=" + this.userInfo + '}';
    }

    public String getId() {
        return this.id;
    }

    public String getSound() {
        return this.sound;
    }

    public String getMessage() {
        return this.message;
    }

    public String getTitle() {
        return this.title;
    }

    public String getNumber() {
        return this.number;
    }

    public String getUserInfo() {
        return this.userInfo;
    }

    public String getRepeatType() {
        return this.repeatType;
    }

    public double getFireDate() {
        return this.fireDate;
    }
}
