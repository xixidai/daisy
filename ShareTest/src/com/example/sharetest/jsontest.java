package com.example.sharetest;

import java.io.IOException;

import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.net.RequestListener;

import android.app.Activity;
import android.os.Bundle;

public class jsontest extends Activity implements RequestListener{
	
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weibolist);
	}
	// (String screen_name, long since_id, long max_id, int count, int page,
		// boolean base_app, FEATURE feature, boolean trim_user, RequestListener
		// listener)
	@Override
	public void onComplete(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(WeiboException arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onIOException(IOException arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
