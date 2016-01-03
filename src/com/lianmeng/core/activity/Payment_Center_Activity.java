package com.lianmeng.core.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lianmeng.core.activity.R;
import com.lianmeng.core.activity.adapter.PaymentCentUpdateAdapter;
import com.lianmeng.core.activity.parser.PaymentCenterParser;
import com.lianmeng.core.activity.vo.Address;
import com.lianmeng.core.activity.vo.AddressDetail;
import com.lianmeng.core.activity.vo.CartProduct;
import com.lianmeng.core.activity.vo.CheckoutAddup;
import com.lianmeng.core.activity.vo.Delivery;
import com.lianmeng.core.activity.vo.InvoiceInfo;
import com.lianmeng.core.activity.vo.Payment;
import com.lianmeng.core.activity.vo.RequestVo;
import com.lianmeng.core.framework.util.Logger;
import com.lianmeng.core.framework.util.SysU;

import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Payment_Center_Activity extends BaseWapperActivity {
	private static final String TAG = null;
	private List<CartProduct> productlistInfo;
	private ListView payment_product_list;
	
	//收货地址
	private TextView textAdress2;
	private TextView textAdress3;
	private TextView textAdress4;
	//支付方式
	private TextView payment_payValue_text;
	//送货时间
	private TextView payment_sendTimeValue_text;
	//发票
	private TextView payment_invoiceValue_text;
	//留言
	private TextView payment_remarkView_text;
	//数量总计
	private TextView shopcar_total_buycount_text;
	//赠送总积分
	private TextView shopcar_total_bonus_text;
	//金额总计
	private TextView shopcar_total_money_text;
	//提交按钮
	private TextView ordr_submit_bottom_text;
	
	@Override
	protected void onHeadRightButton(View v) {
		finish();
		Intent intent = new Intent(this,PaySelectMainActivity.class);
		intent.putExtra("userId", SysU.USERID);
		String prodIds="[";
		for(int i=0;i<productlistInfo.size();i++){
			prodIds=prodIds+"\""+productlistInfo.get(i).id+"\",";
		}
		prodIds=prodIds+"]";
		intent.putExtra("prodId",prodIds);
		startActivity(intent);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ordr_submit_bottom_text:
			finish();
			//Intent intent = new Intent(this,OrdrSubmitOkActivity.class);
			//startActivity(intent);
			Intent intent = new Intent(this,PaySelectMainActivity.class);
			intent.putExtra("userId", SysU.USERID);
			String prodIds="[";
			for(int i=0;i<productlistInfo.size();i++){
				prodIds=prodIds+"\""+productlistInfo.get(i).id+"\",";
			}
			prodIds=prodIds+"]";
			intent.putExtra("prodId",prodIds);
			startActivity(intent);
			break;
		case R.id.select_address:
 			intent = new Intent(this,SelectAddressActivity.class);
			startActivityForResult(intent, 2000);
			break;
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 2000 && resultCode == 200) {
			AddressDetail parcelableExtra = data.getParcelableExtra("address");
			textAdress2.setText(parcelableExtra.getName());
			textAdress3.setText(parcelableExtra.getAreaName());
			textAdress4.setText(parcelableExtra.getAreadetail());
			Logger.d(TAG, "已选择地址" + parcelableExtra.toString());
		}
			
	}

	@Override
	protected void findViewById() {
		payment_product_list = (ListView) this.findViewById(R.id.payment_product_list);
		ordr_submit_bottom_text = (TextView) this.findViewById(R.id.ordr_submit_bottom_text);
		// 收件人信息
		textAdress2 = (TextView) this.findViewById(R.id.textAdress2);//收件人
		textAdress3 = (TextView) this.findViewById(R.id.textAdress3);//收件人地址
		textAdress4 = (TextView) this.findViewById(R.id.textAdress4);//详细地址
		
		
		//支付方式
		payment_payValue_text = (TextView) this.findViewById(R.id.payment_payValue_text);
		//送货时间
		payment_sendTimeValue_text = (TextView) this.findViewById(R.id.payment_sendTimeValue_text);
		//发票
		payment_invoiceValue_text = (TextView) this.findViewById(R.id.payment_invoiceValue_text);
		//留言
		payment_remarkView_text = (TextView) this.findViewById(R.id.payment_remarkView_text);
		//数量总计
		shopcar_total_buycount_text = (TextView) this.findViewById(R.id.shopcar_total_buycount_text);
		//赠送总积分
		shopcar_total_bonus_text = (TextView) this.findViewById(R.id.shopcar_total_bonus_text);
		//金额总计
		shopcar_total_money_text = (TextView) this.findViewById(R.id.shopcar_total_money_text);
		findViewById(R.id.select_address).setOnClickListener(this);
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.my_payment_center_activity);
		setHeadRightVisibility(View.VISIBLE);
		setHeadRightText(R.string.uphandcheckout);
		setTitle(R.string.check_out);
	}

	@Override
	protected void processLogic() {
		RequestVo vo = new RequestVo();
		vo.context = this;
		vo.requestDataMap = new HashMap<String, String>();
		vo.requestDataMap.put("sku", "1200001:3|1200004:2");
		
		String inmapData="{\"ServiceName\":\"srvOrderManagerService\" , \"Data\":{\"ACTION\":\"CHECKBASEORDER\",\"userId\":\""+SysU.USERID+"\"}}";
		HashMap<String, String> prodMap = new HashMap<String, String>();
		prodMap.put("JsonData", inmapData);
		vo.requestDataMap = prodMap;
		vo.requestUrl = R.string.sysRequestServLet;
		vo.jsonParser = new PaymentCenterParser();
		getDataFromServer(vo, new DataCallback<Map<String,Object>>() {
			@Override
			public void processData(Map<String,Object> paramObject, boolean paramBoolean) {
				if(paramObject!=null){
					Address address_info = (Address) paramObject.get("addressInfo");
					Payment paymentInfo = (Payment) paramObject.get("paymentInfo");
					Delivery deliveryInfo = (Delivery) paramObject.get("deliveryInfo");
					InvoiceInfo invoiceInfo = (InvoiceInfo) paramObject.get("invoiceInfo");
				    productlistInfo = (List<CartProduct>) paramObject.get("productlistInfo");
					List<String> checkout = (List<String>)paramObject.get("checkout");
					CheckoutAddup checkoutAdd = (CheckoutAddup) paramObject.get("checkoutAdd");
					// 收件人信息
					textAdress2.setText(address_info.getName()+"");
					textAdress3.setText(address_info.getAddress_area()+"");
					textAdress4.setText(address_info.getAddress_detail()+"");
					//支付方式
					if(paymentInfo.getType()==1){
						payment_payValue_text.setText("货到付款");
					}else if(paymentInfo.getType()==2){
						payment_payValue_text.setText("货到POS机");
					}else if(paymentInfo.getType()==3){
						payment_payValue_text.setText("支付宝");
					}
					//送货时间
					if(deliveryInfo.getType()==1){
						payment_sendTimeValue_text.setText("周一至周五送货");
					}else if(deliveryInfo.getType()==2){
						payment_sendTimeValue_text.setText("双休日及公众假期送货");
					}else if(deliveryInfo.getType()==3){
						payment_sendTimeValue_text.setText("时间不限，工作日双休日及公众假期均可送货");
					}
					//发票
					payment_invoiceValue_text.setText(invoiceInfo.getTitle()+"");
					//留言
					payment_remarkView_text.setText(invoiceInfo.getContent()+"");
					//数量总计
					shopcar_total_buycount_text.setText(checkoutAdd.getTotal_count()+"");
					//总积分
					shopcar_total_bonus_text.setText(checkoutAdd.getTotal_point()+"");
					//金额总计
					shopcar_total_money_text.setText(checkoutAdd.getTotal_price()+"");
					
					PaymentCentUpdateAdapter adapter = 
							new PaymentCentUpdateAdapter(Payment_Center_Activity.this,payment_product_list,productlistInfo);
					payment_product_list.setAdapter(adapter);
				}
			}
		});

	}

	@Override
	protected void setListener() {
		ordr_submit_bottom_text.setOnClickListener(this);

	}
}
