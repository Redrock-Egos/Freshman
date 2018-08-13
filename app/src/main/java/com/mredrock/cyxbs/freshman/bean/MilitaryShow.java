package com.mredrock.cyxbs.freshman.bean;

import java.util.List;

public class MilitaryShow {


    private List<VideoBean> video;
    private List<PictureBean> picture;

    public List<VideoBean> getVideo() {
        return video;
    }

    public void setVideo(List<VideoBean> video) {
        this.video = video;
    }

    public List<PictureBean> getPicture() {
        return picture;
    }

    public void setPicture(List<PictureBean> picture) {
        this.picture = picture;
    }

    public static class VideoBean {
        /**
         * name : 2016重庆邮电大学军训视频2
         * url : https://v.qq.com/x/page/r07539i9p1d.html?
         * video_pic : {"name":"UUID142","url":"url142"}
         */

        private String name;
        private String url;
        private VideoPicBean video_pic;

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

        public VideoPicBean getVideo_pic() {
            return video_pic;
        }

        public void setVideo_pic(VideoPicBean video_pic) {
            this.video_pic = video_pic;
        }

        public static class VideoPicBean {
            /**
             * name : UUID142
             * url : url142
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

    public static class PictureBean {
        /**
         * name : UUID1
         * url : url1
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
