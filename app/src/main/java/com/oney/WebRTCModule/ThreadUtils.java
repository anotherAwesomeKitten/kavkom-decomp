package com.oney.WebRTCModule;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes2.dex */
final class ThreadUtils {
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    ThreadUtils() {
    }

    public static void runOnExecutor(Runnable runnable) {
        executor.execute(runnable);
    }

    public static <T> Future<T> submitToExecutor(Callable<T> callable) {
        return executor.submit(callable);
    }

    public static Future<?> submitToExecutor(Runnable runnable) {
        return executor.submit(runnable);
    }
}
