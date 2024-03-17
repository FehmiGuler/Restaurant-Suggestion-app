package org.n11_bootcamp.user_service.enums;

public enum EnumRate {
    RATE_1(1),
    RATE_2(2),
    RATE_3(3),
    RATE_4(4),
    RATE_5(5);
    private final int value;

    EnumRate(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
