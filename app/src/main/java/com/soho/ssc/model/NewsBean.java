package com.soho.ssc.model;

import java.util.List;

/**
 * @author dell
 * @data 2018/1/4.
 */

public class NewsBean {

    /**
     * limit : 20
     * now : 2018-01-04T11:55:27.327450+08:00
     * offset : 0
     * ok : true
     * result : [{"author":{"amended_reliability":"0","avatar":{"large":"https://1-im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/160/h/160","normal":"https://1-im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/48/h/48","small":"https://1-im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/24/h/24"},"followers_count":41,"gender":"male","is_exists":true,"is_title_authorized":false,"master_category":"normal","nickname":"猛犸","resource_url":"http://apis.guokr.com/community/user/6xod6s.json","title":"","ukey":"6xod6s","url":"http://www.guokr.com/i/0419361220/"},"authors":[{"amended_reliability":"0","avatar":{"large":"https://1-im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/160/h/160","normal":"https://1-im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/48/h/48","small":"https://1-im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/24/h/24"},"followers_count":41,"gender":"male","is_exists":true,"is_title_authorized":false,"master_category":"normal","nickname":"猛犸","resource_url":"http://apis.guokr.com/community/user/6xod6s.json","title":"","ukey":"6xod6s","url":"http://www.guokr.com/i/0419361220/"}],"channel_keys":["hot"],"channels":[{"articles_count":1852,"date_created":"2014-05-23T16:22:09.282129+08:00","key":"hot","name":"热点","url":"https://www.guokr.com/scientific/channel/hot/"}],"copyright":"owned_by_guokr","date_created":"2017-12-28T11:50:27+08:00","date_modified":"2017-12-29T10:44:01.802431+08:00","date_published":"2017-12-28T11:50:27+08:00","id":442577,"image":"","image_description":"","image_info":{"height":155,"url":"https://2-im.guokr.com/hd9j3BQ8JbTe5rTT87grpVz1Ils3mJBuFtTdeVks275KAQAAmwAAAEpQ.jpg","width":330},"is_author_external":false,"is_replyable":true,"is_show_summary":false,"minisite":{"date_created":"2010-10-20T16:20:44+08:00","icon":"https://3-im.guokr.com/TPdfxoaxAGOhbhf2mHZsjvnXSCsT8dtGv0ItDjLiAmBuAAAAWgAAAEpQ.jpg","introduction":"这里报道最新、最酷的资讯，带你快速了解世界科研一线成就","key":"digest","name":"环球科技观光团","url":"https://www.guokr.com/site/digest/"},"minisite_key":"digest","preface":"","recommends_count":0,"replies_count":77,"resource_url":"https://apis.guokr.com/minisite/article/442577.json","small_image":"https://2-im.guokr.com/hd9j3BQ8JbTe5rTT87grpVz1Ils3mJBuFtTdeVks275KAQAAmwAAAEpQ.jpg","subject":{"articles_count":775,"date_created":"2014-05-23T16:22:09.282129+08:00","key":"electronics","name":"电子","url":"https://www.guokr.com/scientific/subject/electronics/"},"subject_key":"electronics","summary":"苹果限速旧手机，是故意的吗？实际上，这个锅应该由锂电池来背。","tags":["iPhone","苹果"],"title":"为手机限速，苹果真的是邪恶巨头吗？","title_hide":"为手机限速，苹果真的是邪恶巨头吗？","ukey_author":"6xod6s","url":"https://www.guokr.com/article/442577/","video_content":""}]
     * total : 1852
     */

    private int limit;
    private String now;
    private int offset;
    private boolean ok;
    private int total;
    private List<ResultBean> result;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * author : {"amended_reliability":"0","avatar":{"large":"https://1-im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/160/h/160","normal":"https://1-im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/48/h/48","small":"https://1-im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/24/h/24"},"followers_count":41,"gender":"male","is_exists":true,"is_title_authorized":false,"master_category":"normal","nickname":"猛犸","resource_url":"http://apis.guokr.com/community/user/6xod6s.json","title":"","ukey":"6xod6s","url":"http://www.guokr.com/i/0419361220/"}
         * authors : [{"amended_reliability":"0","avatar":{"large":"https://1-im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/160/h/160","normal":"https://1-im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/48/h/48","small":"https://1-im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/24/h/24"},"followers_count":41,"gender":"male","is_exists":true,"is_title_authorized":false,"master_category":"normal","nickname":"猛犸","resource_url":"http://apis.guokr.com/community/user/6xod6s.json","title":"","ukey":"6xod6s","url":"http://www.guokr.com/i/0419361220/"}]
         * channel_keys : ["hot"]
         * channels : [{"articles_count":1852,"date_created":"2014-05-23T16:22:09.282129+08:00","key":"hot","name":"热点","url":"https://www.guokr.com/scientific/channel/hot/"}]
         * copyright : owned_by_guokr
         * date_created : 2017-12-28T11:50:27+08:00
         * date_modified : 2017-12-29T10:44:01.802431+08:00
         * date_published : 2017-12-28T11:50:27+08:00
         * id : 442577
         * image :
         * image_description :
         * image_info : {"height":155,"url":"https://2-im.guokr.com/hd9j3BQ8JbTe5rTT87grpVz1Ils3mJBuFtTdeVks275KAQAAmwAAAEpQ.jpg","width":330}
         * is_author_external : false
         * is_replyable : true
         * is_show_summary : false
         * minisite : {"date_created":"2010-10-20T16:20:44+08:00","icon":"https://3-im.guokr.com/TPdfxoaxAGOhbhf2mHZsjvnXSCsT8dtGv0ItDjLiAmBuAAAAWgAAAEpQ.jpg","introduction":"这里报道最新、最酷的资讯，带你快速了解世界科研一线成就","key":"digest","name":"环球科技观光团","url":"https://www.guokr.com/site/digest/"}
         * minisite_key : digest
         * preface :
         * recommends_count : 0
         * replies_count : 77
         * resource_url : https://apis.guokr.com/minisite/article/442577.json
         * small_image : https://2-im.guokr.com/hd9j3BQ8JbTe5rTT87grpVz1Ils3mJBuFtTdeVks275KAQAAmwAAAEpQ.jpg
         * subject : {"articles_count":775,"date_created":"2014-05-23T16:22:09.282129+08:00","key":"electronics","name":"电子","url":"https://www.guokr.com/scientific/subject/electronics/"}
         * subject_key : electronics
         * summary : 苹果限速旧手机，是故意的吗？实际上，这个锅应该由锂电池来背。
         * tags : ["iPhone","苹果"]
         * title : 为手机限速，苹果真的是邪恶巨头吗？
         * title_hide : 为手机限速，苹果真的是邪恶巨头吗？
         * ukey_author : 6xod6s
         * url : https://www.guokr.com/article/442577/
         * video_content :
         */

        private AuthorBean author;
        private String copyright;
        private String date_created;
        private String date_modified;
        private String date_published;
        private int id;
        private String image;
        private String image_description;
        private ImageInfoBean image_info;
        private boolean is_author_external;
        private boolean is_replyable;
        private boolean is_show_summary;
        private MinisiteBean minisite;
        private String minisite_key;
        private String preface;
        private int recommends_count;
        private int replies_count;
        private String resource_url;
        private String small_image;
        private SubjectBean subject;
        private String subject_key;
        private String summary;
        private String title;
        private String title_hide;
        private String ukey_author;
        private String url;
        private String video_content;
        private List<String> channel_keys;
        private List<ChannelsBean> channels;
        private List<String> tags;

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

        public String getDate_created() {
            return date_created;
        }

        public void setDate_created(String date_created) {
            this.date_created = date_created;
        }

        public String getDate_modified() {
            return date_modified;
        }

        public void setDate_modified(String date_modified) {
            this.date_modified = date_modified;
        }

        public String getDate_published() {
            return date_published;
        }

        public void setDate_published(String date_published) {
            this.date_published = date_published;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getImage_description() {
            return image_description;
        }

        public void setImage_description(String image_description) {
            this.image_description = image_description;
        }

        public ImageInfoBean getImage_info() {
            return image_info;
        }

        public void setImage_info(ImageInfoBean image_info) {
            this.image_info = image_info;
        }

        public boolean isIs_author_external() {
            return is_author_external;
        }

        public void setIs_author_external(boolean is_author_external) {
            this.is_author_external = is_author_external;
        }

        public boolean isIs_replyable() {
            return is_replyable;
        }

        public void setIs_replyable(boolean is_replyable) {
            this.is_replyable = is_replyable;
        }

        public boolean isIs_show_summary() {
            return is_show_summary;
        }

        public void setIs_show_summary(boolean is_show_summary) {
            this.is_show_summary = is_show_summary;
        }

        public MinisiteBean getMinisite() {
            return minisite;
        }

        public void setMinisite(MinisiteBean minisite) {
            this.minisite = minisite;
        }

        public String getMinisite_key() {
            return minisite_key;
        }

        public void setMinisite_key(String minisite_key) {
            this.minisite_key = minisite_key;
        }

        public String getPreface() {
            return preface;
        }

        public void setPreface(String preface) {
            this.preface = preface;
        }

        public int getRecommends_count() {
            return recommends_count;
        }

        public void setRecommends_count(int recommends_count) {
            this.recommends_count = recommends_count;
        }

        public int getReplies_count() {
            return replies_count;
        }

        public void setReplies_count(int replies_count) {
            this.replies_count = replies_count;
        }

        public String getResource_url() {
            return resource_url;
        }

        public void setResource_url(String resource_url) {
            this.resource_url = resource_url;
        }

        public String getSmall_image() {
            return small_image;
        }

        public void setSmall_image(String small_image) {
            this.small_image = small_image;
        }

        public SubjectBean getSubject() {
            return subject;
        }

        public void setSubject(SubjectBean subject) {
            this.subject = subject;
        }

        public String getSubject_key() {
            return subject_key;
        }

        public void setSubject_key(String subject_key) {
            this.subject_key = subject_key;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle_hide() {
            return title_hide;
        }

        public void setTitle_hide(String title_hide) {
            this.title_hide = title_hide;
        }

        public String getUkey_author() {
            return ukey_author;
        }

        public void setUkey_author(String ukey_author) {
            this.ukey_author = ukey_author;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getVideo_content() {
            return video_content;
        }

        public void setVideo_content(String video_content) {
            this.video_content = video_content;
        }

        public List<String> getChannel_keys() {
            return channel_keys;
        }

        public void setChannel_keys(List<String> channel_keys) {
            this.channel_keys = channel_keys;
        }

        public List<ChannelsBean> getChannels() {
            return channels;
        }

        public void setChannels(List<ChannelsBean> channels) {
            this.channels = channels;
        }

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public static class AuthorBean {
            /**
             * amended_reliability : 0
             * avatar : {"large":"https://1-im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/160/h/160","normal":"https://1-im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/48/h/48","small":"https://1-im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/24/h/24"}
             * followers_count : 41
             * gender : male
             * is_exists : true
             * is_title_authorized : false
             * master_category : normal
             * nickname : 猛犸
             * resource_url : http://apis.guokr.com/community/user/6xod6s.json
             * title :
             * ukey : 6xod6s
             * url : http://www.guokr.com/i/0419361220/
             */

            private String amended_reliability;
            private AvatarBean avatar;
            private int followers_count;
            private String gender;
            private boolean is_exists;
            private boolean is_title_authorized;
            private String master_category;
            private String nickname;
            private String resource_url;
            private String title;
            private String ukey;
            private String url;

            public String getAmended_reliability() {
                return amended_reliability;
            }

            public void setAmended_reliability(String amended_reliability) {
                this.amended_reliability = amended_reliability;
            }

            public AvatarBean getAvatar() {
                return avatar;
            }

            public void setAvatar(AvatarBean avatar) {
                this.avatar = avatar;
            }

            public int getFollowers_count() {
                return followers_count;
            }

            public void setFollowers_count(int followers_count) {
                this.followers_count = followers_count;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public boolean isIs_exists() {
                return is_exists;
            }

            public void setIs_exists(boolean is_exists) {
                this.is_exists = is_exists;
            }

            public boolean isIs_title_authorized() {
                return is_title_authorized;
            }

            public void setIs_title_authorized(boolean is_title_authorized) {
                this.is_title_authorized = is_title_authorized;
            }

            public String getMaster_category() {
                return master_category;
            }

            public void setMaster_category(String master_category) {
                this.master_category = master_category;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getResource_url() {
                return resource_url;
            }

            public void setResource_url(String resource_url) {
                this.resource_url = resource_url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUkey() {
                return ukey;
            }

            public void setUkey(String ukey) {
                this.ukey = ukey;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public static class AvatarBean {
                /**
                 * large : https://1-im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/160/h/160
                 * normal : https://1-im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/48/h/48
                 * small : https://1-im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/24/h/24
                 */

                private String large;
                private String normal;
                private String small;

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getNormal() {
                    return normal;
                }

                public void setNormal(String normal) {
                    this.normal = normal;
                }

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }
            }
        }

        public static class ImageInfoBean {
            /**
             * height : 155
             * url : https://2-im.guokr.com/hd9j3BQ8JbTe5rTT87grpVz1Ils3mJBuFtTdeVks275KAQAAmwAAAEpQ.jpg
             * width : 330
             */

            private int height;
            private String url;
            private int width;

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }
        }

        public static class MinisiteBean {
            /**
             * date_created : 2010-10-20T16:20:44+08:00
             * icon : https://3-im.guokr.com/TPdfxoaxAGOhbhf2mHZsjvnXSCsT8dtGv0ItDjLiAmBuAAAAWgAAAEpQ.jpg
             * introduction : 这里报道最新、最酷的资讯，带你快速了解世界科研一线成就
             * key : digest
             * name : 环球科技观光团
             * url : https://www.guokr.com/site/digest/
             */

            private String date_created;
            private String icon;
            private String introduction;
            private String key;
            private String name;
            private String url;

            public String getDate_created() {
                return date_created;
            }

            public void setDate_created(String date_created) {
                this.date_created = date_created;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

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

        public static class SubjectBean {
            /**
             * articles_count : 775
             * date_created : 2014-05-23T16:22:09.282129+08:00
             * key : electronics
             * name : 电子
             * url : https://www.guokr.com/scientific/subject/electronics/
             */

            private int articles_count;
            private String date_created;
            private String key;
            private String name;
            private String url;

            public int getArticles_count() {
                return articles_count;
            }

            public void setArticles_count(int articles_count) {
                this.articles_count = articles_count;
            }

            public String getDate_created() {
                return date_created;
            }

            public void setDate_created(String date_created) {
                this.date_created = date_created;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

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

        public static class AuthorsBean {
            /**
             * amended_reliability : 0
             * avatar : {"large":"https://1-im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/160/h/160","normal":"https://1-im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/48/h/48","small":"https://1-im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/24/h/24"}
             * followers_count : 41
             * gender : male
             * is_exists : true
             * is_title_authorized : false
             * master_category : normal
             * nickname : 猛犸
             * resource_url : http://apis.guokr.com/community/user/6xod6s.json
             * title :
             * ukey : 6xod6s
             * url : http://www.guokr.com/i/0419361220/
             */

            private String amended_reliability;
            private AvatarBeanX avatar;
            private int followers_count;
            private String gender;
            private boolean is_exists;
            private boolean is_title_authorized;
            private String master_category;
            private String nickname;
            private String resource_url;
            private String title;
            private String ukey;
            private String url;

            public String getAmended_reliability() {
                return amended_reliability;
            }

            public void setAmended_reliability(String amended_reliability) {
                this.amended_reliability = amended_reliability;
            }

            public AvatarBeanX getAvatar() {
                return avatar;
            }

            public void setAvatar(AvatarBeanX avatar) {
                this.avatar = avatar;
            }

            public int getFollowers_count() {
                return followers_count;
            }

            public void setFollowers_count(int followers_count) {
                this.followers_count = followers_count;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public boolean isIs_exists() {
                return is_exists;
            }

            public void setIs_exists(boolean is_exists) {
                this.is_exists = is_exists;
            }

            public boolean isIs_title_authorized() {
                return is_title_authorized;
            }

            public void setIs_title_authorized(boolean is_title_authorized) {
                this.is_title_authorized = is_title_authorized;
            }

            public String getMaster_category() {
                return master_category;
            }

            public void setMaster_category(String master_category) {
                this.master_category = master_category;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getResource_url() {
                return resource_url;
            }

            public void setResource_url(String resource_url) {
                this.resource_url = resource_url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUkey() {
                return ukey;
            }

            public void setUkey(String ukey) {
                this.ukey = ukey;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public static class AvatarBeanX {
                /**
                 * large : https://1-im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/160/h/160
                 * normal : https://1-im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/48/h/48
                 * small : https://1-im.guokr.com/TL27-S81EuoahCN7pVlXlzCI6I07ORoBQCo7fDv5EUqgAAAAoAAAAFBO.png?imageView2/1/w/24/h/24
                 */

                private String large;
                private String normal;
                private String small;

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getNormal() {
                    return normal;
                }

                public void setNormal(String normal) {
                    this.normal = normal;
                }

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }
            }
        }

        public static class ChannelsBean {
            /**
             * articles_count : 1852
             * date_created : 2014-05-23T16:22:09.282129+08:00
             * key : hot
             * name : 热点
             * url : https://www.guokr.com/scientific/channel/hot/
             */

            private int articles_count;
            private String date_created;
            private String key;
            private String name;
            private String url;

            public int getArticles_count() {
                return articles_count;
            }

            public void setArticles_count(int articles_count) {
                this.articles_count = articles_count;
            }

            public String getDate_created() {
                return date_created;
            }

            public void setDate_created(String date_created) {
                this.date_created = date_created;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

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
}
