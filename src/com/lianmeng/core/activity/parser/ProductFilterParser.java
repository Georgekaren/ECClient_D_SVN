package com.lianmeng.core.activity.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.lianmeng.core.activity.vo.FilterCategory;

public class ProductFilterParser extends BaseParser<List<FilterCategory>> {

	@Override
	public List<FilterCategory> parseJSON(String paramString)
			throws JSONException {
		if (super.checkResponse(paramString) != null) {
			JSONObject jsonObject = new JSONObject(paramString);
			String filter = jsonObject.getString("PUBS_FINALS");
			return JSON.parseArray(filter, FilterCategory.class);
		}else{

		return null;
		}
	}

	

}
