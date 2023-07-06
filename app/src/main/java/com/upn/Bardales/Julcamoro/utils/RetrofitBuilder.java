package com.upn.Bardales.Julcamoro.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    public static Retrofit build() {
        return new Retrofit.Builder()
                .baseUrl("https://648577a6a795d24810b6fb31.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


}
