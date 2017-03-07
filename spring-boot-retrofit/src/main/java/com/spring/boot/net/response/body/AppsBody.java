package com.spring.boot.net.response.body;

import java.util.ArrayList;
import java.util.List;

public class AppsBody {
    
    private List<AppItem> apps;
    
    private boolean is_manager;
    
    public AppsBody() {
    	this.is_manager = false;
		this.apps = new ArrayList<AppItem>();
	}

    public List<AppItem> getApps() {
        return apps;
    }

    public void setApps(List<AppItem> apps) {
        this.apps = apps;
    }

    public boolean isIs_manager() {
        return is_manager;
    }

    public void setIs_manager(boolean is_manager) {
        this.is_manager = is_manager;
    }
    
}
