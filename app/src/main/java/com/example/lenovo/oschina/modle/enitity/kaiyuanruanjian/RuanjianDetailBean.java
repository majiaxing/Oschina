package com.example.lenovo.oschina.modle.enitity.kaiyuanruanjian;

/**
 * Created by Lenovo on 2017/5/24.
 */

public class RuanjianDetailBean {


    private SoftwareBean software;
    private NoticeBean notice;

    public SoftwareBean getSoftware() {
        return software;
    }

    public void setSoftware(SoftwareBean software) {
        this.software = software;
    }

    public NoticeBean getNotice() {
        return notice;
    }

    public void setNotice(NoticeBean notice) {
        this.notice = notice;
    }

    public static class SoftwareBean {
        private String id;
        private String title;
        private String url;
        private String extensionTitle;
        private String authorid;
        private String recommended;
        private String license;
        private String body;
        private String homepage;
        private String document;
        private String download;
        private String logo;
        private String language;
        private String os;
        private String recordtime;
        private String favorite;
        private String tweetCount;
        private String author;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getExtensionTitle() {
            return extensionTitle;
        }

        public void setExtensionTitle(String extensionTitle) {
            this.extensionTitle = extensionTitle;
        }

        public String getAuthorid() {
            return authorid;
        }

        public void setAuthorid(String authorid) {
            this.authorid = authorid;
        }

        public String getRecommended() {
            return recommended;
        }

        public void setRecommended(String recommended) {
            this.recommended = recommended;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getHomepage() {
            return homepage;
        }

        public void setHomepage(String homepage) {
            this.homepage = homepage;
        }

        public String getDocument() {
            return document;
        }

        public void setDocument(String document) {
            this.document = document;
        }

        public String getDownload() {
            return download;
        }

        public void setDownload(String download) {
            this.download = download;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getOs() {
            return os;
        }

        public void setOs(String os) {
            this.os = os;
        }

        public String getRecordtime() {
            return recordtime;
        }

        public void setRecordtime(String recordtime) {
            this.recordtime = recordtime;
        }

        public String getFavorite() {
            return favorite;
        }

        public void setFavorite(String favorite) {
            this.favorite = favorite;
        }

        public String getTweetCount() {
            return tweetCount;
        }

        public void setTweetCount(String tweetCount) {
            this.tweetCount = tweetCount;
        }
    }

    public static class NoticeBean {
        private String atmeCount;
        private String msgCount;
        private String reviewCount;
        private String newFansCount;
        private String newLikeCount;

        public String getAtmeCount() {
            return atmeCount;
        }

        public void setAtmeCount(String atmeCount) {
            this.atmeCount = atmeCount;
        }

        public String getMsgCount() {
            return msgCount;
        }

        public void setMsgCount(String msgCount) {
            this.msgCount = msgCount;
        }

        public String getReviewCount() {
            return reviewCount;
        }

        public void setReviewCount(String reviewCount) {
            this.reviewCount = reviewCount;
        }

        public String getNewFansCount() {
            return newFansCount;
        }

        public void setNewFansCount(String newFansCount) {
            this.newFansCount = newFansCount;
        }

        public String getNewLikeCount() {
            return newLikeCount;
        }

        public void setNewLikeCount(String newLikeCount) {
            this.newLikeCount = newLikeCount;
        }
    }
}
