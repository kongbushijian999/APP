package com.fragment;

import com.maintest.R;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SecondFragment extends Fragment{
	
	@Override  
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {  
        // TODO Auto-generated method stub  
        View v = inflater.inflate(R.layout.layout_second, container,false);  
        return v;  
    }  

}
