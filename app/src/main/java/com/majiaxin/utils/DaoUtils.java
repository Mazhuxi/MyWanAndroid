package com.majiaxin.utils;

import com.majiaxin.app.BaseApp;
import com.majiaxin.bean.DatasBean;
import com.majiaxin.dao.DaoSession;
import com.majiaxin.dao.DatasBeanDao;

import java.util.List;

public class DaoUtils {
    public static void insertData(DatasBean data){
        DaoSession daoSession = BaseApp.getDaoSession();

        DatasBean quaryData = quaryData(data);
        if (quaryData == null){
            daoSession.insert(data);
        }
    }

    public static DatasBean quaryData(DatasBean data){
        DaoSession daoSession = BaseApp.getDaoSession();
        return daoSession.queryBuilder(DatasBean.class)
                .where(DatasBeanDao.Properties.Id.eq(data.getId()))
                .build()
                .unique();
    }

    public static List<DatasBean> quaryAllData(){
        DaoSession daoSession = BaseApp.getDaoSession();
        return daoSession.loadAll(DatasBean.class);
    }

    public static void deleteData(DatasBean data){
        DaoSession daoSession = BaseApp.getDaoSession();

        DatasBean quaryData = quaryData(data);
        if (quaryData != null){
            daoSession.delete(quaryData);
        }
    }

}
