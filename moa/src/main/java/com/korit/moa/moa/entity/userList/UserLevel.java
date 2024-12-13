package com.korit.moa.moa.entity.userList;

import lombok.Getter;
import lombok.Setter;

public enum UserLevel {
    관리자("관리자"),
    우수회원("우수회원"),
    일반회원("일반호원");

    @Getter
    @Setter
    private String status;

     UserLevel(String status) {
        this.status = status;
    }

}

