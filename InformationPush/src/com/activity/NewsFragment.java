package com.activity;

import com.page.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NewsFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		View v = inflater.inflate(R.layout.layout_news, container,false);  
        return v; 
	}
	
}
