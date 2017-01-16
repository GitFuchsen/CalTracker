package de.fhdw.bfwi115a.caltracker.app;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Locale;

public class ActivitySettings extends PreferenceActivity implements Preference.OnPreferenceChangeListener {

    Locale appLocale;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        LinearLayout root = (LinearLayout) findViewById(android.R.id.list).getParent().getParent().getParent();
        Toolbar toolbar_settings = (Toolbar) LayoutInflater.from(this).inflate(R.layout.settings_toolbar, root, false);
        root.addView(toolbar_settings, 0); // insert at top
        toolbar_settings.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addPreferencesFromResource(R.xml.preferences);

        Preference dailyMaxPreference = findPreference(getString(R.string.KEY_PREFERENCE_DAILY_MAX));
        dailyMaxPreference.setOnPreferenceChangeListener(this);

        SharedPreferences sharedPrefDailyMax = PreferenceManager.getDefaultSharedPreferences(this);
        String savedDailyMax = sharedPrefDailyMax.getString(dailyMaxPreference.getKey(), "");
        onPreferenceChange(dailyMaxPreference, savedDailyMax);

        Preference languagePreference = findPreference(getString(R.string.KEY_PREFERENCE_LANGUAGE));
        languagePreference.setOnPreferenceChangeListener(this);

        SharedPreferences sharedPrefLang = PreferenceManager.getDefaultSharedPreferences(this);
        String savedLang = sharedPrefLang.getString(languagePreference.getKey(), "");
        onPreferenceChange(languagePreference, savedLang);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        preference.setSummary(value.toString());
        return false;
    }

    private void setLocale(String lang) {
        appLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = appLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this,ActivitySettings.class);
        startActivity(refresh);
    }
}

