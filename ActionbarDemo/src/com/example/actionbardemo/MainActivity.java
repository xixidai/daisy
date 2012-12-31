package com.example.actionbardemo;



import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ShareActionProvider;

public class MainActivity extends Activity {
	
	ShareActionProvider provider;
	Intent shareIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_ACTION_BAR);
//		setContentView(R.layout.activity_main);
		  final ActionBar actionBar = getActionBar(); 
//		    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		    actionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_CUSTOM);
		    Drawable dr = this.getResources().getDrawable(R.drawable.navigater);
		    actionBar.setBackgroundDrawable(dr);
			updateShareIntent("");

		  
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		super.onCreateOptionsMenu(menu);
		MenuItem item = menu.add("share");
		MenuItem item1=menu.add("show");
	    item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
	    item.setIcon(android.R.drawable.ic_menu_today);
	    item1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
	    item1.setIcon(android.R.drawable.alert_light_frame);
	    provider = new ShareActionProvider(this);
	    item.setActionProvider(provider);
	    provider.setShareHistoryFileName(ShareActionProvider.DEFAULT_SHARE_HISTORY_FILE_NAME);	
	    provider.setShareIntent(shareIntent);
	    
//	    Intent shareIntent = new Intent(Intent.ACTION_SEND);
//	    shareIntent.setType("image/*");
//	    Uri uri = Uri.fromFile(new File(getFilesDir(), "foo.jpg"));
//	    shareIntent.putExtra(Intent.EXTRA_STREAM, uri.toString());	    
//	    item1.setIcon(android.R.drawable.ic_menu_today);    
//		getMenuInflater().inflate(R.menu.activity_main, menu);
//		super.onCreateOptionsMenu(menu); 
//        MenuItem add = menu.add(0, 1, 0, "Save"); 
//        MenuItem open = menu.add(0, 2, 1, "Open"); 
//        MenuItem close = menu.add(0, 3, 2, "Close"); 
//        add.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM); 
//        open.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM); 
//        close.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM); 
        return true; 
		
	}
	public void updateShareIntent(String text) {
		if (shareIntent == null) {
			shareIntent = new Intent(Intent.ACTION_SEND);
			shareIntent.setType("image/*");
		}
//		shareIntent.removeExtra(Intent.EXTRA_TEXT);
//		if (fShare != null) {
			shareIntent.putExtra(Intent.EXTRA_TEXT, text);
		}
}

