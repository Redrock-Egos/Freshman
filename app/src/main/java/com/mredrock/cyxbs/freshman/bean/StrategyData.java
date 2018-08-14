package com.mredrock.cyxbs.freshman.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StrategyData {
    /**
     * array : [{"content":"仅供测试：黄焖Jimmy饭的描述\n换行测试：第二行内容\n换行测试：第三行内容","id":1,"name":"黄焖Jimmy饭","picture":["url47","url57"]},{"content":"仅供测试：红烧牛肉面的描述\n换行测试：第二行内容\n换行测试：第三行内容","id":2,"name":"红烧牛肉面","picture":["url48","url58"]},{"content":"仅供测试：重庆鸡公煲的描述\n换行测试：第二行内容\n换行测试：第三行内容","id":3,"name":"重庆鸡公煲","picture":["url49","url59"]},{"content":"仅供测试：无界的描述\n换行测试：第二行内容\n换行测试：第三行内容","id":4,"name":"无界","picture":["url50","url60"]},{"content":"仅供测试：柒露营的描述\n换行测试：第二行内容\n换行测试：第三行内容","id":5,"name":"柒露营","picture":["url51","url61"]},{"content":"仅供测试：城门老火锅的描述\n换行测试：第二行内容\n换行测试：第三行内容","id":6,"name":"城门老火锅","picture":["url52","url62"]},{"content":"仅供测试：古香缘火锅的描述\n换行测试：第二行内容\n换行测试：第三行内容","id":7,"name":"古香缘火锅","picture":["url53","url63"]},{"content":"仅供测试：洲际沾水米线的描述\n换行测试：第二行内容\n换行测试：第三行内容","id":8,"name":"周记沾水米线","picture":["url54","url64"]},{"content":"仅供测试：杨师傅串串的描述\n换行测试：第二行内容\n换行测试：第三行内容","id":9,"name":"杨师傅串串","picture":["url55","url65"]},{"content":"仅供测试：乡村鲜鸡汤的描述\n换行测试：第二行内容\n换行测试：第三行内容","id":10,"name":"乡村鲜鸡汤","picture":["url56","url66"]}]
     * index : 周边美食
     */

    @SerializedName("index")
    private String name;
    @SerializedName("array")
    private List<DetailData> details;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DetailData> getDetails() {
        return details;
    }

    public void setDetails(List<DetailData> details) {
        this.details = details;
    }

    public static class DetailData {
        /**
         * content : 仅供测试：黄焖Jimmy饭的描述
         换行测试：第二行内容
         换行测试：第三行内容
         * id : 1
         * name : 黄焖Jimmy饭
         * picture : ["url47","url57"]
         */

        private String content;
        private int id;
        private String name;
        private List<String> picture;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public List<String> getPicture() {
            return picture;
        }

        public void setPicture(List<String> picture) {
            this.picture = picture;
        }
    }
}
