package com.soho.ssc.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author dell
 * @data 2018/1/1.
 */

public class ResultBean implements Serializable{

    /**
     * code : pl3
     * data : [{"expect":"2017358","opencode":"9,5,1","opentime":"2017-12-31 20:32:00","opentimestamp":1514723520},{"expect":"2017357","opencode":"8,0,9","opentime":"2017-12-30 20:32:00","opentimestamp":1514637120},{"expect":"2017356","opencode":"9,5,2","opentime":"2017-12-29 20:32:00","opentimestamp":1514550720},{"expect":"2017355","opencode":"3,8,4","opentime":"2017-12-28 20:32:00","opentimestamp":1514464320},{"expect":"2017354","opencode":"1,8,7","opentime":"2017-12-27 20:32:00","opentimestamp":1514377920},{"expect":"2017353","opencode":"3,0,4","opentime":"2017-12-26 20:32:00","opentimestamp":1514291520},{"expect":"2017352","opencode":"9,9,4","opentime":"2017-12-25 20:32:00","opentimestamp":1514205120},{"expect":"2017351","opencode":"2,6,0","opentime":"2017-12-24 20:32:00","opentimestamp":1514118720},{"expect":"2017350","opencode":"1,2,8","opentime":"2017-12-23 20:32:00","opentimestamp":1514032320},{"expect":"2017349","opencode":"1,5,6","opentime":"2017-12-22 20:32:00","opentimestamp":1513945920}]
     * info : 免费接口随机延迟3-6分钟，实时接口请访问www.opencai.net查询、购买或续费
     * rows : 10
     */

    private String code;
    private String info;
    private int rows;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * expect : 2017358
         * opencode : 9,5,1
         * opentime : 2017-12-31 20:32:00
         * opentimestamp : 1514723520
         */

        private String expect;
        private String opencode;
        private String opentime;
        private int opentimestamp;

        public String getExpect() {
            return expect;
        }

        public void setExpect(String expect) {
            this.expect = expect;
        }

        public String getOpencode() {
            return opencode;
        }

        public void setOpencode(String opencode) {
            this.opencode = opencode;
        }

        public String getOpentime() {
            return opentime;
        }

        public void setOpentime(String opentime) {
            this.opentime = opentime;
        }

        public int getOpentimestamp() {
            return opentimestamp;
        }

        public void setOpentimestamp(int opentimestamp) {
            this.opentimestamp = opentimestamp;
        }
    }
}
