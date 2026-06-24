package com.google.android.gms.internal.measurement;

import com.google.common.base.Ascii;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzhw {
    static double zza(byte[] bArr, int i) {
        return Double.longBitsToDouble(zzd(bArr, i));
    }

    static float zzb(byte[] bArr, int i) {
        return Float.intBitsToFloat(zzc(bArr, i));
    }

    static int zza(byte[] bArr, int i, zzhv zzhvVar) throws zzjs {
        int iZzc = zzc(bArr, i, zzhvVar);
        int i2 = zzhvVar.zza;
        if (i2 < 0) {
            throw zzjs.zzf();
        }
        if (i2 > bArr.length - iZzc) {
            throw zzjs.zzh();
        }
        if (i2 == 0) {
            zzhvVar.zzc = zzia.zza;
            return iZzc;
        }
        zzhvVar.zzc = zzia.zza(bArr, iZzc, i2);
        return iZzc + i2;
    }

    static int zzc(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    static int zza(zzll zzllVar, byte[] bArr, int i, int i2, int i3, zzhv zzhvVar) throws IOException {
        Object objZza = zzllVar.zza();
        int iZza = zza(objZza, zzllVar, bArr, i, i2, i3, zzhvVar);
        zzllVar.zzc(objZza);
        zzhvVar.zzc = objZza;
        return iZza;
    }

    static int zza(zzll zzllVar, byte[] bArr, int i, int i2, zzhv zzhvVar) throws IOException {
        Object objZza = zzllVar.zza();
        int iZza = zza(objZza, zzllVar, bArr, i, i2, zzhvVar);
        zzllVar.zzc(objZza);
        zzhvVar.zzc = objZza;
        return iZza;
    }

    static int zza(zzll<?> zzllVar, int i, byte[] bArr, int i2, int i3, zzjt<?> zzjtVar, zzhv zzhvVar) throws IOException {
        int iZza = zza(zzllVar, bArr, i2, i3, zzhvVar);
        zzjtVar.add(zzhvVar.zzc);
        while (iZza < i3) {
            int iZzc = zzc(bArr, iZza, zzhvVar);
            if (i != zzhvVar.zza) {
                break;
            }
            iZza = zza(zzllVar, bArr, iZzc, i3, zzhvVar);
            zzjtVar.add(zzhvVar.zzc);
        }
        return iZza;
    }

    static int zza(byte[] bArr, int i, zzjt<?> zzjtVar, zzhv zzhvVar) throws IOException {
        zzjn zzjnVar = (zzjn) zzjtVar;
        int iZzc = zzc(bArr, i, zzhvVar);
        int i2 = zzhvVar.zza + iZzc;
        while (iZzc < i2) {
            iZzc = zzc(bArr, iZzc, zzhvVar);
            zzjnVar.zzd(zzhvVar.zza);
        }
        if (iZzc == i2) {
            return iZzc;
        }
        throw zzjs.zzh();
    }

    static int zzb(byte[] bArr, int i, zzhv zzhvVar) throws zzjs {
        int iZzc = zzc(bArr, i, zzhvVar);
        int i2 = zzhvVar.zza;
        if (i2 < 0) {
            throw zzjs.zzf();
        }
        if (i2 == 0) {
            zzhvVar.zzc = "";
            return iZzc;
        }
        zzhvVar.zzc = zzmk.zzb(bArr, iZzc, i2);
        return iZzc + i2;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzme zzmeVar, zzhv zzhvVar) throws zzjs {
        if ((i >>> 3) == 0) {
            throw zzjs.zzc();
        }
        int i4 = i & 7;
        if (i4 == 0) {
            int iZzd = zzd(bArr, i2, zzhvVar);
            zzmeVar.zza(i, Long.valueOf(zzhvVar.zzb));
            return iZzd;
        }
        if (i4 == 1) {
            zzmeVar.zza(i, Long.valueOf(zzd(bArr, i2)));
            return i2 + 8;
        }
        if (i4 == 2) {
            int iZzc = zzc(bArr, i2, zzhvVar);
            int i5 = zzhvVar.zza;
            if (i5 < 0) {
                throw zzjs.zzf();
            }
            if (i5 > bArr.length - iZzc) {
                throw zzjs.zzh();
            }
            if (i5 == 0) {
                zzmeVar.zza(i, zzia.zza);
            } else {
                zzmeVar.zza(i, zzia.zza(bArr, iZzc, i5));
            }
            return iZzc + i5;
        }
        if (i4 != 3) {
            if (i4 == 5) {
                zzmeVar.zza(i, Integer.valueOf(zzc(bArr, i2)));
                return i2 + 4;
            }
            throw zzjs.zzc();
        }
        zzme zzmeVarZzd = zzme.zzd();
        int i6 = (i & (-8)) | 4;
        int i7 = 0;
        while (true) {
            if (i2 >= i3) {
                break;
            }
            int iZzc2 = zzc(bArr, i2, zzhvVar);
            i7 = zzhvVar.zza;
            if (i7 == i6) {
                i2 = iZzc2;
                break;
            }
            i2 = zza(i7, bArr, iZzc2, i3, zzmeVarZzd, zzhvVar);
        }
        if (i2 > i3 || i7 != i6) {
            throw zzjs.zzg();
        }
        zzmeVar.zza(i, zzmeVarZzd);
        return i2;
    }

    static int zzc(byte[] bArr, int i, zzhv zzhvVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b >= 0) {
            zzhvVar.zza = b;
            return i2;
        }
        return zza(b, bArr, i2, zzhvVar);
    }

    static int zza(int i, byte[] bArr, int i2, zzhv zzhvVar) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzhvVar.zza = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & 127) << 7);
        int i6 = i2 + 2;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzhvVar.zza = i5 | (b2 << Ascii.SO);
            return i6;
        }
        int i7 = i5 | ((b2 & 127) << 14);
        int i8 = i2 + 3;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzhvVar.zza = i7 | (b3 << Ascii.NAK);
            return i8;
        }
        int i9 = i7 | ((b3 & 127) << 21);
        int i10 = i2 + 4;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzhvVar.zza = i9 | (b4 << Ascii.FS);
            return i10;
        }
        int i11 = i9 | ((b4 & 127) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzhvVar.zza = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzjt<?> zzjtVar, zzhv zzhvVar) {
        zzjn zzjnVar = (zzjn) zzjtVar;
        int iZzc = zzc(bArr, i2, zzhvVar);
        zzjnVar.zzd(zzhvVar.zza);
        while (iZzc < i3) {
            int iZzc2 = zzc(bArr, iZzc, zzhvVar);
            if (i != zzhvVar.zza) {
                break;
            }
            iZzc = zzc(bArr, iZzc2, zzhvVar);
            zzjnVar.zzd(zzhvVar.zza);
        }
        return iZzc;
    }

    static int zzd(byte[] bArr, int i, zzhv zzhvVar) {
        int i2 = i + 1;
        long j = bArr[i];
        if (j >= 0) {
            zzhvVar.zzb = j;
            return i2;
        }
        int i3 = i + 2;
        byte b = bArr[i2];
        long j2 = (j & 127) | (((long) (b & 127)) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            i4 += 7;
            j2 |= ((long) (b2 & 127)) << i4;
            b = b2;
            i3 = i5;
        }
        zzhvVar.zzb = j2;
        return i3;
    }

    static int zza(Object obj, zzll zzllVar, byte[] bArr, int i, int i2, int i3, zzhv zzhvVar) throws IOException {
        int iZza = ((zzkx) zzllVar).zza(obj, bArr, i, i2, i3, zzhvVar);
        zzhvVar.zzc = obj;
        return iZza;
    }

    static int zza(Object obj, zzll zzllVar, byte[] bArr, int i, int i2, zzhv zzhvVar) throws IOException {
        int iZza = i + 1;
        int i3 = bArr[i];
        if (i3 < 0) {
            iZza = zza(i3, bArr, iZza, zzhvVar);
            i3 = zzhvVar.zza;
        }
        int i4 = iZza;
        if (i3 < 0 || i3 > i2 - i4) {
            throw zzjs.zzh();
        }
        int i5 = i4 + i3;
        zzllVar.zza(obj, bArr, i4, i5, zzhvVar);
        zzhvVar.zzc = obj;
        return i5;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzhv zzhvVar) throws zzjs {
        if ((i >>> 3) == 0) {
            throw zzjs.zzc();
        }
        int i4 = i & 7;
        if (i4 == 0) {
            return zzd(bArr, i2, zzhvVar);
        }
        if (i4 == 1) {
            return i2 + 8;
        }
        if (i4 == 2) {
            return zzc(bArr, i2, zzhvVar) + zzhvVar.zza;
        }
        if (i4 != 3) {
            if (i4 == 5) {
                return i2 + 4;
            }
            throw zzjs.zzc();
        }
        int i5 = (i & (-8)) | 4;
        int i6 = 0;
        while (i2 < i3) {
            i2 = zzc(bArr, i2, zzhvVar);
            i6 = zzhvVar.zza;
            if (i6 == i5) {
                break;
            }
            i2 = zza(i6, bArr, i2, i3, zzhvVar);
        }
        if (i2 > i3 || i6 != i5) {
            throw zzjs.zzg();
        }
        return i2;
    }

    static long zzd(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }
}
