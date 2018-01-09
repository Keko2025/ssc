package com.soho.ssc.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by dell on 2017/4/27.
 * desc：历史记录bean 每个bean对应一张表
 */
@Entity
public class HistoryVideo {
    //不能用int  @Id：对象的Id，使用Long类型作为EntityId，否则会报错。(autoincrement = true)表示主键会自增，如果false就会使用旧值
    @Id(autoincrement = true)
    private long id;

    //@Unique：该属性值必须在数据库中是唯一值
    @Unique
    private String name;

    //图片url
    private String poster;

    //更新时间 @Property：可以自定义字段名，注意外键不能使用该属性
    @Property(nameInDb = "updated_time")
    private String updated_time;

    //是否是pro视频
    private boolean is_pro;

    //当前播放时长
    private int current;
    @Generated(hash = 14273793)
    public HistoryVideo(long id, String name, String poster, String updated_time,
                        boolean is_pro, int current) {
        this.id = id;
        this.name = name;
        this.poster = poster;
        this.updated_time = updated_time;
        this.is_pro = is_pro;
        this.current = current;
    }
    @Generated(hash = 717473908)
    public HistoryVideo() {}
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPoster() {
        return this.poster;
    }
    public void setPoster(String poster) {
        this.poster = poster;
    }
    public String getUpdated_time() {
        return this.updated_time;
    }
    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }
    public boolean getIs_pro() {
        return this.is_pro;
    }
    public void setIs_pro(boolean is_pro) {
        this.is_pro = is_pro;
    }
    public int getCurrent() {
        return this.current;
    }
    public void setCurrent(int current) {
        this.current = current;
    }
}
