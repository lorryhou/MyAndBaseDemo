package com.flyzone.main;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab.global.AbMenuItem;
import com.flyzone.R;

public class LeftMenuAdapter extends BaseExpandableListAdapter{
	private Context mContext;
	private ArrayList<String> mGroupName;
	private ArrayList<ArrayList<AbMenuItem>> mChilds;
	private LayoutInflater inflater;
	
	public LeftMenuAdapter(Context context, ArrayList<String> groups,
			ArrayList<ArrayList<AbMenuItem>> childs) {
		mContext = context;
		mGroupName = groups;
		mChilds = childs;
		inflater = LayoutInflater.from(mContext);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return mChilds.get(groupPosition).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, 
			View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.main_menu_list_child, null);
			holder = new ViewHolder();
			holder.mChildName = (TextView) convertView.findViewById(R.id.desktop_list_child_name);
			holder.mChildIcon = (ImageView) convertView.findViewById(R.id.desktop_list_child_icon);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		AbMenuItem abmenu = mChilds.get(groupPosition).get(childPosition);
		holder.mChildName.setText(abmenu.getText());
		holder.mChildIcon.setImageResource(abmenu.getIconId());
		
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return mChilds.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return mGroupName.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return mGroupName.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.main_menu_list_group, null);
			holder = new ViewHolder();
			holder.mGroupName = (TextView) convertView.findViewById(R.id.desktop_list_group_name);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		String name = mGroupName.get(groupPosition);
		holder.mGroupName.setText(name);
		
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		return true;
	}
	
	private class ViewHolder{
		TextView mGroupName;
		TextView mChildName;
		ImageView mChildIcon;
	}

}
