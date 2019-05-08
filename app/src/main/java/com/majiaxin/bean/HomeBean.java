package com.majiaxin.bean;

import java.util.List;

public class HomeBean{
    /**
     * curPage : 1
     * datas : [{"apkLink":"","author":"奇葩AnJoiner","chapterId":77,"chapterName":"响应式编程","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":8376,"link":"https://www.jianshu.com/p/79cb4e1c9771","niceDate":"21小时前","origin":"","prefix":"","projectLink":"","publishTime":1557236172000,"superChapterId":79,"superChapterName":"热门专题","tags":[],"title":"Rx系列之Rxjava操作符进阶-使用场景","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"code小生","chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8369,"link":"https://mp.weixin.qq.com/s/kfdUfPj1XDpoajqRwqSpzQ","niceDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1557158400000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"Android 适配之版本适配","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8372,"link":"https://mp.weixin.qq.com/s/sBmDQXbs1AwNTtTEaf3F-g","niceDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1557158400000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"Android 这些内容你应该知道 | 3 期","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"郭霖","chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8374,"link":"https://mp.weixin.qq.com/s/3Q3aJ0mYUWU5bo8rZOhDcA","niceDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1557158400000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"Jetpack核心组件，ViewModel的使用及原理解析","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"wuyr","chapterId":363,"chapterName":"创意汇","collect":false,"courseId":13,"desc":"Base64刚好对应64卦象，编码时先将目标字符串转成标准的Base64，然后拆分每一个字符，从映射表中取出对应的卦象。解码同理，先把卦象码通过映射表得到Base64，然后解码Base64。\r\n","envelopePic":"https://wanandroid.com/resources/image/pc/default_project_img.jpg","fresh":false,"id":8365,"link":"http://www.wanandroid.com/blog/show/2560","niceDate":"1天前","origin":"","prefix":"","projectLink":"https://github.com/wuyr/HexagramDecoder","publishTime":1557157262000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=363"}],"title":"易经64卦编解码（HexagramDecoder）","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"Robin_Lrange","chapterId":185,"chapterName":"组件化","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8364,"link":"https://www.jianshu.com/p/e40b7eb99573","niceDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1557156972000,"superChapterId":79,"superChapterName":"热门专题","tags":[],"title":"浅谈项目重构之路&mdash;&mdash;模块化","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"C6C","chapterId":227,"chapterName":"注解","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8363,"link":"https://www.jianshu.com/p/9039a3e46dbc","niceDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1557156927000,"superChapterId":227,"superChapterName":"注解 & 反射 & AOP","tags":[],"title":"Android ASM自动埋点方案实践","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":" Chopper_zjxstar","chapterId":262,"chapterName":"SDK开发","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8362,"link":"https://juejin.im/post/5ccd42df51882541ca0345c5","niceDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1557156892000,"superChapterId":79,"superChapterName":"热门专题","tags":[],"title":"对 Android SDK 开发的一些个人心得","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":" 秉心说","chapterId":451,"chapterName":"加密解密","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8361,"link":"https://juejin.im/post/5cceca46e51d456e2a64b35c","niceDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1557156825000,"superChapterId":245,"superChapterName":"Java深入","tags":[],"title":"早恋与加密第一回: 古典加密","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":" 渡口一艘船","chapterId":169,"chapterName":"gradle","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8360,"link":"https://juejin.im/post/5ccf02e36fb9a0322e73a3db","niceDate":"1天前","origin":"","prefix":"","projectLink":"","publishTime":1557156715000,"superChapterId":60,"superChapterName":"开发环境","tags":[],"title":"Gradle插件从入门到进阶","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"玉刚说","chapterId":410,"chapterName":"玉刚说","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8367,"link":"https://mp.weixin.qq.com/s/uI7Fej1_qSJOJnzQ6offpw","niceDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1557072000000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/410/1"}],"title":"深扒 EventBus：register","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8371,"link":"https://mp.weixin.qq.com/s/8ceAAAt8FEL0Se-8--7CfQ","niceDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1557072000000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"我在一个群分享Android 好像被我分享得没人说话了... 2期","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"三好码农","chapterId":77,"chapterName":"响应式编程","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8359,"link":"https://juejin.im/post/5b66eeaa6fb9a04fd93e5369","niceDate":"2天前","origin":"","prefix":"","projectLink":"","publishTime":1557063177000,"superChapterId":79,"superChapterName":"热门专题","tags":[],"title":"RxJava2源码解读之 Map、FlatMap","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"玉刚说","chapterId":410,"chapterName":"玉刚说","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8366,"link":"https://mp.weixin.qq.com/s/MRv1aMJD8XtTEP0keoqerA","niceDate":"2019-05-05","origin":"","prefix":"","projectLink":"","publishTime":1556985600000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/410/1"}],"title":"从零开始实现一个 mini-Retrofit 框架","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"code小生","chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8368,"link":"https://mp.weixin.qq.com/s/_FeFjeCkpPeiPq1I6VoRFw","niceDate":"2019-05-05","origin":"","prefix":"","projectLink":"","publishTime":1556985600000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"Android 中的红点提示怎么统一实现","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8370,"link":"https://mp.weixin.qq.com/s/0EprsJ7sXKmphghMsU3aGw","niceDate":"2019-05-05","origin":"","prefix":"","projectLink":"","publishTime":1556985600000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"微信自研APM利器Matrix 卡顿分析工具之Trace Canary","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"郭霖","chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8373,"link":"https://mp.weixin.qq.com/s/5CeZ6NHF6dm3qN6RgzaGDQ","niceDate":"2019-05-05","origin":"","prefix":"","projectLink":"","publishTime":1556985600000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"程序媛说源码：AsyncTask在子线程创建与调用的那些事儿","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"承香墨影","chapterId":411,"chapterName":"承香墨影","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8375,"link":"https://mp.weixin.qq.com/s/JndDaQTkmbo05ciT1OVW1w","niceDate":"2019-05-05","origin":"","prefix":"","projectLink":"","publishTime":1556985600000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/411/1"}],"title":"&quot;&quot;转 Int，{} 转 List，还有什么奇葩的 JSON 要容错？| 实战","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"飞吧小蚊子","chapterId":443,"chapterName":"Android 10.0","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8358,"link":"https://www.jianshu.com/p/77f319ea53aa","niceDate":"2019-05-04","origin":"","prefix":"","projectLink":"","publishTime":1556976828000,"superChapterId":151,"superChapterName":"5.+高新技术","tags":[],"title":"Android Q适配（1）-------图标篇","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"蒙伟","chapterId":31,"chapterName":"Dialog","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":8357,"link":"https://www.jianshu.com/p/5f8e74726eee","niceDate":"2019-05-04","origin":"","prefix":"","projectLink":"","publishTime":1556976695000,"superChapterId":30,"superChapterName":"用户交互","tags":[],"title":"Android带进场退场动画的dialog对话框","type":0,"userId":-1,"visible":1,"zan":0}]
     * offset : 0
     * over : false
     * pageCount : 323
     * size : 20
     * total : 6446
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<DatasBean> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * apkLink :
         * author : 奇葩AnJoiner
         * chapterId : 77
         * chapterName : 响应式编程
         * collect : false
         * courseId : 13
         * desc :
         * envelopePic :
         * fresh : true
         * id : 8376
         * link : https://www.jianshu.com/p/79cb4e1c9771
         * niceDate : 21小时前
         * origin :
         * prefix :
         * projectLink :
         * publishTime : 1557236172000
         * superChapterId : 79
         * superChapterName : 热门专题
         * tags : []
         * title : Rx系列之Rxjava操作符进阶-使用场景
         * type : 0
         * userId : -1
         * visible : 1
         * zan : 0
         */

        private String apkLink;
        private String author;
        private int chapterId;
        private String chapterName;
        private boolean collect;
        private int courseId;
        private String desc;
        private String envelopePic;
        private boolean fresh;
        private int id;
        private String link;
        private String niceDate;
        private String origin;
        private String prefix;
        private String projectLink;
        private long publishTime;
        private int superChapterId;
        private String superChapterName;
        private String title;
        private int type;
        private int userId;
        private int visible;
        private int zan;
        private List<TagsBean> tags;

        public String getApkLink() {
            return apkLink;
        }

        public void setApkLink(String apkLink) {
            this.apkLink = apkLink;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getChapterId() {
            return chapterId;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public boolean isCollect() {
            return collect;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public boolean isFresh() {
            return fresh;
        }

        public void setFresh(boolean fresh) {
            this.fresh = fresh;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getProjectLink() {
            return projectLink;
        }

        public void setProjectLink(String projectLink) {
            this.projectLink = projectLink;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public int getSuperChapterId() {
            return superChapterId;
        }

        public void setSuperChapterId(int superChapterId) {
            this.superChapterId = superChapterId;
        }

        public String getSuperChapterName() {
            return superChapterName;
        }

        public void setSuperChapterName(String superChapterName) {
            this.superChapterName = superChapterName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }
    }

    public static class TagsBean {
        /**
         * name : 公众号
         * url : /wxarticle/mList/408/1
         */

        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
