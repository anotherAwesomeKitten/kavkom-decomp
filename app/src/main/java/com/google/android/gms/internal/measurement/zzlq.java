package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzlq extends zzlx {
    private final /* synthetic */ zzlm zza;

    @Override // com.google.android.gms.internal.measurement.zzlx, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzlo(this.zza);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private zzlq(zzlm zzlmVar) {
        super(zzlmVar);
        this.zza = zzlmVar;
    }
}
