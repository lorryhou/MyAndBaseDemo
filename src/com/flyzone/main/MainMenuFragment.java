package com.flyzone.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyzone.R;

public class MainMenuFragment extends Fragment {
	private MainActivity mActivity = null;
	private ExpandableListView mMenuListView;
	private TextView mNameText;
	private TextView mUserPoint;
	private ImageView mUserPhoto;
	private ImageView mSunshineView;
	private RelativeLayout loginLayout = null; 
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mActivity = (MainActivity) this.getActivity();
		
		// get the view
		View view = inflater.inflate(R.layout.main_menu, null);
		mMenuListView = (ExpandableListView) view.findViewById(R.id.menu_list);
		mNameText = (TextView) view.findViewById(R.id.user_name);
		mUserPoint = (TextView) view.findViewById(R.id.user_point);
		mUserPhoto  = (ImageView) view.findViewById(R.id.user_photo);
		mSunshineView = (ImageView) view.findViewById(R.id.sunshineView);
		loginLayout = (RelativeLayout) view.findViewById(R.id.login_layout);
		Button btnClearCache = (Button) view.findViewById(R.id.cacheClearBtn);
		btnClearCache.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mActivity.showToast(R.string.clear_cache);
			}
			
		});
		
		return view;	
	}
	
}
