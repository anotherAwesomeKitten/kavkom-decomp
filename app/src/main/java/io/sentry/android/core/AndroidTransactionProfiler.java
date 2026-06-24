package io.sentry.android.core;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import io.sentry.DateUtils;
import io.sentry.HubAdapter;
import io.sentry.IHub;
import io.sentry.ILogger;
import io.sentry.ISentryExecutorService;
import io.sentry.ITransaction;
import io.sentry.ITransactionProfiler;
import io.sentry.PerformanceCollectionData;
import io.sentry.ProfilingTraceData;
import io.sentry.ProfilingTransactionData;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.android.core.AndroidProfiler;
import io.sentry.android.core.internal.util.CpuInfoUtils;
import io.sentry.android.core.internal.util.SentryFrameMetricsCollector;
import io.sentry.util.Objects;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
final class AndroidTransactionProfiler implements ITransactionProfiler {
    private final BuildInfoProvider buildInfoProvider;
    private final Context context;
    private ProfilingTransactionData currentProfilingTransactionData;
    private final ISentryExecutorService executorService;
    private final SentryFrameMetricsCollector frameMetricsCollector;
    private boolean isInitialized;
    private final boolean isProfilingEnabled;
    private final ILogger logger;
    private long profileStartCpuMillis;
    private long profileStartNanos;
    private Date profileStartTimestamp;
    private AndroidProfiler profiler;
    private final String profilingTracesDirPath;
    private final int profilingTracesHz;
    private int transactionsCounter;

    @Deprecated
    public AndroidTransactionProfiler(Context context, SentryAndroidOptions sentryAndroidOptions, BuildInfoProvider buildInfoProvider, SentryFrameMetricsCollector sentryFrameMetricsCollector, IHub iHub) {
        this(context, sentryAndroidOptions, buildInfoProvider, sentryFrameMetricsCollector);
    }

    public AndroidTransactionProfiler(Context context, SentryAndroidOptions sentryAndroidOptions, BuildInfoProvider buildInfoProvider, SentryFrameMetricsCollector sentryFrameMetricsCollector) {
        this(context, buildInfoProvider, sentryFrameMetricsCollector, sentryAndroidOptions.getLogger(), sentryAndroidOptions.getProfilingTracesDirPath(), sentryAndroidOptions.isProfilingEnabled(), sentryAndroidOptions.getProfilingTracesHz(), sentryAndroidOptions.getExecutorService());
    }

    public AndroidTransactionProfiler(Context context, BuildInfoProvider buildInfoProvider, SentryFrameMetricsCollector sentryFrameMetricsCollector, ILogger iLogger, String str, boolean z, int i, ISentryExecutorService iSentryExecutorService) {
        this.isInitialized = false;
        this.transactionsCounter = 0;
        this.profiler = null;
        this.context = (Context) Objects.requireNonNull(ContextUtils.getApplicationContext(context), "The application context is required");
        this.logger = (ILogger) Objects.requireNonNull(iLogger, "ILogger is required");
        this.frameMetricsCollector = (SentryFrameMetricsCollector) Objects.requireNonNull(sentryFrameMetricsCollector, "SentryFrameMetricsCollector is required");
        this.buildInfoProvider = (BuildInfoProvider) Objects.requireNonNull(buildInfoProvider, "The BuildInfoProvider is required.");
        this.profilingTracesDirPath = str;
        this.isProfilingEnabled = z;
        this.profilingTracesHz = i;
        this.executorService = (ISentryExecutorService) Objects.requireNonNull(iSentryExecutorService, "The ISentryExecutorService is required.");
        this.profileStartTimestamp = DateUtils.getCurrentDateTime();
    }

    private void init() {
        if (this.isInitialized) {
            return;
        }
        this.isInitialized = true;
        if (!this.isProfilingEnabled) {
            this.logger.log(SentryLevel.INFO, "Profiling is disabled in options.", new Object[0]);
            return;
        }
        if (this.profilingTracesDirPath == null) {
            this.logger.log(SentryLevel.WARNING, "Disabling profiling because no profiling traces dir path is defined in options.", new Object[0]);
        } else if (this.profilingTracesHz <= 0) {
            this.logger.log(SentryLevel.WARNING, "Disabling profiling because trace rate is set to %d", Integer.valueOf(this.profilingTracesHz));
        } else {
            this.profiler = new AndroidProfiler(this.profilingTracesDirPath, ((int) TimeUnit.SECONDS.toMicros(1L)) / this.profilingTracesHz, this.frameMetricsCollector, this.executorService, this.logger, this.buildInfoProvider);
        }
    }

    @Override // io.sentry.ITransactionProfiler
    public synchronized void start() {
        if (this.buildInfoProvider.getSdkInfoVersion() < 22) {
            return;
        }
        init();
        int i = this.transactionsCounter + 1;
        this.transactionsCounter = i;
        if (i == 1 && onFirstStart()) {
            this.logger.log(SentryLevel.DEBUG, "Profiler started.", new Object[0]);
        } else {
            this.transactionsCounter--;
            this.logger.log(SentryLevel.WARNING, "A profile is already running. This profile will be ignored.", new Object[0]);
        }
    }

    private boolean onFirstStart() {
        AndroidProfiler.ProfileStartData profileStartDataStart;
        AndroidProfiler androidProfiler = this.profiler;
        if (androidProfiler == null || (profileStartDataStart = androidProfiler.start()) == null) {
            return false;
        }
        this.profileStartNanos = profileStartDataStart.startNanos;
        this.profileStartCpuMillis = profileStartDataStart.startCpuMillis;
        this.profileStartTimestamp = profileStartDataStart.startTimestamp;
        return true;
    }

    @Override // io.sentry.ITransactionProfiler
    public synchronized void bindTransaction(ITransaction iTransaction) {
        if (this.transactionsCounter > 0 && this.currentProfilingTransactionData == null) {
            this.currentProfilingTransactionData = new ProfilingTransactionData(iTransaction, Long.valueOf(this.profileStartNanos), Long.valueOf(this.profileStartCpuMillis));
        }
    }

    @Override // io.sentry.ITransactionProfiler
    public synchronized ProfilingTraceData onTransactionFinish(ITransaction iTransaction, List<PerformanceCollectionData> list, SentryOptions sentryOptions) throws Throwable {
        try {
        } catch (Throwable th) {
            th = th;
        }
        try {
            return onTransactionFinish(iTransaction.getName(), iTransaction.getEventId().toString(), iTransaction.getSpanContext().getTraceId().toString(), false, list, sentryOptions);
        } catch (Throwable th2) {
            th = th2;
            throw th;
        }
    }

    private synchronized ProfilingTraceData onTransactionFinish(String str, String str2, String str3, boolean z, List<PerformanceCollectionData> list, SentryOptions sentryOptions) {
        String str4;
        if (this.profiler == null) {
            return null;
        }
        if (this.buildInfoProvider.getSdkInfoVersion() < 22) {
            return null;
        }
        ProfilingTransactionData profilingTransactionData = this.currentProfilingTransactionData;
        if (profilingTransactionData != null && profilingTransactionData.getId().equals(str2)) {
            int i = this.transactionsCounter;
            if (i > 0) {
                this.transactionsCounter = i - 1;
            }
            this.logger.log(SentryLevel.DEBUG, "Transaction %s (%s) finished.", str, str3);
            if (this.transactionsCounter != 0) {
                ProfilingTransactionData profilingTransactionData2 = this.currentProfilingTransactionData;
                if (profilingTransactionData2 != null) {
                    profilingTransactionData2.notifyFinish(Long.valueOf(SystemClock.elapsedRealtimeNanos()), Long.valueOf(this.profileStartNanos), Long.valueOf(Process.getElapsedCpuTime()), Long.valueOf(this.profileStartCpuMillis));
                }
                return null;
            }
            boolean z2 = false;
            AndroidProfiler.ProfileEndData profileEndDataEndAndCollect = this.profiler.endAndCollect(false, list);
            if (profileEndDataEndAndCollect == null) {
                return null;
            }
            long j = profileEndDataEndAndCollect.endNanos - this.profileStartNanos;
            ArrayList arrayList = new ArrayList(1);
            ProfilingTransactionData profilingTransactionData3 = this.currentProfilingTransactionData;
            if (profilingTransactionData3 != null) {
                arrayList.add(profilingTransactionData3);
            }
            this.currentProfilingTransactionData = null;
            this.transactionsCounter = 0;
            String string = "0";
            ActivityManager.MemoryInfo memInfo = getMemInfo();
            if (memInfo != null) {
                string = Long.toString(memInfo.totalMem);
            }
            String str5 = string;
            String[] strArr = Build.SUPPORTED_ABIS;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((ProfilingTransactionData) it.next()).notifyFinish(Long.valueOf(profileEndDataEndAndCollect.endNanos), Long.valueOf(this.profileStartNanos), Long.valueOf(profileEndDataEndAndCollect.endCpuMillis), Long.valueOf(this.profileStartCpuMillis));
                arrayList = arrayList;
                z2 = z2;
            }
            boolean z3 = z2;
            ArrayList arrayList2 = arrayList;
            File file = profileEndDataEndAndCollect.traceFile;
            Date date = this.profileStartTimestamp;
            String string2 = Long.toString(j);
            int sdkInfoVersion = this.buildInfoProvider.getSdkInfoVersion();
            String str6 = (strArr == null || strArr.length <= 0) ? "" : strArr[z3 ? 1 : 0];
            Callable callable = new Callable() { // from class: io.sentry.android.core.AndroidTransactionProfiler$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return CpuInfoUtils.getInstance().readMaxFrequencies();
                }
            };
            String manufacturer = this.buildInfoProvider.getManufacturer();
            String model = this.buildInfoProvider.getModel();
            String versionRelease = this.buildInfoProvider.getVersionRelease();
            Boolean boolIsEmulator = this.buildInfoProvider.isEmulator();
            String proguardUuid = sentryOptions.getProguardUuid();
            String release = sentryOptions.getRelease();
            String environment = sentryOptions.getEnvironment();
            if (profileEndDataEndAndCollect.didTimeout || z) {
                str4 = ProfilingTraceData.TRUNCATION_REASON_TIMEOUT;
            } else {
                str4 = ProfilingTraceData.TRUNCATION_REASON_NORMAL;
            }
            return new ProfilingTraceData(file, date, arrayList2, str, str2, str3, string2, sdkInfoVersion, str6, callable, manufacturer, model, versionRelease, boolIsEmulator, str5, proguardUuid, release, environment, str4, profileEndDataEndAndCollect.measurementsMap);
        }
        this.logger.log(SentryLevel.INFO, "Transaction %s (%s) finished, but was not currently being profiled. Skipping", str, str3);
        return null;
    }

    @Override // io.sentry.ITransactionProfiler
    public boolean isRunning() {
        return this.transactionsCounter != 0;
    }

    @Override // io.sentry.ITransactionProfiler
    public void close() {
        AndroidTransactionProfiler androidTransactionProfiler;
        ProfilingTransactionData profilingTransactionData = this.currentProfilingTransactionData;
        if (profilingTransactionData != null) {
            androidTransactionProfiler = this;
            androidTransactionProfiler.onTransactionFinish(profilingTransactionData.getName(), this.currentProfilingTransactionData.getId(), this.currentProfilingTransactionData.getTraceId(), true, null, HubAdapter.getInstance().getOptions());
        } else {
            androidTransactionProfiler = this;
            int i = androidTransactionProfiler.transactionsCounter;
            if (i != 0) {
                androidTransactionProfiler.transactionsCounter = i - 1;
            }
        }
        AndroidProfiler androidProfiler = androidTransactionProfiler.profiler;
        if (androidProfiler != null) {
            androidProfiler.close();
        }
    }

    private ActivityManager.MemoryInfo getMemInfo() {
        try {
            ActivityManager activityManager = (ActivityManager) this.context.getSystemService("activity");
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            if (activityManager == null) {
                this.logger.log(SentryLevel.INFO, "Error getting MemoryInfo.", new Object[0]);
                return null;
            }
            activityManager.getMemoryInfo(memoryInfo);
            return memoryInfo;
        } catch (Throwable th) {
            this.logger.log(SentryLevel.ERROR, "Error getting MemoryInfo.", th);
            return null;
        }
    }

    int getTransactionsCounter() {
        return this.transactionsCounter;
    }
}
