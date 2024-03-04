package com.michelewm.desafiotodolist.domain.enums;

public enum TaskStatus {
    TODO(0),
    DOING(1),
    DONE(2),
    CANCELLED(3);

    private int code;

    private TaskStatus(int code) { 
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static  TaskStatus valueOf(int code) {
        for (TaskStatus value : TaskStatus.values()){
            if (value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid status code: " + code);
    }
}
