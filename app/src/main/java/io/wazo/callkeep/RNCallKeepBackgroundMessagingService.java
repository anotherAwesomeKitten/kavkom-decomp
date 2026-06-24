package io.wazo.callkeep;

import android.content.Intent;
import android.os.Bundle;
import com.facebook.react.HeadlessJsTaskService;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.jstasks.HeadlessJsTaskConfig;
import com.facebook.react.jstasks.LinearCountingRetryPolicy;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes3.dex */
public class RNCallKeepBackgroundMessagingService extends HeadlessJsTaskService {
    @Override // com.facebook.react.HeadlessJsTaskService
    @Nullable
    protected HeadlessJsTaskConfig getTaskConfig(Intent intent) {
        Bundle extras = intent.getExtras();
        return new HeadlessJsTaskConfig("RNCallKeepBackgroundMessage", Arguments.fromBundle(extras), 60000L, false, new LinearCountingRetryPolicy(5, 500));
    }
}
