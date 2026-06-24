package com.google.android.gms.measurement.internal;

import android.text.TextUtils;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzgi {
    private final zzim zza;

    static zzgi zza(String str) {
        return new zzgi((TextUtils.isEmpty(str) || str.length() > 1) ? zzim.UNINITIALIZED : zzin.zza(str.charAt(0)));
    }

    final zzim zza() {
        return this.zza;
    }

    final String zzb() {
        return String.valueOf(zzin.zza(this.zza));
    }

    zzgi(zzim zzimVar) {
        this.zza = zzimVar;
    }
}
