package com.openplan.server.enums;

import java.util.ArrayList;
import java.util.List;

public enum PlacerKey {

	ENABLE("enable"),

	LEVEL("leval"),

	PROVINCE_NO("provinceNo"),

	CITY_NO("cityNo"),

	REGION_NO("regionNo"),

	NAME("name"),

	;

	private String value;

	public String getValue() {
		return value;
	}

	private PlacerKey(String value) {
		this.value = value;
	}

	public static List<String> getValueList() {
		List<String> list = new ArrayList<String>();
		for (PlacerKey e : PlacerKey.values()) {
			list.add(e.getValue());
		}
		return list;
	}

}
