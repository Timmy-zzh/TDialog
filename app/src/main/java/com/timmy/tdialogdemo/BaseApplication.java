package com.timmy.tdialogdemo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * @author Roshine
 * @date 2020/8/24 21:30
 * @desc
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
