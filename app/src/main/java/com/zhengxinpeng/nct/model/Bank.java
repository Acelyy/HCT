package com.zhengxinpeng.nct.model;

import cn.bmob.v3.BmobObject;

public class Bank extends BmobObject {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
