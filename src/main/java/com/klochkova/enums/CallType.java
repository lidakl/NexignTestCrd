package com.klochkova.enums;

public enum CallType {
    INCOMING("01"),
    OUTGOING("02");

    private final String callCode;

    private CallType(String callCode) {
        this.callCode = callCode;
    }

    public String getCallCode() {
        return this.callCode;
    }

    public static CallType getCallType(String callCode) {
        switch (callCode) {
            case "01": return CallType.INCOMING;
            case "02": return CallType.OUTGOING;
        }
        return null;
    }
}
