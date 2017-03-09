package com.spring.boot.retrofit;

import java.util.List;

import com.spring.boot.entity.domain.Foo;

import retrofit2.http.GET;
import rx.Observable;

public interface ClientRxjava {
	
	@GET("mysql")
	Observable<List<Foo>> listFoo();
}
