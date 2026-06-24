package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
abstract class zzmf<T, B> {
    zzmf() {
    }

    abstract int zza(T t);

    abstract B zza();

    abstract T zza(T t, T t2);

    abstract void zza(B b, int i, int i2);

    abstract void zza(B b, int i, long j);

    abstract void zza(B b, int i, zzia zziaVar);

    abstract void zza(B b, int i, T t);

    abstract void zza(T t, zzna zznaVar) throws IOException;

    abstract boolean zza(zzli zzliVar);

    abstract int zzb(T t);

    abstract void zzb(B b, int i, long j);

    abstract void zzb(T t, zzna zznaVar) throws IOException;

    abstract void zzb(Object obj, B b);

    abstract B zzc(Object obj);

    abstract void zzc(Object obj, T t);

    abstract T zzd(Object obj);

    abstract T zze(B b);

    abstract void zzf(Object obj);

    final boolean zza(B b, zzli zzliVar) throws IOException {
        int iZzd = zzliVar.zzd();
        int i = iZzd >>> 3;
        int i2 = iZzd & 7;
        if (i2 == 0) {
            zzb(b, i, zzliVar.zzl());
            return true;
        }
        if (i2 == 1) {
            zza(b, i, zzliVar.zzk());
            return true;
        }
        if (i2 == 2) {
            zza((Object) b, i, zzliVar.zzp());
            return true;
        }
        if (i2 != 3) {
            if (i2 == 4) {
                return false;
            }
            if (i2 != 5) {
                throw zzjs.zza();
            }
            zza((Object) b, i, zzliVar.zzf());
            return true;
        }
        B bZza = zza();
        int i3 = 4 | (i << 3);
        while (zzliVar.zzc() != Integer.MAX_VALUE && zza((Object) bZza, zzliVar)) {
        }
        if (i3 != zzliVar.zzd()) {
            throw zzjs.zzb();
        }
        zza(b, i, zze(bZza));
        return true;
    }
}
