package com.spring.boot.retrofit;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiManager {

	public static final String SERVER_URL = "http://localhost:9090";

	public static ClientRetrofit createOrigin() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(SERVER_URL)
				.client(buildClient())
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		return retrofit.create(ClientRetrofit.class);
	}
	
	public static ClientRxjava createRxjava() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(SERVER_URL)
				.client(buildClient())
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		return retrofit.create(ClientRxjava.class);
	}

	public static OkHttpClient buildClient() {
		HttpLoggingInterceptor log = new HttpLoggingInterceptor();
		log.setLevel(HttpLoggingInterceptor.Level.BODY);
		Interceptor interceptor = new Interceptor() {
			@Override
			public Response intercept(Chain chain) throws IOException {
				Request request = chain.request().newBuilder()
						.addHeader("Content-Type", "application/json; charset=UTF-8")
						.addHeader("Connection", "keep-alive")
						.addHeader("Accept", "*/*")
						.build();
				return chain.proceed(request);
			}

		};
		OkHttpClient httpClient = new OkHttpClient.Builder()
				.addInterceptor(interceptor)
				.addNetworkInterceptor(log)
				.connectTimeout(3, TimeUnit.SECONDS)
				.build();

		return httpClient;
	}
}
