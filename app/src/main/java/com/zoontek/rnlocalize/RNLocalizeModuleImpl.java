package com.zoontek.rnlocalize;

import android.app.Activity;
import android.content.Intent;
import android.icu.number.NumberFormatter;
import android.icu.number.UnlocalizedNumberFormatter;
import android.icu.util.MeasureUnit;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.text.format.DateFormat;
import androidx.core.os.LocaleListCompat;
import androidx.core.text.util.LocalePreferences;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import io.sentry.protocol.SentryStackFrame;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: RNLocalizeModuleImpl.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005H\u0002J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0018\u0010\u0012\u001a\n \u0013*\u0004\u0018\u00010\u00050\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0018\u0010\u0014\u001a\n \u0013*\u0004\u0018\u00010\u00050\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00100\b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0019\u001a\u00020\u0005H\u0002J\u0006\u0010\u001a\u001a\u00020\u0005J\u000e\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010!\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\"\u001a\u00020\u0005J\u000e\u0010#\u001a\u00020$2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010%\u001a\u00020$2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010&\u001a\u00020$2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010'\u001a\u00020$2\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010(\u001a\u00020)2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010*\u001a\u00020+R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/zoontek/rnlocalize/RNLocalizeModuleImpl;", "", "<init>", "()V", "NAME", "", "ERROR_INVALID_ACTIVITY", "USES_FAHRENHEIT", "", "USES_IMPERIAL", "createLanguageTag", "languageCode", "scriptCode", "countryCode", "getCountryCodeForLocale", "locale", "Ljava/util/Locale;", "getCurrencyCodeForLocale", "getLanguageCodeForLocale", "kotlin.jvm.PlatformType", "getScriptCodeForLocale", "getSystemLocale", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "getSystemLocales", "getMiuiRegionCode", "getCalendar", "getCountry", "getCurrencies", "Lcom/facebook/react/bridge/WritableArray;", "getLocales", "getNumberFormatSettings", "Lcom/facebook/react/bridge/WritableMap;", "getTemperatureUnit", "getTimeZone", "uses24HourClock", "", "usesMetricSystem", "usesAutoDateAndTime", "usesAutoTimeZone", "openAppLanguageSettings", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lcom/facebook/react/bridge/Promise;", "react-native-localize_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class RNLocalizeModuleImpl {
    private static final String ERROR_INVALID_ACTIVITY = "E_INVALID_ACTIVITY";
    public static final String NAME = "RNLocalize";
    public static final RNLocalizeModuleImpl INSTANCE = new RNLocalizeModuleImpl();
    private static final List<String> USES_FAHRENHEIT = CollectionsKt.listOf((Object[]) new String[]{"BS", "BZ", "KY", "PR", "PW", "US"});
    private static final List<String> USES_IMPERIAL = CollectionsKt.listOf((Object[]) new String[]{"LR", "MM", "US"});

    private RNLocalizeModuleImpl() {
    }

    private final String createLanguageTag(String languageCode, String scriptCode, String countryCode) {
        StringBuilder sb = new StringBuilder();
        sb.append(languageCode);
        if (scriptCode.length() > 0) {
            sb.append("-" + scriptCode);
        }
        sb.append("-" + countryCode);
        return sb.toString();
    }

    private final String getCountryCodeForLocale(Locale locale) {
        try {
            String country = locale.getCountry();
            if (Intrinsics.areEqual(country, "419")) {
                return "UN";
            }
            Intrinsics.checkNotNull(country);
            if (country.length() > 0) {
                Locale locale2 = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale2, "getDefault(...)");
                String upperCase = country.toUpperCase(locale2);
                Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                return upperCase;
            }
        } catch (Exception unused) {
        }
        return "";
    }

    private final String getCurrencyCodeForLocale(Locale locale) {
        try {
            Currency currency = Currency.getInstance(locale);
            if (currency != null) {
                String currencyCode = currency.getCurrencyCode();
                if (currencyCode != null) {
                    return currencyCode;
                }
            }
        } catch (Exception unused) {
        }
        return "";
    }

    private final String getLanguageCodeForLocale(Locale locale) {
        String language = locale.getLanguage();
        if (language == null) {
            return language;
        }
        int iHashCode = language.hashCode();
        return iHashCode != 3365 ? iHashCode != 3374 ? (iHashCode == 3391 && language.equals("ji")) ? "yi" : language : !language.equals("iw") ? language : "he" : !language.equals("in") ? language : "id";
    }

    private final String getScriptCodeForLocale(Locale locale) {
        String script = locale.getScript();
        if (script.length() == 0) {
            script = "";
        }
        return script;
    }

    private final Locale getSystemLocale(ReactApplicationContext reactContext) {
        Locale locale = reactContext.getResources().getConfiguration().getLocales().get(0);
        Intrinsics.checkNotNullExpressionValue(locale, "get(...)");
        return locale;
    }

    private final List<Locale> getSystemLocales(ReactApplicationContext reactContext) {
        reactContext.getResources().getConfiguration();
        LocaleListCompat localeListCompat = LocaleListCompat.getDefault();
        Intrinsics.checkNotNullExpressionValue(localeListCompat, "getDefault(...)");
        int size = localeListCompat.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            Locale locale = localeListCompat.get(i);
            Intrinsics.checkNotNull(locale);
            arrayList.add(locale);
        }
        return arrayList;
    }

    private final String getMiuiRegionCode() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Object objRequireNonNull = Objects.requireNonNull(cls.getMethod("get", String.class).invoke(cls, "ro.miui.region"));
            Intrinsics.checkNotNull(objRequireNonNull, "null cannot be cast to non-null type kotlin.String");
            return (String) objRequireNonNull;
        } catch (Exception unused) {
            return "";
        }
    }

    public final String getCalendar() {
        return LocalePreferences.CalendarType.GREGORIAN;
    }

    public final String getCountry(ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        String miuiRegionCode = getMiuiRegionCode();
        String countryCodeForLocale = getCountryCodeForLocale(getSystemLocale(reactContext));
        return miuiRegionCode.length() > 0 ? miuiRegionCode : countryCodeForLocale.length() == 0 ? "US" : countryCodeForLocale;
    }

    public final WritableArray getCurrencies(ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        List<Locale> systemLocales = getSystemLocales(reactContext);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        WritableArray writableArrayCreateArray = Arguments.createArray();
        Iterator<T> it = systemLocales.iterator();
        while (it.hasNext()) {
            String currencyCodeForLocale = INSTANCE.getCurrencyCodeForLocale((Locale) it.next());
            if (currencyCodeForLocale.length() > 0 && linkedHashSet.add(currencyCodeForLocale)) {
                writableArrayCreateArray.pushString(currencyCodeForLocale);
            }
        }
        if (writableArrayCreateArray.size() == 0) {
            writableArrayCreateArray.pushString("USD");
        }
        return writableArrayCreateArray;
    }

    public final WritableArray getLocales(ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        List<Locale> systemLocales = getSystemLocales(reactContext);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        WritableArray writableArrayCreateArray = Arguments.createArray();
        String country = getCountry(reactContext);
        for (Locale locale : systemLocales) {
            String languageCodeForLocale = getLanguageCodeForLocale(locale);
            String scriptCodeForLocale = getScriptCodeForLocale(locale);
            String countryCodeForLocale = getCountryCodeForLocale(locale);
            if (countryCodeForLocale.length() == 0) {
                countryCodeForLocale = country;
            }
            String str = countryCodeForLocale;
            Intrinsics.checkNotNull(languageCodeForLocale);
            Intrinsics.checkNotNull(scriptCodeForLocale);
            String strCreateLanguageTag = createLanguageTag(languageCodeForLocale, scriptCodeForLocale, str);
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putString("languageCode", languageCodeForLocale);
            writableMapCreateMap.putString("countryCode", str);
            writableMapCreateMap.putString("languageTag", strCreateLanguageTag);
            writableMapCreateMap.putBoolean("isRTL", TextUtils.getLayoutDirectionFromLocale(locale) == 1);
            if (scriptCodeForLocale.length() > 0) {
                writableMapCreateMap.putString("scriptCode", scriptCodeForLocale);
            }
            if (linkedHashSet.add(strCreateLanguageTag)) {
                writableArrayCreateArray.pushMap(writableMapCreateMap);
            }
        }
        return writableArrayCreateArray;
    }

    public final WritableMap getNumberFormatSettings(ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(getSystemLocale(reactContext));
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("decimalSeparator", String.valueOf(decimalFormatSymbols.getDecimalSeparator()));
        writableMapCreateMap.putString("groupingSeparator", String.valueOf(decimalFormatSymbols.getGroupingSeparator()));
        return writableMapCreateMap;
    }

    public final String getTemperatureUnit(ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        if (Build.VERSION.SDK_INT < 33) {
            return USES_FAHRENHEIT.contains(getCountry(reactContext)) ? "fahrenheit" : LocalePreferences.TemperatureUnit.CELSIUS;
        }
        String identifier = ((UnlocalizedNumberFormatter) ((UnlocalizedNumberFormatter) NumberFormatter.with().usage("weather")).unit(MeasureUnit.CELSIUS)).locale(getSystemLocale(reactContext)).format(1L).getOutputUnit().getIdentifier();
        Intrinsics.checkNotNull(identifier);
        return StringsKt.startsWith$default(identifier, LocalePreferences.TemperatureUnit.FAHRENHEIT, false, 2, (Object) null) ? "fahrenheit" : LocalePreferences.TemperatureUnit.CELSIUS;
    }

    public final String getTimeZone() {
        String id = TimeZone.getDefault().getID();
        Intrinsics.checkNotNullExpressionValue(id, "getID(...)");
        return id;
    }

    public final boolean uses24HourClock(ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        return DateFormat.is24HourFormat(reactContext);
    }

    public final boolean usesMetricSystem(ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        return !USES_IMPERIAL.contains(getCountry(reactContext));
    }

    public final boolean usesAutoDateAndTime(ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        return Settings.Global.getInt(reactContext.getContentResolver(), "auto_time", 0) != 0;
    }

    public final boolean usesAutoTimeZone(ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        return Settings.Global.getInt(reactContext.getContentResolver(), "auto_time_zone", 0) != 0;
    }

    public final void openAppLanguageSettings(ReactApplicationContext reactContext, Promise promise) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(promise, "promise");
        if (Build.VERSION.SDK_INT < 33) {
            promise.reject("unsupported", "openAppLanguageSettings is supported only on Android 13+");
            return;
        }
        try {
            String packageName = reactContext.getPackageName();
            Intent intent = new Intent();
            intent.setAction("android.settings.APP_LOCALE_SETTINGS");
            intent.setData(Uri.fromParts(SentryStackFrame.JsonKeys.PACKAGE, packageName, null));
            Activity currentActivity = reactContext.getCurrentActivity();
            if (currentActivity != null) {
                currentActivity.startActivity(intent);
            }
            promise.resolve(true);
        } catch (Exception e) {
            promise.reject(ERROR_INVALID_ACTIVITY, e);
        }
    }
}
