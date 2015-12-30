package com.lianmeng.core.activity;

import java.util.HashMap;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.lianmeng.core.activity.R;
import com.lianmeng.core.activity.adapter.SearchAdapter;
import com.lianmeng.core.activity.parser.SearchRecommondParser;
import com.lianmeng.core.activity.vo.RequestVo;
import com.lianmeng.core.framework.util.CommonUtil;
import com.lianmeng.core.framework.util.Constant;

public class SearchActivity extends BaseWapperActivity {
	private EditText keyWordEdit;
 	private ListView hotWordsLv;
	private String [] search;
	
	
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){ 
		case R.id.searchTv:
			 
			
			
//			RequestVo vo = new RequestVo();
//			vo.context = SearchActivity.this;
//			HashMap map = new HashMap();
//			map.put("keyword", keyWord);
//			map.put("page", 1);
//			map.put("pageNum", 10);
//			
//			vo.requestDataMap = map;
//			vo.requestUrl = R.string.search;
//			vo.jsonParser = new SearchParser();
//			super.getDataFromServer(vo, new DataCallback<List<Product>>() {
//
//				@Override
//				public void processData(List<Product> paramObject,
//						boolean paramBoolean) {
//					SearchResultAdapter adapter= new SearchResultAdapter(context, paramObject);
//					
//				}
//			});
			
			
			break;
		}
	}
	
	@Override
	protected void onHeadRightButton(View v) {
		String keyWord = keyWordEdit.getText().toString();
		if(keyWord==null||"".equals(keyWord)){
			CommonUtil.showInfoDialog(SearchActivity.this, getString(R.string.searchProdMsgInputHiteNameMsg));
			return;
		}
		Intent intent = new Intent(SearchActivity.this,SearchProductListActivity.class);
		intent.putExtra("keyword", keyWord);
		startActivity(intent);
 	}

	@Override
	protected void findViewById() {
 		hotWordsLv = (ListView) findViewById(R.id.hotWordsLv);
		keyWordEdit = (EditText) findViewById(R.id.keyWordEdit);
		Intent intent = new Intent();
		
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.search_activity);
		setHeadLeftVisibility(View.INVISIBLE);
		setTitle(getString(R.string.searchProdTitleButtonNameMsg));
		setHeadRightText(getString(R.string.searchProdTitleButtonNameMsg));
		setHeadRightVisibility(View.VISIBLE);
		selectedBottomTab(Constant.SEARCH);
	}

	@Override
	protected void processLogic() {
		showProgressDialog();
		RequestVo vo = new RequestVo();
		vo.context = SearchActivity.this;
		vo.jsonParser = new SearchRecommondParser();
		vo.requestUrl = R.string.sysRequestServLet;
		HashMap<String, String> prodMap = new HashMap<String, String>();
		String inmapData="{\"ServiceName\":\"extProdManagerService\" , \"Data\":{\"ACTION\":\"QRYPRODFINALSLIST\",\"finalkeyword\":\"SUBBRAND\"}}";
		prodMap.put("JsonData", inmapData);
		vo.requestDataMap = prodMap;
		
		super.getDataFromServer(vo, new DataCallback<String []>() {

			@Override
			public void processData(String[] paramObject, boolean paramBoolean) {
				if(paramObject!=null){
					search = paramObject;
					SearchAdapter adapter = new SearchAdapter(context,paramObject);
					hotWordsLv.setAdapter(adapter);
					closeProgressDialog();
				}
			}
		});
	}
	
	
	
	@Override
	protected void setListener() {
 		hotWordsLv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			}
		});
	}

}
