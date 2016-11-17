package com.example.justjava;

import android.app.Application;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.orm.SugarApp;
import com.orm.SugarContext;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MyApp extends SugarApp {


    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
        Iconify.with(new FontAwesomeModule());

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Avenir.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }


//    @Override
//    public void onTerminate() {
//        super.onTerminate();
//        SugarContext.terminate();
//    }

}
