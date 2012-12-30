package com.rarnu.daisyshare;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class TextFragment extends Fragment implements TextWatcher {

	View innerView;
	EditText etShare;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (innerView == null) {
			innerView = inflater.inflate(R.layout.fragment_main, container, false);
			etShare = (EditText) innerView.findViewById(R.id.etShare);
			etShare.addTextChangedListener(this);
		}
		return innerView;
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}

	@Override
	public void afterTextChanged(Editable s) {
		((MainActivity)getActivity()).updateShareIntent(s.toString());
		
	}
}
