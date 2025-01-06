package com.korit.moa.moa.dto.mail;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMailRequestDto {
    private String userId;

    private String userName;

    private String email;
}
