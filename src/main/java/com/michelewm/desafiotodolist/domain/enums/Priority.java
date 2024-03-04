package com.michelewm.desafiotodolist.domain.enums;

public enum Priority {
    HIGH(0),
    AVERAGE(1),
    LOW(2);

    private int code;

    private Priority(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Priority valueOf(int code){
        for (Priority valuePriority : Priority.values()){
            if (valuePriority.getCode() == code){
                return valuePriority;
            }
        }
        throw new IllegalArgumentException("Invalid Priority code" + code);
    }
}
