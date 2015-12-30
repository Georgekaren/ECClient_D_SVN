package com.lianmeng.core.activity.parser;


import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.lianmeng.core.activity.vo.ProductDetail;

public class ProductDetailParser extends BaseParser<ProductDetail> {
	@Override
	public ProductDetail parseJSON(String paramString) throws JSONException {
		if(super.checkResponse(paramString)!=null){
			JSONObject jsonObject = new JSONObject(paramString);
			String product = jsonObject.getString("DATA_INFO");
			ProductDetail productDetail = JSON.parseObject(product,ProductDetail.class);
			return productDetail;
		}
		return null;
	}

}
