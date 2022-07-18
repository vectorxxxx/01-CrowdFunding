package com.vectorx.crowdfunding.entity.vo;

import java.util.List;
import java.util.Map;

public class Student
{
    private String stuId;
    private String stuName;
    private Address address;
    private List<Subject> subjectList;
    private Map<String, String> map;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Student{" + "stuId='" + stuId + '\'' + ", stuName='" + stuName + '\'' + ", address=" + address + ", subjectList=" + subjectList + ", map=" + map + '}';
    }
}
