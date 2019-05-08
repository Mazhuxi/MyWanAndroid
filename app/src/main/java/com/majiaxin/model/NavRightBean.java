package com.majiaxin.model;

public class NavRightBean {
    public String rightData;
    public boolean isTitle;   //判断是否为省份，来进行加载数据
    public String leftTitles; //一个position，同时将城市与省份绑定
    public String tag;

    public NavRightBean() {
    }

    public NavRightBean(String rightData, boolean isTitle, String leftTitles, String tag) {
        this.rightData = rightData;
        this.isTitle = isTitle;
        this.leftTitles = leftTitles;
        this.tag = tag;
    }

    public String getRightData() {
        return rightData;
    }

    public void setRightData(String rightData) {
        this.rightData = rightData;
    }

    public boolean isTitle() {
        return isTitle;
    }

    public void setTitle(boolean title) {
        isTitle = title;
    }

    public String getLeftTitles() {
        return leftTitles;
    }

    public void setLeftTitles(String leftTitles) {
        this.leftTitles = leftTitles;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "NavRightBean{" +
                "rightData='" + rightData + '\'' +
                ", isTitle=" + isTitle +
                ", leftTitles='" + leftTitles + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
