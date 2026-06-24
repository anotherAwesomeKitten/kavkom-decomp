package com.facebook.react.runtime.internal.bolts;

import androidx.core.app.NotificationCompat;
import com.facebook.react.interfaces.TaskInterface;
import com.facebook.react.runtime.internal.bolts.Task;
import io.sentry.protocol.SentryStackFrame;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Task.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 2*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u00012B\t\b\u0010¢\u0006\u0004\b\u0003\u0010\u0004B\u0013\b\u0012\u0012\b\u0010\u0005\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0003\u0010\u0006B\u0011\b\u0012\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\u0003\u0010\tJ\b\u0010\u0015\u001a\u00020\bH\u0016J\b\u0010\u0016\u001a\u00020\bH\u0016J\b\u0010\u0017\u001a\u00020\bH\u0016J\u000f\u0010\u0018\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\u0019J\u0010\u0010\u001a\u001a\n\u0018\u00010\u000fj\u0004\u0018\u0001`\u0010H\u0016J\b\u0010\u001b\u001a\u00020\u0014H\u0016J\u0018\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0000J2\u0010\"\u001a\b\u0012\u0004\u0012\u0002H#0\u0000\"\u0004\b\u0001\u0010#2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H#0\u00132\b\b\u0002\u0010%\u001a\u00020&H\u0007J8\u0010'\u001a\b\u0012\u0004\u0012\u0002H#0\u0000\"\u0004\b\u0001\u0010#2\u0018\u0010$\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u0002H#0\u00000\u00132\b\b\u0002\u0010%\u001a\u00020&H\u0007J0\u0010(\u001a\b\u0012\u0004\u0012\u0002H#0\u0000\"\u0004\b\u0001\u0010#2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H#0\u00132\b\b\u0002\u0010%\u001a\u00020&J6\u0010)\u001a\b\u0012\u0004\u0012\u0002H#0\u0000\"\u0004\b\u0001\u0010#2\u0018\u0010$\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u0002H#0\u00000\u00132\b\b\u0002\u0010%\u001a\u00020&J\b\u0010*\u001a\u00020\u0014H\u0002J\r\u0010+\u001a\u00020\bH\u0000¢\u0006\u0002\b,J\u0019\u0010-\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00018\u0000H\u0000¢\u0006\u0004\b.\u0010/J\u001d\u00100\u001a\u00020\b2\u000e\u0010\u000e\u001a\n\u0018\u00010\u000fj\u0004\u0018\u0001`\u0010H\u0000¢\u0006\u0002\b1R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\rR\u0016\u0010\u000e\u001a\n\u0018\u00010\u000fj\u0004\u0018\u0001`\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00140\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/facebook/react/runtime/internal/bolts/Task;", "TResult", "Lcom/facebook/react/interfaces/TaskInterface;", "<init>", "()V", "result", "(Ljava/lang/Object;)V", "cancelled", "", "(Z)V", SentryStackFrame.JsonKeys.LOCK, "Ljava/lang/Object;", "complete", "Ljava/lang/Object;", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "continuations", "", "Lcom/facebook/react/runtime/internal/bolts/Continuation;", "", "isCompleted", "isCancelled", "isFaulted", "getResult", "()Ljava/lang/Object;", "getError", "waitForCompletion", "duration", "", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "makeVoid", "Ljava/lang/Void;", "continueWith", "TContinuationResult", "continuation", "executor", "Ljava/util/concurrent/Executor;", "continueWithTask", "onSuccess", "onSuccessTask", "runContinuations", "trySetCancelled", "trySetCancelled$ReactAndroid_release", "trySetResult", "trySetResult$ReactAndroid_release", "(Ljava/lang/Object;)Z", "trySetError", "trySetError$ReactAndroid_release", "Companion", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class Task<TResult> implements TaskInterface<TResult> {
    private boolean cancelled;
    private boolean complete;
    private Exception error;
    private TResult result;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final Executor IMMEDIATE_EXECUTOR = Executors.IMMEDIATE;
    public static final Executor UI_THREAD_EXECUTOR = Executors.UI_THREAD;
    private static final Task<Void> TASK_NULL = new Task<>((Object) null);
    private static final Task<Boolean> TASK_TRUE = new Task<>(true);
    private static final Task<Boolean> TASK_FALSE = new Task<>(false);
    private static final Task<Object> TASK_CANCELLED = new Task<>(true);
    private final Object lock = new Object();
    private final List<Continuation<TResult, Unit>> continuations = new ArrayList();

    @JvmStatic
    public static final <TResult> Task<TResult> call(Callable<Task<TResult>> callable, Executor executor) {
        return INSTANCE.call(callable, executor);
    }

    @JvmStatic
    public static final <TResult> Task<TResult> cancelled() {
        return INSTANCE.cancelled();
    }

    @JvmStatic
    public static final <TResult> Task<TResult> forError(Exception exc) {
        return INSTANCE.forError(exc);
    }

    @JvmStatic
    public static final <TResult> Task<TResult> forResult(TResult tresult) {
        return INSTANCE.forResult(tresult);
    }

    public final <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        return continueWith$default(this, continuation, null, 2, null);
    }

    public final <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        return continueWithTask$default(this, continuation, null, 2, null);
    }

    public Task() {
    }

    private Task(TResult tresult) {
        trySetResult$ReactAndroid_release(tresult);
    }

    private Task(boolean z) {
        if (z) {
            trySetCancelled$ReactAndroid_release();
        } else {
            trySetResult$ReactAndroid_release(null);
        }
    }

    @Override // com.facebook.react.interfaces.TaskInterface
    public boolean isCompleted() {
        boolean z;
        synchronized (this.lock) {
            z = this.complete;
        }
        return z;
    }

    @Override // com.facebook.react.interfaces.TaskInterface
    public boolean isCancelled() {
        boolean z;
        synchronized (this.lock) {
            z = this.cancelled;
        }
        return z;
    }

    @Override // com.facebook.react.interfaces.TaskInterface
    public boolean isFaulted() {
        boolean z;
        synchronized (this.lock) {
            z = getError() != null;
        }
        return z;
    }

    @Override // com.facebook.react.interfaces.TaskInterface
    public TResult getResult() {
        TResult tresult;
        synchronized (this.lock) {
            tresult = this.result;
        }
        return tresult;
    }

    @Override // com.facebook.react.interfaces.TaskInterface
    public Exception getError() {
        Exception exc;
        synchronized (this.lock) {
            exc = this.error;
        }
        return exc;
    }

    @Override // com.facebook.react.interfaces.TaskInterface
    public void waitForCompletion() throws InterruptedException {
        synchronized (this.lock) {
            if (!isCompleted()) {
                this.lock.wait();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.facebook.react.interfaces.TaskInterface
    public boolean waitForCompletion(long duration, TimeUnit timeUnit) throws InterruptedException {
        boolean zIsCompleted;
        Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
        synchronized (this.lock) {
            if (!isCompleted()) {
                this.lock.wait(timeUnit.toMillis(duration));
            }
            zIsCompleted = isCompleted();
        }
        return zIsCompleted;
    }

    public final Task<Void> makeVoid() {
        return continueWithTask$default(this, new Continuation() { // from class: com.facebook.react.runtime.internal.bolts.Task$$ExternalSyntheticLambda0
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task) {
                return Task.makeVoid$lambda$7(task);
            }
        }, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Task makeVoid$lambda$7(Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        return task.isCancelled() ? INSTANCE.cancelled() : task.isFaulted() ? INSTANCE.forError(task.getError()) : TASK_NULL;
    }

    public static /* synthetic */ Task continueWith$default(Task task, Continuation continuation, Executor executor, int i, Object obj) {
        if ((i & 2) != 0) {
            executor = IMMEDIATE_EXECUTOR;
        }
        return task.continueWith(continuation, executor);
    }

    public final <TContinuationResult> Task<TContinuationResult> continueWith(final Continuation<TResult, TContinuationResult> continuation, final Executor executor) {
        boolean zIsCompleted;
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        Intrinsics.checkNotNullParameter(executor, "executor");
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        synchronized (this.lock) {
            zIsCompleted = isCompleted();
            if (!zIsCompleted) {
                this.continuations.add(new Continuation() { // from class: com.facebook.react.runtime.internal.bolts.Task$$ExternalSyntheticLambda3
                    @Override // com.facebook.react.runtime.internal.bolts.Continuation
                    public final Object then(Task task) {
                        return Task.continueWith$lambda$9$lambda$8(taskCompletionSource, continuation, executor, task);
                    }
                });
            }
            Unit unit = Unit.INSTANCE;
        }
        if (zIsCompleted) {
            INSTANCE.completeImmediately(taskCompletionSource, continuation, this, executor);
        }
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit continueWith$lambda$9$lambda$8(TaskCompletionSource taskCompletionSource, Continuation continuation, Executor executor, Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        INSTANCE.completeImmediately(taskCompletionSource, continuation, task, executor);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Task continueWithTask$default(Task task, Continuation continuation, Executor executor, int i, Object obj) {
        if ((i & 2) != 0) {
            executor = IMMEDIATE_EXECUTOR;
        }
        return task.continueWithTask(continuation, executor);
    }

    public final <TContinuationResult> Task<TContinuationResult> continueWithTask(final Continuation<TResult, Task<TContinuationResult>> continuation, final Executor executor) {
        boolean zIsCompleted;
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        Intrinsics.checkNotNullParameter(executor, "executor");
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        synchronized (this.lock) {
            zIsCompleted = isCompleted();
            if (!zIsCompleted) {
                this.continuations.add(new Continuation() { // from class: com.facebook.react.runtime.internal.bolts.Task$$ExternalSyntheticLambda1
                    @Override // com.facebook.react.runtime.internal.bolts.Continuation
                    public final Object then(Task task) {
                        return Task.continueWithTask$lambda$11$lambda$10(taskCompletionSource, continuation, executor, task);
                    }
                });
            }
            Unit unit = Unit.INSTANCE;
        }
        if (zIsCompleted) {
            INSTANCE.completeAfterTask(taskCompletionSource, continuation, this, executor);
        }
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit continueWithTask$lambda$11$lambda$10(TaskCompletionSource taskCompletionSource, Continuation continuation, Executor executor, Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        INSTANCE.completeAfterTask(taskCompletionSource, continuation, task, executor);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Task onSuccess$default(Task task, Continuation continuation, Executor executor, int i, Object obj) {
        if ((i & 2) != 0) {
            executor = IMMEDIATE_EXECUTOR;
        }
        return task.onSuccess(continuation, executor);
    }

    public final <TContinuationResult> Task<TContinuationResult> onSuccess(final Continuation<TResult, TContinuationResult> continuation, Executor executor) {
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        Intrinsics.checkNotNullParameter(executor, "executor");
        return continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.internal.bolts.Task$$ExternalSyntheticLambda2
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task) {
                return Task.onSuccess$lambda$12(continuation, task);
            }
        }, executor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Task onSuccess$lambda$12(Continuation continuation, Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        return task.isCancelled() ? INSTANCE.cancelled() : task.isFaulted() ? INSTANCE.forError(task.getError()) : continueWith$default(task, continuation, null, 2, null);
    }

    public static /* synthetic */ Task onSuccessTask$default(Task task, Continuation continuation, Executor executor, int i, Object obj) {
        if ((i & 2) != 0) {
            executor = IMMEDIATE_EXECUTOR;
        }
        return task.onSuccessTask(continuation, executor);
    }

    public final <TContinuationResult> Task<TContinuationResult> onSuccessTask(final Continuation<TResult, Task<TContinuationResult>> continuation, Executor executor) {
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        Intrinsics.checkNotNullParameter(executor, "executor");
        return continueWithTask(new Continuation() { // from class: com.facebook.react.runtime.internal.bolts.Task$$ExternalSyntheticLambda4
            @Override // com.facebook.react.runtime.internal.bolts.Continuation
            public final Object then(Task task) {
                return Task.onSuccessTask$lambda$13(continuation, task);
            }
        }, executor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Task onSuccessTask$lambda$13(Continuation continuation, Task task) {
        Intrinsics.checkNotNullParameter(task, "task");
        return task.isCancelled() ? INSTANCE.cancelled() : task.isFaulted() ? INSTANCE.forError(task.getError()) : continueWithTask$default(task, continuation, null, 2, null);
    }

    private final void runContinuations() {
        synchronized (this.lock) {
            Iterator<Continuation<TResult, Unit>> it = this.continuations.iterator();
            while (it.hasNext()) {
                try {
                    try {
                        it.next().then(this);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } catch (RuntimeException e2) {
                    throw e2;
                }
            }
            this.continuations.clear();
            Unit unit = Unit.INSTANCE;
        }
    }

    public final boolean trySetCancelled$ReactAndroid_release() {
        synchronized (this.lock) {
            if (this.complete) {
                return false;
            }
            this.complete = true;
            this.cancelled = true;
            this.lock.notifyAll();
            runContinuations();
            return true;
        }
    }

    public final boolean trySetResult$ReactAndroid_release(TResult result) {
        synchronized (this.lock) {
            if (this.complete) {
                return false;
            }
            this.complete = true;
            this.result = result;
            this.lock.notifyAll();
            runContinuations();
            return true;
        }
    }

    public final boolean trySetError$ReactAndroid_release(Exception error) {
        synchronized (this.lock) {
            if (this.complete) {
                return false;
            }
            this.complete = true;
            this.error = error;
            this.lock.notifyAll();
            runContinuations();
            return true;
        }
    }

    /* JADX INFO: compiled from: Task.kt */
    @Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\u0004\b\u0001\u0010\tH\u0001¢\u0006\u0002\b\nJ#\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\f\"\u0004\b\u0001\u0010\t2\b\u0010\r\u001a\u0004\u0018\u0001H\tH\u0007¢\u0006\u0002\u0010\u000eJ$\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\t0\f\"\u0004\b\u0001\u0010\t2\u000e\u0010\u0010\u001a\n\u0018\u00010\u0011j\u0004\u0018\u0001`\u0012H\u0007J\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\t0\f\"\u0004\b\u0001\u0010\tH\u0007J0\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\t0\f\"\u0004\b\u0001\u0010\t2\u0012\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\f0\u00162\u0006\u0010\u0017\u001a\u00020\u0005H\u0007JL\u0010\u0018\u001a\u00020\u0019\"\u0004\b\u0001\u0010\u001a\"\u0004\b\u0002\u0010\t2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u001a0\b2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\u001a0\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\t0\f2\u0006\u0010\u0017\u001a\u00020\u0005H\u0002JR\u0010\u001f\u001a\u00020\u0019\"\u0004\b\u0001\u0010\u001a\"\u0004\b\u0002\u0010\t2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u001a0\b2\u0018\u0010\u001c\u001a\u0014\u0012\u0004\u0012\u0002H\t\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001a0\f0\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\t0\f2\u0006\u0010\u0017\u001a\u00020\u0005H\u0002R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020#0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/facebook/react/runtime/internal/bolts/Task$Companion;", "", "<init>", "()V", "IMMEDIATE_EXECUTOR", "Ljava/util/concurrent/Executor;", "UI_THREAD_EXECUTOR", "create", "Lcom/facebook/react/runtime/internal/bolts/TaskCompletionSource;", "TResult", "create$ReactAndroid_release", "forResult", "Lcom/facebook/react/runtime/internal/bolts/Task;", "value", "(Ljava/lang/Object;)Lcom/facebook/react/runtime/internal/bolts/Task;", "forError", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "cancelled", NotificationCompat.CATEGORY_CALL, "callable", "Ljava/util/concurrent/Callable;", "executor", "completeImmediately", "", "TContinuationResult", "tcs", "continuation", "Lcom/facebook/react/runtime/internal/bolts/Continuation;", "task", "completeAfterTask", "TASK_NULL", "Ljava/lang/Void;", "TASK_TRUE", "", "TASK_FALSE", "TASK_CANCELLED", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final <TResult> TaskCompletionSource<TResult> create$ReactAndroid_release() {
            return new TaskCompletionSource<>();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        public final <TResult> Task<TResult> forResult(TResult value) {
            if (value == 0) {
                Task<TResult> task = Task.TASK_NULL;
                Intrinsics.checkNotNull(task, "null cannot be cast to non-null type com.facebook.react.runtime.internal.bolts.Task<TResult of com.facebook.react.runtime.internal.bolts.Task.Companion.forResult>");
                return task;
            }
            if (value instanceof Boolean) {
                Task<TResult> task2 = ((Boolean) value).booleanValue() ? Task.TASK_TRUE : Task.TASK_FALSE;
                Intrinsics.checkNotNull(task2, "null cannot be cast to non-null type com.facebook.react.runtime.internal.bolts.Task<TResult of com.facebook.react.runtime.internal.bolts.Task.Companion.forResult>");
                return task2;
            }
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            taskCompletionSource.setResult(value);
            return taskCompletionSource.getTask();
        }

        @JvmStatic
        public final <TResult> Task<TResult> forError(Exception error) {
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            taskCompletionSource.setError(error);
            return taskCompletionSource.getTask();
        }

        @JvmStatic
        public final <TResult> Task<TResult> cancelled() {
            Task<TResult> task = Task.TASK_CANCELLED;
            Intrinsics.checkNotNull(task, "null cannot be cast to non-null type com.facebook.react.runtime.internal.bolts.Task<TResult of com.facebook.react.runtime.internal.bolts.Task.Companion.cancelled>");
            return task;
        }

        @JvmStatic
        public final <TResult> Task<TResult> call(final Callable<Task<TResult>> callable, Executor executor) {
            Intrinsics.checkNotNullParameter(callable, "callable");
            Intrinsics.checkNotNullParameter(executor, "executor");
            final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            try {
                executor.execute(new Runnable() { // from class: com.facebook.react.runtime.internal.bolts.Task$Companion$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Task.Companion.call$lambda$2(callable, taskCompletionSource);
                    }
                });
            } catch (Exception e) {
                taskCompletionSource.setError(new ExecutorException(e));
            }
            return taskCompletionSource.getTask();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void call$lambda$2(Callable callable, final TaskCompletionSource taskCompletionSource) {
            Continuation continuation = new Continuation() { // from class: com.facebook.react.runtime.internal.bolts.Task$Companion$$ExternalSyntheticLambda2
                @Override // com.facebook.react.runtime.internal.bolts.Continuation
                public final Object then(Task task) {
                    return Task.Companion.call$lambda$2$lambda$0(taskCompletionSource, task);
                }
            };
            try {
                Task task = (Task) callable.call();
                synchronized (task.lock) {
                    if (!task.isCompleted()) {
                        Boolean.valueOf(task.continuations.add(continuation));
                    } else {
                        Intrinsics.checkNotNull(task);
                        continuation.then(task);
                    }
                }
            } catch (CancellationException unused) {
                taskCompletionSource.setCancelled();
            } catch (Exception e) {
                taskCompletionSource.setError(e);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final Unit call$lambda$2$lambda$0(TaskCompletionSource taskCompletionSource, Task task) {
            Intrinsics.checkNotNullParameter(task, "task");
            if (task.isCancelled()) {
                taskCompletionSource.setCancelled();
            } else if (task.isFaulted()) {
                taskCompletionSource.setError(task.getError());
            } else {
                taskCompletionSource.setResult(task.getResult());
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final <TContinuationResult, TResult> void completeImmediately(final TaskCompletionSource<TContinuationResult> tcs, final Continuation<TResult, TContinuationResult> continuation, final Task<TResult> task, Executor executor) {
            try {
                executor.execute(new Runnable() { // from class: com.facebook.react.runtime.internal.bolts.Task$Companion$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        Task.Companion.completeImmediately$lambda$3(continuation, task, tcs);
                    }
                });
            } catch (Exception e) {
                tcs.setError(new ExecutorException(e));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final void completeImmediately$lambda$3(Continuation continuation, Task task, TaskCompletionSource taskCompletionSource) {
            try {
                taskCompletionSource.setResult(continuation.then(task));
            } catch (CancellationException unused) {
                taskCompletionSource.setCancelled();
            } catch (Exception e) {
                taskCompletionSource.setError(e);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final <TContinuationResult, TResult> void completeAfterTask(final TaskCompletionSource<TContinuationResult> tcs, final Continuation<TResult, Task<TContinuationResult>> continuation, final Task<TResult> task, Executor executor) {
            try {
                executor.execute(new Runnable() { // from class: com.facebook.react.runtime.internal.bolts.Task$Companion$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        Task.Companion.completeAfterTask$lambda$5(continuation, task, tcs);
                    }
                });
            } catch (Exception e) {
                tcs.setError(new ExecutorException(e));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void completeAfterTask$lambda$5(Continuation continuation, Task task, final TaskCompletionSource taskCompletionSource) {
            try {
                Task task2 = (Task) continuation.then(task);
                if (task2 == null) {
                    taskCompletionSource.setResult(null);
                } else {
                    Task.continueWith$default(task2, new Continuation() { // from class: com.facebook.react.runtime.internal.bolts.Task$Companion$$ExternalSyntheticLambda0
                        @Override // com.facebook.react.runtime.internal.bolts.Continuation
                        public final Object then(Task task3) {
                            return Task.Companion.completeAfterTask$lambda$5$lambda$4(taskCompletionSource, task3);
                        }
                    }, null, 2, null);
                }
            } catch (CancellationException unused) {
                taskCompletionSource.setCancelled();
            } catch (Exception e) {
                taskCompletionSource.setError(e);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final Unit completeAfterTask$lambda$5$lambda$4(TaskCompletionSource taskCompletionSource, Task task) {
            Intrinsics.checkNotNullParameter(task, "task");
            if (task.isCancelled()) {
                taskCompletionSource.setCancelled();
            } else if (task.isFaulted()) {
                taskCompletionSource.setError(task.getError());
            } else {
                taskCompletionSource.setResult(task.getResult());
            }
            return Unit.INSTANCE;
        }
    }
}
