package com.example.sharetest;



import java.io.IOException;

import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.api.StatusesAPI;

import com.weibo.sdk.android.net.RequestListener;


import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ShareActivity extends Activity implements OnClickListener, RequestListener{

	public String mContent="";
	EditText etEdit;
	Button btnClose,btnSend;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.share);
		
		etEdit=(EditText)findViewById(R.id.etEdit);
		btnClose=(Button)findViewById(R.id.btnClose);
		btnSend=(Button)findViewById(R.id.btnSend);
		
		btnClose.setOnClickListener(this);
		btnSend.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		int viewID=v.getId();
		switch (viewID) {
		case R.id.btnClose:
			finish();
			break;
		case R.id.btnSend:
			StatusesAPI api=new StatusesAPI(MainActivity.access_token);
			Bundle bundle=getIntent().getExtras();
			String name=bundle.getString("name");
			etEdit.setText(name);
			this.mContent=etEdit.getText().toString();
			api.update(this.mContent, "90", "90", this);
//			api.userTimeline("快乐的daisya", 0, 0, 20, 1, 0, 0, 0, this);

//		break;
		
		}
		
	}
	@Override
	public void onComplete(String response) {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				Toast.makeText(ShareActivity.this, R.string.weibosdk_send_sucess, Toast.LENGTH_LONG)
						.show();
			}
		});

		this.finish();
		
	}
	@Override
	public void onIOException(IOException e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onError(WeiboException e) {
		// TODO Auto-generated method stub
		
	}
	

}
