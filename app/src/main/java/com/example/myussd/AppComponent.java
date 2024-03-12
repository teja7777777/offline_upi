package com.example.myussd;





import com.example.myussd.ApplicationModule;
import com.example.myussd.USSDModule;
import com.example.myussd.MainFragment;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component (modules = {ApplicationModule.class, USSDModule.class})
public interface AppComponent {

    void inject(MainFragment fragment);

}
