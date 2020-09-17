package com.vvm.sh.di;


import com.vvm.sh.api.ApiConstantes;
import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.SegurancaTrabalhoApi;
import com.vvm.sh.util.interceptores.WebServiceInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RedeModule {


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        WebServiceInterceptor webServiceInterceptor = new WebServiceInterceptor();


        OkHttpClient client = new OkHttpClient.Builder()

                .addInterceptor(loggingInterceptor)

                .addInterceptor(webServiceInterceptor)

                //establish connection to server
                .connectTimeout(ApiConstantes.CONNECTION_TIMEOUT, TimeUnit.SECONDS)

                //time between each byte read from the server
                .readTimeout(ApiConstantes.READ_TIMEOUT, TimeUnit.SECONDS)

                //time between each byte sent to server
                .writeTimeout(ApiConstantes.WRITE_TIMEOUT, TimeUnit.SECONDS)

                .retryOnConnectionFailure(false)
                //.addInterceptor(new UrlInterceptor())
                .build();


        //Timber.d("Providing OkHttpClient: " + client);
        return client;
    }




    @Provides
    @Singleton
    @Named("SegurancaAlimentarRetrofit")
    Retrofit provideSegurancaAlimentarRetrofit(OkHttpClient okHttpClient) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SegurancaAlimentarApi.URL_BASE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Timber.d("Providing retrofit: " + retrofit);
        return retrofit;
    }


    @Provides
    @Singleton
    @Named("SegurancaTrabalhoRetrofit")
    Retrofit provideSegurancaTrabalhoRetrofit(OkHttpClient okHttpClient) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SegurancaTrabalhoApi.URL_BASE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Timber.d("Providing retrofit: " + retrofit);
        return retrofit;
    }





    @Provides
    @Singleton
    SegurancaAlimentarApi provideSegurancaAlimentarApiInterface(@Named("SegurancaAlimentarRetrofit") Retrofit retrofit) {

        SegurancaAlimentarApi api = retrofit.create(SegurancaAlimentarApi.class);

        //Timber.d("Providing FlickrApi: " + api);
        return api;
    }


    @Provides
    @Singleton
    SegurancaTrabalhoApi provideSegurancaTrabalhoApiInterface(@Named("SegurancaTrabalhoRetrofit") Retrofit retrofit) {

        SegurancaTrabalhoApi api = retrofit.create(SegurancaTrabalhoApi.class);

        //Timber.d("Providing FlickrApi: " + api);
        return api;
    }

}