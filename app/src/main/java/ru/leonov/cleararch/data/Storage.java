package ru.leonov.cleararch.data;

import android.content.Context;
import android.content.SharedPreferences;

import ru.leonov.cleararch.domain.Settings;

import static android.content.Context.MODE_PRIVATE;

public class Storage {
    private static final String NAME_SHARED_PREFERENCE = "CLEAR_ARCH";
    private static final String RUN_COUNTER_NAME = "RUN_COUNTER";

    public Settings getSettings(Context context) {
        Settings settings = new Settings();

        SharedPreferences sharedPref = context.getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE);

        settings.setRunCounter(sharedPref.getInt(RUN_COUNTER_NAME, 0));

        return settings;
    }

    public void setSettings(Context context, Settings settings) {
        SharedPreferences sharedPref = context.getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putInt(RUN_COUNTER_NAME, settings.getRunCounter());

        editor.apply();
    }
}
