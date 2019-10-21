package com.eoc.dong.common.network.response;

/**
 * Created by Administrator on 2017/12/20.
 */

public class AboutUsResponse {
    /**
     * qq : 65645454545
     * aboutXed : {"id":2,"aboutUs":"24234234123123123123123sdfdsf","servePhone":"wet1234214fffff44","wexin":"wet234324333"}
     */

    private String qq;
    private AboutXedBean aboutXed;

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public AboutXedBean getAboutXed() {
        return aboutXed;
    }

    public void setAboutXed(AboutXedBean aboutXed) {
        this.aboutXed = aboutXed;
    }

    public static class AboutXedBean {
        /**
         * id : 2
         * aboutUs : 24234234123123123123123sdfdsf
         * servePhone : wet1234214fffff44
         * wexin : wet234324333
         */

        private int id;
        private String aboutUs;
        private String servePhone;
        private String wexin;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAboutUs() {
            return aboutUs;
        }

        public void setAboutUs(String aboutUs) {
            this.aboutUs = aboutUs;
        }

        public String getServePhone() {
            return servePhone;
        }

        public void setServePhone(String servePhone) {
            this.servePhone = servePhone;
        }

        public String getWexin() {
            return wexin;
        }

        public void setWexin(String wexin) {
            this.wexin = wexin;
        }
    }
}
