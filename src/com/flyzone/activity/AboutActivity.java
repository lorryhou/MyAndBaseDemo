package com.flyzone.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.ab.activity.AbActivity;
import com.ab.view.titlebar.AbTitleBar;
import com.flyzone.R;

public class AboutActivity extends AbActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.about_app);

		AbTitleBar title = this.getTitleBar();
		title.setTitleText("关于");
		title.setLogo(R.drawable.button_selector_back);
		mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);
		this.setTitleBarAbove(true);

		title.getLogoView().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}

		});

		TextView app_version = (TextView) findViewById(R.id.app_version);
		try {
			PackageInfo pkgInfo = getPackageManager().getPackageInfo("com.flyzone",
					PackageManager.GET_CONFIGURATIONS);
			app_version.setText("V:"+pkgInfo.versionName);
		} catch (NameNotFoundException e) {
			showToastInThread(e.getMessage());
		}
		
	}

}
