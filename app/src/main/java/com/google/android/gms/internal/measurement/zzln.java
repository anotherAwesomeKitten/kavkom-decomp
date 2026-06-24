package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzln {
    private static final zzmf<?, ?> zza = new zzmh();

    static int zza(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzit.zzb(i, true);
    }

    static int zza(List<?> list) {
        return list.size();
    }

    static int zza(int i, List<zzia> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzi = size * zzit.zzi(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            iZzi += zzit.zzb(list.get(i2));
        }
        return iZzi;
    }

    static int zzb(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzb(list) + (size * zzit.zzi(i));
    }

    static int zzb(List<Integer> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzjn)) {
            int iZzd = 0;
            while (i < size) {
                iZzd += zzit.zzd(list.get(i).intValue());
                i++;
            }
            return iZzd;
        }
        zzjn zzjnVar = (zzjn) list;
        int iZzd2 = 0;
        while (i < size) {
            iZzd2 += zzit.zzd(zzjnVar.zzb(i));
            i++;
        }
        return iZzd2;
    }

    static int zzc(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzit.zzf(i, 0);
    }

    static int zzc(List<?> list) {
        return list.size() << 2;
    }

    static int zzd(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzit.zzc(i, 0L);
    }

    static int zzd(List<?> list) {
        return list.size() << 3;
    }

    static int zza(int i, List<zzkt> list, zzll zzllVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzb = 0;
        for (int i2 = 0; i2 < size; i2++) {
            iZzb += zzit.zzb(i, list.get(i2), zzllVar);
        }
        return iZzb;
    }

    static int zze(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzit.zzi(i));
    }

    static int zze(List<Integer> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzjn)) {
            int iZzf = 0;
            while (i < size) {
                iZzf += zzit.zzf(list.get(i).intValue());
                i++;
            }
            return iZzf;
        }
        zzjn zzjnVar = (zzjn) list;
        int iZzf2 = 0;
        while (i < size) {
            iZzf2 += zzit.zzf(zzjnVar.zzb(i));
            i++;
        }
        return iZzf2;
    }

    static int zzf(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzf(list) + (list.size() * zzit.zzi(i));
    }

    static int zzf(List<Long> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzke)) {
            int iZzd = 0;
            while (i < size) {
                iZzd += zzit.zzd(list.get(i).longValue());
                i++;
            }
            return iZzd;
        }
        zzke zzkeVar = (zzke) list;
        int iZzd2 = 0;
        while (i < size) {
            iZzd2 += zzit.zzd(zzkeVar.zzb(i));
            i++;
        }
        return iZzd2;
    }

    static int zza(int i, Object obj, zzll zzllVar) {
        if (obj instanceof zzkb) {
            return zzit.zzb(i, (zzkb) obj);
        }
        return zzit.zzc(i, (zzkt) obj, zzllVar);
    }

    static int zzb(int i, List<?> list, zzll zzllVar) {
        int iZza;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iZzi = zzit.zzi(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof zzkb) {
                iZza = zzit.zza((zzkb) obj);
            } else {
                iZza = zzit.zza((zzkt) obj, zzllVar);
            }
            iZzi += iZza;
        }
        return iZzi;
    }

    static int zzg(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzg(list) + (size * zzit.zzi(i));
    }

    static int zzg(List<Integer> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzjn)) {
            int iZzh = 0;
            while (i < size) {
                iZzh += zzit.zzh(list.get(i).intValue());
                i++;
            }
            return iZzh;
        }
        zzjn zzjnVar = (zzjn) list;
        int iZzh2 = 0;
        while (i < size) {
            iZzh2 += zzit.zzh(zzjnVar.zzb(i));
            i++;
        }
        return iZzh2;
    }

    static int zzh(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzh(list) + (size * zzit.zzi(i));
    }

    static int zzh(List<Long> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzke)) {
            int iZzf = 0;
            while (i < size) {
                iZzf += zzit.zzf(list.get(i).longValue());
                i++;
            }
            return iZzf;
        }
        zzke zzkeVar = (zzke) list;
        int iZzf2 = 0;
        while (i < size) {
            iZzf2 += zzit.zzf(zzkeVar.zzb(i));
            i++;
        }
        return iZzf2;
    }

    static int zzb(int i, List<?> list) {
        int iZzb;
        int iZzb2;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int iZzi = zzit.zzi(i) * size;
        if (!(list instanceof zzka)) {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof zzia) {
                    iZzb = zzit.zzb((zzia) obj);
                } else {
                    iZzb = zzit.zzb((String) obj);
                }
                iZzi += iZzb;
                i2++;
            }
            return iZzi;
        }
        zzka zzkaVar = (zzka) list;
        while (i2 < size) {
            Object objZza = zzkaVar.zza(i2);
            if (objZza instanceof zzia) {
                iZzb2 = zzit.zzb((zzia) objZza);
            } else {
                iZzb2 = zzit.zzb((String) objZza);
            }
            iZzi += iZzb2;
            i2++;
        }
        return iZzi;
    }

    static int zzi(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzi(list) + (size * zzit.zzi(i));
    }

    static int zzi(List<Integer> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzjn)) {
            int iZzj = 0;
            while (i < size) {
                iZzj += zzit.zzj(list.get(i).intValue());
                i++;
            }
            return iZzj;
        }
        zzjn zzjnVar = (zzjn) list;
        int iZzj2 = 0;
        while (i < size) {
            iZzj2 += zzit.zzj(zzjnVar.zzb(i));
            i++;
        }
        return iZzj2;
    }

    static int zzj(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzj(list) + (size * zzit.zzi(i));
    }

    static int zzj(List<Long> list) {
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (!(list instanceof zzke)) {
            int iZzg = 0;
            while (i < size) {
                iZzg += zzit.zzg(list.get(i).longValue());
                i++;
            }
            return iZzg;
        }
        zzke zzkeVar = (zzke) list;
        int iZzg2 = 0;
        while (i < size) {
            iZzg2 += zzit.zzg(zzkeVar.zzb(i));
            i++;
        }
        return iZzg2;
    }

    public static zzmf<?, ?> zza() {
        return zza;
    }

    static <UT, UB> UB zza(Object obj, int i, List<Integer> list, zzjo zzjoVar, UB ub, zzmf<UT, UB> zzmfVar) {
        if (zzjoVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int iIntValue = list.get(i3).intValue();
                if (zzjoVar.zza(iIntValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(iIntValue));
                    }
                    i2++;
                } else {
                    ub = (UB) zza(obj, i, iIntValue, ub, zzmfVar);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
            return ub;
        }
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int iIntValue2 = it.next().intValue();
            if (!zzjoVar.zza(iIntValue2)) {
                ub = (UB) zza(obj, i, iIntValue2, ub, zzmfVar);
                it.remove();
            }
        }
        return ub;
    }

    static <UT, UB> UB zza(Object obj, int i, int i2, UB ub, zzmf<UT, UB> zzmfVar) {
        if (ub == null) {
            ub = zzmfVar.zzc(obj);
        }
        zzmfVar.zzb(ub, i, i2);
        return ub;
    }

    static <T, FT extends zzjf<FT>> void zza(zziz<FT> zzizVar, T t, T t2) {
        zzjd<T> zzjdVarZza = zzizVar.zza(t2);
        if (zzjdVarZza.zza.isEmpty()) {
            return;
        }
        zzizVar.zzb(t).zza((zzjd) zzjdVarZza);
    }

    static <T> void zza(zzkm zzkmVar, T t, T t2, long j) {
        zzmg.zza(t, j, zzkmVar.zza(zzmg.zze(t, j), zzmg.zze(t2, j)));
    }

    static <T, UT, UB> void zza(zzmf<UT, UB> zzmfVar, T t, T t2) {
        zzmfVar.zzc(t, zzmfVar.zza(zzmfVar.zzd(t), zzmfVar.zzd(t2)));
    }

    public static void zza(Class<?> cls) {
        zzjk.class.isAssignableFrom(cls);
    }

    public static void zza(int i, List<Boolean> list, zzna zznaVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznaVar.zza(i, list, z);
    }

    public static void zza(int i, List<zzia> list, zzna zznaVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznaVar.zza(i, list);
    }

    public static void zzb(int i, List<Double> list, zzna zznaVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznaVar.zzb(i, list, z);
    }

    public static void zzc(int i, List<Integer> list, zzna zznaVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznaVar.zzc(i, list, z);
    }

    public static void zzd(int i, List<Integer> list, zzna zznaVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznaVar.zzd(i, list, z);
    }

    public static void zze(int i, List<Long> list, zzna zznaVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznaVar.zze(i, list, z);
    }

    public static void zzf(int i, List<Float> list, zzna zznaVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznaVar.zzf(i, list, z);
    }

    public static void zza(int i, List<?> list, zzna zznaVar, zzll zzllVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznaVar.zza(i, list, zzllVar);
    }

    public static void zzg(int i, List<Integer> list, zzna zznaVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznaVar.zzg(i, list, z);
    }

    public static void zzh(int i, List<Long> list, zzna zznaVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznaVar.zzh(i, list, z);
    }

    public static void zzb(int i, List<?> list, zzna zznaVar, zzll zzllVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznaVar.zzb(i, list, zzllVar);
    }

    public static void zzi(int i, List<Integer> list, zzna zznaVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznaVar.zzi(i, list, z);
    }

    public static void zzj(int i, List<Long> list, zzna zznaVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznaVar.zzj(i, list, z);
    }

    public static void zzk(int i, List<Integer> list, zzna zznaVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznaVar.zzk(i, list, z);
    }

    public static void zzl(int i, List<Long> list, zzna zznaVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznaVar.zzl(i, list, z);
    }

    public static void zzb(int i, List<String> list, zzna zznaVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznaVar.zzb(i, list);
    }

    public static void zzm(int i, List<Integer> list, zzna zznaVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznaVar.zzm(i, list, z);
    }

    public static void zzn(int i, List<Long> list, zzna zznaVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zznaVar.zzn(i, list, z);
    }

    static boolean zza(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }
}
