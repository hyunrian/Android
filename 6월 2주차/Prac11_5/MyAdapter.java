package com.kh.prac11_5;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context context;
    int resource;
    ArrayList<Integer> list = new ArrayList<>();

    public MyAdapter(Context context, int resource, ArrayList<Integer> list) {
        this.context = context;
        this.resource = resource;
        this.list = list;
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

        if (view == null) view = View.inflate(context, resource, null);

        ImageView imageView = view.findViewById(R.id.imageView);

        int drawable = list.get(i);
        imageView.setImageResource(drawable);
        return view;
    }
}
