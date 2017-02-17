package com.example.ec.model;

import java.util.List;

/**
 * Created by yzz on 2016/8/31.
 */
public class Pojo {
    /**
     * total : 227
     * result : [{"famous_name":"车尔尼雪夫斯基","famous_saying":"非凡的单纯，非凡的明确\u2014\u2014这是天才的智慧的最可惊人的品质。"},{"famous_name":"约·德莱顿","famous_saying":"天才在社会生活中往往显得迟钝而"},{"famous_name":"雨果","famous_saying":"敢于冲撞命运才是天才"},{"famous_name":"卡莱尔","famous_saying":"所谓天才，就是比任何人都先抵挡痛苦的经验本领。"},{"famous_name":"林肯","famous_saying":"卓越的天才不屑走一条人家走过的路。他寻找迄今没有开拓过的地区。"}]
     * error_code : 0
     * reason : Succes
     */

    private int total;
    private int error_code;
    private String reason;
    /**
     * famous_name : 车尔尼雪夫斯基
     * famous_saying : 非凡的单纯，非凡的明确——这是天才的智慧的最可惊人的品质。
     */

    private List<ResultBean> result;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String famous_name;
        private String famous_saying;

        public String getFamous_name() {
            return famous_name;
        }

        public void setFamous_name(String famous_name) {
            this.famous_name = famous_name;
        }

        public String getFamous_saying() {
            return famous_saying;
        }

        public void setFamous_saying(String famous_saying) {
            this.famous_saying = famous_saying;
        }
    }
}
