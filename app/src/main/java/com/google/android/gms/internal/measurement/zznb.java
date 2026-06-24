package com.google.android.gms.internal.measurement;

import android.support.v4.media.session.PlaybackStateCompat;
import com.facebook.common.util.UriUtil;
import io.sentry.metrics.MetricsHelper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zznb implements zznc {
    private static final zzgz<Long> zza;
    private static final zzgz<Long> zzaa;
    private static final zzgz<String> zzab;
    private static final zzgz<Long> zzac;
    private static final zzgz<String> zzad;
    private static final zzgz<String> zzae;
    private static final zzgz<String> zzaf;
    private static final zzgz<Long> zzag;
    private static final zzgz<Long> zzah;
    private static final zzgz<Long> zzai;
    private static final zzgz<Long> zzaj;
    private static final zzgz<Long> zzak;
    private static final zzgz<Long> zzal;
    private static final zzgz<Long> zzam;
    private static final zzgz<Long> zzan;
    private static final zzgz<Long> zzao;
    private static final zzgz<Long> zzap;
    private static final zzgz<Long> zzaq;
    private static final zzgz<Long> zzar;
    private static final zzgz<Long> zzas;
    private static final zzgz<Long> zzat;
    private static final zzgz<Long> zzau;
    private static final zzgz<Long> zzav;
    private static final zzgz<Long> zzaw;
    private static final zzgz<String> zzax;
    private static final zzgz<Long> zzay;
    private static final zzgz<String> zzaz;
    private static final zzgz<Long> zzb;
    private static final zzgz<Long> zzc;
    private static final zzgz<Long> zzd;
    private static final zzgz<String> zze;
    private static final zzgz<String> zzf;
    private static final zzgz<Long> zzg;
    private static final zzgz<Long> zzh;
    private static final zzgz<String> zzi;
    private static final zzgz<String> zzj;
    private static final zzgz<Long> zzk;
    private static final zzgz<Long> zzl;
    private static final zzgz<Long> zzm;
    private static final zzgz<Long> zzn;
    private static final zzgz<Long> zzo;
    private static final zzgz<Long> zzp;
    private static final zzgz<Long> zzq;
    private static final zzgz<Long> zzr;
    private static final zzgz<Long> zzs;
    private static final zzgz<Long> zzt;
    private static final zzgz<Long> zzu;
    private static final zzgz<String> zzv;
    private static final zzgz<Long> zzw;
    private static final zzgz<Long> zzx;
    private static final zzgz<Long> zzy;
    private static final zzgz<String> zzz;

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zza() {
        return zza.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzb() {
        return zzb.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzc() {
        return zzc.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzd() {
        return zzd.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zze() {
        return zzg.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzf() {
        return zzh.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzg() {
        return zzk.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzh() {
        return zzl.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzi() {
        return zzm.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzj() {
        return zzn.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzk() {
        return zzo.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzl() {
        return zzp.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzm() {
        return zzq.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzn() {
        return zzr.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzo() {
        return zzs.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzp() {
        return zzt.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzq() {
        return zzu.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzr() {
        return zzw.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzs() {
        return zzx.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzt() {
        return zzy.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzu() {
        return zzaa.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzv() {
        return zzac.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzw() {
        return zzag.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzx() {
        return zzah.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzy() {
        return zzai.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzz() {
        return zzaj.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzaa() {
        return zzak.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzab() {
        return zzal.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzac() {
        return zzam.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzad() {
        return zzan.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzae() {
        return zzao.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzaf() {
        return zzap.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzag() {
        return zzaq.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzah() {
        return zzar.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzai() {
        return zzas.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzaj() {
        return zzat.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzak() {
        return zzau.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzal() {
        return zzav.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzam() {
        return zzaw.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final long zzan() {
        return zzay.zza().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final String zzao() {
        return zze.zza();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final String zzap() {
        return zzf.zza();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final String zzaq() {
        return zzi.zza();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final String zzar() {
        return zzj.zza();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final String zzas() {
        return zzv.zza();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final String zzat() {
        return zzz.zza();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final String zzau() {
        return zzab.zza();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final String zzav() {
        return zzad.zza();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final String zzaw() {
        return zzae.zza();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final String zzax() {
        return zzaf.zza();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final String zzay() {
        return zzax.zza();
    }

    @Override // com.google.android.gms.internal.measurement.zznc
    public final String zzaz() {
        return zzaz.zza();
    }

    static {
        zzhh zzhhVarZza = new zzhh(zzgw.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zzhhVarZza.zza("measurement.ad_id_cache_time", 10000L);
        zzb = zzhhVarZza.zza("measurement.app_uninstalled_additional_ad_id_cache_time", 3600000L);
        zzc = zzhhVarZza.zza("measurement.max_bundles_per_iteration", 100L);
        zzd = zzhhVarZza.zza("measurement.config.cache_time", 86400000L);
        zzhhVarZza.zza("measurement.log_tag", "FA");
        zze = zzhhVarZza.zza("measurement.config.url_authority", "app-measurement.com");
        zzf = zzhhVarZza.zza("measurement.config.url_scheme", UriUtil.HTTPS_SCHEME);
        zzg = zzhhVarZza.zza("measurement.upload.debug_upload_interval", 1000L);
        zzh = zzhhVarZza.zza("measurement.session.engagement_interval", 3600000L);
        zzi = zzhhVarZza.zza("measurement.rb.attribution.event_params", "value|currency");
        zzhhVarZza.zza("measurement.id.rb.attribution.app_allowlist", 0L);
        zzj = zzhhVarZza.zza("measurement.sgtm.google_signal.url", "https://app-measurement.com/s");
        zzk = zzhhVarZza.zza("measurement.lifetimevalue.max_currency_tracked", 4L);
        zzl = zzhhVarZza.zza("measurement.dma_consent.max_daily_dcu_realtime_events", 1L);
        zzm = zzhhVarZza.zza("measurement.upload.max_event_parameter_value_length", 100L);
        zzn = zzhhVarZza.zza("measurement.store.max_stored_events_per_app", 100000L);
        zzo = zzhhVarZza.zza("measurement.experiment.max_ids", 50L);
        zzp = zzhhVarZza.zza("measurement.audience.filter_result_max_count", 200L);
        zzq = zzhhVarZza.zza("measurement.upload.max_item_scoped_custom_parameters", 27L);
        zzr = zzhhVarZza.zza("measurement.rb.attribution.client.min_ad_services_version", 7L);
        zzs = zzhhVarZza.zza("measurement.alarm_manager.minimum_interval", 60000L);
        zzt = zzhhVarZza.zza("measurement.upload.minimum_delay", 500L);
        zzu = zzhhVarZza.zza("measurement.monitoring.sample_period_millis", 86400000L);
        zzv = zzhhVarZza.zza("measurement.rb.attribution.app_allowlist", "com.labpixies.flood,com.sofascore.results,games.spearmint.triplecrush,com.block.juggle,io.supercent.linkedcubic,com.cdtg.gunsound,com.corestudios.storemanagementidle,com.cdgames.fidget3d,io.supercent.burgeridle,io.supercent.pizzaidle,jp.ne.ibis.ibispaintx.app,com.dencreak.dlcalculator,com.ebay.kleinanzeigen,de.wetteronline.wetterapp,com.game.shape.shift,com.champion.cubes,bubbleshooter.orig,com.wolt.android,com.master.hotelmaster,com.games.bus.arrival,com.playstrom.dop2,com.huuuge.casino.slots,com.ig.spider.fighting,com.jura.coloring.page,com.rikkogame.ragdoll2,com.ludo.king,com.sigma.prank.sound.haircut,com.crazy.block.robo.monster.cliffs.craft,com.fugo.wow,com.maps.locator.gps.gpstracker.phone,com.gamovation.tileclub,com.pronetis.ironball2,com.meesho.supply,pdf.pdfreader.viewer.editor.free,com.dino.race.master,com.ig.moto.racing,ai.photo.enhancer.photoclear,com.duolingo,com.candle.magic_piano,com.free.vpn.super.hotspot.open,sg.bigo.live,com.cdg.tictactoe,com.zhiliaoapp.musically.go,com.wildspike.wormszone,com.mast.status.video.edit,com.vyroai.photoeditorone,com.pujiagames.deeeersimulator,com.superbinogo.jungleboyadventure,com.trustedapp.pdfreaderpdfviewer,com.artimind.aiart.artgenerator.artavatar,de.cellular.ottohybrid,com.zeptolab.cats.google,in.crossy.daily_crossword");
        zzw = zzhhVarZza.zza("measurement.upload.realtime_upload_interval", 10000L);
        zzx = zzhhVarZza.zza("measurement.upload.refresh_blacklisted_config_interval", 604800000L);
        zzhhVarZza.zza("measurement.config.cache_time.service", 3600000L);
        zzy = zzhhVarZza.zza("measurement.service_client.idle_disconnect_millis", MetricsHelper.FLUSHER_SLEEP_TIME_MS);
        zzhhVarZza.zza("measurement.log_tag.service", "FA-SVC");
        zzz = zzhhVarZza.zza("measurement.sgtm.app_allowlist", "de.zalando.mobile.internal,de.zalando.mobile.internal.debug,de.zalando.lounge.dev,grit.storytel.app,com.rbc.mobile.android,com.rbc.mobile.android,com.dylvian.mango.activities,com.home24.android,com.home24.android.staging,se.lf.mobile.android,se.lf.mobile.android.beta,se.lf.mobile.android.rc,se.lf.mobile.android.test,se.lf.mobile.android.test.debug,com.boots.flagship.android,com.boots.flagshiproi.android,de.zalando.mobile,com.trivago,com.getyourguide.android,es.mobail.meliarewards,se.nansen.coop.debug,se.nansen.coop,se.coop.coop.qa,com.booking,com.google.firebaseengage,com.mse.mseapp.dev,com.mse.mseapp,pl.eobuwie.eobuwieapp,br.com.eventim.mobile.app.Android,ch.ticketcorner.mobile.app.Android,de.eventim.mobile.app.Android,dk.billetlugen.mobile.app.Android,nl.eventim.mobile.app.Android,com.asos.app,com.blueshieldca.prod,dk.magnetix.tivoliapp,matas.matas.internal,nl.omoda");
        zzaa = zzhhVarZza.zza("measurement.upload.stale_data_deletion_interval", 86400000L);
        zzab = zzhhVarZza.zza("measurement.rb.attribution.uri_authority", "google-analytics.com");
        zzac = zzhhVarZza.zza("measurement.rb.attribution.max_queue_time", 1209600000L);
        zzad = zzhhVarZza.zza("measurement.rb.attribution.uri_path", "privacy-sandbox/register-app-conversion");
        zzae = zzhhVarZza.zza("measurement.rb.attribution.query_parameters_to_remove", "");
        zzaf = zzhhVarZza.zza("measurement.rb.attribution.uri_scheme", UriUtil.HTTPS_SCHEME);
        zzag = zzhhVarZza.zza("measurement.sdk.attribution.cache.ttl", 604800000L);
        zzah = zzhhVarZza.zza("measurement.redaction.app_instance_id.ttl", 7200000L);
        zzai = zzhhVarZza.zza("measurement.upload.backoff_period", 43200000L);
        zzaj = zzhhVarZza.zza("measurement.upload.initial_upload_delay_time", 15000L);
        zzak = zzhhVarZza.zza("measurement.upload.interval", 3600000L);
        zzal = zzhhVarZza.zza("measurement.upload.max_bundle_size", PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH);
        zzam = zzhhVarZza.zza("measurement.upload.max_bundles", 100L);
        zzan = zzhhVarZza.zza("measurement.upload.max_conversions_per_day", 500L);
        zzao = zzhhVarZza.zza("measurement.upload.max_error_events_per_day", 1000L);
        zzap = zzhhVarZza.zza("measurement.upload.max_events_per_bundle", 1000L);
        zzaq = zzhhVarZza.zza("measurement.upload.max_events_per_day", 100000L);
        zzar = zzhhVarZza.zza("measurement.upload.max_public_events_per_day", 50000L);
        zzas = zzhhVarZza.zza("measurement.upload.max_queue_time", 2419200000L);
        zzat = zzhhVarZza.zza("measurement.upload.max_realtime_events_per_day", 10L);
        zzau = zzhhVarZza.zza("measurement.upload.max_batch_size", PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH);
        zzav = zzhhVarZza.zza("measurement.upload.retry_count", 6L);
        zzaw = zzhhVarZza.zza("measurement.upload.retry_time", 1800000L);
        zzax = zzhhVarZza.zza("measurement.upload.url", "https://app-measurement.com/a");
        zzay = zzhhVarZza.zza("measurement.upload.window_interval", 3600000L);
        zzaz = zzhhVarZza.zza("measurement.rb.attribution.user_properties", "_npa,npa");
    }
}
