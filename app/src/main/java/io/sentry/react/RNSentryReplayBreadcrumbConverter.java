package io.sentry.react;

import androidx.core.app.NotificationCompat;
import com.facebook.common.util.UriUtil;
import com.facebook.react.uimanager.events.PointerEventHelper;
import com.google.firebase.messaging.Constants;
import io.sentry.Breadcrumb;
import io.sentry.android.replay.DefaultReplayBreadcrumbConverter;
import io.sentry.protocol.Response;
import io.sentry.rrweb.RRWebBreadcrumbEvent;
import io.sentry.rrweb.RRWebEvent;
import io.sentry.rrweb.RRWebSpanEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class RNSentryReplayBreadcrumbConverter extends DefaultReplayBreadcrumbConverter {
    @Override // io.sentry.android.replay.DefaultReplayBreadcrumbConverter, io.sentry.ReplayBreadcrumbConverter
    public RRWebEvent convert(Breadcrumb breadcrumb) {
        if (breadcrumb.getCategory() == null || breadcrumb.getCategory().equals("sentry.event") || breadcrumb.getCategory().equals("sentry.transaction") || breadcrumb.getCategory().equals(UriUtil.HTTP_SCHEME)) {
            return null;
        }
        if (breadcrumb.getCategory().equals(PointerEventHelper.POINTER_TYPE_TOUCH)) {
            return convertTouchBreadcrumb(breadcrumb);
        }
        if (breadcrumb.getCategory().equals(NotificationCompat.CATEGORY_NAVIGATION)) {
            return convertNavigationBreadcrumb(breadcrumb);
        }
        if (breadcrumb.getCategory().equals("xhr")) {
            return convertNetworkBreadcrumb(breadcrumb);
        }
        RRWebEvent rRWebEventConvert = super.convert(breadcrumb);
        if (rRWebEventConvert instanceof RRWebBreadcrumbEvent) {
            RRWebBreadcrumbEvent rRWebBreadcrumbEvent = (RRWebBreadcrumbEvent) rRWebEventConvert;
            if (rRWebBreadcrumbEvent.getCategory() != null && rRWebBreadcrumbEvent.getCategory().equals(NotificationCompat.CATEGORY_NAVIGATION)) {
                return null;
            }
        }
        return rRWebEventConvert;
    }

    public RRWebEvent convertNavigationBreadcrumb(Breadcrumb breadcrumb) {
        RRWebBreadcrumbEvent rRWebBreadcrumbEvent = new RRWebBreadcrumbEvent();
        rRWebBreadcrumbEvent.setCategory(breadcrumb.getCategory());
        setRRWebEventDefaultsFrom(rRWebBreadcrumbEvent, breadcrumb);
        return rRWebBreadcrumbEvent;
    }

    public RRWebEvent convertTouchBreadcrumb(Breadcrumb breadcrumb) {
        RRWebBreadcrumbEvent rRWebBreadcrumbEvent = new RRWebBreadcrumbEvent();
        rRWebBreadcrumbEvent.setCategory("ui.tap");
        rRWebBreadcrumbEvent.setMessage(getTouchPathMessage(breadcrumb.getData("path")));
        setRRWebEventDefaultsFrom(rRWebBreadcrumbEvent, breadcrumb);
        return rRWebBreadcrumbEvent;
    }

    public static String getTouchPathMessage(Object obj) {
        if (!(obj instanceof List)) {
            return null;
        }
        List list = (List) obj;
        if (list.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int iMin = Math.min(3, list.size() - 1); iMin >= 0; iMin--) {
            Object obj2 = list.get(iMin);
            if (!(obj2 instanceof Map)) {
                return null;
            }
            Map map = (Map) obj2;
            Object obj3 = map.get("name");
            Object obj4 = map.get(Constants.ScionAnalytics.PARAM_LABEL);
            boolean z = obj4 instanceof String;
            if (!(obj3 instanceof String) && !z) {
                return null;
            }
            if (z) {
                sb.append(obj4);
            } else {
                sb.append(obj3);
            }
            Object obj5 = map.get("element");
            Object obj6 = map.get("file");
            boolean z2 = obj5 instanceof String;
            boolean z3 = obj6 instanceof String;
            if (z2 && z3) {
                sb.append('(').append(obj5).append(", ").append(obj6).append(')');
            } else if (z2) {
                sb.append('(').append(obj5).append(')');
            } else if (z3) {
                sb.append('(').append(obj6).append(')');
            }
            if (iMin > 0) {
                sb.append(" > ");
            }
        }
        return sb.toString();
    }

    public RRWebEvent convertNetworkBreadcrumb(Breadcrumb breadcrumb) {
        Double d = breadcrumb.getData("start_timestamp") instanceof Number ? (Double) breadcrumb.getData("start_timestamp") : null;
        Double d2 = breadcrumb.getData("end_timestamp") instanceof Number ? (Double) breadcrumb.getData("end_timestamp") : null;
        String str = breadcrumb.getData("url") instanceof String ? (String) breadcrumb.getData("url") : null;
        if (d == null || d2 == null || str == null) {
            return null;
        }
        HashMap map = new HashMap();
        if (breadcrumb.getData("method") instanceof String) {
            map.put("method", breadcrumb.getData("method"));
        }
        if (breadcrumb.getData(Response.JsonKeys.STATUS_CODE) instanceof Double) {
            Double d3 = (Double) breadcrumb.getData(Response.JsonKeys.STATUS_CODE);
            if (d3.doubleValue() > 0.0d) {
                map.put("statusCode", Integer.valueOf(d3.intValue()));
            }
        }
        if (breadcrumb.getData("request_body_size") instanceof Double) {
            map.put("requestBodySize", breadcrumb.getData("request_body_size"));
        }
        if (breadcrumb.getData("response_body_size") instanceof Double) {
            map.put("responseBodySize", breadcrumb.getData("response_body_size"));
        }
        RRWebSpanEvent rRWebSpanEvent = new RRWebSpanEvent();
        rRWebSpanEvent.setOp("resource.http");
        rRWebSpanEvent.setStartTimestamp(d.doubleValue() / 1000.0d);
        rRWebSpanEvent.setEndTimestamp(d2.doubleValue() / 1000.0d);
        rRWebSpanEvent.setDescription(str);
        rRWebSpanEvent.setData(map);
        return rRWebSpanEvent;
    }

    private void setRRWebEventDefaultsFrom(RRWebBreadcrumbEvent rRWebBreadcrumbEvent, Breadcrumb breadcrumb) {
        rRWebBreadcrumbEvent.setLevel(breadcrumb.getLevel());
        rRWebBreadcrumbEvent.setData(breadcrumb.getData());
        rRWebBreadcrumbEvent.setTimestamp(breadcrumb.getTimestamp().getTime());
        rRWebBreadcrumbEvent.setBreadcrumbTimestamp(breadcrumb.getTimestamp().getTime() / 1000.0d);
        rRWebBreadcrumbEvent.setBreadcrumbType(com.facebook.hermes.intl.Constants.COLLATION_DEFAULT);
    }
}
