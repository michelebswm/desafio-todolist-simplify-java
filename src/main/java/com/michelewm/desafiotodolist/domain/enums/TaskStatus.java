package com.michelewm.desafiotodolist.domain.enums;

public enum TaskStatus {
    TODO(1),
    DOING(2),
    DONE(3),
    CANCELLED(4);

    private int code;

    TaskStatus(int code) { 
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static  TaskStatus valueOf(Integer code) {
        for (TaskStatus value : TaskStatus.values()){
            if (value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid status code: " + code);
    }
}
