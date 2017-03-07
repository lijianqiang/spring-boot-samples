package com.spring.boot.net.response.body;

import java.util.ArrayList;
import java.util.List;

public class AppItem {
	private String app_name;
	
	private List<String> model_name;
	
	public AppItem() {
		this.model_name = new ArrayList<String>();
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public List<String> getModel_name() {
		return model_name;
	}

	public void setModel_name(List<String> model_name) {
		this.model_name = model_name;
	}
	

}
