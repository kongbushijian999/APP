package com.activity;

import com.page.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NoticeFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO �Զ����ɵķ������
		View v = inflater.inflate(R.layout.layout_notice, container,false);  
        return v; 
	}
	
}
