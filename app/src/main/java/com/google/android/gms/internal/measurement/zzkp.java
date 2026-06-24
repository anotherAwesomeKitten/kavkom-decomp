package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzkp implements zzkm {
    @Override // com.google.android.gms.internal.measurement.zzkm
    public final int zza(int i, Object obj, Object obj2) {
        zzkn zzknVar = (zzkn) obj;
        if (zzknVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzknVar.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.measurement.zzkm
    public final zzkk<?, ?> zza(Object obj) {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.measurement.zzkm
    public final Object zza(Object obj, Object obj2) {
        zzkn zzknVarZzb = (zzkn) obj;
        zzkn zzknVar = (zzkn) obj2;
        if (!zzknVar.isEmpty()) {
            if (!zzknVarZzb.zzd()) {
                zzknVarZzb = zzknVarZzb.zzb();
            }
            zzknVarZzb.zza(zzknVar);
        }
        return zzknVarZzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzkm
    public final Object zzb(Object obj) {
        return zzkn.zza().zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzkm
    public final Object zzc(Object obj) {
        ((zzkn) obj).zzc();
        return obj;
    }

    @Override // com.google.android.gms.internal.measurement.zzkm
    public final Map<?, ?> zzd(Object obj) {
        return (zzkn) obj;
    }

    @Override // com.google.android.gms.internal.measurement.zzkm
    public final Map<?, ?> zze(Object obj) {
        return (zzkn) obj;
    }

    zzkp() {
    }

    @Override // com.google.android.gms.internal.measurement.zzkm
    public final boolean zzf(Object obj) {
        return !((zzkn) obj).zzd();
    }
}
