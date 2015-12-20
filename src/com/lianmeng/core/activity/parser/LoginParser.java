package com.lianmeng.core.activity.parser;

import org.json.JSONException;
import org.json.JSONObject;

import com.lianmeng.core.activity.vo.UserInfo;


public class LoginParser extends BaseParser<UserInfo> {

	@Override
	public UserInfo parseJSON(String paramString) throws JSONException {
		if (super.checkResponse(paramString) != null) {
			UserInfo localUserInfo = new UserInfo();
			JSONObject jsonObject = new JSONObject(paramString).getJSONObject("userinfo");
			String userid = jsonObject.getString("userId");
			String usersession = jsonObject.getString("usersession");
			localUserInfo.userId = userid;
			localUserInfo.usersession = usersession;
			return localUserInfo;
		}
		return new UserInfo();
	}

}
