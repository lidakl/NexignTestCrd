package com.klochkova.enums;

public enum TariffType {
    UNLIMITED("06"),
    HALF_MINUTE("03"),
    ORDINARY("11");

    private final String tariffCode;

    private TariffType(String tariffCode) {
        this.tariffCode = tariffCode;
    }

    public String getTariffCode() {
        return this.tariffCode;
    }

    public static TariffType getTariffType(String tariffCode) {
        switch (tariffCode) {
            case "06": return TariffType.UNLIMITED;
            case "03": return TariffType.HALF_MINUTE;
            case "11": return TariffType.ORDINARY;
        }
        return null;
    }
}
