package com.grs.product.smartflat.fragments;

import com.grs.product.smartflat.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NoticeFragment extends Fragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_notice, container, false);
		initialiseUI(rootView);
		addListener();
        return rootView;	
        }
	
	private void initialiseUI(View rootView){
		
	}
	
	private void addListener(){
		
	}

}
