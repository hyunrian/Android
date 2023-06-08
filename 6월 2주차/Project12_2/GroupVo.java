package com.kh.project12_2;

public class GroupVo {

    private String g_name;
    private int g_number;

    public GroupVo(String g_name, int g_number) {
        this.g_name = g_name;
        this.g_number = g_number;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    public int getG_number() {
        return g_number;
    }

    public void setG_number(int g_number) {
        this.g_number = g_number;
    }

    @Override
    public String toString() {
        return "GroupVo{" +
                "g_name='" + g_name + '\'' +
                ", g_number=" + g_number +
                '}';
    }
}
