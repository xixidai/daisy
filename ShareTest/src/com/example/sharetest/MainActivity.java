package com.example.sharetest;

import java.text.SimpleDateFormat;


import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.Weibo;
import com.weibo.sdk.android.WeiboAuthListener;
import com.weibo.sdk.android.WeiboDialogError;
import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.keep.AccessTokenKeeper;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class MainActivity extends Activity implements OnClickListener {

   public static Oauth2AccessToken access_token;
	private final String appKey="3155001609";
	private final String redirectUrl="http://weibo.com/u/1787300187";
	
	EditText txview;
	Button btnSh,btnGetList;
	private Weibo mweibo;
	ImageView imgPhoto;
//	String uri="Home/Desktop/face10.jpg";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        txview=(EditText)findViewById(R.id.txview);
        
        btnSh=(Button)findViewById(R.id.btnSh);
        btnSh.setOnClickListener(this);

        
        //获取微博列表
        btnGetList=(Button)findViewById(R.id.btnGetList);
        btnGetList.setOnClickListener(this);
        
        mweibo = Weibo.getInstance(appKey, redirectUrl);
        
//        imgPhoto =(ImageView)findViewById(R.id.imgPhoto);
        
//        imgPhoto.setImageResource(R.drawable.face10);
//          imgPhoto.setImageURI(Uri.parse(uri));
        
         }
    @Override
	public void onClick(View v) {
    	int viewID=v.getId();
    	MainActivity.access_token=AccessTokenKeeper.readAccessToken(this);
    	switch (viewID) {
    	
		case R.id.btnSh:
		if(MainActivity.access_token.isSessionValid()){
	    Intent share=new Intent(MainActivity.this,ShareActivity.class);
	   //将数据传过去
	    Bundle bundle=new Bundle();
	    bundle.putString("name", txview.getText().toString());
	    share.putExtras(bundle);
	    startActivity(share);
	    	}
	    	else{
		    mweibo.authorize(MainActivity.this, new myAuthDialogListener());    	
		 }
			break;
//跳转至微博列表
		case R.id.btnGetList:
			if(MainActivity.access_token.isSessionValid()){
				Intent wlist =new Intent(MainActivity.this,MyweiboList.class);
				startActivity(wlist);
				
			}
			else{
				 mweibo.authorize(MainActivity.this, new myAuthDialogListener());  
			}
			
			
		}
    	
    	
    }

   
    	class myAuthDialogListener implements WeiboAuthListener{
//
	@Override
	public void onComplete(Bundle values) {
//		
		String token=values.getString("access_token");
		String expires_in = values.getString("expires_in");
		MainActivity.access_token = new Oauth2AccessToken(token, expires_in);
		if(MainActivity.access_token.isSessionValid()){
//			String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
//			.format(new java.util.Date(MainActivity.access_token.getExpiresTime()));
//			
//			txview.setText("��֤�ɹ�:\r\n access_token"+token+"\r\n"+expires_in+"\r\n��Ч��"+date);
			AccessTokenKeeper.keepAccessToken(MainActivity.this, access_token);
//			Toast.makeText(MainActivity.this, "认证成功", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onWeiboException(WeiboException e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(WeiboDialogError e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCancel() {
		 
		
	}
	
}

	

    
    
}
