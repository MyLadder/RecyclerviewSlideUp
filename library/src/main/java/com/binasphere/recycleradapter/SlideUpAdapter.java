package com.binasphere.recycleradapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Kerstin on 2015/12/15.
 */
public abstract class SlideUpAdapter extends RecyclerView.Adapter<SlideUpAdapter.BaseViewHolder> {
    private int mPosition;
    private int mSlideUpPx=50;
    public static Object relativeTag=new Object();
    public SlideUpAdapter(){
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        View space=new View(context);

        RelativeLayout relativeLayout= new RelativeLayout(context);
        relativeLayout.setTag(relativeTag);
        relativeLayout.addView(space, ViewGroup.LayoutParams.MATCH_PARENT, 5);
        LinearLayout linearLayout=new LinearLayout(context);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(relativeLayout, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        View itemView= onCreateView(linearLayout, viewType);

        linearLayout.addView(itemView);

        return onCreateViewHolder(linearLayout);
    }
    protected abstract BaseViewHolder onCreateViewHolder(View itemView);
    protected abstract  View onCreateView(LinearLayout parent, int viewType);

    @Override
    public void onBindViewHolder(final BaseViewHolder holder,  final int position) {
        if(position>=mPosition){
            holder.space.setPadding(0,mSlideUpPx,0,0);
            ValueAnimator animator = ValueAnimator.ofFloat(mSlideUpPx, 0);
            animator.setDuration(500).setInterpolator(new DecelerateInterpolator());
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float animatedValue = (float) animation.getAnimatedValue();
                    holder.space.setPadding(0, (int) animatedValue, 0, 0);
                }
            });
            animator.start();
            mPosition = position;
        }
        onBindBaseHolder(holder,position);
    }

    public void setSlideUpPx(int px){
        mSlideUpPx=px;
    }
    protected abstract void onBindBaseHolder(BaseViewHolder holder, int position);


    public abstract static class BaseViewHolder extends RecyclerView.ViewHolder {
        private View space;
        public BaseViewHolder(View itemView) {
            super(itemView);
            space=itemView.findViewWithTag(relativeTag);
//            ViewGroup group= (ViewGroup) itemView;
//            for(int i=0;i<group.getChildCount();i++){
//                View child = group.getChildAt(i);
//                if(child instanceof RelativeLayout){
//                    space=child;
//                    break;
//                }
//            }
        }
    }
}
