package com.michelewm.desafiotodolist.domain.enums;

public enum Priority {
    HIGH(1),
    AVERAGE(2),
    LOW(3)

    private int code;

    Priority(int code){
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
        throw new IllegalArgumentException("Invalid priority code");
    }
}
