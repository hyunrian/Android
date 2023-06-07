package com.kh.listviewex2;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    Context context;
    int resource;
    List<NationDto> list;

    public MyAdapter(Context context, int resource, List<NationDto> list) {
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
        if (view == null) {
            view = View.inflate(context, resource, null);
        }
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textNation = view.findViewById(R.id.textNation);
        TextView textCapital = view.findViewById(R.id.textCapital);
        LinearLayout viewLayout = view.findViewById(R.id.viewLayout);

        NationDto dto = list.get(i);
        int drawable = dto.getDrawable();
        String nation = dto.getNation();
        String capital = dto.getCapital();

        imageView.setImageResource(drawable);
        textNation.setText(nation);
        textCapital.setText(capital);

        if ((i%2) == 0) viewLayout.setBackgroundColor(Color.rgb(234, 250, 241));
        else viewLayout.setBackgroundColor(Color.rgb(235, 245, 251 ));
        // 인덱스에 따른 레이아웃 컬러 설정은 Main이 아니라 여기서 설정해야 함

        return view;
    }

}
