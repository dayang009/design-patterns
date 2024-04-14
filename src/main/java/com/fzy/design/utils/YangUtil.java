package com.fzy.design.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class YangUtil {

	public static Gson getGson() {
		return new GsonBuilder().disableHtmlEscaping()
			.setPrettyPrinting()
			.serializeNulls()
			.setDateFormat("yyyy-MM-dd HH:mm:ss")
			.create();
	}

}
