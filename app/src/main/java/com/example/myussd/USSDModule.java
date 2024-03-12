package com.example.myussd;



        import android.content.Context;

        import com.example.myussd.USSDApi;
        import com.example.myussd.USSDController;

        import java.util.Arrays;
        import java.util.HashMap;
        import java.util.HashSet;

        import javax.inject.Singleton;

        import dagger.Module;
        import dagger.Provides;


@Module
        public class USSDModule {

        private Context context;

        public USSDModule(Context context) {
        this.context = context;
        }

        @Provides
        @Singleton
        public USSDApi provideUSSDApi() {
        return USSDController.getInstance(context);
        }

        @Provides
        @Singleton
        public HashMap<String, HashSet<String>> provideHashMap() {
        HashMap map = new HashMap<>();
        map.put("KEY_LOGIN", new HashSet<>(Arrays.asList("espere", "waiting", "loading", "esperando")));
        map.put("KEY_ERROR", new HashSet<>(Arrays.asList("problema", "problem", "error", "null")));
        return map;
        }


        }
