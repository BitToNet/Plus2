package com.example.plus2.demos.mvp2.bean;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2020-12-30   10:45
 * desc   :
 */
public class UserInfo extends BaseEntity {
    private String company;
    private String name;

    public UserInfo(String company, String name) {
        this.company = company;
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "company='" + company + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
