package org.data;

public enum BonusType {
    DEPOSIT("DEPOSIT"),
    NO_DEPOSIT("NO_DEPOSIT"),
    RELOAD_DEPOSIT("RELOAD_DEPOSIT"),
    RELOAD_NO_DEPOSIT("RELOAD_NO_DEPOSIT");

    private final String value;

    BonusType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
