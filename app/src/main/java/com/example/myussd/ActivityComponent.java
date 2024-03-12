

package com.example.myussd;

import com.example.myussd.ActivityScope;
import com.example.myussd.ActivityModule;
import com.example.myussd.MainActivity;

import dagger.Component;
import dagger.Module;


@ActivityScope
@Component( modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);


}