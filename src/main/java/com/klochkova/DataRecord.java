package com.klochkova;

import com.klochkova.enums.CallType;
import com.klochkova.enums.TariffType;
import com.klochkova.utils.UtilDate;

import java.text.ParseException;
import java.util.Date;

public class DataRecord {
    private String phone;
    private CallType callType;
    private Date dateStart;
    private Date dateEnd;
    private TariffType tariffType;

    public DataRecord(CallType callType, String phone, Date dateStart, Date dateEnd, TariffType tariffType) {
        this.phone = phone;
        this.callType = callType;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.tariffType = tariffType;
    }

    public static DataRecord init(String record) throws ParseException {
        String[] data = record.split(", ");
        return new DataRecord(CallType.getCallType(data[0]), data[1], UtilDate.convertToDate(data[2]), UtilDate.convertToDate(data[3]),
                TariffType.getTariffType(data[4]));
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CallType getCallType() {
        return callType;
    }

    public void setCallType(CallType callType) {
        this.callType = callType;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public TariffType getTariffType() {
        return tariffType;
    }

    public void setTariffType(TariffType tariffType) {
        this.tariffType = tariffType;
    }
}
