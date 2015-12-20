package com.lianmeng.core.activity.parser;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.lianmeng.core.activity.vo.Cart;

public class ShoppingCarParser extends BaseParser<Cart> {

	@Override
	public Cart parseJSON(String paramString) throws JSONException {
		if(checkResponse(paramString)!=null){
			Cart cart= new Cart();
			
		
			JSONObject jsonObject = new JSONObject(paramString);

			String cartstr = jsonObject.getString("cart");
			cart =JSON.parseObject(cartstr, Cart.class);
			
			return cart;
		}
		return null;
	}
	
}
