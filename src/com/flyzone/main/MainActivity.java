package com.flyzone.main;

import java.util.Timer;
import java.util.TimerTask;

import com.ab.activity.AbActivity;
import com.ab.view.slidingmenu.SlidingMenu;
import com.ab.view.titlebar.AbTitleBar;
import com.flyzone.R;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AbActivity {
	private SlidingMenu menu;
	private AbTitleBar mAbTitleBar = null;
	private MainMenuFragment mMainMenuFragment = null;
	private MainContentFragment mMainContentFragment = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAbContentView(R.layout.activity_main);
		
		// 设置标题栏
		mAbTitleBar = this.getTitleBar();
		mAbTitleBar.setTitleText(R.string.app_name);
		mAbTitleBar.setLogo(R.drawable.button_selector_menu);
		mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);
		
		// 设置主内容视图
		mMainContentFragment = new MainContentFragment();
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, mMainContentFragment)
		.commit();
		
		// 设置SlidingMenu
		menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

		// 设置SlidingMenu菜单视图
		mMainMenuFragment = new MainMenuFragment();
		menu.setMenu(R.layout.sliding_menu_menu);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.menu_frame, mMainMenuFragment)
		.commit();
		
		mAbTitleBar.getLogoView().setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(menu.isMenuShowing()){
					menu.showContent();
				}else{
					menu.showMenu();
				}
			}
			
		});
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private static Boolean isExit = false;  
    private static Boolean hasTask = false;  
    private Timer tExit = new Timer();  
    private TimerTask task = new TimerTask() {  
        @Override 

        public void run() {  
            isExit = true;  
            hasTask = true;  
        }  
    };  

	@Override 
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
        if (keyCode == KeyEvent.KEYCODE_BACK) { 
        	if(mMainContentFragment.canBack()){
        		if(isExit == false ) {  
                    isExit = true;
                    showToast("再按一次退出程序");
                    if(!hasTask) {  
                        tExit.schedule(task, 2000);  
                    }  
                } else {
                	//showZero();
                    //finish();  
                    //System.exit(0);
                    
                }
        	}
        }  
        return false;  
    } 

}
