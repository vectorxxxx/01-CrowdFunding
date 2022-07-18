package com.vectorx.crowdfunding.entity.vo;

public class Subject
{
    private String subName;
    private Integer subScore;

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public Integer getSubScore() {
        return subScore;
    }

    public void setSubScore(Integer subScore) {
        this.subScore = subScore;
    }

    @Override
    public String toString() {
        return "Subject{" + "subName='" + subName + '\'' + ", subScore='" + subScore + '\'' + '}';
    }
}
