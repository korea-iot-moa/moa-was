package com.korit.moa.moa.controller;


import com.korit.moa.moa.common.constant.ApiMappingPattern;
import com.korit.moa.moa.dto.ResponseDto;
import com.korit.moa.moa.dto.black_list.response.ResponseBlackListDto;
import com.korit.moa.moa.dto.black_list.response.ResponseGetBlackListDto;
import com.korit.moa.moa.service.BlackListService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ApiMappingPattern.BLACK_LIST)
@RequiredArgsConstructor
public class BlackListController {

    private  final BlackListService blackListService;
    public  static final String GET_BLACK_LIST = "/{groupId}";
    private static final String POST_BLACK_LIST = "/{groupId}";

    @GetMapping(GET_BLACK_LIST)
    public ResponseEntity<ResponseDto<List<ResponseGetBlackListDto>>> getBlackList(@PathVariable Long groupId) {
        ResponseDto<List<ResponseGetBlackListDto>> response = blackListService.getBlackList(groupId);
        HttpStatus status = response.isResult() ? HttpStatus.OK: HttpStatus.BAD_REQUEST;
        return  ResponseEntity.status(status).body(response);
    }

    @PostMapping(POST_BLACK_LIST)
    public ResponseEntity<ResponseDto<ResponseBlackListDto>> postBlackList(
            @PathVariable Long groupId, @RequestBody Map<String, String> reqBody
    ) {
        ResponseDto<ResponseBlackListDto> response = blackListService.postBlackList(groupId, reqBody.get("userId"));
        HttpStatus status = response.isResult() ? HttpStatus.OK: HttpStatus.BAD_REQUEST;
        return  ResponseEntity.status(status).body(response);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto<Void>> deleteBlackList(
            @RequestParam  Long groupId,
            @RequestParam String userId
    ) {
        ResponseDto<Void> response = blackListService.deleteBlackList(groupId, userId);
        HttpStatus status = response.isResult() ? HttpStatus.OK: HttpStatus.BAD_REQUEST;
        return  ResponseEntity.status(status).body(response);
    }

}
