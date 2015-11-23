package com.grs.product.smartflat.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.grs.product.smartflat.R;
import com.grs.product.smartflat.adapter.CustomGridAdapter;
import com.grs.product.smartflat.database.SmartFlatDBManager;
import com.grs.product.smartflat.database.SmartFlatDBTables.TableFlatOwnerDetails;
import com.grs.product.smartflat.models.FlatOwnerDetails;

public class HomeFragment extends Fragment {
	GridView grid;
	  String HALLOWEEN_ORANGE="#ffffff";
	  final CharSequence[] items = {" Engineering "," Polytechnic "," GATE "," CAT "," Aptitude "};
	  public static final String MY_PREFS_NAME = "MyPrefsFile";
	  String[] item = {
	        "User Profile",
	      "Pending Request",
	      "Request",
	      "Unread Notice"} ;
	  
	  int[] imageId = {
		      R.drawable.ic_launcher,
		      R.drawable.ic_launcher,
		      R.drawable.ic_launcher,
		      R.drawable.ic_launcher} ;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_home, container, false);
		initialiseUI(rootView);
        return rootView;
	}
	
	
	private void initialiseUI(View rootview){
		
	    grid=(GridView)rootview.findViewById(R.id.grid);
	    CustomGridAdapter cGridAdapter = new CustomGridAdapter(getActivity(), item, imageId);
        grid.setAdapter(cGridAdapter);
        
        
        
	}

	
}
