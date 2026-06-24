package com.swmansion.rnscreens.gamma.helpers;

import androidx.core.app.NotificationCompat;
import com.swmansion.rnscreens.gamma.common.NamingAwareEventType;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EventHelpers.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a8\u0010\u0000\u001a*\u0012\u0004\u0012\u00020\u0002\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0003j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002`\u00040\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¨\u0006\u0007"}, d2 = {"makeEventRegistrationInfo", "Lkotlin/Pair;", "", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", NotificationCompat.CATEGORY_EVENT, "Lcom/swmansion/rnscreens/gamma/common/NamingAwareEventType;", "react-native-screens_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class EventHelpersKt {
    public static final Pair<String, HashMap<String, String>> makeEventRegistrationInfo(NamingAwareEventType event) {
        Intrinsics.checkNotNullParameter(event, "event");
        return TuplesKt.to(event.getEventName(), MapsKt.hashMapOf(TuplesKt.to("registrationName", event.getEventRegistrationName())));
    }
}
