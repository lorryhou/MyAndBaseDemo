package com.flyzone.main;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ab.bitmap.AbImageCache;
import com.ab.global.AbMenuItem;
import com.ab.task.AbTask;
import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListener;
import com.ab.util.AbFileUtil;
import com.flyzone.R;
import com.flyzone.activity.AboutActivity;

public class MainMenuFragment extends Fragment {
	private MainActivity mActivity = null;
	private ExpandableListView mMenuListView;
	private TextView mNameText;
	private TextView mUserPoint;
	private ImageView mUserPhoto;
	private ImageView mSunshineView;
	private RelativeLayout mLoginLayout = null;
	private ArrayList<String> mGroupName;
	private ArrayList<AbMenuItem> mChild1;
	private ArrayList<AbMenuItem> mChild2;
	private LeftMenuAdapter mLeftMenuAdapter;
	private OnChangeViewListener mOnChangeViewListener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mActivity = (MainActivity) this.getActivity();

		// get the view
		View view = inflater.inflate(R.layout.main_menu, null);
		mMenuListView = (ExpandableListView) view.findViewById(R.id.menu_list);
		mNameText = (TextView) view.findViewById(R.id.user_name);
		mUserPoint = (TextView) view.findViewById(R.id.user_point);
		mUserPhoto = (ImageView) view.findViewById(R.id.user_photo);
		mSunshineView = (ImageView) view.findViewById(R.id.sunshineView);
		mLoginLayout = (RelativeLayout) view.findViewById(R.id.login_layout);
		Button btnClearCache = (Button) view.findViewById(R.id.cacheClearBtn);
		btnClearCache.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mActivity.showToast("正在清空缓存...");
				AbTask task = new AbTask();
				AbTaskItem item = new AbTaskItem();
				item.setListener(new AbTaskListener() {

					@Override
					public void get() {
						try {
							AbFileUtil.removeAllFileCache();
							AbImageCache.removeAllBitmapFromCache();
						} catch (Exception e) {
							mActivity.showToastInThread(e.getMessage());
						}
					}

					@Override
					public void update() {
						mActivity.removeProgressDialog();
						mActivity.showToast("缓存已清空...");
					}
				});

				task.execute(item);
			}

		});

		mGroupName = new ArrayList<String>();
		mChild1 = new ArrayList<AbMenuItem>();
		mChild2 = new ArrayList<AbMenuItem>();

		ArrayList<ArrayList<AbMenuItem>> mChilds = new ArrayList<ArrayList<AbMenuItem>>();
		mChilds.add(mChild1);
		mChilds.add(mChild2);

		// 加载菜单数据
		initMenuData();
		
		mLeftMenuAdapter = new LeftMenuAdapter(mActivity, mGroupName, mChilds);
		mMenuListView.setAdapter(mLeftMenuAdapter);
		for (int i = 0; i < mGroupName.size(); i++) {
			mMenuListView.expandGroup(i);
		}

		mMenuListView.setOnGroupClickListener(new OnGroupClickListener() {

			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				return true;
			}
		});


		mMenuListView.setOnChildClickListener(new OnChildClickListener() {

			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				if (mOnChangeViewListener != null) {
					mOnChangeViewListener.onChangeView(groupPosition,
							childPosition);
				}
				return true;
			}
		});
		
		startAnimation(mSunshineView);

		return view;
	}

	/**
	 * 设置SunshineView Animation
	 * @param mSunshineView2
	 */
	private void startAnimation(ImageView mSunshineView2) {
		// TODO Auto-generated method stub
		AnimationSet animation = new AnimationSet(true);
		RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		rotateAnimation.setDuration(2000);
		rotateAnimation.setRepeatCount(5);
		rotateAnimation.setInterpolator(mActivity, android.R.anim.decelerate_interpolator);
		animation.addAnimation(rotateAnimation);
		
		mSunshineView2.setAnimation(animation);
	}

	/**
	 * 初始化菜单列表
	 */
	private void initMenuData() {
		mGroupName.clear();
		mChild1.clear();
		mChild2.clear();

		mGroupName.add("常用");
		mGroupName.add("操作");

		AbMenuItem m0 = new AbMenuItem();
		m0.setIconId(R.drawable.square);
		m0.setText("朋友圈");
		mChild1.add(m0);

		AbMenuItem m1 = new AbMenuItem();
		m1.setIconId(R.drawable.share);
		m1.setText("程序案例");
		mChild1.add(m1);

		AbMenuItem m2 = new AbMenuItem();
		m2.setIconId(R.drawable.about);
		m2.setText("关于");
		mChild2.add(m2);
		
		setOnChangeViewListener(new OnChangeViewListener(){

			@Override
			public void onChangeView(int groupPosition, int childPosition) {
				if(groupPosition == 0){
					if(childPosition == 0){
						mActivity.showToast("朋友圈...");
					}else if(childPosition == 1){
						mActivity.showToast("程序分享...");
					}
				}else if(groupPosition == 1){
					if(childPosition == 0){
						startActivity(new Intent(mActivity, AboutActivity.class));
					}
				}
			}
			
		});
	}

	public interface OnChangeViewListener {
		public abstract void onChangeView(int groupPosition, int childPosition);
	}

	public void setOnChangeViewListener(OnChangeViewListener onChangeViewListener) {
		mOnChangeViewListener = onChangeViewListener;
	}

}
