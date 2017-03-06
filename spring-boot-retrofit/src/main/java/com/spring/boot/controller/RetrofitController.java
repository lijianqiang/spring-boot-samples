package com.spring.boot.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.boot.entity.domain.Foo;
import com.spring.boot.retrofit.ClientRetrofit;
import com.spring.boot.retrofit.ClientRxjava;
import com.spring.boot.retrofit.RetrofitApiManager;
import com.spring.boot.utils.JsonUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

@Controller
@RequestMapping("/retrofit")
public class RetrofitController {
	
	private static final Logger LOG = LoggerFactory.getLogger(RetrofitController.class);
	
	@RequestMapping
	@ResponseBody
	public String actionIndex() {
		LOG.info("/retrofit");
		List<String> res = new ArrayList<String>(2);
		res.add("/origin");
		res.add("/rxjava");
		return JsonUtil.toJson(res);
	}
	
	@RequestMapping("/origin")
	@ResponseBody
	public String actionOrigin() {
		LOG.info("/retrofit/origin");
		
		ClientRetrofit client = RetrofitApiManager.createOrigin();
		Call<List<Foo>> call = client.listFoo();
		call.enqueue(new Callback<List<Foo>>() {
			
			@Override
			public void onResponse(Call<List<Foo>> arg0, Response<List<Foo>> arg1) {
				LOG.info("retrofit:onResponse:" + JsonUtil.toJson(arg1.body()));
			}
			
			@Override
			public void onFailure(Call<List<Foo>> arg0, Throwable arg1) {
				LOG.error("retrofit:onFailure:", arg1.getMessage());
			}
		});
		
		return "/retrofit/origin finish";
	}
	
	@RequestMapping("/rxjava")
	@ResponseBody
	public String actionRxjava() {
		LOG.info("/retrofit/rxjava");
		ClientRxjava client = RetrofitApiManager.createRxjava();
		Observable<List<Foo>> observable = client.listFoo();
//		observable.subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread()).subscribe(new Observer<List<Foo>>() {
//
//			@Override
//			public void onNext(List<Foo> t) {
//				LOG.info("/retrofit/rxjava:onNext:{}", JsonUtil.toJson(t));
//			}
//
//			@Override
//			public void onError(Throwable e) {
//				LOG.error("/retrofit/rxjava:onError", e);
//			}
//
//			@Override
//			public void onCompleted() {
//				LOG.info("/retrofit/rxjava:onCompleted");
//			}
//		});
		
		observable.subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread()).subscribe(new Subscriber<List<Foo>>() {

			@Override
			public void onNext(List<Foo> t) {
				LOG.info("/retrofit/rxjava:onNext:{}", JsonUtil.toJson(t));
			}

			@Override
			public void onError(Throwable e) {
				LOG.error("/retrofit/rxjava:onError", e);
			}

			@Override
			public void onCompleted() {
				LOG.info("/retrofit/rxjava:onCompleted");
			}
		});
		
		return "/retrofit/rxjava finish";
	}
}
