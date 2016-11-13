package com.bw.test.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import java.util.List;

/**
 * Created by a on 2016/11/3.
 */
public class MyAdapter extends BaseAdapter{
    Context context;
    List<ImageView> list;
    public MyAdapter(Context context, List<ImageView> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=View.inflate(context,R.layout.gridview_item,null);
        android.widget.ImageView icon= (android.widget.ImageView) view.findViewById(R.id.image);
        int image = list.get(i).getImage();
        icon.setImageResource(image);
        icon.setBackgroundColor(Color.WHITE);


        return view;
    }
}
