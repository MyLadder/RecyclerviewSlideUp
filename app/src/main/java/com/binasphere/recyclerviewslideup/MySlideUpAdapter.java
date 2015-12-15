package com.binasphere.recyclerviewslideup;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.binasphere.recycleradapter.SlideUpAdapter;

/**
 * Created by Kerstin on 2015/12/15.
 */
public class MySlideUpAdapter extends SlideUpAdapter {
    @Override
    public int getItemCount() {
        return 100;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View itemView) {
        return new MyHolder(itemView);
    }

    @Override
    public View onCreateView(LinearLayout parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv,parent,false);
    }

    @Override
    protected void onBindBaseHolder(BaseViewHolder holder, int position) {
        MyHolder myHolder= (MyHolder) holder;
        myHolder.tv.setText("Show");
    }
    public static class MyHolder extends BaseViewHolder{
        public TextView tv;
        public MyHolder(View itemView) {
            super(itemView);
            tv= (TextView) itemView.findViewById(R.id.tv_item);
        }
    }
}
