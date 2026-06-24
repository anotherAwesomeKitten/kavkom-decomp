package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzjf;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
abstract class zziz<T extends zzjf<T>> {
    zziz() {
    }

    abstract int zza(Map.Entry<?, ?> entry);

    abstract zzjd<T> zza(Object obj);

    abstract Object zza(zzix zzixVar, zzkt zzktVar, int i);

    abstract <UT, UB> UB zza(Object obj, zzli zzliVar, Object obj2, zzix zzixVar, zzjd<T> zzjdVar, UB ub, zzmf<UT, UB> zzmfVar) throws IOException;

    abstract void zza(zzia zziaVar, Object obj, zzix zzixVar, zzjd<T> zzjdVar) throws IOException;

    abstract void zza(zzli zzliVar, Object obj, zzix zzixVar, zzjd<T> zzjdVar) throws IOException;

    abstract void zza(zzna zznaVar, Map.Entry<?, ?> entry) throws IOException;

    abstract boolean zza(zzkt zzktVar);

    abstract zzjd<T> zzb(Object obj);

    abstract void zzc(Object obj);
}
