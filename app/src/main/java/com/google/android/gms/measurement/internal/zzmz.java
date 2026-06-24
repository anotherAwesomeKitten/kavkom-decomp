package com.google.android.gms.measurement.internal;

import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzmz {
    private String zza;
    private Map<String, String> zzb;
    private int zzc;

    public final int zza() {
        return this.zzc;
    }

    public final String zzb() {
        return this.zza;
    }

    public final Map<String, String> zzc() {
        return this.zzb;
    }

    zzmz(String str, int i) {
        this.zza = str;
        this.zzc = i;
    }

    /* JADX WARN: Incorrect types in method signature: (Ljava/lang/String;Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;Ljava/lang/Integer;)V */
    zzmz(String str, Map map, int i) {
        this.zza = str;
        this.zzb = map;
        this.zzc = i;
    }
}
