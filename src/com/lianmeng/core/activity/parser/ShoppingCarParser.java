package com.lianmeng.core.activity.parser;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.lianmeng.core.activity.vo.Cart;
import com.lianmeng.core.framework.util.Logger;

public class ShoppingCarParser extends BaseParser<Cart> {
	protected static final String TAG = "ShoppingCarParser";
	@Override
	public Cart parseJSON(String paramString) throws JSONException {
		if(checkResponse(paramString)!=null){
			Cart cart= new Cart();
			
		
			JSONObject jsonObject = new JSONObject(paramString);
			Logger.d(TAG, paramString);
			String cartstr = jsonObject.getString("DATA_INFO");
			cart =JSON.parseObject(cartstr, Cart.class);
			
			return cart;
		}
		return null;
	}
	
}
