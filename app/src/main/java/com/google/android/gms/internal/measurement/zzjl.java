package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzjk;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjl implements zzkq {
    private static final zzjl zza = new zzjl();

    public static zzjl zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzkq
    public final zzkr zza(Class<?> cls) {
        if (!zzjk.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Unsupported message type: " + cls.getName());
        }
        try {
            return (zzkr) zzjk.zza(cls.asSubclass(zzjk.class)).zza(zzjk.zze.zzc, (Object) null, (Object) null);
        } catch (Exception e) {
            throw new RuntimeException("Unable to get message info for " + cls.getName(), e);
        }
    }

    private zzjl() {
    }

    @Override // com.google.android.gms.internal.measurement.zzkq
    public final boolean zzb(Class<?> cls) {
        return zzjk.class.isAssignableFrom(cls);
    }
}
