package com.example.lenovo.oschina.modle.enitity.kaiyuanruanjian;

import java.util.List;

/**
 * Created by admin on 2017/4/16.
 */

public class FenLeiBean {

    private String softwarecount;
    private List<SoftwareTypeBean> softwareTypes;

    public String getSoftwarecount() {
        return softwarecount;
    }

    public void setSoftwarecount(String softwarecount) {
        this.softwarecount = softwarecount;
    }

    public List<SoftwareTypeBean> getSoftwareTypes() {
        return softwareTypes;
    }

    public void setSoftwareTypes(List<SoftwareTypeBean> softwareTypes) {
        this.softwareTypes = softwareTypes;
    }

    public static class SoftwareTypeBean {
        private String name;
        private String tag;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }
}
