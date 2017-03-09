package com.spring.boot.retrofit;

import java.util.List;

import com.spring.boot.entity.domain.Foo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ClientRetrofit {
	@GET("mysql")
	Call<List<Foo>> listFoo();
}
