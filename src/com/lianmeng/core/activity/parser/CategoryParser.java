package com.lianmeng.core.activity.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.lianmeng.core.activity.vo.CategoryVo;





public class CategoryParser extends BaseParser<List<CategoryVo>> {

	@Override
	public List<CategoryVo> parseJSON(String paramString) throws JSONException {
		JSONObject obj = new JSONObject(paramString);
		String str = obj.getString("DATA_INFO");
		return JSON.parseArray(str, CategoryVo.class);
	}

	

}
