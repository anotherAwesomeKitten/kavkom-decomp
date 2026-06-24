package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzfj<V> {
    private static final Object zza = new Object();
    private final String zzb;
    private final zzfh<V> zzc;
    private final V zzd;
    private final Object zze;
    private volatile V zzf;
    private volatile V zzg;

    /* JADX WARN: Removed duplicated region for block: B:43:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x005f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final V zza(V r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.zze
            monitor-enter(r0)
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L6f
            if (r4 == 0) goto L7
            return r4
        L7:
            com.google.android.gms.measurement.internal.zzab r4 = com.google.android.gms.measurement.internal.zzfk.zza
            if (r4 != 0) goto Le
            V r4 = r3.zzd
            return r4
        Le:
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzfj.zza
            monitor-enter(r4)
            boolean r0 = com.google.android.gms.measurement.internal.zzab.zza()     // Catch: java.lang.Throwable -> L6a
            if (r0 == 0) goto L22
            V r0 = r3.zzg     // Catch: java.lang.Throwable -> L6a
            if (r0 != 0) goto L1e
            V r0 = r3.zzd     // Catch: java.lang.Throwable -> L6a
            goto L20
        L1e:
            V r0 = r3.zzg     // Catch: java.lang.Throwable -> L6a
        L20:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L6a
            return r0
        L22:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L6a
            java.util.List r4 = com.google.android.gms.measurement.internal.zzbf.zzcy()     // Catch: java.lang.SecurityException -> L58
            java.util.Iterator r4 = r4.iterator()     // Catch: java.lang.SecurityException -> L58
        L2b:
            boolean r0 = r4.hasNext()     // Catch: java.lang.SecurityException -> L58
            if (r0 == 0) goto L58
            java.lang.Object r0 = r4.next()     // Catch: java.lang.SecurityException -> L58
            com.google.android.gms.measurement.internal.zzfj r0 = (com.google.android.gms.measurement.internal.zzfj) r0     // Catch: java.lang.SecurityException -> L58
            boolean r1 = com.google.android.gms.measurement.internal.zzab.zza()     // Catch: java.lang.SecurityException -> L58
            if (r1 != 0) goto L50
            r1 = 0
            com.google.android.gms.measurement.internal.zzfh<V> r2 = r0.zzc     // Catch: java.lang.IllegalStateException -> L46 java.lang.SecurityException -> L58
            if (r2 == 0) goto L46
            java.lang.Object r1 = r2.zza()     // Catch: java.lang.IllegalStateException -> L46 java.lang.SecurityException -> L58
        L46:
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzfj.zza     // Catch: java.lang.SecurityException -> L58
            monitor-enter(r2)     // Catch: java.lang.SecurityException -> L58
            r0.zzg = r1     // Catch: java.lang.Throwable -> L4d
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L4d
            goto L2b
        L4d:
            r4 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L4d
            throw r4     // Catch: java.lang.SecurityException -> L58
        L50:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch: java.lang.SecurityException -> L58
            java.lang.String r0 = "Refreshing flag cache must be done on a worker thread."
            r4.<init>(r0)     // Catch: java.lang.SecurityException -> L58
            throw r4     // Catch: java.lang.SecurityException -> L58
        L58:
            com.google.android.gms.measurement.internal.zzfh<V> r4 = r3.zzc
            if (r4 != 0) goto L5f
            V r4 = r3.zzd
            return r4
        L5f:
            java.lang.Object r4 = r4.zza()     // Catch: java.lang.IllegalStateException -> L64 java.lang.SecurityException -> L67
            return r4
        L64:
            V r4 = r3.zzd
            return r4
        L67:
            V r4 = r3.zzd
            return r4
        L6a:
            r0 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L6a
            throw r0
        L6d:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L6f
            throw r4
        L6f:
            r4 = move-exception
            goto L6d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzfj.zza(java.lang.Object):java.lang.Object");
    }

    public final String zza() {
        return this.zzb;
    }

    private zzfj(String str, V v, V v2, zzfh<V> zzfhVar, boolean z) {
        this.zze = new Object();
        this.zzf = null;
        this.zzg = null;
        this.zzb = str;
        this.zzd = v;
        this.zzc = zzfhVar;
    }
}
