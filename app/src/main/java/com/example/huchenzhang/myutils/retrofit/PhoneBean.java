package com.example.huchenzhang.myutils.retrofit;

/**
 * 手机号
 * Created by hu on 2018/3/26.
 */

public class PhoneBean {

    /**
     * status : 0
     * msg : ok
     * result : {"shouji":"xxxxxxxxxx","province":"北京","city":"","company":"中国移动","cardtype":"GSM","areacode":"010"}
     */

    private String status;
    private String msg;
    private ResultBean result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * shouji : xxxxxxxxxx
         * province : 北京
         * city :
         * company : 中国移动
         * cardtype : GSM
         * areacode : 010
         */

        private String shouji;
        private String province;
        private String city;
        private String company;
        private String cardtype;
        private String areacode;

        public String getShouji() {
            return shouji;
        }

        public void setShouji(String shouji) {
            this.shouji = shouji;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getCardtype() {
            return cardtype;
        }

        public void setCardtype(String cardtype) {
            this.cardtype = cardtype;
        }

        public String getAreacode() {
            return areacode;
        }

        public void setAreacode(String areacode) {
            this.areacode = areacode;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "shouji='" + shouji + '\'' +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", company='" + company + '\'' +
                    ", cardtype='" + cardtype + '\'' +
                    ", areacode='" + areacode + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PhoneBean{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", result=" + result.toString() +
                '}';
    }
}
