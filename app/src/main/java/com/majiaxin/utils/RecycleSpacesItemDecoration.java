package com.majiaxin.utils;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class RecycleSpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int topBottom;
    private int leftRight;

    public RecycleSpacesItemDecoration(int topBottom, int leftRight) {
        this.topBottom = topBottom;
        this.leftRight = leftRight;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        if (layoutManager.getOrientation() == LinearLayoutManager.VERTICAL){
            if (parent.getChildAdapterPosition(view) == layoutManager.getItemCount()-1){
                outRect.bottom = topBottom;
            }
            outRect.top = topBottom;
            outRect.left = leftRight;
            outRect.right = leftRight;
        }else {
            if (parent.getChildAdapterPosition(view) == layoutManager.getItemCount()-1){
                outRect.right = leftRight;
            }
            outRect.top=topBottom;
            outRect.left = leftRight;
            outRect.bottom = topBottom;
        }
    }
}
