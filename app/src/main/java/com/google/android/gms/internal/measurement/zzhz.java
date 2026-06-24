package com.google.android.gms.internal.measurement;

import java.util.NoSuchElementException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzhz extends zzib {
    private int zza = 0;
    private final int zzb;
    private final /* synthetic */ zzia zzc;

    @Override // com.google.android.gms.internal.measurement.zzig
    public final byte zza() {
        int i = this.zza;
        if (i >= this.zzb) {
            throw new NoSuchElementException();
        }
        this.zza = i + 1;
        return this.zzc.zzb(i);
    }

    zzhz(zzia zziaVar) {
        this.zzc = zziaVar;
        this.zzb = zziaVar.zzb();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza < this.zzb;
    }
}
