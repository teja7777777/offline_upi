package com.example.myussd;




        import android.app.Activity;
        import android.content.Context;

        import com.google.android.play.core.appupdate.AppUpdateManager;
        import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
        import com.example.myussd.ActivityContext;
        import com.example.myussd.ActivityScope;

        import dagger.Module;
        import dagger.Provides;


@Module
        public class ActivityModule {

        private Activity mActivity;

        public ActivityModule(Activity activity) {
        mActivity = activity;
        }

        @Provides
        @ActivityContext
        Context provideContext() {
        return mActivity;
        }

        @Provides
        Activity provideActivity() {
        return mActivity;
        }

        @Provides
        @ActivityScope
        public AppUpdateManager provideAppUpdateManager() {
        return AppUpdateManagerFactory.create(mActivity);
        }
        }
