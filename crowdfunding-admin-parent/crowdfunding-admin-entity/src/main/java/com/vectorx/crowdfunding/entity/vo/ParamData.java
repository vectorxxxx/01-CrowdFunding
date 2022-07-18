package com.vectorx.crowdfunding.entity.vo;

import java.util.List;

public class ParamData
{
    private List<String> array;

    public List<String> getArray() {
        return array;
    }

    public void setArray(List<String> array) {
        this.array = array;
    }

    @Override
    public String toString() {
        return "ParamData{" + "array=" + array + '}';
    }
}
