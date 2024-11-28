package com.korit.moa.moa.controller;


import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.black_list.response.ResponseBlackListDto;
import com.korit.moa.moa.service.BlackListService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.BLACK_LIST)
@RequiredArgsConstructor
public class BlackListController {

    private  final BlackListService blackListService;
    public  static final String GET_BLACK_LIST = "/{groupId}";
    private static final String POST_BLACK_LIST = "/{groupId}";
    private static final String DEL_BLACK_LIST = "/{blackListId}";


    @GetMapping(GET_BLACK_LIST)
    public ResponseEntity<ResponseDto<List<ResponseBlackListDto>>> getBlackList(
            @AuthenticationPrincipal String userId, @PathVariable Long groupId) {
        ResponseDto<List<ResponseBlackListDto>> response = blackListService.getBlackList(userId,groupId);
        HttpStatus status = response.isResult() ? HttpStatus.OK: HttpStatus.BAD_REQUEST;
        return  ResponseEntity.status(status).body(response);
    }

    @PostMapping(POST_BLACK_LIST)
    public ResponseEntity<ResponseDto<ResponseBlackListDto>> postBlackList(
        @PathVariable Long groupId,@AuthenticationPrincipal  String userId ) {
        ResponseDto<ResponseBlackListDto> response = blackListService.postBlackList(groupId,userId );
        HttpStatus status = response.isResult() ? HttpStatus.OK: HttpStatus.BAD_REQUEST;
        return  ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(DEL_BLACK_LIST)
    public ResponseEntity<ResponseDto<Void>> deleteBlackList(@PathVariable Long blackListId) {
        ResponseDto<Void> response = blackListService.deleteBlackList(blackListId);
        HttpStatus status = response.isResult() ? HttpStatus.OK: HttpStatus.BAD_REQUEST;
        return  ResponseEntity.status(status).body(response);
    }

}
