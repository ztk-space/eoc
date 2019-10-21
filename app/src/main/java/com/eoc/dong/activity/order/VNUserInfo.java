package com.eoc.dong.activity.order;

/**
 * Created by CodeFatCat on 2019/4/30
 */
public class VNUserInfo {
    private AuthBasicBean authBasic;
    private AuthIdentityBean authIdentity;
    private UserBean user;

    public class UserBean{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public AuthIdentityBean getAuthIdentity() {
        return authIdentity;
    }

    public void setAuthIdentity(AuthIdentityBean authIdentity) {
        this.authIdentity = authIdentity;
    }

    public AuthBasicBean getAuthBasic() {
        return authBasic;
    }

    public void setAuthBasic(AuthBasicBean authBasic) {
        this.authBasic = authBasic;
    }
    public class AuthIdentityBean{
        private String name;
        private String identityNum;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdentityNum() {
            return identityNum;
        }

        public void setIdentityNum(String identityNum) {
            this.identityNum = identityNum;
        }
    }
    public class AuthBasicBean{
        private String id;
        private String userId;
        private String email;
        private String province;
        private String liveCounty;
        private String homeAddr;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getLiveCounty() {
            return liveCounty;
        }

        public void setLiveCounty(String liveCounty) {
            this.liveCounty = liveCounty;
        }

        public String getHomeAddr() {
            return homeAddr;
        }

        public void setHomeAddr(String homeAddr) {
            this.homeAddr = homeAddr;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
