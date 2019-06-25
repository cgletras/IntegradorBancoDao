package com.leilaodequadrinhos.api.model.task.notification;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitBuilder {

    private static final String URL = "https://url";

    private static final HttpLoggingInterceptor logger =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static final OkHttpClient.Builder okHttp =
            new OkHttpClient.Builder()
                    .addInterceptor(logger);

    private static Retrofit getRetroInstance() {
        return new Retrofit.Builder().baseUrl(URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(okHttp.build()).build();
    }

    private static final Retrofit retrofit = getRetroInstance();

    public static <S> S buildService(Class<S> serviceType){
        return retrofit.create(serviceType);
    }
}