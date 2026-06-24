package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzme {
    private static final zzme zza = new zzme(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    public final int zza() {
        int iZzg;
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            int i4 = this.zzc[i3];
            int i5 = i4 >>> 3;
            int i6 = i4 & 7;
            if (i6 == 0) {
                iZzg = zzit.zzg(i5, ((Long) this.zzd[i3]).longValue());
            } else if (i6 == 1) {
                iZzg = zzit.zzc(i5, ((Long) this.zzd[i3]).longValue());
            } else if (i6 == 2) {
                iZzg = zzit.zzc(i5, (zzia) this.zzd[i3]);
            } else if (i6 == 3) {
                iZzg = (zzit.zzi(i5) << 1) + ((zzme) this.zzd[i3]).zza();
            } else {
                if (i6 != 5) {
                    throw new IllegalStateException(zzjs.zza());
                }
                iZzg = zzit.zzf(i5, ((Integer) this.zzd[i3]).intValue());
            }
            i2 += iZzg;
        }
        this.zze = i2;
        return i2;
    }

    public final int zzb() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int iZzd = 0;
        for (int i2 = 0; i2 < this.zzb; i2++) {
            iZzd += zzit.zzd(this.zzc[i2] >>> 3, (zzia) this.zzd[i2]);
        }
        this.zze = iZzd;
        return iZzd;
    }

    public final int hashCode() {
        int i = this.zzb;
        int i2 = (i + 527) * 31;
        int[] iArr = this.zzc;
        int iHashCode = 17;
        int i3 = 17;
        for (int i4 = 0; i4 < i; i4++) {
            i3 = (i3 * 31) + iArr[i4];
        }
        int i5 = (i2 + i3) * 31;
        Object[] objArr = this.zzd;
        int i6 = this.zzb;
        for (int i7 = 0; i7 < i6; i7++) {
            iHashCode = (iHashCode * 31) + objArr[i7].hashCode();
        }
        return i5 + iHashCode;
    }

    public static zzme zzc() {
        return zza;
    }

    final zzme zza(zzme zzmeVar) {
        if (zzmeVar.equals(zza)) {
            return this;
        }
        zzf();
        int i = this.zzb + zzmeVar.zzb;
        zza(i);
        System.arraycopy(zzmeVar.zzc, 0, this.zzc, this.zzb, zzmeVar.zzb);
        System.arraycopy(zzmeVar.zzd, 0, this.zzd, this.zzb, zzmeVar.zzb);
        this.zzb = i;
        return this;
    }

    static zzme zza(zzme zzmeVar, zzme zzmeVar2) {
        int i = zzmeVar.zzb + zzmeVar2.zzb;
        int[] iArrCopyOf = Arrays.copyOf(zzmeVar.zzc, i);
        System.arraycopy(zzmeVar2.zzc, 0, iArrCopyOf, zzmeVar.zzb, zzmeVar2.zzb);
        Object[] objArrCopyOf = Arrays.copyOf(zzmeVar.zzd, i);
        System.arraycopy(zzmeVar2.zzd, 0, objArrCopyOf, zzmeVar.zzb, zzmeVar2.zzb);
        return new zzme(i, iArrCopyOf, objArrCopyOf, true);
    }

    static zzme zzd() {
        return new zzme();
    }

    private zzme() {
        this(0, new int[8], new Object[8], true);
    }

    private zzme(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    private final void zzf() {
        if (!this.zzf) {
            throw new UnsupportedOperationException();
        }
    }

    private final void zza(int i) {
        int[] iArr = this.zzc;
        if (i > iArr.length) {
            int i2 = this.zzb;
            int i3 = i2 + (i2 / 2);
            if (i3 >= i) {
                i = i3;
            }
            if (i < 8) {
                i = 8;
            }
            this.zzc = Arrays.copyOf(iArr, i);
            this.zzd = Arrays.copyOf(this.zzd, i);
        }
    }

    public final void zze() {
        if (this.zzf) {
            this.zzf = false;
        }
    }

    final void zza(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzku.zza(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    final void zza(int i, Object obj) {
        zzf();
        zza(this.zzb + 1);
        int[] iArr = this.zzc;
        int i2 = this.zzb;
        iArr[i2] = i;
        this.zzd[i2] = obj;
        this.zzb = i2 + 1;
    }

    final void zza(zzna zznaVar) throws IOException {
        if (zznaVar.zza() == 2) {
            for (int i = this.zzb - 1; i >= 0; i--) {
                zznaVar.zza(this.zzc[i] >>> 3, this.zzd[i]);
            }
            return;
        }
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zznaVar.zza(this.zzc[i2] >>> 3, this.zzd[i2]);
        }
    }

    private static void zza(int i, Object obj, zzna zznaVar) throws IOException {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            zznaVar.zzb(i2, ((Long) obj).longValue());
            return;
        }
        if (i3 == 1) {
            zznaVar.zza(i2, ((Long) obj).longValue());
            return;
        }
        if (i3 == 2) {
            zznaVar.zza(i2, (zzia) obj);
            return;
        }
        if (i3 != 3) {
            if (i3 == 5) {
                zznaVar.zzb(i2, ((Integer) obj).intValue());
                return;
            }
            throw new RuntimeException(zzjs.zza());
        }
        if (zznaVar.zza() == 1) {
            zznaVar.zzb(i2);
            ((zzme) obj).zzb(zznaVar);
            zznaVar.zza(i2);
        } else {
            zznaVar.zza(i2);
            ((zzme) obj).zzb(zznaVar);
            zznaVar.zzb(i2);
        }
    }

    public final void zzb(zzna zznaVar) throws IOException {
        if (this.zzb == 0) {
            return;
        }
        if (zznaVar.zza() == 1) {
            for (int i = 0; i < this.zzb; i++) {
                zza(this.zzc[i], this.zzd[i], zznaVar);
            }
            return;
        }
        for (int i2 = this.zzb - 1; i2 >= 0; i2--) {
            zza(this.zzc[i2], this.zzd[i2], zznaVar);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzme)) {
            return false;
        }
        zzme zzmeVar = (zzme) obj;
        int i = this.zzb;
        if (i == zzmeVar.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zzmeVar.zzc;
            int i2 = 0;
            while (true) {
                if (i2 < i) {
                    if (iArr[i2] != iArr2[i2]) {
                        break;
                    }
                    i2++;
                } else {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zzmeVar.zzd;
                    int i3 = this.zzb;
                    for (int i4 = 0; i4 < i3; i4++) {
                        if (objArr[i4].equals(objArr2[i4])) {
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
