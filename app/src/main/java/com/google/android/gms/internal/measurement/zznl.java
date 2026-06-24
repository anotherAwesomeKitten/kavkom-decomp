package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zznl implements Supplier<zzno> {
    private static zznl zza = new zznl();
    private final Supplier<zzno> zzb = Suppliers.ofInstance(new zznn());

    @Override // com.google.common.base.Supplier
    public final /* synthetic */ zzno get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zzno) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzno) zza.get()).zzb();
    }
}
