package com.kh.listviewex2;

public class NationDto {
    int drawable;
    String nation, capital;

    public NationDto(int drawable, String nation, String capital) {
        this.drawable = drawable;
        this.nation = nation;
        this.capital = capital;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    @Override
    public String toString() {
        return "NationDto{" +
                "drawable=" + drawable +
                ", nation='" + nation + '\'' +
                ", capital='" + capital + '\'' +
                '}';
    }
}
