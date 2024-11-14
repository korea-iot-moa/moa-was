package com.korit.moa.moa.common.constant;

public class ApiMappingPattern {
    // 인증 절차
    // : 회원가입, 로그인, 검색
    public static final String AUTH = "/api/v1/auth";

    // 회원 관련
    public static final String USER = "/api/v1/users";

    //로그인 모임 관련
    public static final String GROUP = "/api/v1/meeting-group";

    // 추천 관련
    public static final String RECOMMENDATION = "/api/v1/recommendation";

    // 유저 리스트(모임 내 유저)
    public static final String USER_LIST = "/api/v1/user-list";

    // 모임 참여 요청 관련 로직
    public static final String USER_ANSWER = "/api/v1/user-answers";

    // 후기 관련
    public static final String REVIEW = "/api/v1/reviews";

    // 투표 관련
    public static final String VOTE = "/api/v1/votes";

    // 투표 답변 관련
    public static final String VOTE_RESULT = "/api/v1/vote-results";

    // 신고 관련
    public static final String REPORT = "/api/v1/reports";

    // 블랙 리스트
    public static final String BLACK_LIST = "/api/v1/black-list";

    // 게시판 관련
    public static final String NOTICE = "/api/v1/notices";
}
