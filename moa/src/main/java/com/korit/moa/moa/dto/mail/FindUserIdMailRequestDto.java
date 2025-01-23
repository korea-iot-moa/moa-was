package com.korit.moa.moa.dto.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindUserIdMailRequestDto {

    private String phoneNumber;

    private String userName;
}
