package com.kh.prac12_6;

public class MyDiaryVo {
    String text, date;

    public MyDiaryVo(String date, String text) {
        this.text = text;
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MyDiaryDto{" +
                "text='" + text + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
