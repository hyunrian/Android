package com.kh.gridviewex;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    // adapter 생성 시 필요한 파라미터 3개(context, view, data) 정의
    Context context;
    int viewResource; // view
    List<MovieDto> list; // data

    public MyAdapter(Context context, int viewResource, List<MovieDto> list) {
        this.context = context;
        this.viewResource = viewResource;
        this.list = list;
    }

    @Override
    public int getCount() { // 데이터의 개수
        return list.size();
    }

    @Override
    public Object getItem(int i) { // 해당 인덱스의 아이템
        return list.get(i);
    }

    @Override
    public long getItemId(int i) { // 해당 인덱스 아이템의 아이디
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) { // 사용자 정의 뷰
        // 화면에 뷰를 처음 생성하고 스크롤하여 첫줄에 있던 뷰가 보이지 않으면 해당 뷰를 재사용하는 식으로 작동
        if (view == null) {
            view = View.inflate(context, viewResource, null);
        }
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView = view.findViewById(R.id.textView);

        MovieDto dto = list.get(i);
        int drawable = dto.getDrawable();
        String title = dto.getTitle();
        imageView.setImageResource(drawable);
        textView.setText(title);

        return view;
    }
}
