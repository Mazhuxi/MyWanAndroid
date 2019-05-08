package com.majiaxin.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.majiaxin.day02_wanandroid.R;

import java.util.ArrayList;

public class SearchTitleAdapter extends RecyclerView.Adapter<SearchTitleAdapter.ViewHolder> {
    private Context mContext;
    public ArrayList<String> mList = new ArrayList<>();

    public SearchTitleAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(View.inflate(mContext,R.layout.activity_main_popup_search_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.quary.setText(mList.get(i));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(String quary) {
        mList.add(quary);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView quary;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            quary = itemView.findViewById(R.id.quary);
        }
    }
}
