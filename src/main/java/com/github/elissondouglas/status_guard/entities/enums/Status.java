package com.github.elissondouglas.status_guard.entities.enums;

public enum Status {
    ONLINE(1),
    OFFLINE(2),
    SLOW(3),
    UNKNOWN(4);

    private int code;


    Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    private static Status valueOf(int code) {
        for (Status value : Status.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid LoanStatus code");
    }

}
