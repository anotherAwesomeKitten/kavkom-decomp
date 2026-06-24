package com.lugg.RNCConfig;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
class MapConverter {
    MapConverter() {
    }

    public static WritableMap convertMapToWritableMap(Map<String, Object> map) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                putValueInMap(writableMapCreateMap, entry.getKey(), entry.getValue());
            }
        }
        return writableMapCreateMap;
    }

    private static void putValueInMap(WritableMap writableMap, String str, Object obj) {
        if (obj == null) {
            writableMap.putNull(str);
            return;
        }
        if (obj instanceof String) {
            writableMap.putString(str, (String) obj);
            return;
        }
        if (obj instanceof Integer) {
            writableMap.putInt(str, ((Integer) obj).intValue());
            return;
        }
        if (obj instanceof Long) {
            writableMap.putLong(str, ((Long) obj).longValue());
            return;
        }
        if (obj instanceof Double) {
            writableMap.putDouble(str, ((Double) obj).doubleValue());
            return;
        }
        if (obj instanceof Float) {
            writableMap.putDouble(str, ((Float) obj).floatValue());
            return;
        }
        if (obj instanceof Boolean) {
            writableMap.putBoolean(str, ((Boolean) obj).booleanValue());
            return;
        }
        if (obj instanceof Map) {
            writableMap.putMap(str, convertMapToWritableMap((Map) obj));
        } else if (obj instanceof List) {
            writableMap.putArray(str, convertListToWritableArray((List) obj));
        } else {
            writableMap.putString(str, obj.toString());
        }
    }

    private static WritableArray convertListToWritableArray(List<Object> list) {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        if (list != null) {
            for (Object obj : list) {
                if (obj == null) {
                    writableArrayCreateArray.pushNull();
                } else if (obj instanceof String) {
                    writableArrayCreateArray.pushString((String) obj);
                } else if (obj instanceof Integer) {
                    writableArrayCreateArray.pushInt(((Integer) obj).intValue());
                } else if (obj instanceof Long) {
                    writableArrayCreateArray.pushInt(((Long) obj).intValue());
                } else if (obj instanceof Double) {
                    writableArrayCreateArray.pushDouble(((Double) obj).doubleValue());
                } else if (obj instanceof Float) {
                    writableArrayCreateArray.pushDouble(((Float) obj).floatValue());
                } else if (obj instanceof Boolean) {
                    writableArrayCreateArray.pushBoolean(((Boolean) obj).booleanValue());
                } else if (obj instanceof Map) {
                    writableArrayCreateArray.pushMap(convertMapToWritableMap((Map) obj));
                } else if (obj instanceof List) {
                    writableArrayCreateArray.pushArray(convertListToWritableArray((List) obj));
                } else {
                    writableArrayCreateArray.pushString(obj.toString());
                }
            }
        }
        return writableArrayCreateArray;
    }
}
