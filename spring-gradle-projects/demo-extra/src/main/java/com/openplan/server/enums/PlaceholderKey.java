package com.openplan.server.enums;

import java.util.ArrayList;
import java.util.List;

public enum PlaceholderKey {
	ENABLE("enable"),

	NAME("name"),

	PLACER_ID("placer_id"),

	PROVINCE_NO("province_no"),

	CITY_NO("city_no"),

	REGION_NO("region_no"),

	;

	private String value;

	public String getValue() {
		return value;
	}

	private PlaceholderKey(String value) {
		this.value = value;
	}

	public static List<String> getValueList() {
		List<String> list = new ArrayList<String>();
		for (PlaceholderKey e : PlaceholderKey.values()) {
			list.add(e.getValue());
		}
		return list;
	}
}
