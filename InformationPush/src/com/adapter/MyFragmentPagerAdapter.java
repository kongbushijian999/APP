package com.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter{

	//存储所有的fragment
	private List<Fragment> list;
	
	public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
		super(fm);
		this.list=list;
		// TODO 自动生成的构造函数存根
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO 自动生成的方法存根
		return list.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return list.size();
	}

}
