package org.techtown.audio.gilneungsassignment2;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class BaseContextWrapper extends ContextWrapper {
    public BaseContextWrapper(Context base) {
        super(base);
    }
    public static Context wrap(Context context, Locale newLocale) {
        Resources res = context.getResources();
        Configuration configuration = res.getConfiguration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.setLocale(newLocale);

            LocaleList localeList = new LocaleList(newLocale);
            LocaleList.setDefault(localeList);
            configuration.setLocales(localeList);
            configuration.setLocale(newLocale);

            context.getApplicationContext().createConfigurationContext(configuration);
            context = context.createConfigurationContext(configuration);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(newLocale);
            context.getApplicationContext().createConfigurationContext(configuration);
            context = context.createConfigurationContext(configuration);
        } else {
            configuration.locale = newLocale;
            res.updateConfiguration(configuration, res.getDisplayMetrics());
        }
        context.getResources().updateConfiguration(configuration, context.getApplicationContext().getResources().getDisplayMetrics());
        context.getApplicationContext().getResources().updateConfiguration(configuration,context.getApplicationContext().getResources().getDisplayMetrics());
        return new BaseContextWrapper(context);
    }
}