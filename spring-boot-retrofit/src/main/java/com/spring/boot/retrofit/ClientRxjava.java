package com.spring.boot.retrofit;

import java.util.List;

import com.spring.boot.entity.domain.Foo;
import com.spring.boot.net.request.AppsRequest;
import com.spring.boot.net.response.ApiResponse;
import com.spring.boot.net.response.body.AppsBody;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface ClientRxjava {
	
	@GET("mysql")
	Observable<List<Foo>> listFoo();
	
	@POST("/authority/apps")
	Observable<ApiResponse<AppsBody>> getApps(@Body AppsRequest appsRequest);
}
