package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzfx {
    private static zzga zza;

    public static synchronized zzga zza() {
        if (zza == null) {
            zza(new zzfz());
        }
        return zza;
    }

    private static synchronized void zza(zzga zzgaVar) {
        if (zza != null) {
            throw new IllegalStateException("init() already called");
        }
        zza = zzgaVar;
    }
}
