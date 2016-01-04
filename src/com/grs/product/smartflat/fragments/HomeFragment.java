package com.grs.product.smartflat.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import com.grs.product.smartflat.R;
import com.grs.product.smartflat.activities.DashBoardActivity;
import com.grs.product.smartflat.adapter.CustomGridAdapter;

public class HomeFragment extends Fragment {
	GridView mHomeItemsGrid;
	  //String HALLOWEEN_ORANGE="#ffffff";
	  //final CharSequence[] items = {" Engineering "," Polytechnic "," GATE "," CAT "," Aptitude "};
	  public static final String MY_PREFS_NAME = "MyPrefsFile";
	  String[] item = {
	        "User Profile",
	      "Pending Request",
	      "Request",
	      "Unread Notice"} ;
	  
	  int[] imageId = {
		      R.drawable.ic_user_icon,
		      R.drawable.ic_pending_request_icon,
		      R.drawable.ic_request_icon,
		      R.drawable.ic_notice_icon} ;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_home, container, false);
		initialiseUI(rootView);
		addListeners();
        return rootView;
	}
	
	
	private void initialiseUI(View rootview)
	{
	    mHomeItemsGrid=(GridView)rootview.findViewById(R.id.grid);
	    CustomGridAdapter cGridAdapter = new CustomGridAdapter(getActivity(), item, imageId);
        mHomeItemsGrid.setAdapter(cGridAdapter);
	}
	
	private void addListeners(){
		mHomeItemsGrid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position){
				
				case 0:
					Intent intent = new Intent(getActivity(), DashBoardActivity.class);
					intent.putExtra("fromHome", "1");
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;					
				}
				
			}
		});
		
	}

	
}
