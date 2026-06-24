package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzoi implements Supplier<zzoh> {
    private static zzoi zza = new zzoi();
    private final Supplier<zzoh> zzb = Suppliers.ofInstance(new zzok());

    @Override // com.google.common.base.Supplier
    public final /* synthetic */ zzoh get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zzoh) zza.get()).zza();
    }

    @SideEffectFree
    public static boolean zzb() {
        return ((zzoh) zza.get()).zzb();
    }
}
