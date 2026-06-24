package com.facebook.react.runtime;

import io.sentry.protocol.SentryThread;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BridgelessAtomicRef.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0002\u001a\u001bB\u0013\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\u0011\u001a\u00028\u00002\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0007¢\u0006\u0002\u0010\u0014J\u0006\u0010\u0017\u001a\u00020\u0018J\u000b\u0010\u0019\u001a\u00028\u0000¢\u0006\u0002\u0010\u0007R \u0010\u0003\u001a\u0004\u0018\u00018\u00008GX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005R\u001e\u0010\n\u001a\u0004\u0018\u00018\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\u0005R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0015\u001a\u00028\u00008F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0007¨\u0006\u001c"}, d2 = {"Lcom/facebook/react/runtime/BridgelessAtomicRef;", "T", "", "value", "<init>", "(Ljava/lang/Object;)V", "getNullable", "()Ljava/lang/Object;", "setValue", "Ljava/lang/Object;", "initialValue", "getInitialValue", "setInitialValue", SentryThread.JsonKeys.STATE, "Lcom/facebook/react/runtime/BridgelessAtomicRef$State;", "failureMessage", "", "getOrCreate", "provider", "Lcom/facebook/react/runtime/BridgelessAtomicRef$Provider;", "(Lcom/facebook/react/runtime/BridgelessAtomicRef$Provider;)Ljava/lang/Object;", "andReset", "getAndReset", "reset", "", "get", "Provider", "State", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class BridgelessAtomicRef<T> {
    private volatile String failureMessage;
    private T initialValue;
    private volatile State state;
    private volatile T value;

    /* JADX INFO: compiled from: BridgelessAtomicRef.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\bà\u0080\u0001\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002J\r\u0010\u0003\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0005À\u0006\u0001"}, d2 = {"Lcom/facebook/react/runtime/BridgelessAtomicRef$Provider;", "T", "", "get", "()Ljava/lang/Object;", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface Provider<T> {
        T get();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public BridgelessAtomicRef() {
        DefaultConstructorMarker defaultConstructorMarker = null;
        this(defaultConstructorMarker, 1, defaultConstructorMarker);
    }

    public BridgelessAtomicRef(T t) {
        this.value = t;
        this.initialValue = this.value;
        this.state = State.Init;
        this.failureMessage = "";
    }

    public /* synthetic */ BridgelessAtomicRef(Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : obj);
    }

    public final synchronized T getNullable() {
        return this.value;
    }

    public final void setValue(T t) {
        this.value = t;
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: BridgelessAtomicRef.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/facebook/react/runtime/BridgelessAtomicRef$State;", "", "<init>", "(Ljava/lang/String;I)V", "Init", "Creating", "Success", "Failure", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class State {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ State[] $VALUES;
        public static final State Init = new State("Init", 0);
        public static final State Creating = new State("Creating", 1);
        public static final State Success = new State("Success", 2);
        public static final State Failure = new State("Failure", 3);

        private static final /* synthetic */ State[] $values() {
            return new State[]{Init, Creating, Success, Failure};
        }

        public static EnumEntries<State> getEntries() {
            return $ENTRIES;
        }

        private State(String str, int i) {
        }

        static {
            State[] stateArr$values = $values();
            $VALUES = stateArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(stateArr$values);
        }

        public static State valueOf(String str) {
            return (State) Enum.valueOf(State.class, str);
        }

        public static State[] values() {
            return (State[]) $VALUES.clone();
        }
    }

    public final T getInitialValue() {
        return this.initialValue;
    }

    public final void setInitialValue(T t) {
        this.initialValue = t;
    }

    public final T getOrCreate(Provider<T> provider) {
        boolean z;
        T t;
        T t2;
        Intrinsics.checkNotNullParameter(provider, "provider");
        synchronized (this) {
            if (this.state == State.Success) {
                return get();
            }
            if (this.state == State.Failure) {
                throw new RuntimeException("BridgelessAtomicRef: Failed to create object. Reason: " + this.failureMessage);
            }
            boolean z2 = false;
            if (this.state != State.Creating) {
                this.state = State.Creating;
                z = true;
            } else {
                z = false;
            }
            Unit unit = Unit.INSTANCE;
            if (z) {
                try {
                    this.value = provider.get();
                    synchronized (this) {
                        this.state = State.Success;
                        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type java.lang.Object");
                        notifyAll();
                        t = get();
                    }
                    return t;
                } catch (RuntimeException e) {
                    synchronized (this) {
                        this.state = State.Failure;
                        this.failureMessage = String.valueOf(e.getMessage());
                        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type java.lang.Object");
                        notifyAll();
                        Unit unit2 = Unit.INSTANCE;
                        throw new RuntimeException("BridgelessAtomicRef: Failed to create object.", e);
                    }
                }
            }
            synchronized (this) {
                while (this.state == State.Creating) {
                    try {
                        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type java.lang.Object");
                        wait();
                    } catch (InterruptedException unused) {
                        z2 = true;
                    }
                }
                if (z2) {
                    Thread.currentThread().interrupt();
                }
                if (this.state == State.Failure) {
                    throw new RuntimeException("BridgelessAtomicRef: Failed to create object. Reason: " + this.failureMessage);
                }
                t2 = get();
            }
            return t2;
        }
    }

    public final synchronized T getAndReset() {
        T t;
        t = get();
        reset();
        return t;
    }

    public final synchronized void reset() {
        this.value = this.initialValue;
        this.state = State.Init;
        this.failureMessage = "";
    }

    public final synchronized T get() {
        T t;
        t = this.value;
        if (t == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        return t;
    }
}
