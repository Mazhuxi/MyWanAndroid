package com.majiaxin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.majiaxin.bean.HomeBean;
import com.majiaxin.day02_wanandroid.R;
import com.majiaxin.utils.SpUtil;
import com.majiaxin.view.Constants;

import java.util.ArrayList;
import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<HomeBean.DatasBean> mList = new ArrayList<>();

    public ProjectAdapter(Context context) {
        this.context = context;
    }

    public void clearList() {
        mList.clear();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.fragment_project_item, null);
        ProjectHolder holder = new ProjectHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ProjectHolder holder = (ProjectHolder) viewHolder;
        final HomeBean.DatasBean data = mList.get(i);
        //赋值
        Boolean isPicture = (Boolean) SpUtil.getParam(Constants.PICTOR, true);
        if (isPicture) {
            Glide.with(context).load(R.mipmap.ic_launcher_new).into(holder.project_envelopPic);
        } else {
            Glide.with(context).load(data.getEnvelopePic()).into(holder.project_envelopPic);
        }
        holder.project_title.setText(data.getTitle());
        holder.project_desc.setText(data.getDesc());
        holder.project_niceDate.setText(data.getNiceDate());
        holder.project_author.setText(data.getAuthor());
        //点击条目监听
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.onClick(data.getTitle(), data.getLink());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addProjectList(List<HomeBean.DatasBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void addData(List<HomeBean.DatasBean> datas) {
        mList.addAll(datas);
        notifyDataSetChanged();
    }


    class ProjectHolder extends RecyclerView.ViewHolder {
        private ImageView project_envelopPic;
        private TextView project_title;
        private TextView project_desc;
        private TextView project_niceDate;
        private TextView project_author;

        public ProjectHolder(@NonNull View itemView) {
            super(itemView);
            project_envelopPic = itemView.findViewById(R.id.project_envelopPic);
            project_title = itemView.findViewById(R.id.project_title);
            project_desc = itemView.findViewById(R.id.project_desc);
            project_niceDate = itemView.findViewById(R.id.project_niceDate);
            project_author = itemView.findViewById(R.id.project_author);
        }
    }

    private ItemListener itemListener;

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public interface ItemListener {
        void onClick(String title, String url);
    }
}
