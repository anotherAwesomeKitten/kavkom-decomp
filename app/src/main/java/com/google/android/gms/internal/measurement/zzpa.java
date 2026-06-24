package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzpa implements Supplier<zzoz> {
    private static zzpa zza = new zzpa();
    private final Supplier<zzoz> zzb = Suppliers.ofInstance(new zzpc());

    @SideEffectFree
    public static double zza() {
        return ((zzoz) zza.get()).zza();
    }

    @SideEffectFree
    public static long zzb() {
        return ((zzoz) zza.get()).zzb();
    }

    @SideEffectFree
    public static long zzc() {
        return ((zzoz) zza.get()).zzc();
    }

    @SideEffectFree
    public static long zzd() {
        return ((zzoz) zza.get()).zzd();
    }

    @Override // com.google.common.base.Supplier
    public final /* synthetic */ zzoz get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static String zze() {
        return ((zzoz) zza.get()).zze();
    }

    @SideEffectFree
    public static boolean zzf() {
        return ((zzoz) zza.get()).zzf();
    }
}
