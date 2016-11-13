package com.bw.test.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by a on 2016/11/2.
 */
public class MyAdapter extends BaseAdapter{
    private Context context;
    private List<String> list;
    private static HashMap<Integer,Boolean> isSelected;
    public MyAdapter(Context context,List<String> list){
        this.context=context;
        this.list=list;
        isSelected=new HashMap<>();
        initData();
    }

    private void initData() {
        for (int i = 0; i < list.size(); i++) {
            getIsSelected().put(i,false);
        }
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view=View.inflate(context,R.layout.item,null);
            holder.box=(CheckBox) view.findViewById(R.id.box);
            holder.text=(TextView) view.findViewById(R.id.text);
            view.setTag(holder);
        }
        else{
            holder=(ViewHolder)view.getTag();
        }
        holder.text.setText(list.get(i));
        holder.box.setChecked(getIsSelected().get(i));
//        holder.box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(b){
//                    list.get(i).setC
//                }else{
//                    list.get(i).setCheck(false);
//                }
//            }
//        });
        return view;
    }
    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }
    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        MyAdapter.isSelected = isSelected;
    }
    public static class ViewHolder {
        CheckBox box;
        TextView text;
    }

}
