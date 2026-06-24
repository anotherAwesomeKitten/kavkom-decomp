package com.oney.WebRTCModule;

import android.util.Log;
import java.util.Map;
import org.webrtc.RTCStats;
import org.webrtc.RTCStatsReport;

/* JADX INFO: loaded from: classes2.dex */
public class StringUtils {
    private static final String TAG = "StringUtils";

    public static String statsToJSON(RTCStatsReport rTCStatsReport) {
        StringBuilder sb = new StringBuilder("[");
        Map<String, RTCStats> statsMap = rTCStatsReport.getStatsMap();
        boolean z = true;
        for (String str : rTCStatsReport.getStatsMap().keySet()) {
            if (z) {
                z = false;
            } else {
                sb.append(",");
            }
            sb.append("[\"").append(str).append("\",{");
            RTCStats rTCStats = statsMap.get(str);
            sb.append("\"timestamp\":").append(rTCStats.getTimestampUs() / 1000.0d).append(",\"type\":\"").append(rTCStats.getType()).append("\",\"id\":\"").append(rTCStats.getId()).append("\"");
            for (Map.Entry<String, Object> entry : rTCStats.getMembers().entrySet()) {
                sb.append(",").append("\"").append(entry.getKey()).append("\":");
                appendValue(sb, entry.getValue());
            }
            sb.append("}]");
        }
        sb.append("]");
        return sb.toString();
    }

    private static void appendValue(StringBuilder sb, Object obj) {
        if (obj instanceof Object[]) {
            Object[] objArr = (Object[]) obj;
            sb.append("[");
            for (int i = 0; i < objArr.length; i++) {
                if (i != 0) {
                    sb.append(",");
                }
                appendValue(sb, objArr[i]);
            }
            sb.append("]");
            return;
        }
        if (obj instanceof Map) {
            try {
                sb.append("{");
                boolean z = true;
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    if (z) {
                        z = false;
                    } else {
                        sb.append(",");
                    }
                    sb.append("\"").append((String) entry.getKey()).append("\":");
                    appendValue(sb, entry.getValue());
                }
                sb.append("}");
                return;
            } catch (ClassCastException unused) {
                Log.e(TAG, "Error parsing stats value " + obj);
                return;
            }
        }
        if (obj instanceof String) {
            sb.append("\"").append(obj).append("\"");
        } else {
            sb.append(obj);
        }
    }
}
