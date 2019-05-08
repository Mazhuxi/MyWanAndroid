package com.majiaxin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.majiaxin.bean.KnowledgeBean;
import com.majiaxin.day02_wanandroid.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class KnowledgeAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<KnowledgeBean.DataBean> mList = new ArrayList<>();
    private ArrayList<Integer> mColors;


    public KnowledgeAdapter(Context context) {
        mContext = context;
    }

    public void clearList() {
        mList.clear();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(View.inflate(mContext, R.layout.fragment_konwledge_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        ViewHolder holder = (ViewHolder) viewHolder;

        mColors = new ArrayList<>();
        mColors.add(R.color.know_title_color1);
        mColors.add(R.color.know_title_color2);
        mColors.add(R.color.know_title_color3);
        mColors.add(R.color.know_title_color4);
        mColors.add(R.color.know_title_color5);

        KnowledgeBean.DataBean bean = mList.get(position);
        holder.item_title.setText(bean.getName());
        holder.item_title.setTextColor(mContext.getResources().getColor(mColors.get(position%5)));

        final List<KnowledgeBean.DataBean.ChildrenBean> children = bean.getChildren();
        StringBuilder stringBuilder = new StringBuilder();
        for (KnowledgeBean.DataBean.ChildrenBean child : children) {
            stringBuilder.append(child.getName() + "   ");
        }
        holder.item_name.setText(stringBuilder.toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemListener != null){
                    mOnItemListener.OnClick(position,mList.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(List<KnowledgeBean.DataBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView item_title;
        private TextView item_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_name = itemView.findViewById(R.id.item_name);
            item_title = itemView.findViewById(R.id.item_title);
        }
    }
    private onItemListener mOnItemListener;

    public void setOnItemListener(onItemListener onItemListener) {
        mOnItemListener = onItemListener;
    }

    public interface onItemListener{
        void OnClick(int positon,KnowledgeBean.DataBean bean);
    }
}
