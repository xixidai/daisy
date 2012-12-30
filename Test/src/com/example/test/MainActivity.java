package com.example.test;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {
	 Button FX;
	 ImageView SImg;
	 EditText Input;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		FX=(Button)findViewById(R.id.FX);
		FX.setOnClickListener(this);
		
		SImg=(ImageView)findViewById(R.id.SImg);
		Input=(EditText)findViewById(R.id.Input);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (R.id.FX != 0){
		
		new AlertDialog.Builder(this)  
		.setTitle("分享到")  
		.setMultiChoiceItems(new String[] {"选项1","选项2","选项3","选项4"}, null, null)  
		.setPositiveButton("确定", null)                  
		.setNegativeButton("取消", null)  
		.show();  
		}
	}

	
	

}
