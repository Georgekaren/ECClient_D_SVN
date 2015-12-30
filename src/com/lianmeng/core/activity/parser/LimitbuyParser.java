package com.lianmeng.core.activity.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.lianmeng.core.activity.vo.LimitBuyVo;



public class LimitbuyParser extends BaseParser<List<LimitBuyVo>> {

	@Override
	public List<LimitBuyVo> parseJSON(String paramString) throws JSONException {
		if(super.checkResponse(paramString)!=null){
			JSONObject jsonObject = new JSONObject(paramString);
			String productlist = jsonObject.getString("DATA_INFO");
			return JSON.parseArray(productlist, LimitBuyVo.class);
		}else{
		return null;
		}
	}

}
