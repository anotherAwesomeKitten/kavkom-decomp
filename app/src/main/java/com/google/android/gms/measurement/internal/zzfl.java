package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public interface zzfl extends IInterface {
    zzaj zza(zzo zzoVar) throws RemoteException;

    List<zzmu> zza(zzo zzoVar, Bundle bundle) throws RemoteException;

    List<zzno> zza(zzo zzoVar, boolean z) throws RemoteException;

    List<zzae> zza(String str, String str2, zzo zzoVar) throws RemoteException;

    List<zzae> zza(String str, String str2, String str3) throws RemoteException;

    List<zzno> zza(String str, String str2, String str3, boolean z) throws RemoteException;

    List<zzno> zza(String str, String str2, boolean z, zzo zzoVar) throws RemoteException;

    void zza(long j, String str, String str2, String str3) throws RemoteException;

    void zza(Bundle bundle, zzo zzoVar) throws RemoteException;

    void zza(zzae zzaeVar) throws RemoteException;

    void zza(zzae zzaeVar, zzo zzoVar) throws RemoteException;

    void zza(zzbd zzbdVar, zzo zzoVar) throws RemoteException;

    void zza(zzbd zzbdVar, String str, String str2) throws RemoteException;

    void zza(zzno zznoVar, zzo zzoVar) throws RemoteException;

    byte[] zza(zzbd zzbdVar, String str) throws RemoteException;

    String zzb(zzo zzoVar) throws RemoteException;

    void zzc(zzo zzoVar) throws RemoteException;

    void zzd(zzo zzoVar) throws RemoteException;

    void zze(zzo zzoVar) throws RemoteException;

    void zzf(zzo zzoVar) throws RemoteException;

    void zzg(zzo zzoVar) throws RemoteException;

    void zzh(zzo zzoVar) throws RemoteException;
}
