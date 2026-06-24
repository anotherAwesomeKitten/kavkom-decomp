package com.google.android.gms.internal.measurement;

import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzkc implements zzkd {
    private static <E> zzjt<E> zzc(Object obj, long j) {
        return (zzjt) zzmg.zze(obj, j);
    }

    @Override // com.google.android.gms.internal.measurement.zzkd
    public final <L> List<L> zza(Object obj, long j) {
        zzjt zzjtVarZzc = zzc(obj, j);
        if (zzjtVarZzc.zzc()) {
            return zzjtVarZzc;
        }
        int size = zzjtVarZzc.size();
        zzjt zzjtVarZza = zzjtVarZzc.zza(size == 0 ? 10 : size << 1);
        zzmg.zza(obj, j, zzjtVarZza);
        return zzjtVarZza;
    }

    zzkc() {
    }

    @Override // com.google.android.gms.internal.measurement.zzkd
    public final void zzb(Object obj, long j) {
        zzc(obj, j).zzb();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.google.android.gms.internal.measurement.zzjt] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r6v1, types: [com.google.android.gms.internal.measurement.zzjt, java.util.Collection] */
    /* JADX WARN: Type inference failed for: r6v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v3 */
    @Override // com.google.android.gms.internal.measurement.zzkd
    public final <E> void zza(Object obj, Object obj2, long j) {
        zzjt zzjtVarZzc = zzc(obj, j);
        ?? Zzc = zzc(obj2, j);
        int size = zzjtVarZzc.size();
        int size2 = Zzc.size();
        ?? r0 = zzjtVarZzc;
        r0 = zzjtVarZzc;
        if (size > 0 && size2 > 0) {
            boolean zZzc = zzjtVarZzc.zzc();
            ?? Zza = zzjtVarZzc;
            if (!zZzc) {
                Zza = zzjtVarZzc.zza(size2 + size);
            }
            Zza.addAll(Zzc);
            r0 = Zza;
        }
        if (size > 0) {
            Zzc = r0;
        }
        zzmg.zza(obj, j, (Object) Zzc);
    }
}
