package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.checkerframework.dataflow.qual.SideEffectFree;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzov implements Supplier<zzoy> {
    private static zzov zza = new zzov();
    private final Supplier<zzoy> zzb = Suppliers.ofInstance(new zzox());

    @Override // com.google.common.base.Supplier
    public final /* synthetic */ zzoy get() {
        return this.zzb.get();
    }

    @SideEffectFree
    public static boolean zza() {
        return ((zzoy) zza.get()).zza();
    }
}
