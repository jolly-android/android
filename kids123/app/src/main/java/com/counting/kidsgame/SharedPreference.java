package com.counting.kidsgame;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
    public static final String PREFS_KEY_ADFREE = "pref_key_adfree";
    public static final String PREFS_KEY_AL = "pref_key";
    public static final String PREFS_KEY_ISSHOWNEWAPP = "pref_key_isshownewapp";
    public static final String PREFS_KEY_LANG = "pref_key_lang";
    public static final String PREFS_KEY_NS = "pref_key_nevershow";
    public static final String PREFS_NAME_ADFREE = "pref_name_adfree";
    public static final String PREFS_NAME_AL = "pref_name";
    public static final String PREFS_NAME_ISSHOWNEWAPP = "pref_name_isshownewapp";
    public static final String PREFS_NAME_LANG = "pref_name_lang";
    public static final String PREFS_NAME_NS = "pref_name_nevershow";
    public static final String PREF_APPOPEN_DATE = "appopen_date_name";
    public static final String PREF_GAME1_LOCKED = "game1locked_name";
    public static final String PREF_GAME2_LOCKED = "game2locked_name";
    public static final String PREF_GAME3_LOCKED = "game3locked_name";
    public static final String PREF_KEY_APPOPEN_DATE = "appopen_date_key";
    public static final String PREF_KEY_GAME1_LOCKED = "game1locked_key";
    public static final String PREF_KEY_GAME2_LOCKED = "game2locked_key";
    public static final String PREF_KEY_GAME3_LOCKED = "game3locked_key";
    public static final String PREF_KEY_MUSIC = "key_music";
    public static final String PREF_KEY_SOUND = "key_sound";
    public static final String PREF_NAME_MUSIC = "name_music";
    public static final String PREF_NAME_SOUND = "name_sound";
    public String PREFS_KEY;
    public String PREFS_NAME;
    private SharedPreferences sharedPrefisShow;

    public SharedPreference(String str, String str2) {
        this.PREFS_NAME = str;
        this.PREFS_KEY = str2;
    }

    public static int getBuyChoise(Context context) {
        return context.getSharedPreferences("SCORE", 0).getInt("BUY", 0);
    }

    public static void setBuyChoise(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("SCORE", 0).edit();
        edit.putInt("BUY", 1);
        edit.apply();
    }

    public void clearSharedPreference(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences(this.PREFS_NAME, 0).edit();
        edit.clear();
        edit.apply();
    }

    public boolean getDialogNoShow(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME_ISSHOWNEWAPP, 0);
        this.sharedPrefisShow = sharedPreferences;
        return sharedPreferences.getBoolean(PREFS_KEY_ISSHOWNEWAPP, false);
    }

    public boolean getLoveCatLocked(Context context) {
        return Boolean.valueOf(context.getSharedPreferences(PREF_GAME1_LOCKED, 0).getBoolean(PREF_KEY_GAME1_LOCKED, false)).booleanValue();
    }

    public boolean getSettingMusic(Context context) {
        return context.getSharedPreferences(PREF_NAME_MUSIC, 0).getBoolean(PREF_KEY_MUSIC, true);
    }

    public boolean getSettingSound(Context context) {
        return Boolean.valueOf(context.getSharedPreferences(PREF_NAME_SOUND, 0).getBoolean(PREF_KEY_SOUND, true)).booleanValue();
    }

    public int getValue(Context context) {
        return context.getSharedPreferences(this.PREFS_NAME, 0).getInt(this.PREFS_KEY, 0);
    }

    public boolean getValueBool(Context context) {
        return context.getSharedPreferences(PREFS_NAME_ADFREE, 0).getBoolean(PREFS_NAME_ADFREE, false);
    }

    public String getappOpenDate(Context context) {
        return context.getSharedPreferences(PREF_APPOPEN_DATE, 0).getString(PREF_KEY_APPOPEN_DATE, (String) null);
    }

    public boolean getcuteCatLocked(Context context) {
        return Boolean.valueOf(context.getSharedPreferences(PREF_GAME2_LOCKED, 0).getBoolean(PREF_KEY_GAME2_LOCKED, false)).booleanValue();
    }

    public boolean getrealCatLocked(Context context) {
        return Boolean.valueOf(context.getSharedPreferences(PREF_GAME3_LOCKED, 0).getBoolean(PREF_KEY_GAME3_LOCKED, false)).booleanValue();
    }

    public void removeValue(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences(this.PREFS_NAME, 0).edit();
        edit.remove(this.PREFS_KEY);
        edit.apply();
    }

    public void save(Context context, int i) {
        SharedPreferences.Editor edit = context.getSharedPreferences(this.PREFS_NAME, 0).edit();
        edit.putInt(this.PREFS_KEY, i);
        edit.apply();
    }

    public void saveBoolean(Context context, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREFS_NAME_ADFREE, 0).edit();
        edit.putBoolean(PREFS_NAME_ADFREE, z);
        edit.apply();
    }

    public void saveCuteCatLocked(Context context, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREF_GAME2_LOCKED, 0).edit();
        edit.putBoolean(PREF_KEY_GAME2_LOCKED, z);
        edit.apply();
    }

    public void saveLoveCatLocked(Context context, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREF_GAME1_LOCKED, 0).edit();
        edit.putBoolean(PREF_KEY_GAME1_LOCKED, z);
        edit.apply();
    }

    public void saveSettingMusic(Context context, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREF_NAME_MUSIC, 0).edit();
        edit.putBoolean(PREF_KEY_MUSIC, z);
        edit.apply();
    }

    public void saveSettingSound(Context context, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREF_NAME_SOUND, 0).edit();
        edit.putBoolean(PREF_KEY_SOUND, z);
        edit.apply();
        edit.apply();
    }

    public void saveappOpenDate(Context context, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREF_APPOPEN_DATE, 0).edit();
        edit.putString(PREF_KEY_APPOPEN_DATE, str);
        edit.apply();
    }

    public void saverealCatLocked(Context context, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREF_GAME3_LOCKED, 0).edit();
        edit.putBoolean(PREF_KEY_GAME3_LOCKED, z);
        edit.apply();
    }

    public void setDialogNoShow(Context context, boolean z) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME_ISSHOWNEWAPP, 0);
        this.sharedPrefisShow = sharedPreferences;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(PREFS_KEY_ISSHOWNEWAPP, z);
        edit.apply();
    }
}
