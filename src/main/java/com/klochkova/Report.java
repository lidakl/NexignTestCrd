package com.klochkova;

import com.klochkova.enums.CallType;
import com.klochkova.enums.TariffType;
import com.klochkova.utils.Comparator;
import com.klochkova.utils.UtilDate;

import java.io.FileWriter;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.LinkedList;

public class Report {
    public static void fillReport(HashMap<String, LinkedList<DataRecord>> records) {
        Comparator comparator = new Comparator();

        for(LinkedList<DataRecord> list: records.values()) {
            list.sort(comparator);
            try (FileWriter writer = new FileWriter("reports/" + list.get(0).getPhone())){
                getCostByTariff(list.get(0).getTariffType(), list, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void getCostByTariff(TariffType tariff, LinkedList<DataRecord> list, FileWriter writer) throws IOException {
        getHeadTable(list.get(0), writer);
        double sumCost = 0.0;
        switch (tariff) {
            case UNLIMITED: {
                int sumMinutes = 0;
                double currentCost = 0.0;
                int maxMinutes = 300;
                for (DataRecord record: list) {
                    long minutes = record.getDateStart().toInstant().until(
                            record.getDateEnd().toInstant(), ChronoUnit.MINUTES);
                    sumMinutes += minutes;
                    if (sumMinutes > maxMinutes) {
                        currentCost = sumMinutes - maxMinutes - sumCost;
                        sumCost += currentCost;
                    }
                    getBodyTable(record, currentCost, writer);
                }
                sumCost += 100;
                break;
            }
            case HALF_MINUTE: {
                double currentCost = 0.0;
                for (DataRecord record: list) {
                    long minutes = record.getDateStart().toInstant().until(
                            record.getDateEnd().toInstant(), ChronoUnit.MINUTES);
                    currentCost = minutes * 1.5;
                    sumCost += currentCost;
                    getBodyTable(record, currentCost, writer);
                }
                break;
            }
            case ORDINARY: {
                int sumMinutes = 0;
                int maxMinutes = 100;
                double currentCost = 0.0;
                for (DataRecord record: list) {
                    long minutes = record.getDateStart().toInstant().until(
                            record.getDateEnd().toInstant(), ChronoUnit.MINUTES);
                    sumMinutes += minutes;
                    if (sumMinutes < maxMinutes && record.getCallType() == CallType.OUTGOING) {
                        currentCost = minutes * 0.5;
                        sumCost += currentCost;
                    } else if (record.getCallType() == CallType.OUTGOING) {
                        currentCost = minutes * 1.5;
                        sumCost += currentCost;
                    }
                    getBodyTable(record, currentCost, writer);
                }
                break;
            }
        }
        getFooterTable(sumCost, writer);
    }

    public static void getHeadTable(DataRecord record, FileWriter writer) throws IOException {
        writer.write("Tariff index: ".concat(record.getTariffType().getTariffCode()).concat("\n"));
        writer.write("----------------------------------------------------------------------------\n");
        writer.write(String.format("%-50s", "Report for phone number "
                .concat(record.getPhone())
                .concat(":")));
        writer.write("\n");
        writer.write("----------------------------------------------------------------------------\n");
        writer.write(String.format("%-12s%-22s%-22s%-11s%-8s", "| Call Type",
                "|   Start Time",
                "|     End Time", "| Duration", "| Cost"));
        writer.write("|\n");
        writer.write("----------------------------------------------------------------------------\n");
    }

    public static void getBodyTable(DataRecord record, Double cost, FileWriter writer) throws IOException {
        String startTimeStr = UtilDate.convertToString(record.getDateStart());
        String endTimeStr = UtilDate.convertToString(record.getDateEnd());

        String formatterDuration = UtilDate.getDuration(record.getDateStart(), record.getDateEnd());

        writer.write(String.format("%-12s%-22s%-22s%-11s| %5.2f |",
                "|     ".concat(record.getCallType().getCallCode()),
                "| ".concat(startTimeStr),
                "| ".concat(endTimeStr),
                "| ".concat(formatterDuration),
                cost));
        writer.write("\n");
    }

    public static void getFooterTable(Double totalSum, FileWriter writer) throws IOException {
        writer.write("----------------------------------------------------------------------------\n");
        writer.write(String.format("|                                           Total Cost: |    %6.2f rubles |\n",
                totalSum));
        writer.write("----------------------------------------------------------------------------\n");
    }
}
