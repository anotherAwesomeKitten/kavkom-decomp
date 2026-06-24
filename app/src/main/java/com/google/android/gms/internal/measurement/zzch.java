package com.google.android.gms.internal.measurement;

import java.io.File;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzch implements zzci {
    @Override // com.google.android.gms.internal.measurement.zzci
    public final String zza(String str, zzcj zzcjVar, zzck zzckVar) {
        return str;
    }

    @Override // com.google.android.gms.internal.measurement.zzci
    public final /* synthetic */ String zza(String str) {
        return zza(str, zzcj.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzci
    public final /* synthetic */ String zza(File file, String str) {
        return zza(file, str, zzcj.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzci
    public final /* synthetic */ String zza(String str, zzcj zzcjVar) {
        return zza(str, zzcjVar, zzck.RAW_FILE_IO_TYPE);
    }

    @Override // com.google.android.gms.internal.measurement.zzci
    public final /* synthetic */ String zza(File file, String str, zzcj zzcjVar) {
        return zza(new File(file, str).getPath(), zzcjVar);
    }

    zzch() {
    }
}
