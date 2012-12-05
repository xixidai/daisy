package com.example.sharetest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.sharetest.adapter.WeiboAdapter;
import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.api.StatusesAPI;
import com.weibo.sdk.android.api.WeiboAPI.FEATURE;
import com.weibo.sdk.android.net.RequestListener;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class jsontest extends Activity implements RequestListener{
	
	WeiboAdapter adapter;
	ListView lvWeibo;
	List<String> list;
	
	// (String screen_name, long since_id, long max_id, int count, int page,
			// boolean base_app, FEATURE feature, boolean trim_user, RequestListener
			// listener)
	String screen_name="快乐的Daisya";
	long since_id =0;
	long max_id=0;
	int count=100;
	int page=2;
	boolean base_app=false;
	FEATURE feature=FEATURE.ALL;
	boolean trim_user =false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weibolist);
		
		lvWeibo=(ListView)findViewById(R.id.lvWeibo);
		StatusesAPI api=new StatusesAPI(MainActivity.access_token);
		api.userTimelineIds(screen_name, since_id, max_id, count, page, base_app, feature, this);
			
		
	}
	
	@Override
	public void onComplete(String response) {
		list=getJASON(response);
		adapter=new WeiboAdapter(this,list);
		runOnUiThread(new Thread(){
			public void run(){
				lvWeibo.setAdapter(adapter);
			}
		});
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
	
public List<String> getJASON(String json){
	List<String> ret = new ArrayList();
try{
	JSONObject j= new JSONObject(json);
	JSONArray arr=j.getJSONArray("statuses");

	JSONObject jitem =null;
	for(int i=0;i<=arr.length();i++){
		jitem=(JSONObject)arr.opt(i);
		ret.add(jitem.getString("text"));
	}
}catch(Exception e){
	
}
	
	
	return ret;
	
}
	
}
