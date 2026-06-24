package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
class zzij extends zzih {
    protected final byte[] zzb;

    @Override // com.google.android.gms.internal.measurement.zzia
    public byte zza(int i) {
        return this.zzb[i];
    }

    protected int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzia
    byte zzb(int i) {
        return this.zzb[i];
    }

    @Override // com.google.android.gms.internal.measurement.zzia
    protected final int zzb(int i, int i2, int i3) {
        return zzjm.zza(i, this.zzb, zzc(), i3);
    }

    @Override // com.google.android.gms.internal.measurement.zzia
    public int zzb() {
        return this.zzb.length;
    }

    @Override // com.google.android.gms.internal.measurement.zzia
    public final zzia zza(int i, int i2) {
        int iZza = zza(0, i2, zzb());
        if (iZza == 0) {
            return zzia.zza;
        }
        return new zzie(this.zzb, zzc(), iZza);
    }

    zzij(byte[] bArr) {
        super();
        bArr.getClass();
        this.zzb = bArr;
    }

    @Override // com.google.android.gms.internal.measurement.zzia
    final void zza(zzhx zzhxVar) throws IOException {
        zzhxVar.zza(this.zzb, zzc(), zzb());
    }

    @Override // com.google.android.gms.internal.measurement.zzia
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzia) || zzb() != ((zzia) obj).zzb()) {
            return false;
        }
        if (zzb() == 0) {
            return true;
        }
        if (obj instanceof zzij) {
            zzij zzijVar = (zzij) obj;
            int iZza = zza();
            int iZza2 = zzijVar.zza();
            if (iZza == 0 || iZza2 == 0 || iZza == iZza2) {
                return zza(zzijVar, 0, zzb());
            }
            return false;
        }
        return obj.equals(this);
    }

    @Override // com.google.android.gms.internal.measurement.zzih
    final boolean zza(zzia zziaVar, int i, int i2) {
        if (i2 > zziaVar.zzb()) {
            throw new IllegalArgumentException("Length too large: " + i2 + zzb());
        }
        if (i2 > zziaVar.zzb()) {
            throw new IllegalArgumentException("Ran off end of other: 0, " + i2 + ", " + zziaVar.zzb());
        }
        if (zziaVar instanceof zzij) {
            zzij zzijVar = (zzij) zziaVar;
            byte[] bArr = this.zzb;
            byte[] bArr2 = zzijVar.zzb;
            int iZzc = zzc() + i2;
            int iZzc2 = zzc();
            int iZzc3 = zzijVar.zzc();
            while (iZzc2 < iZzc) {
                if (bArr[iZzc2] != bArr2[iZzc3]) {
                    return false;
                }
                iZzc2++;
                iZzc3++;
            }
            return true;
        }
        return zziaVar.zza(0, i2).equals(zza(0, i2));
    }
}
