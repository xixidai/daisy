package com.example.sharetest.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sharetest.R;

public class WeiboAdapter extends BaseAdapter {

	private Context context;
	private List<String> list;
	private LayoutInflater inflater;
	public WeiboAdapter(Context context, List<String> list) {
		this.context = context;
		this.list = list;
		this.inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			v = inflater.inflate(R.layout.weibo_item, parent, false);
		}
		WeiboHolder holder = (WeiboHolder) v.getTag();
		if (holder == null) {
			holder = new WeiboHolder();
			holder.tvItem = (TextView) v.findViewById(R.id.tvItem);
			v.setTag(holder);
		}
		
		String item = list.get(position);
		if (item != null) {
			holder.tvItem.setText(item);
		}
		
		return v;
	}

}
