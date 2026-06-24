package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public class zzmn extends Enum<zzmn> {
    public static final zzmn zza;
    public static final zzmn zzb;
    public static final zzmn zzc;
    public static final zzmn zzd;
    public static final zzmn zze;
    public static final zzmn zzf;
    public static final zzmn zzg;
    public static final zzmn zzh;
    public static final zzmn zzi;
    public static final zzmn zzj;
    public static final zzmn zzk;
    public static final zzmn zzl;
    public static final zzmn zzm;
    public static final zzmn zzn;
    public static final zzmn zzo;
    public static final zzmn zzp;
    public static final zzmn zzq;
    public static final zzmn zzr;
    private static final /* synthetic */ zzmn[] zzs;
    private final zzmx zzt;
    private final int zzu;

    /* synthetic */ zzmn(String str, int i, zzmx zzmxVar, int i2, zzmy zzmyVar) {
        this(str, i, zzmxVar, i2);
    }

    public final int zza() {
        return this.zzu;
    }

    public final zzmx zzb() {
        return this.zzt;
    }

    static {
        zzmn zzmnVar = new zzmn("DOUBLE", 0, zzmx.DOUBLE, 1);
        zza = zzmnVar;
        zzmn zzmnVar2 = new zzmn("FLOAT", 1, zzmx.FLOAT, 5);
        zzb = zzmnVar2;
        zzmn zzmnVar3 = new zzmn("INT64", 2, zzmx.LONG, 0);
        zzc = zzmnVar3;
        zzmn zzmnVar4 = new zzmn("UINT64", 3, zzmx.LONG, 0);
        zzd = zzmnVar4;
        zzmn zzmnVar5 = new zzmn("INT32", 4, zzmx.INT, 0);
        zze = zzmnVar5;
        zzmn zzmnVar6 = new zzmn("FIXED64", 5, zzmx.LONG, 1);
        zzf = zzmnVar6;
        zzmn zzmnVar7 = new zzmn("FIXED32", 6, zzmx.INT, 5);
        zzg = zzmnVar7;
        zzmn zzmnVar8 = new zzmn("BOOL", 7, zzmx.BOOLEAN, 0);
        zzh = zzmnVar8;
        zzmq zzmqVar = new zzmq("STRING", zzmx.STRING);
        zzi = zzmqVar;
        zzms zzmsVar = new zzms("GROUP", zzmx.MESSAGE);
        zzj = zzmsVar;
        zzmu zzmuVar = new zzmu("MESSAGE", zzmx.MESSAGE);
        zzk = zzmuVar;
        zzmw zzmwVar = new zzmw("BYTES", zzmx.BYTE_STRING);
        zzl = zzmwVar;
        zzmn zzmnVar9 = new zzmn("UINT32", 12, zzmx.INT, 0);
        zzm = zzmnVar9;
        zzmn zzmnVar10 = new zzmn("ENUM", 13, zzmx.ENUM, 0);
        zzn = zzmnVar10;
        zzmn zzmnVar11 = new zzmn("SFIXED32", 14, zzmx.INT, 5);
        zzo = zzmnVar11;
        zzmn zzmnVar12 = new zzmn("SFIXED64", 15, zzmx.LONG, 1);
        zzp = zzmnVar12;
        zzmn zzmnVar13 = new zzmn("SINT32", 16, zzmx.INT, 0);
        zzq = zzmnVar13;
        zzmn zzmnVar14 = new zzmn("SINT64", 17, zzmx.LONG, 0);
        zzr = zzmnVar14;
        zzs = new zzmn[]{zzmnVar, zzmnVar2, zzmnVar3, zzmnVar4, zzmnVar5, zzmnVar6, zzmnVar7, zzmnVar8, zzmqVar, zzmsVar, zzmuVar, zzmwVar, zzmnVar9, zzmnVar10, zzmnVar11, zzmnVar12, zzmnVar13, zzmnVar14};
    }

    /* JADX INFO: Access modifiers changed from: private */
    zzmn(String str, int i, zzmx zzmxVar, int i2) {
        super(str, i);
        this.zzt = zzmxVar;
        this.zzu = i2;
    }

    public static zzmn[] values() {
        return (zzmn[]) zzs.clone();
    }
}
