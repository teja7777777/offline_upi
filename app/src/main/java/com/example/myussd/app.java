package com.example.myussd;





import android.app.Application;

import com.example.myussd.AppComponent;
import com.example.myussd.DaggerAppComponent;
import com.example.myussd.USSDModule;

import timber.log.Timber;


public class app extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        appComponent = DaggerAppComponent.builder()
//                .applicationModule(new ApplicationModule(this))
                .uSSDModule(new USSDModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
