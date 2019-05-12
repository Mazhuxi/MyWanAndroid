package com.majiaxin.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.majiaxin.bean.DatasBean;
import com.majiaxin.bean.HomeBannerbBean;
import com.majiaxin.bean.HomeBean;
import com.majiaxin.day02_wanandroid.MainActivity;
import com.majiaxin.day02_wanandroid.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<DatasBean> mArtList = new ArrayList<>();
    private List<HomeBannerbBean.DataBean> mBannerList = new ArrayList<>();
    private int TYPE_BANNER = 1;
    private int TYPE_ARTICLE = 2;
    private ArrayList<String> mBannerTitles;

    public HomeAdapter(Context context) {
        mContext = context;
    }

    public void clearArtList() {
        if (mArtList.size() > 0) {
            mArtList.clear();
        }
    }

    public void clearBannerList() {
        if (mArtList.size() > 0) {
            mBannerList.clear();
        }
    }

    public void clearTitleList() {
        if (mBannerTitles.size() > 0) {
            mBannerTitles.clear();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == TYPE_BANNER) {
            return new BannerViewHolder(View.inflate(mContext, R.layout.fragment_home_banner, null));
        } else {
            return new ArticleViewHolder(View.inflate(mContext, R.layout.fragment_home_article, null));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int type = getItemViewType(position);

        if (type == TYPE_BANNER) {
            BannerViewHolder holder = (BannerViewHolder) viewHolder;
            //设置banner样式
            holder.pager_banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
            //设置图片集合
            holder.pager_banner.setImages(mBannerList);
            //设置图片加载器
            holder.pager_banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    HomeBannerbBean.DataBean bannerPath = (HomeBannerbBean.DataBean) path;
                    Glide.with(mContext).load(bannerPath.getImagePath()).into(imageView);
                }
            });
            //设置banner动画效果
            holder.pager_banner.setBannerAnimation(Transformer.DepthPage);
            //设置标题集合（当banner样式有显示title时）
            mBannerTitles = new ArrayList<>();
            for (HomeBannerbBean.DataBean bean : mBannerList) {
                mBannerTitles.add(bean.getTitle());
            }
            holder.pager_banner.setBannerTitles(mBannerTitles);
            //设置自动轮播，默认为true
            holder.pager_banner.isAutoPlay(true);
            //设置轮播时间
            holder.pager_banner.setDelayTime(mBannerList.size() * 400);
            //设置指示器位置（当banner模式中有指示器时）
            holder.pager_banner.setIndicatorGravity(BannerConfig.CENTER);
            holder.pager_banner.start();
            //设置Banner监听
            holder.pager_banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    mOnItemListener.onClick(mBannerList.get(position).getTitle(), mBannerList.get(position).getUrl());
                }
            });
        } else {
            int newPositon = position;
            if (mBannerList.size() > 0) {
                newPositon = position - 1;
            } else {
                newPositon = position;
            }
            ArticleViewHolder holder = (ArticleViewHolder) viewHolder;
            final DatasBean article = mArtList.get(newPositon);

            holder.pager_author.setText(article.getAuthor());
            holder.pager_chapterName.setText(article.getSuperChapterName() + "/" + article.getChapterName());
            holder.pager_title.setText(article.getTitle());
            holder.pager_niceDate.setText(article.getNiceDate());

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String todayDate = df.format(new Date());
            String oldDate = df.format(new Date(article.getPublishTime()));
            if (todayDate.equals(oldDate)) {
                holder.pager_green_tag.setVisibility(View.VISIBLE);
                holder.pager_green_tag.setText(R.string.newArticle);
            } else {
                holder.pager_green_tag.setVisibility(View.GONE);
            }


            if (article.getSuperChapterName().contains(mContext.getString(R.string.project))) {
                holder.pager_red_tag.setVisibility(View.VISIBLE);
                holder.pager_red_tag.setText(R.string.project);
            } else if (article.getSuperChapterName().contains(mContext.getString(R.string.navigation))) {
                holder.pager_red_tag.setVisibility(View.VISIBLE);
                holder.pager_red_tag.setText(R.string.navigation);
            } else {
                holder.pager_red_tag.setVisibility(View.GONE);
            }


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemListener != null) {
                        mOnItemListener.onClick(article.getTitle(), article.getLink());
                    }
                }
            });

            holder.pager_red_tag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnTagListener != null) {
                        mOnTagListener.onClick(holder.pager_red_tag.getText().toString().trim());
                    }
                }
            });

            holder.pager_like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mOnCheakedListener.onClick(position, isChecked, mArtList.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mBannerList.size() > 0) {
            return mArtList.size() + 1;
        } else {
            return mArtList.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mBannerList.size() > 0 && position == 0) {
            return TYPE_BANNER;
        } else {
            return TYPE_ARTICLE;
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        private Banner pager_banner;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);

            pager_banner = itemView.findViewById(R.id.pager_banner);
        }
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {
        private TextView pager_author;
        private TextView pager_chapterName;
        private TextView pager_title;
        private TextView pager_green_tag;
        private TextView pager_red_tag;
        private CheckBox pager_like;
        private TextView pager_niceDate;

        public ArticleViewHolder(@NonNull View itemView) {
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


    public void addArticleData(List<DatasBean> datas) {
        if (datas != null) {
            mArtList.addAll(datas);
            notifyDataSetChanged();
        }
    }

    public void addBannerData(List<HomeBannerbBean.DataBean> data) {
        if (data != null) {
            mBannerList.addAll(data);
            notifyDataSetChanged();
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

    //项目监听回调
    private onTagListener mOnTagListener;

    public void setOnTagListener(onTagListener onTagListener) {
        mOnTagListener = onTagListener;
    }

    public interface onTagListener {
        void onClick(String title);
    }

    //收藏监听回调
    private onCheakedListener mOnCheakedListener;

    public void setOnCheakedListener(onCheakedListener onCheakedListener) {
        mOnCheakedListener = onCheakedListener;
    }

    public interface onCheakedListener {
        void onClick(int position, boolean isChecked, DatasBean item);
    }
}
