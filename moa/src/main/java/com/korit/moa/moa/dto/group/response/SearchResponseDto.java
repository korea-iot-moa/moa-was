package com.korit.moa.moa.dto.group.response;

import com.korit.moa.moa.entity.meetingGroup.GroupCategory;
import com.korit.moa.moa.entity.meetingGroup.MeetingGroup;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchResponseDto {

    @NotBlank
    private Long groupId;
    @NotBlank
    private String groupTitle;
    @NotBlank
    private String groupAddress;
    @NotBlank
    private GroupCategory groupCategory;
    @NotBlank
    private String groupImage;
    @NotBlank
    private String groupDate;

    public SearchResponseDto(MeetingGroup result) {
        this.groupId = result.getGroupId();
        this.groupTitle = result.getGroupTitle();
        this.groupAddress = result.getGroupAddress();
        this.groupCategory = result.getGroupCategory();
        this.groupImage = result.getGroupImage();
        this.groupDate = result.getGroupDate();
    }


}
