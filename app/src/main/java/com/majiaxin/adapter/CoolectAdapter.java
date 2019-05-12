package com.majiaxin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.majiaxin.bean.DatasBean;
import com.majiaxin.day02_wanandroid.R;

import java.util.ArrayList;
import java.util.List;

public class CoolectAdapter  extends RecyclerView.Adapter {

    private Context mContext;
    private List<DatasBean> mList = new ArrayList<>();

    public CoolectAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(View.inflate(mContext,R.layout.fragment_home_article,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        final DatasBean article = mList.get(i);

        holder.pager_author.setText(article.getAuthor());
        holder.pager_chapterName.setText(article.getSuperChapterName() + "/" + article.getChapterName());
        holder.pager_title.setText(article.getTitle());
        holder.pager_niceDate.setText(article.getNiceDate());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(List<DatasBean> datasBeans) {
        if (datasBeans.size() > 0){
            mList.addAll(datasBeans);
            notifyDataSetChanged();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView pager_author;
        private TextView pager_chapterName;
        private TextView pager_title;
        private TextView pager_green_tag;
        private TextView pager_red_tag;
        private CheckBox pager_like;
        private TextView pager_niceDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pager_author = itemView.findViewById(R.id.pager_author);
            pager_chapterName = itemView.findViewById(R.id.pager_chapterName);
            pager_title = itemView.findViewById(R.id.pager_title);
            pager_green_tag = itemView.findViewById(R.id.pager_green_tag);
            pager_red_tag = itemView.findViewById(R.id.pager_red_tag);
            pager_like = itemView.findViewById(R.id.pager_like);
            pager_niceDate = itemView.findViewById(R.id.pager_niceDate);
        }
    }

    //条目监听回调
    private onItemListener mOnItemListener;

    public void setOnItemListener(onItemListener onItemListener) {
        mOnItemListener = onItemListener;
    }

    public interface onItemListener {
        void onClick(String title, String url);
    }
}
