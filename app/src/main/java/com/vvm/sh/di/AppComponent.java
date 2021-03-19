package com.vvm.sh.di;

import android.app.Application;

import com.vvm.sh.App;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;


@Singleton
@Component(
        modules = {
                AndroidInjectionModule.class,
                ActivityBuildersModule.class,
                ViewModelFactoryModule.class,
                BaseDadosModule.class,
                RedeModule.class,
                //NetworkModule.class,

        }
)
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}