package com.example.binil.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.binil.DetailActivity;
import com.example.binil.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ListAdapter extends BaseAdapter {

     Context mContext;
     LayoutInflater inflater;
     private List<ListGetSet> itemListPogo = null;
     private ArrayList<ListGetSet> arraylist;
    public ImageLoader imageLoader;

    public ListAdapter(Context context, List<ListGetSet> itemLists)
     {
         mContext = context;
         this.itemListPogo = itemLists;
         inflater = LayoutInflater.from(mContext);
         this.arraylist = new ArrayList<ListGetSet>();
         this.arraylist.addAll(itemLists);
         imageLoader=new ImageLoader(mContext.getApplicationContext());
        // imageLoader      = new ImageLoader(mContext.getApplicationContext());
        // imageLoaderRound = new ImageLoaderRound(mContext.getApplicationContext());
     }

     public class ViewHolder {
         TextView itemId;
         TextView img_url;
         ImageView list_img;
         RelativeLayout relative_item;
     }

     @Override
     public int getCount() {
         return itemListPogo.size();
     }

     @Override
     public ListGetSet getItem(int position) {
         return itemListPogo.get(position);
     }

     @Override
     public long getItemId(int position) {
         return position;
     }

     public View getView(final int position, View view, ViewGroup parent)
     {
         final ViewHolder holder;
         if (view == null)
         {
             holder = new ViewHolder();
           view = inflater.inflate(R.layout.list_row, null);


           holder.itemId       = (TextView) view.findViewById(R.id.title);
           holder.list_img    = (ImageView) view.findViewById(R.id.list_image);
           holder.img_url     =(TextView) view.findViewById(R.id.img_url);

           //  RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(width,height);
             //holder.itemImage.setLayoutParams(parms);
             //holder.itemImage.setScaleType(ScaleType.CENTER_CROP);


             view.setTag(holder);
         }
         else
         {
             holder = (ViewHolder) view.getTag();
         }

         // Set the results into TextViews
         holder.itemId.setText(itemListPogo.get(position).getdescritpion());
         holder.img_url.setText(itemListPogo.get(position).getImageURl());
         Log.e("Tag", "Download image " + itemListPogo.get(position).getImageURl());
         imageLoader.DisplayImage(itemListPogo.get(position).getImageURl(), holder.list_img);
       //  imageLoaderRound.DisplayImage(itemListPogo.get(position).getImageURl(), (ImageView) holder.list_img);



         view.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View arg0)
             {
                 //On things on click
                // holder.relative_item.setBackgroundColor(mContext.getResources().getColor(R.color.line_ash));
                 Log.e("Item click", "Item click");

               Intent i = new Intent(mContext,DetailActivity.class);
               i.putExtra("des", holder.itemId.getText().toString());
               i.putExtra("img_url", holder.img_url.getText().toString());
               ((Activity) mContext).finish();
               mContext.startActivity(i);

             }
         });

         return view;
     }

     // Filter Class
     public void filter(String charText)
     {
         charText = charText.toLowerCase(Locale.getDefault());
         itemListPogo.clear();

         if (charText.length() == 0)
         {
             itemListPogo.addAll(arraylist);
         }
         else
         {
             for (ListGetSet wp : arraylist)
             {
                 if (wp.getdescritpion().toLowerCase(Locale.getDefault())
                         .contains(charText))
                 {
                     itemListPogo.add(wp);
                 }
             }
         }
         notifyDataSetChanged();
     }
 }
