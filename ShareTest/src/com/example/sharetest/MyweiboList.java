package com.example.sharetest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sharetest.adapter.WeiboAdapter;
import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.api.StatusesAPI;
import com.weibo.sdk.android.api.WeiboAPI.FEATURE;
import com.weibo.sdk.android.net.RequestListener;

public class MyweiboList extends Activity implements RequestListener {

	ListView lvWeibo;
	WeiboAdapter adapter;
	List<String> list;
	// TextView txlist;

	String screen_name = "快乐的daisya";
	long since_id = 0;
	long max_id = 0;
	int count = 50;
	int page = 1;
	boolean base_app = false;
//	JSONObject jsonobj = null;
//	JSONArray arry;
	FEATURE feature = FEATURE.ALL;
	boolean trim_user = false;

	// (String screen_name, long since_id, long max_id, int count, int page,
	// boolean base_app, FEATURE feature, boolean trim_user, RequestListener
	// listener)

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weibolist);

		// txlist=(TextView)findViewById(R.id.txlist);

		lvWeibo = (ListView) findViewById(R.id.lvWeibo);

		StatusesAPI api = new StatusesAPI(MainActivity.access_token);
		api.userTimeline(screen_name, since_id, max_id, count, page, base_app,
				feature, trim_user, this);

		//

	}

	@Override
	public void onComplete(String response) {
		list = extractWeiboTitle(response);
		adapter = new WeiboAdapter(this, list);
		runOnUiThread(new Runnable() {

			@Override
			public void run() {

				// txlist.setText(content);
				lvWeibo.setAdapter(adapter);
			}
		});
	}

	// TODO Auto-generated method stub

	@Override
	public void onIOException(IOException e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onError(WeiboException e) {
		// TODO Auto-generated method stub

	}

	private List<String> extractWeiboTitle(String json) {
		List<String> result = new ArrayList<String>();
		try {
			JSONObject j = new JSONObject(json);
			JSONArray arr = j.getJSONArray("statuses");

			JSONObject jitem = null;
			for (int i = 0; i < arr.length(); i++) {
//				jitem = arr.getJSONObject(i);
				jitem=(JSONObject)arr.opt(i);
				result.add(jitem.getString("text"));
			}

		} catch (Exception e) {

		}
		return result;
	}
}
