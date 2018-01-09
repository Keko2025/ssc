package com.soho.ssc.db;


import com.soho.ssc.MyApplication;
import com.soho.ssc.domain.HistoryVideo;
import com.soho.ssc.greendao.HistoryVideoDao;

import java.util.List;

/**
 * Created by dell on 2017/4/27.
 * greenDao数据库操作类
 */

public class PlayHisDao {
    //添加数据，如果有重复，则覆盖
    public static void insert(HistoryVideo historyVideo){
        MyApplication.getDaoInstant().getHistoryVideoDao().insertOrReplace(historyVideo);
    }
    //删除某一条数据
    public static void delete(long id){
        MyApplication.getDaoInstant().getHistoryVideoDao().deleteByKey(id);
    }
    //删除某全部数据
    public static void deleteAll(List<HistoryVideo> list){
        MyApplication.getDaoInstant().getHistoryVideoDao().deleteAll();
    }
    //更新数据
    public static void updata(HistoryVideo historyVideo){
        MyApplication.getDaoInstant().getHistoryVideoDao().update(historyVideo);
    }
    //查询全部数据
    public static List<HistoryVideo> queryAll(){
        return MyApplication.getDaoInstant().getHistoryVideoDao().loadAll();
    }
    //查询条件为type=Updated_time的数据
    public static List<HistoryVideo> queryVideo(){
        return MyApplication.getDaoInstant().getHistoryVideoDao()
                .queryBuilder()
                .where(HistoryVideoDao.Properties.Updated_time.eq(HistoryVideo.class)).list();
    }
}
