package com.facebook.react.uimanager.style;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: BackgroundRepeat.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/facebook/react/uimanager/style/BackgroundRepeatKeyword;", "", "<init>", "(Ljava/lang/String;I)V", "Repeat", "Space", "Round", "NoRepeat", "ReactAndroid_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class BackgroundRepeatKeyword {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ BackgroundRepeatKeyword[] $VALUES;
    public static final BackgroundRepeatKeyword Repeat = new BackgroundRepeatKeyword("Repeat", 0);
    public static final BackgroundRepeatKeyword Space = new BackgroundRepeatKeyword("Space", 1);
    public static final BackgroundRepeatKeyword Round = new BackgroundRepeatKeyword("Round", 2);
    public static final BackgroundRepeatKeyword NoRepeat = new BackgroundRepeatKeyword("NoRepeat", 3);

    private static final /* synthetic */ BackgroundRepeatKeyword[] $values() {
        return new BackgroundRepeatKeyword[]{Repeat, Space, Round, NoRepeat};
    }

    public static EnumEntries<BackgroundRepeatKeyword> getEntries() {
        return $ENTRIES;
    }

    private BackgroundRepeatKeyword(String str, int i) {
    }

    static {
        BackgroundRepeatKeyword[] backgroundRepeatKeywordArr$values = $values();
        $VALUES = backgroundRepeatKeywordArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(backgroundRepeatKeywordArr$values);
    }

    public static BackgroundRepeatKeyword valueOf(String str) {
        return (BackgroundRepeatKeyword) Enum.valueOf(BackgroundRepeatKeyword.class, str);
    }

    public static BackgroundRepeatKeyword[] values() {
        return (BackgroundRepeatKeyword[]) $VALUES.clone();
    }
}
