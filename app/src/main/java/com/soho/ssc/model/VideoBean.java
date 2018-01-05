package com.soho.ssc.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author dell
 * @data 2018/1/4.
 */

public class VideoBean implements Serializable {
    /**
     * code : 200
     * data : {"entities":[{"channel":{"avatar":"http://7xoin6.com2.z0.glb.qiniucdn.com/channel-avatar-b0643616116946aa3005c514dda9ee83.jpeg?imageView/1/w/160/h/160","id":1,"name":"Bodybuilding.com"},"read_count":0,"duration":"03:00","id":61,"name":"Pro Sample Video","title":"ChangePro测试","played_count":8385,"poster":"http://7xogwp.com1.z0.glb.clouddn.com/video-poster-f76f938e7e07f181232166b71f003ce3.jpeg","pro":true,"published_at":"2017-05-01T03:02:39.000Z","type":"video","url":"http://7xoin7.com2.z0.glb.qiniucdn.com/video-5b336e2f7104c9eb0827142c375e341e.mp4"}]}
     * message : OK
     * toast :
     */

    private int code;
    private DataBean data;
    private String message;
    private String toast;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToast() {
        return toast;
    }

    public void setToast(String toast) {
        this.toast = toast;
    }

    public static class DataBean implements Serializable{
        private List<EntitiesBean> entities;

        private List<BannersBean> banners;

        public List<EntitiesBean> getEntities() {
            return entities;
        }

        public void setEntities(List<EntitiesBean> entities) {
            this.entities = entities;
        }

        public List<BannersBean> getBanners() {
            return banners;
        }

        public void setBanners(List<BannersBean> banners) {
            this.banners = banners;
        }

        public static class BannersBean  implements Serializable{
            /**
             * position : 1
             * photo_url : http://7xoin6.com2.z0.glb.qiniucdn.com//photo-932bcb12aeb95e2c57d825ca5142c1a9.jpeg?imageView/2/w/640
             */

            private int position;
            private String photo_url;
            private String link_url;

            public String getLink_url() {
                return link_url;
            }

            public void setLink_url(String link_url) {
                this.link_url = link_url;
            }

            public int getPosition() {
                return position;
            }

            public void setPosition(int position) {
                this.position = position;
            }

            public String getPhoto_url() {
                return photo_url;
            }

            public void setPhoto_url(String photo_url) {
                this.photo_url = photo_url;
            }
        }


        public static class EntitiesBean implements Serializable{
            /**
             * channel : {"avatar":"http://7xoin6.com2.z0.glb.qiniucdn.com/channel-avatar-b0643616116946aa3005c514dda9ee83.jpeg?imageView/1/w/160/h/160","id":1,"name":"Bodybuilding.com"}
             * read_count : 0
             * duration : 03:00
             * id : 61
             * name : Pro Sample Video
             * title : ChangePro测试
             * played_count : 8385
             * poster : http://7xogwp.com1.z0.glb.clouddn.com/video-poster-f76f938e7e07f181232166b71f003ce3.jpeg
             * pro : true
             * published_at : 2017-05-01T03:02:39.000Z
             * type : video
             * url : http://7xoin7.com2.z0.glb.qiniucdn.com/video-5b336e2f7104c9eb0827142c375e341e.mp4
             */

            private ChannelBean channel;
            private int read_count;
            private String duration;
            private int id;
            private String name;
            private String title;
            private int played_count;
            private String poster;
            private boolean pro;
            private String published_at;
            private String type;
            private String url;

            public ChannelBean getChannel() {
                return channel;
            }

            public void setChannel(ChannelBean channel) {
                this.channel = channel;
            }

            public int getRead_count() {
                return read_count;
            }

            public void setRead_count(int read_count) {
                this.read_count = read_count;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getPlayed_count() {
                return played_count;
            }

            public void setPlayed_count(int played_count) {
                this.played_count = played_count;
            }

            public String getPoster() {
                return poster;
            }

            public void setPoster(String poster) {
                this.poster = poster;
            }

            public boolean isPro() {
                return pro;
            }

            public void setPro(boolean pro) {
                this.pro = pro;
            }

            public String getPublished_at() {
                return published_at;
            }

            public void setPublished_at(String published_at) {
                this.published_at = published_at;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public static class ChannelBean implements Serializable{
                /**
                 * avatar : http://7xoin6.com2.z0.glb.qiniucdn.com/channel-avatar-b0643616116946aa3005c514dda9ee83.jpeg?imageView/1/w/160/h/160
                 * id : 1
                 * name : Bodybuilding.com
                 */

                private String avatar;
                private int id;
                private String name;

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}

