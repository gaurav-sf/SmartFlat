package com.grs.product.smartflat.fragments;

import com.grs.product.smartflat.R;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FamilyMainFragment extends Fragment {
	
	private FragmentTabHost mTabHost;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_tab_hosting,container, false);
        mTabHost = (FragmentTabHost)rootView.findViewById(R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.fragmentContent);      
        
        mTabHost.addTab(
                mTabHost.newTabSpec("tab1").setIndicator(getTabIndicator(mTabHost.getContext(), R.string.text_happy_Family, android.R.drawable.star_on)),
                FamilyDetailsFragment.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab2").setIndicator(getTabIndicator(mTabHost.getContext(), R.string.text_add_member, android.R.drawable.star_on)),
                AddFamilyMemberFragment.class, null);
        return rootView;
	}
	
    private View getTabIndicator(Context context, int title, int icon) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_layout, null);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(title);
        return view;
    }



}
