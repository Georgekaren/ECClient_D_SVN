package com.lianmeng.core.activity.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.lianmeng.core.activity.vo.HomeBannerVo;

/**
 * 首页baner 解析
 * @author liu
 *
 */
public class HomeBannerParser extends BaseParser<List<HomeBannerVo>>{

	@Override
	public List<HomeBannerVo> parseJSON(String paramString) throws JSONException {
		if (!TextUtils.isEmpty(checkResponse(paramString))) {
			JSONObject j = new JSONObject(paramString);
 			return JSON.parseArray(j.getString("DATA_INFO"), HomeBannerVo.class);
		}
		return null;
	}

}
