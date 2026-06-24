package com.google.android.gms.internal.measurement;

import java.util.Comparator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzic implements Comparator<zzia> {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzia zziaVar, zzia zziaVar2) {
        zzia zziaVar3 = zziaVar;
        zzia zziaVar4 = zziaVar2;
        zzig zzigVar = (zzig) zziaVar3.iterator();
        zzig zzigVar2 = (zzig) zziaVar4.iterator();
        while (zzigVar.hasNext() && zzigVar2.hasNext()) {
            int iCompareTo = Integer.valueOf(zzia.zza(zzigVar.zza())).compareTo(Integer.valueOf(zzia.zza(zzigVar2.zza())));
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return Integer.valueOf(zziaVar3.zzb()).compareTo(Integer.valueOf(zziaVar4.zzb()));
    }

    zzic() {
    }
}
