package com.klochkova.utils;

import com.klochkova.DataRecord;

public class Comparator implements java.util.Comparator<DataRecord> {
    @Override
    public int compare(DataRecord d1, DataRecord d2) {
        return d1.getDateStart().compareTo(d2.getDateStart());
    }
}