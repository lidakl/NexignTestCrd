package com.klochkova.utils;

import com.klochkova.DataRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;

public class FileReader {
    public static HashMap<String, LinkedList<DataRecord>> read(String fileName) {
        HashMap<String, LinkedList<DataRecord>> records = new HashMap<>();
        try(java.io.FileReader reader = new java.io.FileReader("cdr.txt")) {
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            while (line != null) {
                DataRecord record = DataRecord.init(line);
                String phone = record.getPhone();
                if(!records.containsKey(phone)) {
                    records.put(phone, new LinkedList<>());
                }
                records.get(phone).add(record);
                line = br.readLine();
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return records;
    }
}
