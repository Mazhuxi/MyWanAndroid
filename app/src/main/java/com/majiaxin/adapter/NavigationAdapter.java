package com.majiaxin.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.majiaxin.bean.NavigationBean;
import com.majiaxin.day02_wanandroid.R;
import com.majiaxin.day02_wanandroid.ShowSearch;
import com.majiaxin.day02_wanandroid.ShowUrlActivity;
import com.majiaxin.utils.CircularAnimUtil;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NavigationAdapter extends RecyclerView.Adapter {
    public List<NavigationBean.DataBean> list;
    private Context context;
    private setOnClickListener sc;

    public NavigationAdapter(List<NavigationBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.navigationitem, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final int num = i;
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.tv.setText(list.get(i).getName());
        ArrayList<String> title = new ArrayList<>();
        for (int j = 0; j < list.get(i).getArticles().size(); j++) {
            String title1 = list.get(i).getArticles().get(j).getTitle();
            title.add(title1);
        }
        if (title != null) {
            holder.tabflowlayout.setAdapter(new TagAdapter<String>(title) {
                @Override
                public View getView(com.zhy.view.flowlayout.FlowLayout parent, int position, String string) {
                    /*int[] array = {R.color.Amber, R.color.arrow_color, R.color.colorPrimary
                            , R.color.colorPrimaryDark, R.color.Blue, R.color.color_title_bg, R.color.Green
                            , R.color.Deep_Orange, R.color.Lime, R.color.Teal, R.color.Indigo, R.color.Pink
                            , R.color.Yellow, R.color.Amber, R.color.Purple, R.color.Light_Green
                            , R.color.Light_Blue};

                    int random = (int) (Math.random() * (array.length - 1));*/
                     /* Random myRandom = new Random();
                    int ranColor = 0xff000000 | myRandom.nextInt(0x00ffffff);
                    tv.setTextColor(ranColor);*/

                    View inflate = LayoutInflater.from(context).inflate(R.layout.tag_textview, parent, false);
                    TextView tv = inflate.findViewById(R.id.tag_textview);
                    tv.setText(string);
                    String[] colors = {"#8A1D46", "#00005A", "#50821D", "#4A1744", "#884E16", "#035892"};
                    Random random = new Random();
                    int c = random.nextInt(6);
                    tv.setTextColor(Color.parseColor(colors[c]));
                    return inflate;
                }


            });
        }
        holder.tabflowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, com.zhy.view.flowlayout.FlowLayout parent) {
                String link = list.get(position).getArticles().get(position).getLink();
                String name = list.get(position).getName();
                Intent intent = new Intent(context, ShowUrlActivity.class);
                intent.putExtra("url", link);
                intent.putExtra("title", name);
                CircularAnimUtil.startActivity((Activity) context,intent,view,R.color.white);
                return false;
            }

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(List<NavigationBean.DataBean> bean1) {
        list.addAll(bean1);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv;
        public TagFlowLayout tabflowlayout;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv = (TextView) rootView.findViewById(R.id.tv);
            this.tabflowlayout = (TagFlowLayout) rootView.findViewById(R.id.tabflowlayout);
        }
    }

    interface setOnClickListener {
        void setClickListener(View v, int porition);
    }

    public void setList(setOnClickListener sc) {
        this.sc = sc;
    }
}

