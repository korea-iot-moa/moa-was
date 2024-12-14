package com.korit.moa.moa.dto.group.request;

import com.korit.moa.moa.entity.meetingGroup.GroupCategory;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategorySearchRequestDto {

    @NotNull
    private GroupCategory groupCategory;
    @NotNull
    private String groupAddress;

}
