package com.rarnu.daisyshare;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ShareActionProvider;

public class MainActivity extends Activity {

	ActionBar bar;
	TextFragment fShare;
	ShareActionProvider provider;
	Intent shareIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_ACTION_BAR);
		bar = getActionBar();
		bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_CUSTOM);

		fShare = new TextFragment();
		getFragmentManager().beginTransaction()
				.replace(android.R.id.content, fShare)
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.commit();
		updateShareIntent("");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem item = menu.add("share");
		item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		item.setIcon(android.R.drawable.ic_menu_share);
		provider = new ShareActionProvider(this);
		item.setActionProvider(provider);
		provider.setShareHistoryFileName(ShareActionProvider.DEFAULT_SHARE_HISTORY_FILE_NAME);
		provider.setShareIntent(shareIntent);

		return true;
	}

	public void updateShareIntent(String text) {
		if (shareIntent == null) {
			shareIntent = new Intent(Intent.ACTION_SEND);
			shareIntent.setType("image/*");
		}
		shareIntent.removeExtra(Intent.EXTRA_TEXT);
		if (fShare != null) {
			shareIntent.putExtra(Intent.EXTRA_TEXT, text);
		}
	}

}
