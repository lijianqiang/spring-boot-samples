package com.spring.boot.bean;

import java.util.List;

import com.spring.boot.entity.domain.Foo;

public class MultiDatabase {
	private List<Foo> master;

	private List<Foo> slave;

	public List<Foo> getMaster() {
		return master;
	}

	public void setMaster(List<Foo> master) {
		this.master = master;
	}

	public List<Foo> getSlave() {
		return slave;
	}

	public void setSlave(List<Foo> slave) {
		this.slave = slave;
	}

}
