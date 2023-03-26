package com.klochkova;
import com.klochkova.utils.FileReader;

import java.util.HashMap;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        HashMap<String, LinkedList<DataRecord>> records = FileReader.read("cdr.txt");
        Report.fillReport(records);
    }
}
