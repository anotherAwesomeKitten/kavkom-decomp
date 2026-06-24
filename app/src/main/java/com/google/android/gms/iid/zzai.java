package com.google.android.gms.iid;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzai {
    private static zzai zzdd;

    public abstract zzaj<Boolean> zzd(String str, boolean z);

    public static synchronized zzai zzy() {
        if (zzdd == null) {
            zzdd = new zzac();
        }
        return zzdd;
    }
}
