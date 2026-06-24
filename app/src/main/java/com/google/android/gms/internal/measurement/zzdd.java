package com.google.android.gms.internal.measurement;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdd extends zzbu implements zzdb {
    zzdd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void beginAdUnitExposure(String str, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        parcelA_.writeLong(j);
        zzb(23, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        parcelA_.writeString(str2);
        zzbw.zza(parcelA_, bundle);
        zzb(9, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void clearMeasurementEnabled(long j) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeLong(j);
        zzb(43, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void endAdUnitExposure(String str, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        parcelA_.writeLong(j);
        zzb(24, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void generateEventId(zzdg zzdgVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, zzdgVar);
        zzb(22, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void getAppInstanceId(zzdg zzdgVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, zzdgVar);
        zzb(20, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void getCachedAppInstanceId(zzdg zzdgVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, zzdgVar);
        zzb(19, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void getConditionalUserProperties(String str, String str2, zzdg zzdgVar) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        parcelA_.writeString(str2);
        zzbw.zza(parcelA_, zzdgVar);
        zzb(10, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void getCurrentScreenClass(zzdg zzdgVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, zzdgVar);
        zzb(17, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void getCurrentScreenName(zzdg zzdgVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, zzdgVar);
        zzb(16, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void getGmpAppId(zzdg zzdgVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, zzdgVar);
        zzb(21, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void getMaxUserProperties(String str, zzdg zzdgVar) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        zzbw.zza(parcelA_, zzdgVar);
        zzb(6, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void getSessionId(zzdg zzdgVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, zzdgVar);
        zzb(46, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void getTestFlag(zzdg zzdgVar, int i) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, zzdgVar);
        parcelA_.writeInt(i);
        zzb(38, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void getUserProperties(String str, String str2, boolean z, zzdg zzdgVar) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        parcelA_.writeString(str2);
        zzbw.zza(parcelA_, z);
        zzbw.zza(parcelA_, zzdgVar);
        zzb(5, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void initForTests(Map map) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeMap(map);
        zzb(37, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void initialize(IObjectWrapper iObjectWrapper, zzdo zzdoVar, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, iObjectWrapper);
        zzbw.zza(parcelA_, zzdoVar);
        parcelA_.writeLong(j);
        zzb(1, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void isDataCollectionEnabled(zzdg zzdgVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, zzdgVar);
        zzb(40, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void logEvent(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        parcelA_.writeString(str2);
        zzbw.zza(parcelA_, bundle);
        zzbw.zza(parcelA_, z);
        zzbw.zza(parcelA_, z2);
        parcelA_.writeLong(j);
        zzb(2, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void logEventAndBundle(String str, String str2, Bundle bundle, zzdg zzdgVar, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        parcelA_.writeString(str2);
        zzbw.zza(parcelA_, bundle);
        zzbw.zza(parcelA_, zzdgVar);
        parcelA_.writeLong(j);
        zzb(3, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void logHealthData(int i, String str, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeInt(i);
        parcelA_.writeString(str);
        zzbw.zza(parcelA_, iObjectWrapper);
        zzbw.zza(parcelA_, iObjectWrapper2);
        zzbw.zza(parcelA_, iObjectWrapper3);
        zzb(33, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void onActivityCreated(IObjectWrapper iObjectWrapper, Bundle bundle, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, iObjectWrapper);
        zzbw.zza(parcelA_, bundle);
        parcelA_.writeLong(j);
        zzb(27, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void onActivityDestroyed(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, iObjectWrapper);
        parcelA_.writeLong(j);
        zzb(28, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void onActivityPaused(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, iObjectWrapper);
        parcelA_.writeLong(j);
        zzb(29, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void onActivityResumed(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, iObjectWrapper);
        parcelA_.writeLong(j);
        zzb(30, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper, zzdg zzdgVar, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, iObjectWrapper);
        zzbw.zza(parcelA_, zzdgVar);
        parcelA_.writeLong(j);
        zzb(31, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void onActivityStarted(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, iObjectWrapper);
        parcelA_.writeLong(j);
        zzb(25, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void onActivityStopped(IObjectWrapper iObjectWrapper, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, iObjectWrapper);
        parcelA_.writeLong(j);
        zzb(26, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void performAction(Bundle bundle, zzdg zzdgVar, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, bundle);
        zzbw.zza(parcelA_, zzdgVar);
        parcelA_.writeLong(j);
        zzb(32, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void registerOnMeasurementEventListener(zzdh zzdhVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, zzdhVar);
        zzb(35, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void resetAnalyticsData(long j) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeLong(j);
        zzb(12, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void setConditionalUserProperty(Bundle bundle, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, bundle);
        parcelA_.writeLong(j);
        zzb(8, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void setConsent(Bundle bundle, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, bundle);
        parcelA_.writeLong(j);
        zzb(44, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void setConsentThirdParty(Bundle bundle, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, bundle);
        parcelA_.writeLong(j);
        zzb(45, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void setCurrentScreen(IObjectWrapper iObjectWrapper, String str, String str2, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, iObjectWrapper);
        parcelA_.writeString(str);
        parcelA_.writeString(str2);
        parcelA_.writeLong(j);
        zzb(15, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void setDataCollectionEnabled(boolean z) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, z);
        zzb(39, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void setDefaultEventParameters(Bundle bundle) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, bundle);
        zzb(42, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void setEventInterceptor(zzdh zzdhVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, zzdhVar);
        zzb(34, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void setInstanceIdProvider(zzdm zzdmVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, zzdmVar);
        zzb(18, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void setMeasurementEnabled(boolean z, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, z);
        parcelA_.writeLong(j);
        zzb(11, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void setMinimumSessionDuration(long j) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeLong(j);
        zzb(13, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void setSessionTimeoutDuration(long j) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeLong(j);
        zzb(14, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void setSgtmDebugInfo(Intent intent) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, intent);
        zzb(48, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void setUserId(String str, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        parcelA_.writeLong(j);
        zzb(7, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void setUserProperty(String str, String str2, IObjectWrapper iObjectWrapper, boolean z, long j) throws RemoteException {
        Parcel parcelA_ = a_();
        parcelA_.writeString(str);
        parcelA_.writeString(str2);
        zzbw.zza(parcelA_, iObjectWrapper);
        zzbw.zza(parcelA_, z);
        parcelA_.writeLong(j);
        zzb(4, parcelA_);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final void unregisterOnMeasurementEventListener(zzdh zzdhVar) throws RemoteException {
        Parcel parcelA_ = a_();
        zzbw.zza(parcelA_, zzdhVar);
        zzb(36, parcelA_);
    }
}
