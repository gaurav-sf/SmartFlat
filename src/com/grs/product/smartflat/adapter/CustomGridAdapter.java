package com.grs.product.smartflat.adapter;

import com.grs.product.smartflat.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomGridAdapter extends BaseAdapter {

	private Context mContext;
    private final String[] item;
    private final int[] Imageid;
    //Typeface tf; 
      public CustomGridAdapter(Context c,String[] item,int[] Imageid) {
    	  

          mContext = c;
          this.Imageid = Imageid;
          this.item = item;
          //tf = Typeface.createFromAsset(mContext.getAssets(),FONT);
      }
    @Override
    public int getCount() {
      // TODO Auto-generated method stub
      return item.length;
    }
    @Override
    public Object getItem(int position) {
      // TODO Auto-generated method stub
      return null;
    }
    @Override
    public long getItemId(int position) {
      // TODO Auto-generated method stub
      return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      // TODO Auto-generated method stub
      View grid;
      LayoutInflater inflater = (LayoutInflater) mContext
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
          if (convertView == null) {
            grid = new View(mContext);
        grid = inflater.inflate(R.layout.fragment_home_single, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);

            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            textView.setText(item[position]);
            imageView.setImageResource(Imageid[position]);
          } else {
            grid = (View) convertView;
          }
      return grid;
    }
}
