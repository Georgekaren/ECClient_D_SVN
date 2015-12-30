package com.lianmeng.core.activity.parser;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.lianmeng.core.activity.vo.VersionVo;

/**
 * 版本解析器
 * 
 * @author liu
 * 
 */
public class VersionParser extends BaseParser<VersionVo> {

	@Override
	public VersionVo parseJSON(String paramString) throws JSONException {
		if (!TextUtils.isEmpty(checkResponse(paramString))) {
			JSONObject jobj = new JSONObject(paramString);
			//JSONObject jobDataInfo=jobj.getJSONObject("DATA_INFO");
			String version = jobj.getString("DATA_INFO");
			return JSON.parseObject(version, VersionVo.class);
		}
		return null;
	}

}
